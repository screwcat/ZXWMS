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

import com.zx.emanage.inve.service.IWmsInveCommissionGeneralDisposableRulesNewService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralDisposableRulesNew;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralDisposableRulesNewSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionGeneralDisposableRulesNewController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionGeneralDisposableRulesNewController.class);
	
	@Autowired
	private IWmsInveCommissionGeneralDisposableRulesNewService wmsinvecommissiongeneraldisposablerulesnewService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissiongeneraldisposablerulesnewwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralDisposableRulesNewSearchBeanVO queryInfo) {
		return wmsinvecommissiongeneraldisposablerulesnewService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissiongeneraldisposablerulesnewwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralDisposableRulesNewSearchBeanVO queryInfo) {
		return wmsinvecommissiongeneraldisposablerulesnewService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionGeneralDisposableRulesNewVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissiongeneraldisposablerulesnewinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionGeneralDisposableRulesNew getInfoByPK(Integer wms_inve_commission_general_disposable_rules_new_id) {
		return wmsinvecommissiongeneraldisposablerulesnewService.getInfoByPK(wms_inve_commission_general_disposable_rules_new_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissiongeneraldisposablerulesnewsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionGeneralDisposableRulesNew bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissiongeneraldisposablerulesnewService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvecommissiongeneraldisposablerulesnewupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionGeneralDisposableRulesNew bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissiongeneraldisposablerulesnewService.update(bean, user);
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