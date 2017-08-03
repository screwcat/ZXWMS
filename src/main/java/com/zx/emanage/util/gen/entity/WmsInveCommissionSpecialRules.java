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

public class WmsInveCommissionSpecialRules implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_commission_special_rules_id;
	
	private Integer wms_inve_pruduct_category_id;
	
	private java.sql.Date single_start_date;
	
	private String single_start_date_str;
	
	private java.sql.Date single_end_date;
	
	private String single_end_date_str;
	
	private java.math.BigDecimal cumulative_stock_begin;
	
	private java.math.BigDecimal cumulative_stock_end;
	
	private String commission_type;
	
	private java.math.BigDecimal commission_coeff;
	
	private String rule_state;

	private String page_rule_state;
	
	private String enable_flag;
	
	private java.sql.Date start_date;
	
	private String start_date_str;
	
	private java.sql.Date stop_date;
	
	private String stop_date_str;
	
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
	  	"wms_inve_commission_special_rules_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_commission_special_rules_id",
		"wms_inve_pruduct_category_id",
		"single_start_date",
		"single_start_date_str",
		"single_end_date",
		"single_end_date_str",
		"cumulative_stock_begin",
		"cumulative_stock_end",
		"commission_type",
		"commission_coeff",
		"rule_state",
		"page_rule_state",
		"enable_flag",
		"start_date",
		"start_date_str",
		"stop_date",
		"stop_date_str",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_commission_special_rules_id () {
		return wms_inve_commission_special_rules_id;
	}
	
	public void setWms_inve_commission_special_rules_id (Integer obj) {
		wms_inve_commission_special_rules_id = obj;
	}
	
	public Integer getWms_inve_pruduct_category_id () {
		return wms_inve_pruduct_category_id;
	}
	
	public void setWms_inve_pruduct_category_id (Integer obj) {
		wms_inve_pruduct_category_id = obj;
	}
	
	public java.sql.Date getSingle_start_date () {
		return single_start_date;
	}
	
	public void setSingle_start_date (java.sql.Date obj) {
		single_start_date = obj;
	}
	
	public String getSingle_start_date_str () {
		return single_start_date_str;
	}
	
	public void setSingle_start_date_str (String obj) {
		single_start_date_str = obj;
	}
	
	public java.sql.Date getSingle_end_date () {
		return single_end_date;
	}
	
	public void setSingle_end_date (java.sql.Date obj) {
		single_end_date = obj;
	}
	
	public String getSingle_end_date_str () {
		return single_end_date_str;
	}
	
	public void setSingle_end_date_str (String obj) {
		single_end_date_str = obj;
	}
	
	public java.math.BigDecimal getCumulative_stock_begin () {
		return cumulative_stock_begin;
	}
	
	public void setCumulative_stock_begin (java.math.BigDecimal obj) {
		cumulative_stock_begin = obj;
	}
	
	public java.math.BigDecimal getCumulative_stock_end () {
		return cumulative_stock_end;
	}
	
	public void setCumulative_stock_end (java.math.BigDecimal obj) {
		cumulative_stock_end = obj;
	}
	
	public String getCommission_type () {
		return commission_type;
	}
	
	public void setCommission_type (String obj) {
		commission_type = obj;
	}
	
	public java.math.BigDecimal getCommission_coeff () {
		return commission_coeff;
	}
	
	public void setCommission_coeff (java.math.BigDecimal obj) {
		commission_coeff = obj;
	}
	
	public String getRule_state () {
		return rule_state;
	}
	
	public void setRule_state (String obj) {
		rule_state = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public java.sql.Date getStart_date () {
		return start_date;
	}
	
	public void setStart_date (java.sql.Date obj) {
		start_date = obj;
	}
	
	public String getStart_date_str () {
		return start_date_str;
	}
	
	public void setStart_date_str (String obj) {
		start_date_str = obj;
	}
	
	public java.sql.Date getStop_date () {
		return stop_date;
	}
	
	public void setStop_date (java.sql.Date obj) {
		stop_date = obj;
	}
	
	public String getStop_date_str () {
		return stop_date_str;
	}
	
	public void setStop_date_str (String obj) {
		stop_date_str = obj;
	}
	
	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	public String getPage_rule_state() {
		return page_rule_state;
	}

	public void setPage_rule_state(String page_rule_state) {
		this.page_rule_state = page_rule_state;
	}

	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_inve_commission_special_rules_id", this.wms_inve_commission_special_rules_id);
		paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
		paramMap.put("single_start_date", this.single_start_date);
		paramMap.put("single_start_date_str", this.single_start_date_str);
		paramMap.put("single_end_date", this.single_end_date);
		paramMap.put("single_end_date_str", this.single_end_date_str);
		paramMap.put("cumulative_stock_begin", this.cumulative_stock_begin);
		paramMap.put("cumulative_stock_end", this.cumulative_stock_end);
		paramMap.put("commission_type", this.commission_type);
		paramMap.put("commission_coeff", this.commission_coeff);
		paramMap.put("rule_state", this.rule_state);
		paramMap.put("page_rule_state", this.page_rule_state);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("start_date", this.start_date);
		paramMap.put("start_date_str", this.start_date_str);
		paramMap.put("stop_date", this.stop_date);
		paramMap.put("stop_date_str", this.stop_date_str);
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