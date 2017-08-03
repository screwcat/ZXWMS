package com.zx.emanage.loanreview.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.google.gson.Gson;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditReturnSearchDao;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanreview.persist.WmsCreRevAttDao;
import com.zx.emanage.loanreview.persist.WmsCreRevCertificateMainDao;
import com.zx.emanage.loanreview.persist.WmsCreRevCertificateModelDao;
import com.zx.emanage.loanreview.service.IWmsCreRevCertificateMainService;
import com.zx.emanage.loanreview.service.IWmsCreRevWaterMainService;
import com.zx.emanage.loanreview.vo.WmsCreRevCertificateMainSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevAtt;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateMain;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateModel;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevcertificatemainService")
public class WmsCreRevCertificateMainServiceImpl implements IWmsCreRevCertificateMainService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevCertificateMainServiceImpl.class);

    @Autowired
    private WmsCreRevCertificateMainDao wmscrerevcertificatemainDao;

    @Autowired
    private WmsCreRevAttDao wmsCreRevAttDao;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheaddao;

    @Autowired
    private WmsCreRevCertificateModelDao wmscrerevcertificatemodelDao;

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private WmsCreCreditReturnSearchDao wmsCreCreditReturnSearchDao;
    
	@Autowired
	private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevCertificateMainSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevcertificatemainDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevCertificateMainSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevcertificatemainDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevcertificatemainDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevCertificateMain getInfoByPK(Integer wms_cre_rev_certificate_main_id)
    {
        return wmscrerevcertificatemainDao.get(wms_cre_rev_certificate_main_id);
    }

    @Override
    @Transactional
    public String saveFd(String zxsh, WmsHouseCreditWorkFlowVO vo, UserBean user, boolean isCommit)
    {
        String resStr = "";
        resStr = save(zxsh, Integer.valueOf(vo.getWms_cre_credit_head_id()), user);
        resStr = "temOK";
        if (isCommit != false)
        {
            resStr = "success";
            Timestamp sysTime = new Timestamp(System.currentTimeMillis());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("certificate_appro_user_id", user.getUserId());
            params.put("certificate_appro_user_name", user.getRealname());
            params.put("certificate_appro_timestamp", sysTime);
            params.put("wms_cre_credit_head_id", vo.getWms_cre_credit_head_id());
            params.put("certificate_appro_advice", vo.getAdvice());
            params.put("certificate_appro_result", "true".equals(vo.getPass()) ? "1" : "0");
            wmscrecreditheaddao.updateRecord(params);
            //处理流程
            if (vo.getPass() != null)
            {
                vo.setUserId(String.valueOf(user.getUserId()));
		        //数据来源1为pc 2为移动端
		        if("2".equals(vo.getVersion_number())){//2016/5/10版本
		        	vo.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
		        	vo.setDebtkey("16");
                	wmsLoanWorkFlowService.publicApproval(vo);
                	resStr ="success";
		        }else{//2016/2/10版本
		        	resStr = houseCreditWorkFlowService.creTeamHouseCheckApproveWorkFlow(vo);
		        }   
            }
        }
        return resStr;
    }

    @Override
    @Transactional
    public String saveXd(String zxsh, WmsCreRevCertificateModel bean, WmsCreditWorkFlowVO approveWorkFlowVO,
                         UserBean user, boolean isCommit)
    {
        String resStr = "temOK";
        resStr = save(zxsh, approveWorkFlowVO.getWms_cre_credit_head_id(), user);
        resStr = "temOK";
        // 信贷征信模型表
        wmscrerevcertificatemodelDao.deleteForId(bean.getWms_cre_credit_head_id());
        wmscrerevcertificatemodelDao.save(bean);
        if (isCommit != false)
        {
            Timestamp sysTime = new Timestamp(System.currentTimeMillis());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("certificate_appro_user_id", user.getUserId());
            params.put("certificate_appro_user_name", user.getRealname());
            params.put("certificate_appro_timestamp", sysTime);
            params.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmscrecreditheaddao.updateRecord(params);
            approveWorkFlowVO.setUser_id(user.getUserId());
            resStr = creditWorkFlowService.creTeamCheckApproveWorkFlow(approveWorkFlowVO);
            resStr = "success";
        }
        return resStr;
    }

    /**
     * 征信公用保存方法
     * 
     * @param zxsh
     * @param wms_cre_credit_head_id
     * @param user
     * @return
     */
    private String save(String zxsh, Integer wms_cre_credit_head_id, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        List<Map<String, Object>> zxshArr = jsonObjList(zxsh);
        for (Map<String, Object> singleZxshTab : zxshArr)
        {
            Integer wms_cre_credit_line_customer_change_head_id = ((Double) singleZxshTab.get("wms_cre_credit_line_customer_change_head_id")).intValue();
            Map<String, Object> tssmMap = (Map<String, Object>) singleZxshTab.get("tssm");
            WmsCreRevCertificateMain wmsCreRevCertificateMain = new WmsCreRevCertificateMain();
            wmsCreRevCertificateMain.setRemark((String) tssmMap.get("remark"));
            wmsCreRevCertificateMain.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
            wmsCreRevCertificateMain.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            params.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
            wmscrerevcertificatemainDao.deleteForId(params);
            ret = wmscrerevcertificatemainDao.save(wmsCreRevCertificateMain);
            List<Map<String, Object>> fileArr = (List<Map<String, Object>>) singleZxshTab.get("fileArr");// 附件
            params.put("data_type", "4");
            // 贷前审核附件表 删除方法
            wmsCreRevAttDao.deleteRecords(params);
            for (Map<String, Object> m : fileArr)
            {
                WmsCreRevAtt file = new WmsCreRevAtt();
                file.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                file.setData_type("4");
                file.setWms_cre_credit_line_customer_change_head_id(wms_cre_credit_line_customer_change_head_id);
                file.setAttachment_old_name((String) m.get("attachment_old_name"));
                file.setAttachment_new_name((String) m.get("attachment_new_name"));
                file.setAttachment_address((String) m.get("attachment_address"));
                file.setAttachment_type((String) m.get("attachment_type"));
                ret = wmsCreRevAttDao.save(file);
            }
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevCertificateMain bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevcertificatemainDao.update(bean); // update a record
                                                        // replace columns,
                                                        // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreRevCertificateMain() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevCertificateMain> getListByEntity(WmsCreRevCertificateMain queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevCertificateMain> beanList = wmscrerevcertificatemainDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 将传入的json字符串转换为元素为map集合的List集合
     * 
     * @param jsonArrStr
     * @return
     */
    private List<Map<String, Object>> jsonObjList(String jsonArrStr)
    {
        List<Map<String, Object>> jsonObjList = new ArrayList<Map<String, Object>>();
        List<?> jsonList = jsonToList(jsonArrStr);
        Gson gson = new Gson();
        for (Object object : jsonList)
        {
            String jsonStr = gson.toJson(object);
            Map<?, ?> json = jsonToMap(jsonStr);
            jsonObjList.add((Map<String, Object>) json);
        }
        return jsonObjList;
    }

    /**
     * 将传入的json字符串解析为Map集合
     * 
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr)
    {
        Map<?, ?> ObjectMap = null;
        Gson gson = new Gson();
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>()
        {
        }.getType();
        ObjectMap = gson.fromJson(jsonStr, type);
        return ObjectMap;
    }

    /**
     * 将传入的json字符串解析为List集合
     * 
     * @param jsonStr
     * @return
     */
    public static List<?> jsonToList(String jsonStr)
    {
        List<?> ObjectList = null;
        Gson gson = new Gson();
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<?>>()
        {
        }.getType();
        ObjectList = gson.fromJson(jsonStr, type);
        return ObjectList;
    }

    /**
     * 获取特殊说明信息
     */
    @Override
    public List<WmsCreRevCertificateMain> getInfoByEntity(WmsCreRevCertificateMain queryInfo)
    {
        return wmscrerevcertificatemainDao.getListByEntity(queryInfo);
    }

    @Override
    public String creditCertificateToBack(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user)
    {
        String resStr = "success";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("is_review_back", "1");
        paramMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheaddao.updateRecord(paramMap);
        //查询
        Map<String, Object> resMap = wmscrecreditheaddao.getWmsCreCredReturnInfoById(approveWorkFlowVO.getWms_cre_credit_head_id());
        Task task = taskService.createTaskQuery().taskId(approveWorkFlowVO.getTaskId()).singleResult();
        resMap.put("audit_group", task.getName());//退件审核组
        resMap.put("audit_reason", approveWorkFlowVO.getAdvice());//退件原因
        //插入信贷退件查询表
        wmsCreCreditReturnSearchDao.addWmsCreCreditReturnInfo(resMap);
        approveWorkFlowVO.setUser_id(user.getUserId());
        resStr = creditWorkFlowService.creCheckCreditSupply(approveWorkFlowVO);// 流程处理
        return resStr;
    }

    @Override
    public String houseCertificateToBack(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user)
    {
        String resStr = "success";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("is_review_back", "1");
        paramMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheaddao.updateRecord(paramMap);
        approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
        resStr = houseCreditWorkFlowService.creCheckHouseSupply(approveHouseWorkFlowVO);// 流程处理
        return resStr;
    }
}
