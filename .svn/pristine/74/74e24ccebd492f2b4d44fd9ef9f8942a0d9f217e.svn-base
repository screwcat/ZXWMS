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

public class SysPushErrorInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer sys_push_error_info_id;
	
	private String type_code;
	
	private String type_remark;
	
	private String error_value;
	
	private String enable_flag;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
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
	  	"sys_push_error_info_id"
	};
	
	private static String[] columnNameArr = {
		"sys_push_error_info_id",
		"type_code",
		"type_remark",
		"error_value",
		"enable_flag",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"isExcludePKFlag"
	};
  
	public Integer getSys_push_error_info_id () {
		return sys_push_error_info_id;
	}
	
	public void setSys_push_error_info_id (Integer obj) {
		sys_push_error_info_id = obj;
	}
	
	public String getType_code () {
		return type_code;
	}
	
	public void setType_code (String obj) {
		type_code = obj;
	}
	
	public String getType_remark () {
		return type_remark;
	}
	
	public void setType_remark (String obj) {
		type_remark = obj;
	}
	
	public String getError_value () {
		return error_value;
	}
	
	public void setError_value (String obj) {
		error_value = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public Integer getLast_update_user_id () {
		return last_update_user_id;
	}
	
	public void setLast_update_user_id (Integer obj) {
		last_update_user_id = obj;
	}
	
	public java.sql.Timestamp getLast_update_timestamp () {
		return last_update_timestamp;
	}
	
	public void setLast_update_timestamp (java.sql.Timestamp obj) {
		last_update_timestamp = obj;
	}
	
	public String getLast_update_timestamp_str () {
		return last_update_timestamp_str;
	}
	
	public void setLast_update_timestamp_str (String obj) {
		last_update_timestamp_str = obj;
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
		paramMap.put("sys_push_error_info_id", this.sys_push_error_info_id);
		paramMap.put("type_code", this.type_code);
		paramMap.put("type_remark", this.type_remark);
		paramMap.put("error_value", this.error_value);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("last_update_user_id", this.last_update_user_id);
		paramMap.put("last_update_timestamp", this.last_update_timestamp);
		paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
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