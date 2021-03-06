package com.zx.emanage.cremanage.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingApprovalInfoDao;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.persist.WmsCreCreditServiceTypeDao;
import com.zx.emanage.loanreview.persist.WmsCreHousingTrialAssessmentDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.WmsSysDictDataDao;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditServiceType;
import com.zx.emanage.util.gen.entity.WmsCreHousingApprovalInfo;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.sframe.util.SysUtil;

/**
 * 
 * @ClassName: WmsLoanWorkFlowServiceImpl
 * @Description: 内容摘要：赎回流程接口具体功能实现
 * @author hancd
 * @date 2016年11月21日
 * @version V1.0
 * history:
 * 1、2016年11月25日 hancd 创建文件
 */

@Service("wmsLoanWorkFlowService")
public class WmsLoanWorkFlowServiceImpl implements IWmsLoanWorkFlowService
{
    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private PmPersonnelDao pmpersonnelDao;
    
    @Autowired
    private WmsCreCreditHeadDao wmsCreCreditHeadDao;//信用贷款主表
    
    @Autowired
    private WmsCreHousingApprovalInfoDao wmsCreHousingApprovalInfoDao;//房贷——审批信息表
    @Autowired
 	private WmsSysDictDataDao wmssysdictdataDao;//字典表
    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;
    @Autowired
    private WmsCreCreditServiceTypeDao wmsCreCreditServiceTypeDao;// 贷款和还款中间表
    @Autowired
    private WmsCreHousingTrialAssessmentDao wmscrehousingtrialassessmentdao;// 初评表

    private static Logger log = LoggerFactory.getLogger(WmsLoanWorkFlowServiceImpl.class);

