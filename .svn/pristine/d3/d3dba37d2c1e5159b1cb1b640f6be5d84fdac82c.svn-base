package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCommionRecord;
import com.zx.emanage.inve.vo.WmsInveCommionRecordSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCommionRecordService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCommionRecordSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCommionRecordSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommionRecordVO
	 * @author auto_generator
	 */	
	public WmsInveCommionRecord getInfoByPK(Integer wms_inve_commion_record_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCommionRecord bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCommionRecord bean, UserBean user);

	/**
	 * Description :调整金额
	 * @param WmsInveCommionRecord
	 * @return "success" or "error" or user defined
	 * @author zhangyunfei
	 */	
	public String updateWmsInveCommionRecordAmount(WmsInveCommionRecord bean,
			UserBean user);
	
	/**
	 * Description :条件查询实发金额总数
	 * @param bean
	 * @return 实发金额总数
	 * @author zhangyunfei
	 */
	public String findwmsinvepruductcategorySumMoney(WmsInveCommionRecordSearchBeanVO queryInfo);
}