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

import com.zx.emanage.inve.service.IWmsInveCommissionGeneralMonthlyRulesNewService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralMonthlyRulesNew;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralMonthlyRulesNewSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionGeneralMonthlyRulesNewController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionGeneralMonthlyRulesNewController.class);
	
	@Autowired
	private IWmsInveCommissionGeneralMonthlyRulesNewService wmsinvecommissiongeneralmonthlyrulesnewService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralmonthlyrulesnewwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralMonthlyRulesNewSearchBeanVO queryInfo) {
		return wmsinvecommissiongeneralmonthlyrulesnewService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralmonthlyrulesnewwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralMonthlyRulesNewSearchBeanVO queryInfo) {
		return wmsinvecommissiongeneralmonthlyrulesnewService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionGeneralMonthlyRulesNewVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralmonthlyrulesnewinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionGeneralMonthlyRulesNew getInfoByPK(Integer wms_inve_commission_general_monthly_rules_new_id) {
		return wmsinvecommissiongeneralmonthlyrulesnewService.getInfoByPK(wms_inve_commission_general_monthly_rules_new_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralmonthlyrulesnewsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionGeneralMonthlyRulesNew bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissiongeneralmonthlyrulesnewService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralmonthlyrulesnewupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionGeneralMonthlyRulesNew bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissiongeneralmonthlyrulesnewService.update(bean, user);
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