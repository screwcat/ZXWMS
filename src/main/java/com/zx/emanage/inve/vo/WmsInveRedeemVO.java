package com.zx.emanage.inve.vo;


/*
 * @version 2.0
 */

public class WmsInveRedeemVO
{

    private Integer wms_inve_transa_id;

    private String redeemGridData;

    private String end_of_date_org;

    private String is_order_redeem;

    private String is_update;

    private String customer_email;

    public Integer getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    public void setWms_inve_transa_id(Integer wms_inve_transa_id)
    {
        this.wms_inve_transa_id = wms_inve_transa_id;
    }

    public String getRedeemGridData()
    {
        return redeemGridData;
    }

    public void setRedeemGridData(String redeemGridData)
    {
        this.redeemGridData = redeemGridData;
    }

    public String getEnd_of_date_org()
    {
        return end_of_date_org;
    }

    public void setEnd_of_date_org(String end_of_date_org)
    {
        this.end_of_date_org = end_of_date_org;
    }

    public String getIs_order_redeem()
    {
        return is_order_redeem;
    }

    public void setIs_order_redeem(String is_order_redeem)
    {
        this.is_order_redeem = is_order_redeem;
    }

    public String getIs_update()
    {
        return is_update;
    }

    public void setIs_update(String is_update)
    {
        this.is_update = is_update;
    }

    public String getCustomer_email()
    {
        return customer_email;
    }

    public void setCustomer_email(String customer_email)
    {
        this.customer_email = customer_email;
    }
}