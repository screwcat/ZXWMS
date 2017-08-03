package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveTransaCusConfirmDao;
import com.zx.emanage.inve.service.IWmsInveTransaCusConfirmService;
import com.zx.emanage.util.gen.entity.WmsInveTransaCusConfirm;
import com.zx.emanage.inve.vo.WmsInveTransaCusConfirmSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransacusconfirmService")
public class WmsInveTransaCusConfirmServiceImpl implements IWmsInveTransaCusConfirmService {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaCusConfirmServiceImpl.class);

	@Autowired
	private WmsInveTransaCusConfirmDao wmsinvetransacusconfirmDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveTransaCusConfirmSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvetransacusconfirmDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveTransaCusConfirmSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvetransacusconfirmDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvetransacusconfirmDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveTransaCusConfirm getInfoByPK(Integer wms_inve_transa_cus_confirm_id) {
		return wmsinvetransacusconfirmDao.get(wms_inve_transa_cus_confirm_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveTransaCusConfirm bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransacusconfirmDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveTransaCusConfirm bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransacusconfirmDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveTransaCusConfirm() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveTransaCusConfirm> getListByEntity(WmsInveTransaCusConfirm queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveTransaCusConfirm> beanList = wmsinvetransacusconfirmDao.getListByEntity(queryInfo);
		return beanList;
	}
}
