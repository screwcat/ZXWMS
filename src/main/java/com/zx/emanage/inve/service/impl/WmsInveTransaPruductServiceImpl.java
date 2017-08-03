package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveTransaPruductDao;
import com.zx.emanage.inve.service.IWmsInveTransaPruductService;
import com.zx.emanage.util.gen.entity.WmsInveTransaPruduct;
import com.zx.emanage.inve.vo.WmsInveTransaPruductSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransapruductService")
public class WmsInveTransaPruductServiceImpl implements IWmsInveTransaPruductService {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaPruductServiceImpl.class);

	@Autowired
	private WmsInveTransaPruductDao wmsinvetransapruductDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveTransaPruductSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvetransapruductDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveTransaPruductSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvetransapruductDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvetransapruductDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveTransaPruduct getInfoByPK(Integer wms_inve_transa_pruduct_id) {
		return wmsinvetransapruductDao.get(wms_inve_transa_pruduct_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveTransaPruduct bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransapruductDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveTransaPruduct bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransapruductDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveTransaPruduct() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveTransaPruduct> getListByEntity(WmsInveTransaPruduct queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveTransaPruduct> beanList = wmsinvetransapruductDao.getListByEntity(queryInfo);
		return beanList;
	}
}
