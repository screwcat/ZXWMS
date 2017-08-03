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

public class WmsCreCreditLineCustomerVehicle implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_customer_vehicle_id;

    private String license_plate;

    private String brand;

    private String use_year;

    private java.math.BigDecimal mileage;

    private String property_owner;

    private String customer_rela;

    private String user_name;

    private Integer wms_cre_credit_head_id;

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
    private static String[] pkColArr = { "wms_cre_credit_line_customer_vehicle_id" };

    private static String[] columnNameArr = { "wms_cre_credit_line_customer_vehicle_id", "license_plate", "brand",
                                             "use_year", "mileage", "property_owner", "customer_rela", "user_name",
                                             "wms_cre_credit_head_id", "create_user_id", "create_user_name",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str", "enable_flag" };

    public Integer getWms_cre_credit_line_customer_vehicle_id()
    {
        return wms_cre_credit_line_customer_vehicle_id;
    }

    public void setWms_cre_credit_line_customer_vehicle_id(Integer obj)
    {
        wms_cre_credit_line_customer_vehicle_id = obj;
    }

    public String getLicense_plate()
    {
        return license_plate;
    }

    public void setLicense_plate(String obj)
    {
        license_plate = obj;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String obj)
    {
        brand = obj;
    }

    public String getUse_year()
    {
        return use_year;
    }

    public void setUse_year(String obj)
    {
        use_year = obj;
    }

    public java.math.BigDecimal getMileage()
    {
        return mileage;
    }

    public void setMileage(java.math.BigDecimal obj)
    {
        mileage = obj;
    }

    public String getProperty_owner()
    {
        return property_owner;
    }

    public void setProperty_owner(String obj)
    {
        property_owner = obj;
    }

    public String getCustomer_rela()
    {
        return customer_rela;
    }

    public void setCustomer_rela(String obj)
    {
        customer_rela = obj;
    }

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String obj)
    {
        user_name = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
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
        paramMap.put("wms_cre_credit_line_customer_vehicle_id", this.wms_cre_credit_line_customer_vehicle_id);
        paramMap.put("license_plate", this.license_plate);
        paramMap.put("brand", this.brand);
        paramMap.put("use_year", this.use_year);
        paramMap.put("mileage", this.mileage);
        paramMap.put("property_owner", this.property_owner);
        paramMap.put("customer_rela", this.customer_rela);
        paramMap.put("user_name", this.user_name);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
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