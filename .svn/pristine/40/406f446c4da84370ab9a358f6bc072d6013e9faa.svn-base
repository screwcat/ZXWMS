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

import com.zx.emanage.reportmanage.persist.WmsInveCommissionRewardPerformanceDao;
import com.zx.emanage.reportmanage.service.IWmsInveCommissionRewardPerformanceService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsInveCommissionRewardPerformance;
import com.zx.emanage.reportmanage.vo.WmsInveCommissionRewardPerformanceSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionrewardperformanceService")
public class WmsInveCommissionRewardPerformanceServiceImpl implements IWmsInveCommissionRewardPerformanceService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionRewardPerformanceServiceImpl.class);

	@Autowired
	private WmsInveCommissionRewardPerformanceDao wmsinvecommissionrewardperformanceDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionRewardPerformanceSearchBeanVO queryInfo) {
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
        if(StringUtil.isNotBlank(queryInfo.getTeam_id())&& !queryInfo.getTeam_id().equals("-2")&&!queryInfo.getTeam_id().equals("-1")){
           paramMap.put("team_id", queryInfo.getTeam_id());
        }
        //业务员姓名以及编号
        if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
        {
           paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
        } 
        //时间
        if (queryInfo.getCreate_datetime_begin() != null && !"".equals(queryInfo.getCreate_datetime_begin()))
        {
     	   paramMap.put("create_datetime_begin",queryInfo.getCreate_datetime_begin());
        }
        if (queryInfo.getCreate_datetime_end() != null && !"".equals(queryInfo.getCreate_datetime_end()))
        {
     	   paramMap.put("create_datetime_end", queryInfo.getCreate_datetime_end());
        }
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionrewardperformanceDao.search(paramMap);
	    if(list!=null){
	    	for(int i=0;i<list.size();i++){
	    		list.get(i).put("create_datetime_str", list.get(i).get("create_datetime_str").toString().replaceAll("-", ""));
	    	}
	    }
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionRewardPerformanceSearchBeanVO queryInfo) {
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
        if(StringUtil.isNotBlank(queryInfo.getTeam_id())&& !queryInfo.getTeam_id().equals("-2")&&!queryInfo.getTeam_id().equals("-1")){
           paramMap.put("team_id", queryInfo.getTeam_id());
        }
        //业务员姓名以及编号
        if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
        {
           paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
        } 
        //时间
        if (queryInfo.getCreate_datetime_begin() != null && !"".equals(queryInfo.getCreate_datetime_begin()))
        {
     	   paramMap.put("create_datetime_begin", Convert.toDate(queryInfo.getCreate_datetime_begin()));
        }
        if (queryInfo.getCreate_datetime_end() != null && !"".equals(queryInfo.getCreate_datetime_end()))
        {
     	   paramMap.put("create_datetime_end", Convert.toDate(queryInfo.getCreate_datetime_end()) );
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissionrewardperformanceDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionrewardperformanceDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionRewardPerformance getInfoByPK(Integer wms_inve_commission_reward_performance_id) {
		return wmsinvecommissionrewardperformanceDao.get(wms_inve_commission_reward_performance_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionRewardPerformance bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionrewardperformanceDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionRewardPerformance bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionrewardperformanceDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionRewardPerformance() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionRewardPerformance> getListByEntity(WmsInveCommissionRewardPerformance queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionRewardPerformance> beanList = wmsinvecommissionrewardperformanceDao.getListByEntity(queryInfo);
		return beanList;
	}
}
