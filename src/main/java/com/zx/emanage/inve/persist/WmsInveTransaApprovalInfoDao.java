package com.zx.emanage.inve.persist;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveTransaApprovalInfo;

@MyBatisRepository
public interface WmsInveTransaApprovalInfoDao extends BaseDao<WmsInveTransaApprovalInfo> {
	
	public Integer updateAdvice(WmsInveTransaApprovalInfo info);
	
}