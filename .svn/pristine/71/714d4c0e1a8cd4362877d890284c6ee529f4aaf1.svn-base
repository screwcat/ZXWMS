package com.zx.emanage.reportmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCommissionTeamPerformance;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionTeamPerformanceSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCommissionTeamPerformanceService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionTeamPerformanceVO
	 * @author auto_generator
	 */	
	public WmsInveCommissionTeamPerformance getInfoByPK(Integer wms_inve_commission_team_performance_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCommissionTeamPerformance bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCommissionTeamPerformance bean, UserBean user);
	
	/**
	 * Description :根据人员id获取团队佣金
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	public Map<String, Object> getListTeam(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo);
	
}