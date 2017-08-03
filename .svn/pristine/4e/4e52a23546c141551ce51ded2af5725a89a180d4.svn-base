package com.zx.emanage.loanfina.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaCreRepaymentDetailsAttDao;
import com.zx.emanage.loanfina.service.IWmsFinaCreRepaymentDetailsAttService;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentDetailsAtt;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaymentDetailsAttSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinacrerepaymentdetailsattService")
public class WmsFinaCreRepaymentDetailsAttServiceImpl implements IWmsFinaCreRepaymentDetailsAttService {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreRepaymentDetailsAttServiceImpl.class);

	@Autowired
	private WmsFinaCreRepaymentDetailsAttDao wmsfinacrerepaymentdetailsattDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsFinaCreRepaymentDetailsAttSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsfinacrerepaymentdetailsattDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsFinaCreRepaymentDetailsAttSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinacrerepaymentdetailsattDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacrerepaymentdetailsattDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsFinaCreRepaymentDetailsAtt getInfoByPK(Integer wms_fina_cre_repayment_details_att_id) {
		return wmsfinacrerepaymentdetailsattDao.get(wms_fina_cre_repayment_details_att_id);
	}

	@Override	
	@Transactional
	public String save(WmsFinaCreRepaymentDetailsAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacrerepaymentdetailsattDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsFinaCreRepaymentDetailsAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacrerepaymentdetailsattDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsFinaCreRepaymentDetailsAtt() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsFinaCreRepaymentDetailsAtt> getListByEntity(WmsFinaCreRepaymentDetailsAtt queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsFinaCreRepaymentDetailsAtt> beanList = wmsfinacrerepaymentdetailsattDao.getListByEntity(queryInfo);
		return beanList;
	}
}
