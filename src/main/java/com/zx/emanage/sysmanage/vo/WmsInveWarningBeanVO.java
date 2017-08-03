package com.zx.emanage.sysmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

public class WmsInveWarningBeanVO extends GridParamBean{
	private static final long serialVersionUID = 1L;
	
	public String cre_type;
	
	public java.sql.Date protocol_date_begin;
	public java.sql.Date protocol_date_end;
	
	public java.sql.Date borrow_end_date_begin;
	public java.sql.Date borrow_end_date_end;
	
	public Integer is_occupy;
	
	public String ptp_or_wms;

	public String getCre_type() {
		return cre_type;
	}

	public void setCre_type(String cre_type) {
		this.cre_type = cre_type;
	}

	public java.sql.Date getProtocol_date_begin() {
		return protocol_date_begin;
	}

	public void setProtocol_date_begin(java.sql.Date protocol_date_begin) {
		this.protocol_date_begin = protocol_date_begin;
	}

	public java.sql.Date getProtocol_date_end() {
		return protocol_date_end;
	}

	public void setProtocol_date_end(java.sql.Date protocol_date_end) {
		this.protocol_date_end = protocol_date_end;
	}

	public java.sql.Date getBorrow_end_date_begin() {
		return borrow_end_date_begin;
	}

	public void setBorrow_end_date_begin(java.sql.Date borrow_end_date_begin) {
		this.borrow_end_date_begin = borrow_end_date_begin;
	}

	public java.sql.Date getBorrow_end_date_end() {
		return borrow_end_date_end;
	}

	public void setBorrow_end_date_end(java.sql.Date borrow_end_date_end) {
		this.borrow_end_date_end = borrow_end_date_end;
	}

	public Integer getIs_occupy() {
		return is_occupy;
	}

	public void setIs_occupy(Integer is_occupy) {
		this.is_occupy = is_occupy;
	}

	public String getPtp_or_wms() {
		return ptp_or_wms;
	}

	public void setPtp_or_wms(String ptp_or_wms) {
		this.ptp_or_wms = ptp_or_wms;
	}
}
