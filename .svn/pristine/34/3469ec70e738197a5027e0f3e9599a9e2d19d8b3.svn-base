package com.zx.emanage.inve.web;

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

import com.zx.emanage.inve.service.IWmsInveCommionRecordService;
import com.zx.emanage.util.gen.entity.WmsInveCommionRecord;
import com.zx.emanage.inve.vo.WmsInveCommionRecordSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommionRecordController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommionRecordController.class);
	
	@Autowired
	private IWmsInveCommionRecordService wmsinvecommionrecordService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommionrecordwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommionRecordSearchBeanVO queryInfo) {
		return wmsinvecommionrecordService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommionrecordwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommionRecordSearchBeanVO queryInfo) {
		return wmsinvecommionrecordService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommionRecordVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommionrecordinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommionRecord getInfoByPK(Integer wms_inve_commion_record_id) {
		return wmsinvecommionrecordService.getInfoByPK(wms_inve_commion_record_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommionrecordsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommionRecord bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommionrecordService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvecommionrecordupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommionRecord bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommionrecordService.update(bean, user);
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
	 * Description :调整金额
	 * @param WmsInveCommionRecord
	 * @return "success" or "error" or user defined
	 * @author zhangyunfei
	 */	
	@RequestMapping(value = "/inve/wmsinvecommionrecordupdateAmount.do", method = {RequestMethod.POST})
	@ResponseBody
	public String updateWmsInveCommionRecordAmount(HttpServletRequest request, WmsInveCommionRecord bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommionrecordService.updateWmsInveCommionRecordAmount(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		return result;
	}	
	
	
	/**
	 * Description :条件查询实发金额总数
	 * @param bean
	 * @return 实发金额总数
	 * @author zhangyunfei
	 */	
	@RequestMapping(value = "/inve/wmsinvepruductcategorydoQySumMoney.do", method = {RequestMethod.POST})
	@ResponseBody
	public String findwmsinvepruductcategorySumMoney(WmsInveCommionRecordSearchBeanVO queryInfo) {
		String result = "";
		try {
			result = wmsinvecommionrecordService.findwmsinvepruductcategorySumMoney(queryInfo);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		return result;
	}
}