package com.zx.emanage.util.gen.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.math.BigDecimal;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsCusCustomerLineCompany implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cus_customer_line_company_id;

    private Integer wms_cus_customer_id;

    private String comp_full_name;

    private String comp_tel;

    private String compt_address_province;

    private String comp_address_city;

    private String comp_address_district;

    private String comp_address_more;

    private java.sql.Date found_date;

    private String found_date_str;

    private String comp_kind;

    private String place_of_comp;

    private String comp_industry;

    private Integer create_user_id;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cus_customer_line_company_id" };

    private static String[] columnNameArr = { "wms_cus_customer_line_company_id", "wms_cus_customer_id",
                                             "comp_full_name", "comp_tel", "compt_address_province",
                                             "comp_address_city", "comp_address_district", "comp_address_more",
                                             "found_date", "found_date_str", "comp_kind", "place_of_comp",
                                             "comp_industry", "create_user_id", "create_timestamp",
                                             "create_timestamp_str", "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag" };

    public Integer getWms_cus_customer_line_company_id()
    {
        return wms_cus_customer_line_company_id;
    }

    public void setWms_cus_customer_line_company_id(Integer obj)
    {
        wms_cus_customer_line_company_id = obj;
    }

    public Integer getWms_cus_customer_id()
    {
        return wms_cus_customer_id;
    }

    public void setWms_cus_customer_id(Integer obj)
    {
        wms_cus_customer_id = obj;
    }

    public String getComp_full_name()
    {
        return comp_full_name;
    }

    public void setComp_full_name(String obj)
    {
        comp_full_name = obj;
    }

    public String getComp_tel()
    {
        return comp_tel;
    }

    public void setComp_tel(String obj)
    {
        comp_tel = obj;
    }

    public String getCompt_address_province()
    {
        return compt_address_province;
    }

    public void setCompt_address_province(String obj)
    {
        compt_address_province = obj;
    }

    public String getComp_address_city()
    {
        return comp_address_city;
    }

    public void setComp_address_city(String obj)
    {
        comp_address_city = obj;
    }

    public String getComp_address_district()
    {
        return comp_address_district;
    }

    public void setComp_address_district(String obj)
    {
        comp_address_district = obj;
    }

    public String getComp_address_more()
    {
        return comp_address_more;
    }

    public void setComp_address_more(String obj)
    {
        comp_address_more = obj;
    }

    public java.sql.Date getFound_date()
    {
        return found_date;
    }

    public void setFound_date(java.sql.Date obj)
    {
        found_date = obj;
    }

    public String getFound_dateStr()
    {
        return found_date_str;
    }

    public void setFound_dateStr(String obj)
    {
        found_date_str = obj;
    }

    public String getComp_kind()
    {
        return comp_kind;
    }

    public void setComp_kind(String obj)
    {
        comp_kind = obj;
    }

    public String getPlace_of_comp()
    {
        return place_of_comp;
    }

    public void setPlace_of_comp(String obj)
    {
        place_of_comp = obj;
    }

    public String getComp_industry()
    {
        return comp_industry;
    }

    public void setComp_industry(String obj)
    {
        comp_industry = obj;
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

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cus_customer_line_company_id", this.wms_cus_customer_line_company_id);
        paramMap.put("wms_cus_customer_id", this.wms_cus_customer_id);
        paramMap.put("comp_full_name", this.comp_full_name);
        paramMap.put("comp_tel", this.comp_tel);
        paramMap.put("compt_address_province", this.compt_address_province);
        paramMap.put("comp_address_city", this.comp_address_city);
        paramMap.put("comp_address_district", this.comp_address_district);
        paramMap.put("comp_address_more", this.comp_address_more);
        paramMap.put("found_date", this.found_date);
        paramMap.put("found_date_str", this.found_date_str);
        paramMap.put("comp_kind", this.comp_kind);
        paramMap.put("place_of_comp", this.place_of_comp);
        paramMap.put("comp_industry", this.comp_industry);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
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