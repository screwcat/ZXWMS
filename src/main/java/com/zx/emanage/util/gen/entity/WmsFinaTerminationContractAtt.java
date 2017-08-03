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

public class WmsFinaTerminationContractAtt implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_fina_termination_contract_att_id;
	
	private String data_type;
	
	private String attachment_old_name;
	
	private String attachment_new_name;
	
	private String attachment_address;
	
	private String attachment_type;
	
	private Integer wms_fina_termination_contract_id;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
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
	  	"wms_fina_termination_contract_att_id"
	};
	
	private static String[] columnNameArr = {
		"wms_fina_termination_contract_att_id",
		"data_type",
		"attachment_old_name",
		"attachment_new_name",
		"attachment_address",
		"attachment_type",
		"wms_fina_termination_contract_id",
		"create_user_id",
		"create_user_name",
		"create_timestamp",
		"create_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_fina_termination_contract_att_id () {
		return wms_fina_termination_contract_att_id;
	}
	
	public void setWms_fina_termination_contract_att_id (Integer obj) {
		wms_fina_termination_contract_att_id = obj;
	}
	
	public String getData_type () {
		return data_type;
	}
	
	public void setData_type (String obj) {
		data_type = obj;
	}
	
	public String getAttachment_old_name () {
		return attachment_old_name;
	}
	
	public void setAttachment_old_name (String obj) {
		attachment_old_name = obj;
	}
	
	public String getAttachment_new_name () {
		return attachment_new_name;
	}
	
	public void setAttachment_new_name (String obj) {
		attachment_new_name = obj;
	}
	
	public String getAttachment_address () {
		return attachment_address;
	}
	
	public void setAttachment_address (String obj) {
		attachment_address = obj;
	}
	
	public String getAttachment_type () {
		return attachment_type;
	}
	
	public void setAttachment_type (String obj) {
		attachment_type = obj;
	}
	
	public Integer getWms_fina_termination_contract_id () {
		return wms_fina_termination_contract_id;
	}
	
	public void setWms_fina_termination_contract_id (Integer obj) {
		wms_fina_termination_contract_id = obj;
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
		paramMap.put("wms_fina_termination_contract_att_id", this.wms_fina_termination_contract_att_id);
		paramMap.put("data_type", this.data_type);
		paramMap.put("attachment_old_name", this.attachment_old_name);
		paramMap.put("attachment_new_name", this.attachment_new_name);
		paramMap.put("attachment_address", this.attachment_address);
		paramMap.put("attachment_type", this.attachment_type);
		paramMap.put("wms_fina_termination_contract_id", this.wms_fina_termination_contract_id);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_user_name", this.create_user_name);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
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