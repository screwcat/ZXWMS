package com.zx.emanage.cremanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreCarpApproInfo;
import com.zx.emanage.cremanage.vo.WmsCreCarpApproInfoSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCarpApproInfoService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreCarpApproInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreCarpApproInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpApproInfoVO
	 * @author auto_generator
	 */	
	public WmsCreCarpApproInfo getInfoByPK(Integer wms_cre_carp_appro_info_id);	
	/**
	 * Description :get single-table information by primary key 根据贷款主键查询
	 * @param primary key 
	 * @return WmsCreCarpApproInfoVO
	 * @author baisong
	 */	
	public Map<String,Object> getInfoByHK(Integer wms_cre_credit_head_id,Integer carp_appro_type);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreCarpApproInfo bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreCarpApproInfo bean, UserBean user);
}