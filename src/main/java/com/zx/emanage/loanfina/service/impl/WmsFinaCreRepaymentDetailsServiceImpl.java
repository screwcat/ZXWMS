package com.zx.emanage.loanfina.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaCreRepaymentDetailsAttDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepaymentDetailsDao;
import com.zx.emanage.loanfina.service.IWmsFinaCreRepaymentDetailsService;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaymentDetailsSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentDetails;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentDetailsAtt;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinacrerepaymentdetailsService")
public class WmsFinaCreRepaymentDetailsServiceImpl implements IWmsFinaCreRepaymentDetailsService {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreRepaymentDetailsServiceImpl.class);

	@Autowired
	private WmsFinaCreRepaymentDetailsDao wmsfinacrerepaymentdetailsDao;
	@Autowired
	private WmsFinaCreRepaymentDetailsAttDao wmsFinaCreRepaymentDetailsAttDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsFinaCreRepaymentDetailsSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsfinacrerepaymentdetailsDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
	
	@Override
	public Map<String, Object> getListWithPaging(WmsFinaCreRepaymentDetailsSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinacrerepaymentdetailsDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacrerepaymentdetailsDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsFinaCreRepaymentDetails getInfoByPK(Integer wms_fina_cre_repayment_details_id) {
		return wmsfinacrerepaymentdetailsDao.get(wms_fina_cre_repayment_details_id);
	}

	@Override	
	@Transactional
	public String save(WmsFinaCreRepaymentDetails bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacrerepaymentdetailsDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsFinaCreRepaymentDetails bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacrerepaymentdetailsDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsFinaCreRepaymentDetails() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsFinaCreRepaymentDetails> getListByEntity(WmsFinaCreRepaymentDetails queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsFinaCreRepaymentDetails> beanList = wmsfinacrerepaymentdetailsDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :通过贷款表主键获取期还款明细表数据
	 * @param wms_cre_credit_head_id
	 * @return list
	 * @author hancd
	 */
	@Override
	public Map<String,Object> getListBySearchforDetails(WmsFinaCreRepaymentDetailsSearchBeanVO queryInfo) {
		Map<String,Object> paramMap = new HashMap<>();
		if(StringUtil.isNotBlank(queryInfo.getRepayment_date_begin())){
			paramMap.put("repayment_date_begin", queryInfo.getRepayment_date_begin());
		}
		if(StringUtil.isNotBlank(queryInfo.getRepayment_date_end())){
			paramMap.put("repayment_date_end", queryInfo.getRepayment_date_end());
		}
		paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
		paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("pagesize", queryInfo.getPagesize());
	    paramMap.put("offset", queryInfo.getOffset());
		List<Map<String,Object>> list =wmsfinacrerepaymentdetailsDao.getListBySearchforDetails(paramMap);
		//附件信息查询
		for(Map<String,Object> map : list) {
			List<Map<String,Object>> listAtt =  wmsFinaCreRepaymentDetailsAttDao.getWmsFinaCreRepaymentDetailsAtt((Integer) map.get("wms_fina_cre_repayment_details_id"));
			map.put("listAtt", listAtt);
		}
		GridDataBean<Map<String,Object>> bean = new 
	                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacrerepaymentdetailsDao.getListBySearchforDetailsCounts(paramMap),list);
			return bean.getGridData();			
	}
	
}
