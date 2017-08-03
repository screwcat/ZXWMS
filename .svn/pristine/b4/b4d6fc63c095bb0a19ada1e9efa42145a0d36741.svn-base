package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineCarpinfoDao;
import com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineCarpinfoService;
import com.zx.emanage.sysmanage.service.IWmsSysDictDataService;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineCarpinfo;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineCarpinfoSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecustomerchangelinecarpinfoService")
public class WmsCreCustomerChangeLineCarpinfoServiceImpl implements IWmsCreCustomerChangeLineCarpinfoService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCustomerChangeLineCarpinfoServiceImpl.class);

	@Autowired
	private WmsCreCustomerChangeLineCarpinfoDao wmscrecustomerchangelinecarpinfoDao;
	
	@Autowired
	private IWmsSysDictDataService wmsSysDictDataService;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineCarpinfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_line_customer_change_head_id",
                     queryInfo.getWms_cre_credit_line_customer_change_head_id());
        paramMap.put("wms_cre_customer_change_line_carpinfo_id",
                     queryInfo.getWms_cre_customer_change_line_carpinfo_id());
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrecustomerchangelinecarpinfoDao.search(paramMap);
	    for(Map<String,Object> map: list) {
	    	map.put("vi_type_info_name", wmsSysDictDataService.getValMeaningBycodes(74, map.get("vi_type_info").toString()));
	    }
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreCustomerChangeLineCarpinfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrecustomerchangelinecarpinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecustomerchangelinecarpinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCustomerChangeLineCarpinfo getInfoByPK(Integer wms_cre_customer_change_line_carpinfo_id) {
		return wmscrecustomerchangelinecarpinfoDao.get(wms_cre_customer_change_line_carpinfo_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCustomerChangeLineCarpinfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecustomerchangelinecarpinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCustomerChangeLineCarpinfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecustomerchangelinecarpinfoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCustomerChangeLineCarpinfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCustomerChangeLineCarpinfo> getListByEntity(WmsCreCustomerChangeLineCarpinfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCustomerChangeLineCarpinfo> beanList = wmscrecustomerchangelinecarpinfoDao.getListByEntity(queryInfo);
		return beanList;
	}
}
