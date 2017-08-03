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

import com.zx.emanage.inve.service.IWmsInveProtocolRenewalService;
import com.zx.emanage.util.gen.entity.WmsInveProtocolRenewal;
import com.zx.emanage.inve.vo.WmsInveProtocolRenewalSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveProtocolRenewalController {
	private static Logger log = LoggerFactory.getLogger(WmsInveProtocolRenewalController.class);
	
	@Autowired
	private IWmsInveProtocolRenewalService wmsinveprotocolrenewalService;

	  /**
     * 获取协议续期页面列表数据-导出Excel
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
	@RequestMapping(value = "/loanfina/wmsinveprotocolrenewalwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveProtocolRenewalSearchBeanVO queryInfo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinveprotocolrenewalService.getListWithoutPaging(queryInfo,user);
	}

    /**
     * 获取协议续期页面列表数据
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
	@RequestMapping(value = "/loanfina/wmsinveprotocolrenewalwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveProtocolRenewalSearchBeanVO queryInfo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinveprotocolrenewalService.getListWithPaging(queryInfo,user);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveProtocolRenewalVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsinveprotocolrenewalinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveProtocolRenewal getInfoByPK(Integer wms_inve_protocol_renewal_id) {
		return wmsinveprotocolrenewalService.getInfoByPK(wms_inve_protocol_renewal_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsinveprotocolrenewalsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveProtocolRenewal bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinveprotocolrenewalService.save(bean, user);
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
	@RequestMapping(value = "/loanfina/wmsinveprotocolrenewalupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveProtocolRenewal bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinveprotocolrenewalService.update(bean, user);
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