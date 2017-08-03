package com.zx.emanage.cremanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsCreHousingCustomerHouseSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreHousingCustomerHouse;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerChangeHeadVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreHousingCustomerHouseService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreHousingCustomerHouseSearchBeanVO queryInfo);

    /**
     * Description :得到抵押房产信息 为房贷查询
     * 
     * @param queryInfo
     * @return record list
     * @author ry
     */
    public Map<String, Object> getHCHInfoListWithoutPagingByMccid(WmsCreHousingCustomerHouseSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreHousingCustomerHouseSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingCustomerHouseVO
     * @author auto_generator
     */
    public WmsCreHousingCustomerHouse getInfoByPK(Integer id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreHousingCustomerHouse bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreHousingCustomerHouse bean, UserBean user);
/**
 * 
 * 获取房屋信息
 * @param wms_cre_credit_head_id
 * @return
 */
	public Map<String, Object> getwmscrehousingcustomerhouseinfo(
			Integer wms_cre_credit_head_id);
}