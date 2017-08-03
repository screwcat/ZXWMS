package com.zx.emanage.loanpost.web;

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

import com.zx.emanage.loanpost.service.IWmsPostRemindHistoryService;
import com.zx.emanage.util.gen.entity.WmsPostRemindHistory;
import com.zx.emanage.loanpost.vo.WmsPostRemindHistorySearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsPostRemindHistoryController {
	private static Logger log = LoggerFactory.getLogger(WmsPostRemindHistoryController.class);
	
	@Autowired
	private IWmsPostRemindHistoryService wmspostremindhistoryService;

	/**
	 * 
	 * @Title: getListWithPaging 
	 * @Description: (电话提醒导出excel) 
	 * @param session
	 * @param queryInfo
	 * @return Map<String,Object>    返回类型
	 * @author lvtu
	 */
	@RequestMapping(value = "/loanpost/wmspostremindhistorywithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(HttpSession session, WmsPostRemindHistorySearchBeanVO queryInfo) {
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmspostremindhistoryService.getListWithoutPaging(queryInfo, user);
	}

	/**
	 * 
	 * @Title: getListWithPaging 
	 * @Description: (电话提醒列表) 
	 * @param session
	 * @param queryInfo
	 * @return Map<String,Object>    返回类型
	 * @author lvtu
	 */
	@RequestMapping(value = "/loanpost/wmspostremindhistorywithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(HttpSession session, WmsPostRemindHistorySearchBeanVO queryInfo) {
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmspostremindhistoryService.getListWithPaging(queryInfo, user);
	}
	
	/**
	 * 
	 * @Title: getListWithPaging 
	 * @Description: (电话提醒详细信息列表) 
	 * @param session
	 * @param queryInfo
	 * @return Map<String,Object>    返回类型
	 * @author lvtu
	 */
	@RequestMapping(value = "/loanpost/wmspostremindhistoryinfowithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getInfoListWithPaging(long wms_cre_credit_head_id, WmsPostRemindHistorySearchBeanVO queryInfo) {
		return wmspostremindhistoryService.getInfoListWithPaging(queryInfo,wms_cre_credit_head_id);
	}
	
	/**
	 * 
	 * @Title: updatePhoneRemindHistory 
	 * @Description: (电话提醒是否成功) 
	 * @param session
	 * @param repay_no
	 * @param wms_cre_credit_head_id
	 * @param reminder_result
	 * @return    
	 * @return String    返回类型
	 * @throws
	 * @author lvtu
	 */
	@RequestMapping(value = "/loanpost/wmspostupdateremindhistory.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updatePhoneRemindHistory(HttpSession session, int repay_no, int wms_cre_credit_head_id, String reminder_result) {
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmspostremindhistoryService.updatePhoneRemindHistory(user, repay_no, wms_cre_credit_head_id, reminder_result);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsPostRemindHistoryVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanpost/wmspostremindhistoryinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsPostRemindHistory getInfoByPK(Integer wms_post_remind_history_id) {
		return wmspostremindhistoryService.getInfoByPK(wms_post_remind_history_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanpost/wmspostremindhistorysave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsPostRemindHistory bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmspostremindhistoryService.save(bean, user);
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
	@RequestMapping(value = "/loanpost/wmspostremindhistoryupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsPostRemindHistory bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmspostremindhistoryService.update(bean, user);
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
	 * Description :根据 wms_cre_credit_head_id 查询历史信息
	 * @param primary key 
	 * @return WmsPostRemindHistoryVO
	 * @author baisong
	 */	
	@RequestMapping(value = "/loanpost/wmspostremindhistoryinfobyhk.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getInfoByHK(Integer wms_cre_credit_head_id) {
		return wmspostremindhistoryService.getInfoByHK(wms_cre_credit_head_id);
	}	
}