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

import com.zx.emanage.inve.service.IWmsInveJobService;
import com.zx.emanage.util.gen.entity.WmsInveJob;
import com.zx.emanage.inve.vo.WmsInveJobSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveJobController {
	private static Logger log = LoggerFactory.getLogger(WmsInveJobController.class);
	
	@Autowired
	private IWmsInveJobService wmsinvejobService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvejobwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveJobSearchBeanVO queryInfo) {
		return wmsinvejobService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvejobwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveJobSearchBeanVO queryInfo) {
		return wmsinvejobService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveJobVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvejobinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveJob getInfoByPK(Integer wms_inve_job_id) {
		return wmsinvejobService.getInfoByPK(wms_inve_job_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvejobsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveJob bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvejobService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvejobupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveJob bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvejobService.update(bean, user);
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