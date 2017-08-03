package com.zx.emanage.sysmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class SysUserSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private String user_realname;

    private String user_code;

    public String getUser_code()
    {
        return user_code;
    }

    public void setUser_code(String user_code)
    {
        this.user_code = user_code;
    }

    public String getUser_realname()
    {
        return user_realname;
    }

    public void setUser_realname(String user_realname)
    {
        this.user_realname = user_realname;
    }

}