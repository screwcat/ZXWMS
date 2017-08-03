package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionCategoryNewDao;
import com.zx.emanage.inve.service.IWmsInveCommissionCategoryNewService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionCategoryNew;
import com.zx.emanage.inve.vo.WmsInveCommissionCategoryNewSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissioncategorynewService")
public class WmsInveCommissionCategoryNewServiceImpl implements IWmsInveCommissionCategoryNewService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionCategoryNewServiceImpl.class);

	@Autowired
	private WmsInveCommissionCategoryNewDao wmsinvecommissioncategorynewDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionCategoryNewSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissioncategorynewDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionCategoryNewSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissioncategorynewDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissioncategorynewDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionCategoryNew getInfoByPK(Integer wms_inve_commission_category_new_id) {
		return wmsinvecommissioncategorynewDao.get(wms_inve_commission_category_new_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionCategoryNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissioncategorynewDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionCategoryNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissioncategorynewDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionCategoryNew() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionCategoryNew> getListByEntity(WmsInveCommissionCategoryNew queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionCategoryNew> beanList = wmsinvecommissioncategorynewDao.getListByEntity(queryInfo);
		return beanList;
	}
}
