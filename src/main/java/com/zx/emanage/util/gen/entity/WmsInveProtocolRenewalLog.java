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

public class WmsInveProtocolRenewalLog implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_protocol_renewal_log_id;
	
	private Integer wms_inve_transa_id;
	
	private Integer wms_inve_transa_prod_id;
	
	private Integer wms_inve_pruduct_category_id;
	
	private String matching_renewal_result;
	
	private String matching_renewal_remark;
	
	private String enable_flag;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
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
	  	"wms_inve_protocol_renewal_log_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_protocol_renewal_log_id",
		"wms_inve_transa_id",
		"wms_inve_transa_prod_id",
		"wms_inve_pruduct_category_id",
		"matching_renewal_result",
		"matching_renewal_remark",
		"enable_flag",
		"create_timestamp",
		"create_timestamp_str",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_protocol_renewal_log_id () {
		return wms_inve_protocol_renewal_log_id;
	}
	
	public void setWms_inve_protocol_renewal_log_id (Integer obj) {
		wms_inve_protocol_renewal_log_id = obj;
	}
	
	public Integer getWms_inve_transa_id () {
		return wms_inve_transa_id;
	}
	
	public void setWms_inve_transa_id (Integer obj) {
		wms_inve_transa_id = obj;
	}
	
	public Integer getWms_inve_transa_prod_id () {
		return wms_inve_transa_prod_id;
	}
	
	public void setWms_inve_transa_prod_id (Integer obj) {
		wms_inve_transa_prod_id = obj;
	}
	
	public Integer getWms_inve_pruduct_category_id () {
		return wms_inve_pruduct_category_id;
	}
	
	public void setWms_inve_pruduct_category_id (Integer obj) {
		wms_inve_pruduct_category_id = obj;
	}
	
	public String getMatching_renewal_result () {
		return matching_renewal_result;
	}
	
	public void setMatching_renewal_result (String obj) {
		matching_renewal_result = obj;
	}
	
	public String getMatching_renewal_remark () {
		return matching_renewal_remark;
	}
	
	public void setMatching_renewal_remark (String obj) {
		matching_renewal_remark = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public java.sql.Timestamp getCreate_timestamp () {
		return create_timestamp;
	}
	
	public void setCreate_timestamp (java.sql.Timestamp obj) {
		create_timestamp = obj;
	}
	
	public String getCreate_timestamp_str () {
		return create_timestamp_str;
	}
	
	public void setCreate_timestamp_str (String obj) {
		create_timestamp_str = obj;
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
		paramMap.put("wms_inve_protocol_renewal_log_id", this.wms_inve_protocol_renewal_log_id);
		paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
		paramMap.put("wms_inve_transa_prod_id", this.wms_inve_transa_prod_id);
		paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
		paramMap.put("matching_renewal_result", this.matching_renewal_result);
		paramMap.put("matching_renewal_remark", this.matching_renewal_remark);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
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