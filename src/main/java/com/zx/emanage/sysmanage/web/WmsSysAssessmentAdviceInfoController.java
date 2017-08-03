package com.zx.emanage.sysmanage.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.sysmanage.service.IWmsSysAssessmentAdviceInfoService;
import com.zx.emanage.util.gen.entity.WmsSysAssessmentAdviceInfo;

/**
 * 版权所有：版权所有(C) 2016，卓信金控 系统名称：财富管理平台
 * 
 * @ClassName: WmsSysAssessmentAdviceInfoController 模块名称：
 * @Description: 内容摘要：
 * @author ZhangWei
 * @date 2017年5月18日
 * @version V1.0 history: 1、2017年5月18日 ZhangWei 创建文件
 */
@Controller
public class WmsSysAssessmentAdviceInfoController {

	@Autowired
	private IWmsSysAssessmentAdviceInfoService WmsSysAssessmentAdviceInfoService;

	@RequestMapping(value = "/sysmanage/getAssessmentAdviceInfo.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public List<WmsSysAssessmentAdviceInfo> getAssessmentAdviceInfo() {
		return WmsSysAssessmentAdviceInfoService.getAssessmentAdviceInfo();
	}
}
