package com.zx.emanage.cremanage.vo;

import java.math.BigDecimal;

import com.zx.platform.syscontext.vo.GridParamBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreCreditCopyBeanVO
 * 模块名称：
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月26日
 * @version V1.0
 * history:
 * 1、2016年12月26日 baisong 创建文件
 */
public class WmsCreCreditCopyBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    /**主键  **/
    private Integer wms_cre_credit_head_id;

    /**申请贷款额度  **/
    private double credit_limit;

    /**申请最长还款期限  **/
    private Integer max_repayment_time_limit;

    /**单据状态  **/
    private String bill_status;

    /**贷款产品  **/
    private Integer cre_loan_type;

    /**贷款利率  **/
    private BigDecimal loan_interest_rate;

    /**终审金额  **/
    private BigDecimal appro_limit;

    /**组合贷关联主键  **/
    private Integer wms_cre_credit_group_id;

    /**节点名称**/
    private String approval_task_type;

    /**单据编号**/
    private String bill_code;

    /**节点编码**/
    private String approval_task_code;

    /**上单时间标识 1为当前时间 如果不是1则是原始时间**/
    private String is_now;

    /**组合贷主单据 1是0否  **/
    private String is_group_flag;

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id)
    {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

    public double getCredit_limit()
    {
        return credit_limit;
    }

    public void setCredit_limit(double credit_limit)
    {
        this.credit_limit = credit_limit;
    }

    public Integer getMax_repayment_time_limit()
    {
        return max_repayment_time_limit;
    }

    public void setMax_repayment_time_limit(Integer max_repayment_time_limit)
    {
        this.max_repayment_time_limit = max_repayment_time_limit;
    }

    public String getBill_status()
    {
        return bill_status;
    }

    public void setBill_status(String bill_status)
    {
        this.bill_status = bill_status;
    }

    public Integer getCre_loan_type()
    {
        return cre_loan_type;
    }

    public void setCre_loan_type(Integer cre_loan_type)
    {
        this.cre_loan_type = cre_loan_type;
    }

    public BigDecimal getLoan_interest_rate()
    {
        return loan_interest_rate;
    }

    public void setLoan_interest_rate(BigDecimal loan_interest_rate)
    {
        this.loan_interest_rate = loan_interest_rate;
    }

    public BigDecimal getAppro_limit()
    {
        return appro_limit;
    }

    public void setAppro_limit(BigDecimal appro_limit)
    {
        this.appro_limit = appro_limit;
    }

    public Integer getWms_cre_credit_group_id()
    {
        return wms_cre_credit_group_id;
    }

    public void setWms_cre_credit_group_id(Integer wms_cre_credit_group_id)
    {
        this.wms_cre_credit_group_id = wms_cre_credit_group_id;
    }

    public String getApproval_task_type()
    {
        return approval_task_type;
    }

    public void setApproval_task_type(String approval_task_type)
    {
        this.approval_task_type = approval_task_type;
    }

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    public String getIs_now()
    {
        return is_now;
    }

    public void setIs_now(String is_now)
    {
        this.is_now = is_now;
    }

    public String getApproval_task_code()
    {
        return approval_task_code;
    }

    public void setApproval_task_code(String approval_task_code)
    {
        this.approval_task_code = approval_task_code;
    }

    public String getIs_group_flag()
    {
        return is_group_flag;
    }

    public void setIs_group_flag(String is_group_flag)
    {
        this.is_group_flag = is_group_flag;
    }
}