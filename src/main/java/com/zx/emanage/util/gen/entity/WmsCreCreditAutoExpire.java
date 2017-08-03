package com.zx.emanage.util.gen.entity;

import java.sql.Timestamp;

import com.zx.sframe.util.mybatis.BaseEntity;

public class WmsCreCreditAutoExpire implements BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 **/
    private Integer wms_cre_credit_auto_expire_id;

    /** 本次延期天数 **/
    private Integer expire_days;

    /** 延期总天数 累加天数 不算默认天数 **/
    private Integer total_expire_days;

    /** 终审时间 **/
    private Timestamp appro_time;

    /** 操作人id **/
    private Integer operator_id;

    /** 操作人姓名 **/
    private String operator_name;

    /** 贷款单据主键 **/
    private Integer wms_cre_credit_head_id;

    /** 延期原因 **/
    private String expire_reason;

    /** 是否是最新 1 是 0否 **/
    private String is_new;

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

    /** 是否有效 1有效 0无效 **/
    private String enable_flag;

    public void setWms_cre_credit_auto_expire_id(Integer wms_cre_credit_auto_expire_id) {
        this.wms_cre_credit_auto_expire_id = wms_cre_credit_auto_expire_id;
    }

    public Integer getWms_cre_credit_auto_expire_id() {
        return wms_cre_credit_auto_expire_id;
    }

    public void setExpire_days(Integer expire_days) {
        this.expire_days = expire_days;
    }

    public Integer getExpire_days() {
        return expire_days;
    }

    public void setTotal_expire_days(Integer total_expire_days) {
        this.total_expire_days = total_expire_days;
    }

    public Integer getTotal_expire_days() {
        return total_expire_days;
    }

    public void setAppro_time(Timestamp appro_time) {
        this.appro_time = appro_time;
    }

    public Timestamp getAppro_time() {
        return appro_time;
    }

    public void setOperator_id(Integer operator_id) {
        this.operator_id = operator_id;
    }

    public Integer getOperator_id() {
        return operator_id;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

    public Integer getWms_cre_credit_head_id() {
        return wms_cre_credit_head_id;
    }

    public void setExpire_reason(String expire_reason) {
        this.expire_reason = expire_reason;
    }

    public String getExpire_reason() {
        return expire_reason;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getIs_new() {
        return is_new;
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

}