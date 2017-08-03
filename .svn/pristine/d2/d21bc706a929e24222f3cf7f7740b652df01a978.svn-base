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

public class WmsInveCommionRecordAdjust implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_commion_record_adjust_id;
	
	private String adjust_type;
	
	private java.math.BigDecimal adjust_amount;
	
	private String adjust_remark;
	
	private java.sql.Timestamp adjust_datetime;
	
	private String adjust_datetime_str;
	
	private Integer pm_personnel_id;
	
	private boolean isExcludePKFlag;
	
	private Integer wms_inve_commion_record_id;
	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	  	"wms_inve_commion_record_adjust_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_commion_record_adjust_id",
		"adjust_type",
		"adjust_amount",
		"adjust_remark",
		"adjust_datetime",
		"adjust_datetime_str",
		"pm_personnel_id",
		"isExcludePKFlag",
		"wms_inve_commion_record_id"
	};
  
	public Integer getWms_inve_commion_record_adjust_id () {
		return wms_inve_commion_record_adjust_id;
	}
	
	public void setWms_inve_commion_record_adjust_id (Integer obj) {
		wms_inve_commion_record_adjust_id = obj;
	}
	
	public String getAdjust_type () {
		return adjust_type;
	}
	
	public void setAdjust_type (String obj) {
		adjust_type = obj;
	}
	
	public java.math.BigDecimal getAdjust_amount () {
		return adjust_amount;
	}
	
	public void setAdjust_amount (java.math.BigDecimal obj) {
		adjust_amount = obj;
	}
	
	public String getAdjust_remark () {
		return adjust_remark;
	}
	
	public void setAdjust_remark (String obj) {
		adjust_remark = obj;
	}
	
	public java.sql.Timestamp getAdjust_datetime () {
		return adjust_datetime;
	}
	
	public void setAdjust_datetime (java.sql.Timestamp obj) {
		adjust_datetime = obj;
	}
	
	public String getAdjust_datetime_str () {
		return adjust_datetime_str;
	}
	
	public void setAdjust_datetime_str (String obj) {
		adjust_datetime_str = obj;
	}
	
	public Integer getPm_personnel_id () {
		return pm_personnel_id;
	}
	
	public void setPm_personnel_id (Integer obj) {
		pm_personnel_id = obj;
	}
	
	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	
	public Integer getWms_inve_commion_record_id() {
		return wms_inve_commion_record_id;
	}

	public void setWms_inve_commion_record_id(Integer wms_inve_commion_record_id) {
		this.wms_inve_commion_record_id = wms_inve_commion_record_id;
	}

	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_inve_commion_record_adjust_id", this.wms_inve_commion_record_adjust_id);
		paramMap.put("adjust_type", this.adjust_type);
		paramMap.put("adjust_amount", this.adjust_amount);
		paramMap.put("adjust_remark", this.adjust_remark);
		paramMap.put("adjust_datetime", this.adjust_datetime);
		paramMap.put("adjust_datetime_str", this.adjust_datetime_str);
		paramMap.put("pm_personnel_id", this.pm_personnel_id);
		paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
		paramMap.put("wms_inve_commion_record_id", this.wms_inve_commion_record_id);
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