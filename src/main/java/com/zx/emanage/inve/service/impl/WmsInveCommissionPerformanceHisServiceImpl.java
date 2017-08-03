package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionPerformanceHisDao;
import com.zx.emanage.inve.service.IWmsInveCommissionPerformanceHisService;
import com.zx.emanage.inve.vo.WmsInveCommissionPerformanceHisSearchBeanVO;
import com.zx.emanage.util.gen.entity.PmPersonnelOtherinfo;
import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformanceHis;
import com.zx.emanage.util.gen.entity.WmsInveTransaAuth;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionperformancehisService")
public class WmsInveCommissionPerformanceHisServiceImpl implements
		IWmsInveCommissionPerformanceHisService {
	private static Logger log = LoggerFactory
			.getLogger(WmsInveCommissionPerformanceHisServiceImpl.class);

	@Autowired
	private WmsInveCommissionPerformanceHisDao wmsinvecommissionperformancehisDao;

	@Override
	public Map<String, Object> getListWithoutPaging(
			WmsInveCommissionPerformanceHisSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		List<Map<String, Object>> list = wmsinvecommissionperformancehisDao
				.search(paramMap);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows", list);
		return resMap;
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionPerformanceHisSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        if(StringUtil.isNotBlank(queryInfo.getQry_type())){
        	paramMap.put("qry_type",queryInfo.getQry_type());
        }
        if(StringUtil.isNotBlank(queryInfo.getSalesman_id())){
        	paramMap.put("salesman_id",queryInfo.getSalesman_id());
        }
        if(StringUtil.isNotBlank(queryInfo.getDept_id())){
        	paramMap.put("dept_id",queryInfo.getDept_id());
        }
        if(StringUtil.isNotBlank(queryInfo.getStatics_month())){
        	paramMap.put("statics_month",queryInfo.getStatics_month());
        }
        if("3".equals(queryInfo.getQry_type())){
        	List<Map<String,Object>> list = wmsinvecommissionperformancehisDao.searchTeam(paramMap); 
            GridDataBean<Map<String,Object>> bean = new 
                    GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionperformancehisDao.findCountTeam(paramMap),list);
    		return bean.getGridData();	
        }
        else if ("5".equals(queryInfo.getQry_type()) || "6".equals(queryInfo.getQry_type()) || "7".equals(queryInfo.getQry_type()))
        {
            List<Map<String, Object>> list = wmsinvecommissionperformancehisDao.searchOldStock(paramMap);
            GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsinvecommissionperformancehisDao.findCountOldStock(paramMap), list);
			return bean.getGridData();
		}else if("1".equals(queryInfo.getQry_type())){//新增将 特殊处理
        	List<Map<String,Object>> list = wmsinvecommissionperformancehisDao.searchPerAdd(paramMap); 
            GridDataBean<Map<String,Object>> bean = new 
                    GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionperformancehisDao.findCountPerAdd(paramMap),list);
    		return bean.getGridData();
        }else {
        	List<Map<String,Object>> list = wmsinvecommissionperformancehisDao.search(paramMap); 
            GridDataBean<Map<String,Object>> bean = new 
                    GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionperformancehisDao.findCount(paramMap),list);
    		return bean.getGridData();	
        }
        		
	}

	@Override
	public WmsInveCommissionPerformanceHis getInfoByPK(
			Integer wms_inve_commission_performance_his_id) {
		return wmsinvecommissionperformancehisDao
				.get(wms_inve_commission_performance_his_id);
	}

	@Override
	@Transactional
	public String save(WmsInveCommissionPerformanceHis bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionperformancehisDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionPerformanceHis bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionperformancehisDao.update(bean); // update a
																// record
																// replace
																// columns,
																// nonsupport
																// null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	/**
	 * Description :check repeat by "and" method, union checking, need new
	 * WmsInveCommissionPerformanceHis() include check-params
	 * 
	 * @param queryInfo
	 * @param isExludePKFlag
	 *            , true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionPerformanceHis> getListByEntity(
			WmsInveCommissionPerformanceHis queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionPerformanceHis> beanList = wmsinvecommissionperformancehisDao
				.getListByEntity(queryInfo);
		return beanList;
	}

	/**
	 * Description :检查是否存在 存在说明已验证
	 * 
	 * @param bean
	 * @return "list"
	 * @author zhangyunfei
	 */
	@Override
	public List<PmPersonnelOtherinfo> findPmpersonnelOtherCountByPid(
			PmPersonnelOtherinfo bean) {
		List<PmPersonnelOtherinfo> list = wmsinvecommissionperformancehisDao
				.findPmpersonnelOtherCountByPid(bean);
		return list;
	}

	/**
	 * Description :认证情况列表
	 * 
	 * @param bean
	 * @return "Map"
	 * @author zhangyunfei
	 */
	@Override
	public Map<String, Object> getWmsInveTransaAuthListByPid(
			WmsInveTransaAuth queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("sortname", "date_of_payment");
		paramMap.put("sortorder", "desc");
		paramMap.put("pm_personnel_otherinfo_id",
				queryInfo.getPm_personnel_otherinfo_id());

		List<Map<String, Object>> list = wmsinvecommissionperformancehisDao
				.getWmsInveTransaAuthListByPid(paramMap);
		paramMap.put("Rows", list);
		return paramMap;
	}
}
