package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveTransaAttSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_transa_id;
	
	private Integer data_type_name;

	public Integer getWms_inve_transa_id() {
		return wms_inve_transa_id;
	}

	public void setWms_inve_transa_id(Integer wms_inve_transa_id) {
		this.wms_inve_transa_id = wms_inve_transa_id;
	}

	public Integer getData_type_name() {
		return data_type_name;
	}

	public void setData_type_name(Integer data_type_name) {
		this.data_type_name = data_type_name;
	}
	

}