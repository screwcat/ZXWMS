package com.zx.emanage.creditRightManager.service;

import java.util.Map;

import com.zx.emanage.creditRightManager.vo.WmsCredRightColRuleVO;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName: WmsCredRightColRuleService
 * @Description: 规则配置service
 * @author WangShuai
 * @date 2017年2月13日
 * @version V1.0
 * history:
 * 1、2017年2月13日 WangShuai 创建文件
 */
public interface WmsCredRightColRuleService {

	/**
	 * @Title: wmsCredRightColRuleList
	 * @Description:初始话系数
	 * @param wmsCredRightColRuleVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月22日 上午10:28:28
	 * history:
	 * 1、2017年2月22日 WangShuai 创建方法
	*/
	Map<String, Object> wmsCredRightColRuleList(
			WmsCredRightColRuleVO wmsCredRightColRuleVO);

	/**
	 * @Title: getPersonnelScopeVice
	 * @Description: 获取人员列表
	 * @param wmsCredRightColRuleVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月22日 上午10:28:31
	 * history:
	 * 1、2017年2月22日 WangShuai 创建方法
	*/
	Map<String, Object> getPersonnelScopeVice(
			WmsCredRightColRuleVO wmsCredRightColRuleVO);

	/**
	 * @Title: saveWmsCredRightColRules
	 * @Description: 保存配置信息
	 * @param wmsCredRightColRuleVO
	 * @param user
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月22日 上午10:28:33
	 * history:
	 * 1、2017年2月22日 WangShuai 创建方法
	*/
	Map<String, Object> saveWmsCredRightColRules(
			WmsCredRightColRuleVO wmsCredRightColRuleVO, UserBean user);
}
