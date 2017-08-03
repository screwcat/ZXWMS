package com.zx.emanage.reportmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformanceNew;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionPerformanceNewSearchBeanVO;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionPerformanceSearchBeanVO;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionTeamPerformanceSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCommissionPerformanceNewService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionPerformanceNewVO
	 * @author auto_generator
	 */	
	public WmsInveCommissionPerformanceNew getInfoByPK(Integer wms_inve_commission_performance_new_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCommissionPerformanceNew bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCommissionPerformanceNew bean, UserBean user);
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
	 * Description :根据人员id获取团队佣金
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	public Map<String, Object> getListTeam(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo);
	/**
	 * Description :佣金发放查询-excel
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	public Map<String, Object> getListWithoutPagingAll(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo);
	/**
	 * Description :佣金发放查询
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	public Map<String, Object> getListWithPagingAll(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo);
}