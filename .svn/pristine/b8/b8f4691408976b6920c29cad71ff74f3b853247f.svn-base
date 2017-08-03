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

import com.zx.emanage.loanfina.service.IWmsFinaTerminationContractService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContract;
import com.zx.emanage.loanfina.vo.WmsFinaTerminationContractSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaTerminationContractController {
	private static Logger log = LoggerFactory.getLogger(WmsFinaTerminationContractController.class);
	
	@Autowired
	private IWmsFinaTerminationContractService wmsfinaterminationcontractService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsFinaTerminationContractSearchBeanVO queryInfo) {
		return wmsfinaterminationcontractService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsFinaTerminationContractSearchBeanVO queryInfo) {
		return wmsfinaterminationcontractService.getListWithPaging(queryInfo);
	}
	
	/**
	 * 终止合同确认
	 * @param primary key 
	 * @return WmsFinaTerminationContractVO
	 * @author baisong
	 */	
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsFinaTerminationContract getInfoByPK(Integer wms_fina_termination_contract_id) {
		return wmsfinaterminationcontractService.getInfoByPK(wms_fina_termination_contract_id);
	}	

	/**
	 * 终止合同审批保存--附件 --抵押物详情
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsFinaTerminationContract bean,WmsFinaTerminationContractSearchBeanVO beanvo) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinaterminationcontractService.save(bean, user,beanvo);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		
		// record log	
		if("success".equals(result)){
			String msg = "财务管理--终止合同审批";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}

	/**
	 * Description :update method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinaterminationcontractupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsFinaTerminationContract bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinaterminationcontractService.update(bean, user);
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