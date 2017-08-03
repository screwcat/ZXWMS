package com.zx.emanage.cusmanage.service;

import java.util.Map;

import com.zx.emanage.cusmanage.vo.WmsCusCustomerHeadSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCusCustomerHead;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCusCustomerHeadService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerHeadSearchBeanVO queryInfo);

    /**
     * Description :实现客户查询列表
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    public Map<String, Object> getListWithPaging(WmsCusCustomerHeadSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo and user with paging
     * 
     * @param queryInfo
     * @param user
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPagingforadd(WmsCusCustomerHeadSearchBeanVO queryInfo, UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerHeadVO
     * @author auto_generator
     */
    public WmsCusCustomerHead getInfoByPK(Integer wms_cus_customer_id);

    /**
     * 保存
     * 
     * @param bean
     * @return id
     */
    public int saveByPk(com.zx.emanage.util.gen.entity.WmsCusCustomerHead khxinfo,
                        com.zx.emanage.util.gen.entity.WmsCusCustomerLineWorkinfo gzinfo, String housestr,
                        UserBean user, String cusChild, String companystr, String carstr);

    /**
     * 更新
     * 
     * @param khxinfo
     * @param gzinfo
     * @param housestr
     * @param user
     * @return
     */
    public String updateHHW(com.zx.emanage.util.gen.entity.WmsCusCustomerHead khxinfo,
                            com.zx.emanage.util.gen.entity.WmsCusCustomerLineWorkinfo gzinfo, String housestr,
                            UserBean user, String delrowids, String delccrowids, String delcomprowids, String delChildIds, String cusChild,
                            String companystr, String carstr, WmsCusCustomerHeadSearchBeanVO queryInfo);

    public Map<String, Object> getListWithoutPagingforadd(WmsCusCustomerHeadSearchBeanVO queryInfo, Integer user_id);

    public Map<String, Object> getInfoMapByPK(Integer wms_cus_customer_id);

    /**
     * 实现通过客户表单主键删除相关所有客户信息 (软删除)
     * 
     * @param wms_cus_customer_id
     * @return
     */
    public String deleteByPk(Integer wms_cus_customer_id);
    
    /**
     * 保存客户信息
     * @author Administrator
     */
    public int wmsCustomerSave(WmsCusCustomerHeadSearchBeanVO queryInfo, UserBean user);
    
    
    
}