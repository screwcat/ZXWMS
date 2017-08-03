package com.zx.emanage.workflow.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.web.multipart.MultipartFile;

import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;

public interface IWorkflowService
{

    /**
     * 启动流程
     * 
     * @param userId 发起人的ID
     * @param businessId业务表单ID
     * @param variables 需要初始化的map
     * @param processDefinitionKey 流程定义的KEY
     */
    public abstract ProcessInstance startWorkflow(String userId, String processDefinitionKey, String businessId,
                                                  Map<String, Object> variables);

    /**
     * 根据查询条件（业务类型（流程定义的KEY），当前登录用户id，节点名字，可以对应单据状态，业务表单的ID，流程实例的ID，流程任务的ID）
     * 查询待办任务
     * 
     * @param WorkflowSearchInfoHelp 工作流查询信息帮助类
     * @return
     */
    public abstract List<Task> findTodoTasks(WorkflowSearchInfoHelp workflowSearchInfoHelp);
    /**
     * 根据查询条件（业务类型（流程定义的KEY），当前登录用户id，节点名字，可以对应单据状态，业务表单的ID，流程实例的ID，流程任务的ID）(当前登录人id可以为空)
     * 查询待办任务
     * 
     * @param WorkflowSearchInfoHelp 工作流查询信息帮助类
     * @return
     */
    public abstract List<Task> findTodoTasksNoUser(WorkflowSearchInfoHelp workflowSearchInfoHelp);

    /**
     * 根据查询条件（业务类型（流程定义的KEY），当前登录用户id，节点名字，可以对应单据状态，业务表单的ID，流程实例的ID，流程任务的ID）
     * 查询待办任务
     * 
     * @param WorkflowSearchInfoHelp 工作流查询信息帮助类
     * @return 记录数量
     */
    public abstract int findTodoTasksCount(WorkflowSearchInfoHelp workflowSearchInfoHelp);

    /**
     * 根据查询条件（业务类型（流程定义的KEY），当前登录用户id，节点名字，可以对应单据状态，业务表单的ID，流程实例的ID，流程任务的ID）
     * 查询待办任务 （分页）
     * 
     * @param WorkflowSearchInfoHelp 工作流查询信息帮助类
     * @param firstResult 第一条数据的索引值，从0开始
     * @param maxResults 结果数量
     * @return
     */
    public abstract List<Task> findTodoTasks(WorkflowSearchInfoHelp workflowSearchInfoHelp, int firstResult,
                                             int maxResults);

    /**
     * 根据查询条件（业务类型（流程定义的KEY），当前登录用户id，节点名字，可以对应单据状态，业务表单的ID，流程实例的ID，流程任务的ID）
     * 查询封装后的待办任务
     * 
     * @param WorkflowSearchInfoHelp 工作流查询信息帮助类
     * @return
     */
    public abstract List<WorkflowInfoHelp> findPackageTodoTasks(WorkflowSearchInfoHelp workflowSearchInfoHelp);
    /**
     * 根据查询条件（业务类型（流程定义的KEY），当前登录用户id，节点名字，可以对应单据状态，业务表单的ID，流程实例的ID，流程任务的ID）(当前登陆人id可以为空)
     * 查询封装后的待办任务
     * 
     * @param WorkflowSearchInfoHelp 工作流查询信息帮助类
     * @return
     */
    public abstract List<WorkflowInfoHelp> findPackageTodoTasksNoUser(WorkflowSearchInfoHelp workflowSearchInfoHelp);
    /**
     * 根据查询条件（业务类型（流程定义的KEY），当前登录用户id，节点名字，可以对应单据状态，业务表单的ID，流程实例的ID，流程任务的ID）
     * 查询封装后的待办任务（分页）
     * 
     * @param WorkflowSearchInfoHelp 工作流查询信息帮助类
     * @param firstResult 第一条数据的索引值，从0开始
     * @param maxResults 结果数量
     * @return
     */
    public abstract List<WorkflowInfoHelp> findPackageTodoTasks(WorkflowSearchInfoHelp workflowSearchInfoHelp,
                                                                int firstResult, int maxResults);

