package com.zx.emanage.reportmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/**
 * 版权所有：版权所有(C) 2015，卓信金控
 * 文件名称: WmsLoanInveClaimsInquiryReportBeanVo.java
 * 系统名称：WMS
 * 模块名称：理财客户每月收益报表
 * 完成日期：
 * 作    者：
 * 内容摘要：
 * 
 * 文件调用：
 * 修改记录：
 * 修改时间：
 * 修 改 人: 
 * 修改内容：
 * 关联BUG：
 * 修改方法：
 */
public class WmsLoanInveClaimsInquiryReportBeanVo extends GridParamBean{
	 private static final long serialVersionUID = 1L;
	 private String  bill_code;
	 private String  prot_code;
	 private String  cus_name;
	 private String  credit_name;
	 private java.sql.Date date_of_assign_begin;
	 private java.sql.Date date_of_assign_end;
	 private Integer category_name;
	 private Integer wms_inve_transa_prod_id;

	 public String getBill_code() {
		return bill_code;
	}

	public void setBill_code(String bill_code) {
		this.bill_code = bill_code;
	}

	public String getProt_code() {
		return prot_code;
	}

	public void setProt_code(String prot_code) {
		this.prot_code = prot_code;
	}

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}

	public String getCredit_name() {
		return credit_name;
	}

	public void setCredit_name(String credit_name) {
		this.credit_name = credit_name;
	}

	public java.sql.Date getDate_of_assign_begin() {
		return date_of_assign_begin;
	}

	public void setDate_of_assign_begin(java.sql.Date date_of_assign_begin) {
		this.date_of_assign_begin = date_of_assign_begin;
	}

	public java.sql.Date getDate_of_assign_end() {
		return date_of_assign_end;
	}

	public void setDate_of_assign_end(java.sql.Date date_of_assign_end) {
		this.date_of_assign_end = date_of_assign_end;
	}

	public Integer getCategory_name() {
		return category_name;
	}

	public void setCategory_name(Integer category_name) {
		this.category_name = category_name;
	}

	public Integer getWms_inve_transa_prod_id() {
		return wms_inve_transa_prod_id;
	 }

	 public void setWms_inve_transa_prod_id(Integer wms_inve_transa_prod_id) {
	 	this.wms_inve_transa_prod_id = wms_inve_transa_prod_id;
	 }
	
}
