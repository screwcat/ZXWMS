package com.zx.emanage.inve.persist;

import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveReplace;

@MyBatisRepository
public interface WmsInveReplaceDao extends BaseDao<WmsInveReplace> {

	Map<String, Object> get4Appro(Integer wms_inve_replace_id);
	
}