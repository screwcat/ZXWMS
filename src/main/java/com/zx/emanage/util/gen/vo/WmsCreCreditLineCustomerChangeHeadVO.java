package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class WmsCreCreditLineCustomerChangeHeadVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cus_customer_head_id;

    private String customer_code;

    private String customer_name;

    private String customer_ever_name;

    private String customer_category;

    private String gender;

    private String max_degree;

    private String birthday;

    private String id_card;

    private String province;

    private String city;

    private String district;

    private String address_more;

    private String has_children;

    private String children_name;

    private String children_address;

    private String children_telephone;

    private String has_married;

    private String has_house;

    private String current_address_province;

    private String current_address_city;

    private String current_address_district;

    private String current_address_more;

    private String post_code;

    private String fixed_telephone;

    private String mobile_telephone1;

    private String service_password1;

    private String mobile_telephone2;

    private String service_password2;

    private Double personal_year_income;

    private String customer_email;

    private Integer credit_card_limit;

    private String common_occupants;

    private String status;

    private Integer create_user_id;

    private String create_user_name;

    private String create_timestamp;

    private Integer last_update_user_id;

    private String last_update_timestamp;

    private String enable_flag;
    
    private String  house_address_province;
    
    private String house_address_city;
    
    private String house_address_district;
    
    private String house_address_more;
    

    public String getHouse_address_province() {
		return house_address_province;
	}

	public void setHouse_address_province(String house_address_province) {
		this.house_address_province = house_address_province;
	}

	public String getHouse_address_city() {
		return house_address_city;
	}

	public void setHouse_address_city(String house_address_city) {
		this.house_address_city = house_address_city;
	}

	public String getHouse_address_district() {
		return house_address_district;
	}

	public void setHouse_address_district(String house_address_district) {
		this.house_address_district = house_address_district;
	}

	public String getHouse_address_more() {
		return house_address_more;
	}

	public void setHouse_address_more(String house_address_more) {
		this.house_address_more = house_address_more;
	}

	public Integer getWms_cre_credit_line_customer_change_head_id()
    {
        return wms_cre_credit_line_customer_change_head_id;
    }

    public void setWms_cre_credit_line_customer_change_head_id(Integer obj)
    {
        wms_cre_credit_line_customer_change_head_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public Integer getWms_cus_customer_head_id()
    {
        return wms_cus_customer_head_id;
    }

    public void setWms_cus_customer_head_id(Integer obj)
    {
        wms_cus_customer_head_id = obj;
    }

    public String getCustomer_code()
    {
        return customer_code;
    }

    public void setCustomer_code(String obj)
    {
        customer_code = obj;
    }

    public String getCustomer_name()
    {
        return customer_name;
    }

    public void setCustomer_name(String obj)
    {
        customer_name = obj;
    }

    public String getCustomer_ever_name()
    {
        return customer_ever_name;
    }

    public void setCustomer_ever_name(String obj)
    {
        customer_ever_name = obj;
    }

    public String getCustomer_category()
    {
        return customer_category;
    }

    public void setCustomer_category(String obj)
    {
        customer_category = obj;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String obj)
    {
        gender = obj;
    }

    public String getMax_degree()
    {
        return max_degree;
    }

    public void setMax_degree(String obj)
    {
        max_degree = obj;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String obj)
    {
        birthday = obj;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String obj)
    {
        id_card = obj;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String obj)
    {
        province = obj;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String obj)
    {
        city = obj;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String obj)
    {
        district = obj;
    }

    public String getAddress_more()
    {
        return address_more;
    }

    public void setAddress_more(String obj)
    {
        address_more = obj;
    }

    public String getHas_children()
    {
        return has_children;
    }

    public void setHas_children(String obj)
    {
        has_children = obj;
    }

    public String getChildren_name()
    {
        return children_name;
    }

    public void setChildren_name(String obj)
    {
        children_name = obj;
    }

    public String getChildren_address()
    {
        return children_address;
    }

    public void setChildren_address(String obj)
    {
        children_address = obj;
    }

    public String getChildren_telephone()
    {
        return children_telephone;
    }

    public void setChildren_telephone(String obj)
    {
        children_telephone = obj;
    }

    public String getHas_married()
    {
        return has_married;
    }

    public void setHas_married(String obj)
    {
        has_married = obj;
    }

    public String getHas_house()
    {
        return has_house;
    }

    public void setHas_house(String obj)
    {
        has_house = obj;
    }

    public String getCurrent_address_province()
    {
        return current_address_province;
    }

    public void setCurrent_address_province(String obj)
    {
        current_address_province = obj;
    }

    public String getCurrent_address_city()
    {
        return current_address_city;
    }

    public void setCurrent_address_city(String obj)
    {
        current_address_city = obj;
    }

    public String getCurrent_address_district()
    {
        return current_address_district;
    }

    public void setCurrent_address_district(String obj)
    {
        current_address_district = obj;
    }

    public String getCurrent_address_more()
    {
        return current_address_more;
    }

    public void setCurrent_address_more(String obj)
    {
        current_address_more = obj;
    }

    public String getPost_code()
    {
        return post_code;
    }

    public void setPost_code(String obj)
    {
        post_code = obj;
    }

    public String getFixed_telephone()
    {
        return fixed_telephone;
    }

    public void setFixed_telephone(String obj)
    {
        fixed_telephone = obj;
    }

    public String getMobile_telephone1()
    {
        return mobile_telephone1;
    }

    public void setMobile_telephone1(String obj)
    {
        mobile_telephone1 = obj;
    }

    public String getService_password1()
    {
        return service_password1;
    }

    public void setService_password1(String obj)
    {
        service_password1 = obj;
    }

    public String getMobile_telephone2()
    {
        return mobile_telephone2;
    }

    public void setMobile_telephone2(String obj)
    {
        mobile_telephone2 = obj;
    }

    public String getService_password2()
    {
        return service_password2;
    }

    public void setService_password2(String obj)
    {
        service_password2 = obj;
    }

    public Double getPersonal_year_income()
    {
        return personal_year_income;
    }

    public void setPersonal_year_income(Double obj)
    {
        personal_year_income = obj;
    }

    public String getCustomer_email()
    {
        return customer_email;
    }

    public void setCustomer_email(String obj)
    {
        customer_email = obj;
    }

    public Integer getCredit_card_limit()
    {
        return credit_card_limit;
    }

    public void setCredit_card_limit(Integer obj)
    {
        credit_card_limit = obj;
    }

    public String getCommon_occupants()
    {
        return common_occupants;
    }

    public void setCommon_occupants(String obj)
    {
        common_occupants = obj;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String obj)
    {
        status = obj;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer obj)
    {
        create_user_id = obj;
    }

    public String getCreate_user_name()
    {
        return create_user_name;
    }

    public void setCreate_user_name(String obj)
    {
        create_user_name = obj;
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