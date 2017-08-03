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

import com.zx.emanage.inve.service.IWmsInvePruductYearPaySpecialService;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.emanage.inve.vo.WmsInvePruductYearPaySpecialSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInvePruductYearPaySpecialController {
	private static Logger log = LoggerFactory.getLogger(WmsInvePruductYearPaySpecialController.class);
	
	@Autowired
	private IWmsInvePruductYearPaySpecialService wmsinvepruductyearpayspecialService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvepruductyearpayspecialwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInvePruductYearPaySpecialSearchBeanVO queryInfo) {
		return wmsinvepruductyearpayspecialService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvepruductyearpayspecialwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInvePruductYearPaySpecialSearchBeanVO queryInfo) {
		return wmsinvepruductyearpayspecialService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInvePruductYearPaySpecialVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvepruductyearpayspecialinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInvePruductYearPaySpecial getInfoByPK(Integer wms_inve_pruduct_year_pay_special_id) {
		return wmsinvepruductyearpayspecialService.getInfoByPK(wms_inve_pruduct_year_pay_special_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvepruductyearpayspecialsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInvePruductYearPaySpecial bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvepruductyearpayspecialService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvepruductyearpayspecialupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInvePruductYearPaySpecial bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvepruductyearpayspecialService.update(bean, user);
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
	 * Description :根据产品主键获取 年付特表信息
	 * @param primary key 
	 * @return WmsInvePruductYearPaySpecial
	 * @author baisong
	 *  2015-8-14
	 */	
	@RequestMapping(value = "/inve/wmsinvepruductyearpayspecialinfobyck.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInvePruductYearPaySpecial getInfoByCK(Integer wms_inve_pruduct_category_id) {
		return wmsinvepruductyearpayspecialService.getInfoByCK(wms_inve_pruduct_category_id);
	}
}