package com.zx.emanage.loanappro.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */
//这个VO 让我祸害了  jiaodelong
public class WmsCreApproBorrowProtocolSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;
    private String  file_type;//pdf
    
    private String bill_status;
    //标志
    private String status;
    //合同表主键
    private Integer wms_cre_appro_id;
    //贷款表主键
    private Integer wms_cre_credit_head_id;
    //债权人姓名
    private String creditor_name;
    //债权人身份证号码
    private String creditor_identity_id;
    //债权人联系电话
    private String creditor_tel;
    //债权人通讯地址
    private String creditor_address;
    //债务人姓名
    private String debtor_name;
    //身份证号
    private String debtor_identity_id;
    //联系电话
    private String debtor_tel;
    //通讯地址
    private String debtor_address;
    //共同债务人
    private String com_debtor_name;
    //身份证号
    private String com_debtor_identity_id;
    //联系电话
    private String com_debtor_tel;
    //通讯地址
    private String com_debtor_address;
    //产品种类
    private Integer cre_loan_type;
    //借款本金
    private Integer appro_limit;
    //合同金额(小写)
    private java.math.BigDecimal principal_lower;
    //合同金额(大写)
    private String principal_caps;
    //借款用途
    private String borrow_purpose;
    //借款期限
    private String borrow_deadline;
    //申请时间
    private java.sql.Timestamp borrow_begin_date;
    //月利率
    private java.math.BigDecimal borrow_interest;
    //每期还款(小写)
    private java.math.BigDecimal protocol_refund_interest_month;
    //每期还款(大写)
    private String refund_limit_month_caps;
    //截止时间
    private java.sql.Timestamp borrow_end_date;
    //违约金利率（小写）
    private java.math.BigDecimal yuqi_damages;
    //(用于计算)
    private java.math.BigDecimal weiyuejin;
    //第一期利息日
    private java.sql.Timestamp first_refund_date;
    //第二期利息日
    private java.sql.Timestamp second_refund_date;
    //打款用户名
    private String debtor_loan_name;
    //打款账号
    private String debtor_loan_number;
    //打款银行
    private String debtor_loan_bank;
    //签订时间
    private java.sql.Date protocol_date;
    //抵押房产座落
    private String explicit_address;
    //抵押房产权利人
    private String debtor_name_fcdyqlr;
    //抵押房产建筑面积
    private String house_area;
    //抵押房产权属证号
    private String house_certificate_number;
    //抵押房产权属卷号
    private String house_roll_number;
    //还款用户名
    private String refund_name;
    //还款账号
    private String refund_number;
    //还款银行
    private String refund_bank;
    //还款金额
    private java.math.BigDecimal refund_limit_month;
    //还款日期
    private String refund_day;
    //违约金
    private java.math.BigDecimal liquidated_damages;
    //最终还款时间
    private java.sql.Timestamp plan_borrow_date;
    //他项
    private java.math.BigDecimal it_cost_fee;
    //加急费
    private java.math.BigDecimal expedited_fee;
    //转账支付金额
    private java.math.BigDecimal loan_amount;
    //现金支付金额
    private java.math.BigDecimal deduct_money;
    //签订地点
    private String sign_place;
    //手续费率
    private java.math.BigDecimal consult_service_cost;
    //平台总费率
    private java.math.BigDecimal jujian_service_cost;
    //平台费率
    private java.math.BigDecimal service_cost_month;
    //保证人
    private String person_name;
    //身份证号
    private String person_identity_id;
    //乙与丙关系
    private String a_c_relation;
    //第二居所位置
    private String second_house_address;
    //居住地址
    private String c_house_address;
