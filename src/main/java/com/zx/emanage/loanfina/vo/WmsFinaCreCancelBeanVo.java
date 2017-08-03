package com.zx.emanage.loanfina.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsFinaCreCancelBeanVo extends GridParamBean
{

    private static final long serialVersionUID = 1L;


    private Integer wms_cre_credit_head_id;

    private String advice;// 意见

    private String taskId;// id
    private String carkey;// id
    private String nullify_type;//标示当前节点
    private String version_number;//数据来源
    private java.sql.Date timestamp_val;//时间

	public Integer getWms_cre_credit_head_id() {
		return wms_cre_credit_head_id;
	}

	public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
		this.wms_cre_credit_head_id = wms_cre_credit_head_id;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public java.sql.Date getTimestamp_val() {
		return timestamp_val;
	}

	public void setTimestamp_val(java.sql.Date timestamp_val) {
		this.timestamp_val = timestamp_val;
	}

	public String getCarkey() {
		return carkey;
	}

	public void setCarkey(String carkey) {
		this.carkey = carkey;
	}

	public String getNullify_type() {
		return nullify_type;
	}

	public void setNullify_type(String nullify_type) {
		this.nullify_type = nullify_type;
	}

	public String getVersion_number() {
		return version_number;
	}

	public void setVersion_number(String version_number) {
		this.version_number = version_number;
	}
 
}