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

public class PmPersonnelOtherinfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer pm_personnel_otherinfo_id;
	
	private Integer pm_personnel_id;
	
	private String is_all_flag;
	
	private String ensure_date;
	
	private String ensure_way;
	
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
	  	"pm_personnel_otherinfo_id"
	};
	
	private static String[] columnNameArr = {
		"pm_personnel_otherinfo_id",
		"pm_personnel_id",
		"is_all_flag",
		"ensure_date",
		"ensure_way",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getPm_personnel_otherinfo_id () {
		return pm_personnel_otherinfo_id;
	}
	
	public void setPm_personnel_otherinfo_id (Integer obj) {
		pm_personnel_otherinfo_id = obj;
	}
	
	public Integer getPm_personnel_id () {
		return pm_personnel_id;
	}
	
	public void setPm_personnel_id (Integer obj) {
		pm_personnel_id = obj;
	}
	
	public String getIs_all_flag () {
		return is_all_flag;
	}
	
	public void setIs_all_flag (String obj) {
		is_all_flag = obj;
	}
	
	public String getEnsure_date () {
		return ensure_date;
	}
	
	public void setEnsure_date (String obj) {
		ensure_date = obj;
	}
	
	public String getEnsure_way () {
		return ensure_way;
	}
	
	public void setEnsure_way (String obj) {
		ensure_way = obj;
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
		paramMap.put("pm_personnel_otherinfo_id", this.pm_personnel_otherinfo_id);
		paramMap.put("pm_personnel_id", this.pm_personnel_id);
		paramMap.put("is_all_flag", this.is_all_flag);
		paramMap.put("ensure_date", this.ensure_date);
		paramMap.put("ensure_way", this.ensure_way);
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