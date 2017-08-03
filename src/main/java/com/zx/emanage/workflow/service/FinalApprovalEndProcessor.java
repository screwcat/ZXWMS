package com.zx.emanage.workflow.service;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.workflow.util.WorkflowFormHelp;

/**
 * 终审环节结束处理器 如果处理结果为继续审批，根据流水、信息、电审、证信4个审批环节的流程变量值来决定哪个审批环节继续审批
 * 
 * @author Tianyu
 */
@Service
@Transactional
public class FinalApprovalEndProcessor implements TaskListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask)
    {
        delegateTask.setVariable("lsspcontinue", "false");
        delegateTask.setVariable("xxspcontinue", "false");
        delegateTask.setVariable("dsspcontinue", "false");
        delegateTask.setVariable("zxspcontinue", "false");
        String iscontinue = (String) delegateTask.getVariable("iscontinue");
        if (StringUtils.isNotBlank(iscontinue) && "true".equals(iscontinue))
        {

            String lssppass = (String) delegateTask.getVariable("lssppass");
            String xxsppass = (String) delegateTask.getVariable("xxsppass");
            String dssppass = (String) delegateTask.getVariable("dssppass");
            String zxsppass = (String) delegateTask.getVariable("zxsppass");

            if (StringUtils.isNotBlank(lssppass) && "system".equals(lssppass))
            {
                delegateTask.setVariable("lsspcontinue", "true");
            }
            if (StringUtils.isNotBlank(xxsppass) && "system".equals(xxsppass))
            {
                delegateTask.setVariable("xxspcontinue", "true");
            }
            if (StringUtils.isNotBlank(dssppass) && "system".equals(dssppass))
            {
                delegateTask.setVariable("dsspcontinue", "true");
            }
            if (StringUtils.isNotBlank(zxsppass) && "system".equals(zxsppass))
            {
                delegateTask.setVariable("zxspcontinue", "true");
            }
        }
    }

}
