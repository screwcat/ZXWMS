package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveProtocolRenewalLogDao;
import com.zx.emanage.inve.service.IWmsInveProtocolRenewalLogService;
import com.zx.emanage.util.gen.entity.WmsInveProtocolRenewalLog;
import com.zx.emanage.inve.vo.WmsInveProtocolRenewalLogSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveprotocolrenewallogService")
public class WmsInveProtocolRenewalLogServiceImpl implements IWmsInveProtocolRenewalLogService {
	private static Logger log = LoggerFactory.getLogger(WmsInveProtocolRenewalLogServiceImpl.class);

	@Autowired
	private WmsInveProtocolRenewalLogDao wmsinveprotocolrenewallogDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveProtocolRenewalLogSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinveprotocolrenewallogDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveProtocolRenewalLogSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinveprotocolrenewallogDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinveprotocolrenewallogDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveProtocolRenewalLog getInfoByPK(Integer wms_inve_protocol_renewal_log_id) {
		return wmsinveprotocolrenewallogDao.get(wms_inve_protocol_renewal_log_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveProtocolRenewalLog bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveprotocolrenewallogDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveProtocolRenewalLog bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveprotocolrenewallogDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveProtocolRenewalLog() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveProtocolRenewalLog> getListByEntity(WmsInveProtocolRenewalLog queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveProtocolRenewalLog> beanList = wmsinveprotocolrenewallogDao.getListByEntity(queryInfo);
		return beanList;
	}
}
