package com.zx.emanage.util.gen.vo;


import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveRedeemPrincipalDetailSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private java.math.BigDecimal principal_amount;
	
	private java.math.BigDecimal income_amount;
	
	private java.math.BigDecimal public_amount;

	private java.math.BigDecimal management_fee;

	private java.math.BigDecimal payable_basic_income;
	
	private java.math.BigDecimal extend_income;
	
	private java.math.BigDecimal payable_reward_income;
	
    private java.math.BigDecimal current_income;

	public java.math.BigDecimal getPrincipal_amount() {
		return principal_amount;
	}

	public void setPrincipal_amount(java.math.BigDecimal principal_amount) {
		this.principal_amount = principal_amount;
	}

	public java.math.BigDecimal getIncome_amount() {
		return income_amount;
	}

	public void setIncome_amount(java.math.BigDecimal income_amount) {
		this.income_amount = income_amount;
	}

	public java.math.BigDecimal getPublic_amount() {
		return public_amount;
	}

	public void setPublic_amount(java.math.BigDecimal public_amount) {
		this.public_amount = public_amount;
	}

	public java.math.BigDecimal getManagement_fee() {
		return management_fee;
	}

	public void setManagement_fee(java.math.BigDecimal management_fee) {
		this.management_fee = management_fee;
	}

	public java.math.BigDecimal getPayable_basic_income() {
		return payable_basic_income;
	}

	public void setPayable_basic_income(java.math.BigDecimal payable_basic_income) {
		this.payable_basic_income = payable_basic_income;
	}

	public java.math.BigDecimal getExtend_income() {
		return extend_income;
	}

	public void setExtend_income(java.math.BigDecimal extend_income) {
		this.extend_income = extend_income;
	}

	public java.math.BigDecimal getPayable_reward_income() {
		return payable_reward_income;
	}

	public void setPayable_reward_income(java.math.BigDecimal payable_reward_income) {
		this.payable_reward_income = payable_reward_income;
	}

    public java.math.BigDecimal getCurrent_income()
    {
        return current_income;
    }

    public void setCurrent_income(java.math.BigDecimal current_income)
    {
        this.current_income = current_income;
    }
}