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

import com.zx.emanage.inve.service.IWmsInveCommissionSpecialRulesService;
import com.zx.emanage.inve.vo.WmsInveCommissionSpecialRulesSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveCommissionSpecialRules;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionSpecialRulesController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionSpecialRulesController.class);
	
	@Autowired
	private IWmsInveCommissionSpecialRulesService wmsinvecommissionspecialrulesService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissionspecialruleswithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionSpecialRulesSearchBeanVO queryInfo) {
		return wmsinvecommissionspecialrulesService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissionspecialruleswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionSpecialRulesSearchBeanVO queryInfo) {
		return wmsinvecommissionspecialrulesService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionSpecialRulesVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissionspecialrulesinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionSpecialRules getInfoByPK(Integer wms_inve_commission_special_rules_id) {
		return wmsinvecommissionspecialrulesService.getInfoByPK(wms_inve_commission_special_rules_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissionspecialrulessave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionSpecialRules bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionspecialrulesService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvecommissionspecialrulesupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionSpecialRules bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionspecialrulesService.update(bean, user);
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
	 * @Title: doSaveAndUpdate
	 * @Description: 提成特殊规则的添加和修改
	 * @param request
	 * @param bean
	 * @return String 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月16日 上午11:26:33
	 */
	@RequestMapping(value = "/inve/wmsinvecommissionspecialrulessaveandupdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String doSaveAndUpdate(HttpServletRequest request, String specialRulesStr) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionspecialrulesService.saveAndUpdate(specialRulesStr, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		
		// record log	
		if("success".equals(result)){
			String msg = "理财提成特殊规则";
			SysTools.saveLog(request, msg); // record log method
		}
		
		return result;
	}
	
	/**
	 * @Title: getListWithoutPagingForCompare 
	 * @Description: 用于前台数据重复校验
	 * @param queryInfo
	 * @return Map<String,Object> 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月21日 下午1:48:18
	 */
	@RequestMapping(value = "/inve/wmsinvecommissionspecialruleswithoutpagingforcompare.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPagingForCompare(WmsInveCommissionSpecialRulesSearchBeanVO queryInfo) {
		return wmsinvecommissionspecialrulesService.getListWithoutPagingForCompare(queryInfo);
	}
}