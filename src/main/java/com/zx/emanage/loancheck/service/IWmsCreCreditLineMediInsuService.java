package com.zx.emanage.loancheck.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loancheck.vo.WmsCreCreditLineMediInsuSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineMediInsu;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineMediInsuService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineMediInsuSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineMediInsuSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineMediInsuVO
     * @author auto_generator
     */
    public WmsCreCreditLineMediInsu getInfoByPK(Integer wms_cre_credit_line_medi_insu_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditLineMediInsu bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineMediInsu bean, UserBean user);

    /**
     * 通过贷款表主键，实现查询医保信息显示
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    public Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id);
}