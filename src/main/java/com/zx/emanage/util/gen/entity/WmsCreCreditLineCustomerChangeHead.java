package com.zx.emanage.util.gen.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsCreCreditLineCustomerChangeHead implements BaseEntity
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

    private java.sql.Date birthday;

    private String birthday_str;

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

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private String is_major;

    private String work_stat;

    private String house_stat;

    private String residence_time;

    private Integer cre_card_num;

    private Integer existing_loan;

    private Double monthly_income;

    private String resource_of_tel1;

    private String resource_of_tel2;

    private String is_real_name_tel1;

    private String is_real_name_tel2;

    private String real_name_tel1;

    private String real_name_rela1;

    private String real_name_tel2;

    private String real_name_rela2;
    
    private String  customer_age;

    // 是否完善联系人 是否完善联系人 1完善 0/空 未完善
    private String is_finish;

	private boolean isExcludePKFlag;
    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_credit_line_customer_change_head_id" };

    private static String[] columnNameArr = { "wms_cre_credit_line_customer_change_head_id", "wms_cre_credit_head_id",
                                             "wms_cus_customer_head_id", "customer_code", "customer_name",
                                             "customer_ever_name", "customer_category", "gender", "max_degree",
                                             "birthday", "birthday_str", "id_card", "province", "city", "district",
                                             "address_more", "has_children", "children_name", "children_address",
                                             "children_telephone", "has_married", "has_house",
                                             "current_address_province", "current_address_city",
                                             "current_address_district", "current_address_more", "post_code",
                                             "fixed_telephone", "mobile_telephone1", "service_password1",
                                             "mobile_telephone2", "service_password2", "personal_year_income",
                                             "customer_email", "credit_card_limit", "common_occupants", "status",
                                             "create_user_id", "create_user_name", "create_timestamp",
                                             "create_timestamp_str", "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag", "is_major", "work_stat",
                                             "house_stat", "residence_time", "cre_card_num", "existing_loan",
                                             "monthly_income", "resource_of_tel1", "resource_of_tel2",
                                             "is_real_name_tel1", "is_real_name_tel2", "real_name_tel1",
                                             "real_name_rela1", "real_name_tel2", "real_name_rela2","isExcludePKFlag"};

    public String getWork_stat()
    {
        return work_stat;
    }

    public void setWork_stat(String work_stat)
    {
        this.work_stat = work_stat;
    }

    public String getHouse_stat()
    {
        return house_stat;
    }

    public void setHouse_stat(String house_stat)
    {
        this.house_stat = house_stat;
    }

    public String getResidence_time()
    {
        return residence_time;
    }

    public void setResidence_time(String residence_time)
    {
        this.residence_time = residence_time;
    }

    public Integer getCre_card_num()
    {
        return cre_card_num;
    }

    public void setCre_card_num(Integer cre_card_num)
    {
        this.cre_card_num = cre_card_num;
    }

    public Integer getExisting_loan()
    {
        return existing_loan;
    }

    public void setExisting_loan(Integer existing_loan)
    {
        this.existing_loan = existing_loan;
    }

    public Double getMonthly_income()
    {
        return monthly_income;
    }

    public void setMonthly_income(Double monthly_income)
    {
        this.monthly_income = monthly_income;
    }

    public String getResource_of_tel1()
    {
        return resource_of_tel1;
    }

    public void setResource_of_tel1(String resource_of_tel1)
    {
        this.resource_of_tel1 = resource_of_tel1;
    }

    public String getResource_of_tel2()
    {
        return resource_of_tel2;
    }

    public void setResource_of_tel2(String resource_of_tel2)
    {
        this.resource_of_tel2 = resource_of_tel2;
    }

    public String getIs_real_name_tel1()
    {
        return is_real_name_tel1;
    }

    public void setIs_real_name_tel1(String is_real_name_tel1)
    {
        this.is_real_name_tel1 = is_real_name_tel1;
    }

    public String getIs_real_name_tel2()
    {
        return is_real_name_tel2;
    }

    public void setIs_real_name_tel2(String is_real_name_tel2)
    {
        this.is_real_name_tel2 = is_real_name_tel2;
    }

    public String getReal_name_tel1()
    {
        return real_name_tel1;
    }

    public void setReal_name_tel1(String real_name_tel1)
    {
        this.real_name_tel1 = real_name_tel1;
    }

    public String getReal_name_rela1()
    {
        return real_name_rela1;
    }

    public void setReal_name_rela1(String real_name_rela1)
    {
        this.real_name_rela1 = real_name_rela1;
    }

    public String getReal_name_tel2()
    {
        return real_name_tel2;
    }

    public void setReal_name_tel2(String real_name_tel2)
    {
        this.real_name_tel2 = real_name_tel2;
    }

    public String getReal_name_rela2()
    {
        return real_name_rela2;
    }

    public void setReal_name_rela2(String real_name_rela2)
    {
        this.real_name_rela2 = real_name_rela2;
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

    public java.sql.Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(java.sql.Date obj)
    {
        birthday = obj;
    }

    public String getBirthdayStr()
    {
        return birthday_str;
    }

    public void setBirthdayStr(String obj)
    {
        birthday_str = obj;
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

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp obj)
    {
        create_timestamp = obj;
    }

    public String getCreate_timestampStr()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestampStr(String obj)
    {
        create_timestamp_str = obj;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer obj)
    {
        last_update_user_id = obj;
    }

    public java.sql.Timestamp getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(java.sql.Timestamp obj)
    {
        last_update_timestamp = obj;
    }

    public String getLast_update_timestampStr()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestampStr(String obj)
    {
        last_update_timestamp_str = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }

    public String getIs_major()
    {
        return is_major;
    }

    public void setIs_major(String obj)
    {
        is_major = obj;
    }

    public String getCustomer_age() {
		return customer_age;
	}

	public void setCustomer_age(String customer_age) {
		this.customer_age = customer_age;
	}

    public String getIs_finish()
    {
        return is_finish;
    }

    public void setIs_finish(String is_finish)
    {
        this.is_finish = is_finish;
    }

    public boolean getgetIsExcludePKFlag()
    {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cus_customer_head_id", this.wms_cus_customer_head_id);
        paramMap.put("customer_code", this.customer_code);
        paramMap.put("customer_name", this.customer_name);
        paramMap.put("customer_ever_name", this.customer_ever_name);
        paramMap.put("customer_category", this.customer_category);
        paramMap.put("gender", this.gender);
        paramMap.put("max_degree", this.max_degree);
        paramMap.put("birthday", this.birthday);
        paramMap.put("birthday_str", this.birthday_str);
        paramMap.put("id_card", this.id_card);
        paramMap.put("province", this.province);
        paramMap.put("city", this.city);
        paramMap.put("district", this.district);
        paramMap.put("address_more", this.address_more);
        paramMap.put("has_children", this.has_children);
        paramMap.put("children_name", this.children_name);
        paramMap.put("children_address", this.children_address);
        paramMap.put("children_telephone", this.children_telephone);
        paramMap.put("has_married", this.has_married);
        paramMap.put("has_house", this.has_house);
        paramMap.put("current_address_province", this.current_address_province);
        paramMap.put("current_address_city", this.current_address_city);
        paramMap.put("current_address_district", this.current_address_district);
        paramMap.put("current_address_more", this.current_address_more);
        paramMap.put("post_code", this.post_code);
        paramMap.put("fixed_telephone", this.fixed_telephone);
        paramMap.put("mobile_telephone1", this.mobile_telephone1);
        paramMap.put("service_password1", this.service_password1);
        paramMap.put("mobile_telephone2", this.mobile_telephone2);
        paramMap.put("service_password2", this.service_password2);
        paramMap.put("personal_year_income", this.personal_year_income);
        paramMap.put("customer_email", this.customer_email);
        paramMap.put("credit_card_limit", this.credit_card_limit);
        paramMap.put("common_occupants", this.common_occupants);
        paramMap.put("status", this.status);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("is_major", this.is_major);
        paramMap.put("work_stat", this.work_stat);
        paramMap.put("house_stat", this.house_stat);
        paramMap.put("residence_time", this.residence_time);
        paramMap.put("cre_card_num", this.cre_card_num);
        paramMap.put("existing_loan", this.existing_loan);
        paramMap.put("monthly_income", this.monthly_income);
        paramMap.put("resource_of_tel1", this.resource_of_tel1);
        paramMap.put("resource_of_tel2", this.resource_of_tel2);
        paramMap.put("is_real_name_tel1", this.is_real_name_tel1);
        paramMap.put("is_real_name_tel2", this.is_real_name_tel2);
        paramMap.put("real_name_tel1", this.real_name_tel1);
        paramMap.put("real_name_rela1", this.real_name_rela1);
        paramMap.put("real_name_tel2", this.real_name_tel2);
        paramMap.put("real_name_rela2", this.real_name_rela2);
        paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
    }

    /**
     * return the columns map
     */
    public Map<String, Object> getInfoMap()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        this.putInMap(paramMap);
        return paramMap;
    }

    /**
     * remove default value and pk if it is null
     */
    private Map<String, Object> dealWithMap(Map<String, Object> paramMap)
    {
        Set<String> set = new HashSet<String>();
        for (String colName : defaultValColArr)
        {
            set.add(colName);
        }
        for (String colName : pkColArr)
        {
            set.add(colName);
        }
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext())
        {
            String colName = iterator.next();
            if (paramMap.get(colName) == null)
            {
                paramMap.remove(colName);
            }
        }
        return paramMap;
    }
}