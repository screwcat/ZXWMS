package com.zx.emanage.workflow.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.zx.emanage.loanappro.service.IWmsCreCreditApproModelService;
import com.zx.sframe.util.GlobalVal;

/**
 * 实现模型运算Java服务类
 * 
 * @author hancd
 */
public class ToCreditModelOperation implements JavaDelegate
{
    @Override
    public void execute(DelegateExecution execution) throws Exception
    {
        String wms_cre_credit_head_id = execution.getBusinessKey();
        IWmsCreCreditApproModelService iWmsCreCreditApproModelService = (IWmsCreCreditApproModelService) GlobalVal.ctx.getBean("wmscrecreditappromodelService");
        iWmsCreCreditApproModelService.getModelData(Integer.valueOf(wms_cre_credit_head_id));
    }
}
