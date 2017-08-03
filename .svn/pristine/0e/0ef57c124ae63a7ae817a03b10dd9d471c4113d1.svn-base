package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveUpdateCardLogDao;
import com.zx.emanage.inve.service.IWmsInveUpdateCardLogService;
import com.zx.emanage.util.gen.entity.WmsInveUpdateCardLog;
import com.zx.emanage.inve.vo.WmsInveUpdateCardLogSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveupdatecardlogService")
public class WmsInveUpdateCardLogServiceImpl implements IWmsInveUpdateCardLogService {
	private static Logger log = LoggerFactory.getLogger(WmsInveUpdateCardLogServiceImpl.class);

	@Autowired
	private WmsInveUpdateCardLogDao wmsinveupdatecardlogDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveUpdateCardLogSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinveupdatecardlogDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveUpdateCardLogSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinveupdatecardlogDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinveupdatecardlogDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveUpdateCardLog getInfoByPK(Integer wms_inve_update_card_log) {
		return wmsinveupdatecardlogDao.get(wms_inve_update_card_log);
	}

	@Override	
	@Transactional
	public String save(WmsInveUpdateCardLog bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveupdatecardlogDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveUpdateCardLog bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveupdatecardlogDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveUpdateCardLog() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveUpdateCardLog> getListByEntity(WmsInveUpdateCardLog queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveUpdateCardLog> beanList = wmsinveupdatecardlogDao.getListByEntity(queryInfo);
		return beanList;
	}
}
