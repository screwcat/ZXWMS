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

public class WmsInveTransaBackApply implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_transa_back_apply_id;
	
	private Integer wms_inve_transa_prod_id;
	
	private Integer wms_inve_transa_id;
	
	private String bill_code;
	
	private String cus_name;
	
	private String id_card;
	
	private String mobile_phone;
	
	private Integer wms_inve_pruduct_category_id;
	
	private String category_name;
	
	private java.math.BigDecimal product_account;
	
	private Integer card_info;
	
	private String card_owner_name;
	
	private String card_no;
	
	private String bank_of_deposit;
	
	private String bank_of_deposit_pro;
	
	private String bank_of_deposit_city;
	
	private String bank_branch;
	
	private String back_apply_advice;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
	private String enable_flag;
	
	private String salesman_name;
	
	private Integer salesman_id;
	
	private Integer salesman_dept_id;
	
	private String salesman_city_code;
	
	private Integer department_manager_id;
	
	private Integer department_manager_dept_id;
	
	private String department_manager_name;
	
	private Integer vice_general_manager_id;
	
	private Integer vice_general_manager_dept_id;
	
	private String vice_general_manager_name;
	
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
	  	"wms_inve_transa_back_apply_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_transa_back_apply_id",
		"wms_inve_transa_prod_id",
		"wms_inve_transa_id",
		"bill_code",
		"cus_name",
		"id_card",
		"mobile_phone",
		"wms_inve_pruduct_category_id",
		"category_name",
		"product_account",
		"card_info",
		"card_owner_name",
		"card_no",
		"bank_of_deposit",
		"bank_of_deposit_pro",
		"bank_of_deposit_city",
		"bank_branch",
		"back_apply_advice",
		"create_user_id",
		"create_user_name",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
		"salesman_name",
		"salesman_id",
		"salesman_dept_id",
		"salesman_city_code",
		"department_manager_id",
		"department_manager_dept_id",
		"department_manager_name",
		"vice_general_manager_id",
		"vice_general_manager_dept_id",
		"vice_general_manager_name",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_transa_back_apply_id () {
		return wms_inve_transa_back_apply_id;
	}
	
	public void setWms_inve_transa_back_apply_id (Integer obj) {
		wms_inve_transa_back_apply_id = obj;
	}
	
	public Integer getWms_inve_transa_prod_id () {
		return wms_inve_transa_prod_id;
	}
	
	public void setWms_inve_transa_prod_id (Integer obj) {
		wms_inve_transa_prod_id = obj;
	}
	
	public Integer getWms_inve_transa_id () {
		return wms_inve_transa_id;
	}
	
	public void setWms_inve_transa_id (Integer obj) {
		wms_inve_transa_id = obj;
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
	
	public String getMobile_phone () {
		return mobile_phone;
	}
	
	public void setMobile_phone (String obj) {
		mobile_phone = obj;
	}
	
	public Integer getWms_inve_pruduct_category_id () {
		return wms_inve_pruduct_category_id;
	}
	
	public void setWms_inve_pruduct_category_id (Integer obj) {
		wms_inve_pruduct_category_id = obj;
	}
	
	public String getCategory_name () {
		return category_name;
	}
	
	public void setCategory_name (String obj) {
		category_name = obj;
	}
	
	public java.math.BigDecimal getProduct_account () {
		return product_account;
	}
	
	public void setProduct_account (java.math.BigDecimal obj) {
		product_account = obj;
	}
	
	public Integer getCard_info () {
		return card_info;
	}
	
	public void setCard_info (Integer obj) {
		card_info = obj;
	}
	
	public String getCard_owner_name () {
		return card_owner_name;
	}
	
	public void setCard_owner_name (String obj) {
		card_owner_name = obj;
	}
	
	public String getCard_no () {
		return card_no;
	}
	
	public void setCard_no (String obj) {
		card_no = obj;
	}
	
	public String getBank_of_deposit () {
		return bank_of_deposit;
	}
	
	public void setBank_of_deposit (String obj) {
		bank_of_deposit = obj;
	}
	
	public String getBank_of_deposit_pro () {
		return bank_of_deposit_pro;
	}
	
	public void setBank_of_deposit_pro (String obj) {
		bank_of_deposit_pro = obj;
	}
	
	public String getBank_of_deposit_city () {
		return bank_of_deposit_city;
	}
	
	public void setBank_of_deposit_city (String obj) {
		bank_of_deposit_city = obj;
	}
	
	public String getBank_branch () {
		return bank_branch;
	}
	
	public void setBank_branch (String obj) {
		bank_branch = obj;
	}
	
	public String getBack_apply_advice () {
		return back_apply_advice;
	}
	
	public void setBack_apply_advice (String obj) {
		back_apply_advice = obj;
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
	
	public String getSalesman_name () {
		return salesman_name;
	}
	
	public void setSalesman_name (String obj) {
		salesman_name = obj;
	}
	
	public Integer getSalesman_id () {
		return salesman_id;
	}
	
	public void setSalesman_id (Integer obj) {
		salesman_id = obj;
	}
	
	public Integer getSalesman_dept_id () {
		return salesman_dept_id;
	}
	
	public void setSalesman_dept_id (Integer obj) {
		salesman_dept_id = obj;
	}
	
	public String getSalesman_city_code () {
		return salesman_city_code;
	}
	
	public void setSalesman_city_code (String obj) {
		salesman_city_code = obj;
	}
	
	public Integer getDepartment_manager_id () {
		return department_manager_id;
	}
	
	public void setDepartment_manager_id (Integer obj) {
		department_manager_id = obj;
	}
	
	public Integer getDepartment_manager_dept_id () {
		return department_manager_dept_id;
	}
	
	public void setDepartment_manager_dept_id (Integer obj) {
		department_manager_dept_id = obj;
	}
	
	public String getDepartment_manager_name () {
		return department_manager_name;
	}
	
	public void setDepartment_manager_name (String obj) {
		department_manager_name = obj;
	}
	
	public Integer getVice_general_manager_id () {
		return vice_general_manager_id;
	}
	
	public void setVice_general_manager_id (Integer obj) {
		vice_general_manager_id = obj;
	}
	
	public Integer getVice_general_manager_dept_id () {
		return vice_general_manager_dept_id;
	}
	
	public void setVice_general_manager_dept_id (Integer obj) {
		vice_general_manager_dept_id = obj;
	}
	
	public String getVice_general_manager_name () {
		return vice_general_manager_name;
	}
	
	public void setVice_general_manager_name (String obj) {
		vice_general_manager_name = obj;
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
		paramMap.put("wms_inve_transa_back_apply_id", this.wms_inve_transa_back_apply_id);
		paramMap.put("wms_inve_transa_prod_id", this.wms_inve_transa_prod_id);
		paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
		paramMap.put("bill_code", this.bill_code);
		paramMap.put("cus_name", this.cus_name);
		paramMap.put("id_card", this.id_card);
		paramMap.put("mobile_phone", this.mobile_phone);
		paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
		paramMap.put("category_name", this.category_name);
		paramMap.put("product_account", this.product_account);
		paramMap.put("card_info", this.card_info);
		paramMap.put("card_owner_name", this.card_owner_name);
		paramMap.put("card_no", this.card_no);
		paramMap.put("bank_of_deposit", this.bank_of_deposit);
		paramMap.put("bank_of_deposit_pro", this.bank_of_deposit_pro);
		paramMap.put("bank_of_deposit_city", this.bank_of_deposit_city);
		paramMap.put("bank_branch", this.bank_branch);
		paramMap.put("back_apply_advice", this.back_apply_advice);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_user_name", this.create_user_name);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
		paramMap.put("last_update_user_id", this.last_update_user_id);
		paramMap.put("last_update_timestamp", this.last_update_timestamp);
		paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("salesman_name", this.salesman_name);
		paramMap.put("salesman_id", this.salesman_id);
		paramMap.put("salesman_dept_id", this.salesman_dept_id);
		paramMap.put("salesman_city_code", this.salesman_city_code);
		paramMap.put("department_manager_id", this.department_manager_id);
		paramMap.put("department_manager_dept_id", this.department_manager_dept_id);
		paramMap.put("department_manager_name", this.department_manager_name);
		paramMap.put("vice_general_manager_id", this.vice_general_manager_id);
		paramMap.put("vice_general_manager_dept_id", this.vice_general_manager_dept_id);
		paramMap.put("vice_general_manager_name", this.vice_general_manager_name);
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