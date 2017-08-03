package com.zx.emanage.loanpost.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsPostRemindHistorySearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private String protocol_code;//合同编号
	
	private String debtor_tel;//联系电话
	
	private String debtor_name;//客户姓名
	
	private String salesman_name;//业务员
	
	private String cre_type;//贷款产品
	
	private String repay_status;//单据状态

	public String getProtocol_code() {
		return protocol_code;
	}

	public void setProtocol_code(String protocol_code) {
		this.protocol_code = protocol_code;
	}

	public String getDebtor_tel() {
		return debtor_tel;
	}

	public void setDebtor_tel(String debtor_tel) {
		this.debtor_tel = debtor_tel;
	}

	public String getDebtor_name() {
		return debtor_name;
	}

	public void setDebtor_name(String debtor_name) {
		this.debtor_name = debtor_name;
	}

	public String getSalesman_name() {
		return salesman_name;
	}

	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}

	public String getCre_type() {
		return cre_type;
	}

	public void setCre_type(String cre_type) {
		this.cre_type = cre_type;
	}

	public String getRepay_status() {
		return repay_status;
	}

	public void setRepay_status(String repay_status) {
		this.repay_status = repay_status;
	}

}