package com.zx.emanage.loanappro.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */
public class WmsCreApproBorrowProtocolSearchConditionBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    /*-----------------查询条件--------------------*/
    /** 单据编号 */
    private String bill_code;

    /** 业务员/工号 */
    private String salesman_name;

    /** 申请日期 开始 */
    private String create_timestamp_begin;

    /** 申请日期 结束 */
    private String create_timestamp_end;

    /** 客户姓名 */
    private String debtor_name;

    /** 联系电话 */
    private String debtor_tel;

    /** 签单日期  开始 */
    private String protocol_date_begin;

    /** 签单日期  结束 */
    private String protocol_date_end;

    /** 身份证号 */
    private String debtor_identity_id;

    /** 合同编号 */
    private String protocol_id_year_num;

    /** 合同补录期  开始 */
    private String protocol_supply_date_begin;

    /** 合同补录期  结束 */
    private String protocol_supply_date_end;

    /** 贷款类型 */
    private String cre_type;

    /** 产品种类 */
    private String cre_loan_type;

    /** 业务类型 */
    private String bill_type;

    /** 抵押状态 */
    private String mort_flag;

    /*-----------------------------------------*/

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    public String getSalesman_name()
    {
        return salesman_name;
    }

    public void setSalesman_name(String salesman_name)
    {
        this.salesman_name = salesman_name;
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

    public String getDebtor_name()
    {
        return debtor_name;
    }

    public void setDebtor_name(String debtor_name)
    {
        this.debtor_name = debtor_name;
    }

    public String getDebtor_tel()
    {
        return debtor_tel;
    }

    public void setDebtor_tel(String debtor_tel)
    {
        this.debtor_tel = debtor_tel;
    }

    public String getProtocol_date_begin()
    {
        return protocol_date_begin;
    }

    public void setProtocol_date_begin(String protocol_date_begin)
    {
        this.protocol_date_begin = protocol_date_begin;
    }

    public String getProtocol_date_end()
    {
        return protocol_date_end;
    }

    public void setProtocol_date_end(String protocol_date_end)
    {
        this.protocol_date_end = protocol_date_end;
    }

    public String getDebtor_identity_id()
    {
        return debtor_identity_id;
    }

    public void setDebtor_identity_id(String debtor_identity_id)
    {
        this.debtor_identity_id = debtor_identity_id;
    }

    public String getProtocol_id_year_num()
    {
        return protocol_id_year_num;
    }

    public void setProtocol_id_year_num(String protocol_id_year_num)
    {
        this.protocol_id_year_num = protocol_id_year_num;
    }

    public String getProtocol_supply_date_begin()
    {
        return protocol_supply_date_begin;
    }

    public void setProtocol_supply_date_begin(String protocol_supply_date_begin)
    {
        this.protocol_supply_date_begin = protocol_supply_date_begin;
    }

    public String getProtocol_supply_date_end()
    {
        return protocol_supply_date_end;
    }

    public void setProtocol_supply_date_end(String protocol_supply_date_end)
    {
        this.protocol_supply_date_end = protocol_supply_date_end;
    }

    public String getCre_type()
    {
        return cre_type;
    }

    public void setCre_type(String cre_type)
    {
        this.cre_type = cre_type;
    }

    public String getCre_loan_type()
    {
        return cre_loan_type;
    }

    public void setCre_loan_type(String cre_loan_type)
    {
        this.cre_loan_type = cre_loan_type;
    }

    public String getBill_type()
    {
        return bill_type;
    }

    public void setBill_type(String bill_type)
    {
        this.bill_type = bill_type;
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
