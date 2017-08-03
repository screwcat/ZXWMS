package com.zx.emanage.inve.web;

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

import com.zx.emanage.inve.service.IWmsInveCustomerCardService;
import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.inve.vo.WmsInveCustomerCardSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveCustomerCardController
 * 模块名称：
 * @Description: 内容摘要：收益卡相关操作controller
 * @author DongHao
 * @date 2017年4月7日
 * @version V1.0
 * history:
 * 1、2017年4月7日 DongHao 创建文件
 */
@Controller
public class WmsInveCustomerCardController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCustomerCardController.class);
	
	@Autowired
	private IWmsInveCustomerCardService wmsinvecustomercardService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecustomercardwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCustomerCardSearchBeanVO queryInfo) {
		return wmsinvecustomercardService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecustomercardwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCustomerCardSearchBeanVO queryInfo) {
		return wmsinvecustomercardService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCustomerCardVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecustomercardinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCustomerCard getInfoByPK(Integer wms_inve_customer_card_id) {
		return wmsinvecustomercardService.getInfoByPK(wms_inve_customer_card_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecustomercardsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCustomerCard bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecustomercardService.save(bean, user);
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
	@RequestMapping(value = "/inve/wmsinvecustomercardupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCustomerCard bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecustomercardService.update(bean, user);
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
	 * @Title: newAddIncomeCard
	 * @Description: 新增收益卡
	 * @param wmsInveCustomerCard 收益卡信息对象
	 * @param request 页面对象信息
	 * @return 返回map信息
	 * @author: DongHao
	 * @time:2017年4月7日 下午2:22:14
	 * history:
	 * 1、2017年4月7日 DongHao 创建方法
	 */
	@RequestMapping(value="/inve/newAddIncomeCard.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> newAddIncomeCard(HttpServletRequest request, WmsInveCustomerCard wmsInveCustomerCard)
	{
	    //获取当前登录人信息
        UserBean user = (UserBean)request.getSession().getAttribute(GlobalVal.USER_SESSION);
        
        //定义返回结果map集合
        Map<String, Object> res_map = new HashMap<String, Object>();
        
        try
        {
            res_map = wmsinvecustomercardService.newAddIncomeCard(user, wmsInveCustomerCard);
        }
        catch (Exception e)
        {
            res_map.put("error", "error");
            e.printStackTrace();
        }
        
	    return res_map;
	}
}