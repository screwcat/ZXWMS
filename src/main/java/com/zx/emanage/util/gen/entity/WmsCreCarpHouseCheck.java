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

public class WmsCreCarpHouseCheck implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_carp_house_check_id;
	
	private Integer wms_cre_credit_head_id;
	
	private String customer_name;
	
	private String house_address;
	
	private Double house_building_area;
	
	private Integer total_floor;
	
	private String house_layer;
	
	private String community_name;
	
	private Integer housing_pattern;
	
	private Integer building_age;
	
	private Integer decoration_standard;
	
	private Integer house_usage;
	
	private Double online_fold;
	
	private Double house_transaction_price;
	
	private String is_active;
	
	private String house_people_review;
	
	private Double rental_price;
	
	private String housing_towards;
	
	private String common_occupants;
	
	private String residential_environ;
	
	private String residential_manage;
	
	private String facilities;
	
	private String remark;
	
	private Double transaction_price;
	
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
	  	"wms_cre_carp_house_check_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_carp_house_check_id",
		"wms_cre_credit_head_id",
		"customer_name",
		"house_address",
		"house_building_area",
		"total_floor",
		"house_layer",
		"community_name",
		"housing_pattern",
		"building_age",
		"decoration_standard",
		"house_usage",
		"online_fold",
		"house_transaction_price",
		"is_active",
		"house_people_review",
		"rental_price",
		"housing_towards",
		"common_occupants",
		"residential_environ",
		"residential_manage",
		"facilities",
		"remark",
		"transaction_price",
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
  
	public Integer getWms_cre_carp_house_check_id () {
		return wms_cre_carp_house_check_id;
	}
	
	public void setWms_cre_carp_house_check_id (Integer obj) {
		wms_cre_carp_house_check_id = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public String getCustomer_name () {
		return customer_name;
	}
	
	public void setCustomer_name (String obj) {
		customer_name = obj;
	}
	
	public String getHouse_address () {
		return house_address;
	}
	
	public void setHouse_address (String obj) {
		house_address = obj;
	}
	
	public Double getHouse_building_area () {
		return house_building_area;
	}
	
	public void setHouse_building_area (Double obj) {
		house_building_area = obj;
	}
	
	public Integer getTotal_floor () {
		return total_floor;
	}
	
	public void setTotal_floor (Integer obj) {
		total_floor = obj;
	}
	
	public String getHouse_layer () {
		return house_layer;
	}
	
	public void setHouse_layer (String obj) {
		house_layer = obj;
	}
	
	public String getCommunity_name () {
		return community_name;
	}
	
	public void setCommunity_name (String obj) {
		community_name = obj;
	}
	
	public Integer getHousing_pattern () {
		return housing_pattern;
	}
	
	public void setHousing_pattern (Integer obj) {
		housing_pattern = obj;
	}
	
	public Integer getBuilding_age () {
		return building_age;
	}
	
	public void setBuilding_age (Integer obj) {
		building_age = obj;
	}
	
	public Integer getDecoration_standard () {
		return decoration_standard;
	}
	
	public void setDecoration_standard (Integer obj) {
		decoration_standard = obj;
	}
	
	public Integer getHouse_usage () {
		return house_usage;
	}
	
	public void setHouse_usage (Integer obj) {
		house_usage = obj;
	}
	
	public Double getOnline_fold () {
		return online_fold;
	}
	
	public void setOnline_fold (Double obj) {
		online_fold = obj;
	}
	
	public Double getHouse_transaction_price () {
		return house_transaction_price;
	}
	
	public void setHouse_transaction_price (Double obj) {
		house_transaction_price = obj;
	}
	
	public String getIs_active () {
		return is_active;
	}
	
	public void setIs_active (String obj) {
		is_active = obj;
	}
	
	public String getHouse_people_review () {
		return house_people_review;
	}
	
	public void setHouse_people_review (String obj) {
		house_people_review = obj;
	}
	
	public Double getRental_price () {
		return rental_price;
	}
	
	public void setRental_price (Double obj) {
		rental_price = obj;
	}
	
	public String getHousing_towards () {
		return housing_towards;
	}
	
	public void setHousing_towards (String obj) {
		housing_towards = obj;
	}
	
	public String getCommon_occupants () {
		return common_occupants;
	}
	
	public void setCommon_occupants (String obj) {
		common_occupants = obj;
	}
	
	public String getResidential_environ () {
		return residential_environ;
	}
	
	public void setResidential_environ (String obj) {
		residential_environ = obj;
	}
	
	public String getResidential_manage () {
		return residential_manage;
	}
	
	public void setResidential_manage (String obj) {
		residential_manage = obj;
	}
	
	public String getFacilities () {
		return facilities;
	}
	
	public void setFacilities (String obj) {
		facilities = obj;
	}
	
	public String getRemark () {
		return remark;
	}
	
	public void setRemark (String obj) {
		remark = obj;
	}
	
	public Double getTransaction_price () {
		return transaction_price;
	}
	
	public void setTransaction_price (Double obj) {
		transaction_price = obj;
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
		paramMap.put("wms_cre_carp_house_check_id", this.wms_cre_carp_house_check_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("customer_name", this.customer_name);
		paramMap.put("house_address", this.house_address);
		paramMap.put("house_building_area", this.house_building_area);
		paramMap.put("total_floor", this.total_floor);
		paramMap.put("house_layer", this.house_layer);
		paramMap.put("community_name", this.community_name);
		paramMap.put("housing_pattern", this.housing_pattern);
		paramMap.put("building_age", this.building_age);
		paramMap.put("decoration_standard", this.decoration_standard);
		paramMap.put("house_usage", this.house_usage);
		paramMap.put("online_fold", this.online_fold);
		paramMap.put("house_transaction_price", this.house_transaction_price);
		paramMap.put("is_active", this.is_active);
		paramMap.put("house_people_review", this.house_people_review);
		paramMap.put("rental_price", this.rental_price);
		paramMap.put("housing_towards", this.housing_towards);
		paramMap.put("common_occupants", this.common_occupants);
		paramMap.put("residential_environ", this.residential_environ);
		paramMap.put("residential_manage", this.residential_manage);
		paramMap.put("facilities", this.facilities);
		paramMap.put("remark", this.remark);
		paramMap.put("transaction_price", this.transaction_price);
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