package com.zx.emanage.loanreview.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingPaymentDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanreview.persist.WmsCreHousingTrialAssessmentDao;
import com.zx.emanage.loanreview.service.IWmsCreHousingTrialAssessmentService;
import com.zx.emanage.loanreview.vo.WmsCreHousingTrialAssessmentSearchBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.WmsSysDictDataDao;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreHousingPayment;
import com.zx.emanage.util.gen.entity.WmsCreHousingTrialAssessment;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrehousingtrialassessmentService")
public class WmsCreHousingTrialAssessmentServiceImpl implements IWmsCreHousingTrialAssessmentService {
	private static Logger log = LoggerFactory.getLogger(WmsCreHousingTrialAssessmentServiceImpl.class);

	@Autowired
	private WmsCreHousingTrialAssessmentDao wmscrehousingtrialassessmentDao;

	@Autowired
	private WmsCreCreditHeadDao wmsCreCreditHeadDao;
	
	@Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;
	
    @Autowired
    private IWmsLoanWorkFlowService wmsLoanWorkFlowService;//新房贷贷款流程
    
    @Autowired
    private WmsCreCustomerChangeLineHouseinfoDao wmsCreCustomerChangeLineHouseinfoDao;
    
    @Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;
    
    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao; 
	
	@Autowired
	private   PmPersonnelDao pmPersonnelDao;
	
    @Autowired
 	private WmsSysDictDataDao wmssysdictdataDao;//字典表
    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private WmsCreHousingPaymentDao wmsCreHousingPaymentDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreHousingTrialAssessmentSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrehousingtrialassessmentDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreHousingTrialAssessmentSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrehousingtrialassessmentDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrehousingtrialassessmentDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreHousingTrialAssessment getInfoByPK(Integer wms_cre_housing_trial_assessment_id) {
		return wmscrehousingtrialassessmentDao.get(wms_cre_housing_trial_assessment_id);
	}

