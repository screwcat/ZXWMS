package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionCategoryDao;
import com.zx.emanage.inve.service.IWmsInveCommissionCategoryService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionCategory;
import com.zx.emanage.inve.vo.WmsInveCommissionCategorySearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissioncategoryService")
public class WmsInveCommissionCategoryServiceImpl implements IWmsInveCommissionCategoryService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionCategoryServiceImpl.class);

	@Autowired
	private WmsInveCommissionCategoryDao wmsinvecommissioncategoryDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionCategorySearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissioncategoryDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionCategorySearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissioncategoryDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissioncategoryDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionCategory getInfoByPK(Integer wms_inve_commission_category_id) {
		return wmsinvecommissioncategoryDao.get(wms_inve_commission_category_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionCategory bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissioncategoryDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionCategory bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissioncategoryDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionCategory() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionCategory> getListByEntity(WmsInveCommissionCategory queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionCategory> beanList = wmsinvecommissioncategoryDao.getListByEntity(queryInfo);
		return beanList;
	}
}
