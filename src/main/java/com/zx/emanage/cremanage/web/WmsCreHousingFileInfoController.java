package com.zx.emanage.cremanage.web;

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

import com.zx.emanage.cremanage.service.IWmsCreHousingFileInfoService;
import com.zx.emanage.util.gen.domain.WmsCusCustomerLineHouseinfo;
import com.zx.emanage.util.gen.entity.WmsCreHousingFileInfo;
import com.zx.emanage.cremanage.vo.WmsCreHousingFileInfoSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreHousingFileInfoController {
	private static Logger log = LoggerFactory.getLogger(WmsCreHousingFileInfoController.class);
	
	@Autowired
	private IWmsCreHousingFileInfoService wmscrehousingfileinfoService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging 导出
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/cremanage/wmscrehousingfileinfowithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(HttpServletRequest request, WmsCreHousingFileInfo queryInfo) {
   	 	HttpSession session = request.getSession();
   	 	UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrehousingfileinfoService.getListWithoutPaging(user,queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging 列表显示
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/cremanage/wmscrehousingfileinfowithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreHousingFileInfoSearchBeanVO queryInfo) {
		return wmscrehousingfileinfoService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreHousingFileInfoVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/cremanage/wmscrehousingfileinfoinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreHousingFileInfo getInfoByPK(Integer wms_cre_housing_file_info_id) {
		return wmscrehousingfileinfoService.getInfoByPK(wms_cre_housing_file_info_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/cremanage/wmscrehousingfileinfosave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreHousingFileInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrehousingfileinfoService.save(bean, user);
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
	@RequestMapping(value = "/cremanage/wmscrehousingfileinfoupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreHousingFileInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrehousingfileinfoService.update(bean, user);
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
     * 房贷申请列表显示
     * 
     * @param bean
     * @return String
     * @author jiaodelong
     */
    @RequestMapping(value = "/cusmanage/getHouseCreditList.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getHouseCreditList(HttpServletRequest request, WmsCreHousingFileInfo bean)
    {
    	 HttpSession session = request.getSession();
         UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
         return wmscrehousingfileinfoService.getHouseCreditList(bean,user);
    }
    
	
	
	
}