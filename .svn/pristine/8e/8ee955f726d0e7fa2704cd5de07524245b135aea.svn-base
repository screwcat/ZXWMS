package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanreview.vo.WmsCreRevInfoCourtCaseSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoCourtCase;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevInfoCourtCaseService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoCourtCaseSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevInfoCourtCaseSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevInfoCourtCaseVO
     * @author auto_generator
     */
    public WmsCreRevInfoCourtCase getInfoByPK(Integer wms_cre_rev_info_court_case_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreRevInfoCourtCase bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevInfoCourtCase bean, UserBean user);
}