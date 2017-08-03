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

public class SysTaskLog implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_sys_task_log_id;

    private String task_name;

    private java.sql.Date log_time;

    private String log_time_str;

    private String task_status;

    private String task_data;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_sys_task_log_id" };

    private static String[] columnNameArr = { "wms_sys_task_log_id", "task_name", "log_time", "log_time_str",
                                             "task_status", "task_data" };

    public Integer getWms_sys_task_log_id()
    {
        return wms_sys_task_log_id;
    }

    public void setWms_sys_task_log_id(Integer obj)
    {
        wms_sys_task_log_id = obj;
    }

    public String getTask_name()
    {
        return task_name;
    }

    public void setTask_name(String obj)
    {
        task_name = obj;
    }

    public java.sql.Date getLog_time()
    {
        return log_time;
    }

    public void setLog_time(java.sql.Date obj)
    {
        log_time = obj;
    }

    public String getLog_timeStr()
    {
        return log_time_str;
    }

    public void setLog_timeStr(String obj)
    {
        log_time_str = obj;
    }

    public String getTask_status()
    {
        return task_status;
    }

    public void setTask_status(String obj)
    {
        task_status = obj;
    }

    public String getTask_data()
    {
        return task_data;
    }

    public void setTask_data(String obj)
    {
        task_data = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_sys_task_log_id", this.wms_sys_task_log_id);
        paramMap.put("task_name", this.task_name);
        paramMap.put("log_time", this.log_time);
        paramMap.put("log_time_str", this.log_time_str);
        paramMap.put("task_status", this.task_status);
        paramMap.put("task_data", this.task_data);
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