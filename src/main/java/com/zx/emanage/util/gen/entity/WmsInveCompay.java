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

public class WmsInveCompay implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_compay_id;
	
	private Integer dept_id;
	
	private Integer compay_id;
	
	private String compay_name;
	
	private Integer is_outside;
	
	private String enable_flag;
	
	private java.sql.Timestamp create_datetime;
	
	private String create_datetime_str;
	
	private Integer def_asc;
	
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
	  	"wms_inve_compay_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_compay_id",
		"dept_id",
		"compay_id",
		"compay_name",
		"is_outside",
		"enable_flag",
		"create_datetime",
		"create_datetime_str",
		"def_asc",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_compay_id () {
		return wms_inve_compay_id;
	}
	
	public void setWms_inve_compay_id (Integer obj) {
		wms_inve_compay_id = obj;
	}
	
	public Integer getDept_id () {
		return dept_id;
	}
	
	public void setDept_id (Integer obj) {
		dept_id = obj;
	}
	
	public Integer getCompay_id () {
		return compay_id;
	}
	
	public void setCompay_id (Integer obj) {
		compay_id = obj;
	}
	
	public String getCompay_name () {
		return compay_name;
	}
	
	public void setCompay_name (String obj) {
		compay_name = obj;
	}
	
	public Integer getIs_outside () {
		return is_outside;
	}
	
	public void setIs_outside (Integer obj) {
		is_outside = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
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
	
	
	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_inve_compay_id", this.wms_inve_compay_id);
		paramMap.put("dept_id", this.dept_id);
		paramMap.put("compay_id", this.compay_id);
		paramMap.put("compay_name", this.compay_name);
		paramMap.put("is_outside", this.is_outside);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("create_datetime", this.create_datetime);
		paramMap.put("create_datetime_str", this.create_datetime_str);
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