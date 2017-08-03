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

public class ActHiIdentitylink implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id_;

    private String group_id_;

    private String type_;

    private String user_id_;

    private String task_id_;

    private String proc_inst_id_;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "group_id_", "type_", "user_id_", "task_id_", "proc_inst_id_" };

    public String getId_()
    {
        return id_;
    }

    public void setId_(String obj)
    {
        id_ = obj;
    }

    public String getGroup_id_()
    {
        return group_id_;
    }

    public void setGroup_id_(String obj)
    {
        group_id_ = obj;
    }

    public String getType_()
    {
        return type_;
    }

    public void setType_(String obj)
    {
        type_ = obj;
    }

    public String getUser_id_()
    {
        return user_id_;
    }

    public void setUser_id_(String obj)
    {
        user_id_ = obj;
    }

    public String getTask_id_()
    {
        return task_id_;
    }

    public void setTask_id_(String obj)
    {
        task_id_ = obj;
    }

    public String getProc_inst_id_()
    {
        return proc_inst_id_;
    }

    public void setProc_inst_id_(String obj)
    {
        proc_inst_id_ = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id_", this.id_);
        paramMap.put("group_id_", this.group_id_);
        paramMap.put("type_", this.type_);
        paramMap.put("user_id_", this.user_id_);
        paramMap.put("task_id_", this.task_id_);
        paramMap.put("proc_inst_id_", this.proc_inst_id_);
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