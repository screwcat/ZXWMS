package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.PmPersonnelOtherinfo;
import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformanceHis;
import com.zx.emanage.util.gen.entity.WmsInveTransaAuth;
import com.zx.emanage.inve.vo.WmsInveCommissionPerformanceHisSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCommissionPerformanceHisService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionPerformanceHisSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCommissionPerformanceHisSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionPerformanceHisVO
	 * @author auto_generator
	 */	
	public WmsInveCommissionPerformanceHis getInfoByPK(Integer wms_inve_commission_performance_his_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCommissionPerformanceHis bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCommissionPerformanceHis bean, UserBean user);
	
	
	/**
	 * Description :检查是否存在  存在说明已验证
	 * @param bean
	 * @return "list"
	 * @author zhangyunfei
	 */	
	public List<PmPersonnelOtherinfo> findPmpersonnelOtherCountByPid(PmPersonnelOtherinfo bean);
	
	/**
	 * Description :认证情况列表
	 * @param bean
	 * @return "Map"
	 * @author zhangyunfei
	 */
	public Map<String, Object> getWmsInveTransaAuthListByPid(WmsInveTransaAuth queryInfo);

}