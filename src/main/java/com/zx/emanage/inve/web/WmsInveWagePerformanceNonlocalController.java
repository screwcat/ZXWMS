package com.zx.emanage.inve.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveWagePerformanceNonlocalService;
import com.zx.emanage.inve.vo.WmsInveWagePerformanceNonlocalSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveWagePerformanceNonlocal;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveWagePerformanceNonlocalController {
	private static Logger log = LoggerFactory.getLogger(WmsInveWagePerformanceNonlocalController.class);
	
	@Autowired
	private IWmsInveWagePerformanceNonlocalService wmsinvewageperformancenonlocalService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvewageperformancenonlocalwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo) {
		return wmsinvewageperformancenonlocalService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvewageperformancenonlocalwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo) {
		return wmsinvewageperformancenonlocalService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveWagePerformanceNonlocalVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvewageperformancenonlocalinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveWagePerformanceNonlocal getInfoByPK(Integer wms_inve_wage_performance_nonlocal_id) {
		return wmsinvewageperformancenonlocalService.getInfoByPK(wms_inve_wage_performance_nonlocal_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvewageperformancenonlocalsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveWagePerformanceNonlocal bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvewageperformancenonlocalService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvewageperformancenonlocalupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveWagePerformanceNonlocal bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvewageperformancenonlocalService.update(bean, user);
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
	 * 获取公司下拉列表
	 */
	@RequestMapping(value = "/inve/getCompanySelectForSalary.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getCompanySelectForSalary(HttpServletRequest request, WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvewageperformancenonlocalService.getCompanySelectForSalary(user, queryInfo);
    }
	
	/**
     * 根据公司ID获取部门下拉列表
     */
    @RequestMapping(value = "/inve/getDeptSelectByCompanyIdForSalary.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getDeptSelectByCompanyIdForSalary(HttpServletRequest request, WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvewageperformancenonlocalService.getDeptSelectByCompanyIdForSalary(user, queryInfo);
    }
    
    /**
     * 根据部门ID获取团队下拉列表
     */
    @RequestMapping(value = "/inve/getTeamSelectByDeptIdForSalary.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getTeamSelectByDeptIdForSalary(HttpServletRequest request, WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvewageperformancenonlocalService.getTeamSelectByDeptIdForSalary(user, queryInfo);
    }
	
}