package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveUserTodo;
import com.zx.emanage.inve.vo.WmsInveUserTodoSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveUserTodoService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveUserTodoSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveUserTodoSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveUserTodoVO
	 * @author auto_generator
	 */	
	public WmsInveUserTodo getInfoByPK(Integer wms_inve_user_todo_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveUserTodo bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveUserTodo bean, UserBean user);
	
	
	/**
	 * @Deprecated 根据用户信息获取对应用户是否存在待办事项
	 * @param userId
	 * @return
	 * @author donghao
	 * @date 2016年9月8日16:16:30
	 */
	public Map<String, Object> findWmsInveUserToByUserId(Integer userId);
	
	/**
	 * 根据登录人查询代办事项信息
	 * @param user 登录人信息
	 * @return
	 */
	public Map<String, Object> getUserTodoByLoginUser(UserBean user);

}