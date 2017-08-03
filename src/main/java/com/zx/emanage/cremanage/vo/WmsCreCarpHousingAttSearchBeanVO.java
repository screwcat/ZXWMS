package com.zx.emanage.cremanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCreCarpHousingAttSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	private Integer wms_cre_credit_head_id;
	private String data_type;
	private String fileArr;
	private String taskId;
	private String wms_cre_credit_head_id_id;
	
	public Integer getWms_cre_credit_head_id() {
		return wms_cre_credit_head_id;
	}
	public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id) {
		this.wms_cre_credit_head_id = wms_cre_credit_head_id;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getFileArr() {
		return fileArr;
	}
	public void setFileArr(String fileArr) {
		this.fileArr = fileArr;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getWms_cre_credit_head_id_id() {
		return wms_cre_credit_head_id_id;
	}
	public void setWms_cre_credit_head_id_id(String wms_cre_credit_head_id_id) {
		this.wms_cre_credit_head_id_id = wms_cre_credit_head_id_id;
	}
	
}