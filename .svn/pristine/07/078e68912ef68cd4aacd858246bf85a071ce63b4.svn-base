package com.zx.emanage.loanfina.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaCrePeriodRepayDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRealrepayInfoDao;
import com.zx.emanage.loanfina.service.IWmsFinaCreRealrepayInfoService;
import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.emanage.util.gen.entity.WmsFinaCreRealrepayInfo;
import com.zx.emanage.util.gen.entity.WmsPostRemindHistory;
import com.zx.emanage.loanfina.vo.WmsFinaCreRealrepayInfoSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinacrerealrepayinfoService")
public class WmsFinaCreRealrepayInfoServiceImpl implements IWmsFinaCreRealrepayInfoService {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreRealrepayInfoServiceImpl.class);

	@Autowired
	private WmsFinaCreRealrepayInfoDao wmsfinacrerealrepayinfoDao;
	@Autowired
	private WmsFinaCrePeriodRepayDao wmsFinaCrePeriodRepayDao;
	@Override
	public Map<String, Object> getListWithoutPaging(WmsFinaCreRealrepayInfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsfinacrerealrepayinfoDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsFinaCreRealrepayInfoSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinacrerealrepayinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacrerealrepayinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsFinaCreRealrepayInfo getInfoByPK(Integer wms_fina_cre_realrepay_info_id) {
		return wmsfinacrerealrepayinfoDao.get(wms_fina_cre_realrepay_info_id);
	}

	@Override	
	@Transactional
	public String save(WmsFinaCreRealrepayInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacrerealrepayinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsFinaCreRealrepayInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacrerealrepayinfoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsFinaCreRealrepayInfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsFinaCreRealrepayInfo> getListByEntity(WmsFinaCreRealrepayInfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsFinaCreRealrepayInfo> beanList = wmsfinacrerealrepayinfoDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :根据 wms_cre_credit_head_id 查询历史信息
	 * @param primary key 
	 * @return Map
	 * @author baisong
	 */	
	@Override
	public  Map<String, Object> getInfoByHK(Integer wms_cre_credit_head_id) {
		Map<String, Object> map=new HashMap<>();
		List<Map<String, Object>> list=wmsfinacrerealrepayinfoDao.getsum(wms_cre_credit_head_id);
		if(list==null){
			return map;
		}
		for(int i=0;i<list.size();i++){
			Map<String, Object> map1=list.get(i);
			map1.put("repay_no", i+1);
		}
		map.put("Rows", list);
		return map;
	}
	/**
	 * Description :根据 wms_cre_credit_head_id 求和 应还款表
	 * @param primary key 
	 * @return Map
	 * @author baisong
	 */	
	@Override
	public  Map<String, Object> getSumval(Integer wms_cre_credit_head_id) {
		Map<String, Object> map=new HashMap<>();
		map=wmsfinacrerealrepayinfoDao.getSumval(wms_cre_credit_head_id);
		return map;
	}
	@Override
	public Map<String, Object> wmsfinacrerealrepayInfoByNOw(
			WmsFinaCreRealrepayInfoSearchBeanVO queryInfo) {
		Map<String,Object>parameters = new HashMap<>();
		List<Map<String, Object>> list=new ArrayList<>();
		if((queryInfo.getWms_cre_credit_head_id()!=0&&queryInfo.getWms_cre_credit_head_id()!=null)&&(queryInfo.getRepay_no()!=0&&queryInfo.getRepay_no()!=null)){
			parameters.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
			parameters.put("repay_no", queryInfo.getRepay_no());
			list =wmsfinacrerealrepayinfoDao.searchByInfo(parameters);			
		}
		if(list==null||list.size()==0){
			return parameters;
		}
		return list.get(0);
	}
}
