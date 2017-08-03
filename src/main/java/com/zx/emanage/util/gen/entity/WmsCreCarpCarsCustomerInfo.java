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

public class WmsCreCarpCarsCustomerInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_carp_cars_customer_info_id;
	
	private Integer wms_cre_credit_head_id;
	
	private Integer wms_cre_customer_change_line_carpinfo_id;
	
	private String car_name;
	
	private String car_no;
	
	private java.sql.Date car_purchase_time;
	
	private String car_purchase_time_str;
	
	private Integer create_user_id;
	
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
	  	"wms_cre_carp_cars_customer_info_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_carp_cars_customer_info_id",
		"wms_cre_credit_head_id",
		"wms_cre_customer_change_line_carpinfo_id",
		"car_name",
		"car_no",
		"car_purchase_time",
		"car_purchase_time_str",
		"create_user_id",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_carp_cars_customer_info_id () {
		return wms_cre_carp_cars_customer_info_id;
	}
	
	public void setWms_cre_carp_cars_customer_info_id (Integer obj) {
		wms_cre_carp_cars_customer_info_id = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public Integer getWms_cre_customer_change_line_carpinfo_id () {
		return wms_cre_customer_change_line_carpinfo_id;
	}
	
	public void setWms_cre_customer_change_line_carpinfo_id (Integer obj) {
		wms_cre_customer_change_line_carpinfo_id = obj;
	}
	
	public String getCar_name () {
		return car_name;
	}
	
	public void setCar_name (String obj) {
		car_name = obj;
	}
	
	public String getCar_no () {
		return car_no;
	}
	
	public void setCar_no (String obj) {
		car_no = obj;
	}
	
	public java.sql.Date getCar_purchase_time () {
		return car_purchase_time;
	}
	
	public void setCar_purchase_time (java.sql.Date obj) {
		car_purchase_time = obj;
	}
	
	public String getCar_purchase_time_str () {
		return car_purchase_time_str;
	}
	
	public void setCar_purchase_time_str (String obj) {
		car_purchase_time_str = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
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
		paramMap.put("wms_cre_carp_cars_customer_info_id", this.wms_cre_carp_cars_customer_info_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("wms_cre_customer_change_line_carpinfo_id", this.wms_cre_customer_change_line_carpinfo_id);
		paramMap.put("car_name", this.car_name);
		paramMap.put("car_no", this.car_no);
		paramMap.put("car_purchase_time", this.car_purchase_time);
		paramMap.put("car_purchase_time_str", this.car_purchase_time_str);
		paramMap.put("create_user_id", this.create_user_id);
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