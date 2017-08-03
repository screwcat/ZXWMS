package com.zx.emanage.cusmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineWorkinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCusCustomerLineWorkinfo;
import com.zx.emanage.util.gen.vo.WmsCusCustomerLineWorkinfoVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCusCustomerLineWorkinfoService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerLineWorkinfoVO
     * @author auto_generator
     */
    public WmsCusCustomerLineWorkinfoVO getInfoByPK(Integer wms_cus_customer_line_workinfo_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCusCustomerLineWorkinfo bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCusCustomerLineWorkinfo bean, UserBean user);

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String delete(WmsCusCustomerLineWorkinfo bean);
}