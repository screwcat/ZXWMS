package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanreview.vo.WmsCreRevInfoModelSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevInfoModel;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevInfoModelService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevInfoModelSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevInfoModelSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevInfoModelVO
     * @author auto_generator
     */
    public WmsCreRevInfoModel getInfoByPK(Integer wms_cre_rev_info_model_id);

    /**
     * Description :通过外键实现对该数据详细的查询
     * 
     * @param F key
     * @return WmsCreRevInfoModelVO
     * @author hancd
     */
    public WmsCreRevInfoModel getInfoByFK(Integer wms_cre_credit_head_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreRevInfoModel bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevInfoModel bean, UserBean user);
}