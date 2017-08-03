package com.zx.emanage.inve.web;

import java.util.HashMap;
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

import com.zx.emanage.inve.service.IWmsInveTransaBackApplyService;
import com.zx.emanage.inve.service.IWmsInveTransaService;
import com.zx.emanage.util.gen.entity.WmsInveTransaBackApply;
import com.zx.emanage.inve.vo.WmsInveTransaBackApplySearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveTransaBackApplyController {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaBackApplyController.class);
	
	@Autowired
	private IWmsInveTransaBackApplyService wmsinvetransabackapplyService;
	
	@Autowired
	private IWmsInveTransaService wmsinvetransaService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvetransabackapplywithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveTransaBackApplySearchBeanVO queryInfo) {
		return wmsinvetransabackapplyService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvetransabackapplywithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveTransaBackApplySearchBeanVO queryInfo) {
		return wmsinvetransabackapplyService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveTransaBackApplyVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvetransabackapplyinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveTransaBackApply getInfoByPK(Integer wms_inve_transa_back_apply_id) {
		return wmsinvetransabackapplyService.getInfoByPK(wms_inve_transa_back_apply_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvetransabackapplysave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveTransaBackApply bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvetransabackapplyService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvetransabackapplyupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveTransaBackApply bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvetransabackapplyService.update(bean, user);
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
     * 退单申请页面初始化
     * 
     * @param queryInfo
     * @return Map
     * @author wangyihan
     */
    @RequestMapping(value = "/inve/singleApplicationDisp.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> singleApplicationDisp(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_inve_transa_id", request.getParameter("wms_inve_transa_id"));//上单信息表id
        paramMap.put("wms_inve_transa_prod_id", request.getParameter("wms_inve_transa_prod_id"));//上单产品表id
        
        return wmsinvetransabackapplyService.singleApplicationDisp(paramMap, user);
    }
	
    /**
     * 退单申请页面保存
     * 
     * @param queryInfo
     * @return Map
     * @author wangyihan
     */
    @RequestMapping(value = "/inve/singleApplicationSave.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String singleConfirmationSave(WmsInveTransaBackApply bean, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransabackapplyService.singleApplicationSave(bean, request.getParameter("taskId").toString(), user);
    }
    
	/**
     * 获取退单确认页面列表数据
     * 
     * @param queryInfo
     * @return Map
     * @author wangyihan
     */
    @RequestMapping(value = "/inve/searchSingleConfirmationList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> searchSingleConfirmationList(WmsInveTransaSearchBeanVO queryInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransabackapplyService.searchSingleConfirmationList(queryInfo, user);
    }
    
    /**
     * 退单确认页面列表数据导出
     * 
     * @param queryInfo
     * @return Map
     * @author wangyihan
     */
    @RequestMapping(value = "/inve/searchSingleConfirmationListForExport.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> searchSingleConfirmationListForExport(WmsInveTransaSearchBeanVO queryInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransabackapplyService.searchSingleConfirmationListForExport(queryInfo, user);
    }
    
    /**
     * 退单确认页面获取退单详情数据(与退单申请为同一页面但数据来源不同)
     * 
     * @param queryInfo
     * @return Map
     * @author wangyihan
     */
    @RequestMapping(value = "/inve/getBackApplyDataForConfirm.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getBackApplyDataForConfirm(WmsInveTransaBackApplySearchBeanVO queryInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransabackapplyService.getBackApplyDataForConfirm(queryInfo, user);
    }
	
    /**
     * 退单确认页面撤销退单
     * 
     * @param queryInfo
     * @return String
     * @author wangyihan
     */
    @RequestMapping(value = "/inve/revocationTransaBackApply.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String revocationTransaBackApply(WmsInveTransaBackApplySearchBeanVO queryInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransabackapplyService.revocationTransaBackApply(queryInfo, user);
    }
    
    /**
     * 退单确认页面审核
     * 
     * @param queryInfo
     * @return String
     * @author wangyihan
     */
    @RequestMapping(value = "/inve/approvalTransaApply.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String approvalTransaApply(WmsInveTransaBackApplySearchBeanVO queryInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransabackapplyService.approvalTransaApply(queryInfo, user);
    }
    
    /**
     * 获取退单退回页面列表数据
     * 
     * @param queryInfo
     * @return Map
     * @author wangyihan
     */
    @RequestMapping(value = "/inve/searchTransaBackReturnList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> searchTransaBackReturnList(WmsInveTransaSearchBeanVO queryInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransabackapplyService.searchTransaBackReturnList(queryInfo, user);
    }
    
    /**
     * 退单退回页面列表数据导出
     * 
     * @param queryInfo
     * @return Map
     * @author wangyihan
     */
    @RequestMapping(value = "/inve/searchTransaBackReturnListForExport.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> searchTransaBackReturnListForExport(WmsInveTransaSearchBeanVO queryInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        return wmsinvetransabackapplyService.searchTransaBackReturnListForExport(queryInfo, user);
    }
    
    
}