package com.zx.emanage.cusmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCusCustomerLineCarpinfoSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private Integer wms_cus_customer_id;
	
	private Integer wms_cus_customer_line_carpinfo_id;

	public Integer getWms_cus_customer_id() {
		return wms_cus_customer_id;
	}

	public void setWms_cus_customer_id(Integer wms_cus_customer_id) {
		this.wms_cus_customer_id = wms_cus_customer_id;
	}

	public Integer getWms_cus_customer_line_carpinfo_id() {
		return wms_cus_customer_line_carpinfo_id;
	}

	public void setWms_cus_customer_line_carpinfo_id(
			Integer wms_cus_customer_line_carpinfo_id) {
		this.wms_cus_customer_line_carpinfo_id = wms_cus_customer_line_carpinfo_id;
	}
	
}