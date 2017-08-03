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

public class WmsInveApprovalProcess implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_approval_process_id;
	
	private String data_type;
	
	private Integer data_id;
	
	private String taskname;
	
	private String approvers;
	
	private String personnel_deptname;
	
	private String personnel_postname;
	
	private String approveresult;
	
	private String approveadvice;
	
	private java.sql.Timestamp approvetime;
	
	private String approvetime_str;
	
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
	};
	
	private static String[] columnNameArr = {
		"wms_inve_approval_process_id",
		"data_type",
		"data_id",
		"taskname",
		"approvers",
		"personnel_deptname",
		"personnel_postname",
		"approveresult",
		"approveadvice",
		"approvetime",
		"approvetime_str",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_approval_process_id () {
		return wms_inve_approval_process_id;
	}
	
	public void setWms_inve_approval_process_id (Integer obj) {
		wms_inve_approval_process_id = obj;
	}
	
	public String getData_type () {
		return data_type;
	}
	
	public void setData_type (String obj) {
		data_type = obj;
	}
	
	public Integer getData_id () {
		return data_id;
	}
	
	public void setData_id (Integer obj) {
		data_id = obj;
	}
	
	public String getTaskname () {
		return taskname;
	}
	
	public void setTaskname (String obj) {
		taskname = obj;
	}
	
	public String getApprovers () {
		return approvers;
	}
	
	public void setApprovers (String obj) {
		approvers = obj;
	}
	
	public String getPersonnel_deptname () {
		return personnel_deptname;
	}
	
	public void setPersonnel_deptname (String obj) {
		personnel_deptname = obj;
	}
	
	public String getPersonnel_postname () {
		return personnel_postname;
	}
	
	public void setPersonnel_postname (String obj) {
		personnel_postname = obj;
	}
	
	public String getApproveresult () {
		return approveresult;
	}
	
	public void setApproveresult (String obj) {
		approveresult = obj;
	}
	
	public String getApproveadvice () {
		return approveadvice;
	}
	
	public void setApproveadvice (String obj) {
		approveadvice = obj;
	}
	
	public java.sql.Timestamp getApprovetime () {
		return approvetime;
	}
	
	public void setApprovetime (java.sql.Timestamp obj) {
		approvetime = obj;
	}
	
	public String getApprovetime_str () {
		return approvetime_str;
	}
	
	public void setApprovetime_str (String obj) {
		approvetime_str = obj;
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
		paramMap.put("wms_inve_approval_process_id", this.wms_inve_approval_process_id);
		paramMap.put("data_type", this.data_type);
		paramMap.put("data_id", this.data_id);
		paramMap.put("taskname", this.taskname);
		paramMap.put("approvers", this.approvers);
		paramMap.put("personnel_deptname", this.personnel_deptname);
		paramMap.put("personnel_postname", this.personnel_postname);
		paramMap.put("approveresult", this.approveresult);
		paramMap.put("approveadvice", this.approveadvice);
		paramMap.put("approvetime", this.approvetime);
		paramMap.put("approvetime_str", this.approvetime_str);
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