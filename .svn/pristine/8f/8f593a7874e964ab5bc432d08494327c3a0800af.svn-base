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

import com.zx.emanage.loanreview.service.IWmsCreCarpHouseAssessmentService;
import com.zx.emanage.util.gen.entity.WmsCreCarpHouseAssessment;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.emanage.loanreview.vo.WmsCreCarpHouseAssessmentSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCarpHouseAssessmentController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpHouseAssessmentController.class);
	
	@Autowired
	private IWmsCreCarpHouseAssessmentService wmscrecarphouseassessmentService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrecarphouseassessmentwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCarpHouseAssessmentSearchBeanVO queryInfo) {
		return wmscrecarphouseassessmentService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanreview/wmscrecarphouseassessmentwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCarpHouseAssessmentSearchBeanVO queryInfo) {
		return wmscrecarphouseassessmentService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpHouseAssessmentVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrecarphouseassessmentinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCarpHouseAssessment getInfoByPK(Integer wms_cre_carp_house_assessment_id) {
		return wmscrecarphouseassessmentService.getInfoByPK(wms_cre_carp_house_assessment_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanreview/wmscrecarphouseassessmentsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCarpHouseAssessment bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarphouseassessmentService.save(bean, user);
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
	@RequestMapping(value = "/loanreview/wmscrecarphouseassessmentupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCarpHouseAssessment bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarphouseassessmentService.update(bean, user);
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
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingAssessmentVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrecarassessmentinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCarpHouseAssessment getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrecarphouseassessmentService.getInfoByFK(wms_cre_credit_head_id);
    }
}