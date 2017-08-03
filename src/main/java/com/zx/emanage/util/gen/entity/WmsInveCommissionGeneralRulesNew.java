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

public class WmsInveCommissionGeneralRulesNew implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_commission_general_rules_new_id;
	
	private Integer job_code;
	
	private Integer employee_state;
	
	private Integer rule_state;
	
	private Integer page_rule_sate;
	
	private java.sql.Date start_date;
	
	private String start_date_str;
	
	private java.sql.Date stop_date;
	
	private String stop_date_str;
	
	private java.sql.Timestamp create_datetime;
	
	private String create_datetime_str;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_datetime;
	
	private String last_update_datetime_str;
	
	private String enable_flag;
	
	private java.math.BigDecimal commission_disposable;
	
	private java.math.BigDecimal remote_commission_rules;
	
	private Integer belonging_company;
	
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
	  	"wms_inve_commission_general_rules_new_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_commission_general_rules_new_id",
		"job_code",
		"employee_state",
		"rule_state",
		"page_rule_sate",
		"start_date",
		"start_date_str",
		"stop_date",
		"stop_date_str",
		"create_datetime",
		"create_datetime_str",
		"create_user_id",
		"create_user_name",
		"last_update_user_id",
		"last_update_datetime",
		"last_update_datetime_str",
		"enable_flag",
		"commission_disposable",
		"remote_commission_rules",
		"belonging_company",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_commission_general_rules_new_id () {
		return wms_inve_commission_general_rules_new_id;
	}
	
	public void setWms_inve_commission_general_rules_new_id (Integer obj) {
		wms_inve_commission_general_rules_new_id = obj;
	}
	
	public Integer getJob_code () {
		return job_code;
	}
	
	public void setJob_code (Integer obj) {
		job_code = obj;
	}
	
	public Integer getEmployee_state () {
		return employee_state;
	}
	
	public void setEmployee_state (Integer obj) {
		employee_state = obj;
	}
	
	public Integer getRule_state () {
		return rule_state;
	}
	
	public void setRule_state (Integer obj) {
		rule_state = obj;
	}
	
	public Integer getPage_rule_sate () {
		return page_rule_sate;
	}
	
	public void setPage_rule_sate (Integer obj) {
		page_rule_sate = obj;
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
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
	}
	
	public String getCreate_user_name () {
		return create_user_name;
	}
	
	public void setCreate_user_name (String obj) {
		create_user_name = obj;
	}
	
	public Integer getLast_update_user_id () {
		return last_update_user_id;
	}
	
	public void setLast_update_user_id (Integer obj) {
		last_update_user_id = obj;
	}
	
	public java.sql.Timestamp getLast_update_datetime () {
		return last_update_datetime;
	}
	
	public void setLast_update_datetime (java.sql.Timestamp obj) {
		last_update_datetime = obj;
	}
	
	public String getLast_update_datetime_str () {
		return last_update_datetime_str;
	}
	
	public void setLast_update_datetime_str (String obj) {
		last_update_datetime_str = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public java.math.BigDecimal getCommission_disposable () {
		return commission_disposable;
	}
	
	public void setCommission_disposable (java.math.BigDecimal obj) {
		commission_disposable = obj;
	}
	
	public java.math.BigDecimal getRemote_commission_rules () {
		return remote_commission_rules;
	}
	
	public void setRemote_commission_rules (java.math.BigDecimal obj) {
		remote_commission_rules = obj;
	}
	
	public Integer getBelonging_company () {
		return belonging_company;
	}
	
	public void setBelonging_company (Integer obj) {
		belonging_company = obj;
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
		paramMap.put("wms_inve_commission_general_rules_new_id", this.wms_inve_commission_general_rules_new_id);
		paramMap.put("job_code", this.job_code);
		paramMap.put("employee_state", this.employee_state);
		paramMap.put("rule_state", this.rule_state);
		paramMap.put("page_rule_sate", this.page_rule_sate);
		paramMap.put("start_date", this.start_date);
		paramMap.put("start_date_str", this.start_date_str);
		paramMap.put("stop_date", this.stop_date);
		paramMap.put("stop_date_str", this.stop_date_str);
		paramMap.put("create_datetime", this.create_datetime);
		paramMap.put("create_datetime_str", this.create_datetime_str);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_user_name", this.create_user_name);
		paramMap.put("last_update_user_id", this.last_update_user_id);
		paramMap.put("last_update_datetime", this.last_update_datetime);
		paramMap.put("last_update_datetime_str", this.last_update_datetime_str);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("commission_disposable", this.commission_disposable);
		paramMap.put("remote_commission_rules", this.remote_commission_rules);
		paramMap.put("belonging_company", this.belonging_company);
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