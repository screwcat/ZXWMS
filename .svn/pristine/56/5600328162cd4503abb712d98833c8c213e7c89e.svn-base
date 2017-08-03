package com.zx.emanage.workflow.service;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowFormHelp;

/**
 * 赎回流程 实现监听当任意一个领导审批不通过,需要完成修订操作
 * 
 * @author hancd
 */
public class FinancialLDNOApprovalProcessor implements ExecutionListener
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
        // 获取特批申请Task
        Task task = taskQuery.taskDefinitionKey(WorkflowConstantHelp.FINANCIAL_TPSQ).singleResult();
        Map<String, Object> formMap = new HashMap<String, Object>();
        formMap.put(WorkflowFormHelp.FORM_PASS, "noagree");
        formMap.put(WorkflowFormHelp.FORM_ADVICE, "未审批");
        Map<String, String> formMap1 = new HashMap<String, String>();
        formMap1.put(WorkflowFormHelp.FORM_PASS, "noagree");
        formMap1.put(WorkflowFormHelp.FORM_ADVICE, "未审批");
        // 实现把数据保存到流程库
        FormService formService = delegateExecution.getEngineServices().getFormService();
        formService.saveFormData(task.getId(), formMap1);
        // 实现代办任务
        taskService.complete(task.getId(), formMap);
    }

}
