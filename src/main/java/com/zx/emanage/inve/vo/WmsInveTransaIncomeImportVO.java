package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveTransaIncomeImportVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private Integer wms_inve_transa_id;

    private String bill_code;

    private String pay_status;

    private java.sql.Date return_date;

    private Integer last_update_user_id;

    public Integer getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    public void setWms_inve_transa_id(Integer wms_inve_transa_id)
    {
        this.wms_inve_transa_id = wms_inve_transa_id;
    }

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    public String getPay_status()
    {
        return pay_status;
    }

    public void setPay_status(String pay_status)
    {
        this.pay_status = pay_status;
    }

    public java.sql.Date getReturn_date()
    {
        return return_date;
    }

    public void setReturn_date(java.sql.Date return_date)
    {
        this.return_date = return_date;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer last_update_user_id)
    {
        this.last_update_user_id = last_update_user_id;
    }
}