package com.zx.emanage.util.gen.vo;


import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsInveRedeemVO implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_transa_id;
	
    private java.math.BigDecimal redeem_amount;
		
	private java.sql.Date old_date_of_payment;
	
	private String old_date_of_payment_str;

	private Integer old_wms_inve_transa_id;

	private java.sql.Date redeem_date;
	
	private String redeem_date_str;
	
	private String bill_source;

	private String redeem_amount_grid;
	
    private String category_name;

	public Integer getWms_inve_transa_id() {
		return wms_inve_transa_id;
	}

	public void setWms_inve_transa_id(Integer wms_inve_transa_id) {
		this.wms_inve_transa_id = wms_inve_transa_id;
	}

	public java.math.BigDecimal getRedeem_amount() {
		return redeem_amount;
	}

	public void setRedeem_amount(java.math.BigDecimal redeem_amount) {
		this.redeem_amount = redeem_amount;
	}

	public java.sql.Date getOld_date_of_payment() {
		return old_date_of_payment;
	}

	public void setOld_date_of_payment(java.sql.Date old_date_of_payment) {
		this.old_date_of_payment = old_date_of_payment;
	}

	public String getOld_date_of_payment_str() {
		return old_date_of_payment_str;
	}

	public void setOld_date_of_payment_str(String old_date_of_payment_str) {
		this.old_date_of_payment_str = old_date_of_payment_str;
	}

	public Integer getOld_wms_inve_transa_id() {
		return old_wms_inve_transa_id;
	}

	public void setOld_wms_inve_transa_id(Integer old_wms_inve_transa_id) {
		this.old_wms_inve_transa_id = old_wms_inve_transa_id;
	}

	public java.sql.Date getRedeem_date() {
		return redeem_date;
	}

	public void setRedeem_date(java.sql.Date redeem_date) {
		this.redeem_date = redeem_date;
	}

	public String getRedeem_date_str() {
		return redeem_date_str;
	}

	public void setRedeem_date_str(String redeem_date_str) {
		this.redeem_date_str = redeem_date_str;
	}

	public String getBill_source() {
		return bill_source;
	}

	public void setBill_source(String bill_source) {
		this.bill_source = bill_source;
	}

	public String getRedeem_amount_grid() {
		return redeem_amount_grid;
	}

	public void setRedeem_amount_grid(String redeem_amount_grid) {
		this.redeem_amount_grid = redeem_amount_grid;
	}

    public String getCategory_name()
    {
        return category_name;
    }

    public void setCategory_name(String category_name)
    {
        this.category_name = category_name;
    }
}