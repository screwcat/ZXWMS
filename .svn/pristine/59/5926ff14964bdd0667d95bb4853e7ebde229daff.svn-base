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

public class SysTaskMark implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_sys_task_mark_id;

    private String task_type;

    private java.sql.Timestamp task_begin_time;

    private String task_begin_time_str;

    private java.sql.Timestamp task_end_time;

    private String task_end_time_str;

    private String task_status;

    private String task_describe;

    private java.sql.Date task_date;

    private String task_date_str;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_sys_task_mark_id" };

    private static String[] columnNameArr = { "wms_sys_task_mark_id", "task_type", "task_begin_time",
                                             "task_begin_time_str", "task_end_time", "task_end_time_str",
                                             "task_status", "task_describe", "task_date", "task_date_str" };

    public Integer getWms_sys_task_mark_id()
    {
        return wms_sys_task_mark_id;
    }

    public void setWms_sys_task_mark_id(Integer obj)
    {
        wms_sys_task_mark_id = obj;
    }

    public String getTask_type()
    {
        return task_type;
    }

    public void setTask_type(String obj)
    {
        task_type = obj;
    }

    public java.sql.Timestamp getTask_begin_time()
    {
        return task_begin_time;
    }

    public void setTask_begin_time(java.sql.Timestamp obj)
    {
        task_begin_time = obj;
    }

    public String getTask_begin_timeStr()
    {
        return task_begin_time_str;
    }

    public void setTask_begin_timeStr(String obj)
    {
        task_begin_time_str = obj;
    }

    public java.sql.Timestamp getTask_end_time()
    {
        return task_end_time;
    }

    public void setTask_end_time(java.sql.Timestamp obj)
    {
        task_end_time = obj;
    }

    public String getTask_end_timeStr()
    {
        return task_end_time_str;
    }

    public void setTask_end_timeStr(String obj)
    {
        task_end_time_str = obj;
    }

    public String getTask_status()
    {
        return task_status;
    }

    public void setTask_status(String obj)
    {
        task_status = obj;
    }

    public String getTask_describe()
    {
        return task_describe;
    }

    public void setTask_describe(String obj)
    {
        task_describe = obj;
    }

    public java.sql.Date getTask_date()
    {
        return task_date;
    }

    public void setTask_date(java.sql.Date obj)
    {
        task_date = obj;
    }

    public String getTask_dateStr()
    {
        return task_date_str;
    }

    public void setTask_dateStr(String obj)
    {
        task_date_str = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_sys_task_mark_id", this.wms_sys_task_mark_id);
        paramMap.put("task_type", this.task_type);
        paramMap.put("task_begin_time", this.task_begin_time);
        paramMap.put("task_begin_time_str", this.task_begin_time_str);
        paramMap.put("task_end_time", this.task_end_time);
        paramMap.put("task_end_time_str", this.task_end_time_str);
        paramMap.put("task_status", this.task_status);
        paramMap.put("task_describe", this.task_describe);
        paramMap.put("task_date", this.task_date);
        paramMap.put("task_date_str", this.task_date_str);
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