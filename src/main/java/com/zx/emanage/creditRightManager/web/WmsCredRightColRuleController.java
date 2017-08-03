package com.zx.emanage.creditRightManager.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.creditRightManager.service.WmsCredRightColRuleService;
import com.zx.emanage.creditRightManager.vo.WmsCredRightColRuleVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName: WmsCredRightColRuleController
 * @Description: 债权匹配规则处理类
 * @author WangShuai
 * @date 2017年2月13日
 * @version V1.0
 * history:
 * 1、2017年2月13日 WangShuai 创建文件
 */
@Controller
public class WmsCredRightColRuleController {

	@Autowired
	WmsCredRightColRuleService wmsCredRightColRuleService;
	
	/**
	 * @Title: getListWithPaging
	 * @Description: 获取列表
	 * @param queryInfo
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月13日 下午2:33:22
	 * history:
	 * 1、2017年2月13日 WangShuai 创建方法
	*/
	@RequestMapping(value = "/creRigMan/wmsCredRightColRuleList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> wmsCredRightColRuleList(WmsCredRightColRuleVO wmsCredRightColRuleVO) {
		return wmsCredRightColRuleService.wmsCredRightColRuleList(wmsCredRightColRuleVO);
	}
	
	
	/**
	 * @Title: getPersonnelScopeVice
	 * @Description: 获取人员范围
	 * @param wmsCredRightColRuleVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月20日 上午10:53:44
	 * history:
	 * 1、2017年2月20日 WangShuai 创建方法
	*/
	@RequestMapping(value = "/creRigMan/getPersonnelScopeVice.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getPersonnelScopeVice(WmsCredRightColRuleVO wmsCredRightColRuleVO) {
		return wmsCredRightColRuleService.getPersonnelScopeVice(wmsCredRightColRuleVO);
	}
	
		
	/**
	 * @Title: saveWmsCredRightColRules
	 * @Description: 保存配置
	 * @param wmsCredRightColRuleVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月20日 下午1:59:07
	 * history:
	 * 1、2017年2月20日 WangShuai 创建方法
	*/
	@RequestMapping(value = "/creRigMan/saveWmsCredRightColRules.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> saveWmsCredRightColRules(HttpServletRequest request,WmsCredRightColRuleVO wmsCredRightColRuleVO) {
		 HttpSession session = request.getSession();
	     UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmsCredRightColRuleService.saveWmsCredRightColRules(wmsCredRightColRuleVO,user);
	}	
}
