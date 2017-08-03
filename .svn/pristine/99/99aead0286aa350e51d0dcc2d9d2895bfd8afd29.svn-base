package com.zx.emanage.loanfina.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaTerminationContractMortgageListDao;
import com.zx.emanage.loanfina.service.IWmsFinaTerminationContractMortgageListService;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContractMortgageList;
import com.zx.emanage.loanfina.vo.WmsFinaTerminationContractMortgageListSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinaterminationcontractmortgagelistService")
public class WmsFinaTerminationContractMortgageListServiceImpl implements IWmsFinaTerminationContractMortgageListService {
	private static Logger log = LoggerFactory.getLogger(WmsFinaTerminationContractMortgageListServiceImpl.class);

	@Autowired
	private WmsFinaTerminationContractMortgageListDao wmsfinaterminationcontractmortgagelistDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsFinaTerminationContractMortgageListSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("wms_fina_termination_contract_id", queryInfo.getWms_fina_termination_contract_id());
	    List<Map<String,Object>>  list = wmsfinaterminationcontractmortgagelistDao.searchforaffirm(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsFinaTerminationContractMortgageListSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinaterminationcontractmortgagelistDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinaterminationcontractmortgagelistDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsFinaTerminationContractMortgageList getInfoByPK(Integer wms_fina_termination_contract_mortgage_list_id) {
		return wmsfinaterminationcontractmortgagelistDao.get(wms_fina_termination_contract_mortgage_list_id);
	}

	@Override	
	@Transactional
	public String save(WmsFinaTerminationContractMortgageList bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinaterminationcontractmortgagelistDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsFinaTerminationContractMortgageList bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinaterminationcontractmortgagelistDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsFinaTerminationContractMortgageList() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsFinaTerminationContractMortgageList> getListByEntity(WmsFinaTerminationContractMortgageList queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsFinaTerminationContractMortgageList> beanList = wmsfinaterminationcontractmortgagelistDao.getListByEntity(queryInfo);
		return beanList;
	}
}
