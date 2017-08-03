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

import com.zx.emanage.loanfina.service.IWmsFinaCreRepaymentDetailsAttService;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentDetailsAtt;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaymentDetailsAttSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaCreRepaymentDetailsAttController {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreRepaymentDetailsAttController.class);
	
	@Autowired
	private IWmsFinaCreRepaymentDetailsAttService wmsfinacrerepaymentdetailsattService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymentdetailsattwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsFinaCreRepaymentDetailsAttSearchBeanVO queryInfo) {
		return wmsfinacrerepaymentdetailsattService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymentdetailsattwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsFinaCreRepaymentDetailsAttSearchBeanVO queryInfo) {
		return wmsfinacrerepaymentdetailsattService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaCreRepaymentDetailsAttVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymentdetailsattinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsFinaCreRepaymentDetailsAtt getInfoByPK(Integer wms_fina_cre_repayment_details_att_id) {
		return wmsfinacrerepaymentdetailsattService.getInfoByPK(wms_fina_cre_repayment_details_att_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymentdetailsattsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsFinaCreRepaymentDetailsAtt bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacrerepaymentdetailsattService.save(bean, user);
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
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymentdetailsattupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsFinaCreRepaymentDetailsAtt bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacrerepaymentdetailsattService.update(bean, user);
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