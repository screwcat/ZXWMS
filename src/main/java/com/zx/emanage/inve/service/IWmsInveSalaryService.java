package com.zx.emanage.inve.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.emanage.inve.vo.WmsInveSalarySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveSalary;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveSalaryService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveSalarySearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveSalarySearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveSalaryVO
	 * @author auto_generator
	 */	
	public WmsInveSalary getInfoByPK(Integer wms_inve_salary_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveSalary bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveSalary bean, UserBean user);

	/**
	 * 导出工资报表_平台版本
	 * @param date
	 * @param request
	 * @param response
	 */
    public void exportExcelWmsInveSalary(String date, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 
     * @Title: selectWmsInveSalary
     * @Description: 
     * @param user
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年5月10日 下午2:55:10
     * history:
     * 1、2017年5月10日 zhangmingjian 创建方法
     */
    public GridDataBean<Map<String, Object>>  selectWmsInveSalary(WmsInveSalarySearchBeanVO bean);
    /**
     * 
     * @Title: exportExcelWmsInveSalary1
     * @Description: 导出列表 
     * @param bean
     * @param request
     * @param response 
     * @author: zhangmingjian
     * @time:2017年5月10日 下午6:08:44
     * history:
     * 1、2017年5月10日 zhangmingjian 创建方法
     */
    public void exportExcel(WmsInveSalarySearchBeanVO bean, HttpServletRequest request, HttpServletResponse response);
    
}