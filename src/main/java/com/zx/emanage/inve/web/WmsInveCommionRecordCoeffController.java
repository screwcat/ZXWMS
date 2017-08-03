package com.zx.emanage.inve.web;

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

import com.zx.emanage.inve.service.IWmsInveCommionRecordCoeffService;
import com.zx.emanage.util.gen.entity.WmsInveCommionRecordCoeff;
import com.zx.emanage.inve.vo.WmsInveCommionRecordCoeffSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommionRecordCoeffController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommionRecordCoeffController.class);
	
	@Autowired
	private IWmsInveCommionRecordCoeffService wmsinvecommionrecordcoeffService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommionrecordcoeffwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommionRecordCoeffSearchBeanVO queryInfo) {
		return wmsinvecommionrecordcoeffService.getListWithoutPaging(queryInfo);
	}

	/**
	 * 根据代办事项中的月份查找佣金提成系数修正信息
	 * @return
	 */
	@RequestMapping(value = "/inve/getmonthcommionlistwithoutpaing.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getMonthCommionListWithoutPaging(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvecommionrecordcoeffService.getMonthCommionListWithoutPaging(user);
	}
	
	/**
	 * 根据代办事项中的月份查找提成系数审核清空信息
     * @return Map
     * @author zhangyunfei
     */
	@RequestMapping(value = "/inve/getCommionListWithoutPagingByMonth.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCommionListWithoutPagingByMonth(HttpServletRequest request,WmsInveCommionRecordCoeff wmsInveCommionRecordCoeff){
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		return wmsinvecommionrecordcoeffService.getCommionListWithoutPagingByMonth(user,wmsInveCommionRecordCoeff);
	}
	
	@RequestMapping(value = "/inve/savemonthcommionlist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String saveMonthCommionList(HttpServletRequest request,String params){
		String res = "success";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		res = wmsinvecommionrecordcoeffService.saveMonthCommionList(user, params);
		return res;
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommionrecordcoeffwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommionRecordCoeffSearchBeanVO queryInfo) {
		return wmsinvecommionrecordcoeffService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommionRecordCoeffVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommionrecordcoeffinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommionRecordCoeff getInfoByPK(Integer wms_inve_commion_record_coeff_id) {
		return wmsinvecommionrecordcoeffService.getInfoByPK(wms_inve_commion_record_coeff_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommionrecordcoeffsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommionRecordCoeff bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommionrecordcoeffService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvecommionrecordcoeffupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommionRecordCoeff bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommionrecordcoeffService.update(bean, user);
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
}