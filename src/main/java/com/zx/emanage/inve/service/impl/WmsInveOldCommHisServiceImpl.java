package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveOldCommHisDao;
import com.zx.emanage.inve.service.IWmsInveOldCommHisService;
import com.zx.emanage.util.gen.entity.WmsInveOldCommHis;
import com.zx.emanage.inve.vo.WmsInveOldCommHisSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveoldcommhisService")
public class WmsInveOldCommHisServiceImpl implements IWmsInveOldCommHisService {
	private static Logger log = LoggerFactory.getLogger(WmsInveOldCommHisServiceImpl.class);

	@Autowired
	private WmsInveOldCommHisDao wmsinveoldcommhisDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveOldCommHisSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinveoldcommhisDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveOldCommHisSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinveoldcommhisDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinveoldcommhisDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveOldCommHis getInfoByPK(Integer wms_inve_old_comm_his_id) {
		return wmsinveoldcommhisDao.get(wms_inve_old_comm_his_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveOldCommHis bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveoldcommhisDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveOldCommHis bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveoldcommhisDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveOldCommHis() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveOldCommHis> getListByEntity(WmsInveOldCommHis queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveOldCommHis> beanList = wmsinveoldcommhisDao.getListByEntity(queryInfo);
		return beanList;
	}
}
