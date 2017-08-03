package com.zx.emanage.inve.service;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveCommissionSpecialRulesSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveCommissionSpecialRules;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCommissionSpecialRulesService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionSpecialRulesSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCommissionSpecialRulesSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionSpecialRulesVO
	 * @author auto_generator
	 */	
	public WmsInveCommissionSpecialRules getInfoByPK(Integer wms_inve_commission_special_rules_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCommissionSpecialRules bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCommissionSpecialRules bean, UserBean user);
	
	/**
	 * @Title: saveAndUpdate 
	 * @Description: 提成特殊规则的添加和修改
	 * @param list
	 * @param user
	 * @return String 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月16日 上午11:49:57
	 */
	public String saveAndUpdate(String specialRulesStr, UserBean user);
	
	/**
	 * @Title: getListWithoutPagingForCompare 
	 * @Description: 用于前台数据重复校验
	 * @param queryInfo
	 * @return Map<String,Object> 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月21日 下午1:49:06
	 */
	public Map<String, Object> getListWithoutPagingForCompare(WmsInveCommissionSpecialRulesSearchBeanVO queryInfo);
}