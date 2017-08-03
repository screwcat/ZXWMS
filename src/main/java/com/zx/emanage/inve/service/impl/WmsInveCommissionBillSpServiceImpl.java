package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionBillSpDao;
import com.zx.emanage.inve.service.IWmsInveCommissionBillSpService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionBillSp;
import com.zx.emanage.inve.vo.WmsInveCommissionBillSpSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionbillspService")
public class WmsInveCommissionBillSpServiceImpl implements IWmsInveCommissionBillSpService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionBillSpServiceImpl.class);

	@Autowired
	private WmsInveCommissionBillSpDao wmsinvecommissionbillspDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionBillSpSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionbillspDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionBillSpSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissionbillspDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionbillspDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionBillSp getInfoByPK(Integer wms_inve_commission_bill_sp_id) {
		return wmsinvecommissionbillspDao.get(wms_inve_commission_bill_sp_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionBillSp bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		//查询是否存在数据。如果存在更新，不存在添加
		if(bean.getWms_inve_commission_bill_sp_id() != null) {
			ret = wmsinvecommissionbillspDao.update(bean);
		} else {
			ret = wmsinvecommissionbillspDao.save(bean);
		}
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionBillSp bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionbillspDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionBillSp() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionBillSp> getListByEntity(WmsInveCommissionBillSp queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionBillSp> beanList = wmsinvecommissionbillspDao.getListByEntity(queryInfo);
		return beanList;
	}
}
