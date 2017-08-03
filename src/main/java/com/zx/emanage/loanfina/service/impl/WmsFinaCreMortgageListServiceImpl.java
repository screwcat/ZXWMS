package com.zx.emanage.loanfina.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaCreMortgageListDao;
import com.zx.emanage.loanfina.service.IWmsFinaCreMortgageListService;
import com.zx.emanage.util.gen.entity.WmsFinaCreMortgageList;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContractMortgageList;
import com.zx.emanage.loanfina.vo.WmsFinaCreMortgageListSearchBeanVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinacremortgagelistService")
public class WmsFinaCreMortgageListServiceImpl implements IWmsFinaCreMortgageListService {
	private static Logger log = LoggerFactory.getLogger(WmsFinaCreMortgageListServiceImpl.class);

	@Autowired
	private WmsFinaCreMortgageListDao wmsfinacremortgagelistDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsFinaCreMortgageListSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsfinacremortgagelistDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsFinaCreMortgageListSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinacremortgagelistDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacremortgagelistDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsFinaCreMortgageList getInfoByPK(Integer wms_fina_cre_mortgage_list_id) {
		return wmsfinacremortgagelistDao.get(wms_fina_cre_mortgage_list_id);
	}

	@Override	
	@Transactional
	public String save(WmsFinaCreMortgageList bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacremortgagelistDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsFinaCreMortgageList bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinacremortgagelistDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	/**
	 * Description :根据提供的查询条件和必要条件查询符合条件的抵押物信息
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	@Override
	public Map<String, Object> getwmsfinacremortgagelistbySelect(
			WmsFinaCreMortgageListSearchBeanVO queryInfo) {
		Map<String,Object> paramMap = new HashMap<>();
		if(StringUtil.isNotBlank(queryInfo.getMortgage_name())){
			paramMap.put("mortgage_name", SysUtil.getSqlLikeParam(queryInfo.getMortgage_name()));
		}
		if(queryInfo.getMortgage_date_start()!=null){
			paramMap.put("mortgage_date_start", queryInfo.getMortgage_date_start());
		}
		if(queryInfo.getMortgage_date_start()!=null){
			paramMap.put("mortgage_date_end", queryInfo.getMortgage_date_end());
		}
		paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
		paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("pagesize", queryInfo.getPagesize());
	    paramMap.put("offset", queryInfo.getOffset());
	    List<Map<String,Object>> list = wmsfinacremortgagelistDao.getwmsfinacremortgagelistbySelect(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacremortgagelistDao.findFinacremortgagelistbyCount(paramMap),list);
		return bean.getGridData();			
	}
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsFinaCreMortgageList() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsFinaCreMortgageList> getListByEntity(WmsFinaCreMortgageList queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsFinaCreMortgageList> beanList = wmsfinacremortgagelistDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :根据对应条件来获取抵押物清单
	 * @param bean 
	 * @return Map<String,Object>
	 * @author baisong
	 */
	@Override
	public Map<String,Object> getListByEntity(
			WmsFinaCreMortgageListSearchBeanVO bean, UserBean user) {
		Map<String,Object> map=new HashMap<>();
		List<Map<String, Object>> list  =wmsfinacremortgagelistDao.getList(bean);
	    GridDataBean<Map<String,Object>> bean1 = new 
	                GridDataBean<Map<String,Object>>(bean.getPage(), wmsfinacremortgagelistDao.findnCount(bean),list);
		return bean1.getGridData();	
	}
	
	
	/**
	 * Description :正常还款确认 逾期还款确认中抵押物记录保存或更改
	 * @param bean 
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */
	@Override
	@Transactional
	public String saveUpdateMortgage(WmsFinaCreMortgageList bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		bean.setCreate_user_id(user.getUserId());//创建人ID
		bean.setCreate_user_name(user.getRealname());//创建人姓名
		bean.setLast_update_datetime(new Timestamp(System.currentTimeMillis()));//当前时间
		bean.setEnable_flag("1");//数据状态
		if(bean.getWms_fina_cre_mortgage_list_id()!=null&&!"".equals(bean.getWms_fina_cre_mortgage_list_id())){
			ret = wmsfinacremortgagelistDao.update(bean);
		}else{
			ret = wmsfinacremortgagelistDao.save(bean);
		}
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
}
