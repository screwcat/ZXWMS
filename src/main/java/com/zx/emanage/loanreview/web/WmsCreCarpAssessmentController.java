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

import com.zx.emanage.loanreview.service.IWmsCreCarpAssessmentService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCarpAssessment;
import com.zx.emanage.loanreview.vo.WmsCreCarpAssessmentSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCarpAssessmentController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpAssessmentController.class);
	
	@Autowired
	private IWmsCreCarpAssessmentService wmscrecarpassessmentService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/util/wmscrecarpassessmentwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCarpAssessmentSearchBeanVO queryInfo) {
		return wmscrecarpassessmentService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/util/wmscrecarpassessmentwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCarpAssessmentSearchBeanVO queryInfo) {
		return wmscrecarpassessmentService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpAssessmentVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/util/wmscrecarpassessmentinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCarpAssessment getInfoByPK(Integer wms_cre_carp_assessment_id) {
		return wmscrecarpassessmentService.getInfoByPK(wms_cre_carp_assessment_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/util/wmscrecarpassessmentsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCarpAssessment bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarpassessmentService.save(bean, user);
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
	@RequestMapping(value = "/util/wmscrecarpassessmentupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCarpAssessment bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarpassessmentService.update(bean, user);
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
	 * Description :add method--评估单保存更新
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	@RequestMapping(value = "/util/wmscrecarpassessmentsaveupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSaveUpdate(HttpServletRequest request, WmsCreCarpAssessment bean,WmsCreCarpAssessmentSearchBeanVO beanvo) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarpassessmentService.saveUpdate(bean, user, beanvo);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// record log	
		if("success".equals(result)){
			String msg = "车贷--评估单保存更新";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}
	/**
	 * Description :get single-table information by primary key --获取数据评估单
	 * @param primary key 
	 * @return WmsCreCarpAssessmentVO
	 * @author baisong
	 */	
	@RequestMapping(value = "/util/wmscrecarpassessmentinfobyhk.do", method = {RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getInfoByHK(Integer wms_cre_credit_head_id) {
		return wmscrecarpassessmentService.getInfoByHK(wms_cre_credit_head_id);
	}
}