////////////////////////////////////////////////////房产局合同    
    //借款期限
    private Integer regular_borrow_deadline;
    //截止时间
    private java.sql.Timestamp regular_borrow_end_date;
    //双方确认金额
    private java.math.BigDecimal confirm_house_value;
    //合同编号
    private String protocol_id_year;
    
    private String protocol_id_num;
    //甲方--辽宁卓信投资管理有限公司
    private String owner;
    
    private String create_user_id;
    private String create_user_name;
    private java.sql.Timestamp create_timestamp;
    private String enable_flag;
    private String protocol_type;
    private String protocol_id_year_num;
    //1代表等额本息 2代表先息后本
    private String payment_contract_type;
            
    private java.math.BigDecimal principal;
    private java.math.BigDecimal interest;
    private java.math.BigDecimal org_repay_principal;
    private java.math.BigDecimal org_repay_interest;
    
    //债权人账号
    private String creditor_loan_name;
    private String creditor_loan_number;
    private String creditor_loan_bank;
    
    //每期还款金额
    private java.math.BigDecimal service_refund_principal_month_lower;

    /**下载打印合同 标识  1为本公司 2为房产局合同**/
    private String protocol_num;
    
    
    private java.math.BigDecimal appro_limit_fcj;
    private java.math.BigDecimal borrow_interest_fcj;
    private java.math.BigDecimal protocol_refund_interest_month_fcj;
    
    private String bill_type;//业务状态标志（01新增，02展期03新增）
    private String salesman_shortcode;//业务员短工号
    private java.math.BigDecimal fees;
    private String the_number;
    private Integer wms_cre_credit_notary_warn_id;
    private java.math.BigDecimal notarial_fee;
    private java.math.BigDecimal platform_fee;
    private String loan_amount_caps;//转账金额大写
    private String cre_type_name;
    private String bill_code;
    
    private String justice_type;

    private String regular_appro_limit_caps;
    // 是否计算业绩 0：否；1：是'
    private String is_achievement;
    // 是否计算存量 0：否；1：是
    private String is_stock;
    // 抵押形式1一拆2二拆
    private String mort_flag;
    //待缴房产核查费
    private java.math.BigDecimal check_pay; 
    
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCreditor_name() {
		return creditor_name;
	}
	public String getDebtor_name() {
		return debtor_name;
	}
	public String getDebtor_identity_id() {
		return debtor_identity_id;
	}
	public String getDebtor_tel() {
		return debtor_tel;
	}
	public String getCom_debtor_name() {
		return com_debtor_name;
	}
	public String getCom_debtor_identity_id() {
		return com_debtor_identity_id;
	}
	public String getCom_debtor_tel() {
		return com_debtor_tel;
	}
	public String getCom_debtor_address() {
		return com_debtor_address;
	}
	public Integer getCre_loan_type() {
		return cre_loan_type;
	}
	public Integer getAppro_limit() {
		return appro_limit;
	}
	public java.math.BigDecimal getPrincipal_lower() {
		return principal_lower;
	}
	public String getPrincipal_caps() {
		return principal_caps;
	}
	public String getBorrow_purpose() {
		return borrow_purpose;
	}
	public String getBorrow_deadline() {
		return borrow_deadline;
	}
	public java.sql.Timestamp getBorrow_begin_date() {
		return borrow_begin_date;
	}
	public java.math.BigDecimal getBorrow_interest() {
		return borrow_interest;
	}
	public java.math.BigDecimal getProtocol_refund_interest_month() {
		return protocol_refund_interest_month;
	}
	public String getRefund_limit_month_caps() {
		return refund_limit_month_caps;
	}
	public java.sql.Timestamp getBorrow_end_date() {
		return borrow_end_date;
	}
	public java.math.BigDecimal getYuqi_damages() {
		return yuqi_damages;
	}
	public java.math.BigDecimal getWeiyuejin() {
		return weiyuejin;
	}
	public java.sql.Timestamp getFirst_refund_date() {
		return first_refund_date;
	}
	public java.sql.Timestamp getSecond_refund_date() {
		return second_refund_date;
	}
	public String getDebtor_loan_name() {
		return debtor_loan_name;
	}
	public String getDebtor_loan_number() {
		return debtor_loan_number;
	}
	public String getDebtor_loan_bank() {
		return debtor_loan_bank;
	}
	public String getExplicit_address() {
		return explicit_address;
	}
	public String getDebtor_name_fcdyqlr() {
		return debtor_name_fcdyqlr;
	}
	public String getHouse_area() {
		return house_area;
	}
	public String getHouse_certificate_number() {
		return house_certificate_number;
	}
	public String getHouse_roll_number() {
		return house_roll_number;
	}
	public String getRefund_name() {
		return refund_name;
	}
	public String getRefund_number() {
		return refund_number;
	}
	public String getRefund_bank() {
		return refund_bank;
	}
	public java.math.BigDecimal getRefund_limit_month() {
		return refund_limit_month;
	}
	public java.math.BigDecimal getLiquidated_damages() {
		return liquidated_damages;
	}
	public java.sql.Timestamp getPlan_borrow_date() {
		return plan_borrow_date;
	}
	public java.math.BigDecimal getIt_cost_fee() {
		return it_cost_fee;
	}
	public java.math.BigDecimal getExpedited_fee() {
		return expedited_fee;
	}
	public java.math.BigDecimal getLoan_amount() {
		return loan_amount;
	}
	public java.math.BigDecimal getDeduct_money() {
		return deduct_money;
	}
	public String getSign_place() {
		return sign_place;
	}
	public java.math.BigDecimal getConsult_service_cost() {
		return consult_service_cost;
	}
	public java.math.BigDecimal getJujian_service_cost() {
		return jujian_service_cost;
	}
	public java.math.BigDecimal getService_cost_month() {
		return service_cost_month;
	}
	public String getPerson_name() {
		return person_name;
	}
	public String getPerson_identity_id() {
		return person_identity_id;
	}
	public String getA_c_relation() {
		return a_c_relation;
	}
	public String getSecond_house_address() {
		return second_house_address;
	}
	public String getC_house_address() {
		return c_house_address;
	}
	public Integer getRegular_borrow_deadline() {
		return regular_borrow_deadline;
	}
	public java.sql.Timestamp getRegular_borrow_end_date() {
		return regular_borrow_end_date;
	}
	public java.math.BigDecimal getConfirm_house_value() {
		return confirm_house_value;
	}
	public void setCreditor_name(String creditor_name) {
		this.creditor_name = creditor_name;
	}
	public void setDebtor_name(String debtor_name) {
		this.debtor_name = debtor_name;
	}
	public void setDebtor_identity_id(String debtor_identity_id) {
		this.debtor_identity_id = debtor_identity_id;
	}
	public void setDebtor_tel(String debtor_tel) {
		this.debtor_tel = debtor_tel;
	}
	public void setCom_debtor_name(String com_debtor_name) {
		this.com_debtor_name = com_debtor_name;
	}
	public void setCom_debtor_identity_id(String com_debtor_identity_id) {
		this.com_debtor_identity_id = com_debtor_identity_id;
	}
	public void setCom_debtor_tel(String com_debtor_tel) {
		this.com_debtor_tel = com_debtor_tel;
	}
	public void setCom_debtor_address(String com_debtor_address) {
		this.com_debtor_address = com_debtor_address;
	}
	public void setCre_loan_type(Integer cre_loan_type) {
		this.cre_loan_type = cre_loan_type;
	}
	public void setAppro_limit(Integer appro_limit) {
		this.appro_limit = appro_limit;
	}
	public void setPrincipal_lower(java.math.BigDecimal principal_lower) {
		this.principal_lower = principal_lower;
	}
	public void setPrincipal_caps(String principal_caps) {
		this.principal_caps = principal_caps;
	}
	public void setBorrow_purpose(String borrow_purpose) {
		this.borrow_purpose = borrow_purpose;
	}
	public void setBorrow_deadline(String borrow_deadline) {
		this.borrow_deadline = borrow_deadline;
	}
	public void setBorrow_begin_date(java.sql.Timestamp borrow_begin_date) {
		this.borrow_begin_date = borrow_begin_date;
	}
	public void setBorrow_interest(java.math.BigDecimal borrow_interest) {
		this.borrow_interest = borrow_interest;
	}
	public void setProtocol_refund_interest_month(
			java.math.BigDecimal protocol_refund_interest_month) {
		this.protocol_refund_interest_month = protocol_refund_interest_month;
	}
	public void setRefund_limit_month_caps(String refund_limit_month_caps) {
		this.refund_limit_month_caps = refund_limit_month_caps;
	}
	public void setBorrow_end_date(java.sql.Timestamp borrow_end_date) {
		this.borrow_end_date = borrow_end_date;
	}
	public void setYuqi_damages(java.math.BigDecimal yuqi_damages) {
		this.yuqi_damages = yuqi_damages;
	}
	public void setWeiyuejin(java.math.BigDecimal weiyuejin) {
		this.weiyuejin = weiyuejin;
	}
	public void setFirst_refund_date(java.sql.Timestamp first_refund_date) {
		this.first_refund_date = first_refund_date;
	}
	public void setSecond_refund_date(java.sql.Timestamp second_refund_date) {
		this.second_refund_date = second_refund_date;
	}
	public void setDebtor_loan_name(String debtor_loan_name) {
		this.debtor_loan_name = debtor_loan_name;
	}
	public void setDebtor_loan_number(String debtor_loan_number) {
		this.debtor_loan_number = debtor_loan_number;
	}
	public void setDebtor_loan_bank(String debtor_loan_bank) {
		this.debtor_loan_bank = debtor_loan_bank;
	}
	public void setExplicit_address(String explicit_address) {
		this.explicit_address = explicit_address;
	}
	public void setDebtor_name_fcdyqlr(String debtor_name_fcdyqlr) {
		this.debtor_name_fcdyqlr = debtor_name_fcdyqlr;
	}
	public void setHouse_area(String house_area) {
		this.house_area = house_area;
	}
	public void setHouse_certificate_number(String house_certificate_number) {
		this.house_certificate_number = house_certificate_number;
	}
	public void setHouse_roll_number(String house_roll_number) {
		this.house_roll_number = house_roll_number;
	}
	public void setRefund_name(String refund_name) {
		this.refund_name = refund_name;
	}
	public void setRefund_number(String refund_number) {
		this.refund_number = refund_number;
	}
	public void setRefund_bank(String refund_bank) {
		this.refund_bank = refund_bank;
	}
	public void setRefund_limit_month(java.math.BigDecimal refund_limit_month) {
		this.refund_limit_month = refund_limit_month;
	}
	public void setLiquidated_damages(java.math.BigDecimal liquidated_damages) {
		this.liquidated_damages = liquidated_damages;
	}
	public void setPlan_borrow_date(java.sql.Timestamp plan_borrow_date) {
		this.plan_borrow_date = plan_borrow_date;
	}
	public void setIt_cost_fee(java.math.BigDecimal it_cost_fee) {
		this.it_cost_fee = it_cost_fee;
	}
	public void setExpedited_fee(java.math.BigDecimal expedited_fee) {
		this.expedited_fee = expedited_fee;
	}
	public void setLoan_amount(java.math.BigDecimal loan_amount) {
		this.loan_amount = loan_amount;
	}
	public void setDeduct_money(java.math.BigDecimal deduct_money) {
		this.deduct_money = deduct_money;
	}
	public void setSign_place(String sign_place) {
		this.sign_place = sign_place;
	}
	public void setConsult_service_cost(java.math.BigDecimal consult_service_cost) {
		this.consult_service_cost = consult_service_cost;
	}
	public void setJujian_service_cost(java.math.BigDecimal jujian_service_cost) {
		this.jujian_service_cost = jujian_service_cost;
	}
	public void setService_cost_month(java.math.BigDecimal service_cost_month) {
		this.service_cost_month = service_cost_month;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public void setPerson_identity_id(String person_identity_id) {
		this.person_identity_id = person_identity_id;
	}
	public void setA_c_relation(String a_c_relation) {
		this.a_c_relation = a_c_relation;
	}
	public void setSecond_house_address(String second_house_address) {
		this.second_house_address = second_house_address;
	}
	public void setC_house_address(String c_house_address) {
		this.c_house_address = c_house_address;
	}
	public void setRegular_borrow_deadline(Integer regular_borrow_deadline) {
		this.regular_borrow_deadline = regular_borrow_deadline;
	}
	public void setRegular_borrow_end_date(
			java.sql.Timestamp regular_borrow_end_date) {
		this.regular_borrow_end_date = regular_borrow_end_date;
	}
	public void setConfirm_house_value(java.math.BigDecimal confirm_house_value) {
		this.confirm_house_value = confirm_house_value;
	}
	public String getProtocol_id_year() {
		return protocol_id_year;
	}
	public String getProtocol_id_num() {
		return protocol_id_num;
	}
	public void setProtocol_id_year(String protocol_id_year) {
		this.protocol_id_year = protocol_id_year;
	}
	public void setProtocol_id_num(String protocol_id_num) {
		this.protocol_id_num = protocol_id_num;
	}
	public String getRefund_day() {
		return refund_day;
	}
	public void setRefund_day(String refund_day) {
		this.refund_day = refund_day;
	}
	public String getCreditor_identity_id() {
		return creditor_identity_id;
	}
	public String getCreditor_tel() {
		return creditor_tel;
	}
	public String getCreditor_address() {
		return creditor_address;
	}
	public void setCreditor_identity_id(String creditor_identity_id) {
		this.creditor_identity_id = creditor_identity_id;
	}
	public void setCreditor_tel(String creditor_tel) {
		this.creditor_tel = creditor_tel;
	}
	public void setCreditor_address(String creditor_address) {
		this.creditor_address = creditor_address;
	}
	public Integer getWms_cre_appro_id() {
		return wms_cre_appro_id;
	}
	public void setWms_cre_appro_id(Integer wms_cre_appro_id) {
		this.wms_cre_appro_id = wms_cre_appro_id;
	}
	public Integer getWms_cre_credit_head_id() {
		return wms_cre_credit_head_id;
	}
	public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
		this.wms_cre_credit_head_id = wms_cre_credit_head_id;
	}
	public java.sql.Date getProtocol_date() {
		return protocol_date;
	}
	public void setProtocol_date(java.sql.Date protocol_date) {
		this.protocol_date = protocol_date;
	}
	public String getCreate_user_id() {
		return create_user_id;
	}
	public String getCreate_user_name() {
		return create_user_name;
	}
	public java.sql.Timestamp getCreate_timestamp() {
		return create_timestamp;
	}
	public String getEnable_flag() {
		return enable_flag;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}
	public void setCreate_timestamp(java.sql.Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag;
	}
	public String getProtocol_type() {
		return protocol_type;
	}
	public void setProtocol_type(String protocol_type) {
		this.protocol_type = protocol_type;
	}
	public String getProtocol_id_year_num() {
		return protocol_id_year_num;
	}
	public void setProtocol_id_year_num(String protocol_id_year_num) {
		this.protocol_id_year_num = protocol_id_year_num;
	}
	public String getDebtor_address() {
		return debtor_address;
	}
	public void setDebtor_address(String debtor_address) {
		this.debtor_address = debtor_address;
	}
	public String getPayment_contract_type() {
		return payment_contract_type;
	}
	public void setPayment_contract_type(String payment_contract_type) {
		this.payment_contract_type = payment_contract_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

        public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public java.math.BigDecimal getPrincipal() {
		return principal;
	}
	public java.math.BigDecimal getInterest() {
		return interest;
	}
	public java.math.BigDecimal getOrg_repay_principal() {
		return org_repay_principal;
	}
	public java.math.BigDecimal getOrg_repay_interest() {
		return org_repay_interest;
	}
	public void setPrincipal(java.math.BigDecimal principal) {
		this.principal = principal;
	}
	public void setInterest(java.math.BigDecimal interest) {
		this.interest = interest;
	}
	public void setOrg_repay_principal(java.math.BigDecimal org_repay_principal) {
		this.org_repay_principal = org_repay_principal;
	}
	public void setOrg_repay_interest(java.math.BigDecimal org_repay_interest) {
		this.org_repay_interest = org_repay_interest;
	}
	public String getCreditor_loan_name() {
		return creditor_loan_name;
	}
	public String getCreditor_loan_number() {
		return creditor_loan_number;
	}
	public String getCreditor_loan_bank() {
		return creditor_loan_bank;
	}
	public void setCreditor_loan_name(String creditor_loan_name) {
		this.creditor_loan_name = creditor_loan_name;
	}
	public void setCreditor_loan_number(String creditor_loan_number) {
		this.creditor_loan_number = creditor_loan_number;
	}
	public void setCreditor_loan_bank(String creditor_loan_bank) {
		this.creditor_loan_bank = creditor_loan_bank;
	}
	public java.math.BigDecimal getService_refund_principal_month_lower() {
		return service_refund_principal_month_lower;
	}
	public void setService_refund_principal_month_lower(
			java.math.BigDecimal service_refund_principal_month_lower) {
		this.service_refund_principal_month_lower = service_refund_principal_month_lower;
	}
    public String getProtocol_num()
    {
        return protocol_num;
    }

    public void setProtocol_num(String protocol_num)
    {
        this.protocol_num = protocol_num;
    }
    public java.math.BigDecimal getAppro_limit_fcj()
    {
        return appro_limit_fcj;
    }
    public java.math.BigDecimal getBorrow_interest_fcj()
    {
        return borrow_interest_fcj;
    }
    public java.math.BigDecimal getProtocol_refund_interest_month_fcj()
    {
        return protocol_refund_interest_month_fcj;
    }
    public void setAppro_limit_fcj(java.math.BigDecimal appro_limit_fcj)
    {
        this.appro_limit_fcj = appro_limit_fcj;
    }
    public void setBorrow_interest_fcj(java.math.BigDecimal borrow_interest_fcj)
    {
        this.borrow_interest_fcj = borrow_interest_fcj;
    }
    public void setProtocol_refund_interest_month_fcj(java.math.BigDecimal protocol_refund_interest_month_fcj)
    {
        this.protocol_refund_interest_month_fcj = protocol_refund_interest_month_fcj;
    }
    public String getRegular_appro_limit_caps()
    {
        return regular_appro_limit_caps;
    }
    public void setRegular_appro_limit_caps(String regular_appro_limit_caps)
    {
        this.regular_appro_limit_caps = regular_appro_limit_caps;
    }
    public String getBill_type()
    {
        return bill_type;
    }
    public void setBill_type(String bill_type)
    {
        this.bill_type = bill_type;
    }
    public String getSalesman_shortcode()
    {
        return salesman_shortcode;
    }
    public void setSalesman_shortcode(String salesman_shortcode)
    {
        this.salesman_shortcode = salesman_shortcode;
    }
    public String getThe_number()
    {
        return the_number;
    }
    public void setThe_number(String the_number)
    {
        this.the_number = the_number;
    }
    public Integer getWms_cre_credit_notary_warn_id()
    {
        return wms_cre_credit_notary_warn_id;
    }
    public void setWms_cre_credit_notary_warn_id(Integer wms_cre_credit_notary_warn_id)
    {
        this.wms_cre_credit_notary_warn_id = wms_cre_credit_notary_warn_id;
    }
    public java.math.BigDecimal getNotarial_fee()
    {
        return notarial_fee;
    }
    public void setNotarial_fee(java.math.BigDecimal notarial_fee)
    {
        this.notarial_fee = notarial_fee;
    }
    public java.math.BigDecimal getPlatform_fee()
    {
        return platform_fee;
    }
    public void setPlatform_fee(java.math.BigDecimal platform_fee)
    {
        this.platform_fee = platform_fee;
    }
    public String getLoan_amount_caps()
    {
        return loan_amount_caps;
    }
    public void setLoan_amount_caps(String loan_amount_caps)
    {
        this.loan_amount_caps = loan_amount_caps;
    }
    public String getCre_type_name()
    {
        return cre_type_name;
    }
    public void setCre_type_name(String cre_type_name)
    {
        this.cre_type_name = cre_type_name;
    }
    public String getBill_code()
    {
        return bill_code;
    }
    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }
    public java.math.BigDecimal getFees()
    {
        return fees;
    }
    public void setFees(java.math.BigDecimal fees)
    {
        this.fees = fees;
    }
    public String getBill_status()
    {
        return bill_status;
    }
    public void setBill_status(String bill_status)
    {
        this.bill_status = bill_status;
    }
    public String getJustice_type()
    {
        return justice_type;
    }
    public void setJustice_type(String justice_type)
    {
        this.justice_type = justice_type;
    }

    public String getIs_achievement()
    {
        return is_achievement;
    }

    public void setIs_achievement(String is_achievement)
    {
        this.is_achievement = is_achievement;
    }

    public String getIs_stock()
    {
        return is_stock;
    }

    public void setIs_stock(String is_stock)
    {
        this.is_stock = is_stock;
    }
    public java.math.BigDecimal getCheck_pay()
    {
        return check_pay;
    }
    public void setCheck_pay(java.math.BigDecimal check_pay)
    {
        this.check_pay = check_pay;
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
