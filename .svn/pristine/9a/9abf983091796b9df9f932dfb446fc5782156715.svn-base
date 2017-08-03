package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCarpHousingCustomerInfoDao;
import com.zx.emanage.cremanage.service.IWmsCreCarpHousingCustomerInfoService;
import com.zx.emanage.util.gen.entity.WmsCreCarpHousingCustomerInfo;
import com.zx.emanage.cremanage.vo.WmsCreCarpHousingCustomerInfoSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecarphousingcustomerinfoService")
public class WmsCreCarpHousingCustomerInfoServiceImpl implements IWmsCreCarpHousingCustomerInfoService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpHousingCustomerInfoServiceImpl.class);

	@Autowired
	private WmsCreCarpHousingCustomerInfoDao wmscrecarphousingcustomerinfoDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCarpHousingCustomerInfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrecarphousingcustomerinfoDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreCarpHousingCustomerInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrecarphousingcustomerinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecarphousingcustomerinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCarpHousingCustomerInfo getInfoByPK(Integer wms_cre_carp_housing_customer_info_id) {
		return wmscrecarphousingcustomerinfoDao.get(wms_cre_carp_housing_customer_info_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCarpHousingCustomerInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarphousingcustomerinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCarpHousingCustomerInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarphousingcustomerinfoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCarpHousingCustomerInfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCarpHousingCustomerInfo> getListByEntity(WmsCreCarpHousingCustomerInfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCarpHousingCustomerInfo> beanList = wmscrecarphousingcustomerinfoDao.getListByEntity(queryInfo);
		return beanList;
	}

	/**
	 * 抵押车产信息
	 */
	public Map<String, Object> getCarHInfoListWithoutPagingByMccid(WmsCreCarpHousingCustomerInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        List<Map<String, Object>> list = wmscrecarphousingcustomerinfoDao.searchCarHBYMccid(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
	}
}
