package com.zx.emanage.util.gen.entity;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsInveRedeem implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_redeem_id;
	
	private Integer wms_inve_customer_id;
	
	private String bill_code;
	
	private String data_status;
	
	private String id_card;
	
	private String cus_name;
	
	private java.math.BigDecimal total_invest_amount;
	
	private String redeem_reason;
	
	private java.sql.Date redeem_date;
	
	private String redeem_date_str;
	
	private String supervisor_advice;
	
	private String supervisor_result;
	
	private java.sql.Date supervisor_date;
	
	private String supervisor_date_str;
	
	private String submanager_advice;
	
	private String submanager_result;
	
	private java.sql.Date submanager_date;
	
	private String submanager_date_str;
	
	private String manager_advice;
	
	private String manager_result;
	
	private java.sql.Date manager_date;
	
	private String manager_date_str;
	
	private String is_special_approval;
	
	private Integer special_approval_operator_id;
	
	private java.sql.Date special_approval_date;
	
	private String special_approval_date_str;
	
	private Integer special_approval_leader_id;
	
	private String special_approval_leader_name;
	
	private String special_approval_advice;
	
	private String redeem_payback_card_name;
	
	private String redeem_payback_card_no;
	
	private java.math.BigDecimal redeem_apply_total_amount;
	
	private java.math.BigDecimal total_due_income;
	
	private java.math.BigDecimal total_management_fee;
	
	private java.math.BigDecimal redeem_reality_total_amount;
	
	private java.sql.Date payback_date;
	
	private String payback_date_str;
	
	private Integer payback_operator_id;
	
	private String is_finish;
	
	private String is_fully_redeemed;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	private Integer create_user_dept_id;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
	private String enable_flag;
	
	private String is_protocol_finish;
	
	private Integer salesman_id;
	
	private Integer salesman_dept_id;
	
	private String is_payback;
	
	private String special_approval_result;
	
	private java.math.BigDecimal total_deduct_tax_point;
	
	private java.math.BigDecimal total_deduct_money;
	
	private String redeem_company_reason;
	
	private String is_take_off_damages;
	
	private String redeem_type;
	
	private String redeem_type_detail;
	
	private String redeem_reality_total_amount_upper;
	
	private Integer org_wms_inve_redeem_id;
    
    private String is_order_redeem;

    private String get_credit_type;

    private String auth_person;

    private String auth_reason;
  
	public Integer getWms_inve_redeem_id () {
		return wms_inve_redeem_id;
	}
	
	public void setWms_inve_redeem_id (Integer obj) {
		wms_inve_redeem_id = obj;
	}
	
	public Integer getWms_inve_customer_id () {
		return wms_inve_customer_id;
	}
	
	public void setWms_inve_customer_id (Integer obj) {
		wms_inve_customer_id = obj;
	}
	
	public String getBill_code () {
		return bill_code;
	}
	
	public void setBill_code (String obj) {
		bill_code = obj;
	}
	
	public String getData_status () {
		return data_status;
	}
	
	public void setData_status (String obj) {
		data_status = obj;
	}
	
	public String getId_card () {
		return id_card;
	}
	
	public void setId_card (String obj) {
		id_card = obj;
	}
	
	public String getCus_name () {
		return cus_name;
	}
	
	public void setCus_name (String obj) {
		cus_name = obj;
	}
	
	public java.math.BigDecimal getTotal_invest_amount () {
		return total_invest_amount;
	}
	
	public void setTotal_invest_amount (java.math.BigDecimal obj) {
		total_invest_amount = obj;
	}
	
	public String getRedeem_reason () {
		return redeem_reason;
	}
	
	public void setRedeem_reason (String obj) {
		redeem_reason = obj;
	}
	
	public java.sql.Date getRedeem_date () {
		return redeem_date;
	}
	
	public void setRedeem_date (java.sql.Date obj) {
		redeem_date = obj;
	}
	
	public String getRedeem_date_str () {
		return redeem_date_str;
	}
	
	public void setRedeem_date_str (String obj) {
		redeem_date_str = obj;
	}
	
	public String getSupervisor_advice () {
		return supervisor_advice;
	}
	
	public void setSupervisor_advice (String obj) {
		supervisor_advice = obj;
	}
	
	public String getSupervisor_result () {
		return supervisor_result;
	}
	
	public void setSupervisor_result (String obj) {
		supervisor_result = obj;
	}
	
	public java.sql.Date getSupervisor_date () {
		return supervisor_date;
	}
	
	public void setSupervisor_date (java.sql.Date obj) {
		supervisor_date = obj;
	}
	
	public String getSupervisor_date_str () {
		return supervisor_date_str;
	}
	
	public void setSupervisor_date_str (String obj) {
		supervisor_date_str = obj;
	}
	
	public String getSubmanager_advice () {
		return submanager_advice;
	}
	
	public void setSubmanager_advice (String obj) {
		submanager_advice = obj;
	}
	
	public String getSubmanager_result () {
		return submanager_result;
	}
	
	public void setSubmanager_result (String obj) {
		submanager_result = obj;
	}
	
	public java.sql.Date getSubmanager_date () {
		return submanager_date;
	}
	
	public void setSubmanager_date (java.sql.Date obj) {
		submanager_date = obj;
	}
	
	public String getSubmanager_date_str () {
		return submanager_date_str;
	}
	
	public void setSubmanager_date_str (String obj) {
		submanager_date_str = obj;
	}
	
	public String getManager_advice () {
		return manager_advice;
	}
	
	public void setManager_advice (String obj) {
		manager_advice = obj;
	}
	
	public String getManager_result () {
		return manager_result;
	}
	
	public void setManager_result (String obj) {
		manager_result = obj;
	}
	
	public java.sql.Date getManager_date () {
		return manager_date;
	}
	
	public void setManager_date (java.sql.Date obj) {
		manager_date = obj;
	}
	
	public String getManager_date_str () {
		return manager_date_str;
	}
	
	public void setManager_date_str (String obj) {
		manager_date_str = obj;
	}
	
	public String getIs_special_approval () {
		return is_special_approval;
	}
	
	public void setIs_special_approval (String obj) {
		is_special_approval = obj;
	}
	
	public Integer getSpecial_approval_operator_id () {
		return special_approval_operator_id;
	}
	
	public void setSpecial_approval_operator_id (Integer obj) {
		special_approval_operator_id = obj;
	}
	
	public java.sql.Date getSpecial_approval_date () {
		return special_approval_date;
	}
	
	public void setSpecial_approval_date (java.sql.Date obj) {
		special_approval_date = obj;
	}
	
	public String getSpecial_approval_date_str () {
		return special_approval_date_str;
	}
	
	public void setSpecial_approval_date_str (String obj) {
		special_approval_date_str = obj;
	}
	
	public Integer getSpecial_approval_leader_id () {
		return special_approval_leader_id;
	}
	
	public void setSpecial_approval_leader_id (Integer obj) {
		special_approval_leader_id = obj;
	}
	
	public String getSpecial_approval_leader_name () {
		return special_approval_leader_name;
	}
	
	public void setSpecial_approval_leader_name (String obj) {
		special_approval_leader_name = obj;
	}
	
	public String getSpecial_approval_advice () {
		return special_approval_advice;
	}
	
	public void setSpecial_approval_advice (String obj) {
		special_approval_advice = obj;
	}
	
	public String getRedeem_payback_card_name () {
		return redeem_payback_card_name;
	}
	
	public void setRedeem_payback_card_name (String obj) {
		redeem_payback_card_name = obj;
	}
	
	public String getRedeem_payback_card_no () {
		return redeem_payback_card_no;
	}
	
	public void setRedeem_payback_card_no (String obj) {
		redeem_payback_card_no = obj;
	}
	
	public java.math.BigDecimal getRedeem_apply_total_amount () {
		return redeem_apply_total_amount;
	}
	
	public void setRedeem_apply_total_amount (java.math.BigDecimal obj) {
		redeem_apply_total_amount = obj;
	}
	
	public java.math.BigDecimal getTotal_due_income () {
		return total_due_income;
	}
	
	public void setTotal_due_income (java.math.BigDecimal obj) {
		total_due_income = obj;
	}
	
	public java.math.BigDecimal getTotal_management_fee () {
		return total_management_fee;
	}
	
	public void setTotal_management_fee (java.math.BigDecimal obj) {
		total_management_fee = obj;
	}
	
	public java.math.BigDecimal getRedeem_reality_total_amount () {
		return redeem_reality_total_amount;
	}
	
	public void setRedeem_reality_total_amount (java.math.BigDecimal obj) {
		redeem_reality_total_amount = obj;
	}
	
	public java.sql.Date getPayback_date () {
		return payback_date;
	}
	
	public void setPayback_date (java.sql.Date obj) {
		payback_date = obj;
	}
	
	public String getPayback_date_str () {
		return payback_date_str;
	}
	
	public void setPayback_date_str (String obj) {
		payback_date_str = obj;
	}
	
	public Integer getPayback_operator_id () {
		return payback_operator_id;
	}
	
	public void setPayback_operator_id (Integer obj) {
		payback_operator_id = obj;
	}
	
	public String getIs_finish () {
		return is_finish;
	}
	
	public void setIs_finish (String obj) {
		is_finish = obj;
	}
	
	public String getIs_fully_redeemed () {
		return is_fully_redeemed;
	}
	
	public void setIs_fully_redeemed (String obj) {
		is_fully_redeemed = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
	}
	
	public String getCreate_user_name () {
		return create_user_name;
	}
	
	public void setCreate_user_name (String obj) {
		create_user_name = obj;
	}
	
	public Integer getCreate_user_dept_id () {
		return create_user_dept_id;
	}
	
	public void setCreate_user_dept_id (Integer obj) {
		create_user_dept_id = obj;
	}
	
	public java.sql.Timestamp getCreate_timestamp () {
		return create_timestamp;
	}
	
	public void setCreate_timestamp (java.sql.Timestamp obj) {
		create_timestamp = obj;
	}
	
	public String getCreate_timestamp_str () {
		return create_timestamp_str;
	}
	
	public void setCreate_timestamp_str (String obj) {
		create_timestamp_str = obj;
	}
	
	public Integer getLast_update_user_id () {
		return last_update_user_id;
	}
	
	public void setLast_update_user_id (Integer obj) {
		last_update_user_id = obj;
	}
	
	public java.sql.Timestamp getLast_update_timestamp () {
		return last_update_timestamp;
	}
	
	public void setLast_update_timestamp (java.sql.Timestamp obj) {
		last_update_timestamp = obj;
	}
	
	public String getLast_update_timestamp_str () {
		return last_update_timestamp_str;
	}
	
	public void setLast_update_timestamp_str (String obj) {
		last_update_timestamp_str = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public String getIs_protocol_finish () {
		return is_protocol_finish;
	}
	
	public void setIs_protocol_finish (String obj) {
		is_protocol_finish = obj;
	}
	
	public Integer getSalesman_id () {
		return salesman_id;
	}
	
	public void setSalesman_id (Integer obj) {
		salesman_id = obj;
	}
	
	public Integer getSalesman_dept_id () {
		return salesman_dept_id;
	}
	
	public void setSalesman_dept_id (Integer obj) {
		salesman_dept_id = obj;
	}
	
	public String getIs_payback () {
		return is_payback;
	}
	
	public void setIs_payback (String obj) {
		is_payback = obj;
	}
	
	public String getSpecial_approval_result () {
		return special_approval_result;
	}
	
	public void setSpecial_approval_result (String obj) {
		special_approval_result = obj;
	}
	
	public java.math.BigDecimal getTotal_deduct_tax_point () {
		return total_deduct_tax_point;
	}
	
	public void setTotal_deduct_tax_point (java.math.BigDecimal obj) {
		total_deduct_tax_point = obj;
	}
	
	public java.math.BigDecimal getTotal_deduct_money () {
		return total_deduct_money;
	}
	
	public void setTotal_deduct_money (java.math.BigDecimal obj) {
		total_deduct_money = obj;
	}
	
	public String getRedeem_company_reason () {
		return redeem_company_reason;
	}
	
	public void setRedeem_company_reason (String obj) {
		redeem_company_reason = obj;
	}
	
	public String getIs_take_off_damages () {
		return is_take_off_damages;
	}
	
	public void setIs_take_off_damages (String obj) {
		is_take_off_damages = obj;
	}
	
	public String getRedeem_type () {
		return redeem_type;
	}
	
	public void setRedeem_type (String obj) {
		redeem_type = obj;
	}
	
	public String getRedeem_type_detail () {
		return redeem_type_detail;
	}
	
	public void setRedeem_type_detail (String obj) {
		redeem_type_detail = obj;
	}
		
	public String getRedeem_reality_total_amount_upper() {
		return redeem_reality_total_amount_upper;
	}

	public void setRedeem_reality_total_amount_upper(
			String redeem_reality_total_amount_upper) {
		this.redeem_reality_total_amount_upper = redeem_reality_total_amount_upper;
	}

	public Integer getOrg_wms_inve_redeem_id()
	{
		return org_wms_inve_redeem_id;
	}

	public void setOrg_wms_inve_redeem_id(Integer org_wms_inve_redeem_id)
	{
		this.org_wms_inve_redeem_id = org_wms_inve_redeem_id;
	}

    public String getIs_order_redeem()
    {
        return is_order_redeem;
    }

    public void setIs_order_redeem(String is_order_redeem)
    {
        this.is_order_redeem = is_order_redeem;
    }

    public String getGet_credit_type()
    {
        return get_credit_type;
    }

    public void setGet_credit_type(String get_credit_type)
    {
        this.get_credit_type = get_credit_type;
    }

    public String getAuth_person()
    {
        return auth_person;
    }

    public void setAuth_person(String auth_person)
    {
        this.auth_person = auth_person;
    }

    public String getAuth_reason()
    {
        return auth_reason;
    }

    public void setAuth_reason(String auth_reason)
    {
        this.auth_reason = auth_reason;
    }
}