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

import com.zx.emanage.inve.service.IWmsInveWagerulesHeadService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveWagerulesHead;
import com.zx.emanage.inve.vo.WmsInveWagerulesHeadSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveWagerulesHeadController {
	private static Logger log = LoggerFactory.getLogger(WmsInveWagerulesHeadController.class);
	
	@Autowired
	private IWmsInveWagerulesHeadService wmsinvewagerulesheadService;

	/**
	 * Description :实现理财客户经理的工资规则的保存
	 * @param bean 工资规则记录Bean
	 * @return "success" or "error" 返回保存提示信息
	 * @author hancd
	 */	
	@RequestMapping(value = "/inve/wmsinvewagerulesheadsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveWagerulesHead bean,String fdgz) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvewagerulesheadService.save(bean, user,fdgz);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}	
		if("success".equals(result)){
			String msg = "系统管理-理财工资规则-工资基本信息存储";
			SysTools.saveLog(request, msg);
		}
		return result;
	}
	/**
	 * Description :通过传递不同职务获取相关职务的所有配置信息
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	@RequestMapping(value = "/inve/getWmsInveWageRulesList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getWmsInveWageRulesList(WmsInveWagerulesHeadSearchBeanVO queryInfo) {
		return wmsinvewagerulesheadService.getWmsInveWageRulesList(queryInfo);
	}
	/**
	 * Description :更新理财规则数据
	 * @param bean 工资主表信息
	 * @param fdgz 浮动工资列表数据
	 * @return "success" or "error" or user defined
	 * @author hancd
	 */	
	@RequestMapping(value = "/inve/wmsinvewagerulesheadupdate.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveWagerulesHead bean,String fdgz) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvewagerulesheadService.update(bean, user,fdgz);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		if("success".equals(result)){
			String msg = "系统管理-理财工资规则-工资基本信息更新";
			SysTools.saveLog(request, msg); 
		}
		return result;
	}
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvewagerulesheadwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveWagerulesHeadSearchBeanVO queryInfo) {
		return wmsinvewagerulesheadService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvewagerulesheadwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveWagerulesHeadSearchBeanVO queryInfo) {
		return wmsinvewagerulesheadService.getListWithPaging(queryInfo);
	}
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveWagerulesHeadVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvewagerulesheadinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveWagerulesHead getInfoByPK(Integer wms_inve_wagerules_head_id) {
		return wmsinvewagerulesheadService.getInfoByPK(wms_inve_wagerules_head_id);
	}	
	/**
	 * Description :通过传递工资表主键信息获取相关基本信息和浮动工资信息设置
	 * @param wms_inve_wagerules_head_id
	 * @return record list
	 * @author hancd
	 */
	@RequestMapping(value = "/inve/wmsinvewagerulesheadorfloatinginfobypk.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> wmsinvewagerulesheadorfloatinginfobypk(Integer wms_inve_wagerules_head_id) {
		return wmsinvewagerulesheadService.getInveWageRulesHFbypk(wms_inve_wagerules_head_id);
	}
}