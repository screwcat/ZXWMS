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

public class WmsInveOldCommHis implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_old_comm_his_id;
	
	private String inve_type;
	
	private Integer wms_inve_transa_id;
	
	private Integer wms_inve_transa_prod_id;
	
	private java.sql.Date date_of_payment;
	
	private String date_of_payment_str;
	
	private Integer product_deadline;
	
	private String data_status;
	
	private java.math.BigDecimal org_product_account;
	
	private java.math.BigDecimal product_account;
	
	private java.math.BigDecimal redeem_amount;
	
	private Integer wms_inve_redeem_id;
	
	private Integer salesman_id;
	
	private java.sql.Timestamp statics_time;
	
	private String statics_time_str;
	
	private String salesman_status;
	
	private Integer salesman_dept_id;
	
	private Integer department_manager_id;
	
	private Integer department_manager_dept_id;
	
	private Integer commission_days;
	
	private java.math.BigDecimal stock_comm_coeff;
	
	private java.math.BigDecimal team_comm_coeff;
	
	private java.math.BigDecimal old_stock_comm_mon;
	
	private java.math.BigDecimal old_team_comm_mon;
	
	private java.math.BigDecimal tax_point;
	
	private java.math.BigDecimal tax_mon;
	
	private String statics_month;
	
	private java.sql.Date redeem_date;
	
	private String redeem_date_str;
	
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
	  	"wms_inve_old_comm_his_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_old_comm_his_id",
		"inve_type",
		"wms_inve_transa_id",
		"wms_inve_transa_prod_id",
		"date_of_payment",
		"date_of_payment_str",
		"product_deadline",
		"data_status",
		"org_product_account",
		"product_account",
		"redeem_amount",
		"wms_inve_redeem_id",
		"salesman_id",
		"statics_time",
		"statics_time_str",
		"salesman_status",
		"salesman_dept_id",
		"department_manager_id",
		"department_manager_dept_id",
		"commission_days",
		"stock_comm_coeff",
		"team_comm_coeff",
		"old_stock_comm_mon",
		"old_team_comm_mon",
		"tax_point",
		"tax_mon",
		"statics_month",
		"redeem_date",
		"redeem_date_str",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_old_comm_his_id () {
		return wms_inve_old_comm_his_id;
	}
	
	public void setWms_inve_old_comm_his_id (Integer obj) {
		wms_inve_old_comm_his_id = obj;
	}
	
	public String getInve_type () {
		return inve_type;
	}
	
	public void setInve_type (String obj) {
		inve_type = obj;
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
	
	public java.sql.Date getDate_of_payment () {
		return date_of_payment;
	}
	
	public void setDate_of_payment (java.sql.Date obj) {
		date_of_payment = obj;
	}
	
	public String getDate_of_payment_str () {
		return date_of_payment_str;
	}
	
	public void setDate_of_payment_str (String obj) {
		date_of_payment_str = obj;
	}
	
	public Integer getProduct_deadline () {
		return product_deadline;
	}
	
	public void setProduct_deadline (Integer obj) {
		product_deadline = obj;
	}
	
	public String getData_status () {
		return data_status;
	}
	
	public void setData_status (String obj) {
		data_status = obj;
	}
	
	public java.math.BigDecimal getOrg_product_account () {
		return org_product_account;
	}
	
	public void setOrg_product_account (java.math.BigDecimal obj) {
		org_product_account = obj;
	}
	
	public java.math.BigDecimal getProduct_account () {
		return product_account;
	}
	
	public void setProduct_account (java.math.BigDecimal obj) {
		product_account = obj;
	}
	
	public java.math.BigDecimal getRedeem_amount () {
		return redeem_amount;
	}
	
	public void setRedeem_amount (java.math.BigDecimal obj) {
		redeem_amount = obj;
	}
	
	public Integer getWms_inve_redeem_id () {
		return wms_inve_redeem_id;
	}
	
	public void setWms_inve_redeem_id (Integer obj) {
		wms_inve_redeem_id = obj;
	}
	
	public Integer getSalesman_id () {
		return salesman_id;
	}
	
	public void setSalesman_id (Integer obj) {
		salesman_id = obj;
	}
	
	public java.sql.Timestamp getStatics_time () {
		return statics_time;
	}
	
	public void setStatics_time (java.sql.Timestamp obj) {
		statics_time = obj;
	}
	
	public String getStatics_time_str () {
		return statics_time_str;
	}
	
	public void setStatics_time_str (String obj) {
		statics_time_str = obj;
	}
	
	public String getSalesman_status () {
		return salesman_status;
	}
	
	public void setSalesman_status (String obj) {
		salesman_status = obj;
	}
	
	public Integer getSalesman_dept_id () {
		return salesman_dept_id;
	}
	
	public void setSalesman_dept_id (Integer obj) {
		salesman_dept_id = obj;
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
	
	public Integer getCommission_days () {
		return commission_days;
	}
	
	public void setCommission_days (Integer obj) {
		commission_days = obj;
	}
	
	public java.math.BigDecimal getStock_comm_coeff () {
		return stock_comm_coeff;
	}
	
	public void setStock_comm_coeff (java.math.BigDecimal obj) {
		stock_comm_coeff = obj;
	}
	
	public java.math.BigDecimal getTeam_comm_coeff () {
		return team_comm_coeff;
	}
	
	public void setTeam_comm_coeff (java.math.BigDecimal obj) {
		team_comm_coeff = obj;
	}
	
	public java.math.BigDecimal getOld_stock_comm_mon () {
		return old_stock_comm_mon;
	}
	
	public void setOld_stock_comm_mon (java.math.BigDecimal obj) {
		old_stock_comm_mon = obj;
	}
	
	public java.math.BigDecimal getOld_team_comm_mon () {
		return old_team_comm_mon;
	}
	
	public void setOld_team_comm_mon (java.math.BigDecimal obj) {
		old_team_comm_mon = obj;
	}
	
	public java.math.BigDecimal getTax_point () {
		return tax_point;
	}
	
	public void setTax_point (java.math.BigDecimal obj) {
		tax_point = obj;
	}
	
	public java.math.BigDecimal getTax_mon () {
		return tax_mon;
	}
	
	public void setTax_mon (java.math.BigDecimal obj) {
		tax_mon = obj;
	}
	
	public String getStatics_month () {
		return statics_month;
	}
	
	public void setStatics_month (String obj) {
		statics_month = obj;
	}
	
	public java.sql.Date getRedeem_date () {
		return redeem_date;
	}
	
	public void setRedeem_date (java.sql.Date obj) {
		redeem_date = obj;
	}
	
	public String getRedeem_date_str () {
		return redeem_date_str;
	}
	
	public void setRedeem_date_str (String obj) {
		redeem_date_str = obj;
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
		paramMap.put("wms_inve_old_comm_his_id", this.wms_inve_old_comm_his_id);
		paramMap.put("inve_type", this.inve_type);
		paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
		paramMap.put("wms_inve_transa_prod_id", this.wms_inve_transa_prod_id);
		paramMap.put("date_of_payment", this.date_of_payment);
		paramMap.put("date_of_payment_str", this.date_of_payment_str);
		paramMap.put("product_deadline", this.product_deadline);
		paramMap.put("data_status", this.data_status);
		paramMap.put("org_product_account", this.org_product_account);
		paramMap.put("product_account", this.product_account);
		paramMap.put("redeem_amount", this.redeem_amount);
		paramMap.put("wms_inve_redeem_id", this.wms_inve_redeem_id);
		paramMap.put("salesman_id", this.salesman_id);
		paramMap.put("statics_time", this.statics_time);
		paramMap.put("statics_time_str", this.statics_time_str);
		paramMap.put("salesman_status", this.salesman_status);
		paramMap.put("salesman_dept_id", this.salesman_dept_id);
		paramMap.put("department_manager_id", this.department_manager_id);
		paramMap.put("department_manager_dept_id", this.department_manager_dept_id);
		paramMap.put("commission_days", this.commission_days);
		paramMap.put("stock_comm_coeff", this.stock_comm_coeff);
		paramMap.put("team_comm_coeff", this.team_comm_coeff);
		paramMap.put("old_stock_comm_mon", this.old_stock_comm_mon);
		paramMap.put("old_team_comm_mon", this.old_team_comm_mon);
		paramMap.put("tax_point", this.tax_point);
		paramMap.put("tax_mon", this.tax_mon);
		paramMap.put("statics_month", this.statics_month);
		paramMap.put("redeem_date", this.redeem_date);
		paramMap.put("redeem_date_str", this.redeem_date_str);
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