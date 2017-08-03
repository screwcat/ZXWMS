package com.zx.emanage.reportmanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.reportmanage.persist.WmsInveCommissionTeamPerformanceDao;
import com.zx.emanage.reportmanage.service.IWmsInveCommissionTeamPerformanceService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionTeamPerformance;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionTeamPerformanceSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionteamperformanceService")
public class WmsInveCommissionTeamPerformanceServiceImpl implements IWmsInveCommissionTeamPerformanceService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionTeamPerformanceServiceImpl.class);

	@Autowired
	private WmsInveCommissionTeamPerformanceDao wmsinvecommissionteamperformanceDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionteamperformanceDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissionteamperformanceDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionteamperformanceDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionTeamPerformance getInfoByPK(Integer wms_inve_commission_team_performance_id) {
		return wmsinvecommissionteamperformanceDao.get(wms_inve_commission_team_performance_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionTeamPerformance bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionteamperformanceDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionTeamPerformance bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionteamperformanceDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionTeamPerformance() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionTeamPerformance> getListByEntity(WmsInveCommissionTeamPerformance queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionTeamPerformance> beanList = wmsinvecommissionteamperformanceDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :根据人员id获取团队佣金
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@Override
	public Map<String, Object> getListTeam(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	 	paramMap.put("team_user_id", queryInfo.getTeam_user_id()); 
	 	paramMap.put("create_datetime",new java.sql.Date(queryInfo.getCreate_datetime())); 
		paramMap.put("show",queryInfo.getShow()); 
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionteamperformanceDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
}
