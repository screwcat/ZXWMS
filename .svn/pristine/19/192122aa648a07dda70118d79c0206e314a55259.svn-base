package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveWagerulesFloating;
import com.zx.emanage.inve.vo.WmsInveWagerulesFloatingSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveWagerulesFloatingService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveWagerulesFloatingSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveWagerulesFloatingSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveWagerulesFloatingVO
	 * @author auto_generator
	 */	
	public WmsInveWagerulesFloating getInfoByPK(Integer wms_inve_wagerules_floating_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveWagerulesFloating bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveWagerulesFloating bean, UserBean user);
	/**
	 * Description :通过理财工资规则主表主键获取相应对应的浮动规则信息
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	public Map<String, Object> getWmsInveWageRulesFloatingInfoByfk(
			WmsInveWagerulesFloatingSearchBeanVO queryInfo);
}