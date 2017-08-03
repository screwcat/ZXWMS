package com.zx.emanage.workflow.util;

/**
 * 工作流查询信息帮助类
 * 
 * @author Tianyu
 */
public class WorkflowSearchInfoHelp
{

    // 业务类型（流程定义的KEY）必须
    private String processDefinitionKey;

    // 流程实例的ID
    private String processInstanceId;

    // 流程任务的ID
    private String taskId;

    // 当前登录用户id (查询是否自己参与） 必须
    private String userId;

    // 业务表单的ID
    private String businessKey;

    // 发起人的ID,可以对应录入人的ID
    private String startUserId;

    // 申请（发起流程）时间（觉得不应该和流程耦合，暂时未对本查询条件进行支持）
    private String startTime;

    // 节点名字，可以对应单据状态
    private String taskName;

    // 是否完成
    private boolean isFinish;

    // 是否未完成
    private boolean isUnFinish;

    public String getStartUserId()
    {
        return startUserId;
    }

    public void setStartUserId(String startUserId)
    {
        this.startUserId = startUserId;
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

    public boolean isFinish()
    {

        return isFinish;
    }

    public void setFinish(boolean isFinish)
    {

        this.isFinish = isFinish;
    }

    public boolean isUnFinish()
    {

        return isUnFinish;
    }

    public void setUnFinish(boolean isUnFinish)
    {

        this.isUnFinish = isUnFinish;
    }

    public String getProcessDefinitionKey()
    {

        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey)
    {

        this.processDefinitionKey = processDefinitionKey;
    }

    public String getUserId()
    {

        return userId;
    }

    public void setUserId(String userId)
    {

        this.userId = userId;
    }

    public String getBusinessKey()
    {

        return businessKey;
    }

    public void setBusinessKey(String businessKey)
    {

        this.businessKey = businessKey;
    }

    public String getStartTime()
    {

        return startTime;
    }

    public void setStartTime(String startTime)
    {

        this.startTime = startTime;
    }

    public String getTaskName()
    {

        return taskName;
    }

    public void setTaskName(String taskName)
    {

        this.taskName = taskName;
    }

}
