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

public class WmsInveProtocolRenewal implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_protocol_renewal_id;
	
	private Integer wms_inve_transa_id;
	
	private Integer wms_inve_transa_prod_id;
	
	private Integer wms_inve_pruduct_category_id;
	
	private Integer wms_inve_customer_id;
	
	private String bill_code;
	
	private String cus_name;
	
	private String id_card;
	
	private String category_name;
	
	private Integer product_deadline;
	
	private java.math.BigDecimal product_account;
	
	private java.sql.Date atic_renewal_date;
	
	private String atic_renewal_date_str;
	
	private Integer category_type;
	
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
	  	"wms_inve_protocol_renewal_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_protocol_renewal_id",
		"wms_inve_transa_id",
		"wms_inve_transa_prod_id",
		"wms_inve_pruduct_category_id",
		"wms_inve_customer_id",
		"bill_code",
		"cus_name",
		"id_card",
		"category_name",
		"product_deadline",
		"product_account",
		"atic_renewal_date",
		"atic_renewal_date_str",
		"category_type",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_protocol_renewal_id () {
		return wms_inve_protocol_renewal_id;
	}
	
	public void setWms_inve_protocol_renewal_id (Integer obj) {
		wms_inve_protocol_renewal_id = obj;
	}
	
	public Integer getWms_inve_transa_id () {
		return wms_inve_transa_id;
	}
	
	public void setWms_inve_transa_id (Integer obj) {
		wms_inve_transa_id = obj;
	}
	
	public Integer getWms_inve_transa_prod_id () {
		return wms_inve_transa_prod_id;
	}
	
	public void setWms_inve_transa_prod_id (Integer obj) {
		wms_inve_transa_prod_id = obj;
	}
	
	public Integer getWms_inve_pruduct_category_id () {
		return wms_inve_pruduct_category_id;
	}
	
	public void setWms_inve_pruduct_category_id (Integer obj) {
		wms_inve_pruduct_category_id = obj;
	}
	
	public Integer getWms_inve_customer_id () {
		return wms_inve_customer_id;
	}
	
	public void setWms_inve_customer_id (Integer obj) {
		wms_inve_customer_id = obj;
	}
	
	public String getBill_code () {
		return bill_code;
	}
	
	public void setBill_code (String obj) {
		bill_code = obj;
	}
	
	public String getCus_name () {
		return cus_name;
	}
	
	public void setCus_name (String obj) {
		cus_name = obj;
	}
	
	public String getId_card () {
		return id_card;
	}
	
	public void setId_card (String obj) {
		id_card = obj;
	}
	
	public String getCategory_name () {
		return category_name;
	}
	
	public void setCategory_name (String obj) {
		category_name = obj;
	}
	
	public Integer getProduct_deadline () {
		return product_deadline;
	}
	
	public void setProduct_deadline (Integer obj) {
		product_deadline = obj;
	}
	
	public java.math.BigDecimal getProduct_account () {
		return product_account;
	}
	
	public void setProduct_account (java.math.BigDecimal obj) {
		product_account = obj;
	}
	
	public java.sql.Date getAtic_renewal_date () {
		return atic_renewal_date;
	}
	
	public void setAtic_renewal_date (java.sql.Date obj) {
		atic_renewal_date = obj;
	}
	
	public String getAtic_renewal_date_str () {
		return atic_renewal_date_str;
	}
	
	public void setAtic_renewal_date_str (String obj) {
		atic_renewal_date_str = obj;
	}
	
	public Integer getCategory_type () {
		return category_type;
	}
	
	public void setCategory_type (Integer obj) {
		category_type = obj;
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
		paramMap.put("wms_inve_protocol_renewal_id", this.wms_inve_protocol_renewal_id);
		paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
		paramMap.put("wms_inve_transa_prod_id", this.wms_inve_transa_prod_id);
		paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
		paramMap.put("wms_inve_customer_id", this.wms_inve_customer_id);
		paramMap.put("bill_code", this.bill_code);
		paramMap.put("cus_name", this.cus_name);
		paramMap.put("id_card", this.id_card);
		paramMap.put("category_name", this.category_name);
		paramMap.put("product_deadline", this.product_deadline);
		paramMap.put("product_account", this.product_account);
		paramMap.put("atic_renewal_date", this.atic_renewal_date);
		paramMap.put("atic_renewal_date_str", this.atic_renewal_date_str);
		paramMap.put("category_type", this.category_type);
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