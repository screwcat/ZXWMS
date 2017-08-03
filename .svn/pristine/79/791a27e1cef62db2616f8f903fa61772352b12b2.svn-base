package com.zx.emanage.loanfina.web;

import java.util.List;
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

import com.zx.emanage.loanfina.service.IWmsFinaCreRepaymentDetailsService;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentDetails;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaymentDetailsSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaCreRepaymentDetailsController {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreRepaymentDetailsController.class);
	
	@Autowired
	private IWmsFinaCreRepaymentDetailsService wmsfinacrerepaymentdetailsService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymentdetailswithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsFinaCreRepaymentDetailsSearchBeanVO queryInfo) {
		return wmsfinacrerepaymentdetailsService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymentdetailswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsFinaCreRepaymentDetailsSearchBeanVO queryInfo) {
		return wmsfinacrerepaymentdetailsService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaCreRepaymentDetailsVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymentdetailsinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsFinaCreRepaymentDetails getInfoByPK(Integer wms_fina_cre_repayment_details_id) {
		return wmsfinacrerepaymentdetailsService.getInfoByPK(wms_fina_cre_repayment_details_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymentdetailssave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsFinaCreRepaymentDetails bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacrerepaymentdetailsService.save(bean, user);
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
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymentdetailsupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsFinaCreRepaymentDetails bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacrerepaymentdetailsService.update(bean, user);
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
	 * Description :通过贷款信息表主键 查询相应的还款明细数据
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	@RequestMapping(value = "/loanfina/getListByEntity.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getListByEntity(WmsFinaCreRepaymentDetailsSearchBeanVO queryInfo) {
		return wmsfinacrerepaymentdetailsService.getListBySearchforDetails(queryInfo);
	}
}