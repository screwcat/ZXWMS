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

public class WmsCrePersonnelBillChange implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_personnel_bill_change_id;
	
	private Integer personnel_id;
	
	private String personnel_shortcode;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
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
	  	"wms_cre_personnel_bill_change_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_personnel_bill_change_id",
		"personnel_id",
		"personnel_shortcode",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_personnel_bill_change_id () {
		return wms_cre_personnel_bill_change_id;
	}
	
	public void setWms_cre_personnel_bill_change_id (Integer obj) {
		wms_cre_personnel_bill_change_id = obj;
	}
	
	public Integer getPersonnel_id () {
		return personnel_id;
	}
	
	public void setPersonnel_id (Integer obj) {
		personnel_id = obj;
	}
	
	public String getPersonnel_shortcode () {
		return personnel_shortcode;
	}
	
	public void setPersonnel_shortcode (String obj) {
		personnel_shortcode = obj;
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
		paramMap.put("wms_cre_personnel_bill_change_id", this.wms_cre_personnel_bill_change_id);
		paramMap.put("personnel_id", this.personnel_id);
		paramMap.put("personnel_shortcode", this.personnel_shortcode);
		paramMap.put("last_update_timestamp", this.last_update_timestamp);
		paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
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