package com.zx.emanage.sysmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreHousingOperationLog;
import com.zx.emanage.sysmanage.vo.WmsCreHousingOperationLogSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreHousingOperationLogService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreHousingOperationLogSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreHousingOperationLogSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreHousingOperationLogVO
	 * @author auto_generator
	 */	
	public WmsCreHousingOperationLog getInfoByPK(Integer wms_cre_housing_operation_log_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreHousingOperationLog bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreHousingOperationLog bean, UserBean user);
	
	/**
	 * 
	 * @Title: ClaimOperUp
	 * @Description: 发送房产核查领用状态
	 * @param bean
	 * @return 
	 * @author: ZhangWei
	 * @time:2017年6月1日 下午3:52:29
	 * history:
	 * 1、2017年6月1日 ZhangWei 创建方法
	 */
	public Map<String, Object> ClaimOperUp(WmsCreHousingOperationLog bean);
}