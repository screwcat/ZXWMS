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

public class WmsSysSequence implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_sys_sequence_id;

    private String sequence_code;

    private String sequence_meaning;

    private Long sequence_value;

    private java.sql.Date update_date;

    private String update_date_str;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_sys_sequence_id" };

    private static String[] columnNameArr = { "wms_sys_sequence_id", "sequence_code", "sequence_meaning",
                                             "sequence_value", "update_date", "update_date_str" };

    public Integer getWms_sys_sequence_id()
    {
        return wms_sys_sequence_id;
    }

    public void setWms_sys_sequence_id(Integer obj)
    {
        wms_sys_sequence_id = obj;
    }

    public String getSequence_code()
    {
        return sequence_code;
    }

    public void setSequence_code(String obj)
    {
        sequence_code = obj;
    }

    public String getSequence_meaning()
    {
        return sequence_meaning;
    }

    public void setSequence_meaning(String obj)
    {
        sequence_meaning = obj;
    }

    public Long getSequence_value()
    {
        return sequence_value;
    }

    public void setSequence_value(Long obj)
    {
        sequence_value = obj;
    }

    public java.sql.Date getUpdate_date()
    {
        return update_date;
    }

    public void setUpdate_date(java.sql.Date obj)
    {
        update_date = obj;
    }

    public String getUpdate_dateStr()
    {
        return update_date_str;
    }

    public void setUpdate_dateStr(String obj)
    {
        update_date_str = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_sys_sequence_id", this.wms_sys_sequence_id);
        paramMap.put("sequence_code", this.sequence_code);
        paramMap.put("sequence_meaning", this.sequence_meaning);
        paramMap.put("sequence_value", this.sequence_value);
        paramMap.put("update_date", this.update_date);
        paramMap.put("update_date_str", this.update_date_str);
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