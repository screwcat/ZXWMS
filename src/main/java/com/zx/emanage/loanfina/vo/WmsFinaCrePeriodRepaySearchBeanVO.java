package com.zx.emanage.loanfina.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsFinaCrePeriodRepaySearchBeanVO extends GridParamBean {

    private static final long serialVersionUID = 1L;

    private Integer wms_fina_cre_pay_id;//还款信息表主键
    
    private Integer wms_cre_credit_head_id;//贷款单据主键
    
    private Integer wms_cre_appro_borrow_protocol_id;//合同表主键
    
    private String protocol_code;//合同编号
    
    private java.sql.Date protocol_creat_date;//合同签订日期
    
    private java.math.BigDecimal protocol_amount;//合同金额
   
    private java.math.BigDecimal refund_limit_month;//月还款金额
    
    private Integer repay_period;//以还期数
    
    private java.math.BigDecimal repay_principal;//已还本金
    
    private java.math.BigDecimal repay_interest;//已还利息
    
    private Integer un_pay_period;//未还期数
    
    private java.math.BigDecimal un_pay_principal;//剩余本金
    
    private java.math.BigDecimal un_pay_interest;//剩余利息
    
    private Integer cur_overdue_day;//本期逾期天数
    
    private java.math.BigDecimal cur_late_fee;//本期滞纳金金额

    private java.math.BigDecimal total_derate;//总减免额

    private java.math.BigDecimal org_repay_principal;//本期应还款本金
    
    private java.math.BigDecimal org_repay_interest;//本期应还款利息

    private java.math.BigDecimal repay_total;//实际还款总额

    private java.math.BigDecimal derate;//本期减免金额

    private String wms_post_dunning_head_id;//催缴单ID
    
    private String debtor_name;//借款人
    
    public Integer getWms_fina_cre_pay_id()
    {
        return wms_fina_cre_pay_id;
    }
  
    public void setWms_fina_cre_pay_id(Integer wms_fina_cre_pay_id)
    {
        this.wms_fina_cre_pay_id = wms_fina_cre_pay_id;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id)
    {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

    public Integer getWms_cre_appro_borrow_protocol_id()
    {
        return wms_cre_appro_borrow_protocol_id;
    }

    public void setWms_cre_appro_borrow_protocol_id(Integer wms_cre_appro_borrow_protocol_id)
    {
        this.wms_cre_appro_borrow_protocol_id = wms_cre_appro_borrow_protocol_id;
    }

    public String getProtocol_code()
    {
        return protocol_code;
    }

    public void setProtocol_code(String protocol_code)
    {
        this.protocol_code = protocol_code;
    }

    public java.sql.Date getProtocol_creat_date()
    {
        return protocol_creat_date;
    }

    public void setProtocol_creat_date(java.sql.Date protocol_creat_date)
    {
        this.protocol_creat_date = protocol_creat_date;
    }

    public java.math.BigDecimal getProtocol_amount()
    {
        return protocol_amount;
    }

    public void setProtocol_amount(java.math.BigDecimal protocol_amount)
    {
        this.protocol_amount = protocol_amount;
    }

    public java.math.BigDecimal getRefund_limit_month()
    {
        return refund_limit_month;
    }

    public void setRefund_limit_month(java.math.BigDecimal refund_limit_month)
    {
        this.refund_limit_month = refund_limit_month;
    }

    public Integer getRepay_period()
    {
        return repay_period;
    }

    public void setRepay_period(Integer repay_period)
    {
        this.repay_period = repay_period;
    }

    public java.math.BigDecimal getRepay_principal()
    {
        return repay_principal;
    }

    public void setRepay_principal(java.math.BigDecimal repay_principal)
    {
        this.repay_principal = repay_principal;
    }

    public java.math.BigDecimal getRepay_interest()
    {
        return repay_interest;
    }

    public void setRepay_interest(java.math.BigDecimal repay_interest)
    {
        this.repay_interest = repay_interest;
    }

    public Integer getUn_pay_period()
    {
        return un_pay_period;
    }

    public void setUn_pay_period(Integer un_pay_period)
    {
        this.un_pay_period = un_pay_period;
    }

    public java.math.BigDecimal getUn_pay_principal()
    {
        return un_pay_principal;
    }

    public void setUn_pay_principal(java.math.BigDecimal un_pay_principal)
    {
        this.un_pay_principal = un_pay_principal;
    }

    public java.math.BigDecimal getUn_pay_interest()
    {
        return un_pay_interest;
    }

    public void setUn_pay_interest(java.math.BigDecimal un_pay_interest)
    {
        this.un_pay_interest = un_pay_interest;
    }

    public Integer getCur_overdue_day()
    {
        return cur_overdue_day;
    }

    public void setCur_overdue_day(Integer cur_overdue_day)
    {
        this.cur_overdue_day = cur_overdue_day;
    }

    public java.math.BigDecimal getCur_late_fee()
    {
        return cur_late_fee;
    }

    public void setCur_late_fee(java.math.BigDecimal cur_late_fee)
    {
        this.cur_late_fee = cur_late_fee;
    }

    public java.math.BigDecimal getTotal_derate()
    {
        return total_derate;
    }

    public void setTotal_derate(java.math.BigDecimal total_derate)
    {
        this.total_derate = total_derate;
    }

    public java.math.BigDecimal getOrg_repay_principal()
    {
        return org_repay_principal;
    }

    public void setOrg_repay_principal(java.math.BigDecimal org_repay_principal)
    {
        this.org_repay_principal = org_repay_principal;
    }

    public java.math.BigDecimal getOrg_repay_interest()
    {
        return org_repay_interest;
    }

    public void setOrg_repay_interest(java.math.BigDecimal org_repay_interest)
    {
        this.org_repay_interest = org_repay_interest;
    }

    public java.math.BigDecimal getRepay_total()
    {
        return repay_total;
    }

    public void setRepay_total(java.math.BigDecimal repay_total)
    {
        this.repay_total = repay_total;
    }

    public java.math.BigDecimal getDerate()
    {
        return derate;
    }

    public void setDerate(java.math.BigDecimal derate)
    {
        this.derate = derate;
    }

    public String getWms_post_dunning_head_id()
    {
        return wms_post_dunning_head_id;
    }

    public void setWms_post_dunning_head_id(String wms_post_dunning_head_id)
    {
        this.wms_post_dunning_head_id = wms_post_dunning_head_id;
    }

	public String getDebtor_name() {
		return debtor_name;
	}

	public void setDebtor_name(String debtor_name) {
		this.debtor_name = debtor_name;
	}
    
}