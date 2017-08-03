package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommionRecordAdjustDao;
import com.zx.emanage.inve.service.IWmsInveCommionRecordAdjustService;
import com.zx.emanage.util.gen.entity.WmsInveCommionRecordAdjust;
import com.zx.emanage.inve.vo.WmsInveCommionRecordAdjustSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommionrecordadjustService")
public class WmsInveCommionRecordAdjustServiceImpl implements IWmsInveCommionRecordAdjustService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommionRecordAdjustServiceImpl.class);

	@Autowired
	private WmsInveCommionRecordAdjustDao wmsinvecommionrecordadjustDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommionRecordAdjustSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommionrecordadjustDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommionRecordAdjustSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommionrecordadjustDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommionrecordadjustDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommionRecordAdjust getInfoByPK(Integer wms_inve_commion_record_adjust_id) {
		return wmsinvecommionrecordadjustDao.get(wms_inve_commion_record_adjust_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommionRecordAdjust bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommionrecordadjustDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommionRecordAdjust bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommionrecordadjustDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommionRecordAdjust() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommionRecordAdjust> getListByEntity(WmsInveCommionRecordAdjust queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommionRecordAdjust> beanList = wmsinvecommionrecordadjustDao.getListByEntity(queryInfo);
		return beanList;
	}
}
