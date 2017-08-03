package com.zx.emanage.loanpost.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsAllocationSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;

	private Integer wms_fina_cre_pay_id;//id
	private Integer wms_cre_credit_head_id;//id
	private String selectval;//操作标识
	private Integer allocation_number;//要分配的数量
	private Integer debtor_number;//当前单据数量
	private Integer dunning_id;//人员id
	public Integer getWms_fina_cre_pay_id() {
		return wms_fina_cre_pay_id;
	}
	public void setWms_fina_cre_pay_id(Integer wms_fina_cre_pay_id) {
		this.wms_fina_cre_pay_id = wms_fina_cre_pay_id;
	}
	public Integer getWms_cre_credit_head_id() {
		return wms_cre_credit_head_id;
	}
	public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
		this.wms_cre_credit_head_id = wms_cre_credit_head_id;
	}
	public String getSelectval() {
		return selectval;
	}
	public void setSelectval(String selectval) {
		this.selectval = selectval;
	}
	public Integer getAllocation_number() {
		return allocation_number;
	}
	public void setAllocation_number(Integer allocation_number) {
		this.allocation_number = allocation_number;
	}
	public Integer getDebtor_number() {
		return debtor_number;
	}
	public void setDebtor_number(Integer debtor_number) {
		this.debtor_number = debtor_number;
	}
	public Integer getDunning_id() {
		return dunning_id;
	}
	public void setDunning_id(Integer dunning_id) {
		this.dunning_id = dunning_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}