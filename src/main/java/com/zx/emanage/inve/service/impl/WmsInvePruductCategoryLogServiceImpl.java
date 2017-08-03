package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePruductCategoryLogDao;
import com.zx.emanage.inve.service.IWmsInvePruductCategoryLogService;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategoryLog;
import com.zx.emanage.inve.vo.WmsInvePruductCategoryLogSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvepruductcategorylogService")
public class WmsInvePruductCategoryLogServiceImpl implements IWmsInvePruductCategoryLogService {
	private static Logger log = LoggerFactory.getLogger(WmsInvePruductCategoryLogServiceImpl.class);

	@Autowired
	private WmsInvePruductCategoryLogDao wmsinvepruductcategorylogDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInvePruductCategoryLogSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvepruductcategorylogDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInvePruductCategoryLogSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvepruductcategorylogDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvepruductcategorylogDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInvePruductCategoryLog getInfoByPK(Integer wms_inve_pruduct_category_log_id) {
		return wmsinvepruductcategorylogDao.get(wms_inve_pruduct_category_log_id);
	}

	@Override	
	@Transactional
	public String save(WmsInvePruductCategoryLog bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvepruductcategorylogDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInvePruductCategoryLog bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvepruductcategorylogDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInvePruductCategoryLog() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInvePruductCategoryLog> getListByEntity(WmsInvePruductCategoryLog queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInvePruductCategoryLog> beanList = wmsinvepruductcategorylogDao.getListByEntity(queryInfo);
		return beanList;
	}
}
