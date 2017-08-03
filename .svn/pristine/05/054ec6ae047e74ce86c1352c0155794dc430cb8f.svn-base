package com.zx.emanage.loanappro.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanappro.persist.WmsCreApproProtocolSecuredDao;
import com.zx.emanage.loanappro.service.IWmsCreApproProtocolSecuredService;
import com.zx.emanage.util.gen.entity.WmsCreApproProtocolSecured;
import com.zx.emanage.loanappro.vo.WmsCreApproProtocolSecuredSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscreapproprotocolsecuredService")
public class WmsCreApproProtocolSecuredServiceImpl implements IWmsCreApproProtocolSecuredService {
	private static Logger log = LoggerFactory.getLogger(WmsCreApproProtocolSecuredServiceImpl.class);

	@Autowired
	private WmsCreApproProtocolSecuredDao wmscreapproprotocolsecuredDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreApproProtocolSecuredSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscreapproprotocolsecuredDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreApproProtocolSecuredSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscreapproprotocolsecuredDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscreapproprotocolsecuredDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreApproProtocolSecured getInfoByPK(Integer wms_cre_appro_protocol_secured_id) {
		return wmscreapproprotocolsecuredDao.get(wms_cre_appro_protocol_secured_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreApproProtocolSecured bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscreapproprotocolsecuredDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreApproProtocolSecured bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscreapproprotocolsecuredDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreApproProtocolSecured() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreApproProtocolSecured> getListByEntity(WmsCreApproProtocolSecured queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreApproProtocolSecured> beanList = wmscreapproprotocolsecuredDao.getListByEntity(queryInfo);
		return beanList;
	}
}
