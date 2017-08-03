package com.zx.emanage.loanfina.service;

import java.util.Map;

import com.zx.emanage.loanfina.vo.WmsFinaCreLoanAppAttSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanAppAtt;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsFinaCreLoanAppAttService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsFinaCreLoanAppAttSearchBeanVO queryInfo,
                                                    String wms_fina_cre_loan_app_id);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsFinaCreLoanAppAttSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsFinaCreLoanAppVO
     * @author auto_generator
     */
    public WmsFinaCreLoanAppAtt getInfoByPK(Integer wms_fina_cre_loan_app_att_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsFinaCreLoanAppAtt bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsFinaCreLoanAppAtt bean, UserBean user);
}