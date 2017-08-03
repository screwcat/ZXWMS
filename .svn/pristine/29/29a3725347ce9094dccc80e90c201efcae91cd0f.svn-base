package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveSalaryPreItemHisDao;
import com.zx.emanage.inve.service.IWmsInveSalaryPreItemHisService;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreItemHis;
import com.zx.emanage.inve.vo.WmsInveSalaryPreItemHisSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvesalarypreitemhisService")
public class WmsInveSalaryPreItemHisServiceImpl implements IWmsInveSalaryPreItemHisService {
	private static Logger log = LoggerFactory.getLogger(WmsInveSalaryPreItemHisServiceImpl.class);

	@Autowired
	private WmsInveSalaryPreItemHisDao wmsinvesalarypreitemhisDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveSalaryPreItemHisSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvesalarypreitemhisDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveSalaryPreItemHisSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvesalarypreitemhisDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvesalarypreitemhisDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveSalaryPreItemHis getInfoByPK(Integer wms_inve_salary_pre_item_his_id) {
		return wmsinvesalarypreitemhisDao.get(wms_inve_salary_pre_item_his_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveSalaryPreItemHis bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvesalarypreitemhisDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveSalaryPreItemHis bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvesalarypreitemhisDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveSalaryPreItemHis() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveSalaryPreItemHis> getListByEntity(WmsInveSalaryPreItemHis queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveSalaryPreItemHis> beanList = wmsinvesalarypreitemhisDao.getListByEntity(queryInfo);
		return beanList;
	}
}
