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

public class WmsInvePruductRebateWay implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_pruduct_rebate_way_id;
	
	private Integer wms_inve_pruduct_category_id;
	
	private Integer customer_stock_begin;
	
	private Integer customer_stock_end;
	
	private Integer full_month;
	
	private java.math.BigDecimal bonus_rate;
	
	private Integer create_user_id;
	
	private java.sql.Timestamp create_datetime;
	
	private String create_datetime_str;
	
	private String last_update_user_id;
	
	private String last_update_datetime;
	
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
	  	"wms_inve_pruduct_rebate_way_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_pruduct_rebate_way_id",
		"wms_inve_pruduct_category_id",
		"customer_stock_begin",
		"customer_stock_end",
		"full_month",
		"bonus_rate",
		"create_user_id",
		"create_datetime",
		"create_datetime_str",
		"last_update_user_id",
		"last_update_datetime",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_pruduct_rebate_way_id () {
		return wms_inve_pruduct_rebate_way_id;
	}
	
	public void setWms_inve_pruduct_rebate_way_id (Integer obj) {
		wms_inve_pruduct_rebate_way_id = obj;
	}
	
	public Integer getWms_inve_pruduct_category_id () {
		return wms_inve_pruduct_category_id;
	}
	
	public void setWms_inve_pruduct_category_id (Integer obj) {
		wms_inve_pruduct_category_id = obj;
	}
	
	public Integer getCustomer_stock_begin () {
		return customer_stock_begin;
	}
	
	public void setCustomer_stock_begin (Integer obj) {
		customer_stock_begin = obj;
	}
	
	public Integer getCustomer_stock_end () {
		return customer_stock_end;
	}
	
	public void setCustomer_stock_end (Integer obj) {
		customer_stock_end = obj;
	}
	
	public Integer getFull_month () {
		return full_month;
	}
	
	public void setFull_month (Integer obj) {
		full_month = obj;
	}
	
	public java.math.BigDecimal getBonus_rate () {
		return bonus_rate;
	}
	
	public void setBonus_rate (java.math.BigDecimal obj) {
		bonus_rate = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
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
	
	public String getLast_update_user_id () {
		return last_update_user_id;
	}
	
	public void setLast_update_user_id (String obj) {
		last_update_user_id = obj;
	}
	
	public String getLast_update_datetime () {
		return last_update_datetime;
	}
	
	public void setLast_update_datetime (String obj) {
		last_update_datetime = obj;
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
		paramMap.put("wms_inve_pruduct_rebate_way_id", this.wms_inve_pruduct_rebate_way_id);
		paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
		paramMap.put("customer_stock_begin", this.customer_stock_begin);
		paramMap.put("customer_stock_end", this.customer_stock_end);
		paramMap.put("full_month", this.full_month);
		paramMap.put("bonus_rate", this.bonus_rate);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_datetime", this.create_datetime);
		paramMap.put("create_datetime_str", this.create_datetime_str);
		paramMap.put("last_update_user_id", this.last_update_user_id);
		paramMap.put("last_update_datetime", this.last_update_datetime);
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