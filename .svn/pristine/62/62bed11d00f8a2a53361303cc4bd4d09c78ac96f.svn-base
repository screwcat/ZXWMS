package com.zx.emanage.loanfina.web;

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

import com.zx.emanage.loanfina.service.IWmsFinaCreRealrepayInfoService;
import com.zx.emanage.util.gen.entity.WmsFinaCreRealrepayInfo;
import com.zx.emanage.loanfina.vo.WmsFinaCreRealrepayInfoSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaCreRealrepayInfoController {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreRealrepayInfoController.class);
	
	@Autowired
	private IWmsFinaCreRealrepayInfoService wmsfinacrerealrepayinfoService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacrerealrepayinfowithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsFinaCreRealrepayInfoSearchBeanVO queryInfo) {
		return wmsfinacrerealrepayinfoService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacrerealrepayinfowithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsFinaCreRealrepayInfoSearchBeanVO queryInfo) {
		return wmsfinacrerealrepayinfoService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaCreRealrepayInfoVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacrerealrepayinfoinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsFinaCreRealrepayInfo getInfoByPK(Integer wms_fina_cre_realrepay_info_id) {
		return wmsfinacrerealrepayinfoService.getInfoByPK(wms_fina_cre_realrepay_info_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacrerealrepayinfosave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsFinaCreRealrepayInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacrerealrepayinfoService.save(bean, user);
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
	@RequestMapping(value = "/loanfina/wmsfinacrerealrepayinfoupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsFinaCreRealrepayInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacrerealrepayinfoService.update(bean, user);
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
	 * Description :根据 wms_cre_credit_head_id 查询信息
	 * @param primary key 
	 * @return 
	 * @author baisong
	 */	
	@RequestMapping(value = "/loanfina/getinfobyhk.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getInfoByHK(Integer wms_cre_credit_head_id) {
		return wmsfinacrerealrepayinfoService.getInfoByHK(wms_cre_credit_head_id);
	}
	/**
	 * Description :根据 wms_cre_credit_head_id 应还款表  求和
	 * @param primary key 
	 * @return 
	 * @author baisong
	 */	
	@RequestMapping(value = "/loanfina/getSumval.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getSumval(Integer wms_cre_credit_head_id) {
		return wmsfinacrerealrepayinfoService.getSumval(wms_cre_credit_head_id);
	}	
	
	/**
	 * Description :通过贷款单据ID和期数查询相应的应还款信息
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	@RequestMapping(value = "/loanfina/wmsfinacrerealrepayInfoByNOw.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> wmsfinacrerealrepayInfoByNOw(WmsFinaCreRealrepayInfoSearchBeanVO queryInfo) {
		return wmsfinacrerealrepayinfoService.wmsfinacrerealrepayInfoByNOw(queryInfo);
	}
}