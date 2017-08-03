package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreHousingApplyAttDao;
import com.zx.emanage.cremanage.service.IWmsCreHousingApplyAttService;
import com.zx.emanage.util.gen.entity.WmsCreHousingApplyAtt;
import com.zx.emanage.cremanage.vo.WmsCreHousingApplyAttSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrehousingapplyattService")
public class WmsCreHousingApplyAttServiceImpl implements IWmsCreHousingApplyAttService {
	private static Logger log = LoggerFactory.getLogger(WmsCreHousingApplyAttServiceImpl.class);

	@Autowired
	private WmsCreHousingApplyAttDao wmscrehousingapplyattDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreHousingApplyAtt queryInfo) 
	{
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
	    paramMap.put("data_type_name", queryInfo.getData_type_name());
	    List<Map<String,Object>>  list = wmscrehousingapplyattDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreHousingApplyAttSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrehousingapplyattDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrehousingapplyattDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreHousingApplyAtt getInfoByPK(Integer wms_cre_housing_apply_att_id) {
		return wmscrehousingapplyattDao.get(wms_cre_housing_apply_att_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreHousingApplyAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrehousingapplyattDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreHousingApplyAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrehousingapplyattDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreHousingApplyAtt() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreHousingApplyAtt> getListByEntity(WmsCreHousingApplyAtt queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreHousingApplyAtt> beanList = wmscrehousingapplyattDao.getListByEntity(queryInfo);
		return beanList;
	}
}
