package com.zx.emanage.util.gen.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsInveCommissionFloatingNew implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_commission_floating_new_id;
	
	private Integer wms_inve_commission_general_rules_new_id;
	
	private Integer commission_type;
	
	private java.math.BigDecimal cumulate_begin;
	
	private java.math.BigDecimal cumulate_end;
	
	private java.math.BigDecimal floating_commission_disposable;
	
	private java.math.BigDecimal floating_commission_monthly;
	
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
	  	"wms_inve_commission_floating_new_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_commission_floating_new_id",
		"wms_inve_commission_general_rules_new_id",
		"commission_type",
		"cumulate_begin",
		"cumulate_end",
		"floating_commission_disposable",
		"floating_commission_monthly",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_commission_floating_new_id () {
		return wms_inve_commission_floating_new_id;
	}
	
	public void setWms_inve_commission_floating_new_id (Integer obj) {
		wms_inve_commission_floating_new_id = obj;
	}
	
	public Integer getWms_inve_commission_general_rules_new_id () {
		return wms_inve_commission_general_rules_new_id;
	}
	
	public void setWms_inve_commission_general_rules_new_id (Integer obj) {
		wms_inve_commission_general_rules_new_id = obj;
	}
	
	public Integer getCommission_type () {
		return commission_type;
	}
	
	public void setCommission_type (Integer obj) {
		commission_type = obj;
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
	
	public java.math.BigDecimal getFloating_commission_disposable () {
		return floating_commission_disposable;
	}
	
	public void setFloating_commission_disposable (java.math.BigDecimal obj) {
		floating_commission_disposable = obj;
	}
	
	public java.math.BigDecimal getFloating_commission_monthly () {
		return floating_commission_monthly;
	}
	
	public void setFloating_commission_monthly (java.math.BigDecimal obj) {
		floating_commission_monthly = obj;
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
		paramMap.put("wms_inve_commission_floating_new_id", this.wms_inve_commission_floating_new_id);
		paramMap.put("wms_inve_commission_general_rules_new_id", this.wms_inve_commission_general_rules_new_id);
		paramMap.put("commission_type", this.commission_type);
		paramMap.put("cumulate_begin", this.cumulate_begin);
		paramMap.put("cumulate_end", this.cumulate_end);
		paramMap.put("floating_commission_disposable", this.floating_commission_disposable);
		paramMap.put("floating_commission_monthly", this.floating_commission_monthly);
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