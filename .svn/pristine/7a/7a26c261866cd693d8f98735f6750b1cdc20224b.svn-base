package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralRules;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralRulesSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCommissionGeneralRulesService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralRulesSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralRulesSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionGeneralRulesVO
	 * @author auto_generator
	 */	
	public WmsInveCommissionGeneralRules getInfoByPK(Integer wms_inve_commission_general_rules_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCommissionGeneralRules bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCommissionGeneralRules bean, UserBean user);
	/**
	 * Description :获取一般佣金规则信息-根据beanvo中参数不同获取不同类型的信息
	 * @param WmsInveCommissionGeneralRulesSearchBeanVO
	 * @return map
	 * @author baisong
	 * @date 2015/9/17
	 */	
	public Map<String,Object> getInfo(WmsInveCommissionGeneralRulesSearchBeanVO beanvo);	
	/**
	 * Description :保存规则信息--客户
	 * @param WmsInveCommissionGeneralRulesSearchBeanVO WmsInveCommissionGeneralRules HttpServletRequest
	 * @return String
	 * @author baisong
	 * @date 2015/9/17
	 */	
	public String saveKHInfo(UserBean user,WmsInveCommissionGeneralRules bean,WmsInveCommissionGeneralRulesSearchBeanVO beanvo);
}