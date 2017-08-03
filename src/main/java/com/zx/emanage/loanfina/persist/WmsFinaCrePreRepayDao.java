package com.zx.emanage.loanfina.persist;

import com.zx.emanage.util.gen.entity.WmsFinaCrePreRepay;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsFinaCrePreRepayDao extends BaseDao<WmsFinaCrePreRepay>
{
    /**
     * 根据提前还款申请实体类中的某些属性值来查找该实体
     * 
     * @param entity 带某些值的实体类
     * @return 提前还款实体对象
     */
    WmsFinaCrePreRepay getInfoByEntity(WmsFinaCrePreRepay entity);

    int updateByEntity(WmsFinaCrePreRepay entity);

}