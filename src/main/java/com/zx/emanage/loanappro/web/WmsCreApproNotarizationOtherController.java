package com.zx.emanage.loanappro.web;

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

import com.zx.emanage.loanappro.service.IWmsCreApproNotarizationOtherService;
import com.zx.emanage.loanappro.vo.WmsCreNotarizationOtherSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2017，卓信金控 系统名称：财富管理平台
 * 
 * @ClassName: WmsCreApproNotarizationOtherController 模块名称：
 * @Description: 内容摘要：
 * @author ZhangWei
 * @date 2017年7月6日
 * @version V1.0 history: 1、2017年7月6日 ZhangWei 创建文件
 */
@Controller
public class WmsCreApproNotarizationOtherController {
	private static Logger log = LoggerFactory.getLogger(WmsCreApproNotarizationOtherController.class);

	@Autowired
	private IWmsCreApproNotarizationOtherService wmsCreApproNotarizationOtherService;

	@RequestMapping(value = "/loanappro/getNotarizationOtherListWithPaging.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getNotarizationOtherListWithPaging(HttpServletRequest request, WmsCreNotarizationOtherSearchBeanVO queryInfo) {
		// 获取当前登录人
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmsCreApproNotarizationOtherService.getNotarizationOtherListWithPaging(queryInfo, user);
	}
	
	
	@RequestMapping(value = "/loanappro/getNotarizationOtherListWithoutPaging.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getNotarizationOtherListWithoutPaging(HttpServletRequest request, WmsCreNotarizationOtherSearchBeanVO queryInfo) {
		// 获取当前登录人
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmsCreApproNotarizationOtherService.getNotarizationOtherListWithoutPaging(queryInfo, user);
	}
	
	@RequestMapping(value = "/loanappro/GetFinanceLoanAppInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> GetFinanceLoanAppInfo(HttpServletRequest request, Integer wms_cre_credit_head_id) {
		// 获取当前登录人
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmsCreApproNotarizationOtherService.GetFinanceLoanAppInfo(wms_cre_credit_head_id);
	}

	@RequestMapping(value = "/loanappro/notarizationOtherConfirm.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String heIsAmountConfirm(HttpServletRequest request, WmsCreNotarizationOtherSearchBeanVO queryInfo) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		String result = "";
        try
        {
            result = wmsCreApproNotarizationOtherService.updateNotarizationOther(queryInfo, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
	}
}