	/**
	 * Description :房产初评保存
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@Override	
	@Transactional
	public String save(WmsCreHousingTrialAssessmentSearchBeanVO bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		Timestamp now = new Timestamp(System.currentTimeMillis());
        // 调用流程
        WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
        // 判断用户操作 1为保存 保存需要走流程
        if (bean.getFlag().equals("1"))
        {
            // 是否重新核查   is_again_appl;"是否重新审批 1是0否"; 
            approveHouseWorkFlowVO.setIs_again_appl(bean.getIs_again_appl());
            approveHouseWorkFlowVO.setUserId(user.getUserId().toString());
            approveHouseWorkFlowVO.setAdvice(bean.getApproval_advice());
            approveHouseWorkFlowVO.setTaskId(bean.getTaskId());
            approveHouseWorkFlowVO.setBusinessId(bean.getWms_cre_credit_head_id().toString());
            approveHouseWorkFlowVO.setDebtkey("FCCP");// 流程节点(房产初评为B)
            if (StringUtil.isNotBlank(bean.getCheck_pay()))
            {
                approveHouseWorkFlowVO.setCheck_pay(new BigDecimal(bean.getCheck_pay()));// 核查金额
            }
            if (bean.getApproval_result().equals("1"))
            {// 通过
                approveHouseWorkFlowVO.setPass("true");
            }
            else if (bean.getApproval_result().equals("2"))
            {// 退件
                approveHouseWorkFlowVO.setPass("back");
            }
            else if (bean.getApproval_result().equals("3"))
            {// 拒件
                approveHouseWorkFlowVO.setPass("false");
            }
            else if (bean.getApproval_result().equals("4"))
            {// 直接验房
                approveHouseWorkFlowVO.setPass("direct");
            }
            else if (bean.getApproval_result().equals("5"))
            {// 曾终审
                approveHouseWorkFlowVO.setPass("directappl");
            }
            approveHouseWorkFlowVO.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id().toString());
            approveHouseWorkFlowVO.setAdvice(bean.getApproval_advice());
            // 判断是否走流程
            if (approveHouseWorkFlowVO != null && approveHouseWorkFlowVO.getPass() != null)
            {
                // 走流程
                Map<String, Object> flowMap = wmsLoanWorkFlowService.publicApprovalStatus(approveHouseWorkFlowVO);
                // 判断流程是否正常
                if (flowMap != null && flowMap.get("result") != null && "statusError".equals(flowMap.get("result").toString()))
                {
                    return flowMap.get("result").toString();
                }
            }
        }
		
        WmsCreHousingTrialAssessment tb = JSON.parseObject(bean.getWmsCreHousingTrialAssessment_String(), WmsCreHousingTrialAssessment.class);

        if (StringUtils.isNotEmpty(bean.getHouse_buy_money()))
        {
            tb.setHouse_buy_money(Double.parseDouble(bean.getHouse_buy_money()));
        }

		tb.setLast_update_user_id(user.getUserId());
        tb.setLast_update_timestamp(now);
        tb.setVehicle_evaluation_person(bean.getVehicle_evaluation_person());
        tb.setCheck_pay(bean.getCheck_pay());
        tb.setOld_appro_limit(bean.getOld_appro_limit());
        // 曾终审时候才存
        if (bean.getApproval_result().equals("5"))
        {
            tb.setIs_again_appl(bean.getIs_again_appl());
        }
        if (tb.getCreate_timestamp() == null)
        {// 新增
            tb.setCreate_timestamp(now);
            tb.setCreate_user_id(user.getUserId());
            tb.setCreate_user_name(user.getRealname());
            tb.setEnable_flag("1");
            tb.setIs_compound(bean.getIs_compound());
            tb.setHouse_remark(bean.getHouse_remark());
            tb.setHouse_age(bean.getHouse_age());
            ret = wmscrehousingtrialassessmentDao.save(tb);
        }
        else
        {// 修改
            ret = wmscrehousingtrialassessmentDao.update(tb);
        }

        if (ret == 0)
        {
            return "error";
        }
        // 如果不是退件和拒贷则检查缴费表
        if (!bean.getApproval_result().equals("back") && !bean.getApproval_result().equals("false"))
        {
            Map<String,Object> parameters =new HashMap<>();
            parameters.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
            List<Map<String, Object>> listP = wmsCreHousingPaymentDao.search(parameters);
            // 如果不存在缴费记录
            if (listP == null || listP.size() == 0)
            {
                // 查询信息
                parameters = wmsCreHousingPaymentDao.housingPaymentVerificationDisp(parameters);
                WmsCreHousingPayment wmsCreHousingPayment = new WmsCreHousingPayment();
                wmsCreHousingPayment.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
                if (parameters != null)
                {
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
                    java.sql.Date datesql = null;
                    try
                    {
                        // 日期转换
                        datesql = parameters.get("application_date") == null ? null : new java.sql.Date(sdf.parse(parameters.get("application_date").toString()).getTime());
                    }
                    catch (Exception e)
                    {

                    }
                    // 客户姓名
                    wmsCreHousingPayment.setCustomer_name(parameters.get("customer_name") == null ? null : parameters.get("customer_name").toString());
                    // 申请日期
                    wmsCreHousingPayment.setApplication_date(datesql);
                    // 客户电话：申请人手机号码1
                    wmsCreHousingPayment.setMobile_telephone(parameters.get("mobile_telephone") == null ? null : parameters.get("mobile_telephone").toString());
                    // 小区名称
                    wmsCreHousingPayment.setCommunity_name(parameters.get("community_name") == null ? null : parameters.get("community_name").toString());
                    // 业务员id
                    wmsCreHousingPayment.setSalesman_id((parameters.get("salesman_id") == null || "".equals(parameters.get("salesman_id"))) ? null : Integer.valueOf(parameters.get("salesman_id").toString()));
                    // 业务员姓名
                    wmsCreHousingPayment.setSalesman_name(parameters.get("salesman_name") == null ? null : parameters.get("salesman_name").toString());
                    // 团队经理ID
                    wmsCreHousingPayment.setTeam_manager_id((parameters.get("team_manager_id") == null || "".equals(parameters.get("team_manager_id"))) ? null : Integer.valueOf(parameters.get("team_manager_id").toString()));
                    // 团队经理Name
                    wmsCreHousingPayment.setTeam_manager_name(parameters.get("team_manager_name") == null ? null : parameters.get("team_manager_name").toString());
                    // 地址
                    wmsCreHousingPayment.setHouse_address(parameters.get("house_address") == null ? null : parameters.get("house_address").toString());
                }
                wmsCreHousingPayment.setCreate_user_id(user.getUserId());
                wmsCreHousingPayment.setCreate_user_name(user.getRealname());
                wmsCreHousingPayment.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                wmsCreHousingPayment.setLast_update_user_id(user.getUserId());
                wmsCreHousingPayment.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                wmsCreHousingPayment.setEnable_flag("1");
                wmsCreHousingPayment.setPayment_amount(new BigDecimal(0));
                wmsCreHousingPaymentDao.save(wmsCreHousingPayment);
            }
        }
		if(StringUtils.isNotEmpty(bean.getFlag())) {
		    if(bean.getFlag().equals("1")) {//1为保存

		        WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());//查询单据信息
		        WmsCreCreditHead wHead=new WmsCreCreditHead();
		        wHead.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id());
		        wHead.setAssessment_id(user.getUserId().toString());
		        wHead.setAssessment_advice(bean.getApproval_advice());
		        wHead.setAssessment_datetime(now);
		        wHead.setAssessment_result(bean.getApproval_result());
		        wHead.setAssessment_result_page(bean.getApproval_result());
		        wmsCreCreditHeadDao.updateforhouse(wHead);
	            //新流程发送短信
                //查询客户姓名
                Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
                customerChangeParamMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
                customerChangeParamMap.put("is_major", "1");
                customerChangeParamMap.put("enable_flag", "1");
                List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
                PmPersonnel pmPersonnel=new PmPersonnel();
             	pmPersonnel.setPersonnel_id(wmsCreCreditHead.getSalesman_id());
            	List<PmPersonnel> listp=pmPersonnelDao.getListByEntity(pmPersonnel);
            	pmPersonnel=listp.get(0);
                //调用发送信息的接口出现异常 不会影响正常流程  
                try{
	                if(customerChangeList != null && customerChangeList.size() == 1) {
	                    //调用方法map
	                    Map<String, Object> sendMap = new HashMap<String, Object>();
	                    //发送短信map
	                    Map<String, String> msgMap = new HashMap<String, String>();
                        // 极光参数map
	                    Map<String, String> paramMap = new HashMap<String, String>();
                        // 极光 附加参数map
	                    Map<String, String> msg_extras = new HashMap<String, String>();
                        // 如果拒贷不发送短信
                        if (!"fasle".equals(approveHouseWorkFlowVO.getPass()))
                        {
                            // 不发送短信息了20170523
                            // sendMap.put("short_message", "1");
                        }
                        // 初评通过和直接验房都是通过
                        if ("true".equals(approveHouseWorkFlowVO.getPass()) || "direct".equals(approveHouseWorkFlowVO.getPass()) || "directappl".equals(approveHouseWorkFlowVO.getPass()))
                        {
                            msgMap.put("tpl_id", "1692");// 短信消息编码
                            sendMap.put("msg_code", "10002");// 极光消息中心消息编码
                            sendMap.put("msg_code_role", "10002");// 极光消息中心消息编码--流程角色

                        }
                        // 初评退回 短信编码和极光消息中心编码
                        else if ("back".equals(approveHouseWorkFlowVO.getPass()))
                        {
                            msgMap.put("tpl_id", "1693");// 短信消息编码
                            sendMap.put("msg_code", "10003");// 极光消息中心消息编码
                            sendMap.put("msg_code_role", "10003");// 极光消息中心消息编码--流程角色
                        }
                        // 初评拒贷 极光和消息中心编码
                        else if ("false".equals(approveHouseWorkFlowVO.getPass()))
                        {
                        	msgMap.put("tpl_id", "1693");// 短信消息编码
                            sendMap.put("msg_code", "10004");// 极光消息中心消息编码
                            sendMap.put("msg_code_role", "10004");// 极光消息中心消息编码--流程角色
                        }
	                    msgMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
	                    msgMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
	                    msgMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());
	                    if(listp!=null&&listp.size()>0){
	                    	 msgMap.put("tel", listp.get(0).getPersonnel_contacttel());//手机号码
	                    }
	                    paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
	                    paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
	                    paramMap.put("vehicle_evaluation_price", tb.getVehicle_evaluation_price());
	                    paramMap.put("advice", approveHouseWorkFlowVO.getAdvice());//审核意见
	                    paramMap.put("city", wmsCreCreditHead.getCity());
                        paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
	                    msgMap.put("paramJson", JSON.toJSONString(paramMap));
	                    sendMap.put("msgMap", msgMap);
	                    //激光推送
	                    sendMap.put("quart_message","1");//消息附加参数
	                    //如果是消息中心
	    	            sendMap.put("message_center","1");
	    	          
	    	            msg_extras.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());//添加参数
	    	            sendMap.put("msg_extras", msg_extras);//消息附加参数  
	    	            
	    	            sendMap.put("is_oneself", "1");//给业务员自己发送消息
	    	            
	    	            paramMap.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());
	    	            paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
	    	            paramMap.put("create_user_id", user.getUserId().toString());
	    	            paramMap.put("create_user_name", user.getRealname());
	    	            paramMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                        paramMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
	    	            paramMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());
                        paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
	    	            sendMap.put("msg_map", paramMap);//极光推送和消息中心的消息内容参数
                        sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                        // 线程发送消息
                        this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
	                }
                }catch (Exception e){
                	e.printStackTrace();        
                }
                // 通过 直接验房
                if (bean.getApproval_result().equals("1") || bean.getApproval_result().equals("4") || bean.getApproval_result().equals("5"))
                {
                    // 调用发送信息的接口出现异常 不会影响正常流程 --团队经理
	                try{
	    	            //调用方法map
	    	            Map<String, Object> sendMap = new HashMap<String, Object>();
	    	            //参数map
	    	            Map<String, String> paramMap = new HashMap<String, String>();
	    	            //参数map
	                    Map<String, String> msg_extras = new HashMap<String, String>();

	    	            sendMap.put("user_id", wmsCreCreditHead.getSalesman_id());
                        sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
	    	            paramMap.put("bill_code", wHead.getBill_code());
	    	            paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
	    	            
	    	            //消息中心使用
	    	            sendMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
						sendMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
						sendMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());
						
	    	            //极光推送需要的数据参数
	    	            sendMap.put("quart_message", "1");//消息附加参数
	    	            paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name()+pmPersonnel.getPersonnel_shortcode());
	    	            paramMap.put("advice", approveHouseWorkFlowVO.getAdvice());//审核意见
	    	            paramMap.put("vehicle_evaluation_price", tb.getVehicle_evaluation_price());//评估价值
	    	           
                        msg_extras.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());// 添加参数
	    	            sendMap.put("msg_extras", msg_extras);//消息附加参数    
	    	            //发送消息中心   焦德龙
	    	    		//如果是消息中心
	    	            sendMap.put("message_center", "1");
	    	            //提交人
	    	            paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
	    	            paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
	    	            paramMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id().toString());
	    	            paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
	    	            paramMap.put("create_user_id", user.getUserId().toString());
	    	            paramMap.put("create_user_name", user.getRealname());
	    	            paramMap.put("city", wmsCreCreditHead.getCity());
                        paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
	    	            sendMap.put("msg_map", paramMap);//极光推送的消息内容参数
    	            	sendMap.put("role_outside", "1");//判断获取人
    	            	List<String> post_number_list=new ArrayList<>();
    	            	post_number_list.add("TDJL");
    	            	sendMap.put("post_number_list", post_number_list);//判断获取人	 
                        sendMap.put("msg_code", "10005");// 消息编码
                        sendMap.put("msg_code_role", "10005");// 消息编码
                        sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                        // 线程发送消息
                        this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
	                } catch (Exception e) {
	                	e.printStackTrace();        
	                }
                    // 调用发送信息的接口出现异常 不会影响正常流程--门店经理
                    try
                    {
                        Map<String, Object> deptMap = new HashMap<String, Object>();
                        deptMap.put("dept_id", wmsCreCreditHead.getSalesman_dept_id());
                        deptMap.put("dept_level", "6");
                        String dept_info = sysDeptDao.getDeptInfoForSendInfo(deptMap);
                        // 调用方法map
                        Map<String, Object> sendMap = new HashMap<String, Object>();
                        // 参数map
                        Map<String, String> paramMap = new HashMap<String, String>();
                        // 参数map
                        Map<String, String> msg_extras = new HashMap<String, String>();

                        sendMap.put("user_id", wmsCreCreditHead.getSalesman_id());
                        sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                        paramMap.put("bill_code", wHead.getBill_code());
                        paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());

                        // 消息中心使用
                        sendMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                        sendMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                        sendMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());

                        // 极光推送需要的数据参数
                        sendMap.put("quart_message", "1");// 消息附加参数
                        paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                        paramMap.put("advice", approveHouseWorkFlowVO.getAdvice());// 审核意见
                        paramMap.put("vehicle_evaluation_price", tb.getVehicle_evaluation_price());// 评估价值
                        paramMap.put("dept_info", dept_info);// 部门信息
                        msg_extras.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());// 添加参数
                        sendMap.put("msg_extras", msg_extras);// 消息附加参数
                        sendMap.put("dept_info", dept_info);// 部门信息
                        // 发送消息中心 焦德龙
                        // 如果是消息中心
                        sendMap.put("message_center", "1");
                        // 提交人
                        paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                        paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                        paramMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id().toString());
                        paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                        paramMap.put("create_user_id", user.getUserId().toString());
                        paramMap.put("create_user_name", user.getRealname());
                        paramMap.put("city", wmsCreCreditHead.getCity());
                        paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                        sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                        sendMap.put("role_outside", "1");// 判断获取人
                        List<String> post_number_list = new ArrayList<>();
                        post_number_list.add("MDJL");
                        sendMap.put("post_number_list", post_number_list);// 判断获取人
                        sendMap.put("msg_code", "10015");// 消息编码
                        sendMap.put("msg_code_role", "10015");// 消息编码
                        sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                        // 线程发送消息
                        this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    // 调用发送信息的接口出现异常 不会影响正常流程--业务部
                    try
                    {
                        Map<String, Object> deptMap = new HashMap<String, Object>();
                        deptMap.put("dept_id", wmsCreCreditHead.getSalesman_dept_id());
                        deptMap.put("dept_level", "5,6");
                        String dept_info = sysDeptDao.getDeptInfoForSendInfo(deptMap);
                        // 调用方法map
                        Map<String, Object> sendMap = new HashMap<String, Object>();
                        // 参数map
                        Map<String, String> paramMap = new HashMap<String, String>();
                        // 参数map
                        Map<String, String> msg_extras = new HashMap<String, String>();

                        sendMap.put("user_id", wmsCreCreditHead.getSalesman_id());
                        sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                        paramMap.put("bill_code", wHead.getBill_code());
                        paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());

                        // 消息中心使用
                        sendMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                        sendMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                        sendMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());

                        // 极光推送需要的数据参数
                        sendMap.put("quart_message", "1");// 消息附加参数
                        paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                        paramMap.put("advice", approveHouseWorkFlowVO.getAdvice());// 审核意见
                        paramMap.put("vehicle_evaluation_price", tb.getVehicle_evaluation_price());// 评估价值
                        paramMap.put("dept_info_md", dept_info);// 部门信息
                        msg_extras.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());// 添加参数
                        sendMap.put("msg_extras", msg_extras);// 消息附加参数
                        sendMap.put("dept_info_md", dept_info);// 部门信息
                        // 发送消息中心 焦德龙
                        // 如果是消息中心
                        sendMap.put("message_center", "1");
                        // 提交人
                        paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                        paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                        paramMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id().toString());
                        paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                        paramMap.put("create_user_id", user.getUserId().toString());
                        paramMap.put("create_user_name", user.getRealname());
                        paramMap.put("city", wmsCreCreditHead.getCity());
                        paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                        sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数

                        sendMap.put("role_value", "1");// 判断获取人
                        sendMap.put("role_name", WmsHelp.YWB_ROLE_NAME);// 判断获取人
                        sendMap.put("msg_code", "20004");// 消息编码
                        sendMap.put("msg_code_role", "20004");// 消息编码
                        sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                        // 线程发送消息
                        this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    // 是否重新核查 is_again_appl;"是否重新审批 1是0否";
                    if (bean != null && bean.getIs_again_appl() != null && "0".equals(bean.getIs_again_appl()) && "5".equals(bean.getApproval_result()))
                    {
                        // 调用发送信息的接口出现异常 不会影响正常流程--发送pc端消息
                        try
                        {
                            // 调用方法map
                            Map<String, Object> sendMap = new HashMap<String, Object>();
                            // 参数map
                            Map<String, String> paramMap = new HashMap<String, String>();
                            // 根据菜单查询人员
                            sendMap.put("menu_url", WmsHelp.MENU_URL_ZSSP_LIST);// 终审审批ual
                            sendMap.put("salesman_dept_id", wmsCreCreditHead.getSalesman_dept_id());
                            sendMap.put("role_value", "1");
                            paramMap.put("bill_status", "E");// 待终审单据状态
//                            // 获取初评单据数量
//                            List<Map<String, Object>> listS = wmsCreCreditHeadDao.getStatusCount(paramMap);
//                            if (listS != null)
//                            {
//                                paramMap.put("number", listS.size() + "");
//                            }
//                            else
//                            {
                                paramMap.put("number", "1");
                            // }
                            sendMap.put("pc_msg_code", "20015");// pc端消息code
                            sendMap.put("pc_message", "1");// 标识发送pc消息
                            sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                            this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
		    }
		}
		
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreHousingTrialAssessment bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrehousingtrialassessmentDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreHousingTrialAssessment() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreHousingTrialAssessment> getListByEntity(WmsCreHousingTrialAssessment queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreHousingTrialAssessment> beanList = wmscrehousingtrialassessmentDao.getListByEntity(queryInfo);
		return beanList;
	}
	
	/**
     * Description : 房产初审评估单初始化
     * @param bean user
     * @return resMap
     * @author wangyihan
     */ 
	@Override
    @Transactional
    public Map<String, Object> wmsCreHouseIngtrialassessmentDisp(WmsCreHousingTrialAssessmentSearchBeanVO bean, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        paramMap.put("sortname", bean.getSortname());
        paramMap.put("sortorder", bean.getSortorder());
        
        Map<String, Object> resMap = new HashMap<String, Object>();

        List<Map<String, Object>> list = wmscrehousingtrialassessmentDao.search(paramMap);
        
        paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        List<Map<String, Object>> wmsCreCustomerChangeLineHouseInfoList = wmsCreCustomerChangeLineHouseinfoDao.searchHCHBYMccid(paramMap);
        
        if(wmsCreCustomerChangeLineHouseInfoList.size() > 0) {
        	if(list.size() > 0) {
	        	wmsCreCustomerChangeLineHouseInfoList.get(0).put("vehicle_evaluation_person", list.get(0).get("vehicle_evaluation_person"));
	        	wmsCreCustomerChangeLineHouseInfoList.get(0).put("vehicle_evaluation_price", list.get(0).get("vehicle_evaluation_price"));
	        	wmsCreCustomerChangeLineHouseInfoList.get(0).put("remark", list.get(0).get("remark"));
	        	wmsCreCustomerChangeLineHouseInfoList.get(0).put("create_timestamp_str", list.get(0).get("create_timestamp_str"));
	        	wmsCreCustomerChangeLineHouseInfoList.get(0).put("last_update_timestamp_str", list.get(0).get("last_update_timestamp_str"));
	        	wmsCreCustomerChangeLineHouseInfoList.get(0).put("update", "1");
	        	wmsCreCustomerChangeLineHouseInfoList.get(0).put("wms_cre_housing_trial_assessment_id", list.get(0).get("wms_cre_housing_trial_assessment_id"));
        	}
            resMap.put("wmsCreCustomerChangeLineHouseinfo", wmsCreCustomerChangeLineHouseInfoList.get(0));
        }else {
            resMap.put("wmsCreCustomerChangeLineHouseinfo", null);
        }
        
        resMap.put("user_id", user.getUserId());
        
        return resMap;
    }
	
	/**
     * Description : 房产初审评估单初始化--查看方法
     * @param bean user
     * @return resMap
     * @author wangyihan
     */
	@Override
	public Map<String, Object> getwmsCreHouseIngtrialassessmentDisp(
			WmsCreHousingTrialAssessmentSearchBeanVO bean) {
		Map<String, Object> map=new HashMap<>();
		map.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
		List<Map<String, Object>> list = wmscrehousingtrialassessmentDao.search(map);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}   
	
}
