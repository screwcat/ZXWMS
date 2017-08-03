package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCarpApproInfoDao;
import com.zx.emanage.cremanage.service.IWmsCreCarpApproInfoService;
import com.zx.emanage.util.gen.entity.WmsCreCarpApproInfo;
import com.zx.emanage.cremanage.vo.WmsCreCarpApproInfoSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecarpapproinfoService")
public class WmsCreCarpApproInfoServiceImpl implements IWmsCreCarpApproInfoService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpApproInfoServiceImpl.class);

	@Autowired
	private WmsCreCarpApproInfoDao wmscrecarpapproinfoDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCarpApproInfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrecarpapproinfoDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreCarpApproInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrecarpapproinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecarpapproinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCarpApproInfo getInfoByPK(Integer wms_cre_carp_appro_info_id) {
		return wmscrecarpapproinfoDao.get(wms_cre_carp_appro_info_id);
	}
	@Override
	public Map<String,Object> getInfoByHK(Integer wms_cre_credit_head_id,Integer carp_appro_type) {
		Map<String,Object> map=new HashMap();
		map.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
		map.put("carp_appro_type", carp_appro_type);
		return wmscrecarpapproinfoDao.getInfoByHK(map);
	}
	@Override	
	@Transactional
	public String save(WmsCreCarpApproInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarpapproinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCarpApproInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarpapproinfoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCarpApproInfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCarpApproInfo> getListByEntity(WmsCreCarpApproInfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCarpApproInfo> beanList = wmscrecarpapproinfoDao.getListByEntity(queryInfo);
		return beanList;
	}
}
