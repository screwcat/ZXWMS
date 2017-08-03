package com.zx.emanage.creditRightManager.service.impl;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.creditRightManager.persist.WmsCredRightColRuleDao;
import com.zx.emanage.creditRightManager.service.WmsCredRightColRuleService;
import com.zx.emanage.creditRightManager.vo.WmsCredRightColRuleVO;
import com.zx.emanage.creditRightManager.vo.WmsInveCreditSplitconfigBodyVO;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName: WmsCredRightColRuleServiceImpl
 * @Description: 规则配置Service 实现类
 * @author WangShuai
 * @date 2017年2月13日
 * @version V1.0
 * history:
 * 1、2017年2月13日 WangShuai 创建文件
 */
@Service("wmsCredRightColRuleService")
public class WmsCredRightColRuleServiceImpl implements WmsCredRightColRuleService{
	
	@Autowired
	WmsCredRightColRuleDao wmsCredRightColRuleDao;
	/**
	 * @ClassName: WmsCredRightColRuleService
	 * @Description: 规则配置service
	 * @author WangShuai
	 * @date 2017年2月13日
	 * @version V1.0
	 * history:
	 * 1、2017年2月13日 WangShuai 创建文件
	 */
	@Override
	public Map<String, Object> wmsCredRightColRuleList(
			WmsCredRightColRuleVO wmsCredRightColRuleVO) {
		Map<String,Object> res = new HashMap<String,Object>();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list=wmsCredRightColRuleDao.getWmsCredRightColRuleList();
		//去null处理
		list=list==null?new ArrayList<Map<String,Object>>():list;
		
		Map<String,Object> contr = wmsCredRightColRuleDao.getRuleCoeffMap();
		//去null处理
		contr=contr==null?new HashMap<String,Object>():contr;
		
		res.putAll(contr);
		res.put("rulelist", list);
		
		return res;
	}
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
	@Override
	public Map<String, Object> getPersonnelScopeVice(
			WmsCredRightColRuleVO wmsCredRightColRuleVO) {
		
		List<WmsInveCreditSplitconfigBodyVO> alSel=JsonUtil.jsonArrayToList(wmsCredRightColRuleVO.getAlSel(), WmsInveCreditSplitconfigBodyVO.class);
		
		if(null==alSel){
			alSel = new ArrayList<WmsInveCreditSplitconfigBodyVO>();
		}
		
		Map<String,Object> para =new HashMap<String,Object>();
		para.put("alSel", alSel);
		
	    List<Map<String,Object>>  list = wmsCredRightColRuleDao.getPersonnelScopeVice(para);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;	
	}
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
	@Override
	public Map<String, Object> saveWmsCredRightColRules(
			WmsCredRightColRuleVO wmsCredRightColRuleVO, UserBean user) {
		BigDecimal ela_coeff = new BigDecimal(StringUtils.isEmpty(wmsCredRightColRuleVO.getEla_coeff())?"0":wmsCredRightColRuleVO.getEla_coeff());
		int personnel_id = user.getUserId();
		List<WmsInveCreditSplitconfigBodyVO> configs=JsonUtil.jsonArrayToList(wmsCredRightColRuleVO.getAllConfigInfo(), WmsInveCreditSplitconfigBodyVO.class);
		Map<String,Object> para=new HashMap<String,Object>();
		para.put("ela_coeff", ela_coeff);
		para.put("personnel_id", personnel_id);
		wmsCredRightColRuleDao.deleteOtherConfig();
		wmsCredRightColRuleDao.insertCurrConfig(para);
		
		if(configs.size()>0){
			para.put("al", configs);
			wmsCredRightColRuleDao.deleteOtherConfigDetail();
			wmsCredRightColRuleDao.insertCurrConfigDetail(para);
			wmsCredRightColRuleDao.updateCurrConfigDetailInfo();
		}
		
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("result", "success");
		return res;
	}
}
