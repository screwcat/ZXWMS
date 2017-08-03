package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveApprovalProcessDao;
import com.zx.emanage.inve.service.IWmsInveApprovalProcessService;
import com.zx.emanage.util.gen.entity.WmsInveApprovalProcess;
import com.zx.emanage.inve.vo.WmsInveApprovalProcessSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveapprovalprocessService")
public class WmsInveApprovalProcessServiceImpl implements IWmsInveApprovalProcessService {
	private static Logger log = LoggerFactory.getLogger(WmsInveApprovalProcessServiceImpl.class);

	@Autowired
	private WmsInveApprovalProcessDao wmsinveapprovalprocessDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveApprovalProcessSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("data_id", queryInfo.getData_id());
	    paramMap.put("data_type", queryInfo.getData_type());
	    List<Map<String,Object>>  list = wmsinveapprovalprocessDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveApprovalProcessSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinveapprovalprocessDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinveapprovalprocessDao.findCount(paramMap),list);
		return bean.getGridData();			
	}


	@Override	
	@Transactional
	public String save(WmsInveApprovalProcess bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinveapprovalprocessDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveApprovalProcess bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveApprovalProcess() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveApprovalProcess> getListByEntity(WmsInveApprovalProcess queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveApprovalProcess> beanList = wmsinveapprovalprocessDao.getListByEntity(queryInfo);
		return beanList;
	}
}
