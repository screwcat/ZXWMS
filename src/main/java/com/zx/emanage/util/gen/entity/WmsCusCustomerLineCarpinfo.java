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

public class WmsCusCustomerLineCarpinfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cus_customer_line_carpinfo_id;
	
	private Integer wms_cus_customer_id;
	
	private String car_name;
	
	private String car_no;
	
	private String car_type;
	
	private String car_model;
	
	private String car_driving_mode;
	
	private String car_exhaust_quantity;
	
	private java.sql.Date car_purchase_time;
	
	private String car_purchase_time_str;
	
	private java.math.BigDecimal car_purchase_amount;
	
	private String vehicle_acq_mode;
	
	private String vehicle_idn_num;
	
	private java.sql.Date insurance_maturity;
	
	private String insurance_maturity_str;
	
	private String vi_type_info;
	
	private String other_vi_options;
	
	private String other_vi_info;
	
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
	  	"wms_cus_customer_line_carpinfo_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cus_customer_line_carpinfo_id",
		"wms_cus_customer_id",
		"car_name",
		"car_no",
		"car_type",
		"car_model",
		"car_driving_mode",
		"car_exhaust_quantity",
		"car_purchase_time",
		"car_purchase_time_str",
		"car_purchase_amount",
		"vehicle_acq_mode",
		"vehicle_idn_num",
		"insurance_maturity",
		"insurance_maturity_str",
		"vi_type_info",
		"other_vi_options",
		"other_vi_info",
		"create_user_id",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cus_customer_line_carpinfo_id () {
		return wms_cus_customer_line_carpinfo_id;
	}
	
	public void setWms_cus_customer_line_carpinfo_id (Integer obj) {
		wms_cus_customer_line_carpinfo_id = obj;
	}
	
	public Integer getWms_cus_customer_id () {
		return wms_cus_customer_id;
	}
	
	public void setWms_cus_customer_id (Integer obj) {
		wms_cus_customer_id = obj;
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
	
	public String getCar_type () {
		return car_type;
	}
	
	public void setCar_type (String obj) {
		car_type = obj;
	}
	
	public String getCar_model () {
		return car_model;
	}
	
	public void setCar_model (String obj) {
		car_model = obj;
	}
	
	public String getCar_driving_mode () {
		return car_driving_mode;
	}
	
	public void setCar_driving_mode (String obj) {
		car_driving_mode = obj;
	}
	
	public String getCar_exhaust_quantity () {
		return car_exhaust_quantity;
	}
	
	public void setCar_exhaust_quantity (String obj) {
		car_exhaust_quantity = obj;
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
	
	public java.math.BigDecimal getCar_purchase_amount () {
		return car_purchase_amount;
	}
	
	public void setCar_purchase_amount (java.math.BigDecimal obj) {
		car_purchase_amount = obj;
	}
	
	public String getVehicle_acq_mode () {
		return vehicle_acq_mode;
	}
	
	public void setVehicle_acq_mode (String obj) {
		vehicle_acq_mode = obj;
	}
	
	public String getVehicle_idn_num () {
		return vehicle_idn_num;
	}
	
	public void setVehicle_idn_num (String obj) {
		vehicle_idn_num = obj;
	}
	
	public java.sql.Date getInsurance_maturity () {
		return insurance_maturity;
	}
	
	public void setInsurance_maturity (java.sql.Date obj) {
		insurance_maturity = obj;
	}
	
	public String getInsurance_maturity_str () {
		return insurance_maturity_str;
	}
	
	public void setInsurance_maturity_str (String obj) {
		insurance_maturity_str = obj;
	}
	
	public String getVi_type_info () {
		return vi_type_info;
	}
	
	public void setVi_type_info (String obj) {
		vi_type_info = obj;
	}
	
	public String getOther_vi_options () {
		return other_vi_options;
	}
	
	public void setOther_vi_options (String obj) {
		other_vi_options = obj;
	}
	
	public String getOther_vi_info () {
		return other_vi_info;
	}
	
	public void setOther_vi_info (String obj) {
		other_vi_info = obj;
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
		paramMap.put("wms_cus_customer_line_carpinfo_id", this.wms_cus_customer_line_carpinfo_id);
		paramMap.put("wms_cus_customer_id", this.wms_cus_customer_id);
		paramMap.put("car_name", this.car_name);
		paramMap.put("car_no", this.car_no);
		paramMap.put("car_type", this.car_type);
		paramMap.put("car_model", this.car_model);
		paramMap.put("car_driving_mode", this.car_driving_mode);
		paramMap.put("car_exhaust_quantity", this.car_exhaust_quantity);
		paramMap.put("car_purchase_time", this.car_purchase_time);
		paramMap.put("car_purchase_time_str", this.car_purchase_time_str);
		paramMap.put("car_purchase_amount", this.car_purchase_amount);
		paramMap.put("vehicle_acq_mode", this.vehicle_acq_mode);
		paramMap.put("vehicle_idn_num", this.vehicle_idn_num);
		paramMap.put("insurance_maturity", this.insurance_maturity);
		paramMap.put("insurance_maturity_str", this.insurance_maturity_str);
		paramMap.put("vi_type_info", this.vi_type_info);
		paramMap.put("other_vi_options", this.other_vi_options);
		paramMap.put("other_vi_info", this.other_vi_info);
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