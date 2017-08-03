package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanreview.vo.WmsCreRevPhoneContactSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneContact;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevPhoneContactService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevPhoneContactSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevPhoneContactSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevPhoneContactVO
     * @author auto_generator
     */
    public WmsCreRevPhoneContact getInfoByPK(Integer wms_cre_rev_phone_contact_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreRevPhoneContact bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevPhoneContact bean, UserBean user);

    public List<WmsCreRevPhoneContact> getListByEntity(WmsCreRevPhoneContact queryInfo);

    public List<WmsCreRevPhoneContact> getList(WmsCreRevPhoneContact queryInfo);
}