package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInvePruductCategorySearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private String category_name;

    private String is_forbidden;
   
    private  String gridBasicVal;//理财——产品利率设置返利表wms_inve_pruduct_acc_stock
    private  String gridLimitVal;//理财——产品赎回限制表wms_inve_pruduct_return
    private  String updateVal;//标识是哪种更新操作
    private  String category_code;//产品编号
    private  Integer wms_inve_pruduct_category_id;//产品主键
    private  String  operator_ip;//操作者IP地址
    private  Integer appro_result;//确认审批结果
    private  String  appro_advice;//确认审批意见
    public String getCategory_name()
    {
        return category_name;
    }

    public void setCategory_name(String category_name)
    {
        this.category_name = category_name;
    }

    public String getIs_forbidden()
    {
        return is_forbidden;
    }

    public void setIs_forbidden(String is_forbidden)
    {
        this.is_forbidden = is_forbidden;
    }

	public String getGridBasicVal() {
		return gridBasicVal;
	}

	public void setGridBasicVal(String gridBasicVal) {
		this.gridBasicVal = gridBasicVal;
	}

	public String getGridLimitVal() {
		return gridLimitVal;
	}

	public void setGridLimitVal(String gridLimitVal) {
		this.gridLimitVal = gridLimitVal;
	}

	public String getUpdateVal() {
		return updateVal;
	}

	public void setUpdateVal(String updateVal) {
		this.updateVal = updateVal;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public Integer getWms_inve_pruduct_category_id() {
		return wms_inve_pruduct_category_id;
	}

	public void setWms_inve_pruduct_category_id(Integer wms_inve_pruduct_category_id) {
		this.wms_inve_pruduct_category_id = wms_inve_pruduct_category_id;
	}

	public String getOperator_ip() {
		return operator_ip;
	}

	public void setOperator_ip(String operator_ip) {
		this.operator_ip = operator_ip;
	}

	public Integer getAppro_result() {
		return appro_result;
	}

	public void setAppro_result(Integer appro_result) {
		this.appro_result = appro_result;
	}

	public String getAppro_advice() {
		return appro_advice;
	}

	public void setAppro_advice(String appro_advice) {
		this.appro_advice = appro_advice;
	}

}