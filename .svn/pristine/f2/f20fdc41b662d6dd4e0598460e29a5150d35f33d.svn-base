package com.zx.emanage.reportmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveWagePerformance;
import com.zx.emanage.reportmanage.vo.WmsInveWagePerformanceSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveWagePerformanceService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveWagePerformanceSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveWagePerformanceSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveWagePerformanceVO
	 * @author auto_generator
	 */	
	public WmsInveWagePerformance getInfoByPK(Integer wms_inve_wage_performance_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveWagePerformance bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveWagePerformance bean, UserBean user);
}