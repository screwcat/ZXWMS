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

public class WmsCreHousingOperationLog implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_housing_operation_log_id;
	
	private String operation_module;
	
	private String operation_type;
	
	private String operation_reason;
	
	private java.sql.Timestamp operation_time;
	
	private String operation_time_str;
	
	private Integer operation_user_id;
	
	private Integer operation_user_deptid;
	
	private String enable_flag;
	
	private Integer wms_cre_credit_head_id;
	
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
	  	"wms_cre_housing_operation_log_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_housing_operation_log_id",
		"operation_module",
		"operation_type",
		"operation_reason",
		"operation_time",
		"operation_time_str",
		"operation_user_id",
		"operation_user_deptid",
		"enable_flag",
		"wms_cre_credit_head_id",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_housing_operation_log_id () {
		return wms_cre_housing_operation_log_id;
	}
	
	public void setWms_cre_housing_operation_log_id (Integer obj) {
		wms_cre_housing_operation_log_id = obj;
	}
	
	public String getOperation_module () {
		return operation_module;
	}
	
	public void setOperation_module (String obj) {
		operation_module = obj;
	}
	
	public String getOperation_type () {
		return operation_type;
	}
	
	public void setOperation_type (String obj) {
		operation_type = obj;
	}
	
	public String getOperation_reason () {
		return operation_reason;
	}
	
	public void setOperation_reason (String obj) {
		operation_reason = obj;
	}
	
	public java.sql.Timestamp getOperation_time () {
		return operation_time;
	}
	
	public void setOperation_time (java.sql.Timestamp obj) {
		operation_time = obj;
	}
	
	public String getOperation_time_str () {
		return operation_time_str;
	}
	
	public void setOperation_time_str (String obj) {
		operation_time_str = obj;
	}
	
	public Integer getOperation_user_id () {
		return operation_user_id;
	}
	
	public void setOperation_user_id (Integer obj) {
		operation_user_id = obj;
	}
	
	public Integer getOperation_user_deptid () {
		return operation_user_deptid;
	}
	
	public void setOperation_user_deptid (Integer obj) {
		operation_user_deptid = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
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
		paramMap.put("wms_cre_housing_operation_log_id", this.wms_cre_housing_operation_log_id);
		paramMap.put("operation_module", this.operation_module);
		paramMap.put("operation_type", this.operation_type);
		paramMap.put("operation_reason", this.operation_reason);
		paramMap.put("operation_time", this.operation_time);
		paramMap.put("operation_time_str", this.operation_time_str);
		paramMap.put("operation_user_id", this.operation_user_id);
		paramMap.put("operation_user_deptid", this.operation_user_deptid);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
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