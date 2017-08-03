package com.zx.emanage.reportmanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jodd.typeconverter.Convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.reportmanage.persist.WmsInveWagePerformanceDao;
import com.zx.emanage.reportmanage.service.IWmsInveWagePerformanceService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveWagePerformance;
import com.zx.emanage.reportmanage.vo.WmsInveWagePerformanceSearchBeanVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvewageperformanceService")
public class WmsInveWagePerformanceServiceImpl implements IWmsInveWagePerformanceService {
	private static Logger log = LoggerFactory.getLogger(WmsInveWagePerformanceServiceImpl.class);

	@Autowired
	private WmsInveWagePerformanceDao wmsinvewageperformanceDao;
    /**
     * 实现理财工资导出
     */
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveWagePerformanceSearchBeanVO queryInfo) 
	{
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	 	//公司名称
	 	if(StringUtil.isNotBlank(queryInfo.getCompayId()))
	 	{
	 		paramMap.put("company_id", queryInfo.getCompayId());
	 	}
	 	//部门名称
	 	if(StringUtil.isNotBlank(queryInfo.getDeptId())&& !queryInfo.getDeptId().equals("-2"))
	 	{
	 		paramMap.put("dept_id", queryInfo.getDeptId());
	 	}
	 	//团队名称
	 	if(StringUtil.isNotBlank(queryInfo.getTeamId())&& !queryInfo.getTeamId().equals("-2"))
	 	{
	 		paramMap.put("team_id", queryInfo.getTeamId());
	 	}
		//业务员/编号
		if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
		{
				paramMap.put("salesman_name",SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
		}
		//统计时间起始时间
		if(StringUtil.isNotBlank(queryInfo.getCreate_datetime_begin()))
		{
		         paramMap.put("create_datetime_begin", queryInfo.getCreate_datetime_begin());
		}
		//统计时间截止时间
		if(StringUtil.isNotBlank(queryInfo.getCreate_datetime_end()))
		{
		         paramMap.put("create_datetime_end",queryInfo.getCreate_datetime_end());
		}		
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvewageperformanceDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
	/**
	 * 实现理财工资列表查询功能
	 */
	@Override
	public Map<String, Object> getListWithPaging(WmsInveWagePerformanceSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//公司名称
		if(StringUtil.isNotBlank(queryInfo.getCompayId()))
		{
			paramMap.put("company_id", queryInfo.getCompayId());
		}
		//部门名称
		if(StringUtil.isNotBlank(queryInfo.getDeptId()) && !queryInfo.getDeptId().equals("-2"))
		{
			paramMap.put("dept_id", queryInfo.getDeptId());
		}
		//团队名称
		if(StringUtil.isNotBlank(queryInfo.getTeamId()) && !queryInfo.getTeamId().equals("-2"))
		{
			paramMap.put("team_id", queryInfo.getTeamId());
		}
		//业务员/编号
		if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
		{
			paramMap.put("salesman_name",SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
		}
		//统计时间起始时间
		if(StringUtil.isNotBlank(queryInfo.getCreate_datetime_begin()))
		{
	         paramMap.put("create_datetime_begin",queryInfo.getCreate_datetime_begin());
		}
		//统计时间截止时间
		if(StringUtil.isNotBlank(queryInfo.getCreate_datetime_end()))
		{
	         paramMap.put("create_datetime_end", queryInfo.getCreate_datetime_end());
		}
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvewageperformanceDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvewageperformanceDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveWagePerformance getInfoByPK(Integer wms_inve_wage_performance_id) {
		return wmsinvewageperformanceDao.get(wms_inve_wage_performance_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveWagePerformance bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvewageperformanceDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveWagePerformance bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvewageperformanceDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveWagePerformance() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveWagePerformance> getListByEntity(WmsInveWagePerformance queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveWagePerformance> beanList = wmsinvewageperformanceDao.getListByEntity(queryInfo);
		return beanList;
	}
}
