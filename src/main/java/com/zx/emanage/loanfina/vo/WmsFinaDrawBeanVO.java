package com.zx.emanage.loanfina.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsFinaDrawBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private Integer wms_post_dunning_head_id;

    private Integer wms_fina_cre_pay_id;

    private java.sql.Date repay_date;

    private java.math.BigDecimal this_late_fees;

	public Integer getWms_post_dunning_head_id() {
		return wms_post_dunning_head_id;
	}

	public void setWms_post_dunning_head_id(Integer wms_post_dunning_head_id) {
		this.wms_post_dunning_head_id = wms_post_dunning_head_id;
	}

	public Integer getWms_fina_cre_pay_id() {
		return wms_fina_cre_pay_id;
	}

	public void setWms_fina_cre_pay_id(Integer wms_fina_cre_pay_id) {
		this.wms_fina_cre_pay_id = wms_fina_cre_pay_id;
	}

	public java.sql.Date getRepay_date() {
		return repay_date;
	}

	public void setRepay_date(java.sql.Date repay_date) {
		this.repay_date = repay_date;
	}

	public java.math.BigDecimal getThis_late_fees() {
		return this_late_fees;
	}

	public void setThis_late_fees(java.math.BigDecimal this_late_fees) {
		this.this_late_fees = this_late_fees;
	}
}