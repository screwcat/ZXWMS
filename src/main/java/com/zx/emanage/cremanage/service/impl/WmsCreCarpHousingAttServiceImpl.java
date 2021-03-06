package com.zx.emanage.cremanage.service.impl;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zx.emanage.cremanage.persist.WmsCreCarpHousingAttDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingApplyAttDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingCustomerHouseDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingFileInfoDao;
import com.zx.emanage.cremanage.service.IWmsCarLoanWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreCarpHousingAttService;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsApplyInfoSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreCarpHousingAttSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsMoaHouseInfoVO;
import com.zx.emanage.cremanage.vo.WmsSearchHosuingVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysConcurrentPostDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.sysmanage.util.UserCityUtil;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCreCarpHousingAtt;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineHouseinfo;
import com.zx.emanage.util.gen.entity.WmsCreHousingApplyAtt;
import com.zx.emanage.util.gen.entity.WmsCreHousingCustomerHouse;
import com.zx.emanage.util.gen.entity.WmsCreHousingFileInfo;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * @ClassName: WmsCreCarpHousingAttServiceImpl
 * @Description: 内容摘要：房贷附件
 * @author baisong
 * @date 2016年11月23日
 * @version V1.0
 * history:
 * 1、2016年11月23日 baisong 创建文件
 */

