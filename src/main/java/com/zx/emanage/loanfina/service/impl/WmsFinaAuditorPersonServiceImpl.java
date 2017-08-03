package com.zx.emanage.loanfina.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaAuditorPersonDao;
import com.zx.emanage.loanfina.service.IWmsFinaAuditorPersonService;
import com.zx.emanage.util.gen.entity.WmsFinaAuditorPerson;
import com.zx.emanage.loanfina.vo.WmsFinaAuditorPersonSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinaauditorpersonService")
public class WmsFinaAuditorPersonServiceImpl implements IWmsFinaAuditorPersonService {
	private static Logger log = LoggerFactory.getLogger(WmsFinaAuditorPersonServiceImpl.class);

	@Autowired
	private WmsFinaAuditorPersonDao wmsfinaauditorpersonDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsFinaAuditorPersonSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsfinaauditorpersonDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsFinaAuditorPersonSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinaauditorpersonDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinaauditorpersonDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsFinaAuditorPerson getInfoByPK(Integer auditor_person_id) {
		return wmsfinaauditorpersonDao.get(auditor_person_id);
	}

	@Override	
	@Transactional
	public String save(WmsFinaAuditorPerson bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinaauditorpersonDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsFinaAuditorPerson bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinaauditorpersonDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	public List<Map<String, Object>> getListByEntity(
			WmsFinaAuditorPerson queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<Map<String, Object>> beanList = wmsfinaauditorpersonDao.getList(queryInfo);
		return beanList;
	}	
}
