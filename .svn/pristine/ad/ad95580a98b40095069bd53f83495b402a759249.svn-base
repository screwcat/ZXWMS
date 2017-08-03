package com.zx.emanage.loancheck.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loancheck.vo.WmsCreCreditLineCompanyConditionSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyCondition;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerInfo;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineCompanyConditionService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCompanyConditionSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCompanyConditionSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCompanyConditionVO
     * @author auto_generator
     */
    public WmsCreCreditLineCompanyCondition getInfoByPK(Integer wms_cre_credit_line_company_condition_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditLineCompanyCondition bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineCompanyCondition bean, UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCompanyCondition
     * @author auto_generator
     */
    public WmsCreCreditLineCompanyCondition getInfoByFK(Integer wms_cre_credit_line_customer_info_id);

    /**
     * 根基贷款主键ID，查询出企业基本信息的情况显示
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    public List<WmsCreCreditLineCompanyCondition> searchInfoByFKVIEW(Integer wms_cre_credit_head_id);
}