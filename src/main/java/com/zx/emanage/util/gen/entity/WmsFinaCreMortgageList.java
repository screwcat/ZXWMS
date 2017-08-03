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

public class WmsFinaCreMortgageList implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_fina_cre_mortgage_list_id;
	
	private Integer wms_cre_credit_head_id;
	
	private Integer wms_fina_cre_pay_id;
	
	private Integer wms_cre_appro_id;
	
	private String mortgage_code;
	
	private String mortgage_name;
	
	private java.sql.Date mortgage_date;
	
	private String mortgage_date_str;
	
	private Integer mortgage_count;
	
	private java.math.BigDecimal should_mortgage_price;
	
	private java.math.BigDecimal real_mortgage_price;
	
	private java.math.BigDecimal d_value;
	
	private Integer auditor_id;
	
	private String auditor_name;
	
	private String auditor_result;
	
	private java.sql.Timestamp auditor_datetime;
	
	private String auditor_datetime_str;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private java.sql.Timestamp last_update_datetime;
	
	private String last_update_datetime_str;
	
	private String mortgage_status;
	
	private String enable_flag;
	
	private Integer strike_balance_status;
	
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
	  	"wms_fina_cre_mortgage_list_id"
	};
	
	private static String[] columnNameArr = {
		"wms_fina_cre_mortgage_list_id",
		"wms_cre_credit_head_id",
		"wms_fina_cre_pay_id",
		"wms_cre_appro_id",
		"mortgage_code",
		"mortgage_name",
		"mortgage_date",
		"mortgage_date_str",
		"mortgage_count",
		"should_mortgage_price",
		"real_mortgage_price",
		"d_value",
		"auditor_id",
		"auditor_name",
		"auditor_result",
		"auditor_datetime",
		"auditor_datetime_str",
		"create_user_id",
		"create_user_name",
		"last_update_datetime",
		"last_update_datetime_str",
		"mortgage_status",
		"enable_flag",
		"strike_balance_status",
		"isExcludePKFlag"
	};
  
	public Integer getWms_fina_cre_mortgage_list_id () {
		return wms_fina_cre_mortgage_list_id;
	}
	
	public void setWms_fina_cre_mortgage_list_id (Integer obj) {
		wms_fina_cre_mortgage_list_id = obj;
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
	
	public Integer getWms_cre_appro_id () {
		return wms_cre_appro_id;
	}
	
	public void setWms_cre_appro_id (Integer obj) {
		wms_cre_appro_id = obj;
	}
	
	public String getMortgage_code () {
		return mortgage_code;
	}
	
	public void setMortgage_code (String obj) {
		mortgage_code = obj;
	}
	
	public String getMortgage_name () {
		return mortgage_name;
	}
	
	public void setMortgage_name (String obj) {
		mortgage_name = obj;
	}
	
	public java.sql.Date getMortgage_date () {
		return mortgage_date;
	}
	
	public void setMortgage_date (java.sql.Date obj) {
		mortgage_date = obj;
	}
	
	public String getMortgage_date_str () {
		return mortgage_date_str;
	}
	
	public void setMortgage_date_str (String obj) {
		mortgage_date_str = obj;
	}
	
	public Integer getMortgage_count () {
		return mortgage_count;
	}
	
	public void setMortgage_count (Integer obj) {
		mortgage_count = obj;
	}
	
	public java.math.BigDecimal getShould_mortgage_price () {
		return should_mortgage_price;
	}
	
	public void setShould_mortgage_price (java.math.BigDecimal obj) {
		should_mortgage_price = obj;
	}
	
	public java.math.BigDecimal getReal_mortgage_price () {
		return real_mortgage_price;
	}
	
	public void setReal_mortgage_price (java.math.BigDecimal obj) {
		real_mortgage_price = obj;
	}
	
	public java.math.BigDecimal getD_value () {
		return d_value;
	}
	
	public void setD_value (java.math.BigDecimal obj) {
		d_value = obj;
	}
	
	public Integer getAuditor_id () {
		return auditor_id;
	}
	
	public void setAuditor_id (Integer obj) {
		auditor_id = obj;
	}
	
	public String getAuditor_name () {
		return auditor_name;
	}
	
	public void setAuditor_name (String obj) {
		auditor_name = obj;
	}
	
	public String getAuditor_result () {
		return auditor_result;
	}
	
	public void setAuditor_result (String obj) {
		auditor_result = obj;
	}
	
	public java.sql.Timestamp getAuditor_datetime () {
		return auditor_datetime;
	}
	
	public void setAuditor_datetime (java.sql.Timestamp obj) {
		auditor_datetime = obj;
	}
	
	public String getAuditor_datetime_str () {
		return auditor_datetime_str;
	}
	
	public void setAuditor_datetime_str (String obj) {
		auditor_datetime_str = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
	}
	
	public String getCreate_user_name () {
		return create_user_name;
	}
	
	public void setCreate_user_name (String obj) {
		create_user_name = obj;
	}
	
	public java.sql.Timestamp getLast_update_datetime () {
		return last_update_datetime;
	}
	
	public void setLast_update_datetime (java.sql.Timestamp obj) {
		last_update_datetime = obj;
	}
	
	public String getLast_update_datetime_str () {
		return last_update_datetime_str;
	}
	
	public void setLast_update_datetime_str (String obj) {
		last_update_datetime_str = obj;
	}
	
	public String getMortgage_status () {
		return mortgage_status;
	}
	
	public void setMortgage_status (String obj) {
		mortgage_status = obj;
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
	
	public Integer getStrike_balance_status() {
		return strike_balance_status;
	}

	public void setStrike_balance_status(Integer strike_balance_status) {
		this.strike_balance_status = strike_balance_status;
	}

	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	
	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_fina_cre_mortgage_list_id", this.wms_fina_cre_mortgage_list_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("wms_fina_cre_pay_id", this.wms_fina_cre_pay_id);
		paramMap.put("wms_cre_appro_id", this.wms_cre_appro_id);
		paramMap.put("mortgage_code", this.mortgage_code);
		paramMap.put("mortgage_name", this.mortgage_name);
		paramMap.put("mortgage_date", this.mortgage_date);
		paramMap.put("mortgage_date_str", this.mortgage_date_str);
		paramMap.put("mortgage_count", this.mortgage_count);
		paramMap.put("should_mortgage_price", this.should_mortgage_price);
		paramMap.put("real_mortgage_price", this.real_mortgage_price);
		paramMap.put("d_value", this.d_value);
		paramMap.put("auditor_id", this.auditor_id);
		paramMap.put("auditor_name", this.auditor_name);
		paramMap.put("auditor_result", this.auditor_result);
		paramMap.put("auditor_datetime", this.auditor_datetime);
		paramMap.put("auditor_datetime_str", this.auditor_datetime_str);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_user_name", this.create_user_name);
		paramMap.put("last_update_datetime", this.last_update_datetime);
		paramMap.put("last_update_datetime_str", this.last_update_datetime_str);
		paramMap.put("mortgage_status", this.mortgage_status);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("strike_balance_status", this.strike_balance_status);
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