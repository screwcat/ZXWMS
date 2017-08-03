package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCommissionRankingReward;
import com.zx.emanage.inve.vo.WmsInveCommissionRankingRewardSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCommissionRankingRewardService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionRankingRewardSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCommissionRankingRewardSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionRankingRewardVO
	 * @author auto_generator
	 */	
	public WmsInveCommissionRankingReward getInfoByPK(Integer wms_inve_commission_ranking_reward_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCommissionRankingReward bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCommissionRankingReward bean, UserBean user);
	
	/**
	 * Description :根据奖励方式获取奖励规则 
	 * @param WmsInveCommissionRankingRewardSearchBeanVO
	 * @return Map<String,Object>
	 * @author baisong
	 */	
	public Map<String,Object> getInfoByMethod(WmsInveCommissionRankingRewardSearchBeanVO queryInfo);	
}