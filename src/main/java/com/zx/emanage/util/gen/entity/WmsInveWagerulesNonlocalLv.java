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

public class WmsInveWagerulesNonlocalLv implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_wagerules_nonlocal_lv_id;
	
	private Integer wms_inve_wagerules_nonlocal_head_id;
	
	private Integer count_type;
	
	private java.math.BigDecimal cumulate_begin;
	
	private java.math.BigDecimal cumulate_end;
	
	private java.math.BigDecimal wage_money;
	
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
	  	"wms_inve_wagerules_nonlocal_lv_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_wagerules_nonlocal_lv_id",
		"wms_inve_wagerules_nonlocal_head_id",
		"count_type",
		"cumulate_begin",
		"cumulate_end",
		"wage_money",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_wagerules_nonlocal_lv_id () {
		return wms_inve_wagerules_nonlocal_lv_id;
	}
	
	public void setWms_inve_wagerules_nonlocal_lv_id (Integer obj) {
		wms_inve_wagerules_nonlocal_lv_id = obj;
	}
	
	public Integer getWms_inve_wagerules_nonlocal_head_id () {
		return wms_inve_wagerules_nonlocal_head_id;
	}
	
	public void setWms_inve_wagerules_nonlocal_head_id (Integer obj) {
		wms_inve_wagerules_nonlocal_head_id = obj;
	}
	
	public Integer getCount_type () {
		return count_type;
	}
	
	public void setCount_type (Integer obj) {
		count_type = obj;
	}
	
	public java.math.BigDecimal getCumulate_begin () {
		return cumulate_begin;
	}
	
	public void setCumulate_begin (java.math.BigDecimal obj) {
		cumulate_begin = obj;
	}
	
	public java.math.BigDecimal getCumulate_end () {
		return cumulate_end;
	}
	
	public void setCumulate_end (java.math.BigDecimal obj) {
		cumulate_end = obj;
	}
	
	public java.math.BigDecimal getWage_money () {
		return wage_money;
	}
	
	public void setWage_money (java.math.BigDecimal obj) {
		wage_money = obj;
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
		paramMap.put("wms_inve_wagerules_nonlocal_lv_id", this.wms_inve_wagerules_nonlocal_lv_id);
		paramMap.put("wms_inve_wagerules_nonlocal_head_id", this.wms_inve_wagerules_nonlocal_head_id);
		paramMap.put("count_type", this.count_type);
		paramMap.put("cumulate_begin", this.cumulate_begin);
		paramMap.put("cumulate_end", this.cumulate_end);
		paramMap.put("wage_money", this.wage_money);
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