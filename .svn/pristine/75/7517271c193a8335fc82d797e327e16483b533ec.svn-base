package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveDebtHeadDao;
import com.zx.emanage.inve.persist.WmsInveDebtOlnclaimsDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchBackupsDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.service.IWmsInveDebtHeadService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.vo.WmsInveDebtHeadSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.web.WmsInveDebtOlnclaimsController;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysPostDao;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.SysPost;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.entity.SysUserRole;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsInveDebtHead;
import com.zx.emanage.util.gen.entity.WmsInveDebtOlnclaims;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvedebtheadService")
public class WmsInveDebtHeadServiceImpl implements IWmsInveDebtHeadService {
	private static Logger log = LoggerFactory.getLogger(WmsInveDebtHeadServiceImpl.class);

	@Autowired
	private WmsInveDebtHeadDao wmsinvedebtheadDao;
	
	@Autowired
	private IWmsInveWorkFlowService wmsInveWorkFlowService;
	
	@Autowired
	private WmsInveTransaDao wmsinvetransaDao;
	
	@Autowired
	private SysSpecialUserDao sysSpecialUserDao;

    @Autowired
    private PmPersonnelDao pmPersonnelDao;
    
    @Autowired
    private SysPostDao sysPostDao;
    
    @Autowired
    private WmsInveDebtOlnclaimsDao wmsInveDebtOlnclaimsDao;
    
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    
    @Autowired
    private WmsInveTransaProtocolDao wmsinvetransaprotocolDao;
    
    @Autowired 
    private WmsInveTransaMatchDao wmsinvetransamatchDao;
    
    @Autowired
    WmsInveTransaLogDao wmsInveTransaLogDao;
    
    @Autowired
    private WmsInveDebtOlnclaimsDao wmsinvedebtolnclaimsDao;
    
    @Autowired
    private WmsInveTransaMatchDao wmsInveTransaMatchDao;
    
    @Autowired
    private WmsFinaCreRepayDao wmsFinaCreRepayDao;
    
