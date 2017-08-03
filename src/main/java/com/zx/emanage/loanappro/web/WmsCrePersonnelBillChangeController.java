package com.zx.emanage.loanappro.web;

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

import com.zx.emanage.loanappro.service.IWmsCrePersonnelBillChangeService;
import com.zx.emanage.util.gen.entity.WmsCrePersonnelBillChange;
import com.zx.emanage.loanappro.vo.WmsCrePersonnelBillChangeSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCrePersonnelBillChangeController {
	private static Logger log = LoggerFactory.getLogger(WmsCrePersonnelBillChangeController.class);
	
	@Autowired
	private IWmsCrePersonnelBillChangeService wmscrepersonnelbillchangeService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanappro/wmscrepersonnelbillchangewithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCrePersonnelBillChangeSearchBeanVO queryInfo) {
		return wmscrepersonnelbillchangeService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/loanappro/wmscrepersonnelbillchangewithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCrePersonnelBillChangeSearchBeanVO queryInfo) {
		return wmscrepersonnelbillchangeService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCrePersonnelBillChangeVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanappro/wmscrepersonnelbillchangeinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCrePersonnelBillChange getInfoByPK(Integer wms_cre_personnel_bill_change_id) {
		return wmscrepersonnelbillchangeService.getInfoByPK(wms_cre_personnel_bill_change_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/loanappro/wmscrepersonnelbillchangesave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCrePersonnelBillChange bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrepersonnelbillchangeService.save(bean, user);
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
	@RequestMapping(value = "/loanappro/wmscrepersonnelbillchangeupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCrePersonnelBillChange bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrepersonnelbillchangeService.update(bean, user);
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
	 * @Title: updateLastUpdateTime
	 * @Description: TODO(更新最后修改时间)
	 * @param user_id
	 * @param request
	 * @return String
	 * @author: jiaodelong
	 * @time:2017年3月22日 下午1:39:35
	 * history:
	 * 1、2017年3月22日 jiaodelong 创建方法
	 */
	@RequestMapping(value = "/loanappro/updateLastUpdateTime.do", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateLastUpdateTime(Integer user_id,HttpServletRequest request) {
        String result = "";
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmscrepersonnelbillchangeService.updateLastUpdateTime(user_id);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
      
        map.put("result", result);
        return map;
    }   
	
	
	
	
	
	
	
}