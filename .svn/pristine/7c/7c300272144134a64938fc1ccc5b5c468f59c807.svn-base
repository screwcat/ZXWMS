package com.zx.emanage.inve.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import net.sf.cglib.core.TinyBitSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveWagerulesFloatingDao;
import com.zx.emanage.inve.persist.WmsInveWagerulesHeadDao;
import com.zx.emanage.inve.service.IWmsInveWagerulesHeadService;
import com.zx.emanage.util.gen.entity.WmsInveWagerulesFloating;
import com.zx.emanage.util.gen.entity.WmsInveWagerulesHead;
import com.zx.emanage.inve.vo.WmsInveWagerulesHeadSearchBeanVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvewagerulesheadService")
public class WmsInveWagerulesHeadServiceImpl implements IWmsInveWagerulesHeadService {
	private static Logger log = LoggerFactory.getLogger(WmsInveWagerulesHeadServiceImpl.class);

	@Autowired
	private WmsInveWagerulesHeadDao wmsinvewagerulesheadDao;
	@Autowired
	private WmsInveWagerulesFloatingDao wmsInveWagerulesFloatingDao;
	/**
	 * Description :实现理财客户经理的工资规则的保存
	 * @param bean 工资规则记录Bean
	 * @return "success" or "error" 返回保存提示信息
	 * @author hancd
	 */	
	@Override	
	@Transactional
	public String save(WmsInveWagerulesHead bean, UserBean user,String fdgz) {
		String resStr = "success";
		int ret = 0;
		//设置存储理财工资的基本信息
		setWmsInveWagerulesHead(bean,user,1);
		//判断是否有相同工资规则在启用 职务 部门名称  规则状态
		WmsInveWagerulesHead wagerulesHead = new WmsInveWagerulesHead();
		wagerulesHead.setEnable_flag("1");
		wagerulesHead.setRule_state(0);
		wagerulesHead.setPage_rule_state("0");
		List<WmsInveWagerulesHead> list = wmsinvewagerulesheadDao.getListByRepeat(wagerulesHead);
		
		for(WmsInveWagerulesHead w:list){
			if(w.getPost_id()==bean.getPost_id() && w.getDept_id().equals(bean.getDept_id())){
				return "gzrepeat";
			}
		}
		//存储工资基本信息
		ret = wmsinvewagerulesheadDao.save(bean);
		if(bean.getPost_id()!=100){
			//存储浮动工资规则
			List<WmsInveWagerulesFloating> wmsInveWageRulesFloatinglist =JsonUtil.jsonArrayToList(fdgz, WmsInveWagerulesFloating.class);
			resStr=judgeFloatRules(wmsInveWageRulesFloatinglist);
			if(resStr.equals("success")){
				for(WmsInveWagerulesFloating wFloating:wmsInveWageRulesFloatinglist){
					wFloating.setWms_inve_wagerules_head_id(bean.getWms_inve_wagerules_head_id());
					//如果浮动考核 和 浮动考核范围 为-1 将不会存储
					if(wFloating.getFloating_type()!=-1 && wFloating.getFloating_scope() !=-1 && wFloating.getFloating_type()!=null && wFloating.getFloating_scope() !=null){
						ret=wmsInveWagerulesFloatingDao.save(wFloating);					
					}
				}
			}
		}
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	/**
	 * Description :通过传递不同的职务获取所有不同的职务所有配置信息
	 * @param queryInfo 传递参数 职务
	 * @return List
	 * @author hancd
	 */	
	@Override
	public Map<String, Object> getWmsInveWageRulesList(WmsInveWagerulesHeadSearchBeanVO queryInfo) {
		Map<String,Object> paramMap = new HashMap<>();
		if(StringUtil.isNotBlank(queryInfo.getPost_id())){
			paramMap.put("post_id", queryInfo.getPost_id());
		}
		paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
	    List<Map<String,Object>> list = wmsinvewagerulesheadDao.search(paramMap); 
	    GridDataBean<Map<String,Object>> bean = new 
	                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvewagerulesheadDao.findCount(paramMap),list);
	    return bean.getGridData();		
	}
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveWagerulesHeadSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvewagerulesheadDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveWagerulesHeadSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvewagerulesheadDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvewagerulesheadDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveWagerulesHead getInfoByPK(Integer wms_inve_wagerules_head_id) {
		return wmsinvewagerulesheadDao.get(wms_inve_wagerules_head_id);
	}
	/**
	 * Description :更新理财工资规则
	 * @param bean contains pk at least
	 * @param fdgz 浮动工资列表
	 * @return "success" or "error" or user defined
	 * @author hancd
	 */
	@Override
	@Transactional
	public String update(WmsInveWagerulesHead bean, UserBean user,String fdgz) {
		String resStr = "success";
		int ret = 0;
		//如果传递过来的是禁用
		if(bean.getPage_rule_state().equals("1")){
			setWmsInveWagerulesHead(bean,user,2);
		}
		//如果传递过来的是请选择或者是启用
		else if(bean.getPage_rule_state().equals("-1") || bean.getPage_rule_state().equals("0"))
		{
			WmsInveWagerulesHead wageHead = new WmsInveWagerulesHead();
			wageHead.setEnable_flag("1");
			wageHead.setRule_state(0);
			List<WmsInveWagerulesHead> list = wmsinvewagerulesheadDao.getListByRepeat(wageHead);
			for(WmsInveWagerulesHead w:list){
				if(w.getPost_id()==bean.getPost_id() && w.getDept_id().equals(bean.getDept_id())){
					return "gzrepeat";
				}
			}
			bean.setLast_update_user_id(user.getUserId());
			bean.setLast_update_datetime(new Timestamp(System.currentTimeMillis()));
			if(bean.getPage_rule_state().equals("0")){
				bean.setStart_date(setDate(new Date(System.currentTimeMillis())));
				bean.setStop_date(null);
			}
			//根据主表ID，查询浮动工资设置
			WmsInveWagerulesFloating wFloating = new WmsInveWagerulesFloating();
			wFloating.setWms_inve_wagerules_head_id(bean.getWms_inve_wagerules_head_id());
			List<WmsInveWagerulesFloating> wmsinvewagerulefloatlist=wmsInveWagerulesFloatingDao.getListByEntity(wFloating);
			resStr=judgeFloatRules(wmsinvewagerulefloatlist);
			//删除之前的浮动工资信息
			for(WmsInveWagerulesFloating w:wmsinvewagerulefloatlist){
				wmsInveWagerulesFloatingDao.deleteByInfo(w.getWms_inve_wagerules_floating_id());
			}
			if(resStr.equals("success")){
				//判断是客户经理规则修改还是团队经理和见习经理
				if(bean.getPost_id() !=100){
					List<WmsInveWagerulesFloating> rulefloatlist = JsonUtil.jsonArrayToList(fdgz, WmsInveWagerulesFloating.class);
					//存储新定义的规则
					for(WmsInveWagerulesFloating w:rulefloatlist){
						w.setWms_inve_wagerules_head_id(bean.getWms_inve_wagerules_head_id());
						wmsInveWagerulesFloatingDao.save(w);
					}
				}
			}
		}
		ret = wmsinvewagerulesheadDao.update(bean); 
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	/**
	 * Description :通过传递工资表主键信息获取相关基本信息和浮动工资信息设置
	 * @param wms_inve_wagerules_head_id
	 * @return record list
	 * @author hancd
	 */
	@Override
	public Map<String, Object> getInveWageRulesHFbypk(Integer wms_inve_wagerules_head_id) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
	    WmsInveWagerulesHead wmsHead = wmsinvewagerulesheadDao.get(wms_inve_wagerules_head_id);//获取工资主表信息
	    paramMap.put("wmsHead", wmsHead);
	    WmsInveWagerulesFloating wFloating = new WmsInveWagerulesFloating();
	    wFloating.setWms_inve_wagerules_head_id(wms_inve_wagerules_head_id);
	    List<WmsInveWagerulesFloating> wagerulesFloatingList =wmsInveWagerulesFloatingDao.getListByEntity(wFloating);
	    paramMap.put("wFloating", wagerulesFloatingList);
		return paramMap;		
	}
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveWagerulesHead() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveWagerulesHead> getListByEntity(WmsInveWagerulesHead queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveWagerulesHead> beanList = wmsinvewagerulesheadDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * 存储理财工资配置的基本信息
	 * @param bean
	 * @param user
	 * @return
	 */
	private WmsInveWagerulesHead setWmsInveWagerulesHead(WmsInveWagerulesHead bean,UserBean user,Integer flag)
	{
		java.sql.Date date=setDate(new Date(System.currentTimeMillis()));
		Timestamp systime =new Timestamp(System.currentTimeMillis());
		if(flag==1){
			bean.setCreate_datetime(systime);
			bean.setEnable_flag("1");
			bean.setCreate_user_id(user.getUserId());
			bean.setCreate_user_name(user.getRealname());
			if(bean.getDept_id()==null){
				bean.setDept_id(-1);
			}
			//启用时间
			if(bean.getPage_rule_state().equals("0")){
				bean.setStart_date(date);
			}
			//禁用时间
			else if(bean.getPage_rule_state().equals("1"))
			{
				bean.setStop_date(date);
			}
		}else if(flag==2){
			bean.setLast_update_user_id(user.getUserId());
			bean.setLast_update_datetime(systime);
			if(bean.getPage_rule_state().equals("1")){
				bean.setStart_date(null);
				bean.setStop_date(date);
			}
		}
		return bean;
	}
	//设置启用和禁用时间  至启用或禁用日期 下个月底 才算生效
	private java.sql.Date setDate(Date date){
		java.sql.Date date1;
        Calendar cdDay = new GregorianCalendar();
        cdDay.setTime(date);
        cdDay.add(Calendar.MONTH, 1);
        cdDay.set(Calendar.DAY_OF_MONTH, cdDay.getActualMaximum(Calendar.DAY_OF_MONTH));//本月最后一天  
        java.util.Date date_ = cdDay.getTime();
        date1 = new java.sql.Date(date_.getTime());
        return date1;
	} 
	//设置判断浮动考核范围是否正确
	private String  judgeFloatRules(List<WmsInveWagerulesFloating> wmsinvewagerulefloatlist){
		String resStr="success";
		List<WmsInveWagerulesFloating> wfloating1 = new ArrayList<WmsInveWagerulesFloating>();
		List<WmsInveWagerulesFloating> wfloating2 = new ArrayList<WmsInveWagerulesFloating>();
		List<WmsInveWagerulesFloating> wfloating3 = new ArrayList<WmsInveWagerulesFloating>();
		List<WmsInveWagerulesFloating> wfloating4 = new ArrayList<WmsInveWagerulesFloating>();
		List<WmsInveWagerulesFloating> wfloating5 = new ArrayList<WmsInveWagerulesFloating>();
		List<WmsInveWagerulesFloating> wfloating6 = new ArrayList<WmsInveWagerulesFloating>();
		for(int i=0;i<wmsinvewagerulefloatlist.size();i++){
			//存量0  个人0
			if(wmsinvewagerulefloatlist.get(i).getFloating_type()==0 && wmsinvewagerulefloatlist.get(i).getFloating_scope()==0){
				wfloating1.add(wmsinvewagerulefloatlist.get(i));
			}
			//存量0  团队1
			if(wmsinvewagerulefloatlist.get(i).getFloating_type()==0 && wmsinvewagerulefloatlist.get(i).getFloating_scope()==1){
				wfloating2.add(wmsinvewagerulefloatlist.get(i));
			}
			//净增1  个人0
			if(wmsinvewagerulefloatlist.get(i).getFloating_type()==1 && wmsinvewagerulefloatlist.get(i).getFloating_scope()==0){
				wfloating3.add(wmsinvewagerulefloatlist.get(i));
			}
			//净增1  团队1
			if(wmsinvewagerulefloatlist.get(i).getFloating_type()==1 && wmsinvewagerulefloatlist.get(i).getFloating_scope()==1){
				wfloating4.add(wmsinvewagerulefloatlist.get(i));
			}
			//本月新增2 个人0
			if(wmsinvewagerulefloatlist.get(i).getFloating_type()==2 && wmsinvewagerulefloatlist.get(i).getFloating_scope()==0){
				wfloating5.add(wmsinvewagerulefloatlist.get(i));
			}
			//本月新增2  团队1
			if(wmsinvewagerulefloatlist.get(i).getFloating_type()==2 && wmsinvewagerulefloatlist.get(i).getFloating_scope()==1){
				wfloating6.add(wmsinvewagerulefloatlist.get(i));
			}
		}
		//判断存量 和 个人 考核范围限制
		for(int i=0;i<wfloating1.size();i++){
			WmsInveWagerulesFloating wi = wfloating1.get(i);
			for(int j=i+1;j<wfloating1.size();j++){
				WmsInveWagerulesFloating wj =wfloating1.get(j);
				if(wj.getCumulate_begin().compareTo(wi.getCumulate_end())<0){
					return "wfloating1Error";
				}
			}
		}
		for(int i=0;i<wfloating2.size();i++){
			WmsInveWagerulesFloating wi = wfloating2.get(i);
			for(int j=i+1;j<wfloating2.size();j++){
				WmsInveWagerulesFloating wj =wfloating2.get(j);
				if(wj.getCumulate_begin().compareTo(wi.getCumulate_end())<0){
					return "wfloating2Error";
				}
			}
		}
		for(int i=0;i<wfloating3.size();i++){
			WmsInveWagerulesFloating wi = wfloating3.get(i);
			for(int j=i+1;j<wfloating3.size();j++){
				WmsInveWagerulesFloating wj =wfloating3.get(j);
				if(wj.getCumulate_begin().compareTo(wi.getCumulate_end())<0){
					return "wfloating3Error";
				}
			}
		}
		for(int i=0;i<wfloating4.size();i++){
			WmsInveWagerulesFloating wi = wfloating4.get(i);
			for(int j=i+1;j<wfloating4.size();j++){
				WmsInveWagerulesFloating wj =wfloating4.get(j);
				if(wj.getCumulate_begin().compareTo(wi.getCumulate_end())<0){
					return "wfloating4Error";
				}
			}
		}
		for(int i=0;i<wfloating5.size();i++){
			WmsInveWagerulesFloating wi = wfloating5.get(i);
			for(int j=i+1;j<wfloating5.size();j++){
				WmsInveWagerulesFloating wj =wfloating5.get(j);
				if(wj.getCumulate_begin().compareTo(wi.getCumulate_end())<0){
					return "wfloating5Error";
				}
			}
		}
		for(int i=0;i<wfloating6.size();i++){
			WmsInveWagerulesFloating wi = wfloating6.get(i);
			for(int j=i+1;j<wfloating6.size();j++){
				WmsInveWagerulesFloating wj =wfloating6.get(j);
				if(wj.getCumulate_begin().compareTo(wi.getCumulate_end())<0){
					return "wfloating6Error";
				}
			}
		}
		return resStr;
	}
	
}
