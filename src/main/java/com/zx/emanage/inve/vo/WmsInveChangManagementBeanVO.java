package com.zx.emanage.inve.vo;

import java.util.List;
import java.util.Map;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveChangManagementBeanVO
 * 模块名称：变更申请
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年4月11日
 * @version V3.5
 * history:
 * 1、2017年4月11日 zhangmingjian 创建文件
 */
public class WmsInveChangManagementBeanVO
{
    private String griddata;

    private String attinfo;

    private Integer personnel_id;

    private Integer page;

    private Integer pagesize;

    private String create_user_name;

    private String create_timestamp_begin;

    private String create_timestamp_end;

    private String id;

    private String[] ids;
    
    private String old_wms_inve_customer_card_id;

    // 变更申请单id
    private Integer wms_inve_change_app_id;

    /**
     * 客户姓名
     */
    private String cus_name;

    /**
     * 身份证号
     */
    private String id_card;

    /**
     * 上单表主键
     */
    private String wms_inve_transa_id;


    /**
     * 变更类型
     */
    
    private String change_type;

    /**
     * 变更后银行卡主键
     */
    private String wms_inve_customer_card_id;

    /**
     * 单据状态
     */
    private String data_status;
    
    /**
     * 申请单据编号
     */
    private String bill_code;

    /**
     * 联系电话
     */
    private String   mobile_phone;

    /**
     * 变更事由
     */
    private String   remark;


    private Integer create_user_id;

    private String   create_timestamp;

    private Integer last_update_user_id;

    private String last_update_timestamp;
    
    /**
     * 卡主姓名
     */
    private String card_owner_name;

    /**
     * 银行名称
     */
    private String bank_of_deposit;

    /**
     * 银行账号
     */
    private String card_no;

    /**
     * 开户行(省)
     */
    private String bank_of_deposit_pro;

    /**
     * 开户行(市)
     */
    private String bank_of_deposit_city;

    /**
     * 开户行所属支行
     */
    private String bank_branch;
    
    private List<Map<String, Object>> datagrid;

    private List<Map<String, Object>> attrinfo;

    /**
     * @return the bill_code
     */
    public String getBill_code()
    {
        return bill_code;
    }

    /**
     * @param bill_code the bill_code to set
     */
    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    /**
     * @return the change_type
     */
    public String getChange_type()
    {
        return change_type;
    }

    /**
     * @param change_type the change_type to set
     */
    public void setChange_type(String change_type)
    {
        this.change_type = change_type;
    }

    /**
     * @return the wms_inve_customer_card_id
     */
    public String getWms_inve_customer_card_id()
    {
        return wms_inve_customer_card_id;
    }

    /**
     * @param wms_inve_customer_card_id the wms_inve_customer_card_id to set
     */
    public void setWms_inve_customer_card_id(String wms_inve_customer_card_id)
    {
        this.wms_inve_customer_card_id = wms_inve_customer_card_id;
    }

    /**
     * @return the data_status
     */
    public String getData_status()
    {
        return data_status;
    }

    /**
     * @param data_status the data_status to set
     */
    public void setData_status(String data_status)
    {
        this.data_status = data_status;
    }

    /**
     * @return the mobile_phone
     */
    public String getMobile_phone()
    {
        return mobile_phone;
    }

    /**
     * @param mobile_phone the mobile_phone to set
     */
    public void setMobile_phone(String mobile_phone)
    {
        this.mobile_phone = mobile_phone;
    }

