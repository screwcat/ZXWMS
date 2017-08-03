package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInvePruductDeadline;
import com.zx.emanage.inve.vo.WmsInvePruductDeadlineSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInvePruductDeadlineService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInvePruductDeadlineSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInvePruductDeadlineSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInvePruductDeadlineVO
     * @author auto_generator
     */
    public WmsInvePruductDeadline getInfoByPK(Integer wms_inve_pruduct_deadline_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInvePruductDeadline bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInvePruductDeadline bean, UserBean user);

    /**
     * Description :产品复制 查询产品期限表
     * 
     * @param id
     * @return map
     * @author baisong
     */
    public Map<String, Object> getListforlc(Integer wms_inve_pruduct_category_id);

}