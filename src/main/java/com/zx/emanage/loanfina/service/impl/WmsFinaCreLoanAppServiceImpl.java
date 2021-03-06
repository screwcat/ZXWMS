package com.zx.emanage.loanfina.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerDataAttachmentDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingApplyAttDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingApprovalInfoDao;
import com.zx.emanage.cremanage.service.IWmsCarLoanWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WorkflowRoleHelp;
import com.zx.emanage.loanappro.persist.WmsCreApproBorrowProtocolDao;
import com.zx.emanage.loanappro.persist.WmsCreApproServiceProtocolDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditApproDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditServiceTypeDao;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreLoanAppAttDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreLoanAppDao;
import com.zx.emanage.loanfina.persist.WmsFinaCrePeriodRepayDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.loanfina.service.IWmsFinaCreLoanAppService;
import com.zx.emanage.loanfina.vo.WmsFinaCreCancelBeanVo;
import com.zx.emanage.loanfina.vo.WmsFinaCreLoanAppSearchBeanVO;
import com.zx.emanage.remind.persist.WmsCreCreditNotaryWarnDao;
import com.zx.emanage.remind.service.IWmsCreCreditNotaryWarnService;
import com.zx.emanage.remind.vo.CreditMessageToRepayWarnBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsCreApproServiceProtocol;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotaryWarn;
import com.zx.emanage.util.gen.entity.WmsCreCreditServiceType;
import com.zx.emanage.util.gen.entity.WmsCreHousingApprovalInfo;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanApp;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanAppAtt;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * @ClassName: WmsFinaCreLoanAppServiceImpl
 * @Description: 内容摘要：放款申请相关Service实现类
 * @author wangyihan
 * @date 2016年12月23日
 * @version V1.0
 * history:
 * 1、2016年12月23日 wangyihan 创建文件
 */
