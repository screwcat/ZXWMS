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

public class WmsInveCustomerWx implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_customer_wx_id;
	
	private String phone_number;
	
	private String password;
	
	private String cus_name;
	
	private Integer costomer_id;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
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
	  	"wms_inve_customer_wx_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_customer_wx_id",
		"phone_number",
		"password",
		"cus_name",
		"costomer_id",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_customer_wx_id () {
		return wms_inve_customer_wx_id;
	}
	
	public void setWms_inve_customer_wx_id (Integer obj) {
		wms_inve_customer_wx_id = obj;
	}
	
	public String getPhone_number () {
		return phone_number;
	}
	
	public void setPhone_number (String obj) {
		phone_number = obj;
	}
	
	public String getPassword () {
		return password;
	}
	
	public void setPassword (String obj) {
		password = obj;
	}
	
	public String getCus_name () {
		return cus_name;
	}
	
	public void setCus_name (String obj) {
		cus_name = obj;
	}
	
	public Integer getCostomer_id () {
		return costomer_id;
	}
	
	public void setCostomer_id (Integer obj) {
		costomer_id = obj;
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
		paramMap.put("wms_inve_customer_wx_id", this.wms_inve_customer_wx_id);
		paramMap.put("phone_number", this.phone_number);
		paramMap.put("password", this.password);
		paramMap.put("cus_name", this.cus_name);
		paramMap.put("costomer_id", this.costomer_id);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
		paramMap.put("last_update_timestamp", this.last_update_timestamp);
		paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
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