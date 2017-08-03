package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanreview.vo.WmsCreRevWaterRepayLoanSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterRepayLoan;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevWaterRepayLoanService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterRepayLoanSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevWaterRepayLoanSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevWaterRepayLoanVO
     * @author auto_generator
     */
    public WmsCreRevWaterRepayLoan getInfoByPK(Integer wms_cre_rev_water_repay_loan_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreRevWaterRepayLoan bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevWaterRepayLoan bean, UserBean user);
}