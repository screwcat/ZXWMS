package com.zx.emanage.workflow.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.workflow.util.WorkflowConstantHelp;

/**
 * 会签人员变量处理器
 * 
 * @author Tianyu
 */
@Service
@Transactional
public class SetBeforeHousingLoanApprovalGroupsProcessor implements ExecutionListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception
    {

        List<String> groupIdList = new ArrayList<String>();
        groupIdList.add(WorkflowConstantHelp.DQLSFHY);
        groupIdList.add(WorkflowConstantHelp.DQXXFHY);
        groupIdList.add(WorkflowConstantHelp.DQDSFHY);
        groupIdList.add(WorkflowConstantHelp.DQZXFHY);
        groupIdList.add(WorkflowConstantHelp.DQBJFHY);
        delegateExecution.setVariable("setGroupIds", groupIdList);
    }
}