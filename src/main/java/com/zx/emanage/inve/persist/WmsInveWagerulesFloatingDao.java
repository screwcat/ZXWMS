package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveWagerulesFloating;

@MyBatisRepository
public interface WmsInveWagerulesFloatingDao extends BaseDao<WmsInveWagerulesFloating> {

	List<Map<String, Object>> searchByFK(Map<String, Object> paramMap);

	void deleteByInfo(Integer wms_inve_wagerules_floating_id);
	
}