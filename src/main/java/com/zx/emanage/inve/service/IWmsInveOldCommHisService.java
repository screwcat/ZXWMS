package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveOldCommHis;
import com.zx.emanage.inve.vo.WmsInveOldCommHisSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveOldCommHisService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveOldCommHisSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveOldCommHisSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveOldCommHisVO
	 * @author auto_generator
	 */	
	public WmsInveOldCommHis getInfoByPK(Integer wms_inve_old_comm_his_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveOldCommHis bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveOldCommHis bean, UserBean user);
}