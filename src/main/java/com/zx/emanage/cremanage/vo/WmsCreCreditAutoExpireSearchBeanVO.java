package com.zx.emanage.cremanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/**
 * @ClassName: WmsCreCreditAutoExpireSearchBeanVO
 * @Description: 内容摘要：
 * @author Administrator
 * @date 2017年2月20日
 * @version V1.0
 * history:
 * 1、2017年2月20日 Administrator 创建文件
 */
public class WmsCreCreditAutoExpireSearchBeanVO extends GridParamBean
{

    /**
     * @Fields serialVersionUID : 
     */
    private static final long serialVersionUID = 1L;

    private String wms_cre_credit_head_id;
    
    private String bill_code;
    
    private String salesman_name_str;
    
    private String create_timestamp_begin;
    
    private String create_timestamp_end;
    
    private String customer_name;
    
    private String id_card;
    
    private String salesman_city_code;
    
    private String maturity_status;

    // 消息类型
    private String send_type;
    // 参数结束时间
    private String end_date;
    // 参数业务员id
    private String salesman_id;
    // 参数业务员短工号
    private String salesman_shortcode;
    // 参数业务员姓名
    private String salesman_name;
    // 单据状态
    private String bill_status;
    // 人员短工号姓名
    private String personnel_name;
    // 人员短工号
    private String personnel_shortcode;
    // 城市
    private String city;

    //
    private String is_page;
    
    //抵押形式
    private String mort_flag;
    
    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    public String getSalesman_name_str()
    {
        return salesman_name_str;
    }

    public void setSalesman_name_str(String salesman_name_str)
    {
        this.salesman_name_str = salesman_name_str;
    }

    public String getCreate_timestamp_begin()
    {
        return create_timestamp_begin;
    }

    public void setCreate_timestamp_begin(String create_timestamp_begin)
    {
        this.create_timestamp_begin = create_timestamp_begin;
    }

    public String getCreate_timestamp_end()
    {
        return create_timestamp_end;
    }

    public void setCreate_timestamp_end(String create_timestamp_end)
    {
        this.create_timestamp_end = create_timestamp_end;
    }

    public String getCustomer_name()
    {
        return customer_name;
    }

    public void setCustomer_name(String customer_name)
    {
        this.customer_name = customer_name;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String id_card)
    {
        this.id_card = id_card;
    }

    public String getSalesman_city_code()
    {
        return salesman_city_code;
    }

    public void setSalesman_city_code(String salesman_city_code)
    {
        this.salesman_city_code = salesman_city_code;
    }

    public String getMaturity_status()
    {
        return maturity_status;
    }

    public void setMaturity_status(String maturity_status)
    {
        this.maturity_status = maturity_status;
    }

    public String getSend_type()
    {
        return send_type;
    }

    public void setSend_type(String send_type)
    {
        this.send_type = send_type;
    }

    public String getEnd_date()
    {
        return end_date;
    }

    public void setEnd_date(String end_date)
    {
        this.end_date = end_date;
    }

    public String getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(String wms_cre_credit_head_id)
    {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

    public String getSalesman_id()
    {
        return salesman_id;
    }

    public void setSalesman_id(String salesman_id)
    {
        this.salesman_id = salesman_id;
    }

    public String getSalesman_shortcode()
    {
        return salesman_shortcode;
    }

    public void setSalesman_shortcode(String salesman_shortcode)
    {
        this.salesman_shortcode = salesman_shortcode;
    }

    public String getBill_status()
    {
        return bill_status;
    }

    public void setBill_status(String bill_status)
    {
        this.bill_status = bill_status;
    }

    public String getPersonnel_name()
    {
        return personnel_name;
    }

    public void setPersonnel_name(String personnel_name)
    {
        this.personnel_name = personnel_name;
    }

    public String getPersonnel_shortcode()
    {
        return personnel_shortcode;
    }

    public void setPersonnel_shortcode(String personnel_shortcode)
    {
        this.personnel_shortcode = personnel_shortcode;
    }

    public String getSalesman_name()
    {
        return salesman_name;
    }

    public void setSalesman_name(String salesman_name)
    {
        this.salesman_name = salesman_name;
    }

    public String getIs_page()
    {
        return is_page;
    }

    public void setIs_page(String is_page)
    {
        this.is_page = is_page;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getMort_flag()
    {
        return mort_flag;
    }

    public void setMort_flag(String mort_flag)
    {
        this.mort_flag = mort_flag;
    }

}
