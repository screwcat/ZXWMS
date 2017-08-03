package com.zx.emanage.cremanage.persist;

import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsCreCreditReturnSearch;

@MyBatisRepository
public interface WmsCreCreditReturnSearchDao extends BaseDao<WmsCreCreditReturnSearch> {
	public void addWmsCreCreditReturnInfo(Map<String, Object> parMap);
}