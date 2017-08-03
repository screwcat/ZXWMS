package com.zx.emanage.workflow.service;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 终审环节开始处理器 如果处理结果为继续审批，根据流水、办件2个审批环节的流程变量值来决定哪个审批环节继续审批
 * 
 * @author Tianyu
 */
@Service
@Transactional
public class HousingLoanFinalApprovalStartProcessor implements TaskListener
{
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask)
    {
        delegateTask.setVariable("iscontinueenable", "false");

        String lssppass = (String) delegateTask.getVariable("lssppass");
        String bjsppass = (String) delegateTask.getVariable("bjsppass");
        String zxsppass = (String) delegateTask.getVariable("zxsppass");
        String xxsppass = (String) delegateTask.getVariable("xxsppass");
        String dssppass = (String) delegateTask.getVariable("dssppass");

        if (StringUtils.isNotBlank(lssppass) && "system".equals(lssppass))
        {
            delegateTask.setVariable("iscontinueenable", "true");
            return;
        }
        if (StringUtils.isNotBlank(bjsppass) && "system".equals(bjsppass))
        {
            delegateTask.setVariable("iscontinueenable", "true");
            return;
        }
        if (StringUtils.isNotBlank(zxsppass) && "system".equals(zxsppass))
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
    }
}
