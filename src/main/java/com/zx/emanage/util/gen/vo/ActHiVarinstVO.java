package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class ActHiVarinstVO implements Serializable
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

    private String create_time_;

    private String last_updated_time_;

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

    public String getCreate_time_()
    {
        return create_time_;
    }

    public void setCreate_time_(String obj)
    {
        create_time_ = obj;
    }

    public String getLast_updated_time_()
    {
        return last_updated_time_;
    }

    public void setLast_updated_time_(String obj)
    {
        last_updated_time_ = obj;
    }
}