package com.zx.emanage.util.gen.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.math.BigDecimal;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsFinaCreRealrepayInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_fina_cre_realrepay_info_id;
	
	private java.math.BigDecimal total_repayment;
	
	private java.math.BigDecimal is_total_repayment;
	
	private java.math.BigDecimal un_total_repayment;
	
	private java.math.BigDecimal adjustment_amount;
	
	private java.math.BigDecimal org_repay_principal;
	
	private java.math.BigDecimal repay_principal;
	
	private java.math.BigDecimal un_repay_principal;
	
	private java.math.BigDecimal org_repay_interest;
	
	private java.math.BigDecimal repay_interest;
	
	private java.math.BigDecimal un_repay_interest;
	
	private java.math.BigDecimal total_cur_late_fee;
	
	private java.math.BigDecimal cur_late_fee;
	
	private java.math.BigDecimal total_derate;
	
	private java.math.BigDecimal bq_cur_late_fee;
	
	private Integer wms_fina_cre_pay_id;
	
	private Integer wms_cre_appro_id;
	
	private Integer wms_cre_credit_head_id;
	
	private String enable_flag;
	
	private String repay_no;
	
	private Integer wms_fina_cre_period_pay_id;
	
	private Integer repay_no_count;
		
	private String repay_no_detail;
	
	private boolean isExcludePKFlag;
	
   
	
	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	  	"wms_fina_cre_realrepay_info_id"
	};
	
	private static String[] columnNameArr = {
		"wms_fina_cre_realrepay_info_id",
		"total_repayment",
		"is_total_repayment",
		"un_total_repayment",
		"adjustment_amount",
		"org_repay_principal",
		"repay_principal",
		"un_repay_principal",
		"org_repay_interest",
		"repay_interest",
		"un_repay_interest",
		"total_cur_late_fee",
		"cur_late_fee",
		"total_derate",
		"bq_cur_late_fee",
		"wms_fina_cre_pay_id",
		"wms_cre_appro_id",
		"wms_cre_credit_head_id",
		"enable_flag",
		"repay_no",
		"wms_fina_cre_period_pay_id",
		"repay_no_count",
		"repay_no_detail",
		"isExcludePKFlag"
	};
  
	public Integer getWms_fina_cre_realrepay_info_id () {
		return wms_fina_cre_realrepay_info_id;
	}
	
	public void setWms_fina_cre_realrepay_info_id (Integer obj) {
		wms_fina_cre_realrepay_info_id = obj;
	}
	
	public java.math.BigDecimal getTotal_repayment () {
		return total_repayment;
	}
	
	public void setTotal_repayment (java.math.BigDecimal obj) {
		total_repayment = obj;
	}
	
	public java.math.BigDecimal getIs_total_repayment () {
		return is_total_repayment;
	}
	
	public void setIs_total_repayment (java.math.BigDecimal obj) {
		is_total_repayment = obj;
	}
	
	public java.math.BigDecimal getun_total_repayment () {
		return un_total_repayment;
	}
	
	public void setun_total_repayment (java.math.BigDecimal obj) {
		un_total_repayment = obj;
	}
	
	public java.math.BigDecimal getAdjustment_amount () {
		return adjustment_amount;
	}
	
	public void setAdjustment_amount (java.math.BigDecimal obj) {
		adjustment_amount = obj;
	}
	
	public java.math.BigDecimal getOrg_repay_principal () {
		return org_repay_principal;
	}
	
	public void setOrg_repay_principal (java.math.BigDecimal obj) {
		org_repay_principal = obj;
	}
	
	public java.math.BigDecimal getRepay_principal () {
		return repay_principal;
	}
	
	public void setRepay_principal (java.math.BigDecimal obj) {
		repay_principal = obj;
	}
	
	public java.math.BigDecimal getUn_repay_principal () {
		return un_repay_principal;
	}
	
	public void setUn_repay_principal (java.math.BigDecimal obj) {
		un_repay_principal = obj;
	}
	
	public java.math.BigDecimal getOrg_repay_interest () {
		return org_repay_interest;
	}
	
	public void setOrg_repay_interest (java.math.BigDecimal obj) {
		org_repay_interest = obj;
	}
	
	public java.math.BigDecimal getRepay_interest () {
		return repay_interest;
	}
	
	public void setRepay_interest (java.math.BigDecimal obj) {
		repay_interest = obj;
	}
	
	public java.math.BigDecimal getUn_repay_interest () {
		return un_repay_interest;
	}
	
	public void setUn_repay_interest (java.math.BigDecimal obj) {
		un_repay_interest = obj;
	}
	
	public java.math.BigDecimal getTotal_cur_late_fee () {
		return total_cur_late_fee;
	}
	
	public void setTotal_cur_late_fee (java.math.BigDecimal obj) {
		total_cur_late_fee = obj;
	}
	
	public java.math.BigDecimal getCur_late_fee () {
		return cur_late_fee;
	}
	
	public void setCur_late_fee (java.math.BigDecimal obj) {
		cur_late_fee = obj;
	}
	
	public java.math.BigDecimal getTotal_derate () {
		return total_derate;
	}
	
	public void setTotal_derate (java.math.BigDecimal obj) {
		total_derate = obj;
	}
	
	public java.math.BigDecimal getBq_cur_late_fee () {
		return bq_cur_late_fee;
	}
	
	public void setBq_cur_late_fee (java.math.BigDecimal obj) {
		bq_cur_late_fee = obj;
	}
	
	public Integer getWms_fina_cre_pay_id () {
		return wms_fina_cre_pay_id;
	}
	
	public void setWms_fina_cre_pay_id (Integer obj) {
		wms_fina_cre_pay_id = obj;
	}
	
	public Integer getWms_cre_appro_id () {
		return wms_cre_appro_id;
	}
	
	public void setWms_cre_appro_id (Integer obj) {
		wms_cre_appro_id = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public String getRepay_no () {
		return repay_no;
	}
	
	public void setRepay_no (String obj) {
		repay_no = obj;
	}
	
	public Integer getWms_fina_cre_period_pay_id() {
		return wms_fina_cre_period_pay_id;
	}

	public void setWms_fina_cre_period_pay_id(Integer wms_fina_cre_period_pay_id) {
		this.wms_fina_cre_period_pay_id = wms_fina_cre_period_pay_id;
	}

	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public Integer getRepay_no_count() {
		return repay_no_count;
	}

	public void setRepay_no_count(Integer repay_no_count) {
		this.repay_no_count = repay_no_count;
	}

	public String getRepay_no_detail() {
		return repay_no_detail;
	}

	public void setRepay_no_detail(String repay_no_detail) {
		this.repay_no_detail = repay_no_detail;
	}

	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	
	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_fina_cre_realrepay_info_id", this.wms_fina_cre_realrepay_info_id);
		paramMap.put("total_repayment", this.total_repayment);
		paramMap.put("is_total_repayment", this.is_total_repayment);
		paramMap.put("un_total_repayment", this.un_total_repayment);
		paramMap.put("adjustment_amount", this.adjustment_amount);
		paramMap.put("org_repay_principal", this.org_repay_principal);
		paramMap.put("repay_principal", this.repay_principal);
		paramMap.put("un_repay_principal", this.un_repay_principal);
		paramMap.put("org_repay_interest", this.org_repay_interest);
		paramMap.put("repay_interest", this.repay_interest);
		paramMap.put("un_repay_interest", this.un_repay_interest);
		paramMap.put("total_cur_late_fee", this.total_cur_late_fee);
		paramMap.put("cur_late_fee", this.cur_late_fee);
		paramMap.put("total_derate", this.total_derate);
		paramMap.put("bq_cur_late_fee", this.bq_cur_late_fee);
		paramMap.put("wms_fina_cre_pay_id", this.wms_fina_cre_pay_id);
		paramMap.put("wms_cre_appro_id", this.wms_cre_appro_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("repay_no", this.repay_no);
		paramMap.put("wms_fina_cre_period_pay_id", this.wms_fina_cre_period_pay_id);
		paramMap.put("repay_no_count", this.repay_no_count);
		paramMap.put("repay_no_detail", this.repay_no_detail);
		paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
	}
	
	/**
	* return the columns map
	*/
	public Map<String, Object> getInfoMap() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		this.putInMap(paramMap);
		return paramMap;
	}
	
	/**
	* remove default value and pk if it is null
	*/
	private Map<String, Object> dealWithMap(Map<String, Object> paramMap) {
		Set<String> set = new HashSet<String>();
		for (String colName : defaultValColArr) {
			set.add(colName);
		}
		for (String colName : pkColArr) {
			set.add(colName);
		}
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String colName = iterator.next();
			if(paramMap.get(colName) == null) {
				paramMap.remove(colName);
			}
		}
		return paramMap;
	}
}