    /**
     * 覆盖:启动新房贷流程2016-2-23
     * 实现:流程启动公共方法  不同的PERFECTHOUSINGLOANPROCESS和 UPHOUSINGLOANPROCESS俩个版本流程都使用一个启动方法
     * @param userId 用户ID
     * @param businessId 业务单据ID
     * @param salesman_dept_id 上单业务员部门ID
     * @param salesman_id 业务员ID
     * @param processDefinitionKey 流程实例key
     * @author baisong
     */
    @Override
    @Transactional
    public String startFinancialWorkFlow(String userId, String businessId, String salesman_dept_id,String salesman_id,String processDefinitionKey)
    {
    	String result="success";
    	//房贷新流程2016-2-23// 房贷流程2016-5-10俩个版本
        if(processDefinitionKey.equals(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS)||processDefinitionKey.equals(WorkflowConstantHelp.UPHOUSINGLOANPROCESS)){
    		 //启动流程
    		 Map<String,Object> fromMap = new HashMap<>();
    		 fromMap.put("inputkey", "1");
    		 //改变业务单据状态:待复核
    		 WmsCreCreditHead wmsCreCreditHead = new WmsCreCreditHead();//信用贷款主表
    		 //如果流程是UPHOUSINGLOANPROCESS 则走不同的流程
    		 if(processDefinitionKey.equals(WorkflowConstantHelp.UPHOUSINGLOANPROCESS)){
    			wmsCreCreditHead.setBill_status("L");//待初评预估
    			WmsCreHousingApprovalInfo approvalInfo=new WmsCreHousingApprovalInfo();//房贷——审批信息表
    			approvalInfo.setApproval_task_type("房贷申请");//审批节点 根据流程中的是一样的 。请查看流程
    			approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
    			//审批表
				approvalInfo.setWms_cre_credit_head_id(Integer.valueOf(businessId));//贷款主表id
				approvalInfo.setApproval_advice("申请提交成功");//审批意见
				approvalInfo.setApproval_result("true");//审批结果  流程中传递pass的值
				approvalInfo.setApproval_user_id(Integer.valueOf(userId));//审批人id
				approvalInfo.setApproval_time(new Timestamp(System.currentTimeMillis()));//审批时间
				approvalInfo.setEnable_flag("1");
				wmsCreHousingApprovalInfoDao.saveInfo(approvalInfo);
    		 }else{
        		 wmsCreCreditHead.setBill_status("B");//待复核 
    		 }
    		 workflowService.startWorkflow(userId, processDefinitionKey, businessId, fromMap);
    		
    		
    		 wmsCreCreditHead.setWms_cre_credit_head_id(Integer.valueOf(businessId));
    		 wmsCreCreditHeadDao.updateforhouse(wmsCreCreditHead);
    	}
    	return result;
    }    
    /**
     * 复议申请启动新房贷流程2016-9-26
     * 实现:流程启动公共方法  不同的PERFECTHOUSINGLOANPROCESS和 UPHOUSINGLOANPROCESS俩个版本流程都使用一个复议申请方法
     * @param userId 用户ID
     * @param businessId 业务单据ID
     * @param salesman_dept_id 上单业务员部门ID
     * @param salesman_id 业务员ID
     * @param processDefinitionKey 流程实例key
     * @author baisong
     */
    @Override
    @Transactional
    public String againStartFinancialWorkFlow(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO)
    {
    	//基本参数
    	String businessId=wDebtWorkFlowVO.getBusinessId();
    	String userId=wDebtWorkFlowVO.getUserId();
    	String advice=wDebtWorkFlowVO.getAdvice();
    	String result="success";
    	String processDefinitionKey=null;
    	//房贷新流程2016-2-23// 房贷流程2016-5-10俩个版本
        if(businessId!=null){
        	WmsCreCreditHead wCreCreditHead = wmsCreCreditHeadDao.get(Integer.valueOf(businessId));
        	if(wCreCreditHead!=null){
        		if("2".equals(wCreCreditHead.getVersion_number())){
        			processDefinitionKey=WorkflowConstantHelp.UPHOUSINGLOANPROCESS;
        		}else{
        			processDefinitionKey=WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS;
        		}
        	}
    		 //启动流程
    		 Map<String,Object> fromMap = new HashMap<>();
    		 fromMap.put("inputkey", "2");
    		 fromMap.put("advice", advice);
    		 //改变业务单据状态:待复核
    		 WmsCreCreditHead wmsCreCreditHead = new WmsCreCreditHead();//信用贷款主表
    		 //如果流程是UPHOUSINGLOANPROCESS 则走不同的流程
			 wmsCreCreditHead.setBill_status("D");//待终审审批
			 
			 WmsCreHousingApprovalInfo approvalInfo=new WmsCreHousingApprovalInfo();//房贷——审批信息表
			 approvalInfo.setApproval_task_type("房贷复议申请");//审批节点 根据流程中的是一样的 。请查看流程
			 approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
			 //审批表
			 approvalInfo.setWms_cre_credit_head_id(Integer.valueOf(businessId));//贷款主表id
			 approvalInfo.setApproval_advice(advice);//审批意见
			 approvalInfo.setApproval_result("true");//审批结果  流程中传递pass的值
			 approvalInfo.setApproval_user_id(Integer.valueOf(userId));//审批人id
			 approvalInfo.setApproval_time(new Timestamp(System.currentTimeMillis()));//审批时间
			 approvalInfo.setEnable_flag("1");
			 wmsCreHousingApprovalInfoDao.saveInfo(approvalInfo);
			 
    		 workflowService.startWorkflow(userId, processDefinitionKey, businessId, fromMap);
            wmsCreCreditHead.setNullify_type("-1");// 作废标示为0则标示复议的单据
    		 wmsCreCreditHead.setWms_cre_credit_head_id(Integer.valueOf(businessId));
    		 wmsCreCreditHeadDao.updateforhouse(wmsCreCreditHead);
    	}
    	return result;
    }
    /**
     * 覆盖:赎回流程，债权调整流程,理财上单流程
     * 实现公共流程获取IdList,taskList方法
     * @param userId 用户ID
     * @param processDefinitionKey 流程实例Key
     * @param invekey 任务节点key
     * 当invekey=1 代表贷款复核
     * 当invekey=2 代表重新申请
     * 当invekey=3 代表初评预估
     * 当invekey=4 代表办件验房
     * 当invekey=5 代表初评补件/办件补件
     * 当invekey=6 代表待贷款终审
     * 当invekey=7 代表待签订合同
     * 当invekey=8 代表待公正
     * 当invekey=9 代表待他项
     * 当invekey=10 代表待放款申请
     * 当invekey=11 代表待放款申请审批
     * 当invekey=12 代表待放款确认
     *
     * 当invekey=14 代表待放款申请 --新版本手机端使用
     * 当invekey=15 代表 授信确认 --手机端使用
     * 当invekey=16 代表房产核查缴费 --手机端pc端都使用
     * 当invekey=17 电审审批 --手机端使用
     * 当invekey=18 征信审批 --手机端使用
     * @return Map
     * @author baisong
     */
    @Override
    public Map<String, Object> getWorkFlowToIdList(String processDefinitionKey,String userId,String invekey)
    {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        if(processDefinitionKey.equals(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS)){//房贷新流程
        	List<WorkflowInfoHelp> wHelps = new ArrayList<>();
        	if ("1".equals(invekey)) {//贷款复核
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_DKFH);
				//说明不存在
				if (wHelps == null || wHelps.size()==0) {
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("2".equals(invekey)){//重新申请
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_CXSQ);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("3".equals(invekey)){//初评预估
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_CPYG);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("4".equals(invekey)){//办件验房
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_BJSP);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}	
			}else if("5".equals(invekey)){//初评补件/办件补件
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_CPBJ);
				List<WorkflowInfoHelp> khHelps =setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_BJBJ);
				List<WorkflowInfoHelp> khHelpzs =setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_ZSBJ);
				wHelps.addAll(khHelps);
				wHelps.addAll(khHelpzs);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("6".equals(invekey)){//贷款终审
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_DKZS);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("7".equals(invekey)){//签订合同
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_QDHT);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("8".equals(invekey)){//公正
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_GZ);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("9".equals(invekey)){//他项
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TX);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("10".equals(invekey)){//放款申请
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_FKSQ);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("11".equals(invekey)){//放款申请审批
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_FKSQSP);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("12".equals(invekey)){//放款确认
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_FKQR);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("14".equals(invekey)){//手机端待办事项
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_BJSP);//办件
				List<WorkflowInfoHelp> fKSQHelps =setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQ);//放款申请
				List<WorkflowInfoHelp> fKSQSPHelps =setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQSP);//放款申请审批
				List<WorkflowInfoHelp> sxqrHelps =setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_SXQR);//授信确认
				wHelps.addAll(fKSQHelps);
				wHelps.addAll(fKSQSPHelps);
				wHelps.addAll(sxqrHelps);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("16".equals(invekey)){//房产核查缴费2016-9-26 新增流程节点
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_FCHCJF);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else{
				Map<String,Object> pMap = new HashMap<>();
				return pMap;
			}
        	parmMap = setWorkFlowMap(wHelps, processDefinitionKey,invekey);
        }else if(processDefinitionKey.equals(WorkflowConstantHelp.UPHOUSINGLOANPROCESS)){//房贷新流程2016/5/10版本
        	List<WorkflowInfoHelp> wHelps = new ArrayList<>();
        	if("3".equals(invekey)){//初评预估
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_CPYG);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("4".equals(invekey)){//办件验房
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_BJSP);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}	
			}else if("6".equals(invekey)){//贷款终审
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_DKZS);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("7".equals(invekey)){//签订合同
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_HTQD);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("10".equals(invekey)){//放款申请
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQ);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("11".equals(invekey)){//放款申请审批
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQSP);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("12".equals(invekey)){//放款确认
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKQR);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("13".equals(invekey)){//放款申请列表手机端
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKQR);
				List<WorkflowInfoHelp> khHelps =setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_HTQD);
				wHelps.addAll(khHelps);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("14".equals(invekey)){//手机端待办事项
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_BJSP);
				List<WorkflowInfoHelp> htqdHelps =setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_HTQD);//合同签订
				List<WorkflowInfoHelp> fKSQHelps =setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQ);
				List<WorkflowInfoHelp> fKSQSPHelps =setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQSP);
				wHelps.addAll(htqdHelps);
				wHelps.addAll(fKSQHelps);
				wHelps.addAll(fKSQSPHelps);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("15".equals(invekey)){//手机端授信确认
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_SXQR);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("16".equals(invekey)){//房产核查缴费
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FCHCJF);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("17".equals(invekey)){//电审审批
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_DSSP);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}	
			}else if("18".equals(invekey)){//征信审批
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_ZXSP);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}	
			}
        	parmMap = setWorkFlowMap(wHelps, processDefinitionKey,invekey);
        }
        return parmMap;
    }
    /**
     * 覆盖:赎回流程，债权调整流程,理财上单流程
     * 实现公共流程获取IdList,taskList方法
     * @param userId 用户ID
     * @param processDefinitionKey 流程实例Key
     * @param invekey 任务节点key
     * 当invekey=5 代表初评补件/办件补件
     * @return Map
     * @author baisong
     */
    @Override
    public Map<String, Object> getWorkFlowToIdListNoUser(String processDefinitionKey,String userId,String invekey)
    {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        if(processDefinitionKey.equals(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS)){//房贷新流程
        	List<WorkflowInfoHelp> wHelps = new ArrayList<>();
			 if("5".equals(invekey)){//初评补件/办件补件
				wHelps = setWorkFlowListNoUser(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_CPBJ);
				List<WorkflowInfoHelp> khHelps =setWorkFlowListNoUser(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_BJBJ);
				List<WorkflowInfoHelp> khHelpzs =setWorkFlowListNoUser(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_ZSBJ);
				wHelps.addAll(khHelps);
				wHelps.addAll(khHelpzs);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}
        	parmMap = setWorkFlowMap(wHelps, processDefinitionKey,invekey);
        }
        return parmMap;
    }
    /**
     * 覆盖:赎回流程，债权调整流程,理财上单流程
     * 实现公共流程获取IdList,taskList方法
     * @param userId 用户ID
     * @param processDefinitionKey 流程实例Key
     * @param invekey 任务节点key
     * 当invekey=5 代表初评补件/办件补件
     * @return Map
     * @author baisong
     */
    @Override
    public Map<String, Object> getWorkFlowToIdListNoUserVerTwo(String processDefinitionKey,String userId,String invekey,String ver)
    {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        if(processDefinitionKey.equals(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS)||processDefinitionKey.equals(WorkflowConstantHelp.UPHOUSINGLOANPROCESS)){//房贷新流程
        	List<WorkflowInfoHelp> wHelps = new ArrayList<>();
			 if("0".equals(invekey)){//初评补件/办件补件/初审评估/办件/放款审核
				wHelps = setWorkFlowListNoUser(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_CPBJ);
				List<WorkflowInfoHelp> khHelps = setWorkFlowListNoUser(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_BJBJ);
				List<WorkflowInfoHelp> khHelpzs = setWorkFlowListNoUser(processDefinitionKey, userId, WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_ZSBJ);
				//新流程
				//List<WorkflowInfoHelp> CPYG_wHelps = setWorkFlowListNoUser(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_CPYG);
				List<WorkflowInfoHelp> BJSP_wHelps = setWorkFlowListNoUser(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_BJSP);
				//List<WorkflowInfoHelp> DKZS_wHelps = setWorkFlowListNoUser(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_DKZS);
				List<WorkflowInfoHelp> FKSQSP_wHelps =setWorkFlowListNoUser(processDefinitionKey, userId, WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQSP);
				wHelps.addAll(khHelps);
				wHelps.addAll(khHelpzs);
				//wHelps.addAll(CPYG_wHelps);
				wHelps.addAll(BJSP_wHelps);
				//wHelps.addAll(DKZS_wHelps);
				wHelps.addAll(FKSQSP_wHelps);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}
        	parmMap = setWorkFlowMap(wHelps, processDefinitionKey,invekey);
        }
        return parmMap;
    }
	   /**
	    * @deprecated 房贷新旧流程 获取taskid 和idlist合并 
	    * @param parmMap 旧流程参数
	    * @param userId
	    * @param invekey //流程
	    * @return map
	    * 	baisong
	    */
    @Override
    public Map<String, Object> setTaskList(Map<String, Object> parmMap,Integer userId,String invekey,String processDefinitionKey){
	       if(processDefinitionKey.equals(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS)){//如果是当前流程这需要传递一个时间参数
	    		parmMap.put("hprocess_time", WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TIME);//区分新旧流程时间
	       }
    	   Map<String, Object> pmap = getWorkFlowToIdList(processDefinitionKey, String.valueOf(userId),invekey);
	       if(pmap==null||pmap.get("idList")==null){
	        	return parmMap;  
	       }
	       if(parmMap==null||parmMap.get("idList")==null){
	    	   parmMap=new HashMap<>();  
	    	   parmMap.put("idList",(List<String>)pmap.get("idList"));
	    	   parmMap.put("taskIdList", (List<String>)pmap.get("taskIdList"));
	    	   return parmMap;
	       }
    	   List<String> idList=  (List<String>)parmMap.get("idList");//单据id
    	   List<String> taskIdList=  (List<String>)parmMap.get("taskIdList");//流程taskid
    	   idList.addAll((List<String>)pmap.get("idList"));
    	   taskIdList.addAll((List<String>)pmap.get("taskIdList"));
    	   parmMap.put("idList", idList);
    	   parmMap.put("taskIdList", taskIdList);
    	   return parmMap;
    }
    /**
	    * @deprecated 房贷新旧流程 获取taskid 和idlist合并 房贷补件使用
	    * @param parmMap 旧流程参数
	    * @param userId
	    * @param invekey //流程
	    * @return map
	    * 	baisong
	    */
	 @Override
	 public Map<String, Object> setTaskListBJ(Map<String, Object> parmMap,Integer userId,String invekey ){
	 	   parmMap.put("hprocess_time", WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TIME);//区分新旧流程时间
	 	   Map<String, Object> pmap =new HashMap<>();
	 	   if(userId==null){//如果是补件
	 		  pmap = getWorkFlowToIdListNoUser(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS, null, invekey);
	 	   }else{
	 		  pmap = getWorkFlowToIdList(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS, String.valueOf(userId),invekey); 
	 	   }
	 	  
		       if(pmap==null||pmap.get("idList")==null){
		        	return parmMap;  
		       }
		       if(parmMap==null||parmMap.get("idList")==null){
		    	   parmMap=new HashMap<>();  
		    	   parmMap.put("idList",(List<String>)pmap.get("idList"));
		    	   parmMap.put("taskIdList", (List<String>)pmap.get("taskIdList"));
		    	   parmMap.put("approvesGroups", (List<String>)pmap.get("approvesGroups"));
		    	   parmMap.put("approveAdvices", (List<String>)pmap.get("approveAdvices"));
		    	   parmMap.put("approveTimes", (List<String>)pmap.get("approveTimes"));
		    	  //parmMap.put("processDefinitionKey", (List<String>)pmap.get("processDefinitionKey"));
		    	   return parmMap;
		       }
	 	   List<String> idList=  (List<String>)parmMap.get("idList");//单据id
	 	   List<String> taskIdList=  (List<String>)parmMap.get("taskIdList");//流程taskid
	 	   List<String> approvesGroups=  (List<String>)parmMap.get("approvesGroups");//单据approvesGroups
	 	   List<String> approveAdvices=  (List<String>)parmMap.get("approveAdvices");//流程approveAdvices
	 	   List<String> approveTimes=  (List<String>)parmMap.get("approveTimes");//单据idapproveTimes
	 	   idList.addAll((List<String>)pmap.get("idList"));
	 	   taskIdList.addAll((List<String>)pmap.get("taskIdList"));
	 	   approvesGroups.addAll((List<String>)pmap.get("approvesGroups"));
	 	   approveAdvices.addAll((List<String>)pmap.get("approveAdvices"));
	 	   approveTimes.addAll((List<String>)pmap.get("approveTimes"));
	 	   parmMap.put("idList", idList);
	 	   parmMap.put("taskIdList", taskIdList);
	 	   parmMap.put("approvesGroups", approvesGroups);
		   parmMap.put("approveAdvices", approveAdvices);
		   parmMap.put("approveTimes", approveTimes);
	 	   return parmMap;
	 }
	 /**
	    * @deprecated 房贷新流程 获取taskid 和idlist合并 
	    * @param parmMap 旧流程参数
	    * @param userId
	    * @param invekey //流程
	    * @return map
	    * 	baisong
	    */
	 @Override
	 public Map<String, Object> setTaskListVerTwo(Map<String, Object> parmMap,Integer userId,String invekey ){
	 	   parmMap.put("hprocess_time", WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TIME);//区分新旧流程时间
	 	   Map<String, Object> pmap =new HashMap<>();
	 	   if(userId==null){//如果是补件
	 		  pmap = getWorkFlowToIdListNoUserVerTwo(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS, null, invekey,"0");
	 	   }else{
	 		  pmap = getWorkFlowToIdList(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS, String.valueOf(userId),invekey); 
	 	   }
	 	  
		       if(pmap==null||pmap.get("idList")==null){
		        	return parmMap;  
		       }
		       if(parmMap==null||parmMap.get("idList")==null){
		    	   parmMap=new HashMap<>();  
		    	   parmMap.put("idList",(List<String>)pmap.get("idList"));
		    	   parmMap.put("taskIdList", (List<String>)pmap.get("taskIdList"));
		    	   parmMap.put("approvesGroups", (List<String>)pmap.get("approvesGroups"));
		    	   parmMap.put("approveAdvices", (List<String>)pmap.get("approveAdvices"));
		    	   parmMap.put("approveTimes", (List<String>)pmap.get("approveTimes"));
		    	  //parmMap.put("processDefinitionKey", (List<String>)pmap.get("processDefinitionKey"));
		    	   return parmMap;
		       }
	 	   List<String> idList=  (List<String>)parmMap.get("idList");//单据id
	 	   List<String> taskIdList=  (List<String>)parmMap.get("taskIdList");//流程taskid
	 	   List<String> approvesGroups=  (List<String>)parmMap.get("approvesGroups");//单据approvesGroups
	 	   List<String> approveAdvices=  (List<String>)parmMap.get("approveAdvices");//流程approveAdvices
	 	   List<String> approveTimes=  (List<String>)parmMap.get("approveTimes");//单据idapproveTimes
	 	   idList.addAll((List<String>)pmap.get("idList"));
	 	   taskIdList.addAll((List<String>)pmap.get("taskIdList"));
	 	   approvesGroups.addAll((List<String>)pmap.get("approvesGroups"));
	 	   approveAdvices.addAll((List<String>)pmap.get("approveAdvices"));
	 	   approveTimes.addAll((List<String>)pmap.get("approveTimes"));
	 	   parmMap.put("idList", idList);
	 	   parmMap.put("taskIdList", taskIdList);
	 	   parmMap.put("approvesGroups", approvesGroups);
		   parmMap.put("approveAdvices", approveAdvices);
		   parmMap.put("approveTimes", approveTimes);
	 	   return parmMap;
	 }
    /**
	 * 覆盖:赎回流程，债权调整流程，理财上单流程
	 * 将所有查询到结果集添加到页面显示列表 增添任务taskId属性
	 * @param list 查询业务数据集合
	 * @param idLists 查询流程业务主键集合
	 * @param taskIdLists 查询流程业务主键对应的流程任务集合
	 * @param inveHead 代表主键名称
	 */
	@Override
	public List<Map<String, Object>> addTaskIdToList(List<Map<String, Object>> list, List<Integer> idLists,List<String> taskIdLists,String processDefinitionKey) {
		 if (idLists != null)
	        {
	            for (Map<String, Object> map : list)
	            {
	            	Integer key =null;
	            	if(processDefinitionKey.equals(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS)){//新房贷流程
	            		 key = (Integer) map.get("wms_cre_credit_head_id");
	            	}
	                for (int i = 0; i < idLists.size(); i++)
	                {
						if (idLists.get(i).compareTo(key) == 0)
	                    {
	                        map.put("taskId", taskIdLists.get(i));
	                        break;
	                    }
	                }
	            }
	        }
	        else
	        {
	            list = null;
	        }
	   return list;
	}
    
    /**
     * 覆盖:赎回流程，债权调整流程,理财上单流程 历程查看功能
     * @param processDefinitionKey 流程实例key
     * @param businessKey 每个单据主表ID
     * @author hancd
     */
    @Override
    public Map<String, Object> inveWorkFlowProcess(String processDefinitionKey,String businessKey)
    {
        // 流程历程查看
        Map<String, Object> paMap = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 设置流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(processDefinitionKey);
        workflowSearchInfoHelp.setFinish(true);// 查询完成
        workflowSearchInfoHelp.setBusinessKey(businessKey);//流程中单据主键
        // 读取流程返回的结果
        List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(workflowSearchInfoHelp);
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            // 根据流程里面的提供审批人id :approverids
            String approverids = workflowHistoryInfoHelp.getApproverids();
            boolean isNum = approverids.matches("[0-9]+");
            if (isNum)
            {
                PmPersonnel person = pmpersonnelDao.get(Integer.valueOf(workflowHistoryInfoHelp.getApproverids()));
                if (person != null)
                {
                    // 相应的id，获取相对应的部门名称
                    workflowHistoryInfoHelp.setPersonnel_deptName(person.getPersonnel_deptname());
                    // 相应的id，获取相对应的职务名称
                    workflowHistoryInfoHelp.setPersonnel_postName(person.getPersonnel_postname());
                }else{
                	// 相应的id，获取相对应的部门名称
                    workflowHistoryInfoHelp.setPersonnel_deptName("");
                    // 相应的id，获取相对应的职务名称
                    workflowHistoryInfoHelp.setPersonnel_postName("");
                }
            }else{
            	  // 相应的id，获取相对应的部门名称
                workflowHistoryInfoHelp.setPersonnel_deptName("");
                // 相应的id，获取相对应的职务名称
                workflowHistoryInfoHelp.setPersonnel_postName("");
            }
        }

        List<WorkflowHistoryInfoHelp> works = new ArrayList<WorkflowHistoryInfoHelp>();
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            if ("true".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                if ("未审批".equals(workflowHistoryInfoHelp.getApproveAdvice()))
                {
                    // 当出现审批结果为true,但审批意见为'未审批'，说明属于特批，其他审批环节系统自动完成,不需要显示。
                    continue;
                }
                else
                {
                    // 当出现审批结果为true,审批意见为其他内容，说明属于正常审批通过。
                    workflowHistoryInfoHelp.setApproveResult("审批通过");
                }
            }
            else if ("noagree".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                // 当出现noagree的值时，都属于系统自动完成不需要显示
                continue;
            }
            else if ("false".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                // 当出现false的值时,说明审批结果为不通过
                workflowHistoryInfoHelp.setApproveResult("审批不通过");
            }
            else if ("over".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                continue;
            }
            else if("nullify".equals(workflowHistoryInfoHelp.getApproveResult())){
            	// 当出现false的值时,说明审批结果为不通过
                workflowHistoryInfoHelp.setApproveResult("已终止");
            }else if("undo1".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("[客户撤销>[协议签订]]");
            }else if("undo2".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("[客户撤销>[客户确认]]");
            }else if("compe".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("已完成");
            }else  if("supply".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("退回");
            }else  if("overdue".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("过期");
            }else  if("cross".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("跨过签合同节点");
            }else  if("overtime".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("过期");
            }else  if("credit".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("授信流转");
            }else  if("makezs".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("补件-终审");
            }else  if("makepg".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("补件-评估");
            }else if(workflowHistoryInfoHelp.getApproveResult()==null){
            	workflowHistoryInfoHelp.setApproveResult("");
            }
         // 把所有true,false,noagree,nullify,over进行中文翻译，添加到works中
            works.add(workflowHistoryInfoHelp);
        }
        paMap.put("Rows", works);
        return paMap;
    }

	/**
	 * 涵盖:新房贷流程
	 * 实现所有流程所有节点的审批操作同时改变所有操作后的单据状态变化
     * @param debtkey 任务节点key
     *      当debtkey=1 代表复核
	 *      当debtkey=2 代表重新申请
	 * 		当debtkey=3 代表初评预估
	 * 		当debtkey=4 代表办件验房
     * 		当debtkey=5 代表贷款终审
     * 		当debtkey=6 代表放款准备合同
     * 		当debtkey=7 代表放款准备公证
     * 		当debtkey=8 代表放款准备他项
     * 		当debtkey=9 代表待放款审核--放款申请
     * 		当debtkey=10 代表放款审核
     * 		当debtkey=11 代表放款
     * 		当debtkey=12 代表补件
     * 		当debtkey=13 房产核查缴费
     * 		当debtkey=14 授信确认
     * 		当debtkey=15 电审审批
     * 		当debtkey=16 征信审批
     * 		当debtkey=17 房产信息补录
     * @return 
	 * @author baisong
	 */
	@Override
    @Transactional
	public void publicApproval(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO) {
		Map<String,String> formPropertiesMap = new HashMap<String, String>();
		formPropertiesMap.put("pass",wDebtWorkFlowVO.getPass());
		formPropertiesMap.put("advice", wDebtWorkFlowVO.getAdvice());
		String version_="";//流程版本号
		if("3".equals(wDebtWorkFlowVO.getDebtkey())){//初审评估 PERFECTHOUSINGLOANPROCESS有俩个版本 第二个版本有房产核查缴费 为了区分改变单据状态
			version_=getVersion_(wDebtWorkFlowVO.getTaskId());
		}
        WmsCreHousingApprovalInfo approvalInfo = new WmsCreHousingApprovalInfo();// 房贷——审批信息表
		//房贷：A 草稿  B待复核  C待贷前审核  D待终审  E待放款准备  F待放款审核  G待放款  H已完成  Z终止  I 复核退回  J 补件中 K待审批 L待初评预估 M合同完善完成 N授信确认 O 房产核查缴费 P初评退件 Q 终审退件
		WmsCreCreditHead wmsCreCreditHead = new WmsCreCreditHead();//贷款主表

        // 贷款表主键就是BusinessId
        if (wDebtWorkFlowVO.getWms_cre_credit_head_id() != null && !"".equals(wDebtWorkFlowVO.getWms_cre_credit_head_id()))
        {
            wDebtWorkFlowVO.setBusinessId(wDebtWorkFlowVO.getWms_cre_credit_head_id());
        }
        wmsCreCreditHead.setWms_cre_credit_head_id(Integer.valueOf(wDebtWorkFlowVO.getBusinessId()));
        // 兼容组合贷数据 如果数据流程标示为空则查询数据库
        if (wDebtWorkFlowVO.getIs_workflow() == null)
        {
            WmsCreCreditHead wHead = wmsCreCreditHeadDao.get(wmsCreCreditHead.getWms_cre_credit_head_id());
            wDebtWorkFlowVO.setIs_workflow(wHead.getIs_workflow());
        }
        // 房贷pc流程
        if (wDebtWorkFlowVO.getProcessDefinitionKey().equals(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS))
        {
//			//贷款表主键就是BusinessId
//			if(wDebtWorkFlowVO.getWms_cre_credit_head_id()!=null&&!"".equals(wDebtWorkFlowVO.getWms_cre_credit_head_id())){
//				wDebtWorkFlowVO.setBusinessId(wDebtWorkFlowVO.getWms_cre_credit_head_id());	
//			}
//			wmsCreCreditHead.setWms_cre_credit_head_id(Integer.valueOf(wDebtWorkFlowVO.getBusinessId()));
//            // 兼容组合贷数据 如果数据流程标示为空则查询数据库
//            if (wDebtWorkFlowVO.getIs_workflow() == null)
//            {
//                WmsCreCreditHead wHead = wmsCreCreditHeadDao.get(wmsCreCreditHead.getWms_cre_credit_head_id());
//                wDebtWorkFlowVO.setIs_workflow(wHead.getIs_workflow());
//            }
			if("1".equals(wDebtWorkFlowVO.getDebtkey())){// 复核
			     approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_DKFH);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){//通过
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("L");//待初评预估
				}else if("false".equals(wDebtWorkFlowVO.getPass())){//不通过
				    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("I");//复合退回
				}
			}else if("2".equals(wDebtWorkFlowVO.getDebtkey())){//重新申请
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_CXSQ);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("B");
					formPropertiesMap.put("advice", "重新申请已提交");
				}
			}else if("3".equals(wDebtWorkFlowVO.getDebtkey())){//初评预估
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_CPYG);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					if(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_VERSION.equals(version_)){//区分流程版本号 查看地址http://192.168.1.107:9080/WMS/workflow/process-list
						wmsCreCreditHead.setBill_status("C");//待办件验房--待贷前审核--- 
					}else{
						wmsCreCreditHead.setBill_status("O");// 2016-9-26 日流程改变新添加  O 房产核查缴费	
					}
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//已终止
				}else if("supply".equals(wDebtWorkFlowVO.getPass())){//初评补件
				    approvalInfo.setApproval_type("2");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("J");//补件中
				}
			}else if("4".equals(wDebtWorkFlowVO.getDebtkey())){//办件验房
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_BJSP);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("D");//待终审
				}else if("supply".equals(wDebtWorkFlowVO.getPass())){//办件补件
				    approvalInfo.setApproval_type("2");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("J");//补件中
                }
                // 过期作废
                else if ("overdue".equals(wDebtWorkFlowVO.getPass()))
                {
                    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
                    wmsCreCreditHead.setBill_status("Z");// 过期作废
                }
			}else if("5".equals(wDebtWorkFlowVO.getDebtkey())){//贷款终审
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_DKZS);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("E");//待放款准备--合同-公正-他项
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}else if("supply".equals(wDebtWorkFlowVO.getPass())){//终审补件
				    approvalInfo.setApproval_type("2");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("J");//补件中
				}
			}else if("6".equals(wDebtWorkFlowVO.getDebtkey())){//放款准备--合同
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_QDHT);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
                    // 判断是否存在流程如果没有流程则需要另外处理 是否存在流程：1存在 0不存在
                    if ("0".equals(wDebtWorkFlowVO.getIs_workflow()))
                    {
                        theContractOrNotarizationOrOtherNoWorkFlow(wDebtWorkFlowVO);
                    }
                    else if ("1".equals(wDebtWorkFlowVO.getIs_workflow()))
                    {
                        theContractOrNotarizationOrOther(wDebtWorkFlowVO);
                    }
					formPropertiesMap.put("advice", "签订合同审批通过");
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("7".equals(wDebtWorkFlowVO.getDebtkey())){//放款准备--公证
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_GZ);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
                    // 判断是否存在流程如果没有流程则需要另外处理 是否存在流程：1存在 0不存在
                    if ("0".equals(wDebtWorkFlowVO.getIs_workflow()))
                    {
                        theContractOrNotarizationOrOtherNoWorkFlow(wDebtWorkFlowVO);
                    }
                    else if ("1".equals(wDebtWorkFlowVO.getIs_workflow()))
                    {
                        theContractOrNotarizationOrOther(wDebtWorkFlowVO);
                    }
					formPropertiesMap.put("advice", "公证审批通过");
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("8".equals(wDebtWorkFlowVO.getDebtkey())){//放款准备--他项
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TX);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
                    // 判断是否存在流程如果没有流程则需要另外处理 是否存在流程：1存在 0不存在
                    if ("0".equals(wDebtWorkFlowVO.getIs_workflow()))
                    {
                        theContractOrNotarizationOrOtherNoWorkFlow(wDebtWorkFlowVO);
                    }
                    else if ("1".equals(wDebtWorkFlowVO.getIs_workflow()))
                    {
                        theContractOrNotarizationOrOther(wDebtWorkFlowVO);
                    }
					formPropertiesMap.put("advice", "他项审批通过");
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("9".equals(wDebtWorkFlowVO.getDebtkey())){//待放款审核 --放款申请
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_FKSQ);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("K");//待审核
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("10".equals(wDebtWorkFlowVO.getDebtkey())){//放款审核
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_FKSQSP);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("G");//待放款
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("2");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("F");//重新放款申请
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("11".equals(wDebtWorkFlowVO.getDebtkey())){//放款
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_FKQR);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("H");//放款
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("12".equals(wDebtWorkFlowVO.getDebtkey())){//补件
                approvalInfo.setApproval_task_type("补件");//
                approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
				wmsCreCreditHead.setBill_status(wDebtWorkFlowVO.getStatus());
				
			}else if("13".equals(wDebtWorkFlowVO.getDebtkey())){//房产核查缴费 2016-9-26流程变更
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_FCHCJF);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("C");//--待办件验房--待贷前审核---
					formPropertiesMap.put("advice", "房产核查缴费完成");
				}else if("overtime".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}
			//更改状态
			wmsCreCreditHead.setLast_update_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
			wmsCreCreditHeadDao.updateforhouse(wmsCreCreditHead);
		}else if(wDebtWorkFlowVO.getProcessDefinitionKey().equals(WorkflowConstantHelp.UPHOUSINGLOANPROCESS)){//2016/5/10房贷流程

			 if("3".equals(wDebtWorkFlowVO.getDebtkey())){//初评预估
				 approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_CPYG);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					if(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_VERSION.equals(version_)){//区分流程版本号 查看地址http://192.168.1.107:9080/WMS/workflow/process-list   UPHOUSINGLOANPROCESS
						wmsCreCreditHead.setBill_status("C");//待办件验房--待贷前审核--- 
					}else{
						 //定义区分流程的字典表id （因地域差异流程不同，沈阳长春不走授信确认但是外阜需要走）
						 List<String> codeList = wmssysdictdataDao.getCodeByDictId(WmsHelp.SYS_DICT_ID);//120为数据库字典表id
		                 if(!codeList.contains(wDebtWorkFlowVO.getCity_code())){
		            	   wDebtWorkFlowVO.setPass("credit");//流转到授信确认
		            	   formPropertiesMap.put("pass","credit");
		            	   wmsCreCreditHead.setBill_status("N");// 2016-9-26 日流程改变新添加  N 授信确认
		                 }else{
		            	   wmsCreCreditHead.setBill_status("O");// 2016-9-26 日流程改变新添加  O 房产核查缴费	 
		                 }
					}
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//已终止
				}else if("supply".equals(wDebtWorkFlowVO.getPass())){//退件  信息补录
					approvalInfo.setApproval_type("2");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("P");//初评退件
				}
			}else if("4".equals(wDebtWorkFlowVO.getDebtkey())){//办件验房
				approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_BJSP);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					//wmsCreCreditHead.setBill_status("D");//待终审
				}else if("overdue".equals(wDebtWorkFlowVO.getPass())){//过期作废
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//过期作废
				}
			}else if("5".equals(wDebtWorkFlowVO.getDebtkey())){//贷款终审
				 approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_DKZS);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("E");//待放款准备--合同-公正-他项
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}else if("supply".equals(wDebtWorkFlowVO.getPass())){//退件  信息补录
					approvalInfo.setApproval_type("2");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Q");//终审退件
				}
			}else if("6".equals(wDebtWorkFlowVO.getDebtkey())){//放款准备--合同
				 approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_HTQD);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("F");//待办件验房--待贷前审核
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("9".equals(wDebtWorkFlowVO.getDebtkey())){//待放款审核 --放款申请
				 approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQ);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("K");//待审核
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("10".equals(wDebtWorkFlowVO.getDebtkey())){//放款审核
				 approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQSP);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("G");//待放款
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("2");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("F");//重新放款申请
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("11".equals(wDebtWorkFlowVO.getDebtkey())){//放款
				 approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKQR);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("H");//放款
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("12".equals(wDebtWorkFlowVO.getDebtkey())){//放款申请手机端
				 approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQ);//审批节点 根据流程中的是一样的 。请查看流程
				if("cross".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("4");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("K");//待放款审核
				}
			}else if("13".equals(wDebtWorkFlowVO.getDebtkey())){//房产核查缴费 2016-9-26流程变更
				approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FCHCJF);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("C");//--待办件验房--待贷前审核---
					formPropertiesMap.put("advice", "房产核查缴费完成");
					formPropertiesMap.put("credit_confirm_result",wDebtWorkFlowVO.getCredit_confirm_result());//处理授信确认结果
				}else if("overtime".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("14".equals(wDebtWorkFlowVO.getDebtkey())){//授信确认 2016-9-26流程变更--此功能pc端暂时没用
				approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_SXQR);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("O");//--房产核查缴费 2016-9-26流程变更
					formPropertiesMap.put("advice", "授信确认完成");
				}else if("overtime".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("15".equals(wDebtWorkFlowVO.getDebtkey())){//电审审批  2016-9-26流程变更
				approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_DSSP);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					//wmsCreCreditHead.setBill_status("D");//待终审
				}else if("overdue".equals(wDebtWorkFlowVO.getPass())){//过期作废
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//过期作废
				}
			}else if("16".equals(wDebtWorkFlowVO.getDebtkey())){//证信审批  2016-9-26流程变更
				approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_ZXSP);//审批节点 根据流程中的是一样的 。请查看流程
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					//wmsCreCreditHead.setBill_status("D");//待终审
				}else if("overdue".equals(wDebtWorkFlowVO.getPass())){//过期作废
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//过期作废
				}
			}else if("17".equals(wDebtWorkFlowVO.getDebtkey())){//房产信息补录  2016-9-26流程变更
				approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_XXBL);//审批节点 根据流程中的是一样的 。请查看流程
				if("makepg".equals(wDebtWorkFlowVO.getPass())){//房产评估节点
					approvalInfo.setApproval_type("2");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("L");//评估
				}else if("makezs".equals(wDebtWorkFlowVO.getPass())){//房产终审节点
					approvalInfo.setApproval_type("2");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("D");//待终审
				}
			}
		}
        // 保存审批信息
        // 当前时间
        java.sql.Timestamp nowDate = new java.sql.Timestamp(System.currentTimeMillis());
        wmsCreCreditHead.setLast_update_timestamp(nowDate);
        // 审批表
        approvalInfo.setWms_cre_credit_head_id(Integer.valueOf(wDebtWorkFlowVO.getBusinessId()));// 贷款主表id
        approvalInfo.setApproval_advice(wDebtWorkFlowVO.getAdvice());// 审批意见
        approvalInfo.setApproval_result(wDebtWorkFlowVO.getPass());// 审批结果//
                                                                   // 流程中传递pass的值
        approvalInfo.setApproval_user_id(Integer.valueOf(wDebtWorkFlowVO.getUserId()));// 审批人id
        approvalInfo.setApproval_time(nowDate);// 审批时间
        approvalInfo.setEnable_flag("1");
        wmsCreHousingApprovalInfoDao.saveInfo(approvalInfo);

        // 是否存在流程：1存在 0不存在 2016-12-27 baisong
        if ("1".equals(wDebtWorkFlowVO.getIs_workflow()))
        {
            // 如果标识有流程但是taskid还是空的时候需要查询taskId
            if (wDebtWorkFlowVO.getTaskId() == null || "undefined".equals(wDebtWorkFlowVO.getTaskId()) || "".equals(wDebtWorkFlowVO.getTaskId()))
            {
                WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
                // 业务主键
                workflowSearchInfoHelp.setBusinessKey(wDebtWorkFlowVO.getBusinessId());
                // 节点名称
                workflowSearchInfoHelp.setTaskName(approvalInfo.getApproval_task_type());
                Task task = workflowService.getTaskInfo(workflowSearchInfoHelp);
                // 如果是合同签订则改变查询条件（因UPHOUSINGLOANPROCESS和PERFECTHOUSINGLOANPROCESS版本合同节点名称不一致的问题）
                if (task == null && WorkflowConstantHelp.UPHOUSINGLOANPROCESS_HTQD.equals(wDebtWorkFlowVO.getDebtkey()))
                {
                    workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_QDHT);
                    task = workflowService.getTaskInfo(workflowSearchInfoHelp);
                }
                if (task != null)
                {
                    wDebtWorkFlowVO.setTaskId(task.getId());
                }
            }
            // 调用流程
            workflowService.completeTask(wDebtWorkFlowVO.getUserId(), wDebtWorkFlowVO.getTaskId(), formPropertiesMap);
        }

		//判断手机端流程是否是房产核查（办件验房） 电审审批 征信审批  三个并行特殊处理 更改单据状态
		if(wDebtWorkFlowVO.getProcessDefinitionKey().equals(WorkflowConstantHelp.UPHOUSINGLOANPROCESS)){//2016/5/10房贷流程
			if("16".equals(wDebtWorkFlowVO.getDebtkey())||"15".equals(wDebtWorkFlowVO.getDebtkey())||"4".equals(wDebtWorkFlowVO.getDebtkey())){
				judgeApprovalStatus(wDebtWorkFlowVO,wmsCreCreditHead);//判断是否需要更改单据状态	
			}	
		}
		//更改状态
		wmsCreCreditHeadDao.updateforhouse(wmsCreCreditHead);
	}
	/**
	 * 涵盖:新房贷流程
	 * 实现所有流程所有节点的审批操作同时改变所有操作后的单据状态变化--手机端审批使用
     * @param debtkey 任务节点key
     *      当debtkey=1 代表复核
	 *      当debtkey=2 代表重新申请
	 * 		当debtkey=3 代表初评预估
	 * 		当debtkey=4 代表办件验房
     * 		当debtkey=5 代表贷款终审
     * 		当debtkey=6 代表放款准备合同
     * 		当debtkey=7 代表放款准备公证
     * 		当debtkey=8 代表放款准备他项
     * 		当debtkey=9 代表待放款审核--放款申请
     * 		当debtkey=10 代表放款审核
     * 		当debtkey=11 代表放款
     * 		当debtkey=12 代表补件
     * 
     * @return 
	 * @author baisong
	 * @throws Exception 
	 */
	@Override
    @Transactional
	public String publicApprovalPhone(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO){
		Map<String,String> formPropertiesMap = new HashMap<String, String>();
		WmsCreCreditHead wmsCreCreditHead = new WmsCreCreditHead();//信用贷款主表
		formPropertiesMap.put("pass",wDebtWorkFlowVO.getPass());
		formPropertiesMap.put("advice", wDebtWorkFlowVO.getAdvice());
		String result="success";
		//贷款表主键就是BusinessId
		if(wDebtWorkFlowVO.getWms_cre_credit_head_id()!=null&&!"".equals(wDebtWorkFlowVO.getWms_cre_credit_head_id())){
			wDebtWorkFlowVO.setBusinessId(wDebtWorkFlowVO.getWms_cre_credit_head_id());	
		}
		WmsCreCreditHead wmsHead= new WmsCreCreditHead();//信用贷款主表
		wmsHead=wmsCreCreditHeadDao.get(Integer.valueOf(wDebtWorkFlowVO.getBusinessId()));//根据主键获取贷款主表信息主要用于贷款手机端
        // 兼容组合贷数据 如果数据流程标示为空则查询数据库
        if (wDebtWorkFlowVO.getIs_workflow() == null)
        {
            wDebtWorkFlowVO.setIs_workflow(wmsHead.getIs_workflow());
        }
		//数据来源1为pc 2为移动端
		if("2".equals(wmsHead.getVersion_number())){
			wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);	
		}else{
		    wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);	
		}
		 WmsCreHousingApprovalInfo approvalInfo=new WmsCreHousingApprovalInfo();//房贷——审批信息表
		if(wDebtWorkFlowVO.getProcessDefinitionKey().equals(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS)){//房产流程第一版   
			wmsCreCreditHead.setWms_cre_credit_head_id(Integer.valueOf(wDebtWorkFlowVO.getBusinessId()));
			if("4".equals(wDebtWorkFlowVO.getDebtkey())){//办件验房
			    approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_BJSP);//审批节点 根据流程中的是一样的 。请查看流程
				if(!"C".equals(wmsHead.getBill_status())){
					result="error";
					return result;
				}
				Map<String,Object> map=	gettaskId(wDebtWorkFlowVO.getProcessDefinitionKey(),wDebtWorkFlowVO.getBusinessId(),wDebtWorkFlowVO.getUserId(),WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_BJSP);
				if("success".equals(map.get("result"))){
					wDebtWorkFlowVO.setTaskId(map.get("taskId").toString());
				}else{
					result="taskIdError";//流程id错误
					return result;
				}
				if("true".equals(wDebtWorkFlowVO.getPass())||"false".equals(wDebtWorkFlowVO.getPass())){
				    approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("D");//待终审

//              }
                    // 2016-12-20baisong 根据需求确定 添加过期按钮此段代码作废
//					else if("overdue".equals(wDebtWorkFlowVO.getPass())){
//					result="notallowerror";//当前单据不允许当前操作
//					return result;
                }
                // 2016-12-20baisong 根据需求确定 添加过期按钮
                else if ("overdue".equals(wDebtWorkFlowVO.getPass()))
                {
                    approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
                    // 过期作废
                    wmsCreCreditHead.setBill_status("Z");//过期作废
                }else if("supply".equals(wDebtWorkFlowVO.getPass())){//初评补件
                    approvalInfo.setApproval_type("2");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("J");//补件中
				}

			}else if("9".equals(wDebtWorkFlowVO.getDebtkey())){//待放款审核 --放款申请
				if(!"F".equals(wmsHead.getBill_status())&&!"E".equals(wmsHead.getBill_status())){//不能为贷贷前准备和待放款审核
					result="error";
					return result;
				}
				Map<String,Object> map=	new HashMap<>();
				if("E".equals(wmsHead.getBill_status())){
					if(!"nullify".equals(wDebtWorkFlowVO.getPass())){
						wDebtWorkFlowVO.setPass("cross");//如果是带贷前准备的状态需要改变传递参数，请参看流程图
						formPropertiesMap.put("pass",wDebtWorkFlowVO.getPass());
					}
					map=gettaskId(wDebtWorkFlowVO.getProcessDefinitionKey(),wDebtWorkFlowVO.getBusinessId(),wDebtWorkFlowVO.getUserId(),WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_QDHT);
				}else{
					map=gettaskId(wDebtWorkFlowVO.getProcessDefinitionKey(),wDebtWorkFlowVO.getBusinessId(),wDebtWorkFlowVO.getUserId(),WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_FKSQ);	
				}
				if("success".equals(map.get("result"))){
					wDebtWorkFlowVO.setTaskId(map.get("taskId").toString());
				}else{
					result="taskIdError";
					return result;
				}
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsCreCreditHead.setBill_status("K");//待审核
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("10".equals(wDebtWorkFlowVO.getDebtkey())){//放款审核
				if(!"K".equals(wmsHead.getBill_status())){
					result="error";
					return result;
				}
				Map<String,Object> map=	gettaskId(wDebtWorkFlowVO.getProcessDefinitionKey(),wDebtWorkFlowVO.getBusinessId(),wDebtWorkFlowVO.getUserId(),WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_FKSQSP);
				if("success".equals(map.get("result"))){
					wDebtWorkFlowVO.setTaskId(map.get("taskId").toString());
				}else{
					result="taskIdError";
					return result;
				}
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsCreCreditHead.setBill_status("G");//待放款
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsCreCreditHead.setBill_status("F");//重新放款申请
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}
			//更改状态
			wmsCreCreditHead.setLast_update_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
			wmsCreCreditHeadDao.updateforhouse(wmsCreCreditHead);
		}else if(wDebtWorkFlowVO.getProcessDefinitionKey().equals(WorkflowConstantHelp.UPHOUSINGLOANPROCESS)){//2016/5/10房贷流程
			wmsCreCreditHead.setWms_cre_credit_head_id(Integer.valueOf(wDebtWorkFlowVO.getBusinessId()));
			if("4".equals(wDebtWorkFlowVO.getDebtkey())){//办件验房
				approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_BJSP);//审批节点 根据流程中的是一样的 。请查看流程
				if(!"C".equals(wmsHead.getBill_status())){
					result="error";
					return result;
				}
				Map<String,Object> map=	gettaskId(wDebtWorkFlowVO.getProcessDefinitionKey(),wDebtWorkFlowVO.getBusinessId(),wDebtWorkFlowVO.getUserId(),WorkflowConstantHelp.UPHOUSINGLOANPROCESS_BJSP);
				if("success".equals(map.get("result"))){
					wDebtWorkFlowVO.setTaskId(map.get("taskId").toString());
				}else{
					result="taskIdError";
					return result;
				}
				if("true".equals(wDebtWorkFlowVO.getPass())||"false".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					//wmsCreCreditHead.setBill_status("D");//待终审
						
				}else if("overdue".equals(wDebtWorkFlowVO.getPass())){//过期作废
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//过期作废
				}
			}else if("9".equals(wDebtWorkFlowVO.getDebtkey())){//待放款审核 --放款申请
				approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQ);//审批节点 根据流程中的是一样的 。请查看流程
				if(!"F".equals(wmsHead.getBill_status())&&!"E".equals(wmsHead.getBill_status())){//不能为贷贷前准备和待放款审核
					result="error";
					return result;
				}
				Map<String,Object> map=	new HashMap<>(); 
				if("E".equals(wmsHead.getBill_status())){
					if(!"nullify".equals(wDebtWorkFlowVO.getPass())){
						wDebtWorkFlowVO.setPass("cross");//如果是带贷前准备的状态需要改变传递参数，请参看流程图
						approvalInfo.setApproval_type("4");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
						formPropertiesMap.put("pass",wDebtWorkFlowVO.getPass());
					}
                    if ("1".equals(wDebtWorkFlowVO.getIs_workflow()))
                    {
                        map = gettaskId(wDebtWorkFlowVO.getProcessDefinitionKey(), wDebtWorkFlowVO.getBusinessId(), wDebtWorkFlowVO.getUserId(), WorkflowConstantHelp.UPHOUSINGLOANPROCESS_HTQD);
                    }
                }
                else if ("1".equals(wDebtWorkFlowVO.getIs_workflow()))
                {
					map=gettaskId(wDebtWorkFlowVO.getProcessDefinitionKey(),wDebtWorkFlowVO.getBusinessId(),wDebtWorkFlowVO.getUserId(),WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQ);	
				}
				if("success".equals(map.get("result"))){
					wDebtWorkFlowVO.setTaskId(map.get("taskId").toString());
				}else{
					result="taskIdError";
					return result;
				}
				if("true".equals(wDebtWorkFlowVO.getPass())||"cross".equals(wDebtWorkFlowVO.getPass())){
					if("cross".equals(wDebtWorkFlowVO.getPass())){
						approvalInfo.setApproval_type("4");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					}else{
						approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					}
					wmsCreCreditHead.setBill_status("K");//待审核
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("10".equals(wDebtWorkFlowVO.getDebtkey())){//放款审核
				approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQSP);//审批节点 根据流程中的是一样的 。请查看流程
				if(!"K".equals(wmsHead.getBill_status())){
					result="error";
					return result;
				}
                if ("1".equals(wDebtWorkFlowVO.getIs_workflow()))
                {
                    Map<String, Object> map = gettaskId(wDebtWorkFlowVO.getProcessDefinitionKey(), wDebtWorkFlowVO.getBusinessId(), wDebtWorkFlowVO.getUserId(), WorkflowConstantHelp.UPHOUSINGLOANPROCESS_FKSQSP);
                    if ("success".equals(map.get("result")))
                    {
                        wDebtWorkFlowVO.setTaskId(map.get("taskId").toString());
                    }
                    else
                    {
                        result = "taskIdError";
                        return result;
                    }
				}
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("G");//待放款
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("2");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("F");//重新放款申请
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}else if("14".equals(wDebtWorkFlowVO.getDebtkey())){//授信确认 2016-9-26流程变更
				approvalInfo.setApproval_task_type(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_SXQR);//审批节点 根据流程中的是一样的 。请查看流程
				Map<String,Object> map=	gettaskId(wDebtWorkFlowVO.getProcessDefinitionKey(),wDebtWorkFlowVO.getBusinessId(),wDebtWorkFlowVO.getUserId(),WorkflowConstantHelp.UPHOUSINGLOANPROCESS_SXQR);
				if("success".equals(map.get("result"))){
					wDebtWorkFlowVO.setTaskId(map.get("taskId").toString());
				}else{
					result="taskIdError";
					return result;
				}
				if("true".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("1");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("O");//--房产核查缴费 2016-9-26流程变更
					formPropertiesMap.put("advice", "授信确认完成");
				}else if("overtime".equals(wDebtWorkFlowVO.getPass())){
					approvalInfo.setApproval_type("3");//审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
					wmsCreCreditHead.setBill_status("Z");//终止
				}
			}
	        if(!"success".equals(result)) {
	        	throw new RuntimeException("单据状态不正确，审批流程错误！");
	        }
		}
        // 当前时间
        java.sql.Timestamp nowDate = new java.sql.Timestamp(System.currentTimeMillis());
        wmsCreCreditHead.setLast_update_timestamp(nowDate);
		//审批表
        approvalInfo.setWms_cre_credit_head_id(Integer.valueOf(wDebtWorkFlowVO.getBusinessId()));//贷款主表id
        approvalInfo.setApproval_advice(wDebtWorkFlowVO.getAdvice());//审批意见
        approvalInfo.setApproval_result(wDebtWorkFlowVO.getPass());//审批结果  流程中传递pass的值
        approvalInfo.setApproval_user_id(Integer.valueOf(wDebtWorkFlowVO.getUserId()));//审批人id
        approvalInfo.setApproval_time(nowDate);//审批时间
        approvalInfo.setEnable_flag("1");
        wmsCreHousingApprovalInfoDao.saveInfo(approvalInfo);
		//调用流程
		//workflowService.completeTask(wDebtWorkFlowVO.getUserId(), wDebtWorkFlowVO.getTaskId(), formPropertiesMap);
        // 是否存在流程：1存在 0不存在 2016-12-27 baisong
        if ("1".equals(wDebtWorkFlowVO.getIs_workflow()))
        {
            // 调用流程
            workflowService.completeTask(wDebtWorkFlowVO.getUserId(), wDebtWorkFlowVO.getTaskId(), formPropertiesMap);
        }
		//判断手机端流程是否是房产核查（办件验房） 电审审批 征信审批  三个并行特殊处理 更改单据状态
		if(wDebtWorkFlowVO.getProcessDefinitionKey().equals(WorkflowConstantHelp.UPHOUSINGLOANPROCESS)){//2016/5/10房贷流程
			if("4".equals(wDebtWorkFlowVO.getDebtkey())){
				judgeApprovalStatus(wDebtWorkFlowVO,wmsCreCreditHead);//判断是否需要更改单据状态	
			}	
		}
		//更改状态
		wmsCreCreditHeadDao.updateforhouse(wmsCreCreditHead);
		return result;
	}
	/**
	 * 获取对应参数taskid
	 * @param processDefinitionKey
	 * @param businessId
	 * @param userId
	 * @param taskName
	 * @return
	 * @author baisong
	 */
	public Map<String,Object> gettaskId(String processDefinitionKey,String businessId,String userId,String taskName){
		//2016-10-11找到更好的方法查找task信息 baisong
		Map<String, Object> paramMap=new HashMap<>();
		if(processDefinitionKey==null||businessId==null||taskName==null){
			paramMap.put("result", "error");
    		paramMap.put("message", "找不到对应的参数！");
	        return paramMap;
		}
		Task task= workflowService.getTaskInfobyKey(businessId,taskName,processDefinitionKey);
		if(task==null){
			paramMap.put("result", "error");
    		paramMap.put("message", "找不到对应的流程参数！");
	        return paramMap;
		}else{
    		paramMap.put("taskId", task.getId());
    		paramMap.put("result", "success");
    		paramMap.put("message", "找到对应的参数！");
    		return paramMap;
    	}
		//因方法不够优化所以作废
		/*WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(processDefinitionKey);// 房贷key
        workflowSearchInfoHelp.setBusinessKey(businessId);
        workflowSearchInfoHelp.setUserId(userId);
        workflowSearchInfoHelp.setTaskName(taskName);
        Map<String, Object> paramMap=new HashMap<>();
        // 根据条件查找代办任务信息
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
    	if(workflowInfoHelps==null||workflowInfoHelps.size()==0){ 		
    		//paramMap.put("taskId", workflowInfoHelps.get(0).getTaskId());
    		paramMap.put("result", "error");
    		paramMap.put("message", "找不到对应的参数！");
	        return paramMap;
    	}else{
    		paramMap.put("taskId", workflowInfoHelps.get(0).getTaskId());
    		paramMap.put("result", "success");
    		paramMap.put("message", "找到对应的参数！");
    		return paramMap;
    	}*/
	}
    
    /**
     * 实现公共获取流程节点查询该节点数据
     * @param processDefinitionKey 流程实例key
     * @param userId 当前审批ID
     * @param taskName 当前节点名称
     * @return
     */
    private List<WorkflowInfoHelp> setWorkFlowList(String processDefinitionKey,String userId,String taskName){
    	WorkflowSearchInfoHelp wInfoHelp = new WorkflowSearchInfoHelp();
    	wInfoHelp.setProcessDefinitionKey(processDefinitionKey);
    	wInfoHelp.setUserId(userId);
    	wInfoHelp.setTaskName(taskName);
    	List<WorkflowInfoHelp> wHelps = workflowService.findPackageTodoTasks(wInfoHelp);
    	return wHelps;
    }
    /**
     * 实现公共获取流程节点查询该节点数据  userid可以为空
     * @param processDefinitionKey 流程实例key
     * @param userId 当前审批ID
     * @param taskName 当前节点名称
     * @return
     */
    private List<WorkflowInfoHelp> setWorkFlowListNoUser(String processDefinitionKey,String userId,String taskName){
    	WorkflowSearchInfoHelp wInfoHelp = new WorkflowSearchInfoHelp();
    	wInfoHelp.setProcessDefinitionKey(processDefinitionKey);
    	wInfoHelp.setUserId(userId);
    	wInfoHelp.setTaskName(taskName);
    	List<WorkflowInfoHelp> wHelps = workflowService.findPackageTodoTasksNoUser(wInfoHelp);
    	return wHelps;
    }
    /**
     * 实现公共获取流程节点查询该节点数据--根据业务主见查询当前单据的taskId
     * @param processDefinitionKey 流程实例key
     * @param businessKey 当前节点名称
     * @return
     */
    private List<WorkflowInfoHelp> getTaskIdbyBusinessKey(String processDefinitionKey,String businessKey){
    	WorkflowSearchInfoHelp wInfoHelp = new WorkflowSearchInfoHelp();
    	wInfoHelp.setProcessDefinitionKey(processDefinitionKey);
    	wInfoHelp.setBusinessKey(businessKey);
    	List<WorkflowInfoHelp> wHelps = workflowService.findPackageTodoTasks(wInfoHelp);
    	return wHelps;
    }
    /**
     * 实现公共获取流程节点查询数据返回结果
     * @param wHelps
     * @return
     */
    private Map<String,Object> setWorkFlowMap(List<WorkflowInfoHelp> wHelps,String processDefinitionKey,String invekey){
    	 Map<String, Object> parmMap = new HashMap<String, Object>();
         List<Integer> idList = new ArrayList<Integer>();
         List<String> taskIdList = new ArrayList<String>();
         List<String> approvesGroups = new ArrayList<>();
         List<String> approveAdvices = new ArrayList<>();
         List<String> approveTimes = new ArrayList<>();
         for (WorkflowInfoHelp workflowInfoHelp : wHelps)
         {
        	 idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
             taskIdList.add(workflowInfoHelp.getTaskId());
             if (invekey.equals("5"))
             {
                 WorkflowSearchInfoHelp w = new WorkflowSearchInfoHelp();
                 w.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
                 w.setFinish(true);
                 w.setBusinessKey(workflowInfoHelp.getBusinessKey());
                 List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(w);
                 for (int i = workflowHistoryInfoHelps.size() - 1; i >= 0; i--)
                 {
                     if ("supply".equals(workflowHistoryInfoHelps.get(i).getApproveResult()))
                     {
                         approvesGroups.add(workflowHistoryInfoHelps.get(i).getTaskName());
                         approveAdvices.add(workflowHistoryInfoHelps.get(i).getApproveAdvice());
                         approveTimes.add(workflowHistoryInfoHelps.get(i).getApproveTime());
                         break;
                     }
                 }
             }
         }
         parmMap.put("approvesGroups", approvesGroups);
         parmMap.put("approveAdvices", approveAdvices);
         parmMap.put("approveTimes", approveTimes);
         parmMap.put("idList", idList);
         parmMap.put("taskIdList", taskIdList);
         parmMap.put("processDefinitionKey", processDefinitionKey);
         return parmMap;
    }
    /**
     * 根据人员获取当前人的电话
     * @param userId
     * @param sendMap
     */
    public void  getPmInfo(String userId,Map<String,String> sendMap){
    	 //发送短信参数
    	if(userId!=null){
    		try {
	    		PmPersonnel pmpersonnel=pmpersonnelDao.get(Integer.valueOf(userId));
	    		if(pmpersonnel!=null){
	    			sendMap.put("tel", pmpersonnel.getPersonnel_contacttel());//电话	
	    		}
	    		//发送短信
	    		sendMap.put("tpl_id", "1689");
	    		
                //参数map
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("url", PlatformGlobalVar.SYS_PROPERTIES.get("zshUrl"));
                Gson gson = new Gson();
                sendMap.put("paramJson", gson.toJson(paramMap));
	    		
		        SysUtil.sendMsg(sendMap);
    		}catch(Exception e){
    		}
    	}
    }
    /**
     * 根据人员获取当前人的电话
     * @param userId
     * @param sendMap
     */
    public void  getPmInfo(Map<String,String> sendMap){
    		try {
	    		//发送短信
    		    sendMap.put("tpl_id", "1689");

                //参数map
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("url", PlatformGlobalVar.SYS_PROPERTIES.get("zshUrl"));
                Gson gson = new Gson();
                sendMap.put("paramJson", gson.toJson(paramMap));
                
		        SysUtil.sendMsg(sendMap);
    		}catch(Exception e){
    		}
    }
    /**
	 * 合同公正他项 审批公用方法
	 * @param approveHouseWorkFlowVO
	 * @param key
	 */
    public void theContractOrNotarizationOrOther(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO)
    {
        // 根据存储的状态来判断是否要改变表的状态
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 获取流程key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
        workflowSearchInfoHelp.setFinish(true);
        workflowSearchInfoHelp.setBusinessKey(wDebtWorkFlowVO.getBusinessId());
        workflowSearchInfoHelp.setTaskName("签订合同");
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
        if ((historicTaskInstances == null || historicTaskInstances.size() == 0)&&!"6".equals(wDebtWorkFlowVO.getDebtkey()))
        {
            return;
        }
        workflowSearchInfoHelp.setTaskName("公证");
        historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
        if ((historicTaskInstances == null || historicTaskInstances.size() == 0)&&!"7".equals(wDebtWorkFlowVO.getDebtkey()))
        {
            return;
        }
        workflowSearchInfoHelp.setTaskName("他项");
        historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
        if ((historicTaskInstances == null || historicTaskInstances.size() == 0)&&!"8".equals(wDebtWorkFlowVO.getDebtkey()))
        {
            return;
        }

        Map<String, Object> aMap = new HashMap<>();
        aMap.put("wms_cre_credit_head_id", wDebtWorkFlowVO.getBusinessId());
        // 改变表单状态：F待放款审核
        aMap.put("bill_status", "F");
        wmsCreCreditHeadDao.updateRecord(aMap);
    }
    /**
     * 房贷补件根据流程历程判断状态
     * @param wDebtWorkFlowVO
     * @return
     * baisong
     */
    public String getInfoforworkflow(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO){
    	String bill_status="";
    	WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);// 房产流程实例key
        workflowSearchInfoHelp.setBusinessKey(String.valueOf(wDebtWorkFlowVO.getBusinessId()));
        // 放款申请审批节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_BJSP);
        workflowSearchInfoHelp.setFinish(true);//流程节点是否完成
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
        	bill_status="C";//办件验房
        }else{
        	bill_status="L";//初评预估
        }
        return bill_status;
    }
    @Override
    public void houseCreditInquiryTreatment(String userId, String wms_cre_credit_head_id, String key)
    {
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);// 房贷key
        workflowSearchInfoHelp.setBusinessKey(wms_cre_credit_head_id);
        workflowSearchInfoHelp.setUserId(userId);
        if ("1".equals(key))
        {
            // 实现房贷草稿 实现流程开启
        	startFinancialWorkFlow(userId, wms_cre_credit_head_id, null,null,WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
        }
        else if ("2".equals(key))
        {
            // 实现复核退回
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_CXSQ);
            // 根据条件查找代办任务信息
            List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
            // 提交给流程处理 该表表单状态
            WmsHouseCreditWorkFlowVO approveVo = new WmsHouseCreditWorkFlowVO();
            approveVo.setUserId(userId);
            approveVo.setPass("true");
            approveVo.setAdvice("重新申请已提交");
            approveVo.setTaskId(workflowInfoHelps.get(0).getTaskId());//流程id
            approveVo.setBusinessId(wms_cre_credit_head_id);//单据id
            approveVo.setDebtkey("2");//重新申请
            approveVo.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
            publicApproval(approveVo);//执行流程
        }
        else if ("3".equals(key))
        {
        	 // 提交给流程处理 修改该表单状态
            WmsHouseCreditWorkFlowVO approveVo = new WmsHouseCreditWorkFlowVO();
            // 实现信贷补件
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_CPBJ);//初评补件
            // 根据条件查找代办任务信息
            List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
            approveVo.setStatus("L");//初评补件
            if(workflowInfoHelps==null ||workflowInfoHelps.size()==0){
            	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_BJBJ);//办件补件
            	workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
            	approveVo.setStatus("C");//办件补件
            }
            if(workflowInfoHelps==null ||workflowInfoHelps.size()==0){
            	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_ZSBJ);//终审补件
            	workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
            	approveVo.setStatus("D");//终审补件
            }
            approveVo.setUserId(userId);
            approveVo.setPass("true");
            approveVo.setAdvice("补件完成");
            approveVo.setTaskId(workflowInfoHelps.get(0).getTaskId());//流程id
            approveVo.setBusinessId(wms_cre_credit_head_id);//单据id
            approveVo.setDebtkey("12");//补件
            approveVo.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
            publicApproval(approveVo);//执行流程
        }
    }
	/**
	 * 签订合同他项公正作废
	 * @param userId
	 * @param wms_cre_credit_head_id
	 * @param key
	 */
    @Override
    public String houseCncel(String userId, String wms_cre_credit_head_id, String key)
    {
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);// 房贷key
        workflowSearchInfoHelp.setBusinessKey(wms_cre_credit_head_id);
        workflowSearchInfoHelp.setUserId(userId);
        workflowSearchInfoHelp.setUnFinish(true);// 查询完成
        	// 实现作废
            // workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_GZ);
            // 根据条件查找代办任务信息
            List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
            int num =0;
            for(WorkflowInfoHelp  workhelp: workflowInfoHelps){
            	if(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_GZ.equals(workhelp.getTaskName())
        			||WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TX.equals(workhelp.getTaskName())
        			||WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_QDHT.equals(workhelp.getTaskName())){//公正他项签订合同
            		num++;
            	}
            }
            if(num<2){
            	return "notnullify" ;//不能作废
            }
	        for(WorkflowInfoHelp  workhelp: workflowInfoHelps){
	        	if(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_GZ.equals(workhelp.getTaskName())){//公正
	        		if(workhelp!=null){//公正
	        			// 提交给流程处理 该表表单状态
	        			WmsHouseCreditWorkFlowVO approveVo = new WmsHouseCreditWorkFlowVO();
	        			approveVo.setUserId(userId);
	        			approveVo.setPass("nullify");
	        			approveVo.setAdvice("作废");
	        			approveVo.setTaskId(workhelp.getTaskId());//流程id
	        			approveVo.setBusinessId(wms_cre_credit_head_id);//单据id
	        			approveVo.setDebtkey("7");//
	        			approveVo.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
	        			publicApproval(approveVo);//执行流程
	                }
	        	}
	        	if(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TX.equals(workhelp.getTaskName())){//公正
		        	if(workhelp!=null ){//他项
						// 提交给流程处理 该表表单状态
						WmsHouseCreditWorkFlowVO approveVo = new WmsHouseCreditWorkFlowVO();
						approveVo.setUserId(userId);
						approveVo.setPass("nullify");
						approveVo.setAdvice("作废");
						approveVo.setTaskId(workhelp.getTaskId());//流程id
						approveVo.setBusinessId(wms_cre_credit_head_id);//单据id
						approveVo.setDebtkey("8");//
						approveVo.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
						publicApproval(approveVo);//执行流程
			        }
	        	}
	        	if(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_QDHT.equals(workhelp.getTaskName())){//公正
		        	 if(workhelp!=null ){//签订合同
		 				// 提交给流程处理 该表表单状态
		 				WmsHouseCreditWorkFlowVO approveVo = new WmsHouseCreditWorkFlowVO();
		 				approveVo.setUserId(userId);
		 				approveVo.setPass("nullify");
		 				approveVo.setAdvice("作废");
		 				approveVo.setTaskId(workhelp.getTaskId());//流程id
		 				approveVo.setBusinessId(wms_cre_credit_head_id);//单据id
		 				approveVo.setDebtkey("6");//
		 				approveVo.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
		 				publicApproval(approveVo);//执行流程
		 	        }
	        	}
	        	
	        }
	        return "success";
    }
    /**
	 * 获取某个节点的taskid
	 * @param userId 人员id
	 * @param BusinessKey  单据主键
	 * @param processDefinitionKey 流程key
	 * @param taskName 流程节点名称
	 * @return  List<WorkflowInfoHelp>
	 * @date 2016/5/10
	 * @author baisong
	 */
    @Override
    public  List<WorkflowInfoHelp> getTaskId(String userId, String wms_cre_credit_head_id, String processDefinitionKey,String taskName)
    {
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(processDefinitionKey);//key
        workflowSearchInfoHelp.setBusinessKey(wms_cre_credit_head_id);
        workflowSearchInfoHelp.setUserId(userId);
        workflowSearchInfoHelp.setUnFinish(true);//查询未完成
        //节点名称
        workflowSearchInfoHelp.setTaskName(taskName);
        // 根据条件查找代办任务信息
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
    	return workflowInfoHelps;

    }
    

    /**
     *房贷流程判断办件审核 电审审核 征信审核是否完成
     * @param wDebtWorkFlowVO
     * @param wmsCreCreditHead
     */
    
    private void judgeApprovalStatus(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO,WmsCreCreditHead wmsCreCreditHead){
    	Task task=workflowService.getTaskInfobyKey(wDebtWorkFlowVO.getBusinessId(), WorkflowConstantHelp.UPHOUSINGLOANPROCESS_DKZS, WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
    	if(task!=null&&task.getId()!=null){
    		wmsCreCreditHead.setBill_status("D");//待终审
    	}
    	/*//查询流程
    	List<WorkflowInfoHelp> WorkflowInfoList=getTaskId(wDebtWorkFlowVO.getUserId(), wDebtWorkFlowVO.getBusinessId(), WorkflowConstantHelp.UPHOUSINGLOANPROCESS,WorkflowConstantHelp.UPHOUSINGLOANPROCESS_DKZS);//贷款终审环节
    	if(WorkflowInfoList!=null&&WorkflowInfoList.size()>0){
    		
    	}*/
    }
    
   /**
    * 获取流程版本号
    * @param taskId
    * @return String 
    * @author  baisong
    * @date 2016-10-17
    */
    @Override
    public String getVersion_(String taskId){
    	String version_="";
		//获取流程版本
		Task task=workflowService.getTaskInfo(taskId);
		String tasksz[]	=task.getProcessDefinitionId().split(":");
		version_=tasksz[1];
		return version_;
    }

    /**
     * 合同公正他项 审批公用方法 没有流程版本
     * @param approveHouseWorkFlowVO
     * @param key
     */
    public void theContractOrNotarizationOrOtherNoWorkFlow(WmsHouseCreditWorkFlowVO wDebtWorkFlowVO)
    {
        WmsCreHousingApprovalInfo approvalInfo=new WmsCreHousingApprovalInfo();
        approvalInfo.setWms_cre_credit_head_id(Integer.valueOf(wDebtWorkFlowVO.getWms_cre_credit_head_id()));
        approvalInfo.setEnable_flag("1");
        approvalInfo.setApproval_type("1");
        // 签合同
        if(!"6".equals(wDebtWorkFlowVO.getDebtkey())){
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_QDHT);// 签订合同
            List<WmsCreHousingApprovalInfo> list_QDHT = wmsCreHousingApprovalInfoDao.getListByEntity(approvalInfo);
            if (list_QDHT == null || list_QDHT.size() == 0)
            {
                return;
            }
        }
        // 公正
        if (!"7".equals(wDebtWorkFlowVO.getDebtkey()))
        {
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_GZ);// 公正
            List<WmsCreHousingApprovalInfo> list_GZ = wmsCreHousingApprovalInfoDao.getListByEntity(approvalInfo);
            if (list_GZ == null || list_GZ.size() == 0)
            {
                return;
            }
        }
        // 他想
        if (!"8".equals(wDebtWorkFlowVO.getDebtkey()))
        {
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TX);// 他项
            List<WmsCreHousingApprovalInfo> list_TX = wmsCreHousingApprovalInfoDao.getListByEntity(approvalInfo);
            if (list_TX == null || list_TX.size() == 0)
            {
                return;
            }
        }
        Map<String, Object> aMap = new HashMap<>();
        aMap.put("wms_cre_credit_head_id", wDebtWorkFlowVO.getBusinessId());
        // 改变表单状态：F待放款审核
        aMap.put("bill_status", "F");
        wmsCreCreditHeadDao.updateRecord(aMap);
    }

    /**
     * @Title: publicApprovalStatus
     * @Description: 状态流转控制方法
     * @param wDebtStatusVO
     * @return 
     * @author: baisong
     * @time:2017年2月17日 下午2:36:07
     * @see com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService#publicApprovalStatus(com.zx.emanage.cremanage.vo.WmsHouseCreditStatusVO)
     * history:
     * 1、2017年2月17日 baisong 创建方法
     * 
     *     申请 apply
                               修订 revise
                               通过 true 
                               拒件 false
                               作废 nullify
                               退件 back
                               直接验房 direct
    */
    @Override
    public Map<String, Object> publicApprovalStatus(WmsHouseCreditWorkFlowVO wDebtStatusVO)
    {
        Map<String, Object> retMap = new HashMap<String, Object>();

        WmsCreHousingApprovalInfo approvalInfo = new WmsCreHousingApprovalInfo();// 房贷——审批信息表
        approvalInfo.setWms_cre_credit_head_id(Integer.valueOf(wDebtStatusVO.getWms_cre_credit_head_id()));
        WmsCreCreditHead wmsCreCreditHead = new WmsCreCreditHead();//贷款主表
        wmsCreCreditHead.setWms_cre_credit_head_id(Integer.valueOf(wDebtStatusVO.getWms_cre_credit_head_id()));
        WmsCreCreditHead wHead = new WmsCreCreditHead();//贷款主表
        
        boolean nullify_flag = false;//作废标识：为true时需更新主表字段
        
        //如果单据状态为空则查询数据库
        if (wDebtStatusVO.getBill_status() == null||"".equals(wDebtStatusVO.getBill_status())||"undefined".equals(wDebtStatusVO.getBill_status()))
        {
            wHead = wmsCreCreditHeadDao.get(Integer.valueOf(wDebtStatusVO.getWms_cre_credit_head_id()));
            if (wHead != null)
            {
                wDebtStatusVO.setBill_status(wHead.getBill_status());
            }

        }
        // 贷款申请
        if ("DKSQ".equals(wDebtStatusVO.getDebtkey()))
        {
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_DKSQ);// 审批节点
            approvalInfo.setApproval_type("1");// 审批类型 :1正常审批2退件审批3作废审批4签订合同放款申请
            wmsCreCreditHead.setBill_status("B");// 待初评审批
        }
        // 房产初评
        else if ("FCCP".equals(wDebtStatusVO.getDebtkey()))
        {
            // 判断状态是否一致
            if (!"B".equals(wDebtStatusVO.getBill_status()))
            {
                // 状态异常
                retMap.put("result", "statusError");
                return retMap;
            }
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_FCCP);// 审批节点
            // 通过
            if ("true".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("1");// 审批类型 :1正常审批2退件审批3作废审批
                // 初评通过如果金额大于0则需要走缴费否则跳过
                if (wDebtStatusVO.getCheck_pay().compareTo(new BigDecimal(0)) > 0)
                {
                    wmsCreCreditHead.setBill_status("C");// 核查缴费
                }
                else
                {
                    wmsCreCreditHead.setBill_status("D");// 房产核查
                }
            }
            // 拒件
            else if ("false".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("3");// 审批类型 :1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("BJ");// 初评拒贷
                nullify_flag = true;
            }
            // 退件
            else if ("back".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("2");// 审批类型 :1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("BT");// 初评退回
            }
            // 直接验房
            else if ("direct".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("1");// 审批类型 :1正常审批2退件审批3作废审批
                // 初评直接验房如果金额大于0则需要走缴费否则跳过
                if (wDebtStatusVO.getCheck_pay().compareTo(new BigDecimal(0)) > 0)
                {
                    wmsCreCreditHead.setBill_status("C");// 核查缴费
                }
                else
                {
                    wmsCreCreditHead.setBill_status("D");// 房产核查
                }
            }
            // 直接终审
            else if ("directappl".equals(wDebtStatusVO.getPass()))
            {

                // 判断是否重新核查 如果否 则直接终审
                if (wDebtStatusVO.getIs_again_appl() != null && "0".equals(wDebtStatusVO.getIs_again_appl()))
                {
                    approvalInfo.setApproval_type("1");// 审批类型 :1正常审批2退件审批3作废审批
                    wmsCreCreditHead.setBill_status("E");// 终审审批
                }
                else
                {
                    approvalInfo.setApproval_type("1");// 审批类型 :1正常审批2退件审批3作废审批
                    // 初评直接终审如果金额大于0则需要走缴费否则跳过
                    if (wDebtStatusVO.getCheck_pay().compareTo(new BigDecimal(0)) > 0)
                    {
                        wmsCreCreditHead.setBill_status("C");// 核查缴费
                    }
                    else
                    {
                        wmsCreCreditHead.setBill_status("D");// 房产核查
                    }
                    wDebtStatusVO.setPass("once");// 曾经终审--显示为通过
                }
            }
        }
        // 核查缴费
        else if ("HCJF".equals(wDebtStatusVO.getDebtkey()))
        {
            // 判断状态是否一致
            if (!"C".equals(wDebtStatusVO.getBill_status()))
            {
                // 状态异常
                retMap.put("result", "statusError");
                return retMap;
            }
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_HCJF);// 审批节点

            // 通过
            if ("true".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("1");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("D");// 房产核查
            }
            // 作废
            else if ("nullify".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("3");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("CZ");// 核查缴费作废
                nullify_flag = true;
            }
        }
        // 房产核查
        else if ("FCHC".equals(wDebtStatusVO.getDebtkey()))
        {
            // 判断状态是否一致
            if (!"D".equals(wDebtStatusVO.getBill_status()))
            {
                // 状态异常
                retMap.put("result", "statusError");
                return retMap;
            }
            // 传递值转换下
            if ("true".equals(wDebtStatusVO.getPass()))
            {
                wDebtStatusVO.setPass("accord");
            }
            else if ("false".equals(wDebtStatusVO.getPass()))
            {
                wDebtStatusVO.setPass("notaccord");
            }
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_FCHC);// 审批节点
            // 通过
            if ("accord".equals(wDebtStatusVO.getPass()) || "notaccord".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("1");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("E");// 终审审批
            }
            // 作废
            else if ("nullify".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("3");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("DZ");// 房产核查作废
                nullify_flag = true;
            }
        }
        // 终审审批
        else if ("ZSSP".equals(wDebtStatusVO.getDebtkey()))
        {
            // 判断状态是否一致 E待终审审批 EH终审恢复
            if (!"E".equals(wDebtStatusVO.getBill_status()) && !"EH".equals(wDebtStatusVO.getBill_status()) && !"EZ".equals(wDebtStatusVO.getBill_status()))
            {
                // 状态异常
                retMap.put("result", "statusError");
                return retMap;
            }
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_ZSSP);// 审批节点
            // 通过
            if ("true".equals(wDebtStatusVO.getPass()))
            {

                approvalInfo.setApproval_type("1");// 审批类型1正常审批2退件审批3作废审批
                // 是否面签
                if ("1".equals(wDebtStatusVO.getIs_visa()))
                {
                    wmsCreCreditHead.setBill_status("F");// 面签
                }
                else
                {
                    Map<String, Object> map = new HashMap<>();
                    map.put("wms_cre_credit_head_id", wDebtStatusVO.getWms_cre_credit_head_id());
                    map.put("is_major", "1");
                    // 联系人信息表
                    List<WmsCreCreditLineCustomerChangeHead> list = wmsCreCreditLineCustomerChangeHeadDao.getInfoByHeadId(map);
                    // 判断是否完善完成客户联系人信息
                    if (list != null && list.size() > 0 && "1".equals(list.get(0).getIs_finish()))
                    {
                        wmsCreCreditHead.setBill_status("H");// 合同签订
                    }
                    else
                    {
                        wmsCreCreditHead.setBill_status("G");// 完善信息
                    }
                }
            }
            // 作废
            else if ("false".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("3");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("EJ");// 终审作废
                nullify_flag = true;
            }
            // 作废
            else if ("nullify".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("3");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("EZ");// 房产终审作废
                nullify_flag = true;
            }
            // 退件
            else if ("back".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("2");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("ET");// 房产终审退件
            }
            // 终审恢复
            else if ("recovery".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("1");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("EH");// 房产终审恢复
            }
        }
        // 面签审批
        else if ("MSSP".equals(wDebtStatusVO.getDebtkey()))
        {
            // 判断状态是否一致
            if (!"F".equals(wDebtStatusVO.getBill_status()))
            {
                // 状态异常
                retMap.put("result", "statusError");
                return retMap;
            }
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_MSSP);// 审批节点
            // 通过
            if ("true".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("1");// 审批类型1正常审批2退件审批3作废审批
                Map<String, Object> map = new HashMap<>();
                map.put("wms_cre_credit_head_id", wDebtStatusVO.getWms_cre_credit_head_id());
                map.put("is_major", "1");
                List<WmsCreCreditLineCustomerChangeHead> list = wmsCreCreditLineCustomerChangeHeadDao.getInfoByHeadId(map);
                // 判断是否完善完成客户联系人信息
                if (list != null && list.size() > 0 && "1".equals(list.get(0).getIs_finish()))
                {
                    wmsCreCreditHead.setBill_status("H");// 合同签订
                }
                else
                {
                    wmsCreCreditHead.setBill_status("G");// 完善信息
                }
            }
            // 作废
            else if ("false".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("3");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("FZ");// 面签拒贷
                nullify_flag = true;
            }
        }
        // 完善信息
        else if ("WSXX".equals(wDebtStatusVO.getDebtkey()))
        {
            // 判断状态是否一致
            if (!"G".equals(wDebtStatusVO.getBill_status()) && !"HT".equals(wDebtStatusVO.getBill_status()))
            {
                // 状态异常
                retMap.put("result", "success");
            }
            else
            {
                // 通过-完善
                if ("perfect".equals(wDebtStatusVO.getPass()))
                {
                    approvalInfo.setApproval_type("1");// 审批类型1正常审批2退件审批3作废审批
                    wmsCreCreditHead.setBill_status("H");// 签订合同
                }
            }
            approvalInfo.setApproval_type("1");// 审批类型1正常审批2退件审批3作废审批
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_WSXX);// 审批节点

        }
        // 签订合同
        else if ("QDHT".equals(wDebtStatusVO.getDebtkey()))
        {
            // 判断状态是否一致
            if ("I".equals(wDebtStatusVO.getBill_status()) && "true".equals(wDebtStatusVO.getPass()))
            {
                // 签订合同如果状态是签订完成则返回成功
                retMap.put("result", "success");
                return retMap;
            }
            // 判断状态是否一致
            if (!"H".equals(wDebtStatusVO.getBill_status()) && !"KT".equals(wDebtStatusVO.getBill_status()) && !"I".equals(wDebtStatusVO.getBill_status()))
            {
                // 状态异常
                retMap.put("result", "statusError");
                return retMap;
            }
            
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_QDHT);// 审批节点
            // 通过
            if ("true".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("1");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("I");// 签订合同
            }
            // 作废
            else if ("nullify".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("3");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("HZ");// 合同作废
                nullify_flag = true;
            }
            // 退回
            else if ("back".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("2");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("HT");// 合同退件
            }
        }
        // 放款申请
        else if ("FKSQ".equals(wDebtStatusVO.getDebtkey()))
        {
            // 判断状态是否一致
            if (!"I".equals(wDebtStatusVO.getBill_status()))
            {
                // 状态异常
                retMap.put("result", "statusError");
                return retMap;
            }
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_FKSQ);// 审批节点
            // 通过
            if ("true".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("1");// 审批类型1正常审批2退件审批3作废审批
                // 单据类型--业务状态： 01 新增、02 展期、03 续期
                if ("02".equals(wHead.getBill_type()))
                {
                    wmsCreCreditHead.setBill_status("W");// 放款审批
                }
                else
                {
                    wmsCreCreditHead.setBill_status("K");// 放款审批
                }
            }
        }
        // 放款审批
        else if ("FKSP".equals(wDebtStatusVO.getDebtkey()))
        {
            // 判断状态是否一致
            if (!"K".equals(wDebtStatusVO.getBill_status()))
            {
                // 状态异常
                retMap.put("result", "statusError");
                return retMap;
            }
            // 如果是fasle 需要转换一下
            if ("false".equals(wDebtStatusVO.getPass()))
            {
                wDebtStatusVO.setPass("back");
            }
            approvalInfo.setApproval_task_type(WorkflowConstantHelp.STRHOUSINGLOANNODE_FKSP);// 审批节点
            // 通过
            if ("true".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("1");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("W");// 放款完成
            }
            // 作废
            else if ("nullify".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("3");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("KZ");// 放款作废
                nullify_flag = true;
            }
            // 退回
            else if ("back".equals(wDebtStatusVO.getPass()))
            {
                approvalInfo.setApproval_type("2");// 审批类型1正常审批2退件审批3作废审批
                wmsCreCreditHead.setBill_status("KT");// 放款退回
            }
        }
        // 当前时间
        java.sql.Timestamp nowDate = new java.sql.Timestamp(System.currentTimeMillis());
        wmsCreCreditHead.setLast_update_timestamp(nowDate);

        // 审批表
        approvalInfo.setWms_cre_credit_head_id(Integer.valueOf(wDebtStatusVO.getWms_cre_credit_head_id()));// 贷款主表id
        approvalInfo.setApproval_advice(wDebtStatusVO.getAdvice());// 审批意见
        approvalInfo.setApproval_result(wDebtStatusVO.getPass());// 审批结果
                                                                 // 流程中传递pass的值
        approvalInfo.setApproval_user_id(Integer.valueOf(wDebtStatusVO.getUserId()));// 审批人id
        approvalInfo.setApproval_time(nowDate);// 审批时间
        approvalInfo.setApproval_task_code(wDebtStatusVO.getDebtkey());// 审批编码
        // 放款申请不保存审批信息
        if ("FKSQ".equals(wDebtStatusVO.getDebtkey()))
        {
            approvalInfo.setEnable_flag("0");
        }
        else
        {
            approvalInfo.setEnable_flag("1");
        }
        wmsCreHousingApprovalInfoDao.saveInfo(approvalInfo);

        // 如果是作废的需要更新主表作废字段
        if(nullify_flag) {
            PmPersonnel pmpersonnel = pmpersonnelDao.get(new Integer(wDebtStatusVO.getUserId()));
            wmsCreCreditHead.setNullify_user_id(new Integer(wDebtStatusVO.getUserId()));
            wmsCreCreditHead.setNullify_user_name(pmpersonnel.getPersonnel_name());
            wmsCreCreditHead.setNullify_reason(wDebtStatusVO.getAdvice());
            wmsCreCreditHead.setNullify_type(wDebtStatusVO.getDebtkey());
            wmsCreCreditHead.setNullify_timestamp(nowDate);
            // 作废时同步作废中间表--如果有中间表的情况下
            WmsCreCreditServiceType wmsCreCreditServiceType = new WmsCreCreditServiceType();
            wmsCreCreditServiceType.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id());
            wmsCreCreditServiceType.setEnable_flag("0");
            wmsCreCreditServiceTypeDao.update(wmsCreCreditServiceType);
        }
        // 更改状态
        int num = wmsCreCreditHeadDao.updateforhouse(wmsCreCreditHead);
        // 判断状态是否一致
        if (num == 0)
        {
            // 保存错误
            retMap.put("result", "error");
        }
        else
        {
            retMap.put("result", "success");
        }
        return retMap;
    }
}
