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

public class WmsInveJob implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_job_id;
	
	private String job_code;
	
	private String job_encode;
	
	private String job_name;
	
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
	  	"wms_inve_job_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_job_id",
		"job_code",
		"job_encode",
		"job_name",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_job_id () {
		return wms_inve_job_id;
	}
	
	public void setWms_inve_job_id (Integer obj) {
		wms_inve_job_id = obj;
	}
	
	public String getJob_code () {
		return job_code;
	}
	
	public void setJob_code (String obj) {
		job_code = obj;
	}
	
	public String getJob_encode () {
		return job_encode;
	}
	
	public void setJob_encode (String obj) {
		job_encode = obj;
	}
	
	public String getJob_name () {
		return job_name;
	}
	
	public void setJob_name (String obj) {
		job_name = obj;
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
		paramMap.put("wms_inve_job_id", this.wms_inve_job_id);
		paramMap.put("job_code", this.job_code);
		paramMap.put("job_encode", this.job_encode);
		paramMap.put("job_name", this.job_name);
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