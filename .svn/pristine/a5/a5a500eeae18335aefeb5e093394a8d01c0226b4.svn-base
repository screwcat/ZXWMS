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

import com.zx.emanage.loanfina.service.IWmsFinaTerminationContractAttService;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContractAtt;
import com.zx.emanage.loanfina.vo.WmsFinaTerminationContractAttSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaTerminationContractAttController {
	private static Logger log = LoggerFactory.getLogger(WmsFinaTerminationContractAttController.class);
	
	@Autowired
	private IWmsFinaTerminationContractAttService wmsfinaterminationcontractattService;

	/**
	 * 终止合同确认 获取上传附件信息
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractattwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsFinaTerminationContractAttSearchBeanVO queryInfo) {
		return wmsfinaterminationcontractattService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractattwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsFinaTerminationContractAttSearchBeanVO queryInfo) {
		return wmsfinaterminationcontractattService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaTerminationContractAttVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractattinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsFinaTerminationContractAtt getInfoByPK(Integer wms_fina_termination_contract_att_id) {
		return wmsfinaterminationcontractattService.getInfoByPK(wms_fina_termination_contract_att_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractattsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsFinaTerminationContractAtt bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinaterminationcontractattService.save(bean, user);
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
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractattupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsFinaTerminationContractAtt bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinaterminationcontractattService.update(bean, user);
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