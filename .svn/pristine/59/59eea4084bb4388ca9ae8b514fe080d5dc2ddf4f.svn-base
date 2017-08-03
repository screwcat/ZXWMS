package com.zx.emanage.sysmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.sysmanage.vo.SysRoleMenuFunctionSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysRoleMenuFunction;
import com.zx.emanage.util.gen.vo.SysRoleMenuFunctionVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface ISysRoleMenuFunctionService
{
    /**
     * Description :get combox list by vo queryInfo
     * 
     * @param queryInfo
     * @return combox list
     * @author auto_generator
     */
    public List<Map<String, Object>> getComboxList(SysRoleMenuFunctionSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(SysRoleMenuFunctionSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(SysRoleMenuFunctionSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysRoleMenuFunctionVO
     * @author auto_generator
     */
    public SysRoleMenuFunctionVO getInfoByPK(Integer id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(SysRoleMenuFunction bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(SysRoleMenuFunction bean, UserBean user);

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String delete(SysRoleMenuFunction bean);
}