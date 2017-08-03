package com.zx.emanage.loanpost.service;

import java.util.Map;
import com.zx.emanage.loanpost.vo.WmsPostLoanWorkFlowBeanVO;

/**
 * 主要实现
 * 提前还款流程
 * 转件管理流程
 * 对应的流的处理接口
 * @author Hancd
 */
public interface IWmsPostLoanWorkFlowService
{
    /**
     * 实现提前还款审核操作
     * 
     * @param wmsPostLoanWorkFlowBeanVO 封装好的VO
     */
    public void wmsPostLoanApproveBefore(WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO);

    /**
     * 实现转件审核操作
     * 
     * @param wmsPostLoanWorkFlowBeanVO 封装好的VO
     */
    public void wmsPostLoanApproveTransferPaper(WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO);

    /**
     * 实现提前还款流程历程显示
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    public Map<String, Object> postLoanApproveProcessBefore(Integer wms_cre_credit_head_id);

    /**
     * 实现转件管理的流程历程显示
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    public Map<String, Object> postLoanApproveProcessTransferPaper(Integer wms_cre_credit_head_id);

    /**
     * 实现提前还款申请的流程启动 以及对重新申请的表单进行的操作
     * 
     * @param taskId 流程节点ID
     * @param wms_cre_credit_head_id 贷款主表单ID
     * @param userId 当前登录人ID
     */
    public void repeatOrBooleanWmsPostLoanWorkFlowBefore(String taskId, String wms_cre_credit_head_id, String userId);

    /**
     * 实现转件管理申请的流程启动 以及对重新申请的表单进行的操作
     * 
     * @param taskId 流程节点ID
     * @param wms_cre_credit_head_id 贷款主表单ID
     * @param userId 当前登录人ID
     */
    public void repeatOrBooleanWmsPostLoanWorkFlowTransferPaper(String taskId, String wms_cre_credit_head_id,
                                                                String userId);
}
