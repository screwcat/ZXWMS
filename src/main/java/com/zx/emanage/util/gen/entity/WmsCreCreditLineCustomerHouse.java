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

public class WmsCreCreditLineCustomerHouse implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_customer_hous_id;

    private String address;

    private Integer floor_num;

    private java.math.BigDecimal built_area;

    private String house_type;

    private String house_right;

    private String customer_rela;

    private String status;

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
    private static String[] pkColArr = { "wms_cre_credit_line_customer_hous_id" };

    private static String[] columnNameArr = { "wms_cre_credit_line_customer_hous_id", "address", "floor_num",
                                             "built_area", "house_type", "house_right", "customer_rela", "status",
                                             "wms_cre_credit_head_id", "create_user_id", "create_user_name",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str", "enable_flag" };

    public Integer getWms_cre_credit_line_customer_hous_id()
    {
        return wms_cre_credit_line_customer_hous_id;
    }

    public void setWms_cre_credit_line_customer_hous_id(Integer obj)
    {
        wms_cre_credit_line_customer_hous_id = obj;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String obj)
    {
        address = obj;
    }

    public Integer getFloor_num()
    {
        return floor_num;
    }

    public void setFloor_num(Integer obj)
    {
        floor_num = obj;
    }

    public java.math.BigDecimal getBuilt_area()
    {
        return built_area;
    }

    public void setBuilt_area(java.math.BigDecimal obj)
    {
        built_area = obj;
    }

    public String getHouse_type()
    {
        return house_type;
    }

    public void setHouse_type(String obj)
    {
        house_type = obj;
    }

    public String getHouse_right()
    {
        return house_right;
    }

    public void setHouse_right(String obj)
    {
        house_right = obj;
    }

    public String getCustomer_rela()
    {
        return customer_rela;
    }

    public void setCustomer_rela(String obj)
    {
        customer_rela = obj;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String obj)
    {
        status = obj;
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
        paramMap.put("wms_cre_credit_line_customer_hous_id", this.wms_cre_credit_line_customer_hous_id);
        paramMap.put("address", this.address);
        paramMap.put("floor_num", this.floor_num);
        paramMap.put("built_area", this.built_area);
        paramMap.put("house_type", this.house_type);
        paramMap.put("house_right", this.house_right);
        paramMap.put("customer_rela", this.customer_rela);
        paramMap.put("status", this.status);
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