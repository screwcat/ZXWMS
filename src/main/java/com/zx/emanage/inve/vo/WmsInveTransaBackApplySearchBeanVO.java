package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveTransaBackApplySearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_transa_id;
	
	private Integer wms_inve_transa_prod_id;
	
	private String taskId;
	
	private Integer back_apply_result;
	
	private String back_apply_advice;

    public Integer getWms_inve_transa_id() {
        return wms_inve_transa_id;
    }

    public void setWms_inve_transa_id(Integer wms_inve_transa_id) {
        this.wms_inve_transa_id = wms_inve_transa_id;
    }

    public Integer getWms_inve_transa_prod_id() {
        return wms_inve_transa_prod_id;
    }

    public void setWms_inve_transa_prod_id(Integer wms_inve_transa_prod_id) {
        this.wms_inve_transa_prod_id = wms_inve_transa_prod_id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getBack_apply_result() {
        return back_apply_result;
    }

    public void setBack_apply_result(Integer back_apply_result) {
        this.back_apply_result = back_apply_result;
    }

    public String getBack_apply_advice() {
        return back_apply_advice;
    }

    public void setBack_apply_advice(String back_apply_advice) {
        this.back_apply_advice = back_apply_advice;
    }
	
	

}