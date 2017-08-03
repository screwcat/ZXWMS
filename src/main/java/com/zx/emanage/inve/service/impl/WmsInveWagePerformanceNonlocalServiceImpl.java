package com.zx.emanage.inve.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.typeconverter.Convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCompayDao;
import com.zx.emanage.inve.persist.WmsInveWagePerformanceNonlocalDao;
import com.zx.emanage.inve.service.IWmsInveWagePerformanceNonlocalService;
import com.zx.emanage.inve.vo.WmsInveWagePerformanceNonlocalSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveWagePerformanceNonlocal;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvewageperformancenonlocalService")
public class WmsInveWagePerformanceNonlocalServiceImpl implements IWmsInveWagePerformanceNonlocalService {
	private static Logger log = LoggerFactory.getLogger(WmsInveWagePerformanceNonlocalServiceImpl.class);

	@Autowired
	private WmsInveWagePerformanceNonlocalDao wmsinvewageperformancenonlocalDao;
	
	@Autowired
	private WmsInveCompayDao wmsInveCompayDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo) {
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
	    List<Map<String,Object>>  list = wmsinvewageperformancenonlocalDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows", list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo) {
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    //公司范围
        if(StringUtil.isNotBlank(queryInfo.getCompayId()) && !queryInfo.getCompayId().equals("-2"))
        {
            paramMap.put("company_id", queryInfo.getCompayId());
        }
        //部门范围
        if(StringUtil.isNotBlank(queryInfo.getDeptId()) && !queryInfo.getDeptId().equals("-2"))
        {
            paramMap.put("dept_id", queryInfo.getDeptId());
        }
        //团队范围
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
             paramMap.put("create_datetime_begin", Convert.toDate(queryInfo.getCreate_datetime_begin()));
        }
        //统计时间截止时间
        if(StringUtil.isNotBlank(queryInfo.getCreate_datetime_end()))
        {
             paramMap.put("create_datetime_end", Convert.toDate(queryInfo.getCreate_datetime_end()));
        }
	    
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvewageperformancenonlocalDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvewageperformancenonlocalDao.findCount(paramMap), list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveWagePerformanceNonlocal getInfoByPK(Integer wms_inve_wage_performance_nonlocal_id) {
		return wmsinvewageperformancenonlocalDao.get(wms_inve_wage_performance_nonlocal_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveWagePerformanceNonlocal bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvewageperformancenonlocalDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveWagePerformanceNonlocal bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvewageperformancenonlocalDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveWagePerformanceNonlocal() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveWagePerformanceNonlocal> getListByEntity(WmsInveWagePerformanceNonlocal queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveWagePerformanceNonlocal> beanList = wmsinvewageperformancenonlocalDao.getListByEntity(queryInfo);
		return beanList;
	}
	
	/**
	 * 获取公司下拉列表
	 */
	@Override
	public List<Map<String, Object>> getCompanySelectForSalary(UserBean user, WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo) {
	    log.debug("--------------------获取公司下拉列表处理开始--------------------");
	    
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    List<Map<String, Object>> list = null;
	    if(StringUtil.isNotBlank(queryInfo.getIs_comm_new())){
	    	list = wmsInveCompayDao.getAllCompany(paramMap);
	    }else{
	    	list = wmsInveCompayDao.getCompanySelectForSalary(paramMap);
	    }
	    
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("dept_id", "-1");
	    map.put("compay_name", "--全部--");
	    list.add(0, map);
	    map = new HashMap<String, Object>();
	    map.put("dept_id", "-2");
        map.put("compay_name", "--请选择--");
        list.add(0, map);
	    
	    log.debug("--------------------获取公司下拉列表处理结束--------------------");
	    return list;
	}
	
	/**
     * 根据公司ID获取部门下拉列表
     */
	@Override
	public List<Map<String, Object>> getDeptSelectByCompanyIdForSalary(UserBean user, WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo) {
        log.debug("--------------------根据公司ID获取部门下拉列表处理开始--------------------");
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("company_id", queryInfo.getCompayId());
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        //不是全部才查询部门
        if(!queryInfo.getCompayId().equals("-1")) {
            list = wmsInveCompayDao.getDeptSelectByCompanyIdForSalary(paramMap);
        }
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dept_id", "-1");
        map.put("dept_name", "--全部--");
        list.add(0, map);
        map = new HashMap<String, Object>();
        map.put("dept_id", "-2");
        map.put("dept_name", "--请选择--");
        list.add(0, map);
        
        log.debug("--------------------根据公司ID获取部门下拉列表处理结束--------------------");
        return list;
    }
    
    /**
     * 根据部门ID获取团队下拉列表
     */
	@Override
	public List<Map<String, Object>> getTeamSelectByDeptIdForSalary(UserBean user, WmsInveWagePerformanceNonlocalSearchBeanVO queryInfo) {
        log.debug("--------------------根据部门ID获取团队下拉列表处理开始--------------------");
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dept_id", queryInfo.getDeptId());
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        //不是全部才查询团队
        if(!queryInfo.getDeptId().equals("-1")) {
            list = wmsInveCompayDao.getTeamSelectByDeptIdForSalary(paramMap);
        }
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dept_id", "-1");
        map.put("dept_name", "--全部--");
        list.add(0, map);
        map = new HashMap<String, Object>();
        map.put("dept_id", "-2");
        map.put("dept_name", "--请选择--");
        list.add(0, map);
        
        log.debug("--------------------根据部门ID获取团队下拉列表处理结束--------------------");
        return list;
    }
	
}
