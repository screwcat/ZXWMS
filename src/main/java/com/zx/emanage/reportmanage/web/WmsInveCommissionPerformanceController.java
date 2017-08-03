package com.zx.emanage.reportmanage.web;

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

import com.zx.emanage.reportmanage.service.IWmsInveCommissionPerformanceService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformance;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionPerformanceSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionPerformanceController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionPerformanceController.class);
	
	@Autowired
	private IWmsInveCommissionPerformanceService wmsinvecommissionperformanceService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancewithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionperformanceService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancewithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionperformanceService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionPerformanceVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformanceinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionPerformance getInfoByPK(Integer wms_inve_commission_performance_id) {
		return wmsinvecommissionperformanceService.getInfoByPK(wms_inve_commission_performance_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancesave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionPerformance bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionperformanceService.save(bean, user);
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
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformanceupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionPerformance bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionperformanceService.update(bean, user);
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
	 * Description :update method--修改单据 添加发放日期
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformanceupdatestatus.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdateStatus(HttpServletRequest request, WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionperformanceService.updateStatus(queryInfo, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}			
		// record log	
		if("success".equals(result)){
			String msg = "报表管理-佣金发放确认";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}	
	
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformanceshowDetails.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> showDetails(WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionperformanceService.showDetails(queryInfo);
	}
	/**
	 * Description :佣金发放查询-excel
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancewithoutpaginglistall.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPagingAll(WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionperformanceService.getListWithoutPagingAll(queryInfo);
	}

	/**
	 * Description :佣金发放查询
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancewithpaginglistall.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPagingAll(WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionperformanceService.getListWithPagingAll(queryInfo);
	}
	
}