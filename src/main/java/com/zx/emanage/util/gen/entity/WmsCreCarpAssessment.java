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

public class WmsCreCarpAssessment implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_carp_assessment_id;
	
	private Integer wms_cre_credit_head_id;
	
	private String customer_name;
	
	private String car_name;
	
	private String car_type;
	
	private String car_no;
	
	private String car_life;
	
	private java.math.BigDecimal driving_range;
	
	private String vehicle_territory_province;
	
	private String vehicle_territory_city;
	
	private String vehicle_territory_other;
	
	private String vi_type_info;
	
	private String other_vi_info;
	
	private String is_major_accidents;
	
	private String major_accidents_remark;
	
	private java.math.BigDecimal vehicle_market_value;
	
	private java.math.BigDecimal vehicle_evaluation_price;
	
	private String carp_assessment_remark;
	
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
	  	"wms_cre_carp_assessment_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_carp_assessment_id",
		"wms_cre_credit_head_id",
		"customer_name",
		"car_name",
		"car_type",
		"car_no",
		"car_life",
		"driving_range",
		"vehicle_territory_province",
		"vehicle_territory_city",
		"vehicle_territory_other",
		"vi_type_info",
		"other_vi_info",
		"is_major_accidents",
		"major_accidents_remark",
		"vehicle_market_value",
		"vehicle_evaluation_price",
		"carp_assessment_remark",
		"create_user_id",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_carp_assessment_id () {
		return wms_cre_carp_assessment_id;
	}
	
	public void setWms_cre_carp_assessment_id (Integer obj) {
		wms_cre_carp_assessment_id = obj;
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
	
	public String getCar_name () {
		return car_name;
	}
	
	public void setCar_name (String obj) {
		car_name = obj;
	}
	
	public String getCar_type () {
		return car_type;
	}
	
	public void setCar_type (String obj) {
		car_type = obj;
	}
	
	public String getCar_no () {
		return car_no;
	}
	
	public void setCar_no (String obj) {
		car_no = obj;
	}
	
	public String getCar_life () {
		return car_life;
	}
	
	public void setCar_life (String obj) {
		car_life = obj;
	}
	
	public java.math.BigDecimal getDriving_range () {
		return driving_range;
	}
	
	public void setDriving_range (java.math.BigDecimal obj) {
		driving_range = obj;
	}
	
	public String getVehicle_territory_province () {
		return vehicle_territory_province;
	}
	
	public void setVehicle_territory_province (String obj) {
		vehicle_territory_province = obj;
	}
	
	public String getVehicle_territory_city () {
		return vehicle_territory_city;
	}
	
	public void setVehicle_territory_city (String obj) {
		vehicle_territory_city = obj;
	}
	
	public String getVehicle_territory_other () {
		return vehicle_territory_other;
	}
	
	public void setVehicle_territory_other (String obj) {
		vehicle_territory_other = obj;
	}
	
	public String getVi_type_info () {
		return vi_type_info;
	}
	
	public void setVi_type_info (String obj) {
		vi_type_info = obj;
	}
	
	public String getOther_vi_info () {
		return other_vi_info;
	}
	
	public void setOther_vi_info (String obj) {
		other_vi_info = obj;
	}
	
	public String getIs_major_accidents () {
		return is_major_accidents;
	}
	
	public void setIs_major_accidents (String obj) {
		is_major_accidents = obj;
	}
	
	public String getMajor_accidents_remark () {
		return major_accidents_remark;
	}
	
	public void setMajor_accidents_remark (String obj) {
		major_accidents_remark = obj;
	}
	
	public java.math.BigDecimal getVehicle_market_value () {
		return vehicle_market_value;
	}
	
	public void setVehicle_market_value (java.math.BigDecimal obj) {
		vehicle_market_value = obj;
	}
	
	public java.math.BigDecimal getVehicle_evaluation_price () {
		return vehicle_evaluation_price;
	}
	
	public void setVehicle_evaluation_price (java.math.BigDecimal obj) {
		vehicle_evaluation_price = obj;
	}
	
	public String getCarp_assessment_remark () {
		return carp_assessment_remark;
	}
	
	public void setCarp_assessment_remark (String obj) {
		carp_assessment_remark = obj;
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
		paramMap.put("wms_cre_carp_assessment_id", this.wms_cre_carp_assessment_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("customer_name", this.customer_name);
		paramMap.put("car_name", this.car_name);
		paramMap.put("car_type", this.car_type);
		paramMap.put("car_no", this.car_no);
		paramMap.put("car_life", this.car_life);
		paramMap.put("driving_range", this.driving_range);
		paramMap.put("vehicle_territory_province", this.vehicle_territory_province);
		paramMap.put("vehicle_territory_city", this.vehicle_territory_city);
		paramMap.put("vehicle_territory_other", this.vehicle_territory_other);
		paramMap.put("vi_type_info", this.vi_type_info);
		paramMap.put("other_vi_info", this.other_vi_info);
		paramMap.put("is_major_accidents", this.is_major_accidents);
		paramMap.put("major_accidents_remark", this.major_accidents_remark);
		paramMap.put("vehicle_market_value", this.vehicle_market_value);
		paramMap.put("vehicle_evaluation_price", this.vehicle_evaluation_price);
		paramMap.put("carp_assessment_remark", this.carp_assessment_remark);
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