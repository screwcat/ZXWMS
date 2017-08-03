package com.zx.emanage.loancheck.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loancheck.vo.WmsCreCreditLineCompanyConditionInvestorSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyConditionInvestor;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineCompanyConditionInvestorService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCompanyConditionInvestorSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCompanyConditionInvestorSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCompanyConditionInvestorVO
     * @author auto_generator
     */
    public WmsCreCreditLineCompanyConditionInvestor getInfoByPK(Integer wms_cre_credit_line_company_condition_investor_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditLineCompanyConditionInvestor bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineCompanyConditionInvestor bean, UserBean user);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListByFKWithoutPaging(Integer wms_cre_credit_head_id);

    /**
     * 通过企业信息主表单ID外键，获取相应的企业投资人信息
     * 
     * @param wms_cre_credit_line_company_condition_id
     * @return
     */
    public Map<String, Object> searchInfoByPK(Integer wms_cre_credit_line_company_condition_id);
}