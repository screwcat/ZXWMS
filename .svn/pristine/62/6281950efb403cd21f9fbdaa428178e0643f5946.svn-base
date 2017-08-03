package com.zx.emanage.loanreview.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditReturnSearchDao;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneContactDao;
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneMainDao;
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneModelDao;
import com.zx.emanage.loanreview.service.IWmsCreRevPhoneMainService;
import com.zx.emanage.loanreview.vo.WmsCreRevPhoneMainSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneContact;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneMain;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneModel;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevphonemainService")
public class WmsCreRevPhoneMainServiceImpl implements IWmsCreRevPhoneMainService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevPhoneMainServiceImpl.class);

    @Autowired
    private WmsCreRevPhoneMainDao wmscrerevphonemainDao;

    @Autowired
    private WmsCreRevPhoneContactDao wmscrerevphonecontactDao;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheaddao;

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;

    @Autowired
    private WmsCreRevPhoneModelDao wmscrerevphonemodelDao;// 电审模型
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private WmsCreCreditReturnSearchDao wmsCreCreditReturnSearchDao;
    
	@Autowired
	private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程


    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevPhoneMainSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevphonemainDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevPhoneMainSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevphonemainDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevphonemainDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevPhoneMain getInfoByPK(Integer wms_cre_rev_phone_main_id)
    {
        return wmscrerevphonemainDao.get(wms_cre_rev_phone_main_id);
    }

    @Override
    @Transactional
    public String save(String wms_cre_credit_head_id, String dsInfo, String lxrInfo,
                       WmsCreditWorkFlowVO approveWorkFlowVO, String phone_appro_eval, String documentary_opinion, String saveFlag, UserBean user,
                       WmsCreRevPhoneModel wmsCreRevPhoneModel)
    {
        String advice = "单据评价：";
        if ("1".equals(phone_appro_eval))
        {
            advice += "优<br />";
        }
        else if ("2".equals(phone_appro_eval))
        {
            advice += "良<br />";
        }
        else if ("3".equals(phone_appro_eval))
        {
            advice += "中<br />";
        }
        else if ("4".equals(phone_appro_eval))
        {
            advice += "差<br />";
        }
        approveWorkFlowVO.setYadvice(approveWorkFlowVO.getAdvice());
        approveWorkFlowVO.setAdvice(advice + approveWorkFlowVO.getAdvice());
        String resStr = "success";
        List<WmsCreRevPhoneMain> dslsArr = JsonUtil.jsonArrayToList(dsInfo, WmsCreRevPhoneMain.class);
        List<WmsCreRevPhoneContact> mcrpcList = JsonUtil.jsonArrayToList(lxrInfo, WmsCreRevPhoneContact.class);
        // 保存贷款电审审核——电审总体情况
        for (int i = 0; i < dslsArr.size(); i++)
        {
            WmsCreRevPhoneMain mcrpm = dslsArr.get(i);
            mcrpm.setCreate_user_id(user.getUserId());
            mcrpm.setCreate_user_name(user.getRealname());
            mcrpm.setEnable_flag("1");
            mcrpm.setCreate_timestamp(new Timestamp(new Date().getTime()));
            mcrpm.setLast_update_timestamp(new Timestamp(new Date().getTime()));
            System.out.println("********************************************");
            // System.out.println(mcrpm.getRec_approval_amount());
            System.out.println("********************************************");
            WmsCreRevPhoneMain wmsCreRevPhoneMain = new WmsCreRevPhoneMain();
            wmsCreRevPhoneMain.setWms_cre_credit_head_id(mcrpm.getWms_cre_credit_head_id());
            wmsCreRevPhoneMain.setWms_cre_credit_line_customer_change_head_id(mcrpm.getWms_cre_credit_line_customer_change_head_id());
            wmscrerevphonemainDao.delete(wmsCreRevPhoneMain);
            wmscrerevphonemainDao.save(mcrpm);
        }
        // 保存贷款电审审核——联系人情况
        for (int j = 0; j < mcrpcList.size(); j++)
        {
            WmsCreRevPhoneContact mcrpc = mcrpcList.get(j);
            WmsCreRevPhoneContact wmsCreRevPhoneContact = new WmsCreRevPhoneContact();
            wmsCreRevPhoneContact.setWms_cre_credit_head_id(mcrpc.getWms_cre_credit_head_id());
            wmsCreRevPhoneContact.setWms_cre_credit_line_customer_change_head_id(mcrpc.getWms_cre_credit_line_customer_change_head_id());
            wmsCreRevPhoneContact.setWms_cre_customer_change_line_contact_id(mcrpc.getWms_cre_customer_change_line_contact_id());
            wmscrerevphonecontactDao.delete(wmsCreRevPhoneContact);
            wmscrerevphonecontactDao.save(mcrpc);
        }
        if (wmsCreRevPhoneModel.getCar_market_value() != null)
        {
            wmscrerevphonemodelDao.delete(wmsCreRevPhoneModel);
            wmscrerevphonemodelDao.save(wmsCreRevPhoneModel);
        }
        if (saveFlag.equals("0"))
        {
            return resStr = "temOK";
        }
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("phone_appro_user_id", user.getUserId());
        params.put("phone_appro_user_name", user.getRealname());
        params.put("phone_appro_timestamp", sysTime);
        params.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        params.put("phone_appro_eval", phone_appro_eval);
        //单据意见
        if(documentary_opinion != null && documentary_opinion.length() > 0) {
        	params.put("documentary_opinion", documentary_opinion);
        }
        
        wmscrecreditheaddao.updateRecord(params);
        approveWorkFlowVO.setUser_id(user.getUserId());
        approveWorkFlowVO.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        resStr = creditWorkFlowService.telTeamCheckApproveWorkFlow(approveWorkFlowVO);

        return resStr;
    }

    @Override
    @Transactional
    public String saveHouse(String wms_cre_credit_head_id, String dsInfo, String lxrInfo,
                            WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String phone_appro_eval, String saveFlag,
                            UserBean user)
    {
        String advice = "单据评价：";
        if ("1".equals(phone_appro_eval))
        {
            advice += "优<br />";
        }
        else if ("2".equals(phone_appro_eval))
        {
            advice += "良<br />";
        }
        else if ("3".equals(phone_appro_eval))
        {
            advice += "中<br />";
        }
        else if ("4".equals(phone_appro_eval))
        {
            advice += "差<br />";
        }
        String adv = approveHouseWorkFlowVO.getAdvice();
        approveHouseWorkFlowVO.setAdvice(advice + approveHouseWorkFlowVO.getAdvice());
        String resStr = "success";
        List<WmsCreRevPhoneMain> dslsArr = JsonUtil.jsonArrayToList(dsInfo, WmsCreRevPhoneMain.class);
        List<WmsCreRevPhoneContact> mcrpcList = JsonUtil.jsonArrayToList(lxrInfo, WmsCreRevPhoneContact.class);
        // 保存贷款电审审核——电审总体情况
        for (int i = 0; i < dslsArr.size(); i++)
        {
            WmsCreRevPhoneMain mcrpm = dslsArr.get(i);
            mcrpm.setCreate_user_id(user.getUserId());
            mcrpm.setCreate_user_name(user.getRealname());
            mcrpm.setEnable_flag("1");
            mcrpm.setCreate_timestamp(new Timestamp(new Date().getTime()));
            mcrpm.setLast_update_timestamp(new Timestamp(new Date().getTime()));
            WmsCreRevPhoneMain wmsCreRevPhoneMain = new WmsCreRevPhoneMain();
            wmsCreRevPhoneMain.setWms_cre_credit_head_id(mcrpm.getWms_cre_credit_head_id());
            wmsCreRevPhoneMain.setWms_cre_credit_line_customer_change_head_id(mcrpm.getWms_cre_credit_line_customer_change_head_id());
            wmscrerevphonemainDao.delete(wmsCreRevPhoneMain);
            wmscrerevphonemainDao.save(mcrpm);
        }
        // 保存贷款电审审核——联系人情况
        for (int j = 0; j < mcrpcList.size(); j++)
        {
            WmsCreRevPhoneContact mcrpc = mcrpcList.get(j);
            WmsCreRevPhoneContact wmsCreRevPhoneContact = new WmsCreRevPhoneContact();
            wmsCreRevPhoneContact.setWms_cre_credit_head_id(mcrpc.getWms_cre_credit_head_id());
            wmsCreRevPhoneContact.setWms_cre_credit_line_customer_change_head_id(mcrpc.getWms_cre_credit_line_customer_change_head_id());
            wmsCreRevPhoneContact.setWms_cre_customer_change_line_contact_id(mcrpc.getWms_cre_customer_change_line_contact_id());
            wmscrerevphonecontactDao.delete(wmsCreRevPhoneContact);
            wmscrerevphonecontactDao.save(mcrpc);
        }
        if (saveFlag.equals("0"))
        {
            return resStr = "temOK";
        }
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("phone_appro_user_id", user.getUserId());
        params.put("phone_appro_user_name", user.getRealname());
        params.put("phone_appro_timestamp", sysTime);
        params.put("phone_appro_result", "true".equals(approveHouseWorkFlowVO.getPass()) ? "1" : "0");
        params.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        params.put("phone_appro_eval", phone_appro_eval);
        params.put("phone_appro_advice", adv);
        wmscrecreditheaddao.updateRecord(params);
        approveHouseWorkFlowVO.setUserId(user.getUserId().toString());
        approveHouseWorkFlowVO.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        //处理流程
        if (approveHouseWorkFlowVO.getPass() != null)
        {
        	approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
	        //数据来源1为pc 2为移动端
	        if("2".equals(approveHouseWorkFlowVO.getVersion_number())){//2016/5/10版本
	        	approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
	        	approveHouseWorkFlowVO.setDebtkey("15");//电审
            	wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
            	resStr ="success";
	        }else{//2016/2/10版本
	        	 resStr = houseCreditWorkFlowService.creTelTeamHouseCheckApproveWorkFlow(approveHouseWorkFlowVO);
	        }   
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevPhoneMain bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevphonemainDao.update(bean); // update a record replace
                                                  // columns, nonsupport null
                                                  // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreRevPhoneMain() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    @Override
    public List<WmsCreRevPhoneMain> getListByEntity(WmsCreRevPhoneMain queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<WmsCreRevPhoneMain> beanList = wmscrerevphonemainDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    @Transactional
    public String phoneToBack(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user)
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
        creditWorkFlowService.creCheckCreditSupply(approveWorkFlowVO);// 流程处理
        return resStr;
    }

    @Override
    @Transactional
    public String housePhoneToBack(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user)
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
