package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.SysDept;
import com.zx.emanage.util.gen.entity.SysFunction;
import com.zx.emanage.util.gen.entity.SysUser;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysFunctionDao extends BaseDao<SysFunction>
{

    List<SysFunction> getAllFunc();

    List<Map<String, Object>> queryForList(Map<String, Object> params);

}
