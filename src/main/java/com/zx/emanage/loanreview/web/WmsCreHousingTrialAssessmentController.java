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

import com.zx.emanage.loanreview.service.IWmsCreHousingTrialAssessmentService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreHousingTrialAssessment;
import com.zx.emanage.loanreview.vo.WmsCreHousingTrialAssessmentSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreHousingTrialAssessmentController {
	private static Logger log = LoggerFactory.getLogger(WmsCreHousingTrialAssessmentController.class);
	
	@Autowired
	private IWmsCreHousingTrialAssessmentService wmscrehousingtrialassessmentService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrehousingtrialassessmentwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreHousingTrialAssessmentSearchBeanVO queryInfo) {
		return wmscrehousingtrialassessmentService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrehousingtrialassessmentwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreHousingTrialAssessmentSearchBeanVO queryInfo) {
		return wmscrehousingtrialassessmentService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreHousingTrialAssessmentVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrehousingtrialassessmentinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreHousingTrialAssessment getInfoByPK(Integer wms_cre_housing_trial_assessment_id) {
		return wmscrehousingtrialassessmentService.getInfoByPK(wms_cre_housing_trial_assessment_id);
	}	

	/**
	 * Description :房产初评保存
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrehousingtrialassessmentsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreHousingTrialAssessmentSearchBeanVO bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrehousingtrialassessmentService.save(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		// record log	
		if("success".equals(result)){
			String msg = "审核管理>房产初评>保存";
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
	@RequestMapping(value = "/loanreview/wmscrehousingtrialassessmentupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreHousingTrialAssessment bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrehousingtrialassessmentService.update(bean, user);
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
	 * 房产初审评估单初始化
	 */
	/**
     * Description :update method
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */ 
    @RequestMapping(value = "/loanreview/wmscrehouseingtrialassessmentdisp.do", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> wmsCreHouseIngtrialassessmentDisp(HttpServletRequest request, WmsCreHousingTrialAssessmentSearchBeanVO bean) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrehousingtrialassessmentService.wmsCreHouseIngtrialassessmentDisp(bean, user);
    } 
    /**
	 * 房产初审评估单初始化--查看方法
	 */
	/**
     * Description :update method
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator--baisong
     */ 
    @RequestMapping(value = "/loanreview/getwmsCreHouseIngtrialassessmentDisp.do", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getwmsCreHouseIngtrialassessmentDisp(WmsCreHousingTrialAssessmentSearchBeanVO bean) {
        return wmscrehousingtrialassessmentService.getwmsCreHouseIngtrialassessmentDisp(bean);
    } 
	
}