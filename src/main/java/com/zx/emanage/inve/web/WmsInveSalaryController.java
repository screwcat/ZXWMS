package com.zx.emanage.inve.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveSalaryService;
import com.zx.emanage.inve.vo.WmsInveSalarySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveSalary;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveSalaryController {
	private static Logger log = LoggerFactory.getLogger(WmsInveSalaryController.class);
	
	@Autowired
	private IWmsInveSalaryService wmsinvesalaryService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvesalarywithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveSalarySearchBeanVO queryInfo) {
		return wmsinvesalaryService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvesalarywithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveSalarySearchBeanVO queryInfo) {
		return wmsinvesalaryService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveSalaryVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvesalaryinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveSalary getInfoByPK(Integer wms_inve_salary_id) {
		return wmsinvesalaryService.getInfoByPK(wms_inve_salary_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvesalarysave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveSalary bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvesalaryService.save(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		/*
		// record log	
		if("success".equals(result)){
			String msg = "log content";
			SysTools.saveLog(request, msg); // record log method
		}
		*/
		return result;
	}

	/**
	 * Description :update method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvesalaryupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveSalary bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvesalaryService.update(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		/*			
		// record log	
		if("success".equals(result)){
			String msg = "log content";
			SysTools.saveLog(request, msg); // record log method
		}
		*/
		return result;
	}	
	
	
	/**
	 * 导出工资报表_平台版本
	 * 
	 * @param date
	 * @param request
	 * @param response
	 * 
	 */
	@RequestMapping(value="/inve/exportExcelWmsInveSalary.do", method={RequestMethod.GET, RequestMethod.POST})
    public void exportExcelWmsInveSalary(String date, HttpServletRequest request, HttpServletResponse response)
    {
        wmsinvesalaryService.exportExcelWmsInveSalary(date, request, response);
	}
	/**
	 * 
	 * @Title: selectWmsInveSalary
	 * @Description: 查询工资情况列表
	 * @param statics_month
	 * @param request
	 * @param response 
	 * @author: zhangmingjian
	 * @time:2017年5月10日 下午2:47:10
	 * history:
	 * 1、2017年5月10日 zhangmingjian 创建方法
	 */
	@RequestMapping(value="/inve/selectWmsInveSalary.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public Map<String, Object> selectWmsInveSalary(WmsInveSalarySearchBeanVO bean, HttpServletRequest request, HttpServletResponse response)
    {
	    
	    return wmsinvesalaryService.selectWmsInveSalary(bean).getGridData();
    }
	
	@RequestMapping(value="/inve/exportExcel.do", method={RequestMethod.GET, RequestMethod.POST})
    public void exportExcel(String month,WmsInveSalarySearchBeanVO bean, HttpServletRequest request, HttpServletResponse response)
    {
	    bean.setStatics_month(month);
        // 报表时间
        wmsinvesalaryService.exportExcel(bean, request, response);
    }
	
	
}