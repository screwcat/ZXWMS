package com.zx.emanage.util.gen.entity;

import com.zx.sframe.util.mybatis.BaseEntity;
import java.sql.Timestamp;
import java.math.BigDecimal;

public class WmsCreHousingAgainAppl implements BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 **/
    private Integer wms_cre_housing_again_appl_id;

    /** 贷款产品类型_原始 记录复议前贷款申请的产品 **/
    private String cre_loan_type_old;

    /** 贷款产品类型 新的产品类型 **/
    private String cre_loan_type;

    /** 还款期限_原始 **/
    private Integer max_repayment_time_limit_old;

    /** 还款期限 **/
    private Integer max_repayment_time_limit;

    /** 申请贷款额度_原始 **/
    private BigDecimal credit_limit_old;

    /** 申请贷款额度 **/
    private BigDecimal credit_limit;

    /** 贷款利率_原始 **/
    private BigDecimal loan_interest_rate_old;

    /** 贷款利率 **/
    private BigDecimal loan_interest_rate;

    /** 业务员id_原始 **/
    private Integer salesman_id_old;

    /** 业务员id **/
    private Integer salesman_id;

    /** 业务员姓名_原始 **/
    private String salesman_name_old;

    /** 业务员姓名 **/
    private String salesman_name;

    /** 业务员城市编码_原始 **/
    private String salesman_city_code_old;

    /** 业务员所在部门ID **/
    private Integer salesman_dept_id_old;

    /** 业务员城市编码 **/
    private String salesman_city_code;

    /** 业务员所在部门ID **/
    private Integer salesman_dept_id;

    /** 申请复议原因 **/
    private String again_appl_reason;

    /** 备注信息 **/
    private String again_appl_remark;

    /** 创建人 **/
    private Integer create_user_id;

    /** 创建人姓名 **/
    private String create_user_name;

    /** 创建时间 **/
    private Timestamp create_timestamp;

    /** 上次修改人 **/
    private Integer last_update_user_id;

    /** 上次修改时间 **/
    private Timestamp last_update_timestamp;

    /** 是否有效 **/
    private String enable_flag;

    /** 信用贷款主表 **/
    private Integer wms_cre_credit_head_id;

    public void setWms_cre_housing_again_appl_id(Integer wms_cre_housing_again_appl_id) {
        this.wms_cre_housing_again_appl_id = wms_cre_housing_again_appl_id;
    }

    public Integer getWms_cre_housing_again_appl_id() {
        return wms_cre_housing_again_appl_id;
    }

    public void setCre_loan_type_old(String cre_loan_type_old) {
        this.cre_loan_type_old = cre_loan_type_old;
    }

    public String getCre_loan_type_old() {
        return cre_loan_type_old;
    }

    public void setCre_loan_type(String cre_loan_type) {
        this.cre_loan_type = cre_loan_type;
    }

    public String getCre_loan_type() {
        return cre_loan_type;
    }

    public void setMax_repayment_time_limit_old(Integer max_repayment_time_limit_old) {
        this.max_repayment_time_limit_old = max_repayment_time_limit_old;
    }

    public Integer getMax_repayment_time_limit_old() {
        return max_repayment_time_limit_old;
    }

    public void setMax_repayment_time_limit(Integer max_repayment_time_limit) {
        this.max_repayment_time_limit = max_repayment_time_limit;
    }

    public Integer getMax_repayment_time_limit() {
        return max_repayment_time_limit;
    }

    public void setCredit_limit_old(BigDecimal credit_limit_old) {
        this.credit_limit_old = credit_limit_old;
    }

    public BigDecimal getCredit_limit_old() {
        return credit_limit_old;
    }

    public void setCredit_limit(BigDecimal credit_limit) {
        this.credit_limit = credit_limit;
    }

    public BigDecimal getCredit_limit() {
        return credit_limit;
    }

    public void setLoan_interest_rate_old(BigDecimal loan_interest_rate_old) {
        this.loan_interest_rate_old = loan_interest_rate_old;
    }

    public BigDecimal getLoan_interest_rate_old() {
        return loan_interest_rate_old;
    }

    public void setLoan_interest_rate(BigDecimal loan_interest_rate) {
        this.loan_interest_rate = loan_interest_rate;
    }

    public BigDecimal getLoan_interest_rate() {
        return loan_interest_rate;
    }

    public void setSalesman_id_old(Integer salesman_id_old) {
        this.salesman_id_old = salesman_id_old;
    }

    public Integer getSalesman_id_old() {
        return salesman_id_old;
    }

    public void setSalesman_id(Integer salesman_id) {
        this.salesman_id = salesman_id;
    }

    public Integer getSalesman_id() {
        return salesman_id;
    }

    public void setSalesman_name_old(String salesman_name_old) {
        this.salesman_name_old = salesman_name_old;
    }

    public String getSalesman_name_old() {
        return salesman_name_old;
    }

    public void setSalesman_name(String salesman_name) {
        this.salesman_name = salesman_name;
    }

    public String getSalesman_name() {
        return salesman_name;
    }

    public void setSalesman_city_code_old(String salesman_city_code_old) {
        this.salesman_city_code_old = salesman_city_code_old;
    }

    public String getSalesman_city_code_old() {
        return salesman_city_code_old;
    }

    public void setSalesman_dept_id_old(Integer salesman_dept_id_old) {
        this.salesman_dept_id_old = salesman_dept_id_old;
    }

    public Integer getSalesman_dept_id_old() {
        return salesman_dept_id_old;
    }

    public void setSalesman_city_code(String salesman_city_code) {
        this.salesman_city_code = salesman_city_code;
    }

    public String getSalesman_city_code() {
        return salesman_city_code;
    }

    public void setSalesman_dept_id(Integer salesman_dept_id) {
        this.salesman_dept_id = salesman_dept_id;
    }

    public Integer getSalesman_dept_id() {
        return salesman_dept_id;
    }

    public void setAgain_appl_reason(String again_appl_reason) {
        this.again_appl_reason = again_appl_reason;
    }

    public String getAgain_appl_reason() {
        return again_appl_reason;
    }

    public void setAgain_appl_remark(String again_appl_remark) {
        this.again_appl_remark = again_appl_remark;
    }

    public String getAgain_appl_remark() {
        return again_appl_remark;
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

    public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

    public Integer getWms_cre_credit_head_id() {
        return wms_cre_credit_head_id;
    }

}