package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveWagerulesNonlocalHeadDao;
import com.zx.emanage.inve.persist.WmsInveWagerulesNonlocalLvDao;
import com.zx.emanage.inve.service.IWmsInveWagerulesNonlocalHeadService;
import com.zx.emanage.inve.vo.WmsInveWagerulesNonlocalHeadSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveWagerulesNonlocalLvSearchBeanVO;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.entity.WmsInveWagerulesNonlocalHead;
import com.zx.emanage.util.gen.entity.WmsInveWagerulesNonlocalLv;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvewagerulesnonlocalheadService")
public class WmsInveWagerulesNonlocalHeadServiceImpl implements IWmsInveWagerulesNonlocalHeadService {
	private static Logger log = LoggerFactory.getLogger(WmsInveWagerulesNonlocalHeadServiceImpl.class);

	@Autowired
	private WmsInveWagerulesNonlocalHeadDao wmsinvewagerulesnonlocalheadDao;
	
	@Autowired
	private WmsInveWagerulesNonlocalLvDao wmsinvewagerulesnonlocallvdao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveWagerulesNonlocalHeadSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvewagerulesnonlocalheadDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
	//理财薪资规则列表显示
	@Override
	public Map<String, Object> getListWithPaging(WmsInveWagerulesNonlocalHead queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())) {
	        paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
	    }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end())) {
	        paramMap.put("create_timestamp_end",queryInfo.getCreate_timestamp_end());
	    }
		if(queryInfo.getPage_rule_state() != -1) {
	        paramMap.put("page_rule_state",queryInfo.getPage_rule_state());
	    }
		if((queryInfo.getCompay_id() != null) && (queryInfo.getCompay_id() != -1 || queryInfo.getCompay_id() != -2)) {
	        paramMap.put("compay_id",queryInfo.getCompay_id());
	    }
		if(queryInfo.getPersonnel_postid() != -2){
			 paramMap.put("personnel_postid",queryInfo.getPersonnel_postid());
		}
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvewagerulesnonlocalheadDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvewagerulesnonlocalheadDao.findCount(paramMap),list);
		return bean.getGridData();	
	}

	@Override
	public WmsInveWagerulesNonlocalHead getInfoByPK(Integer wms_inve_wagerules_nonlocal_head_id) {
		return wmsinvewagerulesnonlocalheadDao.get(wms_inve_wagerules_nonlocal_head_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveWagerulesNonlocalHead bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvewagerulesnonlocalheadDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveWagerulesNonlocalHead bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvewagerulesnonlocalheadDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveWagerulesNonlocalHead() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveWagerulesNonlocalHead> getListByEntity(WmsInveWagerulesNonlocalHead queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveWagerulesNonlocalHead> beanList = wmsinvewagerulesnonlocalheadDao.getListByEntity(queryInfo);
		return beanList;
	}
	@Override
	@Transactional
	public String saveFinancialSalaryRule(WmsInveWagerulesNonlocalLvSearchBeanVO beanLv, UserBean user) {
		if(beanLv.getPage_rule_state() == 0){
			 //校验规则是否存在重复
		    Map<String, Object> paramMap = new HashMap<String, Object>();
		    paramMap.put("compay_id", beanLv.getCompay_id());
		    paramMap.put("dept_id", beanLv.getDept_id());
		    paramMap.put("personnel_postId", beanLv.getPersonnel_postid());
		    
		    List<Map<String, Object>> repeatMap = wmsinvewagerulesnonlocalheadDao.repeatRulesCount(paramMap);
		    if(repeatMap != null && repeatMap.size() > 0) {
		        return "repeat";
		    }
		}
	   
		WmsInveWagerulesNonlocalHead bean = new WmsInveWagerulesNonlocalHead();
		Timestamp date = new Timestamp(System.currentTimeMillis());
		bean.setCompay_id(beanLv.getCompay_id());
		bean.setDept_id(beanLv.getDept_id());
		bean.setPersonnel_postid(beanLv.getPersonnel_postid());
		bean.setBill_code(CodeNoUtil.getSalaryRulesCode());
		bean.setCreate_user_id(user.getUserId());
		bean.setCreate_user_name(user.getRealname());
		bean.setCreate_datetime(date.toString());
		bean.setEnable_flag("1");
		bean.setPage_rule_state(beanLv.getPage_rule_state());
		bean.setRemark(beanLv.getRemark());
		if(beanLv.getPage_rule_state() == 0){
			bean.setStart_date(date.toString());
		}
		if(beanLv.getPage_rule_state() == 1){
			bean.setStop_date(date.toString());
		}
		int n = wmsinvewagerulesnonlocalheadDao.save(bean);
    	List<WmsInveWagerulesNonlocalLv> NonlocalLvList = JsonUtil.jsonArrayToList(beanLv.getJsonstr(), WmsInveWagerulesNonlocalLv.class);
    	for(WmsInveWagerulesNonlocalLv lv : NonlocalLvList){
    		lv.setWms_inve_wagerules_nonlocal_head_id(bean.getWms_inve_wagerules_nonlocal_head_id());
    		lv.setEnable_flag("1");
    		wmsinvewagerulesnonlocallvdao.save(lv);
    	}
		if(n > 0){
			return "success";
		}else{
			return "error";
		}
		
	}
	@Override
	@Transactional
	public Map<String, Object> getFinancialSalaryRuleAll(WmsInveWagerulesNonlocalHead bean) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("wms_inve_wagerules_nonlocal_head_id", bean.getWms_inve_wagerules_nonlocal_head_id());
		paramMap.put("sortname", "");
	    paramMap.put("offset", "");
		List<Map<String,Object>> list = wmsinvewagerulesnonlocalheadDao.search(paramMap);
		List <Map<String,Object>> listlv = wmsinvewagerulesnonlocallvdao.search(paramMap);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap.put("list", list);
		resultmap.put("listlv", listlv);
		return resultmap;
	}
	@Override
	@Transactional
	public String updateFinancialSalaryRule(WmsInveWagerulesNonlocalLvSearchBeanVO beanLv, UserBean user) {
	    if(beanLv.getPage_rule_state() == 0){
			//校验规则是否存在重复
		    Map<String, Object> paramMap = new HashMap<String, Object>();
	        paramMap.put("compay_id", beanLv.getCompay_id());
	        paramMap.put("dept_id", beanLv.getDept_id());
	        paramMap.put("personnel_postId", beanLv.getPersonnel_postid());
	        paramMap.put("wms_inve_wagerules_nonlocal_head_id_ne", beanLv.getWms_inve_wagerules_nonlocal_head_id());
	        
	        List<Map<String, Object>> repeatMap = wmsinvewagerulesnonlocalheadDao.repeatRulesCount(paramMap);
		    if(repeatMap != null && repeatMap.size() > 0) {
		        return "repeat";
		    }
		}
		WmsInveWagerulesNonlocalHead bean = new WmsInveWagerulesNonlocalHead();
		bean.setBill_code(beanLv.getBill_code());
		bean.setCompay_id(beanLv.getCompay_id());
		bean.setDept_id(beanLv.getDept_id());
		bean.setPersonnel_postid(beanLv.getPersonnel_postid());
		bean.setPage_rule_state(beanLv.getPage_rule_state());
		bean.setRemark(beanLv.getRemark());
		Timestamp date = new Timestamp(System.currentTimeMillis());
		if(beanLv.getPage_rule_state() == 0){
			bean.setStart_date(date.toString());
		}
		if(beanLv.getPage_rule_state() == 1){
			bean.setStop_date(date.toString());
		}
		bean.setWms_inve_wagerules_nonlocal_head_id(beanLv.getWms_inve_wagerules_nonlocal_head_id());
		int n = wmsinvewagerulesnonlocalheadDao.update(bean);
		wmsinvewagerulesnonlocallvdao.deleteHeadLv(beanLv.getWms_inve_wagerules_nonlocal_head_id());
		List<WmsInveWagerulesNonlocalLv> NonlocalLvList = JsonUtil.jsonArrayToList(beanLv.getJsonstr(), WmsInveWagerulesNonlocalLv.class);
    	for(WmsInveWagerulesNonlocalLv lv : NonlocalLvList){
    		lv.setWms_inve_wagerules_nonlocal_head_id(bean.getWms_inve_wagerules_nonlocal_head_id());
    		lv.setEnable_flag("1");
    		wmsinvewagerulesnonlocallvdao.save(lv);
    	}
		if(n > 0){
			return "success";
		}else{
			return "error";
		}
	}
	@Override
	public String deleteFinancialSalaryRule(Integer wms_inve_wagerules_nonlocal_head_id) {
		try {
			wmsinvewagerulesnonlocalheadDao
					.deleteHead(wms_inve_wagerules_nonlocal_head_id);
			wmsinvewagerulesnonlocallvdao
					.deleteHeadLv(wms_inve_wagerules_nonlocal_head_id);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	@Override
	@Transactional
	public String copyFinancialSalaryRule(Integer wms_inve_wagerules_nonlocal_head_id,UserBean user) {
		WmsInveWagerulesNonlocalLv bean = new WmsInveWagerulesNonlocalLv();
		bean.setWms_inve_wagerules_nonlocal_head_id(wms_inve_wagerules_nonlocal_head_id);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("wms_inve_wagerules_nonlocal_head_id", wms_inve_wagerules_nonlocal_head_id);
		paramMap.put("sortname", "");
	    paramMap.put("offset", "");
	    WmsInveWagerulesNonlocalHead head = wmsinvewagerulesnonlocalheadDao.get(wms_inve_wagerules_nonlocal_head_id);
	    //复制信息 把不用的赋值为空
		head.setWms_inve_wagerules_nonlocal_head_id(null);
		head.setBill_code(CodeNoUtil.getSalaryRulesCode());
		head.setStart_date(null);
		head.setStop_date(null);
		head.setCreate_timestamp_end(null);
		head.setPage_rule_state(-1);
		head.setCreate_user_id(user.getUserId());
		head.setCreate_user_name(user.getRealname());
		head.setCreate_datetime(new Timestamp(System.currentTimeMillis()).toString());
		int n = wmsinvewagerulesnonlocalheadDao.save(head);
		List<WmsInveWagerulesNonlocalLv> lvList = wmsinvewagerulesnonlocallvdao.getListByEntity(bean);
		for(WmsInveWagerulesNonlocalLv lv : lvList){
			lv.setWms_inve_wagerules_nonlocal_lv_id(null);
			lv.setWms_inve_wagerules_nonlocal_head_id(head.getWms_inve_wagerules_nonlocal_head_id());
			wmsinvewagerulesnonlocallvdao.save(lv);
		}
		if(n > 0){
			return "success";
		}else{
			return "error";
		}
	}
}
