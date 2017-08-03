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

public class ActHiDetail implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id_;

    private String type_;

    private String proc_inst_id_;

    private String execution_id_;

    private String task_id_;

    private String act_inst_id_;

    private String name_;

    private String var_type_;

    private Integer rev_;

    private java.sql.Timestamp time_;

    private String time__str;

    private String bytearray_id_;

    private Double double_;

    private Long long_;

    private String text_;

    private String text2_;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "type_", "proc_inst_id_", "execution_id_", "task_id_",
                                             "act_inst_id_", "name_", "var_type_", "rev_", "time_", "time__str",
                                             "bytearray_id_", "double_", "long_", "text_", "text2_" };

    public String getId_()
    {
        return id_;
    }

    public void setId_(String obj)
    {
        id_ = obj;
    }

    public String getType_()
    {
        return type_;
    }

    public void setType_(String obj)
    {
        type_ = obj;
    }

    public String getProc_inst_id_()
    {
        return proc_inst_id_;
    }

    public void setProc_inst_id_(String obj)
    {
        proc_inst_id_ = obj;
    }

    public String getExecution_id_()
    {
        return execution_id_;
    }

    public void setExecution_id_(String obj)
    {
        execution_id_ = obj;
    }

    public String getTask_id_()
    {
        return task_id_;
    }

    public void setTask_id_(String obj)
    {
        task_id_ = obj;
    }

    public String getAct_inst_id_()
    {
        return act_inst_id_;
    }

    public void setAct_inst_id_(String obj)
    {
        act_inst_id_ = obj;
    }

    public String getName_()
    {
        return name_;
    }

    public void setName_(String obj)
    {
        name_ = obj;
    }

    public String getVar_type_()
    {
        return var_type_;
    }

    public void setVar_type_(String obj)
    {
        var_type_ = obj;
    }

    public Integer getRev_()
    {
        return rev_;
    }

    public void setRev_(Integer obj)
    {
        rev_ = obj;
    }

    public java.sql.Timestamp getTime_()
    {
        return time_;
    }

    public void setTime_(java.sql.Timestamp obj)
    {
        time_ = obj;
    }

    public String getTime_Str()
    {
        return time__str;
    }

    public void setTime_Str(String obj)
    {
        time__str = obj;
    }

    public String getBytearray_id_()
    {
        return bytearray_id_;
    }

    public void setBytearray_id_(String obj)
    {
        bytearray_id_ = obj;
    }

    public Double getDouble_()
    {
        return double_;
    }

    public void setDouble_(Double obj)
    {
        double_ = obj;
    }

    public Long getLong_()
    {
        return long_;
    }

    public void setLong_(Long obj)
    {
        long_ = obj;
    }

    public String getText_()
    {
        return text_;
    }

    public void setText_(String obj)
    {
        text_ = obj;
    }

    public String getText2_()
    {
        return text2_;
    }

    public void setText2_(String obj)
    {
        text2_ = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id_", this.id_);
        paramMap.put("type_", this.type_);
        paramMap.put("proc_inst_id_", this.proc_inst_id_);
        paramMap.put("execution_id_", this.execution_id_);
        paramMap.put("task_id_", this.task_id_);
        paramMap.put("act_inst_id_", this.act_inst_id_);
        paramMap.put("name_", this.name_);
        paramMap.put("var_type_", this.var_type_);
        paramMap.put("rev_", this.rev_);
        paramMap.put("time_", this.time_);
        paramMap.put("time__str", this.time__str);
        paramMap.put("bytearray_id_", this.bytearray_id_);
        paramMap.put("double_", this.double_);
        paramMap.put("long_", this.long_);
        paramMap.put("text_", this.text_);
        paramMap.put("text2_", this.text2_);
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