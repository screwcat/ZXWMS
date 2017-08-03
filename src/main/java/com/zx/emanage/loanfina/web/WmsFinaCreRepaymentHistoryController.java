package com.zx.emanage.loanfina.web;

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

import com.zx.emanage.loanfina.service.IWmsFinaCreRepaymentHistoryService;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaymentHistorySearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaDrawBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsFinaCreRealrepayInfo;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentHistory;
import com.zx.emanage.util.gen.entity.WmsPostDunningCommission;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaCreRepaymentHistoryController {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreRepaymentHistoryController.class);
	
	@Autowired
	private IWmsFinaCreRepaymentHistoryService wmsfinacrerepaymenthistoryService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymenthistorywithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
		return wmsfinacrerepaymenthistoryService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymenthistorywithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
		return wmsfinacrerepaymenthistoryService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaCreRepaymentHistoryVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymenthistoryinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsFinaCreRepaymentHistory getInfoByPK(Integer wms_fina_cre_repayment_history_id) {
		return wmsfinacrerepaymenthistoryService.getInfoByPK(wms_fina_cre_repayment_history_id);
	}	

	/**
	 * Description :保存每次存款历史信息
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author hancd
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymenthistorysave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, String detailIds, String beanJSON, WmsFinaCreRepaymentHistory bean,WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacrerepaymenthistoryService.save(detailIds, beanJSON, bean, user,queryInfo);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if("success".equals(result)){
			String msg = "财务管理-还款管理-正常还款确认";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}
	/**
	 * Description :保存每次存款历史信息--逾期还款确认
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymenthistorysaveOverdue.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSaveOverdue(HttpServletRequest request, WmsFinaCreRepaymentHistory bean,WmsFinaCreRepaymentHistorySearchBeanVO queryInfo,WmsPostDunningCommission wCommission) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacrerepaymenthistoryService.doSaveOverdue(bean, user,queryInfo,wCommission);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if("success".equals(result)){
			String msg = "财务管理-还款管理-逾期还款确认";
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
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymenthistoryupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsFinaCreRepaymentHistory bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsfinacrerepaymenthistoryService.update(bean, user);
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
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymenthistoryinfobyhk.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getInfoByHK(Integer wms_cre_credit_head_id) {
		return wmsfinacrerepaymenthistoryService.getInfoByHK(wms_cre_credit_head_id);
	}
	
	/**
	 * Description :把本次实际还款额  分配给本期应还应还本金和应还利息和上期调整额
	 * @param queryInfo
     * @return record list
	 * @author hancd
     */
	@RequestMapping(value = "/loanfina/getFPPrive.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public WmsFinaCreRealrepayInfo getFPPrive(WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
		return wmsfinacrerepaymenthistoryService.getFPPrive(queryInfo);
	}	
	/***
	 * 根据wms_cre_credit_head_id查询多条逾期历史
	 * @param wms_cre_credit_head_id
	 * @return list
	 * baisong
	 */
	@RequestMapping(value = "/loanfina/wmsfinacrerepaymenthistorysearchHistory.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> searchHistory(WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
		return wmsfinacrerepaymenthistoryService.searchHistory(queryInfo);
	}
	/**
	 * Description :根据 wms_cre_credit_head_id以及期数  查询对应期还款历史
	 * @param primary key 
	 * @return Map
	 * @author hancd
	 */	
	@RequestMapping(value = "/loanfina/getInfoByRepaymentorRepayNo.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getInfoByRepaymentorRepayNo(WmsFinaCreRepaymentHistorySearchBeanVO queryInfo) {
		return wmsfinacrerepaymenthistoryService.getInfoByRepaymentorRepayNo(queryInfo);
	}
	/**
	 * Description :逾期还款计算提成
	 * @param primary key 
	 * @return Map
	 * @author baisong 
	 */	
	@RequestMapping(value = "/loanfina/getDraw.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getDraw(WmsFinaDrawBeanVO queryInfo) {
		return wmsfinacrerepaymenthistoryService.getDraw(queryInfo);
	}
}