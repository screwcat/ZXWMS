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

import com.zx.platform.syscontext.vo.GridParamBean;
import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsInveWagerulesNonlocalHead extends GridParamBean implements BaseEntity{
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_wagerules_nonlocal_head_id;
	
	private Integer compay_id;
	
	private Integer dept_id;
	
	private Integer personnel_postid;
	
	private String bill_code;
	
	private Integer rule_state;
	
	private Integer page_rule_state;
	
	private String start_date;
	
	private String start_date_str;
	
	private String stop_date;
	
	private String stop_date_str;
	
	private String create_datetime;
	
	private String create_datetime_str;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private Integer last_update_user_id;
	
	private String last_update_datetime;
	
	private String last_update_datetime_str;
	
	private String enable_flag;
	
	private boolean isExcludePKFlag;
	
	private String create_timestamp_begin;
	
	private String create_timestamp_end;
	
	private String remark;
	
	
	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	  	"wms_inve_wagerules_nonlocal_head_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_wagerules_nonlocal_head_id",
		"compay_id",
		"dept_id",
		"personnel_postid",
		"bill_code",
		"rule_state",
		"page_rule_state",
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
		"remark",
		"isExcludePKFlag"
	};
  
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getWms_inve_wagerules_nonlocal_head_id () {
		return wms_inve_wagerules_nonlocal_head_id;
	}
	
	public void setWms_inve_wagerules_nonlocal_head_id (Integer obj) {
		wms_inve_wagerules_nonlocal_head_id = obj;
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
	
	public Integer getPersonnel_postid () {
		return personnel_postid;
	}
	
	public void setPersonnel_postid (Integer obj) {
		personnel_postid = obj;
	}
	
	public String getBill_code () {
		return bill_code;
	}
	
	public void setBill_code (String obj) {
		bill_code = obj;
	}
	
	public Integer getRule_state () {
		return rule_state;
	}
	
	public void setRule_state (Integer obj) {
		rule_state = obj;
	}
	
	public Integer getPage_rule_state () {
		return page_rule_state;
	}
	
	public void setPage_rule_state (Integer obj) {
		page_rule_state = obj;
	}
	

	
	public String getStart_date_str () {
		return start_date_str;
	}
	
	public void setStart_date_str (String obj) {
		start_date_str = obj;
	}
	

	
	public String getStop_date_str () {
		return stop_date_str;
	}
	
	public void setStop_date_str (String obj) {
		stop_date_str = obj;
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
	
	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	
	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getStop_date() {
		return stop_date;
	}

	public void setStop_date(String stop_date) {
		this.stop_date = stop_date;
	}

	public String getCreate_datetime() {
		return create_datetime;
	}

	public void setCreate_datetime(String create_datetime) {
		this.create_datetime = create_datetime;
	}

	public String getLast_update_datetime() {
		return last_update_datetime;
	}

	public void setLast_update_datetime(String last_update_datetime) {
		this.last_update_datetime = last_update_datetime;
	}

	public String getCreate_timestamp_begin() {
		return create_timestamp_begin;
	}

	public void setCreate_timestamp_begin(String create_timestamp_begin) {
		this.create_timestamp_begin = create_timestamp_begin;
	}

	public String getCreate_timestamp_end() {
		return create_timestamp_end;
	}

	public void setCreate_timestamp_end(String create_timestamp_end) {
		this.create_timestamp_end = create_timestamp_end;
	}

	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_inve_wagerules_nonlocal_head_id", this.wms_inve_wagerules_nonlocal_head_id);
		paramMap.put("compay_id", this.compay_id);
		paramMap.put("dept_id", this.dept_id);
		paramMap.put("personnel_postid", this.personnel_postid);
		paramMap.put("bill_code", this.bill_code);
		paramMap.put("rule_state", this.rule_state);
		paramMap.put("page_rule_state", this.page_rule_state);
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
		paramMap.put("remark", this.remark);
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