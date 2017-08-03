package com.zx.emanage.cremanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCreCreditReturnSearchSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private String bill_code; //单据编号
	
	private String salesman_name; //业务员
	
	private String create_user_name; //录单员
	
	private String head_create_datetime_start; //申请时间开始
	
	private String head_create_datetime_end; //申请时间结束
	
	private String audit_time_start; //退件时间开始
	
	private String audit_time_end; //退件时间结束
	
	private String dept_city_sel; //所属城市
	
	private String dept_name_sel; //所属门店

	public String getBill_code() {
		return bill_code;
	}

	public void setBill_code(String bill_code) {
		this.bill_code = bill_code;
	}

	public String getSalesman_name() {
		return salesman_name;
	}

	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public String getHead_create_datetime_start() {
		return head_create_datetime_start;
	}

	public void setHead_create_datetime_start(String head_create_datetime_start) {
		this.head_create_datetime_start = head_create_datetime_start;
	}

	public String getHead_create_datetime_end() {
		return head_create_datetime_end;
	}

	public void setHead_create_datetime_end(String head_create_datetime_end) {
		this.head_create_datetime_end = head_create_datetime_end;
	}

	public String getAudit_time_start() {
		return audit_time_start;
	}

	public void setAudit_time_start(String audit_time_start) {
		this.audit_time_start = audit_time_start;
	}

	public String getAudit_time_end() {
		return audit_time_end;
	}

	public void setAudit_time_end(String audit_time_end) {
		this.audit_time_end = audit_time_end;
	}

	public String getDept_city_sel() {
		return dept_city_sel;
	}

	public void setDept_city_sel(String dept_city_sel) {
		this.dept_city_sel = dept_city_sel;
	}

	public String getDept_name_sel() {
		return dept_name_sel;
	}

	public void setDept_name_sel(String dept_name_sel) {
		this.dept_name_sel = dept_name_sel;
	}

}