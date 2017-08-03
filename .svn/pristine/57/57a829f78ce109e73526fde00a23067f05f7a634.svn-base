package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanreview.vo.WmsCreRevPhoneModelSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneContact;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneModel;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevPhoneModelService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevPhoneModelSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevPhoneModelSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevPhoneModelVO
     * @author auto_generator
     */
    public WmsCreRevPhoneModel getInfoByPK(Integer wms_cre_rev_phone_model_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreRevPhoneModel bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevPhoneModel bean, UserBean user);

    /**
     * 获取电审重要数据 --模型
     * 
     * @param wms_cre_credit_head_id
     * @return WmsCreRevPhoneModelVO
     * @author baisong
     */
    public WmsCreRevPhoneModel getInfoByFK(Integer wms_cre_credit_head_id);

}