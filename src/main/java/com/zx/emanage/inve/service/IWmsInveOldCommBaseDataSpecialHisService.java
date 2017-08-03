package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveOldCommBaseDataSpecialHis;
import com.zx.emanage.inve.vo.WmsInveOldCommBaseDataSpecialHisSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveOldCommBaseDataSpecialHisService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveOldCommBaseDataSpecialHisSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveOldCommBaseDataSpecialHisSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveOldCommBaseDataSpecialHisVO
	 * @author auto_generator
	 */	
	public WmsInveOldCommBaseDataSpecialHis getInfoByPK(Integer wms_inve_old_comm_base_data_special_his_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveOldCommBaseDataSpecialHis bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveOldCommBaseDataSpecialHis bean, UserBean user);
}