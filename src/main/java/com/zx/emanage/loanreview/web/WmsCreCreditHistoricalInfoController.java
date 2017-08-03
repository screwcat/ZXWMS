package com.zx.emanage.loanreview.web;


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

import com.zx.emanage.loanreview.service.IWmsCreCreditHistoricalInfoService;
import com.zx.emanage.loanreview.vo.WmsCreCreditHistoricalInfoSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditHistoricalInfo;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditHistoricalInfoController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditHistoricalInfoController.class);
	
	@Autowired
	private IWmsCreCreditHistoricalInfoService wmscrecredithistoricalinfoService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrecredithistoricalinfowithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCreditHistoricalInfoSearchBeanVO queryInfo) {
		return wmscrecredithistoricalinfoService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrecredithistoricalinfowithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public Map<String, Object> getListWithPaging(HttpServletRequest request, WmsCreCreditHistoricalInfoSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecredithistoricalinfoService.getListWithPaging(queryInfo, user);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCreditHistoricalInfoVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrecredithistoricalinfoinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCreditHistoricalInfo getInfoByPK(Integer wms_cre_credit_historical_info_id) {
		return wmscrecredithistoricalinfoService.getInfoByPK(wms_cre_credit_historical_info_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrecredithistoricalinfosave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCreditHistoricalInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecredithistoricalinfoService.save(bean, user);
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
	@RequestMapping(value = "/loanreview/wmscrecredithistoricalinfoupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCreditHistoricalInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecredithistoricalinfoService.update(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// record log	
		if("success".equals(result)){
            String msg = "历史单据信息修改";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}	
}