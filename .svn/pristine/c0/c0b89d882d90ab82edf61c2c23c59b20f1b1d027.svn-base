package com.zx.emanage.loanpost.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.loanpost.persist.WmsPostDunningHeadDao;
import com.zx.emanage.loanpost.service.IWmsPostLoanWorkFlowService;
import com.zx.emanage.loanpost.vo.WmsPostLoanWorkFlowBeanVO;
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneMainDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsPostDunningHead;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;

/**
 * 主要实现提前还款流程与转件管理流程的对应的流的处理接口实现
 * 
 * @author hancd
 */
@Service("wmspostloanworkflowService")
public class WmsPostLoanWorkFlowServiceImpl implements IWmsPostLoanWorkFlowService
{
    private static Logger log = LoggerFactory.getLogger(WmsPostLoanWorkFlowServiceImpl.class);

    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private PmPersonnelDao pmpersonnelDao;
    
    @Autowired
    private WmsPostDunningHeadDao wmsPostDunningHeadDao;

    /**
     * 实现提前还款申请的流程启动 以及对重新申请的表单进行的操作
     */
    @Override
    public void repeatOrBooleanWmsPostLoanWorkFlowBefore(String taskId, String wms_cre_credit_head_id, String userId)
    {

        if (StringUtil.isNotBlank(taskId))
        {
            // 实现对重新申请表单的结果提交存储
            WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO = new WmsPostLoanWorkFlowBeanVO();
            wmsPostLoanWorkFlowBeanVO.setPass("true");
            wmsPostLoanWorkFlowBeanVO.setAdvice("重新提交提前贷款申请");
            wmsPostLoanWorkFlowBeanVO.setTaskId(taskId);
            wmsPostLoanWorkFlowBeanVO.setUser_id(Integer.valueOf(userId));
            wmsPostLoanApproveWorkFlow(wmsPostLoanWorkFlowBeanVO);
        }
        else
        {
            // 实现转件申请流程开启
            workflowService.startWorkflow(userId, WorkflowConstantHelp.BEFOREREPAYPROCESS, wms_cre_credit_head_id,
                                          new HashMap<String, Object>());
        }
    }

    /**
     * 实现提前还款审批处理实现
     * 
     * @param wmsPostLoanWorkFlowBeanVO
     */

    @Override
    public void wmsPostLoanApproveBefore(WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO)
    {
        wmsPostLoanApproveWorkFlow(wmsPostLoanWorkFlowBeanVO);
    }

    /**
     * 实现提前贷款审批流程历史显示
     */
    @Override
    public Map<String, Object> postLoanApproveProcessBefore(Integer wms_cre_credit_head_id)
    {
        Map<String, Object> reammap = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.BEFOREREPAYPROCESS);
        workflowSearchInfoHelp.setFinish(true);
        workflowSearchInfoHelp.setBusinessKey(String.valueOf(wms_cre_credit_head_id));
        List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(workflowSearchInfoHelp);
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            // 根据流程里面的提供审批人id :approverids
            String approverids = workflowHistoryInfoHelp.getApproverids();
            boolean isNum = approverids.matches("[0-9]+");
            if (isNum)
            {
                PmPersonnel person = pmpersonnelDao.get(Integer.valueOf(workflowHistoryInfoHelp.getApproverids()));
                if (person != null)
                {
                    // 相应的id，获取相对应的部门名称
                    workflowHistoryInfoHelp.setPersonnel_deptName(person.getPersonnel_deptname());
                    // 相应的id，获取相对应的职务名称
                    workflowHistoryInfoHelp.setPersonnel_postName(person.getPersonnel_postname());
                }
            }
        }

        List<WorkflowHistoryInfoHelp> works = new ArrayList<WorkflowHistoryInfoHelp>();
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            if ("true".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                workflowHistoryInfoHelp.setApproveResult("审批通过");
            }
            else if ("false".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                workflowHistoryInfoHelp.setApproveResult("审批不通过");
            }
            works.add(workflowHistoryInfoHelp);
        }
        reammap.put("Rows", works);
        return reammap;
    }

    /**
     * 实现转件管理申请的流程启动 以及对重新申请的表单进行的操作
     */
    @Override
    public void repeatOrBooleanWmsPostLoanWorkFlowTransferPaper(String taskId, String wms_cre_credit_head_id,
                                                                String userId)
    {
        if (StringUtil.isNotBlank(taskId))
        {
            // 实现对重新申请表单的结果提交存储
            WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO = new WmsPostLoanWorkFlowBeanVO();
            wmsPostLoanWorkFlowBeanVO.setPass("true");
            wmsPostLoanWorkFlowBeanVO.setAdvice("重新提交转件申请");
            wmsPostLoanWorkFlowBeanVO.setTaskId(taskId);
            wmsPostLoanWorkFlowBeanVO.setUser_id(Integer.valueOf(userId));
            wmsPostLoanApproveWorkFlow(wmsPostLoanWorkFlowBeanVO);
        }
        else
        {
            // 实现转件申请流程开启
            workflowService.startWorkflow(userId, WorkflowConstantHelp.TRANSFERPAPERPROCESS, wms_cre_credit_head_id,
                                          new HashMap<String, Object>());
        }
    }

    /**
     * 实现转件审批流程实现
     */
    @Override
    public void wmsPostLoanApproveTransferPaper(WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO)
    {
        wmsPostLoanApproveWorkFlow(wmsPostLoanWorkFlowBeanVO);
    }

    /**
     * 实现转件过程流程历史显示
     */
    @Override
    public Map<String, Object> postLoanApproveProcessTransferPaper(Integer wms_cre_credit_head_id)
    {
        Map<String, Object> reammap = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.TRANSFERPAPERPROCESS);
        workflowSearchInfoHelp.setFinish(true);
        workflowSearchInfoHelp.setBusinessKey(String.valueOf(wms_cre_credit_head_id));
        List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(workflowSearchInfoHelp);
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            // 根据流程里面的提供审批人id :approverids
            PmPersonnel person = pmpersonnelDao.get(Integer.valueOf(workflowHistoryInfoHelp.getApproverids()));
            if (person != null)
            {
                // 相应的id，获取相对应的部门名称
                workflowHistoryInfoHelp.setPersonnel_deptName(person.getPersonnel_deptname());
                // 相应的id，获取相对应的职务名称
                workflowHistoryInfoHelp.setPersonnel_postName(person.getPersonnel_postname());
            }
        }
        List<WorkflowHistoryInfoHelp> works = new ArrayList<WorkflowHistoryInfoHelp>();
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            if ("true".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                workflowHistoryInfoHelp.setApproveResult("审批通过");
            }
            else if ("false".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                workflowHistoryInfoHelp.setApproveResult("审批不通过");
            }
            works.add(workflowHistoryInfoHelp);
        }
        reammap.put("Rows", works);
        return reammap;
    }
    /**
     * 实现公共审批结果和意见的流存储
     * 
     * @param wmsPostLoanWorkFlowBeanVO
     */
    private void wmsPostLoanApproveWorkFlow(WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO)
    {
        Map<String, String> formPropertiesMap = new HashMap<String, String>();
        formPropertiesMap.put("pass", wmsPostLoanWorkFlowBeanVO.getPass());
        formPropertiesMap.put("advice", wmsPostLoanWorkFlowBeanVO.getAdvice());
        workflowService.completeTask(String.valueOf(wmsPostLoanWorkFlowBeanVO.getUser_id()),
                                     wmsPostLoanWorkFlowBeanVO.getTaskId(), formPropertiesMap);
    }
}
