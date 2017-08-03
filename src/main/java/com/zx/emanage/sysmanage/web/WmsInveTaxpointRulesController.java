package com.zx.emanage.sysmanage.web;

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
import com.zx.emanage.sysmanage.service.IWmsInveTaxpointRulesService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveTaxpointRules;
import com.zx.emanage.sysmanage.vo.WmsInveTaxpointRulesSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveTaxpointRulesController {
	private static Logger log = LoggerFactory.getLogger(WmsInveTaxpointRulesController.class);
	
	@Autowired
	private IWmsInveTaxpointRulesService wmsinvetaxpointrulesService;

	/**
	 * Description :获取税点配置所有信息
	 * @param queryInfo
	 * @return record list
	 * @author yangqiyu
	 */
	@RequestMapping(value = "/sysmanage/wmsinvetaxpointruleswithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveTaxpointRulesSearchBeanVO queryInfo) {
		return wmsinvetaxpointrulesService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveTaxpointRulesVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/sysmanage/wmsinvetaxpointrulesinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveTaxpointRules getInfoByPK(Integer wms_inve_taxpoint_rules_id) {
		return wmsinvetaxpointrulesService.getInfoByPK(wms_inve_taxpoint_rules_id);
	}	

	/**
	 * @Title:doUpdate
	 * Description :理财税点信息保存操作
	 * @param request
	 * @param beanJSON
	 * @return "success" or "error"
	 * @author hancd
	 * @date 2015年12月28日
	 */
	@RequestMapping(value = "/sysmanage/wmsinvetaxpointrulesChange.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, String beanJSON) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvetaxpointrulesService.doChange(beanJSON, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}			 
		if("success".equals(result)){
			String msg = "产品管理>理财税点配置";
			SysTools.saveLog(request, msg); // record log method
		}
		return result;
	}	
	/**
	 * Description :获取税点配置固定值
	 * @return String
	 * @author yangqiyu
	 */
	@RequestMapping(value="/sysmanage/wmsinvetaxpointrulesgetTaxFixed.do",method={RequestMethod.POST})
	@ResponseBody
	public String getTax_fixed(){
		String result="";
		result=wmsinvetaxpointrulesService.taxfixedToPage();
		return result;
	}
}