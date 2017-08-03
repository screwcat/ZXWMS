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

public class SysSpecialUser implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer sys_special_user_id;
	
	private String personnel_shortcode;
	
	private String personnel_name;
	
	private String personnel_postname;
	
	private Integer personnel_id;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
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
	  	"sys_special_user_id"
	};
	
	private static String[] columnNameArr = {
		"sys_special_user_id",
		"personnel_shortcode",
		"personnel_name",
		"personnel_postname",
		"personnel_id",
		"create_timestamp",
		"create_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getSys_special_user_id () {
		return sys_special_user_id;
	}
	
	public void setSys_special_user_id (Integer obj) {
		sys_special_user_id = obj;
	}
	
	public String getPersonnel_shortcode () {
		return personnel_shortcode;
	}
	
	public void setPersonnel_shortcode (String obj) {
		personnel_shortcode = obj;
	}
	
	public String getPersonnel_name () {
		return personnel_name;
	}
	
	public void setPersonnel_name (String obj) {
		personnel_name = obj;
	}
	
	public String getPersonnel_postname () {
		return personnel_postname;
	}
	
	public void setPersonnel_postname (String obj) {
		personnel_postname = obj;
	}
	
	public Integer getPersonnel_id () {
		return personnel_id;
	}
	
	public void setPersonnel_id (Integer obj) {
		personnel_id = obj;
	}
	
	public java.sql.Timestamp getCreate_timestamp () {
		return create_timestamp;
	}
	
	public void setCreate_timestamp (java.sql.Timestamp obj) {
		create_timestamp = obj;
	}
	
	public String getCreate_timestamp_str () {
		return create_timestamp_str;
	}
	
	public void setCreate_timestamp_str (String obj) {
		create_timestamp_str = obj;
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
		paramMap.put("sys_special_user_id", this.sys_special_user_id);
		paramMap.put("personnel_shortcode", this.personnel_shortcode);
		paramMap.put("personnel_name", this.personnel_name);
		paramMap.put("personnel_postname", this.personnel_postname);
		paramMap.put("personnel_id", this.personnel_id);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
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