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

import com.zx.emanage.loanappro.service.IWmsCreCreditGroupService;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.entity.WmsCreCreditGroup;
import com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO;
import com.zx.emanage.loanappro.vo.WmsCreCreditGroupSearchBeanVO;
import com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditGroupController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditGroupController.class);
	
	@Autowired
	private IWmsCreCreditGroupService wmscrecreditgroupService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanappro/wmscrecreditgroupwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCreditGroupSearchBeanVO queryInfo) {
		return wmscrecreditgroupService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanappro/wmscrecreditgroupwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCreditGroupSearchBeanVO queryInfo) {
		return wmscrecreditgroupService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCreditGroupVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanappro/wmscrecreditgroupinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCreditGroup getInfoByPK(Integer wms_cre_credit_group_id) {
		return wmscrecreditgroupService.getInfoByPK(wms_cre_credit_group_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanappro/wmscrecreditgroupsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCreditGroup bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecreditgroupService.save(bean, user);
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
	@RequestMapping(value = "/loanappro/wmscrecreditgroupupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCreditGroup bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecreditgroupService.update(bean, user);
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
     * @Title: combinedLoanInfoSave
     * @Description: TODO(组合贷组合保存)
     * @param queryInfo
     * @param request
     * @return 
     * @author: jiaodelong
     * @time:2016年12月28日 上午10:43:22
     * history:
     * 1、2016年12月28日 jiaodelong 创建方法
     */
    @RequestMapping(value = "/loanappro/combinedLoanInfoSave.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public String combinedLoanInfoSave(WmsCreCreditGroup queryInfo,HttpServletRequest request)
    {
        String result = "";
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmscrecreditgroupService.combinedLoanInfoSave(queryInfo, user);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }
    
    /**
     * 
     * @Title: combinedLoanInfoSaveForCaiFen
     * @Description: TODO(组合贷拆分保存)
     * @param queryInfo
     * @param request
     * @return 
     * @author: jiaodelong
     * @time:2016年12月28日 下午6:24:34
     * history:
     * 1、2016年12月28日 jiaodelong 创建方法
     */
    @RequestMapping(value = "/loanappro/combinedLoanInfoSaveForCaiFen.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public String combinedLoanInfoSaveForCaiFen(WmsCreCreditHeadVO bean,HttpServletRequest request)
    {
        String result = "";
        // 获取当前登录人
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmscrecreditgroupService.combinedLoanInfoSaveForCaiFen(bean, user);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }
    
    
    @RequestMapping(value = "/loanappro/madeBillCode.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public String madeBillCode(HttpServletRequest request)
    {
        return CodeNoUtil.getHouseCreCode();
    }
}