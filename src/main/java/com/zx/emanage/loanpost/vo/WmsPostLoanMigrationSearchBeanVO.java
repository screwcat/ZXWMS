package com.zx.emanage.loanpost.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsPostLoanMigrationSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    // 转件管理查询条件
    private String protocol_code;// 合同编号

    private String debtor_name;// 客户姓名

    private String debtor_tel;// 联系电话

    private String customer_officer_name;// 客户专员

    private String cre_type;// 贷款产品

    private String migration_status;// 是否发起转件申请

    private String margtype;// 转件管理子项

    private String wms_cre_credit_head_id;

    public String getProtocol_code()
    {
        return protocol_code;
    }

    public void setProtocol_code(String protocol_code)
    {
        this.protocol_code = protocol_code;
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

    public String getCustomer_officer_name()
    {
        return customer_officer_name;
    }

    public void setCustomer_officer_name(String customer_officer_name)
    {
        this.customer_officer_name = customer_officer_name;
    }

    public String getCre_type()
    {
        return cre_type;
    }

    public void setCre_type(String cre_type)
    {
        this.cre_type = cre_type;
    }

    public String getMigration_status()
    {
        return migration_status;
    }

    public void setMigration_status(String migration_status)
    {
        this.migration_status = migration_status;
    }

    public String getMargtype()
    {
        return margtype;
    }

    public void setMargtype(String margtype)
    {
        this.margtype = margtype;
    }

    public String getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(String wms_cre_credit_head_id)
    {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

}