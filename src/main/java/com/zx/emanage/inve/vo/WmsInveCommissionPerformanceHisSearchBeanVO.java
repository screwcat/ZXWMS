package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveCommissionPerformanceHisSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private String qry_type;//查询类型
	
	private String statics_month;
	
	private String salesman_id;
	
	private String dept_id;

	public String getQry_type() {
		return qry_type;
	}

	public void setQry_type(String qry_type) {
		this.qry_type = qry_type;
	}

	public String getStatics_month() {
		return statics_month;
	}

	public void setStatics_month(String statics_month) {
		this.statics_month = statics_month;
	}

	public String getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
}