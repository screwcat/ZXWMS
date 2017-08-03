package com.zx.emanage.sysmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.sysmanage.vo.SysSelectMeun;
import com.zx.emanage.sysmanage.vo.SysUserRoleSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysUserRole;
import com.zx.emanage.util.gen.entity.SysMenu;
import com.zx.emanage.util.gen.vo.SysUserRoleVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface ISysUserRoleService
{
    /**
     * Description :get combox list by vo queryInfo
     * 
     * @param queryInfo
     * @return combox list
     * @author auto_generator
     */
    public List<Map<String, Object>> getComboxList(SysUserRoleSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(SysUserRoleSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(SysUserRoleSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysUserRoleVO
     * @author auto_generator
     */
    public SysUserRoleVO getInfoByPK(Integer id);

    /**
     * Description :add method
     * 
     * @param roleArr
     * @return "success" or "error" or user defined
     * @author auto_generator
     * @param user_id
     */
    public String save(String roleArr, UserBean user, Integer user_id);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(SysUserRole bean, UserBean user);

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String delete(SysUserRole bean);

    // 首页跳转
    public SysMenu getUserMenu(SysSelectMeun meun);
}