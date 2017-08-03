package com.zx.emanage.inve.persist;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveAtt;

@MyBatisRepository
public interface WmsInveAttDao extends BaseDao<WmsInveAtt>
{
    /**
     * 根据上单ID删除附件
     * 
     * @param wms_inve_transa_id 上单ID
     */
    void delete(Integer wms_inve_transa_id);
}