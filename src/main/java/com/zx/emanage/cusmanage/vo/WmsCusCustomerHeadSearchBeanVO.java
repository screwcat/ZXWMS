package com.zx.emanage.cusmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCusCustomerHeadSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private String customer_name;// 客户姓名

    private String mobile_telephone;// 客户电话

    private String id_card;// 身份证号

    private String create_timestamp;// 创建时间

    private String status;// 客户信息录入状态(0:暂存，1:提交）
    
    private String syncCustomerInfoFlag;//0:不同步  1:同步
    
    /** 类型:保存0 修改1 */
    private Integer type;
    
    /** 客户id */
    private Integer wms_cre_credit_line_customer_change_head_id;
    
    /** 客户信息(json) */
    private String customerInfo;
    
    /** 子女信息(json) */
    private String childInfo;
    
    /** 房产信息(json) */
    private String houseInfo;
    
    /** 工作信息(json) */
    private String workInfo;
    
    /** 公司信息(json) */
    private String companyInfo;
    
    /** 删除的子女信息id数组 */
    private Integer deleteChildId[];

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getSyncCustomerInfoFlag() {
        return syncCustomerInfoFlag;
    }

    public void setSyncCustomerInfoFlag(String syncCustomerInfoFlag) {
        this.syncCustomerInfoFlag = syncCustomerInfoFlag;
    }

    public String getCustomer_name()
    {
        return customer_name;
    }

    public void setCustomer_name(String customer_name)
    {
        this.customer_name = customer_name;
    }

    public String getMobile_telephone()
    {
        return mobile_telephone;
    }

    public void setMobile_telephone(String mobile_telephone)
    {
        this.mobile_telephone = mobile_telephone;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String id_card)
    {
        this.id_card = id_card;
    }

    public String getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(String create_timestamp)
    {
        this.create_timestamp = create_timestamp;
    }
    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getWms_cre_credit_line_customer_change_head_id() {
        return wms_cre_credit_line_customer_change_head_id;
    }

    public void setWms_cre_credit_line_customer_change_head_id(
            Integer wms_cre_credit_line_customer_change_head_id) {
        this.wms_cre_credit_line_customer_change_head_id = wms_cre_credit_line_customer_change_head_id;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getChildInfo() {
        return childInfo;
    }

    public void setChildInfo(String childInfo) {
        this.childInfo = childInfo;
    }

    public String getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
    }

    public String getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(String workInfo) {
        this.workInfo = workInfo;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public Integer[] getDeleteChildId() {
        return deleteChildId;
    }

    public void setDeleteChildId(Integer[] deleteChildId) {
        this.deleteChildId = deleteChildId;
    }

}