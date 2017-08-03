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

import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.service.IWmsCreCreditVisaApplService;
import com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO;
import com.zx.emanage.loanappro.vo.WmsCreCreditVisaApplSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditVisaAppl;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditVisaApplController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditVisaApplController.class);
	
	@Autowired
	private IWmsCreCreditVisaApplService wmscrecreditvisaapplService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanappro/wmscrecreditvisaapplwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCreditVisaApplSearchBeanVO queryInfo) {
		return wmscrecreditvisaapplService.getListWithoutPaging(queryInfo);
	}

    /**
     * 
     * @Title: getListWithPaging
     * @Description: TODO(查询面签历史)
     * @param queryInfo
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午3:39:22
     * history:
     * 1、2017年2月21日 baisong 创建方法
     */
	@RequestMapping(value = "/loanappro/wmscrecreditvisaapplwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCreditVisaApplSearchBeanVO queryInfo) {
		return wmscrecreditvisaapplService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCreditVisaApplVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanappro/wmscrecreditvisaapplinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCreditVisaAppl getInfoByPK(Integer wms_cre_credit_visa_appl_id) {
		return wmscrecreditvisaapplService.getInfoByPK(wms_cre_credit_visa_appl_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanappro/wmscrecreditvisaapplsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCreditVisaAppl bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecreditvisaapplService.save(bean, user);
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
	@RequestMapping(value = "/loanappro/wmscrecreditvisaapplupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCreditVisaAppl bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecreditvisaapplService.update(bean, user);
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
     * 
     * @Title: doSaveforHouse
     * @Description: TODO(终审面签保存-房贷)
     * @param request
     * @param bean
     * @param approveHouseWorkFlowVO
     * @param beanVo
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午2:21:45
     * history:
     * 1、2017年2月21日 baisong 创建方法
     */
    @RequestMapping(value = "/loanappro/wmscrevisaappforHousesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSaveforHouse(HttpServletRequest request, WmsCreCreditVisaAppl bean, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, WmsCreCreditHeadVO beanVo)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditvisaapplService.saveforhouse(bean, user, approveHouseWorkFlowVO, beanVo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "终审管理-房产终审-终审面签";
            SysTools.saveLog(request, msg); // record log method
        }

        return result;
    }
}