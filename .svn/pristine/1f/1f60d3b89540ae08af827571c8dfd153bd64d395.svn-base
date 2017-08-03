package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveWagerulesFloatingDao;
import com.zx.emanage.inve.service.IWmsInveWagerulesFloatingService;
import com.zx.emanage.util.gen.entity.WmsInveWagerulesFloating;
import com.zx.emanage.inve.vo.WmsInveWagerulesFloatingSearchBeanVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvewagerulesfloatingService")
public class WmsInveWagerulesFloatingServiceImpl implements IWmsInveWagerulesFloatingService {
	private static Logger log = LoggerFactory.getLogger(WmsInveWagerulesFloatingServiceImpl.class);

	@Autowired
	private WmsInveWagerulesFloatingDao wmsinvewagerulesfloatingDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveWagerulesFloatingSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvewagerulesfloatingDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveWagerulesFloatingSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvewagerulesfloatingDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvewagerulesfloatingDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveWagerulesFloating getInfoByPK(Integer wms_inve_wagerules_floating_id) {
		return wmsinvewagerulesfloatingDao.get(wms_inve_wagerules_floating_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveWagerulesFloating bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvewagerulesfloatingDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveWagerulesFloating bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvewagerulesfloatingDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveWagerulesFloating() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveWagerulesFloating> getListByEntity(WmsInveWagerulesFloating queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveWagerulesFloating> beanList = wmsinvewagerulesfloatingDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :通过理财工资规则主表主键获取相应对应的浮动规则信息
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	@Override
	public Map<String, Object> getWmsInveWageRulesFloatingInfoByfk(
			WmsInveWagerulesFloatingSearchBeanVO queryInfo) {
		Map<String,Object> paramMap = new HashMap<>();
		if(StringUtil.isNotBlank(queryInfo.getWms_inve_wagerules_head_id())){
			paramMap.put("wms_inve_wagerules_head_id", queryInfo.getWms_inve_wagerules_head_id());
		}
	    paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		List<Map<String,Object>>  list = wmsinvewagerulesfloatingDao.searchByFK(paramMap);
		Map<String, Object> resMap = new HashMap<String, Object>();
	    resMap.put("Rows",list);
		return resMap;				
	}
}
