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

import com.zx.emanage.cremanage.service.IWmsCreCarpCarsCustomerInfoService;
import com.zx.emanage.util.gen.entity.WmsCreCarpCarsCustomerInfo;
import com.zx.emanage.cremanage.vo.WmsCreCarpCarsCustomerInfoSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreCarpHousingCustomerInfoSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCarpCarsCustomerInfoController {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpCarsCustomerInfoController.class);
	
	@Autowired
	private IWmsCreCarpCarsCustomerInfoService wmscrecarpcarscustomerinfoService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/cremanage/wmscrecarpcarscustomerinfowithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsCreCarpCarsCustomerInfoSearchBeanVO queryInfo) {
		return wmscrecarpcarscustomerinfoService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/cremanage/wmscrecarpcarscustomerinfowithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsCreCarpCarsCustomerInfoSearchBeanVO queryInfo) {
		return wmscrecarpcarscustomerinfoService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpCarsCustomerInfoVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/cremanage/wmscrecarpcarscustomerinfoinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsCreCarpCarsCustomerInfo getInfoByPK(Integer wms_cre_carp_cars_customer_info_id) {
		return wmscrecarpcarscustomerinfoService.getInfoByPK(wms_cre_carp_cars_customer_info_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/cremanage/wmscrecarpcarscustomerinfosave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsCreCarpCarsCustomerInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarpcarscustomerinfoService.save(bean, user);
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
	@RequestMapping(value = "/cremanage/wmscrecarpcarscustomerinfoupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsCreCarpCarsCustomerInfo bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmscrecarpcarscustomerinfoService.update(bean, user);
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
	 * Description :get 根据贷款主表id返回抵押车辆信息
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@RequestMapping(value = "/cremanage/wmscrecarpcarscustomerinfolist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getLis(WmsCreCarpCarsCustomerInfoSearchBeanVO queryInfo) {
		return wmscrecarpcarscustomerinfoService.getList(queryInfo);
	}
	
	/**
     * Description :得到抵押房产信息 为车贷查询
     * 
     * @param queryInfo
     * @return record list
     */
    @RequestMapping(value = "/cremanage/wmscrecarcustomercarallinfowithmccidf.do", method = { RequestMethod.GET,
                                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCarHInfoListWithoutPagingByMccid(WmsCreCarpHousingCustomerInfoSearchBeanVO queryInfo) {
        return wmscrecarpcarscustomerinfoService.getCarHInfoListWithoutPagingByMccid(queryInfo);
    }
    
    /**
     * Description :车贷抵押车产重复性判断
     * 
     * @param queryInfo
     * @return record list
     */
    @RequestMapping(value = "/cremanage/wmscrecarcustomercarsearchcarinfoexistcount.do", method = { RequestMethod.GET,
    		RequestMethod.POST })
    @ResponseBody
    public Integer searchCarInfoExistCount(String wms_cus_customer_id, String vehicle_idn_num) {
    	return wmscrecarpcarscustomerinfoService.searchCarInfoExistCount(wms_cus_customer_id, vehicle_idn_num);
    }
}