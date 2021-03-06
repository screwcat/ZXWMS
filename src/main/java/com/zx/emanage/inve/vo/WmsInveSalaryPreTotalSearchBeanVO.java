package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveSalaryPreTotalSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private Integer dept_id;

    private Integer data_status;

    private String statics_month;

    private Integer menu_id;

    private String dept_manager_name;

    private String dept_ids;

    private Integer last_update_user_id;

    private String data_status_code;

    public Integer getDept_id()
    {
        return dept_id;
    }

    public void setDept_id(Integer dept_id)
    {
        this.dept_id = dept_id;
    }

    public Integer getData_status()
    {
        return data_status;
    }

    public void setData_status(Integer data_status)
    {
        this.data_status = data_status;
    }

    public String getStatics_month()
    {
        return statics_month;
    }

    public void setStatics_month(String statics_month)
    {
        this.statics_month = statics_month;
    }

    public Integer getMenu_id()
    {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id)
    {
        this.menu_id = menu_id;
    }

    public String getDept_manager_name()
    {
        return dept_manager_name;
    }

    public void setDept_manager_name(String dept_manager_name)
    {
        this.dept_manager_name = dept_manager_name;
    }

    public String getDept_ids()
    {
        return dept_ids;
    }

    public void setDept_ids(String dept_ids)
    {
        this.dept_ids = dept_ids;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer last_update_user_id)
    {
        this.last_update_user_id = last_update_user_id;
    }

    public String getData_status_code()
    {
        return data_status_code;
    }

    public void setData_status_code(String data_status_code)
    {
        this.data_status_code = data_status_code;
    }
}