package com.zx.emanage.creditRightManager.vo;

import java.sql.Date;

import com.zx.platform.syscontext.vo.GridParamBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCredRightNotifyCustomerSearchBeanVo
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年3月13日
 * @version V1.0
 * history:
 * 1、2017年3月13日 jinzhm 创建文件
 */
public class WmsCredRightNotifyCustomerSearchBeanVo extends GridParamBean
{
    /**
     * @Fields serialVersionUID : 
     */
    private static final long serialVersionUID = 1L;

    private String cre_per_name;

    private String protocol_id_year_num;

    private String cre_per_card_id;

    private Date crepg_end_date_begin;

    private Date crepg_end_date_end;

    private String end_method;

    private String cus_name;

    private String salesman_name;

    private String is_notified;

    private String is_config_notification;

    private String menu_id;

    /**
     * @return the menu_id
     */
    public String getMenu_id()
    {
        return menu_id;
    }

    /**
     * @param menu_id the menu_id to set
     */
    public void setMenu_id(String menu_id)
    {
        this.menu_id = menu_id;
    }

    /**
     * @return the cre_per_name
     */
    public String getCre_per_name()
    {
        return cre_per_name;
    }

    /**
     * @param cre_per_name the cre_per_name to set
     */
    public void setCre_per_name(String cre_per_name)
    {
        this.cre_per_name = cre_per_name;
    }

    /**
     * @return the protocol_id_year_num
     */
    public String getProtocol_id_year_num()
    {
        return protocol_id_year_num;
    }

    /**
     * @param protocol_id_year_num the protocol_id_year_num to set
     */
    public void setProtocol_id_year_num(String protocol_id_year_num)
    {
        this.protocol_id_year_num = protocol_id_year_num;
    }

    /**
     * @return the cre_per_card_id
     */
    public String getCre_per_card_id()
    {
        return cre_per_card_id;
    }

    /**
     * @param cre_per_card_id the cre_per_card_id to set
     */
    public void setCre_per_card_id(String cre_per_card_id)
    {
        this.cre_per_card_id = cre_per_card_id;
    }

    /**
     * @return the crepg_end_date_begin
     */
    public Date getCrepg_end_date_begin()
    {
        return crepg_end_date_begin;
    }

    /**
     * @param crepg_end_date_begin the crepg_end_date_begin to set
     */
    public void setCrepg_end_date_begin(Date crepg_end_date_begin)
    {
        this.crepg_end_date_begin = crepg_end_date_begin;
    }

    /**
     * @return the crepg_end_date_end
     */
    public Date getCrepg_end_date_end()
    {
        return crepg_end_date_end;
    }

    /**
     * @param crepg_end_date_end the crepg_end_date_end to set
     */
    public void setCrepg_end_date_end(Date crepg_end_date_end)
    {
        this.crepg_end_date_end = crepg_end_date_end;
    }

    /**
     * @return the end_method
     */
    public String getEnd_method()
    {
        return end_method;
    }

    /**
     * @param end_method the end_method to set
     */
    public void setEnd_method(String end_method)
    {
        this.end_method = end_method;
    }

    /**
     * @return the cus_name
     */
    public String getCus_name()
    {
        return cus_name;
    }

    /**
     * @param cus_name the cus_name to set
     */
    public void setCus_name(String cus_name)
    {
        this.cus_name = cus_name;
    }

    /**
     * @return the salesman_name
     */
    public String getSalesman_name()
    {
        return salesman_name;
    }

    /**
     * @param salesman_name the salesman_name to set
     */
    public void setSalesman_name(String salesman_name)
    {
        this.salesman_name = salesman_name;
    }

    /**
     * @return the is_notified
     */
    public String getIs_notified()
    {
        return is_notified;
    }

    /**
     * @param is_notified the is_notified to set
     */
    public void setIs_notified(String is_notified)
    {
        this.is_notified = is_notified;
    }

    /**
     * @return the is_config_notification
     */
    public String getIs_config_notification()
    {
        return is_config_notification;
    }

    /**
     * @param is_config_notification the is_config_notification to set
     */
    public void setIs_config_notification(String is_config_notification)
    {
        this.is_config_notification = is_config_notification;
    }

}
