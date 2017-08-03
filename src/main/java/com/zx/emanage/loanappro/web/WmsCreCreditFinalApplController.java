package com.zx.emanage.loanappro.web;

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

import com.zx.emanage.loanappro.service.IWmsCreCreditFinalApplService;
import com.zx.emanage.loanappro.vo.WmsCreCreditFinalApplSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditFinalAppl;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditFinalApplController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditFinalApplController.class);
	
	@Autowired
	private IWmsCreCreditFinalApplService wmscrecreditfinalapplService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanappro/wmscrecreditfinalapplwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCreditFinalApplSearchBeanVO queryInfo) {
		return wmscrecreditfinalapplService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanappro/wmscrecreditfinalapplwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCreditFinalApplSearchBeanVO queryInfo) {
		return wmscrecreditfinalapplService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCreditFinalApplVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanappro/wmscrecreditfinalapplinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCreditFinalAppl getInfoByPK(Integer wms_cre_credit_final_appl_id) {
		return wmscrecreditfinalapplService.getInfoByPK(wms_cre_credit_final_appl_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanappro/wmscrecreditfinalapplsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCreditFinalAppl bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecreditfinalapplService.save(bean, user);
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
	@RequestMapping(value = "/loanappro/wmscrecreditfinalapplupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCreditFinalAppl bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecreditfinalapplService.update(bean, user);
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
    * @Description: 根据ID查询贷款单信息
    * @param queryInfo
    * @param request
    * @return    
    * @return Map<String,Object>    
    * @throws
    * @author baisong 
    * @date 2015年7月2日 下午5:19:02
    */
    @RequestMapping(value = "/cremanage/getWmsCusCreditFinalApplInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsCusCreditFinalApplInfo(HttpServletRequest request, Integer wms_cre_credit_head_id)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditfinalapplService.getWmsCusCreditFinalApplInfo(user, wms_cre_credit_head_id);
    }

    /**
    * @Description: 根据ID查询贷款单信息
    * @param queryInfo
    * @param request
    * @return    
    * @return Map<String,Object>    
    * @throws
    * @author baisong 
    * @date 2015年7月2日 下午5:19:02
    */
    @RequestMapping(value = "/cremanage/getWmsCusCreditFinalVisalInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsCusCreditFinalVisaInfo(HttpServletRequest request, Integer wms_cre_credit_head_id)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditfinalapplService.getWmsCusCreditFinalVisaInfo(user, wms_cre_credit_head_id);
    }
}