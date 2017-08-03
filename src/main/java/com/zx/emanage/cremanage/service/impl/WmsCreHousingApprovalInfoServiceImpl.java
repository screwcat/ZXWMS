package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreHousingApprovalInfoDao;
import com.zx.emanage.cremanage.service.IWmsCreHousingApprovalInfoService;
import com.zx.emanage.util.gen.entity.WmsCreHousingApprovalInfo;
import com.zx.emanage.cremanage.vo.WmsCreHousingApprovalInfoSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrehousingapprovalinfoService")
public class WmsCreHousingApprovalInfoServiceImpl implements IWmsCreHousingApprovalInfoService {
	private static Logger log = LoggerFactory.getLogger(WmsCreHousingApprovalInfoServiceImpl.class);

	@Autowired
	private WmsCreHousingApprovalInfoDao wmscrehousingapprovalinfoDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreHousingApprovalInfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrehousingapprovalinfoDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreHousingApprovalInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrehousingapprovalinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrehousingapprovalinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}


	@Override	
	@Transactional
	public String save(WmsCreHousingApprovalInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrehousingapprovalinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreHousingApprovalInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreHousingApprovalInfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreHousingApprovalInfo> getListByEntity(WmsCreHousingApprovalInfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreHousingApprovalInfo> beanList = wmscrehousingapprovalinfoDao.getListByEntity(queryInfo);
		return beanList;
	}
}
