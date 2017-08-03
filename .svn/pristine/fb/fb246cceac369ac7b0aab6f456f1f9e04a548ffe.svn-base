package com.zx.emanage.loanappro.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingApprovalInfoDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.persist.WmsCreCreditVisaApplDao;
import com.zx.emanage.loanappro.service.IWmsCreCreditVisaApplService;
import com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO;
import com.zx.emanage.loanappro.vo.WmsCreCreditVisaApplSearchBeanVO;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditVisaAppl;
import com.zx.emanage.util.gen.entity.WmsCreHousingApprovalInfo;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditvisaapplService")
public class WmsCreCreditVisaApplServiceImpl implements IWmsCreCreditVisaApplService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditVisaApplServiceImpl.class);

	@Autowired
	private WmsCreCreditVisaApplDao wmscrecreditvisaapplDao;
    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheaddao;// 贷款主表

    @Autowired
    private IWmsLoanWorkFlowService wmsLoanWorkFlowService;// 流程类
    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;//客户变更表
    @Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;

    @Autowired
    private WmsCreHousingApprovalInfoDao wmsCreHousingApprovalInfoDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCreditVisaApplSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
	    List<Map<String,Object>>  list = wmscrecreditvisaapplDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

    /**
     * @Title: getListWithPaging
     * @Description: TODO(查询面签历史)
     * @param queryInfo
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午3:39:22
     * history:
     * 1、2017年2月21日 baisong 创建方法
     */
	@Override
	public Map<String, Object> getListWithPaging(WmsCreCreditVisaApplSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());

        List<Map<String,Object>> list = wmscrecreditvisaapplDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecreditvisaapplDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCreditVisaAppl getInfoByPK(Integer wms_cre_credit_visa_appl_id) {
		return wmscrecreditvisaapplDao.get(wms_cre_credit_visa_appl_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCreditVisaAppl bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecreditvisaapplDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCreditVisaAppl bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecreditvisaapplDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCreditVisaAppl() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCreditVisaAppl> getListByEntity(WmsCreCreditVisaAppl queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCreditVisaAppl> beanList = wmscrecreditvisaapplDao.getListByEntity(queryInfo);
		return beanList;
	}

    /**
     * @Title: saveforhouse
     * @Description: TODO(终审面签)
     * @param bean
     * @param user
     * @param approveHouseWorkFlowVO
     * @param beanVo
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午2:24:51
     * @see com.zx.emanage.loanappro.service.IWmsCreCreditVisaApplService#saveforhouse(com.zx.emanage.util.gen.entity.WmsCreCreditFinalAppl, com.zx.sframe.util.vo.UserBean, com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO, com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO)
     * history:
     * 1、2017年2月21日 baisong 创建方法
    */
    @Override
    @Transactional
    public String saveforhouse(WmsCreCreditVisaAppl bean, UserBean user, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, WmsCreCreditHeadVO beanVo)
    {
        // 流程类
        approveHouseWorkFlowVO.setDebtkey("MSSP");// 贷款终审面签
        approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));// 保存userid
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
        wmscrecreditheaddao.updateLimit(beanVo.getInfoMapAppro());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        // 终审面签表
        if (bean.getWms_cre_credit_visa_appl_id() == null || "".equals(bean.getWms_cre_credit_visa_appl_id()))
        {
            bean.setCreate_timestamp(now);
            bean.setCreate_user_id(user.getUserId());
            bean.setCreate_user_name(user.getRealname());
            bean.setLast_update_timestamp(now);
            bean.setLast_update_user_id(user.getUserId());
            bean.setWms_cre_credit_visa_appl_id(null);
            bean.setEnable_flag("1");
            wmscrecreditvisaapplDao.save(bean);
        }
        else
        {
            bean.setLast_update_timestamp(now);
            bean.setLast_update_user_id(user.getUserId());
            wmscrecreditvisaapplDao.update(bean);
        }
        // 查询贷款单据信息
        WmsCreCreditHead wmsCreCreditHead = wmscrecreditheaddao.get(bean.getWms_cre_credit_head_id());
        if ("false".equals(approveHouseWorkFlowVO.getPass()))
        {// 如果单据审批不通过
            wmsCreCreditHead.setNullify_type(approveHouseWorkFlowVO.getDebtkey());// 终审审批环节"5"
            wmsCreCreditHead.setNullify_user_name(user.getRealname());
            wmsCreCreditHead.setNullify_user_id(user.getUserId());
            wmsCreCreditHead.setNullify_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsCreCreditHead.setNullify_reason(approveHouseWorkFlowVO.getAdvice());//
            int ret = wmscrecreditheaddao.update(wmsCreCreditHead);// 更新贷款主表
        }
        // 判断是否改变金额 如果改变金额发送短信息
        if (bean.getOld_appro_limit().compareTo(bean.getAppro_limit()) != 0 || "false".equals(approveHouseWorkFlowVO.getPass()))
        {
            WmsCreHousingApprovalInfo approvalInfo = new WmsCreHousingApprovalInfo();// 房贷——审批信息表
            approvalInfo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
            approvalInfo.setEnable_flag("1");
            approvalInfo.setApproval_task_code(approveHouseWorkFlowVO.getDebtkey());
            List<WmsCreHousingApprovalInfo> approvalInfoList = wmsCreHousingApprovalInfoDao.getListByEntity(approvalInfo);
            if (approvalInfoList != null && approvalInfoList.size() > 0)
            {
                approvalInfo.setWms_cre_housing_approval_info(approvalInfoList.get(approvalInfoList.size() - 1).getWms_cre_housing_approval_info());
                int index = approvalInfoList.get(approvalInfoList.size() - 1).getApproval_advice().lastIndexOf(":");
                if (index >= 0)
                {
                    StringBuffer sb = new StringBuffer();
                    String advice = approvalInfoList.get(approvalInfoList.size() - 1).getApproval_advice().substring(0, index);
                    sb.append(advice);
                    sb.append(":");
                    sb.append(bean.getAppro_limit() + "万元");
                    approvalInfo.setApproval_advice(sb.toString());
                    wmsCreHousingApprovalInfoDao.update(approvalInfo);
                }
            }
            //查询客户姓名
            Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
            customerChangeParamMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            customerChangeParamMap.put("is_major", "1");
            customerChangeParamMap.put("enable_flag", "1");
            List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
           //调用发送信息的接口出现异常 不会影响正常流程  
            try{
                if(customerChangeList != null && customerChangeList.size() == 1) {
                    //调用方法map
                    Map<String, Object> sendMap = new HashMap<String, Object>();
                    //参数map
                    Map<String, String> paramMap = new HashMap<String, String>();
                    //极光附加参数map
                    Map<String, String> msg_extras = new HashMap<String, String>();
                    paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                    paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                    paramMap.put("city", wmsCreCreditHead.getCity());
                    paramMap.put("advice", approveHouseWorkFlowVO.getAdvice());//意见
                    paramMap.put("appro_limit", bean.getAppro_limit().toString());// 金额
                    paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                    //激光推送
                    sendMap.put("quart_message","1");//消息附加参数
                    //如果是消息中心
                    sendMap.put("message_center","1");
             
                    msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());//添加参数
                    sendMap.put("msg_extras", msg_extras);//消息附加参数  
                    sendMap.put("is_oneself", "1");// 给业务员自己发送消息
                    sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                    // 面签拒件
                    if ("false".equals(approveHouseWorkFlowVO.getPass()))
                    {
                        sendMap.put("msg_code", "10013");// 消息编码
                        sendMap.put("msg_code_role", "10013");// 消息编码--流程角色
                    }
                    else
                    {
                        sendMap.put("msg_code", "10012");// 消息编码
                        sendMap.put("msg_code_role", "10012");// 消息编码--流程角色
                    }
                    paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
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
        }
        return "success";
    }
}
