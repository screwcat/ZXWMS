package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.exception.InveTransException;
import com.zx.emanage.inve.persist.WmsInveTransaPruductDao;
import com.zx.emanage.inve.persist.WmsInveTransaPruductRulesDao;
import com.zx.emanage.inve.service.IWmsInveTransaPruductRulesService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionSpecialRules;
import com.zx.emanage.util.gen.entity.WmsInveTransaPruduct;
import com.zx.emanage.util.gen.entity.WmsInveTransaPruductRules;
import com.zx.emanage.inve.vo.WmsInveCommissionSpecialRulesSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaPruductRulesSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransapruductrulesService")
public class WmsInveTransaPruductRulesServiceImpl implements IWmsInveTransaPruductRulesService {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaPruductRulesServiceImpl.class);

	@Autowired
	private WmsInveTransaPruductRulesDao wmsinvetransapruductrulesDao;
	@Autowired
	private WmsInveTransaPruductDao wmsInveTransaPruductDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveTransaPruductRulesSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvetransapruductrulesDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveTransaPruductRulesSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        String wms_inve_transa_pruduct_rules_ids="";
        List<Map<String,Object>> list = wmsinvetransapruductrulesDao.search(paramMap); 
        for(int i=0;i<list.size();i++){
        	if(i==0){
        		wms_inve_transa_pruduct_rules_ids=list.get(i).get("wms_inve_transa_pruduct_rules_id").toString();
        	}else{
        		wms_inve_transa_pruduct_rules_ids=wms_inve_transa_pruduct_rules_ids+","+list.get(i).get("wms_inve_transa_pruduct_rules_id").toString();
        	}
        	WmsInveTransaPruduct wmsInveTransaPruduct=new WmsInveTransaPruduct();
        	wmsInveTransaPruduct.setWms_inve_transa_pruduct_rules_id(Integer.valueOf(list.get(i).get("wms_inve_transa_pruduct_rules_id").toString()));
        	List<WmsInveTransaPruduct> listp=wmsInveTransaPruductDao.getListByEntity(wmsInveTransaPruduct);
        	String wms_inve_pruduct_category_id="";
        	String category_name="";
        	for(int j=0;j<listp.size();j++){
        		WmsInveTransaPruduct transaPruduct=	listp.get(j);
        		if(j==0){
        			wms_inve_pruduct_category_id=""+transaPruduct.getWms_inve_pruduct_category_id();
        			category_name=""+transaPruduct.getCategory_name();
        		}else{
        			wms_inve_pruduct_category_id=wms_inve_pruduct_category_id+","+transaPruduct.getWms_inve_pruduct_category_id();
        			category_name=category_name+","+transaPruduct.getCategory_name();
        		}
        	}
        	list.get(i).put("wms_inve_pruduct_category_id", wms_inve_pruduct_category_id);
        	list.get(i).put("category_name", category_name);
        }
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvetransapruductrulesDao.findCount(paramMap),list);
        bean.getGridData().put("wms_inve_transa_pruduct_rules_ids", wms_inve_transa_pruduct_rules_ids);
        return bean.getGridData();			
	}

	@Override
	public WmsInveTransaPruductRules getInfoByPK(Integer wms_inve_transa_pruduct_rules_id) {
		return wmsinvetransapruductrulesDao.get(wms_inve_transa_pruduct_rules_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveTransaPruductRules bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransapruductrulesDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String doSaveUpdate(UserBean user,WmsInveTransaPruductRulesSearchBeanVO beanvo) throws InveTransException{
		String resStr = "success";
		int ret = 0;
		List<WmsInveTransaPruductRulesSearchBeanVO> pruductRulesPt = JsonUtil.jsonArrayToList(beanvo.getPruductRules(), WmsInveTransaPruductRulesSearchBeanVO.class);
		List<String> rulesIdList=new ArrayList<>();
		if(beanvo.getWms_inve_transa_pruduct_rules_ids()!=null){
			String wms_inve_transa_pruduct_rules_ids = beanvo.getWms_inve_transa_pruduct_rules_ids();//产品id
			rulesIdList =Arrays.asList(wms_inve_transa_pruduct_rules_ids.split(","));
		
		}
		//删除原规则
		if(rulesIdList.size()>0){
			wmsinvetransapruductrulesDao.deleteList(rulesIdList);	
		}
		List<WmsInveTransaPruduct> pruductS= new ArrayList<>();
		for(WmsInveTransaPruductRulesSearchBeanVO pruductRulep:pruductRulesPt){
			WmsInveTransaPruductRules pruductRule=new WmsInveTransaPruductRules();
			pruductRule.setCommission_general_rules(pruductRulep.getCommission_general_rules());
			pruductRule.setCreate_user_id(user.getUserId());
			pruductRule.setCreate_user_name(user.getRealname());
			pruductRule.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
			pruductRule.setEnable_flag("1");
			ret = wmsinvetransapruductrulesDao.save(pruductRule); 
			String categoryId = pruductRulep.getWms_inve_pruduct_category_id();//产品id
			List<String> categoryIds = Arrays.asList(categoryId.split(","));
			String categoryName = pruductRulep.getCategory_name();//产品id
			List<String> categoryNames = Arrays.asList(categoryName.split(","));
			for(int i=0;i<categoryIds.size();i++){
				WmsInveTransaPruduct wmsInveTransaPruduct=new WmsInveTransaPruduct();
				//产品佣金规则主键
				wmsInveTransaPruduct.setWms_inve_transa_pruduct_rules_id(pruductRule.getWms_inve_transa_pruduct_rules_id());
				wmsInveTransaPruduct.setWms_inve_pruduct_category_id(Integer.valueOf(categoryIds.get(i)));
				wmsInveTransaPruduct.setCategory_name(categoryNames.get(i));
				wmsInveTransaPruduct.setEnable_flag("1");
				pruductS.add(wmsInveTransaPruduct);
			}
		}
		//删除
		if(rulesIdList.size()>0){
			wmsInveTransaPruductDao.deleteList(rulesIdList);	
		}
		if(pruductS.size()>0){
			wmsInveTransaPruductDao.saveList(pruductS);
		}
		ischeck();
		return resStr;
	}
	/**
	 * 检查规则产品是否有重复
	 * baisong
	 */
	public void ischeck() throws InveTransException{
		List <Map<String,Object>> list=wmsInveTransaPruductDao.ischeck();
		if(list!=null&&list.size()>0){
			InveTransException inveTransException=new InveTransException();
			throw inveTransException;
		}
	}
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveTransaPruductRules() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveTransaPruductRules> getListByEntity(WmsInveTransaPruductRules queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveTransaPruductRules> beanList = wmsinvetransapruductrulesDao.getListByEntity(queryInfo);
		return beanList;
	}
}
