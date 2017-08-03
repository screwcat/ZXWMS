package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveOldCommBaseDataSpecialHis;

@MyBatisRepository
public interface WmsInveOldCommBaseDataSpecialHisDao extends BaseDao<WmsInveOldCommBaseDataSpecialHis> {

	/**
	 * @Title: getWmsInveOldCommBaseDataByCondition
	 * @Description: 查询当月老佣金特殊数据历史数据
	 * @param paramMap
	 * @author: zhangyunfei
	 * @time:2016年11月8日 下午3:45:17
	*/
	List<Map<String, Object>> getWmsInveOldCommBaseDataByCondition(
			Map<String, Object> paramMap);
	
}