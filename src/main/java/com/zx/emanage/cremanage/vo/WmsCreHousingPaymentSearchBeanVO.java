package com.zx.emanage.cremanage.vo;

import java.math.BigDecimal;

import com.zx.platform.syscontext.vo.GridParamBean;

public class WmsCreHousingPaymentSearchBeanVO extends GridParamBean  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer wms_cre_credit_head_id;
	
	private String customer_name;
	
	private String application_date;
	
	private String mobile_telephone;
	
	private String community_name;
	
	private String salesman_name;
	
	private Integer salesman_id;
	
	private String team_manager_name;
	
	private Integer team_manager_id;
	
	private String house_address;
	
	private BigDecimal payment_amount;
	
	private String taskId;// 任务节点ID.
	
	private String version_number;// 版本号

    // 房产核查缴费主键
    private Integer wms_cre_housing_payment_id;
	
	public Integer getWms_cre_credit_head_id() {
		return wms_cre_credit_head_id;
	}

	public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
		this.wms_cre_credit_head_id = wms_cre_credit_head_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getApplication_date() {
		return application_date;
	}

	public void setApplication_date(String application_date) {
		this.application_date = application_date;
	}

	public String getMobile_telephone() {
		return mobile_telephone;
	}

	public void setMobile_telephone(String mobile_telephone) {
		this.mobile_telephone = mobile_telephone;
	}

	public String getCommunity_name() {
		return community_name;
	}

	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}

	public String getSalesman_name() {
		return salesman_name;
	}

	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}

	public Integer getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(Integer salesman_id) {
		this.salesman_id = salesman_id;
	}

	public String getTeam_manager_name() {
		return team_manager_name;
	}

	public void setTeam_manager_name(String team_manager_name) {
		this.team_manager_name = team_manager_name;
	}

	public Integer getTeam_manager_id() {
		return team_manager_id;
	}

	public void setTeam_manager_id(Integer team_manager_id) {
		this.team_manager_id = team_manager_id;
	}

	public String getHouse_address() {
		return house_address;
	}

	public void setHouse_address(String house_address) {
		this.house_address = house_address;
	}

	public BigDecimal getPayment_amount() {
		return payment_amount;
	}

	public void setPayment_amount(BigDecimal payment_amount) {
		this.payment_amount = payment_amount;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getVersion_number() {
		return version_number;
	}

	public void setVersion_number(String version_number) {
		this.version_number = version_number;
	}

    public Integer getWms_cre_housing_payment_id()
    {
        return wms_cre_housing_payment_id;
    }

    public void setWms_cre_housing_payment_id(Integer wms_cre_housing_payment_id)
    {
        this.wms_cre_housing_payment_id = wms_cre_housing_payment_id;
    }

}
