package com.zx.emanage.loancheck.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loancheck.vo.WmsCreCreditLineCallListSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCallList;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineCallListService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCallListSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCallListSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCallListVO
     * @author auto_generator
     */
    public WmsCreCreditLineCallList getInfoByPK(Integer wms_cre_credit_line_call_list_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreCreditLineCallList bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineCallList bean, UserBean user);

    /**
     * 通过贷款主键id获取对应的电话信息
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    public Map<String, Object> searchInfoByFK(Integer wms_cre_credit_head_id);
}