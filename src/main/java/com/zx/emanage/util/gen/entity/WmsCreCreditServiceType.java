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

public class WmsCreCreditServiceType implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_credit_service_type_id;
	
	private Integer wms_cre_credit_head_id;
	
	private Integer pre_wms_cre_credit_head_id;
	
	private Integer old_wms_cre_credit_head_id;
	
	private Integer wms_cre_credit_notary_warn_id;
	
	private Integer pre_wms_cre_credit_notary_warn_id;
	
	private Integer old_wms_cre_credit_notary_warn_id;
	
	private String bill_code;
	
	private java.sql.Timestamp operating_time;
	
	private String operating_time_str;
	
	private String the_number;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
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
	  	"wms_cre_credit_service_type_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_credit_service_type_id",
		"wms_cre_credit_head_id",
		"pre_wms_cre_credit_head_id",
		"old_wms_cre_credit_head_id",
		"wms_cre_credit_notary_warn_id",
		"pre_wms_cre_credit_notary_warn_id",
		"old_wms_cre_credit_notary_warn_id",
		"bill_code",
		"operating_time",
		"operating_time_str",
		"the_number",
		"create_user_id",
		"create_user_name",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_credit_service_type_id () {
		return wms_cre_credit_service_type_id;
	}
	
	public void setWms_cre_credit_service_type_id (Integer obj) {
		wms_cre_credit_service_type_id = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public Integer getPre_wms_cre_credit_head_id () {
		return pre_wms_cre_credit_head_id;
	}
	
	public void setPre_wms_cre_credit_head_id (Integer obj) {
		pre_wms_cre_credit_head_id = obj;
	}
	
	public Integer getOld_wms_cre_credit_head_id () {
		return old_wms_cre_credit_head_id;
	}
	
	public void setOld_wms_cre_credit_head_id (Integer obj) {
		old_wms_cre_credit_head_id = obj;
	}
	
	public Integer getWms_cre_credit_notary_warn_id () {
		return wms_cre_credit_notary_warn_id;
	}
	
	public void setWms_cre_credit_notary_warn_id (Integer obj) {
		wms_cre_credit_notary_warn_id = obj;
	}
	
	public Integer getPre_wms_cre_credit_notary_warn_id () {
		return pre_wms_cre_credit_notary_warn_id;
	}
	
	public void setPre_wms_cre_credit_notary_warn_id (Integer obj) {
		pre_wms_cre_credit_notary_warn_id = obj;
	}
	
	public Integer getOld_wms_cre_credit_notary_warn_id () {
		return old_wms_cre_credit_notary_warn_id;
	}
	
	public void setOld_wms_cre_credit_notary_warn_id (Integer obj) {
		old_wms_cre_credit_notary_warn_id = obj;
	}
	
	public String getBill_code () {
		return bill_code;
	}
	
	public void setBill_code (String obj) {
		bill_code = obj;
	}
	
	public java.sql.Timestamp getOperating_time () {
		return operating_time;
	}
	
	public void setOperating_time (java.sql.Timestamp obj) {
		operating_time = obj;
	}
	
	public String getOperating_time_str () {
		return operating_time_str;
	}
	
	public void setOperating_time_str (String obj) {
		operating_time_str = obj;
	}
	
	public String getThe_number () {
		return the_number;
	}
	
	public void setThe_number (String obj) {
		the_number = obj;
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
	
	public Integer getLast_update_user_id () {
		return last_update_user_id;
	}
	
	public void setLast_update_user_id (Integer obj) {
		last_update_user_id = obj;
	}
	
	public java.sql.Timestamp getLast_update_timestamp () {
		return last_update_timestamp;
	}
	
	public void setLast_update_timestamp (java.sql.Timestamp obj) {
		last_update_timestamp = obj;
	}
	
	public String getLast_update_timestamp_str () {
		return last_update_timestamp_str;
	}
	
	public void setLast_update_timestamp_str (String obj) {
		last_update_timestamp_str = obj;
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
		paramMap.put("wms_cre_credit_service_type_id", this.wms_cre_credit_service_type_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("pre_wms_cre_credit_head_id", this.pre_wms_cre_credit_head_id);
		paramMap.put("old_wms_cre_credit_head_id", this.old_wms_cre_credit_head_id);
		paramMap.put("wms_cre_credit_notary_warn_id", this.wms_cre_credit_notary_warn_id);
		paramMap.put("pre_wms_cre_credit_notary_warn_id", this.pre_wms_cre_credit_notary_warn_id);
		paramMap.put("old_wms_cre_credit_notary_warn_id", this.old_wms_cre_credit_notary_warn_id);
		paramMap.put("bill_code", this.bill_code);
		paramMap.put("operating_time", this.operating_time);
		paramMap.put("operating_time_str", this.operating_time_str);
		paramMap.put("the_number", this.the_number);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_user_name", this.create_user_name);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
		paramMap.put("last_update_user_id", this.last_update_user_id);
		paramMap.put("last_update_timestamp", this.last_update_timestamp);
		paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
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