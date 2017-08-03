package com.zx.emanage.cusmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cusmanage.vo.WmsCusCustomerChildSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCusCustomerChild;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCusCustomerChildService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerChildSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCusCustomerChildSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerChildVO
     * @author auto_generator
     */
    public WmsCusCustomerChild getInfoByPK(Integer wms_cus_customer_child_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCusCustomerChild bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCusCustomerChild bean, UserBean user);
}