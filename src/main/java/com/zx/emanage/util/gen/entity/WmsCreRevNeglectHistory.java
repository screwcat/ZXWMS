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

public class WmsCreRevNeglectHistory implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_rev_neglect_history_id;
	
	private Integer wms_cre_credit_head_id;
	//审批环节
	private String approval_link;
	//审批人
	private String approvel_use_name;
	//审批人ID
	private Integer approvel_id;
	//审批人部门
	private String approvel_dept_name;
	//审批人部门ID
	private Integer approvel_dept_id;
	//审批人职务：角色职务
	private String personnel_postname;
	//审批结果：0代表不通过  1代表通过  2代表忽略
	private String approvel_result;
	//审批意见：存储提示信息
	private String approvel_idea;
	//审批时间
	private java.sql.Timestamp approvel_datetime;
	//
	private String approvel_datetime_str;
	//单据创建时间
	private java.sql.Timestamp create_datetime;
	
	private String create_datetime_str;
	//单据创建ID
	private Integer create_id;
	//单据修改时间
	private java.sql.Timestamp create_last_update_datetime;
	
	private String create_last_update_datetime_str;
	
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
	  	"wms_cre_rev_neglect_history_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_rev_neglect_history_id",
		"wms_cre_credit_head_id",
		"approval_link",
		"approvel_use_name",
		"approvel_id",
		"approvel_dept_name",
		"approvel_dept_id",
		"personnel_postname",
		"approvel_result",
		"approvel_idea",
		"approvel_datetime",
		"approvel_datetime_str",
		"create_datetime",
		"create_datetime_str",
		"create_id",
		"create_last_update_datetime",
		"create_last_update_datetime_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_rev_neglect_history_id () {
		return wms_cre_rev_neglect_history_id;
	}
	
	public void setWms_cre_rev_neglect_history_id (Integer obj) {
		wms_cre_rev_neglect_history_id = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public String getApproval_link () {
		return approval_link;
	}
	
	public void setApproval_link (String obj) {
		approval_link = obj;
	}
	
	public String getApprovel_use_name () {
		return approvel_use_name;
	}
	
	public void setApprovel_use_name (String obj) {
		approvel_use_name = obj;
	}
	
	public Integer getApprovel_id () {
		return approvel_id;
	}
	
	public void setApprovel_id (Integer obj) {
		approvel_id = obj;
	}
	
	public String getApprovel_dept_name () {
		return approvel_dept_name;
	}
	
	public void setApprovel_dept_name (String obj) {
		approvel_dept_name = obj;
	}
	
	public Integer getApprovel_dept_id () {
		return approvel_dept_id;
	}
	
	public void setApprovel_dept_id (Integer obj) {
		approvel_dept_id = obj;
	}
	
	public String getPersonnel_postname () {
		return personnel_postname;
	}
	
	public void setPersonnel_postname (String obj) {
		personnel_postname = obj;
	}
	
	public String getApprovel_result () {
		return approvel_result;
	}
	
	public void setApprovel_result (String obj) {
		approvel_result = obj;
	}
	
	public String getApprovel_idea () {
		return approvel_idea;
	}
	
	public void setApprovel_idea (String obj) {
		approvel_idea = obj;
	}
	
	public java.sql.Timestamp getApprovel_datetime () {
		return approvel_datetime;
	}
	
	public void setApprovel_datetime (java.sql.Timestamp obj) {
		approvel_datetime = obj;
	}
	
	public String getApprovel_datetime_str () {
		return approvel_datetime_str;
	}
	
	public void setApprovel_datetime_str (String obj) {
		approvel_datetime_str = obj;
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
	
	public Integer getCreate_id () {
		return create_id;
	}
	
	public void setCreate_id (Integer obj) {
		create_id = obj;
	}
	
	public java.sql.Timestamp getCreate_last_update_datetime () {
		return create_last_update_datetime;
	}
	
	public void setCreate_last_update_datetime (java.sql.Timestamp obj) {
		create_last_update_datetime = obj;
	}
	
	public String getCreate_last_update_datetime_str () {
		return create_last_update_datetime_str;
	}
	
	public void setCreate_last_update_datetime_str (String obj) {
		create_last_update_datetime_str = obj;
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
		paramMap.put("wms_cre_rev_neglect_history_id", this.wms_cre_rev_neglect_history_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("approval_link", this.approval_link);
		paramMap.put("approvel_use_name", this.approvel_use_name);
		paramMap.put("approvel_id", this.approvel_id);
		paramMap.put("approvel_dept_name", this.approvel_dept_name);
		paramMap.put("approvel_dept_id", this.approvel_dept_id);
		paramMap.put("personnel_postname", this.personnel_postname);
		paramMap.put("approvel_result", this.approvel_result);
		paramMap.put("approvel_idea", this.approvel_idea);
		paramMap.put("approvel_datetime", this.approvel_datetime);
		paramMap.put("approvel_datetime_str", this.approvel_datetime_str);
		paramMap.put("create_datetime", this.create_datetime);
		paramMap.put("create_datetime_str", this.create_datetime_str);
		paramMap.put("create_id", this.create_id);
		paramMap.put("create_last_update_datetime", this.create_last_update_datetime);
		paramMap.put("create_last_update_datetime_str", this.create_last_update_datetime_str);
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