@Service("wmscrecarphousingattService")
public class WmsCreCarpHousingAttServiceImpl implements IWmsCreCarpHousingAttService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpHousingAttServiceImpl.class);
	@Autowired
    private WmsCreCreditHeadDao wmscrecreditheadDao;
	@Autowired
	private IWmsCarLoanWorkFlowService wmsCarLoanWorkFlowService;
	
	@Autowired
	private WmsCreCarpHousingAttDao wmscrecarphousingattDao;
	@Autowired
	private IWmsCarLoanWorkFlowService carLoanWorkFlowService;//流程
	
	@Autowired
	private   PmPersonnelDao pmPersonnelDao;
    
    @Autowired
    private WmsCreHousingFileInfoDao wmsCreHousingFileInfoDao;//上传信息表
    @Autowired
    private WmsCreHousingApplyAttDao wmsCreHousingApplyAttDao;//附件表

    @Autowired
    private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程
    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;
    @Autowired
    private IWorkflowService workflowService;
    @Autowired
    private WmsCreHousingCustomerHouseDao wmsCreHousingCustomerHouseDao;//房贷——抵押房产信息wms_cre_housing_customer_house
    @Autowired
    private  WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;//贷款管理——客户信息变更表wms_cre_credit_line_customer_change_head
    @Autowired
    private WmsCreCustomerChangeLineHouseinfoDao wmsCreCustomerChangeLineHouseinfoDao;//贷款管理——客户房产信息变更表wms_cre_customer_change_line_houseinfo
    @Autowired
    private SysRoleDao sysroleDao_m;
    @Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;
    @Autowired
    private SysConcurrentPostDao sysConcurrentPostDao;
    
    @Override
	public Map<String, Object> getListWithoutPaging(WmsCreCarpHousingAttSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
	    paramMap.put("data_type", queryInfo.getData_type());
	    List<Map<String,Object>>  list = wmscrecarphousingattDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreCarpHousingAttSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrecarphousingattDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecarphousingattDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCarpHousingAtt getInfoByPK(Integer wms_cre_carp_housing_att_id) {
		return wmscrecarphousingattDao.get(wms_cre_carp_housing_att_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCarpHousingAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarphousingattDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
	//车贷合同补件
	@Override	
	@Transactional
	public String save( UserBean user,WmsCreCarpHousingAttSearchBeanVO beanvo) {
		String resStr = "success";
		int ret = 0;
		List<WmsCreCarpHousingAtt> attachment = JsonUtil.jsonArrayToList(beanvo.getFileArr(), WmsCreCarpHousingAtt.class); // 前台json附件路径数据转换为list
		 for (int i = 0; i < attachment.size(); i++)
	        {
			 	WmsCreCarpHousingAtt bean = attachment.get(i);
			 	bean.setWms_cre_credit_head_id(new Integer(beanvo.getWms_cre_credit_head_id_id()));
			 	bean.setData_type(beanvo.getData_type());
			 	bean.setCreate_user_id(user.getUserId());
				bean.setCreate_user_name(user.getRealname());
				bean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
				bean.setLast_update_user_id(user.getUserId());
				bean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
				bean.setEnable_flag("1");
				ret = wmscrecarphousingattDao.save(bean);
				if (ret == 0) {
					resStr = "error";
					return resStr;
				}
	        }
		if(beanvo.getTaskId()!=null&&!"".equals(beanvo.getTaskId())){
			 WmsCarLoanWorkFlowVO wVo=new WmsCarLoanWorkFlowVO();//流程
			 //给流程赋值
	         wVo.setWms_cre_credit_head_id(beanvo.getWms_cre_credit_head_id());
	         wVo.setPass("true");//是否通过
	         wVo.setAdvice("通过");//意见
	         wVo.setUserId(String.valueOf(user.getUserId()));//操作人id
	         wVo.setTaskId(beanvo.getTaskId());//流程节点id
	        //carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
	        carLoanWorkFlowService.carLoanApprovalProcess(wVo,"7");
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCarpHousingAtt bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarphousingattDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCarpHousingAtt() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCarpHousingAtt> getListByEntity(WmsCreCarpHousingAtt queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCarpHousingAtt> beanList = wmscrecarphousingattDao.getListByEntity(queryInfo);
		return beanList;
	}

	/**
	 * 贷款复核
	 */
	public String doSQSave(WmsCarLoanWorkFlowVO wmsCarLoanWorkFlowVO, String fileArr, UserBean user) {
		String result = "OK";
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        List<WmsCreCarpHousingAtt> Lattachment = JsonUtil.jsonArrayToList(fileArr, WmsCreCarpHousingAtt.class); // 前台json附件路径数据转换为list
        /*----------------------------------------------WmsPostLoanMigration资料附件�?begin----------------------------------------------*/
        // 附件先更新再添加
        WmsCreCarpHousingAtt mplmDropThisMig = new WmsCreCarpHousingAtt();
        mplmDropThisMig.setWms_cre_credit_head_id(Integer.valueOf(wmsCarLoanWorkFlowVO.getWms_cre_credit_head_id()));
        mplmDropThisMig.setEnable_flag("0");// 是否有效
        wmscrecarphousingattDao.update(mplmDropThisMig);
        // 删除�?��附件
        Map<String, Object> deletAtt = new HashMap<>();
        deletAtt.put("wms_cre_credit_head_id", Integer.valueOf(wmsCarLoanWorkFlowVO.getWms_cre_credit_head_id()));
        wmscrecarphousingattDao.deleteRecords(deletAtt);
        // 更新后添加附�?
        for (int i = 0; i < Lattachment.size(); i++)
        {
        	WmsCreCarpHousingAtt mplm = Lattachment.get(i);
            mplm.setWms_cre_credit_head_id(Integer.valueOf(wmsCarLoanWorkFlowVO.getWms_cre_credit_head_id()));
            mplm.setCreate_user_id(user.getUserId()); // 创建人id
            mplm.setCreate_user_name(user.getRealname());// 创建人名�?
            mplm.setCreate_timestamp(sysTime);// 创建时间
            mplm.setEnable_flag("1");// 是否有效
            wmscrecarphousingattDao.save(mplm);
        }
        /*----------------------------------------------WmsPostLoanMigration资料附件�?end----------------------------------------------*/
        // 保存复核意见到主表单
        if ("false".equals(wmsCarLoanWorkFlowVO.getPass()))
        {
            Map<String, Object> parmmap = new HashMap<String, Object>();
            parmmap.put("wms_cre_credit_head_id", wmsCarLoanWorkFlowVO.getWms_cre_credit_head_id());
            parmmap.put("check_back_remark", wmsCarLoanWorkFlowVO.getAdvice());
            wmscrecreditheadDao.updateRecord(parmmap);
        }
        // 流程�?��
        wmsCarLoanWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
        wmsCarLoanWorkFlowService.carLoanApprovalProcess(wmsCarLoanWorkFlowVO, "1");
        return result;
	}

	@Override
    public String doSQSaveBatch(String wms_credit_head_ids, String taskids, String wmsCreditLimits,
                                    String wmsCreditCreLoanTypes, String pass, String advice, String user_id)
    {
        String result = "OK";
        String[] ids = wms_credit_head_ids.split(",");
        String[] taskIds = taskids.split(",");
        String[] creditlimits = wmsCreditLimits.split(",");
        String[] creditloantypes = wmsCreditCreLoanTypes.split(",");
        for (int i = 0; i < ids.length; i++)
        {
        	WmsCarLoanWorkFlowVO wmsCarLoanWorkFlowVO = new WmsCarLoanWorkFlowVO();
            wmsCarLoanWorkFlowVO.setWms_cre_credit_head_id(Integer.parseInt(ids[i]));
            wmsCarLoanWorkFlowVO.setTaskId(taskIds[i]);
            wmsCarLoanWorkFlowVO.setPass(pass);
            wmsCarLoanWorkFlowVO.setAdvice(advice);
            wmsCarLoanWorkFlowVO.setUserId(user_id);
            wmsCarLoanWorkFlowService.carLoanApprovalProcess(wmsCarLoanWorkFlowVO, "1");
        }
        return result;
    }
	/**
	 * moa 调用wms方法 申请 办件 补件等
	 * @param request
	 * @param wmsCarLoanWorkFlowVO
	 * @param fileArr
	 * @return 
	 */
	@Override
	@Transactional
	public Map<String, Object> wmsMoaSave(WmsMoaHouseInfoVO bean) {
		Map<String,Object> map=new HashMap<>();
    	PmPersonnel pmPersonnel=new PmPersonnel();
    	pmPersonnel.setPersonnel_id(Integer.valueOf(bean.getPersonnel_id()));
    	List<PmPersonnel> listp=pmPersonnelDao.getListByEntity(pmPersonnel);
    	if(listp==null||listp.size()==0){
    		map.put("result", "error");
	        map.put("message", "获取审批人调用失败！");
	        map.put("flag", false);
	        return map;
    	}
    	List<WmsCreHousingApplyAtt> list= new ArrayList<>(); 
    	if(bean.getList()!=null){
    		list=JsonUtil.jsonArrayToList(bean.getList(), WmsCreHousingApplyAtt.class);
    	}
        Date date = new Date(System.currentTimeMillis());
        Timestamp now = new Timestamp(date.getTime());
    	if("1".equals(bean.getFalg())){//业务员申请
    		WmsCreCreditHead wHead=new WmsCreCreditHead();//主表
    		wHead.setSalesman_id(listp.get(0).getPersonnel_id());//业务员id
    		wHead.setSalesman_code(listp.get(0).getPersonnel_code());//业务员code
    		wHead.setSalesman_shortcode(listp.get(0).getPersonnel_shortcode());//业务员shortcode
    		wHead.setSalesman_name(listp.get(0).getPersonnel_name());//姓名
    		wHead.setSalesman_city_code(listp.get(0).getPersonnel_regionnumber());//区域编码
    		wHead.setSalesman_dept_id(listp.get(0).getPersonnel_deptid());//部门id
            //新增城市编码替换
    		wHead.setCity(UserCityUtil.getUserCity(listp.get(0).getPersonnel_regionnumber()));
    		wHead.setCre_type("2");//贷款产品
    		wHead.setBill_status("A");//草稿
    		wHead.setEnable_flag("1");//enable_flag
    		wHead.setBill_code(CodeNoUtil.getHouseCreCode());//单据编号
    		wHead.setIs_xudai("0");
    		wHead.setCre_loan_type(-1);
    		wHead.setCreate_timestamp(now);// 创建时间
    		wHead.setCreate_user_city_code(listp.get(0).getPersonnel_regionnumber());//创建人区域编码
            // 是否有流程
            wHead.setIs_workflow("1");
    		wmscrecreditheadDao.saveByPk(wHead); 
    		  // 抵押房产信息
           /* WmsCreHousingCustomerHouse wmshch = new WmsCreHousingCustomerHouse();
            wmshch.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());
            wmshch.setWms_cre_customer_change_line_houseinfo_id(0);
            wmshch.setHouse_use("");
            wmshch.setCreate_user_id(listp.get(0).getPersonnel_id()); // 创建人id
            wmshch.setCreate_user_name(listp.get(0).getPersonnel_name());// 创建人名�?
            wmshch.setCreate_timestamp(now);// 创建时间
            wmsCreHousingCustomerHouseDao.save(wmshch);*/
            
    		WmsCreHousingFileInfo wmsCreHousingFileInfo = new WmsCreHousingFileInfo();
    		wmsCreHousingFileInfo.setBill_code(wHead.getBill_code());
            wmsCreHousingFileInfo.setCreate_timestamp(now);
            wmsCreHousingFileInfo.setCreate_user_id(listp.get(0).getPersonnel_id());
            wmsCreHousingFileInfo.setCreate_user_name(listp.get(0).getPersonnel_name());
            wmsCreHousingFileInfo.setEnable_flag("1");
            wmsCreHousingFileInfo.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());
            wmsCreHousingFileInfo.setBill_status(0);//单据状态 ：0表示未生成贷款申请单1表示已经生成贷款申请单'
            wmsCreHousingFileInfo.setSalesman_shortcode(listp.get(0).getPersonnel_shortcode());
            wmsCreHousingFileInfoDao.save(wmsCreHousingFileInfo);
            for(WmsCreHousingApplyAtt att:list){
            	att.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());
            	att.setWms_cre_housing_file_info_id(wmsCreHousingFileInfo.getWms_cre_housing_file_info_id());
            	wmsCreHousingApplyAttDao.save(att);
            }
    	}else if("2".equals(bean.getFalg())){//办件人员办件
    		WmsCreCreditHead wHead=new WmsCreCreditHead();//主表
    		wHead.setWms_cre_credit_head_id(Integer.valueOf(bean.getWms_cre_credit_head_id()));
    		wHead.setHouse_appro_user_id(listp.get(0).getPersonnel_id());//办件人id
    		wHead.setHouse_appro_user_name(listp.get(0).getPersonnel_name());//办件人姓名
    		wHead.setHouse_appro_timestamp(now);//办件时间
    		wHead.setHouse_appro_result("1");;//办件结果
    		wHead.setHouse_appro_advice("手机办件 同意");//办件意见
    		wHead.setHouse_appro_result_page("1");//办件审批结果展示
    		wmscrecreditheadDao.updateforhouse(wHead);       
            for(WmsCreHousingApplyAtt att:list){
            	att.setWms_cre_credit_head_id(Integer.valueOf(bean.getWms_cre_credit_head_id()));
            	wmsCreHousingApplyAttDao.save(att);
            }
            Map<String, Object> paramMap=new HashMap<>();
    		paramMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
    		paramMap.put("hprocess_time", WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TIME);
    		paramMap=wmscrecreditheadDao.searchWmsCreCreditHeadByMap(paramMap);
    		WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
	        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);// 房贷key
	        workflowSearchInfoHelp.setBusinessKey(bean.getWms_cre_credit_head_id());
	        workflowSearchInfoHelp.setUserId(listp.get(0).getPersonnel_id().toString());
	        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_BJSP);
	        // 根据条件查找代办任务信息
	        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
	    	if(workflowInfoHelps==null||workflowInfoHelps.size()==0){
	    		map.put("result", "error");
		        map.put("message", "获取流程错误！");
		        map.put("flag", false);
		        return map;
	    	}
	        WmsHouseCreditWorkFlowVO vo =new WmsHouseCreditWorkFlowVO();
    		vo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
    		vo.setPass("true");
    		vo.setUserId(listp.get(0).getPersonnel_id().toString());
    		vo.setTaskId(workflowInfoHelps.get(0).getTaskId());
    		vo.setAdvice("手机办件 同意");
    		 // <!--房贷流程版本号 1为老流程  2为新流程-->
    		if("2".equals(paramMap.get("edition_num").toString())){//新流程
    			vo.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
            	vo.setDebtkey("4");
            	wmsLoanWorkFlowService.publicApproval(vo);
        	}else if("1".equals(paramMap.get("edition_num").toString())){//旧流程
        		houseCreditWorkFlowService.doapprovalHouseWorkFlow(vo);
        	}
    	}else if("3".equals(bean.getFalg())){//补件
    		   for(WmsCreHousingApplyAtt att:list){
	               	att.setWms_cre_credit_head_id(Integer.valueOf(bean.getWms_cre_credit_head_id()));
	               	wmsCreHousingApplyAttDao.save(att);
               }
    	}else{
    		map.put("result", "error");
	        map.put("message", "找不到对应的参数！");
	        map.put("flag", false);
	        return map;
    	}
    	map.put("result", "success");
        map.put("message", "成功！");
        map.put("flag", true);
        return map;
	}

	@Override
	public Map<String, Object> getHosuingIdList(String personnel_id) {
	    Map<String, Object> paramMap = new HashMap<>();
	    		//houseCreditWorkFlowService.getIdListWorkFlow(personnel_id,"2");
		paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,Integer.valueOf(personnel_id), "4",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程

		if (paramMap.get("idList") == null)
		{
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("idList", new ArrayList<Map<String, Object>>());
			return resultMap;
		}else{
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("idList",paramMap.get("idList"));
			return resultMap;
		}
	}
	/**
	 * moa 调用wms方法  获取单据的退件原因从流程历史中获取
	 * @param request
	 * @param param
	 * @param 
	 * @return Map<String,Object> 
	 */
	@Override
	public Map<String, Object> getHosuingWorkInfo(String param) {
		if(!"2".equals(param)){
			 Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlowNoUser(null,"12");//房贷旧流程
		     paramMap = wmsLoanWorkFlowService.setTaskListBJ(paramMap,null, "5");//合并俩个流程	
		     return paramMap;
		}else{
			 Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlowNoUser(null,"12");//房贷旧流程
		     paramMap = wmsLoanWorkFlowService.setTaskListVerTwo(paramMap,null, "0");//合并俩个流程	
		     return paramMap;
		}
       
	}
	
	///*************下面方法是给移动端第二版本使用开始*****************///
	/**
	 * moa 调用wms方法 查询列表 获取流程idlist 
	 * @param request
	 * @param wmsCarLoanWorkFlowVO
	 * @param personnel_id
	 * @param invekey
	 * @return 
	 */
	@Override
	public Map<String, Object> getHosuingIdList(WmsSearchHosuingVO wmsSearchHosuingVO) {
	    Map<String, Object> paramMap = new HashMap<>();
	    		//houseCreditWorkFlowService.getIdListWorkFlow(personnel_id,"2");
				//paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,Integer.valueOf(wmsSearchHosuingVO.getPersonnel_id()),wmsSearchHosuingVO.getInvekey(),WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
		paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,Integer.valueOf(wmsSearchHosuingVO.getPersonnel_id()),wmsSearchHosuingVO.getInvekey(),WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	

		if (paramMap.get("idList") == null)
		{
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("idList", new ArrayList<Map<String, Object>>());
			return resultMap;
		}else{
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("idList",paramMap.get("idList"));
			return resultMap;
		}
	}
	/**
	 * moa 调用wms方法 申请 补件等
	 * @param request
	 * @param wmsCarLoanWorkFlowVO
	 * @param fileArr
	 * @return 
	 */
	@Override
	@Transactional
	public Map<String, Object> wmsMoaSaveUp(WmsMoaHouseInfoVO bean,String customer_info) {
		Map<String,Object> map=new HashMap<>();
    	PmPersonnel pmPersonnel=new PmPersonnel();
    	pmPersonnel.setPersonnel_id(Integer.valueOf(bean.getPersonnel_id()));
    	List<PmPersonnel> listp=pmPersonnelDao.getListByEntity(pmPersonnel);
    	if(listp==null||listp.size()==0){
    		map.put("result", "error");
	        map.put("message", "获取审批人调用失败！");
	        map.put("flag", false);
	        return map;
    	}
    	pmPersonnel=listp.get(0);
    	//兼职部门
    	Map<String,Object> mapConPost=new HashMap<>();
    	mapConPost.put("personnel_id", bean.getPersonnel_id());
    	mapConPost.put("top_dept_code", WmsHelp.TOP_DEPT_CODE);//贷款端--DPHCPGLBXXX001 产品管理部   测试环境id=31 
    	List<Map<String,Object>> listmapConPost=sysConcurrentPostDao.searchList(mapConPost);
    	if(listmapConPost!=null &&listmapConPost.size()>0){
    		pmPersonnel.setDept_id(listmapConPost.get(0).get("dept_id").toString());
    	}
    	WmsApplyInfoSearchBeanVO  wmsApplyInfoSearchBeanVO=new WmsApplyInfoSearchBeanVO();
    	if(customer_info!=null&&!"".equals(customer_info)){
    		wmsApplyInfoSearchBeanVO=JsonUtil.jsonStringToBean(customer_info, WmsApplyInfoSearchBeanVO.class);
    	}
    	List<WmsCreHousingApplyAtt> list= new ArrayList<>(); 
    	if(bean.getList()!=null){
    		list=JsonUtil.jsonArrayToList(bean.getList(), WmsCreHousingApplyAtt.class);
    	}
        Date date = new Date(System.currentTimeMillis());
        Timestamp now = new Timestamp(date.getTime());
        // 流程状态太控制参数类
        WmsHouseCreditWorkFlowVO wDebtStatusVO = new WmsHouseCreditWorkFlowVO();
        wDebtStatusVO.setDebtkey("DKSQ");// 贷款申请节点
        wDebtStatusVO.setUserId(pmPersonnel.getPersonnel_id().toString());// 操作人id
        wDebtStatusVO.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());

        // 贷款退件重新修订
        if ("3".equals(bean.getFalg()))
        {
            wDebtStatusVO.setPass("revise");
            wDebtStatusVO.setAdvice("贷款申请重新提交");
            wmsLoanWorkFlowService.publicApprovalStatus(wDebtStatusVO);
        }
    	if("1".equals(bean.getFalg())){//业务员申请
    		WmsCreCreditHead wHead=new WmsCreCreditHead();//主表
    		wHead.setSalesman_id(listp.get(0).getPersonnel_id());//业务员id
    		wHead.setSalesman_code(listp.get(0).getPersonnel_code());//业务员code
    		wHead.setSalesman_shortcode(listp.get(0).getPersonnel_shortcode());//业务员shortcode
    		wHead.setSalesman_name(listp.get(0).getPersonnel_name());//姓名
    		wHead.setSalesman_city_code(listp.get(0).getPersonnel_regionnumber());//区域编码
    		wHead.setSalesman_dept_id(listp.get(0).getPersonnel_deptid());//部门id

            //新增城市编码替换
    		wHead.setCity(UserCityUtil.getUserCity(listp.get(0).getPersonnel_regionnumber()));
    		//2016-6-2日添加《-
    		
    		wHead.setCredit_limit(wmsApplyInfoSearchBeanVO.getCredit_limit());//借款额度
    		wHead.setMax_repayment_time_limit(wmsApplyInfoSearchBeanVO.getMax_repayment_time_limit());//贷款期限
    		//-》
            wHead.setVersion_number("3");//
            wHead.setBill_type("01");// 业务类型 01新增
    		wHead.setCre_type("2");//贷款产品
            wHead.setBill_status("B");// L待初评预估
    		wHead.setEnable_flag("1");//enable_flag
    		wHead.setBill_code(CodeNoUtil.getHouseCreCode());//单据编号
    		wHead.setIs_xudai("0");
    		wHead.setCre_loan_type(-1);
    		wHead.setCreate_timestamp(now);// 创建时间
    		wHead.setCreate_user_city_code(listp.get(0).getPersonnel_regionnumber());//创建人区域编码
    		wHead.setLast_update_user_id(listp.get(0).getPersonnel_id());//上次修改人
    		wHead.setLast_update_timestamp(now);//上次修改时间
    		wHead.setCreate_user_id(listp.get(0).getPersonnel_id());//创建人
    		wHead.setCreate_user_name(listp.get(0).getPersonnel_name());//创建人名称
            // 是否有流程
            wHead.setIs_workflow("1");
    		wmscrecreditheadDao.saveByPk(wHead); 
            // 贷款申请
            if ("1".equals(bean.getFalg()))
            {
                wDebtStatusVO.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id().toString());
                wDebtStatusVO.setPass("true");
                wDebtStatusVO.setAdvice("贷款申请");
                wmsLoanWorkFlowService.publicApprovalStatus(wDebtStatusVO);
            }
    		//客户变更主表
    		WmsCreCreditLineCustomerChangeHead wmsCustomerChangeHead=new WmsCreCreditLineCustomerChangeHead();
    		wmsCustomerChangeHead.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());//贷款表主键
    		wmsCustomerChangeHead.setIs_major("1");//是否为主贷人
    		wmsCustomerChangeHead.setCustomer_name(wmsApplyInfoSearchBeanVO.getCustomer_name());//姓名
    		wmsCustomerChangeHead.setCustomer_age(wmsApplyInfoSearchBeanVO.getCustomer_age());//客户年龄
    		wmsCustomerChangeHead.setMobile_telephone1(wmsApplyInfoSearchBeanVO.getMobile_telephone1());//申请人手机号码1
    		wmsCustomerChangeHead.setCustomer_code(CodeNoUtil.getCustomerCode());//生产用户编码
    		wmsCustomerChangeHead.setCreate_user_id(listp.get(0).getPersonnel_id());//创建人
    		wmsCustomerChangeHead.setCreate_user_name(listp.get(0).getPersonnel_name());//创建人名称
    		wmsCustomerChangeHead.setCreate_timestamp(now);//创建日期
    		wmsCustomerChangeHead.setLast_update_user_id(listp.get(0).getPersonnel_id());//上次修改人
    		wmsCustomerChangeHead.setLast_update_timestamp(now);//上次修改时间
    		wmsCustomerChangeHead.setEnable_flag("1");
    		wmsCreCreditLineCustomerChangeHeadDao.saveWithKey(wmsCustomerChangeHead);
    		//客户房产信息变更表
    		WmsCreCustomerChangeLineHouseinfo wmsCreHouseinfo=new WmsCreCustomerChangeLineHouseinfo();
    		wmsCreHouseinfo.setWms_cre_credit_line_customer_change_head_id(wmsCustomerChangeHead.getWms_cre_credit_line_customer_change_head_id());//客户变更信息表主键
    		wmsCreHouseinfo.setCommunity_name(wmsApplyInfoSearchBeanVO.getCommunity_name());//小区名称
    		wmsCreHouseinfo.setHouse_type(wmsApplyInfoSearchBeanVO.getHouse_type());//房产类型
    		wmsCreHouseinfo.setHouse_building_area(wmsApplyInfoSearchBeanVO.getHouse_building_area());//建筑面积
    		wmsCreHouseinfo.setHouse_address_more(wmsApplyInfoSearchBeanVO.getHouse_address_more());//抵押房产地址
    		wmsCreHouseinfo.setCreate_user_id(listp.get(0).getPersonnel_id());//创建人
    		wmsCreHouseinfo.setCreate_timestamp(now);//创建日期
    		wmsCreHouseinfo.setLast_update_user_id(listp.get(0).getPersonnel_id());//上次修改人
    		wmsCreHouseinfo.setLast_update_timestamp(now);//上次修改时间
    		wmsCreHouseinfo.setHouses_number(wmsApplyInfoSearchBeanVO.getHouses_number());//名下几处房产
    		wmsCreHouseinfo.setEnable_flag("1");
    		wmsCreHouseinfo.setCity(wmsApplyInfoSearchBeanVO.getCity());//城市
    		
    		wmsCreHouseinfo.setIs_compound(wmsApplyInfoSearchBeanVO.getIs_compound());//是否复式
    		String house_buy_date = wmsApplyInfoSearchBeanVO.getHouse_buy_date();
    		if(StringUtils.isNotEmpty(house_buy_date)) {
    		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		    try {
    		        wmsCreHouseinfo.setHouse_buy_date(new java.sql.Date(format.parse(house_buy_date).getTime()));//购买时间
    		    } catch(Exception e) {
    		        log.error("时间格式化错误");
    		    }
    		} else {
    		    wmsCreHouseinfo.setHouse_buy_date(null);//购买时间
    		}
    		wmsCreHouseinfo.setHouse_age(wmsApplyInfoSearchBeanVO.getHouse_age());//房屋房龄
    		wmsCreHouseinfo.setHouse_remark(wmsApplyInfoSearchBeanVO.getHouse_remark());//备注
    		
    		wmsCreCustomerChangeLineHouseinfoDao.addNewRecord(wmsCreHouseinfo);
    		
    		//房贷——抵押房产信息wms_cre_housing_customer_house
    		WmsCreHousingCustomerHouse	wmsCreHousingCustomerHouse=new WmsCreHousingCustomerHouse();
    		wmsCreHousingCustomerHouse.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());//	 信用贷款表主键
    		wmsCreHousingCustomerHouse.setWms_cre_customer_change_line_houseinfo_id(wmsCreHouseinfo.getWms_cre_customer_change_line_houseinfo_id());//'房产变更信息表主键',
    		wmsCreHousingCustomerHouse.setCreate_user_id(listp.get(0).getPersonnel_id());//创建人
    		wmsCreHousingCustomerHouse.setCreate_timestamp(now);//创建日期
    		wmsCreHousingCustomerHouse.setCreate_user_name(listp.get(0).getPersonnel_name());//创建人名称
    		wmsCreHousingCustomerHouse.setLast_update_user_id(listp.get(0).getPersonnel_id());//上次修改人
    		wmsCreHousingCustomerHouse.setLast_update_timestamp(now);//上次修改时间
    		wmsCreHousingCustomerHouseDao.save(wmsCreHousingCustomerHouse);
    		
    		WmsCreHousingFileInfo wmsCreHousingFileInfo = new WmsCreHousingFileInfo();
    		wmsCreHousingFileInfo.setBill_code(wHead.getBill_code());
            wmsCreHousingFileInfo.setCreate_timestamp(now);
            wmsCreHousingFileInfo.setCreate_user_id(listp.get(0).getPersonnel_id());
            wmsCreHousingFileInfo.setCreate_user_name(listp.get(0).getPersonnel_name());
            wmsCreHousingFileInfo.setEnable_flag("1");
            wmsCreHousingFileInfo.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());
            wmsCreHousingFileInfo.setBill_status(1);//单据状态 ：0表示未生成贷款申请单1表示已经生成贷款申请单'
            wmsCreHousingFileInfo.setSalesman_shortcode(listp.get(0).getPersonnel_shortcode());
            wmsCreHousingFileInfoDao.save(wmsCreHousingFileInfo);
            
            Map<String, Object> attParamMap = new HashMap<String, Object>();
            attParamMap.put("bill_code", wHead.getBill_code());
            
    		//保存上传的图片信息
            for(WmsCreHousingApplyAtt att: list) {
            	att.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());
            	att.setWms_cre_housing_file_info_id(wmsCreHousingFileInfo.getWms_cre_housing_file_info_id());
            	
            	attParamMap.put("data_detail_name", att.getData_detail_name());
            	//生成新的图片编号
            	att.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));
            	
            	wmsCreHousingApplyAttDao.save(att);
            }
            //调用发送信息的接口出现异常 不会影响正常流程  
            try{
	            //调用方法map
	            Map<String, Object> sendMap = new HashMap<String, Object>();
	            //发送短信map
	            Map<String, String> msgMap = new HashMap<String, String>();
	            //参数map
	            Map<String, String> paramMap = new HashMap<String, String>();
	            //参数map
                Map<String, String> msg_extras = new HashMap<String, String>();
	            //发送短信模块参数
                // sendMap.put("short_message", "1");
	            sendMap.put("user_id", listp.get(0).getPersonnel_id());
                sendMap.put("role_value", '1');// 流程节点名称角色--WorkflowRoleHelp.HOUSE_MQZZG
                // sendMap.put("is_dis_area", "1");// 需要区分地区
                // 修改 01： 如果长春给长春 其他都给沈阳 baisong date 2016-11-23
                /* if ("0431".equals(pmPersonnel.getPersonnel_regionnumber()))
                 {
                     sendMap.put("regionNumber", pmPersonnel.getPersonnel_regionnumber());//需要区分地区编码   
                 }else{
                     sendMap.put("regionNumber", "0024");// 需要区分地区编码
                 }*/
                // 根据菜单查询人员
                sendMap.put("menu_url", WmsHelp.MENU_URL_CSPG_LIST);// 初审评估ual
                sendMap.put("salesman_dept_id", wHead.getSalesman_dept_id());// 初审评估ual

	            msgMap.put("tpl_id", "1691");
	            
	            paramMap.put("bill_code", wHead.getBill_code());
	            paramMap.put("customer_name", wmsApplyInfoSearchBeanVO.getCustomer_name());
	            paramMap.put("city", wHead.getCity());
	            msgMap.put("paramJson", new Gson().toJson(paramMap));
	           
	            sendMap.put("msg_map", paramMap);
	           
	            sendMap.put("msgMap", msgMap);

	            //极光推送
	            List<String> post_number_list=new ArrayList<>();
	            post_number_list.add("TDJL");
	            //极光推送需要的数据参数
	            sendMap.put("quart_message", "1");//消息附加参数
	            paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name()+pmPersonnel.getPersonnel_shortcode());
	            sendMap.put("msg_code", "10001");//消息编码
	            sendMap.put("msg_code_role","20011");//消息附加参数--根据角色区分的消息
	            msgMap.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());//添加参数
	            sendMap.put("role_outside", "1");//判断获取人
	          
	         
	    		//如果是消息中心
	            sendMap.put("message_center", "1");
	            //提交人
	            sendMap.put("salesman_name_code", listp.get(0).getPersonnel_id());
	            sendMap.put("bill_code", wHead.getBill_code());
	            sendMap.put("customer_name", wmsApplyInfoSearchBeanVO.getCustomer_name());
	            //消息编码
	            sendMap.put("msg_code", "10001");
	            paramMap.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());
	            paramMap.put("bill_status", wHead.getBill_status());
	            paramMap.put("create_user_id", listp.get(0).getPersonnel_id().toString());
	            paramMap.put("create_user_name", listp.get(0).getPersonnel_name());
	            paramMap.put("city", wHead.getCity());
            
	            sendMap.put("msg_map", paramMap);//极光推送的消息内容参数
	            sendMap.put("post_number_list", post_number_list);//人员类型
	            msg_extras.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());//添加参数
	            sendMap.put("msg_extras", msg_extras);//消息附加参数  
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
	                
                /* //第二次发送
                 sendMap.remove("short_message");
                 sendMap.remove("role_value");
                 sendMap.put("msg_code", "20003");// 消息编码
                 post_number_list.remove(0);
                 post_number_list.add("MDJL");
                 sendMap.put("post_number_list", post_number_list);//人员类型
                this.wmsCreCreditHeadService.getUserAndSendInfo(sendMap);*/
            }catch (Exception e){
            	e.printStackTrace();        
            }
            
        }
        // 申请人员补录或者退件修订
        else if ("2".equals(bean.getFalg()) || "3".equals(bean.getFalg()))
        {
    	    //保存修改日志
            try {
                WmsCreCreditHeadSearchBeanVO vo = new WmsCreCreditHeadSearchBeanVO();
                UserBean user = new UserBean();
                user.setUserId(listp.get(0).getPersonnel_id());
                user.setRealname(listp.get(0).getPersonnel_name());
                vo.setWms_cre_credit_head_id(Integer.parseInt(bean.getWms_cre_credit_head_id()));
                vo.setNow_timestamp(now);
                vo.setLog_type("4");//补录
                this.wmsCreCreditHeadService.saveHouseLoanLog(vo, user);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
    	    
    		//标示是否是房产核查（办件）人员
            Boolean isBj = false;
            Boolean isown = false;
            List<String> listPm = pmPersonnelDao.getJurisdictionInfo(pmPersonnel.getPersonnel_id());// 当前登陆人的菜单权限
            // 判断是否有房产核查权限
            if (listPm.contains(WmsHelp.MENU_ID_FCHC_LIST))
            {
                isBj = true;
                WmsCreCreditHead whead = wmscrecreditheadDao.get(Integer.valueOf(bean.getWms_cre_credit_head_id()));
                if (whead != null && whead.getSalesman_id().toString().equals(pmPersonnel.getPersonnel_id().toString()))
                {
                    isown = true;
                }
            }

            // if(!isBj){
            // 如果是退件修订--重新贷款申请
            if (!isBj || isown)
            {
	    		//主表更新
	    		WmsCreCreditHead wHead=new WmsCreCreditHead();
	    		wHead.setWms_cre_credit_head_id(Integer.valueOf(bean.getWms_cre_credit_head_id()));
	    		//2016-6-2日添加《-
	    		wHead.setCredit_limit(wmsApplyInfoSearchBeanVO.getCredit_limit());//借款额度
	    		wHead.setMax_repayment_time_limit(wmsApplyInfoSearchBeanVO.getMax_repayment_time_limit());//贷款期限
	    		//-》
	    		wHead.setLast_update_user_id(listp.get(0).getPersonnel_id());//上次修改人
	    		wHead.setLast_update_timestamp(now);//上次修改时间
	    		wmscrecreditheadDao.updateforhouse(wHead);  
	    		
	    		//客户变更主表更新
	    		WmsCreCreditLineCustomerChangeHead wmsCustomerChangeHead=new WmsCreCreditLineCustomerChangeHead();
	    		wmsCustomerChangeHead.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());//贷款表主键
	    		wmsCustomerChangeHead.setIs_major("1");//是否为主贷人
	    		wmsCustomerChangeHead.setCustomer_name(wmsApplyInfoSearchBeanVO.getCustomer_name());//姓名
	    		wmsCustomerChangeHead.setCustomer_age(wmsApplyInfoSearchBeanVO.getCustomer_age());//客户年龄
	    		wmsCustomerChangeHead.setMobile_telephone1(wmsApplyInfoSearchBeanVO.getMobile_telephone1());//申请人手机号码1
	    		wmsCustomerChangeHead.setLast_update_user_id(listp.get(0).getPersonnel_id());//上次修改人
	    		wmsCustomerChangeHead.setLast_update_timestamp(now);//上次修改时间
	    		wmsCreCreditLineCustomerChangeHeadDao.updateforBLTwo(wmsCustomerChangeHead);
	    		
	      		//客户房产信息变更表更新
	    		Map<String,Object> paramMapTwo=new HashMap<>();
	    		WmsCreCustomerChangeLineHouseinfo wmsCreHouseinfo=new WmsCreCustomerChangeLineHouseinfo();
	    		wmsCreHouseinfo.setWms_cre_credit_line_customer_change_head_id(wmsCustomerChangeHead.getWms_cre_credit_line_customer_change_head_id());//客户变更信息表主键
	    		wmsCreHouseinfo.setCommunity_name(wmsApplyInfoSearchBeanVO.getCommunity_name());//小区名称
	    		wmsCreHouseinfo.setHouse_type(wmsApplyInfoSearchBeanVO.getHouse_type());//房产类型
	    		wmsCreHouseinfo.setHouse_building_area(wmsApplyInfoSearchBeanVO.getHouse_building_area());//建筑面积
	    		wmsCreHouseinfo.setHouse_address_more(wmsApplyInfoSearchBeanVO.getHouse_address_more());//抵押房产地址
	    		wmsCreHouseinfo.setCreate_user_id(listp.get(0).getPersonnel_id());//创建人
	    		wmsCreHouseinfo.setCreate_timestamp(now);//创建日期
	    		wmsCreHouseinfo.setLast_update_user_id(listp.get(0).getPersonnel_id());//上次修改人
	    		wmsCreHouseinfo.setLast_update_timestamp(now);//上次修改时间
	    		wmsCreHouseinfo.setHouses_number(wmsApplyInfoSearchBeanVO.getHouses_number());//名下几处房产
	    		wmsCreHouseinfo.setCity(wmsApplyInfoSearchBeanVO.getCity());//城市
	    		
	    		wmsCreHouseinfo.setIs_compound(wmsApplyInfoSearchBeanVO.getIs_compound());//是否复式
	            String house_buy_date = wmsApplyInfoSearchBeanVO.getHouse_buy_date();
	            if(StringUtils.isNotEmpty(house_buy_date)) {
	                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	                try {
	                    wmsCreHouseinfo.setHouse_buy_date(new java.sql.Date(format.parse(house_buy_date).getTime()));//购买时间
	                } catch(Exception e) {
	                    log.error("时间格式化错误");
	                }
	            } else {
	                wmsCreHouseinfo.setHouse_buy_date(null);//购买时间
	            }
	            wmsCreHouseinfo.setHouse_age(wmsApplyInfoSearchBeanVO.getHouse_age());//房屋房龄
	            wmsCreHouseinfo.setHouse_remark(wmsApplyInfoSearchBeanVO.getHouse_remark());//备注
	    		
	    		wmsCreHouseinfo.putInMap(paramMapTwo);
	    		paramMapTwo.put("is_major", "1");
	    		paramMapTwo.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id());
	    		wmsCreCustomerChangeLineHouseinfoDao.updateforBLTwo(paramMapTwo);
            }
            
            Map<String, Object> attParamMap = new HashMap<String, Object>();
            WmsCreCreditHead wHead = wmscrecreditheadDao.get(Integer.valueOf(bean.getWms_cre_credit_head_id()));
            attParamMap.put("bill_code", wHead.getBill_code());
    		//保存上传的图片信息
            for(WmsCreHousingApplyAtt att:list){
            	att.setWms_cre_credit_head_id(Integer.valueOf(bean.getWms_cre_credit_head_id()));
            	//生成新的图片编号
            	attParamMap.put("data_detail_name", att.getData_detail_name());
                att.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));
            	wmsCreHousingApplyAttDao.save(att);
            }
            // 补录信息
            if ("2".equals(bean.getFalg()))
            {

                // 调用发送信息的接口出现异常 不会影响正常流程
                try
                {
                    // 调用方法map
                    Map<String, Object> sendMap = new HashMap<String, Object>();
                    // 发送短信map
                    Map<String, String> msgMap = new HashMap<String, String>();
                    // 参数map
                    Map<String, String> paramMap = new HashMap<String, String>();
                    sendMap.put("user_id", listp.get(0).getPersonnel_id());
                    // 极光推送
                    List<String> post_number_list = new ArrayList<>();
                    post_number_list.add("TDJL");
                    // 极光推送需要的数据参数
                    sendMap.put("quart_message", "1");// 消息附加参数

                    sendMap.put("msg_code", "10002");// 消息编码
                    msgMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());// 添加参数
                    sendMap.put("role_outside", "1");// 判断获取人

                    // 发送消息中心 焦德龙
                    // 如果是消息中心
                    sendMap.put("message_center", "1");
                    // 提交信息
                    paramMap.put("customer_name", wmsApplyInfoSearchBeanVO.getCustomer_name());
                    paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                    paramMap.put("bill_code", wHead.getBill_code());
                    paramMap.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());
                    paramMap.put("bill_status", wHead.getBill_status());
                    paramMap.put("create_user_id", listp.get(0).getPersonnel_id().toString());
                    paramMap.put("create_user_name", listp.get(0).getPersonnel_name());
                    paramMap.put("city", wHead.getCity());

                    sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                    sendMap.put("post_number_list", post_number_list);// 人员类型
                    sendMap.put("msg_extras", msgMap);// 消息附加参数

                    this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);

                    /* // 第二次发送
                     sendMap.put("msg_code", "20004");// 消息编码
                     post_number_list.clear();
                     post_number_list.add("MDJL");
                     sendMap.put("post_number_list", post_number_list);// 人员类型
                     this.wmsCreCreditHeadService.getUserAndSendInfo(sendMap);*/

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            // 重新贷款申请
            if ("3".equals(bean.getFalg()))
            {
                // 调用发送信息的接口出现异常 不会影响正常流程
                try
                {
                    // 调用方法map
                    Map<String, Object> sendMap = new HashMap<String, Object>();
                    // 发送短信map
                    Map<String, String> msgMap = new HashMap<String, String>();
                    // 参数map
                    Map<String, String> paramMap = new HashMap<String, String>();
                    // 参数map
                    Map<String, String> msg_extras = new HashMap<String, String>();
                    // 发送短信模块参数
                    // sendMap.put("short_message", "1");
                    sendMap.put("user_id", listp.get(0).getPersonnel_id());
                    sendMap.put("role_value", '1');// 流程节点名称角色--WorkflowRoleHelp.HOUSE_MQZZG
                    // sendMap.put("is_dis_area", "1");// 需要区分地区
                    // 修改 01： 如果长春给长春 其他都给沈阳 baisong date 2016-11-23
                    /* if ("0431".equals(pmPersonnel.getPersonnel_regionnumber()))
                     {
                         sendMap.put("regionNumber", pmPersonnel.getPersonnel_regionnumber());//需要区分地区编码   
                     }else{
                         sendMap.put("regionNumber", "0024");// 需要区分地区编码
                     }*/
                    // 根据菜单查询人员
                    sendMap.put("menu_url", WmsHelp.MENU_URL_CSPG_LIST);// 初审评估ual
                    sendMap.put("salesman_dept_id", wHead.getSalesman_dept_id());// 初审评估ual

                    msgMap.put("tpl_id", "1691");

                    paramMap.put("bill_code", wHead.getBill_code());
                    paramMap.put("customer_name", wmsApplyInfoSearchBeanVO.getCustomer_name());
                    paramMap.put("city", wHead.getCity());
                    msgMap.put("paramJson", new Gson().toJson(paramMap));

                    sendMap.put("msg_map", paramMap);

                    sendMap.put("msgMap", msgMap);

                    // 极光推送
                    List<String> post_number_list = new ArrayList<>();
                    post_number_list.add("TDJL");
                    // 极光推送需要的数据参数
                    sendMap.put("quart_message", "1");// 消息附加参数
                    paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                    sendMap.put("msg_code", "10001");// 消息编码
                    sendMap.put("msg_code_role", "20011");// 消息附加参数--根据角色区分的消息
                    msgMap.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());// 添加参数
                    sendMap.put("role_outside", "1");// 判断获取人

                    // 发送消息中心 焦德龙
                    // 如果是消息中心
                    sendMap.put("message_center", "1");
                    // 提交人
                    sendMap.put("salesman_name_code", listp.get(0).getPersonnel_id());
                    sendMap.put("bill_code", wHead.getBill_code());
                    sendMap.put("customer_name", wmsApplyInfoSearchBeanVO.getCustomer_name());
                    // 消息编码
                    sendMap.put("msg_code", "10001");
                    paramMap.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());
                    paramMap.put("bill_status", wHead.getBill_status());
                    paramMap.put("create_user_id", listp.get(0).getPersonnel_id().toString());
                    paramMap.put("create_user_name", listp.get(0).getPersonnel_name());
                    paramMap.put("city", wHead.getCity());

                    sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                    sendMap.put("post_number_list", post_number_list);// 人员类型
                    msg_extras.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());// 添加参数
                    sendMap.put("msg_extras", msg_extras);// 消息附加参数
                    this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);

                    /*  // 第二次发送
                      sendMap.remove("short_message");
                      sendMap.remove("role_value");
                      sendMap.put("msg_code", "20003");// 消息编码
                      post_number_list.remove(0);
                      post_number_list.add("MDJL");
                      sendMap.put("post_number_list", post_number_list);// 人员类型
                      this.wmsCreCreditHeadService.getUserAndSendInfo(sendMap);*/
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
    	}else{
    		map.put("result", "error");
	        map.put("message", "找不到对应的参数！");
	        map.put("flag", false);
	        return map;
    	}
    	map.put("result", "success");
        map.put("message", "成功！");
        map.put("flag", true);
        return map;
	}
	///*************移动端第二版本使用结束*****************///

    /**
     * @Title: wmsBizMoaSaveUp
     * @Description: 贷款申请 补录  提交等
     * @param bean
     * @param customer_info
     * @return 
     * @author: baisong
     * @time:2017年3月22日 上午11:41:47
     * @see com.zx.emanage.cremanage.service.IWmsCreCarpHousingAttService#wmsBizMoaSaveUp(com.zx.emanage.cremanage.vo.WmsMoaHouseInfoVO, java.lang.String)
     * history:
     * 1、2017年3月22日 baisong 创建方法
    */
    @Override
    public Map<String, Object> wmsBizMoaSaveUp(WmsMoaHouseInfoVO bean, String customer_info)
    {

        Map<String, Object> map = new HashMap<>();
        PmPersonnel pmPersonnel = new PmPersonnel();
        pmPersonnel.setPersonnel_id(Integer.valueOf(bean.getPersonnel_id()));
        List<PmPersonnel> listp = pmPersonnelDao.getListByEntity(pmPersonnel);
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        if (listp == null || listp.size() == 0)
        {
            map.put("result", "error");
            map.put("message", "获取审批人调用失败！");
            map.put("flag", false);
            return map;
        }
        pmPersonnel = listp.get(0);
        // 兼职部门
        Map<String, Object> mapConPost = new HashMap<>();
        mapConPost.put("personnel_id", bean.getPersonnel_id());
        mapConPost.put("top_dept_code", WmsHelp.TOP_DEPT_CODE);// 贷款端--DPHCPGLBXXX001
                                                               // 产品管理部
                                                               // 测试环境id=31
        List<Map<String, Object>> listmapConPost = sysConcurrentPostDao.searchList(mapConPost);
        if (listmapConPost != null && listmapConPost.size() > 0)
        {
            pmPersonnel.setDept_id(listmapConPost.get(0).get("dept_id").toString());
        }
        WmsApplyInfoSearchBeanVO wmsApplyInfoSearchBeanVO = new WmsApplyInfoSearchBeanVO();
        if (customer_info != null && !"".equals(customer_info))
        {
            wmsApplyInfoSearchBeanVO = JsonUtil.jsonStringToBean(customer_info, WmsApplyInfoSearchBeanVO.class);
        }
        List<WmsCreHousingApplyAtt> list = new ArrayList<>();
        if (bean.getList() != null && !"".equals(bean.getList()) && !"null".equals(bean.getList()))
        {
            list = JsonUtil.jsonArrayToList(bean.getList(), WmsCreHousingApplyAtt.class);
        }
        Date date = new Date(System.currentTimeMillis());
        Timestamp now = new Timestamp(date.getTime());
        // 流程状态太控制参数类
        WmsHouseCreditWorkFlowVO wDebtStatusVO = new WmsHouseCreditWorkFlowVO();
        wDebtStatusVO.setDebtkey("DKSQ");// 贷款申请节点
        wDebtStatusVO.setUserId(pmPersonnel.getPersonnel_id().toString());// 操作人id
        wDebtStatusVO.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
        boolean is_Falg = false;// 标识单据是否是草稿提交单据false为否
        // 贷款退回重新提交
        if ("2".equals(bean.getFalg()))
        {
            wDebtStatusVO.setPass("revise");
            wDebtStatusVO.setAdvice("贷款申请重新提交");
            wmsLoanWorkFlowService.publicApprovalStatus(wDebtStatusVO);
        } // 贷款申请
        else if ("4".equals(bean.getFalg()))
        {
            // 查询单据当前状态
            WmsCreCreditHead wmscrecredithead = wmscrecreditheadDao.get(Integer.valueOf(bean.getWms_cre_credit_head_id()));
            // 如果单据不为空并且单据状态为草稿 则需要更新单据状态
            if (wmscrecredithead != null && "A".equals(wmscrecredithead.getBill_status()))
            {
                is_Falg = true;
                wDebtStatusVO.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
                wDebtStatusVO.setPass("apply");
                wDebtStatusVO.setAdvice("贷款申请提交");
                wmsLoanWorkFlowService.publicApprovalStatus(wDebtStatusVO);
            }
        }
        // 业务员申请 3为暂存
        if (("1".equals(bean.getFalg()) || "3".equals(bean.getFalg())) && (bean.getWms_cre_credit_head_id() == null || "".equals(bean.getWms_cre_credit_head_id()) || "0".equals(bean.getWms_cre_credit_head_id())))
        {
            WmsCreCreditHead wHead = new WmsCreCreditHead();// 主表
            wHead.setSalesman_id(listp.get(0).getPersonnel_id());// 业务员id
            wHead.setSalesman_code(listp.get(0).getPersonnel_code());// 业务员code
            wHead.setSalesman_shortcode(listp.get(0).getPersonnel_shortcode());// 业务员shortcode
            wHead.setSalesman_name(listp.get(0).getPersonnel_name());// 姓名
            wHead.setSalesman_city_code(listp.get(0).getPersonnel_regionnumber());// 区域编码
            wHead.setSalesman_dept_id(listp.get(0).getPersonnel_deptid());// 部门id
    
            // 新增城市编码替换
            wHead.setCity(UserCityUtil.getUserCity(listp.get(0).getPersonnel_regionnumber()));
                    //2016-6-2日添加《-

            wHead.setCredit_limit(wmsApplyInfoSearchBeanVO.getCredit_limit());// 借款额度
            wHead.setMax_repayment_time_limit(wmsApplyInfoSearchBeanVO.getMax_repayment_time_limit());// 贷款期限
                    //-》
            wHead.setVersion_number("3");//
            wHead.setBill_type("01");// 业务类型 01新增
            wHead.setCre_type("2");// 贷款产品
            if ("1".equals(bean.getFalg()))
            {
                wHead.setBill_status("B");// 待初评预估
            }
            else
            {
                wHead.setBill_status("A");// 草稿
            }

            wHead.setEnable_flag("1");// enable_flag
            wHead.setBill_code(CodeNoUtil.getHouseCreCode());// 单据编号
            wHead.setIs_xudai("0");
            wHead.setCre_loan_type(-1);
            wHead.setCreate_timestamp(now);// 创建时间
            wHead.setCreate_user_city_code(listp.get(0).getPersonnel_regionnumber());// 创建人区域编码
            wHead.setLast_update_user_id(listp.get(0).getPersonnel_id());// 上次修改人
            wHead.setLast_update_timestamp(now);// 上次修改时间
            wHead.setCreate_user_id(listp.get(0).getPersonnel_id());// 创建人
            wHead.setCreate_user_name(listp.get(0).getPersonnel_name());// 创建人名称
            // 是否有流程
            wHead.setIs_workflow("1");
            wHead.setIs_group_flag("0");// "组合贷拆分单据 0否1是"
            if (wmsApplyInfoSearchBeanVO.getMort_flag() != null && !"".equals(wmsApplyInfoSearchBeanVO.getMort_flag()))
            {
                wHead.setMort_flag(wmsApplyInfoSearchBeanVO.getMort_flag());// 抵押标识 // 1一拆 // 2二拆                                                          
            }else{
                wHead.setMort_flag("1"); 
            }
            wmscrecreditheadDao.saveByPk(wHead);
            // 贷款申请
            if ("1".equals(bean.getFalg()))
            {
                wDebtStatusVO.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id().toString());
                wDebtStatusVO.setPass("apply");
                wDebtStatusVO.setAdvice("贷款申请提交");
                wmsLoanWorkFlowService.publicApprovalStatus(wDebtStatusVO);
            }
            // 客户变更主表
            WmsCreCreditLineCustomerChangeHead wmsCustomerChangeHead = new WmsCreCreditLineCustomerChangeHead();
            wmsCustomerChangeHead.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());// 贷款表主键
            wmsCustomerChangeHead.setIs_major("1");// 是否为主贷人
            wmsCustomerChangeHead.setCustomer_name(wmsApplyInfoSearchBeanVO.getCustomer_name());// 姓名
            wmsCustomerChangeHead.setCustomer_age(wmsApplyInfoSearchBeanVO.getCustomer_age());// 客户年龄
            wmsCustomerChangeHead.setMobile_telephone1(wmsApplyInfoSearchBeanVO.getMobile_telephone1());// 申请人手机号码1
            wmsCustomerChangeHead.setCustomer_code(CodeNoUtil.getCustomerCode());// 生产用户编码
            wmsCustomerChangeHead.setCreate_user_id(listp.get(0).getPersonnel_id());// 创建人
            wmsCustomerChangeHead.setCreate_user_name(listp.get(0).getPersonnel_name());// 创建人名称
            wmsCustomerChangeHead.setCreate_timestamp(now);// 创建日期
            wmsCustomerChangeHead.setLast_update_user_id(listp.get(0).getPersonnel_id());// 上次修改人
            wmsCustomerChangeHead.setLast_update_timestamp(now);// 上次修改时间
            wmsCustomerChangeHead.setEnable_flag("1");
            wmsCreCreditLineCustomerChangeHeadDao.saveWithKey(wmsCustomerChangeHead);
            // 客户房产信息变更表
            WmsCreCustomerChangeLineHouseinfo wmsCreHouseinfo = new WmsCreCustomerChangeLineHouseinfo();
            wmsCreHouseinfo.setWms_cre_credit_line_customer_change_head_id(wmsCustomerChangeHead.getWms_cre_credit_line_customer_change_head_id());// 客户变更信息表主键
            wmsCreHouseinfo.setCommunity_name(wmsApplyInfoSearchBeanVO.getCommunity_name());// 小区名称
            wmsCreHouseinfo.setHouse_type(wmsApplyInfoSearchBeanVO.getHouse_type());// 房产类型
            wmsCreHouseinfo.setHouse_building_area(wmsApplyInfoSearchBeanVO.getHouse_building_area());// 建筑面积
            wmsCreHouseinfo.setHouse_address_more(wmsApplyInfoSearchBeanVO.getHouse_address_more());// 抵押房产地址
            wmsCreHouseinfo.setCreate_user_id(listp.get(0).getPersonnel_id());// 创建人
            wmsCreHouseinfo.setCreate_timestamp(now);// 创建日期
            wmsCreHouseinfo.setLast_update_user_id(listp.get(0).getPersonnel_id());// 上次修改人
            wmsCreHouseinfo.setLast_update_timestamp(now);// 上次修改时间
            wmsCreHouseinfo.setHouses_number(wmsApplyInfoSearchBeanVO.getHouses_number());// 名下几处房产
            wmsCreHouseinfo.setEnable_flag("1");
            wmsCreHouseinfo.setCity(wmsApplyInfoSearchBeanVO.getCity());// 城市

            wmsCreHouseinfo.setIs_compound(wmsApplyInfoSearchBeanVO.getIs_compound());// 是否复式
            String house_buy_date = wmsApplyInfoSearchBeanVO.getHouse_buy_date();
            if (StringUtils.isNotEmpty(house_buy_date))
            {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try
                {
                    wmsCreHouseinfo.setHouse_buy_date(new java.sql.Date(format.parse(house_buy_date).getTime()));// 购买时间
                }
                catch (Exception e)
                {
                    log.error("时间格式化错误");
                    }
            }
            else
            {
                wmsCreHouseinfo.setHouse_buy_date(null);// 购买时间
            }
            wmsCreHouseinfo.setHouse_age(wmsApplyInfoSearchBeanVO.getHouse_age());// 房屋房龄
            wmsCreHouseinfo.setHouse_remark(wmsApplyInfoSearchBeanVO.getHouse_remark());// 备注

            wmsCreCustomerChangeLineHouseinfoDao.addNewRecord(wmsCreHouseinfo);

            // 房贷——抵押房产信息wms_cre_housing_customer_house
            WmsCreHousingCustomerHouse wmsCreHousingCustomerHouse = new WmsCreHousingCustomerHouse();
            wmsCreHousingCustomerHouse.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());// 信用贷款表主键
            wmsCreHousingCustomerHouse.setWms_cre_customer_change_line_houseinfo_id(wmsCreHouseinfo.getWms_cre_customer_change_line_houseinfo_id());// '房产变更信息表主键',
            wmsCreHousingCustomerHouse.setCreate_user_id(listp.get(0).getPersonnel_id());// 创建人
            wmsCreHousingCustomerHouse.setCreate_timestamp(now);// 创建日期
            wmsCreHousingCustomerHouse.setCreate_user_name(listp.get(0).getPersonnel_name());// 创建人名称
            wmsCreHousingCustomerHouse.setLast_update_user_id(listp.get(0).getPersonnel_id());// 上次修改人
            wmsCreHousingCustomerHouse.setLast_update_timestamp(now);// 上次修改时间
            wmsCreHousingCustomerHouseDao.save(wmsCreHousingCustomerHouse);

            WmsCreHousingFileInfo wmsCreHousingFileInfo = new WmsCreHousingFileInfo();
            wmsCreHousingFileInfo.setBill_code(wHead.getBill_code());
            wmsCreHousingFileInfo.setCreate_timestamp(now);
            wmsCreHousingFileInfo.setCreate_user_id(listp.get(0).getPersonnel_id());
            wmsCreHousingFileInfo.setCreate_user_name(listp.get(0).getPersonnel_name());
            wmsCreHousingFileInfo.setEnable_flag("1");
            wmsCreHousingFileInfo.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());
            wmsCreHousingFileInfo.setBill_status(1);// 单据状态
                                                    // ：0表示未生成贷款申请单1表示已经生成贷款申请单'
            wmsCreHousingFileInfo.setSalesman_shortcode(listp.get(0).getPersonnel_shortcode());
            wmsCreHousingFileInfoDao.save(wmsCreHousingFileInfo);

            Map<String, Object> attParamMap = new HashMap<String, Object>();
            attParamMap.put("bill_code", wHead.getBill_code());

            // 保存上传的图片信息
            for (WmsCreHousingApplyAtt att : list)
            {
                att.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());
                att.setWms_cre_housing_file_info_id(wmsCreHousingFileInfo.getWms_cre_housing_file_info_id());
                if (att.getData_detail_name() == null || "".equals(att.getData_detail_name()))
                {
                    att.setData_detail_name(WmsHelp.SYS_DICT_ID_MIS_HOUSE_ID);// 小类id
                }
                if (att.getData_type_name() == null || "".equals(att.getData_type_name()))
                {
                    att.setData_type_name(WmsHelp.SYS_DICT_ID_MIS_HOUSE_PID);// 大类id
                }
                attParamMap.put("data_detail_name", att.getData_detail_name());// 字典表id
                // 生成新的图片编号
                att.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));

                wmsCreHousingApplyAttDao.save(att);
            }
            // 为贷款申请保存
            if ("1".equals(bean.getFalg()))
            {
                // 调用发送信息的接口出现异常 不会影响正常流程
                try
                {
                    /*    // 调用方法map
                        Map<String, Object> sendMapshort = new HashMap<String, Object>();
                        // 发送短信map
                        Map<String, String> msgMapshort = new HashMap<String, String>();
                        // 参数map
                        Map<String, String> paramMapshort = new HashMap<String, String>();

                        // 发送短信模块参数
                        sendMapshort.put("short_message", "1");
                        sendMapshort.put("user_id", listp.get(0).getPersonnel_id());
                        sendMapshort.put("role_value", '1');// 流程节点名称角色--WorkflowRoleHelp.HOUSE_MQZZG

                        // 根据菜单查询人员
                        sendMapshort.put("menu_url", WmsHelp.MENU_URL_CSPG_LIST);// 初审评估ual
                        sendMapshort.put("salesman_dept_id", wHead.getSalesman_dept_id());// 初审评估ual

                        msgMapshort.put("tpl_id", "1691");

                        paramMapshort.put("bill_code", wHead.getBill_code());
                        paramMapshort.put("customer_name", wmsApplyInfoSearchBeanVO.getCustomer_name());
                        paramMapshort.put("city", wHead.getCity());
                        paramMapshort.put("app_name", WmsHelp.APP_NAME_MIS);
                        msgMapshort.put("paramJson", new Gson().toJson(paramMapshort));

                        sendMapshort.put("msg_map", paramMapshort);
                        sendMapshort.put("app_name", WmsHelp.APP_NAME_MIS);
                        sendMapshort.put("msgMap", msgMapshort);
                        // 发送短信息
                        this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMapshort);*/

                    // 调用方法map
                    Map<String, Object> sendMap = new HashMap<String, Object>();
                    // 参数map
                    Map<String, String> paramMap = new HashMap<String, String>();
                    // 参数map
                    Map<String, String> msg_extras = new HashMap<String, String>();
                    
                    sendMap.put("user_id", listp.get(0).getPersonnel_id());
                    // 根据菜单查询人员
                    sendMap.put("menu_url", WmsHelp.MENU_URL_CSPG_LIST);// 初审评估ual
                    sendMap.put("salesman_dept_id", wHead.getSalesman_dept_id());// 初审评估ual

                    // 极光推送
                    List<String> post_number_list = new ArrayList<>();
                    post_number_list.add("TDJL");
                    // 极光推送需要的数据参数
                    sendMap.put("quart_message", "1");// 消息附加参数
                    paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                    sendMap.put("msg_code", "10001");// 消息编码
                    sendMap.put("msg_code_role", "10001");// 消息附加参数--根据角色区分的消息
                    sendMap.put("app_name", WmsHelp.APP_NAME_MIS);// app别名mis
                    sendMap.put("role_outside", "1");// 判断获取人

                    // 发送消息中心 焦德龙
                    // 如果是消息中心
                    sendMap.put("message_center", "1");
                    // 提交人
                    sendMap.put("salesman_name_code", listp.get(0).getPersonnel_id());
                    sendMap.put("bill_code", wHead.getBill_code());
                    sendMap.put("customer_name", wmsApplyInfoSearchBeanVO.getCustomer_name());
                    sendMap.put("credit_limit", decimalFormat.format(wHead.getCredit_limit()));
                    // 消息编码
                    sendMap.put("msg_code", "10001");
                    sendMap.put("msg_code_role", "10001");// 消息附加参数--根据角色区分的消息
                    paramMap.put("bill_code", wHead.getBill_code());
                    paramMap.put("customer_name", wmsApplyInfoSearchBeanVO.getCustomer_name());
                    paramMap.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());
                    paramMap.put("bill_status", wHead.getBill_status());
                    paramMap.put("create_user_id", listp.get(0).getPersonnel_id().toString());
                    paramMap.put("create_user_name", listp.get(0).getPersonnel_name());
                    paramMap.put("city", wHead.getCity());
                    paramMap.put("app_name", WmsHelp.APP_NAME_MIS);// app别名mis
                    paramMap.put("credit_limit", decimalFormat.format(wHead.getCredit_limit()));
                    sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                    sendMap.put("post_number_list", post_number_list);// 人员类型
                    msg_extras.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());// 添加参数
                    sendMap.put("msg_extras", msg_extras);// 消息附加参数
                    this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                // 调用发送信息的接口出现异常 不会影响正常流程--发送pc端消息
                try
                {
                    // 调用方法map
                    Map<String, Object> sendMap = new HashMap<String, Object>();
                    // 参数map
                    Map<String, String> paramMap = new HashMap<String, String>();
                    // 根据菜单查询人员
                    sendMap.put("menu_url", WmsHelp.MENU_URL_CSPG_LIST);// 初审评估ual
                    sendMap.put("salesman_dept_id", wHead.getSalesman_dept_id());// 初审评估ual
                    sendMap.put("role_value", "1");
                    paramMap.put("bill_status", "B");// 初评的单据状态
                    // // 获取初评单据数量
                    // List<Map<String, Object>> listS =
                    // wmscrecreditheadDao.getStatusCount(paramMap);
                    // if (listS != null)
                    // {
                    // paramMap.put("number", listS.size() + "");
                    // }
                    // else
                    // {
                        paramMap.put("number", "1");
                    // }
                    sendMap.put("pc_msg_code", "20014");// pc端消息code
                    sendMap.put("pc_message", "1");// 标识发送pc消息
                    sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                    this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }
        // 1：申请提交2:退回重新提交 3：暂存4:信息补录
        else if (!"0".equals(bean.getWms_cre_credit_head_id()) && !"".equals(bean.getWms_cre_credit_head_id()) && bean.getWms_cre_credit_head_id() != null && ("2".equals(bean.getFalg()) || "3".equals(bean.getFalg()) || "4".equals(bean.getFalg())))
        {
            // 保存修改日志
            try
            {
                WmsCreCreditHeadSearchBeanVO vo = new WmsCreCreditHeadSearchBeanVO();
                UserBean user = new UserBean();
                user.setUserId(listp.get(0).getPersonnel_id());
                user.setRealname(listp.get(0).getPersonnel_name());
                vo.setWms_cre_credit_head_id(Integer.parseInt(bean.getWms_cre_credit_head_id()));
                vo.setNow_timestamp(now);
                vo.setLog_type("4");// 补录
                this.wmsCreCreditHeadService.saveHouseLoanLog(vo, user);
            }
            catch (Exception e)
            {
                log.error(e.getMessage());
            }
            // 主表更新
            WmsCreCreditHead wHead = new WmsCreCreditHead();
            wHead.setWms_cre_credit_head_id(Integer.valueOf(bean.getWms_cre_credit_head_id()));
            // 2016-6-2日添加《-
            wHead.setCredit_limit(wmsApplyInfoSearchBeanVO.getCredit_limit());// 借款额度
            wHead.setMax_repayment_time_limit(wmsApplyInfoSearchBeanVO.getMax_repayment_time_limit());// 贷款期限
            // -》
            wHead.setLast_update_user_id(listp.get(0).getPersonnel_id());// 上次修改人
            wHead.setLast_update_timestamp(now);// 上次修改时间
            wHead.setMort_flag(wmsApplyInfoSearchBeanVO.getMort_flag());//抵押标识 1一拆 2二拆
            wmscrecreditheadDao.updateforhouse(wHead);

            // 客户变更主表更新
            WmsCreCreditLineCustomerChangeHead wmsCustomerChangeHead = new WmsCreCreditLineCustomerChangeHead();
            wmsCustomerChangeHead.setWms_cre_credit_head_id(wHead.getWms_cre_credit_head_id());// 贷款表主键
            wmsCustomerChangeHead.setIs_major("1");// 是否为主贷人
            wmsCustomerChangeHead.setCustomer_name(wmsApplyInfoSearchBeanVO.getCustomer_name());// 姓名
            wmsCustomerChangeHead.setCustomer_age(wmsApplyInfoSearchBeanVO.getCustomer_age());// 客户年龄
            wmsCustomerChangeHead.setMobile_telephone1(wmsApplyInfoSearchBeanVO.getMobile_telephone1());// 申请人手机号码1
            wmsCustomerChangeHead.setLast_update_user_id(listp.get(0).getPersonnel_id());// 上次修改人
            wmsCustomerChangeHead.setLast_update_timestamp(now);// 上次修改时间
            wmsCreCreditLineCustomerChangeHeadDao.updateforBLTwo(wmsCustomerChangeHead);

            // 客户房产信息变更表更新
            Map<String, Object> paramMapTwo = new HashMap<>();
            WmsCreCustomerChangeLineHouseinfo wmsCreHouseinfo = new WmsCreCustomerChangeLineHouseinfo();
            wmsCreHouseinfo.setWms_cre_credit_line_customer_change_head_id(wmsCustomerChangeHead.getWms_cre_credit_line_customer_change_head_id());// 客户变更信息表主键
            wmsCreHouseinfo.setCommunity_name(wmsApplyInfoSearchBeanVO.getCommunity_name());// 小区名称
            wmsCreHouseinfo.setHouse_type(wmsApplyInfoSearchBeanVO.getHouse_type());// 房产类型
            wmsCreHouseinfo.setHouse_building_area(wmsApplyInfoSearchBeanVO.getHouse_building_area());// 建筑面积
            wmsCreHouseinfo.setHouse_address_more(wmsApplyInfoSearchBeanVO.getHouse_address_more());// 抵押房产地址
            wmsCreHouseinfo.setCreate_user_id(listp.get(0).getPersonnel_id());// 创建人
            wmsCreHouseinfo.setCreate_timestamp(now);// 创建日期
            wmsCreHouseinfo.setLast_update_user_id(listp.get(0).getPersonnel_id());// 上次修改人
            wmsCreHouseinfo.setLast_update_timestamp(now);// 上次修改时间
            wmsCreHouseinfo.setHouses_number(wmsApplyInfoSearchBeanVO.getHouses_number());// 名下几处房产
            wmsCreHouseinfo.setCity(wmsApplyInfoSearchBeanVO.getCity());// 城市

            wmsCreHouseinfo.setIs_compound(wmsApplyInfoSearchBeanVO.getIs_compound());// 是否复式
            String house_buy_date = wmsApplyInfoSearchBeanVO.getHouse_buy_date();
            if (StringUtils.isNotEmpty(house_buy_date))
            {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try
                {
                    wmsCreHouseinfo.setHouse_buy_date(new java.sql.Date(format.parse(house_buy_date).getTime()));// 购买时间
                }
                catch (Exception e)
                {
                    log.error("时间格式化错误");
                }
            }
            else
            {
                wmsCreHouseinfo.setHouse_buy_date(null);// 购买时间
            }
            wmsCreHouseinfo.setHouse_age(wmsApplyInfoSearchBeanVO.getHouse_age());// 房屋房龄
            wmsCreHouseinfo.setHouse_remark(wmsApplyInfoSearchBeanVO.getHouse_remark());// 备注

            wmsCreHouseinfo.putInMap(paramMapTwo);
            paramMapTwo.put("is_major", "1");
            paramMapTwo.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id());
            wmsCreCustomerChangeLineHouseinfoDao.updateforBLTwo(paramMapTwo);

            Map<String, Object> attParamMap = new HashMap<String, Object>();
            wHead = wmscrecreditheadDao.get(Integer.valueOf(bean.getWms_cre_credit_head_id()));
            attParamMap.put("bill_code", wHead.getBill_code());
            // 保存上传的图片信息
            for (WmsCreHousingApplyAtt att : list)
            {
                att.setWms_cre_credit_head_id(Integer.valueOf(bean.getWms_cre_credit_head_id()));
                if (att.getData_detail_name() == null || "".equals(att.getData_detail_name()))
                {
                    att.setData_detail_name(WmsHelp.SYS_DICT_ID_MIS_HOUSE_ID);// 小类id
                }
                if (att.getData_type_name() == null || "".equals(att.getData_type_name()))
                {
                    att.setData_type_name(WmsHelp.SYS_DICT_ID_MIS_HOUSE_PID);// 大类id
                }
                // 生成新的图片编号
                attParamMap.put("data_detail_name", att.getData_detail_name());// 字典表id
                att.setAttachment_old_name(wmsCreHousingApplyAttDao.getNextAttSeqByBillCodeAndDataTypeName(attParamMap));
                wmsCreHousingApplyAttDao.save(att);
            }
            // 重新贷款申请
            if ("2".equals(bean.getFalg()))
            {
                // 调用发送信息的接口出现异常 不会影响正常流程
                try
                {
                    /*    // 调用方法map
                        Map<String, Object> sendMapshort = new HashMap<String, Object>();
                        // 发送短信map
                        Map<String, String> msgMapshort = new HashMap<String, String>();
                        // 参数map
                        Map<String, String> paramMapshort = new HashMap<String, String>();

                        // 发送短信模块参数
                        sendMapshort.put("short_message", "1");
                        sendMapshort.put("user_id", listp.get(0).getPersonnel_id());
                        sendMapshort.put("role_value", '1');// 流程节点名称角色--WorkflowRoleHelp.HOUSE_MQZZG

                        // 根据菜单查询人员
                        sendMapshort.put("menu_url", WmsHelp.MENU_URL_CSPG_LIST);// 初审评估ual
                        sendMapshort.put("salesman_dept_id", wHead.getSalesman_dept_id());// 初审评估ual

                        msgMapshort.put("tpl_id", "1691");

                        paramMapshort.put("bill_code", wHead.getBill_code());
                        paramMapshort.put("customer_name", wmsApplyInfoSearchBeanVO.getCustomer_name());
                        paramMapshort.put("city", wHead.getCity());
                        msgMapshort.put("paramJson", new Gson().toJson(paramMapshort));

                        paramMapshort.put("app_name", WmsHelp.APP_NAME_MIS);
                        sendMapshort.put("msg_map", paramMapshort);

                        sendMapshort.put("msgMap", msgMapshort);
                        sendMapshort.put("app_name", WmsHelp.APP_NAME_MIS);
                        // 发送短信息
                        this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMapshort);*/

                    // 调用方法map
                    Map<String, Object> sendMap = new HashMap<String, Object>();
                    // 参数map
                    Map<String, String> paramMap = new HashMap<String, String>();
                    // 参数map
                    Map<String, String> msg_extras = new HashMap<String, String>();

                    sendMap.put("user_id", listp.get(0).getPersonnel_id());
                    // 根据菜单查询人员
                    sendMap.put("menu_url", WmsHelp.MENU_URL_CSPG_LIST);// 初审评估ual
                    sendMap.put("salesman_dept_id", wHead.getSalesman_dept_id());// 初审评估ual

                    // 极光推送
                    List<String> post_number_list = new ArrayList<>();
                    post_number_list.add("TDJL");
                    // 极光推送需要的数据参数
                    sendMap.put("quart_message", "1");// 消息附加参数
                    paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                    sendMap.put("msg_code", "10001");// 消息编码
                    sendMap.put("msg_code_role", "10001");// 消息附加参数--根据角色区分的消息
                    sendMap.put("app_name", WmsHelp.APP_NAME_MIS);// app别名mis
                    sendMap.put("role_outside", "1");// 判断获取人

                    // 发送消息中心 焦德龙
                    // 如果是消息中心
                    sendMap.put("message_center", "1");
                    // 提交人
                    sendMap.put("salesman_name_code", listp.get(0).getPersonnel_id());
                    sendMap.put("bill_code", wHead.getBill_code());
                    sendMap.put("customer_name", wmsApplyInfoSearchBeanVO.getCustomer_name());
                    sendMap.put("credit_limit", decimalFormat.format(wHead.getCredit_limit()));
                    // 消息编码
                    sendMap.put("msg_code", "10001");
                    sendMap.put("msg_code_role", "10001");// 消息附加参数--根据角色区分的消息
                    paramMap.put("bill_code", wHead.getBill_code());
                    paramMap.put("customer_name", wmsApplyInfoSearchBeanVO.getCustomer_name());
                    paramMap.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());
                    paramMap.put("bill_status", wHead.getBill_status());
                    paramMap.put("create_user_id", listp.get(0).getPersonnel_id().toString());
                    paramMap.put("create_user_name", listp.get(0).getPersonnel_name());
                    paramMap.put("city", wHead.getCity());
                    paramMap.put("app_name", WmsHelp.APP_NAME_MIS);// app别名mis
                    paramMap.put("credit_limit", decimalFormat.format(wHead.getCredit_limit()));
                    sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                    sendMap.put("post_number_list", post_number_list);// 人员类型
                    msg_extras.put("wms_cre_credit_head_id", wHead.getWms_cre_credit_head_id().toString());// 添加参数
                    sendMap.put("msg_extras", msg_extras);// 消息附加参数
                    this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                // 调用发送信息的接口出现异常 不会影响正常流程--发送pc端消息
                try
                {
                    // 调用方法map
                    Map<String, Object> sendMap = new HashMap<String, Object>();
                    // 参数map
                    Map<String, String> paramMap = new HashMap<String, String>();
                    // 根据菜单查询人员
                    sendMap.put("menu_url", WmsHelp.MENU_URL_CSPG_LIST);// 初审评估ual
                    sendMap.put("salesman_dept_id", wHead.getSalesman_dept_id());// 初审评估ual
                    sendMap.put("role_value", "1");
                    paramMap.put("bill_status", "B");// 初评的单据状态
                    // // 获取初评单据数量
                    // List<Map<String, Object>> listS =
                    // wmscrecreditheadDao.getStatusCount(paramMap);
                    // if (listS != null)
                    // {
                    // paramMap.put("number", listS.size() + "");
                    // }
                    // else
                    // {
                        paramMap.put("number", "1");
                    // }
                    sendMap.put("pc_msg_code", "20014");// pc端消息code
                    sendMap.put("pc_message", "1");// 标识发送pc消息
                    sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                    this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

        }
        map.put("result", "success");
        map.put("message", "成功！");
        map.put("flag", true);
        return map;
    }

    /**
     * @Title: postBizDeleteBill
     * @Description: 手机端贷款单据删除(3.2.35 手机端贷款单据删除)
     * @param bean
     * @return 
     * @author: baisong
     * @time:2017年4月6日 下午2:27:48
     * @see com.zx.emanage.cremanage.service.IWmsCreCarpHousingAttService#postBizDeleteBill(com.zx.emanage.cremanage.vo.WmsMoaHouseInfoVO)
     * history:
     * 1、2017年4月6日 baisong 创建方法
    */
    @Override
    @Transactional
    public Map<String, Object> postBizDeleteBill(WmsMoaHouseInfoVO bean)
    {
        Map<String, Object> map = new HashMap<>();
        WmsCreCreditHead wmscrecredithead = new WmsCreCreditHead();
        wmscrecredithead.setWms_cre_credit_head_id(Integer.valueOf(bean.getWms_cre_credit_head_id()));
//        wmscrecredithead.setBill_status("A");//变成什么状态？
        wmscrecredithead.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        wmscrecredithead.setLast_update_user_id(Integer.valueOf(bean.getPersonnel_id()));
        wmscrecredithead.setEnable_flag("0");
        wmscrecreditheadDao.updateDelete(wmscrecredithead);
        map.put("result", "success");
        map.put("message", "成功！");
        map.put("flag", true);
        return map;
    }
}
