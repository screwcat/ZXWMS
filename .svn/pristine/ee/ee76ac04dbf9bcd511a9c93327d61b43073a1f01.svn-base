package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePruductYearPaySpecialDao;
import com.zx.emanage.inve.service.IWmsInvePruductYearPaySpecialService;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.emanage.inve.vo.WmsInvePruductYearPaySpecialSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvepruductyearpayspecialService")
public class WmsInvePruductYearPaySpecialServiceImpl implements IWmsInvePruductYearPaySpecialService {
	private static Logger log = LoggerFactory.getLogger(WmsInvePruductYearPaySpecialServiceImpl.class);

	@Autowired
	private WmsInvePruductYearPaySpecialDao wmsinvepruductyearpayspecialDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInvePruductYearPaySpecialSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvepruductyearpayspecialDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInvePruductYearPaySpecialSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvepruductyearpayspecialDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvepruductyearpayspecialDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInvePruductYearPaySpecial getInfoByPK(Integer wms_inve_pruduct_year_pay_special_id) {
		return wmsinvepruductyearpayspecialDao.get(wms_inve_pruduct_year_pay_special_id);
	}

	@Override	
	@Transactional
	public String save(WmsInvePruductYearPaySpecial bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvepruductyearpayspecialDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInvePruductYearPaySpecial bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvepruductyearpayspecialDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInvePruductYearPaySpecial() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInvePruductYearPaySpecial> getListByEntity(WmsInvePruductYearPaySpecial queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInvePruductYearPaySpecial> beanList = wmsinvepruductyearpayspecialDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :根据产品主键获取 年付特表信息
	 * @param primary key 
	 * @return WmsInvePruductYearPaySpecial
	 * @author baisong
	 *  2015-8-14
	 */	
	@Override
	public WmsInvePruductYearPaySpecial getInfoByCK(Integer wms_inve_pruduct_category_id) {
		WmsInvePruductYearPaySpecial paySpecial=new WmsInvePruductYearPaySpecial();
		paySpecial.setWms_inve_pruduct_category_id(wms_inve_pruduct_category_id);
		List<WmsInvePruductYearPaySpecial>	list =wmsinvepruductyearpayspecialDao.getListByEntity(paySpecial);
		return list.get(0);
	}

}
