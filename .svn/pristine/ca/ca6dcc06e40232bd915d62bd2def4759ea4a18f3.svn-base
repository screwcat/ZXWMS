package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionGeneralMonthlyRulesDao;
import com.zx.emanage.inve.service.IWmsInveCommissionGeneralMonthlyRulesService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralMonthlyRules;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralMonthlyRulesSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissiongeneralmonthlyrulesService")
public class WmsInveCommissionGeneralMonthlyRulesServiceImpl implements IWmsInveCommissionGeneralMonthlyRulesService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionGeneralMonthlyRulesServiceImpl.class);

	@Autowired
	private WmsInveCommissionGeneralMonthlyRulesDao wmsinvecommissiongeneralmonthlyrulesDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralMonthlyRulesSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissiongeneralmonthlyrulesDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralMonthlyRulesSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissiongeneralmonthlyrulesDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissiongeneralmonthlyrulesDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionGeneralMonthlyRules getInfoByPK(Integer wms_inve_commission_general_monthly_rules_id) {
		return wmsinvecommissiongeneralmonthlyrulesDao.get(wms_inve_commission_general_monthly_rules_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionGeneralMonthlyRules bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneralmonthlyrulesDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionGeneralMonthlyRules bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneralmonthlyrulesDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionGeneralMonthlyRules() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionGeneralMonthlyRules> getListByEntity(WmsInveCommissionGeneralMonthlyRules queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionGeneralMonthlyRules> beanList = wmsinvecommissiongeneralmonthlyrulesDao.getListByEntity(queryInfo);
		return beanList;
	}
}
