package com.zx.emanage.cremanage.web;

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

import com.zx.emanage.cremanage.service.IWmsCreCreditReturnSearchService;
import com.zx.emanage.util.gen.entity.WmsCreCreditReturnSearch;
import com.zx.emanage.cremanage.vo.WmsCreCreditReturnSearchSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditReturnSearchController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditReturnSearchController.class);
	
	@Autowired
	private IWmsCreCreditReturnSearchService wmscrecreditreturnsearchService;

	/**
	 * @Title: getListWithoutPaging 
	 * @Description: 退件列表Excel导出
	 * @param queryInfo
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年6月24日 下午1:58:31
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditreturnsearchwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCreditReturnSearchSearchBeanVO queryInfo) {
		return wmscrecreditreturnsearchService.getListWithoutPaging(queryInfo);
	}

	/**
	 * @Title: getListWithPaging 
	 * @Description: 退件列表查询
	 * @param queryInfo
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年6月24日 下午1:55:57
	 */
	@RequestMapping(value = "/cremanage/wmscrecreditreturnsearchwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCreditReturnSearchSearchBeanVO queryInfo) {
		return wmscrecreditreturnsearchService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCreditReturnSearchVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/cremanage/wmscrecreditreturnsearchinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCreditReturnSearch getInfoByPK(Integer wms_cre_credit_return_search_id) {
		return wmscrecreditreturnsearchService.getInfoByPK(wms_cre_credit_return_search_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/cremanage/wmscrecreditreturnsearchsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCreditReturnSearch bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecreditreturnsearchService.save(bean, user);
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
	@RequestMapping(value = "/cremanage/wmscrecreditreturnsearchupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCreditReturnSearch bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecreditreturnsearchService.update(bean, user);
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