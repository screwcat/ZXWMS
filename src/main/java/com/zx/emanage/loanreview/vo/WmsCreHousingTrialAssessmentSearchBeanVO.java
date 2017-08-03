package com.zx.emanage.loanreview.vo;

import java.math.BigDecimal;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCreHousingTrialAssessmentSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private String wmsCreHousingTrialAssessment_String;
	
	private String taskId;
	
	private Integer wms_cre_credit_head_id;
	
	private String flag;
	
	private String approval_result;
	
	private String approval_advice;
	
	private Integer wms_cre_credit_line_customer_change_head_id;
	
	private String house_buy_money;
	
	private String vehicle_evaluation_person;
	
	private String customer_age;
	
	private String houses_number;
	
	private String house_age;//房屋年龄
	private String house_remark;//备注
	private String is_compound;//是否复式
	/** 1:旧流程   2:新流程 **/
	private String edition_num;
	private String check_pay;//核查缴费金额（元）

    private BigDecimal old_appro_limit;// 曾终审金额";

    private String is_again_appl;// "是否重新审批 1是0否";
	

	/**
	 * @return the check_pay
	 */
	public String getCheck_pay() {
		return check_pay;
	}

	/**
	 * @param check_pay the check_pay to set
	 */
	public void setCheck_pay(String check_pay) {
		this.check_pay = check_pay;
	}

    public String getWmsCreHousingTrialAssessment_String() {
        return wmsCreHousingTrialAssessment_String;
    }

    public void setWmsCreHousingTrialAssessment_String(
            String wmsCreHousingTrialAssessment_String) {
        this.wmsCreHousingTrialAssessment_String = wmsCreHousingTrialAssessment_String;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getWms_cre_credit_head_id() {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getApproval_result() {
        return approval_result;
    }

    public void setApproval_result(String approval_result) {
        this.approval_result = approval_result;
    }

    public String getApproval_advice() {
        return approval_advice;
    }

    public void setApproval_advice(String approval_advice) {
        this.approval_advice = approval_advice;
    }

    public String getEdition_num() {
        return edition_num;
    }

    public void setEdition_num(String edition_num) {
        this.edition_num = edition_num;
    }

    public Integer getWms_cre_credit_line_customer_change_head_id() {
        return wms_cre_credit_line_customer_change_head_id;
    }

    public void setWms_cre_credit_line_customer_change_head_id(
            Integer wms_cre_credit_line_customer_change_head_id) {
        this.wms_cre_credit_line_customer_change_head_id = wms_cre_credit_line_customer_change_head_id;
    }

    public String getHouse_buy_money() {
        return house_buy_money;
    }

    public void setHouse_buy_money(String house_buy_money) {
        this.house_buy_money = house_buy_money;
    }

	public String getVehicle_evaluation_person() {
		return vehicle_evaluation_person;
	}

	public void setVehicle_evaluation_person(String vehicle_evaluation_person) {
		this.vehicle_evaluation_person = vehicle_evaluation_person;
	}

	public String getCustomer_age() {
		return customer_age;
	}

	public void setCustomer_age(String customer_age) {
		this.customer_age = customer_age;
	}

	public String getHouses_number() {
		return houses_number;
	}

	public void setHouses_number(String houses_number) {
		this.houses_number = houses_number;
	}

	public String getHouse_age() {
		return house_age;
	}

	public String getHouse_remark() {
		return house_remark;
	}

	public String getIs_compound() {
		return is_compound;
	}

	public void setHouse_age(String house_age) {
		this.house_age = house_age;
	}

	public void setHouse_remark(String house_remark) {
		this.house_remark = house_remark;
	}

	public void setIs_compound(String is_compound) {
		this.is_compound = is_compound;
	}

    public BigDecimal getOld_appro_limit()
    {
        return old_appro_limit;
    }

    public void setOld_appro_limit(BigDecimal old_appro_limit)
    {
        this.old_appro_limit = old_appro_limit;
    }

    public String getIs_again_appl()
    {
        return is_again_appl;
    }

    public void setIs_again_appl(String is_again_appl)
    {
        this.is_again_appl = is_again_appl;
    }
    
	
}