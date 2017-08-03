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

public class WmsInveSalary implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_salary_id;
	
	private Integer personnel_id;
	
	private Integer personnel_deptid;
	
	private Integer personnel_postid;
	
	private Integer personnel_state;
	
	private java.math.BigDecimal base_salary;
	
	private java.math.BigDecimal performance_salary;
	
	private java.math.BigDecimal train_salary;
	
	private java.math.BigDecimal post_salary;
	
	private java.math.BigDecimal adjust_amount;
	
	private String statics_month;
	
	private java.sql.Timestamp statics_time;
	
	private String statics_time_str;
	
	private java.math.BigDecimal per_add_base;
	
	private java.math.BigDecimal per_add_deal;
	
	private java.math.BigDecimal per_add_base_querter;
	
	private java.math.BigDecimal per_add_deal_querter;
	
	private java.math.BigDecimal per_stock_all;
	
	private java.math.BigDecimal per_stock_new;
	
	private java.math.BigDecimal team_clear_add;
	
	private java.math.BigDecimal team_clear_add_quarter;
	
	private java.math.BigDecimal team_staff_num_regular_quarter;
	
	private java.sql.Timestamp personnel_trialstarttime;
	
	private String personnel_trialstarttime_str;
	
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
	  	"wms_inve_salary_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_salary_id",
		"personnel_id",
		"personnel_deptid",
		"personnel_postid",
		"personnel_state",
		"base_salary",
		"performance_salary",
		"train_salary",
		"post_salary",
		"adjust_amount",
		"statics_month",
		"statics_time",
		"statics_time_str",
		"per_add_base",
		"per_add_deal",
		"per_add_base_querter",
		"per_add_deal_querter",
		"per_stock_all",
		"per_stock_new",
		"team_clear_add",
		"team_clear_add_quarter",
		"team_staff_num_regular_quarter",
		"personnel_trialstarttime",
		"personnel_trialstarttime_str",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_salary_id () {
		return wms_inve_salary_id;
	}
	
	public void setWms_inve_salary_id (Integer obj) {
		wms_inve_salary_id = obj;
	}
	
	public Integer getPersonnel_id () {
		return personnel_id;
	}
	
	public void setPersonnel_id (Integer obj) {
		personnel_id = obj;
	}
	
	public Integer getPersonnel_deptid () {
		return personnel_deptid;
	}
	
	public void setPersonnel_deptid (Integer obj) {
		personnel_deptid = obj;
	}
	
	public Integer getPersonnel_postid () {
		return personnel_postid;
	}
	
	public void setPersonnel_postid (Integer obj) {
		personnel_postid = obj;
	}
	
	public Integer getPersonnel_state () {
		return personnel_state;
	}
	
	public void setPersonnel_state (Integer obj) {
		personnel_state = obj;
	}
	
	public java.math.BigDecimal getBase_salary () {
		return base_salary;
	}
	
	public void setBase_salary (java.math.BigDecimal obj) {
		base_salary = obj;
	}
	
	public java.math.BigDecimal getPerformance_salary () {
		return performance_salary;
	}
	
	public void setPerformance_salary (java.math.BigDecimal obj) {
		performance_salary = obj;
	}
	
	public java.math.BigDecimal getTrain_salary () {
		return train_salary;
	}
	
	public void setTrain_salary (java.math.BigDecimal obj) {
		train_salary = obj;
	}
	
	public java.math.BigDecimal getPost_salary () {
		return post_salary;
	}
	
	public void setPost_salary (java.math.BigDecimal obj) {
		post_salary = obj;
	}
	
	public java.math.BigDecimal getAdjust_amount () {
		return adjust_amount;
	}
	
	public void setAdjust_amount (java.math.BigDecimal obj) {
		adjust_amount = obj;
	}
	
	public String getStatics_month () {
		return statics_month;
	}
	
	public void setStatics_month (String obj) {
		statics_month = obj;
	}
	
	public java.sql.Timestamp getStatics_time () {
		return statics_time;
	}
	
	public void setStatics_time (java.sql.Timestamp obj) {
		statics_time = obj;
	}
	
	public String getStatics_time_str () {
		return statics_time_str;
	}
	
	public void setStatics_time_str (String obj) {
		statics_time_str = obj;
	}
	
	public java.math.BigDecimal getPer_add_base () {
		return per_add_base;
	}
	
	public void setPer_add_base (java.math.BigDecimal obj) {
		per_add_base = obj;
	}
	
	public java.math.BigDecimal getPer_add_deal () {
		return per_add_deal;
	}
	
	public void setPer_add_deal (java.math.BigDecimal obj) {
		per_add_deal = obj;
	}
	
	public java.math.BigDecimal getPer_add_base_querter () {
		return per_add_base_querter;
	}
	
	public void setPer_add_base_querter (java.math.BigDecimal obj) {
		per_add_base_querter = obj;
	}
	
	public java.math.BigDecimal getPer_add_deal_querter () {
		return per_add_deal_querter;
	}
	
	public void setPer_add_deal_querter (java.math.BigDecimal obj) {
		per_add_deal_querter = obj;
	}
	
	public java.math.BigDecimal getPer_stock_all () {
		return per_stock_all;
	}
	
	public void setPer_stock_all (java.math.BigDecimal obj) {
		per_stock_all = obj;
	}
	
	public java.math.BigDecimal getPer_stock_new () {
		return per_stock_new;
	}
	
	public void setPer_stock_new (java.math.BigDecimal obj) {
		per_stock_new = obj;
	}
	
	public java.math.BigDecimal getTeam_clear_add () {
		return team_clear_add;
	}
	
	public void setTeam_clear_add (java.math.BigDecimal obj) {
		team_clear_add = obj;
	}
	
	public java.math.BigDecimal getTeam_clear_add_quarter () {
		return team_clear_add_quarter;
	}
	
	public void setTeam_clear_add_quarter (java.math.BigDecimal obj) {
		team_clear_add_quarter = obj;
	}
	
	public java.math.BigDecimal getTeam_staff_num_regular_quarter () {
		return team_staff_num_regular_quarter;
	}
	
	public void setTeam_staff_num_regular_quarter (java.math.BigDecimal obj) {
		team_staff_num_regular_quarter = obj;
	}
	
	public java.sql.Timestamp getPersonnel_trialstarttime () {
		return personnel_trialstarttime;
	}
	
	public void setPersonnel_trialstarttime (java.sql.Timestamp obj) {
		personnel_trialstarttime = obj;
	}
	
	public String getPersonnel_trialstarttime_str () {
		return personnel_trialstarttime_str;
	}
	
	public void setPersonnel_trialstarttime_str (String obj) {
		personnel_trialstarttime_str = obj;
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
		paramMap.put("wms_inve_salary_id", this.wms_inve_salary_id);
		paramMap.put("personnel_id", this.personnel_id);
		paramMap.put("personnel_deptid", this.personnel_deptid);
		paramMap.put("personnel_postid", this.personnel_postid);
		paramMap.put("personnel_state", this.personnel_state);
		paramMap.put("base_salary", this.base_salary);
		paramMap.put("performance_salary", this.performance_salary);
		paramMap.put("train_salary", this.train_salary);
		paramMap.put("post_salary", this.post_salary);
		paramMap.put("adjust_amount", this.adjust_amount);
		paramMap.put("statics_month", this.statics_month);
		paramMap.put("statics_time", this.statics_time);
		paramMap.put("statics_time_str", this.statics_time_str);
		paramMap.put("per_add_base", this.per_add_base);
		paramMap.put("per_add_deal", this.per_add_deal);
		paramMap.put("per_add_base_querter", this.per_add_base_querter);
		paramMap.put("per_add_deal_querter", this.per_add_deal_querter);
		paramMap.put("per_stock_all", this.per_stock_all);
		paramMap.put("per_stock_new", this.per_stock_new);
		paramMap.put("team_clear_add", this.team_clear_add);
		paramMap.put("team_clear_add_quarter", this.team_clear_add_quarter);
		paramMap.put("team_staff_num_regular_quarter", this.team_staff_num_regular_quarter);
		paramMap.put("personnel_trialstarttime", this.personnel_trialstarttime);
		paramMap.put("personnel_trialstarttime_str", this.personnel_trialstarttime_str);
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