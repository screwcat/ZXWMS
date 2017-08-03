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

public class WmsInvePruductCategoryLog implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_pruduct_category_log_id;
	
	private Integer wms_inve_pruduct_category_id;
	
	private String category_code;
	
	private Integer operation_type;
	
	private Integer appro_result;
	
	private String appro_advice;
	
	private Integer t_appro_user_id;
	
	private Integer t_appro_result;
	
	private String t_appro_advice;
	
	private String enable_reason;
	
	private java.sql.Date disable_time;
	
	private String disable_time_str;
	
	private Integer operator_user_id;
	
	private String operator_name;
	
	private java.sql.Timestamp operator_datetime;
	
	private String operator_datetime_str;
	
	private String operator_ip;
	
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
	  	"wms_inve_pruduct_category_log_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_pruduct_category_log_id",
		"wms_inve_pruduct_category_id",
		"category_code",
		"operation_type",
		"appro_result",
		"appro_advice",
		"t_appro_user_id",
		"t_appro_result",
		"t_appro_advice",
		"enable_reason",
		"disable_time",
		"disable_time_str",
		"operator_user_id",
		"operator_name",
		"operator_datetime",
		"operator_datetime_str",
		"operator_ip",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_pruduct_category_log_id () {
		return wms_inve_pruduct_category_log_id;
	}
	
	public void setWms_inve_pruduct_category_log_id (Integer obj) {
		wms_inve_pruduct_category_log_id = obj;
	}
	
	public Integer getWms_inve_pruduct_category_id () {
		return wms_inve_pruduct_category_id;
	}
	
	public void setWms_inve_pruduct_category_id (Integer obj) {
		wms_inve_pruduct_category_id = obj;
	}
	
	public String getCategory_code () {
		return category_code;
	}
	
	public void setCategory_code (String obj) {
		category_code = obj;
	}
	
	public Integer getOperation_type () {
		return operation_type;
	}
	
	public void setOperation_type (Integer obj) {
		operation_type = obj;
	}
	
	public Integer getAppro_result () {
		return appro_result;
	}
	
	public void setAppro_result (Integer obj) {
		appro_result = obj;
	}
	
	public String getAppro_advice () {
		return appro_advice;
	}
	
	public void setAppro_advice (String obj) {
		appro_advice = obj;
	}
	
	public Integer getT_appro_user_id () {
		return t_appro_user_id;
	}
	
	public void setT_appro_user_id (Integer obj) {
		t_appro_user_id = obj;
	}
	
	public Integer getT_appro_result () {
		return t_appro_result;
	}
	
	public void setT_appro_result (Integer obj) {
		t_appro_result = obj;
	}
	
	public String getT_appro_advice () {
		return t_appro_advice;
	}
	
	public void setT_appro_advice (String obj) {
		t_appro_advice = obj;
	}
	
	public String getEnable_reason () {
		return enable_reason;
	}
	
	public void setEnable_reason (String obj) {
		enable_reason = obj;
	}
	
	public java.sql.Date getDisable_time () {
		return disable_time;
	}
	
	public void setDisable_time (java.sql.Date obj) {
		disable_time = obj;
	}
	
	public String getDisable_time_str () {
		return disable_time_str;
	}
	
	public void setDisable_time_str (String obj) {
		disable_time_str = obj;
	}
	
	public Integer getOperator_user_id () {
		return operator_user_id;
	}
	
	public void setOperator_user_id (Integer obj) {
		operator_user_id = obj;
	}
	
	public String getOperator_name () {
		return operator_name;
	}
	
	public void setOperator_name (String obj) {
		operator_name = obj;
	}
	
	public java.sql.Timestamp getOperator_datetime () {
		return operator_datetime;
	}
	
	public void setOperator_datetime (java.sql.Timestamp obj) {
		operator_datetime = obj;
	}
	
	public String getOperator_datetime_str () {
		return operator_datetime_str;
	}
	
	public void setOperator_datetime_str (String obj) {
		operator_datetime_str = obj;
	}
	
	public String getOperator_ip () {
		return operator_ip;
	}
	
	public void setOperator_ip (String obj) {
		operator_ip = obj;
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
		paramMap.put("wms_inve_pruduct_category_log_id", this.wms_inve_pruduct_category_log_id);
		paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
		paramMap.put("category_code", this.category_code);
		paramMap.put("operation_type", this.operation_type);
		paramMap.put("appro_result", this.appro_result);
		paramMap.put("appro_advice", this.appro_advice);
		paramMap.put("t_appro_user_id", this.t_appro_user_id);
		paramMap.put("t_appro_result", this.t_appro_result);
		paramMap.put("t_appro_advice", this.t_appro_advice);
		paramMap.put("enable_reason", this.enable_reason);
		paramMap.put("disable_time", this.disable_time);
		paramMap.put("disable_time_str", this.disable_time_str);
		paramMap.put("operator_user_id", this.operator_user_id);
		paramMap.put("operator_name", this.operator_name);
		paramMap.put("operator_datetime", this.operator_datetime);
		paramMap.put("operator_datetime_str", this.operator_datetime_str);
		paramMap.put("operator_ip", this.operator_ip);
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