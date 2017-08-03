package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveExtendInfoDao;
import com.zx.emanage.inve.service.IWmsInveExtendInfoService;
import com.zx.emanage.util.gen.entity.WmsInveExtendInfo;
import com.zx.emanage.inve.vo.WmsInveExtendInfoSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveextendinfoService")
public class WmsInveExtendInfoServiceImpl implements IWmsInveExtendInfoService {
	private static Logger log = LoggerFactory.getLogger(WmsInveExtendInfoServiceImpl.class);

	@Autowired
	private WmsInveExtendInfoDao wmsinveextendinfoDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveExtendInfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinveextendinfoDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveExtendInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinveextendinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinveextendinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveExtendInfo getInfoByPK(Integer wms_inve_extend_info_id) {
		return wmsinveextendinfoDao.get(wms_inve_extend_info_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveExtendInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveextendinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveExtendInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveextendinfoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveExtendInfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveExtendInfo> getListByEntity(WmsInveExtendInfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveExtendInfo> beanList = wmsinveextendinfoDao.getListByEntity(queryInfo);
		return beanList;
	}
}
