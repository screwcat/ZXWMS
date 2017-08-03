package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.apache.commons.net.ntp.TimeStamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Util;
import com.zx.emanage.inve.persist.WmsInveCommissionPersonnelRewardDao;
import com.zx.emanage.inve.persist.WmsInveCommissionRankingRewardDao;
import com.zx.emanage.inve.persist.WmsInveCommissionRewardHeadRulesDao;
import com.zx.emanage.inve.persist.WmsInveCommissionSpecialRulesPtDao;
import com.zx.emanage.inve.service.IWmsInveCommissionRewardHeadRulesService;
import com.zx.emanage.util.gen.entity.WmsInveCommissionPersonnelReward;
import com.zx.emanage.util.gen.entity.WmsInveCommissionRankingReward;
import com.zx.emanage.util.gen.entity.WmsInveCommissionRewardHeadRules;
import com.zx.emanage.util.gen.entity.WmsInveCommissionSpecialRulesPt;
import com.zx.emanage.inve.vo.WmsInveCommissionRewardHeadRulesSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveCommissionSpecialRulesSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissionrewardheadrulesService")
public class WmsInveCommissionRewardHeadRulesServiceImpl implements IWmsInveCommissionRewardHeadRulesService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionRewardHeadRulesServiceImpl.class);

	@Autowired
	private WmsInveCommissionRewardHeadRulesDao wmsinvecommissionrewardheadrulesDao;
	@Autowired
	private WmsInveCommissionPersonnelRewardDao wmsInveCommissionPersonnelRewardDao;//理财佣金配置——奖励规则设置-根据人员奖励wms_inve_commission_personnel_reward
	@Autowired
	private WmsInveCommissionRankingRewardDao wmsInveCommissionRankingRewardDao;//理财佣金配置——奖励规则设置-根据排名奖励wms_inve_commission_ranking_reward
	@Autowired
	private	WmsInveCommissionSpecialRulesPtDao wmsInveCommissionSpecialRulesPtDao;//理财佣金配置——特权人明细表wms_inve_commission_special_rules_pt
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionRewardHeadRulesSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissionrewardheadrulesDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveCommissionRewardHeadRulesSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissionrewardheadrulesDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissionrewardheadrulesDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveCommissionRewardHeadRules getInfoByPK(Integer wms_inve_commission_reward_head_rules_id) {
		return wmsinvecommissionrewardheadrulesDao.get(wms_inve_commission_reward_head_rules_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionRewardHeadRules bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionrewardheadrulesDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionRewardHeadRules bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissionrewardheadrulesDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionRewardHeadRules() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionRewardHeadRules> getListByEntity(WmsInveCommissionRewardHeadRules queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionRewardHeadRules> beanList = wmsinvecommissionrewardheadrulesDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :保存奖励信息
	 * @param bean
	 * @param beanvo
	 * @return "success" or "error" or user defined
	 * @author baisong
	 * @date 2015/9/16
	 */
	@Override
	@Transactional
	public String saveAll(WmsInveCommissionRewardHeadRules bean, UserBean user,
			WmsInveCommissionRewardHeadRulesSearchBeanVO beanvo) {
		String resStr = "success";
		int ret = 0;
		List<WmsInveCommissionRankingReward> listRanking=	JsonUtil.jsonArrayToList(beanvo.getRanking_reward(),WmsInveCommissionRankingReward.class);//根据排名
		List<WmsInveCommissionPersonnelReward> listPersonnel=	JsonUtil.jsonArrayToList(beanvo.getPerson_way(),WmsInveCommissionPersonnelReward.class);//根据人员
		List<WmsInveCommissionSpecialRulesSearchBeanVO> specialRulesPt = JsonUtil.jsonArrayToList(beanvo.getPerson_way(), WmsInveCommissionSpecialRulesSearchBeanVO.class);
		/*if(listPersonnel!=null&&listPersonnel.size()>0){
			for(int i=0;i<listPersonnel.size();i++){
				for(int l=i+1;l<listPersonnel.size();l++){
					if(i!=l&&listPersonnel.get(i).getReward_model().equals(listPersonnel.get(l).getReward_model())){//奖励模式
						WmsInveCommissionSpecialRulesSearchBeanVO beanVOi = specialRulesPt.get(i);
						String personnelIdStri = beanVOi.getPersonnel_id_str();
						List<String> personnelIdsi = Arrays.asList(personnelIdStri.split(","));
						WmsInveCommissionSpecialRulesSearchBeanVO beanVOl = specialRulesPt.get(l);
						String personnelIdStrl = beanVOl.getPersonnel_id_str();
						List<String> personnelIdsl = Arrays.asList(personnelIdStrl.split(","));
						for(int h=0;h<personnelIdsi.size();h++){
							for(int m=0;m<personnelIdsl.size();m++){
								if(personnelIdsi.get(h).equals(personnelIdsl.get(m))){
									resStr = "errorid";//人员id重复
									return resStr;
								}
							}
						}
					}	
				}
			}
		}*/
		//初始化原始数据
		//奖励主表
		wmsinvecommissionrewardheadrulesDao.updateAll();

		java.sql.Timestamp systemtime=new java.sql.Timestamp(System.currentTimeMillis());
		bean.setCreate_user_id(user.getUserId());
		bean.setCreate_datetime(systemtime);
		bean.setEnable_flag("1");
		ret = wmsinvecommissionrewardheadrulesDao.save(bean);
		if (ret == 0) {
			resStr = "error";
			return resStr;
		}
		
		if(listRanking!=null&&listRanking.size()>0){
			for(WmsInveCommissionRankingReward rankingReward: listRanking){
				rankingReward.setWms_inve_commission_reward_head_rules_id(bean.getWms_inve_commission_reward_head_rules_id());//主表主键
			}
			ret =wmsInveCommissionRankingRewardDao.saveAll(listRanking);//排名表
			if (ret == 0) {
				resStr = "error";
				return resStr;
			}
		}
		if(listPersonnel!=null&&listPersonnel.size()>0){
			int i=0;
			for(WmsInveCommissionPersonnelReward rersonnelReward: listPersonnel){
				rersonnelReward.setWms_inve_commission_reward_head_rules_id(bean.getWms_inve_commission_reward_head_rules_id());//主表主键
				ret =wmsInveCommissionPersonnelRewardDao.save(rersonnelReward);//人员奖励表
				if (ret == 0) {
					return "error";
				}
				//List<WmsInveCommissionSpecialRulesSearchBeanVO> specialRulesPt = JsonUtil.jsonArrayToList(beanvo.getPerson_way(), WmsInveCommissionSpecialRulesSearchBeanVO.class);
				WmsInveCommissionSpecialRulesSearchBeanVO beanVO = specialRulesPt.get(i);
				String personnelIdStr = beanVO.getPersonnel_id_str();
				List<String> personnelIds = Arrays.asList(personnelIdStr.split(","));
				//查询人员表中的人员信息
				List<WmsInveCommissionSpecialRulesPt> pecialRulesPts = wmsInveCommissionSpecialRulesPtDao.getPmPersonnels(personnelIds);
				for(WmsInveCommissionSpecialRulesPt pts : pecialRulesPts) {
					pts.setWms_inve_commission_personnel_reward_id(rersonnelReward.getWms_inve_commission_personnel_reward_id());
					pts.setCreate_user_id(user.getUserId());
					pts.setCreate_user_name(user.getRealname());
					pts.setCreate_user_dept_id(user.getDeptId());
					pts.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
					pts.setEnable_flag("1");
				}
				
				//删除原有人员信息
				wmsInveCommissionSpecialRulesPtDao.deletePerson(rersonnelReward.getWms_inve_commission_personnel_reward_id());
				
				//添加新的人员信息
				if(pecialRulesPts != null && pecialRulesPts.size() > 0) {
					wmsInveCommissionSpecialRulesPtDao.addBatch(pecialRulesPts);
				}
				i++;
			}
		}
		return resStr;
	}
	/**
	 * Description :查询数据库中现有有效数据--奖励规则
	 * @param 
	 * @return map
	 * @author baisong
	 * @date 2015/9/16
	 */	
	@Override
	public Map<String, Object> getInfo() {
		Map<String, Object> map=new HashMap<>();
		WmsInveCommissionRewardHeadRules bean=new WmsInveCommissionRewardHeadRules();
		bean.setEnable_flag("1");
		List<WmsInveCommissionRewardHeadRules> list=wmsinvecommissionrewardheadrulesDao.getListByEntity(bean);
		if(list==null||list.size()==0){
			return null;	
		}
		map.put("rewardheadrules", list.get(0));//奖励主表
		if(list.get(0).getCommission_reward_type()==1){//  排名1 人员2
			WmsInveCommissionRankingReward wmsInveCommissionRankingReward=new WmsInveCommissionRankingReward();//排名信息
			wmsInveCommissionRankingReward.setWms_inve_commission_reward_head_rules_id(list.get(0).getWms_inve_commission_reward_head_rules_id());
			List<WmsInveCommissionRankingReward> listRules=wmsInveCommissionRankingRewardDao.getListByEntity(wmsInveCommissionRankingReward);
			map.put("listRules", listRules);//按照排名
		}else if(list.get(0).getCommission_reward_type()==2){
			WmsInveCommissionPersonnelReward wmsInveCommissionPersonnelReward=new WmsInveCommissionPersonnelReward();
			wmsInveCommissionPersonnelReward.setWms_inve_commission_reward_head_rules_id(list.get(0).getWms_inve_commission_reward_head_rules_id());
			List<WmsInveCommissionPersonnelReward> listPersonnel=	wmsInveCommissionPersonnelRewardDao.getListByEntity(wmsInveCommissionPersonnelReward);
			map.put("listPersonnel", listPersonnel);//按照人员
			if(listPersonnel!=null&&listPersonnel.size()>0){
				List<List<WmsInveCommissionSpecialRulesPt>> personList=new ArrayList<>();//临时参数--用于保存查询人员信息
				for(int i=0;i<listPersonnel.size();i++){
					List<WmsInveCommissionSpecialRulesPt> listpt=wmsInveCommissionSpecialRulesPtDao.getPersonnelsByPer(listPersonnel.get(i).getWms_inve_commission_personnel_reward_id());
					personList.add(listpt);
				}
				map.put("personList", personList);//单据内详细的人员信息
			}	
		}
		return map;
	}
	/**
	 * Description :保存奖励信息--2015-11-4需求变更
	 * @param bean
	 * @param beanvo
	 * @return "success" or "error" or user defined
	 * @author baisong
	 * @date 2015/9/16
	 */
	@Override
	@Transactional
	public String doSaveInfo(UserBean user,WmsInveCommissionRewardHeadRulesSearchBeanVO beanvo) {
		String resStr = "success";
		int ret = 0;
		java.sql.Timestamp systemtime=new java.sql.Timestamp(System.currentTimeMillis());
		List<WmsInveCommissionRankingReward> listReward1=	JsonUtil.jsonArrayToList(beanvo.getGrid_yj(),WmsInveCommissionRankingReward.class);//佣金比例奖励 1
		List<WmsInveCommissionRankingReward> listReward0=	JsonUtil.jsonArrayToList(beanvo.getGrid_jj(),WmsInveCommissionRankingReward.class);//奖金奖励0

		//初始化原始数据
		//奖励主表
		wmsinvecommissionrewardheadrulesDao.updateAll();
		WmsInveCommissionRewardHeadRules bean=new  WmsInveCommissionRewardHeadRules();
		bean.setCommission_reward_type(1);//1代表 根据排名奖励2代表 根据人员奖励
		bean.setCreate_user_id(user.getUserId());
		bean.setReward_method(1);//按照佣金比例奖励 1 奖金奖励0
		bean.setCreate_datetime(systemtime);
		bean.setEnable_flag("1");
		ret = wmsinvecommissionrewardheadrulesDao.save(bean);
		if (ret == 0) {
			resStr = "error";
			return resStr;
		}
		if(listReward1!=null&&listReward1.size()>0){//保存佣金奖励
			for(WmsInveCommissionRankingReward rankingReward: listReward1){
				rankingReward.setWms_inve_commission_reward_head_rules_id(bean.getWms_inve_commission_reward_head_rules_id());//主表主键
			}
			ret =wmsInveCommissionRankingRewardDao.saveAll(listReward1);//排名表
			if (ret == 0) {
				resStr = "error";
				return resStr;
			}
		}
		bean.setWms_inve_commission_reward_head_rules_id(null);//主键
		bean.setReward_method(0);//按照佣金比例奖励 1 奖金奖励0
		ret = wmsinvecommissionrewardheadrulesDao.save(bean);
		if(listReward0!=null&&listReward0.size()>0){//保存佣金奖励
			for(WmsInveCommissionRankingReward rankingReward: listReward0){
				rankingReward.setWms_inve_commission_reward_head_rules_id(bean.getWms_inve_commission_reward_head_rules_id());//主表主键
			}
			ret =wmsInveCommissionRankingRewardDao.saveAll(listReward0);//排名表
			if (ret == 0) {
				resStr = "error";
				return resStr;
			}
		}
		
		return resStr;
	}
}
