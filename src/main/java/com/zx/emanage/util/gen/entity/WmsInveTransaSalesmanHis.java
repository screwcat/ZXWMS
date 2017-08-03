package com.zx.emanage.util.gen.entity;

import com.zx.sframe.util.mybatis.BaseEntity;

/**
 * 理财上单业务人员历史表
 * 
 * @author Administrator
 * 
 */
public class WmsInveTransaSalesmanHis implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8309331002613954212L;

	private Integer wms_inve_transa_salesman_his_id;

	private Integer wms_inve_transa_id;

	private Integer salesman_id;

	private Integer salesman_dept_id;

	private Integer department_manager_id;

	private Integer department_manager_dept_id;

	private Integer vice_general_manager_id;

	private Integer vice_general_manager_dept_id;

	private Integer general_manager_id;

	private Integer general_manager_dept_id;

	private Integer center_manager_id;

	private Integer center_manager_dept_id;

	private String remark;

	private Integer create_user_id;

	private java.sql.Timestamp create_timestamp;

	public Integer getWms_inve_transa_salesman_his_id() {
		return wms_inve_transa_salesman_his_id;
	}

	public void setWms_inve_transa_salesman_his_id(
			Integer wms_inve_transa_salesman_his_id) {
		this.wms_inve_transa_salesman_his_id = wms_inve_transa_salesman_his_id;
	}

	public Integer getWms_inve_transa_id() {
		return wms_inve_transa_id;
	}

	public void setWms_inve_transa_id(Integer wms_inve_transa_id) {
		this.wms_inve_transa_id = wms_inve_transa_id;
	}

	public Integer getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(Integer salesman_id) {
		this.salesman_id = salesman_id;
	}

	public Integer getSalesman_dept_id() {
		return salesman_dept_id;
	}

	public void setSalesman_dept_id(Integer salesman_dept_id) {
		this.salesman_dept_id = salesman_dept_id;
	}

	public Integer getDepartment_manager_id() {
		return department_manager_id;
	}

	public void setDepartment_manager_id(Integer department_manager_id) {
		this.department_manager_id = department_manager_id;
	}

	public Integer getDepartment_manager_dept_id() {
		return department_manager_dept_id;
	}

	public void setDepartment_manager_dept_id(Integer department_manager_dept_id) {
		this.department_manager_dept_id = department_manager_dept_id;
	}

	public Integer getVice_general_manager_id() {
		return vice_general_manager_id;
	}

	public void setVice_general_manager_id(Integer vice_general_manager_id) {
		this.vice_general_manager_id = vice_general_manager_id;
	}

	public Integer getVice_general_manager_dept_id() {
		return vice_general_manager_dept_id;
	}

	public void setVice_general_manager_dept_id(
			Integer vice_general_manager_dept_id) {
		this.vice_general_manager_dept_id = vice_general_manager_dept_id;
	}

	public Integer getGeneral_manager_id() {
		return general_manager_id;
	}

	public void setGeneral_manager_id(Integer general_manager_id) {
		this.general_manager_id = general_manager_id;
	}

	public Integer getGeneral_manager_dept_id() {
		return general_manager_dept_id;
	}

	public void setGeneral_manager_dept_id(Integer general_manager_dept_id) {
		this.general_manager_dept_id = general_manager_dept_id;
	}

	public Integer getCenter_manager_id() {
		return center_manager_id;
	}

	public void setCenter_manager_id(Integer center_manager_id) {
		this.center_manager_id = center_manager_id;
	}

	public Integer getCenter_manager_dept_id() {
		return center_manager_dept_id;
	}

	public void setCenter_manager_dept_id(Integer center_manager_dept_id) {
		this.center_manager_dept_id = center_manager_dept_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(Integer create_user_id) {
		this.create_user_id = create_user_id;
	}

	public java.sql.Timestamp getCreate_timestamp() {
		return create_timestamp;
	}

	public void setCreate_timestamp(java.sql.Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

}
