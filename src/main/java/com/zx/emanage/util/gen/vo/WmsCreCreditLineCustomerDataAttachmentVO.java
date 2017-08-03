package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class WmsCreCreditLineCustomerDataAttachmentVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_customer_data_attachment_id;

    private String data_type_name;

    private String data_detail_name;

    private String attachment_old_name;

    private String attachment_new_name;

    private String attachment_address;

    private String attachment_type;

    private Integer wms_cre_credit_head_id;

    public Integer getWms_cre_credit_line_customer_data_attachment_id()
    {
        return wms_cre_credit_line_customer_data_attachment_id;
    }

    public void setWms_cre_credit_line_customer_data_attachment_id(Integer obj)
    {
        wms_cre_credit_line_customer_data_attachment_id = obj;
    }

    public String getData_type_name()
    {
        return data_type_name;
    }

    public void setData_type_name(String obj)
    {
        data_type_name = obj;
    }

    public String getData_detail_name()
    {
        return data_detail_name;
    }

    public void setData_detail_name(String obj)
    {
        data_detail_name = obj;
    }

    public String getAttachment_old_name()
    {
        return attachment_old_name;
    }

    public void setAttachment_old_name(String obj)
    {
        attachment_old_name = obj;
    }

    public String getAttachment_new_name()
    {
        return attachment_new_name;
    }

    public void setAttachment_new_name(String obj)
    {
        attachment_new_name = obj;
    }

    public String getAttachment_address()
    {
        return attachment_address;
    }

    public void setAttachment_address(String obj)
    {
        attachment_address = obj;
    }

    public String getAttachment_type()
    {
        return attachment_type;
    }

    public void setAttachment_type(String obj)
    {
        attachment_type = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }
}