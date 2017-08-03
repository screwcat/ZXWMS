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

import com.zx.emanage.inve.service.IWmsInveCommissionGeneralRulesService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralRules;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralRulesSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionGeneralRulesController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionGeneralRulesController.class);
	
	@Autowired
	private IWmsInveCommissionGeneralRulesService wmsinvecommissiongeneralrulesService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralruleswithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralRulesSearchBeanVO queryInfo) {
		return wmsinvecommissiongeneralrulesService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralruleswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralRulesSearchBeanVO queryInfo) {
		return wmsinvecommissiongeneralrulesService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionGeneralRulesVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralrulesinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionGeneralRules getInfoByPK(Integer wms_inve_commission_general_rules_id) {
		return wmsinvecommissiongeneralrulesService.getInfoByPK(wms_inve_commission_general_rules_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralrulessave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionGeneralRules bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissiongeneralrulesService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralrulesupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionGeneralRules bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissiongeneralrulesService.update(bean, user);
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
	 * Description :获取一般佣金规则信息-根据beanvo中参数不同获取不同类型的信息
	 * @param WmsInveCommissionGeneralRulesSearchBeanVO
	 * @return map
	 * @author baisong
	 * @date 2015/9/17
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissiongeneralrulesinfo.do", method = {RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> getInfo(WmsInveCommissionGeneralRulesSearchBeanVO beanvo) {
		return wmsinvecommissiongeneralrulesService.getInfo(beanvo);
	}	
	
	/**
	 * Description :保存规则信息--客户
	 * @param WmsInveCommissionGeneralRulesSearchBeanVO WmsInveCommissionGeneralRules HttpServletRequest
	 * @return String
	 * @author baisong
	 * @date 2015/9/17
	 */	
	@RequestMapping(value = "/inve/saveKHInfo.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveKHInfo(HttpServletRequest request,WmsInveCommissionGeneralRules bean,WmsInveCommissionGeneralRulesSearchBeanVO beanvo) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissiongeneralrulesService.saveKHInfo(user,bean, beanvo);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}		
		// record log	
		if("success".equals(result)){
			String msg = "系统管理--理财佣金一般规则--保存";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}	
}