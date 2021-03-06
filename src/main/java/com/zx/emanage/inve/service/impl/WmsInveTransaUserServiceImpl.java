package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveTransaUserDao;
import com.zx.emanage.inve.service.IWmsInveTransaUserService;
import com.zx.emanage.util.gen.entity.WmsInveTransaUser;
import com.zx.emanage.inve.vo.WmsInveTransaUserSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransauserService")
public class WmsInveTransaUserServiceImpl implements IWmsInveTransaUserService {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaUserServiceImpl.class);

	@Autowired
	private WmsInveTransaUserDao wmsinvetransauserDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveTransaUserSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvetransauserDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveTransaUserSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvetransauserDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvetransauserDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveTransaUser getInfoByPK(Integer wms_inve_transa_user_id) {
		return wmsinvetransauserDao.get(wms_inve_transa_user_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveTransaUser bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransauserDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveTransaUser bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransauserDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveTransaUser() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveTransaUser> getListByEntity(WmsInveTransaUser queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveTransaUser> beanList = wmsinvetransauserDao.getListByEntity(queryInfo);
		return beanList;
	}
}
