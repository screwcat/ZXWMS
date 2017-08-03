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

public class WmsInveCreditEmail implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_credit_email_id;
	
	private Integer wms_inve_transa_id;
	
	private Integer wms_inve_clerk_protocol_id;
	
	private String email_subject;
	
	private String email_content;
	
	private String send_status;
	
	private java.sql.Timestamp send_datetime;
	
	private String send_datetime_str;
	
	private Integer create_user_id;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
	private String enable_flag;
	
	private boolean isExcludePKFlag;
	
    private String data_type;

    private String bill_code;
	

	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	  	"wms_inve_credit_email_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_credit_email_id",
		"wms_inve_transa_id",
		"wms_inve_clerk_protocol_id",
		"email_subject",
		"email_content",
		"send_status",
		"send_datetime",
		"send_datetime_str",
		"create_user_id",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
 "isExcludePKFlag", "data_type", "bill_code"
	};
  
	public Integer getWms_inve_credit_email_id () {
		return wms_inve_credit_email_id;
	}
	
	public void setWms_inve_credit_email_id (Integer obj) {
		wms_inve_credit_email_id = obj;
	}
	
	public Integer getWms_inve_transa_id () {
		return wms_inve_transa_id;
	}
	
	public void setWms_inve_transa_id (Integer obj) {
		wms_inve_transa_id = obj;
	}
	
	public Integer getWms_inve_clerk_protocol_id () {
		return wms_inve_clerk_protocol_id;
	}
	
	public void setWms_inve_clerk_protocol_id (Integer obj) {
		wms_inve_clerk_protocol_id = obj;
	}
	
	public String getEmail_subject () {
		return email_subject;
	}
	
	public void setEmail_subject (String obj) {
		email_subject = obj;
	}
	
	public String getEmail_content () {
		return email_content;
	}
	
	public void setEmail_content (String obj) {
		email_content = obj;
	}
	
	public String getSend_status () {
		return send_status;
	}
	
	public void setSend_status (String obj) {
		send_status = obj;
	}
	
	public java.sql.Timestamp getSend_datetime () {
		return send_datetime;
	}
	
	public void setSend_datetime (java.sql.Timestamp obj) {
		send_datetime = obj;
	}
	
	public String getSend_datetime_str () {
		return send_datetime_str;
	}
	
	public void setSend_datetime_str (String obj) {
		send_datetime_str = obj;
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
	
	
    public String getData_type()
    {
        return data_type;
    }

    public void setData_type(String data_type)
    {
        this.data_type = data_type;
    }

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    /**
    * put all columns into a map
    */
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_inve_credit_email_id", this.wms_inve_credit_email_id);
		paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
		paramMap.put("wms_inve_clerk_protocol_id", this.wms_inve_clerk_protocol_id);
		paramMap.put("email_subject", this.email_subject);
		paramMap.put("email_content", this.email_content);
		paramMap.put("send_status", this.send_status);
		paramMap.put("send_datetime", this.send_datetime);
		paramMap.put("send_datetime_str", this.send_datetime_str);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
		paramMap.put("last_update_user_id", this.last_update_user_id);
		paramMap.put("last_update_timestamp", this.last_update_timestamp);
		paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
        paramMap.put("data_type", this.data_type);
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