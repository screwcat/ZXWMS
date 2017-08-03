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

import com.zx.emanage.reportmanage.service.IWmsInveCommissionRewardPerformanceService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionRewardPerformance;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionRewardPerformanceSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionRewardPerformanceController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionRewardPerformanceController.class);
	
	@Autowired
	private IWmsInveCommissionRewardPerformanceService wmsinvecommissionrewardperformanceService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionrewardperformancewithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionRewardPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionrewardperformanceService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionrewardperformancewithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionRewardPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionrewardperformanceService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionRewardPerformanceVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/reportmanage/wmsinvecommissionrewardperformanceinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionRewardPerformance getInfoByPK(Integer wms_inve_commission_reward_performance_id) {
		return wmsinvecommissionrewardperformanceService.getInfoByPK(wms_inve_commission_reward_performance_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/reportmanage/wmsinvecommissionrewardperformancesave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionRewardPerformance bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionrewardperformanceService.save(bean, user);
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
	@RequestMapping(value = "/reportmanage/wmsinvecommissionrewardperformanceupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionRewardPerformance bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionrewardperformanceService.update(bean, user);
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
}