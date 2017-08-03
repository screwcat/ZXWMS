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

public class WmsInveCommissionRewardPerformance implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_commission_reward_performance_id;
	
	private Integer compay_id;
	
	private Integer dept_id;
	
	private Integer team_id;
	
	private Integer salesman_id;
	
	private String salesman_name;
	
	private String salesman_shortcode;
	
	private String job_id;
	
	private java.math.BigDecimal stock;
	
	private java.math.BigDecimal monthly_net_growth;
	
	private java.math.BigDecimal monthly_newly_added;
	
	private java.math.BigDecimal reward_commission_coeff;
	
	private java.math.BigDecimal reward_money;
	
	private java.sql.Timestamp create_datetime;
	
	private String create_datetime_str;
	
	private String enable_flag;
	
	private String remark;
	
	private java.math.BigDecimal cash_prize;
	
	private Integer  diff_types;
	
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
	  	"wms_inve_commission_reward_performance_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_commission_reward_performance_id",
		"compay_id",
		"dept_id",
		"team_id",
		"salesman_id",
		"salesman_name",
		"salesman_shortcode",
		"job_id",
		"stock",
		"monthly_net_growth",
		"monthly_newly_added",
		"reward_commission_coeff",
		"reward_money",
		"create_datetime",
		"create_datetime_str",
		"enable_flag",
		"remark",
		"cash_prize",
		"diff_types",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_commission_reward_performance_id () {
		return wms_inve_commission_reward_performance_id;
	}
	
	public void setWms_inve_commission_reward_performance_id (Integer obj) {
		wms_inve_commission_reward_performance_id = obj;
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
	
	public Integer getSalesman_id () {
		return salesman_id;
	}
	
	public void setSalesman_id (Integer obj) {
		salesman_id = obj;
	}
	
	public String getSalesman_name () {
		return salesman_name;
	}
	
	public void setSalesman_name (String obj) {
		salesman_name = obj;
	}
	
	public String getSalesman_shortcode () {
		return salesman_shortcode;
	}
	
	public void setSalesman_shortcode (String obj) {
		salesman_shortcode = obj;
	}

	public java.math.BigDecimal getStock () {
		return stock;
	}
	
	public void setStock (java.math.BigDecimal obj) {
		stock = obj;
	}
	
	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public java.math.BigDecimal getMonthly_net_growth () {
		return monthly_net_growth;
	}
	
	public void setMonthly_net_growth (java.math.BigDecimal obj) {
		monthly_net_growth = obj;
	}
	
	public java.math.BigDecimal getMonthly_newly_added () {
		return monthly_newly_added;
	}
	
	public void setMonthly_newly_added (java.math.BigDecimal obj) {
		monthly_newly_added = obj;
	}
	
	public java.math.BigDecimal getReward_commission_coeff () {
		return reward_commission_coeff;
	}
	
	public void setReward_commission_coeff (java.math.BigDecimal obj) {
		reward_commission_coeff = obj;
	}
	
	public java.math.BigDecimal getReward_money () {
		return reward_money;
	}
	
	public void setReward_money (java.math.BigDecimal obj) {
		reward_money = obj;
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
	
	public String getRemark () {
		return remark;
	}
	
	public void setRemark (String obj) {
		remark = obj;
	}
	
	public java.math.BigDecimal getCash_prize() {
		return cash_prize;
	}

	public void setCash_prize(java.math.BigDecimal cash_prize) {
		this.cash_prize = cash_prize;
	}

	public Integer getDiff_types() {
		return diff_types;
	}

	public void setDiff_types(Integer diff_types) {
		this.diff_types = diff_types;
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
		paramMap.put("wms_inve_commission_reward_performance_id", this.wms_inve_commission_reward_performance_id);
		paramMap.put("compay_id", this.compay_id);
		paramMap.put("dept_id", this.dept_id);
		paramMap.put("team_id", this.team_id);
		paramMap.put("salesman_id", this.salesman_id);
		paramMap.put("salesman_name", this.salesman_name);
		paramMap.put("salesman_shortcode", this.salesman_shortcode);
		paramMap.put("job_id", this.job_id);
		paramMap.put("stock", this.stock);
		paramMap.put("monthly_net_growth", this.monthly_net_growth);
		paramMap.put("monthly_newly_added", this.monthly_newly_added);
		paramMap.put("reward_commission_coeff", this.reward_commission_coeff);
		paramMap.put("reward_money", this.reward_money);
		paramMap.put("create_datetime", this.create_datetime);
		paramMap.put("create_datetime_str", this.create_datetime_str);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("remark", this.remark);
		paramMap.put("cash_prize", this.cash_prize);
		paramMap.put("diff_types", this.diff_types);
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