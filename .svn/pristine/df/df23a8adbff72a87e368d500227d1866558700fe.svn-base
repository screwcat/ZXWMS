package com.zx.emanage.inve.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveCommissionCategoryNewDao;
import com.zx.emanage.inve.persist.WmsInveCommissionFloatingNewDao;
import com.zx.emanage.inve.persist.WmsInveCommissionGeneralDisposableRulesNewDao;
import com.zx.emanage.inve.persist.WmsInveCommissionGeneralMonthlyRulesNewDao;
import com.zx.emanage.inve.persist.WmsInveCommissionGeneralNetRulesNewDao;
import com.zx.emanage.inve.persist.WmsInveCommissionGeneralRulesNewDao;
import com.zx.emanage.inve.service.IWmsInveCommissionGeneralRulesNewService;
import com.zx.emanage.inve.vo.WmsInveCommissionGeneralRulesNewSearchBeanVO;
import com.zx.emanage.sysmanage.persist.WmsSysDictDataDao;
import com.zx.emanage.util.gen.entity.WmsInveCommissionCategoryNew;
import com.zx.emanage.util.gen.entity.WmsInveCommissionFloatingNew;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralDisposableRulesNew;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralMonthlyRulesNew;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralNetRulesNew;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralRulesNew;
import com.zx.emanage.util.gen.entity.WmsSysDictData;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecommissiongeneralrulesnewService")
public class WmsInveCommissionGeneralRulesNewServiceImpl implements IWmsInveCommissionGeneralRulesNewService {
	private static Logger log = LoggerFactory.getLogger(WmsInveCommissionGeneralRulesNewServiceImpl.class);

	@Autowired
	private WmsInveCommissionGeneralRulesNewDao wmsinvecommissiongeneralrulesnewDao;
	
	@Autowired
	private WmsInveCommissionGeneralDisposableRulesNewDao wmsInveCommissionGeneralDisposableRulesNewDao;
	
	@Autowired
	private WmsInveCommissionGeneralNetRulesNewDao wmsInveCommissionGeneralNetRulesNewDao;
	
	@Autowired
	private WmsInveCommissionGeneralMonthlyRulesNewDao wmsInveCommissionGeneralMonthlyRulesNewDao;
	
	@Autowired
	private WmsInveCommissionFloatingNewDao wmsInveCommissionFloatingNewDao;

	@Autowired
	private WmsInveCommissionCategoryNewDao wmsInveCommissionCategoryNewDao;
	
