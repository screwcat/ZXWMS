package com.zx.emanage.workflow.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.workflow.util.WorkflowConstantHelp;

/**
 * 流程变量处理器
 * 
 * @author Tianyu
 */
@Service
@Transactional
public class BeforeHousingLoanApprovalStartProcessor implements TaskListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask)
    {
        // delegateTask.setVariable( "pass", "true" );

        Set<IdentityLink> identityLinks = delegateTask.getCandidates();
        for (IdentityLink identityLink : identityLinks)
        {
            String groupId = identityLink.getGroupId();
            if (StringUtils.isNotBlank(groupId) && groupId.equals(WorkflowConstantHelp.DQLSFHY))
            {
                delegateTask.setName(WorkflowConstantHelp.HOUSINGLOANPROCESS_LSSP);
            }
            if (StringUtils.isNotBlank(groupId) && groupId.equals(WorkflowConstantHelp.DQXXFHY))
            {
                delegateTask.setName(WorkflowConstantHelp.HOUSINGLOANPROCESS_XXSP);
            }
            if (StringUtils.isNotBlank(groupId) && groupId.equals(WorkflowConstantHelp.DQDSFHY))
            {
                delegateTask.setName(WorkflowConstantHelp.HOUSINGLOANPROCESS_DSSP);
            }
            if (StringUtils.isNotBlank(groupId) && groupId.equals(WorkflowConstantHelp.DQZXFHY))
            {
                delegateTask.setName(WorkflowConstantHelp.HOUSINGLOANPROCESS_ZXSP);
            }
            if (StringUtils.isNotBlank(groupId) && groupId.equals(WorkflowConstantHelp.DQBJFHY))
            {
                delegateTask.setName(WorkflowConstantHelp.HOUSINGLOANPROCESS_BJSP);
            }
        }
    }
}