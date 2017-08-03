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

public class WmsInveReplace implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_replace_id;
	
	private Integer wms_inve_transa_id;
	
	private String new_wms_inve_transa_id;
	
	private Integer new_wms_inve_pruduct_category_id;
	
	private java.sql.Date apply_datetime;
	
	private String apply_datetime_str;
	
	private java.math.BigDecimal org_product_amount;
	
	private java.sql.Date begin_of_date;
	
	private String begin_of_date_str;
	
	private java.math.BigDecimal product_account;
	
	private java.math.BigDecimal extra_pay_amount;
	
	private String redeem_company_reason;
	
	private String is_take_off_damages;
	
	private String replace_reason;
	
	private String data_status;
	
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
	  	"wms_inve_replace_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_replace_id",
		"wms_inve_transa_id",
		"new_wms_inve_transa_id",
		"new_wms_inve_pruduct_category_id",
		"apply_datetime",
		"apply_datetime_str",
		"org_product_amount",
		"begin_of_date",
		"begin_of_date_str",
		"product_account",
		"extra_pay_amount",
		"redeem_company_reason",
		"is_take_off_damages",
		"replace_reason",
		"data_status",
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
  
	public Integer getWms_inve_replace_id () {
		return wms_inve_replace_id;
	}
	
	public void setWms_inve_replace_id (Integer obj) {
		wms_inve_replace_id = obj;
	}
	
	public Integer getWms_inve_transa_id () {
		return wms_inve_transa_id;
	}
	
	public void setWms_inve_transa_id (Integer obj) {
		wms_inve_transa_id = obj;
	}
	
	public String getNew_wms_inve_transa_id () {
		return new_wms_inve_transa_id;
	}
	
	public void setNew_wms_inve_transa_id (String obj) {
		new_wms_inve_transa_id = obj;
	}
	
	public Integer getNew_wms_inve_pruduct_category_id () {
		return new_wms_inve_pruduct_category_id;
	}
	
	public void setNew_wms_inve_pruduct_category_id (Integer obj) {
		new_wms_inve_pruduct_category_id = obj;
	}
	
	public java.sql.Date getApply_datetime () {
		return apply_datetime;
	}
	
	public void setApply_datetime (java.sql.Date obj) {
		apply_datetime = obj;
	}
	
	public String getApply_datetime_str () {
		return apply_datetime_str;
	}
	
	public void setApply_datetime_str (String obj) {
		apply_datetime_str = obj;
	}
	
	public java.math.BigDecimal getOrg_product_amount () {
		return org_product_amount;
	}
	
	public void setOrg_product_amount (java.math.BigDecimal obj) {
		org_product_amount = obj;
	}
	
	public java.sql.Date getBegin_of_date () {
		return begin_of_date;
	}
	
	public void setBegin_of_date (java.sql.Date obj) {
		begin_of_date = obj;
	}
	
	public String getBegin_of_date_str () {
		return begin_of_date_str;
	}
	
	public void setBegin_of_date_str (String obj) {
		begin_of_date_str = obj;
	}
	
	public java.math.BigDecimal getProduct_account () {
		return product_account;
	}
	
	public void setProduct_account (java.math.BigDecimal obj) {
		product_account = obj;
	}
	
	public java.math.BigDecimal getExtra_pay_amount () {
		return extra_pay_amount;
	}
	
	public void setExtra_pay_amount (java.math.BigDecimal obj) {
		extra_pay_amount = obj;
	}
	
	public String getRedeem_company_reason () {
		return redeem_company_reason;
	}
	
	public void setRedeem_company_reason (String obj) {
		redeem_company_reason = obj;
	}
	
	public String getIs_take_off_damages () {
		return is_take_off_damages;
	}
	
	public void setIs_take_off_damages (String obj) {
		is_take_off_damages = obj;
	}
	
	public String getReplace_reason () {
		return replace_reason;
	}
	
	public void setReplace_reason (String obj) {
		replace_reason = obj;
	}
	
	public String getData_status () {
		return data_status;
	}
	
	public void setData_status (String obj) {
		data_status = obj;
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
		paramMap.put("wms_inve_replace_id", this.wms_inve_replace_id);
		paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
		paramMap.put("new_wms_inve_transa_id", this.new_wms_inve_transa_id);
		paramMap.put("new_wms_inve_pruduct_category_id", this.new_wms_inve_pruduct_category_id);
		paramMap.put("apply_datetime", this.apply_datetime);
		paramMap.put("apply_datetime_str", this.apply_datetime_str);
		paramMap.put("org_product_amount", this.org_product_amount);
		paramMap.put("begin_of_date", this.begin_of_date);
		paramMap.put("begin_of_date_str", this.begin_of_date_str);
		paramMap.put("product_account", this.product_account);
		paramMap.put("extra_pay_amount", this.extra_pay_amount);
		paramMap.put("redeem_company_reason", this.redeem_company_reason);
		paramMap.put("is_take_off_damages", this.is_take_off_damages);
		paramMap.put("replace_reason", this.replace_reason);
		paramMap.put("data_status", this.data_status);
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