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
public class ApprovalStartProcessor implements TaskListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask)
    {
        String currentTaskDefinitionKey = delegateTask.getTaskDefinitionKey();
        String sppass = currentTaskDefinitionKey + "pass";
        String spcontinue = (String) delegateTask.getVariable(currentTaskDefinitionKey + "continue");
        if (StringUtils.isNotBlank(spcontinue) && spcontinue.equals("true"))
        {
            // 重新初始化sppass
            delegateTask.setVariable(sppass, "false");
            // 重新初始化iscontinueenable
            delegateTask.setVariable("iscontinueenable", "false");
            // 重新初始化iscontinueenable
            delegateTask.setVariable("iscontinue", "false");
        }
    }
}