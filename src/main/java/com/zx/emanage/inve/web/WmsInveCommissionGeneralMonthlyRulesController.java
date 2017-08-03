package com.zx.emanage.inve.web;

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

import com.zx.emanage.inve.service.IWmsInveCommissionGeneralMonthlyRulesService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralMonthlyRules;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralMonthlyRulesSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionGeneralMonthlyRulesController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionGeneralMonthlyRulesController.class);
	
	@Autowired
	private IWmsInveCommissionGeneralMonthlyRulesService wmsinvecommissiongeneralmonthlyrulesService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralmonthlyruleswithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralMonthlyRulesSearchBeanVO queryInfo) {
		return wmsinvecommissiongeneralmonthlyrulesService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralmonthlyruleswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralMonthlyRulesSearchBeanVO queryInfo) {
		return wmsinvecommissiongeneralmonthlyrulesService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionGeneralMonthlyRulesVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralmonthlyrulesinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionGeneralMonthlyRules getInfoByPK(Integer wms_inve_commission_general_monthly_rules_id) {
		return wmsinvecommissiongeneralmonthlyrulesService.getInfoByPK(wms_inve_commission_general_monthly_rules_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralmonthlyrulessave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionGeneralMonthlyRules bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissiongeneralmonthlyrulesService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralmonthlyrulesupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionGeneralMonthlyRules bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissiongeneralmonthlyrulesService.update(bean, user);
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