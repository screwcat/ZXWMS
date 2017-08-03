package com.zx.emanage.loanreview.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.google.gson.Gson;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditReturnSearchDao;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loanreview.persist.WmsCreRevAttDao;
import com.zx.emanage.loanreview.persist.WmsCreRevInspectionFamilyDao;
import com.zx.emanage.loanreview.persist.WmsCreRevInspectionMainDao;
import com.zx.emanage.loanreview.service.IWmsCreRevInspectionMainService;
import com.zx.emanage.util.gen.entity.WmsCreCreditAtt;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreRevAtt;
import com.zx.emanage.util.gen.entity.WmsCreRevInspectionFamily;
import com.zx.emanage.util.gen.entity.WmsCreRevInspectionMain;
import com.zx.emanage.loanreview.vo.WmsCreRevInspectionMainSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrerevinspectionmainService")
public class WmsCreRevInspectionMainServiceImpl implements IWmsCreRevInspectionMainService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevInspectionMainServiceImpl.class);

    @Autowired
    private WmsCreRevInspectionMainDao wmscrerevinspectionmainDao;

    @Autowired
    private WmsCreRevInspectionFamilyDao wmsCreRevInspectionFamilyDao;

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheadDao;

    @Autowired
    private WmsCreRevAttDao wmsCreRevAttDao;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private WmsCreCreditReturnSearchDao wmsCreCreditReturnSearchDao;
    
    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreRevInspectionMainSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrerevinspectionmainDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreRevInspectionMainSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrerevinspectionmainDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrerevinspectionmainDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreRevInspectionMain getInfoByPK(Integer wms_cre_rev_inspection_main_id)
    {
        return wmscrerevinspectionmainDao.get(wms_cre_rev_inspection_main_id);
    }

    /**
     * 验点信息数据会显示
     */
    @Override
    public java.util.Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id)
    {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        WmsCreRevInspectionMain wmsCreRevInspectionMain = wmscrerevinspectionmainDao.getByFK(paramMap);
        resultMap.put("result", wmsCreRevInspectionMain);
        List<Map<String, Object>> familyData = wmsCreRevInspectionFamilyDao.search(paramMap);
        resultMap.put("family", familyData);
        paramMap.put("data_type", "5");
        List<Map<String, Object>> wmsCreRevAtts = wmsCreRevAttDao.search(paramMap);
        resultMap.put("fileArrs", wmsCreRevAtts);
        WmsCreCreditHead wCreCreditHead = wmscrecreditheadDao.get(wms_cre_credit_head_id);
        wCreCreditHead.setTrial_interview_timestamp_str(new SimpleDateFormat("yyyy-MM-dd").format(wCreCreditHead.getTrial_interview_timestamp()));
        resultMap.put("wCreCreditHead", wCreCreditHead);
        return resultMap;
    }

    /**
     * 验点信息提交和暂存功能
     */
    @Override
    @Transactional
    public String save(WmsCreRevInspectionMain bean, WmsCreditWorkFlowVO approveWorkFlowVO, String ydfamliy,
                       Integer flag, UserBean user, String fileArrs)
    {
        String resStr = "temOK";
        int ret = 0;
        // 实现验点总体数据的存储
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        Map<String, Object> beanMap = new HashMap<>();
        beanMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmscrerevinspectionmainDao.deleteInfo(beanMap);
        // 存储验点结果
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setCreate_timestamp(sysTime);
        bean.setEnable_flag("1");
        ret = wmscrerevinspectionmainDao.save(bean);
        // 实现保存家庭背景资料
        List<WmsCreRevInspectionFamily> familyArr = JsonUtil.jsonArrayToList(ydfamliy, WmsCreRevInspectionFamily.class);
        // 根据贷款单据主键 删除家庭背景相应数据
        Map<String, Object> wFamilyMap = new HashMap<>();
        wFamilyMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmsCreRevInspectionFamilyDao.deleteInfo(wFamilyMap);
        for (WmsCreRevInspectionFamily family : familyArr)
        {
            WmsCreRevInspectionFamily wFamily = new WmsCreRevInspectionFamily();
            // 贷款单主键
            wFamily.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            // 家庭关系
            wFamily.setContact_relation_type(family.getContact_relation_type());
            // 姓名
            wFamily.setName(family.getName());
            // 年龄
            wFamily.setAge(family.getAge());
            // 工作单位名称
            wFamily.setWork_unit_full_name(family.getWork_unit_full_name());
            // 个人收入情况
            wFamily.setPersonal_income(family.getPersonal_income());
            // 车辆
            wFamily.setCar_brand(family.getCar_brand());
            // 房产
            wFamily.setHouse_stat(family.getHouse_stat());
            // 是否有效
            wFamily.setEnable_flag("1");
            ret = wmsCreRevInspectionFamilyDao.save(wFamily);
        }
        // 获取附件信息
        List<WmsCreRevAtt> fileArrss = JsonUtil.jsonArrayToList(fileArrs, WmsCreRevAtt.class); // 前台json附件路径数据转换为list
        // 删除验点附件中的数据
        Map<String, Object> fileMap = new HashMap<>();
        fileMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        fileMap.put("data_type", "5");
        wmsCreRevAttDao.deleteRecords(fileMap);
        //客户变更表主键
        WmsCreCreditLineCustomerChangeHead wChangeHead = new WmsCreCreditLineCustomerChangeHead();
        wChangeHead.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
        wChangeHead.setIs_major("1");
        List<WmsCreCreditLineCustomerChangeHead> creCreditLineCustomerChangeHead =wmsCreCreditLineCustomerChangeHeadDao.getListByEntity(wChangeHead);
        // 实现存储附件信息
        for (int i = 0; i < fileArrss.size(); i++)
        {
            WmsCreRevAtt wRevAtt = new WmsCreRevAtt();
            wRevAtt.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            wRevAtt.setData_type("5");
            wRevAtt.setWms_cre_credit_line_customer_change_head_id(creCreditLineCustomerChangeHead.get(0).getWms_cre_credit_line_customer_change_head_id());
            wRevAtt.setAttachment_type(fileArrss.get(i).getAttachment_type());
            wRevAtt.setAttachment_new_name(fileArrss.get(i).getAttachment_new_name());
            wRevAtt.setAttachment_old_name(fileArrss.get(i).getAttachment_old_name());
            wRevAtt.setAttachment_address(fileArrss.get(i).getAttachment_address());
            wmsCreRevAttDao.save(wRevAtt);
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        if (flag == 1)
        {
            resStr = "success";
            // 验点流程存储
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("inspection_appro_user_id", user.getUserId());
            params.put("inspection_appro_user_name", user.getRealname());
            params.put("inspection_appro_timestamp", sysTime);
            params.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            wmscrecreditheadDao.updateRecord(params);
            // 存储流程
            approveWorkFlowVO.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            approveWorkFlowVO.setUser_id(user.getUserId());
            resStr = creditWorkFlowService.ydTeamCheckApproveWorkFlow(approveWorkFlowVO);
        }

        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreRevInspectionMain bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrerevinspectionmainDao.update(bean); // update a record
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
     * WmsCreRevInspectionMain() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreRevInspectionMain> getListByEntity(WmsCreRevInspectionMain queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreRevInspectionMain> beanList = wmscrerevinspectionmainDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public String creditydToBack(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user)
    {
        String resStr = "success";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("is_review_back", "1");
        paramMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheadDao.updateRecord(paramMap);
        //查询
        Map<String, Object> resMap = wmscrecreditheadDao.getWmsCreCredReturnInfoById(approveWorkFlowVO.getWms_cre_credit_head_id());
        Task task = taskService.createTaskQuery().taskId(approveWorkFlowVO.getTaskId()).singleResult();
        resMap.put("audit_group", task.getName());//退件审核组
        resMap.put("audit_reason", approveWorkFlowVO.getAdvice());//退件原因
        //插入信贷退件查询表
        wmsCreCreditReturnSearchDao.addWmsCreCreditReturnInfo(resMap);
        approveWorkFlowVO.setUser_id(user.getUserId());
        resStr = creditWorkFlowService.creCheckCreditSupply(approveWorkFlowVO);// 信贷流程处理
        return resStr;
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
    private static Map<?, ?> jsonToMap(String jsonStr)
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
    private static List<?> jsonToList(String jsonStr)
    {
        List<?> ObjectList = null;
        Gson gson = new Gson();
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<?>>()
        {
        }.getType();
        ObjectList = gson.fromJson(jsonStr, type);
        return ObjectList;
    }

}
