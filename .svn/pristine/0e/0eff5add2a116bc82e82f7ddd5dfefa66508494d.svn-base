package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsPersonnelAchievementHisDao;
import com.zx.emanage.inve.service.IWmsPersonnelAchievementHisService;
import com.zx.emanage.inve.vo.WmsPersonnelAchievementHisSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsPersonnelAchievementHis;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmspersonnelachievementhisService")
public class WmsPersonnelAchievementHisServiceImpl implements IWmsPersonnelAchievementHisService {
	private static Logger log = LoggerFactory.getLogger(WmsPersonnelAchievementHisServiceImpl.class);

	@Autowired
	private WmsPersonnelAchievementHisDao wmspersonnelachievementhisDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsPersonnelAchievementHisSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmspersonnelachievementhisDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsPersonnelAchievementHisSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmspersonnelachievementhisDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmspersonnelachievementhisDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsPersonnelAchievementHis getInfoByPK(Integer wms_personnel_achievement_his_id) {
		return wmspersonnelachievementhisDao.get(wms_personnel_achievement_his_id);
	}

	@Override	
	@Transactional
	public String save(WmsPersonnelAchievementHis bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmspersonnelachievementhisDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsPersonnelAchievementHis bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmspersonnelachievementhisDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsPersonnelAchievementHis() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsPersonnelAchievementHis> getListByEntity(WmsPersonnelAchievementHis queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsPersonnelAchievementHis> beanList = wmspersonnelachievementhisDao.getListByEntity(queryInfo);
		return beanList;
	}

    /**
     * @Title: getPersonnelAchievementByPid
     * @Description: 通过人员Id和static_month查询员工本月 上季度 本季度业绩
     * @param queryInfo
     * @return 
     * @author: Administrator
     * @time:2017年1月12日 下午2:52:57
     * @see com.zx.emanage.inve.service.IWmsPersonnelAchievementHisService#getPersonnelAchievementByPid(com.zx.emanage.inve.vo.WmsPersonnelAchievementHisSearchBeanVO)
     * history:
     * 1、2017年1月12日 Administrator 创建方法
    */
    @Override
    public Map<String, Object> getPersonnelAchievementByPid(WmsPersonnelAchievementHisSearchBeanVO queryInfo)
    {
        Map<String, Object> map = wmspersonnelachievementhisDao.getPersonnelAchievementByPid(queryInfo);
        return map;
    }
}
