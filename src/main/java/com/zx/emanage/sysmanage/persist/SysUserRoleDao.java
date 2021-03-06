package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.SysSelectMeun;
import com.zx.emanage.util.gen.entity.SysMenu;
import com.zx.emanage.util.gen.entity.SysUserRole;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysUserRoleDao extends BaseDao<SysUserRole>
{

    /**
     * 用UserRole中的参数删除记录
     * 
     * @param parameters
     * @return
     */
    void deleteByUserRoleMap(Map<String, Object> parameters);

    /**
     * 实现查询是否是销售部主管 还是货后部主管
     * 
     * @param userIds
     * @return
     */
    List<String> searchforbeforeortransfer(Map<String, Object> userIds);

    // 用于首页客户管理等 页面跳转
    SysMenu selectByUserId(SysSelectMeun meun);

    /**
     * 获取用户角色信息
     * 
     * @param userId
     * @return
     */
    public List<SysUserRole> getUserRole(int userId);

    /**
     * 通过给定选取范围，得出数据库中最后的值并且进行加1
     * 
     * @param maxVal
     * @return nextId
     */
    int getNextId(int maxVal);

    /**
     * 实现保存完整数据
     * 
     * @param s
     */
    void saveInfo(com.zx.emanage.util.gen.entity.SysUserRole sUserRole);
    /**
     * 获取拥有验点角色的UserId
     * @return
     */
    List<String> findUserIDs();
    
    /**
     * @Title: findRoleForDHZG 
     * @Description: 根据登录人ID,判断器角色是否有贷后主管权限 
     * @param user_id
     * @return    
     * @return Integer    返回类型
     * @throws
     * @author lvtu
     */
    Integer findRoleForDHZG(Integer user_id);
    /**
     * @Title: 根据流程角色id获取拥有当前角色的人 并且当前人需要拥有对应菜单权限 
     * @param map  map中需要传递role_value 参数 是流程角色 例如：htzy为合同专员
     * @return list  map 中会返回拥有当前权限的人员id
     * @author  baisong
     */
    List<Map<String,Object>> getRoleListUser(Map<String,Object> map);
    /**
     * @Title: 根据流程角色id获取拥有当前角色的人 并且当前人需要拥有对应菜单权限 
     * @param map  map中需要传递user_id 参数 是id 
     * @return list  map 中会返回拥有当前权限的人员权限
     * @author  baisong
     */
    List<Map<String,Object>> getUserListRole(Map<String,Object> map);
    /**
     * @Title: 根据流程角色id获取拥有当前角色的人
     * @param map  map中需要传递role_value 参数 是流程角色 例如：htzy为合同专员
     * @return list  map 中会返回拥有当前权限的人员id
     * @author  baisong
     */
    List<Map<String,Object>> getRoleListByWorkflow(Map<String,Object> map);  
    /**
     * @Title: 根据人员id获取客户经理团队经理门店经理的信息
     * @param map  
     * @return list  map 中会返回拥有当前权限的人员id
     * @author  baisong
     */
    List<Map<String,Object>> getSuperiorAndoneself(Map<String,Object> map);

    /**
     * 
     * @Title: getUserByMenu
     * @Description: TODO(根据菜单 查询拥有当前菜单的人)
     * @param map
     * @return 
     * @author: baisong
     * @time:2017年2月24日 上午11:36:18
     * history:
     * 1、2017年2月24日 baisong 创建方法
     */
    List<Map<String,Object>> getUserByMenu(Map<String,Object> map);

    /**
     * @Title: getUserByRole
     * @Description: 根据角色查询拥有当前权限的人
     * @param resMap
     * @return 
     * @author: baisong
     * @time:2017年5月23日 下午4:06:46
     * history:
     * 1、2017年5月23日 baisong 创建方法
    */
    List<Map<String, Object>> getUserByRole(Map<String, Object> resMap);
}
