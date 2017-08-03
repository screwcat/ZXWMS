package com.zx.emanage.workflow.service.impl;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.zx.emanage.inve.persist.WmsInveSalaryPreTotalDao;
import com.zx.sframe.util.GlobalVal;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSalarySetListener
 * 模块名称：
 * @Description: 内容摘要：
 * @author Guanxu
 * @date 2017年12月31日
 * @version V1.0
 * history:
 * 1、2017年12月31日 Guanxu 创建文件
 */
public class WmsInveSalarySetListener implements JavaDelegate
{

    /**
     * 
     */
    public WmsInveSalarySetListener()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @Title: execute
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param arg0
     * @throws Exception 
     * @author: Guanxu
     * @time:2017年12月31日 上午11:23:50
     * @see org.activiti.engine.delegate.JavaDelegate#execute(org.activiti.engine.delegate.DelegateExecution)
     * history:
     * 1、2017年12月31日 Guanxu 创建方法
    */
    @SuppressWarnings("deprecation")
    @Override
    public void execute(DelegateExecution delegate) throws Exception
    {

        WmsInveSalaryPreTotalDao wmsInveSalaryPreTotalDao = (WmsInveSalaryPreTotalDao) GlobalVal.ctx.getBean("wmsInveSalaryPreTotalDao");

        if (wmsInveSalaryPreTotalDao != null)
        {
            if (delegate.getBusinessKey() != null && !"".equals(delegate.getBusinessKey()))
            {
                Integer wms_inve_salary_pre_total_id = Integer.valueOf(delegate.getBusinessKey());
                wmsInveSalaryPreTotalDao.updateWmsInveSalarySetDataStatus(wms_inve_salary_pre_total_id);
            }
        }

    }

}
