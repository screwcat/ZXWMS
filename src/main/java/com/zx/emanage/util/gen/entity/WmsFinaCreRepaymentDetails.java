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

public class WmsFinaCreRepaymentDetails implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_fina_cre_repayment_details_id;
	
	private Integer wms_fina_cre_repayment_history_id;
	
	private Integer wms_fina_cre_period_pay_id;
	
	private Integer repay_no;
	
	private String repayment_history_code;
	
	private java.sql.Date repayment_date;
	
	private String repayment_date_str;
	
	private String repayment_style;
	
	private java.math.BigDecimal repayment_price;
	
	private String this_repayment_remark;
	
	private Integer wms_cre_appro_id;
	
	private Integer wms_cre_credit_head_id;
	
	private Integer wms_fina_cre_pay_id;
	
	private String enable_flag;
	
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
	  	"wms_fina_cre_repayment_details_id"
	};
	
	private static String[] columnNameArr = {
		"wms_fina_cre_repayment_details_id",
		"wms_fina_cre_repayment_history_id",
		"wms_fina_cre_period_pay_id",
		"repay_no",
		"repayment_history_code",
		"repayment_date",
		"repayment_date_str",
		"repayment_style",
		"repayment_price",
		"this_repayment_remark",
		"wms_cre_appro_id",
		"wms_cre_credit_head_id",
		"wms_fina_cre_pay_id",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_fina_cre_repayment_details_id () {
		return wms_fina_cre_repayment_details_id;
	}
	
	public void setWms_fina_cre_repayment_details_id (Integer obj) {
		wms_fina_cre_repayment_details_id = obj;
	}
	
	public Integer getWms_fina_cre_repayment_history_id () {
		return wms_fina_cre_repayment_history_id;
	}
	
	public void setWms_fina_cre_repayment_history_id (Integer obj) {
		wms_fina_cre_repayment_history_id = obj;
	}
	
	public Integer getWms_fina_cre_period_pay_id () {
		return wms_fina_cre_period_pay_id;
	}
	
	public void setWms_fina_cre_period_pay_id (Integer obj) {
		wms_fina_cre_period_pay_id = obj;
	}
	
	public Integer getRepay_no () {
		return repay_no;
	}
	
	public void setRepay_no (Integer obj) {
		repay_no = obj;
	}
	
	public String getRepayment_history_code () {
		return repayment_history_code;
	}
	
	public void setRepayment_history_code (String obj) {
		repayment_history_code = obj;
	}
	
	public java.sql.Date getRepayment_date () {
		return repayment_date;
	}
	
	public void setRepayment_date (java.sql.Date obj) {
		repayment_date = obj;
	}
	
	public String getRepayment_date_str () {
		return repayment_date_str;
	}
	
	public void setRepayment_date_str (String obj) {
		repayment_date_str = obj;
	}
	
	public String getRepayment_style () {
		return repayment_style;
	}
	
	public void setRepayment_style (String obj) {
		repayment_style = obj;
	}
	
	public java.math.BigDecimal getRepayment_price () {
		return repayment_price;
	}
	
	public void setRepayment_price (java.math.BigDecimal obj) {
		repayment_price = obj;
	}
	
	public String getThis_repayment_remark () {
		return this_repayment_remark;
	}
	
	public void setThis_repayment_remark (String obj) {
		this_repayment_remark = obj;
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
	
	public Integer getWms_fina_cre_pay_id () {
		return wms_fina_cre_pay_id;
	}
	
	public void setWms_fina_cre_pay_id (Integer obj) {
		wms_fina_cre_pay_id = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	
	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_fina_cre_repayment_details_id", this.wms_fina_cre_repayment_details_id);
		paramMap.put("wms_fina_cre_repayment_history_id", this.wms_fina_cre_repayment_history_id);
		paramMap.put("wms_fina_cre_period_pay_id", this.wms_fina_cre_period_pay_id);
		paramMap.put("repay_no", this.repay_no);
		paramMap.put("repayment_history_code", this.repayment_history_code);
		paramMap.put("repayment_date", this.repayment_date);
		paramMap.put("repayment_date_str", this.repayment_date_str);
		paramMap.put("repayment_style", this.repayment_style);
		paramMap.put("repayment_price", this.repayment_price);
		paramMap.put("this_repayment_remark", this.this_repayment_remark);
		paramMap.put("wms_cre_appro_id", this.wms_cre_appro_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("wms_fina_cre_pay_id", this.wms_fina_cre_pay_id);
		paramMap.put("enable_flag", this.enable_flag);
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