package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveTransaPruductUser;
import com.zx.emanage.inve.vo.WmsInveTransaPruductUserSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveTransaPruductUserService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveTransaPruductUserSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveTransaPruductUserSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveTransaPruductUserVO
	 * @author auto_generator
	 */	
	public WmsInveTransaPruductUser getInfoByPK(Integer wms_inve_transa_pruduct_user_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveTransaPruductUser bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveTransaPruductUser bean, UserBean user);
	
	/**
     * Description :整个页面数据保存
     * @param bean user
     * @return "success" or "error" or user defined
     * @author wangyihan
     */
    public String allSave(WmsInveTransaPruductUserSearchBeanVO bean, UserBean user);
	
}