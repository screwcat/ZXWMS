package com.zx.emanage.util.gen.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;

import com.zx.sframe.util.mybatis.BaseEntity;

/**
 * 
 * @ClassName: WmsCreCreditNotaryWarn
 * @Description: 内容摘要：
 * @author wangyihan
 * @date 2016年12月2日
 * @version V1.0
 * history:
 * 1、2016年12月2日 wangyihan 创建文件
 */
public class WmsCreCreditNotaryWarn implements BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 **/
    private Integer wms_cre_credit_notary_warn_id;

    /** 单据编码 **/
    private String bill_code;

    /** 单据的状态  0为结清  **/
    private String bill_status;

    /** 公正到期时间 **/
    private Date end_time;

    /** 到期原因 **/
    private String end_reason;

    /** 到期天数 **/
    private Integer end_day_number;

    /** 申请日期 **/
    private Date appl_date;

    /** 客户姓名 **/
    private String customer_name;

    /** 身份证号 **/
    private String id_card;

    /** 通讯地址 **/
    private String current_address_more;

    /** 联系电话 **/
    private String mobile_telephone;

    /** 共贷人姓名 **/
    private String com_debtor_name;

    /** 共同贷款人身份证号 **/
    private String com_debtor_identity_id;

    /** 共同贷款人电话 **/
    private String com_debtor_tel;

    /** 固定电话 **/
    private String debtor_fixed_line;

    /** 合同签订日期 **/
    private Date protocol_date;

    /** 终止还款日期 **/
    private Date borrow_end_date;

    /** 合同号 **/
    private String protocol_id_year_num;

    /** 产品名称 **/
    private String category_name;

    /** 利息 **/
    private String borrow_interest;

    /** 借款金额 **/
    private String appro_limit;

    /** 借款期限 **/
    private Integer borrow_deadline;

    /** 打卡金额 **/
    private String loan_amount;

    /** 平台费 **/
    private String platform_fee;

    /** 公证费 **/
    private String notarial_fee;

    /** 合同金额 **/
    private String principal_lower;

    /** 期还款额 **/
    private String refund_limit_month;

    /** 业务员的名字 **/
    private String salesman_name;

    /** 业务员的短工号 **/
    private String salesman_shortcode;

    /** 业务员所在部门编码 **/
    private String salesman_dept_code;

    /** 团队经理姓名 **/
    private String team_manager_name;

    /** 团队经理短工号 **/
    private String team_manager_code;

    /** 所属营业部部门编码 **/
    private String sales_epartment_code;

    /** 所属营业部部门名称 **/
    private String sales_epartment_name;

    /** 创建人id **/
    private Integer create_user_id;

    /** 创建人id **/
    private String create_user_name;

    /** 创建时间 **/
    private Timestamp create_timestamp;

    /** 最后一次更改人id **/
    private Integer last_update_user_id;

    /** 最后一次更改时间 **/
    private Timestamp last_update_timestamp;

    /** 数据有效性 1有效0无效 **/
    private String enable_flag;

    /** 备注 **/
    private String warn_remak;

    /** 已经提醒的次数 每提醒一次 次数加1 结清重置为0 **/
    private Integer alreadyse_send_message_number;

    /** 是否周末未进行提醒  如果是周末 未进行提醒的单据 为1 否则为0或者空 **/
    private Integer is_weekend_not_remind;

    /** 数据来源  1是导入 2页面增加 **/
    private String data_sources;

    /** 是否续贷 1是 0不是 **/
    private String is_cycles;

    /** 结清日期 **/
    private Date clean_up_date;

    /** 结清信息 **/
    private String clean_up_info;

    /** 银行 **/
    private String refund_bank;

    /** 银行卡号 **/
    private String refund_number;

    /** 户主姓名 **/
    private String refund_name;

    /** 已还期数 **/
    private Integer repay_period;

    /** 剩余还款金额 **/
    private BigDecimal un_pay_principal;

    /** 贷款产品1信贷2房贷3车贷 **/
    private String cre_type;

    /** 短信发送号码：多个用逗号隔开 **/
    private String send_message_number;

    /** 短信发送时间 **/
    private Timestamp send_message_time;

    /** 单据还款状态1、正常2、逾期3、结清4、续贷7、移交公司 **/
    private String repay_status;

    /** 贷款单据主键 **/
    private Integer  wms_cre_credit_head_id;

	/** 还款日期 **/
	private java.sql.Date current_repay_date;

	/** 发送短信息时间 **/
	private Timestamp send_message_date;
	
	/** 应还款金额 **/
    private String should_repay_principal;

    /** 业务状态： 01 新增、02 展期、03 续期 **/
    private String bill_type;

    /** 次数： 单据类型对应的次数 （展期和续贷次数） **/
    private String the_number;

    /**组合贷关联时间**/
    private Timestamp group_date;

    /** 组合贷编号 **/
    private String bill_code_group;

    /** 业务员的id **/
    private Integer salesman_id;
    
    /** 业务员所在部门id **/
    private Integer salesman_dept_id;

    /** 业务员所在部门id**/
    private Integer sales_epartment_id;

    /** 城市 **/
    private String city;

    /**业务员所在部门id**/
    private Integer salesman_own_dept_id;

    /**业务员所在部门编码**/
    private String salesman_own_dept_code;

    /**业务员所在部门id**/
    private String salesman_city_code;

    /**是否计算业绩 0：否；1：是**/
    private String is_achievement;

    /**是否计算存量 0：否；1：是**/
    private String is_stock;

    public void setWms_cre_credit_notary_warn_id(Integer wms_cre_credit_notary_warn_id) {
        this.wms_cre_credit_notary_warn_id = wms_cre_credit_notary_warn_id;
    }

    public Integer getWms_cre_credit_notary_warn_id() {
        return wms_cre_credit_notary_warn_id;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_status(String bill_status) {
        this.bill_status = bill_status;
    }

    public String getBill_status() {
        return bill_status;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_reason(String end_reason) {
        this.end_reason = end_reason;
    }

    public String getEnd_reason() {
        return end_reason;
    }

    public void setEnd_day_number(Integer end_day_number) {
        this.end_day_number = end_day_number;
    }

    public Integer getEnd_day_number() {
        return end_day_number;
    }

    public void setAppl_date(Date appl_date) {
        this.appl_date = appl_date;
    }

    public Date getAppl_date() {
        return appl_date;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getId_card() {
        return id_card;
    }

    public void setCurrent_address_more(String current_address_more) {
        this.current_address_more = current_address_more;
    }

    public String getCurrent_address_more() {
        return current_address_more;
    }

    public void setMobile_telephone(String mobile_telephone) {
        this.mobile_telephone = mobile_telephone;
    }

    public String getMobile_telephone() {
        return mobile_telephone;
    }

    public void setCom_debtor_name(String com_debtor_name) {
        this.com_debtor_name = com_debtor_name;
    }

    public String getCom_debtor_name() {
        return com_debtor_name;
    }

    public void setCom_debtor_identity_id(String com_debtor_identity_id) {
        this.com_debtor_identity_id = com_debtor_identity_id;
    }

    public String getCom_debtor_identity_id() {
        return com_debtor_identity_id;
    }

    public void setCom_debtor_tel(String com_debtor_tel) {
        this.com_debtor_tel = com_debtor_tel;
    }

    public String getCom_debtor_tel() {
        return com_debtor_tel;
    }

    public void setDebtor_fixed_line(String debtor_fixed_line) {
        this.debtor_fixed_line = debtor_fixed_line;
    }

    public String getDebtor_fixed_line() {
        return debtor_fixed_line;
    }

    public void setProtocol_date(Date protocol_date) {
        this.protocol_date = protocol_date;
    }

    public Date getProtocol_date() {
        return protocol_date;
    }

    public void setBorrow_end_date(Date borrow_end_date) {
        this.borrow_end_date = borrow_end_date;
    }

    public Date getBorrow_end_date() {
        return borrow_end_date;
    }

    public void setProtocol_id_year_num(String protocol_id_year_num) {
        this.protocol_id_year_num = protocol_id_year_num;
    }

    public String getProtocol_id_year_num() {
        return protocol_id_year_num;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setBorrow_interest(String borrow_interest) {
        this.borrow_interest = borrow_interest;
    }

    public String getBorrow_interest() {
        return borrow_interest;
    }

    public void setAppro_limit(String appro_limit) {
        this.appro_limit = appro_limit;
    }

    public String getAppro_limit() {
        return appro_limit;
    }

    public void setBorrow_deadline(Integer borrow_deadline) {
        this.borrow_deadline = borrow_deadline;
    }

    public Integer getBorrow_deadline() {
        return borrow_deadline;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setPlatform_fee(String platform_fee) {
        this.platform_fee = platform_fee;
    }

    public String getPlatform_fee() {
        return platform_fee;
    }

    public void setNotarial_fee(String notarial_fee) {
        this.notarial_fee = notarial_fee;
    }

    public String getNotarial_fee() {
        return notarial_fee;
    }

    public void setPrincipal_lower(String principal_lower) {
        this.principal_lower = principal_lower;
    }

    public String getPrincipal_lower() {
        return principal_lower;
    }

    public void setRefund_limit_month(String refund_limit_month) {
        this.refund_limit_month = refund_limit_month;
    }

    public String getRefund_limit_month() {
        return refund_limit_month;
    }

    public void setSalesman_name(String salesman_name) {
        this.salesman_name = salesman_name;
    }

    public String getSalesman_name() {
        return salesman_name;
    }

    public void setSalesman_shortcode(String salesman_shortcode) {
        this.salesman_shortcode = salesman_shortcode;
    }

    public String getSalesman_shortcode() {
        return salesman_shortcode;
    }

    public void setSalesman_dept_code(String salesman_dept_code) {
        this.salesman_dept_code = salesman_dept_code;
    }

    public String getSalesman_dept_code() {
        return salesman_dept_code;
    }

    public void setTeam_manager_name(String team_manager_name) {
        this.team_manager_name = team_manager_name;
    }

    public String getTeam_manager_name() {
        return team_manager_name;
    }

    public void setTeam_manager_code(String team_manager_code) {
        this.team_manager_code = team_manager_code;
    }

    public String getTeam_manager_code() {
        return team_manager_code;
    }

    public void setSales_epartment_code(String sales_epartment_code) {
        this.sales_epartment_code = sales_epartment_code;
    }

    public String getSales_epartment_code() {
        return sales_epartment_code;
    }

    public void setSales_epartment_name(String sales_epartment_name) {
        this.sales_epartment_name = sales_epartment_name;
    }

    public String getSales_epartment_name() {
        return sales_epartment_name;
    }

    public void setCreate_user_id(Integer create_user_id) {
        this.create_user_id = create_user_id;
    }

    public Integer getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_name(String create_user_name) {
        this.create_user_name = create_user_name;
    }

    public String getCreate_user_name() {
        return create_user_name;
    }

    public void setCreate_timestamp(Timestamp create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    public Timestamp getCreate_timestamp() {
        return create_timestamp;
    }

    public void setLast_update_user_id(Integer last_update_user_id) {
        this.last_update_user_id = last_update_user_id;
    }

    public Integer getLast_update_user_id() {
        return last_update_user_id;
    }

    public void setLast_update_timestamp(Timestamp last_update_timestamp) {
        this.last_update_timestamp = last_update_timestamp;
    }

    public Timestamp getLast_update_timestamp() {
        return last_update_timestamp;
    }

    public void setEnable_flag(String enable_flag) {
        this.enable_flag = enable_flag;
    }

    public String getEnable_flag() {
        return enable_flag;
    }

    public void setWarn_remak(String warn_remak) {
        this.warn_remak = warn_remak;
    }

    public String getWarn_remak() {
        return warn_remak;
    }

    public void setAlreadyse_send_message_number(Integer alreadyse_send_message_number) {
        this.alreadyse_send_message_number = alreadyse_send_message_number;
    }

    public Integer getAlreadyse_send_message_number() {
        return alreadyse_send_message_number;
    }

    public void setIs_weekend_not_remind(Integer is_weekend_not_remind) {
        this.is_weekend_not_remind = is_weekend_not_remind;
    }

    public Integer getIs_weekend_not_remind() {
        return is_weekend_not_remind;
    }

    public void setData_sources(String data_sources) {
        this.data_sources = data_sources;
    }

    public String getData_sources() {
        return data_sources;
    }

    public void setIs_cycles(String is_cycles) {
        this.is_cycles = is_cycles;
    }

    public String getIs_cycles() {
        return is_cycles;
    }

    public void setClean_up_date(Date clean_up_date) {
        this.clean_up_date = clean_up_date;
    }

    public Date getClean_up_date() {
        return clean_up_date;
    }

    public void setClean_up_info(String clean_up_info) {
        this.clean_up_info = clean_up_info;
    }

    public String getClean_up_info() {
        return clean_up_info;
    }

    public void setRefund_bank(String refund_bank) {
        this.refund_bank = refund_bank;
    }

    public String getRefund_bank() {
        return refund_bank;
    }

    public void setRefund_number(String refund_number) {
        this.refund_number = refund_number;
    }

    public String getRefund_number() {
        return refund_number;
    }

    public void setRefund_name(String refund_name) {
        this.refund_name = refund_name;
    }

    public String getRefund_name() {
        return refund_name;
    }

    public void setRepay_period(Integer repay_period) {
        this.repay_period = repay_period;
    }

    public Integer getRepay_period() {
        return repay_period;
    }

    public void setUn_pay_principal(BigDecimal un_pay_principal) {
        this.un_pay_principal = un_pay_principal;
    }

    public BigDecimal getUn_pay_principal() {
        return un_pay_principal;
    }

    public void setCre_type(String cre_type) {
        this.cre_type = cre_type;
    }

    public String getCre_type() {
        return cre_type;
    }

    public void setSend_message_number(String send_message_number) {
        this.send_message_number = send_message_number;
    }

    public String getSend_message_number() {
        return send_message_number;
    }

    public void setSend_message_time(Timestamp send_message_time) {
        this.send_message_time = send_message_time;
    }

    public Timestamp getSend_message_time() {
        return send_message_time;
    }

    public void setRepay_status(String repay_status) {
        this.repay_status = repay_status;
    }

    public String getRepay_status() {
        return repay_status;
    }

	public Integer getWms_cre_credit_head_id() {
		return wms_cre_credit_head_id;
	}

	public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
		this.wms_cre_credit_head_id = wms_cre_credit_head_id;
	}

	public java.sql.Date getCurrent_repay_date() {
		return current_repay_date;
	}

	public void setCurrent_repay_date(java.sql.Date current_repay_date) {
		this.current_repay_date = current_repay_date;
	}

	public Timestamp getSend_message_date() {
		return send_message_date;
	}

	public void setSend_message_date(Timestamp send_message_date) {
		this.send_message_date = send_message_date;
	}
	
	public String getShould_repay_principal()
    {
        return should_repay_principal;
    }

    public void setShould_repay_principal(String should_repay_principal)
    {
        this.should_repay_principal = should_repay_principal;
    }

    public String getBill_type()
    {
        return bill_type;
    }

    public void setBill_type(String bill_type)
    {
        this.bill_type = bill_type;
    }

    public String getThe_number()
    {
        return the_number;
    }

    public void setThe_number(String the_number)
    {
        this.the_number = the_number;
    }

    public Timestamp getGroup_date()
    {
        return group_date;
    }

    public void setGroup_date(Timestamp group_date)
    {
        this.group_date = group_date;
    }

    public String getBill_code_group()
    {
        return bill_code_group;
    }

    public void setBill_code_group(String bill_code_group)
    {
        this.bill_code_group = bill_code_group;
    }

    public Integer getSalesman_id()
    {
        return salesman_id;
    }

    public void setSalesman_id(Integer salesman_id)
    {
        this.salesman_id = salesman_id;
    }

    public Integer getSalesman_dept_id()
    {
        return salesman_dept_id;
    }

    public void setSalesman_dept_id(Integer salesman_dept_id)
    {
        this.salesman_dept_id = salesman_dept_id;
    }

    public Integer getSales_epartment_id()
    {
        return sales_epartment_id;
    }

    public void setSales_epartment_id(Integer sales_epartment_id)
    {
        this.sales_epartment_id = sales_epartment_id;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Integer getSalesman_own_dept_id()
    {
        return salesman_own_dept_id;
    }

    public void setSalesman_own_dept_id(Integer salesman_own_dept_id)
    {
        this.salesman_own_dept_id = salesman_own_dept_id;
    }

    public String getSalesman_own_dept_code()
    {
        return salesman_own_dept_code;
    }

    public void setSalesman_own_dept_code(String salesman_own_dept_code)
    {
        this.salesman_own_dept_code = salesman_own_dept_code;
    }

    public String getSalesman_city_code()
    {
        return salesman_city_code;
    }

    public void setSalesman_city_code(String salesman_city_code)
    {
        this.salesman_city_code = salesman_city_code;
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

    @Override  
	public boolean equals(Object obj) {  
	    WmsCreCreditNotaryWarn bean = (WmsCreCreditNotaryWarn)obj;   
    	return ((StringUtils.isEmpty(id_card) && StringUtils.isEmpty(bean.getId_card())) || id_card.equals(bean.getId_card())) &&
    	        ((StringUtils.isEmpty(mobile_telephone) && StringUtils.isEmpty(bean.getMobile_telephone())) || mobile_telephone.equals(bean.getMobile_telephone()) &&
    	        ((StringUtils.isEmpty(refund_number) && StringUtils.isEmpty(bean.getRefund_number())) || refund_number.equals(bean.getRefund_number())));   
	} 
	
	@Override  
	public int hashCode() {  
	    String in = id_card + mobile_telephone + refund_number;  
	    return in.hashCode();  
	}  
	
	

}