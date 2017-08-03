package com.zx.emanage.util.gen.entity;

import com.zx.sframe.util.mybatis.BaseEntity;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.sql.Date;

public class WmsCreCreditRepayHistory implements BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 **/
    private Integer wms_cre_credit_repay_history_id;

    /** 提醒单据主键 **/
    private Integer wms_cre_credit_notary_warn_id;

    /** 还款序号 **/
    private String repay_history_code;

    /** 还款日期 **/
    private Date repay_date;

    /** 还款金额 **/
    private BigDecimal repay_principal;
    
    /** 实际还款金额 **/
    private BigDecimal actual_repay_principal;
    
    /** 还款期数 **/
    private Integer repay_no;

    /** 跟踪处理结果 **/
    private String track_handle_result;

    /** 是否是最新 1是0否 **/
    private String is_new;

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

    public void setWms_cre_credit_repay_history_id(Integer wms_cre_credit_repay_history_id) {
        this.wms_cre_credit_repay_history_id = wms_cre_credit_repay_history_id;
    }

    public Integer getWms_cre_credit_repay_history_id() {
        return wms_cre_credit_repay_history_id;
    }

    public void setWms_cre_credit_notary_warn_id(Integer wms_cre_credit_notary_warn_id) {
        this.wms_cre_credit_notary_warn_id = wms_cre_credit_notary_warn_id;
    }

    public Integer getWms_cre_credit_notary_warn_id() {
        return wms_cre_credit_notary_warn_id;
    }

    public void setRepay_history_code(String repay_history_code) {
        this.repay_history_code = repay_history_code;
    }

    public String getRepay_history_code() {
        return repay_history_code;
    }

    public void setRepay_date(Date repay_date) {
        this.repay_date = repay_date;
    }

    public Date getRepay_date() {
        return repay_date;
    }

    public void setRepay_principal(BigDecimal repay_principal) {
        this.repay_principal = repay_principal;
    }

    public BigDecimal getRepay_principal() {
        return repay_principal;
    }

    public void setRepay_no(Integer repay_no) {
        this.repay_no = repay_no;
    }

    public Integer getRepay_no() {
        return repay_no;
    }

    public void setTrack_handle_result(String track_handle_result) {
        this.track_handle_result = track_handle_result;
    }

    public String getTrack_handle_result() {
        return track_handle_result;
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

	public BigDecimal getActual_repay_principal() {
		return actual_repay_principal;
	}

	public void setActual_repay_principal(BigDecimal actual_repay_principal) {
		this.actual_repay_principal = actual_repay_principal;
	}

}