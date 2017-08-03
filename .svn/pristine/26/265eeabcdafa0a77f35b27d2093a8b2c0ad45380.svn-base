package com.zx.emanage.loanappro.web;

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

import com.zx.emanage.loanappro.service.IWmsCreApproProtocolSecuredService;
import com.zx.emanage.util.gen.entity.WmsCreApproProtocolSecured;
import com.zx.emanage.loanappro.vo.WmsCreApproProtocolSecuredSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreApproProtocolSecuredController {
	private static Logger log = LoggerFactory.getLogger(WmsCreApproProtocolSecuredController.class);
	
	@Autowired
	private IWmsCreApproProtocolSecuredService wmscreapproprotocolsecuredService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanappro/wmscreapproprotocolsecuredwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreApproProtocolSecuredSearchBeanVO queryInfo) {
		return wmscreapproprotocolsecuredService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanappro/wmscreapproprotocolsecuredwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreApproProtocolSecuredSearchBeanVO queryInfo) {
		return wmscreapproprotocolsecuredService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreApproProtocolSecuredVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanappro/wmscreapproprotocolsecuredinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreApproProtocolSecured getInfoByPK(Integer wms_cre_appro_protocol_secured_id) {
		return wmscreapproprotocolsecuredService.getInfoByPK(wms_cre_appro_protocol_secured_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanappro/wmscreapproprotocolsecuredsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreApproProtocolSecured bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscreapproprotocolsecuredService.save(bean, user);
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
	@RequestMapping(value = "/loanappro/wmscreapproprotocolsecuredupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreApproProtocolSecured bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscreapproprotocolsecuredService.update(bean, user);
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