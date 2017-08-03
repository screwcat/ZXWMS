package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class SysLogVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String unit_name;

    private String user_code;

    private String user_name;

    private String oper_behavior;

    private String oper_timestamp;

    private String oper_ip;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer obj)
    {
        id = obj;
    }

    public String getUnit_name()
    {
        return unit_name;
    }

    public void setUnit_name(String obj)
    {
        unit_name = obj;
    }

    public String getUser_code()
    {
        return user_code;
    }

    public void setUser_code(String obj)
    {
        user_code = obj;
    }

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String obj)
    {
        user_name = obj;
    }

    public String getOper_behavior()
    {
        return oper_behavior;
    }

    public void setOper_behavior(String obj)
    {
        oper_behavior = obj;
    }

    public String getOper_timestamp()
    {
        return oper_timestamp;
    }

    public void setOper_timestamp(String obj)
    {
        oper_timestamp = obj;
    }

    public String getOper_ip()
    {
        return oper_ip;
    }

    public void setOper_ip(String obj)
    {
        oper_ip = obj;
    }
}