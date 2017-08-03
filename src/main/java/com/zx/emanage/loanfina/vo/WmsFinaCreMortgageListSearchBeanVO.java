package com.zx.emanage.loanfina.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsFinaCreMortgageListSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private Integer wms_fina_cre_mortgage_list_id;
	private Integer wms_cre_credit_head_id;
	private java.sql.Date mortgage_date_start;
	private java.sql.Date mortgage_date_end;
	
	private String mortgage_name;
	
	private  String mortgage_status;
	
	public Integer getWms_cre_credit_head_id() {
		return wms_cre_credit_head_id;
	}
	public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
		this.wms_cre_credit_head_id = wms_cre_credit_head_id;
	}
	public java.sql.Date getMortgage_date_start() {
		return mortgage_date_start;
	}
	public void setMortgage_date_start(java.sql.Date mortgage_date_start) {
		this.mortgage_date_start = mortgage_date_start;
	}
	public java.sql.Date getMortgage_date_end() {
		return mortgage_date_end;
	}
	public void setMortgage_date_end(java.sql.Date mortgage_date_end) {
		this.mortgage_date_end = mortgage_date_end;
	}
	public Integer getWms_fina_cre_mortgage_list_id() {
		return wms_fina_cre_mortgage_list_id;
	}
	public void setWms_fina_cre_mortgage_list_id(
			Integer wms_fina_cre_mortgage_list_id) {
		this.wms_fina_cre_mortgage_list_id = wms_fina_cre_mortgage_list_id;
	}
	public String getMortgage_name() {
		return mortgage_name;
	}
	public void setMortgage_name(String mortgage_name) {
		this.mortgage_name = mortgage_name;
	}

	public String getMortgage_status() {
		return mortgage_status;
	}
	public void setMortgage_status(String mortgage_status) {
		this.mortgage_status = mortgage_status;
	}
}