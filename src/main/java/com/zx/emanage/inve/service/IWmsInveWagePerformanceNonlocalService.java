package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveWagePerformanceNonlocalSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveWagePerformanceNonlocal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveWagePerformanceNonlocalService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveWagePerformanceNonlocalVO
	 * @author auto_generator
	 */	
	public WmsInveWagePerformanceNonlocal getInfoByPK(Integer wms_inve_wage_performance_nonlocal_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveWagePerformanceNonlocal bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveWagePerformanceNonlocal bean, UserBean user);
	
	/**
     * 获取公司下拉列表
     */
	public List<Map<String, Object>> getCompanySelectForSalary(UserBean user, WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo);
    
    /**
     * 根据公司ID获取部门下拉列表
     */
	public List<Map<String, Object>> getDeptSelectByCompanyIdForSalary(UserBean user, WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo);
    
    /**
     * 根据部门ID获取团队下拉列表
     */
	public List<Map<String, Object>> getTeamSelectByDeptIdForSalary(UserBean user, WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo);
	
}