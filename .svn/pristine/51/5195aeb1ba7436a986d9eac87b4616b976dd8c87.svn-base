package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditReturnSearchDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditReturnSearchService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditReturnSearch;
import com.zx.emanage.cremanage.vo.WmsCreCreditReturnSearchSearchBeanVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditreturnsearchService")
public class WmsCreCreditReturnSearchServiceImpl implements IWmsCreCreditReturnSearchService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditReturnSearchServiceImpl.class);

	@Autowired
	private WmsCreCreditReturnSearchDao wmscrecreditreturnsearchDao;

	/**
	 * 退件列表Excel导出
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreCreditReturnSearchSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	 	if(StringUtil.isNotBlank(queryInfo.getBill_code())) {
	 		paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getSalesman_name())) {
	 		paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getCreate_user_name())) {
	 		paramMap.put("create_user_name", SysTools.getSqlLikeParam(queryInfo.getCreate_user_name()));
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getHead_create_datetime_start())) {
	 		paramMap.put("head_create_datetime_start", queryInfo.getHead_create_datetime_start());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getHead_create_datetime_end())) {
	 		paramMap.put("head_create_datetime_end", queryInfo.getHead_create_datetime_end());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getAudit_time_start())) {
	 		paramMap.put("audit_time_start", queryInfo.getAudit_time_start());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getAudit_time_end())) {
	 		paramMap.put("audit_time_end", queryInfo.getAudit_time_end());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getDept_city_sel())) {
	 		paramMap.put("dept_city_sel", queryInfo.getDept_city_sel());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getDept_name_sel())) {
	 		paramMap.put("dept_name_sel", queryInfo.getDept_name_sel());
	 	}
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrecreditreturnsearchDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
	/**
	 * 实现退件查询功能的列表显示功能
	 * @author lvtu
	 */
	@Override
	public Map<String, Object> getListWithPaging(WmsCreCreditReturnSearchSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(StringUtil.isNotBlank(queryInfo.getBill_code())) {
	 		paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getSalesman_name())) {
	 		paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getCreate_user_name())) {
	 		paramMap.put("create_user_name", SysTools.getSqlLikeParam(queryInfo.getCreate_user_name()));
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getHead_create_datetime_start())) {
	 		paramMap.put("head_create_datetime_start", queryInfo.getHead_create_datetime_start());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getHead_create_datetime_end())) {
	 		paramMap.put("head_create_datetime_end", queryInfo.getHead_create_datetime_end());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getAudit_time_start())) {
	 		paramMap.put("audit_time_start", queryInfo.getAudit_time_start());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getAudit_time_end())) {
	 		paramMap.put("audit_time_end", queryInfo.getAudit_time_end());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getDept_city_sel())) {
	 		paramMap.put("dept_city_sel", queryInfo.getDept_city_sel());
	 	}
	 	if(StringUtil.isNotBlank(queryInfo.getDept_name_sel())) {
	 		paramMap.put("dept_name_sel", queryInfo.getDept_name_sel());
	 	}
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrecreditreturnsearchDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecreditreturnsearchDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCreditReturnSearch getInfoByPK(Integer wms_cre_credit_return_search_id) {
		return wmscrecreditreturnsearchDao.get(wms_cre_credit_return_search_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCreditReturnSearch bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecreditreturnsearchDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCreditReturnSearch bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecreditreturnsearchDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCreditReturnSearch() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCreditReturnSearch> getListByEntity(WmsCreCreditReturnSearch queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCreditReturnSearch> beanList = wmscrecreditreturnsearchDao.getListByEntity(queryInfo);
		return beanList;
	}
}
