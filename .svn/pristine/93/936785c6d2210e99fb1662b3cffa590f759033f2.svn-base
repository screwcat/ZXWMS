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
public class SetBeforeHousingApprovalUp implements ExecutionListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception
    {
    	//String businessKey=delegateExecution.getBusinessKey();//贷款主表id
    	
        List<String> groupIdList = new ArrayList<String>();
       
        groupIdList.add(WorkflowConstantHelp.DQBJFHY);
        //判断是否需要电审核证信审核  如果点击授信确认则需要      授信确认 credit_confirm   1：普通贷（默认值）0：授信贷
        if (delegateExecution!=null&&delegateExecution.getVariable("credit_confirm_result")!=null
        		&&delegateExecution.getVariable("credit_confirm_result").toString().equals(WorkflowConstantHelp.UPHOUSINGLOANPROCESS_CREDIT_CONFIRM))
        {
        	 groupIdList.add(WorkflowConstantHelp.DQDSFHY);
             groupIdList.add(WorkflowConstantHelp.DQZXFHY);
        }
        delegateExecution.setVariable("setGroupIds", groupIdList);
    }
}