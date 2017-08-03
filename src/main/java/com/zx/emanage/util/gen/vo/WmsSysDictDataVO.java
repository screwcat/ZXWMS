package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class WmsSysDictDataVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer wms_sys_dict_data_id;

    private String value_code;

    private String value_meaning;

    private Integer wms_sys_dict_id;

    private Integer p_wms_sys_dict_data_id;

    private Integer sort_order;

    public Integer getWms_sys_dict_data_id()
    {
        return wms_sys_dict_data_id;
    }

    public void setWms_sys_dict_data_id(Integer obj)
    {
        wms_sys_dict_data_id = obj;
    }

    public String getValue_code()
    {
        return value_code;
    }

    public void setValue_code(String obj)
    {
        value_code = obj;
    }

    public String getValue_meaning()
    {
        return value_meaning;
    }

    public void setValue_meaning(String obj)
    {
        value_meaning = obj;
    }

    public Integer getWms_sys_dict_id()
    {
        return wms_sys_dict_id;
    }

    public void setWms_sys_dict_id(Integer obj)
    {
        wms_sys_dict_id = obj;
    }

    public Integer getP_wms_sys_dict_data_id()
    {
        return p_wms_sys_dict_data_id;
    }

    public void setP_wms_sys_dict_data_id(Integer obj)
    {
        p_wms_sys_dict_data_id = obj;
    }

    public Integer getSort_order()
    {
        return sort_order;
    }

    public void setSort_order(Integer obj)
    {
        sort_order = obj;
    }
}