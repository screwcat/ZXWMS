package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInvePruductReturn;
import com.zx.emanage.inve.vo.WmsInvePruductReturnSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInvePruductReturnService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInvePruductReturnSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInvePruductReturnSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInvePruductReturnVO
     * @author auto_generator
     */
    public WmsInvePruductReturn getInfoByPK(Integer wms_inve_pruduct_return_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInvePruductReturn bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInvePruductReturn bean, UserBean user);

    /**
     * Description :产品复制 查询产品限制表
     * 
     * @param id
     * @return map
     * @author baisong
     */
    public Map<String, Object> getListforlc(Integer wms_inve_pruduct_category_id);

}