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

import com.zx.emanage.inve.service.IWmsInveCommissionRewardHeadRulesService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralRules;
import com.zx.emanage.util.gen.entity.WmsInveCommissionRewardHeadRules;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralRulesSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveCommissionRewardHeadRulesSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionRewardHeadRulesController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionRewardHeadRulesController.class);
	
	@Autowired
	private IWmsInveCommissionRewardHeadRulesService wmsinvecommissionrewardheadrulesService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissionrewardheadruleswithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionRewardHeadRulesSearchBeanVO queryInfo) {
		return wmsinvecommissionrewardheadrulesService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissionrewardheadruleswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionRewardHeadRulesSearchBeanVO queryInfo) {
		return wmsinvecommissionrewardheadrulesService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionRewardHeadRulesVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissionrewardheadrulesinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionRewardHeadRules getInfoByPK(Integer wms_inve_commission_reward_head_rules_id) {
		return wmsinvecommissionrewardheadrulesService.getInfoByPK(wms_inve_commission_reward_head_rules_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissionrewardheadrulessave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionRewardHeadRules bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionrewardheadrulesService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvecommissionrewardheadrulesupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionRewardHeadRules bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionrewardheadrulesService.update(bean, user);
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
	 * Description :保存奖励信息
	 * @param bean
	 * @param beanvo
	 * @return "success" or "error" or user defined
	 * @author baisong
	 * @date 2015/9/16
	 */
	@RequestMapping(value = "/inve/wmsinvecommissionrewardheadrulessaveall.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSaveAll(HttpServletRequest request, WmsInveCommissionRewardHeadRules bean,WmsInveCommissionRewardHeadRulesSearchBeanVO beanvo) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionrewardheadrulesService.saveAll(bean, user,beanvo);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// record log	
		if("success".equals(result)){
			String msg = "系统管理-理财奖励规则-保存";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}
	
	/**
	 * Description :查询数据库中现有有效数据--奖励规则
	 * @param 
	 * @return map
	 * @author baisong
	 * @date 2015/9/16
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissionrewardheadrulesinfo.do", method = {RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> getInfo() {
		return wmsinvecommissionrewardheadrulesService.getInfo();
	}
	/**
	 * Description :保存奖励信息
	 * @param bean
	 * @param beanvo
	 * @return "success" or "error" or user defined
	 * @author baisong
	 * @date 2015/9/16
	 */
	@RequestMapping(value = "/inve/doSaveInfo.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSaveInfo(HttpServletRequest request,WmsInveCommissionRewardHeadRulesSearchBeanVO beanvo) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionrewardheadrulesService.doSaveInfo(user,beanvo);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// record log	
		if("success".equals(result)){
			String msg = "系统管理-理财奖励规则-保存";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}
}