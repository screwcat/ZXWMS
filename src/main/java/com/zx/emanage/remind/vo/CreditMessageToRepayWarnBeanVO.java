package com.zx.emanage.remind.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 *  还款提醒类
 */

public class CreditMessageToRepayWarnBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	//贷款主表
	private String wms_cre_credit_head_id;

	//贷款类型1信贷2房贷
	private String cre_type;
	
	//贷款单据状态 
	private String bill_status;
	
	public String getWms_cre_credit_head_id() {
		return wms_cre_credit_head_id;
	}

	public void setWms_cre_credit_head_id(String wms_cre_credit_head_id) {
		this.wms_cre_credit_head_id = wms_cre_credit_head_id;
	}

	public String getCre_type() {
		return cre_type;
	}

	public void setCre_type(String cre_type) {
		this.cre_type = cre_type;
	}

	public String getBill_status() {
		return bill_status;
	}

	public void setBill_status(String bill_status) {
		this.bill_status = bill_status;
	}
	
	
}