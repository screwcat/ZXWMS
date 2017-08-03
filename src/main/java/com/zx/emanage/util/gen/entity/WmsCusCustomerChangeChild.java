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

public class WmsCusCustomerChangeChild implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cus_customer_change_child_id;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private String child_name;

    private String child_telephone;

    private String school_work_name;

    private String house_address_province;

    private String house_address_city;

    private String house_address_district;

    private String house_address_more;

    private Integer create_user_id;

    private String create_user_name;

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
    private static String[] pkColArr = { "wms_cus_customer_change_child_id" };

    private static String[] columnNameArr = { "wms_cus_customer_change_child_id",
                                             "wms_cre_credit_line_customer_change_head_id", "child_name",
                                             "child_telephone", "school_work_name", "house_address_province",
                                             "house_address_city", "house_address_district", "house_address_more",
                                             "create_user_id", "create_user_name", "create_timestamp",
                                             "create_timestamp_str", "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag" };

    public Integer getWms_cus_customer_change_child_id()
    {
        return wms_cus_customer_change_child_id;
    }

    public void setWms_cus_customer_change_child_id(Integer obj)
    {
        wms_cus_customer_change_child_id = obj;
    }

    public Integer getWms_cre_credit_line_customer_change_head_id()
    {
        return wms_cre_credit_line_customer_change_head_id;
    }

    public void setWms_cre_credit_line_customer_change_head_id(Integer obj)
    {
        wms_cre_credit_line_customer_change_head_id = obj;
    }

    public String getChild_name()
    {
        return child_name;
    }

    public void setChild_name(String obj)
    {
        child_name = obj;
    }

    public String getChild_telephone()
    {
        return child_telephone;
    }

    public void setChild_telephone(String obj)
    {
        child_telephone = obj;
    }

    public String getSchool_work_name()
    {
        return school_work_name;
    }

    public void setSchool_work_name(String obj)
    {
        school_work_name = obj;
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

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cus_customer_change_child_id", this.wms_cus_customer_change_child_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("child_name", this.child_name);
        paramMap.put("child_telephone", this.child_telephone);
        paramMap.put("school_work_name", this.school_work_name);
        paramMap.put("house_address_province", this.house_address_province);
        paramMap.put("house_address_city", this.house_address_city);
        paramMap.put("house_address_district", this.house_address_district);
        paramMap.put("house_address_more", this.house_address_more);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
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