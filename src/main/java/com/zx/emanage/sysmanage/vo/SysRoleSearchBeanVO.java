package com.zx.emanage.sysmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class SysRoleSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private String role_name;

    public String getRole_name()
    {
        return role_name;
    }

    public void setRole_name(String role_name)
    {
        this.role_name = role_name;
    }

}