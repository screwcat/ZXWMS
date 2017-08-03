package com.zx.emanage.reportmanage.service.impl;

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

import com.zx.emanage.reportmanage.persist.WmsInveCommissionPerformanceNewDao;
import com.zx.emanage.reportmanage.service.IWmsInveCommissionPerformanceNewService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformanceNew;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionPerformanceNewSearchBeanVO;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionPerformanceSearchBeanVO;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionTeamPerformanceSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionperformancenewService")
public class WmsInveCommissionPerformanceNewServiceImpl implements IWmsInveCommissionPerformanceNewService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionPerformanceNewServiceImpl.class);

	@Autowired
	private WmsInveCommissionPerformanceNewDao wmsinvecommissionperformancenewDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo) {
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
        //业务组名称ID
        if(StringUtil.isNotBlank(queryInfo.getGroup_id())&& !queryInfo.getGroup_id().equals("-2")&& !queryInfo.getGroup_id().equals("-1")){
            paramMap.put("group_id", queryInfo.getGroup_id());
        }
        //业务员姓名以及编号
        if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
        {
           paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
        } 
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionperformancenewDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo) {
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
        //业务组名称ID
       if(StringUtil.isNotBlank(queryInfo.getGroup_id())&& !queryInfo.getGroup_id().equals("-2")&& !queryInfo.getGroup_id().equals("-1")){
           paramMap.put("group_id", queryInfo.getGroup_id());
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
        List<Map<String,Object>> list = wmsinvecommissionperformancenewDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionperformancenewDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionPerformanceNew getInfoByPK(Integer wms_inve_commission_performance_new_id) {
		return wmsinvecommissionperformancenewDao.get(wms_inve_commission_performance_new_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionPerformanceNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionperformancenewDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionPerformanceNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionperformancenewDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionPerformanceNew() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionPerformanceNew> getListByEntity(WmsInveCommissionPerformanceNew queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionPerformanceNew> beanList = wmsinvecommissionperformancenewDao.getListByEntity(queryInfo);
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
		ret = wmsinvecommissionperformancenewDao.updateStatus(paramMap); // 
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
	    List<Map<String,Object>>  list = wmsinvecommissionperformancenewDao.getDetails(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
	/**
	 * Description :根据人员id获取团队佣金
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@Override
	public Map<String, Object> getListTeam(WmsInveCommissionTeamPerformanceSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	 	paramMap.put("team_user_id", queryInfo.getTeam_user_id()); 
	 	paramMap.put("create_datetime",new java.sql.Date(queryInfo.getCreate_datetime())); 
		paramMap.put("show",queryInfo.getShow()); 
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionperformancenewDao.searchTeam(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
	
	@Override
	public Map<String, Object> getListWithoutPagingAll(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo) {
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
	    List<Map<String,Object>>  list = wmsinvecommissionperformancenewDao.searchAll(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
	@Override
	public Map<String, Object> getListWithPagingAll(WmsInveCommissionPerformanceNewSearchBeanVO queryInfo) {
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
        //业务组名称ID
       if(StringUtil.isNotBlank(queryInfo.getGroup_id())&& !queryInfo.getGroup_id().equals("-2")&& !queryInfo.getGroup_id().equals("-1")){
           paramMap.put("group_id", queryInfo.getGroup_id());
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
        List<Map<String,Object>> list = wmsinvecommissionperformancenewDao.searchAll(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionperformancenewDao.findCountAll(paramMap),list);
		return bean.getGridData();			
	}
}
