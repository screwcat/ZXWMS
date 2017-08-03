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

public class WmsInveSpecialApproval implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer special_approval_leader_id;
	
	private String special_approval_leader_name;
	
	private String special_approval_leader_phone;
	
	private String special_approval_leader_shortcode;
	
	private Integer special_approval_leader_pmid;
	
	private String enable_flag;
	
	private Integer def_asc;
	
	private Integer	top_function;
	
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
	  	"special_approval_leader_id"
	};
	
	private static String[] columnNameArr = {
		"special_approval_leader_id",
		"special_approval_leader_name",
		"special_approval_leader_phone",
		"special_approval_leader_shortcode",
		"special_approval_leader_pmid",
		"enable_flag",
		"def_asc",
		"isExcludePKFlag",
		"top_function"
	};
  
	public Integer getSpecial_approval_leader_id () {
		return special_approval_leader_id;
	}
	
	public void setSpecial_approval_leader_id (Integer obj) {
		special_approval_leader_id = obj;
	}
	
	public String getSpecial_approval_leader_name () {
		return special_approval_leader_name;
	}
	
	public void setSpecial_approval_leader_name (String obj) {
		special_approval_leader_name = obj;
	}
	
	public String getSpecial_approval_leader_phone () {
		return special_approval_leader_phone;
	}
	
	public void setSpecial_approval_leader_phone (String obj) {
		special_approval_leader_phone = obj;
	}
	
	public String getSpecial_approval_leader_shortcode () {
		return special_approval_leader_shortcode;
	}
	
	public void setSpecial_approval_leader_shortcode (String obj) {
		special_approval_leader_shortcode = obj;
	}
	
	public Integer getSpecial_approval_leader_pmid () {
		return special_approval_leader_pmid;
	}
	
	public void setSpecial_approval_leader_pmid (Integer obj) {
		special_approval_leader_pmid = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public Integer getDef_asc () {
		return def_asc;
	}
	
	public void setDef_asc (Integer obj) {
		def_asc = obj;
	}
	
	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	public Integer getTop_function() {
		return top_function;
	}

	public void setTop_function(Integer top_function) {
		this.top_function = top_function;
	}

	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("special_approval_leader_id", this.special_approval_leader_id);
		paramMap.put("special_approval_leader_name", this.special_approval_leader_name);
		paramMap.put("special_approval_leader_phone", this.special_approval_leader_phone);
		paramMap.put("special_approval_leader_shortcode", this.special_approval_leader_shortcode);
		paramMap.put("special_approval_leader_pmid", this.special_approval_leader_pmid);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("def_asc", this.def_asc);
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