package com.zx.emanage.cremanage.vo;

public class WmsCarInfoVO {
	
	private String carstr;
	
	private Integer wms_cus_customer_id;
	
	private Integer wms_cre_credit_line_customer_change_head_id;

	public Integer getWms_cre_credit_line_customer_change_head_id() {
		return wms_cre_credit_line_customer_change_head_id;
	}

	public void setWms_cre_credit_line_customer_change_head_id(
			Integer wms_cre_credit_line_customer_change_head_id) {
		this.wms_cre_credit_line_customer_change_head_id = wms_cre_credit_line_customer_change_head_id;
	}

	public Integer getWms_cus_customer_id() {
		return wms_cus_customer_id;
	}

	public void setWms_cus_customer_id(Integer wms_cus_customer_id) {
		this.wms_cus_customer_id = wms_cus_customer_id;
	}

	public String getCarstr() {
		return carstr;
	}

	public void setCarstr(String carstr) {
		this.carstr = carstr;
	}
}
