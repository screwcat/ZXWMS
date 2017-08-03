package com.zx.emanage.workflow.service;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowFormHelp;

/**
 * 赎回流程 实现当经理审批通过后同时让处在“特批申请”的流程状态系统自动审批完成
 * 
 * @author hancd
 */
public class FinancialZJLYESApprovalProcessor implements TaskListener
{

    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask)
    {
        String processDefinitionId = delegateTask.getProcessDefinitionId();
        String processInstanceId = delegateTask.getProcessInstanceId();
        TaskService taskService = delegateTask.getExecution().getEngineServices().getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionId(processDefinitionId)
                                         .processInstanceId(processInstanceId);
        String pass = (String) delegateTask.getVariable(WorkflowFormHelp.FORM_PASS);
        String tppass = (String) delegateTask.getVariable("tppass");
        // 处于特批操作,正常结束pass=true
        if ("true".equals(pass) && tppass != null && tppass.equals("TP"))
        {

        }
        else if ("true".equals(pass))
        {// 处于正常审批，需要结束特批
            // 获取特批申请Task
            Task task = taskQuery.taskDefinitionKey(WorkflowConstantHelp.FINANCIAL_TPSQ).singleResult();
            // 动态让tpsq审批
            Map<String, Object> formMap = new HashMap<String, Object>();
            formMap.put(WorkflowFormHelp.FORM_PASS, "true");
            formMap.put(WorkflowFormHelp.FORM_ADVICE, "未审批");
            Map<String, String> formMap1 = new HashMap<String, String>();
            formMap1.put(WorkflowFormHelp.FORM_PASS, "true");
            formMap1.put(WorkflowFormHelp.FORM_ADVICE, "未审批");
            // 实现把数据保存到流程库
            FormService formService = delegateTask.getExecution().getEngineServices().getFormService();
            formService.saveFormData(task.getId(), formMap1);
            // 实现代办任务
            taskService.complete(task.getId(), formMap);
        }

    }
}
