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

public class WmsCreCustomerChangeLineHouseinfo implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_customer_change_line_houseinfo_id;

    private java.sql.Date house_buy_date;

    private String house_buy_date_str;

    private Double house_buy_money;

    private Double house_building_area;

    private String house_address_province;

    private String house_address_city;

    private String house_address_district;

    private String house_address_more;

    private String house_post_code;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private Integer create_user_id;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private String house_address_street;

    private String is_supp_house_card;

    private String house_type;

    private String house_no;

    private String house_vol_no;

    private String community_name;
    
    private String houses_number;  
    
    private String city;
    
    //是否复式
    private String is_compound;
    
    //房屋房龄   
    private String house_age;
    
    //备注
    private String house_remark;
    
    private String is_claim;
    
    private Integer claim_user_id;
    
    /**
	 * @return the is_claim
	 */
	public String getIs_claim() {
		return is_claim;
	}

	/**
	 * @param is_claim the is_claim to set
	 */
	public void setIs_claim(String is_claim) {
		this.is_claim = is_claim;
	}

	/**
	 * @return the claim_user_id
	 */
	public Integer getClaim_user_id() {
		return claim_user_id;
	}

	/**
	 * @param claim_user_id the claim_user_id to set
	 */
	public void setClaim_user_id(Integer claim_user_id) {
		this.claim_user_id = claim_user_id;
	}

	/**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_customer_change_line_houseinfo_id" };

    private static String[] columnNameArr = { "wms_cre_customer_change_line_houseinfo_id", "house_buy_date",
                                             "house_buy_date_str", "house_buy_money", "house_building_area",
                                             "house_address_province", "house_address_city", "house_address_district",
                                             "house_address_more", "house_post_code",
                                             "wms_cre_credit_line_customer_change_head_id", "create_user_id",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str", "enable_flag",
                                             "house_address_street", "is_supp_house_card", "house_type", "house_no",
                                             "house_vol_no", "community_name" };

    public Integer getWms_cre_customer_change_line_houseinfo_id()
    {
        return wms_cre_customer_change_line_houseinfo_id;
    }

    public void setWms_cre_customer_change_line_houseinfo_id(Integer obj)
    {
        wms_cre_customer_change_line_houseinfo_id = obj;
    }

    public java.sql.Date getHouse_buy_date()
    {
        return house_buy_date;
    }

    public void setHouse_buy_date(java.sql.Date obj)
    {
        house_buy_date = obj;
    }

    public String getHouse_buy_dateStr()
    {
        return house_buy_date_str;
    }

    public void setHouse_buy_dateStr(String obj)
    {
        house_buy_date_str = obj;
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

    public String getHouse_address_street()
    {
        return house_address_street;
    }

    public void setHouse_address_street(String obj)
    {
        house_address_street = obj;
    }

    public String getIs_supp_house_card()
    {
        return is_supp_house_card;
    }

    public void setIs_supp_house_card(String is_supp_house_card)
    {
        this.is_supp_house_card = is_supp_house_card;
    }

    public String getHouse_type()
    {
        return house_type;
    }

    public void setHouse_type(String house_type)
    {
        this.house_type = house_type;
    }

    public String getHouse_no()
    {
        return house_no;
    }

    public void setHouse_no(String house_no)
    {
        this.house_no = house_no;
    }

    public String getHouse_vol_no()
    {
        return house_vol_no;
    }

    public void setHouse_vol_no(String house_vol_no)
    {
        this.house_vol_no = house_vol_no;
    }

    public String getCommunity_name()
    {
        return community_name;
    }

    public void setCommunity_name(String community_name)
    {
        this.community_name = community_name;
    }

    public String getHouses_number() {
		return houses_number;
	}

	public void setHouses_number(String houses_number) {
		this.houses_number = houses_number;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	/**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_customer_change_line_houseinfo_id", this.wms_cre_customer_change_line_houseinfo_id);
        paramMap.put("house_buy_date", this.house_buy_date);
        paramMap.put("house_buy_date_str", this.house_buy_date_str);
        paramMap.put("house_buy_money", this.house_buy_money);
        paramMap.put("house_building_area", this.house_building_area);
        paramMap.put("house_address_province", this.house_address_province);
        paramMap.put("house_address_city", this.house_address_city);
        paramMap.put("house_address_district", this.house_address_district);
        paramMap.put("house_address_more", this.house_address_more);
        paramMap.put("house_post_code", this.house_post_code);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("house_address_street", this.house_address_street);
        paramMap.put("is_supp_house_card", this.is_supp_house_card);
        paramMap.put("house_type", this.house_type);
        paramMap.put("house_no", this.house_no);
        paramMap.put("house_vol_no", this.house_vol_no);
        paramMap.put("community_name", this.community_name);
        paramMap.put("houses_number", this.houses_number);
        paramMap.put("city", this.city);
        paramMap.put("is_compound", this.is_compound);
        paramMap.put("house_age", this.house_age);
        paramMap.put("house_remark", this.house_remark);
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

    public String getIs_compound() {
        return is_compound;
    }

    public void setIs_compound(String is_compound) {
        this.is_compound = is_compound;
    }

    public String getHouse_age() {
        return house_age;
    }

    public void setHouse_age(String house_age) {
        this.house_age = house_age;
    }

    public String getHouse_remark() {
        return house_remark;
    }

    public void setHouse_remark(String house_remark) {
        this.house_remark = house_remark;
    }

    
    
}