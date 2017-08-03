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

import com.zx.emanage.inve.service.IWmsInveOldCommBaseDataSpecialHisService;
import com.zx.emanage.inve.vo.WmsInveOldCommBaseDataSpecialHisSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveOldCommBaseDataSpecialHis;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveOldCommBaseDataSpecialHisController {
	private static Logger log = LoggerFactory.getLogger(WmsInveOldCommBaseDataSpecialHisController.class);
	
	@Autowired
	private IWmsInveOldCommBaseDataSpecialHisService wmsinveoldcommbasedataspecialhisService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinveoldcommbasedataspecialhiswithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveOldCommBaseDataSpecialHisSearchBeanVO queryInfo) {
		return wmsinveoldcommbasedataspecialhisService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinveoldcommbasedataspecialhiswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveOldCommBaseDataSpecialHisSearchBeanVO queryInfo) {
		return wmsinveoldcommbasedataspecialhisService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveOldCommBaseDataSpecialHisVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinveoldcommbasedataspecialhisinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveOldCommBaseDataSpecialHis getInfoByPK(Integer wms_inve_old_comm_base_data_special_his_id) {
		return wmsinveoldcommbasedataspecialhisService.getInfoByPK(wms_inve_old_comm_base_data_special_his_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinveoldcommbasedataspecialhissave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveOldCommBaseDataSpecialHis bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinveoldcommbasedataspecialhisService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinveoldcommbasedataspecialhisupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveOldCommBaseDataSpecialHis bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinveoldcommbasedataspecialhisService.update(bean, user);
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