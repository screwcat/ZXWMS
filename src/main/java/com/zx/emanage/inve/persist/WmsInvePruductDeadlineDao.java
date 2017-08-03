package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInvePruductDeadline;

@MyBatisRepository
public interface WmsInvePruductDeadlineDao extends BaseDao<WmsInvePruductDeadline>
{
    /**
     * 理财产品 复制 初始化期限表
     * 
     * @param wms_inve_pruduct_category_id
     * @return map baisong
     */
    public List<Map<String, Object>> getListforlc(Integer wms_inve_pruduct_category_id);
}