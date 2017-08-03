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

public class WmsSysDict implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_sys_dict_id;

    private String dict_type;

    private String dict_code;

    private String dict_name;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_sys_dict_id" };

    private static String[] columnNameArr = { "wms_sys_dict_id", "dict_type", "dict_code", "dict_name" };

    public Integer getWms_sys_dict_id()
    {
        return wms_sys_dict_id;
    }

    public void setWms_sys_dict_id(Integer obj)
    {
        wms_sys_dict_id = obj;
    }

    public String getDict_type()
    {
        return dict_type;
    }

    public void setDict_type(String obj)
    {
        dict_type = obj;
    }

    public String getDict_code()
    {
        return dict_code;
    }

    public void setDict_code(String obj)
    {
        dict_code = obj;
    }

    public String getDict_name()
    {
        return dict_name;
    }

    public void setDict_name(String obj)
    {
        dict_name = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_sys_dict_id", this.wms_sys_dict_id);
        paramMap.put("dict_type", this.dict_type);
        paramMap.put("dict_code", this.dict_code);
        paramMap.put("dict_name", this.dict_name);
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