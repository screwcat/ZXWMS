package com.zx.emanage.loanappro.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zx.emanage.cremanage.persist.WmsCreCarpApproInfoDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditAttDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditAutoExpireDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.service.IWmsCarLoanWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.persist.WmsCreCreditFinalApplDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditGroupDao;
import com.zx.emanage.loanappro.service.WmsCreloanApproVisaInterviewService;
import com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.util.gen.SysConstant;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCreCarpApproInfo;
import com.zx.emanage.util.gen.entity.WmsCreCreditAtt;
import com.zx.emanage.util.gen.entity.WmsCreCreditAutoExpire;
import com.zx.emanage.util.gen.entity.WmsCreCreditFinalAppl;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreloanApproVisaInterviewServiceImpl
 * 模块名称：终审
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@Service("wmscreloanapprovisainterviewService")
public class WmsCreloanApproVisaInterviewServiceImpl implements WmsCreloanApproVisaInterviewService
{
    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheaddao;

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;// 流程

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;// 房贷流程

    @Autowired
    private WmsCreCreditAttDao wmsCreCreditAttDao;// 信贷附件上传表
    @Autowired
    private IWmsCarLoanWorkFlowService carLoanWorkFlowService;//流程
	@Autowired
	private WmsCreCarpApproInfoDao wmscrecarpapproinfoDao;
	@Autowired
	private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程
	@Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;
	@Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;
	@Autowired
    private PmPersonnelDao pmPersonnelDao;
    @Autowired
    private WmsCreCreditGroupDao wmsCreCreditGroupDao;// 组合贷表

    @Autowired
    private WmsCreCreditFinalApplDao wmscrecreditfinalapplDao;//终审表

    @Autowired
    private WmsCreCreditAutoExpireDao wmsCreCreditAutoExpireDao;// 终审到期表

    @Autowired
    private SysDeptDao sysDeptDao;
    /**
     * 终审审批
     */
    @Override
    @Transactional
    public String save(WmsCreCreditHeadVO bean, UserBean user, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
    	
        if (bean.getCredit_limit() != null)
        {
        	if("false".equals(approveWorkFlowVO.getPass())){
	        	bean.setNullify_type(1);//单据作废环节
	        	bean.setNullify_user_name(user.getRealname());//单据作废环节
	        	bean.setNullify_user_id(user.getUserId());//单据作废操作ID
	        	bean.setNullify_timestamp(new Timestamp(System.currentTimeMillis()));//单据作废操作时间
	        	bean.setNullify_reason(approveWorkFlowVO.getAdvice());//单据作废原因
        	}
            bean.setAppro_limit(new BigDecimal(bean.getCredit_limit()));// 把申请额度的值赋值给终审字段
            wmscrecreditheaddao.updateLimit(bean.getInfoMapAppro());
        }else if("false".equals(approveWorkFlowVO.getPass())){
        	WmsCreCreditHead wmsCreCreditHead=new WmsCreCreditHead();
            wmsCreCreditHead.setNullify_type("1");// 单据作废环节
        	wmsCreCreditHead.setNullify_user_name(user.getRealname());//单据作废环节
        	wmsCreCreditHead.setNullify_user_id(user.getUserId());//单据作废操作ID
        	wmsCreCreditHead.setNullify_timestamp(new Timestamp(System.currentTimeMillis()));//单据作废操作时间
        	wmsCreCreditHead.setNullify_reason(approveWorkFlowVO.getAdvice());//单据作废原因
        	wmscrecreditheaddao.update(wmsCreCreditHead);
        }
        approveWorkFlowVO.setUser_id(user.getUserId());// 保存userid///
        if (approveWorkFlowVO.getPass().equals("iscontinue"))
        {
            creditWorkFlowService.contiuneCreditAprove(approveWorkFlowVO);
        }
        else
        {
            creditWorkFlowService.creCheckloanApproApproveWorkFlow(approveWorkFlowVO);// 保存工作流内容
        }
        return "success";
    }

