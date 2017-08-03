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

import com.zx.emanage.cremanage.service.IWmsCreCarpHousingCustomerInfoService;
import com.zx.emanage.util.gen.entity.WmsCreCarpHousingCustomerInfo;
import com.zx.emanage.cremanage.vo.WmsCreCarpHousingCustomerInfoSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreHousingCustomerHouseSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCarpHousingCustomerInfoController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpHousingCustomerInfoController.class);
	
	@Autowired
	private IWmsCreCarpHousingCustomerInfoService wmscrecarphousingcustomerinfoService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/cremanage/wmscrecarphousingcustomerinfowithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCarpHousingCustomerInfoSearchBeanVO queryInfo) {
		return wmscrecarphousingcustomerinfoService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/cremanage/wmscrecarphousingcustomerinfowithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCarpHousingCustomerInfoSearchBeanVO queryInfo) {
		return wmscrecarphousingcustomerinfoService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpHousingCustomerInfoVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/cremanage/wmscrecarphousingcustomerinfoinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCarpHousingCustomerInfo getInfoByPK(Integer wms_cre_carp_housing_customer_info_id) {
		return wmscrecarphousingcustomerinfoService.getInfoByPK(wms_cre_carp_housing_customer_info_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/cremanage/wmscrecarphousingcustomerinfosave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCarpHousingCustomerInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarphousingcustomerinfoService.save(bean, user);
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
	@RequestMapping(value = "/cremanage/wmscrecarphousingcustomerinfoupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCarpHousingCustomerInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarphousingcustomerinfoService.update(bean, user);
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
     * Description :得到抵押房产信息 为车贷查询
     * 
     * @param queryInfo
     * @return record list
     */
    @RequestMapping(value = "/cremanage/wmscrecarcustomerhouseallinfowithmccidf.do", method = { RequestMethod.GET,
                                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCarHInfoListWithoutPagingByMccid(WmsCreCarpHousingCustomerInfoSearchBeanVO queryInfo) {
        return wmscrecarphousingcustomerinfoService.getCarHInfoListWithoutPagingByMccid(queryInfo);
    }
}