package com.zx.emanage.sysmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.sysmanage.vo.MenuTreeBean;
import com.zx.emanage.sysmanage.vo.SysSelectMeun;
import com.zx.emanage.sysmanage.vo.SysUserMenuFuncSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysUserMenuFunc;
import com.zx.emanage.util.gen.entity.SysMenu;
import com.zx.emanage.util.gen.vo.SysUserMenuFuncVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface ISysUserMenuFuncService
{
    /**
     * Description :get combox list by vo queryInfo
     * 
     * @param queryInfo
     * @return combox list
     * @author auto_generator
     */
    public List<Map<String, Object>> getComboxList(SysUserMenuFuncSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(SysUserMenuFuncSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(SysUserMenuFuncSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysUserMenuFuncVO
     * @author auto_generator
     */
    public SysUserMenuFuncVO getInfoByPK(Integer id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(String menuArr, UserBean user, Integer user_id);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(SysUserMenuFunc bean, UserBean user);

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String delete(SysUserMenuFunc bean);

    public List<MenuTreeBean> getUserMenuTree(Integer user_id);

}