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

public class WmsInveWagerulesFloating implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_wagerules_floating_id;
	
	private Integer wms_inve_wagerules_head_id;
	
	private Integer floating_type;
	
	private Integer floating_scope;
	
	private java.math.BigDecimal cumulate_begin;
	
	private java.math.BigDecimal cumulate_end;
	
	private String wage_operation;
	
	private java.math.BigDecimal floating_wage_money;
	
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
	  	"wms_inve_wagerules_floating_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_wagerules_floating_id",
		"wms_inve_wagerules_head_id",
		"floating_type",
		"floating_scope",
		"cumulate_begin",
		"cumulate_end",
		"wage_operation",
		"floating_wage_money",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_wagerules_floating_id () {
		return wms_inve_wagerules_floating_id;
	}
	
	public void setWms_inve_wagerules_floating_id (Integer obj) {
		wms_inve_wagerules_floating_id = obj;
	}
	
	public Integer getWms_inve_wagerules_head_id () {
		return wms_inve_wagerules_head_id;
	}
	
	public void setWms_inve_wagerules_head_id (Integer obj) {
		wms_inve_wagerules_head_id = obj;
	}
	
	public Integer getFloating_type () {
		return floating_type;
	}
	
	public void setFloating_type (Integer obj) {
		floating_type = obj;
	}
	
	public Integer getFloating_scope () {
		return floating_scope;
	}
	
	public void setFloating_scope (Integer obj) {
		floating_scope = obj;
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
	
	public String getWage_operation () {
		return wage_operation;
	}
	
	public void setWage_operation (String obj) {
		wage_operation = obj;
	}
	
	public java.math.BigDecimal getFloating_wage_money () {
		return floating_wage_money;
	}
	
	public void setFloating_wage_money (java.math.BigDecimal obj) {
		floating_wage_money = obj;
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
		paramMap.put("wms_inve_wagerules_floating_id", this.wms_inve_wagerules_floating_id);
		paramMap.put("wms_inve_wagerules_head_id", this.wms_inve_wagerules_head_id);
		paramMap.put("floating_type", this.floating_type);
		paramMap.put("floating_scope", this.floating_scope);
		paramMap.put("cumulate_begin", this.cumulate_begin);
		paramMap.put("cumulate_end", this.cumulate_end);
		paramMap.put("wage_operation", this.wage_operation);
		paramMap.put("floating_wage_money", this.floating_wage_money);
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