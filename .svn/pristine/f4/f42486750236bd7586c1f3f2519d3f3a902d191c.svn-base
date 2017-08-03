package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCommissionRewardHeadRules;
import com.zx.emanage.inve.vo.WmsInveCommissionRewardHeadRulesSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCommissionRewardHeadRulesService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionRewardHeadRulesSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCommissionRewardHeadRulesSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionRewardHeadRulesVO
	 * @author auto_generator
	 */	
	public WmsInveCommissionRewardHeadRules getInfoByPK(Integer wms_inve_commission_reward_head_rules_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCommissionRewardHeadRules bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCommissionRewardHeadRules bean, UserBean user);
	
	/**
	 * Description :保存奖励信息
	 * @param bean
	 * @param beanvo
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	public String saveAll(WmsInveCommissionRewardHeadRules bean, UserBean user,WmsInveCommissionRewardHeadRulesSearchBeanVO beanvo);

	/**
	 * Description :查询数据库中现有有效数据--奖励规则
	 * @param 
	 * @return map
	 * @author baisong
	 * @date 2015/9/16
	 */	
	public Map<String,Object> getInfo();	
	/**
	 * Description :保存奖励信息--2015-11-4需求变更
	 * @param bean
	 * @param beanvo
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	public String doSaveInfo( UserBean user,WmsInveCommissionRewardHeadRulesSearchBeanVO beanvo);

	
}