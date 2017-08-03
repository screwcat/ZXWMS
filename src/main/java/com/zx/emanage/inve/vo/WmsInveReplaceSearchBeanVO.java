package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveReplaceSearchBeanVO extends GridParamBean {
	
	private String data_status;//单据状态
	
	private String cus_name;//客户姓名
	
	private String id_card;//有效证件
	
	private String data_type;//数据类型，退回查询时使用
	

	public String getData_status() {
		return data_status;
	}


	public void setData_status(String data_status) {
		this.data_status = data_status;
	}


	public String getCus_name() {
		return cus_name;
	}


	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}


	public String getId_card() {
		return id_card;
	}


	public void setId_card(String id_card) {
		this.id_card = id_card;
	}


	public String getData_type() {
		return data_type;
	}


	public void setData_type(String data_type) {
		this.data_type = data_type;
	}


	private static final long serialVersionUID = 1L;

}