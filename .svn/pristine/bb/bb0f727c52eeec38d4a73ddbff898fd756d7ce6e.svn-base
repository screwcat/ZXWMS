package com.zx.emanage.loanappro.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.persist.WmsCreApproBorrowProtocolDao;
import com.zx.emanage.loanappro.persist.WmsCreApproProtocolAttachDao;
import com.zx.emanage.loanappro.service.IWmsCreApproProtocolAttachService;
import com.zx.emanage.loanappro.vo.WmsCreApproProtocolAttachSearchBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.WmsCreApproProtocolAttach;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreApproProtocolAttachServiceImpl
 * 模块名称：终审模块
 * @Description: 内容摘要：处理终审模块内容
 * @author baisong
 * @date 2016年12月28日
 * @version V1.0
 * history:
 * 1、2016年12月28日 baisong 创建文件
 */
@Service("wmscreapproprotocolattachService")
public class WmsCreApproProtocolAttachServiceImpl implements IWmsCreApproProtocolAttachService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreApproProtocolAttachServiceImpl.class);

    @Autowired
    private WmsCreApproProtocolAttachDao wmscreapproprotocolattachDao;
	@Autowired
	private IWmsLoanWorkFlowService wmsLoanWorkFlowService;//新房贷贷款流程
    @Autowired
    private WmsCreCreditHeadDao wmsCreCreditHeadDao;
    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;//房产变更表
    @Autowired
    private PmPersonnelDao pmPersonnelDao;
    @Autowired
    private  IWmsCreCreditHeadService wmsCreCreditHeadService;
    @Autowired
    private IWorkflowService  workflowService;

    @Autowired
    private WmsCreApproBorrowProtocolDao wmsCreApproBorrowProtocolDao;// 合同

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreApproProtocolAttachSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscreapproprotocolattachDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreApproProtocolAttachSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscreapproprotocolattachDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscreapproprotocolattachDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreApproProtocolAttach getInfoByPK(Integer wms_cre_appro_protocol_attach_id)
    {
        return wmscreapproprotocolattachDao.get(wms_cre_appro_protocol_attach_id);
    }

    @Override
    @Transactional
    public String save(WmsCreApproProtocolAttach bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscreapproprotocolattachDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreApproProtocolAttach bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscreapproprotocolattachDao.update(bean); // update a record
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
     * WmsCreApproProtocolAttach() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreApproProtocolAttach> getListByEntity(WmsCreApproProtocolAttach queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreApproProtocolAttach> beanList = wmscreapproprotocolattachDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 
     * @Title: updateAttach
     * @Description: TODO(房产局合同保存)
     * @param bean
     * @param approveHouseWorkFlowVO
     * @param user
     * @return 
     * @author: baisong
     * @time:2017年1月10日 上午10:16:05
     * @see com.zx.emanage.loanappro.service.IWmsCreApproProtocolAttachService#updateAttach(com.zx.emanage.util.gen.entity.WmsCreApproProtocolAttach, com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年1月10日 baisong 创建方法
     */
	@Override
    @Transactional
	public String updateAttach(WmsCreApproProtocolAttach bean,WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO,UserBean user) {
		String resStr = "success";
		int ret = 0;
        ret = wmscreapproprotocolattachDao.updateAttach(bean); //
        WmsCreCreditHead wCreCreditHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
        // 数据来源1为pc 2为移动端 2016/5/10 baisong
        if ("2".equals(wCreCreditHead.getVersion_number()))
        {// 2016/5/10版本
            // 换成更好的方法获取流程id
            if (wCreCreditHead != null && "1".equals(wCreCreditHead.getIs_workflow()))
            {
                Task task = workflowService.getTaskInfobyKey(wCreCreditHead.getWms_cre_credit_head_id().toString(), WorkflowConstantHelp.UPHOUSINGLOANPROCESS_HTQD, WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
                if (task != null)
                {
                    approveHouseWorkFlowVO.setTaskId(task.getId());
                }
            }
            approveHouseWorkFlowVO.setUserId(user.getUserId().toString());
            approveHouseWorkFlowVO.setDebtkey("6");// 合同签订
            approveHouseWorkFlowVO.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id().toString());
            approveHouseWorkFlowVO.setAdvice("合同已签订");
            approveHouseWorkFlowVO.setBusinessId(bean.getWms_cre_credit_head_id().toString());
            approveHouseWorkFlowVO.setPass("true");
            approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
            wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
        }
	      /*  //新流程发送短信
            if("2".equals(wCreCreditHead.getVersion_number())) {
            	 //查询贷款单据信息
	            PmPersonnel pmPersonnel= pmPersonnelDao.get(wCreCreditHead.getSalesman_id());
                //查询客户姓名
                Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
                customerChangeParamMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
                customerChangeParamMap.put("is_major", "1");
                customerChangeParamMap.put("enable_flag", "1");
                List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
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
	                    sendMap.put("short_message", "1");
	                    msgMap.put("tpl_id", "1696");
	                    sendMap.put("role_value", WorkflowRoleHelp.HOUSE_GLBJL);//管理部经理
	                    paramMap.put("bill_code", wCreCreditHead.getBill_code());
	                    paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
	                    
	                    msgMap.put("paramJson", new Gson().toJson(paramMap));
	                    sendMap.put("msgMap", msgMap);
	                    //激光推送
	                    sendMap.put("quart_message","1");//消息附加参数
	                    //如果是消息中心
	    	            sendMap.put("message_center","1");
	    	          
	    	            msg_extras.put("wms_cre_credit_head_id", wCreCreditHead.getWms_cre_credit_head_id().toString());//添加参数
	    	            sendMap.put("msg_extras", msg_extras);//消息附加参数  
	    	            sendMap.put("msg_code", "20002");//消息编码
	    	            sendMap.put("msg_code_role", "20002");//消息编码--流程角色
	    	            
	    	            paramMap.put("wms_cre_credit_head_id", wCreCreditHead.getWms_cre_credit_head_id().toString());
	    	            paramMap.put("bill_status", wCreCreditHead.getBill_status());
	    	            paramMap.put("create_user_id", user.getUserId().toString());
	    	            paramMap.put("create_user_name", user.getRealname());
	    	            sendMap.put("msg_map", paramMap);//极光推送和消息中心的消息内容参数
	    	            
	                    this.wmsCreCreditHeadService.getUserAndSendInfo(sendMap);
	                }
                }catch (Exception e){
                	e.printStackTrace();        
                }
            }*/
	        if (ret == 0)
	        {
	            resStr = "error";
	        }
	        return resStr;
	}
    /**
     * 房贷合同结束流程 新版合同 ireport
     * @param approveHouseWorkFlowVO
     * @param user
     * @return
     * @author baisong 
     * @date 20160902
     */
	@Override
    @Transactional
	public String updateHTQDworkflow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO,UserBean user) {
		String resStr = "success";
        // 获取组合贷数据--如果是一条则正常走
        List<WmsCreCreditHead> list = wmsCreCreditHeadDao.getGroupHead(Integer.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));

        if(list!=null&&list.size()>0){
            // 组合贷循环
            for (WmsCreCreditHead wCreCreditHead : list)
            {
                // 数据来源1为pc 2为移动端 2016/5/10 baisong
                // 去流程中获取当前节点当前数据当前审批人的流程节点taskid
                Task task = null;
                // 判断版本和是否存在流程
                if ("1".equals(wCreCreditHead.getVersion_number()))
                {
                    approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
                    if ("1".equals(wCreCreditHead.getIs_workflow()))
                    {
                        task = workflowService.getTaskInfobyKey(wCreCreditHead.getWms_cre_credit_head_id().toString(), WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_QDHT, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
                    }
                }
                // 判断是否存在流程
                else
                {
                    approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
                    if ("1".equals(wCreCreditHead.getIs_workflow()))
                    {
                        task = workflowService.getTaskInfobyKey(wCreCreditHead.getWms_cre_credit_head_id().toString(), WorkflowConstantHelp.UPHOUSINGLOANPROCESS_HTQD, WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
                    }
                }
                // 判断是否为空
                if (task != null)
                {
                    approveHouseWorkFlowVO.setTaskId(task.getId());
                }
                approveHouseWorkFlowVO.setUserId(user.getUserId().toString());
                approveHouseWorkFlowVO.setDebtkey("6");// 合同签订
                approveHouseWorkFlowVO.setAdvice("合同已签订");
                approveHouseWorkFlowVO.setWms_cre_credit_head_id(wCreCreditHead.getWms_cre_credit_head_id().toString());
                approveHouseWorkFlowVO.setBusinessId(wCreCreditHead.getWms_cre_credit_head_id().toString());
                approveHouseWorkFlowVO.setPass("true");
                approveHouseWorkFlowVO.setIs_workflow(wCreCreditHead.getIs_workflow());
                wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
            }
        }
        return resStr;
	}
}
