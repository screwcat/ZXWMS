package com.zx.emanage.loancheck.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCustomerInfoSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyCondition;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerInfo;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineHouseRecord;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineCustomerInfoService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCustomerInfoSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCustomerInfoSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerInfoVO
     * @author auto_generator
     */
    public WmsCreCreditLineCustomerInfo getInfoByPK(Integer wms_cre_credit_line_customer_info_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditLineCustomerInfo creCreditCustomerInfo,
                       WmsCreCreditLineCompanyCondition creCreditCompanyCondition,
                       WmsCreCreditLineHouseRecord creCreditLineHouseRecord, String paramCsinfo, String paramTelinfo,
                       String fileArrTelPhone, String paramMedicareinfo, String fileArrMedicare,
                       String paramReserveinfo, String fileArrReserve, String bainfoAndDetaiList,
                       WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user, String telsId);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineCustomerInfo bean, UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerInfoVO
     * @author auto_generator
     */
    public WmsCreCreditLineCustomerInfo getInfoByFK(Integer wms_cre_credit_line_customer_info_id);
}