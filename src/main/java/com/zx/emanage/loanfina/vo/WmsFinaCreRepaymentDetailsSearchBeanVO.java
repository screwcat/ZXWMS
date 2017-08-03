package com.zx.emanage.loanfina.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsFinaCreRepaymentDetailsSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	/**
	 * 财务正常还款 还款明细查询用到此处
	 * @author hancd
	 */
	private Integer wms_cre_credit_head_id;
	private String repayment_date_begin;
	private String repayment_date_end;
	public Integer getWms_cre_credit_head_id() {
		return wms_cre_credit_head_id;
	}

	public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
		this.wms_cre_credit_head_id = wms_cre_credit_head_id;
	}

	public String getRepayment_date_begin() {
		return repayment_date_begin;
	}

	public void setRepayment_date_begin(String repayment_date_begin) {
		this.repayment_date_begin = repayment_date_begin;
	}

	public String getRepayment_date_end() {
		return repayment_date_end;
	}

	public void setRepayment_date_end(String repayment_date_end) {
		this.repayment_date_end = repayment_date_end;
	}
	
}