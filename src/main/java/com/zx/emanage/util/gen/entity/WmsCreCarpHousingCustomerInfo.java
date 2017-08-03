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

public class WmsCreCarpHousingCustomerInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_carp_housing_customer_info_id;
	
	private Integer wms_cre_credit_head_id;
	
	private Integer wms_cre_customer_change_line_houseinfo_id;
	
	private String house_situation;
	
	private String house_type;
	
	private String built_area;
	
	private String is_house_loan;
	
	private String house_address_province;
	
	private String house_address_city;
	
	private String house_address_area;
	
	private String house_address_other;
	
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
	  	"wms_cre_carp_housing_customer_info_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_carp_housing_customer_info_id",
		"wms_cre_credit_head_id",
		"wms_cre_customer_change_line_houseinfo_id",
		"house_situation",
		"house_type",
		"built_area",
		"is_house_loan",
		"house_address_province",
		"house_address_city",
		"house_address_area",
		"house_address_other",
		"create_user_id",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_carp_housing_customer_info_id () {
		return wms_cre_carp_housing_customer_info_id;
	}
	
	public void setWms_cre_carp_housing_customer_info_id (Integer obj) {
		wms_cre_carp_housing_customer_info_id = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public Integer getWms_cre_customer_change_line_houseinfo_id () {
		return wms_cre_customer_change_line_houseinfo_id;
	}
	
	public void setWms_cre_customer_change_line_houseinfo_id (Integer obj) {
		wms_cre_customer_change_line_houseinfo_id = obj;
	}
	
	public String getHouse_situation () {
		return house_situation;
	}
	
	public void setHouse_situation (String obj) {
		house_situation = obj;
	}
	
	public String getHouse_type () {
		return house_type;
	}
	
	public void setHouse_type (String obj) {
		house_type = obj;
	}
	
	public String getBuilt_area () {
		return built_area;
	}
	
	public void setBuilt_area (String obj) {
		built_area = obj;
	}
	
	public String getIs_house_loan () {
		return is_house_loan;
	}
	
	public void setIs_house_loan (String obj) {
		is_house_loan = obj;
	}
	
	public String getHouse_address_province () {
		return house_address_province;
	}
	
	public void setHouse_address_province (String obj) {
		house_address_province = obj;
	}
	
	public String getHouse_address_city () {
		return house_address_city;
	}
	
	public void setHouse_address_city (String obj) {
		house_address_city = obj;
	}
	
	public String getHouse_address_area () {
		return house_address_area;
	}
	
	public void setHouse_address_area (String obj) {
		house_address_area = obj;
	}
	
	public String getHouse_address_other () {
		return house_address_other;
	}
	
	public void setHouse_address_other (String obj) {
		house_address_other = obj;
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
		paramMap.put("wms_cre_carp_housing_customer_info_id", this.wms_cre_carp_housing_customer_info_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("wms_cre_customer_change_line_houseinfo_id", this.wms_cre_customer_change_line_houseinfo_id);
		paramMap.put("house_situation", this.house_situation);
		paramMap.put("house_type", this.house_type);
		paramMap.put("built_area", this.built_area);
		paramMap.put("is_house_loan", this.is_house_loan);
		paramMap.put("house_address_province", this.house_address_province);
		paramMap.put("house_address_city", this.house_address_city);
		paramMap.put("house_address_area", this.house_address_area);
		paramMap.put("house_address_other", this.house_address_other);
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