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

public class ActHiVarinst implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id_;

    private String proc_inst_id_;

    private String execution_id_;

    private String task_id_;

    private String name_;

    private String var_type_;

    private Integer rev_;

    private String bytearray_id_;

    private Double double_;

    private Long long_;

    private String text_;

    private String text2_;

    private java.sql.Timestamp create_time_;

    private String create_time__str;

    private java.sql.Timestamp last_updated_time_;

    private String last_updated_time__str;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "proc_inst_id_", "execution_id_", "task_id_", "name_",
                                             "var_type_", "rev_", "bytearray_id_", "double_", "long_", "text_",
                                             "text2_", "create_time_", "create_time__str", "last_updated_time_",
                                             "last_updated_time__str" };

    public String getId_()
    {
        return id_;
    }

    public void setId_(String obj)
    {
        id_ = obj;
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

    public java.sql.Timestamp getCreate_time_()
    {
        return create_time_;
    }

    public void setCreate_time_(java.sql.Timestamp obj)
    {
        create_time_ = obj;
    }

    public String getCreate_time_Str()
    {
        return create_time__str;
    }

    public void setCreate_time_Str(String obj)
    {
        create_time__str = obj;
    }

    public java.sql.Timestamp getLast_updated_time_()
    {
        return last_updated_time_;
    }

    public void setLast_updated_time_(java.sql.Timestamp obj)
    {
        last_updated_time_ = obj;
    }

    public String getLast_updated_time_Str()
    {
        return last_updated_time__str;
    }

    public void setLast_updated_time_Str(String obj)
    {
        last_updated_time__str = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id_", this.id_);
        paramMap.put("proc_inst_id_", this.proc_inst_id_);
        paramMap.put("execution_id_", this.execution_id_);
        paramMap.put("task_id_", this.task_id_);
        paramMap.put("name_", this.name_);
        paramMap.put("var_type_", this.var_type_);
        paramMap.put("rev_", this.rev_);
        paramMap.put("bytearray_id_", this.bytearray_id_);
        paramMap.put("double_", this.double_);
        paramMap.put("long_", this.long_);
        paramMap.put("text_", this.text_);
        paramMap.put("text2_", this.text2_);
        paramMap.put("create_time_", this.create_time_);
        paramMap.put("create_time__str", this.create_time__str);
        paramMap.put("last_updated_time_", this.last_updated_time_);
        paramMap.put("last_updated_time__str", this.last_updated_time__str);
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