package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.SysRole;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysRoleDao extends BaseDao<SysRole>
{

    List<Map<String, Object>> getSysRoleMenuCheck(Map<String, Object> paramMap);

    List<Map<String, Object>> getSysRoleFuncCheck(Map<String, Object> paramMap);

    List<Map<String, Object>> search2(Map<String, Object> paramMap);

    List<String> getUserRoleNameList(int userId);

    /**
     * 判断是否存在业务部门团队经理
     * 
     * @param userId
     * @return
     */
    int findRole(Integer userId);

    /**
     * 实现根据用户ID,查询其角色是不是信审部主管
     * 
     * @param userId
     * @return
     */
    int findRoleForCDS(Integer userId);
/**
 * 获取短信发送人员
 * @return
 */
	List<com.zx.emanage.util.gen.domain.SysRole> getMessagePeople();
}
