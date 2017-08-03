package com.zx.emanage.sysmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.SysPushErrorInfo;
import com.zx.emanage.sysmanage.vo.SysPushErrorInfoSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface ISysPushErrorInfoService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(SysPushErrorInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(SysPushErrorInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return SysPushErrorInfoVO
	 * @author auto_generator
	 */	
	public SysPushErrorInfo getInfoByPK(Integer sys_push_error_info_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(SysPushErrorInfo bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(SysPushErrorInfo bean, UserBean user);
}