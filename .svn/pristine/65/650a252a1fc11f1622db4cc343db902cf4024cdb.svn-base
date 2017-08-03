package com.zx.emanage.cremanage.service;

/**
 * 版权所有 ：版权所有(C) 2014，卓信财富
 * 文件名称 : ApproveWorkFlowService.Java
 * 系统名称 ：WMS
 * 模块名称 ：信贷流程接口
 * 完成日期 ：2014-05-12 
 * 作    者    ：HANCD
 * 内容摘要 ：主要实现信贷流程接口的定义，支撑信贷流程的中所有审批的环节处理与请求。  
 * 
 * 文件调用 ：调用该类时要声明在应用类中的声明此类。在service类中调用。
 * 修改记录 ：
 * 修改时间 ：
 * 修 改 人   ：
 * 修改内容 ：
 * 关联BUG ：
 * 修改方法：
 */
import java.util.List;
import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;

/**
 * 实现信贷流程和房贷流程接口
 * 
 * @author hancd
 * @version 1.2
 */
public interface IWmsCreditWorkFlowService
{
    /**
     * 实现信贷开启流程
     * 
     * @param userId 当前登录人ID
     * @param businessId 单据业务主键
     */
    public void startWorkFlow(String userId, String businessId);

    /**
     * 实现信贷二次开启功能 主要实现终审可以把已终止状态单据二次开启流程
     * 
     * @param userId 当前登录人ID
     * @param businessId 单据业务主键
     * @param startUserId 单据原始开启人ID
     */
    public void startSecondaryWorkFlow(String userId, String businessId, String startUserId);

    /**
     * 信贷-列表显示 根据应有条件查询相应的数据列表获得id以及taskId
     * 
     * @param 用户ID
     * @param creditKey 用于区分节点的key creditKey=12代表信贷复核退回 creditKey=13代表信贷贷前退回
     *            creditKey=15代表信贷复议修订
     */
    public Map<String, Object> getIdTaskIdCreditList(String useId, String creditKey);

    /**
     * 信贷-添加列表taskID 实现为列表显示的每条数据添加流程节点taskID
     * 
     * @param approvesGroups
     * @param approveAdvices
     * @param approveTimes
     */
    public List<Map<String, Object>> addTaskCredit(List<Map<String, Object>> list, List<Integer> idLists,
                                                   List<String> taskIdLists, List<String> approvesGroups,
                                                   List<String> approveAdvices, List<String> approveTimes);

    /**
     * 信贷-添加列表taskID 实现为列表显示的每条数据添加流程节点taskID
     */
    public List<Map<String, Object>> addTaskCredit(List<Map<String, Object>> list, List<Integer> idLists,
                                                   List<String> taskIdLists);

    /**
     * 实现复议修订页面显示
     * 
     * @param list
     * @param idLists
     * @param taskIdLists
     * @param approvesGroups
     * @return
     */
    public List<Map<String, Object>> addTaskCredit(List<Map<String, Object>> list, List<Integer> idLists,
                                                   List<String> taskIdLists, List<String> approvesGroups);

    /**
     * 信贷-贷前处理信贷所有退件处理 只需要提供：userID,taskID,advice
     */
    public String creCheckCreditSupply(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 复核审批
     * 
     * @param approveWorkFlowVO
     */
    public void creCheckCreditApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 流水组审批
     * 
     * @param approveWorkFlowVO
     */
    public String runTeamCheckApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 信息组审批
     * 
     * @param approveWorkFlowVO
     */
    public String infoTeamCheckApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 电审组审
     * 
     * @param approveWorkFlowVO
     */
    public String telTeamCheckApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 征信组审
     * 
     * @param approveWorkFlowVO
     */
    public String creTeamCheckApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 验点组审批
     * 
     * @param approveWorkFlowVO
     */
    public String ydTeamCheckApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 终审审批
     * 
     * @param approveWorkFlowVO
     */
    public void creCheckloanApproApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 实现继续贷前审批
     * 
     * @param approveWorkFlowVO
     */
    public void contiuneCreditAprove(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 终审面签
     * 
     * @param approveWorkFlowVO
     */
    public void creCheckloanApproInterView(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 判断信贷面签环节是否是新流程还是旧流程
     * 
     * @param approveWorkFlowVO
     */
    public String BooleanCheckloanApproInterView(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 合同签订
     * 
     * @param approveWorkFlowVO
     */
    public void creCheckConcludeAndSignApprove(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 实现信贷流程查看
     * 
     * @param approveWorkFlowVO
     */
    public Map<String, Object> checkApprovalProcess(Integer wms_cre_credit_head_id);

    /**
     * 实现放款申请
     * 
     * @param approveWorkFlowVO
     */
    public void makeLoansAprove(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 实现放款申请审批
     */
    public void makeLoansExaminationAndApproval(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 实现放款确认
     * 
     * @param approveWorkFlowVO
     */
    public void makeLoansaffirm(WmsCreditWorkFlowVO approveWorkFlowVO);

    /**
     * 实现信贷贷款管理-贷款查询 针对信贷单据 含有草稿，复核退回，补件中，复议修订进行集中处理
     * 
     * @param wms_cre_credit_head_id 贷款表单据主键ID
     * @param credit_limit 用户申请金额
     * @param cre_loan_type 用户贷款产品类型
     * @param userId 代表审批人ID
     * @param key 标识 1代表草稿 2代表复核退回 3代表补件中 4代表复议修订
     */
    public void creditInquiryTreatment(WmsCreditWorkFlowVO aWorkFlowVO, String key);

    /**
     * 实现初审面签审批流转 主要：当初审面签审核人不进行实地验点 流程自动把这条单进行流转
     */
    public String loanApproInitialInterWorkflow(WmsCreditWorkFlowVO aWorkFlowVO);
}
