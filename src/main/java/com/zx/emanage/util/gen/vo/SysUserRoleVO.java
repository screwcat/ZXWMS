package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class SysUserRoleVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer user_id;

    private Integer role_id;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer obj)
    {
        id = obj;
    }

    public Integer getUser_id()
    {
        return user_id;
    }

    public void setUser_id(Integer obj)
    {
        user_id = obj;
    }

    public Integer getRole_id()
    {
        return role_id;
    }

    public void setRole_id(Integer obj)
    {
        role_id = obj;
    }
}