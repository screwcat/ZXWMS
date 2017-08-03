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

import com.zx.emanage.inve.service.IWmsInveCommissionPerformanceHisService;
import com.zx.emanage.util.gen.entity.PmPersonnelOtherinfo;
import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformanceHis;
import com.zx.emanage.util.gen.entity.WmsInveTransaAuth;
import com.zx.emanage.inve.vo.WmsInveCommissionPerformanceHisSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCommissionPerformanceHisController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionPerformanceHisController.class);
	
	@Autowired
	private IWmsInveCommissionPerformanceHisService wmsinvecommissionperformancehisService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissionperformancehiswithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionPerformanceHisSearchBeanVO queryInfo) {
		return wmsinvecommissionperformancehisService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecommissionperformancehiswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCommissionPerformanceHisSearchBeanVO queryInfo) {
		return wmsinvecommissionperformancehisService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCommissionPerformanceHisVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissionperformancehisinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCommissionPerformanceHis getInfoByPK(Integer wms_inve_commission_performance_his_id) {
		return wmsinvecommissionperformancehisService.getInfoByPK(wms_inve_commission_performance_his_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecommissionperformancehissave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCommissionPerformanceHis bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionperformancehisService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvecommissionperformancehisupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCommissionPerformanceHis bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecommissionperformancehisService.update(bean, user);
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
	 * Description :检查是否存在  存在说明已验证
	 * @param bean
	 * @return "exist"
	 * @author zhangyunfei
	 */	
    @RequestMapping(value = "/inve/pmpersonnelotherinfoCheck.do", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public List<PmPersonnelOtherinfo> findPmpersonnelOtherCountByPid(PmPersonnelOtherinfo bean) {
        return wmsinvecommissionperformancehisService.findPmpersonnelOtherCountByPid(bean);
    }
    
    /**
	 * Description :认证情况列表
	 * @param bean
	 * @return "Map"
	 * @author zhangyunfei
	 */	
	@RequestMapping(value = "/inve/getWmsInveTransaAuthListByPid.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getWmsInveTransaAuthListByPid(WmsInveTransaAuth queryInfo) {
		return wmsinvecommissionperformancehisService.getWmsInveTransaAuthListByPid(queryInfo);
	}
}