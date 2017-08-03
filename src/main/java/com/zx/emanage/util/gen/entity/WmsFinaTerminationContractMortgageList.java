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

public class WmsFinaTerminationContractMortgageList implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_fina_termination_contract_mortgage_list_id;
	
	private Integer wms_fina_termination_contract_id;
	
	private String collateral_name;
	
	private Long collateral_accout;
	
	private java.math.BigDecimal collateral_estimated_value;
	
	private java.math.BigDecimal collateral_actual_value;
	
	private java.math.BigDecimal collateral_value;
	
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
	  	"wms_fina_termination_contract_mortgage_list_id"
	};
	
	private static String[] columnNameArr = {
		"wms_fina_termination_contract_mortgage_list_id",
		"wms_fina_termination_contract_id",
		"collateral_name",
		"collateral_accout",
		"collateral_estimated_value",
		"collateral_actual_value",
		"collateral_value",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_fina_termination_contract_mortgage_list_id () {
		return wms_fina_termination_contract_mortgage_list_id;
	}
	
	public void setWms_fina_termination_contract_mortgage_list_id (Integer obj) {
		wms_fina_termination_contract_mortgage_list_id = obj;
	}
	
	public Integer getWms_fina_termination_contract_id () {
		return wms_fina_termination_contract_id;
	}
	
	public void setWms_fina_termination_contract_id (Integer obj) {
		wms_fina_termination_contract_id = obj;
	}
	
	public String getCollateral_name () {
		return collateral_name;
	}
	
	public void setCollateral_name (String obj) {
		collateral_name = obj;
	}
	
	public Long getCollateral_accout () {
		return collateral_accout;
	}
	
	public void setCollateral_accout (Long obj) {
		collateral_accout = obj;
	}
	
	public java.math.BigDecimal getCollateral_estimated_value () {
		return collateral_estimated_value;
	}
	
	public void setCollateral_estimated_value (java.math.BigDecimal obj) {
		collateral_estimated_value = obj;
	}
	
	public java.math.BigDecimal getCollateral_actual_value () {
		return collateral_actual_value;
	}
	
	public void setCollateral_actual_value (java.math.BigDecimal obj) {
		collateral_actual_value = obj;
	}
	
	public java.math.BigDecimal getCollateral_value () {
		return collateral_value;
	}
	
	public void setCollateral_value (java.math.BigDecimal obj) {
		collateral_value = obj;
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
		paramMap.put("wms_fina_termination_contract_mortgage_list_id", this.wms_fina_termination_contract_mortgage_list_id);
		paramMap.put("wms_fina_termination_contract_id", this.wms_fina_termination_contract_id);
		paramMap.put("collateral_name", this.collateral_name);
		paramMap.put("collateral_accout", this.collateral_accout);
		paramMap.put("collateral_estimated_value", this.collateral_estimated_value);
		paramMap.put("collateral_actual_value", this.collateral_actual_value);
		paramMap.put("collateral_value", this.collateral_value);
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