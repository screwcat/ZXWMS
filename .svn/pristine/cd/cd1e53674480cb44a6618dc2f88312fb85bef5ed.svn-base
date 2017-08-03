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

public class WmsFinaAuditorPerson implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer auditor_person_id;
	
	private Integer person_id;
	
	private String person_name;
	
	private String auditor_remark;
	
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
	  	"auditor_person_id"
	};
	
	private static String[] columnNameArr = {
		"auditor_person_id",
		"person_id",
		"person_name",
		"auditor_remark",
		"isExcludePKFlag"
	};
  
	public Integer getAuditor_person_id () {
		return auditor_person_id;
	}
	
	public void setAuditor_person_id (Integer obj) {
		auditor_person_id = obj;
	}
	
	public Integer getPerson_id () {
		return person_id;
	}
	
	public void setPerson_id (Integer obj) {
		person_id = obj;
	}
	
	public String getPerson_name () {
		return person_name;
	}
	
	public void setPerson_name (String obj) {
		person_name = obj;
	}
	
	public String getAuditor_remark () {
		return auditor_remark;
	}
	
	public void setAuditor_remark (String obj) {
		auditor_remark = obj;
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
		paramMap.put("auditor_person_id", this.auditor_person_id);
		paramMap.put("person_id", this.person_id);
		paramMap.put("person_name", this.person_name);
		paramMap.put("auditor_remark", this.auditor_remark);
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