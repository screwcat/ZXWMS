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

public class WmsCreCreditReturnSearch implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_credit_return_search_id;
	
	private Integer wms_cre_credit_head_id;
	
	private String bill_code;
	
	private java.sql.Timestamp head_create_datetime;
	
	private String head_create_datetime_str;
	
	private String cre_type;
	
	private String cre_loan_type;
	
	private String cus_name;
	
	private Double credit_limit;
	
	private String audit_group;
	
	private String audit_reason;
	
	private java.sql.Timestamp audit_time;
	
	private String audit_time_str;
	
	private String city_name;
	
	private String city_code;
	
	private String store_name;
	
	private Integer store_deptid;
	
	private String create_user_name;
	
	private Integer create_user_id;
	
	private String salesman_name;
	
	private Integer salesman_id;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
	private java.sql.Timestamp last_update_datetamp;
	
	private String last_update_datetamp_str;
	
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
	  	"wms_cre_credit_return_search_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_credit_return_search_id",
		"wms_cre_credit_head_id",
		"bill_code",
		"head_create_datetime",
		"head_create_datetime_str",
		"cre_type",
		"cre_loan_type",
		"cus_name",
		"credit_limit",
		"audit_group",
		"audit_reason",
		"audit_time",
		"audit_time_str",
		"city_name",
		"city_code",
		"store_name",
		"store_deptid",
		"create_user_name",
		"create_user_id",
		"salesman_name",
		"salesman_id",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_datetamp",
		"last_update_datetamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_credit_return_search_id () {
		return wms_cre_credit_return_search_id;
	}
	
	public void setWms_cre_credit_return_search_id (Integer obj) {
		wms_cre_credit_return_search_id = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public String getBill_code () {
		return bill_code;
	}
	
	public void setBill_code (String obj) {
		bill_code = obj;
	}
	
	public java.sql.Timestamp getHead_create_datetime () {
		return head_create_datetime;
	}
	
	public void setHead_create_datetime (java.sql.Timestamp obj) {
		head_create_datetime = obj;
	}
	
	public String getHead_create_datetime_str () {
		return head_create_datetime_str;
	}
	
	public void setHead_create_datetime_str (String obj) {
		head_create_datetime_str = obj;
	}
	
	public String getCre_type () {
		return cre_type;
	}
	
	public void setCre_type (String obj) {
		cre_type = obj;
	}
	
	public String getCre_loan_type () {
		return cre_loan_type;
	}
	
	public void setCre_loan_type (String obj) {
		cre_loan_type = obj;
	}
	
	public String getCus_name () {
		return cus_name;
	}
	
	public void setCus_name (String obj) {
		cus_name = obj;
	}
	
	public Double getCredit_limit () {
		return credit_limit;
	}
	
	public void setCredit_limit (Double obj) {
		credit_limit = obj;
	}
	
	public String getAudit_group () {
		return audit_group;
	}
	
	public void setAudit_group (String obj) {
		audit_group = obj;
	}
	
	public String getAudit_reason () {
		return audit_reason;
	}
	
	public void setAudit_reason (String obj) {
		audit_reason = obj;
	}
	
	public java.sql.Timestamp getAudit_time () {
		return audit_time;
	}
	
	public void setAudit_time (java.sql.Timestamp obj) {
		audit_time = obj;
	}
	
	public String getAudit_time_str () {
		return audit_time_str;
	}
	
	public void setAudit_time_str (String obj) {
		audit_time_str = obj;
	}
	
	public String getCity_name () {
		return city_name;
	}
	
	public void setCity_name (String obj) {
		city_name = obj;
	}
	
	public String getCity_code () {
		return city_code;
	}
	
	public void setCity_code (String obj) {
		city_code = obj;
	}
	
	public String getStore_name () {
		return store_name;
	}
	
	public void setStore_name (String obj) {
		store_name = obj;
	}
	
	public Integer getStore_deptid () {
		return store_deptid;
	}
	
	public void setStore_deptid (Integer obj) {
		store_deptid = obj;
	}
	
	public String getCreate_user_name () {
		return create_user_name;
	}
	
	public void setCreate_user_name (String obj) {
		create_user_name = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
	}
	
	public String getSalesman_name () {
		return salesman_name;
	}
	
	public void setSalesman_name (String obj) {
		salesman_name = obj;
	}
	
	public Integer getSalesman_id () {
		return salesman_id;
	}
	
	public void setSalesman_id (Integer obj) {
		salesman_id = obj;
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
	
	public java.sql.Timestamp getLast_update_datetamp () {
		return last_update_datetamp;
	}
	
	public void setLast_update_datetamp (java.sql.Timestamp obj) {
		last_update_datetamp = obj;
	}
	
	public String getLast_update_datetamp_str () {
		return last_update_datetamp_str;
	}
	
	public void setLast_update_datetamp_str (String obj) {
		last_update_datetamp_str = obj;
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
		paramMap.put("wms_cre_credit_return_search_id", this.wms_cre_credit_return_search_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("bill_code", this.bill_code);
		paramMap.put("head_create_datetime", this.head_create_datetime);
		paramMap.put("head_create_datetime_str", this.head_create_datetime_str);
		paramMap.put("cre_type", this.cre_type);
		paramMap.put("cre_loan_type", this.cre_loan_type);
		paramMap.put("cus_name", this.cus_name);
		paramMap.put("credit_limit", this.credit_limit);
		paramMap.put("audit_group", this.audit_group);
		paramMap.put("audit_reason", this.audit_reason);
		paramMap.put("audit_time", this.audit_time);
		paramMap.put("audit_time_str", this.audit_time_str);
		paramMap.put("city_name", this.city_name);
		paramMap.put("city_code", this.city_code);
		paramMap.put("store_name", this.store_name);
		paramMap.put("store_deptid", this.store_deptid);
		paramMap.put("create_user_name", this.create_user_name);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("salesman_name", this.salesman_name);
		paramMap.put("salesman_id", this.salesman_id);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
		paramMap.put("last_update_datetamp", this.last_update_datetamp);
		paramMap.put("last_update_datetamp_str", this.last_update_datetamp_str);
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