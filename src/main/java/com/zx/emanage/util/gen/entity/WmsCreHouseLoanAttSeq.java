package com.zx.emanage.util.gen.entity;

import com.zx.sframe.util.mybatis.BaseEntity;

public class WmsCreHouseLoanAttSeq implements BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 **/
    private String wms_cre_house_loan_att_seq_name;

    /** 单据编号 **/
    private String bill_code;

    /** 附件小类 **/
    private Integer data_detail_name;

    /** 当前序列值 **/
    private Integer current_value;

    /** 自增步长 **/
    private Integer increment;

    public void setWms_cre_house_loan_att_seq_name(String wms_cre_house_loan_att_seq_name) {
        this.wms_cre_house_loan_att_seq_name = wms_cre_house_loan_att_seq_name;
    }

    public String getWms_cre_house_loan_att_seq_name() {
        return wms_cre_house_loan_att_seq_name;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public String getBill_code() {
        return bill_code;
    }

    public Integer getData_detail_name() {
        return data_detail_name;
    }

    public void setData_detail_name(Integer data_detail_name) {
        this.data_detail_name = data_detail_name;
    }

    public void setCurrent_value(Integer current_value) {
        this.current_value = current_value;
    }

    public Integer getCurrent_value() {
        return current_value;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

    public Integer getIncrement() {
        return increment;
    }

}