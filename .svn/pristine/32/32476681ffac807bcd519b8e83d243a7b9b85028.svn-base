package com.zx.emanage.reportmanage.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jodd.typeconverter.Convert;
import jodd.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.reportmanage.persist.WmsInveCommissionPerformanceDao;
import com.zx.emanage.reportmanage.persist.WmsInveCommissionTeamPerformanceDao;
import com.zx.emanage.reportmanage.service.IWmsInveCommissionPerformanceService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformance;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionPerformanceSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionperformanceService")
public class WmsInveCommissionPerformanceServiceImpl implements IWmsInveCommissionPerformanceService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionPerformanceServiceImpl.class);

	@Autowired
	private WmsInveCommissionPerformanceDao wmsinvecommissionperformanceDao;
	@Autowired
	private WmsInveCommissionTeamPerformanceDao wmsInveCommissionTeamPerformanceDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
 	 //部门名称ID
       if(StringUtil.isNotBlank(queryInfo.getCompay_id())&&!queryInfo.getCompay_id().equals("-2")&&!queryInfo.getCompay_id().equals("-1"))
       {
           paramMap.put("compay_id", queryInfo.getCompay_id());
       }
       //门店名称ID
       if(StringUtil.isNotBlank(queryInfo.getDept_id())&&!queryInfo.getDept_id().equals("-2")&&!queryInfo.getDept_id().equals("-1"))
       {
           paramMap.put("dept_id", queryInfo.getDept_id());
       }
       //业务组名称ID
        if(StringUtil.isNotBlank(queryInfo.getTeam_id())&& !queryInfo.getTeam_id().equals("-2")&& !queryInfo.getTeam_id().equals("-1")){
           paramMap.put("team_id", queryInfo.getTeam_id());
       }
       //业务员姓名以及编号
       if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
       {
           paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
       } 
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionperformanceDao.searchForExport(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	   //部门名称ID
       if(StringUtil.isNotBlank(queryInfo.getCompay_id())&&!queryInfo.getCompay_id().equals("-2")&&!queryInfo.getCompay_id().equals("-1"))
       {
           paramMap.put("compay_id", queryInfo.getCompay_id());
       }
       //门店名称ID
       if(StringUtil.isNotBlank(queryInfo.getDept_id())&&!queryInfo.getDept_id().equals("-2")&&!queryInfo.getDept_id().equals("-1"))
       {
           paramMap.put("dept_id", queryInfo.getDept_id());
       }
       //业务组名称ID
        if(StringUtil.isNotBlank(queryInfo.getTeam_id())&& !queryInfo.getTeam_id().equals("-2")&& !queryInfo.getTeam_id().equals("-1")){
           paramMap.put("team_id", queryInfo.getTeam_id());
       }
       //业务员姓名以及编号
       if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
       {
           paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
       } 
		paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissionperformanceDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionperformanceDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionPerformance getInfoByPK(Integer wms_inve_commission_performance_id) {
		return wmsinvecommissionperformanceDao.get(wms_inve_commission_performance_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionPerformance bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionperformanceDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionPerformance bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionperformanceDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionPerformance() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionPerformance> getListByEntity(WmsInveCommissionPerformance queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionPerformance> beanList = wmsinvecommissionperformanceDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :update method-修改单据并添加发放日期
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */
	@Override
	@Transactional
	public String updateStatus(WmsInveCommissionPerformanceSearchBeanVO queryInfo, UserBean user) {
		String resStr = "success";
		int ret = 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		 //部门名称ID
	       if(StringUtil.isNotBlank(queryInfo.getCompay_id())&&!queryInfo.getCompay_id().equals("-2")&&!queryInfo.getCompay_id().equals("-1"))
	       {
	           paramMap.put("compay_id", queryInfo.getCompay_id());
	       }
	       //门店名称ID
	       if(StringUtil.isNotBlank(queryInfo.getDept_id())&&!queryInfo.getDept_id().equals("-2")&&!queryInfo.getDept_id().equals("-1"))
	       {
	           paramMap.put("dept_id", queryInfo.getDept_id());
	       }
	       //业务组名称ID
	        if(StringUtil.isNotBlank(queryInfo.getTeam_id())&& !queryInfo.getTeam_id().equals("-2")&& !queryInfo.getTeam_id().equals("-1")){
	           paramMap.put("team_id", queryInfo.getTeam_id());
	       }
	       //业务员姓名以及编号
	       if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
	       {
	           paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
	       } 
		ret = wmsinvecommissionperformanceDao.updateStatus(paramMap); // update a record replace columns, nonsupport null val
		ret = wmsInveCommissionTeamPerformanceDao.updateStatus(paramMap); // update a record replace columns, nonsupport null val
		
		return resStr;
	}

	@Override
	public Map<String, Object> showDetails(WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
       //业务员id
       if(StringUtil.isNotBlank(queryInfo.getSalesman_id()))
       {
           paramMap.put("salesman_id", queryInfo.getSalesman_id());
       } 	
        paramMap.put("show",queryInfo.getShow()); 
       	paramMap.put("create_datetime",new java.sql.Date(queryInfo.getCreate_datetime())); 
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionperformanceDao.getDetails(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
	
	@Override
	public Map<String, Object> getListWithoutPagingAll(WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
 	 //部门名称ID
       if(StringUtil.isNotBlank(queryInfo.getCompay_id())&&!queryInfo.getCompay_id().equals("-2")&&!queryInfo.getCompay_id().equals("-1"))
       {
           paramMap.put("compay_id", queryInfo.getCompay_id());
       }
       //门店名称ID
       if(StringUtil.isNotBlank(queryInfo.getDept_id())&&!queryInfo.getDept_id().equals("-2")&&!queryInfo.getDept_id().equals("-1"))
       {
           paramMap.put("dept_id", queryInfo.getDept_id());
       }
       //业务组名称ID
        if(StringUtil.isNotBlank(queryInfo.getTeam_id())&& !queryInfo.getTeam_id().equals("-2")&& !queryInfo.getTeam_id().equals("-1")){
           paramMap.put("team_id", queryInfo.getTeam_id());
       }
       //业务员姓名以及编号
       if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
       {
           paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
       } 
       //发放时间
       if (queryInfo.getLssue_date_begin() != null && !"".equals(queryInfo.getLssue_date_begin()))
       {
    	   paramMap.put("lssue_date_begin",Convert.toDate(queryInfo.getLssue_date_begin()));
       }
       if (queryInfo.getLssue_date_end() != null && !"".equals(queryInfo.getLssue_date_end()))
       {
    	   paramMap.put("lssue_date_end",  Convert.toDate(queryInfo.getLssue_date_end()));
       }
       //员工状态--是否包含兼职
       if (queryInfo.getPersonnel_state() != null && !"".equals(queryInfo.getPersonnel_state()))
       {
    	   paramMap.put("personnel_state", queryInfo.getPersonnel_state());
       }
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionperformanceDao.searchForExportCommissionPaymentQuery(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPagingAll(WmsInveCommissionPerformanceSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	 //部门名称ID
       if(StringUtil.isNotBlank(queryInfo.getCompay_id())&&!queryInfo.getCompay_id().equals("-2")&&!queryInfo.getCompay_id().equals("-1"))
       {
           paramMap.put("compay_id", queryInfo.getCompay_id());
       }
       //门店名称ID
       if(StringUtil.isNotBlank(queryInfo.getDept_id())&&!queryInfo.getDept_id().equals("-2")&&!queryInfo.getDept_id().equals("-1"))
       {
           paramMap.put("dept_id", queryInfo.getDept_id());
       }
       //业务组名称ID
        if(StringUtil.isNotBlank(queryInfo.getTeam_id())&& !queryInfo.getTeam_id().equals("-2")&& !queryInfo.getTeam_id().equals("-1")){
           paramMap.put("team_id", queryInfo.getTeam_id());
       }
       //业务员姓名以及编号
       if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
       {
           paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
       } 
       //发放时间
       if (queryInfo.getLssue_date_begin() != null && !"".equals(queryInfo.getLssue_date_begin()))
       {
    	   paramMap.put("lssue_date_begin",Convert.toDate(queryInfo.getLssue_date_begin()));
       }
       if (queryInfo.getLssue_date_end() != null && !"".equals(queryInfo.getLssue_date_end()))
       {
    	   paramMap.put("lssue_date_end",  Convert.toDate(queryInfo.getLssue_date_end()));
       }
       //员工状态--是否包含兼职
       if (queryInfo.getPersonnel_state() != null && !"".equals(queryInfo.getPersonnel_state()))
       {
    	   paramMap.put("personnel_state", queryInfo.getPersonnel_state());
       }

		paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissionperformanceDao.searchAll(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionperformanceDao.findCountAll(paramMap),list);
		return bean.getGridData();			
	}
}
