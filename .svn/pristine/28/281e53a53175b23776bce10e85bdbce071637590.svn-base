package com.zx.emanage.workflow.service;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;

import com.zx.emanage.workflow.service.impl.WorkflowServiceImpl;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowFormHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;

/**
 * 赎回流程 联动特批申请操作 实现经理审批zjlsp系统自动完成审批操作
 * 
 * @author Hancd
 */
public class FinancialZJLApprovalProcessor implements TaskListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask)
    {
        TaskService taskService = delegateTask.getExecution().getEngineServices().getTaskService();
        // 接收特批申请监听传递的tp参数，判断是否执行系统自动执行
        if (delegateTask.getVariable("tppass") != null && delegateTask.getVariable("tppass").toString().equals("TP"))
        {
            if (StringUtils.isEmpty(delegateTask.getAssignee()))
            {
                Map<String, Object> formMap = new HashMap<String, Object>();
                formMap.put(WorkflowFormHelp.FORM_PASS, "true");
                formMap.put(WorkflowFormHelp.FORM_ADVICE, "未审批");
                Map<String, String> formMap1 = new HashMap<String, String>();
                formMap1.put(WorkflowFormHelp.FORM_PASS, "true");
                formMap1.put(WorkflowFormHelp.FORM_ADVICE, "未审批");
                // 实现把数据保存到流程库
                FormService formService = delegateTask.getExecution().getEngineServices().getFormService();
                formService.saveFormData(delegateTask.getId(), formMap1);
                taskService.setVariable(delegateTask.getId(), "zjlsppass", "system");
                // 实现代办任务
                taskService.complete(delegateTask.getId(), formMap);
            }
        }
    }
}
