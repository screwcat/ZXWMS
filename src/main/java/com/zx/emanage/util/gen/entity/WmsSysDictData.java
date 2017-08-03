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

public class WmsSysDictData implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_sys_dict_data_id;

    private String value_code;

    private String value_meaning;

    private Integer wms_sys_dict_id;

    private Integer p_wms_sys_dict_data_id;

    private Integer sort_order;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_sys_dict_data_id" };

    private static String[] columnNameArr = { "wms_sys_dict_data_id", "value_code", "value_meaning", "wms_sys_dict_id",
                                             "p_wms_sys_dict_data_id", "sort_order" };

    public Integer getWms_sys_dict_data_id()
    {
        return wms_sys_dict_data_id;
    }

    public void setWms_sys_dict_data_id(Integer obj)
    {
        wms_sys_dict_data_id = obj;
    }

    public String getValue_code()
    {
        return value_code;
    }

    public void setValue_code(String obj)
    {
        value_code = obj;
    }

    public String getValue_meaning()
    {
        return value_meaning;
    }

    public void setValue_meaning(String obj)
    {
        value_meaning = obj;
    }

    public Integer getWms_sys_dict_id()
    {
        return wms_sys_dict_id;
    }

    public void setWms_sys_dict_id(Integer obj)
    {
        wms_sys_dict_id = obj;
    }

    public Integer getP_wms_sys_dict_data_id()
    {
        return p_wms_sys_dict_data_id;
    }

    public void setP_wms_sys_dict_data_id(Integer obj)
    {
        p_wms_sys_dict_data_id = obj;
    }

    public Integer getSort_order()
    {
        return sort_order;
    }

    public void setSort_order(Integer obj)
    {
        sort_order = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_sys_dict_data_id", this.wms_sys_dict_data_id);
        paramMap.put("value_code", this.value_code);
        paramMap.put("value_meaning", this.value_meaning);
        paramMap.put("wms_sys_dict_id", this.wms_sys_dict_id);
        paramMap.put("p_wms_sys_dict_data_id", this.p_wms_sys_dict_data_id);
        paramMap.put("sort_order", this.sort_order);
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