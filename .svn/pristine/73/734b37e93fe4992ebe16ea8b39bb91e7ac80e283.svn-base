package com.zx.emanage.loanfina.web;

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

import com.zx.emanage.loanfina.service.IWmsFinaCreMortgageListService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsFinaCreMortgageList;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContractMortgageList;
import com.zx.emanage.loanfina.vo.WmsFinaCreMortgageListSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaCreMortgageListController {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreMortgageListController.class);
	
	@Autowired
	private IWmsFinaCreMortgageListService wmsfinacremortgagelistService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacremortgagelistwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsFinaCreMortgageListSearchBeanVO queryInfo) {
		return wmsfinacremortgagelistService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacremortgagelistwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsFinaCreMortgageListSearchBeanVO queryInfo) {
		return wmsfinacremortgagelistService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaCreMortgageListVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacremortgagelistinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsFinaCreMortgageList getInfoByPK(Integer wms_fina_cre_mortgage_list_id) {
		return wmsfinacremortgagelistService.getInfoByPK(wms_fina_cre_mortgage_list_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacremortgagelistsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsFinaCreMortgageList bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacremortgagelistService.save(bean, user);
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
	 * @author baisong
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacremortgagelistupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsFinaCreMortgageList bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacremortgagelistService.update(bean, user);
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
	 * Description :根据提供的查询条件和必要条件查询符合条件的抵押物信息
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	@RequestMapping(value = "/loanfina/getwmsfinacremortgagelistbySelect.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getwmsfinacremortgagelistbySelect(WmsFinaCreMortgageListSearchBeanVO queryInfo) {
		return wmsfinacremortgagelistService.getwmsfinacremortgagelistbySelect(queryInfo);
	}
	/**
	 * Description :根据对应条件来获取抵押物清单
	 * @param bean 
	 * @return Map<String,Object>
	 * @author baisong
	 */
	@RequestMapping(value = "/loanfina/WmsFinaCreMortgageListgetListByEntity.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getListByEntity(HttpServletRequest request, WmsFinaCreMortgageListSearchBeanVO bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		Map<String,Object> map =new HashMap<>();
		try {
			map = wmsfinacremortgagelistService.getListByEntity(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return map;
	}
	
	/**
	 * Description :正常还款确认 逾期还款确认中抵押物记录保存或更改
	 * @param bean 
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */
	@RequestMapping(value = "/loanfina/wmsfinacremortgagelistsaveUpdateMortgage.do", method = {RequestMethod.POST})
	@ResponseBody
	public String saveUpdateMortgage(HttpServletRequest request, WmsFinaCreMortgageList bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacremortgagelistService.saveUpdateMortgage(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
					
		// record log	
		if("success".equals(result)){
			String msg = "正常还款确认或者逾期还款确认--抵押物详情保存";
			SysTools.saveLog(request, msg);
		}
		return result;
	}
}