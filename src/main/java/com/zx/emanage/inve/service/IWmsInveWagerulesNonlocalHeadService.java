package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveWagerulesNonlocalHead;
import com.zx.emanage.util.gen.entity.WmsInveWagerulesNonlocalLv;
import com.zx.emanage.inve.vo.WmsInveWagerulesNonlocalHeadSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveWagerulesNonlocalLvSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveWagerulesNonlocalHeadService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveWagerulesNonlocalHeadSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
//	public Map<String, Object> getListWithPaging(WmsInveWagerulesNonlocalHeadSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveWagerulesNonlocalHeadVO
	 * @author auto_generator
	 */	
	public WmsInveWagerulesNonlocalHead getInfoByPK(Integer wms_inve_wagerules_nonlocal_head_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveWagerulesNonlocalHead bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveWagerulesNonlocalHead bean, UserBean user);
/**
 * 理财薪资规则裂变显示
 * @param queryInfo
 * @return String
 * @author jiaodelong
 * @data 2016年1月14日
 */
	Map<String, Object> getListWithPaging(WmsInveWagerulesNonlocalHead queryInfo);
	/**
	 * 保存理财薪资规程
	 * 
	 * @param bean
	 * @param beanLv
	 * @return String
	 * @author jiaodelong
	 * @data 2016年1月14日
	 */
	public String saveFinancialSalaryRule(WmsInveWagerulesNonlocalLvSearchBeanVO beanLv,UserBean user );
	/**
	 * 根据wms_inve_wagerules_nonlocal_head_id 查询全部理财薪资规则
	 * @param wms_inve_wagerules_nonlocal_head_id
	 * @return Map<String, Object>
	 * @author jiaodelong
	 * @data 2016年1月14日
	 */
	public Map<String, Object> getFinancialSalaryRuleAll(WmsInveWagerulesNonlocalHead bean);
	/**
	 * 修改理财薪资规则
	 * 
	 * @param bean
	 * @param beanLv
	 * @return String
	 * @author jiaodelong
	 * @data 2016年1月14日
	 */
	public String updateFinancialSalaryRule(WmsInveWagerulesNonlocalLvSearchBeanVO beanLv,UserBean user );
	
	/**
	 * 修改理财薪资规则
	 * 
	 * @param wms_inve_wagerules_nonlocal_head_id
	 * @return void
	 * @author jiaodelong
	 * @data 2016年1月15日
	 */
	public String deleteFinancialSalaryRule(Integer wms_inve_wagerules_nonlocal_head_id);
	/**
	 * 复制理财薪资规则
	 * 
	 * @param wms_inve_wagerules_nonlocal_head_id
	 * @return string
	 * @author jiaodelong
	 * @data 2016年1月15日
	 */
	public String copyFinancialSalaryRule(Integer wms_inve_wagerules_nonlocal_head_id,UserBean user);

}