    /**
     * @return the remark
     */
    public String getRemark()
    {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    /**
     * @return the create_user_id
     */
    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    /**
     * @param create_user_id the create_user_id to set
     */
    public void setCreate_user_id(Integer create_user_id)
    {
        this.create_user_id = create_user_id;
    }

    /**
     * @return the create_timestamp
     */
    public String getCreate_timestamp()
    {
        return create_timestamp;
    }

    /**
     * @param create_timestamp the create_timestamp to set
     */
    public void setCreate_timestamp(String create_timestamp)
    {
        this.create_timestamp = create_timestamp;
    }

    /**
     * @return the last_update_user_id
     */
    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    /**
     * @param last_update_user_id the last_update_user_id to set
     */
    public void setLast_update_user_id(Integer last_update_user_id)
    {
        this.last_update_user_id = last_update_user_id;
    }

    /**
     * @return the last_update_timestamp
     */
    public String getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    /**
     * @param last_update_timestamp the last_update_timestamp to set
     */
    public void setLast_update_timestamp(String last_update_timestamp)
    {
        this.last_update_timestamp = last_update_timestamp;
    }

    /**
     * @return the card_owner_name
     */
    public String getCard_owner_name()
    {
        return card_owner_name;
    }

    /**
     * @param card_owner_name the card_owner_name to set
     */
    public void setCard_owner_name(String card_owner_name)
    {
        this.card_owner_name = card_owner_name;
    }

    /**
     * @return the bank_of_deposit
     */
    public String getBank_of_deposit()
    {
        return bank_of_deposit;
    }

    /**
     * @param bank_of_deposit the bank_of_deposit to set
     */
    public void setBank_of_deposit(String bank_of_deposit)
    {
        this.bank_of_deposit = bank_of_deposit;
    }

    /**
     * @return the card_no
     */
    public String getCard_no()
    {
        return card_no;
    }

    /**
     * @param card_no the card_no to set
     */
    public void setCard_no(String card_no)
    {
        this.card_no = card_no;
    }

    /**
     * @return the bank_of_deposit_pro
     */
    public String getBank_of_deposit_pro()
    {
        return bank_of_deposit_pro;
    }

    /**
     * @param bank_of_deposit_pro the bank_of_deposit_pro to set
     */
    public void setBank_of_deposit_pro(String bank_of_deposit_pro)
    {
        this.bank_of_deposit_pro = bank_of_deposit_pro;
    }

    /**
     * @return the bank_of_deposit_city
     */
    public String getBank_of_deposit_city()
    {
        return bank_of_deposit_city;
    }

    /**
     * @param bank_of_deposit_city the bank_of_deposit_city to set
     */
    public void setBank_of_deposit_city(String bank_of_deposit_city)
    {
        this.bank_of_deposit_city = bank_of_deposit_city;
    }

    /**
     * @return the bank_branch
     */
    public String getBank_branch()
    {
        return bank_branch;
    }

    /**
     * @param bank_branch the bank_branch to set
     */
    public void setBank_branch(String bank_branch)
    {
        this.bank_branch = bank_branch;
    }

    /**
     * @return the wms_inve_transa_id
     */
    public String getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    /**
     * @param wms_inve_transa_id the wms_inve_transa_id to set
     */
    public void setWms_inve_transa_id(String wms_inve_transa_id)
    {
        this.wms_inve_transa_id = wms_inve_transa_id;
    }

    /**
     * @return the page
     */
    public Integer getPage()
    {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Integer page)
    {
        this.page = page;
    }

    /**
     * @return the pagesize
     */
    public Integer getPagesize()
    {
        return pagesize;
    }

    /**
     * @param pagesize the pagesize to set
     */
    public void setPagesize(Integer pagesize)
    {
        this.pagesize = pagesize;
    }

    /**
     * @return the cus_name
     */
    public String getCus_name()
    {
        return cus_name;
    }

    /**
     * @param cus_name the cus_name to set
     */
    public void setCus_name(String cus_name)
    {
        this.cus_name = cus_name;
    }

    /**
     * @return the id_card
     */
    public String getId_card()
    {
        return id_card;
    }

    /**
     * @param id_card the id_card to set
     */
    public void setId_card(String id_card)
    {
        this.id_card = id_card;
    }

    /**
     * @return the griddata
     */
    public String getGriddata()
    {
        return griddata;
    }

    /**
     * @param griddata the griddata to set
     */
    public void setGriddata(String griddata)
    {
        this.griddata = griddata;
    }

    /**
     * @return the attinfo
     */
    public String getAttinfo()
    {
        return attinfo;
    }

    /**
     * @param attinfo the attinfo to set
     */
    public void setAttinfo(String attinfo)
    {
        this.attinfo = attinfo;
    }

    /**
     * @return the datagrid
     */
    public List<Map<String, Object>> getDatagrid()
    {
        return datagrid;
    }

    /**
     * @param datagrid the datagrid to set
     */
    public void setDatagrid(List<Map<String, Object>> datagrid)
    {
        this.datagrid = datagrid;
    }

    /**
     * @return the attrinfo
     */
    public List<Map<String, Object>> getAttrinfo()
    {
        return attrinfo;
    }

    /**
     * @param attrinfo the attrinfo to set
     */
    public void setAttrinfo(List<Map<String, Object>> attrinfo)
    {
        this.attrinfo = attrinfo;
    }

    /**
     * @return the wms_inve_change_app_id
     */
    public Integer getWms_inve_change_app_id()
    {
        return wms_inve_change_app_id;
    }

    /**
     * @param wms_inve_change_app_id the wms_inve_change_app_id to set
     */
    public void setWms_inve_change_app_id(Integer wms_inve_change_app_id)
    {
        this.wms_inve_change_app_id = wms_inve_change_app_id;
    }

    /**
     * @return the personnel_id
     */
    public Integer getPersonnel_id()
    {
        return personnel_id;
    }

    /**
     * @param personnel_id the personnel_id to set
     */
    public void setPersonnel_id(Integer personnel_id)
    {
        this.personnel_id = personnel_id;
    }

    /**
     * @return the create_user_name
     */
    public String getCreate_user_name()
    {
        return create_user_name;
    }

    /**
     * @param create_user_name the create_user_name to set
     */
    public void setCreate_user_name(String create_user_name)
    {
        this.create_user_name = create_user_name;
    }

    /**
     * @return the create_timestamp_begin
     */
    public String getCreate_timestamp_begin()
    {
        return create_timestamp_begin;
    }

    /**
     * @param create_timestamp_begin the create_timestamp_begin to set
     */
    public void setCreate_timestamp_begin(String create_timestamp_begin)
    {
        this.create_timestamp_begin = create_timestamp_begin;
    }

    /**
     * @return the create_timestamp_end
     */
    public String getCreate_timestamp_end()
    {
        return create_timestamp_end;
    }

    /**
     * @param create_timestamp_end the create_timestamp_end to set
     */
    public void setCreate_timestamp_end(String create_timestamp_end)
    {
        this.create_timestamp_end = create_timestamp_end;
    }

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return the ids
     */
    public String[] getIds()
    {
        return ids;
    }

    /**
     * @param ids the ids to set
     */
    public void setIds(String[] ids)
    {
        this.ids = ids;
    }
    
    private String flag;

    /**
     * @return the flag
     */
    public String getFlag()
    {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    /**
     * @return the old_wms_inve_customer_card_id
     */
    public String getOld_wms_inve_customer_card_id()
    {
        return old_wms_inve_customer_card_id;
    }

    /**
     * @param old_wms_inve_customer_card_id the old_wms_inve_customer_card_id to set
     */
    public void setOld_wms_inve_customer_card_id(String old_wms_inve_customer_card_id)
    {
        this.old_wms_inve_customer_card_id = old_wms_inve_customer_card_id;
    }
    
    
}
