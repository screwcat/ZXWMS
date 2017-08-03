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

public class WmsPersonnelAchievementHis implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_personnel_achievement_his_id;
	
	private Integer company_id;
	
	private String company_name;
	
	private Integer dept_id;
	
	private String dept_name;
	
	private Integer personnel_id;
	
	private String personnel_name_detail;
	
	private String post_number;
	
	private String post_name;
	
	private String statics_month;
	
	private java.math.BigDecimal per_add_base;
	
	private java.math.BigDecimal per_add_deal;
	
	private java.math.BigDecimal per_clear_add;
	
	private java.math.BigDecimal per_clear_add_deal;
	
	private java.math.BigDecimal per_stock_all;
	
	private java.math.BigDecimal per_stock_new;
	
	private String per_stock_lev;
	
	private java.math.BigDecimal team_add_base;
	
	private java.math.BigDecimal team_add_deal;
	
	private java.math.BigDecimal team_redeem_all;
	
	private java.math.BigDecimal team_clear_add;
	
	private java.math.BigDecimal team_stock_all;
	
	private java.math.BigDecimal team_stock_new;
	
	private Integer team_staff_num;
	
	private java.math.BigDecimal team_per_increase;
	
	private Integer team_staff_num_regular;
	
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
	  	"wms_personnel_achievement_his_id"
	};
	
	private static String[] columnNameArr = {
		"wms_personnel_achievement_his_id",
		"company_id",
		"company_name",
		"dept_id",
		"dept_name",
		"personnel_id",
		"personnel_name_detail",
		"post_number",
		"post_name",
		"statics_month",
		"per_add_base",
		"per_add_deal",
		"per_clear_add",
		"per_clear_add_deal",
		"per_stock_all",
		"per_stock_new",
		"per_stock_lev",
		"team_add_base",
		"team_add_deal",
		"team_redeem_all",
		"team_clear_add",
		"team_stock_all",
		"team_stock_new",
		"team_staff_num",
		"team_per_increase",
		"team_staff_num_regular",
		"isExcludePKFlag"
	};
  
	public Integer getWms_personnel_achievement_his_id () {
		return wms_personnel_achievement_his_id;
	}
	
	public void setWms_personnel_achievement_his_id (Integer obj) {
		wms_personnel_achievement_his_id = obj;
	}
	
	public Integer getCompany_id () {
		return company_id;
	}
	
	public void setCompany_id (Integer obj) {
		company_id = obj;
	}
	
	public String getCompany_name () {
		return company_name;
	}
	
	public void setCompany_name (String obj) {
		company_name = obj;
	}
	
	public Integer getDept_id () {
		return dept_id;
	}
	
	public void setDept_id (Integer obj) {
		dept_id = obj;
	}
	
	public String getDept_name () {
		return dept_name;
	}
	
	public void setDept_name (String obj) {
		dept_name = obj;
	}
	
	public Integer getPersonnel_id () {
		return personnel_id;
	}
	
	public void setPersonnel_id (Integer obj) {
		personnel_id = obj;
	}
	
	public String getPersonnel_name_detail () {
		return personnel_name_detail;
	}
	
	public void setPersonnel_name_detail (String obj) {
		personnel_name_detail = obj;
	}
	
	public String getPost_number () {
		return post_number;
	}
	
	public void setPost_number (String obj) {
		post_number = obj;
	}
	
	public String getPost_name () {
		return post_name;
	}
	
	public void setPost_name (String obj) {
		post_name = obj;
	}
	
	public String getStatics_month () {
		return statics_month;
	}
	
	public void setStatics_month (String obj) {
		statics_month = obj;
	}
	
	public java.math.BigDecimal getPer_add_base () {
		return per_add_base;
	}
	
	public void setPer_add_base (java.math.BigDecimal obj) {
		per_add_base = obj;
	}
	
	public java.math.BigDecimal getPer_add_deal () {
		return per_add_deal;
	}
	
	public void setPer_add_deal (java.math.BigDecimal obj) {
		per_add_deal = obj;
	}
	
	public java.math.BigDecimal getPer_clear_add () {
		return per_clear_add;
	}
	
	public void setPer_clear_add (java.math.BigDecimal obj) {
		per_clear_add = obj;
	}
	
	public java.math.BigDecimal getPer_clear_add_deal () {
		return per_clear_add_deal;
	}
	
	public void setPer_clear_add_deal (java.math.BigDecimal obj) {
		per_clear_add_deal = obj;
	}
	
	public java.math.BigDecimal getPer_stock_all () {
		return per_stock_all;
	}
	
	public void setPer_stock_all (java.math.BigDecimal obj) {
		per_stock_all = obj;
	}
	
	public java.math.BigDecimal getPer_stock_new () {
		return per_stock_new;
	}
	
	public void setPer_stock_new (java.math.BigDecimal obj) {
		per_stock_new = obj;
	}
	
	public String getPer_stock_lev () {
		return per_stock_lev;
	}
	
	public void setPer_stock_lev (String obj) {
		per_stock_lev = obj;
	}
	
	public java.math.BigDecimal getTeam_add_base () {
		return team_add_base;
	}
	
	public void setTeam_add_base (java.math.BigDecimal obj) {
		team_add_base = obj;
	}
	
	public java.math.BigDecimal getTeam_add_deal () {
		return team_add_deal;
	}
	
	public void setTeam_add_deal (java.math.BigDecimal obj) {
		team_add_deal = obj;
	}
	
	public java.math.BigDecimal getTeam_redeem_all () {
		return team_redeem_all;
	}
	
	public void setTeam_redeem_all (java.math.BigDecimal obj) {
		team_redeem_all = obj;
	}
	
	public java.math.BigDecimal getTeam_clear_add () {
		return team_clear_add;
	}
	
	public void setTeam_clear_add (java.math.BigDecimal obj) {
		team_clear_add = obj;
	}
	
	public java.math.BigDecimal getTeam_stock_all () {
		return team_stock_all;
	}
	
	public void setTeam_stock_all (java.math.BigDecimal obj) {
		team_stock_all = obj;
	}
	
	public java.math.BigDecimal getTeam_stock_new () {
		return team_stock_new;
	}
	
	public void setTeam_stock_new (java.math.BigDecimal obj) {
		team_stock_new = obj;
	}
	
	public Integer getTeam_staff_num () {
		return team_staff_num;
	}
	
	public void setTeam_staff_num (Integer obj) {
		team_staff_num = obj;
	}
	
	public java.math.BigDecimal getTeam_per_increase () {
		return team_per_increase;
	}
	
	public void setTeam_per_increase (java.math.BigDecimal obj) {
		team_per_increase = obj;
	}
	
	public Integer getTeam_staff_num_regular () {
		return team_staff_num_regular;
	}
	
	public void setTeam_staff_num_regular (Integer obj) {
		team_staff_num_regular = obj;
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
		paramMap.put("wms_personnel_achievement_his_id", this.wms_personnel_achievement_his_id);
		paramMap.put("company_id", this.company_id);
		paramMap.put("company_name", this.company_name);
		paramMap.put("dept_id", this.dept_id);
		paramMap.put("dept_name", this.dept_name);
		paramMap.put("personnel_id", this.personnel_id);
		paramMap.put("personnel_name_detail", this.personnel_name_detail);
		paramMap.put("post_number", this.post_number);
		paramMap.put("post_name", this.post_name);
		paramMap.put("statics_month", this.statics_month);
		paramMap.put("per_add_base", this.per_add_base);
		paramMap.put("per_add_deal", this.per_add_deal);
		paramMap.put("per_clear_add", this.per_clear_add);
		paramMap.put("per_clear_add_deal", this.per_clear_add_deal);
		paramMap.put("per_stock_all", this.per_stock_all);
		paramMap.put("per_stock_new", this.per_stock_new);
		paramMap.put("per_stock_lev", this.per_stock_lev);
		paramMap.put("team_add_base", this.team_add_base);
		paramMap.put("team_add_deal", this.team_add_deal);
		paramMap.put("team_redeem_all", this.team_redeem_all);
		paramMap.put("team_clear_add", this.team_clear_add);
		paramMap.put("team_stock_all", this.team_stock_all);
		paramMap.put("team_stock_new", this.team_stock_new);
		paramMap.put("team_staff_num", this.team_staff_num);
		paramMap.put("team_per_increase", this.team_per_increase);
		paramMap.put("team_staff_num_regular", this.team_staff_num_regular);
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