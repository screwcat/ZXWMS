package com.zx.emanage.util.gen.entity;

import java.sql.Timestamp;

import com.zx.sframe.util.mybatis.BaseEntity;

public class WmsCreCreditHeadLog implements BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 **/
    private Integer wms_cre_credit_head_log_id;

    /** 操作人id **/
    private Integer create_user_id;

    /** 操作人姓名 **/
    private String create_user_name;

    /** 操作时间 **/
    private Timestamp create_timestamp;

    /** 数据状态 **/
    private String enable_flag;

    /** 贷款主表主键 **/
    private Integer wms_cre_credit_head_id;

    /** 单据主表上一版历史信息json字符串(bean) **/
    private String wms_cre_credit_head_json;

    /** 客户信息变更表上一版历史信息json字符串(list) **/
    private String wms_cre_credit_line_customer_change_head_json;

    /** 客户房产信息变更表上一版历史信息json字符串(list) **/
    private String wms_cre_customer_change_line_houseinfo_json;

    /** 客户工作信息变更表上一版历史信息json字符串(list) **/
    private String wms_cre_customer_change_line_workinfo_json;

    /** 客户公司信息变更表上一版历史信息json字符串(list) **/
    private String wms_cre_customer_change_line_company_json;

    /** 客户联系人信息变更表上一版历史信息json字符串(list) **/
    private String wms_cre_customer_change_line_contact_json;

    /** 客户子女信息变更表上一版历史信息json字符串(list) **/
    private String wms_cus_customer_change_child_json;

    /** 客户上传附件表上一版历史信息json字符串(list) **/
    private String wms_cre_housing_apply_att_json;

    /** 借款合同表上一版历史信息json字符串(bean) **/
    private String wms_cre_appro_borrow_protocol_json;

    /** 1:修改客户信息 2:修改房贷单据 3:放款申请 4:补录 **/
    private String log_type;

    /** 备注 **/
    private String remark;

    public void setWms_cre_credit_head_log_id(Integer wms_cre_credit_head_log_id) {
        this.wms_cre_credit_head_log_id = wms_cre_credit_head_log_id;
    }

    public Integer getWms_cre_credit_head_log_id() {
        return wms_cre_credit_head_log_id;
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

    public void setWms_cre_credit_head_json(String wms_cre_credit_head_json) {
        this.wms_cre_credit_head_json = wms_cre_credit_head_json;
    }

    public String getWms_cre_credit_head_json() {
        return wms_cre_credit_head_json;
    }

    public void setWms_cre_credit_line_customer_change_head_json(String wms_cre_credit_line_customer_change_head_json) {
        this.wms_cre_credit_line_customer_change_head_json = wms_cre_credit_line_customer_change_head_json;
    }

    public String getWms_cre_credit_line_customer_change_head_json() {
        return wms_cre_credit_line_customer_change_head_json;
    }

    public void setWms_cre_customer_change_line_houseinfo_json(String wms_cre_customer_change_line_houseinfo_json) {
        this.wms_cre_customer_change_line_houseinfo_json = wms_cre_customer_change_line_houseinfo_json;
    }

    public String getWms_cre_customer_change_line_houseinfo_json() {
        return wms_cre_customer_change_line_houseinfo_json;
    }

    public void setWms_cre_customer_change_line_workinfo_json(String wms_cre_customer_change_line_workinfo_json) {
        this.wms_cre_customer_change_line_workinfo_json = wms_cre_customer_change_line_workinfo_json;
    }

    public String getWms_cre_customer_change_line_workinfo_json() {
        return wms_cre_customer_change_line_workinfo_json;
    }

    public void setWms_cre_customer_change_line_company_json(String wms_cre_customer_change_line_company_json) {
        this.wms_cre_customer_change_line_company_json = wms_cre_customer_change_line_company_json;
    }

    public String getWms_cre_customer_change_line_company_json() {
        return wms_cre_customer_change_line_company_json;
    }

    public void setWms_cre_customer_change_line_contact_json(String wms_cre_customer_change_line_contact_json) {
        this.wms_cre_customer_change_line_contact_json = wms_cre_customer_change_line_contact_json;
    }

    public String getWms_cre_customer_change_line_contact_json() {
        return wms_cre_customer_change_line_contact_json;
    }

    public void setWms_cus_customer_change_child_json(String wms_cus_customer_change_child_json) {
        this.wms_cus_customer_change_child_json = wms_cus_customer_change_child_json;
    }

    public String getWms_cus_customer_change_child_json() {
        return wms_cus_customer_change_child_json;
    }

    public void setWms_cre_housing_apply_att_json(String wms_cre_housing_apply_att_json) {
        this.wms_cre_housing_apply_att_json = wms_cre_housing_apply_att_json;
    }

    public String getWms_cre_housing_apply_att_json() {
        return wms_cre_housing_apply_att_json;
    }

    public void setWms_cre_appro_borrow_protocol_json(String wms_cre_appro_borrow_protocol_json) {
        this.wms_cre_appro_borrow_protocol_json = wms_cre_appro_borrow_protocol_json;
    }

    public String getWms_cre_appro_borrow_protocol_json() {
        return wms_cre_appro_borrow_protocol_json;
    }

    public void setLog_type(String log_type) {
        this.log_type = log_type;
    }

    public String getLog_type() {
        return log_type;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

}