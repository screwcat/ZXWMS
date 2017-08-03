package com.zx.emanage.inve.service;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveSalaryPreItemSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveSalaryPreTotalSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveSalarySetWorkFlowVO;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreItem;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveSalaryPreItemService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveSalaryPreItemSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveSalaryPreItemSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveSalaryPreItemVO
	 * @author auto_generator
	 */	
	public WmsInveSalaryPreItem getInfoByPK(Integer wms_inve_salary_pre_item_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveSalaryPreItem bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveSalaryPreItem bean, UserBean user);

    /**
     * @Title: getPerformanceSalaryPreItemsByDeptId
     * @Description: 通过static_month 和部门id 获取工资前提配置子表信息
     * @param queryInfo
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月11日 上午10:01:22
     * history:
     * 1、2017年1月11日 Administrator 创建方法
    */
    public Map<String, Object> getPerformanceSalaryPreItemsByDeptId(WmsInveSalaryPreItemSearchBeanVO queryInfo);

    /**
     * @Title: updatePerformanceSalaryPreItemsById
     * @Description: 批量更新工资前提配置表item表
     * @param gridData 
     * @param wmsInveSalaryPreTotalVO 
     * @author: zhangyunfei
     * @time:2017年1月11日 下午2:08:06
     * history:
     * 1、2017年1月11日 Administrator 创建方法
    */
    public String updatePerformanceSalaryPreItemsById(String gridData, UserBean user, String wms_inve_salary_pre_total_id, String create_user_id, WmsInveSalaryPreTotalSearchBeanVO wmsInveSalaryPreTotalVO);

    /**
     * @Title: approvePerformanceSalaryPre
     * @Description: 工资前提配置审批操作
     * @param gridData
     * @param vo
     * @return 
     * @author: zhangyunfei
     * @param user 
     * @param wmsInveSalaryPreTotalVO 
     * @time:2017年1月12日 下午6:23:10
     * history:
     * 1、2017年1月12日 Administrator 创建方法
    */
    public String approvePerformanceSalaryPre(String gridData, WmsInveSalarySetWorkFlowVO vo, UserBean user, WmsInveSalaryPreTotalSearchBeanVO wmsInveSalaryPreTotalVO);

    /**
     * @Title: approvePerformanceSalaryPreMoa
     * @Description: 手持审批绩效工资
     * @param advice(审批意见)
     * @param pass(审核结果)
     * @param dept_ids(团队ids)
     * @author: zhangyunfei
     * @return 
     * @time:2017年4月24日 上午10:46:25
     * history:
     * 1、2017年4月24日 Administrator 创建方法
    */
    public String approvePerformanceSalaryPreMoa(String advice, String pass, String personnel_shortcode, String dept_ids);
}