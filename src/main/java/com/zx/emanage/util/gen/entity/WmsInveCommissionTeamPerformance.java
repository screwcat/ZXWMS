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

public class WmsInveCommissionTeamPerformance implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_commission_team_performance_id;
	
	private Integer dept_id;
	
	private Integer team_id;
	
	private Integer team_user_id;
	
	private java.math.BigDecimal float_disposable_commission_coeff;
	
	private java.math.BigDecimal float_disposable_commission_money;
	
	private java.math.BigDecimal float_monthly_commission_coeff;
	
	private java.math.BigDecimal float_monthly_commission_money;
	
	private java.sql.Timestamp create_datetime;
	
	private String create_datetime_str;
	
	private String enable_flag;
	
	private java.sql.Date lssue_date;
	
	private String lssue_date_str;
	
	private Integer wms_inve_pruduct_category_id;
	
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
	  	"wms_inve_commission_team_performance_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_commission_team_performance_id",
		"dept_id",
		"team_id",
		"team_user_id",
		"float_disposable_commission_coeff",
		"float_disposable_commission_money",
		"float_monthly_commission_coeff",
		"float_monthly_commission_money",
		"create_datetime",
		"create_datetime_str",
		"enable_flag",
		"lssue_date",
		"lssue_date_str",
		"wms_inve_pruduct_category_id",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_commission_team_performance_id () {
		return wms_inve_commission_team_performance_id;
	}
	
	public void setWms_inve_commission_team_performance_id (Integer obj) {
		wms_inve_commission_team_performance_id = obj;
	}
	
	public Integer getDept_id () {
		return dept_id;
	}
	
	public void setDept_id (Integer obj) {
		dept_id = obj;
	}
	
	public Integer getTeam_id () {
		return team_id;
	}
	
	public void setTeam_id (Integer obj) {
		team_id = obj;
	}
	
	public Integer getTeam_user_id () {
		return team_user_id;
	}
	
	public void setTeam_user_id (Integer obj) {
		team_user_id = obj;
	}
	
	public java.math.BigDecimal getFloat_disposable_commission_coeff () {
		return float_disposable_commission_coeff;
	}
	
	public void setFloat_disposable_commission_coeff (java.math.BigDecimal obj) {
		float_disposable_commission_coeff = obj;
	}
	
	public java.math.BigDecimal getFloat_disposable_commission_money () {
		return float_disposable_commission_money;
	}
	
	public void setFloat_disposable_commission_money (java.math.BigDecimal obj) {
		float_disposable_commission_money = obj;
	}
	
	public java.math.BigDecimal getFloat_monthly_commission_coeff () {
		return float_monthly_commission_coeff;
	}
	
	public void setFloat_monthly_commission_coeff (java.math.BigDecimal obj) {
		float_monthly_commission_coeff = obj;
	}
	
	public java.math.BigDecimal getFloat_monthly_commission_money () {
		return float_monthly_commission_money;
	}
	
	public void setFloat_monthly_commission_money (java.math.BigDecimal obj) {
		float_monthly_commission_money = obj;
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
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public java.sql.Date getLssue_date () {
		return lssue_date;
	}
	
	public void setLssue_date (java.sql.Date obj) {
		lssue_date = obj;
	}
	
	public String getLssue_date_str () {
		return lssue_date_str;
	}
	
	public void setLssue_date_str (String obj) {
		lssue_date_str = obj;
	}
	
	public Integer getWms_inve_pruduct_category_id () {
		return wms_inve_pruduct_category_id;
	}
	
	public void setWms_inve_pruduct_category_id (Integer obj) {
		wms_inve_pruduct_category_id = obj;
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
		paramMap.put("wms_inve_commission_team_performance_id", this.wms_inve_commission_team_performance_id);
		paramMap.put("dept_id", this.dept_id);
		paramMap.put("team_id", this.team_id);
		paramMap.put("team_user_id", this.team_user_id);
		paramMap.put("float_disposable_commission_coeff", this.float_disposable_commission_coeff);
		paramMap.put("float_disposable_commission_money", this.float_disposable_commission_money);
		paramMap.put("float_monthly_commission_coeff", this.float_monthly_commission_coeff);
		paramMap.put("float_monthly_commission_money", this.float_monthly_commission_money);
		paramMap.put("create_datetime", this.create_datetime);
		paramMap.put("create_datetime_str", this.create_datetime_str);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("lssue_date", this.lssue_date);
		paramMap.put("lssue_date_str", this.lssue_date_str);
		paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
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