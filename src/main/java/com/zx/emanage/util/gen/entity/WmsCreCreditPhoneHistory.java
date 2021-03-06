package com.zx.emanage.util.gen.entity;

import com.zx.sframe.util.mybatis.BaseEntity;
import java.sql.Timestamp;

public class WmsCreCreditPhoneHistory implements BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 **/
    private Integer wms_cre_credit_phone_history_id;

    /** 提醒单据主键 **/
    private Integer wms_cre_credit_notary_warn_id;

    /** 拨打电话序号 **/
    private String phone_history_code;

    /** 拨打电话时间 **/
    private Timestamp phone_date;

    /** 是否是最新 1是0否 **/
    private String is_new;

    /** 拨打电话记录结果 **/
    private String phone_handle_result;

    /** 创建人id **/
    private Integer create_user_id;

    /** 创建人姓名 **/
    private String create_user_name;

    /** 创建时间 **/
    private Timestamp create_timestamp;

    /** 最后更改人id **/
    private Integer last_update_user_id;

    /** 最后更改时间 **/
    private Timestamp last_update_timestamp;

    /** 数据状态 1有效 0无效 **/
    private String enable_flag;
    /** 电话记录类型 1公证到期提醒  0还款提醒*/
    private String phone_history_type;
    public void setWms_cre_credit_phone_history_id(Integer wms_cre_credit_phone_history_id) {
        this.wms_cre_credit_phone_history_id = wms_cre_credit_phone_history_id;
    }

    public Integer getWms_cre_credit_phone_history_id() {
        return wms_cre_credit_phone_history_id;
    }

    public void setWms_cre_credit_notary_warn_id(Integer wms_cre_credit_notary_warn_id) {
        this.wms_cre_credit_notary_warn_id = wms_cre_credit_notary_warn_id;
    }

    public Integer getWms_cre_credit_notary_warn_id() {
        return wms_cre_credit_notary_warn_id;
    }

    public void setPhone_history_code(String phone_history_code) {
        this.phone_history_code = phone_history_code;
    }

    public String getPhone_history_code() {
        return phone_history_code;
    }

    public void setPhone_date(Timestamp phone_date) {
        this.phone_date = phone_date;
    }

    public Timestamp getPhone_date() {
        return phone_date;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setPhone_handle_result(String phone_handle_result) {
        this.phone_handle_result = phone_handle_result;
    }

    public String getPhone_handle_result() {
        return phone_handle_result;
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

    public String getPhone_history_type()
    {
        return phone_history_type;
    }

    public void setPhone_history_type(String phone_history_type)
    {
        this.phone_history_type = phone_history_type;
    }


}