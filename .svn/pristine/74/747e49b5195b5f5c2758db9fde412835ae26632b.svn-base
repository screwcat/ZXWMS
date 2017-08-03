package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveWagerulesNonlocalLvDao;
import com.zx.emanage.inve.service.IWmsInveWagerulesNonlocalLvService;
import com.zx.emanage.util.gen.entity.WmsInveWagerulesNonlocalLv;
import com.zx.emanage.inve.vo.WmsInveWagerulesNonlocalLvSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvewagerulesnonlocallvService")
public class WmsInveWagerulesNonlocalLvServiceImpl implements IWmsInveWagerulesNonlocalLvService {
	private static Logger log = LoggerFactory.getLogger(WmsInveWagerulesNonlocalLvServiceImpl.class);

	@Autowired
	private WmsInveWagerulesNonlocalLvDao wmsinvewagerulesnonlocallvDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveWagerulesNonlocalLvSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvewagerulesnonlocallvDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveWagerulesNonlocalLvSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvewagerulesnonlocallvDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvewagerulesnonlocallvDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveWagerulesNonlocalLv getInfoByPK(Integer wms_inve_wagerules_nonlocal_lv_id) {
		return wmsinvewagerulesnonlocallvDao.get(wms_inve_wagerules_nonlocal_lv_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveWagerulesNonlocalLv bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvewagerulesnonlocallvDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveWagerulesNonlocalLv bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvewagerulesnonlocallvDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveWagerulesNonlocalLv() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveWagerulesNonlocalLv> getListByEntity(WmsInveWagerulesNonlocalLv queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveWagerulesNonlocalLv> beanList = wmsinvewagerulesnonlocallvDao.getListByEntity(queryInfo);
		return beanList;
	}
}
