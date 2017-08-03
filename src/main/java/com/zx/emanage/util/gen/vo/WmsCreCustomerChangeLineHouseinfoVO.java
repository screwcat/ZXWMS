package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class WmsCreCustomerChangeLineHouseinfoVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer wms_cre_customer_change_line_houseinfo_id;

    private String house_buy_date;

    private Double house_buy_money;

    private Double house_building_area;

    private String house_address_province;

    private String house_address_city;

    private String house_address_district;

    private String house_address_more;

    private String house_post_code;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private Integer create_user_id;

    private String create_timestamp;

    private Integer last_update_user_id;

    private String last_update_timestamp;

    private String enable_flag;

    public Integer getWms_cre_customer_change_line_houseinfo_id()
    {
        return wms_cre_customer_change_line_houseinfo_id;
    }

    public void setWms_cre_customer_change_line_houseinfo_id(Integer obj)
    {
        wms_cre_customer_change_line_houseinfo_id = obj;
    }

    public String getHouse_buy_date()
    {
        return house_buy_date;
    }

    public void setHouse_buy_date(String obj)
    {
        house_buy_date = obj;
    }

    public Double getHouse_buy_money()
    {
        return house_buy_money;
    }

    public void setHouse_buy_money(Double obj)
    {
        house_buy_money = obj;
    }

    public Double getHouse_building_area()
    {
        return house_building_area;
    }

    public void setHouse_building_area(Double obj)
    {
        house_building_area = obj;
    }

    public String getHouse_address_province()
    {
        return house_address_province;
    }

    public void setHouse_address_province(String obj)
    {
        house_address_province = obj;
    }

    public String getHouse_address_city()
    {
        return house_address_city;
    }

    public void setHouse_address_city(String obj)
    {
        house_address_city = obj;
    }

    public String getHouse_address_district()
    {
        return house_address_district;
    }

    public void setHouse_address_district(String obj)
    {
        house_address_district = obj;
    }

    public String getHouse_address_more()
    {
        return house_address_more;
    }

    public void setHouse_address_more(String obj)
    {
        house_address_more = obj;
    }

    public String getHouse_post_code()
    {
        return house_post_code;
    }

    public void setHouse_post_code(String obj)
    {
        house_post_code = obj;
    }

    public Integer getWms_cre_credit_line_customer_change_head_id()
    {
        return wms_cre_credit_line_customer_change_head_id;
    }

    public void setWms_cre_credit_line_customer_change_head_id(Integer obj)
    {
        wms_cre_credit_line_customer_change_head_id = obj;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer obj)
    {
        create_user_id = obj;
    }

    public String getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(String obj)
    {
        create_timestamp = obj;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer obj)
    {
        last_update_user_id = obj;
    }

    public String getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(String obj)
    {
        last_update_timestamp = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }
}