package com.zx.emanage.loancheck.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loancheck.vo.WmsCreCreditLineOtherSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineOther;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineOtherService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineOtherSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineOtherSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineOtherVO
     * @author auto_generator
     */
    public WmsCreCreditLineOther getInfoByPK(Integer wms_cre_credit_line_other);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditLineOther bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineOther bean, UserBean user);
}