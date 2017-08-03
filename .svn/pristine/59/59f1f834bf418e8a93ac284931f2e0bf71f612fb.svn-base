package com.zx.emanage.workflow.service;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.workflow.util.WorkflowFormHelp;

/**
 * 终审环节开始处理器
 * 
 * @author Tianyu
 */
@Service
@Transactional
public class FinalApprovalStartProcessor implements TaskListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask)
    {
        delegateTask.setVariable("iscontinueenable", "false");

        String lssppass = (String) delegateTask.getVariable("lssppass");
        String xxsppass = (String) delegateTask.getVariable("xxsppass");
        String dssppass = (String) delegateTask.getVariable("dssppass");
        String zxsppass = (String) delegateTask.getVariable("zxsppass");

        if (StringUtils.isNotBlank(lssppass) && "system".equals(lssppass))
        {
            delegateTask.setVariable("iscontinueenable", "true");
            return;
        }
        if (StringUtils.isNotBlank(xxsppass) && "system".equals(xxsppass))
        {
            delegateTask.setVariable("iscontinueenable", "true");
            return;
        }
        if (StringUtils.isNotBlank(dssppass) && "system".equals(dssppass))
        {
            delegateTask.setVariable("iscontinueenable", "true");
            return;
        }
        if (StringUtils.isNotBlank(zxsppass) && "system".equals(zxsppass))
        {
            delegateTask.setVariable("iscontinueenable", "true");
            return;
        }
    }

}
