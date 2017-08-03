package com.zx.emanage.util.gen.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsCreApproBorrowProtocol implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_appro_id;

    private String protocol_id_year;

    private String protocol_id_num;

    private java.sql.Date protocol_date;

    private String protocol_date_str;

    private String creditor_name;

    private String creditor_identity_id;

    private String debtor_name;

    private String debtor_identity_id;

    private String creditor_address;

    private String debtor_address;

    private String creditor_tel;

    private String debtor_tel;

    private String debtor_fixed_line;

    private String principal_caps;

    private java.math.BigDecimal principal_lower;

    private Integer borrow_deadline;

    private java.sql.Date borrow_begin_date;

    private String borrow_begin_date_str;

    private java.sql.Date borrow_end_date;

    private String borrow_end_date_str;

    private String borrow_purpose;

    private java.math.BigDecimal borrow_interest;

    private String refund_bank;

    private String refund_number;

    private String refund_name;

    private java.math.BigDecimal refund_limit_month;

    private String refund_day;

    private Long liquidated_damages;

    private Integer wms_cre_credit_head_id;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private String protocol_type;

    private String com_debtor_name;

    private String com_debtor_identity_id;

    private String com_debtor_address;

    private String com_debtor_tel;

    private String com_debtor_fixed_line;

    private java.math.BigDecimal refund_principal_month_lower;

    private String refund_principal_month_caps;

    private java.math.BigDecimal refund_interest_month_lower;

    private String refund_interest_month_caps;

    private String refund_limit_month_caps;

    private java.math.BigDecimal compensate;

    private java.math.BigDecimal yuqi_damages;

    private String compensate_caps;

    private String yuqi_damages_caps;

    private String protocol_id_year_num;

    private String borrow_interest_caps;

    private java.math.BigDecimal principal;

    private java.math.BigDecimal interest;

    private java.math.BigDecimal org_repay_principal;

    private java.math.BigDecimal org_repay_interest;

    private java.math.BigDecimal refund_limit_month_lower;

    private java.sql.Date plan_borrow_date;

    private String plan_borrow_date_str;

    private java.math.BigDecimal loan_amount;

    private String company;

    private String cheque_number;

    private String bill_number;

    private java.math.BigDecimal deduct_money;

    private String c_house_address;

    private String a_c_relation;

    private String second_house_address;

    private String arbitrate_name;

    private String court_name;

    private String dispute_solve;

    private String house_sale;

    private String house_estimate;

    private String sign_place;

    private String replace_pay_money_caps;

    private java.math.BigDecimal replace_pay_money_lower;

    private String cash_transfer_lower;

    private String payment_contract_type;
    
    private java.math.BigDecimal appro_limit;
    
    //房贷三号四号使用
    private java.math.BigDecimal weiyuejin;
    private java.sql.Date first_refund_date;
    private java.sql.Date second_refund_date;
    private String creditor_loan_name;
    private String creditor_loan_number;
    private String creditor_loan_bank;
    private String  debtor_loan_name;
    private String  debtor_loan_number;
    private String debtor_loan_bank;
    private java.math.BigDecimal protocol_refund_interest_month;
    /*
   creditor_zip_code    varchar(20) comment '债权人邮编',
   debtor_zip_code      varchar(20) comment '债务人邮编',
   embezzlement_damages decimal(16,8) comment '挪用借款违约金',
   loan_currency        varchar(50) comment '借款币种',
   debtor_residence_address varchar(100) comment '债务人居住地址',
   protocol_d_num       varchar(100) comment '抵押合同编号',
   */
    private String  creditor_zip_code;  
    private String  debtor_zip_code;    
    private java.math.BigDecimal embezzlement_damages;
    private String  loan_currency ;    
    private String  debtor_residence_address;
    private String  protocol_d_num;
    
    private String justice_type;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    @JsonIgnore
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    @JsonIgnore
    private static String[] pkColArr = { "wms_cre_appro_id" };

    @JsonIgnore
    private static String[] columnNameArr = { "wms_cre_appro_id", "protocol_id_year", "protocol_id_num",
                                             "protocol_date", "protocol_date_str", "creditor_name",
                                             "creditor_identity_id", "debtor_name", "debtor_identity_id",
                                             "creditor_address", "debtor_address", "creditor_tel", "debtor_tel",
                                             "debtor_fixed_line", "principal_caps", "principal_lower",
                                             "borrow_deadline", "borrow_begin_date", "borrow_begin_date_str",
                                             "borrow_end_date", "borrow_end_date_str", "borrow_purpose",
                                             "borrow_interest", "refund_bank", "refund_number", "refund_name",
                                             "refund_limit_month", "refund_day", "liquidated_damages",
                                             "wms_cre_credit_head_id", "create_user_id", "create_user_name",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str", "enable_flag",
                                             "protocol_type", "com_debtor_name", "com_debtor_identity_id",
                                             "com_debtor_address", "com_debtor_tel", "com_debtor_fixed_line",
                                             "refund_principal_month_lower", "refund_principal_month_caps",
                                             "refund_interest_month_lower", "refund_interest_month_caps",
                                             "refund_limit_month_caps", "compensate", "yuqi_damages",
                                             "compensate_caps", "yuqi_damages_caps", "protocol_id_year_num",
                                             "borrow_interest_caps", "principal", "interest", "org_repay_principal",
                                             "org_repay_interest", "refund_limit_month_lower", "plan_borrow_date",
                                             "plan_borrow_date_str", "loan_amount", "company", "cheque_number",
                                             "bill_number", "deduct_money", "c_house_address", "a_c_relation",
                                             "second_house_address", "arbitrate_name", "court_name", "dispute_solve",
                                             "house_sale", "house_estimate", "sign_place", "replace_pay_money_caps",
                                             "replace_pay_money_lower", "cash_transfer_lower", "payment_contract_type",
                                             "isExcludePKFlag" };

    public String getCash_transfer_lower()
    {
        return cash_transfer_lower;
    }

    public void setCash_transfer_lower(String cash_transfer_lower)
    {
        this.cash_transfer_lower = cash_transfer_lower;
    }

    public Integer getWms_cre_appro_id()
    {
        return wms_cre_appro_id;
    }

    public void setWms_cre_appro_id(Integer obj)
    {
        wms_cre_appro_id = obj;
    }

    public String getProtocol_id_year()
    {
        return protocol_id_year;
    }

    public void setProtocol_id_year(String obj)
    {
        protocol_id_year = obj;
    }

    public String getProtocol_id_num()
    {
        return protocol_id_num;
    }

    public void setProtocol_id_num(String obj)
    {
        protocol_id_num = obj;
    }

    public java.sql.Date getProtocol_date()
    {
        return protocol_date;
    }

    public void setProtocol_date(java.sql.Date obj)
    {
        protocol_date = obj;
    }

    public String getProtocol_date_str()
    {
        return protocol_date_str;
    }

    public void setProtocol_date_str(String obj)
    {
        protocol_date_str = obj;
    }

    public String getCreditor_name()
    {
        return creditor_name;
    }

    public void setCreditor_name(String obj)
    {
        creditor_name = obj;
    }

    public String getCreditor_identity_id()
    {
        return creditor_identity_id;
    }

    public void setCreditor_identity_id(String obj)
    {
        creditor_identity_id = obj;
    }

    public String getDebtor_name()
    {
        return debtor_name;
    }

    public void setDebtor_name(String obj)
    {
        debtor_name = obj;
    }

    public String getDebtor_identity_id()
    {
        return debtor_identity_id;
    }

    public void setDebtor_identity_id(String obj)
    {
        debtor_identity_id = obj;
    }

    public String getCreditor_address()
    {
        return creditor_address;
    }

    public void setCreditor_address(String obj)
    {
        creditor_address = obj;
    }

    public String getDebtor_address()
    {
        return debtor_address;
    }

    public void setDebtor_address(String obj)
    {
        debtor_address = obj;
    }

    public String getCreditor_tel()
    {
        return creditor_tel;
    }

    public void setCreditor_tel(String obj)
    {
        creditor_tel = obj;
    }

    public String getDebtor_tel()
    {
        return debtor_tel;
    }

    public void setDebtor_tel(String obj)
    {
        debtor_tel = obj;
    }

    public String getDebtor_fixed_line()
    {
        return debtor_fixed_line;
    }

    public void setDebtor_fixed_line(String obj)
    {
        debtor_fixed_line = obj;
    }

    public String getPrincipal_caps()
    {
        return principal_caps;
    }

    public void setPrincipal_caps(String obj)
    {
        principal_caps = obj;
    }

    public java.math.BigDecimal getPrincipal_lower()
    {
        return principal_lower;
    }

    public void setPrincipal_lower(java.math.BigDecimal obj)
    {
        principal_lower = obj;
    }

    public Integer getBorrow_deadline()
    {
        return borrow_deadline;
    }

    public void setBorrow_deadline(Integer obj)
    {
        borrow_deadline = obj;
    }

    public java.sql.Date getBorrow_begin_date()
    {
        return borrow_begin_date;
    }

    public void setBorrow_begin_date(java.sql.Date obj)
    {
        borrow_begin_date = obj;
    }

    public String getBorrow_begin_date_str()
    {
        return borrow_begin_date_str;
    }

    public void setBorrow_begin_date_str(String obj)
    {
        borrow_begin_date_str = obj;
    }

    public java.sql.Date getBorrow_end_date()
    {
        return borrow_end_date;
    }

    public void setBorrow_end_date(java.sql.Date obj)
    {
        borrow_end_date = obj;
    }

    public String getBorrow_end_date_str()
    {
        return borrow_end_date_str;
    }

    public void setBorrow_end_date_str(String obj)
    {
        borrow_end_date_str = obj;
    }

    public String getBorrow_purpose()
    {
        return borrow_purpose;
    }

    public void setBorrow_purpose(String obj)
    {
        borrow_purpose = obj;
    }

    public java.math.BigDecimal getBorrow_interest()
    {
        return borrow_interest;
    }

    public void setBorrow_interest(java.math.BigDecimal obj)
    {
        borrow_interest = obj;
    }

    public String getRefund_bank()
    {
        return refund_bank;
    }

    public void setRefund_bank(String obj)
    {
        refund_bank = obj;
    }

    public String getRefund_number()
    {
        return refund_number;
    }

    public void setRefund_number(String obj)
    {
        refund_number = obj;
    }

    public String getRefund_name()
    {
        return refund_name;
    }

    public void setRefund_name(String obj)
    {
        refund_name = obj;
    }

    public java.math.BigDecimal getRefund_limit_month()
    {
        return refund_limit_month;
    }

    public void setRefund_limit_month(java.math.BigDecimal obj)
    {
        refund_limit_month = obj;
    }

    public String getRefund_day()
    {
        return refund_day;
    }

    public void setRefund_day(String obj)
    {
        refund_day = obj;
    }

    public Long getLiquidated_damages()
    {
        return liquidated_damages;
    }

    public void setLiquidated_damages(Long obj)
    {
        liquidated_damages = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer obj)
    {
        create_user_id = obj;
    }

    public String getCreate_user_name()
    {
        return create_user_name;
    }

    public void setCreate_user_name(String obj)
    {
        create_user_name = obj;
    }

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp obj)
    {
        create_timestamp = obj;
    }

    public String getCreate_timestamp_str()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestamp_str(String obj)
    {
        create_timestamp_str = obj;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer obj)
    {
        last_update_user_id = obj;
    }

    public java.sql.Timestamp getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(java.sql.Timestamp obj)
    {
        last_update_timestamp = obj;
    }

    public String getLast_update_timestamp_str()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestamp_str(String obj)
    {
        last_update_timestamp_str = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }

    public String getProtocol_type()
    {
        return protocol_type;
    }

    public void setProtocol_type(String obj)
    {
        protocol_type = obj;
    }

    public String getCom_debtor_name()
    {
        return com_debtor_name;
    }

    public void setCom_debtor_name(String obj)
    {
        com_debtor_name = obj;
    }

    public String getCom_debtor_identity_id()
    {
        return com_debtor_identity_id;
    }

    public void setCom_debtor_identity_id(String obj)
    {
        com_debtor_identity_id = obj;
    }

    public String getCom_debtor_address()
    {
        return com_debtor_address;
    }

    public void setCom_debtor_address(String obj)
    {
        com_debtor_address = obj;
    }

    public String getCom_debtor_tel()
    {
        return com_debtor_tel;
    }

    public void setCom_debtor_tel(String obj)
    {
        com_debtor_tel = obj;
    }

    public String getCom_debtor_fixed_line()
    {
        return com_debtor_fixed_line;
    }

    public void setCom_debtor_fixed_line(String obj)
    {
        com_debtor_fixed_line = obj;
    }

    public java.math.BigDecimal getRefund_principal_month_lower()
    {
        return refund_principal_month_lower;
    }

    public void setRefund_principal_month_lower(java.math.BigDecimal obj)
    {
        refund_principal_month_lower = obj;
    }

    public String getRefund_principal_month_caps()
    {
        return refund_principal_month_caps;
    }

    public void setRefund_principal_month_caps(String obj)
    {
        refund_principal_month_caps = obj;
    }

    public java.math.BigDecimal getRefund_interest_month_lower()
    {
        return refund_interest_month_lower;
    }

    public void setRefund_interest_month_lower(java.math.BigDecimal obj)
    {
        refund_interest_month_lower = obj;
    }

    public String getRefund_interest_month_caps()
    {
        return refund_interest_month_caps;
    }

    public void setRefund_interest_month_caps(String obj)
    {
        refund_interest_month_caps = obj;
    }

    public String getRefund_limit_month_caps()
    {
        return refund_limit_month_caps;
    }

    public void setRefund_limit_month_caps(String obj)
    {
        refund_limit_month_caps = obj;
    }

    public java.math.BigDecimal getCompensate()
    {
        return compensate;
    }

    public void setCompensate(java.math.BigDecimal obj)
    {
        compensate = obj;
    }

    public java.math.BigDecimal getYuqi_damages()
    {
        return yuqi_damages;
    }

    public void setYuqi_damages(java.math.BigDecimal obj)
    {
        yuqi_damages = obj;
    }

    public String getCompensate_caps()
    {
        return compensate_caps;
    }

    public void setCompensate_caps(String obj)
    {
        compensate_caps = obj;
    }

    public String getYuqi_damages_caps()
    {
        return yuqi_damages_caps;
    }

    public void setYuqi_damages_caps(String obj)
    {
        yuqi_damages_caps = obj;
    }

    public String getProtocol_id_year_num()
    {
        return protocol_id_year_num;
    }

    public void setProtocol_id_year_num(String obj)
    {
        protocol_id_year_num = obj;
    }

    public String getBorrow_interest_caps()
    {
        return borrow_interest_caps;
    }

    public void setBorrow_interest_caps(String obj)
    {
        borrow_interest_caps = obj;
    }

    public java.math.BigDecimal getPrincipal()
    {
        return principal;
    }

    public void setPrincipal(java.math.BigDecimal obj)
    {
        principal = obj;
    }

    public java.math.BigDecimal getInterest()
    {
        return interest;
    }

    public void setInterest(java.math.BigDecimal obj)
    {
        interest = obj;
    }

    public java.math.BigDecimal getOrg_repay_principal()
    {
        return org_repay_principal;
    }

    public void setOrg_repay_principal(java.math.BigDecimal obj)
    {
        org_repay_principal = obj;
    }

    public java.math.BigDecimal getOrg_repay_interest()
    {
        return org_repay_interest;
    }

    public void setOrg_repay_interest(java.math.BigDecimal obj)
    {
        org_repay_interest = obj;
    }

    public java.math.BigDecimal getRefund_limit_month_lower()
    {
        return refund_limit_month_lower;
    }

    public void setRefund_limit_month_lower(java.math.BigDecimal obj)
    {
        refund_limit_month_lower = obj;
    }

    public java.sql.Date getPlan_borrow_date()
    {
        return plan_borrow_date;
    }

    public void setPlan_borrow_date(java.sql.Date obj)
    {
        plan_borrow_date = obj;
    }

    public String getPlan_borrow_date_str()
    {
        return plan_borrow_date_str;
    }

    public void setPlan_borrow_date_str(String obj)
    {
        plan_borrow_date_str = obj;
    }

    public java.math.BigDecimal getLoan_amount()
    {
        return loan_amount;
    }

    public void setLoan_amount(java.math.BigDecimal obj)
    {
        loan_amount = obj;
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String obj)
    {
        company = obj;
    }

    public String getCheque_number()
    {
        return cheque_number;
    }

    public void setCheque_number(String obj)
    {
        cheque_number = obj;
    }

    public String getBill_number()
    {
        return bill_number;
    }

    public void setBill_number(String obj)
    {
        bill_number = obj;
    }

    public java.math.BigDecimal getDeduct_money()
    {
        return deduct_money;
    }

    public void setDeduct_money(java.math.BigDecimal obj)
    {
        deduct_money = obj;
    }

    public String getC_house_address()
    {
        return c_house_address;
    }

    public void setC_house_address(String obj)
    {
        c_house_address = obj;
    }

    public String getA_c_relation()
    {
        return a_c_relation;
    }

    public void setA_c_relation(String obj)
    {
        a_c_relation = obj;
    }

    public String getSecond_house_address()
    {
        return second_house_address;
    }

    public void setSecond_house_address(String obj)
    {
        second_house_address = obj;
    }

    public String getArbitrate_name()
    {
        return arbitrate_name;
    }

    public void setArbitrate_name(String obj)
    {
        arbitrate_name = obj;
    }

    public String getCourt_name()
    {
        return court_name;
    }

    public void setCourt_name(String obj)
    {
        court_name = obj;
    }

    public String getDispute_solve()
    {
        return dispute_solve;
    }

    public void setDispute_solve(String obj)
    {
        dispute_solve = obj;
    }

    public String getHouse_sale()
    {
        return house_sale;
    }

    public void setHouse_sale(String obj)
    {
        house_sale = obj;
    }

    public String getHouse_estimate()
    {
        return house_estimate;
    }

    public void setHouse_estimate(String obj)
    {
        house_estimate = obj;
    }

    public String getSign_place()
    {
        return sign_place;
    }

    public void setSign_place(String obj)
    {
        sign_place = obj;
    }

    public String getReplace_pay_money_caps()
    {
        return replace_pay_money_caps;
    }

    public void setReplace_pay_money_caps(String obj)
    {
        replace_pay_money_caps = obj;
    }

    public java.math.BigDecimal getReplace_pay_money_lower()
    {
        return replace_pay_money_lower;
    }

    public void setReplace_pay_money_lower(java.math.BigDecimal obj)
    {
        replace_pay_money_lower = obj;
    }

    public String getPayment_contract_type()
    {
        return payment_contract_type;
    }

    public void setPayment_contract_type(String payment_contract_type)
    {
        this.payment_contract_type = payment_contract_type;
    }

    public boolean getgetIsExcludePKFlag()
    {
        return isExcludePKFlag;
    }

    public void setgetIsExcludePKFlag(boolean obj)
    {
        isExcludePKFlag = obj;
    }

    public java.math.BigDecimal getWeiyuejin() {
		return weiyuejin;
	}

	public void setWeiyuejin(java.math.BigDecimal weiyuejin) {
		this.weiyuejin = weiyuejin;
	}

	public java.sql.Date getFirst_refund_date() {
		return first_refund_date;
	}

	public void setFirst_refund_date(java.sql.Date first_refund_date) {
		this.first_refund_date = first_refund_date;
	}

	public java.sql.Date getSecond_refund_date() {
		return second_refund_date;
	}

	public void setSecond_refund_date(java.sql.Date second_refund_date) {
		this.second_refund_date = second_refund_date;
	}

	public String getCreditor_loan_name() {
		return creditor_loan_name;
	}

	public void setCreditor_loan_name(String creditor_loan_name) {
		this.creditor_loan_name = creditor_loan_name;
	}

	public String getCreditor_loan_number() {
		return creditor_loan_number;
	}

	public void setCreditor_loan_number(String creditor_loan_number) {
		this.creditor_loan_number = creditor_loan_number;
	}

	public String getCreditor_loan_bank() {
		return creditor_loan_bank;
	}

	public void setCreditor_loan_bank(String creditor_loan_bank) {
		this.creditor_loan_bank = creditor_loan_bank;
	}

	public String getDebtor_loan_name() {
		return debtor_loan_name;
	}

	public void setDebtor_loan_name(String debtor_loan_name) {
		this.debtor_loan_name = debtor_loan_name;
	}

	public String getDebtor_loan_number() {
		return debtor_loan_number;
	}

	public void setDebtor_loan_number(String debtor_loan_number) {
		this.debtor_loan_number = debtor_loan_number;
	}

	public String getDebtor_loan_bank() {
		return debtor_loan_bank;
	}

	public void setDebtor_loan_bank(String debtor_loan_bank) {
		this.debtor_loan_bank = debtor_loan_bank;
	}

	public java.math.BigDecimal getProtocol_refund_interest_month() {
		return protocol_refund_interest_month;
	}

	public void setProtocol_refund_interest_month(
			java.math.BigDecimal protocol_refund_interest_month) {
		this.protocol_refund_interest_month = protocol_refund_interest_month;
	}

	public String getCreditor_zip_code() {
		return creditor_zip_code;
	}

	public void setCreditor_zip_code(String creditor_zip_code) {
		this.creditor_zip_code = creditor_zip_code;
	}

	public String getDebtor_zip_code() {
		return debtor_zip_code;
	}

	public void setDebtor_zip_code(String debtor_zip_code) {
		this.debtor_zip_code = debtor_zip_code;
	}

	public java.math.BigDecimal getEmbezzlement_damages() {
		return embezzlement_damages;
	}

	public void setEmbezzlement_damages(java.math.BigDecimal embezzlement_damages) {
		this.embezzlement_damages = embezzlement_damages;
	}

	public String getLoan_currency() {
		return loan_currency;
	}

	public void setLoan_currency(String loan_currency) {
		this.loan_currency = loan_currency;
	}

	public String getDebtor_residence_address() {
		return debtor_residence_address;
	}

	public void setDebtor_residence_address(String debtor_residence_address) {
		this.debtor_residence_address = debtor_residence_address;
	}

	public String getProtocol_d_num() {
		return protocol_d_num;
	}

	public void setProtocol_d_num(String protocol_d_num) {
		this.protocol_d_num = protocol_d_num;
	}

	
	public java.math.BigDecimal getAppro_limit()
    {
        return appro_limit;
    }

    public void setAppro_limit(java.math.BigDecimal appro_limit)
    {
        this.appro_limit = appro_limit;
    }

    public String getJustice_type()
    {
        return justice_type;
    }

    public void setJustice_type(String justice_type)
    {
        this.justice_type = justice_type;
    }

    /**
     * put all columns into a map
     */
	@JsonIgnore
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_appro_id", this.wms_cre_appro_id);
        paramMap.put("protocol_id_year", this.protocol_id_year);
        paramMap.put("protocol_id_num", this.protocol_id_num);
        paramMap.put("protocol_date", this.protocol_date);
        paramMap.put("protocol_date_str", this.protocol_date_str);
        paramMap.put("creditor_name", this.creditor_name);
        paramMap.put("creditor_identity_id", this.creditor_identity_id);
        paramMap.put("debtor_name", this.debtor_name);
        paramMap.put("debtor_identity_id", this.debtor_identity_id);
        paramMap.put("creditor_address", this.creditor_address);
        paramMap.put("debtor_address", this.debtor_address);
        paramMap.put("creditor_tel", this.creditor_tel);
        paramMap.put("debtor_tel", this.debtor_tel);
        paramMap.put("debtor_fixed_line", this.debtor_fixed_line);
        paramMap.put("principal_caps", this.principal_caps);
        paramMap.put("principal_lower", this.principal_lower);
        paramMap.put("borrow_deadline", this.borrow_deadline);
        paramMap.put("borrow_begin_date", this.borrow_begin_date);
        paramMap.put("borrow_begin_date_str", this.borrow_begin_date_str);
        paramMap.put("borrow_end_date", this.borrow_end_date);
        paramMap.put("borrow_end_date_str", this.borrow_end_date_str);
        paramMap.put("borrow_purpose", this.borrow_purpose);
        paramMap.put("borrow_interest", this.borrow_interest);
        paramMap.put("refund_bank", this.refund_bank);
        paramMap.put("refund_number", this.refund_number);
        paramMap.put("refund_name", this.refund_name);
        paramMap.put("refund_limit_month", this.refund_limit_month);
        paramMap.put("refund_day", this.refund_day);
        paramMap.put("liquidated_damages", this.liquidated_damages);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("protocol_type", this.protocol_type);
        paramMap.put("com_debtor_name", this.com_debtor_name);
        paramMap.put("com_debtor_identity_id", this.com_debtor_identity_id);
        paramMap.put("com_debtor_address", this.com_debtor_address);
        paramMap.put("com_debtor_tel", this.com_debtor_tel);
        paramMap.put("com_debtor_fixed_line", this.com_debtor_fixed_line);
        paramMap.put("refund_principal_month_lower", this.refund_principal_month_lower);
        paramMap.put("refund_principal_month_caps", this.refund_principal_month_caps);
        paramMap.put("refund_interest_month_lower", this.refund_interest_month_lower);
        paramMap.put("refund_interest_month_caps", this.refund_interest_month_caps);
        paramMap.put("refund_limit_month_caps", this.refund_limit_month_caps);
        paramMap.put("compensate", this.compensate);
        paramMap.put("yuqi_damages", this.yuqi_damages);
        paramMap.put("compensate_caps", this.compensate_caps);
        paramMap.put("yuqi_damages_caps", this.yuqi_damages_caps);
        paramMap.put("protocol_id_year_num", this.protocol_id_year_num);
        paramMap.put("borrow_interest_caps", this.borrow_interest_caps);
        paramMap.put("principal", this.principal);
        paramMap.put("interest", this.interest);
        paramMap.put("org_repay_principal", this.org_repay_principal);
        paramMap.put("org_repay_interest", this.org_repay_interest);
        paramMap.put("refund_limit_month_lower", this.refund_limit_month_lower);
        paramMap.put("plan_borrow_date", this.plan_borrow_date);
        paramMap.put("plan_borrow_date_str", this.plan_borrow_date_str);
        paramMap.put("loan_amount", this.loan_amount);
        paramMap.put("company", this.company);
        paramMap.put("cheque_number", this.cheque_number);
        paramMap.put("bill_number", this.bill_number);
        paramMap.put("deduct_money", this.deduct_money);
        paramMap.put("c_house_address", this.c_house_address);
        paramMap.put("a_c_relation", this.a_c_relation);
        paramMap.put("second_house_address", this.second_house_address);
        paramMap.put("arbitrate_name", this.arbitrate_name);
        paramMap.put("court_name", this.court_name);
        paramMap.put("dispute_solve", this.dispute_solve);
        paramMap.put("house_sale", this.house_sale);
        paramMap.put("house_estimate", this.house_estimate);
        paramMap.put("sign_place", this.sign_place);
        paramMap.put("replace_pay_money_caps", this.replace_pay_money_caps);
        paramMap.put("replace_pay_money_lower", this.replace_pay_money_lower);
        paramMap.put("cash_transfer_lower", this.cash_transfer_lower);
        paramMap.put("payment_contract_type", this.payment_contract_type);
        paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
    }

    /**
     * return the columns map
     */
	@JsonIgnore
    public Map<String, Object> getInfoMap()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        this.putInMap(paramMap);
        return paramMap;
    }

    /**
     * remove default value and pk if it is null
     */
	@JsonIgnore
    private Map<String, Object> dealWithMap(Map<String, Object> paramMap)
    {
        Set<String> set = new HashSet<String>();
        for (String colName : defaultValColArr)
        {
            set.add(colName);
        }
        for (String colName : pkColArr)
        {
            set.add(colName);
        }
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext())
        {
            String colName = iterator.next();
            if (paramMap.get(colName) == null)
            {
                paramMap.remove(colName);
            }
        }
        return paramMap;
    }
}