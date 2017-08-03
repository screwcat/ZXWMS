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

public class WmsCreHousingApprovalInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_housing_approval_info;
	
	private Integer wms_cre_credit_head_id;
	
	private String approval_type;
	
	private String approval_result;
	
	private Integer approval_user_id;
	
	private String approval_user_name;
	
	private java.sql.Timestamp approval_time;
	
	private String approval_time_str;
	
	private String approval_advice;
	
	private String approval_task_type;
	
	private String approval_user_deptName;
	
	private String approval_user_postnub;
	
	private String enable_flag;
	
	private boolean isExcludePKFlag;
    // 审批节点编码 例如 终审审批 ：ZSSP WorkflowConstantHelp.java STRHOUSINGLOANNODE_ZSSP'
    private String approval_task_code;
	
	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	};
	
	private static String[] columnNameArr = {
		"wms_cre_housing_approval_info",
		"wms_cre_credit_head_id",
		"approval_type",
		"approval_result",
		"approval_user_id",
		"approval_user_name",
		"approval_time",
		"approval_time_str",
		"approval_advice",
		"approval_task_type",
		"approval_user_deptid",
		"approval_user_postnub",
		"enable_flag",
 "isExcludePKFlag", "approval_task_code"
	};
  
	public Integer getWms_cre_housing_approval_info () {
		return wms_cre_housing_approval_info;
	}
	
	public void setWms_cre_housing_approval_info (Integer obj) {
		wms_cre_housing_approval_info = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public String getApproval_type () {
		return approval_type;
	}
	
	public void setApproval_type (String obj) {
		approval_type = obj;
	}
	
	public String getApproval_result () {
		return approval_result;
	}
	
	public void setApproval_result (String obj) {
		approval_result = obj;
	}
	
	public Integer getApproval_user_id () {
		return approval_user_id;
	}
	
	public void setApproval_user_id (Integer obj) {
		approval_user_id = obj;
	}
	
	public String getApproval_user_name () {
		return approval_user_name;
	}
	
	public void setApproval_user_name (String obj) {
		approval_user_name = obj;
	}
	
	public java.sql.Timestamp getApproval_time () {
		return approval_time;
	}
	
	public void setApproval_time (java.sql.Timestamp obj) {
		approval_time = obj;
	}
	
	public String getApproval_time_str () {
		return approval_time_str;
	}
	
	public void setApproval_time_str (String obj) {
		approval_time_str = obj;
	}
	
	public String getApproval_advice () {
		return approval_advice;
	}
	
	public void setApproval_advice (String obj) {
		approval_advice = obj;
	}
	
	public String getApproval_task_type () {
		return approval_task_type;
	}
	
	public void setApproval_task_type (String obj) {
		approval_task_type = obj;
	}
	

	
	public String getApproval_user_deptName() {
		return approval_user_deptName;
	}

	public void setApproval_user_deptName(String approval_user_deptName) {
		this.approval_user_deptName = approval_user_deptName;
	}

	public String getApproval_user_postnub () {
		return approval_user_postnub;
	}
	
	public void setApproval_user_postnub (String obj) {
		approval_user_postnub = obj;
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
	
	
    public String getApproval_task_code()
    {
        return approval_task_code;
    }

    public void setApproval_task_code(String approval_task_code)
    {
        this.approval_task_code = approval_task_code;
    }

    /**
    * put all columns into a map
    */
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_cre_housing_approval_info", this.wms_cre_housing_approval_info);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("approval_type", this.approval_type);
		paramMap.put("approval_result", this.approval_result);
		paramMap.put("approval_user_id", this.approval_user_id);
		paramMap.put("approval_user_name", this.approval_user_name);
		paramMap.put("approval_time", this.approval_time);
		paramMap.put("approval_time_str", this.approval_time_str);
		paramMap.put("approval_advice", this.approval_advice);
		paramMap.put("approval_task_type", this.approval_task_type);
		paramMap.put("approval_user_deptid", this.approval_user_deptName);
		paramMap.put("approval_user_postnub", this.approval_user_postnub);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
        paramMap.put("approval_task_code", this.approval_task_code);

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