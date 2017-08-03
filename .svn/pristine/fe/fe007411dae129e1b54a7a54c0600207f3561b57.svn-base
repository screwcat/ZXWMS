package com.zx.emanage.loancheck.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCompanyAddressSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyAddress;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineOther;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineCompanyAddressService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCompanyAddressSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCompanyAddressSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCompanyAddressVO
     * @author auto_generator
     */
    public WmsCreCreditLineCompanyAddress getInfoByPK(Integer wms_cre_credit_line_company_address_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditLineCompanyAddress bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineCompanyAddress bean, UserBean user);

    public String saveAll(String qyjbqk, String gqjg, String qygdzc_cfxx, String qygdzc_clxx, String qygdzc_sbxx,
                          String sxyqyxx_syghs, String sxyqyxx_xycgs, String dywqk, String jyqk_yszkmx,
                          String jyqk_yfzkmx, String jyqk_kcyl, String jyqk_kcspmx, String wms_cre_credit_head_id,
                          WmsCreCreditLineOther wmsCreCreditLineOther, WmsCreditWorkFlowVO approveWorkFlowVO,
                          UserBean user);

    public Map<String, Object> selectAllByWmsCreCreditHeadId(String wms_cre_credit_head_id, UserBean user);
}