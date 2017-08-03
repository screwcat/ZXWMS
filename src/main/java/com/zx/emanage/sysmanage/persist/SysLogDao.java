package com.zx.emanage.sysmanage.persist;

import com.zx.emanage.util.gen.entity.SysLog;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository("syslogDao")
public interface SysLogDao extends BaseDao<SysLog>
{

}
