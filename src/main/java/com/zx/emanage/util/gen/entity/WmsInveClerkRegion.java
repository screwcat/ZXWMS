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

public class WmsInveClerkRegion implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_clerk_region_id;
	
	private String region_number;
	
	private String region_name;
	
	private String region_protocol_prefix;
	
	private String region_protocol_area;
	
	private String region_postcode;
	
	private String region_protocol_b_name;
	
	private String region_protocol_b_area;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private Integer create_user_dept_id;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
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
	  	"wms_inve_clerk_region_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_clerk_region_id",
		"region_number",
		"region_name",
		"region_protocol_prefix",
		"region_protocol_area",
		"region_postcode",
		"region_protocol_b_name",
		"region_protocol_b_area",
		"create_user_id",
		"create_user_name",
		"create_user_dept_id",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_clerk_region_id () {
		return wms_inve_clerk_region_id;
	}
	
	public void setWms_inve_clerk_region_id (Integer obj) {
		wms_inve_clerk_region_id = obj;
	}
	
	public String getRegion_number () {
		return region_number;
	}
	
	public void setRegion_number (String obj) {
		region_number = obj;
	}
	
	public String getRegion_name () {
		return region_name;
	}
	
	public void setRegion_name (String obj) {
		region_name = obj;
	}
	
	public String getRegion_protocol_prefix () {
		return region_protocol_prefix;
	}
	
	public void setRegion_protocol_prefix (String obj) {
		region_protocol_prefix = obj;
	}
	
	public String getRegion_protocol_area () {
		return region_protocol_area;
	}
	
	public void setRegion_protocol_area (String obj) {
		region_protocol_area = obj;
	}
	
	public String getRegion_postcode () {
		return region_postcode;
	}
	
	public void setRegion_postcode (String obj) {
		region_postcode = obj;
	}
	
	public String getRegion_protocol_b_name () {
		return region_protocol_b_name;
	}
	
	public void setRegion_protocol_b_name (String obj) {
		region_protocol_b_name = obj;
	}
	
	public String getRegion_protocol_b_area () {
		return region_protocol_b_area;
	}
	
	public void setRegion_protocol_b_area (String obj) {
		region_protocol_b_area = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
	}
	
	public String getCreate_user_name () {
		return create_user_name;
	}
	
	public void setCreate_user_name (String obj) {
		create_user_name = obj;
	}
	
	public Integer getCreate_user_dept_id () {
		return create_user_dept_id;
	}
	
	public void setCreate_user_dept_id (Integer obj) {
		create_user_dept_id = obj;
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
	
	public Integer getLast_update_user_id () {
		return last_update_user_id;
	}
	
	public void setLast_update_user_id (Integer obj) {
		last_update_user_id = obj;
	}
	
	public java.sql.Timestamp getLast_update_timestamp () {
		return last_update_timestamp;
	}
	
	public void setLast_update_timestamp (java.sql.Timestamp obj) {
		last_update_timestamp = obj;
	}
	
	public String getLast_update_timestamp_str () {
		return last_update_timestamp_str;
	}
	
	public void setLast_update_timestamp_str (String obj) {
		last_update_timestamp_str = obj;
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
		paramMap.put("wms_inve_clerk_region_id", this.wms_inve_clerk_region_id);
		paramMap.put("region_number", this.region_number);
		paramMap.put("region_name", this.region_name);
		paramMap.put("region_protocol_prefix", this.region_protocol_prefix);
		paramMap.put("region_protocol_area", this.region_protocol_area);
		paramMap.put("region_postcode", this.region_postcode);
		paramMap.put("region_protocol_b_name", this.region_protocol_b_name);
		paramMap.put("region_protocol_b_area", this.region_protocol_b_area);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_user_name", this.create_user_name);
		paramMap.put("create_user_dept_id", this.create_user_dept_id);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
		paramMap.put("last_update_user_id", this.last_update_user_id);
		paramMap.put("last_update_timestamp", this.last_update_timestamp);
		paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
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