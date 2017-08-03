package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class SysDeptVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String dept_name;

    private Integer p_dept_id;

    private String enable_flag;

    private String create_user;

    private String create_timestamp;

    private String last_update_user;

    private String last_update_timestamp;

    private String last_update_remark;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer obj)
    {
        id = obj;
    }

    public String getDept_name()
    {
        return dept_name;
    }

    public void setDept_name(String obj)
    {
        dept_name = obj;
    }

    public Integer getP_dept_id()
    {
        return p_dept_id;
    }

    public void setP_dept_id(Integer obj)
    {
        p_dept_id = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }

    public String getCreate_user()
    {
        return create_user;
    }

    public void setCreate_user(String obj)
    {
        create_user = obj;
    }

    public String getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(String obj)
    {
        create_timestamp = obj;
    }

    public String getLast_update_user()
    {
        return last_update_user;
    }

    public void setLast_update_user(String obj)
    {
        last_update_user = obj;
    }

    public String getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(String obj)
    {
        last_update_timestamp = obj;
    }

    public String getLast_update_remark()
    {
        return last_update_remark;
    }

    public void setLast_update_remark(String obj)
    {
        last_update_remark = obj;
    }
}