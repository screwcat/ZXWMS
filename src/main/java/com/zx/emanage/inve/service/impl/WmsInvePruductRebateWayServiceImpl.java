package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePruductRebateWayDao;
import com.zx.emanage.inve.service.IWmsInvePruductRebateWayService;
import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
import com.zx.emanage.inve.vo.WmsInvePruductRebateWaySearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvepruductrebatewayService")
public class WmsInvePruductRebateWayServiceImpl implements IWmsInvePruductRebateWayService {
	private static Logger log = LoggerFactory.getLogger(WmsInvePruductRebateWayServiceImpl.class);

	@Autowired
	private WmsInvePruductRebateWayDao wmsinvepruductrebatewayDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInvePruductRebateWaySearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("wms_inve_pruduct_category_id", queryInfo.getWms_inve_pruduct_category_id());
	    paramMap.put("category_rebate_way", queryInfo.getCategory_rebate_way());//返利方式
	    List<Map<String,Object>>  list = wmsinvepruductrebatewayDao.searchforcp(paramMap);
	    if(list.size()==0){
	    	Map<String, Object> resp = new HashMap<String, Object>();
	    	resp.put("customer_stock_begin", "");
	    	resp.put("customer_stock_end", "");
	    	resp.put("full_month", "");
	    	resp.put("bonus_rate", "");
	    	list.add(resp);
	    }
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInvePruductRebateWaySearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvepruductrebatewayDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvepruductrebatewayDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInvePruductRebateWay getInfoByPK(Integer wms_inve_pruduct_rebate_way_id) {
		return wmsinvepruductrebatewayDao.get(wms_inve_pruduct_rebate_way_id);
	}

	@Override	
	@Transactional
	public String save(WmsInvePruductRebateWay bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvepruductrebatewayDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInvePruductRebateWay bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvepruductrebatewayDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInvePruductRebateWay() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInvePruductRebateWay> getListByEntity(WmsInvePruductRebateWay queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInvePruductRebateWay> beanList = wmsinvepruductrebatewayDao.getListByEntity(queryInfo);
		return beanList;
	}
}
