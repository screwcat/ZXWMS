package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.SysUser;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysUserDao extends BaseDao<SysUser>
{
    SysUser login(Map<String, Object> parameters);

    int updateRecordCols(SysUser sysUser);

    List<Map<String, Object>> checkUser(Map<String, Object> paramMap);

    List<Map<String, Object>> search2(Map<String, Object> parameters);

    int findCount2(Map<String, Object> paramMaps);

    /**
     * 逻辑删除用户记录
     * 
     * @param id 用户ID
     */
    void setEnableFlagFalse(Integer id);

    Long getCodeNo(Map<String, Object> paramMap);

    Long getRepeatCodeNo(Map<String, Object> paramMap);

    Long getRepeatCodeNoMonth(Map<String, Object> paramMap);

}
