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

public class SysLog implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String unit_name;

    private String user_code;

    private String user_name;

    private String oper_behavior;

    private java.sql.Timestamp oper_timestamp;

    private String oper_timestamp_str;

    private String oper_ip;

    private String oper_type;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id" };

    private static String[] columnNameArr = { "id", "unit_name", "user_code", "user_name", "oper_behavior",
                                             "oper_timestamp", "oper_timestamp_str", "oper_ip", "oper_type" };

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer obj)
    {
        id = obj;
    }

    public String getUnit_name()
    {
        return unit_name;
    }

    public void setUnit_name(String obj)
    {
        unit_name = obj;
    }

    public String getUser_code()
    {
        return user_code;
    }

    public void setUser_code(String obj)
    {
        user_code = obj;
    }

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String obj)
    {
        user_name = obj;
    }

    public String getOper_behavior()
    {
        return oper_behavior;
    }

    public void setOper_behavior(String obj)
    {
        oper_behavior = obj;
    }

    public java.sql.Timestamp getOper_timestamp()
    {
        return oper_timestamp;
    }

    public void setOper_timestamp(java.sql.Timestamp obj)
    {
        oper_timestamp = obj;
    }

    public String getOper_timestampStr()
    {
        return oper_timestamp_str;
    }

    public void setOper_timestampStr(String obj)
    {
        oper_timestamp_str = obj;
    }

    public String getOper_ip()
    {
        return oper_ip;
    }

    public void setOper_ip(String obj)
    {
        oper_ip = obj;
    }

    public String getOper_type()
    {
        return oper_type;
    }

    public void setOper_type(String oper_type)
    {
        this.oper_type = oper_type;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id", this.id);
        paramMap.put("unit_name", this.unit_name);
        paramMap.put("user_code", this.user_code);
        paramMap.put("user_name", this.user_name);
        paramMap.put("oper_behavior", this.oper_behavior);
        paramMap.put("oper_timestamp", this.oper_timestamp);
        paramMap.put("oper_timestamp_str", this.oper_timestamp_str);
        paramMap.put("oper_ip", this.oper_ip);
        paramMap.put("oper_type", this.oper_type);
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