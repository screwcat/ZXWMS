package com.zx.emanage.sysmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class SysUserRoleSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private Integer user_id;

    public Integer getUser_id()
    {
        return user_id;
    }

    public void setUser_id(Integer user_id)
    {
        this.user_id = user_id;
    }

}