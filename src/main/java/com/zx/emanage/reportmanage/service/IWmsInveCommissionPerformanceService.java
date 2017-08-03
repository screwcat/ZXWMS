package com.zx.emanage.reportmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformance;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionPerformanceSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCommissionPerformanceService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionPerformanceSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCommissionPerformanceSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionPerformanceVO
	 * @author auto_generator
	 */	
	public WmsInveCommissionPerformance getInfoByPK(Integer wms_inve_commission_performance_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCommissionPerformance bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCommissionPerformance bean, UserBean user);
	/**
	 * Description :update method-修改单据并添加发放日期
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */
	public String updateStatus(WmsInveCommissionPerformanceSearchBeanVO queryInfo, UserBean user);
	/**
	 * Description :根据业务员id获取当前业务员的详细单据信息
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	public Map<String, Object> showDetails(WmsInveCommissionPerformanceSearchBeanVO queryInfo);
	/**
	 * Description :佣金发放查询-excel
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	public Map<String, Object> getListWithoutPagingAll(WmsInveCommissionPerformanceSearchBeanVO queryInfo);
	
	/**
	 * Description :佣金发放查询
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	public Map<String, Object> getListWithPagingAll(WmsInveCommissionPerformanceSearchBeanVO queryInfo);
	
}