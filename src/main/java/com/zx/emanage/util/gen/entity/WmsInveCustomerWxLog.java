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

public class WmsInveCustomerWxLog implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_customer_wx_log_id;
	
	private String log_text;
	
	private java.sql.Timestamp log_time;
	
	private String log_time_str;
	
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
	  	"wms_inve_customer_wx_log_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_customer_wx_log_id",
		"log_text",
		"log_time",
		"log_time_str",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_customer_wx_log_id () {
		return wms_inve_customer_wx_log_id;
	}
	
	public void setWms_inve_customer_wx_log_id (Integer obj) {
		wms_inve_customer_wx_log_id = obj;
	}
	
	public String getLog_text () {
		return log_text;
	}
	
	public void setLog_text (String obj) {
		log_text = obj;
	}
	
	public java.sql.Timestamp getLog_time () {
		return log_time;
	}
	
	public void setLog_time (java.sql.Timestamp obj) {
		log_time = obj;
	}
	
	public String getLog_time_str () {
		return log_time_str;
	}
	
	public void setLog_time_str (String obj) {
		log_time_str = obj;
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
		paramMap.put("wms_inve_customer_wx_log_id", this.wms_inve_customer_wx_log_id);
		paramMap.put("log_text", this.log_text);
		paramMap.put("log_time", this.log_time);
		paramMap.put("log_time_str", this.log_time_str);
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