package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;

@MyBatisRepository
public interface WmsInvePruductRebateWayDao extends BaseDao<WmsInvePruductRebateWay> {
	/**
     * 根据产品主键删除对应信息
     * @param wms_inve_pruduct_category_id
     * baisong
     */
	void delete(Integer wms_inve_pruduct_category_id);
    /**
     * search:产品主键
     * @param parameters
     * @return
     * @since JDK 1.7
     * @author baisong
     */
    List<Map<String, Object>> searchforcp(Map<String, Object> parameters);
}