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

public class WmsFinaCreOverdueHistory implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_fina_cre_overdue_history_id;
	
	private String repayment_history_code;
	
	private  String overdue_date;
	
	private String overdue_date_str;
	
	private java.math.BigDecimal cur_overdue_day;
	
	private java.math.BigDecimal late_fees;
	
	private java.math.BigDecimal is_late_fees;
	
	private java.math.BigDecimal relief_late_fees;
	
	private String enable_flag;
	
	private Integer wms_fina_cre_repayment_history_id;
	
	private Integer wms_fina_cre_realrepay_info_id;
	
	private Integer wms_cre_credit_head_id;
	
	private Integer repay_no;
	
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
	  	"wms_fina_cre_overdue_history_id"
	};
	
	private static String[] columnNameArr = {
		"wms_fina_cre_overdue_history_id",
		"repayment_history_code",
		"overdue_date",
		"overdue_date_str",
		"cur_overdue_day",
		"late_fees",
		"is_late_fees",
		"relief_late_fees",
		"enable_flag",
		"wms_fina_cre_repayment_history_id",
		"wms_fina_cre_realrepay_info_id",
		"wms_cre_credit_head_id",
		"repay_no",
		"isExcludePKFlag"
	};
  
	public Integer getWms_fina_cre_overdue_history_id () {
		return wms_fina_cre_overdue_history_id;
	}
	
	public void setWms_fina_cre_overdue_history_id (Integer obj) {
		wms_fina_cre_overdue_history_id = obj;
	}
	
	public String getRepayment_history_code () {
		return repayment_history_code;
	}
	
	public void setRepayment_history_code (String obj) {
		repayment_history_code = obj;
	}
	

	public String getOverdue_date() {
		return overdue_date;
	}

	public void setOverdue_date(String overdue_date) {
		this.overdue_date = overdue_date;
	}

	public String getOverdue_date_str () {
		return overdue_date_str;
	}
	
	public void setOverdue_date_str (String obj) {
		overdue_date_str = obj;
	}
	
	public java.math.BigDecimal getCur_overdue_day () {
		return cur_overdue_day;
	}
	
	public void setCur_overdue_day (java.math.BigDecimal obj) {
		cur_overdue_day = obj;
	}
	
	public java.math.BigDecimal getLate_fees () {
		return late_fees;
	}
	
	public void setLate_fees (java.math.BigDecimal obj) {
		late_fees = obj;
	}
	
	public java.math.BigDecimal getIs_late_fees () {
		return is_late_fees;
	}
	
	public void setIs_late_fees (java.math.BigDecimal obj) {
		is_late_fees = obj;
	}
	
	public java.math.BigDecimal getRelief_late_fees () {
		return relief_late_fees;
	}
	
	public void setRelief_late_fees (java.math.BigDecimal obj) {
		relief_late_fees = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public Integer getWms_fina_cre_repayment_history_id () {
		return wms_fina_cre_repayment_history_id;
	}
	
	public void setWms_fina_cre_repayment_history_id (Integer obj) {
		wms_fina_cre_repayment_history_id = obj;
	}
	
	public Integer getWms_fina_cre_realrepay_info_id () {
		return wms_fina_cre_realrepay_info_id;
	}
	
	public void setWms_fina_cre_realrepay_info_id (Integer obj) {
		wms_fina_cre_realrepay_info_id = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public Integer getRepay_no() {
		return repay_no;
	}

	public void setRepay_no(Integer repay_no) {
		this.repay_no = repay_no;
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
		paramMap.put("wms_fina_cre_overdue_history_id", this.wms_fina_cre_overdue_history_id);
		paramMap.put("repayment_history_code", this.repayment_history_code);
		paramMap.put("overdue_date", this.overdue_date);
		paramMap.put("overdue_date_str", this.overdue_date_str);
		paramMap.put("cur_overdue_day", this.cur_overdue_day);
		paramMap.put("late_fees", this.late_fees);
		paramMap.put("is_late_fees", this.is_late_fees);
		paramMap.put("relief_late_fees", this.relief_late_fees);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("wms_fina_cre_repayment_history_id", this.wms_fina_cre_repayment_history_id);
		paramMap.put("wms_fina_cre_realrepay_info_id", this.wms_fina_cre_realrepay_info_id);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("repay_no", this.repay_no);
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