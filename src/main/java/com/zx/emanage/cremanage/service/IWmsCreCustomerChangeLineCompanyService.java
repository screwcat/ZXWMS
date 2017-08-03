package com.zx.emanage.cremanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineCompanySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineCompany;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCustomerChangeLineCompanyService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineCompanySearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCustomerChangeLineCompanySearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCustomerChangeLineCompanyVO
     * @author auto_generator
     */
    public WmsCreCustomerChangeLineCompany getInfoByPK(Integer wms_cre_customer_change_line_company_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCustomerChangeLineCompany bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCustomerChangeLineCompany bean, UserBean user);
}