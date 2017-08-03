package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreHousingCreditConfirmDao;
import com.zx.emanage.cremanage.service.IWmsCreHousingCreditConfirmService;
import com.zx.emanage.util.gen.entity.WmsCreHousingCreditConfirm;
import com.zx.emanage.cremanage.vo.WmsCreHousingCreditConfirmSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrehousingcreditconfirmService")
public class WmsCreHousingCreditConfirmServiceImpl implements IWmsCreHousingCreditConfirmService {
	private static Logger log = LoggerFactory.getLogger(WmsCreHousingCreditConfirmServiceImpl.class);

	@Autowired
	private WmsCreHousingCreditConfirmDao wmscrehousingcreditconfirmDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreHousingCreditConfirmSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrehousingcreditconfirmDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreHousingCreditConfirmSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrehousingcreditconfirmDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrehousingcreditconfirmDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreHousingCreditConfirm getInfoByPK(Integer wms_cre_housing_credit_confirm_id) {
		return wmscrehousingcreditconfirmDao.get(wms_cre_housing_credit_confirm_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreHousingCreditConfirm bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrehousingcreditconfirmDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreHousingCreditConfirm bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrehousingcreditconfirmDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreHousingCreditConfirm() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreHousingCreditConfirm> getListByEntity(WmsCreHousingCreditConfirm queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreHousingCreditConfirm> beanList = wmscrehousingcreditconfirmDao.getListByEntity(queryInfo);
		return beanList;
	}
}
