package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionGeneralNetRulesNewDao;
import com.zx.emanage.inve.service.IWmsInveCommissionGeneralNetRulesNewService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralNetRulesNew;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralNetRulesNewSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissiongeneralnetrulesnewService")
public class WmsInveCommissionGeneralNetRulesNewServiceImpl implements IWmsInveCommissionGeneralNetRulesNewService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionGeneralNetRulesNewServiceImpl.class);

	@Autowired
	private WmsInveCommissionGeneralNetRulesNewDao wmsinvecommissiongeneralnetrulesnewDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralNetRulesNewSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissiongeneralnetrulesnewDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralNetRulesNewSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissiongeneralnetrulesnewDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissiongeneralnetrulesnewDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionGeneralNetRulesNew getInfoByPK(Integer wms_inve_commission_general_net_rules_new_id) {
		return wmsinvecommissiongeneralnetrulesnewDao.get(wms_inve_commission_general_net_rules_new_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionGeneralNetRulesNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneralnetrulesnewDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionGeneralNetRulesNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneralnetrulesnewDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionGeneralNetRulesNew() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionGeneralNetRulesNew> getListByEntity(WmsInveCommissionGeneralNetRulesNew queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionGeneralNetRulesNew> beanList = wmsinvecommissiongeneralnetrulesnewDao.getListByEntity(queryInfo);
		return beanList;
	}
}
