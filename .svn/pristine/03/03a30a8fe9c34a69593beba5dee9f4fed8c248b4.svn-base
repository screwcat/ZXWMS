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

public class WmsInveWagePerformanceNonlocal implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_wage_performance_nonlocal_id;
	
	private Integer compay_id;
	
	private Integer dept_id;
	
	private Integer team_id;
	
	private String salesman_name;
	
	private String salesman_shortcode;
	
	private Integer salesman_id;
	
	private Integer job_id;
	
	private java.math.BigDecimal base_wage;
	
	private Integer personnel_state;
	
	private java.sql.Timestamp create_datetime;
	
	private String create_datetime_str;
	
	private String remark;
	
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
	  	"wms_inve_wage_performance_nonlocal_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_wage_performance_nonlocal_id",
		"compay_id",
		"dept_id",
		"team_id",
		"salesman_name",
		"salesman_shortcode",
		"salesman_id",
		"job_id",
		"base_wage",
		"personnel_state",
		"create_datetime",
		"create_datetime_str",
		"remark",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_wage_performance_nonlocal_id () {
		return wms_inve_wage_performance_nonlocal_id;
	}
	
	public void setWms_inve_wage_performance_nonlocal_id (Integer obj) {
		wms_inve_wage_performance_nonlocal_id = obj;
	}
	
	public Integer getCompay_id () {
		return compay_id;
	}
	
	public void setCompay_id (Integer obj) {
		compay_id = obj;
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
	
	public String getSalesman_name () {
		return salesman_name;
	}
	
	public void setSalesman_name (String obj) {
		salesman_name = obj;
	}
	
	public String getSalesman_shortcode () {
		return salesman_shortcode;
	}
	
	public void setSalesman_shortcode (String obj) {
		salesman_shortcode = obj;
	}
	
	public Integer getSalesman_id () {
		return salesman_id;
	}
	
	public void setSalesman_id (Integer obj) {
		salesman_id = obj;
	}
	
	public Integer getJob_id () {
		return job_id;
	}
	
	public void setJob_id (Integer obj) {
		job_id = obj;
	}
	
	public java.math.BigDecimal getBase_wage () {
		return base_wage;
	}
	
	public void setBase_wage (java.math.BigDecimal obj) {
		base_wage = obj;
	}
	
	public Integer getPersonnel_state () {
		return personnel_state;
	}
	
	public void setPersonnel_state (Integer obj) {
		personnel_state = obj;
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
	
	public String getRemark () {
		return remark;
	}
	
	public void setRemark (String obj) {
		remark = obj;
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
		paramMap.put("wms_inve_wage_performance_nonlocal_id", this.wms_inve_wage_performance_nonlocal_id);
		paramMap.put("compay_id", this.compay_id);
		paramMap.put("dept_id", this.dept_id);
		paramMap.put("team_id", this.team_id);
		paramMap.put("salesman_name", this.salesman_name);
		paramMap.put("salesman_shortcode", this.salesman_shortcode);
		paramMap.put("salesman_id", this.salesman_id);
		paramMap.put("job_id", this.job_id);
		paramMap.put("base_wage", this.base_wage);
		paramMap.put("personnel_state", this.personnel_state);
		paramMap.put("create_datetime", this.create_datetime);
		paramMap.put("create_datetime_str", this.create_datetime_str);
		paramMap.put("remark", this.remark);
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