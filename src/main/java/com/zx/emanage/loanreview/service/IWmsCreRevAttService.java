package com.zx.emanage.loanreview.service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineContactSearchBeanVO;
import com.zx.emanage.loanreview.vo.WmsCreRevAttSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevAtt;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevAttService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevAttSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevAttSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevAttVO
     * @author auto_generator
     */
    public WmsCreRevAtt getInfoByPK(Integer wms_cre_rev_att_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreRevAtt bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevAtt bean, UserBean user);

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreRevAtt() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    public List<WmsCreRevAtt> getListByEntity(WmsCreRevAtt queryInfo, Boolean isExcludePKFlag);


}