package com.zx.emanage.sysmanage.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.SysRoleMenuFunction;
import com.zx.emanage.util.gen.entity.SysUser;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysRoleMenuFunctionDao extends BaseDao<SysRoleMenuFunction>
{
    /**
     * 根据角色ID删除SysRoleMenuFunction
     * 
     * @param sys_role_id 角色ID
     */
    void deleteByRoleId(Integer role_id);

}
