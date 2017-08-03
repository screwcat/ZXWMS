package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveOldCommBaseDataSpecialHisDao;
import com.zx.emanage.inve.service.IWmsInveOldCommBaseDataSpecialHisService;
import com.zx.emanage.util.gen.entity.WmsInveOldCommBaseDataSpecialHis;
import com.zx.emanage.inve.vo.WmsInveOldCommBaseDataSpecialHisSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveoldcommbasedataspecialhisService")
public class WmsInveOldCommBaseDataSpecialHisServiceImpl implements IWmsInveOldCommBaseDataSpecialHisService {
	private static Logger log = LoggerFactory.getLogger(WmsInveOldCommBaseDataSpecialHisServiceImpl.class);

	@Autowired
	private WmsInveOldCommBaseDataSpecialHisDao wmsinveoldcommbasedataspecialhisDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveOldCommBaseDataSpecialHisSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinveoldcommbasedataspecialhisDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveOldCommBaseDataSpecialHisSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinveoldcommbasedataspecialhisDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinveoldcommbasedataspecialhisDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveOldCommBaseDataSpecialHis getInfoByPK(Integer wms_inve_old_comm_base_data_special_his_id) {
		return wmsinveoldcommbasedataspecialhisDao.get(wms_inve_old_comm_base_data_special_his_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveOldCommBaseDataSpecialHis bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveoldcommbasedataspecialhisDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveOldCommBaseDataSpecialHis bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveoldcommbasedataspecialhisDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveOldCommBaseDataSpecialHis() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveOldCommBaseDataSpecialHis> getListByEntity(WmsInveOldCommBaseDataSpecialHis queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveOldCommBaseDataSpecialHis> beanList = wmsinveoldcommbasedataspecialhisDao.getListByEntity(queryInfo);
		return beanList;
	}
}
