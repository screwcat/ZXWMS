package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionFloatingNewDao;
import com.zx.emanage.inve.service.IWmsInveCommissionFloatingNewService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionFloatingNew;
import com.zx.emanage.inve.vo.WmsInveCommissionFloatingNewSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionfloatingnewService")
public class WmsInveCommissionFloatingNewServiceImpl implements IWmsInveCommissionFloatingNewService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionFloatingNewServiceImpl.class);

	@Autowired
	private WmsInveCommissionFloatingNewDao wmsinvecommissionfloatingnewDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionFloatingNewSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionfloatingnewDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionFloatingNewSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissionfloatingnewDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionfloatingnewDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionFloatingNew getInfoByPK(Integer wms_inve_commission_floating_new_id) {
		return wmsinvecommissionfloatingnewDao.get(wms_inve_commission_floating_new_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionFloatingNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionfloatingnewDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionFloatingNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionfloatingnewDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionFloatingNew() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionFloatingNew> getListByEntity(WmsInveCommissionFloatingNew queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionFloatingNew> beanList = wmsinvecommissionfloatingnewDao.getListByEntity(queryInfo);
		return beanList;
	}
}
