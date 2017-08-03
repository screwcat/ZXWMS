package com.zx.emanage.util.gen.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsInveRedeemPrincipalDetail implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_redeem_principal_detail_id;
	
	private Integer wms_inve_redeem_detail_id;
	
	private String principal_type;
	
	private java.math.BigDecimal principal_amount;
	
	private java.math.BigDecimal reward_income;
	
	private java.math.BigDecimal income_amount;
	
	private java.math.BigDecimal paid_income_amount;
	
	private java.math.BigDecimal extend_amount;
	
	private java.math.BigDecimal used_income_amount;
	
	private java.math.BigDecimal total_management_fee;
	
    private java.math.BigDecimal current_income;

    private java.math.BigDecimal income_to_principal_amount;

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
	  	"wms_inve_redeem_principal_detail_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_redeem_principal_detail_id",
		"wms_inve_redeem_detail_id",
		"principal_type",
		"principal_amount",
		"income_amount",
		"paid_income_amount",
		"used_income_amount",
		"total_management_fee",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_redeem_principal_detail_id () {
		return wms_inve_redeem_principal_detail_id;
	}
	
	public void setWms_inve_redeem_principal_detail_id (Integer obj) {
		wms_inve_redeem_principal_detail_id = obj;
	}
	
	public Integer getWms_inve_redeem_detail_id () {
		return wms_inve_redeem_detail_id;
	}
	
	public void setWms_inve_redeem_detail_id (Integer obj) {
		wms_inve_redeem_detail_id = obj;
	}
	
	public String getPrincipal_type () {
		return principal_type;
	}
	
	public void setPrincipal_type (String obj) {
		principal_type = obj;
	}
	
	public java.math.BigDecimal getPrincipal_amount () {
		return principal_amount;
	}
	
	public void setPrincipal_amount (java.math.BigDecimal obj) {
		principal_amount = obj;
	}
	
	public java.math.BigDecimal getReward_income() {
		return reward_income;
	}

	public void setReward_income(java.math.BigDecimal reward_income) {
		this.reward_income = reward_income;
	}

	public java.math.BigDecimal getIncome_amount () {
		return income_amount;
	}
	
	public void setIncome_amount (java.math.BigDecimal obj) {
		income_amount = obj;
	}
	
	public java.math.BigDecimal getPaid_income_amount () {
		return paid_income_amount;
	}
	
	public void setPaid_income_amount (java.math.BigDecimal obj) {
		paid_income_amount = obj;
	}
	
	public java.math.BigDecimal getUsed_income_amount () {
		return used_income_amount;
	}
	
	public void setUsed_income_amount (java.math.BigDecimal obj) {
		used_income_amount = obj;
	}
	
	public java.math.BigDecimal getExtend_amount() {
		return extend_amount;
	}

	public void setExtend_amount(java.math.BigDecimal extend_amount) {
		this.extend_amount = extend_amount;
	}
	
	public java.math.BigDecimal getTotal_management_fee() {
		return total_management_fee;
	}

	public void setTotal_management_fee(java.math.BigDecimal total_management_fee) {
		this.total_management_fee = total_management_fee;
	}

    public java.math.BigDecimal getCurrent_income()
    {
        return current_income;
    }

    public void setCurrent_income(java.math.BigDecimal current_income)
    {
        this.current_income = current_income;
    }

    public java.math.BigDecimal getIncome_to_principal_amount()
    {
        return income_to_principal_amount;
    }

    public void setIncome_to_principal_amount(java.math.BigDecimal income_to_principal_amount)
    {
        this.income_to_principal_amount = income_to_principal_amount;
    }

    public boolean getgetIsExcludePKFlag()
    {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_inve_redeem_principal_detail_id", this.wms_inve_redeem_principal_detail_id);
		paramMap.put("wms_inve_redeem_detail_id", this.wms_inve_redeem_detail_id);
		paramMap.put("principal_type", this.principal_type);
		paramMap.put("principal_amount", this.principal_amount);
		paramMap.put("income_amount", this.income_amount);
		paramMap.put("paid_income_amount", this.paid_income_amount);
		paramMap.put("used_income_amount", this.used_income_amount);
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