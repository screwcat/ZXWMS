package com.zx.emanage.workflow.service;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程变量处理器 记录流水、办件、证信3个审批环节的流程变量 , 便于终审环节继续审批操作
 * 
 * @author Tianyu
 */
@Service
@Transactional
public class HousingLoanApprovalStartProcessor implements TaskListener
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