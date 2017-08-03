package com.zx.emanage.loanfina.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsFinaCreRealrepayInfo;
import com.zx.emanage.loanfina.vo.WmsFinaCreRealrepayInfoSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsFinaCreRealrepayInfoService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsFinaCreRealrepayInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsFinaCreRealrepayInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaCreRealrepayInfoVO
	 * @author auto_generator
	 */	
	public WmsFinaCreRealrepayInfo getInfoByPK(Integer wms_fina_cre_realrepay_info_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsFinaCreRealrepayInfo bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsFinaCreRealrepayInfo bean, UserBean user);
	/**
	 * Description :根据 wms_cre_credit_head_id 查询信息
	 * @param primary key 
	 * @return WmsPostRemindHistoryVO
	 * @author baisong
	*/	
	public Map<String, Object> getInfoByHK(Integer wms_post_remind_history_id);		
	/**
	 * Description :根据 wms_cre_credit_head_id 查询信息
	 * @param primary key 
	 * @return WmsPostRemindHistoryVO
	 * @author baisong
	*/	
	public Map<String, Object> getSumval(Integer wms_post_remind_history_id);
	/**
	 * Description:通过wms_cre_credit_head_id和repay_no 查询相应的还款信息
	 * @param queryInfo
	 * @return
	 */
	public Map<String, Object> wmsfinacrerealrepayInfoByNOw(
			WmsFinaCreRealrepayInfoSearchBeanVO queryInfo);
}