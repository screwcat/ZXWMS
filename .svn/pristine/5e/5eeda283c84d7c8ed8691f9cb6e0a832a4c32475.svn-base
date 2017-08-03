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

import com.zx.emanage.loanfina.service.IWmsFinaTerminationContractMortgageListService;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContractMortgageList;
import com.zx.emanage.loanfina.vo.WmsFinaTerminationContractMortgageListSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaTerminationContractMortgageListController {
	private static Logger log = LoggerFactory.getLogger(WmsFinaTerminationContractMortgageListController.class);
	
	@Autowired
	private IWmsFinaTerminationContractMortgageListService wmsfinaterminationcontractmortgagelistService;

	/**
	 *	根据条件查询抵押物详情
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractmortgagelistwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsFinaTerminationContractMortgageListSearchBeanVO queryInfo) {
		return wmsfinaterminationcontractmortgagelistService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractmortgagelistwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsFinaTerminationContractMortgageListSearchBeanVO queryInfo) {
		return wmsfinaterminationcontractmortgagelistService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaTerminationContractMortgageListVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractmortgagelistinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsFinaTerminationContractMortgageList getInfoByPK(Integer wms_fina_termination_contract_mortgage_list_id) {
		return wmsfinaterminationcontractmortgagelistService.getInfoByPK(wms_fina_termination_contract_mortgage_list_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractmortgagelistsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsFinaTerminationContractMortgageList bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinaterminationcontractmortgagelistService.save(bean, user);
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
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractmortgagelistupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsFinaTerminationContractMortgageList bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinaterminationcontractmortgagelistService.update(bean, user);
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