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

import com.zx.emanage.loanfina.service.IWmsFinaCreOverdueHistoryService;
import com.zx.emanage.util.gen.entity.WmsFinaCreOverdueHistory;
import com.zx.emanage.loanfina.vo.WmsFinaCreOverdueHistorySearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaCreOverdueHistoryController {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreOverdueHistoryController.class);
	
	@Autowired
	private IWmsFinaCreOverdueHistoryService wmsfinacreoverduehistoryService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacreoverduehistorywithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsFinaCreOverdueHistorySearchBeanVO queryInfo) {
		return wmsfinacreoverduehistoryService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacreoverduehistorywithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsFinaCreOverdueHistorySearchBeanVO queryInfo) {
		return wmsfinacreoverduehistoryService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaCreOverdueHistoryVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacreoverduehistoryinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsFinaCreOverdueHistory getInfoByPK(Integer wms_fina_cre_overdue_history_id) {
		return wmsfinacreoverduehistoryService.getInfoByPK(wms_fina_cre_overdue_history_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacreoverduehistorysave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsFinaCreOverdueHistory bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacreoverduehistoryService.save(bean, user);
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
	@RequestMapping(value = "/loanfina/wmsfinacreoverduehistoryupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsFinaCreOverdueHistory bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacreoverduehistoryService.update(bean, user);
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