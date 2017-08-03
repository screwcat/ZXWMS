package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSigendApplicationVo
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2017年2月10日
 * @version V1.0
 * history:
 * 1、2017年2月10日 DongHao 创建文件
 */
public class WmsInveSigendApplicationVo extends GridParamBean
{

    /**
     * @Fields serialVersionUID : 
     */
    private static final long serialVersionUID = -2641993382090700265L;

    /************签单客户基本信息**************/

    private String cus_name;// 客户姓名

    private String mobile_phone;// 客户联系方式

    private String id_card;// 客户有效证件

    private String customer_email;// 客户邮件地址

    private String cus_address;// 客户通讯地址

    private Integer salesman_id;// 归属业务员id

    private String salesman_city_code;// 归属业务员城市编码

    private Integer salesman_dept_id;// 归属业务员部门id

    private String salesman_city;// 归属业务员所属城市

    private String bill_send_mode;// 设置的默认地址

    private String income_account;// 收益到账提醒

    private String expiration_reminder;// 到期提醒

    private String debt_expires;// 债权到期提醒

    /************购买产品信息**************/

    private java.math.BigDecimal product_total_count_lower;// 产品投资金额(小写)

    private String product_total_count_upper;// 产品投资金额(大写)

    private Integer category_id;// 产品Id

    /************收益卡信息**************/

    private String card_owner_name;// 卡住姓名

    private String bank_of_deposit;// 开户行

    private String card_no;// 收益卡卡号

    private String bank_branch;// 支行

    private String bank_of_deposit_pro;// 省

    private String bank_of_deposit_city;// 市

    private String saveFlag;// 保存类型

    private Integer wms_inve_transa_id;// 上单表主键

    public String getCus_name()
    {
        return cus_name;
    }

    public void setCus_name(String cus_name)
    {
        this.cus_name = cus_name;
    }

    public String getMobile_phone()
    {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone)
    {
        this.mobile_phone = mobile_phone;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String id_card)
    {
        this.id_card = id_card;
    }

    public String getCustomer_email()
    {
        return customer_email;
    }

    public void setCustomer_email(String customer_email)
    {
        this.customer_email = customer_email;
    }

    public String getCus_address()
    {
        return cus_address;
    }

    public void setCus_address(String cus_address)
    {
        this.cus_address = cus_address;
    }

    public Integer getSalesman_id()
    {
        return salesman_id;
    }

    public void setSalesman_id(Integer salesman_id)
    {
        this.salesman_id = salesman_id;
    }

    public String getSalesman_city_code()
    {
        return salesman_city_code;
    }

    public void setSalesman_city_code(String salesman_city_code)
    {
        this.salesman_city_code = salesman_city_code;
    }

    public Integer getSalesman_dept_id()
    {
        return salesman_dept_id;
    }

    public void setSalesman_dept_id(Integer salesman_dept_id)
    {
        this.salesman_dept_id = salesman_dept_id;
    }

    public String getSalesman_city()
    {
        return salesman_city;
    }

    public void setSalesman_city(String salesman_city)
    {
        this.salesman_city = salesman_city;
    }

    public String getBill_send_mode()
    {
        return bill_send_mode;
    }

    public void setBill_send_mode(String bill_send_mode)
    {
        this.bill_send_mode = bill_send_mode;
    }

    public String getIncome_account()
    {
        return income_account;
    }

    public void setIncome_account(String income_account)
    {
        this.income_account = income_account;
    }

    public String getExpiration_reminder()
    {
        return expiration_reminder;
    }

    public void setExpiration_reminder(String expiration_reminder)
    {
        this.expiration_reminder = expiration_reminder;
    }

    public String getDebt_expires()
    {
        return debt_expires;
    }

    public void setDebt_expires(String debt_expires)
    {
        this.debt_expires = debt_expires;
    }

    public java.math.BigDecimal getProduct_total_count_lower()
    {
        return product_total_count_lower;
    }

    public void setProduct_total_count_lower(java.math.BigDecimal product_total_count_lower)
    {
        this.product_total_count_lower = product_total_count_lower;
    }

    public String getProduct_total_count_upper()
    {
        return product_total_count_upper;
    }

    public void setProduct_total_count_upper(String product_total_count_upper)
    {
        this.product_total_count_upper = product_total_count_upper;
    }

    public Integer getCategory_id()
    {
        return category_id;
    }

    public void setCategory_id(Integer category_id)
    {
        this.category_id = category_id;
    }

    public String getCard_owner_name()
    {
        return card_owner_name;
    }

    public void setCard_owner_name(String card_owner_name)
    {
        this.card_owner_name = card_owner_name;
    }

    public String getBank_of_deposit()
    {
        return bank_of_deposit;
    }

    public void setBank_of_deposit(String bank_of_deposit)
    {
        this.bank_of_deposit = bank_of_deposit;
    }

    public String getCard_no()
    {
        return card_no;
    }

    public void setCard_no(String card_no)
    {
        this.card_no = card_no;
    }

    public String getBank_branch()
    {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch)
    {
        this.bank_branch = bank_branch;
    }

    public String getBank_of_deposit_pro()
    {
        return bank_of_deposit_pro;
    }

    public void setBank_of_deposit_pro(String bank_of_deposit_pro)
    {
        this.bank_of_deposit_pro = bank_of_deposit_pro;
    }

    public String getBank_of_deposit_city()
    {
        return bank_of_deposit_city;
    }

    public void setBank_of_deposit_city(String bank_of_deposit_city)
    {
        this.bank_of_deposit_city = bank_of_deposit_city;
    }

    public String getSaveFlag()
    {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag)
    {
        this.saveFlag = saveFlag;
    }

    public Integer getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    public void setWms_inve_transa_id(Integer wms_inve_transa_id)
    {
        this.wms_inve_transa_id = wms_inve_transa_id;
    }

}
