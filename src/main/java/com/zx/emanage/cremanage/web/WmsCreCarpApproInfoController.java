package com.zx.emanage.cremanage.web;

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

import com.zx.emanage.cremanage.service.IWmsCreCarpApproInfoService;
import com.zx.emanage.util.gen.entity.WmsCreCarpApproInfo;
import com.zx.emanage.cremanage.vo.WmsCreCarpApproInfoSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCarpApproInfoController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpApproInfoController.class);
	
	@Autowired
	private IWmsCreCarpApproInfoService wmscrecarpapproinfoService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrecarpapproinfowithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCarpApproInfoSearchBeanVO queryInfo) {
		return wmscrecarpapproinfoService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrecarpapproinfowithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCarpApproInfoSearchBeanVO queryInfo) {
		return wmscrecarpapproinfoService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpApproInfoVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrecarpapproinfoinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCarpApproInfo getInfoByPK(Integer wms_cre_carp_appro_info_id) {
		return wmscrecarpapproinfoService.getInfoByPK(wms_cre_carp_appro_info_id);
	}	
	/**
	 * Description :get single-table information by primary key 根据贷款主键查询
	 * @param primary key 
	 * @return WmsCreCarpApproInfoVO
	 * @author baisong
	 */	
	@RequestMapping(value = "/loanreview/wmscrecarpapproinfoinfobyhk.do", method = {RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> getInfoByHK(Integer wms_cre_credit_head_id,Integer carp_appro_type) {

		return wmscrecarpapproinfoService.getInfoByHK(wms_cre_credit_head_id,carp_appro_type);
	}
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrecarpapproinfosave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCarpApproInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarpapproinfoService.save(bean, user);
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
	@RequestMapping(value = "/loanreview/wmscrecarpapproinfoupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCarpApproInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarpapproinfoService.update(bean, user);
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