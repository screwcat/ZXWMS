package com.zx.emanage.reportmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveCommissionPerformanceSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	public String compay_id;
	public String dept_id;
	public String team_id;
	private String show;//展示
	public String personnel_Code;
	
	public String salesman_id;
	
	public String lssue_date_begin;
	public String lssue_date_end;
	
	public String personnel_state;//员工状态
	
	private long create_datetime;//

	public String group_id;
	
	public String getCompay_id() {
		return compay_id;
	}

	public void setCompay_id(String compay_id) {
		this.compay_id = compay_id;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getPersonnel_Code() {
		return personnel_Code;
	}

	public void setPersonnel_Code(String personnel_Code) {
		this.personnel_Code = personnel_Code;
	}

	public String getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}

	

	public String getPersonnel_state() {
		return personnel_state;
	}

	public void setPersonnel_state(String personnel_state) {
		this.personnel_state = personnel_state;
	}

	public long getCreate_datetime() {
		return create_datetime;
	}

	public void setCreate_datetime(long create_datetime) {
		this.create_datetime = create_datetime;
	}

	public String getLssue_date_begin() {
		return lssue_date_begin;
	}

	public void setLssue_date_begin(String lssue_date_begin) {
		this.lssue_date_begin = lssue_date_begin;
	}

	public String getLssue_date_end() {
		return lssue_date_end;
	}

	public void setLssue_date_end(String lssue_date_end) {
		this.lssue_date_end = lssue_date_end;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	
}