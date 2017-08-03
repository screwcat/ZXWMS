package com.zx.emanage.cusmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineCompanySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCusCustomerLineCompanyService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerLineCompanySearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCusCustomerLineCompanySearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerLineCompanyVO
     * @author auto_generator
     */
    public WmsCusCustomerLineCompany getInfoByPK(Integer wms_cus_customer_line_company_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCusCustomerLineCompany bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCusCustomerLineCompany bean, UserBean user);
}