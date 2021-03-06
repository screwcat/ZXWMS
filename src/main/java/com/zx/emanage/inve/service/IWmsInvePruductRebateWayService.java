package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
import com.zx.emanage.inve.vo.WmsInvePruductRebateWaySearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInvePruductRebateWayService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInvePruductRebateWaySearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInvePruductRebateWaySearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInvePruductRebateWayVO
	 * @author auto_generator
	 */	
	public WmsInvePruductRebateWay getInfoByPK(Integer wms_inve_pruduct_rebate_way_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInvePruductRebateWay bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInvePruductRebateWay bean, UserBean user);
}