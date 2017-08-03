package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCommissionRankingReward;

@MyBatisRepository
public interface WmsInveCommissionRankingRewardDao extends BaseDao<WmsInveCommissionRankingReward> {
	//批量保存排名信息
	Integer saveAll(List<WmsInveCommissionRankingReward> list);
	/**
	 * Description :根据奖励方式获取奖励规则 
	 * @param map
	 * @return list
	 * @author baisong
	 */	
	List<Map<String,Object>> getInfoByMethod(Map<String, Object> paramMap);
}