package com.zx.emanage.util.gen.entity;

import com.zx.sframe.util.mybatis.BaseEntity;
import java.sql.Timestamp;

public class WmsCreCreditSendMessageLog implements BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 **/
    private Integer wms_cre_credit_send_message_log_id;

    /** 提醒单据主键 **/
    private Integer wms_cre_credit_notary_warn_id;

    /** 发送短信息时间 **/
    private Timestamp send_message_date;

    /** 发送短信息参数内容 **/
    private String send_message_result;

    /** 发送短信息类型 1还款提醒 2生日提醒 **/
    private String send_message_type;

    /** 短信息模板编号 **/
    private String send_message_code;

    /** 发送短信息号码 **/
    private String send_message_phone_number;

    /** 创建人id **/
    private Integer create_user_id;

    /** 创建人姓名 **/
    private String create_user_name;

    /** 创建时间 **/
    private Timestamp create_timestamp;

    /** 数据状态 **/
    private String enable_flag;

    public void setWms_cre_credit_send_message_log_id(Integer wms_cre_credit_send_message_log_id) {
        this.wms_cre_credit_send_message_log_id = wms_cre_credit_send_message_log_id;
    }

    public Integer getWms_cre_credit_send_message_log_id() {
        return wms_cre_credit_send_message_log_id;
    }

    public void setWms_cre_credit_notary_warn_id(Integer wms_cre_credit_notary_warn_id) {
        this.wms_cre_credit_notary_warn_id = wms_cre_credit_notary_warn_id;
    }

    public Integer getWms_cre_credit_notary_warn_id() {
        return wms_cre_credit_notary_warn_id;
    }

    public void setSend_message_date(Timestamp send_message_date) {
        this.send_message_date = send_message_date;
    }

    public Timestamp getSend_message_date() {
        return send_message_date;
    }

    public void setSend_message_result(String send_message_result) {
        this.send_message_result = send_message_result;
    }

    public String getSend_message_result() {
        return send_message_result;
    }

    public void setSend_message_type(String send_message_type) {
        this.send_message_type = send_message_type;
    }

    public String getSend_message_type() {
        return send_message_type;
    }

    public void setSend_message_code(String send_message_code) {
        this.send_message_code = send_message_code;
    }

    public String getSend_message_code() {
        return send_message_code;
    }

    public void setSend_message_phone_number(String send_message_phone_number) {
        this.send_message_phone_number = send_message_phone_number;
    }

    public String getSend_message_phone_number() {
        return send_message_phone_number;
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

}