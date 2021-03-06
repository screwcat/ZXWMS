package com.zx.emanage.util.gen.vo;

import java.math.BigDecimal;
import java.sql.Date;

import com.zx.sframe.util.mybatis.BaseEntity;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveOfflineCreditImportTemp
 * 模块名称：现在债权导入临时对象
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年4月11日
 * @version V1.0
 * history:
 * 1、2017年4月11日 jinzhm 创建文件
 */
public class WmsInveOfflineCreditImportTemp implements BaseEntity
{
    /**
     * @Fields serialVersionUID : 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String cre_per_name;

    private String protocol_id_year_num;

    private String cre_per_card_id;

    private BigDecimal cre_pledge_mon;

    private BigDecimal house_size;

    private Date crepg_start_date;

    private Date crepg_end_date;

    private String rele_per_name;

    private String local_name;

    private String cus_name;

    private BigDecimal match_product_account;

    private Date date_of_payment;

    private String financial_bill_code;

    private String bill_code;

    private Integer rele_per_id;

    private String local_num;

    private String enable_flag;
    
    private String remark;

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
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
     * @return the cre_pledge_mon
     */
    public BigDecimal getCre_pledge_mon()
    {
        return cre_pledge_mon;
    }

    /**
     * @param cre_pledge_mon the cre_pledge_mon to set
     */
    public void setCre_pledge_mon(BigDecimal cre_pledge_mon)
    {
        this.cre_pledge_mon = cre_pledge_mon;
    }

    /**
     * @return the house_size
     */
    public BigDecimal getHouse_size()
    {
        return house_size;
    }

    /**
     * @param house_size the house_size to set
     */
    public void setHouse_size(BigDecimal house_size)
    {
        this.house_size = house_size;
    }

    /**
     * @return the crepg_start_date
     */
    public Date getCrepg_start_date()
    {
        return crepg_start_date;
    }

    /**
     * @param crepg_start_date the crepg_start_date to set
     */
    public void setCrepg_start_date(Date crepg_start_date)
    {
        this.crepg_start_date = crepg_start_date;
    }

    /**
     * @return the crepg_end_date
     */
    public Date getCrepg_end_date()
    {
        return crepg_end_date;
    }

    /**
     * @param crepg_end_date the crepg_end_date to set
     */
    public void setCrepg_end_date(Date crepg_end_date)
    {
        this.crepg_end_date = crepg_end_date;
    }

    /**
     * @return the rele_per_name
     */
    public String getRele_per_name()
    {
        return rele_per_name;
    }

    /**
     * @param rele_per_name the rele_per_name to set
     */
    public void setRele_per_name(String rele_per_name)
    {
        this.rele_per_name = rele_per_name;
    }

    /**
     * @return the local_name
     */
    public String getLocal_name()
    {
        return local_name;
    }

    /**
     * @param local_name the local_name to set
     */
    public void setLocal_name(String local_name)
    {
        this.local_name = local_name;
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
     * @return the match_product_account
     */
    public BigDecimal getMatch_product_account()
    {
        return match_product_account;
    }

    /**
     * @param match_product_account the match_product_account to set
     */
    public void setMatch_product_account(BigDecimal match_product_account)
    {
        this.match_product_account = match_product_account;
    }

    /**
     * @return the date_of_payment
     */
    public Date getDate_of_payment()
    {
        return date_of_payment;
    }

    /**
     * @param date_of_payment the date_of_payment to set
     */
    public void setDate_of_payment(Date date_of_payment)
    {
        this.date_of_payment = date_of_payment;
    }

    /**
     * @return the financial_bill_code
     */
    public String getFinancial_bill_code()
    {
        return financial_bill_code;
    }

    /**
     * @param financial_bill_code the financial_bill_code to set
     */
    public void setFinancial_bill_code(String financial_bill_code)
    {
        this.financial_bill_code = financial_bill_code;
    }

    /**
     * @return the bill_code
     */
    public String getBill_code()
    {
        return bill_code;
    }

    /**
     * @param bill_code the bill_code to set
     */
    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    /**
     * @return the rele_per_id
     */
    public Integer getRele_per_id()
    {
        return rele_per_id;
    }

    /**
     * @param rele_per_id the rele_per_id to set
     */
    public void setRele_per_id(Integer rele_per_id)
    {
        this.rele_per_id = rele_per_id;
    }

    /**
     * @return the local_num
     */
    public String getLocal_num()
    {
        return local_num;
    }

    /**
     * @param local_num the local_num to set
     */
    public void setLocal_num(String local_num)
    {
        this.local_num = local_num;
    }

    /**
     * @return the enable_flag
     */
    public String getEnable_flag()
    {
        return enable_flag;
    }

    /**
     * @param enable_flag the enable_flag to set
     */
    public void setEnable_flag(String enable_flag)
    {
        this.enable_flag = enable_flag;
    }

    /**
     * @return the remark
     */
    public String getRemark()
    {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

}
