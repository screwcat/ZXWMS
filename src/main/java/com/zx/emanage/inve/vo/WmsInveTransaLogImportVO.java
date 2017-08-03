package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveTransaLogImportVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private Integer wms_inve_transa_log_id;

    private Integer wms_inve_transa_id;

    private java.math.BigDecimal product_account;

    private java.math.BigDecimal product_interest_account;

    private String operate_item;

    private java.sql.Date change_date;

    private String change_date_str;

    private String remark;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String bill_code;

    public Integer getWms_inve_transa_log_id()
    {
        return wms_inve_transa_log_id;
    }

    public void setWms_inve_transa_log_id(Integer wms_inve_transa_log_id)
    {
        this.wms_inve_transa_log_id = wms_inve_transa_log_id;
    }

    public Integer getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    public void setWms_inve_transa_id(Integer wms_inve_transa_id)
    {
        this.wms_inve_transa_id = wms_inve_transa_id;
    }

    public java.math.BigDecimal getProduct_account()
    {
        return product_account;
    }

    public void setProduct_account(java.math.BigDecimal product_account)
    {
        this.product_account = product_account;
    }

    public java.math.BigDecimal getProduct_interest_account()
    {
        return product_interest_account;
    }

    public void setProduct_interest_account(java.math.BigDecimal product_interest_account)
    {
        this.product_interest_account = product_interest_account;
    }

    public String getOperate_item()
    {
        return operate_item;
    }

    public void setOperate_item(String operate_item)
    {
        this.operate_item = operate_item;
    }

    public java.sql.Date getChange_date()
    {
        return change_date;
    }

    public void setChange_date(java.sql.Date change_date)
    {
        this.change_date = change_date;
    }

    public String getChange_date_str()
    {
        return change_date_str;
    }

    public void setChange_date_str(String change_date_str)
    {
        this.change_date_str = change_date_str;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer create_user_id)
    {
        this.create_user_id = create_user_id;
    }

    public String getCreate_user_name()
    {
        return create_user_name;
    }

    public void setCreate_user_name(String create_user_name)
    {
        this.create_user_name = create_user_name;
    }

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp create_timestamp)
    {
        this.create_timestamp = create_timestamp;
    }

    public String getCreate_timestamp_str()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestamp_str(String create_timestamp_str)
    {
        this.create_timestamp_str = create_timestamp_str;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer last_update_user_id)
    {
        this.last_update_user_id = last_update_user_id;
    }

    public java.sql.Timestamp getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(java.sql.Timestamp last_update_timestamp)
    {
        this.last_update_timestamp = last_update_timestamp;
    }

    public String getLast_update_timestamp_str()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestamp_str(String last_update_timestamp_str)
    {
        this.last_update_timestamp_str = last_update_timestamp_str;
    }

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }
}