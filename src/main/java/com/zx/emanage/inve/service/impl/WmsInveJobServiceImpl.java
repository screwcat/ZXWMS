package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveJobDao;
import com.zx.emanage.inve.service.IWmsInveJobService;
import com.zx.emanage.util.gen.entity.WmsInveJob;
import com.zx.emanage.inve.vo.WmsInveJobSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvejobService")
public class WmsInveJobServiceImpl implements IWmsInveJobService {
	private static Logger log = LoggerFactory.getLogger(WmsInveJobServiceImpl.class);

	@Autowired
	private WmsInveJobDao wmsinvejobDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveJobSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvejobDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveJobSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvejobDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvejobDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveJob getInfoByPK(Integer wms_inve_job_id) {
		return wmsinvejobDao.get(wms_inve_job_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveJob bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvejobDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveJob bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvejobDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveJob() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveJob> getListByEntity(WmsInveJob queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveJob> beanList = wmsinvejobDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * 理财工资配置：根据提供的职务代号  获取相关职务信息
	 */
	@Override
	public WmsInveJob getAllSysPostWithList(String job_code) {
		WmsInveJob wInveJob = new WmsInveJob();
		wInveJob.setJob_code(job_code);
		List<WmsInveJob>wmsInveJobList=wmsinvejobDao.getListByEntity(wInveJob);
		return wmsInveJobList.get(0);
	}
}
