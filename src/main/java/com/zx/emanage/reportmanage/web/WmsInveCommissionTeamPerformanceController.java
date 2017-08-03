package com.zx.emanage.reportmanage.web;

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

import com.zx.emanage.reportmanage.service.IWmsInveCommissionTeamPerformanceService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionTeamPerformance;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionTeamPerformanceSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionTeamPerformanceController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionTeamPerformanceController.class);
	
	@Autowired
	private IWmsInveCommissionTeamPerformanceService wmsinvecommissionteamperformanceService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionteamperformancewithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionteamperformanceService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionteamperformancewithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionteamperformanceService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionTeamPerformanceVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/reportmanage/wmsinvecommissionteamperformanceinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionTeamPerformance getInfoByPK(Integer wms_inve_commission_team_performance_id) {
		return wmsinvecommissionteamperformanceService.getInfoByPK(wms_inve_commission_team_performance_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/reportmanage/wmsinvecommissionteamperformancesave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionTeamPerformance bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionteamperformanceService.save(bean, user);
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
	@RequestMapping(value = "/reportmanage/wmsinvecommissionteamperformanceupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionTeamPerformance bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionteamperformanceService.update(bean, user);
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
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/reportmanage/getListTeam.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListTeam(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionteamperformanceService.getListTeam(queryInfo);
	}
}