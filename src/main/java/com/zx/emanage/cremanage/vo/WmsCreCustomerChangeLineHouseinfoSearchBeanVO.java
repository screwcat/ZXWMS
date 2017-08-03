package com.zx.emanage.cremanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCreCustomerChangeLineHouseinfoSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private Integer wms_cre_customer_change_line_houseinfo_id;
    
    private java.sql.Timestamp house_buy_date;
    
    private String house_buy_money;
    
    private String house_building_area;
    
    private String house_address_province;
    
    private String house_address_city;
    
    private String house_address_district;
    
    private String house_address_more;
    
    private String house_post_code;
    
    private Integer create_user_id;
    
    private java.sql.Timestamp create_timestamp;
    
    private Integer last_update_user_id;
    
    private java.sql.Timestamp last_update_timestamp;
    
    private String enable_flag;
    
    private String house_address_street;
    
    private String is_supp_house_card;
    
    private String house_type;
    
    private String house_no;
    
    private String house_vol_no;
    
    private String community_name;
    
    private String houses_number;
    
    private String city;
    
    private String house_age;
    
    private String house_remark;
    
    private String is_compound;
    
    private String house_place;
    
    private String house_name;
    
    public Integer getWms_cre_customer_change_line_houseinfo_id()
    {
        return wms_cre_customer_change_line_houseinfo_id;
    }

    public void setWms_cre_customer_change_line_houseinfo_id(Integer wms_cre_customer_change_line_houseinfo_id)
    {
        this.wms_cre_customer_change_line_houseinfo_id = wms_cre_customer_change_line_houseinfo_id;
    }

    public Integer getWms_cre_credit_line_customer_change_head_id()
    {
        return wms_cre_credit_line_customer_change_head_id;
    }

    public void setWms_cre_credit_line_customer_change_head_id(Integer wms_cre_credit_line_customer_change_head_id)
    {
        this.wms_cre_credit_line_customer_change_head_id = wms_cre_credit_line_customer_change_head_id;
    }

	public java.sql.Timestamp getHouse_buy_date() {
		return house_buy_date;
	}

	public String getHouse_buy_money() {
		return house_buy_money;
	}

	public String getHouse_building_area() {
		return house_building_area;
	}

	public String getHouse_address_province() {
		return house_address_province;
	}

	public String getHouse_address_city() {
		return house_address_city;
	}

	public String getHouse_address_district() {
		return house_address_district;
	}

	public String getHouse_address_more() {
		return house_address_more;
	}

	public String getHouse_post_code() {
		return house_post_code;
	}

	public Integer getCreate_user_id() {
		return create_user_id;
	}

	public java.sql.Timestamp getCreate_timestamp() {
		return create_timestamp;
	}

	public Integer getLast_update_user_id() {
		return last_update_user_id;
	}

	public java.sql.Timestamp getLast_update_timestamp() {
		return last_update_timestamp;
	}

	public String getEnable_flag() {
		return enable_flag;
	}

	public String getHouse_address_street() {
		return house_address_street;
	}

	public String getIs_supp_house_card() {
		return is_supp_house_card;
	}

	public String getHouse_type() {
		return house_type;
	}

	public String getHouse_no() {
		return house_no;
	}

	public String getHouse_vol_no() {
		return house_vol_no;
	}

	public String getCommunity_name() {
		return community_name;
	}

	public String getHouses_number() {
		return houses_number;
	}

	public String getCity() {
		return city;
	}

	public String getHouse_age() {
		return house_age;
	}

	public String getHouse_remark() {
		return house_remark;
	}

	public String getIs_compound() {
		return is_compound;
	}

	public String getHouse_place() {
		return house_place;
	}

	public String getHouse_name() {
		return house_name;
	}

	public void setHouse_buy_date(java.sql.Timestamp house_buy_date) {
		this.house_buy_date = house_buy_date;
	}

	public void setHouse_buy_money(String house_buy_money) {
		this.house_buy_money = house_buy_money;
	}

	public void setHouse_building_area(String house_building_area) {
		this.house_building_area = house_building_area;
	}

	public void setHouse_address_province(String house_address_province) {
		this.house_address_province = house_address_province;
	}

	public void setHouse_address_city(String house_address_city) {
		this.house_address_city = house_address_city;
	}

	public void setHouse_address_district(String house_address_district) {
		this.house_address_district = house_address_district;
	}

	public void setHouse_address_more(String house_address_more) {
		this.house_address_more = house_address_more;
	}

	public void setHouse_post_code(String house_post_code) {
		this.house_post_code = house_post_code;
	}

	public void setCreate_user_id(Integer create_user_id) {
		this.create_user_id = create_user_id;
	}

	public void setCreate_timestamp(java.sql.Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	public void setLast_update_user_id(Integer last_update_user_id) {
		this.last_update_user_id = last_update_user_id;
	}

	public void setLast_update_timestamp(java.sql.Timestamp last_update_timestamp) {
		this.last_update_timestamp = last_update_timestamp;
	}

	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag;
	}

	public void setHouse_address_street(String house_address_street) {
		this.house_address_street = house_address_street;
	}

	public void setIs_supp_house_card(String is_supp_house_card) {
		this.is_supp_house_card = is_supp_house_card;
	}

	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}

	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}

	public void setHouse_vol_no(String house_vol_no) {
		this.house_vol_no = house_vol_no;
	}

	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}

	public void setHouses_number(String houses_number) {
		this.houses_number = houses_number;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setHouse_age(String house_age) {
		this.house_age = house_age;
	}

	public void setHouse_remark(String house_remark) {
		this.house_remark = house_remark;
	}

	public void setIs_compound(String is_compound) {
		this.is_compound = is_compound;
	}

	public void setHouse_place(String house_place) {
		this.house_place = house_place;
	}

	public void setHouse_name(String house_name) {
		this.house_name = house_name;
	}
}