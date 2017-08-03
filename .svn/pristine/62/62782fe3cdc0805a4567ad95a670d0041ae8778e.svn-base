package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.emanage.inve.vo.WmsInvePruductYearPaySpecialSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInvePruductYearPaySpecialService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInvePruductYearPaySpecialSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInvePruductYearPaySpecialSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInvePruductYearPaySpecialVO
	 * @author auto_generator
	 */	
	public WmsInvePruductYearPaySpecial getInfoByPK(Integer wms_inve_pruduct_year_pay_special_id);
	/**
	 * Description :根据产品主键获取 年付特表信息
	 * @param primary key 
	 * @return WmsInvePruductYearPaySpecial
	 * @author baisong
	 *  2015-8-14
	 */	
	public WmsInvePruductYearPaySpecial getInfoByCK(Integer wms_inve_pruduct_category_id);
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInvePruductYearPaySpecial bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInvePruductYearPaySpecial bean, UserBean user);
}