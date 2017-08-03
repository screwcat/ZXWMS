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

public class WmsInveCommissionPerformanceNew implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_commission_performance_new_id;
	
	private Integer compay_id;
	
	private Integer dept_id;
	
	private Integer team_id;
	
	private String salesman_name;
	
	private Integer salesman_id;
	
	private String salesman_shortcode;
	
	private String personnel_state;
	
	private Integer wms_inve_transa_prod_id;
	
	private Integer wms_inve_transa_id;
	
	private String bill_code;
	
	private String cus_name;
	
	private java.math.BigDecimal investredemp_amount;
	
	private Integer commission_days;
	
	private java.math.BigDecimal disposable_commission_coeff;
	
	private java.math.BigDecimal disposable_commission_money;
	
	private java.math.BigDecimal monthly_commission_coeff;
	
	private java.math.BigDecimal monthly_commission_money;
	
	private Integer monthly_commission_stock;
	
	private java.math.BigDecimal float_disposable_commission_coeff;
	
	private java.math.BigDecimal float_disposable_commission_money;
	
	private java.math.BigDecimal float_monthly_commission_coeff;
	
	private java.math.BigDecimal float_monthly_commission_money;
	
	private Integer float_monthly_commission_stock;
	
	private java.math.BigDecimal net_commission_coeff;
	
	private java.math.BigDecimal net_commission_money;
	
	private Integer net_commission_stock;
	
	private java.math.BigDecimal allopatry_commission_coeff;
	
	private java.math.BigDecimal allopatry_commission_money;
	
	private Integer is_team;
	
	private Integer is_lssue;
	
	private String data_status;
	
	private java.sql.Timestamp create_datetime;
	
	private String create_datetime_str;
	
	private String enable_flag;
	
	private java.sql.Date lssue_date;
	
	private String lssue_date_str;
	
	private String remark;
	
	private boolean isExcludePKFlag;
	private Integer wms_inve_pruduct_category_id;
	private Integer group_id;
	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	  	"wms_inve_commission_performance_new_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_commission_performance_new_id",
		"compay_id",
		"dept_id",
		"team_id",
		"salesman_name",
		"salesman_id",
		"salesman_shortcode",
		"personnel_state",
		"wms_inve_transa_prod_id",
		"wms_inve_transa_id",
		"bill_code",
		"cus_name",
		"investredemp_amount",
		"commission_days",
		"disposable_commission_coeff",
		"disposable_commission_money",
		"monthly_commission_coeff",
		"monthly_commission_money",
		"monthly_commission_stock",
		"float_disposable_commission_coeff",
		"float_disposable_commission_money",
		"float_monthly_commission_coeff",
		"float_monthly_commission_money",
		"float_monthly_commission_stock",
		"net_commission_coeff",
		"net_commission_money",
		"net_commission_stock",
		"allopatry_commission_coeff",
		"allopatry_commission_money",
		"is_team",
		"is_lssue",
		"data_status",
		"create_datetime",
		"create_datetime_str",
		"enable_flag",
		"lssue_date",
		"lssue_date_str",
		"remark",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_commission_performance_new_id () {
		return wms_inve_commission_performance_new_id;
	}
	
	public void setWms_inve_commission_performance_new_id (Integer obj) {
		wms_inve_commission_performance_new_id = obj;
	}
	
	public Integer getCompay_id () {
		return compay_id;
	}
	
	public void setCompay_id (Integer obj) {
		compay_id = obj;
	}
	
	public Integer getDept_id () {
		return dept_id;
	}
	
	public void setDept_id (Integer obj) {
		dept_id = obj;
	}
	
	public Integer getTeam_id () {
		return team_id;
	}
	
	public void setTeam_id (Integer obj) {
		team_id = obj;
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
	
	public String getSalesman_shortcode () {
		return salesman_shortcode;
	}
	
	public void setSalesman_shortcode (String obj) {
		salesman_shortcode = obj;
	}
	
	public String getPersonnel_state () {
		return personnel_state;
	}
	
	public void setPersonnel_state (String obj) {
		personnel_state = obj;
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
	
	public java.math.BigDecimal getInvestredemp_amount () {
		return investredemp_amount;
	}
	
	public void setInvestredemp_amount (java.math.BigDecimal obj) {
		investredemp_amount = obj;
	}
	
	public Integer getCommission_days () {
		return commission_days;
	}
	
	public void setCommission_days (Integer obj) {
		commission_days = obj;
	}
	
	public java.math.BigDecimal getDisposable_commission_coeff () {
		return disposable_commission_coeff;
	}
	
	public void setDisposable_commission_coeff (java.math.BigDecimal obj) {
		disposable_commission_coeff = obj;
	}
	
	public java.math.BigDecimal getDisposable_commission_money () {
		return disposable_commission_money;
	}
	
	public void setDisposable_commission_money (java.math.BigDecimal obj) {
		disposable_commission_money = obj;
	}
	
	public java.math.BigDecimal getMonthly_commission_coeff () {
		return monthly_commission_coeff;
	}
	
	public void setMonthly_commission_coeff (java.math.BigDecimal obj) {
		monthly_commission_coeff = obj;
	}
	
	public java.math.BigDecimal getMonthly_commission_money () {
		return monthly_commission_money;
	}
	
	public void setMonthly_commission_money (java.math.BigDecimal obj) {
		monthly_commission_money = obj;
	}
	
	public Integer getMonthly_commission_stock () {
		return monthly_commission_stock;
	}
	
	public void setMonthly_commission_stock (Integer obj) {
		monthly_commission_stock = obj;
	}
	
	public java.math.BigDecimal getFloat_disposable_commission_coeff () {
		return float_disposable_commission_coeff;
	}
	
	public void setFloat_disposable_commission_coeff (java.math.BigDecimal obj) {
		float_disposable_commission_coeff = obj;
	}
	
	public java.math.BigDecimal getFloat_disposable_commission_money () {
		return float_disposable_commission_money;
	}
	
	public void setFloat_disposable_commission_money (java.math.BigDecimal obj) {
		float_disposable_commission_money = obj;
	}
	
	public java.math.BigDecimal getFloat_monthly_commission_coeff () {
		return float_monthly_commission_coeff;
	}
	
	public void setFloat_monthly_commission_coeff (java.math.BigDecimal obj) {
		float_monthly_commission_coeff = obj;
	}
	
	public java.math.BigDecimal getFloat_monthly_commission_money () {
		return float_monthly_commission_money;
	}
	
	public void setFloat_monthly_commission_money (java.math.BigDecimal obj) {
		float_monthly_commission_money = obj;
	}
	
	public Integer getFloat_monthly_commission_stock () {
		return float_monthly_commission_stock;
	}
	
	public void setFloat_monthly_commission_stock (Integer obj) {
		float_monthly_commission_stock = obj;
	}
	
	public java.math.BigDecimal getNet_commission_coeff () {
		return net_commission_coeff;
	}
	
	public void setNet_commission_coeff (java.math.BigDecimal obj) {
		net_commission_coeff = obj;
	}
	
	public java.math.BigDecimal getNet_commission_money () {
		return net_commission_money;
	}
	
	public void setNet_commission_money (java.math.BigDecimal obj) {
		net_commission_money = obj;
	}
	
	public Integer getNet_commission_stock () {
		return net_commission_stock;
	}
	
	public void setNet_commission_stock (Integer obj) {
		net_commission_stock = obj;
	}
	
	public java.math.BigDecimal getAllopatry_commission_coeff () {
		return allopatry_commission_coeff;
	}
	
	public void setAllopatry_commission_coeff (java.math.BigDecimal obj) {
		allopatry_commission_coeff = obj;
	}
	
	public java.math.BigDecimal getAllopatry_commission_money () {
		return allopatry_commission_money;
	}
	
	public void setAllopatry_commission_money (java.math.BigDecimal obj) {
		allopatry_commission_money = obj;
	}
	
	public Integer getIs_team () {
		return is_team;
	}
	
	public void setIs_team (Integer obj) {
		is_team = obj;
	}
	
	public Integer getIs_lssue () {
		return is_lssue;
	}
	
	public void setIs_lssue (Integer obj) {
		is_lssue = obj;
	}
	
	public String getData_status () {
		return data_status;
	}
	
	public void setData_status (String obj) {
		data_status = obj;
	}
	
	public java.sql.Timestamp getCreate_datetime () {
		return create_datetime;
	}
	
	public void setCreate_datetime (java.sql.Timestamp obj) {
		create_datetime = obj;
	}
	
	public String getCreate_datetime_str () {
		return create_datetime_str;
	}
	
	public void setCreate_datetime_str (String obj) {
		create_datetime_str = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public java.sql.Date getLssue_date () {
		return lssue_date;
	}
	
	public void setLssue_date (java.sql.Date obj) {
		lssue_date = obj;
	}
	
	public String getLssue_date_str () {
		return lssue_date_str;
	}
	
	public void setLssue_date_str (String obj) {
		lssue_date_str = obj;
	}
	
	public String getRemark () {
		return remark;
	}
	
	public void setRemark (String obj) {
		remark = obj;
	}
	
	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}

	public Integer getWms_inve_pruduct_category_id() {
		return wms_inve_pruduct_category_id;
	}

	public void setWms_inve_pruduct_category_id(Integer wms_inve_pruduct_category_id) {
		this.wms_inve_pruduct_category_id = wms_inve_pruduct_category_id;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_inve_commission_performance_new_id", this.wms_inve_commission_performance_new_id);
		paramMap.put("compay_id", this.compay_id);
		paramMap.put("dept_id", this.dept_id);
		paramMap.put("team_id", this.team_id);
		paramMap.put("salesman_name", this.salesman_name);
		paramMap.put("salesman_id", this.salesman_id);
		paramMap.put("salesman_shortcode", this.salesman_shortcode);
		paramMap.put("personnel_state", this.personnel_state);
		paramMap.put("wms_inve_transa_prod_id", this.wms_inve_transa_prod_id);
		paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
		paramMap.put("bill_code", this.bill_code);
		paramMap.put("cus_name", this.cus_name);
		paramMap.put("investredemp_amount", this.investredemp_amount);
		paramMap.put("commission_days", this.commission_days);
		paramMap.put("disposable_commission_coeff", this.disposable_commission_coeff);
		paramMap.put("disposable_commission_money", this.disposable_commission_money);
		paramMap.put("monthly_commission_coeff", this.monthly_commission_coeff);
		paramMap.put("monthly_commission_money", this.monthly_commission_money);
		paramMap.put("monthly_commission_stock", this.monthly_commission_stock);
		paramMap.put("float_disposable_commission_coeff", this.float_disposable_commission_coeff);
		paramMap.put("float_disposable_commission_money", this.float_disposable_commission_money);
		paramMap.put("float_monthly_commission_coeff", this.float_monthly_commission_coeff);
		paramMap.put("float_monthly_commission_money", this.float_monthly_commission_money);
		paramMap.put("float_monthly_commission_stock", this.float_monthly_commission_stock);
		paramMap.put("net_commission_coeff", this.net_commission_coeff);
		paramMap.put("net_commission_money", this.net_commission_money);
		paramMap.put("net_commission_stock", this.net_commission_stock);
		paramMap.put("allopatry_commission_coeff", this.allopatry_commission_coeff);
		paramMap.put("allopatry_commission_money", this.allopatry_commission_money);
		paramMap.put("is_team", this.is_team);
		paramMap.put("is_lssue", this.is_lssue);
		paramMap.put("data_status", this.data_status);
		paramMap.put("create_datetime", this.create_datetime);
		paramMap.put("create_datetime_str", this.create_datetime_str);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("lssue_date", this.lssue_date);
		paramMap.put("lssue_date_str", this.lssue_date_str);
		paramMap.put("remark", this.remark);
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