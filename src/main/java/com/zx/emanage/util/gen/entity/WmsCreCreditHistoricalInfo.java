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

public class WmsCreCreditHistoricalInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_credit_historical_info_id;
	
	private String apply_date;
	
	private String customer_name;
	
	private String community_name;
	
	private String house_address;
	
	private String house_building_area;
	
	private String credit_limit;
	
	private String vehicle_evaluation_price;
	
	private String appro_limit;
	
	private String city;
	
	private String house_unit_price;
	
	private String cre_type;
	
	private String appro_unit_limit;
	
	private String info_remark;
	
	private String enable_flag;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
	private String salesman_name;
	
	private String salesman_shortcode;
	
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
	  	"wms_cre_credit_historical_info_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_credit_historical_info_id",
		"apply_date",
		"customer_name",
		"community_name",
		"house_address",
		"house_building_area",
		"credit_limit",
		"vehicle_evaluation_price",
		"appro_limit",
		"city",
		"house_unit_price",
		"cre_type",
		"appro_unit_limit",
		"info_remark",
		"enable_flag",
		"create_timestamp",
		"create_timestamp_str",
		"create_user_id",
		"create_user_name",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"salesman_name",
		"salesman_shortcode",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_credit_historical_info_id () {
		return wms_cre_credit_historical_info_id;
	}
	
	public void setWms_cre_credit_historical_info_id (Integer obj) {
		wms_cre_credit_historical_info_id = obj;
	}
	
	public String getApply_date () {
		return apply_date;
	}
	
	public void setApply_date (String obj) {
		apply_date = obj;
	}
	
	public String getCustomer_name () {
		return customer_name;
	}
	
	public void setCustomer_name (String obj) {
		customer_name = obj;
	}
	
	public String getCommunity_name () {
		return community_name;
	}
	
	public void setCommunity_name (String obj) {
		community_name = obj;
	}
	
	public String getHouse_address () {
		return house_address;
	}
	
	public void setHouse_address (String obj) {
		house_address = obj;
	}
	
	public String getHouse_building_area () {
		return house_building_area;
	}
	
	public void setHouse_building_area (String obj) {
		house_building_area = obj;
	}
	
	public String getCredit_limit () {
		return credit_limit;
	}
	
	public void setCredit_limit (String obj) {
		credit_limit = obj;
	}
	
	public String getVehicle_evaluation_price () {
		return vehicle_evaluation_price;
	}
	
	public void setVehicle_evaluation_price (String obj) {
		vehicle_evaluation_price = obj;
	}
	
	public String getAppro_limit () {
		return appro_limit;
	}
	
	public void setAppro_limit (String obj) {
		appro_limit = obj;
	}
	
	public String getCity () {
		return city;
	}
	
	public void setCity (String obj) {
		city = obj;
	}
	
	public String getHouse_unit_price () {
		return house_unit_price;
	}
	
	public void setHouse_unit_price (String obj) {
		house_unit_price = obj;
	}
	
	public String getCre_type () {
		return cre_type;
	}
	
	public void setCre_type (String obj) {
		cre_type = obj;
	}
	
	public String getAppro_unit_limit () {
		return appro_unit_limit;
	}
	
	public void setAppro_unit_limit (String obj) {
		appro_unit_limit = obj;
	}
	
	public String getInfo_remark () {
		return info_remark;
	}
	
	public void setInfo_remark (String obj) {
		info_remark = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
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
		paramMap.put("wms_cre_credit_historical_info_id", this.wms_cre_credit_historical_info_id);
		paramMap.put("apply_date", this.apply_date);
		paramMap.put("customer_name", this.customer_name);
		paramMap.put("community_name", this.community_name);
		paramMap.put("house_address", this.house_address);
		paramMap.put("house_building_area", this.house_building_area);
		paramMap.put("credit_limit", this.credit_limit);
		paramMap.put("vehicle_evaluation_price", this.vehicle_evaluation_price);
		paramMap.put("appro_limit", this.appro_limit);
		paramMap.put("city", this.city);
		paramMap.put("house_unit_price", this.house_unit_price);
		paramMap.put("cre_type", this.cre_type);
		paramMap.put("appro_unit_limit", this.appro_unit_limit);
		paramMap.put("info_remark", this.info_remark);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_user_name", this.create_user_name);
		paramMap.put("last_update_user_id", this.last_update_user_id);
		paramMap.put("last_update_timestamp", this.last_update_timestamp);
		paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
		paramMap.put("salesman_name", this.salesman_name);
		paramMap.put("salesman_shortcode", this.salesman_shortcode);
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