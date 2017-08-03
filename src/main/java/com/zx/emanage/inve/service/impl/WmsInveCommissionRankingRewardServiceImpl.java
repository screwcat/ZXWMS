package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionRankingRewardDao;
import com.zx.emanage.inve.service.IWmsInveCommissionRankingRewardService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionRankingReward;
import com.zx.emanage.inve.vo.WmsInveCommissionRankingRewardSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionrankingrewardService")
public class WmsInveCommissionRankingRewardServiceImpl implements IWmsInveCommissionRankingRewardService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionRankingRewardServiceImpl.class);

	@Autowired
	private WmsInveCommissionRankingRewardDao wmsinvecommissionrankingrewardDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionRankingRewardSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionrankingrewardDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionRankingRewardSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissionrankingrewardDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionrankingrewardDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionRankingReward getInfoByPK(Integer wms_inve_commission_ranking_reward_id) {
		return wmsinvecommissionrankingrewardDao.get(wms_inve_commission_ranking_reward_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionRankingReward bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionrankingrewardDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionRankingReward bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionrankingrewardDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionRankingReward() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionRankingReward> getListByEntity(WmsInveCommissionRankingReward queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionRankingReward> beanList = wmsinvecommissionrankingrewardDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :根据奖励方式获取奖励规则 
	 * @param WmsInveCommissionRankingRewardSearchBeanVO
	 * @return Map<String,Object>
	 * @author baisong
	 */	
	@Override
	public Map<String,Object>  getInfoByMethod(WmsInveCommissionRankingRewardSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("reward_method", queryInfo.getReward_method());//奖励方式   ：按照佣金比例奖励 1 奖金奖励0
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",wmsinvecommissionrankingrewardDao.getInfoByMethod(paramMap));
		return resMap;
	}

}
