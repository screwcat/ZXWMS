package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveWagerulesNonlocalLvSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private String jsonstr;
	
	private String count_types;
	
	private String cumulate_begins;
	
	private String cumulate_ends;
	
	private String wage_moneys;
	
	private Integer wms_inve_wagerules_nonlocal_head_id;
	
	private Integer compay_id;
	
	private Integer dept_id;
	
	private Integer personnel_postid;
	
	private String bill_code;
	
	private Integer rule_state;
	
	private Integer page_rule_state;
	
	private String remark;
	
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCount_types() {
		return count_types;
	}

	public void setCount_types(String count_types) {
		this.count_types = count_types;
	}

	public String getCumulate_begins() {
		return cumulate_begins;
	}

	public void setCumulate_begins(String cumulate_begins) {
		this.cumulate_begins = cumulate_begins;
	}

	public String getCumulate_ends() {
		return cumulate_ends;
	}

	public void setCumulate_ends(String cumulate_ends) {
		this.cumulate_ends = cumulate_ends;
	}

	public String getWage_moneys() {
		return wage_moneys;
	}

	public void setWage_moneys(String wage_moneys) {
		this.wage_moneys = wage_moneys;
	}

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}

	public Integer getWms_inve_wagerules_nonlocal_head_id() {
		return wms_inve_wagerules_nonlocal_head_id;
	}

	public void setWms_inve_wagerules_nonlocal_head_id(
			Integer wms_inve_wagerules_nonlocal_head_id) {
		this.wms_inve_wagerules_nonlocal_head_id = wms_inve_wagerules_nonlocal_head_id;
	}

	public Integer getCompay_id() {
		return compay_id;
	}

	public void setCompay_id(Integer compay_id) {
		this.compay_id = compay_id;
	}

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	public Integer getPersonnel_postid() {
		return personnel_postid;
	}

	public void setPersonnel_postid(Integer personnel_postid) {
		this.personnel_postid = personnel_postid;
	}

	public String getBill_code() {
		return bill_code;
	}

	public void setBill_code(String bill_code) {
		this.bill_code = bill_code;
	}

	public Integer getRule_state() {
		return rule_state;
	}

	public void setRule_state(Integer rule_state) {
		this.rule_state = rule_state;
	}

	public Integer getPage_rule_state() {
		return page_rule_state;
	}

	public void setPage_rule_state(Integer page_rule_state) {
		this.page_rule_state = page_rule_state;
	}
	
	
}