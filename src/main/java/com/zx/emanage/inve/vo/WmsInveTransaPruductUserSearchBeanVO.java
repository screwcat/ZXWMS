package com.zx.emanage.inve.vo;


import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveTransaPruductUserSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;
	
	private String wmsInveTransaPruductUserSearchBeanVOList_JSON;
	
	private Integer wms_inve_transa_pruduct_user_id;
	
	private String deleteIds;

	private String userIds;

	private String userNames;
	
	private String shortCodes;
	
	private String productIds;
	
	private String productNames;
	
	private Integer group_info;

    public String getWmsInveTransaPruductUserSearchBeanVOList_JSON() {
        return wmsInveTransaPruductUserSearchBeanVOList_JSON;
    }

    public void setWmsInveTransaPruductUserSearchBeanVOList_JSON(
            String wmsInveTransaPruductUserSearchBeanVOList_JSON) {
        this.wmsInveTransaPruductUserSearchBeanVOList_JSON = wmsInveTransaPruductUserSearchBeanVOList_JSON;
    }

    public Integer getWms_inve_transa_pruduct_user_id() {
        return wms_inve_transa_pruduct_user_id;
    }

    public void setWms_inve_transa_pruduct_user_id(
            Integer wms_inve_transa_pruduct_user_id) {
        this.wms_inve_transa_pruduct_user_id = wms_inve_transa_pruduct_user_id;
    }

    public String getDeleteIds() {
        return deleteIds;
    }

    public void setDeleteIds(String deleteIds) {
        this.deleteIds = deleteIds;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public String getShortCodes() {
        return shortCodes;
    }

    public void setShortCodes(String shortCodes) {
        this.shortCodes = shortCodes;
    }

	public Integer getGroup_info() {
		return group_info;
	}

	public void setGroup_info(Integer group_info) {
		this.group_info = group_info;
	}
}