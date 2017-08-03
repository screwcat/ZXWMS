package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInvePosSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    public String enable_flag;

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String enable_flag)
    {
        this.enable_flag = enable_flag;
    }

}