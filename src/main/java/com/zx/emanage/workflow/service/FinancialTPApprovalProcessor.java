package com.zx.emanage.workflow.service;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import com.zx.emanage.workflow.util.WorkflowFormHelp;

/**
 * 赎回流程 当执行特批申请功能：监听特批申请操作 实现特批操作即系统自动完成审批操作
 * 
 * @author Hancd
 */
public class FinancialTPApprovalProcessor implements ExecutionListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception
    {
        String processDefinitionId = delegateExecution.getProcessDefinitionId();
        String processInstanceId = delegateExecution.getProcessInstanceId();
        TaskService taskService = delegateExecution.getEngineServices().getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionId(processDefinitionId)
                                         .processInstanceId(processInstanceId);
        // 节点名称的定义
        String[] spNames = { "jlsp", "fzjlsp", "zjlsp" };
        String pass = (String) delegateExecution.getVariable(WorkflowFormHelp.FORM_PASS);
        if ("true".equals(pass))
        {// 当pass=true,系统自动完成经理,副总经理,总经理特批.
            for (int i = 0; i < spNames.length; i++)
            {
                Task task = taskQuery.taskDefinitionKey(spNames[i]).singleResult();
                // Task不为空,说明当前领导审批到那个环节
                if (null != task)
                {
                    if (StringUtils.isEmpty(task.getAssignee()))
                    {
                        Map<String, Object> formMap = new HashMap<String, Object>();
                        formMap.put(WorkflowFormHelp.FORM_PASS, "true");
                        formMap.put(WorkflowFormHelp.FORM_ADVICE, "未审批");
                        // 加入tp参数,后面需要用这样参数来判断是否需要进行自动审批操作
                        formMap.put("tppass", "TP");
                        Map<String, String> formMap1 = new HashMap<String, String>();
                        formMap1.put(WorkflowFormHelp.FORM_PASS, "true");
                        formMap1.put(WorkflowFormHelp.FORM_ADVICE, "未审批");
                        // 实现把数据保存到流程库
                        FormService formService = delegateExecution.getEngineServices().getFormService();
                        formService.saveFormData(task.getId(), formMap1);
                        taskService.setVariable(task.getId(), "sppass", "system");
                        // 实现代办任务
                        taskService.complete(task.getId(), formMap);
                    }
                    break;
                }
            }
        }
        else if ("false".equals(pass))
        {// 当pass=false,系统把当前节点任务结束
            for (int i = 0; i < spNames.length; i++)
            {
                Task task = taskQuery.taskDefinitionKey(spNames[i]).singleResult();
                // Task不为空,说明当前领导审批到那个环节
                if (null != task)
                {
                    if (StringUtils.isEmpty(task.getAssignee()))
                    {
                        Map<String, Object> formMap = new HashMap<String, Object>();
                        formMap.put(WorkflowFormHelp.FORM_PASS, "over");
                        formMap.put(WorkflowFormHelp.FORM_ADVICE, "已终止");
                        Map<String, String> formMap1 = new HashMap<String, String>();
                        formMap1.put(WorkflowFormHelp.FORM_PASS, "over");
                        formMap1.put(WorkflowFormHelp.FORM_ADVICE, "已终止");
                        // 实现把数据保存到流程库
                        FormService formService = delegateExecution.getEngineServices().getFormService();
                        formService.saveFormData(task.getId(), formMap1);
                        taskService.setVariable(task.getId(), "sppass", "system");
                        // 实现代办任务
                        taskService.complete(task.getId(), formMap);
                    }
                    break;
                }
            }
        }
    }
}
