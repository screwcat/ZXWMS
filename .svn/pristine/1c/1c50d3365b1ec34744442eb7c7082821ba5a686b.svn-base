package com.zx.emanage.loanfina.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaCreOverdueHistoryDao;
import com.zx.emanage.loanfina.service.IWmsFinaCreOverdueHistoryService;
import com.zx.emanage.util.gen.entity.WmsFinaCreOverdueHistory;
import com.zx.emanage.loanfina.vo.WmsFinaCreOverdueHistorySearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinacreoverduehistoryService")
public class WmsFinaCreOverdueHistoryServiceImpl implements IWmsFinaCreOverdueHistoryService {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreOverdueHistoryServiceImpl.class);

	@Autowired
	private WmsFinaCreOverdueHistoryDao wmsfinacreoverduehistoryDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsFinaCreOverdueHistorySearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsfinacreoverduehistoryDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsFinaCreOverdueHistorySearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinacreoverduehistoryDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacreoverduehistoryDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsFinaCreOverdueHistory getInfoByPK(Integer wms_fina_cre_overdue_history_id) {
		return wmsfinacreoverduehistoryDao.get(wms_fina_cre_overdue_history_id);
	}

	@Override	
	@Transactional
	public String save(WmsFinaCreOverdueHistory bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacreoverduehistoryDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsFinaCreOverdueHistory bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacreoverduehistoryDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsFinaCreOverdueHistory() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsFinaCreOverdueHistory> getListByEntity(WmsFinaCreOverdueHistory queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsFinaCreOverdueHistory> beanList = wmsfinacreoverduehistoryDao.getListByEntity(queryInfo);
		return beanList;
	}
}
