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

public class WmsSysProperty implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_sys_property_id;

    private String property_meaning;

    private String property_value;

    private java.sql.Date update_date;

    private String update_date_str;

    private String property_value_caps;

    private String cre_type;

    private Integer cre_loan_type;

    private String property_column;

    private String region_number;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_sys_property_id" };

    private static String[] columnNameArr = { "wms_sys_property_id", "property_meaning", "property_value",
                                             "update_date", "update_date_str", "property_value_caps", "cre_type",
                                             "cre_loan_type", "property_column", "region_number", "isExcludePKFlag" };

    public Integer getWms_sys_property_id()
    {
        return wms_sys_property_id;
    }

    public void setWms_sys_property_id(Integer obj)
    {
        wms_sys_property_id = obj;
    }

    public String getProperty_meaning()
    {
        return property_meaning;
    }

    public void setProperty_meaning(String obj)
    {
        property_meaning = obj;
    }

    public String getProperty_value()
    {
        return property_value;
    }

    public void setProperty_value(String obj)
    {
        property_value = obj;
    }

    public java.sql.Date getUpdate_date()
    {
        return update_date;
    }

    public void setUpdate_date(java.sql.Date obj)
    {
        update_date = obj;
    }

    public String getUpdate_date_str()
    {
        return update_date_str;
    }

    public void setUpdate_date_str(String obj)
    {
        update_date_str = obj;
    }

    public String getProperty_value_caps()
    {
        return property_value_caps;
    }

    public void setProperty_value_caps(String obj)
    {
        property_value_caps = obj;
    }

    public String getCre_type()
    {
        return cre_type;
    }

    public void setCre_type(String obj)
    {
        cre_type = obj;
    }

    public Integer getCre_loan_type()
    {
        return cre_loan_type;
    }

    public void setCre_loan_type(Integer obj)
    {
        cre_loan_type = obj;
    }

    public String getProperty_column()
    {
        return property_column;
    }

    public void setProperty_column(String obj)
    {
        property_column = obj;
    }

    public String getRegion_number()
    {
        return region_number;
    }

    public void setRegion_number(String obj)
    {
        region_number = obj;
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
        paramMap.put("wms_sys_property_id", this.wms_sys_property_id);
        paramMap.put("property_meaning", this.property_meaning);
        paramMap.put("property_value", this.property_value);
        paramMap.put("update_date", this.update_date);
        paramMap.put("update_date_str", this.update_date_str);
        paramMap.put("property_value_caps", this.property_value_caps);
        paramMap.put("cre_type", this.cre_type);
        paramMap.put("cre_loan_type", this.cre_loan_type);
        paramMap.put("property_column", this.property_column);
        paramMap.put("region_number", this.region_number);
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