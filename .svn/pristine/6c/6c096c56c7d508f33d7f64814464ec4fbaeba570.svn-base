package com.zx.emanage.loanfina.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaTerminationContractAttDao;
import com.zx.emanage.loanfina.service.IWmsFinaTerminationContractAttService;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContractAtt;
import com.zx.emanage.loanfina.vo.WmsFinaTerminationContractAttSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinaterminationcontractattService")
public class WmsFinaTerminationContractAttServiceImpl implements IWmsFinaTerminationContractAttService {
	private static Logger log = LoggerFactory.getLogger(WmsFinaTerminationContractAttServiceImpl.class);

	@Autowired
	private WmsFinaTerminationContractAttDao wmsfinaterminationcontractattDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsFinaTerminationContractAttSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("wms_fina_termination_contract_id", queryInfo.getWms_fina_termination_contract_id());
	    List<Map<String,Object>>  list = wmsfinaterminationcontractattDao.searchforaffirm(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsFinaTerminationContractAttSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinaterminationcontractattDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinaterminationcontractattDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsFinaTerminationContractAtt getInfoByPK(Integer wms_fina_termination_contract_att_id) {
		return wmsfinaterminationcontractattDao.get(wms_fina_termination_contract_att_id);
	}

	@Override	
	@Transactional
	public String save(WmsFinaTerminationContractAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinaterminationcontractattDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsFinaTerminationContractAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinaterminationcontractattDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsFinaTerminationContractAtt() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsFinaTerminationContractAtt> getListByEntity(WmsFinaTerminationContractAtt queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsFinaTerminationContractAtt> beanList = wmsfinaterminationcontractattDao.getListByEntity(queryInfo);
		return beanList;
	}
}
