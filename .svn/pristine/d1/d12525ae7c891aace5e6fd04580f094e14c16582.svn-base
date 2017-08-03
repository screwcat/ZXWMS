package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCommionRecordCoeff;
import com.zx.emanage.inve.vo.WmsInveCommionRecordCoeffSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCommionRecordCoeffService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCommionRecordCoeffSearchBeanVO queryInfo);
	
	/**
	 * 根据代办事项中的月份查找佣金提成系数修正信息
	 * @return
	 */
	public Map<String, Object> getMonthCommionListWithoutPaging(UserBean user);
	
	/**
	 * 根据代办事项中的月份查找提成系数审核清空信息
     * @return Map
     * @author zhangyunfei
     */
	public Map<String, Object> getCommionListWithoutPagingByMonth(UserBean user,WmsInveCommionRecordCoeff wmsInveCommionRecordCoeff);
	
	/**
	 * 保存提成系数修正信息
	 * @param user
	 * @param params 前台传过来的集合字符串
	 * @return
	 */
	public String saveMonthCommionList(UserBean user, String params);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCommionRecordCoeffSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommionRecordCoeffVO
	 * @author auto_generator
	 */	
	public WmsInveCommionRecordCoeff getInfoByPK(Integer wms_inve_commion_record_coeff_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCommionRecordCoeff bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCommionRecordCoeff bean, UserBean user);
}