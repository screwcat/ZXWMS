package com.zx.emanage.sysmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.sysmanage.vo.SysSpecialUserSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface ISysSpecialUserService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(SysSpecialUserSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(SysSpecialUserSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return SysSpecialUserVO
	 * @author auto_generator
	 */	
	public SysSpecialUser getInfoByPK(Integer sys_special_user_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(SysSpecialUser bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(SysSpecialUser bean, UserBean user);
	
	/**
     * 获取特批人列表数据
     */
    public List<SysSpecialUser> getSysSpecialUserList(SysSpecialUser queryInfo, UserBean user);
	
}