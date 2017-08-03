package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionFloatingDao;
import com.zx.emanage.inve.service.IWmsInveCommissionFloatingService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionFloating;
import com.zx.emanage.inve.vo.WmsInveCommissionFloatingSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionfloatingService")
public class WmsInveCommissionFloatingServiceImpl implements IWmsInveCommissionFloatingService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionFloatingServiceImpl.class);

	@Autowired
	private WmsInveCommissionFloatingDao wmsinvecommissionfloatingDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionFloatingSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionfloatingDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionFloatingSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissionfloatingDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionfloatingDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionFloating getInfoByPK(Integer wms_inve_commission_floating_id) {
		return wmsinvecommissionfloatingDao.get(wms_inve_commission_floating_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionFloating bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionfloatingDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionFloating bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionfloatingDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionFloating() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionFloating> getListByEntity(WmsInveCommissionFloating queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionFloating> beanList = wmsinvecommissionfloatingDao.getListByEntity(queryInfo);
		return beanList;
	}
}
