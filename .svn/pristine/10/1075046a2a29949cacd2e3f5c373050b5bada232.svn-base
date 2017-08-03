package com.zx.emanage.loancheck.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loancheck.vo.WmsCreCreditLineAccuFundSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineAccuFund;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineAccuFundService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineAccuFundSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineAccuFundSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineAccuFundVO
     * @author auto_generator
     */
    public WmsCreCreditLineAccuFund getInfoByPK(Integer wms_cre_credit_line_accu_fund_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditLineAccuFund bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineAccuFund bean, UserBean user);

    /**
     * 通过主表单ID，获取公积金信息
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    public Map<String, Object> searchInfoByFK(Integer wms_cre_credit_head_id);
}