    /**
     * 根据查询条件查询流程历史任务（用于审批历程）
     * 
     * @param workflowSearchInfoHelp 工作流查询信息帮助类
     * @return
     */
    public abstract List<HistoricTaskInstance> findHistoricTaskInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp);

    /**
     * 根据查询条件查询封装后的流程的历史任务（用于审批历程）
     * 
     * @param workflowSearchInfoHelp 工作流查询信息帮助类
     * @return
     */
    public abstract List<WorkflowHistoryInfoHelp> findPackageHistoricTaskInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp);

    /**
     * 根据查询条件查询历史流程（如果设置isUnFinish为真包括正在运行的流程)
     * 
     * @return
     */
    public abstract List<HistoricProcessInstance> findHistoricProcessInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp);

    /**
     * 根据查询条件查询历史流程（如果设置isUnFinish为真包括正在运行的流程)
     * 
     * @return 记录数量
     */
    public abstract int findHistoricProcessInstancesCount(WorkflowSearchInfoHelp workflowSearchInfoHelp);

    /**
     * 根据查询条件查询历史流程（如果设置isUnFinish为真包括正在运行的流程)（分页）
     * 
     * @param WorkflowSearchInfoHelp 工作流查询信息帮助类
     * @param firstResult 第一条数据的索引值，从0开始
     * @param maxResults 结果数量
     * @return
     */
    public abstract List<HistoricProcessInstance> findHistoricProcessInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp,
                                                                               int firstResult, int maxResults);

    /**
     * 根据查询条件查询封装后的流程历史，封装到工作流流程实例信息帮助类WorkflowInfoHelp里
     * 
     * @param processInstanceId 流程实例的ID
     * @return
     */
    public abstract List<WorkflowInfoHelp> findPackageHistoricProcessInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp);

    /**
     * 根据查询条件查询封装后的流程历史，封装到工作流流程实例信息帮助类WorkflowInfoHelp里（分页）
     * 
     * @param WorkflowSearchInfoHelp 工作流查询信息帮助类
     * @param firstResult 第一条数据的索引值，从0开始
     * @param maxResults 结果数量
     * @return
     */
    public abstract List<WorkflowInfoHelp> findPackageHistoricProcessInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp,
                                                                               int firstResult, int maxResults);

    /**
     * 读取启动流程的表单字段
     */
    public abstract Object findStartForm(String processDefinitionId) throws Exception;

    /**
     * 提交启动流程的表单内容并保存表单
     */
    public abstract void submitStartFormAndStartProcessInstance(String userId, String processDefinitionId,
                                                                Map<String, String> formPropertiesMap);

    /**
     * 读取Task的表单字段
     */
    public abstract Object findTaskForm(String taskId) throws Exception;

    /**
     * 提交task表单内容的并保存表单
     */
    public abstract void completeTask(String userId, String taskId, Map<String, String> formPropertiesMap);

    /**
     * 签收任务
     * 
     * @param userId 用户ID
     * @return
     */
    public abstract void claimTask(String userId, String taskId);

    /**
     * 办理任务
     * 
     * @param userId 用户ID
     * @return
     */
    public abstract void completeTask(String userId, String taskId);

    /**
     * 查询流程变量
     * 
     * @param taskId 流程任务ID
     * @param variableName 变量名
     * @return
     */
    public abstract Object findTaskVariable(String taskId, String variableName);

    /**
     * 设置流程变量
     * 
     * @param taskId 流程任务ID
     * @param variableName 变量名
     * @return
     */
    public abstract void setTaskVariable(String taskId, String variableName, Object value);

    /**
     * 设置任务给委派人
     * 
     * @param taskId 流程任务ID
     * @param userId 委派用户的ID
     * @return
     */
    public abstract void delegateTask(String taskId, String userId);

    /**
     * 分页查询全部流程定义对象
     * 
     * @param processDefinitionId 流程定义ID
     * @return
     */
    public abstract long findProcessDefinitionListCount();

    /**
     * 分页查询全部流程定义对象
     * 
     * @param processDefinitionId 流程定义ID
     * @return
     */
    public abstract List<ProcessDefinition> findProcessDefinitionListPage(int firstResult, int maxResults);

    /**
     * 部署流程定义对象
     * 
     * @param file MultipartFile类型
     * @return
     */
    public abstract void deployProcessDefinition(MultipartFile file);
    /**
     * 获取流程基本信息
     * @param taskId
     * @return
     */
    public abstract Task getTaskInfo(String taskId);
    /**
     * 获取流程基本信息
     * @param taskId
     * @return
     */
    public Task getTaskInfobyKey(String businessKey,String taskName,String processDefinitionKey);
    /**
     * @Title: getTaskIdByBusinessId
     * @Description: 根据业务主键ID获取流程ID
     * @param businessKey
     * @param processDefinitionKey
     * @return 
     * @author: Guanxu
     * @time:2016年11月7日 下午1:30:11
     * history:
     * 1、2016年11月7日 Guanxu 创建方法
    */
    String getTaskIdByBusinessId(String businessKey, String processDefinitionKey);

    /**
     * 
     * @Title: getTaskListInfo
     * @Description: TODO(根据业务主键获取task信息 list)
     * @param businessKey
     * @param taskName
     * @param processDefinitionKey
     * @return 
     * @author: baisong
     * @time:2016年12月20日 下午1:30:25
     * history:
     * 1、2016年12月20日 baisong 创建方法
     */
    List<Task> getTaskListInfo(String businessKey, String taskName, String processDefinitionKey);

    /**
     * 
     * @Title: getTaskInfo
     * @Description: TODO(根据业务主键获取taskid)
     * @param businessKey
     * @param taskName
     * @param processDefinitionKey
     * @return 
     * @author: baisong
     * @time:2016年12月19日 下午1:54:29
     * history:
     * 1、2016年12月19日 baisong 创建方法
     */
    public Task getTaskInfo(WorkflowSearchInfoHelp workflowSearchInfoHelp);
}