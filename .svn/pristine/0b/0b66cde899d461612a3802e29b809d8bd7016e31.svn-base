package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveTransaAttDao;
import com.zx.emanage.inve.service.IWmsInveTransaAttService;
import com.zx.emanage.util.gen.entity.WmsInveTransaAtt;
import com.zx.emanage.inve.vo.WmsInveTransaAttSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransaattService")
public class WmsInveTransaAttServiceImpl implements IWmsInveTransaAttService {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaAttServiceImpl.class);

	@Autowired
	private WmsInveTransaAttDao wmsinvetransaattDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveTransaAttSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	 	paramMap.put("wms_inve_transa_id", queryInfo.getWms_inve_transa_id());
	 	paramMap.put("data_type_name", queryInfo.getData_type_name());
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvetransaattDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveTransaAttSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvetransaattDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvetransaattDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveTransaAtt getInfoByPK(Integer wms_inve_transa_att_id) {
		return wmsinvetransaattDao.get(wms_inve_transa_att_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveTransaAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransaattDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveTransaAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransaattDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveTransaAtt() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveTransaAtt> getListByEntity(WmsInveTransaAtt queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveTransaAtt> beanList = wmsinvetransaattDao.getListByEntity(queryInfo);
		return beanList;
	}
	//根据wms_inve_transa_prod_id 查询附件信息
	@Override
	public Map<String, Object> getListPaging(String wms_inve_transa_prod_id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sortorder", "");
	    paramMap.put("offset", "");
	    paramMap.put("wms_inve_transa_prod_id",wms_inve_transa_prod_id);
	      
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("Rows", wmsinvetransaattDao.search(paramMap));
	      
		return resultMap;
	}
}
