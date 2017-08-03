package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommionRecordAdjustDao;
import com.zx.emanage.inve.persist.WmsInveCommionRecordDao;
import com.zx.emanage.inve.service.IWmsInveCommionRecordService;
import com.zx.emanage.util.gen.entity.WmsInveCommionRecord;
import com.zx.emanage.util.gen.entity.WmsInveCommionRecordAdjust;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategoryLog;
import com.zx.emanage.inve.vo.WmsInveCommionRecordSearchBeanVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommionrecordService")
public class WmsInveCommionRecordServiceImpl implements IWmsInveCommionRecordService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommionRecordServiceImpl.class);

	@Autowired
	private WmsInveCommionRecordDao wmsinvecommionrecordDao;
	@Autowired
	private WmsInveCommionRecordAdjustDao wmsInveCommionRecordAdjustDao;
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommionRecordSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommionrecordDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommionRecordSearchBeanVO queryInfo) {
        Map<String, Object> paramMap = setParams(queryInfo);
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommionrecordDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommionrecordDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommionRecord getInfoByPK(Integer wms_inve_commion_record_id) {
		return wmsinvecommionrecordDao.get(wms_inve_commion_record_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommionRecord bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommionrecordDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommionRecord bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommionrecordDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommionRecord() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommionRecord> getListByEntity(WmsInveCommionRecord queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommionRecord> beanList = wmsinvecommionrecordDao.getListByEntity(queryInfo);
		return beanList;
	}
	
	
	/**
	 * Description :调整金额
	 * @param WmsInveCommionRecord
	 * @return "success" or "error" or user defined
	 * @author zhangyunfei
	 */	
	@Transactional
	public String updateWmsInveCommionRecordAmount(WmsInveCommionRecord bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		bean.setPm_personnel_id(user.getUserId());
		bean.setAdjust_datetime(new Timestamp(new Date().getTime()));
		ret = wmsinvecommionrecordDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		
		WmsInveCommionRecordAdjust adjustRecord =new WmsInveCommionRecordAdjust();
			adjustRecord.setWms_inve_commion_record_id(bean.getWms_inve_commion_record_id());
			adjustRecord.setAdjust_type("1");
			adjustRecord.setAdjust_amount(bean.getAdjust_amount());
			adjustRecord.setAdjust_remark(bean.getAdjust_remark());
			adjustRecord.setPm_personnel_id(user.getUserId());
			adjustRecord.setAdjust_datetime(new Timestamp(new Date().getTime()));
			ret =wmsInveCommionRecordAdjustDao.save(adjustRecord);//日志
			if (ret == 0)
		    {
		        resStr = "error";
		    }
		return resStr;
	}	
	
	@Override
	public String findwmsinvepruductcategorySumMoney(WmsInveCommionRecordSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = setParams(queryInfo);
        String sumMoney = wmsinvecommionrecordDao.findwmsinvepruductcategorySumMoney(paramMap); 
   
		return sumMoney;			
	}

	protected Map<String, Object> setParams(
			WmsInveCommionRecordSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();

        if(StringUtil.isNotBlank(queryInfo.getCompay_id()) && !"-1".equals(queryInfo.getCompay_id()) && !"-2".equals(queryInfo.getCompay_id())){
        	 paramMap.put("compay_id", queryInfo.getCompay_id());
        }
        if(StringUtil.isNotBlank(queryInfo.getDept_id()) && !"-1".equals(queryInfo.getDept_id()) && !"-2".equals(queryInfo.getDept_id())){
       	 	paramMap.put("dept_id", queryInfo.getDept_id());
        }
        if(StringUtil.isNotBlank(queryInfo.getPersonnel_info())){
        	paramMap.put("personnel_info", queryInfo.getPersonnel_info());
        }
        if(StringUtil.isNotBlank(queryInfo.getStatics_month())){
        	paramMap.put("statics_month", queryInfo.getStatics_month());
        }
        if(StringUtil.isNotBlank(queryInfo.getPersonnel_postid()) && !"-1".equals(queryInfo.getPersonnel_postid()) && !"-2".equals(queryInfo.getPersonnel_postid())){
        	if("7".equals(queryInfo.getPersonnel_postid())){
        		paramMap.put("personnel_state", queryInfo.getPersonnel_postid());
        	}else{
        		String post_id=queryInfo.getPersonnel_postid();
        		List<String> post_number_list = new ArrayList<>();
        		if("JXTDJL".equals(post_id)){
        			//post_id="'JXTDJL','JXBMJL'";
        			post_id="PCFJXBMJL%";
        			post_number_list.add(post_id);
        			paramMap.put("post_number_list", post_number_list);
        		}else if("TDJL".equals(post_id)){
        			//post_id="'TDJL','BMJL'";
        			post_id="PCFBMJL%";
        			post_number_list.add(post_id);
        			paramMap.put("post_number_list", post_number_list);
        		}else if("ZJL".equals(post_id)){
        			post_number_list.add("PCFZJL%");
        			post_number_list.add("PCFJXZJL%");
        			post_number_list.add("PCFZXFZJL%");
        			post_number_list.add("PCFJXZXFZJL%");
        			post_number_list.add("PCFZXFGSZJL%");
        			post_number_list.add("PCFJXZXFGSZJL%");
        			paramMap.put("post_number_list", post_number_list);
        		}else if("HXLD".equals(post_id)){
        			paramMap.put("hxld", "1");
        		}else if("OTHER".equals(post_id)){
        			paramMap.put("other", "1");
        			post_number_list.add("PCFJXBMJL%");
            		post_number_list.add("PCFBMJL%");
            		post_number_list.add("PCFJXKHJL%");
            		post_number_list.add("PCFKHJL%");
            		post_number_list.add("PCFZJL%");
        			post_number_list.add("PCFJXZJL%");
        			post_number_list.add("PCFZXFZJL%");
        			post_number_list.add("PCFJXZXFZJL%");
        			post_number_list.add("PCFZXFGSZJL%");
        			post_number_list.add("PCFJXZXFGSZJL%");
        			paramMap.put("post_id_list", post_number_list);
        		}else{
        			post_number_list.add(post_id+"%");
        			paramMap.put("post_number_list", post_number_list);
        		}
        	}
        	
        }
		return paramMap;
	}
}
