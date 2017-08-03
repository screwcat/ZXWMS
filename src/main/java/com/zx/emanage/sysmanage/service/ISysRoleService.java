package com.zx.emanage.sysmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.sysmanage.vo.MenuTreeBean;
import com.zx.emanage.sysmanage.vo.SysRoleSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysRole;
import com.zx.emanage.util.gen.vo.SysRoleVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface ISysRoleService
{
    /**
     * Description :get combox list by vo queryInfo
     * 
     * @param queryInfo
     * @return combox list
     * @author auto_generator
     */
    public List<Map<String, Object>> getComboxList(SysRoleSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(SysRoleSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(SysRoleSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysRoleVO
     * @author auto_generator
     */
    public com.zx.emanage.util.gen.entity.SysRole getInfoByPK(Integer id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(com.zx.emanage.util.gen.entity.SysRole sysRole, UserBean user, String menuStr);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(SysRole bean, UserBean user);

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String delete(com.zx.emanage.util.gen.entity.SysRole bean);

    List<MenuTreeBean> getMenuTreeBean(Integer modifyRoleId);
/**
 * 获取短信发送人员
 * @returnMap<String, Object>
 */
	public Map<String, Object> getMessagePeople();
}