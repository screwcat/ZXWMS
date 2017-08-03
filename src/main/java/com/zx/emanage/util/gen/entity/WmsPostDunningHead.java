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

public class WmsPostDunningHead implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_post_dunning_head_id;
	
	private Integer wms_fina_cre_pay_id;
	
	private Integer wms_cre_appro_borrow_protocol_id;
	
	private Integer wms_cre_credit_head_id;
	
	private String dunning_name;
	
	private String dunning_tel;
	
	private Integer cre_loan_type;
	
	private String protocol_code;
	
	private java.sql.Date protocol_creat_date;
	
	private String protocol_creat_date_str;
	
	private java.math.BigDecimal protocol_amount;
	
	private java.sql.Date default_date;
	
	private String default_date_str;
	
	private Integer repay_period;
	
	private Integer deadline;
	
	private java.math.BigDecimal un_matching_creditor;
	
	private java.math.BigDecimal liquidated_damages;
	
	private Integer overdue_day;
	
	private java.math.BigDecimal default_amount;
	
	private String salesman_name;
	
	private String salesman_id;
	
	private String team_manager_id;
	
	private String team_manager_name;
	
	private String refund_bank;
	
	private String refund_number;
	
	private String mortgrage;
	
	private java.math.BigDecimal return_amount;
	
	private Integer return_ratio;
	
	private java.math.BigDecimal return_ommission;
	
	private String dunning_remark;
	
	private Integer create_dunning_id;
	
	private String create_dunning_name;
	
	private Integer create_dunning_deptid;
	
	private java.sql.Timestamp create_dunning_datetime;
	
	private String create_dunning_datetime_str;
	
	private String create_dunning_result;
	
	private Integer final_dunning_id;
	
	private String final_dunning_name;
	
	private Integer final_dunning_deptid;
	
	private java.sql.Timestamp final_dunning_datetime;
	
	private String final_dunning_datetime_str;
	
	private String final_dunning_result;
	
	private String home_dunning_status;
	
	private String internal_dunning_result;
	
	private String internal_dunning_advice;
	
	private String outside_dunning_result;
	
	private String outside_dunning_advice;
	
	private String legalappeal_dunning_result;
	
	private String legalappeal_dunning_advice;
	
	private String dunning_status;
	
	private Integer loansupervisor_id;
	
	private String loansupervisor_name;
	
	private Integer loansupervisor_deptid;
	
	private java.sql.Timestamp loansupervisor_datetime;
	
	private String loansupervisor_datetime_str;
	
	private String loansupervisor_result;
	
	private String loansupervisor_advice;
	
	private String enable_flag;
	
    private java.math.BigDecimal un_pay_principal;
	
	private java.math.BigDecimal un_pay_interest;
	
	private java.math.BigDecimal un_total_pay_late_fee;
	
	private java.math.BigDecimal expect_payment_amount;
	
	private String reject_status;
	private String reject_advice;
	private Integer reject_id;
	private String reject_name;
	private java.sql.Date reject_datetime;
	private Integer visit_dunning_id;
	private String visit_dunning_name;
	private java.sql.Timestamp post_dunning_create_datetime;
	private java.sql.Timestamp post_dunning_update_datetime;
	private String post_dunning_cj_code;
	private java.sql.Date post_dunning_supervisor_time;
	private java.sql.Date post_dunning_commissioner_time;
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
	  	"wms_post_dunning_head_id"
	};
	
	private static String[] columnNameArr = {
		"wms_post_dunning_head_id",
		"wms_fina_cre_pay_id",
		"wms_cre_appro_borrow_protocol_id",
		"wms_cre_credit_head_id",
		"dunning_name",
		"dunning_tel",
		"cre_loan_type",
		"protocol_code",
		"protocol_creat_date",
		"protocol_creat_date_str",
		"protocol_amount",
		"default_date",
		"default_date_str",
		"repay_period",
		"deadline",
		"un_matching_creditor",
		"liquidated_damages",
		"overdue_day",
		"default_amount",
		"salesman_name",
		"salesman_id",
		"team_manager_id",
		"team_manager_name",
		"refund_bank",
		"refund_number",
		"mortgrage",
		"return_amount",
		"return_ratio",
		"return_ommission",
		"dunning_remark",
		"create_dunning_id",
		"create_dunning_name",
		"create_dunning_deptid",
		"create_dunning_datetime",
		"create_dunning_datetime_str",
		"create_dunning_result",
		"final_dunning_id",
		"final_dunning_name",
		"final_dunning_deptid",
		"final_dunning_datetime",
		"final_dunning_datetime_str",
		"final_dunning_result",
		"internal_dunning_status",
		"internal_dunning_result",
		"internal_dunning_advice",
		"outside_dunning_status",
		"outside_dunning_result",
		"outside_dunning_advice",
		"legalappeal_dunning_status",
		"legalappeal_dunning_result",
		"legalappeal_dunning_advice",
		"dunning_status",
		"loansupervisor_id",
		"loansupervisor_name",
		"loansupervisor_deptid",
		"loansupervisor_datetime",
		"loansupervisor_datetime_str",
		"loansupervisor_result",
		"loansupervisor_advice",
		"enable_flag",
		"un_pay_principal",
		"un_pay_interest",
		"un_total_pay_late_fee",
		"isExcludePKFlag"
	};
  
	public Integer getWms_post_dunning_head_id () {
		return wms_post_dunning_head_id;
	}
	
	public void setWms_post_dunning_head_id (Integer obj) {
		wms_post_dunning_head_id = obj;
	}
	
	public Integer getWms_fina_cre_pay_id () {
		return wms_fina_cre_pay_id;
	}
	
	public void setWms_fina_cre_pay_id (Integer obj) {
		wms_fina_cre_pay_id = obj;
	}
	
	public Integer getWms_cre_appro_borrow_protocol_id () {
		return wms_cre_appro_borrow_protocol_id;
	}
	
	public void setWms_cre_appro_borrow_protocol_id (Integer obj) {
		wms_cre_appro_borrow_protocol_id = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public String getDunning_name () {
		return dunning_name;
	}
	
	public void setDunning_name (String obj) {
		dunning_name = obj;
	}
	
	public String getDunning_tel () {
		return dunning_tel;
	}
	
	public void setDunning_tel (String obj) {
		dunning_tel = obj;
	}
	
	public Integer getCre_loan_type () {
		return cre_loan_type;
	}
	
	public void setCre_loan_type (Integer obj) {
		cre_loan_type = obj;
	}
	
	public String getProtocol_code () {
		return protocol_code;
	}
	
	public void setProtocol_code (String obj) {
		protocol_code = obj;
	}
	
	public java.sql.Date getProtocol_creat_date () {
		return protocol_creat_date;
	}
	
	public void setProtocol_creat_date (java.sql.Date obj) {
		protocol_creat_date = obj;
	}
	
	public String getProtocol_creat_date_str () {
		return protocol_creat_date_str;
	}
	
	public void setProtocol_creat_date_str (String obj) {
		protocol_creat_date_str = obj;
	}
	
	public java.math.BigDecimal getProtocol_amount () {
		return protocol_amount;
	}
	
	public void setProtocol_amount (java.math.BigDecimal obj) {
		protocol_amount = obj;
	}
	
	public java.sql.Date getDefault_date () {
		return default_date;
	}
	
	public void setDefault_date (java.sql.Date obj) {
		default_date = obj;
	}
	
	public String getDefault_date_str () {
		return default_date_str;
	}
	
	public void setDefault_date_str (String obj) {
		default_date_str = obj;
	}
	
	public Integer getRepay_period () {
		return repay_period;
	}
	
	public void setRepay_period (Integer obj) {
		repay_period = obj;
	}
	
	public Integer getDeadline () {
		return deadline;
	}
	
	public void setDeadline (Integer obj) {
		deadline = obj;
	}
	
	public java.math.BigDecimal getUn_matching_creditor () {
		return un_matching_creditor;
	}
	
	public void setUn_matching_creditor (java.math.BigDecimal obj) {
		un_matching_creditor = obj;
	}
	
	public java.math.BigDecimal getLiquidated_damages () {
		return liquidated_damages;
	}
	
	public void setLiquidated_damages (java.math.BigDecimal obj) {
		liquidated_damages = obj;
	}
	
	public Integer getOverdue_day () {
		return overdue_day;
	}
	
	public void setOverdue_day (Integer obj) {
		overdue_day = obj;
	}
	
	public java.math.BigDecimal getDefault_amount () {
		return default_amount;
	}
	
	public void setDefault_amount (java.math.BigDecimal obj) {
		default_amount = obj;
	}
	
	public String getSalesman_name () {
		return salesman_name;
	}
	
	public void setSalesman_name (String obj) {
		salesman_name = obj;
	}
	
	public String getSalesman_id () {
		return salesman_id;
	}
	
	public void setSalesman_id (String obj) {
		salesman_id = obj;
	}
	
	public String getTeam_manager_id () {
		return team_manager_id;
	}
	
	public void setTeam_manager_id (String obj) {
		team_manager_id = obj;
	}
	
	public String getTeam_manager_name () {
		return team_manager_name;
	}
	
	public void setTeam_manager_name (String obj) {
		team_manager_name = obj;
	}
	
	public String getRefund_bank () {
		return refund_bank;
	}
	
	public void setRefund_bank (String obj) {
		refund_bank = obj;
	}
	
	public String getRefund_number () {
		return refund_number;
	}
	
	public void setRefund_number (String obj) {
		refund_number = obj;
	}
	
	public String getMortgrage () {
		return mortgrage;
	}
	
	public void setMortgrage (String obj) {
		mortgrage = obj;
	}
	
	public java.math.BigDecimal getReturn_amount () {
		return return_amount;
	}
	
	public void setReturn_amount (java.math.BigDecimal obj) {
		return_amount = obj;
	}
	
	public Integer getReturn_ratio () {
		return return_ratio;
	}
	
	public void setReturn_ratio (Integer obj) {
		return_ratio = obj;
	}
	
	public java.math.BigDecimal getReturn_ommission () {
		return return_ommission;
	}
	
	public void setReturn_ommission (java.math.BigDecimal obj) {
		return_ommission = obj;
	}
	
	public String getDunning_remark()
    {
        return dunning_remark;
    }

    public void setDunning_remark(String dunning_remark)
    {
        this.dunning_remark = dunning_remark;
    }

    public Integer getCreate_dunning_id () {
		return create_dunning_id;
	}
	
	public void setCreate_dunning_id (Integer obj) {
		create_dunning_id = obj;
	}
	
	public String getCreate_dunning_name () {
		return create_dunning_name;
	}
	
	public void setCreate_dunning_name (String obj) {
		create_dunning_name = obj;
	}
	
	public Integer getCreate_dunning_deptid () {
		return create_dunning_deptid;
	}
	
	public void setCreate_dunning_deptid (Integer obj) {
		create_dunning_deptid = obj;
	}
	
	public java.sql.Timestamp getCreate_dunning_datetime () {
		return create_dunning_datetime;
	}
	
	public void setCreate_dunning_datetime (java.sql.Timestamp obj) {
		create_dunning_datetime = obj;
	}
	
	public String getCreate_dunning_datetime_str () {
		return create_dunning_datetime_str;
	}
	
	public void setCreate_dunning_datetime_str (String obj) {
		create_dunning_datetime_str = obj;
	}
	
	public String getCreate_dunning_result () {
		return create_dunning_result;
	}
	
	public void setCreate_dunning_result (String obj) {
		create_dunning_result = obj;
	}
	
	public Integer getFinal_dunning_id () {
		return final_dunning_id;
	}
	
	public void setFinal_dunning_id (Integer obj) {
		final_dunning_id = obj;
	}
	
	public String getFinal_dunning_name () {
		return final_dunning_name;
	}
	
	public void setFinal_dunning_name (String obj) {
		final_dunning_name = obj;
	}
	
	public Integer getFinal_dunning_deptid () {
		return final_dunning_deptid;
	}
	
	public void setFinal_dunning_deptid (Integer obj) {
		final_dunning_deptid = obj;
	}
	
	public java.sql.Timestamp getFinal_dunning_datetime () {
		return final_dunning_datetime;
	}
	
	public void setFinal_dunning_datetime (java.sql.Timestamp obj) {
		final_dunning_datetime = obj;
	}
	
	public String getFinal_dunning_datetime_str () {
		return final_dunning_datetime_str;
	}
	
	public void setFinal_dunning_datetime_str (String obj) {
		final_dunning_datetime_str = obj;
	}
	
	public String getFinal_dunning_result () {
		return final_dunning_result;
	}
	
	public void setFinal_dunning_result (String obj) {
		final_dunning_result = obj;
	}
		
	public String getHome_dunning_status()
    {
        return home_dunning_status;
    }

    public void setHome_dunning_status(String home_dunning_status)
    {
        this.home_dunning_status = home_dunning_status;
    }

    public String getInternal_dunning_result () {
		return internal_dunning_result;
	}
	
	public void setInternal_dunning_result (String obj) {
		internal_dunning_result = obj;
	}
	
	public String getInternal_dunning_advice () {
		return internal_dunning_advice;
	}
	
	public void setInternal_dunning_advice (String obj) {
		internal_dunning_advice = obj;
	}
	
	public String getOutside_dunning_result () {
		return outside_dunning_result;
	}
	
	public void setOutside_dunning_result (String obj) {
		outside_dunning_result = obj;
	}
	
	public String getOutside_dunning_advice () {
		return outside_dunning_advice;
	}
	
	public void setOutside_dunning_advice (String obj) {
		outside_dunning_advice = obj;
	}
	
	public String getLegalappeal_dunning_result () {
		return legalappeal_dunning_result;
	}
	
	public void setLegalappeal_dunning_result (String obj) {
		legalappeal_dunning_result = obj;
	}
	
	public String getLegalappeal_dunning_advice () {
		return legalappeal_dunning_advice;
	}
	
	public void setLegalappeal_dunning_advice (String obj) {
		legalappeal_dunning_advice = obj;
	}
	
	public String getDunning_status () {
		return dunning_status;
	}
	
	public void setDunning_status (String obj) {
		dunning_status = obj;
	}
	
	public Integer getLoansupervisor_id () {
		return loansupervisor_id;
	}
	
	public void setLoansupervisor_id (Integer obj) {
		loansupervisor_id = obj;
	}
	
	public String getLoansupervisor_name () {
		return loansupervisor_name;
	}
	
	public void setLoansupervisor_name (String obj) {
		loansupervisor_name = obj;
	}
	
	public Integer getLoansupervisor_deptid () {
		return loansupervisor_deptid;
	}
	
	public void setLoansupervisor_deptid (Integer obj) {
		loansupervisor_deptid = obj;
	}
	
	public java.sql.Timestamp getLoansupervisor_datetime () {
		return loansupervisor_datetime;
	}
	
	public void setLoansupervisor_datetime (java.sql.Timestamp obj) {
		loansupervisor_datetime = obj;
	}
	
	public String getLoansupervisor_datetime_str () {
		return loansupervisor_datetime_str;
	}
	
	public void setLoansupervisor_datetime_str (String obj) {
		loansupervisor_datetime_str = obj;
	}
	
	public String getLoansupervisor_result () {
		return loansupervisor_result;
	}
	
	public void setLoansupervisor_result (String obj) {
		loansupervisor_result = obj;
	}
	
	public String getLoansupervisor_advice () {
		return loansupervisor_advice;
	}
	
	public void setLoansupervisor_advice (String obj) {
		loansupervisor_advice = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public java.math.BigDecimal getUn_pay_principal () {
		return un_pay_principal;
	}
	
	public void setUn_pay_principal (java.math.BigDecimal obj) {
		un_pay_principal = obj;
	}
	
	public java.math.BigDecimal getUn_pay_interest () {
		return un_pay_interest;
	}
	
	public void setUn_pay_interest (java.math.BigDecimal obj) {
		un_pay_interest = obj;
	}
	
	public java.math.BigDecimal getUn_total_pay_late_fee () {
		return un_total_pay_late_fee;
	}
	
	public void setUn_total_pay_late_fee (java.math.BigDecimal obj) {
		un_total_pay_late_fee = obj;
	}
	
	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	public java.math.BigDecimal getExpect_payment_amount() {
		return expect_payment_amount;
	}

	public void setExpect_payment_amount(java.math.BigDecimal expect_payment_amount) {
		this.expect_payment_amount = expect_payment_amount;
	}

	public String getReject_status() {
		return reject_status;
	}

	public void setReject_status(String reject_status) {
		this.reject_status = reject_status;
	}

	public String getReject_advice() {
		return reject_advice;
	}

	public void setReject_advice(String reject_advice) {
		this.reject_advice = reject_advice;
	}

	public Integer getReject_id() {
		return reject_id;
	}

	public void setReject_id(Integer reject_id) {
		this.reject_id = reject_id;
	}

	public String getReject_name() {
		return reject_name;
	}

	public void setReject_name(String reject_name) {
		this.reject_name = reject_name;
	}

	public java.sql.Date getReject_datetime() {
		return reject_datetime;
	}

	public void setReject_datetime(java.sql.Date reject_datetime) {
		this.reject_datetime = reject_datetime;
	}

	public Integer getVisit_dunning_id() {
		return visit_dunning_id;
	}

	public void setVisit_dunning_id(Integer visit_dunning_id) {
		this.visit_dunning_id = visit_dunning_id;
	}

	public String getVisit_dunning_name() {
		return visit_dunning_name;
	}

	public void setVisit_dunning_name(String visit_dunning_name) {
		this.visit_dunning_name = visit_dunning_name;
	}

	public java.sql.Timestamp getPost_dunning_create_datetime() {
		return post_dunning_create_datetime;
	}

	public void setPost_dunning_create_datetime(
			java.sql.Timestamp post_dunning_create_datetime) {
		this.post_dunning_create_datetime = post_dunning_create_datetime;
	}

	public java.sql.Timestamp getPost_dunning_update_datetime() {
		return post_dunning_update_datetime;
	}

	public void setPost_dunning_update_datetime(
			java.sql.Timestamp post_dunning_update_datetime) {
		this.post_dunning_update_datetime = post_dunning_update_datetime;
	}

	public String getPost_dunning_cj_code() {
		return post_dunning_cj_code;
	}

	public void setPost_dunning_cj_code(String post_dunning_cj_code) {
		this.post_dunning_cj_code = post_dunning_cj_code;
	}

	public java.sql.Date getPost_dunning_supervisor_time() {
		return post_dunning_supervisor_time;
	}

	public void setPost_dunning_supervisor_time(
			java.sql.Date post_dunning_supervisor_time) {
		this.post_dunning_supervisor_time = post_dunning_supervisor_time;
	}

	public java.sql.Date getPost_dunning_commissioner_time() {
		return post_dunning_commissioner_time;
	}

	public void setPost_dunning_commissioner_time(
			java.sql.Date post_dunning_commissioner_time) {
		this.post_dunning_commissioner_time = post_dunning_commissioner_time;
	}

	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_post_dunning_head_id", this.wms_post_dunning_head_id);
		paramMap.put("wms_fina_cre_pay_id", this.wms_fina_cre_pay_id);
		paramMap.put("wms_cre_appro_borrow_protocol_id", this.wms_cre_appro_borrow_protocol_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("dunning_name", this.dunning_name);
		paramMap.put("dunning_tel", this.dunning_tel);
		paramMap.put("cre_loan_type", this.cre_loan_type);
		paramMap.put("protocol_code", this.protocol_code);
		paramMap.put("protocol_creat_date", this.protocol_creat_date);
		paramMap.put("protocol_creat_date_str", this.protocol_creat_date_str);
		paramMap.put("protocol_amount", this.protocol_amount);
		paramMap.put("default_date", this.default_date);
		paramMap.put("default_date_str", this.default_date_str);
		paramMap.put("repay_period", this.repay_period);
		paramMap.put("deadline", this.deadline);
		paramMap.put("un_matching_creditor", this.un_matching_creditor);
		paramMap.put("liquidated_damages", this.liquidated_damages);
		paramMap.put("overdue_day", this.overdue_day);
		paramMap.put("default_amount", this.default_amount);
		paramMap.put("salesman_name", this.salesman_name);
		paramMap.put("salesman_id", this.salesman_id);
		paramMap.put("team_manager_id", this.team_manager_id);
		paramMap.put("team_manager_name", this.team_manager_name);
		paramMap.put("refund_bank", this.refund_bank);
		paramMap.put("refund_number", this.refund_number);
		paramMap.put("mortgrage", this.mortgrage);
		paramMap.put("return_amount", this.return_amount);
		paramMap.put("return_ratio", this.return_ratio);
		paramMap.put("return_ommission", this.return_ommission);
		paramMap.put("dunning_remark", this.dunning_remark);
		paramMap.put("create_dunning_id", this.create_dunning_id);
		paramMap.put("create_dunning_name", this.create_dunning_name);
		paramMap.put("create_dunning_deptid", this.create_dunning_deptid);
		paramMap.put("create_dunning_datetime", this.create_dunning_datetime);
		paramMap.put("create_dunning_datetime_str", this.create_dunning_datetime_str);
		paramMap.put("create_dunning_result", this.create_dunning_result);
		paramMap.put("final_dunning_id", this.final_dunning_id);
		paramMap.put("final_dunning_name", this.final_dunning_name);
		paramMap.put("final_dunning_deptid", this.final_dunning_deptid);
		paramMap.put("final_dunning_datetime", this.final_dunning_datetime);
		paramMap.put("final_dunning_datetime_str", this.final_dunning_datetime_str);
		paramMap.put("final_dunning_result", this.final_dunning_result);
		paramMap.put("home_dunning_status", this.home_dunning_status);
		paramMap.put("internal_dunning_result", this.internal_dunning_result);
		paramMap.put("internal_dunning_advice", this.internal_dunning_advice);
		paramMap.put("outside_dunning_result", this.outside_dunning_result);
		paramMap.put("outside_dunning_advice", this.outside_dunning_advice);
		paramMap.put("legalappeal_dunning_result", this.legalappeal_dunning_result);
		paramMap.put("legalappeal_dunning_advice", this.legalappeal_dunning_advice);
		paramMap.put("dunning_status", this.dunning_status);
		paramMap.put("loansupervisor_id", this.loansupervisor_id);
		paramMap.put("loansupervisor_name", this.loansupervisor_name);
		paramMap.put("loansupervisor_deptid", this.loansupervisor_deptid);
		paramMap.put("loansupervisor_datetime", this.loansupervisor_datetime);
		paramMap.put("loansupervisor_datetime_str", this.loansupervisor_datetime_str);
		paramMap.put("loansupervisor_result", this.loansupervisor_result);
		paramMap.put("loansupervisor_advice", this.loansupervisor_advice);
		paramMap.put("un_pay_principal", this.un_pay_principal);
		paramMap.put("un_pay_interest", this.un_pay_interest);
		paramMap.put("un_total_pay_late_fee", this.un_total_pay_late_fee);
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