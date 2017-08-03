package com.zx.emanage.cremanage.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;

/**
 * 实现车贷流程接口
 * 
 * @author hancd
 *
 */
public interface IWmsCarLoanWorkFlowService{
	
	/**
	 * 车贷流程启动
	 * @param userId 当前审核人ID
	 * @param businessId 流程通用ID=业务单据主键
	 */
	public abstract void startWorkFlow(String userId,String businessId);
	
	/**
	 * 车贷流程节点列表显示的数据获取对应流程中的ID列表
	 * @param userId 当前审核人ID
	 * @param carkey 流程节点钥匙
	 * carkey: 1:车贷复核  2:复核退回  3:评估审核  4:办件审核  5:贷前退回 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
	 */
	public abstract Map<String,Object> getIdListWorkFlow(String userId, String carkey);
	
	/**
	 * 车贷流程节点列表中对每条数据追加流程任务taskId
	 * @param list
	 * @param idLists
	 * @param taskIdLists
	 */
	public abstract List<Map<String,Object>> setWorkFlowTaskID(List<Map<String, Object>> list, List<Integer> idLists,
            List<String> taskIdLists,List<String> approvesGroups,
            List<String> approveAdvices,
            List<String> approveTimes);
	
	/**
	 * 车贷流程历程显示
	 * @param businessId 业务主键
	 */
	public abstract Map<String,Object> getcarLoanWorkFlowView(String businessId);
		
	/**
	 * 车贷各个审批环节审批公共方法
	 * @param wVo 审批提交给流程的公共参数
	 * @param carKey 车贷环节审批KEY
	 * carkey: 1:车贷复核  2:复核退回  3:评估审核  4:办件审核  5:贷前退回 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
	 */
	public abstract String carLoanApprovalProcess(WmsCarLoanWorkFlowVO wVo,String carKey);
}
