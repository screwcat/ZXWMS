package com.zx.emanage.workflow.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.workflow.util.WorkflowConstantHelp;

/**
 * 会签人员变量处理器--新房贷流程
 * 
 * @author baisong
 */
@Service
@Transactional
public class SetBeforeHousingApprovalGroupsProcessor implements ExecutionListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception
    {
        List<String> groupIdList = new ArrayList<String>();
        groupIdList.add(WorkflowConstantHelp.HTZY);
        groupIdList.add(WorkflowConstantHelp.HDQBJFHY);
        groupIdList.add(WorkflowConstantHelp.GZFHY);
       
        delegateExecution.setVariable("setGroupIds", groupIdList);
    }
}