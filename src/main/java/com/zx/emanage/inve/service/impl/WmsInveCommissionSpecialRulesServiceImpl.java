package com.zx.emanage.inve.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionSpecialRulesDao;
import com.zx.emanage.inve.persist.WmsInveCommissionSpecialRulesPtDao;
import com.zx.emanage.inve.service.IWmsInveCommissionSpecialRulesService;
import com.zx.emanage.inve.vo.WmsInveCommissionSpecialRulesSearchBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.WmsInveCommissionSpecialRules;
import com.zx.emanage.util.gen.entity.WmsInveCommissionSpecialRulesPt;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionspecialrulesService")
public class WmsInveCommissionSpecialRulesServiceImpl implements IWmsInveCommissionSpecialRulesService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionSpecialRulesServiceImpl.class);

	@Autowired
	private WmsInveCommissionSpecialRulesDao wmsinvecommissionspecialrulesDao;

	@Autowired
	private WmsInveCommissionSpecialRulesPtDao wmsInveCommissionSpecialRulesPtDao;
	
	@Autowired
	private PmPersonnelDao pmPersonnelDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionSpecialRulesSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	 	paramMap.put("sortname", "wms_inve_commission_special_rules_id");
        paramMap.put("sortorder", "asc");
	    if(queryInfo.getForbidden_id_str() != null && queryInfo.getForbidden_id_str() != "") {
	    	List<String> forbiddenIds = JsonUtil.jsonArrayToList(queryInfo.getForbidden_id_str(), String.class);
	    	if(forbiddenIds != null && forbiddenIds.size() > 0) {
	    		paramMap.put("forbiddenIds", forbiddenIds);
	    	}
	    }
	    paramMap.put("enable_flag", "1");
	    List<Map<String,Object>> list = wmsinvecommissionspecialrulesDao.searchSpecials(paramMap);
	    
	    for(Map<String,Object> map : list) {
	    	WmsInveCommissionSpecialRulesPt pt = new WmsInveCommissionSpecialRulesPt();
	    	pt.setWms_inve_commission_special_rules_id((Integer) map.get("wms_inve_commission_special_rules_id"));
	    	List<WmsInveCommissionSpecialRulesPt> pts = wmsInveCommissionSpecialRulesPtDao.getListByEntity(pt);
	    	map.put("wmsInveCommissionSpecialRulesPts", pts);
	    }
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionSpecialRulesSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", "wms_inve_commission_special_rules_id");
        paramMap.put("sortorder", "asc");
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        if(queryInfo.getForbidden_id_str() != null && queryInfo.getForbidden_id_str() != "") {
        	List<String> forbiddenIds = JsonUtil.jsonArrayToList(queryInfo.getForbidden_id_str(), String.class);
        	if(forbiddenIds != null && forbiddenIds.size() > 0) {
	    		paramMap.put("forbiddenIds", forbiddenIds);
	    	}
	    }
        paramMap.put("enable_flag", "1");
        List<Map<String,Object>> list = wmsinvecommissionspecialrulesDao.searchSpecials(paramMap);
	    
	    for(Map<String,Object> map : list) {
	    	WmsInveCommissionSpecialRulesPt pt = new WmsInveCommissionSpecialRulesPt();
	    	pt.setWms_inve_commission_special_rules_id((Integer) map.get("wms_inve_commission_special_rules_id"));
	    	List<WmsInveCommissionSpecialRulesPt> pts = wmsInveCommissionSpecialRulesPtDao.getListByEntity(pt);
	    	map.put("wmsInveCommissionSpecialRulesPts", pts);
	    }
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionspecialrulesDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionSpecialRules getInfoByPK(Integer wms_inve_commission_special_rules_id) {
		return wmsinvecommissionspecialrulesDao.get(wms_inve_commission_special_rules_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionSpecialRules bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionspecialrulesDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionSpecialRules bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionspecialrulesDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionSpecialRules() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionSpecialRules> getListByEntity(WmsInveCommissionSpecialRules queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionSpecialRules> beanList = wmsinvecommissionspecialrulesDao.getListByEntity(queryInfo);
		return beanList;
	}
	
	/**
	 * 提成特殊规则的添加和修改
	 */
	@Override
	public String saveAndUpdate(String specialRulesStr, UserBean user) {
		try {
			List<WmsInveCommissionSpecialRules> specialRules = JsonUtil.jsonArrayToList(specialRulesStr, WmsInveCommissionSpecialRules.class);
			List<WmsInveCommissionSpecialRulesSearchBeanVO> specialRulesPt = JsonUtil.jsonArrayToList(specialRulesStr, WmsInveCommissionSpecialRulesSearchBeanVO.class);
			
			for(int i = 0; i < specialRules.size(); i++) {
				WmsInveCommissionSpecialRules wmsInveCommissionSpecialRules = specialRules.get(i);
				WmsInveCommissionSpecialRulesSearchBeanVO beanVO = specialRulesPt.get(i);
				//启用、 禁用
				if("1".equals(wmsInveCommissionSpecialRules.getPage_rule_state())) {
					wmsInveCommissionSpecialRules.setStop_date(new Date(System.currentTimeMillis()));
				} else if("0".equals(wmsInveCommissionSpecialRules.getPage_rule_state())) {
					wmsInveCommissionSpecialRules.setStart_date(new Date(System.currentTimeMillis()));
					
				}
				wmsInveCommissionSpecialRules.setRule_state(wmsInveCommissionSpecialRules.getPage_rule_state());
				
				/*
				 * 主表：主键为空执行插入操作，否则更新操作 
				 */
				if(wmsInveCommissionSpecialRules.getWms_inve_commission_special_rules_id() == null) {	
					//主表
					wmsInveCommissionSpecialRules.setEnable_flag("1");
					wmsinvecommissionspecialrulesDao.save(wmsInveCommissionSpecialRules);
					
				} else {
					//主表
					wmsinvecommissionspecialrulesDao.update(wmsInveCommissionSpecialRules);
					
				}
				
				String personnelIdStr = beanVO.getPersonnel_id_str();
				List<String> personnelIds = Arrays.asList(personnelIdStr.split(","));
				
				//查询人员表中的人员信息
				List<WmsInveCommissionSpecialRulesPt> pecialRulesPts = wmsInveCommissionSpecialRulesPtDao.getPmPersonnels(personnelIds);
				for(WmsInveCommissionSpecialRulesPt pts : pecialRulesPts) {
					pts.setWms_inve_commission_special_rules_id(wmsInveCommissionSpecialRules.getWms_inve_commission_special_rules_id());
					pts.setCreate_user_id(user.getUserId());
					pts.setCreate_user_name(user.getRealname());
					pts.setCreate_user_dept_id(user.getDeptId());
					pts.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
					pts.setEnable_flag("1");
					if("0".equals(wmsInveCommissionSpecialRules.getPage_rule_state())) {//禁用
						pts.setEnable_flag("0");
					}
					
				}
				
				//删除原有人员信息
				wmsInveCommissionSpecialRulesPtDao.delPecialRulesPts(wmsInveCommissionSpecialRules.getWms_inve_commission_special_rules_id());
				
				//添加新的人员信息
				if(pecialRulesPts != null && pecialRulesPts.size() > 0) {
					wmsInveCommissionSpecialRulesPtDao.addBatch(pecialRulesPts);
				}
				
			}
		} catch (Exception e) {
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * 用于前台数据重复校验
	 */
	@Override
	public Map<String, Object> getListWithoutPagingForCompare(WmsInveCommissionSpecialRulesSearchBeanVO queryInfo) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
	 	paramMap.put("sortname", "wms_inve_commission_special_rules_id");
        paramMap.put("sortorder", "asc");
	    if(queryInfo.getForbidden_id_str() != null && queryInfo.getForbidden_id_str() != "") {
	    	List<String> forbiddenIds = JsonUtil.jsonArrayToList(queryInfo.getForbidden_id_str(), String.class);
	    	if(forbiddenIds != null && forbiddenIds.size() > 0) {
	    		paramMap.put("forbiddenIds", forbiddenIds);
	    	}
	    }
	    paramMap.put("enable_flag", "1");
	    List<Map<String,Object>> list = wmsinvecommissionspecialrulesDao.searchSpecials(paramMap);
	    
	    for(Map<String,Object> map : list) {
	    	WmsInveCommissionSpecialRulesPt pt = new WmsInveCommissionSpecialRulesPt();
	    	pt.setWms_inve_commission_special_rules_id((Integer) map.get("wms_inve_commission_special_rules_id"));
	    	List<WmsInveCommissionSpecialRulesPt> pts = wmsInveCommissionSpecialRulesPtDao.getListByEntity(pt);
	    	String str = "";
	    	if(pts != null && pts.size() > 0) {
	    		StringBuffer buffer = new StringBuffer();
		    	for(WmsInveCommissionSpecialRulesPt rulesPt : pts) {
		    		buffer.append(rulesPt.getPersonnel_id()).append(",");	
		    	}
		    	str = buffer.substring(0, buffer.length() - 1);
	    	}
	    	map.put("personnel_id_str", str);
	    }
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
}
