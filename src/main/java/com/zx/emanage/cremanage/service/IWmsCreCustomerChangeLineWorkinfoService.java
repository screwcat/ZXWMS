package com.zx.emanage.cremanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineWorkinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineWorkinfo;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineWorkinfoVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCustomerChangeLineWorkinfoService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCustomerChangeLineWorkinfoVO
     * @author auto_generator
     */
    public WmsCreCustomerChangeLineWorkinfoVO getInfoByPK(Integer wms_cre_customer_change_line_workinfo_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCustomerChangeLineWorkinfo bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCustomerChangeLineWorkinfo bean, UserBean user);

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String delete(WmsCreCustomerChangeLineWorkinfo bean);
}