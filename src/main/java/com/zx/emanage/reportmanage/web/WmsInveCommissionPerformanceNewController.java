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

import com.zx.emanage.reportmanage.service.IWmsInveCommissionPerformanceNewService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformanceNew;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionPerformanceNewSearchBeanVO;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionPerformanceSearchBeanVO;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionTeamPerformanceSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionPerformanceNewController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionPerformanceNewController.class);
	
	@Autowired
	private IWmsInveCommissionPerformanceNewService wmsinvecommissionperformancenewService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancenewwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo) {
		return wmsinvecommissionperformancenewService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancenewwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo) {
		return wmsinvecommissionperformancenewService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionPerformanceNewVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancenewinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionPerformanceNew getInfoByPK(Integer wms_inve_commission_performance_new_id) {
		return wmsinvecommissionperformancenewService.getInfoByPK(wms_inve_commission_performance_new_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancenewsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionPerformanceNew bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionperformancenewService.save(bean, user);
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
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancenewupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionPerformanceNew bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionperformancenewService.update(bean, user);
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
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformanceupdatestatusnew.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdateStatus(HttpServletRequest request, WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionperformancenewService.updateStatus(queryInfo, user);
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
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformanceshowDetailsNew.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> showDetails(WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionperformancenewService.showDetails(queryInfo);
	}
	
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/reportmanage/getListTeamNew.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListTeam(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo) {
		return wmsinvecommissionperformancenewService.getListTeam(queryInfo);
	}
	/**
	 * Description :佣金发放查询-excel
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancewithoutpaginglistallNew.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPagingAll(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo) {
		return wmsinvecommissionperformancenewService.getListWithoutPagingAll(queryInfo);
	}
	/**
	 * Description :佣金发放查询
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@RequestMapping(value = "/reportmanage/wmsinvecommissionperformancewithpaginglistallNew.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPagingAll(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo) {
		return wmsinvecommissionperformancenewService.getListWithPagingAll(queryInfo);
	}
	
}