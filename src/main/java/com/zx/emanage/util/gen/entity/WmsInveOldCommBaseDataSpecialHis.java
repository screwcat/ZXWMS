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

public class WmsInveOldCommBaseDataSpecialHis implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_old_comm_base_data_special_his_id;
	
	private Integer department_manager_id;
	
	private Integer salesman_id;
	
	private String product_account_desc;
	
	private java.math.BigDecimal product_account;
	
	private java.math.BigDecimal team_comm_coeff;
	
	private java.math.BigDecimal old_team_comm_mon;
	
	private Integer commission_days;
	
	private java.sql.Timestamp statics_time;
	
	private String statics_time_str;
	
	private String statics_month;
	
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
	  	"wms_inve_old_comm_base_data_special_his_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_old_comm_base_data_special_his_id",
		"department_manager_id",
		"salesman_id",
		"product_account_desc",
		"product_account",
		"team_comm_coeff",
		"old_team_comm_mon",
		"commission_days",
		"statics_time",
		"statics_time_str",
		"statics_month",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_old_comm_base_data_special_his_id () {
		return wms_inve_old_comm_base_data_special_his_id;
	}
	
	public void setWms_inve_old_comm_base_data_special_his_id (Integer obj) {
		wms_inve_old_comm_base_data_special_his_id = obj;
	}
	
	public Integer getDepartment_manager_id () {
		return department_manager_id;
	}
	
	public void setDepartment_manager_id (Integer obj) {
		department_manager_id = obj;
	}
	
	public Integer getSalesman_id () {
		return salesman_id;
	}
	
	public void setSalesman_id (Integer obj) {
		salesman_id = obj;
	}
	
	public String getProduct_account_desc () {
		return product_account_desc;
	}
	
	public void setProduct_account_desc (String obj) {
		product_account_desc = obj;
	}
	
	public java.math.BigDecimal getProduct_account () {
		return product_account;
	}
	
	public void setProduct_account (java.math.BigDecimal obj) {
		product_account = obj;
	}
	
	public java.math.BigDecimal getTeam_comm_coeff () {
		return team_comm_coeff;
	}
	
	public void setTeam_comm_coeff (java.math.BigDecimal obj) {
		team_comm_coeff = obj;
	}
	
	public java.math.BigDecimal getOld_team_comm_mon () {
		return old_team_comm_mon;
	}
	
	public void setOld_team_comm_mon (java.math.BigDecimal obj) {
		old_team_comm_mon = obj;
	}
	
	public Integer getCommission_days () {
		return commission_days;
	}
	
	public void setCommission_days (Integer obj) {
		commission_days = obj;
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
	
	public String getStatics_month () {
		return statics_month;
	}
	
	public void setStatics_month (String obj) {
		statics_month = obj;
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
		paramMap.put("wms_inve_old_comm_base_data_special_his_id", this.wms_inve_old_comm_base_data_special_his_id);
		paramMap.put("department_manager_id", this.department_manager_id);
		paramMap.put("salesman_id", this.salesman_id);
		paramMap.put("product_account_desc", this.product_account_desc);
		paramMap.put("product_account", this.product_account);
		paramMap.put("team_comm_coeff", this.team_comm_coeff);
		paramMap.put("old_team_comm_mon", this.old_team_comm_mon);
		paramMap.put("commission_days", this.commission_days);
		paramMap.put("statics_time", this.statics_time);
		paramMap.put("statics_time_str", this.statics_time_str);
		paramMap.put("statics_month", this.statics_month);
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