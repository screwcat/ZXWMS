package com.zx.emanage.cremanage.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;

/**
 * 实现房贷流程
 * 
 * @author hancd
 */
public interface IWmsHouseCreditWorkFlowService
{
    /**
     * 实现房贷申请流程的启动
     * 
     * @param userId 用户ID
     * @param businessId 贷款表单ID
     */
    public abstract void startHouseCreditWorkFlow(String userId, String businessId);

    /**
     * 根据代办任务(userID,taskName,processDefinitionKey)，查询出所有工程流中存在代办taskId
     * 
     * @param userId
     * @param housekey 0: 代表贷款复核 1：代表流水审批 2：代表办件审批 3：代表贷款终审 4：代表签订合同 5：代表公证
     *            6：代表他项 7：代表放款申请 8：代表征信审批 9：代表信息审批 10：代表电审审批 11:代表复核退回
     *            12:代表贷前退回 13:代表待审核
     * @return 返回从流程里面查询出所有Map<"IdList",idList>和Map<"taskIdList",taskIdList>集合
     */
    public abstract Map<String, Object> getIdListWorkFlow(String userId, String housekey);
    /**
     * 根据代办任务(userID,taskName,processDefinitionKey)，查询出所有工程流中存在代办taskId
     * 
     * @param userId  当前方法userid可以为空
     * @param housekey 0: 代表贷款复核 1：代表流水审批 2：代表办件审批 3：代表贷款终审 4：代表签订合同 5：代表公证
     *            6：代表他项 7：代表放款申请 8：代表征信审批 9：代表信息审批 10：代表电审审批 11:代表复核退回
     *            12:代表贷前退回 13:代表待审核
     * @return 返回从流程里面查询出所有Map<"IdList",idList>和Map<"taskIdList",taskIdList>集合
     */
    public abstract Map<String, Object> getIdListWorkFlowNoUser(String userId, String housekey);

    /**
     * 用来实现所有列表显示中获取流程里面taskId 根据查询业务数据库查询结果后，进行列表添加taskId
     * 
     * @param list 查询数据库数据显示list列表数据
     * @param workflowInfoHelps 查询流程后获取的流程信息列表
     */
    public abstract List<Map<String, Object>> addTaskIDHouse(List<Map<String, Object>> list, List<Integer> idLists,
                                                             List<String> taskIdLists);

    /**
     * 用来实现贷前退回时列表数据的获取以及显示
     * 
     * @param list
     * @param idLists
     * @param taskIdLists
     * @param approvesGroups
     * @param approveAdvices
     * @param approveTimes
     * @return
     */
    public abstract List<Map<String, Object>> addTaskIDHouse(List<Map<String, Object>> list, List<Integer> idLists,
                                                             List<String> taskIdLists, List<String> approvesGroups,
                                                             List<String> approveAdvices, List<String> approveTimes);

    /**
     * 实现房贷贷款复核审批
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract void creCheckHouseCreditWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 实现贷前所有退件处理
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract String creCheckHouseSupply(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 流水审批组审批
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract String runTeamHouseCheckApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 信息审批组审批
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract String creInfoTeamHouseCheckApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 电审审批组审批
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract String creTelTeamHouseCheckApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 证信审批组审批
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract String creTeamHouseCheckApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 办件审批组审批
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract String doapprovalHouseWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 放贷贷款终审审批
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract void theMortgageLoanApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 继续房贷待前审核
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract void contiuneMortgageLoanBeforeApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 签订合同 公证 他项 审批过程
     * 
     * @param approveHouseWorkFlowVO
     * @param key 0:代表签订合同 1：代表公证 2：代表他项
     */
    public abstract void theContractOrNotarizationOrOther(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String key);

    /**
     * 房贷放款申请
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract void theMortgageLoanWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 房贷放款申请审批
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract void theMortgageLoanApprovelWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 放款确认审批
     * 
     * @param approveHouseWorkFlowVO
     */
    public abstract void mortgageLoanExaminationAndApproval(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);

    /**
     * 获取信贷贷款确认和房贷贷款确认审核存放在流程中的主表单ID并添加到idList以Map形式返回
     * 
     * @param userId 当前审批人ID
     */
    public abstract Map<String, Object> getCreditOrMortgageIdListWorkFlow(String userId);

    /**
     * 根据idList查询数据库获取的数据并且为每一个数据添加taskId
     * 
     * @param list 查询后返回的结果
     * @return 返回添加taskId列表数据
     */
    public abstract List<Map<String, Object>> addTaskIDCreditOrHouse(List<Map<String, Object>> list,
                                                                     List<Integer> idLists, List<String> taskIdLists);

    /**
     * 流程历程显示
     * 
     * @param wms_cre_credit_head_id 贷款表单ID
     */
    public abstract Map<String, Object> houseCreditWorkFlowView(String wms_cre_credit_head_id);

    /**
     * 实现房贷贷款管理-贷款查询 针对房贷单据 含有草稿，复核退回，补件中进行集中处理
     * 
     * @param wms_cre_credit_head_id 贷款表单据主键ID
     * @param userId 代表审批人ID
     * @param key 标识 1代表草稿 2代表复核退回 3代表补件中
     */
    public void houseCreditInquiryTreatment(String userId, String wms_cre_credit_head_id, String key);

    /**
     * 实现房贷 电审 流水  信调 忽略功能  
     * 原理：让单据流程流转，不走业务单据
     * @param approveHouseWorkFlowVO
     * @param 
     * @author hancd
     */
    public void houseCreditApprovalIgonre(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO);
}
