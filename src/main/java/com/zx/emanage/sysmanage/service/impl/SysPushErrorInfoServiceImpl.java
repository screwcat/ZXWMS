package com.zx.emanage.sysmanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.sysmanage.persist.SysPushErrorInfoDao;
import com.zx.emanage.sysmanage.service.ISysPushErrorInfoService;
import com.zx.emanage.util.gen.entity.SysPushErrorInfo;
import com.zx.emanage.sysmanage.vo.SysPushErrorInfoSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("syspusherrorinfoService")
public class SysPushErrorInfoServiceImpl implements ISysPushErrorInfoService {
	private static Logger log = LoggerFactory.getLogger(SysPushErrorInfoServiceImpl.class);

	@Autowired
	private SysPushErrorInfoDao syspusherrorinfoDao;

	@Override
	public Map<String, Object> getListWithoutPaging(SysPushErrorInfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = syspusherrorinfoDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(SysPushErrorInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = syspusherrorinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), syspusherrorinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public SysPushErrorInfo getInfoByPK(Integer sys_push_error_info_id) {
		return syspusherrorinfoDao.get(sys_push_error_info_id);
	}

	@Override	
	@Transactional
	public String save(SysPushErrorInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = syspusherrorinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(SysPushErrorInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = syspusherrorinfoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new SysPushErrorInfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<SysPushErrorInfo> getListByEntity(SysPushErrorInfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<SysPushErrorInfo> beanList = syspusherrorinfoDao.getListByEntity(queryInfo);
		return beanList;
	}
}
