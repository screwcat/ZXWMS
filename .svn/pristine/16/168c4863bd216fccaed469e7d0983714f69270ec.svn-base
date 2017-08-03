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

public class WmsInveCommissionGeneralMonthlyRules implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_commission_general_monthly_rules_id;
	
	private Integer wms_inve_commission_general_rules_id;
	
	private Integer stock_begin;
	
	private Integer stock_end;
	
	private java.math.BigDecimal base_commission;
	
	private java.math.BigDecimal positive_commission;
	
	private Integer create_user_id;
	
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
	  	"wms_inve_commission_general_monthly_rules_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_commission_general_monthly_rules_id",
		"wms_inve_commission_general_rules_id",
		"stock_begin",
		"stock_end",
		"base_commission",
		"positive_commission",
		"create_user_id",
		"create_timestamp",
		"create_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_commission_general_monthly_rules_id () {
		return wms_inve_commission_general_monthly_rules_id;
	}
	
	public void setWms_inve_commission_general_monthly_rules_id (Integer obj) {
		wms_inve_commission_general_monthly_rules_id = obj;
	}
	
	public Integer getWms_inve_commission_general_rules_id () {
		return wms_inve_commission_general_rules_id;
	}
	
	public void setWms_inve_commission_general_rules_id (Integer obj) {
		wms_inve_commission_general_rules_id = obj;
	}
	
	public Integer getStock_begin () {
		return stock_begin;
	}
	
	public void setStock_begin (Integer obj) {
		stock_begin = obj;
	}
	
	public Integer getStock_end () {
		return stock_end;
	}
	
	public void setStock_end (Integer obj) {
		stock_end = obj;
	}
	
	public java.math.BigDecimal getBase_commission () {
		return base_commission;
	}
	
	public void setBase_commission (java.math.BigDecimal obj) {
		base_commission = obj;
	}
	
	public java.math.BigDecimal getPositive_commission () {
		return positive_commission;
	}
	
	public void setPositive_commission (java.math.BigDecimal obj) {
		positive_commission = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
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
		paramMap.put("wms_inve_commission_general_monthly_rules_id", this.wms_inve_commission_general_monthly_rules_id);
		paramMap.put("wms_inve_commission_general_rules_id", this.wms_inve_commission_general_rules_id);
		paramMap.put("stock_begin", this.stock_begin);
		paramMap.put("stock_end", this.stock_end);
		paramMap.put("base_commission", this.base_commission);
		paramMap.put("positive_commission", this.positive_commission);
		paramMap.put("create_user_id", this.create_user_id);
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