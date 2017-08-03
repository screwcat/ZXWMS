package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanreview.vo.WmsCreRevWaterRepayLoanLineSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterRepayLoanLine;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevWaterRepayLoanLineService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterRepayLoanLineSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevWaterRepayLoanLineSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevWaterRepayLoanLineVO
     * @author auto_generator
     */
    public WmsCreRevWaterRepayLoanLine getInfoByPK(Integer wms_cre_rev_water_repay_loan_line_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreRevWaterRepayLoanLine bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevWaterRepayLoanLine bean, UserBean user);
}