    /**
     * 终审面签
     */
    @Override
    @Transactional
    public String saveInterview(WmsCreCreditHeadVO bean, UserBean user, WmsCreditWorkFlowVO approveWorkFlowVO,
                                String fileArrs)
    {
        // 判断贷款金额是否为空
       /* if (bean.getAppro_limit() != null)
        {
            bean.setVisa_limit(bean.getAppro_limit());// 将终审的金额赋值给面签金额字段
            wmscrecreditheaddao.updateLimit(bean.getInfoMapVisa());
        } */
        if (bean.getAppro_limit() != null)
        {
        	if("false".equals(approveWorkFlowVO.getPass())){
	        	bean.setNullify_type(2);//单据作废环节
	        	bean.setNullify_user_name(user.getRealname());//单据作废环节
	        	bean.setNullify_user_id(user.getUserId());//单据作废操作ID
	        	bean.setNullify_timestamp(new Timestamp(System.currentTimeMillis()));//单据作废操作时间
	        	bean.setNullify_reason(approveWorkFlowVO.getAdvice());//单据作废原因
        	}
        	bean.setVisa_limit(bean.getAppro_limit());// 将终审的金额赋值给面签金额字段
            wmscrecreditheaddao.updateLimit(bean.getInfoMapVisa());
        }else if("false".equals(approveWorkFlowVO.getPass())){
        	WmsCreCreditHead wmsCreCreditHead=new WmsCreCreditHead();
            wmsCreCreditHead.setNullify_type("1");// 单据作废环节
        	wmsCreCreditHead.setNullify_user_name(user.getRealname());//单据作废环节
        	wmsCreCreditHead.setNullify_user_id(user.getUserId());//单据作废操作ID
        	wmsCreCreditHead.setNullify_timestamp(new Timestamp(System.currentTimeMillis()));//单据作废操作时间
        	wmsCreCreditHead.setNullify_reason(approveWorkFlowVO.getAdvice());//单据作废原因
        	wmscrecreditheaddao.update(wmsCreCreditHead);
        }
        // 判断当审批结果为同意的时候,才能改变还款期限和产品类型
        if ("true".equals(approveWorkFlowVO.getPass()))
        {
            Map<String, Object> parmMap = new HashMap<String, Object>();
            parmMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
            // 存储还款期限
            parmMap.put("max_repayment_time_limit", bean.getMax_repayment_time_limit());
            // 产品类型
            parmMap.put("cre_loan_type", bean.getCre_loan_type());
            wmscrecreditheaddao.updateLimit(parmMap);
        }
        // 保存面签上传附件信息wms_cre_credit_att
        List<WmsCreCreditAtt> attachment = JsonUtil.jsonArrayToList(fileArrs, WmsCreCreditAtt.class); // 前台json附件路径数据转换为list
        Timestamp syTimestamp = new Timestamp(System.currentTimeMillis());
        for (int i = 0; i < attachment.size(); i++)
        {
            WmsCreCreditAtt wCreCreditAtt = attachment.get(i);
            wCreCreditAtt.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());// 存储单据主键
            wCreCreditAtt.setCreate_timestamp(syTimestamp);// 存储当前时间
            wCreCreditAtt.setCreate_user_id(user.getUserId());// 创建者ID
            wCreCreditAtt.setCreate_user_name(user.getRealname());// 创建者姓名
            wCreCreditAtt.setData_type("5");// 面签附件
            wCreCreditAtt.setEnable_flag("1");// 附件是否有效
            wmsCreCreditAttDao.save(wCreCreditAtt);// 保存数据
        }
        // 获取当前登录人的ID
        approveWorkFlowVO.setUser_id(user.getUserId());// 保存userid///
        // 调用工作流中能够实现终审面签审批流程的方法
        creditWorkFlowService.creCheckloanApproInterView(approveWorkFlowVO);// 保存工作流内容
        // 返回结果
        return "success";
    }

      /***
       * 
       * @Title: saveforhouse
       * @Description: TODO(终审保存)
       * @param bean
       * @param user
       * @param approveHouseWorkFlowVO
       * @param beanVo
       * @return 
       * @author: baisong
       * @time:2017年2月20日 下午5:47:29
       * @see com.zx.emanage.loanappro.service.WmsCreloanApproVisaInterviewService#saveforhouse(com.zx.emanage.util.gen.entity.WmsCreCreditFinalAppl, com.zx.sframe.util.vo.UserBean, com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO, com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO)
       * history:
       * 1、2017年2月20日 baisong 创建方法
       */
    @Override
    @Transactional
    public String saveforhouse(WmsCreCreditFinalAppl bean, UserBean user, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, WmsCreCreditHeadVO beanVo)
    {
        //流程类
    	approveHouseWorkFlowVO.setDebtkey("ZSSP");//贷款终审
        approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));//保存userid
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
        // 终审金额为空时更新数据库为null
        if (beanVo.getAppro_limit() != null)
        {
            wmscrecreditheaddao.updateLimit(beanVo.getInfoMapAppro());
        }
        else
        {
            wmscrecreditheaddao.updateLimitAll(beanVo.getInfoMapAppro());
            Map<String, Object> map = new HashMap<>();
            map.put("wms_cre_credit_head_id", beanVo.getWms_cre_credit_head_id());
            map.put("appro_limit", beanVo.getAppro_limit());
            // 更新终审审批信息
            wmscrecreditfinalapplDao.updateApprolimit(map);
        }
        Timestamp now=new Timestamp(System.currentTimeMillis());
        //终审表
        if(bean.getWms_cre_credit_final_appl_id()==null||"".equals(bean.getWms_cre_credit_final_appl_id())){
            bean.setCreate_timestamp(now);
            bean.setCreate_user_id(user.getUserId());
            bean.setCreate_user_name(user.getRealname());
            bean.setLast_update_timestamp(now);
            bean.setLast_update_user_id(user.getUserId());
            bean.setWms_cre_credit_final_appl_id(null);
            bean.setEnable_flag("1");
            wmscrecreditfinalapplDao.save(bean);
        }else {
            bean.setLast_update_timestamp(now);
            bean.setLast_update_user_id(user.getUserId());
            wmscrecreditfinalapplDao.update(bean);
        }
    	 //查询贷款单据信息
        WmsCreCreditHead wmsCreCreditHead = wmscrecreditheaddao.get(bean.getWms_cre_credit_head_id());
        if("false".equals(approveHouseWorkFlowVO.getPass())){//如果单据审批不通过
            wmsCreCreditHead.setNullify_type(approveHouseWorkFlowVO.getDebtkey());// 终审审批环节"5"
            wmsCreCreditHead.setNullify_user_name(user.getRealname());
            wmsCreCreditHead.setNullify_user_id(user.getUserId());
            wmsCreCreditHead.setNullify_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsCreCreditHead.setNullify_reason(approveHouseWorkFlowVO.getAdvice());//
    	    int ret= wmscrecreditheaddao.update(wmsCreCreditHead);//更新贷款主表
        }
        // 处理终审到期表
        WmsCreCreditAutoExpire wmsCreCreditAutoExpire = new WmsCreCreditAutoExpire();
        wmsCreCreditAutoExpire.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
        wmsCreCreditAutoExpire.setIs_new("0");// 是否是最新 1 是 0否
        wmsCreCreditAutoExpire.setEnable_flag("1");
        wmsCreCreditAutoExpireDao.update(wmsCreCreditAutoExpire);
        // 保存终审到期表
        wmsCreCreditAutoExpire.setAppro_time(new Timestamp(System.currentTimeMillis()));// 终审时间
        wmsCreCreditAutoExpire.setExpire_days(0);// 延期天数
        wmsCreCreditAutoExpire.setTotal_expire_days(0);// 延期总天数
        wmsCreCreditAutoExpire.setOperator_id(user.getUserId());// 操作人id
        wmsCreCreditAutoExpire.setOperator_name(user.getRealname());// 操作人姓名
        wmsCreCreditAutoExpire.setExpire_reason("终审通过进入倒计时");// 延期原因
        wmsCreCreditAutoExpire.setIs_new("1");// 是否是最新 1 是 0否
        wmsCreCreditAutoExpire.setCreate_user_id(user.getUserId());
        wmsCreCreditAutoExpire.setCreate_user_name(user.getRealname());
        wmsCreCreditAutoExpire.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsCreCreditAutoExpire.setLast_update_user_id(user.getUserId());
        wmsCreCreditAutoExpire.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsCreCreditAutoExpireDao.save(wmsCreCreditAutoExpire);


        // 查询客户信息-- 发送消息
        Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
        customerChangeParamMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        customerChangeParamMap.put("is_major", "1");
        customerChangeParamMap.put("enable_flag", "1");
        List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
        //调用发送信息的接口出现异常 不会影响正常流程  
        PmPersonnel pmPersonnel= pmPersonnelDao.get(wmsCreCreditHead.getSalesman_id());
         try{
    
            if(customerChangeList != null && customerChangeList.size() == 1) {
            	//终审人员提交终审额度  终审通过  发送给客户经理 短信/消息 /极光/
                //调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                //发送短信map
                Map<String, String> msgMap = new HashMap<String, String>();
                //参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                //参数map
                Map<String, String> msg_extras = new HashMap<String, String>();

                // 终审通过
                if ("true".equals(approveHouseWorkFlowVO.getPass()))
                {
                    // sendMap.put("short_message", "1");
                    msgMap.put("tpl_id", "1695");
                    sendMap.put("msg_code", "10006");// 消息编码
                    sendMap.put("msg_code_role", "10006");// 消息编码
                }
                // 退回
                else if ("back".equals(approveHouseWorkFlowVO.getPass()))
                {
                    // sendMap.put("short_message", "1");
                    msgMap.put("tpl_id", "2430");
                    sendMap.put("msg_code", "10007");// 消息编码
                    sendMap.put("msg_code_role", "10007");// 消息编码
                    paramMap.put("advice", approveHouseWorkFlowVO.getAdvice());// 不通过原因
                }
                // 拒贷
                else if ("false".equals(approveHouseWorkFlowVO.getPass()))
                {
                    sendMap.put("msg_code", "10008");// 消息编码
                    sendMap.put("msg_code_role", "10008");// 消息编码
                    paramMap.put("advice", approveHouseWorkFlowVO.getAdvice());// 不通过原因
                }
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                paramMap.put("appro_limit", bean.getAppro_limit().toString());
                paramMap.put("city", wmsCreCreditHead.getCity());
                msgMap.put("paramJson", new Gson().toJson(paramMap));
                
                sendMap.put("user_id", wmsCreCreditHead.getSalesman_id());
                
                msgMap.put("user_id",user.getUserId().toString());//消息发送人id
                msgMap.put("tel",pmPersonnel.getPersonnel_contacttel());//接受短信人电话
                sendMap.put("msgMap", msgMap);
                //如果是消息中心
                sendMap.put("message_center","1");
                sendMap.put("quart_message", "1");//消息附加参数

                msg_extras.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id().toString());
                sendMap.put("is_oneself", "1");//给当前业务员发
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getUserId().toString());
                paramMap.put("create_user_name", user.getRealname());
               
                //消息和激光推送
                paramMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id().toString());
                paramMap.put("personnel_id", pmPersonnel.getPersonnel_id().toString());
    			paramMap.put("personnel_shortCode", pmPersonnel.getPersonnel_shortcode());
    			paramMap.put("personnel_name",pmPersonnel.getPersonnel_name());
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
    			sendMap.put("msg_extras", msg_extras);//消息附加参数  
    			sendMap.put("msg_map", paramMap);//消息内容参数
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
        }catch (Exception e){
        	e.printStackTrace();        
        }
         if ("true".equals(approveHouseWorkFlowVO.getPass())) {//审批通过发送消息
    
          //调用发送信息的接口出现异常 不会影响正常流程  
            try{
            	//调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                //参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                //参数map
                Map<String, String> msg_extras = new HashMap<String, String>();
                sendMap.put("user_id", wmsCreCreditHead.getSalesman_id());
                paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name()+pmPersonnel.getPersonnel_shortcode());
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                
                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getUserId().toString());
                paramMap.put("create_user_name", user.getRealname());
                paramMap.put("city", wmsCreCreditHead.getCity());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
				paramMap.put("appro_limit", bean.getAppro_limit().toString());//终审审批金额
                //极光推送
                List<String> post_number_list=new ArrayList<>();
                post_number_list.add("TDJL");
                sendMap.put("post_number_list", post_number_list);//人员类型
                //极光推送需要的数据参数
                sendMap.put("quart_message", "1");//消息附加参数
                sendMap.put("msg_code", "10009");// 消息编码
                sendMap.put("msg_code_role", "10009");// 消息编码
                sendMap.put("role_outside", "1");//判断获取人
              
                //发送消息中心   焦德龙
        		//如果是消息中心
                sendMap.put("message_center", "1");
               
                //消息编码
                sendMap.put("msg_map", paramMap);//极光推送的消息内容参数
                sendMap.put("post_number_list", post_number_list);//人员类型
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());//添加参数
                sendMap.put("msg_extras", msg_extras);//消息附加参数  
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
                   
                /*取消给合同专员发送消息
                 *  //第二次发送
                 sendMap.remove("quart_message");//移除极光推送
                 sendMap.remove("role_outside");//移除选人
                 sendMap.remove("post_number_list");//移除团队经理等
                 sendMap.remove("role_outside");//移除
                 
                 sendMap.put("role_value",WorkflowRoleHelp.HOUSE_HTZY);//合同专员
                 sendMap.put("regionNumber",pmPersonnel.getPersonnel_regionnumber());//区域编码
                 sendMap.put("msg_code", "20012");//消息编码
                 sendMap.put("msg_code_role", "20012");//消息编码
                 sendMap.put("is_dis_area", "1");//地域区分
                 this.wmsCreCreditHeadService.getUserAndSendInfo(sendMap);*/
            }
            catch (Exception e)
            {
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
                paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());

                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getUserId().toString());
                paramMap.put("create_user_name", user.getRealname());
                paramMap.put("city", wmsCreCreditHead.getCity());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                paramMap.put("appro_limit", bean.getAppro_limit().toString());// 终审审批金额
                paramMap.put("dept_info", dept_info);// 部门信息
                // 极光推送
                List<String> post_number_list = new ArrayList<>();
                post_number_list.add("MDJL");
                sendMap.put("post_number_list", post_number_list);// 人员类型
                // 极光推送需要的数据参数
                sendMap.put("quart_message", "1");// 消息附加参数
                sendMap.put("msg_code", "10016");// 消息编码
                sendMap.put("msg_code_role", "10016");// 消息编码
                sendMap.put("role_outside", "1");// 判断获取人
                sendMap.put("dept_info", dept_info);// 部门信息

                // 发送消息中心 焦德龙
                // 如果是消息中心
                sendMap.put("message_center", "1");

                // 消息编码
                sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                sendMap.put("post_number_list", post_number_list);// 人员类型
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                sendMap.put("msg_extras", msg_extras);// 消息附加参数
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
                paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());

                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getUserId().toString());
                paramMap.put("create_user_name", user.getRealname());
                paramMap.put("city", wmsCreCreditHead.getCity());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                paramMap.put("appro_limit", bean.getAppro_limit().toString());// 终审审批金额
                paramMap.put("dept_info_md", dept_info);// 部门信息
                // 极光推送需要的数据参数
                sendMap.put("quart_message", "1");// 消息附加参数
                sendMap.put("msg_code", "20006");// 消息编码
                sendMap.put("msg_code_role", "20006");// 消息编码
                sendMap.put("dept_info_md", dept_info);// 部门信息
                // 发送消息中心
                sendMap.put("message_center", "1");
                sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                sendMap.put("msg_extras", msg_extras);// 消息附加参数
                sendMap.put("role_value", "1");// 判断获取人
                sendMap.put("role_name", WmsHelp.YWB_ROLE_NAME);// 判断获取人
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return "success";
    }
    /**
     * 终审审批--车模块
     */
    @Override
    @Transactional
    public String saveforcar(WmsCreCreditHeadVO bean, UserBean user, WmsCarLoanWorkFlowVO wVo)
    {
    	Map<String, Object> map=SysConstant.getCDInfo(bean.getCre_loan_type().toString());//获取贷款利率
    	bean.setLoan_interest_rate(new BigDecimal(map.get("CD_DKLL").toString()).multiply(new BigDecimal(100)));
    	wmscrecreditheaddao.updateLimit(bean.getInfoMapAppro());
    	wVo.setUserId(String.valueOf(user.getUserId()));// 保存userid///
    	//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
    	carLoanWorkFlowService.carLoanApprovalProcess(wVo,"6");//保存流程
    	//车贷——审核环节结果意见存储wms_cre_carp_appro_info
        Timestamp system =new Timestamp(System.currentTimeMillis());
        WmsCreCarpApproInfo  wmscrecarpapproinfo=new WmsCreCarpApproInfo();
        wmscrecarpapproinfo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
        wmscrecarpapproinfo.setCarp_appro_type(3);//车贷审核环节1为评估2为办件3为终审
        List<WmsCreCarpApproInfo> list=wmscrecarpapproinfoDao.getListByEntity(wmscrecarpapproinfo);
        wmscrecarpapproinfo.setCarp_appro_type(3);//车贷审核环节1为评估2为办件3为终审
        //wmscrecarpapproinfo.setIs_yd("true".equals(wVo.getInspection_pass())?1:0);//是否验点
        wmscrecarpapproinfo.setCarp_appro_pass("true".equals(wVo.getPass())?1:0);//审核结果
        wmscrecarpapproinfo.setCarp_appro_advice(wVo.getAdvice());//审核意见
        wmscrecarpapproinfo.setEnable_flag("1");
        if(list!=null&&list.size()>0){
        	wmscrecarpapproinfo.setLast_update_user_id(user.getUserId());
	        wmscrecarpapproinfo.setLast_update_timestamp(system);
        	wmscrecarpapproinfo.setWms_cre_carp_appro_info_id(list.get(0).getWms_cre_carp_appro_info_id());
        	wmscrecarpapproinfoDao.update(wmscrecarpapproinfo);
        }else{
	        wmscrecarpapproinfo.setCreate_user_id(user.getUserId());//创建人
	        wmscrecarpapproinfo.setCreate_timestamp(system);//创建人
	     	wmscrecarpapproinfo.setLast_update_user_id(user.getUserId());
	        wmscrecarpapproinfo.setLast_update_timestamp(system);
        	wmscrecarpapproinfoDao.save(wmscrecarpapproinfo);
        }
        return "success";
    }

}
