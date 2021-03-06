package com.zx.emanage.cremanage.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineContactSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineContact;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineContactVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCustomerChangeLineContactService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo);

    /**
     * 实现对客户联系人信息查询
     * 
     * @param wms_cre_credit_head_id
     * @param wms_cre_credit_line_customer_change_head_id
     * @return record list
     * @author hancd
     */
    public Map<String, Object> getContactsListWithPagingFK(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCustomerChangeLineContactVO
     * @author auto_generator
     */
    public WmsCreCustomerChangeLineContactVO getInfoByPK(Integer wms_cre_customer_change_line_contact_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCustomerChangeLineContact bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCustomerChangeLineContact bean, UserBean user);

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String delete(WmsCreCustomerChangeLineContact bean);

    /**
     * 根据贷款单据id和客户变更表id获取对应的联系人信息
     * 
     * @param queryInfo
     * @author 张风山
     */
    public List<com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact> getContactList(com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact queryInfo);
    
    /**
     * 查询联系人信息
     */
    public Map<String, Object> searchList(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo);
/**
 * 3.37	发送授信确认详细信息 
 * @param queryInfo
 * @param attList 
 */
    public void sendCreditConfirmInfo(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo, String save_type);

/**
 * @Title: getContactsInfoList
 * @Description: TODO(根据headid查询联系人信息)
 * @param wms_cre_credit_head_id
 * @return 
 * @author: jiaodelong
 * @time:2017年2月17日 上午11:58:41
 * history:
 * 1、2017年2月17日 jiaodelong 创建方法
*/
public Map<String, Object> getContactsInfoList(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo);

/**
 * @Title: getBizSavePerfectInfo
 * @Description: TODO(3.2.18        客户联系人信息保存)
 * @param vo
 * @param save_type 
 * @author: jiaodelong
 * @time:2017年3月17日 下午5:00:38
 * history:
 * 1、2017年3月17日 jiaodelong 创建方法
*/
public void getBizSavePerfectInfo(WmsCreCustomerChangeLineContactSearchBeanVO vo, String save_type); 
    
}