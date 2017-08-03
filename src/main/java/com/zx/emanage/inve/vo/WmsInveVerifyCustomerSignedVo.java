package com.zx.emanage.inve.vo;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveVerifyCustomerSignedVo
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2017年7月18日
 * @version V1.0
 * history:
 * 1、2017年7月18日 DongHao 创建文件
 */
public class WmsInveVerifyCustomerSignedVo
{

    private String wms_inve_pruduct_category_id;// 产品id(多个产品id以逗号分隔)

    private Integer costomer_id;// crm客户id

    private String id_card_number;// 身份证号

    private String contact_number;// 电话号

    private String buy_type;// 购买类型

    public String getWms_inve_pruduct_category_id()
    {
        return wms_inve_pruduct_category_id;
    }

    public void setWms_inve_pruduct_category_id(String wms_inve_pruduct_category_id)
    {
        this.wms_inve_pruduct_category_id = wms_inve_pruduct_category_id;
    }

    public Integer getCostomer_id()
    {
        return costomer_id;
    }

    public void setCostomer_id(Integer costomer_id)
    {
        this.costomer_id = costomer_id;
    }

    public String getId_card_number()
    {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number)
    {
        this.id_card_number = id_card_number;
    }

    public String getContact_number()
    {
        return contact_number;
    }

    public void setContact_number(String contact_number)
    {
        this.contact_number = contact_number;
    }

    public String getBuy_type()
    {
        return buy_type;
    }

    public void setBuy_type(String buy_type)
    {
        this.buy_type = buy_type;
    }

}
