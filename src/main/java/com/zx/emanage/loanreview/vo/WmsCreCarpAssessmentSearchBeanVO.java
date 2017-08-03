package com.zx.emanage.loanreview.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCreCarpAssessmentSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	public String  taskId;//id
	public String fileArr;//上传文件
	public String saveVal;//标记暂存还是保存
	public String pass;
	public String advice;
	public String inspection_pass;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getFileArr() {
		return fileArr;
	}
	public void setFileArr(String fileArr) {
		this.fileArr = fileArr;
	}
	public String getSaveVal() {
		return saveVal;
	}
	public void setSaveVal(String saveVal) {
		this.saveVal = saveVal;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getInspection_pass() {
		return inspection_pass;
	}
	public void setInspection_pass(String inspection_pass) {
		this.inspection_pass = inspection_pass;
	}
}