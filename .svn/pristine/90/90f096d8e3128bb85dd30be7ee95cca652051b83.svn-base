package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCreditSplitHeadDao;
import com.zx.emanage.inve.service.IWmsInveCreditSplitHeadService;
import com.zx.emanage.util.gen.entity.WmsInveCreditSplitHead;
import com.zx.emanage.inve.vo.WmsInveCreditSplitHeadSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecreditsplitheadService")
public class WmsInveCreditSplitHeadServiceImpl implements IWmsInveCreditSplitHeadService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCreditSplitHeadServiceImpl.class);

	@Autowired
	private WmsInveCreditSplitHeadDao wmsinvecreditsplitheadDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCreditSplitHeadSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecreditsplitheadDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCreditSplitHeadSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecreditsplitheadDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecreditsplitheadDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCreditSplitHead getInfoByPK(Integer wms_inve_credit_split_head_id) {
		return wmsinvecreditsplitheadDao.get(wms_inve_credit_split_head_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCreditSplitHead bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditsplitheadDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCreditSplitHead bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditsplitheadDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCreditSplitHead() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCreditSplitHead> getListByEntity(WmsInveCreditSplitHead queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCreditSplitHead> beanList = wmsinvecreditsplitheadDao.getListByEntity(queryInfo);
		return beanList;
	}
}
