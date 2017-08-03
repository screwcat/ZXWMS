package com.zx.emanage.inve.vo;

import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class WmsInveRedeemRecordVO
{

	private static final long serialVersionUID = 1L;

    private Integer wms_inve_redeem_record_id;

    private Integer wms_inve_transa_id;

    private Integer wms_inve_customer_id;

    private Integer wms_inve_redeem_id;

    private Integer wms_inve_redeem_detail_id;

    private java.math.BigDecimal principal_amount;

    private java.sql.Timestamp redeem_date;

    private String redeem_date_str;

    private String redeem_reason;

    private String redeem_company_reason;
    
    private String is_fully_redeemed;

    private java.math.BigDecimal management_fee;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private String enable_flag;

    private String bill_code;

    private String data_status;

    private String id_card;

    private String cus_name;

    private java.math.BigDecimal total_invest_amount;

    private java.math.BigDecimal redeem_apply_total_amount;

    private java.math.BigDecimal total_due_income;

    private java.math.BigDecimal total_management_fee;

    private java.math.BigDecimal redeem_reality_total_amount;

    private String redeem_reality_total_amount_upper;

    private Integer bel_salesman_id_id;

    private String bel_salesman_name;

    private Integer bel_salesman_dept_id;

    private Integer create_user_id;

    private String create_user_name;

    private Integer create_user_dept_id;

    private Integer salesman_id;

    private Integer salesman_dept_id;

    private String is_payback;

    private BigDecimal total_deduct_tax_point;

    private BigDecimal total_deduct_money;

    private String redeem_type;

    private BigDecimal redeem_product_interest;

    private Integer wms_inve_transa_prod_id;
    
    private Integer wms_inve_pruduct_category_id;

    private String category_name;

    private String is_take_off_damages;

    public Integer getWms_inve_redeem_record_id()
    {
        return wms_inve_redeem_record_id;
    }

    public void setWms_inve_redeem_record_id(Integer wms_inve_redeem_record_id)
    {
        this.wms_inve_redeem_record_id = wms_inve_redeem_record_id;
    }

    public Integer getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    public void setWms_inve_transa_id(Integer wms_inve_transa_id)
    {
        this.wms_inve_transa_id = wms_inve_transa_id;
    }

    public Integer getWms_inve_redeem_id()
    {
        return wms_inve_redeem_id;
    }

    public void setWms_inve_redeem_id(Integer wms_inve_redeem_id)
    {
        this.wms_inve_redeem_id = wms_inve_redeem_id;
    }

    public Integer getWms_inve_redeem_detail_id()
    {
        return wms_inve_redeem_detail_id;
    }

    public void setWms_inve_redeem_detail_id(Integer wms_inve_redeem_detail_id)
    {
        this.wms_inve_redeem_detail_id = wms_inve_redeem_detail_id;
    }

    public java.math.BigDecimal getPrincipal_amount()
    {
        return principal_amount;
    }

    public void setPrincipal_amount(java.math.BigDecimal principal_amount)
    {
        this.principal_amount = principal_amount;
    }

    public java.sql.Timestamp getRedeem_date()
    {
        return redeem_date;
    }

    public void setRedeem_date(java.sql.Timestamp redeem_date)
    {
        this.redeem_date = redeem_date;
    }

    public String getRedeem_date_str()
    {
        return redeem_date_str;
    }

    public void setRedeem_date_str(String redeem_date_str)
    {
        this.redeem_date_str = redeem_date_str;
    }

    public String getRedeem_company_reason()
    {
        return redeem_company_reason;
    }

    public void setRedeem_company_reason(String redeem_company_reason)
    {
        this.redeem_company_reason = redeem_company_reason;
    }

    public java.math.BigDecimal getManagement_fee()
    {
        return management_fee;
    }

    public void setManagement_fee(java.math.BigDecimal management_fee)
    {
        this.management_fee = management_fee;
    }

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp create_timestamp)
    {
        this.create_timestamp = create_timestamp;
    }

    public String getCreate_timestamp_str()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestamp_str(String create_timestamp_str)
    {
        this.create_timestamp_str = create_timestamp_str;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String enable_flag)
    {
        this.enable_flag = enable_flag;
    }

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    public String getData_status()
    {
        return data_status;
    }

    public void setData_status(String data_status)
    {
        this.data_status = data_status;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String id_card)
    {
        this.id_card = id_card;
    }

    public String getCus_name()
    {
        return cus_name;
    }

    public void setCus_name(String cus_name)
    {
        this.cus_name = cus_name;
    }

    public java.math.BigDecimal getTotal_invest_amount()
    {
        return total_invest_amount;
    }

    public void setTotal_invest_amount(java.math.BigDecimal total_invest_amount)
    {
        this.total_invest_amount = total_invest_amount;
    }

    public java.math.BigDecimal getRedeem_apply_total_amount()
    {
        return redeem_apply_total_amount;
    }

    public void setRedeem_apply_total_amount(java.math.BigDecimal redeem_apply_total_amount)
    {
        this.redeem_apply_total_amount = redeem_apply_total_amount;
    }

    public java.math.BigDecimal getTotal_due_income()
    {
        return total_due_income;
    }

    public void setTotal_due_income(java.math.BigDecimal total_due_income)
    {
        this.total_due_income = total_due_income;
    }

    public java.math.BigDecimal getTotal_management_fee()
    {
        return total_management_fee;
    }

    public void setTotal_management_fee(java.math.BigDecimal total_management_fee)
    {
        this.total_management_fee = total_management_fee;
    }

    public java.math.BigDecimal getRedeem_reality_total_amount()
    {
        return redeem_reality_total_amount;
    }

    public void setRedeem_reality_total_amount(java.math.BigDecimal redeem_reality_total_amount)
    {
        this.redeem_reality_total_amount = redeem_reality_total_amount;
    }

    public String getRedeem_reality_total_amount_upper()
    {
        return redeem_reality_total_amount_upper;
    }

    public void setRedeem_reality_total_amount_upper(String redeem_reality_total_amount_upper)
    {
        this.redeem_reality_total_amount_upper = redeem_reality_total_amount_upper;
    }

    public Integer getBel_salesman_id_id()
    {
        return bel_salesman_id_id;
    }

    public void setBel_salesman_id_id(Integer bel_salesman_id_id)
    {
        this.bel_salesman_id_id = bel_salesman_id_id;
    }

    public Integer getBel_salesman_dept_id()
    {
        return bel_salesman_dept_id;
    }

    public void setBel_salesman_dept_id(Integer bel_salesman_dept_id)
    {
        this.bel_salesman_dept_id = bel_salesman_dept_id;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer create_user_id)
    {
        this.create_user_id = create_user_id;
    }

    public Integer getCreate_user_dept_id()
    {
        return create_user_dept_id;
    }

    public void setCreate_user_dept_id(Integer create_user_dept_id)
    {
        this.create_user_dept_id = create_user_dept_id;
    }

    public Integer getWms_inve_transa_prod_id()
    {
        return wms_inve_transa_prod_id;
    }

    public void setWms_inve_transa_prod_id(Integer wms_inve_transa_prod_id)
    {
        this.wms_inve_transa_prod_id = wms_inve_transa_prod_id;
    }

    public String getCategory_name()
    {
        return category_name;
    }

    public void setCategory_name(String category_name)
    {
        this.category_name = category_name;
    }

    /**
     * @return the bel_salesman_name
     */
    public String getBel_salesman_name()
    {
        return bel_salesman_name;
    }

    /**
     * @param bel_salesman_name the bel_salesman_name to set
     */
    public void setBel_salesman_name(String bel_salesman_name)
    {
        this.bel_salesman_name = bel_salesman_name;
    }

    /**
     * @return the wms_inve_customer_id
     */
    public Integer getWms_inve_customer_id()
    {
        return wms_inve_customer_id;
    }

    /**
     * @param wms_inve_customer_id the wms_inve_customer_id to set
     */
    public void setWms_inve_customer_id(Integer wms_inve_customer_id)
    {
        this.wms_inve_customer_id = wms_inve_customer_id;
    }

    /**
     * @return the redeem_reason
     */
    public String getRedeem_reason()
    {
        return redeem_reason;
    }

    /**
     * @param redeem_reason the redeem_reason to set
     */
    public void setRedeem_reason(String redeem_reason)
    {
        this.redeem_reason = redeem_reason;
    }

    /**
     * @return the is_fully_redeemed
     */
    public String getIs_fully_redeemed()
    {
        return is_fully_redeemed;
    }

    /**
     * @param is_fully_redeemed the is_fully_redeemed to set
     */
    public void setIs_fully_redeemed(String is_fully_redeemed)
    {
        this.is_fully_redeemed = is_fully_redeemed;
    }

    /**
     * @return the create_user_name
     */
    public String getCreate_user_name()
    {
        return create_user_name;
    }

    /**
     * @param create_user_name the create_user_name to set
     */
    public void setCreate_user_name(String create_user_name)
    {
        this.create_user_name = create_user_name;
    }

    /**
     * @return the salesman_id
     */
    public Integer getSalesman_id()
    {
        return salesman_id;
    }

    /**
     * @param salesman_id the salesman_id to set
     */
    public void setSalesman_id(Integer salesman_id)
    {
        this.salesman_id = salesman_id;
    }

    /**
     * @return the salesman_dept_id
     */
    public Integer getSalesman_dept_id()
    {
        return salesman_dept_id;
    }

    /**
     * @param salesman_dept_id the salesman_dept_id to set
     */
    public void setSalesman_dept_id(Integer salesman_dept_id)
    {
        this.salesman_dept_id = salesman_dept_id;
    }

    /**
     * @return the is_payback
     */
    public String getIs_payback()
    {
        return is_payback;
    }

    /**
     * @param is_payback the is_payback to set
     */
    public void setIs_payback(String is_payback)
    {
        this.is_payback = is_payback;
    }

    /**
     * @return the total_deduct_tax_point
     */
    public BigDecimal getTotal_deduct_tax_point()
    {
        return total_deduct_tax_point;
    }

    /**
     * @param total_deduct_tax_point the total_deduct_tax_point to set
     */
    public void setTotal_deduct_tax_point(BigDecimal total_deduct_tax_point)
    {
        this.total_deduct_tax_point = total_deduct_tax_point;
    }

    /**
     * @return the total_deduct_money
     */
    public BigDecimal getTotal_deduct_money()
    {
        return total_deduct_money;
    }

    /**
     * @param total_deduct_money the total_deduct_money to set
     */
    public void setTotal_deduct_money(BigDecimal total_deduct_money)
    {
        this.total_deduct_money = total_deduct_money;
    }

    /**
     * @return the redeem_type
     */
    public String getRedeem_type()
    {
        return redeem_type;
    }

    /**
     * @param redeem_type the redeem_type to set
     */
    public void setRedeem_type(String redeem_type)
    {
        this.redeem_type = redeem_type;
    }

    /**
     * @return the wms_inve_pruduct_category_id
     */
    public Integer getWms_inve_pruduct_category_id()
    {
        return wms_inve_pruduct_category_id;
    }

    /**
     * @param wms_inve_pruduct_category_id the wms_inve_pruduct_category_id to set
     */
    public void setWms_inve_pruduct_category_id(Integer wms_inve_pruduct_category_id)
    {
        this.wms_inve_pruduct_category_id = wms_inve_pruduct_category_id;
    }

    /**
     * @return the redeem_product_interest
     */
    public BigDecimal getRedeem_product_interest()
    {
        return redeem_product_interest;
    }

    /**
     * @param redeem_product_interest the redeem_product_interest to set
     */
    public void setRedeem_product_interest(BigDecimal redeem_product_interest)
    {
        this.redeem_product_interest = redeem_product_interest;
    }

    public String getIs_take_off_damages()
    {
        return is_take_off_damages;
    }

    public void setIs_take_off_damages(String is_take_off_damages)
    {
        this.is_take_off_damages = is_take_off_damages;
    }
}