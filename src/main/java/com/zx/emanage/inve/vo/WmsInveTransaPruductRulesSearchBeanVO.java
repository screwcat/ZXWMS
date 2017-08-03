package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveTransaPruductRulesSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	public String category_name;//产品名集合
	
	public String wms_inve_pruduct_category_id;//产品id集合

	public String wms_inve_transa_pruduct_rules_ids;//产品佣金规则主键
	
	public String pruductRules;
	
	private Integer commission_general_rules;
	
	public String getWms_inve_pruduct_category_id() {
		return wms_inve_pruduct_category_id;
	}

	public void setWms_inve_pruduct_category_id(String wms_inve_pruduct_category_id) {
		this.wms_inve_pruduct_category_id = wms_inve_pruduct_category_id;
	}

	public String getWms_inve_transa_pruduct_rules_ids() {
		return wms_inve_transa_pruduct_rules_ids;
	}

	public void setWms_inve_transa_pruduct_rules_ids(
			String wms_inve_transa_pruduct_rules_ids) {
		this.wms_inve_transa_pruduct_rules_ids = wms_inve_transa_pruduct_rules_ids;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getPruductRules() {
		return pruductRules;
	}

	public void setPruductRules(String pruductRules) {
		this.pruductRules = pruductRules;
	}

	public Integer getCommission_general_rules() {
		return commission_general_rules;
	}

	public void setCommission_general_rules(Integer commission_general_rules) {
		this.commission_general_rules = commission_general_rules;
	}
}