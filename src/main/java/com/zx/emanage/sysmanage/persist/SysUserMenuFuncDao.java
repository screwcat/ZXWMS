package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.SysSelectMeun;
import com.zx.emanage.util.gen.entity.SysMenu;
import com.zx.emanage.util.gen.entity.SysUser;
import com.zx.emanage.util.gen.entity.SysUserMenuFunc;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysUserMenuFuncDao extends BaseDao<SysUserMenuFunc>
{

    void deleteBySysUserMenuFuncMap(Map<String, Object> parameters);

    List<Map<String, Object>> getSysUserMenuCheck(Map<String, Object> params);

    List<Map<String, Object>> getSysUserFuncCheck(Map<String, Object> params);

    /**
     * 根据用户ID删除记录
     * 
     * @param user_id
     */
    void deleteByUserId(Integer user_id);

}
