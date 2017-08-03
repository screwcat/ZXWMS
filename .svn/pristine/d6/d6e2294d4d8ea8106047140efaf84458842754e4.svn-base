package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionPersonnelRewardDao;
import com.zx.emanage.inve.service.IWmsInveCommissionPersonnelRewardService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionPersonnelReward;
import com.zx.emanage.inve.vo.WmsInveCommissionPersonnelRewardSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionpersonnelrewardService")
public class WmsInveCommissionPersonnelRewardServiceImpl implements IWmsInveCommissionPersonnelRewardService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionPersonnelRewardServiceImpl.class);

	@Autowired
	private WmsInveCommissionPersonnelRewardDao wmsinvecommissionpersonnelrewardDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionPersonnelRewardSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionpersonnelrewardDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionPersonnelRewardSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissionpersonnelrewardDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionpersonnelrewardDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionPersonnelReward getInfoByPK(Integer wms_inve_commission_personnel_reward_id) {
		return wmsinvecommissionpersonnelrewardDao.get(wms_inve_commission_personnel_reward_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionPersonnelReward bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionpersonnelrewardDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionPersonnelReward bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionpersonnelrewardDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionPersonnelReward() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionPersonnelReward> getListByEntity(WmsInveCommissionPersonnelReward queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionPersonnelReward> beanList = wmsinvecommissionpersonnelrewardDao.getListByEntity(queryInfo);
		return beanList;
	}
}
