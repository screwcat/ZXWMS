package com.zx.emanage.inve.persist;

import java.util.List;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveWagerulesHead;

@MyBatisRepository
public interface WmsInveWagerulesHeadDao extends BaseDao<WmsInveWagerulesHead> {

	List<WmsInveWagerulesHead> getListByRepeat(WmsInveWagerulesHead wagerulesHead);
	
}