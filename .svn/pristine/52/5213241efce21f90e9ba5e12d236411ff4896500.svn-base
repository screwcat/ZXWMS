package com.zx.emanage.loanreview.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanreview.persist.WmsCreCarpHouseAssessmentDao;
import com.zx.emanage.loanreview.service.IWmsCreCarpHouseAssessmentService;
import com.zx.emanage.util.gen.entity.WmsCreCarpHouseAssessment;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.emanage.loanreview.vo.WmsCreCarpHouseAssessmentSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecarphouseassessmentService")
public class WmsCreCarpHouseAssessmentServiceImpl implements IWmsCreCarpHouseAssessmentService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpHouseAssessmentServiceImpl.class);

	@Autowired
	private WmsCreCarpHouseAssessmentDao wmscrecarphouseassessmentDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCarpHouseAssessmentSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrecarphouseassessmentDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreCarpHouseAssessmentSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrecarphouseassessmentDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecarphouseassessmentDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCarpHouseAssessment getInfoByPK(Integer wms_cre_carp_house_assessment_id) {
		return wmscrecarphouseassessmentDao.get(wms_cre_carp_house_assessment_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCarpHouseAssessment bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarphouseassessmentDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCarpHouseAssessment bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarphouseassessmentDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCarpHouseAssessment() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCarpHouseAssessment> getListByEntity(WmsCreCarpHouseAssessment queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCarpHouseAssessment> beanList = wmscrecarphouseassessmentDao.getListByEntity(queryInfo);
		return beanList;
	}
    @Override
    public WmsCreCarpHouseAssessment getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrecarphouseassessmentDao.getInfoByFK(wms_cre_credit_head_id);
    }
}
