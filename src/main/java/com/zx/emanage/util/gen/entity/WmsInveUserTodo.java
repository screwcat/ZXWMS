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

public class WmsInveUserTodo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_user_todo_id;
	
	private Integer user_id;
	
	private String todo_list;
	
	private java.sql.Timestamp create_datetime;
	
	private String create_datetime_str;
	
	private Integer create_user_id;
	
	private java.sql.Timestamp handle_datetme;
	
	private String handle_datetme_str;
	
	private String data_status;
	
	private String remark;
	
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
	  	"wms_inve_user_todo_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_user_todo_id",
		"user_id",
		"todo_list",
		"create_datetime",
		"create_datetime_str",
		"create_user_id",
		"handle_datetme",
		"handle_datetme_str",
		"data_status",
		"remark",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_user_todo_id () {
		return wms_inve_user_todo_id;
	}
	
	public void setWms_inve_user_todo_id (Integer obj) {
		wms_inve_user_todo_id = obj;
	}
	
	public Integer getUser_id () {
		return user_id;
	}
	
	public void setUser_id (Integer obj) {
		user_id = obj;
	}
	
	public String getTodo_list () {
		return todo_list;
	}
	
	public void setTodo_list (String obj) {
		todo_list = obj;
	}
	
	public java.sql.Timestamp getCreate_datetime () {
		return create_datetime;
	}
	
	public void setCreate_datetime (java.sql.Timestamp obj) {
		create_datetime = obj;
	}
	
	public String getCreate_datetime_str () {
		return create_datetime_str;
	}
	
	public void setCreate_datetime_str (String obj) {
		create_datetime_str = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
	}
	
	public java.sql.Timestamp getHandle_datetme () {
		return handle_datetme;
	}
	
	public void setHandle_datetme (java.sql.Timestamp obj) {
		handle_datetme = obj;
	}
	
	public String getHandle_datetme_str () {
		return handle_datetme_str;
	}
	
	public void setHandle_datetme_str (String obj) {
		handle_datetme_str = obj;
	}
	
	public String getData_status () {
		return data_status;
	}
	
	public void setData_status (String obj) {
		data_status = obj;
	}
	
	public String getRemark () {
		return remark;
	}
	
	public void setRemark (String obj) {
		remark = obj;
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
		paramMap.put("wms_inve_user_todo_id", this.wms_inve_user_todo_id);
		paramMap.put("user_id", this.user_id);
		paramMap.put("todo_list", this.todo_list);
		paramMap.put("create_datetime", this.create_datetime);
		paramMap.put("create_datetime_str", this.create_datetime_str);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("handle_datetme", this.handle_datetme);
		paramMap.put("handle_datetme_str", this.handle_datetme_str);
		paramMap.put("data_status", this.data_status);
		paramMap.put("remark", this.remark);
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