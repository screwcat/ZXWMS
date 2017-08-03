package com.zx.emanage.sysmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class SysLogSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private String user_code;// 账号姓名

    private String user_name;// 姓名

    public String getUser_code()
    {
        return user_code;
    }

    public void setUser_code(String user_code)
    {
        this.user_code = user_code;
    }

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String user_name)
    {
        this.user_name = user_name;
    }

}