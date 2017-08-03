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
 * 流程变量处理器-新房贷会签方法
 * 
 * @author baisong
 */
@Service
@Transactional
public class BeforeHousingApprovalUp implements TaskListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask)
    {
        // delegateTask.setVariable( "pass", "true" );
    	//String businessKey =delegateTask.getExecution().getBusinessKey();//贷款主表主键
        Set<IdentityLink> identityLinks = delegateTask.getCandidates();
        for (IdentityLink identityLink : identityLinks)
        {
            String groupId = identityLink.getGroupId();

            if (StringUtils.isNotBlank(groupId) && groupId.equals(WorkflowConstantHelp.DQBJFHY))//贷前办件审批
            {
                delegateTask.setName(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_BJSP); 
            }
            if (StringUtils.isNotBlank(groupId) && groupId.equals(WorkflowConstantHelp.DQDSFHY))//贷前电审审批
            {
                delegateTask.setName(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_DSSP); 
            }
            if (StringUtils.isNotBlank(groupId) && groupId.equals(WorkflowConstantHelp.DQZXFHY))//贷前证信审批
            {
                delegateTask.setName(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_ZXSP); 
            }
        }
    }
}