@Service("wmsfinacreloanappService")
public class WmsFinaCreLoanAppServiceImpl implements IWmsFinaCreLoanAppService
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCreLoanAppServiceImpl.class);

    @Autowired
    private WmsFinaCreLoanAppDao wmsfinacreloanappDao;

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Autowired
    private WmsFinaCreLoanAppAttDao wmsfinacreloanappattDao;

    @Autowired
    private WmsCreCreditLineCustomerDataAttachmentDao wmscrecreditlinecustomerdataattachmentlistDao;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;
    @Autowired
    private  WmsCreCreditHeadDao wmsCreCreditHeadDao;
    @Autowired
    private  IWmsCarLoanWorkFlowService carLoanWorkFlowService;//流程
    @Autowired
    private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程
    
    @Autowired
    private WmsCreApproBorrowProtocolDao wmsCreApproBorrowProtocolDao;
    
    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;
    
    @Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;
    @Autowired
    private WmsCreApproServiceProtocolDao wmsCreApproServiceProtocolDao;//合同居间服务表 
    @Autowired
    private PmPersonnelDao pmPersonnelDao;
    @Autowired
    private WmsCreHousingApplyAttDao wmsCreHousingApplyAttDao;
    
    @Autowired
    private WmsCreApproBorrowProtocolDao wmscreapproborrowprotocolDao;
    
    @Autowired
    private WmsCreApproServiceProtocolDao wmscreapproserviceprotocolDao;
    
    @Autowired
    private WmsFinaCreRepayDao wmsfinacrerepayDao;
    
    @Autowired
    private WmsFinaCrePeriodRepayDao wmsfinacreperiodrepayDao;
    
    @Autowired
    private IWmsCreCreditNotaryWarnService wmsCreCreditNotaryWarnService;
    
    @Autowired
    private WmsCreCreditApproDao wmsCreCreditApproDao;
    
    @Autowired
    private WmsSysPropertyDao wmssyspropertyDao;
    
    @Autowired
    private WmsCreCreditNotaryWarnDao  wmsCreCreditNotaryWarnDao;

    @Autowired
    private WmsCreCreditServiceTypeDao wmsCreCreditServiceTypeDao;
    
    @Autowired
    private WmsFinaCreLoanAppDao wmsFinaCreLoanAppDao;

    @Autowired
    private WmsCreHousingApprovalInfoDao wmsCreHousingApprovalInfoDao;// 房贷——审批信息表
    
    @Override
    public Map<String, Object> getListWithoutPaging(WmsFinaCreLoanAppSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsfinacreloanappDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsFinaCreLoanAppSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsfinacreloanappDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsfinacreloanappDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsFinaCreLoanApp getInfoByPK(Integer wms_fina_cre_loan_app)
    {
        return wmsfinacreloanappDao.get(wms_fina_cre_loan_app);
    }

    @Override
    @Transactional
    public String save(WmsFinaCreLoanApp bean, UserBean user, int wms_cre_credit_head_id, String taskId, String fileArr)
    {
        String resStr = "success";

        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        List<WmsFinaCreLoanAppAtt> attachlist = JsonUtil.jsonArrayToList(fileArr, WmsFinaCreLoanAppAtt.class);

        int ret = 0;
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setCreate_timestamp(sysTime);
        bean.setLast_update_user_id(user.getUserId());
        bean.setLast_update_timestamp(sysTime);
        bean.setEnable_flag("1");
        wmsfinacreloanappDao.deleteByFk(wms_cre_credit_head_id);
        ret = wmsfinacreloanappDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        bean = wmsfinacreloanappDao.getWmsFinaCreLoanAppByFk(wms_cre_credit_head_id);
        wmsfinacreloanappattDao.deleteByFk(bean.getWms_fina_cre_loan_app());
        for (int i = 0; i < attachlist.size(); i++)
        {
            WmsFinaCreLoanAppAtt wmsFinaCreLoanAppAtt = attachlist.get(i);
            wmsFinaCreLoanAppAtt.setWms_fina_cre_loan_app_id(bean.getWms_fina_cre_loan_app());
            wmsFinaCreLoanAppAtt.setCreate_user_id(user.getUserId()); // 创建人id
            wmsFinaCreLoanAppAtt.setCreate_user_name(user.getRealname());// 创建人名字
            wmsFinaCreLoanAppAtt.setCreate_timestamp(sysTime);// 创建时间
            wmsFinaCreLoanAppAtt.setEnable_flag("1");// 是否有效
            wmsfinacreloanappattDao.save(wmsFinaCreLoanAppAtt);
        }
        WmsCreditWorkFlowVO approveWorkFlowVO = new WmsCreditWorkFlowVO();
        approveWorkFlowVO.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        approveWorkFlowVO.setTaskId(taskId);
        approveWorkFlowVO.setUser_id(user.getUserId());
        approveWorkFlowVO.setPass("true");
        creditWorkFlowService.makeLoansAprove(approveWorkFlowVO);
        return resStr;
    }
    /**
     * 放款申请作废
     */
    @Override
    @Transactional
    public String doSaveCancel(WmsFinaCreCancelBeanVo bean,UserBean user, WmsCreCreditHead hbean)
    {
        String resStr = "success";

        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        hbean.setNullify_type("4");// 放款申请
        hbean.setNullify_user_name(user.getRealname());
        hbean.setNullify_user_id(user.getUserId());
        hbean.setNullify_timestamp(sysTime);
        hbean.setNullify_reason(bean.getAdvice());
        int ret= wmsCreCreditHeadDao.update(hbean);//更新贷款主表
        if (ret == 0)
        {
            resStr = "error";
            return resStr;
        }
        WmsCreditWorkFlowVO approveWorkFlowVO = new WmsCreditWorkFlowVO();
        approveWorkFlowVO.setWms_cre_credit_head_id(hbean.getWms_cre_credit_head_id());
        approveWorkFlowVO.setTaskId(bean.getTaskId());
        approveWorkFlowVO.setUser_id(user.getUserId());
        approveWorkFlowVO.setAdvice(bean.getAdvice());
        WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(hbean.getWms_cre_credit_head_id());
        if(wmsCreCreditHead.getCreate_timestamp().before(new Timestamp(bean.getTimestamp_val().getTime()))){
        	 approveWorkFlowVO.setPass("true");//单据作废
        }else{
        	approveWorkFlowVO.setPass("false");//单据作废	
        }
        creditWorkFlowService.makeLoansAprove(approveWorkFlowVO);
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsFinaCreLoanApp bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsfinacreloanappDao.update(bean); // update a record replace
                                                 // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsFinaCreLoanApp() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsFinaCreLoanApp> getListByEntity(WmsFinaCreLoanApp queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsFinaCreLoanApp> beanList = wmsfinacreloanappDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    @Transactional
    public String housingLoanAppSave(WmsFinaCreLoanApp bean, UserBean user, WmsCreCreditHeadHouseSearchBeanVO beanvo)
    {
        String resStr = "success";

        //并发校验(单据状态)
        WmsCreCreditHead wmsCreCreditHead = this.wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
        if("2".equals(wmsCreCreditHead.getVersion_number())&&!wmsCreCreditHead.getBill_status().equals("F")) {
            return "change";
        }
        
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        List<WmsFinaCreLoanAppAtt> attachlist = JsonUtil.jsonArrayToList(beanvo.getFileArr(), WmsFinaCreLoanAppAtt.class);

        int ret = 0;
        bean.setBridge_amount(floor(bean.getBridge_amount(), 8));
        bean.setFees(floor(bean.getFees(), 8));
        bean.setHouse_area(floor(bean.getHouse_area(), 8));
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setCreate_timestamp(sysTime);
        bean.setLast_update_user_id(user.getUserId());
        bean.setLast_update_timestamp(sysTime);
        bean.setEnable_flag("1");
        wmsfinacreloanappDao.deleteByFk(bean.getWms_cre_credit_head_id());
        ret = wmsfinacreloanappDao.save(bean);

        if (ret == 0)
        {
            resStr = "error";
        }
        bean = wmsfinacreloanappDao.getWmsFinaCreLoanAppByFk(bean.getWms_cre_credit_head_id());
        wmsfinacreloanappattDao.deleteByFk(bean.getWms_fina_cre_loan_app());
        for (int i = 0; i < attachlist.size(); i++)
        {
            WmsFinaCreLoanAppAtt wmsFinaCreLoanAppAtt = attachlist.get(i);
            wmsFinaCreLoanAppAtt.setWms_fina_cre_loan_app_id(bean.getWms_fina_cre_loan_app());
            wmsFinaCreLoanAppAtt.setCreate_user_id(user.getUserId()); // 创建人id
            wmsFinaCreLoanAppAtt.setCreate_user_name(user.getRealname());// 创建人名字
            wmsFinaCreLoanAppAtt.setCreate_timestamp(sysTime);// 创建时间
            wmsFinaCreLoanAppAtt.setEnable_flag("1");// 是否有效
            wmsfinacreloanappattDao.save(wmsFinaCreLoanAppAtt);
        }
        WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
        approveHouseWorkFlowVO.setWms_cre_credit_head_id(String.valueOf(bean.getWms_cre_credit_head_id()));
        approveHouseWorkFlowVO.setTaskId(beanvo.getTaskId());
        approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
        if("2".equals(beanvo.getEdition_num())){//1为旧流程2为新流程
	        //数据来源1为pc 2为移动端
	        if("2".equals(beanvo.getVersion_number())){//2016/5/10版本
	        	 approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
	        }else{//2016/2/10版本
	        	 approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
	        }
        	//approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
        	approveHouseWorkFlowVO.setDebtkey("9");
        	approveHouseWorkFlowVO.setPass("true");
        	wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
        }else if("1".equals(beanvo.getEdition_num())){
        	houseCreditWorkFlowService.theMortgageLoanWorkFlow(approveHouseWorkFlowVO);
        }
        
        return resStr;
    }
    
    
    
    /**
     * 车贷放款申请
     * baisong
     */
    @Override
    @Transactional
    public String carLoanAppSave(WmsFinaCreLoanApp bean, UserBean user, String taskId, String fileArr)
    {
        String resStr = "success";

        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        List<WmsFinaCreLoanAppAtt> attachlist = JsonUtil.jsonArrayToList(fileArr, WmsFinaCreLoanAppAtt.class);

        int ret = 0;
        bean.setBridge_amount(floor(bean.getBridge_amount(), 8));
        bean.setFees(floor(bean.getFees(), 8));
        bean.setHouse_area(floor(bean.getHouse_area(), 8));
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setCreate_timestamp(sysTime);
        bean.setLast_update_user_id(user.getUserId());
        bean.setLast_update_timestamp(sysTime);
        bean.setEnable_flag("1");
        wmsfinacreloanappDao.deleteByFk(bean.getWms_cre_credit_head_id());
        ret = wmsfinacreloanappDao.save(bean);

        if (ret == 0)
        {
            resStr = "error";
        }
        bean = wmsfinacreloanappDao.getWmsFinaCreLoanAppByFk(bean.getWms_cre_credit_head_id());
        wmsfinacreloanappattDao.deleteByFk(bean.getWms_fina_cre_loan_app());
        for (int i = 0; i < attachlist.size(); i++)
        {
            WmsFinaCreLoanAppAtt wmsFinaCreLoanAppAtt = attachlist.get(i);
            wmsFinaCreLoanAppAtt.setWms_fina_cre_loan_app_id(bean.getWms_fina_cre_loan_app());
            wmsFinaCreLoanAppAtt.setCreate_user_id(user.getUserId()); // 创建人id
            wmsFinaCreLoanAppAtt.setCreate_user_name(user.getRealname());// 创建人名字
            wmsFinaCreLoanAppAtt.setCreate_timestamp(sysTime);// 创建时间
            wmsFinaCreLoanAppAtt.setEnable_flag("1");// 是否有效
            wmsfinacreloanappattDao.save(wmsFinaCreLoanAppAtt);
        }
        /*WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
        approveHouseWorkFlowVO.setWms_cre_credit_head_id(String.valueOf(bean.getWms_cre_credit_head_id()));
        approveHouseWorkFlowVO.setTaskId(taskId);
        approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
        houseCreditWorkFlowService.theMortgageLoanWorkFlow(approveHouseWorkFlowVO);*/
		 WmsCarLoanWorkFlowVO wVo=new WmsCarLoanWorkFlowVO();//流程
		 //给流程赋值
         wVo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
         wVo.setPass("true");//是否通过
         wVo.setAdvice("通过");//意见
         wVo.setUserId(String.valueOf(user.getUserId()));//操作人id
         wVo.setTaskId(taskId);//流程节点id
        //carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        carLoanWorkFlowService.carLoanApprovalProcess(wVo,"8");//流程
        return resStr;
    }

    private BigDecimal floor(BigDecimal num, int digit)
    {
        String numStr = String.valueOf(num);
        if (numStr.length() - numStr.lastIndexOf(".") > digit + 1)
        {
            if (Integer.parseInt("" + numStr.charAt(numStr.lastIndexOf(".") + digit + 1)) < 5)
            {
                return BigDecimal.valueOf(Double.parseDouble(numStr.substring(0, numStr.lastIndexOf(".") + digit + 1)));
            }
            else
            {
                return BigDecimal.valueOf(Double.parseDouble(numStr.substring(0, numStr.lastIndexOf(".") + digit + 1)) + 0.00000001);
            }
        }
        else
        {
            return num;
        }
    }
    
    /**
     * moa调用wms方法:放款申请结果保存
     * @param WmsFinaCreLoanAppSearchBeanVO queryInfo
     * @return Map<String, Object> resMap
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @Override
    @Transactional
    public Map<String, Object> sendLoanApprovalInfoUp(WmsFinaCreLoanAppSearchBeanVO queryInfo) throws Exception {
        Map<String, Object> resMap = new HashMap<String, Object>();
  
            long now_long = System.currentTimeMillis();
            java.sql.Date now_date = new java.sql.Date(now_long);
            java.sql.Timestamp now_timestamp = new java.sql.Timestamp(now_long);
            BigDecimal weiyuejin=new BigDecimal(0.05);//违约金
            
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<String, Object>();
            map = mapper.readValue(queryInfo.getLoanApprovalInfoJson(), new TypeReference<Map<String, Object>>(){});
            Integer wms_cre_credit_head_id = (int)map.get("wms_cre_credit_head_id");
            WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(wms_cre_credit_head_id);
            Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
            customerChangeParamMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            customerChangeParamMap.put("is_major", "1");
            customerChangeParamMap.put("enable_flag", "1");
            List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
            
            //将原放款申请信息逻辑删除
            Map<String, Object> deleteWmsFinaCreLoanAppMap = new HashMap<String, Object>();
            deleteWmsFinaCreLoanAppMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            this.wmsfinacreloanappDao.deleteByWmsCreCreditHeadId(deleteWmsFinaCreLoanAppMap);
            
            //保存放款申请信息
            WmsFinaCreLoanApp wmsFinaCreLoanApp = new WmsFinaCreLoanApp();
            wmsFinaCreLoanApp.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmsFinaCreLoanApp.setLoan_amount(new BigDecimal(map.get("loan_amount").toString()));
            wmsFinaCreLoanApp.setLoan_date(null);
            wmsFinaCreLoanApp.setRemark((String)map.get("remark"));
            wmsFinaCreLoanApp.setCreate_date(now_date);
            wmsFinaCreLoanApp.setDeduction_of_interest(new BigDecimal(map.get("deduction_of_interest").toString()));
            wmsFinaCreLoanApp.setFees(new BigDecimal(map.get("fees").toString()));
            wmsFinaCreLoanApp.setFees_detail((String)map.get("fees_detail"));
            wmsFinaCreLoanApp.setHouse_area(map.get("house_building_area") == null ? null : new BigDecimal(map.get("house_building_area").toString()));
            wmsFinaCreLoanApp.setLoan_amount_caps((String)map.get("loan_amount_caps"));
            wmsFinaCreLoanApp.setCreate_user_id((Integer)map.get("user_id"));
            wmsFinaCreLoanApp.setCreate_user_name((String)map.get("user_name"));
            wmsFinaCreLoanApp.setCreate_timestamp(now_timestamp);
            wmsFinaCreLoanApp.setLast_update_user_id((Integer)map.get("user_id"));
            wmsFinaCreLoanApp.setLast_update_timestamp(now_timestamp);
            wmsFinaCreLoanApp.setEnable_flag("1");
            wmsFinaCreLoanApp.setIt_cost_fee(new BigDecimal(map.get("it_cost_fee").toString()));
            wmsFinaCreLoanApp.setExpedited_fee(new BigDecimal(map.get("expedited_fee").toString()));
            wmsFinaCreLoanApp.setCre_type_name((String)map.get("cre_type_name"));
            wmsFinaCreLoanApp.setNotary_is_finish((int)map.get("notary_is_finish"));
            wmsFinaCreLoanApp.setHe_is_finish((int)map.get("he_is_finish"));
            
            this.wmsfinacreloanappDao.save(wmsFinaCreLoanApp);
            
            //保存合同信息(如果已生成合同则更新合同,如果未生成合同则新生成合同)
            Map<String, Object> contractParamMap = new HashMap<String, Object>();
            contractParamMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            contractParamMap.put("sortname", "");
            contractParamMap.put("sortorder", "");
            contractParamMap.put("offset", null);
            contractParamMap.put("pagesize", null);
            
            List<Map<String, Object>> contractList = this.wmsCreApproBorrowProtocolDao.search(contractParamMap);
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
            if(null == contractList || contractList.size() == 0) {//生成合同
                WmsCreApproBorrowProtocol wmsCreApproBorrowProtocol = new WmsCreApproBorrowProtocol();
                // 合同编号自动生成
                String yearnum = CodeNoUtil.getProCreCode();// 合同编号
                wmsCreApproBorrowProtocol.setProtocol_id_year_num(yearnum);
                String code[] = yearnum.split("年第");
                if (code.length > 1)
                {
                    String protocol_id_year = code[0];
                    String num = code[1];
                    String protocol_id_num = num.substring(0, num.length() - 1);
                    wmsCreApproBorrowProtocol.setProtocol_id_year(protocol_id_year);// 合同编号 年
                    wmsCreApproBorrowProtocol.setProtocol_id_num(protocol_id_num);// 合同编号 号
                }
                wmsCreApproBorrowProtocol.setProtocol_date(now_date);
                wmsCreApproBorrowProtocol.setCreditor_name(null);//债权人姓名
                wmsCreApproBorrowProtocol.setCreditor_identity_id(null);//债权人身份证号码
                wmsCreApproBorrowProtocol.setDebtor_name(customerChangeList.get(0).get("customer_name").toString());//债务人姓名
                if(!"".equals(customerChangeList.get(0).get("mobile_telephone1"))&&customerChangeList.get(0).get("mobile_telephone1")!=null){
                	 wmsCreApproBorrowProtocol.setDebtor_tel(customerChangeList.get(0).get("mobile_telephone1").toString());//债务人电话	
                }
                wmsCreApproBorrowProtocol.setDebtor_identity_id(null);//债务人身份证号码
                wmsCreApproBorrowProtocol.setCreditor_address(null);//债权人通讯地址
                wmsCreApproBorrowProtocol.setDebtor_address(null);//债务人通讯地址
                wmsCreApproBorrowProtocol.setCreditor_tel(null);//债权人联系电话
                wmsCreApproBorrowProtocol.setDebtor_fixed_line(null);//债务人固定电话
                wmsCreApproBorrowProtocol.setPrincipal_caps(null);//借款本金(大写)
                
                //wmsCreApproBorrowProtocol.setPrincipal_lower(null);//借款本金(小写)
                
                wmsCreApproBorrowProtocol.setPrincipal_lower(new BigDecimal(map.get("principal_lower").toString()));//借款本金(小写)
                wmsCreApproBorrowProtocol.setBorrow_deadline((int)map.get("borrow_deadline"));//借款期限
                wmsCreApproBorrowProtocol.setBorrow_begin_date(new java.sql.Date(format.parse(map.get("borrow_begin_date").toString()).getTime()));//借款起始日期
                wmsCreApproBorrowProtocol.setBorrow_end_date(new java.sql.Date(format.parse(map.get("borrow_end_date").toString()).getTime()));//借款终止日期
                wmsCreApproBorrowProtocol.setBorrow_purpose(null);//借款用途
                wmsCreApproBorrowProtocol.setBorrow_interest(new BigDecimal(map.get("borrow_interest").toString()));//借款利息(%)
                wmsCreApproBorrowProtocol.setRefund_bank(null);//还款银行
                wmsCreApproBorrowProtocol.setRefund_number(null);//还款账号
                wmsCreApproBorrowProtocol.setRefund_name(null);//还款户名
                
                //wmsCreApproBorrowProtocol.setRefund_limit_month(null);//月还款金额
                
                wmsCreApproBorrowProtocol.setRefund_day(null);//还款日
                wmsCreApproBorrowProtocol.setLiquidated_damages(new BigDecimal(map.get("liquidated_damages").toString()).longValue());//违约金
                wmsCreApproBorrowProtocol.setWms_cre_credit_head_id(wms_cre_credit_head_id);//信用贷款信息表主键id
                wmsCreApproBorrowProtocol.setCreate_user_id((Integer)map.get("user_id"));//创建人主键
                wmsCreApproBorrowProtocol.setCreate_user_name((String)map.get("user_name"));//创建人姓名
                wmsCreApproBorrowProtocol.setCreate_timestamp(now_timestamp);//创建时间
                wmsCreApproBorrowProtocol.setLast_update_user_id((Integer)map.get("user_id"));//最近修改人
                wmsCreApproBorrowProtocol.setLast_update_timestamp(now_timestamp);//最近修改时间
                wmsCreApproBorrowProtocol.setEnable_flag("1");//是否有效标志(0:无效，1:有效)
                wmsCreApproBorrowProtocol.setProtocol_type(null);//合同类型
                wmsCreApproBorrowProtocol.setCom_debtor_name(null);//共同债务人姓名
                wmsCreApproBorrowProtocol.setCom_debtor_identity_id(null);//共同债务人身份证号
                wmsCreApproBorrowProtocol.setCom_debtor_address(null);//共同债务人通讯地址
                wmsCreApproBorrowProtocol.setCom_debtor_tel(null);//共同债务人电话
                wmsCreApproBorrowProtocol.setCom_debtor_fixed_line(null);//共同债务人固定联系电话
                wmsCreApproBorrowProtocol.setRefund_principal_month_lower(null);//月还款本金小写
                wmsCreApproBorrowProtocol.setRefund_principal_month_caps(null);//每月还款本金大写
                wmsCreApproBorrowProtocol.setRefund_interest_month_lower(null);//月还款利息小写
                wmsCreApproBorrowProtocol.setRefund_interest_month_caps(null);//月还款利息大写
                wmsCreApproBorrowProtocol.setRefund_limit_month_caps(null);//月还款额大写
                wmsCreApproBorrowProtocol.setCompensate(null);//提前还款补偿
                wmsCreApproBorrowProtocol.setYuqi_damages(weiyuejin);//逾期违约金利率
                wmsCreApproBorrowProtocol.setCompensate_caps(null);//提前还款补偿大写
                wmsCreApproBorrowProtocol.setYuqi_damages_caps(null);//逾期违约金利率大写
                wmsCreApproBorrowProtocol.setBorrow_interest_caps(null);//月利率大写
                wmsCreApproBorrowProtocol.setPrincipal(null);//其中本金总额
                wmsCreApproBorrowProtocol.setInterest(null);//其中利息总额
                wmsCreApproBorrowProtocol.setOrg_repay_principal(null);//其中本金
                wmsCreApproBorrowProtocol.setOrg_repay_interest(null);//每月利息
                wmsCreApproBorrowProtocol.setRefund_limit_month_lower(null);//月还款额小写
                wmsCreApproBorrowProtocol.setPlan_borrow_date(null);//计划还款日期
                wmsCreApproBorrowProtocol.setLoan_amount(new BigDecimal(map.get("loan_amount").toString()));//转账金额(借据中的转账金额)
                wmsCreApproBorrowProtocol.setCompany(null);//公司名头
                wmsCreApproBorrowProtocol.setCheque_number(null);//支票张数
                wmsCreApproBorrowProtocol.setBill_number(null);//票据号码
                wmsCreApproBorrowProtocol.setDeduct_money(null);//收据中的现金现金
                wmsCreApproBorrowProtocol.setC_house_address(null);//丙的居所
                wmsCreApproBorrowProtocol.setA_c_relation(null);//乙丙关系
                wmsCreApproBorrowProtocol.setSecond_house_address(null);//第二居所
                wmsCreApproBorrowProtocol.setArbitrate_name(null);//仲裁名称
                wmsCreApproBorrowProtocol.setCourt_name(null);//法院名称
                wmsCreApproBorrowProtocol.setDispute_solve(null);//争论方式
                wmsCreApproBorrowProtocol.setHouse_sale(null);//房产拍卖
                wmsCreApproBorrowProtocol.setHouse_estimate(null);//房产评估
                wmsCreApproBorrowProtocol.setSign_place(null);//地点
                wmsCreApproBorrowProtocol.setReplace_pay_money_caps(null);//代还款金额大写
                wmsCreApproBorrowProtocol.setReplace_pay_money_lower(null);//代还款金额小写
                wmsCreApproBorrowProtocol.setCash_transfer_lower(null);//信贷合同  -支票借据中的 现在/转账
                wmsCreApproBorrowProtocol.setPayment_contract_type((String)map.get("payment_contract_type"));//还款类型： 1代表等额本息 2代表先息后本
                wmsCreApproBorrowProtocol.setWeiyuejin(weiyuejin);//每日违约金利率
                wmsCreApproBorrowProtocol.setFirst_refund_date(null);//第一个还款日
                wmsCreApproBorrowProtocol.setSecond_refund_date(null);//第二个还款日
                wmsCreApproBorrowProtocol.setCreditor_loan_name(null);//甲方放款账户名
                wmsCreApproBorrowProtocol.setCreditor_loan_number(null);//甲方放款账号
                wmsCreApproBorrowProtocol.setCreditor_loan_bank(null);//甲方放款账户开户行
                wmsCreApproBorrowProtocol.setDebtor_loan_name(null);//乙方收款账户名
                wmsCreApproBorrowProtocol.setDebtor_loan_number(null);//乙方收款账号
                wmsCreApproBorrowProtocol.setDebtor_loan_bank(null);//乙方收款账户开户行
                wmsCreApproBorrowProtocol.setProtocol_refund_interest_month(null);//合同中月利息
                wmsCreApproBorrowProtocol.setProtocol_d_num(null);//抵押合同编号
                wmsCreApproBorrowProtocol.setCreditor_zip_code(null);//债权人邮编
                wmsCreApproBorrowProtocol.setDebtor_zip_code(null);//债务人邮编
                wmsCreApproBorrowProtocol.setEmbezzlement_damages(null);//挪用借款违约金
                wmsCreApproBorrowProtocol.setLoan_currency(null);//借款币种
                wmsCreApproBorrowProtocol.setDebtor_residence_address(null);//债务人居住地址
                wmsCreApproBorrowProtocolDao.save(wmsCreApproBorrowProtocol);
                //居间服务表
                WmsCreApproServiceProtocol wmsCreApproServiceProtocol = new WmsCreApproServiceProtocol();
                wmsCreApproServiceProtocol.setEnable_flag("1");//数据状态
                wmsCreApproServiceProtocol.setWms_cre_credit_head_id(wms_cre_credit_head_id);//信用贷款信息表主键id
                wmsCreApproServiceProtocol.setCreate_user_id((Integer)map.get("user_id"));//创建人主键
                wmsCreApproServiceProtocol.setCreate_user_name((String)map.get("user_name"));//创建人姓名
                wmsCreApproServiceProtocol.setCreate_timestamp(now_timestamp);//创建时间
                wmsCreApproServiceProtocol.setLast_update_user_id((Integer)map.get("user_id"));//最近修改人
                wmsCreApproServiceProtocol.setLast_update_timestamp(now_timestamp);//最近修改时间
                wmsCreApproServiceProtocol.setRefund_deadline(wmsCreApproBorrowProtocol.getBorrow_deadline());//还款期限
                wmsCreApproServiceProtocol.setConsult_service_cost(new BigDecimal(0)); //服务费利率
                wmsCreApproServiceProtocol.setJujian_service_cost(new BigDecimal(0)); //居间服务费利率
                wmsCreApproServiceProtocol.setService_cost_month(new BigDecimal(0));  //每月支付金额的利率
                wmsCreApproServiceProtocol.setProtocol_date(wmsCreApproBorrowProtocol.getProtocol_date());//协议签订日期    

                wmsCreApproServiceProtocolDao.save(wmsCreApproServiceProtocol);
            } else {//更新合同
                
                //保存修改日志
                try {
                    WmsCreCreditHeadSearchBeanVO vo = new WmsCreCreditHeadSearchBeanVO();
                    vo.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                    vo.setNow_timestamp(now_timestamp);
                    vo.setWms_cre_appro_borrow_protocol_json(mapper.writeValueAsString(contractList.get(0)));
                    vo.setLog_type("3");//放款申请
                    UserBean user = new UserBean();
                    user.setUserId((Integer)map.get("user_id"));
                    user.setRealname((String)map.get("user_name"));
                    this.wmsCreCreditHeadService.saveHouseLoanLog(vo, user);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
                
                //baisong 2016-6-25
                BigDecimal borrow_interest = new BigDecimal(map.get("borrow_interest").toString());//合同表利率--页面传递过来的--修改后的利率
                BigDecimal service_cost_month = new BigDecimal(0);//居间服务利率
                BigDecimal old_borrow_interest = new BigDecimal(contractList.get(0).get("borrow_interest").toString());//原合同中的利率
                //如果新输入的额利率大于等于原始利率则 原始合同表利率不变 多的部分加到居间服务表利率中
                if(borrow_interest.compareTo(old_borrow_interest) > 0) {
                	service_cost_month = borrow_interest.subtract(old_borrow_interest);
                	borrow_interest = old_borrow_interest;
                }
                
                //更新合同表
                WmsCreApproBorrowProtocol paramObj = new WmsCreApproBorrowProtocol();
                paramObj.setBorrow_deadline((int)map.get("borrow_deadline"));//借款期限
                paramObj.setBorrow_begin_date(new java.sql.Date(format.parse(map.get("borrow_begin_date").toString()).getTime()));//借款起始日期
                paramObj.setBorrow_end_date(new java.sql.Date(format.parse(map.get("borrow_end_date").toString()).getTime()));//借款终止日期
                paramObj.setBorrow_interest(borrow_interest);//借款利息(%)
                paramObj.setLiquidated_damages(new BigDecimal(map.get("liquidated_damages").toString()).longValue());//违约金
                paramObj.setWms_cre_credit_head_id(wms_cre_credit_head_id);//信用贷款信息表主键id
                paramObj.setLast_update_user_id((Integer)map.get("user_id"));//最近修改人
                paramObj.setLast_update_timestamp(now_timestamp);//最近修改时间
                paramObj.setEnable_flag("1");//是否有效标志(0:无效，1:有效)
                paramObj.setLoan_amount(new BigDecimal(map.get("loan_amount").toString()));//转账金额(借据中的转账金额)
                paramObj.setPayment_contract_type((String)map.get("payment_contract_type"));//还款类型： 1代表等额本息 2代表先息后本
                wmsCreApproBorrowProtocolDao.update(paramObj);
                
                //更新居间服务协议表
                WmsCreApproServiceProtocol wmsCreApproServiceProtocol = new WmsCreApproServiceProtocol();
                wmsCreApproServiceProtocol.setWms_cre_credit_head_id(wms_cre_credit_head_id);//信用贷款信息表主键id
                wmsCreApproServiceProtocol.setLast_update_user_id((Integer)map.get("user_id"));//最近修改人
                wmsCreApproServiceProtocol.setLast_update_timestamp(now_timestamp);//最近修改时间
                wmsCreApproServiceProtocol.setRefund_deadline((int)map.get("borrow_deadline"));//还款期限
                wmsCreApproServiceProtocol.setProtocol_date(now_date);//协议签订日期 
                wmsCreApproServiceProtocol.setService_cost_month(service_cost_month);//每月支付利率
                wmsCreApproServiceProtocolDao.update(wmsCreApproServiceProtocol);
            }
            
            //保存放款申请附件信息
            List<WmsFinaCreLoanAppAtt> attList1 = JsonUtil.jsonArrayToList(queryInfo.getId_card_list(), 
                    WmsFinaCreLoanAppAtt.class);
            List<WmsFinaCreLoanAppAtt> attList2 = JsonUtil.jsonArrayToList(queryInfo.getBank_list(),
                    WmsFinaCreLoanAppAtt.class);
            List<WmsFinaCreLoanAppAtt> attList3 = JsonUtil.jsonArrayToList(queryInfo.getTransfer_list(),
                    WmsFinaCreLoanAppAtt.class);
            List<WmsFinaCreLoanAppAtt> attList4 = JsonUtil.jsonArrayToList(queryInfo.getProtocol_list(),
                    WmsFinaCreLoanAppAtt.class);
            List<WmsFinaCreLoanAppAtt> attListAll = new ArrayList<WmsFinaCreLoanAppAtt>();
            if(attList1!=null){
            	attListAll.addAll(attList1);
            }
			if(attList2!=null){
				attListAll.addAll(attList2);        	
			}
			if(attList3!=null){
				 attListAll.addAll(attList3);
			}
			if(attList4!=null){
				 attListAll.addAll(attList4);
			}
            
			Map<String, Object> attParamMap = new HashMap<String, Object>();
			attParamMap.put("bill_code", wmsCreCreditHead.getBill_code());
            for(WmsFinaCreLoanAppAtt att : attListAll) {
                att.setCreate_timestamp(now_timestamp);
                att.setLast_update_timestamp(now_timestamp);
                att.setWms_fina_cre_loan_app_id(wmsFinaCreLoanApp.getWms_fina_cre_loan_app());
                att.setEnable_flag("1");
                att.setAttachment_new_name(att.getAttachment_address().substring(0, att.getAttachment_address().lastIndexOf(".")));
                att.setAttachment_type(att.getAttachment_address().substring(att.getAttachment_address().lastIndexOf(".") + 1));
                att.setCreate_user_id((Integer)map.get("user_id"));
                att.setCreate_user_name((String)map.get("user_name"));
                att.setLast_update_user_id((Integer)map.get("user_id"));
                
                //生成新的图片编号
                attParamMap.put("data_detail_name", att.getData_type());
                att.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));
                
                this.wmsfinacreloanappattDao.save(att);
            }
            
            //调用流程
            WmsHouseCreditWorkFlowVO vo = new WmsHouseCreditWorkFlowVO();
            vo.setWms_cre_credit_head_id(wms_cre_credit_head_id.toString());
            vo.setPass("true");
            vo.setUserId(map.get("user_id").toString());
            vo.setAdvice("放款申请");
            vo.setDebtkey("9");
            wmsLoanWorkFlowService.publicApprovalPhone(vo);
            //新流程发送短信
            if("2".equals(wmsCreCreditHead.getVersion_number())) {
                //调用发送信息的接口出现异常 不会影响正常流程  
                try{
	                if(customerChangeList != null && customerChangeList.size() == 1) {
	                    //调用方法map
	                    Map<String, Object> sendMap = new HashMap<String, Object>();
	                    //发送短信map
	                    Map<String, String> msgMap = new HashMap<String, String>();
	                    //参数map
	                    Map<String, String> paramMap = new HashMap<String, String>();
	                    //参数map
	                    Map<String, String> msg_extras = new HashMap<String, String>();
                    // sendMap.put("short_message", "1");
	                    msgMap.put("tpl_id", "1696");
	                    sendMap.put("role_value", WorkflowRoleHelp.HOUSE_GLBJL);//管理部经理
	                    paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
	                    paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
	                    paramMap.put("city", wmsCreCreditHead.getCity());
	                    
	                    msgMap.put("paramJson", new Gson().toJson(paramMap));
	                    sendMap.put("msgMap", msgMap);
	                    //激光推送
	                    sendMap.put("quart_message","1");//消息附加参数
	                    //如果是消息中心
	    	            sendMap.put("message_center","1");
	    	          
	    	            msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());//添加参数
	    	            sendMap.put("msg_extras", msg_extras);//消息附加参数  
	    	            sendMap.put("msg_code", "20002");//消息编码
	    	            sendMap.put("msg_code_role", "20002");//消息编码--流程角色
	    	            
	    	            paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
	    	            paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
	    	            paramMap.put("create_user_id", map.get("user_id").toString());
	    	            paramMap.put("create_user_name", (String)map.get("user_name"));
	    	            sendMap.put("msg_map", paramMap);//极光推送和消息中心的消息内容参数
	    	            
	                    this.wmsCreCreditHeadService.getUserAndSendInfo(sendMap);
	                }
                }catch (Exception e){
                	e.printStackTrace();        
                }
            }
            resMap.put("result", "调用成功");
            resMap.put("message", "success");
        
        
        return resMap;
    }
    
    /**
     * moa调用wms方法:放款申请结果保存(组合贷)
     * @param WmsFinaCreLoanAppSearchBeanVO queryInfo
     * @return Map<String, Object> resMap
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @Override
    @Transactional
    public Map<String, Object> sendLoanApprovalInfoUpAgain(WmsFinaCreLoanAppSearchBeanVO queryInfo) throws Exception {
        List<Map<String, Object>> groupList = JSON.parseObject(queryInfo.getGroup_list(), new TypeReference<List<Map<String, Object>>>(){}.getType());
        Map<String, Object> resMap = new HashMap<String, Object>();
        long now_long = System.currentTimeMillis();
        java.sql.Date now_date = new java.sql.Date(now_long);
        java.sql.Timestamp now_timestamp = new java.sql.Timestamp(now_long);
        BigDecimal weiyuejin = new BigDecimal(0.05);//违约金
        ObjectMapper mapper = new ObjectMapper();
        WmsFinaCreLoanApp wmsFinaCreLoanApp = new WmsFinaCreLoanApp();
        
        for(Map<String, Object> map : groupList) {
            Integer wms_cre_credit_head_id = (int)map.get("wms_cre_credit_head_id");
            WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(wms_cre_credit_head_id);
            Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
            customerChangeParamMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            customerChangeParamMap.put("is_major", "1");
            customerChangeParamMap.put("enable_flag", "1");
            List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
            
            //将原放款申请信息逻辑删除
            Map<String, Object> deleteWmsFinaCreLoanAppMap = new HashMap<String, Object>();
            deleteWmsFinaCreLoanAppMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            this.wmsfinacreloanappDao.deleteByWmsCreCreditHeadId(deleteWmsFinaCreLoanAppMap);
            
            //保存放款申请信息
            wmsFinaCreLoanApp = new WmsFinaCreLoanApp();
            wmsFinaCreLoanApp.setWms_cre_credit_head_id(wms_cre_credit_head_id);
            wmsFinaCreLoanApp.setLoan_amount(new BigDecimal(map.get("loan_amount").toString()));
            wmsFinaCreLoanApp.setLoan_date(null);
            wmsFinaCreLoanApp.setRemark((String)map.get("remark"));
            wmsFinaCreLoanApp.setCreate_date(now_date);
            wmsFinaCreLoanApp.setDeduction_of_interest(new BigDecimal(map.get("deduction_of_interest").toString()));
            wmsFinaCreLoanApp.setFees(new BigDecimal(map.get("fees").toString()));
            wmsFinaCreLoanApp.setFees_detail((String)map.get("fees_detail"));
            wmsFinaCreLoanApp.setHouse_area(map.get("house_building_area") == null ? null : new BigDecimal(map.get("house_building_area").toString()));
            wmsFinaCreLoanApp.setLoan_amount_caps((String)map.get("loan_amount_caps"));
            wmsFinaCreLoanApp.setCreate_user_id((Integer)map.get("user_id"));
            wmsFinaCreLoanApp.setCreate_user_name((String)map.get("user_name"));
            wmsFinaCreLoanApp.setCreate_timestamp(now_timestamp);
            wmsFinaCreLoanApp.setLast_update_user_id((Integer)map.get("user_id"));
            wmsFinaCreLoanApp.setLast_update_timestamp(now_timestamp);
            wmsFinaCreLoanApp.setEnable_flag("1");
            wmsFinaCreLoanApp.setIt_cost_fee(new BigDecimal(map.get("it_cost_fee").toString()));
            wmsFinaCreLoanApp.setExpedited_fee(new BigDecimal(map.get("expedited_fee").toString()));
            wmsFinaCreLoanApp.setCre_type_name((String)map.get("cre_type_name"));
            wmsFinaCreLoanApp.setNotary_is_finish((int)map.get("notary_is_finish"));
            wmsFinaCreLoanApp.setHe_is_finish((int)map.get("he_is_finish"));
            
            this.wmsfinacreloanappDao.save(wmsFinaCreLoanApp);
            
            //保存合同信息(如果已生成合同则更新合同,如果未生成合同则新生成合同)
            Map<String, Object> contractParamMap = new HashMap<String, Object>();
            contractParamMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            contractParamMap.put("sortname", "");
            contractParamMap.put("sortorder", "");
            contractParamMap.put("offset", null);
            contractParamMap.put("pagesize", null);
            
            List<Map<String, Object>> contractList = this.wmsCreApproBorrowProtocolDao.search(contractParamMap);
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
            if(null == contractList || contractList.size() == 0) {//生成合同
                WmsCreApproBorrowProtocol wmsCreApproBorrowProtocol = new WmsCreApproBorrowProtocol();
                // 合同编号自动生成
                String yearnum = CodeNoUtil.getProCreCode();// 合同编号
                wmsCreApproBorrowProtocol.setProtocol_id_year_num(yearnum);
                String code[] = yearnum.split("年第");
                if (code.length > 1)
                {
                    String protocol_id_year = code[0];
                    String num = code[1];
                    String protocol_id_num = num.substring(0, num.length() - 1);
                    wmsCreApproBorrowProtocol.setProtocol_id_year(protocol_id_year);// 合同编号 年
                    wmsCreApproBorrowProtocol.setProtocol_id_num(protocol_id_num);// 合同编号 号
                }
                wmsCreApproBorrowProtocol.setProtocol_date(now_date);
                wmsCreApproBorrowProtocol.setCreditor_name(null);//债权人姓名
                wmsCreApproBorrowProtocol.setCreditor_identity_id(null);//债权人身份证号码
                wmsCreApproBorrowProtocol.setDebtor_name(customerChangeList.get(0).get("customer_name").toString());//债务人姓名
                if(!"".equals(customerChangeList.get(0).get("mobile_telephone1"))&&customerChangeList.get(0).get("mobile_telephone1")!=null){
                     wmsCreApproBorrowProtocol.setDebtor_tel(customerChangeList.get(0).get("mobile_telephone1").toString());//债务人电话 
                }
                wmsCreApproBorrowProtocol.setDebtor_identity_id(null);//债务人身份证号码
                wmsCreApproBorrowProtocol.setCreditor_address(null);//债权人通讯地址
                wmsCreApproBorrowProtocol.setDebtor_address(null);//债务人通讯地址
                wmsCreApproBorrowProtocol.setCreditor_tel(null);//债权人联系电话
                wmsCreApproBorrowProtocol.setDebtor_fixed_line(null);//债务人固定电话
                wmsCreApproBorrowProtocol.setPrincipal_caps(null);//借款本金(大写)
                //wmsCreApproBorrowProtocol.setPrincipal_lower(null);//借款本金(小写)
                
                wmsCreApproBorrowProtocol.setPrincipal_lower(new BigDecimal(map.get("principal_lower").toString()));//借款本金(小写)
                wmsCreApproBorrowProtocol.setBorrow_deadline((int)map.get("borrow_deadline"));//借款期限
                wmsCreApproBorrowProtocol.setBorrow_begin_date(new java.sql.Date(format.parse(map.get("borrow_begin_date").toString()).getTime()));//借款起始日期
                wmsCreApproBorrowProtocol.setBorrow_end_date(new java.sql.Date(format.parse(map.get("borrow_end_date").toString()).getTime()));//借款终止日期
                wmsCreApproBorrowProtocol.setBorrow_purpose(null);//借款用途
                wmsCreApproBorrowProtocol.setBorrow_interest(new BigDecimal(map.get("borrow_interest").toString()));//借款利息(%)
                wmsCreApproBorrowProtocol.setRefund_bank(null);//还款银行
                wmsCreApproBorrowProtocol.setRefund_number(null);//还款账号
                wmsCreApproBorrowProtocol.setRefund_name(null);//还款户名
                //wmsCreApproBorrowProtocol.setRefund_limit_month(null);//月还款金额
                
                wmsCreApproBorrowProtocol.setRefund_day(null);//还款日
                wmsCreApproBorrowProtocol.setLiquidated_damages(new BigDecimal(map.get("liquidated_damages").toString()).longValue());//违约金
                wmsCreApproBorrowProtocol.setWms_cre_credit_head_id(wms_cre_credit_head_id);//信用贷款信息表主键id
                wmsCreApproBorrowProtocol.setCreate_user_id((Integer)map.get("user_id"));//创建人主键
                wmsCreApproBorrowProtocol.setCreate_user_name((String)map.get("user_name"));//创建人姓名
                wmsCreApproBorrowProtocol.setCreate_timestamp(now_timestamp);//创建时间
                wmsCreApproBorrowProtocol.setLast_update_user_id((Integer)map.get("user_id"));//最近修改人
                wmsCreApproBorrowProtocol.setLast_update_timestamp(now_timestamp);//最近修改时间
                wmsCreApproBorrowProtocol.setEnable_flag("1");//是否有效标志(0:无效，1:有效)
                wmsCreApproBorrowProtocol.setProtocol_type(null);//合同类型
                wmsCreApproBorrowProtocol.setCom_debtor_name(null);//共同债务人姓名
                wmsCreApproBorrowProtocol.setCom_debtor_identity_id(null);//共同债务人身份证号
                wmsCreApproBorrowProtocol.setCom_debtor_address(null);//共同债务人通讯地址
                wmsCreApproBorrowProtocol.setCom_debtor_tel(null);//共同债务人电话
                wmsCreApproBorrowProtocol.setCom_debtor_fixed_line(null);//共同债务人固定联系电话
                wmsCreApproBorrowProtocol.setRefund_principal_month_lower(null);//月还款本金小写
                wmsCreApproBorrowProtocol.setRefund_principal_month_caps(null);//每月还款本金大写
                wmsCreApproBorrowProtocol.setRefund_interest_month_lower(null);//月还款利息小写
                wmsCreApproBorrowProtocol.setRefund_interest_month_caps(null);//月还款利息大写
                wmsCreApproBorrowProtocol.setRefund_limit_month_caps(null);//月还款额大写
                wmsCreApproBorrowProtocol.setCompensate(null);//提前还款补偿
                wmsCreApproBorrowProtocol.setYuqi_damages(weiyuejin);//逾期违约金利率
                wmsCreApproBorrowProtocol.setCompensate_caps(null);//提前还款补偿大写
                wmsCreApproBorrowProtocol.setYuqi_damages_caps(null);//逾期违约金利率大写
                wmsCreApproBorrowProtocol.setBorrow_interest_caps(null);//月利率大写
                wmsCreApproBorrowProtocol.setPrincipal(null);//其中本金总额
                wmsCreApproBorrowProtocol.setInterest(null);//其中利息总额
                wmsCreApproBorrowProtocol.setOrg_repay_principal(null);//其中本金
                wmsCreApproBorrowProtocol.setOrg_repay_interest(null);//每月利息
                wmsCreApproBorrowProtocol.setRefund_limit_month_lower(null);//月还款额小写
                wmsCreApproBorrowProtocol.setPlan_borrow_date(null);//计划还款日期
                wmsCreApproBorrowProtocol.setLoan_amount(new BigDecimal(map.get("loan_amount").toString()));//转账金额(借据中的转账金额)
                wmsCreApproBorrowProtocol.setCompany(null);//公司名头
                wmsCreApproBorrowProtocol.setCheque_number(null);//支票张数
                wmsCreApproBorrowProtocol.setBill_number(null);//票据号码
                wmsCreApproBorrowProtocol.setDeduct_money(null);//收据中的现金现金
                wmsCreApproBorrowProtocol.setC_house_address(null);//丙的居所
                wmsCreApproBorrowProtocol.setA_c_relation(null);//乙丙关系
                wmsCreApproBorrowProtocol.setSecond_house_address(null);//第二居所
                wmsCreApproBorrowProtocol.setArbitrate_name(null);//仲裁名称
                wmsCreApproBorrowProtocol.setCourt_name(null);//法院名称
                wmsCreApproBorrowProtocol.setDispute_solve(null);//争论方式
                wmsCreApproBorrowProtocol.setHouse_sale(null);//房产拍卖
                wmsCreApproBorrowProtocol.setHouse_estimate(null);//房产评估
                wmsCreApproBorrowProtocol.setSign_place(null);//地点
                wmsCreApproBorrowProtocol.setReplace_pay_money_caps(null);//代还款金额大写
                wmsCreApproBorrowProtocol.setReplace_pay_money_lower(null);//代还款金额小写
                wmsCreApproBorrowProtocol.setCash_transfer_lower(null);//信贷合同  -支票借据中的 现在/转账
                wmsCreApproBorrowProtocol.setPayment_contract_type((String)map.get("payment_contract_type"));//还款类型： 1代表等额本息 2代表先息后本
                wmsCreApproBorrowProtocol.setWeiyuejin(weiyuejin);//每日违约金利率
                wmsCreApproBorrowProtocol.setFirst_refund_date(null);//第一个还款日
                wmsCreApproBorrowProtocol.setSecond_refund_date(null);//第二个还款日
                wmsCreApproBorrowProtocol.setCreditor_loan_name(null);//甲方放款账户名
                wmsCreApproBorrowProtocol.setCreditor_loan_number(null);//甲方放款账号
                wmsCreApproBorrowProtocol.setCreditor_loan_bank(null);//甲方放款账户开户行
                wmsCreApproBorrowProtocol.setDebtor_loan_name(null);//乙方收款账户名
                wmsCreApproBorrowProtocol.setDebtor_loan_number(null);//乙方收款账号
                wmsCreApproBorrowProtocol.setDebtor_loan_bank(null);//乙方收款账户开户行
                wmsCreApproBorrowProtocol.setProtocol_refund_interest_month(null);//合同中月利息
                wmsCreApproBorrowProtocol.setProtocol_d_num(null);//抵押合同编号
                wmsCreApproBorrowProtocol.setCreditor_zip_code(null);//债权人邮编
                wmsCreApproBorrowProtocol.setDebtor_zip_code(null);//债务人邮编
                wmsCreApproBorrowProtocol.setEmbezzlement_damages(null);//挪用借款违约金
                wmsCreApproBorrowProtocol.setLoan_currency(null);//借款币种
                wmsCreApproBorrowProtocol.setDebtor_residence_address(null);//债务人居住地址
                wmsCreApproBorrowProtocolDao.save(wmsCreApproBorrowProtocol);
                //居间服务表
                WmsCreApproServiceProtocol wmsCreApproServiceProtocol = new WmsCreApproServiceProtocol();
                wmsCreApproServiceProtocol.setEnable_flag("1");//数据状态
                wmsCreApproServiceProtocol.setWms_cre_credit_head_id(wms_cre_credit_head_id);//信用贷款信息表主键id
                wmsCreApproServiceProtocol.setCreate_user_id((Integer)map.get("user_id"));//创建人主键
                wmsCreApproServiceProtocol.setCreate_user_name((String)map.get("user_name"));//创建人姓名
                wmsCreApproServiceProtocol.setCreate_timestamp(now_timestamp);//创建时间
                wmsCreApproServiceProtocol.setLast_update_user_id((Integer)map.get("user_id"));//最近修改人
                wmsCreApproServiceProtocol.setLast_update_timestamp(now_timestamp);//最近修改时间
                wmsCreApproServiceProtocol.setRefund_deadline(wmsCreApproBorrowProtocol.getBorrow_deadline());//还款期限
                wmsCreApproServiceProtocol.setConsult_service_cost(new BigDecimal(0)); //服务费利率
                wmsCreApproServiceProtocol.setJujian_service_cost(new BigDecimal(0)); //居间服务费利率
                wmsCreApproServiceProtocol.setService_cost_month(new BigDecimal(0));  //每月支付金额的利率
                wmsCreApproServiceProtocol.setProtocol_date(wmsCreApproBorrowProtocol.getProtocol_date());//协议签订日期    
    
                wmsCreApproServiceProtocolDao.save(wmsCreApproServiceProtocol);
            } else {//更新合同
                
                //保存修改日志
                try {
                    WmsCreCreditHeadSearchBeanVO vo = new WmsCreCreditHeadSearchBeanVO();
                    vo.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                    vo.setNow_timestamp(now_timestamp);
                    vo.setWms_cre_appro_borrow_protocol_json(mapper.writeValueAsString(contractList.get(0)));
                    vo.setLog_type("3");//放款申请
                    UserBean user = new UserBean();
                    user.setUserId((Integer)map.get("user_id"));
                    user.setRealname((String)map.get("user_name"));
                    this.wmsCreCreditHeadService.saveHouseLoanLog(vo, user);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
                
                //baisong 2016-6-25
                BigDecimal borrow_interest = new BigDecimal(map.get("borrow_interest").toString());//合同表利率--页面传递过来的--修改后的利率
                BigDecimal service_cost_month = new BigDecimal(0);//居间服务利率
                BigDecimal old_borrow_interest = new BigDecimal(contractList.get(0).get("borrow_interest").toString());//原合同中的利率
                //如果新输入的额利率大于等于原始利率则 原始合同表利率不变 多的部分加到居间服务表利率中
                if(borrow_interest.compareTo(old_borrow_interest) > 0) {
                    service_cost_month = borrow_interest.subtract(old_borrow_interest);
                    borrow_interest = old_borrow_interest;
                }
                
                //更新合同表
                WmsCreApproBorrowProtocol paramObj = new WmsCreApproBorrowProtocol();
                paramObj.setBorrow_deadline((int)map.get("borrow_deadline"));//借款期限
                paramObj.setBorrow_begin_date(new java.sql.Date(format.parse(map.get("borrow_begin_date").toString()).getTime()));//借款起始日期
                paramObj.setBorrow_end_date(new java.sql.Date(format.parse(map.get("borrow_end_date").toString()).getTime()));//借款终止日期
                paramObj.setBorrow_interest(borrow_interest);//借款利息(%)
                paramObj.setLiquidated_damages(new BigDecimal(map.get("liquidated_damages").toString()).longValue());//违约金
                paramObj.setWms_cre_credit_head_id(wms_cre_credit_head_id);//信用贷款信息表主键id
                paramObj.setLast_update_user_id((Integer)map.get("user_id"));//最近修改人
                paramObj.setLast_update_timestamp(now_timestamp);//最近修改时间
                paramObj.setEnable_flag("1");//是否有效标志(0:无效，1:有效)
                paramObj.setLoan_amount(new BigDecimal(map.get("loan_amount").toString()));//转账金额(借据中的转账金额)
                paramObj.setPayment_contract_type((String)map.get("payment_contract_type"));//还款类型： 1代表等额本息 2代表先息后本
                wmsCreApproBorrowProtocolDao.update(paramObj);
                
                //更新居间服务协议表
                WmsCreApproServiceProtocol wmsCreApproServiceProtocol = new WmsCreApproServiceProtocol();
                wmsCreApproServiceProtocol.setWms_cre_credit_head_id(wms_cre_credit_head_id);//信用贷款信息表主键id
                wmsCreApproServiceProtocol.setLast_update_user_id((Integer)map.get("user_id"));//最近修改人
                wmsCreApproServiceProtocol.setLast_update_timestamp(now_timestamp);//最近修改时间
                wmsCreApproServiceProtocol.setRefund_deadline((int)map.get("borrow_deadline"));//还款期限
                wmsCreApproServiceProtocol.setProtocol_date(now_date);//协议签订日期 
                wmsCreApproServiceProtocol.setService_cost_month(service_cost_month);//每月支付利率
                wmsCreApproServiceProtocolDao.update(wmsCreApproServiceProtocol);
            }
            
            //保存放款申请附件信息
            List<WmsFinaCreLoanAppAtt> attList1 = JsonUtil.jsonArrayToList(queryInfo.getId_card_list(), 
                    WmsFinaCreLoanAppAtt.class);
            List<WmsFinaCreLoanAppAtt> attList2 = JsonUtil.jsonArrayToList(queryInfo.getBank_list(),
                    WmsFinaCreLoanAppAtt.class);
            List<WmsFinaCreLoanAppAtt> attList3 = JsonUtil.jsonArrayToList(queryInfo.getTransfer_list(),
                    WmsFinaCreLoanAppAtt.class);
            List<WmsFinaCreLoanAppAtt> attList4 = JsonUtil.jsonArrayToList(queryInfo.getProtocol_list(),
                    WmsFinaCreLoanAppAtt.class);
            List<WmsFinaCreLoanAppAtt> attListAll = new ArrayList<WmsFinaCreLoanAppAtt>();
            if(attList1 != null) {
                attListAll.addAll(attList1);
            }
            if(attList2 != null) {
                attListAll.addAll(attList2);            
            }
            if(attList3 != null) {
                 attListAll.addAll(attList3);
            }
            if(attList4 != null ){
                 attListAll.addAll(attList4);
            }
            
            Map<String, Object> attParamMap = new HashMap<String, Object>();
            attParamMap.put("bill_code", wmsCreCreditHead.getBill_code());
            for(WmsFinaCreLoanAppAtt att : attListAll) {
                att.setCreate_timestamp(now_timestamp);
                att.setLast_update_timestamp(now_timestamp);
                att.setWms_fina_cre_loan_app_id(wmsFinaCreLoanApp.getWms_fina_cre_loan_app());
                att.setEnable_flag("1");
                att.setAttachment_new_name(att.getAttachment_address().substring(0, att.getAttachment_address().lastIndexOf(".")));
                att.setAttachment_type(att.getAttachment_address().substring(att.getAttachment_address().lastIndexOf(".") + 1));
                att.setCreate_user_id((Integer)map.get("user_id"));
                att.setCreate_user_name((String)map.get("user_name"));
                att.setLast_update_user_id((Integer)map.get("user_id"));
                
                //生成新的图片编号
                attParamMap.put("data_detail_name", att.getData_type());
                att.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));
                
                this.wmsfinacreloanappattDao.save(att);
            }
            
            //调用流程
            WmsHouseCreditWorkFlowVO vo = new WmsHouseCreditWorkFlowVO();
            vo.setWms_cre_credit_head_id(wms_cre_credit_head_id.toString());
            vo.setPass("true");
            vo.setUserId(map.get("user_id").toString());
            vo.setAdvice("放款申请");
            vo.setDebtkey("9");
            wmsLoanWorkFlowService.publicApprovalPhone(vo);
            //新流程发送短信
            if("2".equals(wmsCreCreditHead.getVersion_number())) {
                //调用发送信息的接口出现异常 不会影响正常流程  
                try{
                    if(customerChangeList != null && customerChangeList.size() == 1) {
                        //调用方法map
                        Map<String, Object> sendMap = new HashMap<String, Object>();
                        //发送短信map
                        Map<String, String> msgMap = new HashMap<String, String>();
                        //参数map
                        Map<String, String> paramMap = new HashMap<String, String>();
                        //参数map
                        Map<String, String> msg_extras = new HashMap<String, String>();
                        // sendMap.put("short_message", "1");
                        msgMap.put("tpl_id", "1696");
                        sendMap.put("role_value", WorkflowRoleHelp.HOUSE_GLBJL);//管理部经理
                        paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                        paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                        paramMap.put("city", wmsCreCreditHead.getCity());
                        
                        msgMap.put("paramJson", new Gson().toJson(paramMap));
                        sendMap.put("msgMap", msgMap);
                        //激光推送
                        sendMap.put("quart_message","1");//消息附加参数
                        //如果是消息中心
                        sendMap.put("message_center","1");
                      
                        msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());//添加参数
                        sendMap.put("msg_extras", msg_extras);//消息附加参数  
                        sendMap.put("msg_code", "20002");//消息编码
                        sendMap.put("msg_code_role", "20002");//消息编码--流程角色
                        
                        paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                        paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                        paramMap.put("create_user_id", map.get("user_id").toString());
                        paramMap.put("create_user_name", (String)map.get("user_name"));
                        sendMap.put("msg_map", paramMap);//极光推送和消息中心的消息内容参数
                        
                        this.wmsCreCreditHeadService.getUserAndSendInfo(sendMap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();        
                }
            }
            resMap.put("result", "调用成功");
            resMap.put("message", "success");
        }
        
        return resMap;
    }

	@Override
	public void updateWmsFinaCreLoanApp(WmsFinaCreLoanApp bean) {
		wmsfinacreloanappDao.updateWmsFinaCreLoanApp(bean);
	}
	
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException {
	    String test = "{\"loan_amount\":600000,\"borrow_end_date\":\"2016-07-19\",\"borrow_interest\":0,\"expedited_fee\":0,\"remark\":\"先息后本：转账金额\\u003d合同金额-手续费-他项费用-加急费用-一个月月息\\n等额本息：转账金额\\u003d借款本金-手续费-他项费用-加急费用\",\"payment_contract_type\":\"2\",\"borrow_deadline\":0,\"he_is_finish\":1,\"it_cost_fee\":0,\"fees\":0,\"borrow_begin_date\":\"2016-07-20\",\"principal_lower\":600000,\"house_building_area\":250.0,\"deduction_of_interest\":0,\"user_name\":\"陈月\",\"liquidated_damages\":0,\"wms_cre_credit_head_id\":3704,\"user_id\":178,\"notary_is_finish\":1}";
	    Map<String, Object> map = new HashMap<String, Object>();
	    ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(test, new TypeReference<Map<String, Object>>(){});  
        Long liqu = new BigDecimal(map.get("liquidated_damages").toString()).longValue();
        // System.out.println(liqu);
    }
	
	/**
     * 
     * @Title: initCombineLoanInfo
     * @Description: TODO(放款申请组合贷初始化信息)
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2016年12月23日 下午4:15:16
     * history:
     * 1、2016年12月23日 wangyihan 创建方法
     */
    public WmsFinaCreLoanAppSearchBeanVO initCombineLoanInfo(WmsFinaCreLoanAppSearchBeanVO queryInfo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        paramMap.put("hprocess_time", WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TIME);
        if(StringUtils.isNotEmpty(queryInfo.getBill_status())) {
            paramMap.put("bill_status", queryInfo.getBill_status());
        }
        queryInfo.setInitCombineLoanInfoMap(this.wmsfinacreloanappDao.initCombineLoanInfo(paramMap));
        return queryInfo;
    }
    
    public String giveInfoToPTP(Map<String, Object> map, Integer personnel_id) {
        String result="success";
        //获取WMS贷款客户填写电话1的客户
        List<Map<String,Object>> list1 = wmsCreCreditApproDao.getNameAndPhone1(map);
        //获取WMS贷款客户填写电话2联系人信息
        List<Map<String,Object>> list2 = wmsCreCreditApproDao.getNameAndPhone2(map);
        //获取WMS贷款客户联系人信息
        List<Map<String,Object>> list3=  wmsCreCreditApproDao.getNameAndPhone3(map);
        //存放所有根据所传递ID查询到的人员信息
        List<Map<String,Object>> listAll=new ArrayList<Map<String,Object>>();
        //将WMS贷款客户填写电话1的客户插入到所有中
        for(int i=0;i<list1.size();i++){
            //如果需要Map插入的key顺序的话   Map<String,Object> listMap = new LinkedHashMap<String,Object>();
            Map<String,Object> listMap=new HashMap<String,Object>();
            listMap.put("user_phone",list1.get(i).get("mobile_telephone1"));
            listMap.put("user_type",1);
            listMap.put("user_id_card",list1.get(i).get("id_card"));
            listMap.put("user_real_name",list1.get(i).get("customer_name"));
            listAll.add(listMap);
        }
        //将WMS贷款客户填写电话2的客户插入到所有中
        for(int i=0;i<list2.size();i++){
            if(list2.get(i).get("mobile_telephone2")!=null
                    &&list2.get(i).get("mobile_telephone2")!=""
                    &&!list2.get(i).get("mobile_telephone2").equals("无")){
                Map<String,Object> listMap=new HashMap<String,Object>();
                listMap.put("user_phone",list2.get(i).get("mobile_telephone2"));
                listMap.put("user_type",1);
                listMap.put("user_id_card",list2.get(i).get("id_card"));
                listMap.put("user_real_name",list2.get(i).get("customer_name"));
                listAll.add(listMap);
            }
        }
        //将WMS贷款客户联系人信息插入到所有中
        for(int i=0;i<list3.size();i++){
            //Map<String,Object> listMap = new LinkedHashMap<String,Object>();
            Map<String,Object> listMap=new HashMap<String,Object>();
            listMap.put("user_phone",list3.get(i).get("contact_mobile_phone"));
            listMap.put("user_type",2);
            //listMap.put("user_id_card","\t")
            listMap.put("user_id_card","");
            listMap.put("user_real_name",list3.get(i).get("contact_name"));
            listAll.add(listMap);
        }
        Gson gson = new Gson();
        String userList=gson.toJson(listAll);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("interface_num","PTP_OUT_001"));
        nvps.add(new BasicNameValuePair("sys_num","WMS"));
        nvps.add(new BasicNameValuePair("user_id", "0"));
        nvps.add(new BasicNameValuePair("userList",userList));
        nvps.add(new BasicNameValuePair("personnel_id",personnel_id.toString()));
        HttpPost httpPost = new HttpPost(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple");
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(httpPost);
            System.out.println(response.getStatusLine());
            response.close();
            httpclient.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            result="error";
            return result;
        }   
        return result;
    }

    /**
     * @Title: wmsFinaCreLoanAppSaveForPerfact
     * @Description: TODO(提交放款)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2017年2月27日 上午9:53:26
     * @see com.zx.emanage.loanfina.service.IWmsFinaCreLoanAppService#wmsFinaCreLoanAppSaveForPerfact(java.lang.Integer)
     * history:
     * 1、2017年2月27日 jiaodelong 创建方法
    */
    @Override
    @Transactional
    public Map<String, Object> wmsFinaCreLoanAppSaveForPerfact(WmsFinaCreLoanAppSearchBeanVO bean, UserBean user)
    {
        String resStr = "success";
        /*int n = 0;
        Integer wms_cre_credit_head_id = bean.getWms_cre_credit_head_id();
        String bill_type = bean.getBill_type();
        //如果为展期，修改还款表状态为结清
        if(bill_type.equals("02")){
           WmsCreCreditNotaryWarn warn = new WmsCreCreditNotaryWarn();
           warn.setWms_cre_credit_head_id(wms_cre_credit_head_id);
           warn.setRepay_status("3");
           n = wmsCreCreditNotaryWarnDao.updateRepaystatus(warn);
        }
        if(n == 0){
           resStr = "error";
        }*/
        Map<String, Object> map = new HashMap<>();
        // 获取组合贷
        Map<String, Object> mapGroup = new HashMap<>();
        mapGroup.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        mapGroup.put("bill_status", "H,HT,I,KT");
        // List<Map<String, Object>> listGroup =
        // wmsCreCreditHeadDao.getGroupIdForHeadIdStatus(mapGroup);
        List<Map<String, Object>> listGroup = wmsCreCreditHeadDao.getGroupInfo(mapGroup);
        // 判断组合贷
        if (listGroup != null && listGroup.size() > 0)
        {
            // 判断是否为组合贷
            if (listGroup.size() > 0)
            {
                String msg = "";
                String msgG = "";
                for (Map<String, Object> mapG : listGroup)
                {
                    if (mapG != null && !"I".equals((mapG.get("bill_status"))))
                    {
                        if ("".equals(msg))
                        {
                            msg = mapG.get("bill_code").toString() + "(" + mapG.get("bill_status_name").toString() + ")";
                        }
                        else
                        {
                            msg = msg + "," + mapG.get("bill_code").toString() + "(" + mapG.get("bill_status_name").toString() + ")";
                        }
                    }
                    // 合同完成
                    else if (mapG != null && "I".equals((mapG.get("bill_status"))))
                    {
                        if ("".equals(msgG))
                        {
                            msgG = mapG.get("bill_code").toString() + "(" + mapG.get("bill_status_name").toString() + ")";
                        }
                        else
                        {
                            msgG = msgG + "," + mapG.get("bill_code").toString() + "(" + mapG.get("bill_status_name").toString() + ")";
                        }
                    }
                }
                // 判断是否是检查方法
                if (bean.getCheck_flag() != null && "1".equals(bean.getCheck_flag()))
                {
                    if ("".equals(msg))
                    {
                        map.put("msgInfo", msgG);
                        map.put("msgflag", "1");// 可以继续提交
                        return map;

                    }
                    else
                    {
                        map.put("msgInfo", msg);
                        map.put("msgflag", "2");// 不可以继续提交
                        return map;
                    }

                }
                if (!"".equals(msg))
                {
                    map.put("msgInfo", msg);
                    map.put("msgflag", "2");// 不可以继续提交
                    return map;
                }
            }
            if (bean.getCheck_flag() != null && "1".equals(bean.getCheck_flag()))
            {
                map.put("msgInfo", "");
                map.put("msgflag", "1");// 可以继续提交
                return map;
            }
            for (Map<String, Object> mapG : listGroup)
            {
                // 并发校验(单据状态)
                WmsCreCreditHead wmsCreCreditHead = this.wmsCreCreditHeadDao.get(Integer.valueOf(mapG.get("wms_cre_credit_head_id").toString()));
                // 更改放款申请表，放款申请时间
                Map<String, Object> appMap = new HashMap<String, Object>();
                appMap.put("create_date", new Date(System.currentTimeMillis()));
                appMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
                wmsFinaCreLoanAppDao.updateLoanDate(appMap);
                // 单据类型--业务状态： 01 新增、02 展期、03 续期
                if ("02".equals(wmsCreCreditHead.getBill_type()))
                {
                    CreditMessageToRepayWarnBeanVO beanvo = new CreditMessageToRepayWarnBeanVO();
                    // 获取信息放款数据同步到还款提醒模块
                    beanvo.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                    // 贷款类型-房贷
                    beanvo.setCre_type("2");
                    String result = wmsCreCreditNotaryWarnService.getHouseCreditMessageToRepayWarn(beanvo, user);

                    WmsCreCreditServiceType wmsCreCreditServiceType = new WmsCreCreditServiceType();
                    wmsCreCreditServiceType.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id());
                    // 获取贷款表和还款提醒表的中间表
                    List<WmsCreCreditServiceType> list = wmsCreCreditServiceTypeDao.getListByEntity(wmsCreCreditServiceType);
                    if (list != null && list.size() > 0)
                    {
                        wmsCreCreditServiceType = list.get(0);
                        // 单据还款状态1、正常2、逾期3、结清4、续贷7、移交公司
                        WmsCreCreditNotaryWarn wmsCreCreditNotaryWarn = new WmsCreCreditNotaryWarn();
                        wmsCreCreditNotaryWarn.setRepay_status("3");
                        wmsCreCreditNotaryWarn.setClean_up_date(new Date(System.currentTimeMillis()));// 结清日期
                        wmsCreCreditNotaryWarn.setClean_up_info("展期后原单据结清");
                        wmsCreCreditNotaryWarn.setWms_cre_credit_notary_warn_id(wmsCreCreditServiceType.getPre_wms_cre_credit_notary_warn_id());
                        // 原单据变为结清
                        wmsCreCreditNotaryWarnDao.update(wmsCreCreditNotaryWarn);
                        wmsCreCreditNotaryWarn = new WmsCreCreditNotaryWarn();
                        // 更新新还款单据次数
                        wmsCreCreditNotaryWarn.setWms_cre_credit_notary_warn_id(Integer.valueOf(result));
                        // 增加展期次数
                        if (wmsCreCreditServiceType != null && wmsCreCreditServiceType.getThe_number() != null && !wmsCreCreditServiceType.getThe_number().equals(""))
                        {
                            String number = wmsCreCreditServiceType.getThe_number();
                            Integer n_number = Integer.parseInt(number) + 1;
                            wmsCreCreditNotaryWarn.setThe_number(n_number.toString());
                        }
                        else
                        {
                            wmsCreCreditNotaryWarn.setThe_number("1");
                        }
                        wmsCreCreditServiceType.setThe_number(wmsCreCreditNotaryWarn.getThe_number());
                        // wmsCreCreditServiceTypeDao.updateTht_Number(wmsCreCreditServiceType);
                        // 更新新还款单据次数
                        wmsCreCreditNotaryWarnDao.update(wmsCreCreditNotaryWarn);

                        /*//更改放款申请表，放款申请时间
                        Map<String,Object> appMap = new HashMap<String,Object>();
                        appMap.put("create_date", new Date(System.currentTimeMillis()));
                        appMap.put("wms_cre_credit_head_id", list.get(0).getWms_cre_credit_head_id());
                        wmsFinaCreLoanAppDao.updateLoanDate(appMap);*/
                    }
                    // 判断是否同步成功
                    if (!"listNull".equals(result) && !"exceptionError".equals(result) && !"error".equals(result))
                    {
                        wmsCreCreditServiceType.setWms_cre_credit_notary_warn_id(Integer.valueOf(result));
                        wmsCreCreditServiceTypeDao.updateSyn(wmsCreCreditServiceType);
                    }
                }
                else if ("03".equals(wmsCreCreditHead.getBill_type()))
                {
                    WmsCreCreditServiceType wmsCreCreditServiceType = new WmsCreCreditServiceType();
                    wmsCreCreditServiceType.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id());
                    // 获取贷款表和还款提醒表的中间表
                    List<WmsCreCreditServiceType> list = wmsCreCreditServiceTypeDao.getListByEntity(wmsCreCreditServiceType);
                    if (list != null && list.size() > 0)
                    {
                        wmsCreCreditServiceType = list.get(0);
                        // 单据还款状态1、正常2、逾期3、结清4、续贷7、移交公司
                        WmsCreCreditNotaryWarn wmsCreCreditNotaryWarn = new WmsCreCreditNotaryWarn();
                        // wmsCreCreditNotaryWarn.setWms_cre_credit_notary_warn_id(wmsCreCreditServiceType.getPre_wms_cre_credit_notary_warn_id());
                        // 增加展期次数
                        if (wmsCreCreditServiceType != null && wmsCreCreditServiceType.getThe_number() != null && !wmsCreCreditServiceType.getThe_number().equals(""))
                        {
                            String number = wmsCreCreditServiceType.getThe_number();
                            Integer n_number = Integer.parseInt(number) + 1;
                            wmsCreCreditNotaryWarn.setThe_number(n_number.toString());
                        }
                        else
                        {
                            wmsCreCreditNotaryWarn.setThe_number("1");
                        }
                        wmsCreCreditServiceType.setThe_number(wmsCreCreditNotaryWarn.getThe_number());
                        wmsCreCreditServiceTypeDao.updateSyn(wmsCreCreditServiceType);
                        /*wmsCreCreditNotaryWarn.setWms_cre_credit_head_id(wmsCreCreditServiceType.getWms_cre_credit_head_id());
                         wmsCreCreditNotaryWarnDao.updateInfoForHeadId(wmsCreCreditNotaryWarn);*/
                    }
                }
                WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                approveHouseWorkFlowVO.setDebtkey("FKSQ");
                approveHouseWorkFlowVO.setUserId(user.getUserId().toString());
                approveHouseWorkFlowVO.setPass("true");
                approveHouseWorkFlowVO.setAdvice("放款申请完成");
                approveHouseWorkFlowVO.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                wmsLoanWorkFlowService.publicApprovalStatus(approveHouseWorkFlowVO);
                // 单据类型--业务状态： 02 展期 展期需要系统自动添加放款审批操作节点
                if ("02".equals(wmsCreCreditHead.getBill_type()))
                {
                    WmsCreHousingApprovalInfo approvalInfo = new WmsCreHousingApprovalInfo();// 房贷——审批信息表
                    approvalInfo.setApproval_type("1");// 审批类型1正常审批2退件审批3作废审批
                    approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_FKSP);// 审批节点
                    // 审批表
                    approvalInfo.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id());// 贷款主表id
                    approvalInfo.setApproval_advice("展期放款申请自动放款审批");// 审批意见
                    approvalInfo.setApproval_result("true");// 审批结果 //
                                                            // 流程中传递pass的值
                    approvalInfo.setApproval_user_id(Integer.valueOf(approveHouseWorkFlowVO.getUserId()));// 审批人id
                    approvalInfo.setApproval_time(new java.sql.Timestamp(System.currentTimeMillis()));// 审批时间
                    approvalInfo.setApproval_task_code("FKSP");// 审批编码
                    approvalInfo.setEnable_flag("2");
                    wmsCreHousingApprovalInfoDao.saveInfo(approvalInfo);
                }

                // 单据类型--业务状态： 01 新增、02 展期、03 续期
                if (!"02".equals(wmsCreCreditHead.getBill_type()))
                {
                    // 新流程发送短信
                    // 查询客户姓名
                    Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
                    customerChangeParamMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
                    customerChangeParamMap.put("is_major", "1");
                    customerChangeParamMap.put("enable_flag", "1");
                    List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
                    // 调用发送信息的接口出现异常 不会影响正常流程
                    try
                    {
                        if (customerChangeList != null && customerChangeList.size() == 1)
                        {
                            // 调用方法map
                            Map<String, Object> sendMap = new HashMap<String, Object>();
                            // 发送短信map
                            Map<String, String> msgMap = new HashMap<String, String>();
                            // 参数map
                            Map<String, String> paramMap = new HashMap<String, String>();
                            // 极光 附加参数map
                            Map<String, String> msg_extras = new HashMap<String, String>();
                            // sendMap.put("short_message", "1");
                            msgMap.put("tpl_id", "1696");
                            sendMap.put("user_id", user.getUserId());
                            // 把业务部门主管修改成管理部经理
                            sendMap.put("role_value", "1");// 终审人员--WorkflowRoleHelp.HOUSE_GLBJL
                            // 根据菜单查询人员
                            sendMap.put("menu_url", WmsHelp.MENU_URL_FKSP_LIST);// 放款审批ual
                            sendMap.put("salesman_dept_id", wmsCreCreditHead.getSalesman_dept_id());// 初审评估ual
                            paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                            paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                            paramMap.put("city", wmsCreCreditHead.getCity());

                            msgMap.put("paramJson", new Gson().toJson(paramMap));
                            sendMap.put("msgMap", msgMap);
                            // 如果值为1则发送消息中心消息
                            sendMap.put("message_center", "1");
                            // 如果值为1则发送极光消息
                            sendMap.put("quart_message", "1");

                            msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                            sendMap.put("msg_extras", msg_extras);// 消息附加参数

                            sendMap.put("msg_code", "20002");// 极光消息中心消息编码
                            sendMap.put("msg_code_role", "20002");// 极光消息中心消息编码--流程角色

                            paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                            paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                            paramMap.put("create_user_id", user.getUserId().toString());
                            paramMap.put("create_user_name", user.getRealname());

                            paramMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                            paramMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                            paramMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());
                            paramMap.put("app_name", WmsHelp.APP_NAME_MIF);
                            sendMap.put("msg_map", paramMap);// 极光推送和消息中心的消息内容参数
                            sendMap.put("app_name", WmsHelp.APP_NAME_MIF);
                            // 线程发送消息
                            this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        map.put("msgInfo", resStr);
        return map;

    }
    
    
    
}