    @Autowired
    private WmsInveTransaMatchBackupsDao wmsInveTransaMatchBackupsDao;
    /**
	 * Description :【债权变动确认】列表显示
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	@Override
	public Map<String, Object> getListWithPaging(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS,user.getUserId().toString(), "4");
		if(StringUtil.isNotBlank(queryInfo.getCus_name())) {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
		if(StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1")) {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
		if(StringUtil.isNotBlank(queryInfo.getId_card())) {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())) {
	        paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
	    }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end())) {
	        paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	    }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Integer> data_statusList = new ArrayList<Integer>();
        paramMap.put("data_statusList", null);
        List<Map<String,Object>> list = wmsinvedebtheadDao.searchList(paramMap); 
        //加流程
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"),(String)paramMap.get("processDefinitionKey"));
        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvedebtheadDao.findListCount(paramMap),list);
		return bean.getGridData();			
	}
	/**
	 * Description :债权变动确认获取列表 导出
	 * @param queryInfo
	 * @return record list
	 * @author lvtu
	 */
    @Override
	public Map<String, Object> getListWithoutPaging(WmsInveDebtHeadSearchBeanVO queryInfo,UserBean user) {
    	
    	Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS,user.getUserId().toString(), "4");
		if(StringUtil.isNotBlank(queryInfo.getCus_name())) {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
		if(StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1")) {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
		if(StringUtil.isNotBlank(queryInfo.getId_card())) {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())) {
	        paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
	    }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end())) {
	        paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	    }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Integer> data_statusList = new ArrayList<Integer>();
        paramMap.put("data_statusList", null);
        List<Map<String,Object>> list = wmsinvedebtheadDao.searchList(paramMap); 
	    SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;	
	}
	/**
	 * Description :【债权调整】/【协议签订】列表显示
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	@Override
	public Map<String, Object> getListPrintProtocol (WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS,user.getUserId().toString(), "5");
		if(StringUtil.isNotBlank(queryInfo.getCus_name())) {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
		if(StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1")) {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
		if(StringUtil.isNotBlank(queryInfo.getId_card())) {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())) {
	        paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
	    }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end())) {
	        paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	    }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Integer> data_statusList = new ArrayList<Integer>();
    	data_statusList.add(8);
    	data_statusList.add(9);
    	paramMap.put("data_statusList", data_statusList);        	
        List<Map<String,Object>> list = wmsinvedebtheadDao.searchList(paramMap); 
        //加流程
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"),(String)paramMap.get("processDefinitionKey"));
        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvedebtheadDao.findListCount(paramMap),list);
		return bean.getGridData();			
	}
	/**
	 * Description :【债权调整】/【协议签订】列表导出
	 * @param queryInfo
	 * @return record list
	 * @author jiaodelong
	 */
	@Override
	public Map<String, Object> getWithoutListPrintProtocol(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS,user.getUserId().toString(), "5");
		if(StringUtil.isNotBlank(queryInfo.getCus_name())) {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
		if(StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1")) {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
		if(StringUtil.isNotBlank(queryInfo.getId_card())) {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())) {
	        paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
	    }
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end())) {
	        paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	    }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
    	List<Integer> data_statusList = new ArrayList<Integer>();
       	data_statusList.add(8);
       	data_statusList.add(9);
       	paramMap.put("data_statusList", data_statusList);        	
        List<Map<String,Object>> list = wmsinvedebtheadDao.searchList(paramMap); 
        //屏蔽身份证号和手机号码
        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;
	}
	/**
	 *  获取【债权变动申请】列表数据显示
	 */
	@Override
	public Map<String, Object> getDebtInfoWithPagingList(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//理财产品 精准
		if(StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1")) {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
		//客户姓名 模糊
		if(StringUtil.isNotBlank(queryInfo.getCus_name())){
			paramMap.put("cus_name",SysUtil.getSqlLikeParam(queryInfo.getCus_name()));
		}
		//身份证号 模糊
		if(StringUtil.isNotBlank(queryInfo.getId_card())){
			paramMap.put("id_card",SysUtil.getSqlLikeParam(queryInfo.getId_card()));
		}
		//时间精准查询
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())){
			paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
		}
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end())){
			paramMap.put("create_timestamp_end",queryInfo.getCreate_timestamp_end());
		}
		paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("pagesize", queryInfo.getPagesize());
	    paramMap.put("offset", queryInfo.getOffset());
	    //判断
        SysUserRole sysr = new SysUserRole();
        sysr.setUser_id(user.getUserId());
        List<SysUserRole> listsysur=sysUserRoleDao.getListByEntity(sysr);
        boolean flag=false;
        for(SysUserRole sysuserrole:listsysur){
	    	if(sysuserrole.getRole_id() ==150){
	    		flag=true;
	    		break;
	    	}
        }
        if(flag){
        	 paramMap.put("key", 1);
        }else{
        	 paramMap.put("key", 2);
        }
	    SysSpecialUser sys = new SysSpecialUser();
	    sys.setEnable_flag("1");
	    //存入未处理的身份证
        List<Map<String,Object>> list = wmsinvedebtheadDao.getCusOriginalClaims(paramMap);
        for(int i = 0;i<list.size();i++){
        	Map<String,Object> map = list.get(i);
        	map.put("id_card_str",map.get("id_card"));
        }
        list = SysTools.setListView(list, user, sysSpecialUserDao.getListByEntity(sys));
        int findcount=wmsinvedebtheadDao.findCountCusOriginalClaims(paramMap);
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(),findcount,list);
		return bean.getGridData();			
	}
	/**
	 * Description :获取【债权变动申请】列表数据导出
	 * @param queryInfo
	 * @return Map
	 * @author yangqiyu
	 */
	@Override
	public Map<String, Object> getDebtInfoWithoutPagingList(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(StringUtil.isNotBlank(queryInfo.getCategory_name())&& !queryInfo.getCategory_name().equals("-1")){
			paramMap.put("category_name", queryInfo.getCategory_name());
		}
		//客户姓名 模糊
		if(StringUtil.isNotBlank(queryInfo.getCus_name())){
			paramMap.put("cus_name",SysUtil.getSqlLikeParam(queryInfo.getCus_name()));  
		}
		//身份证号 模糊
		if(StringUtil.isNotBlank(queryInfo.getId_card())){
			paramMap.put("id_card",SysUtil.getSqlLikeParam(queryInfo.getId_card()));
		}
		//时间精准查询
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())){
			paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
		}
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end())){
			paramMap.put("create_timestamp_end",queryInfo.getCreate_timestamp_end());
		}
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    //判断
        SysUserRole sysr = new SysUserRole();
        sysr.setUser_id(user.getUserId());
        List<SysUserRole> listsysur=sysUserRoleDao.getListByEntity(sysr);
        boolean flag=false;
        for(SysUserRole sysuserrole:listsysur){
	    	if(sysuserrole.getRole_id() ==150){
	    		flag=true;
	    		break;
	    	}
        }
        if(flag){
        	 paramMap.put("key", 1);
        }else{
        	 paramMap.put("key", 2);
        }
        List<Map<String, Object>> list = wmsinvedebtheadDao.getCusOriginalClaims(paramMap);
        SysSpecialUser sys = new SysSpecialUser();
        sys.setEnable_flag("1");
        list = SysTools.setListView(list, user, sysSpecialUserDao.getListByEntity(sys));
        paramMap.clear();
        paramMap.put("Rows", list);
        return paramMap;
	}
	/**
	 * 【债权变动申请】  保存操作
	 *  @param bean
	 *  @param user
	 *  @param beanJson
	 *  @return String
	 *  @author yangqiyu
	 */
	@Override	
	@Transactional
	public String save(WmsInveDebtHead bean, UserBean user,String beanJson) {
		String resStr = "success";
		
		int ret = 0;
		
		bean.setBill_code(CodeNoUtil.getDebtCode());//获取债权变动申请单编号

		bean.setCreate_user_id(user.getUserId());//获取申请人ID
		
		bean.setCreate_dept_id(user.getDeptId());//获取申请人部门ID
		
		bean.setEnable_flag("1");//设定数据有效性
		
		bean.setCreate_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));//设定调整单据创建时间
		
		//存储债权变更申请单信息
		ret = wmsinvedebtheadDao.save(bean);
		//获取原始债权信息列表
		List<WmsInveDebtOlnclaims> olnlist = JsonUtil.jsonArrayToList(beanJson,WmsInveDebtOlnclaims.class);
		for(WmsInveDebtOlnclaims wmsInveDebtOlnclaims:olnlist){
			wmsInveDebtOlnclaims.setCreate_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
			wmsInveDebtOlnclaims.setCreate_user_id(user.getUserId());
			wmsInveDebtOlnclaims.setEnable_flag("1");
			wmsInveDebtOlnclaims.setOlnclaims("1");
			wmsInveDebtOlnclaims.setWms_inve_debt_head_id(bean.getWms_inve_debt_head_id());
			wmsInveDebtOlnclaimsDao.save(wmsInveDebtOlnclaims);
		}
		//发起流程申请
		WmsInveTransa wmsinvetransa=wmsinvetransaDao.get(bean.getWms_inve_transa_id());
		resStr=wmsInveWorkFlowService.startFinancialWorkFlow(user.getUserId().toString(), bean.getWms_inve_debt_head_id().toString(), wmsinvetransa.getSalesman_dept_id().toString(), wmsinvetransa.getSalesman_id().toString(),WorkflowConstantHelp.DEBTADJUSTMENTPROCESS);
		if(ret == 0){
			resStr = "error";
		}else{
			//修改上单信息表数据状态
			WmsInveTransa t = new WmsInveTransa();
			t.setWms_inve_transa_id(bean.getWms_inve_transa_id());
			t.setData_status("9");
			wmsinvetransaDao.update(t);
		}
		return resStr;
	}
	/**
	 * Description :根据上单产品id获取债权匹配信息
	 * @param wms_inve_transa_prod_id
	 * @return  Map<String,Object>
	 * @author yangqiyu
	 */
	@Override
	public Map<String, Object> getMatchInfoByProd(Integer wms_inve_transa_prod_id,UserBean user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("wms_inve_transa_prod_id", wms_inve_transa_prod_id);
		map.put("wms_inve_redeem_id", 0);//0 为有效  其他值为无效
		List<Map<String,Object>> list = wmsinvedebtheadDao.getMatchInfoByProd(map);
		for(int i =0;i<list.size();i++){
			Map<String,Object> maps = list.get(i);
			maps.put("id_card_str",maps.get("credit_id_card"));
		}
		SysSpecialUser sys = new SysSpecialUser();
		sys.setEnable_flag("1");
		list = SysTools.setListView(list, user, sysSpecialUserDao.getListByEntity(sys));
		map.clear();
		map.put("Rows",list);
		return map;
	}
	/**
	 * 财务管理-理财债权变动-债权变动审核 显示
	 * @param Map<String,Object>
	 * @return Map<String,Object>
	 * @author yangqiyu
	 */
	@Override
	public Map<String, Object> getAllFromWmsInveDebtHead(WmsInveDebtHeadSearchBeanVO queryInfo,UserBean user) {
		//进入流程
		Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS,user.getUserId().toString(), "1");
		//客户姓名 模糊
		if(StringUtil.isNotBlank(queryInfo.getCus_name())){
			paramMap.put("cus_name",SysUtil.getSqlLikeParam(queryInfo.getCus_name()));
		}
		//理财产品 精准
		if(StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1")){
			paramMap.put("category_name",queryInfo.getCategory_name());
		}
		//身份证号 模糊
		if(StringUtil.isNotBlank(queryInfo.getId_card())){
			paramMap.put("id_card",SysUtil.getSqlLikeParam(queryInfo.getId_card()));
		}
		//时间精准查询
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())){
			paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
		}
		if(StringUtil.isNotEmpty(queryInfo.getCreate_timestamp_end()) && StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())){
			paramMap.put("create_timestamp_end",queryInfo.getCreate_timestamp_end());
		}	
		paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("pagesize", queryInfo.getPagesize());
	    paramMap.put("offset", queryInfo.getOffset());
	    List<Map<String,Object>> list = wmsinvedebtheadDao.getAllFromWmsInveDebtHead(paramMap);
	    int findcount=wmsinvedebtheadDao.findCountAllFromWmsInveDebtHead(paramMap);
	    list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"),(String)paramMap.get("processDefinitionKey"));
	    SysSpecialUser sys = new SysSpecialUser();
        sys.setEnable_flag("1");
        list = SysTools.setListView(list, user, sysSpecialUserDao.getListByEntity(sys));
       
	    GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(),findcount,list);
		return bean.getGridData();			
	}
	/**
	 * 财务管理-理财债券变动-债权变动审核 
	 * 通过查询条件进行页面数据导出
	 * @param queryInfo
	 * @param request
	 * @return map
	 * @author yangqiyu
	 */
	@Override
	public Map<String, Object> getAllWithoutWmsInveDebtHead(
			WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS,user.getUserId().toString(), "1");
		if(StringUtil.isNotBlank(queryInfo.getCus_name())){
			paramMap.put("cus_name",SysUtil.getSqlLikeParam(queryInfo.getCus_name()));
		}
		if(StringUtil.isNotBlank(queryInfo.getCategory_name())&& !queryInfo.getCategory_name().equals("-1")){
			paramMap.put("category_name",queryInfo.getCategory_name());
		}      
		if(StringUtil.isNotBlank(queryInfo.getId_card())){
			paramMap.put("id_card",SysUtil.getSqlLikeParam(queryInfo.getId_card()));
		}
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())){
			paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
		}
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end())){
			paramMap.put("create_timestamp_end",queryInfo.getCreate_timestamp_end());
		}	
		paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("pagesize", queryInfo.getPagesize());
	    paramMap.put("offset", queryInfo.getOffset());
	    List<Map<String,Object>> list = wmsinvedebtheadDao.getAllFromWmsInveDebtHead(paramMap);
	    list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"),(String)paramMap.get("processDefinitionKey"));
        list =setListView(list, user);
        paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;			
	}
	/**
	 * 将变更修订内容导出到Excel
	 * @param Map<String,Object>
	 * @return Map<String,Object>
	 * @author yangqiyu
	 */
	@Override
	public Map<String, Object> getAllFromWmsInveDebtHeadToExcel(WmsInveDebtHeadSearchBeanVO queryInfo,UserBean user) {
		Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS,user.getUserId().toString(), "1");
		//客户姓名 模糊
		if(StringUtil.isNotBlank(queryInfo.getCus_name())){
			paramMap.put("cus_name",SysUtil.getSqlLikeParam(queryInfo.getCus_name()));
		}
		//理财产品 精准
		if(StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1")){
			paramMap.put("category_name",queryInfo.getCategory_name());
		}
		//身份证号 模糊
		if(StringUtil.isNotBlank(queryInfo.getId_card())){
			paramMap.put("id_card",SysUtil.getSqlLikeParam(queryInfo.getId_card()));
		}
		//时间精准查询
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())){
			paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
		}
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end())){
			paramMap.put("create_timestamp_end",queryInfo.getCreate_timestamp_end());
		}
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvedebtheadDao.getAllFromWmsInveDebtHead(paramMap);
        SysSpecialUser sys = new SysSpecialUser();
        sys.setEnable_flag("1");
        list = SysTools.setListView(list, user, sysSpecialUserDao.getListByEntity(sys));
        paramMap.clear();
        paramMap.put("Rows", list);
        return paramMap;
	}
	/**
	 * 【债权变更修订】取消修订 
	 * @param wms_inve_debt_head_id
	 * @param taskId
	 * @param user
	 * @author yangqiyu
	 */
	@Override
	@Transactional
	public String giveUpWmsInveDebtHead(String wms_inve_debt_head_id,String taskId,UserBean user) {
		String result = "success";
		//更改理财单据的状态
		WmsInveDebtHead wmsInveDebtHead =wmsinvedebtheadDao.get(Integer.valueOf(wms_inve_debt_head_id));
		WmsInveTransa wmsInveTransa = new WmsInveTransa();
		wmsInveTransa.setWms_inve_transa_id(wmsInveDebtHead.getWms_inve_transa_id());
		wmsInveTransa.setData_status("4");
		wmsinvetransaDao.update(wmsInveTransa);
		//启用取消修订
		WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
		wDebtWorkFlowVO.setPass("false");
		wDebtWorkFlowVO.setAdvice("已终止");
		wDebtWorkFlowVO.setTaskId(taskId);
		wDebtWorkFlowVO.setUserID(user.getUserId().toString());
		wDebtWorkFlowVO.setDebtkey("4");
		wDebtWorkFlowVO.setBusinessId(wms_inve_debt_head_id);
		wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
		return result;
	}
	/**
	 * 【债权变更修订】修订提交功能 
	 * @param
	 * @author Administrator
	 */
	@Override
	@Transactional
	public String  updateClaims(WmsInveDebtHead wmsInveDebtHead,UserBean user,String taskId){	
		String result = "success";
		Map<String,Object>paMap = new HashMap<>();
		paMap.put("wms_inve_debt_head_id", wmsInveDebtHead.getWms_inve_debt_head_id());
		paMap.put("debt_reason", wmsInveDebtHead.getDebt_reason());
		paMap.put("key", "1");
		wmsinvedebtheadDao.updateRecord(paMap);
		//调用流程
		WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
		wDebtWorkFlowVO.setDebtkey("4");
		WmsInveTransa wmsInveTransa =wmsinvetransaDao.get(wmsinvedebtheadDao.get(wmsInveDebtHead.getWms_inve_debt_head_id()).getWms_inve_transa_id());
		wDebtWorkFlowVO.setCxdept_id(wmsInveTransa.getSalesman_dept_id().toString());//原始单据部门ID
		wDebtWorkFlowVO.setTaskId(taskId);
		wDebtWorkFlowVO.setPass("true");
		wDebtWorkFlowVO.setUserID(user.getUserId().toString());
		wDebtWorkFlowVO.setBusinessId(wmsInveDebtHead.getWms_inve_debt_head_id().toString());
		wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
		return result;
	}
	/**
	 * 实现债权调整三级审批的审批信息提交保存操作
	 * @param wmsInveDebtWorkFlowVO 
	 * @param UserBean 
	 * @return String
	 * @author yangqiyu
	 */
	@Override
	@Transactional
	public String approvalOpinion(WmsInveDebtWorkFlowVO wmsInveDebtWorkFlowVO,UserBean user) {
		String result="success";
		String deptkey = "";
		//根据当前登录人锁定职务表主键，查询相对应的职务编码
		PmPersonnel pm = pmPersonnelDao.get(user.getUserId());
		SysPost sysPost =sysPostDao.get(pm.getPersonnel_postid());
		if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.jl"))||sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.jxtdjl"))){
			deptkey="1";
		}else if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.fzjl"))){
			deptkey="2";
		}else if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.zjl"))){
			deptkey="3";
		}else{
			return "error";
		}
		wmsInveDebtWorkFlowVO.setDebtkey(deptkey);
		wmsInveDebtWorkFlowVO.setUserID(user.getUserId().toString());
		wmsInveDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS);
		try{
			wmsInveWorkFlowService.publicApproval(wmsInveDebtWorkFlowVO);
			result="success";
		}catch(Exception e){
			log.error(e.getMessage());
			result="error";
		}
		return result;
	}
	
	/**
	 * 特批申请,债权匹配、打印协议 全部公用一个方法
	 */
	@Override
	@Transactional
	public String approvalSave(WmsInveTransaProtocol Protocolbean, WmsInveDebtHead bean, UserBean user, String type, String taskId) {
		String resStr = "success";
		int ret = 0;
		WmsInveDebtHead debthead = wmsinvedebtheadDao.get(bean.getWms_inve_debt_head_id());
		if("special".equals(type)){//处理特批申请
			bean.setIs_sp_app(1);//是否特批
			bean.setSp_app_operator_id(user.getUserId());
			bean.setSp_app_date(new Timestamp(System.currentTimeMillis()));
			ret = wmsinvedebtheadDao.update(bean);
			//推送流程操作
			WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
			wDebtWorkFlowVO.setPass(bean.getSp_app_result().equals("1")?"true":"false");
			wDebtWorkFlowVO.setAdvice(bean.getSp_app_advice());
			wDebtWorkFlowVO.setTaskId(taskId);
			wDebtWorkFlowVO.setUserID(user.getUserId().toString());
			wDebtWorkFlowVO.setBusinessId(bean.getWms_inve_debt_head_id().toString());
			wDebtWorkFlowVO.setDebtkey("6");//代表处理特批申请单据
			wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS);
			wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
			
			//如果特批不通过，将原单据状态修改为收益中
			if("0".equals(bean.getSp_app_result())) {
				WmsInveTransa wmsInveTransa = new WmsInveTransa();
				wmsInveTransa.setWms_inve_transa_id(debthead.getWms_inve_transa_id());
				wmsInveTransa.setData_status("4");//收益中
				wmsinvetransaDao.update(wmsInveTransa);
			}
		}else if("normal".equals(type)){//处理财务确认
			bean.setIs_debtadj_confirm("1");//确认完毕
			ret = wmsinvedebtheadDao.update(bean); 
			//执行流程操作
			WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
			wDebtWorkFlowVO.setTaskId(taskId);
			wDebtWorkFlowVO.setUserID(user.getUserId().toString());
			wDebtWorkFlowVO.setBusinessId(bean.getWms_inve_debt_head_id().toString());
			wDebtWorkFlowVO.setDebtkey("5");//代表确认完毕
			wDebtWorkFlowVO.setPass("true");//代表同意通过
			wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS);
			wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
		}else if("xyqd".equals(type)){//处理打印协议
			bean.setIs_protocol_finish("1");//协议打印完毕
			ret = wmsinvedebtheadDao.update(bean);
			//把债权原始数据失效
			wmsinvetransamatchDao.updateRedeemByProdid(bean.getWms_inve_transa_prod_id());
			//把原始合同失效
			wmsinvetransaprotocolDao.updateProtocolRedeem(bean.getWms_inve_transa_prod_id());
//			//保存协议
			Protocolbean.setCreate_user_id(user.getUserId());
			Protocolbean.setCreate_user_name(user.getRealname());
			Protocolbean.setEnable_flag("1");
			Protocolbean.setWms_inve_redeem_id(0);
			Protocolbean.setCreate_timestamp(new Timestamp(new Date().getTime()));
			Protocolbean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
	        ret = wmsinvetransaprotocolDao.save(Protocolbean);
	        //插入日志
        	Map<String, Object> paramap = new HashMap<String, Object>();
        	paramap.put("wms_inve_transa_id", bean.getWms_inve_transa_id());
        	paramap.put("param_date", new Date());
//        	List<WmsInveTransaLog> wmsInveTransaLoglist = wmsInveTransaLogDao.getWmsInveTransaLog(paramap);
        	WmsInveTransaLog wmsInveTransaLog=null;
//        	if(wmsInveTransaLoglist != null&&wmsInveTransaLoglist.size()>0) {
//        		wmsInveTransaLog=wmsInveTransaLoglist.get(0);
//        		wmsInveTransaLog.setRemark("关联协议编号 &lt;" + Protocolbean.getProt_code() + "&gt;#" + Protocolbean.getWms_inve_transa_protocol_id()
//        				+ "#" + Protocolbean.getWms_inve_transa_id() + "#" + Protocolbean.getWms_inve_transa_prod_id());
//        		wmsInveTransaLogDao.update(wmsInveTransaLog);
//        	}
//        	else{
        		//2015-12-3 处理年付
        		wmsInveTransaLog =new WmsInveTransaLog();
        		wmsInveTransaLog.setChange_date(new java.sql.Date(System.currentTimeMillis()));
        		wmsInveTransaLog.setWms_inve_transa_id(Protocolbean.getWms_inve_transa_id());
        		wmsInveTransaLog.setOperate_item("债权变动");
        		wmsInveTransaLog.setCreate_user_id(user.getUserId());
        		wmsInveTransaLog.setCreate_user_name(user.getRealname());
        		wmsInveTransaLog.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        		wmsInveTransaLog.setRemark("关联协议编号 &lt;" + Protocolbean.getProt_code() + "&gt;#" + Protocolbean.getWms_inve_transa_protocol_id()
        				+ "#" + Protocolbean.getWms_inve_transa_id() + "#" + Protocolbean.getWms_inve_transa_prod_id());
        		wmsInveTransaLogDao.save(wmsInveTransaLog);
//        	}
			
			
        	Map<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("wms_inve_debt_head_id", bean.getWms_inve_debt_head_id());
            hashMap.put("sortname", "");
            hashMap.put("sortorder", "");
            List<Map<String, Object>> list = wmsinvedebtolnclaimsDao.selectOlnclaimsByWmsInveDebtHeadId(hashMap);
            WmsInveTransaMatch match = new WmsInveTransaMatch();
            WmsInveDebtOlnclaims olnclaimsTB = new WmsInveDebtOlnclaims();
            WmsFinaCreRepay wmsFinaCreRepay = new WmsFinaCreRepay();
            Integer matchId = 0;
            //插入债权匹配表(附带协议id)并更新债权变动副表信息(更新债权匹配表id)
			for(Map<String, Object> map : list){
				Integer olnclaims = Integer.parseInt(map.get("olnclaims").toString());//1代表原债权匹配结果 2代表新手动债权匹配结果
				if(olnclaims == 1){//原债权
					//将原债权占用的还款信息释放并更新金额
					//修改还款信息表可匹配的债权(matching_creditor), 加上原有债权的本次转让额度
		            wmsFinaCreRepay = wmsFinaCreRepayDao.get((Integer) map.get("wms_fina_cre_repay_id"));
		            wmsFinaCreRepay.setMatching_creditor(wmsFinaCreRepay.getMatching_creditor().add((BigDecimal) map.get("assign_account")));
		            wmsFinaCreRepay.setIs_occupy(0);//释放
		            wmsFinaCreRepay.setOccupants(0);//不被任何一方占用
		            wmsFinaCreRepayDao.update(wmsFinaCreRepay);
		            //删除原有匹配信息
		            //wmsInveTransaMatchDao.deleteById(match.getWms_inve_transa_match());
		            //备份
		            /*wmsInveTransaMatchBackupsDao.save(match);*/
				}
				if(olnclaims == 2){//新手动债权
					match = new WmsInveTransaMatch();
					match.setWms_inve_transa_prod_id((Integer) map.get("wms_inve_transa_prod_id"));
					match.setWms_fina_cre_repay_id((Integer) map.get("wms_fina_cre_repay_id"));
					match.setCredit_name(map.get("credit_name").toString());
					match.setCredit_id_card(map.get("credit_id_card").toString());
					match.setAssign_account((BigDecimal) map.get("assign_account"));
					match.setDate_of_assign((java.sql.Date) map.get("date_of_assign"));
					match.setRepay_begin_date((java.sql.Date) map.get("repay_begin_date"));
					match.setRepay_end_date((java.sql.Date) map.get("repay_end_date"));
					match.setProduct_interest_month((BigDecimal) map.get("product_interest_month"));
					match.setCreate_user_id((Integer) map.get("create_user_id"));
					match.setProtocol_code(map.get("protocol_code").toString());
					match.setCreate_timestamp((Timestamp) map.get("create_timestamp"));
					match.setLast_update_user_id(user.getUserId());
					match.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
					match.setEnable_flag("1");
					match.setWms_inve_redeem_id(0);
					match.setWms_inve_transa_protocol_id(Protocolbean.getWms_inve_transa_protocol_id());//插入生成的协议id
					wmsInveTransaMatchDao.save(match);
					
					olnclaimsTB = new WmsInveDebtOlnclaims();
					olnclaimsTB.setWms_inve_debt_olnclaims_id((Integer) map.get("wms_inve_debt_olnclaims_id"));
					olnclaimsTB.setWms_inve_transa_match_id(match.getWms_inve_transa_match());
					wmsinvedebtolnclaimsDao.update(olnclaimsTB);
					//处理还款信息表数据
					wmsFinaCreRepay = wmsFinaCreRepayDao.get((Integer) map.get("wms_fina_cre_repay_id"));
					//可匹配债权-本次匹配的债权额度
		            wmsFinaCreRepay.setMatching_creditor(wmsFinaCreRepay.getMatching_creditor().subtract((BigDecimal) map.get("assign_account")));
		            //贷款产品1、信贷产品  2、房贷产品 （以后增加类别后再进行完善）
		            if("1".equals(wmsFinaCreRepay.getCre_type())){//判断是否是信贷产品
		            	 wmsFinaCreRepay.setIs_occupy(0);//释放
				         wmsFinaCreRepay.setOccupants(0);//不被任何一方占用
		            }
		            wmsFinaCreRepayDao.update(wmsFinaCreRepay);
				}
			}
            
			//将原始合同(协议)变为失效
//			wmsinvetransaprotocolDao.setTransaProtocolFailure(debthead.getWms_inve_transa_id());
			//单据状态修改为收益中
			WmsInveTransa wmsInveTransa = new WmsInveTransa();
			wmsInveTransa.setWms_inve_transa_id(bean.getWms_inve_transa_id());
			wmsInveTransa.setData_status("4");
			wmsinvetransaDao.update(wmsInveTransa);
		    //执行流程操作
			WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setTaskId(taskId);
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wDebtWorkFlowVO.setBusinessId(bean.getWms_inve_debt_head_id().toString());
            wDebtWorkFlowVO.setDebtkey("8");//代表协议签订
            wDebtWorkFlowVO.setPass("true");//代表同意通过
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS);
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
		}else if("zqtz".equals(type)){//处理债权调整
            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setTaskId(taskId);
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wDebtWorkFlowVO.setBusinessId(bean.getWms_inve_debt_head_id().toString());
            wDebtWorkFlowVO.setDebtkey("7");//代表债权调整
            wDebtWorkFlowVO.setPass("true");//代表同意通过
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS);
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
		}
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	/**
	 * 获取债权变动申请原始债权和手动匹配债权以及债权申请单数据
	 */
	@Override
	public Map<String, Object> getDebtInfoList(Integer wms_inve_debt_head_id,
			UserBean user,String olnclaims) {
		Map<String,Object> paraMap = new HashMap<>();
 		WmsInveDebtHead wDebtHead = wmsinvedebtheadDao.get(wms_inve_debt_head_id);
		WmsInveDebtOlnclaims debtOlnclaims = new WmsInveDebtOlnclaims();
		debtOlnclaims.setEnable_flag("1");
		debtOlnclaims.setWms_inve_debt_head_id(wms_inve_debt_head_id);
		debtOlnclaims.setOlnclaims(olnclaims);
		List<Map<String,Object>> debtOlist =wmsInveDebtOlnclaimsDao.getListByMap(debtOlnclaims);
		
		SysSpecialUser sys = new SysSpecialUser();
        sys.setEnable_flag("1");
        debtOlist = SysTools.setListView(debtOlist, user, sysSpecialUserDao.getListByEntity(sys));
		
		paraMap.put("wDebtHead", wDebtHead);
		paraMap.put("Rows", debtOlist);
		return paraMap;
	}
	/**
	 * 债权调整【修订】列表显示
	 * @param queryInfo
	 * @param user
	 * @return Map<String,Object>
	 * @author yangqiyu
	 */
	@Override
	public Map<String, Object> getUpdateWmsInveDebtHead(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS,user.getUserId().toString(), "2");
		//产品id转义
		if(StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1")) {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
		//客户姓名 模糊
		if(StringUtil.isNotBlank(queryInfo.getCus_name())){
			paramMap.put("cus_name",SysUtil.getSqlLikeParam(queryInfo.getCus_name()));
		}
		//身份证号 模糊
		if(StringUtil.isNotBlank(queryInfo.getId_card())){
			paramMap.put("id_card",SysUtil.getSqlLikeParam(queryInfo.getId_card()));
		}
		//时间精准查询
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())){
			paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
		}
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end())){
			paramMap.put("create_timestamp_end",queryInfo.getCreate_timestamp_end());
		}
		paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvedebtheadDao.getUpdateWmsInveDebtHead(paramMap);
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"),(String)paramMap.get("processDefinitionKey"));
        SysSpecialUser sys = new SysSpecialUser();
        sys.setEnable_flag("1");
        list = SysTools.setListView(list, user, sysSpecialUserDao.getListByEntity(sys));
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvedebtheadDao.findListCount(paramMap),list);
        return bean.getGridData();
	}
	
	private List<Map<String,Object>> setListView(List<Map<String,Object>>list,UserBean user){
		SysSpecialUser specialUser = new SysSpecialUser();
		specialUser.setEnable_flag("1");
		return SysTools.setListView(list, user,sysSpecialUserDao.getListByEntity(specialUser));
	}
	
	/**
	 * 债权调整【修订】导出到Excel
	 * @param queryInfo
	 * @param user
	 * @return Map<String,Object>
	 * @author yangqiyu
	 */
	@Override
	public Map<String, Object> getUpdateWmsInveDebtHeadExcel(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS,user.getUserId().toString(), "2");
		//产品id转义
		if(StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1")) {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
		//客户姓名 模糊
		if(StringUtil.isNotBlank(queryInfo.getCus_name())){
			paramMap.put("cus_name",SysUtil.getSqlLikeParam(queryInfo.getCus_name()));
		}
		//身份证号 模糊
		if(StringUtil.isNotBlank(queryInfo.getId_card())){
			paramMap.put("id_card",SysUtil.getSqlLikeParam(queryInfo.getId_card()));
		}
		//时间精准查询
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin())){
			paramMap.put("create_timestamp_begin",queryInfo.getCreate_timestamp_begin());
		}
		if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()) && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end())){
			paramMap.put("create_timestamp_end",queryInfo.getCreate_timestamp_end());
		}
		paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvedebtheadDao.getUpdateWmsInveDebtHead(paramMap);
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"),(String)paramMap.get("processDefinitionKey"));
        list =setListView(list, user);
        paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
        return resMap;
	}
	
	/**
	 * 实现查看债权调整流程历程
	 */
	@Override
	public Map<String, Object> getApprovalProcessView(String wms_inve_debt_head_id) {
		return wmsInveWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS,wms_inve_debt_head_id);
	}
	
	@Override
	@Transactional
	public String update(WmsInveDebtHead bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvedebtheadDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	@Override
	public WmsInveDebtHead getInfoByPK(Integer wms_inve_debtadjust_head_id) {
		return wmsinvedebtheadDao.get(wms_inve_debtadjust_head_id);
	}
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveDebtHead() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveDebtHead> getListByEntity(WmsInveDebtHead queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveDebtHead> beanList = wmsinvedebtheadDao.getListByEntity(queryInfo);
		return beanList;
	}
	
}
