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

public class SysRole implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String role_name;

    private String enable_flag;

    private int create_user_id;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private int last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String last_update_remark;

    private String is_workflow;

    private String work_flow_id;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id" };

    private static String[] columnNameArr = { "id", "role_name", "enable_flag", "create_user", "create_timestamp",
                                             "create_timestamp_str", "last_update_user", "last_update_timestamp",
                                             "last_update_timestamp_str", "last_update_remark", "is_workflow",
                                             "work_flow_id" };

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer obj)
    {
        id = obj;
    }

    public String getRole_name()
    {
        return role_name;
    }

    public void setRole_name(String obj)
    {
        role_name = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }

    public int getCreate_user()
    {
        return create_user_id;
    }

    public void setCreate_user(int obj)
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

    public String getCreate_timestampStr()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestampStr(String obj)
    {
        create_timestamp_str = obj;
    }

    public int getLast_update_user()
    {
        return last_update_user_id;
    }

    public void setLast_update_user(int obj)
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

    public String getLast_update_remark()
    {
        return last_update_remark;
    }

    public void setLast_update_remark(String obj)
    {
        last_update_remark = obj;
    }

    public String getIs_workflow()
    {
        return is_workflow;
    }

    public void setIs_workflow(String obj)
    {
        is_workflow = obj;
    }

    public String getWork_flow_id()
    {
        return work_flow_id;
    }

    public void setWork_flow_id(String obj)
    {
        work_flow_id = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id", this.id);
        paramMap.put("role_name", this.role_name);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("create_user", this.create_user_id);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("last_update_remark", this.last_update_remark);
        paramMap.put("is_workflow", this.is_workflow);
        paramMap.put("work_flow_id", this.work_flow_id);
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