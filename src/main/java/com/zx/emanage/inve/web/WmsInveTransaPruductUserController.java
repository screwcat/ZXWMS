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

import com.zx.emanage.inve.exception.InveTransException;
import com.zx.emanage.inve.service.IWmsInveTransaPruductUserService;
import com.zx.emanage.util.gen.entity.WmsInveTransaPruductUser;
import com.zx.emanage.inve.vo.WmsInveTransaPruductUserSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveTransaPruductUserController {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaPruductUserController.class);
	
	@Autowired
	private IWmsInveTransaPruductUserService wmsinvetransapruductuserService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvetransapruductuserwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveTransaPruductUserSearchBeanVO queryInfo) {
		return wmsinvetransapruductuserService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvetransapruductuserwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveTransaPruductUserSearchBeanVO queryInfo) {
		return wmsinvetransapruductuserService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveTransaPruductUserVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvetransapruductuserinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveTransaPruductUser getInfoByPK(Integer wms_inve_transa_pruduct_user_id) {
		return wmsinvetransapruductuserService.getInfoByPK(wms_inve_transa_pruduct_user_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvetransapruductusersave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveTransaPruductUser bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvetransapruductuserService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvetransapruductuserupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveTransaPruductUser bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvetransapruductuserService.update(bean, user);
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
     * Description :整个页面数据保存
     * @param WmsInveTransaPruductUserSearchBeanVO
     * @return "success" or "error" or user defined
     * @author wangyihan
     */ 
    @RequestMapping(value = "/inve/wmsInveTransaAllSave.do", method = {RequestMethod.POST})
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveTransaPruductUserSearchBeanVO bean) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
        try{
            result = wmsinvetransapruductuserService.allSave(bean, user);
        }catch(InveTransException e){//用户重复
            result = "errorUser"; 
        }catch (Exception e) {
            result = "error"; 
        }
        return result;
    }
	
}