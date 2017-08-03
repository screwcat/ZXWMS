package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionGeneralDisposableRulesNewDao;
import com.zx.emanage.inve.service.IWmsInveCommissionGeneralDisposableRulesNewService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralDisposableRulesNew;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralDisposableRulesNewSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissiongeneraldisposablerulesnewService")
public class WmsInveCommissionGeneralDisposableRulesNewServiceImpl implements IWmsInveCommissionGeneralDisposableRulesNewService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionGeneralDisposableRulesNewServiceImpl.class);

	@Autowired
	private WmsInveCommissionGeneralDisposableRulesNewDao wmsinvecommissiongeneraldisposablerulesnewDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralDisposableRulesNewSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissiongeneraldisposablerulesnewDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralDisposableRulesNewSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissiongeneraldisposablerulesnewDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissiongeneraldisposablerulesnewDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionGeneralDisposableRulesNew getInfoByPK(Integer wms_inve_commission_general_disposable_rules_new_id) {
		return wmsinvecommissiongeneraldisposablerulesnewDao.get(wms_inve_commission_general_disposable_rules_new_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionGeneralDisposableRulesNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneraldisposablerulesnewDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionGeneralDisposableRulesNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneraldisposablerulesnewDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionGeneralDisposableRulesNew() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionGeneralDisposableRulesNew> getListByEntity(WmsInveCommissionGeneralDisposableRulesNew queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionGeneralDisposableRulesNew> beanList = wmsinvecommissiongeneraldisposablerulesnewDao.getListByEntity(queryInfo);
		return beanList;
	}
}
