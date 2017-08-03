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

public class WmsFinaCreRepaymentHistory implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_fina_cre_repayment_history_id;
	
	private String repayment_history_code;
	
	private java.sql.Date repayment_date;
	
	private String repayment_date_str;
	
	private java.math.BigDecimal this_total_repayment;
	
	private java.math.BigDecimal this_principal;
	
	private java.math.BigDecimal this_interest;
	
	private java.math.BigDecimal this_late_fees;
	
	private java.math.BigDecimal this_amount_relief;
	
	private String whether_mortgage;
	
	private java.math.BigDecimal this_mortgage;
	
	private String this_collateral_ids;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private String create_user_dept_id;
	
	private java.sql.Timestamp create_user_datetime;
	
	private String create_user_datetime_str;
	
	private Integer wms_fina_cre_pos_id;
	
	private String pos_bank_card;
	
	private String this_repayment_remark;
	
	private Integer wms_cre_appro_id;
	
	private Integer wms_cre_credit_head_id;
	
	private Integer wms_fina_cre_pay_id;
	
	private java.math.BigDecimal adjustment_amount;
	
	private Integer repay_no;
	
	private Integer the_clear_marks;
	
	private Integer the_overdue;
	
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
	  	"wms_fina_cre_repayment_history_id"
	};
	
	private static String[] columnNameArr = {
		"wms_fina_cre_repayment_history_id",
		"repayment_history_code",
		"repayment_date",
		"repayment_date_str",
		"this_total_repayment",
		"this_principal",
		"this_interest",
		"this_late_fees",
		"this_amount_relief",
		"whether_mortgage",
		"this_mortgage",
		"this_collateral_ids",
		"create_user_id",
		"create_user_name",
		"create_user_dept_id",
		"create_user_datetime",
		"create_user_datetime_str",
		"wms_fina_cre_pos_id",
		"pos_bank_card",
		"this_repayment_remark",
		"wms_cre_appro_id",
		"wms_cre_credit_head_id",
		"wms_fina_cre_pay_id",
		"adjustment_amount",
		"repay_no",
		"the_clear_marks",
		"the_overdue",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_fina_cre_repayment_history_id () {
		return wms_fina_cre_repayment_history_id;
	}
	
	public void setWms_fina_cre_repayment_history_id (Integer obj) {
		wms_fina_cre_repayment_history_id = obj;
	}
	
	public String getRepayment_history_code () {
		return repayment_history_code;
	}
	
	public void setRepayment_history_code (String obj) {
		repayment_history_code = obj;
	}
	
	public java.sql.Date getRepayment_date () {
		return repayment_date;
	}
	
	public void setRepayment_date (java.sql.Date obj) {
		repayment_date = obj;
	}
	
	public String getRepayment_date_str () {
		return repayment_date_str;
	}
	
	public void setRepayment_date_str (String obj) {
		repayment_date_str = obj;
	}
	
	public java.math.BigDecimal getThis_total_repayment () {
		return this_total_repayment;
	}
	
	public void setThis_total_repayment (java.math.BigDecimal obj) {
		this_total_repayment = obj;
	}
	
	public java.math.BigDecimal getThis_principal () {
		return this_principal;
	}
	
	public void setThis_principal (java.math.BigDecimal obj) {
		this_principal = obj;
	}
	
	public java.math.BigDecimal getThis_interest () {
		return this_interest;
	}
	
	public void setThis_interest (java.math.BigDecimal obj) {
		this_interest = obj;
	}
	
	public java.math.BigDecimal getThis_late_fees () {
		return this_late_fees;
	}
	
	public void setThis_late_fees (java.math.BigDecimal obj) {
		this_late_fees = obj;
	}
	
	public java.math.BigDecimal getThis_amount_relief () {
		return this_amount_relief;
	}
	
	public void setThis_amount_relief (java.math.BigDecimal obj) {
		this_amount_relief = obj;
	}
	
	public String getWhether_mortgage () {
		return whether_mortgage;
	}
	
	public void setWhether_mortgage (String obj) {
		whether_mortgage = obj;
	}
	
	public java.math.BigDecimal getThis_mortgage () {
		return this_mortgage;
	}
	
	public void setThis_mortgage (java.math.BigDecimal obj) {
		this_mortgage = obj;
	}
	
	public String getThis_collateral_ids () {
		return this_collateral_ids;
	}
	
	public void setThis_collateral_ids (String obj) {
		this_collateral_ids = obj;
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
	
	public String getCreate_user_dept_id () {
		return create_user_dept_id;
	}
	
	public void setCreate_user_dept_id (String obj) {
		create_user_dept_id = obj;
	}
	
	public java.sql.Timestamp getCreate_user_datetime () {
		return create_user_datetime;
	}
	
	public void setCreate_user_datetime (java.sql.Timestamp obj) {
		create_user_datetime = obj;
	}
	
	public String getCreate_user_datetime_str () {
		return create_user_datetime_str;
	}
	
	public void setCreate_user_datetime_str (String obj) {
		create_user_datetime_str = obj;
	}
	
	public Integer getWms_fina_cre_pos_id () {
		return wms_fina_cre_pos_id;
	}
	
	public void setWms_fina_cre_pos_id (Integer obj) {
		wms_fina_cre_pos_id = obj;
	}
	
	public String getPos_bank_card () {
		return pos_bank_card;
	}
	
	public void setPos_bank_card (String obj) {
		pos_bank_card = obj;
	}
	
	public String getThis_repayment_remark () {
		return this_repayment_remark;
	}
	
	public void setThis_repayment_remark (String obj) {
		this_repayment_remark = obj;
	}
	
	public Integer getWms_cre_appro_id () {
		return wms_cre_appro_id;
	}
	
	public void setWms_cre_appro_id (Integer obj) {
		wms_cre_appro_id = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public Integer getWms_fina_cre_pay_id () {
		return wms_fina_cre_pay_id;
	}
	
	public void setWms_fina_cre_pay_id (Integer obj) {
		wms_fina_cre_pay_id = obj;
	}
	
	public java.math.BigDecimal getAdjustment_amount () {
		return adjustment_amount;
	}
	
	public void setAdjustment_amount (java.math.BigDecimal obj) {
		adjustment_amount = obj;
	}
	
	public Integer getRepay_no () {
		return repay_no;
	}
	
	public void setRepay_no (Integer obj) {
		repay_no = obj;
	}
	
	public Integer getThe_clear_marks () {
		return the_clear_marks;
	}
	
	public void setThe_clear_marks (Integer obj) {
		the_clear_marks = obj;
	}
	
	public Integer getThe_overdue () {
		return the_overdue;
	}
	
	public void setThe_overdue (Integer obj) {
		the_overdue = obj;
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
		paramMap.put("wms_fina_cre_repayment_history_id", this.wms_fina_cre_repayment_history_id);
		paramMap.put("repayment_history_code", this.repayment_history_code);
		paramMap.put("repayment_date", this.repayment_date);
		paramMap.put("repayment_date_str", this.repayment_date_str);
		paramMap.put("this_total_repayment", this.this_total_repayment);
		paramMap.put("this_principal", this.this_principal);
		paramMap.put("this_interest", this.this_interest);
		paramMap.put("this_late_fees", this.this_late_fees);
		paramMap.put("this_amount_relief", this.this_amount_relief);
		paramMap.put("whether_mortgage", this.whether_mortgage);
		paramMap.put("this_mortgage", this.this_mortgage);
		paramMap.put("this_collateral_ids", this.this_collateral_ids);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_user_name", this.create_user_name);
		paramMap.put("create_user_dept_id", this.create_user_dept_id);
		paramMap.put("create_user_datetime", this.create_user_datetime);
		paramMap.put("create_user_datetime_str", this.create_user_datetime_str);
		paramMap.put("wms_fina_cre_pos_id", this.wms_fina_cre_pos_id);
		paramMap.put("pos_bank_card", this.pos_bank_card);
		paramMap.put("this_repayment_remark", this.this_repayment_remark);
		paramMap.put("wms_cre_appro_id", this.wms_cre_appro_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("wms_fina_cre_pay_id", this.wms_fina_cre_pay_id);
		paramMap.put("adjustment_amount", this.adjustment_amount);
		paramMap.put("repay_no", this.repay_no);
		paramMap.put("the_clear_marks", this.the_clear_marks);
		paramMap.put("the_overdue", this.the_overdue);
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