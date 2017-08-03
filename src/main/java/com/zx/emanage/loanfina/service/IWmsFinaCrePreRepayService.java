package com.zx.emanage.loanfina.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanfina.vo.WmsFinaCrePreRepaySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCrePreRepay;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsFinaCrePreRepayService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsFinaCrePreRepaySearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsFinaCrePreRepaySearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsFinaCrePreRepayVO
     * @author auto_generator
     */
    public WmsFinaCrePreRepay getInfoByPK(Integer wms_fina_cre_pre_repay_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsFinaCrePreRepay bean, UserBean user, String taskId);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsFinaCrePreRepay bean, UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsFinaCrePreRepayVO
     * @author wangyishun
     */
    public WmsFinaCrePreRepay getInfoByEntity(WmsFinaCrePreRepay entity);
}