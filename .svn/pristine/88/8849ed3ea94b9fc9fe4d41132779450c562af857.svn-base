package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCurrentRateDao;
import com.zx.emanage.inve.service.IWmsInveCurrentRateService;
import com.zx.emanage.util.gen.entity.WmsInveCurrentRate;
import com.zx.emanage.inve.vo.WmsInveCurrentRateSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecurrentrateService")
public class WmsInveCurrentRateServiceImpl implements IWmsInveCurrentRateService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCurrentRateServiceImpl.class);

	@Autowired
	private WmsInveCurrentRateDao wmsinvecurrentrateDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCurrentRateSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecurrentrateDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCurrentRateSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecurrentrateDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecurrentrateDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCurrentRate getInfoByPK(Integer wms_inve_current_rate_id) {
		return wmsinvecurrentrateDao.get(wms_inve_current_rate_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCurrentRate bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecurrentrateDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCurrentRate bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecurrentrateDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCurrentRate() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCurrentRate> getListByEntity(WmsInveCurrentRate queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCurrentRate> beanList = wmsinvecurrentrateDao.getListByEntity(queryInfo);
		return beanList;
	}
}
