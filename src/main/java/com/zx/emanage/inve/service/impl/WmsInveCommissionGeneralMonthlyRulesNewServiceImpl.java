package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionGeneralMonthlyRulesNewDao;
import com.zx.emanage.inve.service.IWmsInveCommissionGeneralMonthlyRulesNewService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralMonthlyRulesNew;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralMonthlyRulesNewSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissiongeneralmonthlyrulesnewService")
public class WmsInveCommissionGeneralMonthlyRulesNewServiceImpl implements IWmsInveCommissionGeneralMonthlyRulesNewService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionGeneralMonthlyRulesNewServiceImpl.class);

	@Autowired
	private WmsInveCommissionGeneralMonthlyRulesNewDao wmsinvecommissiongeneralmonthlyrulesnewDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralMonthlyRulesNewSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissiongeneralmonthlyrulesnewDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralMonthlyRulesNewSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissiongeneralmonthlyrulesnewDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissiongeneralmonthlyrulesnewDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionGeneralMonthlyRulesNew getInfoByPK(Integer wms_inve_commission_general_monthly_rules_new_id) {
		return wmsinvecommissiongeneralmonthlyrulesnewDao.get(wms_inve_commission_general_monthly_rules_new_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionGeneralMonthlyRulesNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneralmonthlyrulesnewDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionGeneralMonthlyRulesNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneralmonthlyrulesnewDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionGeneralMonthlyRulesNew() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionGeneralMonthlyRulesNew> getListByEntity(WmsInveCommissionGeneralMonthlyRulesNew queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionGeneralMonthlyRulesNew> beanList = wmsinvecommissiongeneralmonthlyrulesnewDao.getListByEntity(queryInfo);
		return beanList;
	}
}
