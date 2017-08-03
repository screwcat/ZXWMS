package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCustomerWxLogDao;
import com.zx.emanage.inve.service.IWmsInveCustomerWxLogService;
import com.zx.emanage.util.gen.entity.WmsInveCustomerWx;
import com.zx.emanage.util.gen.entity.WmsInveCustomerWxLog;
import com.zx.emanage.inve.vo.WmsInveCustomerWxLogSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecustomerwxlogService")
public class WmsInveCustomerWxLogServiceImpl implements IWmsInveCustomerWxLogService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCustomerWxLogServiceImpl.class);

	@Autowired
	private WmsInveCustomerWxLogDao wmsinvecustomerwxlogDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCustomerWxLogSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecustomerwxlogDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCustomerWxLogSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecustomerwxlogDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecustomerwxlogDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCustomerWxLog getInfoByPK(Integer wms_inve_customer_wx_log_id) {
		return wmsinvecustomerwxlogDao.get(wms_inve_customer_wx_log_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCustomerWxLog bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecustomerwxlogDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCustomerWxLog bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecustomerwxlogDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCustomerWxLog() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCustomerWxLog> getListByEntity(WmsInveCustomerWxLog queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCustomerWxLog> beanList = wmsinvecustomerwxlogDao.getListByEntity(queryInfo);
		return beanList;
	}
	
}
