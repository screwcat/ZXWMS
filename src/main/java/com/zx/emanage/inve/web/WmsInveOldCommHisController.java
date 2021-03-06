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

import com.zx.emanage.inve.service.IWmsInveOldCommHisService;
import com.zx.emanage.util.gen.entity.WmsInveOldCommHis;
import com.zx.emanage.inve.vo.WmsInveOldCommHisSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveOldCommHisController {
	private static Logger log = LoggerFactory.getLogger(WmsInveOldCommHisController.class);
	
	@Autowired
	private IWmsInveOldCommHisService wmsinveoldcommhisService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinveoldcommhiswithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveOldCommHisSearchBeanVO queryInfo) {
		return wmsinveoldcommhisService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinveoldcommhiswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveOldCommHisSearchBeanVO queryInfo) {
		return wmsinveoldcommhisService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveOldCommHisVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinveoldcommhisinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveOldCommHis getInfoByPK(Integer wms_inve_old_comm_his_id) {
		return wmsinveoldcommhisService.getInfoByPK(wms_inve_old_comm_his_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinveoldcommhissave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveOldCommHis bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinveoldcommhisService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinveoldcommhisupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveOldCommHis bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinveoldcommhisService.update(bean, user);
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