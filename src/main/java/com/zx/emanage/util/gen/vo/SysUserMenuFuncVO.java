package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class SysUserMenuFuncVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer user_id;

    private Integer menu_id;

    private Integer func_id;

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

    public Integer getUser_id()
    {
        return user_id;
    }

    public void setUser_id(Integer obj)
    {
        user_id = obj;
    }

    public Integer getMenu_id()
    {
        return menu_id;
    }

    public void setMenu_id(Integer obj)
    {
        menu_id = obj;
    }

    public Integer getFunc_id()
    {
        return func_id;
    }

    public void setFunc_id(Integer obj)
    {
        func_id = obj;
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