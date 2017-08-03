package com.zx.emanage.cremanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsCusCustomerChangeChildSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCusCustomerChangeChild;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCusCustomerChangeChildService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerChangeChildSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCusCustomerChangeChildSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerChangeChildVO
     * @author auto_generator
     */
    public WmsCusCustomerChangeChild getInfoByPK(Integer wms_cus_customer_change_child_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCusCustomerChangeChild bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCusCustomerChangeChild bean, UserBean user);
}