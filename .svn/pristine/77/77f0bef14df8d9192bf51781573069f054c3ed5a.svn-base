package com.zx.emanage.inve.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveExpiredCustomerService;
import com.zx.emanage.inve.vo.WmsInveExpiredCustomerVo;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName WmsInveExpiredCustomerController
 * @Description 内容摘要: 到期客户一览表的controller类
 * @author DongHao
 * @date 2016年11月24日
 * @version V1.0
 * @history 1. 2016年11月24日17:52:38 DongHao 创建文件
 */
@Controller
public class WmsInveExpiredCustomerController {

	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(WmsInveExpiredCustomerController.class);
	
	@Autowired
	private IWmsInveExpiredCustomerService wmsInveExpiredCustomerService;
	
	
	/**
	 * @Title findExpiredCustomer
	 * @Description 内容摘要: 该方法是查询到期客户一览表的数据
	 * @param wmsInveExpiredCustomerVo
	 *            查询条件对象
	 * @param request
	 * @param response
	 * @return 返回map集合
	 * @author DongHao
	 * @Time 2016年11月24日 17:42:45
	 * @history 1. 2016年11月24日17:43:16 DongHao 创建方法
	 */
	@RequestMapping(value = "/inve/wmsInveExpiredCustomerList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> findExpiredCustomer(WmsInveExpiredCustomerVo wmsInveExpiredCustomerVo,HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
    	UserBean user =(UserBean)session.getAttribute(GlobalVal.USER_SESSION);
    	Map<String, Object> resultMap = wmsInveExpiredCustomerService.findExpiredCustomer(user, wmsInveExpiredCustomerVo);
		return resultMap;
	}
	
	/**
	 * @Title exportExpiredCustomerExcel
	 * @Description 内容摘要: 该方法主要是获取导出到期客户报表的数据
	 * @param wmsInveExpiredCustomerVo
	 * @param request
	 * @param response
	 * @return 返回map集合
	 * @author DongHao
	 * @Time 2016年11月24日 17:42:45
	 * @history 1. 2016年11月24日17:43:16 DongHao 创建方法
	 */
	@RequestMapping(value="/inve/getsearchexpiredcustomerlisttopage.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> exportExpiredCustomerExcel(WmsInveExpiredCustomerVo wmsInveExpiredCustomerVo, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
    	UserBean user =(UserBean)session.getAttribute(GlobalVal.USER_SESSION);
    	Map<String, Object> resultMap = wmsInveExpiredCustomerService.findExportExpiredCustomerExcel(user, wmsInveExpiredCustomerVo);
		return resultMap;
	}
}
