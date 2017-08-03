package com.zx.emanage.inve.service.impl;

import java.sql.Date;
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

import com.zx.emanage.inve.persist.WmsInveCommissionCategoryDao;
import com.zx.emanage.inve.persist.WmsInveCommissionFloatingDao;
import com.zx.emanage.inve.persist.WmsInveCommissionGeneralMonthlyRulesDao;
import com.zx.emanage.inve.persist.WmsInveCommissionGeneralRulesDao;
import com.zx.emanage.inve.service.IWmsInveCommissionGeneralRulesService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionCategory;
import com.zx.emanage.util.gen.entity.WmsInveCommissionFloating;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralMonthlyRules;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralRules;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralRulesSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissiongeneralrulesService")
public class WmsInveCommissionGeneralRulesServiceImpl implements IWmsInveCommissionGeneralRulesService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionGeneralRulesServiceImpl.class);

	@Autowired
	private WmsInveCommissionGeneralRulesDao wmsinvecommissiongeneralrulesDao;
	@Autowired
	private WmsInveCommissionFloatingDao wmsInveCommissionFloatingDao;//理财佣金配置——一般规则配置------浮动佣金系数配置副表wms_inve_commission_floating
	@Autowired
	private WmsInveCommissionCategoryDao wmsInveCommissionCategoryDao;//理财佣金配置——一般规则-相关产品管理wms_inve_commission_category
	@Autowired
	private WmsInveCommissionGeneralMonthlyRulesDao wmsInveCommissionGeneralMonthlyRulesDao;//月付佣金
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralRulesSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissiongeneralrulesDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralRulesSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissiongeneralrulesDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissiongeneralrulesDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionGeneralRules getInfoByPK(Integer wms_inve_commission_general_rules_id) {
		return wmsinvecommissiongeneralrulesDao.get(wms_inve_commission_general_rules_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionGeneralRules bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneralrulesDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionGeneralRules bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneralrulesDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionGeneralRules() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionGeneralRules> getListByEntity(WmsInveCommissionGeneralRules queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		List<WmsInveCommissionGeneralRules> beanList = wmsinvecommissiongeneralrulesDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :获取一般佣金规则信息-根据beanvo中参数不同获取不同类型的信息
	 * @param WmsInveCommissionGeneralRulesSearchBeanVO
	 * @return map
	 * @author baisong
	 * @date 2015/9/17
	 */	
	@Override
	public Map<String, Object> getInfo(WmsInveCommissionGeneralRulesSearchBeanVO beanvo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("job_code", beanvo.getJob_code());//职务配置职务ID
		paramMap.put("sortname", beanvo.getSortname());
        paramMap.put("sortorder", beanvo.getSortorder());
        paramMap.put("pagesize", beanvo.getPagesize());
        paramMap.put("offset", beanvo.getOffset());
		List<Map<String,Object>> beanList=	wmsinvecommissiongeneralrulesDao.getList(paramMap);
		if(beanvo.getJob_code()==101||beanvo.getJob_code()==102){////101团队经理 102见习经理
			if(beanList!=null&&beanList.size()>0){//循环浮动工资
				for(int i=0;i<beanList.size();i++){
					List<Map<String ,Object>> list= wmsInveCommissionFloatingDao.getListByid(Integer.valueOf(beanList.get(i).get("wms_inve_commission_general_rules_id").toString()));
					beanList.get(i).put("commissionfloating", list);
				}
			}	
		}
		for(int i=0;i<beanList.size();i++){
			//2015-11-3号 baisong 需求变更 月付佣金添加增加删除
			List<Map<String ,Object>> list1= wmsInveCommissionGeneralMonthlyRulesDao.getListByid(Integer.valueOf(beanList.get(i).get("wms_inve_commission_general_rules_id").toString()));
			beanList.get(i).put("parammonthly", list1);//月付佣金	
		}
		if(beanList!=null&&beanList.size()>0){//循环浮动工资
			for(int i=0;i<beanList.size();i++){
				String ids="";//id
				String names="";//name
				List<Map<String ,Object>> list= wmsInveCommissionCategoryDao.getListByid(Integer.valueOf(beanList.get(i).get("wms_inve_commission_general_rules_id").toString()));
				for(int g=0;g<list.size();g++){//处理id name
					if(g==0){
						ids+=list.get(g).get("wms_inve_pruduct_category_id");
						names+=list.get(g).get("category_name");	
					}else{
						ids+=","+list.get(g).get("wms_inve_pruduct_category_id");
						names+=","+list.get(g).get("category_name");
					}
				}
				beanList.get(i).put("ids", ids);
				beanList.get(i).put("names", names);
			}
		}	
		GridDataBean<Map<String,Object>> bean = new 
	                GridDataBean<Map<String,Object>>(beanvo.getPage(), wmsinvecommissiongeneralrulesDao.findCountList(paramMap),beanList);
		return bean.getGridData();
	}
	/**
	 * Description :保存规则信息--客户
	 * @param WmsInveCommissionGeneralRulesSearchBeanVO WmsInveCommissionGeneralRules HttpServletRequest
	 * @return String
	 * @author baisong
	 * @date 2015/9/17
	 */	
	@Override
	@Transactional
	public String saveKHInfo(UserBean user, WmsInveCommissionGeneralRules bean,WmsInveCommissionGeneralRulesSearchBeanVO beanvo) {
		String resStr = "success";
		java.sql.Timestamp systemtime=new java.sql.Timestamp(System.currentTimeMillis());
		if(beanvo.getCategory_ids()!=null&&beanvo.getCategory_names()!=null){
			String [] ids=beanvo.getCategory_ids().split(",");//产品id
			Map<String ,Object> map=new HashMap<>();
			map.put("employee_state", bean.getEmployee_state());//员工状态
			map.put("job_code", bean.getJob_code());//职务配置职务ID
			map.put("list", Arrays.asList(ids));//产品id
			map.put("wms_inve_commission_general_rules_id", bean.getWms_inve_commission_general_rules_id());//产品id
			List<Map<String ,Object>> listmap=wmsInveCommissionCategoryDao.isCheck(map);
			/*for(int i=0;i<listmap.size();i++){
				if(listmap.get(i).get("wms_inve_commission_general_rules_id").equals(bean.getWms_inve_commission_general_rules_id())){
					listmap.remove(i);
					i--;
				}
			}*/
			if(listmap!=null&&listmap.size()>0){
				resStr = "errorid";
				return resStr;//产品重复-当前产品存在规则
			}
		}
		//-1 代表请选择 0代表启用 1代表禁用
		if(bean.getPage_rule_state().equals("0")){
			bean.setStart_date(new java.sql.Date(System.currentTimeMillis()));//启用时间
		}else if(bean.getPage_rule_state().equals("1")){
			bean.setStop_date(new java.sql.Date(System.currentTimeMillis()));//禁用时间
		}
		bean.setRule_state(Integer.valueOf(bean.getPage_rule_state()));//状态
		if(bean!=null&&bean.getWms_inve_commission_general_rules_id()!=null){
			bean.setLast_update_datetime(systemtime);
			bean.setLast_update_user_id(user.getUserId());
			wmsinvecommissiongeneralrulesDao.update(bean);
		}else if(bean!=null){
			bean.setCreate_datetime(systemtime);
			bean.setCreate_user_id(user.getUserId());
			bean.setEnable_flag("1");
			int ret=wmsinvecommissiongeneralrulesDao.save(bean);
			if (ret == 0) {
				resStr = "error";
				return resStr;
			}	
		}
		wmsInveCommissionGeneralMonthlyRulesDao.deletebykey(bean.getWms_inve_commission_general_rules_id());//删除月付佣金原来表内容
		if(beanvo.getParammonthly()!=null){//月付佣金
			List<WmsInveCommissionGeneralMonthlyRules> list=JsonUtil.jsonArrayToList(beanvo.getParammonthly(), WmsInveCommissionGeneralMonthlyRules.class);
			for(WmsInveCommissionGeneralMonthlyRules monthlyRules:list){
				if(monthlyRules!=null){//如果月付佣金为空则不保存
					monthlyRules.setWms_inve_commission_general_rules_id(bean.getWms_inve_commission_general_rules_id());//主表主键
					monthlyRules.setCreate_user_id(user.getUserId());
					monthlyRules.setCreate_timestamp(systemtime);
					monthlyRules.setEnable_flag("1");
					wmsInveCommissionGeneralMonthlyRulesDao.save(monthlyRules);
				}
			}
		}
		if(bean.getJob_code()==101||bean.getJob_code()==102){//101团队经理 102见习经理
			wmsInveCommissionFloatingDao.deletebykey(bean.getWms_inve_commission_general_rules_id());//删除原来表内容
			if(beanvo.getCommission_floating()!=null){//浮动工资
				List<WmsInveCommissionFloating> list=JsonUtil.jsonArrayToList(beanvo.getCommission_floating(), WmsInveCommissionFloating.class);
				for(WmsInveCommissionFloating commissionFloat:list){
					if(commissionFloat!=null&&commissionFloat.getCommission_type()!=null){//如果佣金规则为空则不保存
						commissionFloat.setWms_inve_commission_general_rules_id(bean.getWms_inve_commission_general_rules_id());//主表主键
						wmsInveCommissionFloatingDao.save(commissionFloat);
					}
				}
			}
		}
		wmsInveCommissionCategoryDao.deleteAll(bean.getWms_inve_commission_general_rules_id());//删除全部相关产品数据
		if(beanvo.getCategory_ids()!=null&&beanvo.getCategory_names()!=null){//判断产品信息
			//List<Integer> listid=JsonUtil.jsonArrayToList(beanvo.getCategory_ids(), Integer.class);//产品id
			//List<String> listname=JsonUtil.jsonArrayToList(beanvo.getCategory_names(), String.class);//产品name
			String [] ids=beanvo.getCategory_ids().split(",");//产品id
			String [] names=beanvo.getCategory_names().split(",");//产品名称
			if(ids.length>0&&!ids[0].equals("")){
				List<WmsInveCommissionCategory> categorylist=new ArrayList<>();
				for(int i=0;i<ids.length;i++){
					WmsInveCommissionCategory wmsInveCommissionCategory=new WmsInveCommissionCategory();//相关产品表
					wmsInveCommissionCategory.setWms_inve_commission_general_rules_id(bean.getWms_inve_commission_general_rules_id());//主表主键
					wmsInveCommissionCategory.setWms_inve_pruduct_category_id(Integer.valueOf(ids[i]));//产品id
					wmsInveCommissionCategory.setCategory_name(names[i]);//产品name
					categorylist.add(wmsInveCommissionCategory);
				}
				wmsInveCommissionCategoryDao.saveAll(categorylist);//相关产品批量新增
			}
		}
		return resStr;
	}
}
