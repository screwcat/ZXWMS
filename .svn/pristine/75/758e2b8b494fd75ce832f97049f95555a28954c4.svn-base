package com.zx.emanage.sysmanage.persist;

import java.util.List;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.SysWebUser;

@MyBatisRepository
public interface SysWebUserDao extends BaseDao<SysWebUser>
{
    /**
     * 外网用通过用户身份证来查询次用户是否已有用户名密码
     * 
     * @param id_card
     * @return SysWebUser baisong
     */
    public List<SysWebUser> getById(String id_card);

    /**
     * 查询用户名密码是否已经存在
     * 
     * @param username
     * @return SysWebUser baisong
     */
    public SysWebUser getByUserName(String username);
}