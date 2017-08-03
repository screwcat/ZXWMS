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
 * 会签人员变量处理器
 * 
 * @author Tianyu
 */
@Service
@Transactional
public class SetBeforeCreditApprovalGroupsProcessor implements ExecutionListener
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
        // 任意金额的卓业贷，都需要进行验点环节
        if (delegateExecution.getVariable("cre_loan_type").toString()
                             .equals(WorkflowConstantHelp.CREDITPROCESS_YD_SQ_LOAN_ZYED_TYPE))
        {
            groupIdList.add(WorkflowConstantHelp.DQYDFHY);
        }
        else if (delegateExecution.getVariable("cre_loan_type").toString().equals(WorkflowConstantHelp.CREDITPROCESS_YD_SQ_LOAN_ZYID_TYPE))
        // 区分:当申请金额大于等于20W并且是卓营贷,需要动态添加节点"待前验点复合员"流程角色
        // 修订:当申请金额大于等于10W并且是卓营贷，需要动态添加节点"待前验点复核员"流程角色  时间：2015-06-03
        // 修订:取消对卓营贷金额限制 ，时间:2015-12-14
        {
            groupIdList.add(WorkflowConstantHelp.DQYDFHY);
        }
        delegateExecution.setVariable("setGroupIds", groupIdList);
    }
}