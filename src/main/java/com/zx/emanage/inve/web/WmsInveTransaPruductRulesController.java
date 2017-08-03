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

import com.zx.emanage.inve.exception.InveTransException;
import com.zx.emanage.inve.service.IWmsInveTransaPruductRulesService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveTransaPruductRules;
import com.zx.emanage.inve.vo.WmsInveTransaPruductRulesSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveTransaPruductRulesController {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaPruductRulesController.class);
	
	@Autowired
	private IWmsInveTransaPruductRulesService wmsinvetransapruductrulesService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvetransapruductruleswithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveTransaPruductRulesSearchBeanVO queryInfo) {
		return wmsinvetransapruductrulesService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvetransapruductruleswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveTransaPruductRulesSearchBeanVO queryInfo) {
		return wmsinvetransapruductrulesService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveTransaPruductRulesVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvetransapruductrulesinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveTransaPruductRules getInfoByPK(Integer wms_inve_transa_pruduct_rules_id) {
		return wmsinvetransapruductrulesService.getInfoByPK(wms_inve_transa_pruduct_rules_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvetransapruductrulessave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveTransaPruductRules bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvetransapruductrulesService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvetransapruductrulesupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSaveUpdate(HttpServletRequest request,WmsInveTransaPruductRulesSearchBeanVO beanvo) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			try{
				result = wmsinvetransapruductrulesService.doSaveUpdate(user,beanvo);
			}catch(InveTransException j){//如果是数据重复
				//log.error(j.getMessage());
				result = "errorid";	
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
					
		// record log	
		if("success".equals(result)){
			String msg = "理财配置管理--产品对应佣金规则--保存";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}	
}