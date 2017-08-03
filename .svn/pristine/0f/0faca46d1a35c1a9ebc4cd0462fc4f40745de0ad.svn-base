package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCarpCarsCustomerInfoDao;
import com.zx.emanage.cremanage.service.IWmsCreCarpCarsCustomerInfoService;
import com.zx.emanage.sysmanage.service.IWmsSysDictDataService;
import com.zx.emanage.util.gen.entity.WmsCreCarpCarsCustomerInfo;
import com.zx.emanage.cremanage.vo.WmsCreCarpCarsCustomerInfoSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreCarpHousingCustomerInfoSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecarpcarscustomerinfoService")
public class WmsCreCarpCarsCustomerInfoServiceImpl implements IWmsCreCarpCarsCustomerInfoService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpCarsCustomerInfoServiceImpl.class);

	@Autowired
	private WmsCreCarpCarsCustomerInfoDao wmscrecarpcarscustomerinfoDao;
	@Autowired
	private IWmsSysDictDataService iWmsSysDictDataService;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCarpCarsCustomerInfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrecarpcarscustomerinfoDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreCarpCarsCustomerInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrecarpcarscustomerinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecarpcarscustomerinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCarpCarsCustomerInfo getInfoByPK(Integer wms_cre_carp_cars_customer_info_id) {
		return wmscrecarpcarscustomerinfoDao.get(wms_cre_carp_cars_customer_info_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCarpCarsCustomerInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarpcarscustomerinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCarpCarsCustomerInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarpcarscustomerinfoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCarpCarsCustomerInfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCarpCarsCustomerInfo> getListByEntity(WmsCreCarpCarsCustomerInfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCarpCarsCustomerInfo> beanList = wmscrecarpcarscustomerinfoDao.getListByEntity(queryInfo);
		return beanList;
	}
	@Override
	public Map<String, Object> getList(WmsCreCarpCarsCustomerInfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
	    Map<String,Object>  map = wmscrecarpcarscustomerinfoDao.getInfo(paramMap);
	    if(map.get("vi_type_info")!=null){
		    map.put("vi_type_info_val", map.get("vi_type_info").toString());//转换保险信息
			map.put("vi_type_info", iWmsSysDictDataService.getValMeaningBycodes(74, map.get("vi_type_info").toString()));//转换保险信息
	    }
		return map;		
	}

	@Override
	public Map<String, Object> getCarHInfoListWithoutPagingByMccid(WmsCreCarpHousingCustomerInfoSearchBeanVO queryInfo) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        List<Map<String, Object>> list = wmscrecarpcarscustomerinfoDao.searchCarCBYMccid(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
	}

	@Override
	public Integer searchCarInfoExistCount(String wms_cus_customer_head_id, String vehicle_idn_num) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cus_customer_head_id", wms_cus_customer_head_id);
        paramMap.put("vehicle_idn_num", vehicle_idn_num);
		return wmscrecarpcarscustomerinfoDao.searchCarInfoExistCount(paramMap);
	}
}
