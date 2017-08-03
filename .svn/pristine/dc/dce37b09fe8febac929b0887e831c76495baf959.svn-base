package com.zx.emanage.sysmanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveTaxpointRules;
import com.zx.emanage.sysmanage.vo.WmsInveTaxpointRulesSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveTaxpointRulesService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveTaxpointRulesSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveTaxpointRulesSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveTaxpointRulesVO
	 * @author auto_generator
	 */	
	public WmsInveTaxpointRules getInfoByPK(Integer wms_inve_taxpoint_rules_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveTaxpointRules bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveTaxpointRules bean, UserBean user);
	/**
	 * Description :查询固定值 
	 * @return 固定值
	 * @author yangqiyu
	 */
	public String taxfixedToPage();
	/**
	 * 保存操作
	 * @return "success" or "error"
	 * @author yangqiyu
	 */
	public String doChange(String bean, UserBean user);
}