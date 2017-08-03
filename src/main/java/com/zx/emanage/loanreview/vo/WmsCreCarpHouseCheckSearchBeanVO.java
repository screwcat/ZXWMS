package com.zx.emanage.loanreview.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCreCarpHouseCheckSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	private String bill_code;// 单据编号

    private String salesman_name;// 业务员

    private String create_timestamp;// 申请时间

    private String create_timestamp1;// 申请时间区间

    private String id_card;// 身份证号

    private String flag;// 是否为贷款复核

    private String flag_water;// 流水审批 1

    private String flag_info;// 信息审批 2

    private String flag_phone;// 电审审批 3

    private String flag_certificate;// 征信审批 4

    private String flag_yd;// 验点审批5

    private String customer_name;// 客户姓名

    private String mobile_telephone;// 手机号码

    private String cre_type;// 产品类型

    private String city;// 所属城市

    private String bill_status;// 单据状态
    
    private String dept_city_sel;//所属城市

    private String dept_name_sel;//所属门店

	public String getBill_code() {
		return bill_code;
	}

	public void setBill_code(String bill_code) {
		this.bill_code = bill_code;
	}

	public String getSalesman_name() {
		return salesman_name;
	}

	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}

	public String getCreate_timestamp() {
		return create_timestamp;
	}

	public void setCreate_timestamp(String create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	public String getCreate_timestamp1() {
		return create_timestamp1;
	}

	public void setCreate_timestamp1(String create_timestamp1) {
		this.create_timestamp1 = create_timestamp1;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag_water() {
		return flag_water;
	}

	public void setFlag_water(String flag_water) {
		this.flag_water = flag_water;
	}

	public String getFlag_info() {
		return flag_info;
	}

	public void setFlag_info(String flag_info) {
		this.flag_info = flag_info;
	}

	public String getFlag_phone() {
		return flag_phone;
	}

	public void setFlag_phone(String flag_phone) {
		this.flag_phone = flag_phone;
	}

	public String getFlag_certificate() {
		return flag_certificate;
	}

	public void setFlag_certificate(String flag_certificate) {
		this.flag_certificate = flag_certificate;
	}

	public String getFlag_yd() {
		return flag_yd;
	}

	public void setFlag_yd(String flag_yd) {
		this.flag_yd = flag_yd;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getMobile_telephone() {
		return mobile_telephone;
	}

	public void setMobile_telephone(String mobile_telephone) {
		this.mobile_telephone = mobile_telephone;
	}

	public String getCre_type() {
		return cre_type;
	}

	public void setCre_type(String cre_type) {
		this.cre_type = cre_type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBill_status() {
		return bill_status;
	}

	public void setBill_status(String bill_status) {
		this.bill_status = bill_status;
	}

	public String getDept_city_sel() {
		return dept_city_sel;
	}

	public void setDept_city_sel(String dept_city_sel) {
		this.dept_city_sel = dept_city_sel;
	}

	public String getDept_name_sel() {
		return dept_name_sel;
	}

	public void setDept_name_sel(String dept_name_sel) {
		this.dept_name_sel = dept_name_sel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}