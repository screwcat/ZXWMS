package com.zx.emanage.loanreview.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCreCreditHistoricalInfoSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;

    public String customer_name;// 客户姓名

    public String salesman_name_str;// 业务员/工号

    public String apply_date_start;// 申请时间开始

    public String apply_date_end;// 申请时间结束

    public String house_address;// 房产地址

    public String is_need_paging;// 是否分页0否1是

    public String getCustomer_name()
    {
        return customer_name;
    }

    public void setCustomer_name(String customer_name)
    {
        this.customer_name = customer_name;
    }

    public String getSalesman_name_str()
    {
        return salesman_name_str;
    }

    public void setSalesman_name_str(String salesman_name_str)
    {
        this.salesman_name_str = salesman_name_str;
    }

    public String getApply_date_start()
    {
        return apply_date_start;
    }

    public void setApply_date_start(String apply_date_start)
    {
        this.apply_date_start = apply_date_start;
    }

    public String getApply_date_end()
    {
        return apply_date_end;
    }

    public void setApply_date_end(String apply_date_end)
    {
        this.apply_date_end = apply_date_end;
    }

    public String getHouse_address()
    {
        return house_address;
    }

    public void setHouse_address(String house_address)
    {
        this.house_address = house_address;
    }

    public String getIs_need_paging()
    {
        return is_need_paging;
    }

    public void setIs_need_paging(String is_need_paging)
    {
        this.is_need_paging = is_need_paging;
    }


}