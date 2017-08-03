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

public class WmsInvePruductReturn implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_pruduct_return_id;

    private Integer wms_inve_pruduct_category_id;

    private Integer deadline_begin_date;

    private Integer deadline_end_date;

    private java.math.BigDecimal product_interest;

    private java.math.BigDecimal management_fee;

    private String is_enable_return;

    private Integer create_user_id;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_inve_pruduct_return_id" };

    private static String[] columnNameArr = { "wms_inve_pruduct_return_id", "wms_inve_pruduct_category_id",
                                             "deadline_begin_date", "deadline_end_date", "product_interest",
                                             "management_fee", "is_enable_return", "create_user_id",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str", "isExcludePKFlag" };

    public Integer getWms_inve_pruduct_return_id()
    {
        return wms_inve_pruduct_return_id;
    }

    public void setWms_inve_pruduct_return_id(Integer obj)
    {
        wms_inve_pruduct_return_id = obj;
    }

    public Integer getWms_inve_pruduct_category_id()
    {
        return wms_inve_pruduct_category_id;
    }

    public void setWms_inve_pruduct_category_id(Integer obj)
    {
        wms_inve_pruduct_category_id = obj;
    }

    public Integer getDeadline_begin_date()
    {
        return deadline_begin_date;
    }

    public void setDeadline_begin_date(Integer obj)
    {
        deadline_begin_date = obj;
    }

    public Integer getDeadline_end_date()
    {
        return deadline_end_date;
    }

    public void setDeadline_end_date(Integer obj)
    {
        deadline_end_date = obj;
    }

    public java.math.BigDecimal getProduct_interest()
    {
        return product_interest;
    }

    public void setProduct_interest(java.math.BigDecimal obj)
    {
        product_interest = obj;
    }

    public java.math.BigDecimal getManagement_fee()
    {
        return management_fee;
    }

    public void setManagement_fee(java.math.BigDecimal obj)
    {
        management_fee = obj;
    }

    public String getIs_enable_return()
    {
        return is_enable_return;
    }

    public void setIs_enable_return(String obj)
    {
        is_enable_return = obj;
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

    public String getCreate_timestamp_str()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestamp_str(String obj)
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

    public String getLast_update_timestamp_str()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestamp_str(String obj)
    {
        last_update_timestamp_str = obj;
    }

    public boolean getgetIsExcludePKFlag()
    {
        return isExcludePKFlag;
    }

    public void setgetIsExcludePKFlag(boolean obj)
    {
        isExcludePKFlag = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_inve_pruduct_return_id", this.wms_inve_pruduct_return_id);
        paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
        paramMap.put("deadline_begin_date", this.deadline_begin_date);
        paramMap.put("deadline_end_date", this.deadline_end_date);
        paramMap.put("product_interest", this.product_interest);
        paramMap.put("management_fee", this.management_fee);
        paramMap.put("is_enable_return", this.is_enable_return);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
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