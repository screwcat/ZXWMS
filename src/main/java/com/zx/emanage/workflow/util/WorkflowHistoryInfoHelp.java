package com.zx.emanage.workflow.util;

/**
 * 工作流流程历史信息帮助类
 * 
 * @author Tianyu
 */
public class WorkflowHistoryInfoHelp
{

    // 流程实例的ID
    private String processInstanceId;

    // 流程任务的ID
    private String taskId;

    // 业务表单的ID
    private String businessKey;

    // // 发起人的ID,可以对应录入人的ID
    // private String startUserId;
    // // 发起人的Name,可以对应录入人的名字
    // private String startUser;
    // 审批人的ID字符串 用逗号分隔
    private String approverids;

    // 审批人的名字字符串 用逗号分隔
    private String approvers;

    // 审批人的角色
    private String approversGroup;

    // 节点名字，可以对应单据状态
    private String taskName;

    // 审批结果
    private String approveResult;

    // 审批意见
    private String approveAdvice;

    // 审批时间
    private String approveTime;

    // ---------------------------------------------
    private Integer personnel_deptId;// 部门ID

    private String personnel_deptCode;// 部门编号

    private String personnel_deptName;// 部门名称

    private Integer personnel_postId;// 职务ID

    private String personnel_postName;// 职务名称

    private Integer personnel_postLevelId;// 职级ID

    private String approveTimeInterval;

    private Integer approve_status;// 审批标识

    private String personnel_shortCode; // 短工号

    public Integer getPersonnel_deptId()
    {
        return personnel_deptId;
    }

    public void setPersonnel_deptId(Integer personnel_deptId)
    {
        this.personnel_deptId = personnel_deptId;
    }

    public String getPersonnel_deptCode()
    {
        return personnel_deptCode;
    }

    public void setPersonnel_deptCode(String personnel_deptCode)
    {
        this.personnel_deptCode = personnel_deptCode;
    }

    public String getPersonnel_deptName()
    {
        return personnel_deptName;
    }

    public void setPersonnel_deptName(String personnel_deptName)
    {
        this.personnel_deptName = personnel_deptName;
    }

    public Integer getPersonnel_postId()
    {
        return personnel_postId;
    }

    public void setPersonnel_postId(Integer personnel_postId)
    {
        this.personnel_postId = personnel_postId;
    }

    public String getPersonnel_postName()
    {
        return personnel_postName;
    }

    public void setPersonnel_postName(String personnel_postName)
    {
        this.personnel_postName = personnel_postName;
    }

    public Integer getPersonnel_postLevelId()
    {
        return personnel_postLevelId;
    }

    public void setPersonnel_postLevelId(Integer personnel_postLevelId)
    {
        this.personnel_postLevelId = personnel_postLevelId;
    }

    public String getApproveTime()
    {

        return approveTime;
    }

    public void setApproveTime(String approveTime)
    {

        this.approveTime = approveTime;
    }

    public String getApproversGroup()
    {

        return approversGroup;
    }

    public void setApproversGroup(String approversGroup)
    {

        this.approversGroup = approversGroup;
    }

    public String getApproveResult()
    {

        return approveResult;
    }

    public void setApproveResult(String approveResult)
    {

        this.approveResult = approveResult;
    }

    public String getApproveAdvice()
    {

        return approveAdvice;
    }

    public void setApproveAdvice(String approveAdvice)
    {

        this.approveAdvice = approveAdvice;
    }

    // public String getStartUser() {
    //
    // return startUser;
    // }
    //
    //
    // public void setStartUser( String startUser ) {
    //
    // this.startUser = startUser;
    // }

    public String getApproverids()
    {

        return approverids;
    }

    public void setApproverids(String approverids)
    {

        this.approverids = approverids;
    }

    public String getProcessInstanceId()
    {

        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId)
    {

        this.processInstanceId = processInstanceId;
    }

    public String getTaskId()
    {

        return taskId;
    }

    public void setTaskId(String taskId)
    {

        this.taskId = taskId;
    }

    public String getBusinessKey()
    {

        return businessKey;
    }

    public void setBusinessKey(String businessKey)
    {

        this.businessKey = businessKey;
    }

    public String getApprovers()
    {

        return approvers;
    }

    public void setApprovers(String approvers)
    {

        this.approvers = approvers;
    }

    // public String getStartUserId() {
    //
    // return startUserId;
    // }
    //
    //
    // public void setStartUserId( String startUserId ) {
    //
    // this.startUserId = startUserId;
    // }

    public String getTaskName()
    {

        return taskName;
    }

    public void setTaskName(String taskName)
    {

        this.taskName = taskName;
    }

    public String getApproveTimeInterval()
    {
        return approveTimeInterval;
    }

    public void setApproveTimeInterval(String approveTimeInterval)
    {
        this.approveTimeInterval = approveTimeInterval;
    }

    public Integer getApprove_status()
    {
        return approve_status;
    }

    public void setApprove_status(Integer approve_status)
    {
        this.approve_status = approve_status;
    }

    public String getPersonnel_shortCode()
    {
        return personnel_shortCode;
    }

    public void setPersonnel_shortCode(String personnel_shortCode)
    {
        this.personnel_shortCode = personnel_shortCode;
    }

}
