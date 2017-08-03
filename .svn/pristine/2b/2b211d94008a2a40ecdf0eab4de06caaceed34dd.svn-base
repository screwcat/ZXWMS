package com.zx.emanage.util.gen.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zx.sframe.util.mybatis.BaseEntity;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreCreditGroup
 * 模块名称：
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
public class WmsCreCreditGroup implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_credit_group_id;
	
	private String bill_code_group;
	
	private java.math.BigDecimal appro_limit_group;
	
	private java.sql.Timestamp group_date;
	
	private String group_date_str;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
	private String enable_flag;
	
	private boolean isExcludePKFlag;
	
	private String[] head_ids;
	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	  	"wms_cre_credit_group_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_credit_group_id",
		"bill_code_group",
		"appro_limit_group",
		"group_date",
		"group_date_str",
		"create_user_id",
		"create_user_name",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_credit_group_id () {
		return wms_cre_credit_group_id;
	}
	
	public void setWms_cre_credit_group_id (Integer obj) {
		wms_cre_credit_group_id = obj;
	}
	
	public String getBill_code_group () {
		return bill_code_group;
	}
	
	public void setBill_code_group (String obj) {
		bill_code_group = obj;
	}
	
	public java.math.BigDecimal getAppro_limit_group () {
		return appro_limit_group;
	}
	
	public void setAppro_limit_group (java.math.BigDecimal obj) {
		appro_limit_group = obj;
	}
	
	public java.sql.Timestamp getGroup_date () {
		return group_date;
	}
	
	public void setGroup_date (java.sql.Timestamp obj) {
		group_date = obj;
	}
	
	public String getGroup_date_str () {
		return group_date_str;
	}
	
	public void setGroup_date_str (String obj) {
		group_date_str = obj;
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
	
	
	public String[] getHead_ids()
    {
        return head_ids;
    }

    public void setHead_ids(String[] head_ids)
    {
        this.head_ids = head_ids;
    }

    /**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_cre_credit_group_id", this.wms_cre_credit_group_id);
		paramMap.put("bill_code_group", this.bill_code_group);
		paramMap.put("appro_limit_group", this.appro_limit_group);
		paramMap.put("group_date", this.group_date);
		paramMap.put("group_date_str", this.group_date_str);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_user_name", this.create_user_name);
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