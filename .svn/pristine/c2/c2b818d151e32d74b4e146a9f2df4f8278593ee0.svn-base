package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveWagerulesHead;
import com.zx.emanage.inve.vo.WmsInveWagerulesHeadSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveWagerulesHeadService {
	
	/**
	 * Description :实现理财客户经理的工资规则的保存
	 * @param bean 工资规则记录Bean
	 * @param fdgz 浮动工资信息
	 * @return "success" or "error" 返回保存提示信息
	 * @author hancd
	 */	
	public String save(WmsInveWagerulesHead bean, UserBean user,String fdgz);
	
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveWagerulesHeadSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveWagerulesHeadSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveWagerulesHeadVO
	 * @author auto_generator
	 */	
	public WmsInveWagerulesHead getInfoByPK(Integer wms_inve_wagerules_head_id);	
	
	/**
	 * Description :更新理财工资规则
	 * @param bean contains pk at least
	 * @param fdgz 浮动工资列表
	 * @return "success" or "error" or user defined
	 * @author hancd
	 */
	public String update(WmsInveWagerulesHead bean, UserBean user,String fdgz);
	/**
	 * Description :通过传递不同职务获取相关职务的所有配置信息
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	public Map<String, Object> getWmsInveWageRulesList(
			WmsInveWagerulesHeadSearchBeanVO queryInfo);
	/**
	 * Description :通过传递工资表主键信息获取相关基本信息和浮动工资信息设置
	 * @param wms_inve_wagerules_head_id
	 * @return record list
	 * @author hancd
	 */
	public Map<String, Object> getInveWageRulesHFbypk(
			Integer wms_inve_wagerules_head_id);
}