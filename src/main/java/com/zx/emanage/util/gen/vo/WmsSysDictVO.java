package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class WmsSysDictVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer wms_sys_dict_id;

    private String dict_type;

    private String dict_code;

    private String dict_name;

    public Integer getWms_sys_dict_id()
    {
        return wms_sys_dict_id;
    }

    public void setWms_sys_dict_id(Integer obj)
    {
        wms_sys_dict_id = obj;
    }

    public String getDict_type()
    {
        return dict_type;
    }

    public void setDict_type(String obj)
    {
        dict_type = obj;
    }

    public String getDict_code()
    {
        return dict_code;
    }

    public void setDict_code(String obj)
    {
        dict_code = obj;
    }

    public String getDict_name()
    {
        return dict_name;
    }

    public void setDict_name(String obj)
    {
        dict_name = obj;
    }
}