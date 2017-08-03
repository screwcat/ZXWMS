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

public class WmsInvePadPower implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_pad_power_id;
	
	private String device_num;
	
	private Integer personnel_id;
	
    private String personnel_shortcode;
	
	private java.sql.Timestamp end_time;
	
	private String end_time_str;
	
	private java.sql.Timestamp start_time;
	
	private String start_time_str;
	
	private Integer create_user_id;
	
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
	  	"wms_inve_pad_power_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_pad_power_id",
		"device_num",
		"personnel_id",
		"personnel_shortcode",
		"end_time",
		"end_time_str",
		"start_time",
		"start_time_str",
		"create_user_id",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_pad_power_id () {
		return wms_inve_pad_power_id;
	}
	
	public void setWms_inve_pad_power_id (Integer obj) {
		wms_inve_pad_power_id = obj;
	}
	
	public String getDevice_num () {
		return device_num;
	}
	
	public void setDevice_num (String obj) {
		device_num = obj;
	}
	
	public Integer getPersonnel_id () {
		return personnel_id;
	}
	
	public void setPersonnel_id (Integer obj) {
		personnel_id = obj;
	}
	
    public String getPersonnel_shortcode()
    {
        return personnel_shortcode;
    }

    public void setPersonnel_shortcode(String personnel_shortcode)
    {
        this.personnel_shortcode = personnel_shortcode;
    }

    public java.sql.Timestamp getEnd_time()
    {
		return end_time;
	}
	
	public void setEnd_time (java.sql.Timestamp obj) {
		end_time = obj;
	}
	
	public String getEnd_time_str () {
		return end_time_str;
	}
	
	public void setEnd_time_str (String obj) {
		end_time_str = obj;
	}
	
	public java.sql.Timestamp getStart_time () {
		return start_time;
	}
	
	public void setStart_time (java.sql.Timestamp obj) {
		start_time = obj;
	}
	
	public String getStart_time_str () {
		return start_time_str;
	}
	
	public void setStart_time_str (String obj) {
		start_time_str = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
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
		paramMap.put("wms_inve_pad_power_id", this.wms_inve_pad_power_id);
		paramMap.put("device_num", this.device_num);
		paramMap.put("personnel_id", this.personnel_id);
		paramMap.put("personnel_shortcode", this.personnel_shortcode);
		paramMap.put("end_time", this.end_time);
		paramMap.put("end_time_str", this.end_time_str);
		paramMap.put("start_time", this.start_time);
		paramMap.put("start_time_str", this.start_time_str);
		paramMap.put("create_user_id", this.create_user_id);
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