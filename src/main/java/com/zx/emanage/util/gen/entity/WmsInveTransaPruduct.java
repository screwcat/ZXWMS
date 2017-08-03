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

public class WmsInveTransaPruduct implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_transa_pruduct_id;
	
	private Integer wms_inve_transa_pruduct_user_id;
	
	private Integer wms_inve_pruduct_category_id;
	
	private String category_name;
	
	private String enable_flag;
	
	private Integer wms_inve_transa_pruduct_rules_id;
	
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
	  	"wms_inve_transa_pruduct_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_transa_pruduct_id",
		"wms_inve_transa_pruduct_user_id",
		"wms_inve_pruduct_category_id",
		"category_name",
		"enable_flag",
		"wms_inve_transa_pruduct_rules_id",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_transa_pruduct_id () {
		return wms_inve_transa_pruduct_id;
	}
	
	public void setWms_inve_transa_pruduct_id (Integer obj) {
		wms_inve_transa_pruduct_id = obj;
	}
	
	public Integer getWms_inve_transa_pruduct_user_id () {
		return wms_inve_transa_pruduct_user_id;
	}
	
	public void setWms_inve_transa_pruduct_user_id (Integer obj) {
		wms_inve_transa_pruduct_user_id = obj;
	}
	
	public Integer getWms_inve_pruduct_category_id () {
		return wms_inve_pruduct_category_id;
	}
	
	public void setWms_inve_pruduct_category_id (Integer obj) {
		wms_inve_pruduct_category_id = obj;
	}
	
	public String getCategory_name () {
		return category_name;
	}
	
	public void setCategory_name (String obj) {
		category_name = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public Integer getWms_inve_transa_pruduct_rules_id () {
		return wms_inve_transa_pruduct_rules_id;
	}
	
	public void setWms_inve_transa_pruduct_rules_id (Integer obj) {
		wms_inve_transa_pruduct_rules_id = obj;
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
		paramMap.put("wms_inve_transa_pruduct_id", this.wms_inve_transa_pruduct_id);
		paramMap.put("wms_inve_transa_pruduct_user_id", this.wms_inve_transa_pruduct_user_id);
		paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
		paramMap.put("category_name", this.category_name);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("wms_inve_transa_pruduct_rules_id", this.wms_inve_transa_pruduct_rules_id);
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