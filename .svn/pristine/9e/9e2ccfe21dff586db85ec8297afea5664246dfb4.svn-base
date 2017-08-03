package com.zx.emanage.workflow.service;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.workflow.util.WorkflowFormHelp;

/**
 * 流程变量处理器 记录流水、信息、电审、证信4个审批环节的流程变量 , 便于终审环节继续审批操作
 * 
 * @author Tianyu
 */
@Service
@Transactional
public class ApprovalEndProcessor implements TaskListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask)
    {

        String spNames[] = { "lssp", "xxsp", "dssp", "zxsp" };
        String pass = (String) delegateTask.getVariable(WorkflowFormHelp.FORM_PASS);
        String currentTaskDefinitionKey = delegateTask.getTaskDefinitionKey();
        if ("false".equals(pass))
        {

            String sppass = (String) delegateTask.getVariable(currentTaskDefinitionKey + "pass");
            // 不是系统自动完成的当前审批
            if (!(StringUtils.isNotBlank(sppass) && sppass.equals("system")))
            {
                delegateTask.setVariable(delegateTask.getTaskDefinitionKey() + "pass", "false");
                // 查看其他审批环节哪个任务没开始办理
                // 找到当前是哪个审批
                int indexFlag = -1;
                for (int i = 0; i < spNames.length; i++)
                {
                    if (spNames[i].equals(currentTaskDefinitionKey))
                    {
                        indexFlag = i;
                        break;
                    }
                }
                // 处理其他审批
                for (int i = 0; i < spNames.length; i++)
                {
                    if (indexFlag == i)
                    {
                        continue;
                    }
                    // 其他环节任务
                    String processDefinitionId = delegateTask.getProcessDefinitionId();
                    String processInstanceId = delegateTask.getProcessInstanceId();
                    TaskService taskService = delegateTask.getExecution().getEngineServices().getTaskService();
                    TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionId(processDefinitionId)
                                                     .processInstanceId(processInstanceId);
                    Task task = taskQuery.taskDefinitionKey(spNames[i]).singleResult();
                    // 其他环节已经办理的,已经成为历史任务,PASS
                    if (null != task)
                    {
                        // 任务还没有分派给人，还未签收或者办理
                        if (StringUtils.isEmpty(task.getAssignee()))
                        {
                            // 填写审批表单数据，假完成
                            Map<String, Object> formMap = new HashMap<String, Object>();
                            formMap.put(WorkflowFormHelp.FORM_PASS, "false");
                            // formMap.put( WorkflowFormHelp.FORM_ADVICE,
                            // delegateTask.getName()+"环节没通过，在"+task.getName()+"环节审批人没拿到任务的时候，系统自动完成！"
                            // );
                            formMap.put(WorkflowFormHelp.FORM_ADVICE, "未审批");
                            // taskService.setVariable( task.getId(),
                            // spNames[i]+"pass", "system" );
                            // taskService.complete( task.getId(),formMap );

                            Map<String, String> formMap1 = new HashMap<String, String>();
                            formMap1.put(WorkflowFormHelp.FORM_PASS, "false");
                            // formMap1.put( WorkflowFormHelp.FORM_ADVICE,
                            // delegateTask.getName()+"环节没通过，在"+task.getName()+"环节审批人没拿到任务的时候，系统自动完成！"
                            // );
                            formMap1.put(WorkflowFormHelp.FORM_ADVICE, "未审批");
                            FormService formService = delegateTask.getExecution().getEngineServices().getFormService();
                            formService.saveFormData(task.getId(), formMap1);
                            taskService.setVariable(task.getId(), spNames[i] + "pass", "system");
                            // taskService.setVariables( task.getId(), formMap
                            // );不起作用，不懂深层ACTIVITI原理
                            taskService.complete(task.getId(), formMap);// 不能直接用taskService.complete(
                                                                        // task.getId())方法
                                                                        // 因为有formkey
                                                                        // 必须用此这个方法，否则无限循环
                        }
                    }
                }
            }
        }
        if ("true".equals(pass))
        {
            delegateTask.setVariable(delegateTask.getTaskDefinitionKey() + "pass", "true");
        }
    }

}
