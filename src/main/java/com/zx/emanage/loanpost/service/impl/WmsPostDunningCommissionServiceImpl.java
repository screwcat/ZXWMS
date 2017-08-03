package com.zx.emanage.loanpost.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanpost.persist.WmsPostDunningCommissionDao;
import com.zx.emanage.loanpost.service.IWmsPostDunningCommissionService;
import com.zx.emanage.util.gen.entity.WmsPostDunningCommission;
import com.zx.emanage.loanpost.vo.WmsPostDunningCommissionSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmspostdunningcommissionService")
public class WmsPostDunningCommissionServiceImpl implements IWmsPostDunningCommissionService {
	private static Logger log = LoggerFactory.getLogger(WmsPostDunningCommissionServiceImpl.class);

	@Autowired
	private WmsPostDunningCommissionDao wmspostdunningcommissionDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsPostDunningCommissionSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmspostdunningcommissionDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsPostDunningCommissionSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmspostdunningcommissionDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmspostdunningcommissionDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsPostDunningCommission getInfoByPK(Integer wms_post_dunning_commission_id) {
		return wmspostdunningcommissionDao.get(wms_post_dunning_commission_id);
	}

	@Override	
	@Transactional
	public String save(WmsPostDunningCommission bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmspostdunningcommissionDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsPostDunningCommission bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmspostdunningcommissionDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsPostDunningCommission() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsPostDunningCommission> getListByEntity(WmsPostDunningCommission queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsPostDunningCommission> beanList = wmspostdunningcommissionDao.getListByEntity(queryInfo);
		return beanList;
	}
}
