package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.SysUser;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysMenuDao extends BaseDao<SysUser>
{

    List<Map<String, Object>> queryForList(Map<String, Object> paramMap);

    /**
     * 得到主菜单
     * 
     * @return
     */
    List<Map<String, Object>> getMenus(Integer user_id);
    /**
     * 根据菜单url获取当前人的权限
     * 
     * @return
     * @author baisong
     * @date 2016-10-19
     */
    List<Map<String, Object>> queryChildrenDeptInfo(Map<String, Object> map);

    /**
     * 
     * @Title: isChildrenDept
     * @Description: TODO(根据人id和菜单url判断某一条数据是否有权限)
     * @param map
     * @return 
     * @author: admin
     * @time:2017年2月24日 上午10:21:51
     * history:
     * 1、2017年2月24日 admin 创建方法
     */
    String isChildrenDept(Map<String, Object> map);
}
