package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInvePruductRebateWaySearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	private	Integer wms_inve_pruduct_category_id;
	private	Integer category_rebate_way;//返利方式
	public Integer getWms_inve_pruduct_category_id() {
		return wms_inve_pruduct_category_id;
	}
	public void setWms_inve_pruduct_category_id(Integer wms_inve_pruduct_category_id) {
		this.wms_inve_pruduct_category_id = wms_inve_pruduct_category_id;
	}
	public Integer getCategory_rebate_way() {
		return category_rebate_way;
	}
	public void setCategory_rebate_way(Integer category_rebate_way) {
		this.category_rebate_way = category_rebate_way;
	}
	
}