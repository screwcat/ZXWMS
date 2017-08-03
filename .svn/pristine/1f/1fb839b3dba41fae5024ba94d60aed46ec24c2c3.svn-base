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

public class WmsPostDunningDetailed implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_post_dunning_detailed_id;
	
	private Integer wms_post_dunning_head_id;
	
	private Integer wms_fina_cre_pay_id;
	
	private Integer wms_cre_credit_head_id;
	
	private java.sql.Date dunning_datetime;
	
	private String dunning_datetime_str;
	
	private String dunning_name;
	
	private String dunning_remark;
	
	private String enable_flag;
	
	private Integer dunning_id;
	
	private Integer dunning_deptid;
	
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
	  	"wms_post_dunning_detailed_id"
	};
	
	private static String[] columnNameArr = {
		"wms_post_dunning_detailed_id",
		"wms_post_dunning_head_id",
		"wms_fina_cre_pay_id",
		"wms_cre_credit_head_id",
		"dunning_datetime",
		"dunning_datetime_str",
		"dunning_name",
		"dunning_remark",
		"enable_flag",
		"dunning_id",
		"dunning_deptid",
		"isExcludePKFlag"
	};
  
	public Integer getWms_post_dunning_detailed_id () {
		return wms_post_dunning_detailed_id;
	}
	
	public void setWms_post_dunning_detailed_id (Integer obj) {
		wms_post_dunning_detailed_id = obj;
	}
	
	public Integer getWms_post_dunning_head_id () {
		return wms_post_dunning_head_id;
	}
	
	public void setWms_post_dunning_head_id (Integer obj) {
		wms_post_dunning_head_id = obj;
	}
	
	public Integer getWms_fina_cre_pay_id () {
		return wms_fina_cre_pay_id;
	}
	
	public void setWms_fina_cre_pay_id (Integer obj) {
		wms_fina_cre_pay_id = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public java.sql.Date getDunning_datetime () {
		return dunning_datetime;
	}
	
	public void setDunning_datetime (java.sql.Date obj) {
		dunning_datetime = obj;
	}
	
	public String getDunning_datetime_str () {
		return dunning_datetime_str;
	}
	
	public void setDunning_datetime_str (String obj) {
		dunning_datetime_str = obj;
	}
	
	public String getDunning_name () {
		return dunning_name;
	}
	
	public void setDunning_name (String obj) {
		dunning_name = obj;
	}
	
	public String getDunning_remark () {
		return dunning_remark;
	}
	
	public void setDunning_remark (String obj) {
		dunning_remark = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public Integer getDunning_id () {
		return dunning_id;
	}
	
	public void setDunning_id (Integer obj) {
		dunning_id = obj;
	}
	
	public Integer getDunning_deptid () {
		return dunning_deptid;
	}
	
	public void setDunning_deptid (Integer obj) {
		dunning_deptid = obj;
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
		paramMap.put("wms_post_dunning_detailed_id", this.wms_post_dunning_detailed_id);
		paramMap.put("wms_post_dunning_head_id", this.wms_post_dunning_head_id);
		paramMap.put("wms_fina_cre_pay_id", this.wms_fina_cre_pay_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("dunning_datetime", this.dunning_datetime);
		paramMap.put("dunning_datetime_str", this.dunning_datetime_str);
		paramMap.put("dunning_name", this.dunning_name);
		paramMap.put("dunning_remark", this.dunning_remark);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("dunning_id", this.dunning_id);
		paramMap.put("dunning_deptid", this.dunning_deptid);
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