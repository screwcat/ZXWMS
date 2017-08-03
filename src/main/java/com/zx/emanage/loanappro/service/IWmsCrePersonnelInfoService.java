package com.zx.emanage.loanappro.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanappro.vo.WmsCrePersonnelInfoSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCrePersonnelInfo;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCrePersonnelInfoService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCrePersonnelInfoSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCrePersonnelInfoSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCrePersonnelInfoVO
     * @author auto_generator
     */
    public WmsCrePersonnelInfo getInfoByPK(Integer wms_cre_personnel_info_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCrePersonnelInfo bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCrePersonnelInfo bean, UserBean user);
}