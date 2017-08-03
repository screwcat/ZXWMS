package com.zx.emanage.inve.web;

import java.util.HashMap;
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

import com.google.gson.Gson;
import com.zx.emanage.inve.service.IWmsInveReplaceService;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveReplace;
import com.zx.emanage.inve.vo.WmsInveReplaceSearchBeanVO;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveReplaceController {
	private static Logger log = LoggerFactory.getLogger(WmsInveReplaceController.class);
	
	@Autowired
	private IWmsInveReplaceService wmsinvereplaceService;
	
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvereplacewithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveReplaceSearchBeanVO queryInfo) {
		return wmsinvereplaceService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvereplacewithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveReplaceSearchBeanVO queryInfo) {
		return wmsinvereplaceService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveReplaceVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvereplaceinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveReplace getInfoByPK(Integer wms_inve_replace_id) {
		return wmsinvereplaceService.getInfoByPK(wms_inve_replace_id);
	}
	
	@RequestMapping(value = "/inve/wmsinvereplaceinfobypk4Appro.do", method = {RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> get4Appro(Integer wms_inve_replace_id) {
		return wmsinvereplaceService.get4Appro(wms_inve_replace_id);
	}
	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvereplacesave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveReplace bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvereplaceService.save(bean, user);
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
	
	@RequestMapping(value = "/inve/wmsinvereplacesavecw.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSaveCW(HttpServletRequest request, WmsInveReplace bean,String pass,String advice) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvereplaceService.cwsp(bean, user,pass,advice);
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
	
	@RequestMapping(value = "/inve/wmsinvereplacesavezjl.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doCWSaveZJL(HttpServletRequest request, WmsInveReplace bean,String pass) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvereplaceService.zjlsp(bean, user,pass);
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
	@RequestMapping(value = "/inve/wmsinvereplaceupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveReplace bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvereplaceService.update(bean, user);
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
	
	@RequestMapping(value = "/inve/cancelWmsInveReplace.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doCancel(HttpServletRequest request, WmsInveReplace bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvereplaceService.cancel(bean, user);
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