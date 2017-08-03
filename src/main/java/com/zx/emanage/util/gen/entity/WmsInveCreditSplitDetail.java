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

public class WmsInveCreditSplitDetail implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_credit_split_detail_id;
	
	private Integer wms_inve_credit_split_head_id;
	
	private Integer credit_split_amount;
	
	private String cus_name;
	
	private String inve_amount;
	
	private String date_of_payment;
	
	private String financial_bill_code;
	
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
	  	"wms_inve_credit_split_detail_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_credit_split_detail_id",
		"wms_inve_credit_split_head_id",
		"credit_split_amount",
		"cus_name",
		"inve_amount",
		"date_of_payment",
		"financial_bill_code",
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
  
	public Integer getWms_inve_credit_split_detail_id () {
		return wms_inve_credit_split_detail_id;
	}
	
	public void setWms_inve_credit_split_detail_id (Integer obj) {
		wms_inve_credit_split_detail_id = obj;
	}
	
	public Integer getWms_inve_credit_split_head_id () {
		return wms_inve_credit_split_head_id;
	}
	
	public void setWms_inve_credit_split_head_id (Integer obj) {
		wms_inve_credit_split_head_id = obj;
	}
	
	public Integer getCredit_split_amount () {
		return credit_split_amount;
	}
	
	public void setCredit_split_amount (Integer obj) {
		credit_split_amount = obj;
	}
	
	public String getCus_name () {
		return cus_name;
	}
	
	public void setCus_name (String obj) {
		cus_name = obj;
	}
	
	public String getInve_amount () {
		return inve_amount;
	}
	
	public void setInve_amount (String obj) {
		inve_amount = obj;
	}
	
	public String getDate_of_payment () {
		return date_of_payment;
	}
	
	public void setDate_of_payment (String obj) {
		date_of_payment = obj;
	}
	
	public String getFinancial_bill_code () {
		return financial_bill_code;
	}
	
	public void setFinancial_bill_code (String obj) {
		financial_bill_code = obj;
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
		paramMap.put("wms_inve_credit_split_detail_id", this.wms_inve_credit_split_detail_id);
		paramMap.put("wms_inve_credit_split_head_id", this.wms_inve_credit_split_head_id);
		paramMap.put("credit_split_amount", this.credit_split_amount);
		paramMap.put("cus_name", this.cus_name);
		paramMap.put("inve_amount", this.inve_amount);
		paramMap.put("date_of_payment", this.date_of_payment);
		paramMap.put("financial_bill_code", this.financial_bill_code);
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