package com.zx.emanage.loanappro.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanappro.persist.WmsCreCreditServiceTypeDao;
import com.zx.emanage.loanappro.service.IWmsCreCreditServiceTypeService;
import com.zx.emanage.util.gen.entity.WmsCreCreditServiceType;
import com.zx.emanage.loanappro.vo.WmsCreCreditServiceTypeSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditservicetypeService")
public class WmsCreCreditServiceTypeServiceImpl implements IWmsCreCreditServiceTypeService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditServiceTypeServiceImpl.class);

	@Autowired
	private WmsCreCreditServiceTypeDao wmscrecreditservicetypeDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCreditServiceTypeSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrecreditservicetypeDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreCreditServiceTypeSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrecreditservicetypeDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecreditservicetypeDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCreditServiceType getInfoByPK(Integer wms_cre_credit_service_type_id) {
		return wmscrecreditservicetypeDao.get(wms_cre_credit_service_type_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCreditServiceType bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecreditservicetypeDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCreditServiceType bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecreditservicetypeDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCreditServiceType() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCreditServiceType> getListByEntity(WmsCreCreditServiceType queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCreditServiceType> beanList = wmscrecreditservicetypeDao.getListByEntity(queryInfo);
		return beanList;
	}
}