	@Autowired
	private WmsSysDictDataDao wmsSysDictDataDao;
	
	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveCommissionGeneralRulesNewSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvecommissiongeneralrulesnewDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
    public Map<String, Object> getListWithPaging(WmsInveCommissionGeneralRulesNewSearchBeanVO queryInfo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvecommissiongeneralrulesnewDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvecommissiongeneralrulesnewDao.findCount(paramMap),list);
        return bean.getGridData();          
    }
	
	@Override
	public WmsInveCommissionGeneralRulesNew getInfoByPK(Integer wms_inve_commission_general_rules_new_id) {
		return wmsinvecommissiongeneralrulesnewDao.get(wms_inve_commission_general_rules_new_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveCommissionGeneralRulesNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneralrulesnewDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveCommissionGeneralRulesNew bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvecommissiongeneralrulesnewDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveCommissionGeneralRulesNew() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveCommissionGeneralRulesNew> getListByEntity(WmsInveCommissionGeneralRulesNew queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveCommissionGeneralRulesNew> beanList = wmsinvecommissiongeneralrulesnewDao.getListByEntity(queryInfo);
		return beanList;
	}
	
	/**
     * Description :获取一般佣金规则所属公司下拉列表id87  --产品对应人员页面中也有使用 获取人员分组信息 字典表id89 baisong
     * @param WmsInveCommissionGeneralNetRulesNewSearchBeanVO
     * @return map
     * @author wangyihan
     * @date 2015/12/11
     */
    @Override
    public Map<String, Object> getBelongCompanyList(WmsInveCommissionGeneralRulesNewSearchBeanVO queryInfo) {
        log.debug("--------------------获取一般佣金规则所属公司下拉列表处理开始--------------------");
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_dict_id", queryInfo.getWms_sys_dict_id());
        List<WmsSysDictData> list = wmsSysDictDataDao.getDictData(paramMap);
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("belong_company_list", list);
        
        log.debug("--------------------获取一般佣金规则所属公司下拉列表处理结束--------------------");
        return resultMap;
    }
	
	/**
     * Description :获取一般佣金规则信息-根据beanvo中参数不同获取不同类型的信息
     * @param WmsInveCommissionGeneralNetRulesNewSearchBeanVO
     * @return map
     * @author wangyihan
     * @date 2015/12/11
     */
    @Override
    public Map<String, Object> getInfo(WmsInveCommissionGeneralRulesNewSearchBeanVO queryInfo) {
        log.debug("--------------------获取一般佣金规则信息处理开始--------------------");
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("job_code", queryInfo.getJob_code());//职务配置职务ID
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> beanList = wmsinvecommissiongeneralrulesnewDao.getList(paramMap);
        
        Integer tempWms_inve_commission_general_rules_new_id = 0;
        String ids = "";//id
        String names = "";//name
        
        Map<String, Object> paramMapYXC_One = new HashMap<String, Object>();//上单当月给予一次性佣金
        paramMapYXC_One.put("data_type", "1");
        Map<String, Object> paramMapYXC_Two = new HashMap<String, Object>();//上单满...个月再按照...
        paramMapYXC_Two.put("data_type", "2");
        Map<String, Object> paramMapYXC = new HashMap<String, Object>();
        Map<String, Object> paramMapYF = new HashMap<String, Object>();
        Map<String, Object> paramMapCP = new HashMap<String, Object>();
        Map<String, Object> paramMapFD = new HashMap<String, Object>();
        
        if(beanList != null && beanList.size() > 0){
            for(int i = 0;i < beanList.size(); i++){
                
                tempWms_inve_commission_general_rules_new_id = Integer.valueOf(beanList.get(i).get("wms_inve_commission_general_rules_new_id").toString());
                paramMapYXC_One.put("wms_inve_commission_general_rules_new_id", tempWms_inve_commission_general_rules_new_id);
                paramMapYXC_Two.put("wms_inve_commission_general_rules_new_id", tempWms_inve_commission_general_rules_new_id);
                paramMapYXC.put("wms_inve_commission_general_rules_new_id", tempWms_inve_commission_general_rules_new_id);
                paramMapYF.put("wms_inve_commission_general_rules_new_id", tempWms_inve_commission_general_rules_new_id);
                paramMapCP.put("wms_inve_commission_general_rules_new_id", tempWms_inve_commission_general_rules_new_id);
                paramMapFD.put("wms_inve_commission_general_rules_new_id", tempWms_inve_commission_general_rules_new_id);
                
                //客户/见习/团队查询一次性佣金(上单当月给予一次性佣金)
                beanList.get(i).put("disposable_one", 
                        wmsInveCommissionGeneralDisposableRulesNewDao.getList(paramMapYXC_One));
                
                //客户/见习/团队查询一次性佣金(上单满...个月再按照...)
                beanList.get(i).put("disposable_two", 
                        wmsInveCommissionGeneralDisposableRulesNewDao.getList(paramMapYXC_Two));
                
                //客户/见习/团队查询一次性佣金(页面最下面的)
                beanList.get(i).put("disposable", 
                        wmsInveCommissionGeneralNetRulesNewDao.getList(paramMapYXC));
                
                //客户/见习/团队查询月付佣金
                beanList.get(i).put("parammonthly", 
                        wmsInveCommissionGeneralMonthlyRulesNewDao.getList(paramMapYF));
                
                //往list里插入产品id
                ids = "";//id
                names = "";//name
                List<Map<String, Object>> list = wmsInveCommissionCategoryNewDao.getList(paramMapCP);
                for(int g = 0;g < list.size(); g++){//处理id name
                    if(g == 0){
                        ids+= list.get(g).get("wms_inve_pruduct_category_id");
                        names+= list.get(g).get("category_name");    
                    }else{
                        ids+= "," + list.get(g).get("wms_inve_pruduct_category_id");
                        names+= "," + list.get(g).get("category_name");
                    }
                }
                
                beanList.get(i).put("ids", ids);
                beanList.get(i).put("names", names);
                
                //见习/团队查询浮动佣金
                if(queryInfo.getJob_code() == 101 || queryInfo.getJob_code() == 102){//101团队经理 102见习经理
                    beanList.get(i).put("commissionfloating", 
                            wmsInveCommissionFloatingNewDao.getList(paramMapFD));
                }   
            }
        }
        
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>
            (queryInfo.getPage(), wmsinvecommissiongeneralrulesnewDao.findCountList(paramMap), beanList);
        
        log.debug("--------------------获取一般佣金规则信息处理结束--------------------");
        return bean.getGridData();
    }
    
    /**
     * Description :保存规则信息
     * @param UserBean WmsInveCommissionGeneralRulesSearchBeanVO WmsInveCommissionGeneralRules
     * @return String
     * @author wangyihan
     * @date 2015/12/12
     */ 
    @Override
    @Transactional
    public String saveRules(UserBean user, WmsInveCommissionGeneralRulesNew bean, WmsInveCommissionGeneralRulesNewSearchBeanVO beanVO) {
        log.debug("--------------------保存规则信息处理开始--------------------");
        
        String resStr = "success";
        Timestamp systemtime = new Timestamp(System.currentTimeMillis());
        
        if(beanVO.getCategory_ids() != null && beanVO.getCategory_names() != null){
            String [] ids = beanVO.getCategory_ids().split(",");//产品id
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("employee_state", bean.getEmployee_state());//员工状态
            map.put("job_code", bean.getJob_code());//职务配置职务ID
            map.put("list", Arrays.asList(ids));//产品id
            map.put("wms_inve_commission_general_rules_new_id", bean.getWms_inve_commission_general_rules_new_id());
            
            if(bean.getBelonging_company().intValue() != 1){//不是全部分公司(可以定义相同产品)
                map.put("belonging_company_same", bean.getBelonging_company());
            }
            
            List<Map<String, Object>> listmap = wmsInveCommissionCategoryNewDao.isCheck(map);
            if(listmap != null && listmap.size() > 0){
                resStr = "errorid";
                return resStr;//产品重复-当前产品存在规则
            }
        }
        
        //-1 代表请选择 0代表启用 1代表禁用
        if(bean.getPage_rule_sate() != null){
            if(bean.getPage_rule_sate().intValue() == 0){//0:启用
                bean.setStart_date(new Date(System.currentTimeMillis()));//启用时间
            }else if(bean.getPage_rule_sate().intValue() == 1){//1:禁用
                bean.setStop_date(new Date(System.currentTimeMillis()));//禁用时间
            }
        }
        
        bean.setRule_state(Integer.valueOf(bean.getPage_rule_sate()));//状态
        
        if(bean != null){
            if(bean.getWms_inve_commission_general_rules_new_id() != null){//主键存在:update
                bean.setLast_update_datetime(systemtime);
                bean.setLast_update_user_id(user.getUserId());
                wmsinvecommissiongeneralrulesnewDao.update(bean);
            }else{//主键不存在:insert
                bean.setCreate_datetime(systemtime);
                bean.setCreate_user_id(user.getUserId());
                bean.setCreate_user_name(user.getRealname());
                bean.setEnable_flag("1");
                int ret = wmsinvecommissiongeneralrulesnewDao.save(bean);
                if (ret == 0) {
                    resStr = "error";
                    return resStr;
                } 
            }
        }
        
        List<WmsInveCommissionGeneralDisposableRulesNew> saveList = new ArrayList<WmsInveCommissionGeneralDisposableRulesNew>();
        wmsInveCommissionGeneralDisposableRulesNewDao.deletebykey(bean.getWms_inve_commission_general_rules_new_id());//删除一次性佣金原来表内容(上单当月给予一次性佣金与上单满...个月再按照...)
        if(StringUtils.isNotBlank(beanVO.getDisposable_one())){//一次性佣金(上单当月给予一次性佣金)
            List<WmsInveCommissionGeneralDisposableRulesNew> list = JsonUtil.jsonArrayToList(beanVO.getDisposable_one(), WmsInveCommissionGeneralDisposableRulesNew.class);
            for(WmsInveCommissionGeneralDisposableRulesNew disposableRules_one : list){
                if(disposableRules_one != null){
                    disposableRules_one.setWms_inve_commission_general_rules_new_id(bean.getWms_inve_commission_general_rules_new_id());//主表主键
                    disposableRules_one.setCreate_user_id(user.getUserId());
                    disposableRules_one.setCreate_user_name(user.getRealname());
                    disposableRules_one.setCreate_timestamp(systemtime);
                    disposableRules_one.setData_type("1");//数据类型 1为上单当月给予一次性佣金
                    disposableRules_one.setEnable_flag("1");
                    saveList.add(disposableRules_one);
                }
            }
        }
        if(StringUtils.isNotBlank(beanVO.getDisposable_one())){//一次性佣金(上单满...个月再按照...)
            List<WmsInveCommissionGeneralDisposableRulesNew> list = JsonUtil.jsonArrayToList(beanVO.getDisposable_two(), WmsInveCommissionGeneralDisposableRulesNew.class);
            for(WmsInveCommissionGeneralDisposableRulesNew disposableRules_two : list){
                if(disposableRules_two != null){
                    disposableRules_two.setWms_inve_commission_general_rules_new_id(bean.getWms_inve_commission_general_rules_new_id());//主表主键
                    disposableRules_two.setCreate_user_id(user.getUserId());
                    disposableRules_two.setCreate_user_name(user.getRealname());
                    disposableRules_two.setCreate_timestamp(systemtime);
                    disposableRules_two.setData_type("2");//数据类型 2为上单满...个月再按照...
                    disposableRules_two.setEnable_flag("1");
                    saveList.add(disposableRules_two);
                }
            }
        }
        wmsInveCommissionGeneralDisposableRulesNewDao.saveAll(saveList);
        
        wmsInveCommissionGeneralNetRulesNewDao.deletebykey(bean.getWms_inve_commission_general_rules_new_id());//删除一次性佣金原来表内容
        if(beanVO.getParammonthly() != null){//月付佣金
            List<WmsInveCommissionGeneralNetRulesNew> list = JsonUtil.jsonArrayToList(beanVO.getDisposable(), WmsInveCommissionGeneralNetRulesNew.class);
            for(WmsInveCommissionGeneralNetRulesNew disposableRules : list){
                if(disposableRules != null){//如果月付佣金为空则不保存
                    disposableRules.setWms_inve_commission_general_rules_new_id(bean.getWms_inve_commission_general_rules_new_id());//主表主键
                    disposableRules.setCreate_user_id(user.getUserId());
                    disposableRules.setCreate_user_name(user.getRealname());
                    disposableRules.setCreate_timestamp(systemtime);
                    disposableRules.setEnable_flag("1");
                    wmsInveCommissionGeneralNetRulesNewDao.save(disposableRules);
                }
            }
        }
        
        wmsInveCommissionGeneralMonthlyRulesNewDao.deletebykey(bean.getWms_inve_commission_general_rules_new_id());//删除月付佣金原来表内容
        if(beanVO.getParammonthly() != null){//月付佣金
            List<WmsInveCommissionGeneralMonthlyRulesNew> list = JsonUtil.jsonArrayToList(beanVO.getParammonthly(), WmsInveCommissionGeneralMonthlyRulesNew.class);
            for(WmsInveCommissionGeneralMonthlyRulesNew monthlyRules : list){
                if(monthlyRules != null){//如果月付佣金为空则不保存
                    monthlyRules.setWms_inve_commission_general_rules_new_id(bean.getWms_inve_commission_general_rules_new_id());//主表主键
                    monthlyRules.setCreate_user_id(user.getUserId());
                    monthlyRules.setCreate_user_name(user.getRealname());
                    monthlyRules.setCreate_timestamp(systemtime);
                    monthlyRules.setEnable_flag("1");
                    wmsInveCommissionGeneralMonthlyRulesNewDao.save(monthlyRules);
                }
            }
        }
        
        if(bean.getJob_code() == 101 || bean.getJob_code() == 102){//101团队经理 102见习经理
            wmsInveCommissionFloatingNewDao.deletebykey(bean.getWms_inve_commission_general_rules_new_id());//删除原来表内容
            if(beanVO.getCommission_floating() != null){//浮动佣金
                List<WmsInveCommissionFloatingNew> list = JsonUtil.jsonArrayToList(beanVO.getCommission_floating(), WmsInveCommissionFloatingNew.class);
                for(WmsInveCommissionFloatingNew commissionFloat : list){
                    if(commissionFloat != null && commissionFloat.getCommission_type() != null){//如果佣金规则为空则不保存
                        commissionFloat.setWms_inve_commission_general_rules_new_id(bean.getWms_inve_commission_general_rules_new_id());//主表主键
                        wmsInveCommissionFloatingNewDao.save(commissionFloat);
                    }
                }
            }
        }
        
        wmsInveCommissionCategoryNewDao.deleteAll(bean.getWms_inve_commission_general_rules_new_id());//删除全部相关产品数据
        if(beanVO.getCategory_ids() != null && beanVO.getCategory_names() != null){//判断产品信息
            String [] ids = beanVO.getCategory_ids().split(",");//产品id
            String [] names = beanVO.getCategory_names().split(",");//产品名称
            if(ids.length > 0 && !ids[0].equals("")){
                List<WmsInveCommissionCategoryNew> categorylist = new ArrayList<WmsInveCommissionCategoryNew>();
                WmsInveCommissionCategoryNew wmsInveCommissionCategoryNew = new WmsInveCommissionCategoryNew();//相关产品表
                for(int i = 0;i < ids.length; i++){
                    wmsInveCommissionCategoryNew = new WmsInveCommissionCategoryNew();
                    wmsInveCommissionCategoryNew.setWms_inve_commission_general_rules_new_id(bean.getWms_inve_commission_general_rules_new_id());//主表主键
                    wmsInveCommissionCategoryNew.setWms_inve_pruduct_category_id(Integer.valueOf(ids[i]));//产品id
                    wmsInveCommissionCategoryNew.setCategory_name(names[i]);//产品name
                    categorylist.add(wmsInveCommissionCategoryNew);
                }
                wmsInveCommissionCategoryNewDao.saveAll(categorylist);//相关产品批量新增
            }
        }
        
        log.debug("--------------------保存规则信息处理结束--------------------");
        return resStr;
    }
	
}
