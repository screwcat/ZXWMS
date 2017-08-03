package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCreditEmailDao;
import com.zx.emanage.inve.service.IWmsInveCreditEmailService;
import com.zx.emanage.util.gen.entity.WmsInveCreditEmail;
import com.zx.emanage.inve.vo.WmsInveCreditEmailSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecreditemailService")
public class WmsInveCreditEmailServiceImpl implements IWmsInveCreditEmailService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCreditEmailServiceImpl.class);

	@Autowired
	private WmsInveCreditEmailDao wmsinvecreditemailDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCreditEmailSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecreditemailDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCreditEmailSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecreditemailDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecreditemailDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCreditEmail getInfoByPK(Integer wms_inve_credit_email_id) {
		return wmsinvecreditemailDao.get(wms_inve_credit_email_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCreditEmail bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditemailDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCreditEmail bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecreditemailDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCreditEmail() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCreditEmail> getListByEntity(WmsInveCreditEmail queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCreditEmail> beanList = wmsinvecreditemailDao.getListByEntity(queryInfo);
		return beanList;
	}
}
