package com.zx.emanage.cusmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cusmanage.persist.WmsCusCustomerLineCarpinfoDao;
import com.zx.emanage.cusmanage.service.IWmsCusCustomerLineCarpinfoService;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineCarpinfoSearchBeanVO;
import com.zx.emanage.sysmanage.service.IWmsSysDictDataService;
import com.zx.emanage.util.gen.entity.WmsCusCustomerLineCarpinfo;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscuscustomerlinecarpinfoService")
public class WmsCusCustomerLineCarpinfoServiceImpl implements IWmsCusCustomerLineCarpinfoService {
	private static Logger log = LoggerFactory.getLogger(WmsCusCustomerLineCarpinfoServiceImpl.class);

	@Autowired
	private WmsCusCustomerLineCarpinfoDao wmscuscustomerlinecarpinfoDao;
	@Autowired
	private IWmsSysDictDataService wmsSysDictDataService;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCusCustomerLineCarpinfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	 	if (queryInfo.getWms_cus_customer_id() != null) {
	 		paramMap.put("wms_cus_customer_id", queryInfo.getWms_cus_customer_id());
        }
        if (queryInfo.getWms_cus_customer_line_carpinfo_id() != null) {
        	paramMap.put("wms_cus_customer_line_carpinfo_id", queryInfo.getWms_cus_customer_line_carpinfo_id());
        }
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscuscustomerlinecarpinfoDao.search(paramMap);
	    for(Map<String,Object> map: list) {
	    	map.put("vi_type_info_name", wmsSysDictDataService.getValMeaningBycodes(74, map.get("vi_type_info").toString()));
	    }
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCusCustomerLineCarpinfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscuscustomerlinecarpinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscuscustomerlinecarpinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCusCustomerLineCarpinfo getInfoByPK(Integer wms_cus_customer_line_carpinfo_id) {
		return wmscuscustomerlinecarpinfoDao.get(wms_cus_customer_line_carpinfo_id);
	}

	@Override	
	@Transactional
	public String save(WmsCusCustomerLineCarpinfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscuscustomerlinecarpinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCusCustomerLineCarpinfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscuscustomerlinecarpinfoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCusCustomerLineCarpinfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCusCustomerLineCarpinfo> getListByEntity(WmsCusCustomerLineCarpinfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCusCustomerLineCarpinfo> beanList = wmscuscustomerlinecarpinfoDao.getListByEntity(queryInfo);
		return beanList;
	}
}
