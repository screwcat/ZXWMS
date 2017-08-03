package com.zx.emanage.inve.service;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveCommissionGeneralRulesNewSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralRulesNew;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCommissionGeneralRulesNewService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralRulesNewSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralRulesNewSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionGeneralRulesNewVO
	 * @author auto_generator
	 */	
	public WmsInveCommissionGeneralRulesNew getInfoByPK(Integer wms_inve_commission_general_rules_new_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCommissionGeneralRulesNew bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCommissionGeneralRulesNew bean, UserBean user);
	
	/**
     * Description :获取一般佣金规则所属公司下拉列表
     * @param WmsInveCommissionGeneralNetRulesNewSearchBeanVO
     * @return map
     * @author wangyihan
     * @date 2015/12/11
     */
    public Map<String, Object> getBelongCompanyList(WmsInveCommissionGeneralRulesNewSearchBeanVO queryInfo);
	
	/**
	 * Description:获取佣金规则列表
	 * @param queryInfo
	 * @return
	 */
	public Map<String, Object> getInfo(WmsInveCommissionGeneralRulesNewSearchBeanVO queryInfo);
	
	/**
     * Description :保存规则信息--客户
     * @param WmsInveCommissionGeneralRulesSearchBeanVO WmsInveCommissionGeneralRules HttpServletRequest
     * @return String
     */ 
    public String saveRules(UserBean user, WmsInveCommissionGeneralRulesNew bean, WmsInveCommissionGeneralRulesNewSearchBeanVO beanVO);
    
    
	
}