package com.zx.emanage.inve.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.zx.emanage.cremanage.vo.SysSendInfoVO;
import com.zx.emanage.inve.persist.WmsInveDebtHeadDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveSalaryPreTotalDao;
import com.zx.emanage.inve.persist.WmsInveSpecialApprovalDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveSalarySetWorkFlowVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysPostDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.SysDept;
import com.zx.emanage.util.gen.entity.SysPost;
import com.zx.emanage.util.gen.entity.WmsInveDebtHead;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreTotal;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.sframe.util.HttpClientUtil;
import com.zx.sframe.util.SysUtil;

/**
 * 赎回流程接口具体功能实现
 * 
 * @author hancd
 */
@Service("wmsInveWorkFlowService")
public class WmsInveWorkFlowServiceImpl implements IWmsInveWorkFlowService
{
    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private PmPersonnelDao pmpersonnelDao;

    @Autowired
    private WmsInveRedeemDao wmsInveRedeemDao;

    @Autowired
    private WmsInveTransaDao wInveTransaDao;
    
    @Autowired
    private WmsInveDebtHeadDao wmsInveDebtHeadDao;
    
    @Autowired
    private SysPostDao sysPostDao;
    
    @Autowired
    private WmsInveTransaDao wmsInveTransaDao;
    
    @Autowired
    private WmsInveSpecialApprovalDao wmsInveSpecialApprovalDao;
    
    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private WmsInveSalaryPreTotalDao wmsInveSalaryPreTotalDao;

    private static Logger log = LoggerFactory.getLogger(WmsInveWorkFlowServiceImpl.class);

    /**
     * 覆盖:赎回流程，债权调整流程，理财上单流程    
     * 实现:流程启动公共方法
     * @param userId 用户ID
     * @param businessId 业务单据ID
     * @param salesman_dept_id 上单业务员部门ID
     * @param salesman_id 业务员ID
     * @param processDefinitionKey 流程实例key
     * @param wms_inve_transa_id 上单信息表主键ID
     * @author hancd
     */
    @Override
    public String startFinancialWorkFlow(String userId, String businessId, String salesman_dept_id,String salesman_id,String processDefinitionKey,Integer... wms_inve_transa_id)
    {
    	String result="success";
    	Map<String,String> sendMap = new HashMap<String, String>();//发送短信的方法
    	if(processDefinitionKey.equals(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS)){//赎回流程启动
    		Map<String,Object> fromMap = new HashMap<>();
    		Map<String, Object> statusMap = new HashMap<>();
    		int wmsInveTransaId = 0;
    		if(wms_inve_transa_id==null || wms_inve_transa_id.length == 0){
    			wmsInveTransaId = wmsInveTransaDao.getWmsInveTransaIdByWmsInveRedeemId(Integer.parseInt(businessId));
    		}else{
    			wmsInveTransaId = wms_inve_transa_id[0];
    		}
    		//根据上单信息id获取对应的上单信息
    		Map<String, Object> wmsInveTransa = wmsInveTransaDao.getObjectInfo(wmsInveTransaId);
    		//根据当前人员id获取对应的人员信息
            Map<String, Object> personnelMap = (Map<String, Object>) pmpersonnelDao.getPersonnelByPersonnelId(Integer.parseInt("" + wmsInveTransa.get("bel_salesman_id_id")));
            boolean flag = true;
        	if((personnelMap.get("companyName") + "").equals("财富管理中心")){
        		SysDept sysDept = sysDeptDao.getDeptById(Integer.parseInt(wmsInveTransa.get("bel_salesman_dept_id") + ""));
        		if(sysDept.getDept_level() == 6){
        			//需要判断是否是外阜
        			//根据人员所在部门id获取对应的职务信息
                	List<SysPost> list= sysPostDao.getSysPostBySalesManDeptId(Integer.parseInt(""+wmsInveTransa.get("bel_center_manager_dept_id")));
                	boolean is_waifu = false;
                	for(SysPost sysPost : list){
                		if(sysPost.getPost_number().startsWith(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.waifu"))){
                			is_waifu = true;
                			break;
                		}
                	}
                	if (!is_waifu) {
//                		if(!sPost.getPost_number().startsWith(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.waifu"))){
                			//部门经理、副总、总
                			if(wmsInveTransa.get("bel_department_manager_id")!=null && wmsInveTransa.get("bel_vice_general_manager_id")!=null && wmsInveTransa.get("bel_general_manager_id") !=null){
                				int personnelBuMenJingLiId = Integer.parseInt(wmsInveTransa.get("bel_department_manager_id") + "");
                    			int personnelFuZongId = Integer.parseInt(wmsInveTransa.get("bel_vice_general_manager_id") + "");
                     			int personnelZongId = Integer.parseInt(wmsInveTransa.get("bel_general_manager_id") + "");
                    			
                    			if(personnelBuMenJingLiId != personnelFuZongId && personnelFuZongId != personnelZongId && personnelFuZongId != personnelZongId){
                    				flag = false;
                        			//非业务部门直接找冯总审批
                               		fromMap.put("userId",""+wmsInveTransa.get("bel_department_manager_id"));
                               		fromMap.put("pass", "false");
                               		//单据状态变为待部门经理审批状态:1
                                    statusMap.put("data_status", "1");
                                    statusMap.put("wms_inve_redeem_id", businessId);
                                    //业务单据指向特批
                                    //statusMap.put("special_approval_leader_id", ""+wmsInveTransa.get("bel_department_manager_id"));
                                    //发送短信参数
                                  	if(fromMap.get("userId")!=null){
                                   		getPmInfo(fromMap.get("userId").toString(),sendMap);
                                   	}
                                sendJGHandler("01", Integer.valueOf(businessId), wmsInveTransa.get("bel_department_manager_id") + "");
                    			}
                			}
//                		}
					}
        		}
        	}
        	if(flag){
        		//非业务部门直接找冯总审批
         		fromMap.put("userId", ""+wmsInveTransa.get("bel_general_manager_id"));
         		fromMap.put("pass", "false");
         		//单据状态变为待总经理审批状态:3
                statusMap.put("data_status", "3");
                statusMap.put("wms_inve_redeem_id", businessId);
                //业务单据指向特批
                statusMap.put("special_approval_leader_id", ""+wmsInveTransa.get("bel_general_manager_id"));
                //发送短信参数
             	if(fromMap.get("userId")!=null){
             		getPmInfo(fromMap.get("userId").toString(),sendMap);
             	}
                sendJGHandler("03", Integer.valueOf(businessId), "" + wmsInveTransa.get("bel_general_manager_id"));
        	}
        	wmsInveRedeemDao.updateRecord(statusMap);
        	//启动流程
            workflowService.startWorkflow(userId,processDefinitionKey, businessId,fromMap);
    	}else if(processDefinitionKey.equals(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS)){//债权调整流程启动
    		//判断单据所述业务员职务是什么角色:团队经理TDJL  见习团队经理JXTDJL  客户经理KHJL  副总经理FZJL  总经理ZJL  或其他
        	PmPersonnel  pm = pmpersonnelDao.get(Integer.valueOf(salesman_id));
        	SysPost  sysPost=sysPostDao.get(pm.getPersonnel_postid());
        	Map<String,Object> fromMap = new HashMap<>();
        	List<Map<String,Object>> idList = null;
        	if(sysPost.getPost_number().equals("KHJL")){//单据提交人职务为客户经理
        		idList = getUserID(salesman_dept_id, "1");
        		//通过部门ID和key=1 存在上一级
        		if(idList!=null && idList.size()>0)
        		{
        			fromMap.put("userId",idList.get(0).get("personnel_id").toString());    			
        		}
        		//通过部门ID和key=1不存在上一级 走特批 页面友好提示
        		else
        		{
        			result ="error1";//代表此团队中无团队经理，走特批
        			idList = getUserID(salesman_dept_id, "0");
        			fromMap.put("userId",idList.get(0).get("personnel_id").toString());    	
        		}
        	}else if(sysPost.getPost_number().equals("TDJL") || sysPost.getPost_number().equals("JXTDJL")){//单据上单人为团队经理
        		fromMap.put("userId",salesman_id);  
        	}else if(sysPost.getPost_number().equals("FZJL")){//单据提交人为副总经理
        		result ="error2";//代表此单据业务员角色为副总经理，走特批
        		fromMap.put("userId",salesman_id);  
        	}else if(sysPost.getPost_number().equals("ZJL")){//单据提交人为总经理
        		result ="error3";//代表此团队中无团队经理，走特批
        		fromMap.put("userId",salesman_id);  
        	}else{
        		//不存在上一级 走特批 页面友好提示
    			result ="error1";//代表此团队中无团队经理，走特批
    			idList = getUserID(salesman_dept_id, "0");
    			fromMap.put("userId",idList.get(0).get("personnel_id").toString());    	
        	}
        	//启动流程
    		workflowService.startWorkflow(userId,processDefinitionKey, businessId,fromMap);
    		//改变单据状态为：待团队经理审批
    		Map<String,Object> paMap = new HashMap<>();
    		paMap.put("data_status",1);
    		paMap.put("wms_inve_debt_head_id", businessId);
    		wmsInveDebtHeadDao.updateRecord(paMap);
    	}else if(processDefinitionKey.equals(WorkflowConstantHelp.FINANCIALSINGLEROCESS)){//理财上单流程启动
    		 //启动流程
    		 Map<String,Object> fromMap = new HashMap<>();
    		 workflowService.startWorkflow(userId, processDefinitionKey, businessId, fromMap);
            // WMSINVETRANSA WTRANSA = NEW WMSINVETRANSA();
            // // WTRANSA.SETDATA_STATUS("9");//待复核
            // WTRANSA.SETWMS_INVE_TRANSA_ID(INTEGER.VALUEOF(BUSINESSID));
            // WMSINVETRANSADAO.UPDATE(WTRANSA);
    	}
    	return result;
    }
    /**
     * 覆盖:赎回流程，债权调整流程,理财上单流程
     * 实现公共流程获取IdList,taskList方法
     * @param userId 用户ID
     * @param processDefinitionKey 流程实例Key
     * processDefinitionKey=financialRedemptionProcess;//赎回流程
     * @param invekey 任务节点key
     * 当invekey=1 待修订 
     * 当invekey=2 待回款以及特批
     * 当invekey=3 代表三级审批
     * processDefinitionKey=debtAdjustmentProcess;//债权调整流程
     * @param invekey 任务节点key
     * 当invekey=1 代表三级审批
     * 当invekey=2 代表修订
     * 当invekey=4 代表确认和特批申请 
     * 当invekey=5 代表债权调整和协议打印
     * processDefinitionKey=financialSingleProcess;//理财上单流程
     * @param invekey 任务节点key
     * 当invekey=1 代表待复核
     * 当invekey=2 代表待支付
     * 当invekey=3 代表待审核
     * 当invekey=4 代表待签约/待确认
     * 当invekey=5 代表待复核修订
     * 当invekey=6 代表待支付退回
     * 当invekey=7 代表待审核退回
     * 当invekey=8 代表待退单退回
     * 当invekey=9 代表待退单确认
     * @return Map
     * @author hancd
     */
    @Override
    public Map<String, Object> getWorkFlowToIdList(String processDefinitionKey,String userId,String invekey)
    {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        if(processDefinitionKey.equals(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS))//赎回流程key
        {
        	  if ("1".equals(invekey))
              {// 待修订
                  List<WorkflowInfoHelp> workflowInfoHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS_CXXD);
                  if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
                  {
                      Map<String, Object> pMap = new HashMap<String, Object>();
                      return pMap;
                  }
                  parmMap=setWorkFlowMap(workflowInfoHelps,WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
              }
              else if ("2".equals(invekey))
              {
                  //特批申请和回款
                  List<WorkflowInfoHelp> wHelpsTps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS_TPSQ);
                  List<WorkflowInfoHelp> wHelpsHks = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS_HK);
                  if (wHelpsTps.size() == 0 && wHelpsHks.size() == 0)
                  {
                      Map<String, Object> parm = new HashMap<>();
                      return parm;
                  }
                  wHelpsTps.addAll(wHelpsHks);
                  parmMap =setWorkFlowMap(wHelpsTps,WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
              }
              else if("3".equals(invekey))//三级审批
              {
//            	 PmPersonnel personnel =pmpersonnelDao.get(Integer.valueOf(userId));
      			 //SysPost sysPost = sysPostDao.get(personnel.getPersonnel_postid());
      			 List<WorkflowInfoHelp> wHelps =new ArrayList<>();
//      			 SysDept sysDept = sysDeptDao.getDeptById(personnel.getPersonnel_deptid());
//      			 if(sysDept.getDept_level() == 6){
//      				wHelps= setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_JLSP);
//      			 }else if(sysDept.getDept_level()  == 4){
//      				wHelps = setWorkFlowList(processDefinitionKey, userId,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_FZJLSP);
//      			 }else if(sysDept.getDept_level()  == 2){
//      				wHelps = setWorkFlowList(processDefinitionKey, userId,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_ZJLSP);
//      			 }
      			 
      			wHelps.addAll(setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_JLSP));
   				wHelps.addAll(setWorkFlowList(processDefinitionKey, userId,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_FZJLSP));
   				wHelps.addAll(setWorkFlowList(processDefinitionKey, userId,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_ZJLSP));
   				
      			/* 
      			if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.jl"))||sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.jxtdjl"))){
      				wHelps= setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_JLSP);
      			}else if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.fzjl"))){
      				wHelps = setWorkFlowList(processDefinitionKey, userId,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_FZJLSP);
      			}else if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.zjl"))){
      				wHelps = setWorkFlowList(processDefinitionKey, userId,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_ZJLSP);
      			}*/
      			//去掉用职务编码去限制数据的显示
      			/*WorkflowSearchInfoHelp wInfoHelp = new WorkflowSearchInfoHelp();
      	    	wInfoHelp.setProcessDefinitionKey(processDefinitionKey);
      	    	wInfoHelp.setUserId(userId);
      	    	List<WorkflowInfoHelp> wHelps = workflowService.findPackageTodoTasks(wInfoHelp);*/
                if (wHelps == null || wHelps.size() == 0)
                {
                  Map<String, Object> pMap = new HashMap<String, Object>();
                  return pMap;
                }
                  parmMap=setWorkFlowMap(wHelps,WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
               }else if("4".equals(invekey)){
                	 //特批申请
                    List<WorkflowInfoHelp> wHelpsTps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS_TPSQ);
                    if (wHelpsTps.size() == 0 )
                    {
                        Map<String, Object> parm = new HashMap<>();
                        return parm;
                    }
                    parmMap =setWorkFlowMap(wHelpsTps,WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
               }else if("5".equals(invekey)){//手机审批 暂时废掉
            	   PmPersonnel personnel =pmpersonnelDao.get(Integer.valueOf(userId));
         			SysPost sysPost = sysPostDao.get(personnel.getPersonnel_postid());
         			SysDept sysDept = sysDeptDao.getDeptById(personnel.getPersonnel_deptid());
         			List<WorkflowInfoHelp> wHelps =new ArrayList<>();
         			if(sysDept.getDept_level() == 6){
          				wHelps= setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_JLSP);
          			 }else if(sysDept.getDept_level() == 4){
          				wHelps = setWorkFlowList(processDefinitionKey, userId,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_FZJLSP);
          			 }else if(sysDept.getDept_level() == 2){
          				wHelps = setWorkFlowList(processDefinitionKey, userId,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_ZJLSP);
          			 }
         			/*if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.jl"))||sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.jxtdjl"))){
         				wHelps= setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_JLSP);
         			}else if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.fzjl"))){
         				wHelps = setWorkFlowList(processDefinitionKey, userId,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_FZJLSP);
         			}else if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.zjl"))){
         				wHelps = setWorkFlowList(processDefinitionKey, userId,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_ZJLSP);
         			}*/
         	       List<WorkflowInfoHelp> wHelpsTps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS_TPSQ);
                   if (wHelps.size() == 0 && wHelpsTps.size() == 0)
                   {
                       Map<String, Object> parm = new HashMap<>();
                       return parm;
                   }
                   wHelps.addAll(wHelpsTps);
                   if (wHelps == null || wHelps.size() == 0)
                   {
                     Map<String, Object> pMap = new HashMap<String, Object>();
                     return pMap;
                   }
                     parmMap=setWorkFlowMap(wHelps,WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
               }
        }
        else if(processDefinitionKey.equals(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS))//债权调整key
        {
    		if("1".equals(invekey)){//1代表三级审批
    			PmPersonnel personnel =pmpersonnelDao.get(Integer.valueOf(userId));
    			SysPost sysPost = sysPostDao.get(personnel.getPersonnel_postid());
    			/*List<WorkflowInfoHelp> wHelps = new ArrayList<>();
    			if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.jl"))||sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.jxtdjl"))){
    				wHelps= setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_JLSP);
    			}else if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.fzjl"))){
    				wHelps= setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_FZJLSP);
    			}else if(sysPost.getPost_number().equals(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.zjl"))){
    				wHelps= setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_ZJLSP);
    			}*/
    			//去掉用职务编码去限制数据的显示
      			WorkflowSearchInfoHelp wInfoHelp = new WorkflowSearchInfoHelp();
      	    	wInfoHelp.setProcessDefinitionKey(processDefinitionKey);
      	    	wInfoHelp.setUserId(userId);
      	    	List<WorkflowInfoHelp> wHelps = workflowService.findPackageTodoTasks(wInfoHelp);
      	    	
    			if(wHelps ==null || wHelps.size()==0){
    				Map<String,Object> pMap = new HashMap<>();
    				return pMap;
    			}
    			parmMap=setWorkFlowMap(wHelps,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS);
    		}else if("2".equals(invekey)){//2代表修订
    			List<WorkflowInfoHelp> wHelps=setWorkFlowList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_XD);
    			//如果结果集中为空并且大小等于0 代表流程中不存在
    			if(wHelps ==null || wHelps.size()==0){
    				Map<String,Object> pMap = new HashMap<>();
    				return pMap;
    			}
    			parmMap=setWorkFlowMap(wHelps,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS);
    		}else if("4".equals(invekey)){//4代表确认和特批申请 
    			List<WorkflowInfoHelp> wHelps =setWorkFlowList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_CWQR);
    			List<WorkflowInfoHelp> workflowInfoHelps1 =setWorkFlowList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_TPSQ);
    			wHelps.addAll(workflowInfoHelps1);
    			//如果结果集中为空并且大小等于0 代表流程中不存在
    			if(wHelps ==null || wHelps.size()==0){
    				Map<String,Object> pMap = new HashMap<>();
    				return pMap;
    			}
    			parmMap=setWorkFlowMap(wHelps,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS);
    		}else if("5".equals(invekey)){//代表债权调整和协议打印
    			List<WorkflowInfoHelp> wHelps =setWorkFlowList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_ZQTZ);
    			List<WorkflowInfoHelp> workflowInfoHelps1 =setWorkFlowList(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS, userId, WorkflowConstantHelp.DEBTADJUSTMENTPROCESS_XYQD);
    			wHelps.addAll(workflowInfoHelps1);
    			//如果结果集中为空并且大小等于0 代表流程中不存在
    			if(wHelps ==null || wHelps.size()==0){
    				Map<String,Object> pMap = new HashMap<>();
    				return pMap;
    			}
    			parmMap=setWorkFlowMap(wHelps,WorkflowConstantHelp.DEBTADJUSTMENTPROCESS);
    		}
        }
        else if(processDefinitionKey.equals(WorkflowConstantHelp.FINANCIALSINGLEROCESS)){//理财上单流程
        	List<WorkflowInfoHelp> wHelps = new ArrayList<>();
        	if ("1".equals(invekey)) {//待复核
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALSINGLEROCESS_FH);
				//说明不存在
				if (wHelps == null || wHelps.size()==0) {
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("2".equals(invekey)){//待支付
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALSINGLEROCESS_ZF);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("3".equals(invekey)){//待审核
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALSINGLEROCESS_ZGQR);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("4".equals(invekey)){//待签约/待确认
                wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALSINGLEROCESS_QY);
				List<WorkflowInfoHelp> khHelps =setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALSINGLEROCESS_KHQR);
				wHelps.addAll(khHelps);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("5".equals(invekey)){//待复核修订
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALSINGLEROCESS_FHXD);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("6".equals(invekey)){//待支付退回
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALSINGLEROCESS_ZFTH);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("7".equals(invekey)){//待审核退回
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALSINGLEROCESS_SHTH);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("8".equals(invekey)){//待退单退回
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALSINGLEROCESS_TDTH);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}else if("9".equals(invekey)){//待退单确认
				wHelps = setWorkFlowList(processDefinitionKey, userId, WorkflowConstantHelp.FINANCIALSINGLEROCESS_TDQR);
				//说明不存在
				if(wHelps == null || wHelps.size()==0){
					Map<String,Object> pMap = new HashMap<>();
					return pMap;
				}
			}
        	parmMap = setWorkFlowMap(wHelps, processDefinitionKey);
        }
        return parmMap;
    }
    /**
	 * 覆盖:赎回流程，债权调整流程，理财上单流程
	 * 将所有查询到结果集添加到页面显示列表 增添任务taskId属性
	 * @param list 查询业务数据集合
	 * @param idLists 查询流程业务主键集合
	 * @param taskIdLists 查询流程业务主键对应的流程任务集合
	 * @param inveHead 代表主键名称
	 */
	@Override
	public List<Map<String, Object>> addTaskIdToList(List<Map<String, Object>> list, List<Integer> idLists,List<String> taskIdLists,String processDefinitionKey) {
		 if (idLists != null)
	        {
	            for (Map<String, Object> map : list)
	            {
	            	Integer key =null;
	            	if(processDefinitionKey.equals(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS)){//赎回流程
	            		 key = (Integer) map.get("wms_inve_redeem_id");
	            	}else if(processDefinitionKey.equals(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS)){//债权调整流程
	            		 key = (Integer) map.get("wms_inve_debt_head_id");
	            	}else if(processDefinitionKey.equals(WorkflowConstantHelp.FINANCIALSINGLEROCESS)){//理财上单流程
	            		 key = (Integer) map.get("wms_inve_transa_id");
	            	}
	                for (int i = 0; i < idLists.size(); i++)
	                {
						if (idLists.get(i).compareTo(key) == 0)
	                    {
                        map.put("taskId", taskIdLists.get(i));
	                        break;
	                    }
	                }
	            }
	        }
	        else
	        {
	            list = null;
	        }
	   return list;
	}
    
    /**
     * 覆盖:赎回流程，债权调整流程,理财上单流程 历程查看功能
     * @param processDefinitionKey 流程实例key
     * @param businessKey 每个单据主表ID
     * @author hancd
     */
    @Override
    public Map<String, Object> inveWorkFlowProcess(String processDefinitionKey,String businessKey)
    {
        // 赎回流程历程查看
        Map<String, Object> paMap = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 设置流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(processDefinitionKey);
        workflowSearchInfoHelp.setFinish(true);// 查询完成
        workflowSearchInfoHelp.setBusinessKey(businessKey);//流程中单据主键
        // 读取流程返回的结果
        List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(workflowSearchInfoHelp);
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            // 根据流程里面的提供审批人id :approverids
            String approverids = workflowHistoryInfoHelp.getApproverids();
            boolean isNum = approverids.matches("[0-9]+");
            if (isNum)
            {
                PmPersonnel person = pmpersonnelDao.get(Integer.valueOf(workflowHistoryInfoHelp.getApproverids()));
                if (person != null)
                {
                    // 相应的id，获取相对应的部门名称
                    workflowHistoryInfoHelp.setPersonnel_deptName(person.getPersonnel_deptname());
                    // 相应的id，获取相对应的职务名称
                    workflowHistoryInfoHelp.setPersonnel_postName(person.getPersonnel_postname());

                    workflowHistoryInfoHelp.setPersonnel_shortCode(person.getPersonnel_shortcode());
                }
            }else{
            	//如果是上单流程 并且没有审批人 则是系统自动审批
            	if(WorkflowConstantHelp.FINANCIALSINGLEROCESS.equals(processDefinitionKey)){
	            	workflowHistoryInfoHelp.setApproveAdvice("支付倒计时作废");
	            	workflowHistoryInfoHelp.setApprovers("系统");
            	}
            }
        }

        List<WorkflowHistoryInfoHelp> works = new ArrayList<WorkflowHistoryInfoHelp>();
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            if ("true".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                if ("未审批".equals(workflowHistoryInfoHelp.getApproveAdvice()))
                {
                    // 当出现审批结果为true,但审批意见为'未审批'，说明属于特批，其他审批环节系统自动完成,不需要显示。
                    continue;
                }
                else
                {
                    /**
                     * pad端发生赎回时,不走三级审批和冯总特批的流程,
                     * 而是直接到待汇款任务节点,该判断是为了设置流程节点的名称,
                     * 因为该流程设置的节点名称为特批申请,所以设置成回款
                     */
                    if(workflowHistoryInfoHelp.getApproveAdvice() != null && "回款完成".equals(workflowHistoryInfoHelp.getApproveAdvice()) && "特批申请".equals(workflowHistoryInfoHelp.getTaskName()))
                    {
                        workflowHistoryInfoHelp.setTaskName("回款");
                    }
                    // 当出现审批结果为true,审批意见为其他内容，说明属于正常审批通过。
                    workflowHistoryInfoHelp.setApproveResult("审批通过");
                }
            }
            else if ("noagree".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                // 当出现noagree的值时，都属于系统自动完成不需要显示
                continue;
            }
            else if ("false".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                // 当出现false的值时,说明审批结果为不通过
                workflowHistoryInfoHelp.setApproveResult("审批不通过");
            }
            else if ("over".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                continue;
            }
            else if ("is_over".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                workflowHistoryInfoHelp.setApproveResult("已终止");
            }
            else if ("is_cancel".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                workflowHistoryInfoHelp.setApproveResult("撤单");
            }
            else if("nullify".equals(workflowHistoryInfoHelp.getApproveResult())){
            	// 当出现false的值时,说明审批结果为不通过
                workflowHistoryInfoHelp.setApproveResult("已终止");
            }else if("undo1".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("[客户撤销>[协议签订]]");
            }else if("undo2".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("[客户撤销>[客户确认]]");
            }else if("compe".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("已完成");
            }else  if("supply".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("退回");
            }else if(workflowHistoryInfoHelp.getApproveResult() == null || "".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                WmsInveSalaryPreTotal wmsInveSalaryPreTotal = wmsInveSalaryPreTotalDao.get(Integer.parseInt(businessKey));
                if(wmsInveSalaryPreTotal.getData_status() != null && "4".equals(wmsInveSalaryPreTotal.getData_status()))
                {
                    workflowHistoryInfoHelp.setApproveResult("审批通过");
                    workflowHistoryInfoHelp.setApproveAdvice("已完成(系统自动)");
                }
            }
            // 把所有true,false,noagree,nullify,over进行中文翻译，添加到works中
            works.add(workflowHistoryInfoHelp);
        }
        paMap.put("Rows", works);
        return paMap;
    }

	    /**
     * 涵盖:赎回流程，债权调整流程,,理财上单流程
     * 实现所有流程所有节点的审批操作同时改变所有操作后的单据状态变化
     *processDefinitionKey=financialRedemptionProcess;//赎回流程
     * @param debtkey 任务节点key
     *       当debtkey=1 代表团队经理审批
     *       当debtkey=2 代表副总经理审批
     *       当debtkey=3 代表总经理审批
     *       当debtkey=4 代表修订
     *       当debtkey=5 代表确认/回款
     *       当debtkey=6 代表特批申请
     *       当debtkey=7 代表取消申请
     * processDefinitionKey=debtAdjustmentProcess;//债权调整流程 
     * @param debtkey 任务节点key  
     *       当debtkey=1 代表团队经理审批
     *       当debtkey=2 代表副总经理审批
     *       当debtkey=3 代表总经理审批
     *       当debtkey=4 代表修订
     *       当debtkey=5 代表确认/回款
     *       当debtkey=6 代表特批申请
     *       //当debtkey=7 代表债权调整
     *       //当debtkey=8 代表协议签订
     * processDefinitionKey=financialSingleProcess;//理财上单流程
     * @param debtkey 任务节点key
     *      当debtkey=1 代表复核
     *      当debtkey=2 代表支付
     * 		当debtkey=3 代表主管确认
     * 		当debtkey=4 代表协议签订
     * 		当debtkey=5 代表客户确认
     * 		当debtkey=6 代表复核修订
     * 		当debtkey=7 代表支付退回
     * 		当debtkey=8 代表审核退回
     * 		当debtkey=9 代表退单退回
     * 		当debtkey=10 代表退单确认
     * @return 
     * @author hancd
     */
	@Override
	public void publicApproval(WmsInveDebtWorkFlowVO wDebtWorkFlowVO, Integer... wms_inve_transa_id) {
		java.sql.Date systime = new java.sql.Date(System.currentTimeMillis());
		Map<String,String> formPropertiesMap = new HashMap<String, String>();
		formPropertiesMap.put("pass",wDebtWorkFlowVO.getPass());
		formPropertiesMap.put("advice", wDebtWorkFlowVO.getAdvice());
		Map<String,String> sendMap = new HashMap<String, String>();//发送短信的方法
		
		if (wDebtWorkFlowVO.getProcessDefinitionKey().equals(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS)) {//赎回流程
			WmsInveRedeem wmsInveRedeem = new WmsInveRedeem();
			wmsInveRedeem.setWms_inve_redeem_id(Integer.valueOf(wDebtWorkFlowVO.getBusinessId()));
			
			int wmsInveTransaId = 0;
    		if(wms_inve_transa_id==null || wms_inve_transa_id.length == 0){
    			wmsInveTransaId = wmsInveTransaDao.getWmsInveTransaIdByWmsInveRedeemId(Integer.valueOf(wDebtWorkFlowVO.getBusinessId()));
    		}else{
    			wmsInveTransaId = wms_inve_transa_id[0];
    		}
	        if ("1".equals(wDebtWorkFlowVO.getDebtkey()))//debtkey=1 代表待团队经理审批环节
	        {
                String task_id = wDebtWorkFlowVO.getTaskId();
                if (StringUtil.isBlank(task_id))
                {
                    Task task = workflowService.getTaskInfobyKey(wDebtWorkFlowVO.getBusinessId(), WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS_JLSP, wDebtWorkFlowVO.getProcessDefinitionKey());
                    wDebtWorkFlowVO.setTaskId(task.getId());
                }
	            // 获取该经理审批所在部门ID
	            if ("true".equals(wDebtWorkFlowVO.getPass()))
	            {
	            	Map<String, Object> wmsInveTransa = wmsInveTransaDao.getObjectInfo(wmsInveTransaId);
	            	formPropertiesMap.put("userId", "" + wmsInveTransa.get("bel_vice_general_manager_id"));
             		//单据状态变为待副总审批状态:2
	            	formPropertiesMap.put("data_status", "2");
	            	wmsInveRedeem.setData_status("2");
	            	PmPersonnel pm = pmpersonnelDao.get(Integer.valueOf("" + wmsInveTransa.get("bel_vice_general_manager_id")));
                    //发送短信参数
                 	if(formPropertiesMap.get("userId")!=null){
                 		sendMap.put("name",pm.getPersonnel_name());//人名称
		            	sendMap.put("tel", pm.getPersonnel_extensiontel());//电话	
                 		getPmInfo(formPropertiesMap.get("userId").toString(),sendMap);
                 	}
                    sendJGHandler("1", Integer.valueOf(wDebtWorkFlowVO.getBusinessId()), "" + wmsInveTransa.get("bel_vice_general_manager_id"));
	            }else if("false".equals(wDebtWorkFlowVO.getPass())){
	            	wmsInveRedeem.setData_status("4");//待修订
	            }
	            wmsInveRedeem.setSupervisor_advice(wDebtWorkFlowVO.getAdvice());
            	wmsInveRedeem.setSupervisor_result(wDebtWorkFlowVO.getPass().equals("true")?"1":"0");
            	wmsInveRedeem.setSupervisor_date(systime);
	        }
	        else if ("2".equals(wDebtWorkFlowVO.getDebtkey()))// 当Invekey=2为待副总经理审批环节
	        {
                String task_id = wDebtWorkFlowVO.getTaskId();
                if (StringUtil.isBlank(task_id))
                {
                    Task task = workflowService.getTaskInfobyKey(wDebtWorkFlowVO.getBusinessId(), WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS_FZJLSP, wDebtWorkFlowVO.getProcessDefinitionKey());
                    wDebtWorkFlowVO.setTaskId(task.getId());
                }
	            if ("true".equals(wDebtWorkFlowVO.getPass()))
	            {
	            	wmsInveRedeem.setData_status("3");//待总经理审批
	            	Map<String, Object> wmsInveTransa = wmsInveTransaDao.getObjectInfo(wmsInveTransaId);
	            	formPropertiesMap.put("userId", "" + wmsInveTransa.get("bel_general_manager_id"));
             		//单据状态变为待总经理审批状态:3
	            	formPropertiesMap.put("data_status", "3");
	            	wmsInveRedeem.setData_status("3");
	            	PmPersonnel pm = pmpersonnelDao.get(Integer.valueOf("" + wmsInveTransa.get("bel_general_manager_id")));
                    //发送短信参数
                 	if(formPropertiesMap.get("userId")!=null){
                 		sendMap.put("name",pm.getPersonnel_name());//人名称
		            	sendMap.put("tel", pm.getPersonnel_extensiontel());//电话	
                 		getPmInfo(formPropertiesMap.get("userId").toString(),sendMap);
                 	}
                    sendJGHandler("2", Integer.valueOf(wDebtWorkFlowVO.getBusinessId()), "" + wmsInveTransa.get("bel_general_manager_id"));
	            }else if("false".equals(wDebtWorkFlowVO.getPass())){
	            	wmsInveRedeem.setData_status("4");//待修订
	            }
	            wmsInveRedeem.setSubmanager_advice(wDebtWorkFlowVO.getAdvice());
            	wmsInveRedeem.setSubmanager_result(wDebtWorkFlowVO.getPass().equals("true")?"1":"0");
            	wmsInveRedeem.setSubmanager_date(systime);
	        }
	        else if ("3".equals(wDebtWorkFlowVO.getDebtkey()))// 当Invekey=3为待总经理审批环节
	        {
                String task_id = wDebtWorkFlowVO.getTaskId();
                if (StringUtil.isBlank(task_id))
                {
                    Task task = workflowService.getTaskInfobyKey(wDebtWorkFlowVO.getBusinessId(), WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS_ZJLSP, wDebtWorkFlowVO.getProcessDefinitionKey());
                    wDebtWorkFlowVO.setTaskId(task.getId());
                }
	            if ("true".equals(wDebtWorkFlowVO.getPass()))
	            {
	            	wmsInveRedeem.setData_status("5");
                    sendJGHandler("3", Integer.valueOf(wDebtWorkFlowVO.getBusinessId()), "");
	            }
	            else if ("false".equals(wDebtWorkFlowVO.getPass()))
	            {
	                // 状态变待修订4
	            	wmsInveRedeem.setData_status("4");
	            }
	            wmsInveRedeem.setManager_advice(wDebtWorkFlowVO.getAdvice());
	            wmsInveRedeem.setManager_result(wDebtWorkFlowVO.getPass().equals("true")?"1":"0");
	            wmsInveRedeem.setManager_date(systime);
	        }
	        else if ("4".equals(wDebtWorkFlowVO.getDebtkey()))// 当Invekey=4为重新修订
	        {
	            if (wDebtWorkFlowVO.getPass().equals("true"))
	            {
	            	//TODO 重新修订
	            	//根据上单信息id获取对应的上单信息
	        		Map<String, Object> wmsInveTransa = wmsInveTransaDao.getObjectInfo(wmsInveTransaId);
	        		//根据当前人员id获取对应的人员信息
	            	Map<String, Object> personnelMap = (Map<String, Object>) pmpersonnelDao.getPersonnelByPersonnelId(Integer.parseInt(""+wmsInveTransa.get("salesman_id")));
	                boolean flag = true;
	                if((personnelMap.get("companyName") + "").equals("财富管理中心")){
	                	SysDept sysDept = sysDeptDao.getDeptById(Integer.parseInt(wmsInveTransa.get("bel_salesman_dept_id") + ""));
	            		if(sysDept.getDept_level() == 6){
	            			//需要判断是否是外阜
	            			//根据人员所在部门id获取对应的职务信息
	                    	List<SysPost> list= sysPostDao.getSysPostBySalesManDeptId(Integer.parseInt(""+wmsInveTransa.get("bel_center_manager_dept_id")));
	                    	boolean is_waifu = false;
	                    	for(SysPost sysPost : list){
	                    		if(sysPost.getPost_number().startsWith(PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.waifu"))){
	                    			is_waifu = true;
	                    			break;
	                    		}
	                    	}
	                    	if (!is_waifu) {
	                    	    //部门经理、副总、总
	                    		if(wmsInveTransa.get("bel_department_manager_id")!=null && wmsInveTransa.get("bel_vice_general_manager_id")!=null && wmsInveTransa.get("bel_general_manager_id") !=null){
	                    			int personnelBuMenJingLiId = Integer.parseInt(wmsInveTransa.get("bel_department_manager_id") + "");
	                        		int personnelFuZongId = Integer.parseInt(wmsInveTransa.get("bel_vice_general_manager_id") + "");
	                         		int personnelZongId = Integer.parseInt(wmsInveTransa.get("bel_general_manager_id") + "");
	                        		if(personnelBuMenJingLiId != personnelFuZongId && personnelFuZongId != personnelZongId && personnelFuZongId != personnelZongId){
	                        			flag = false;
	                        			wmsInveRedeem.setData_status("1");
	                		           	formPropertiesMap.put("advice", "完成重新修订");
	                		            // 需要获取原来单据业务员的部门
	                		           	formPropertiesMap.put("userId", ""+wmsInveTransa.get("bel_department_manager_id"));// 添加需要指派的人ID
	                        		}
	                    		}
	    					}
	            		}
	                }
	                if(flag){
	                	wmsInveRedeem.setData_status("3");
		            	formPropertiesMap.put("advice", "完成重新修订");
		                // 需要获取原来单据业务员的部门
		            	formPropertiesMap.put("userId", ""+wmsInveTransa.get("bel_general_manager_id"));// 添加需要指派的人ID
	                }
	            }
	            else if(wDebtWorkFlowVO.getPass().equals("false"))
	            {
	            	wmsInveRedeem.setData_status("7");
	            	formPropertiesMap.put("advice", "取消修订");
	            }
	        }
	        else if ("5".equals(wDebtWorkFlowVO.getDebtkey()))// 当Invekey=5为回款审批
	        {
	        	if(wmsInveRedeemDao.get(wmsInveRedeem.getWms_inve_redeem_id()).getIs_fully_redeemed().equals("1")){//全部赎回
	        		 wmsInveRedeem.setData_status("6");
	        		 formPropertiesMap.put("pass", "true");
	 	             formPropertiesMap.put("advice", "回款完成");
	 	             //批量把上单单据变成已完成
	 	             for (int i = 0; i < wDebtWorkFlowVO.getwInveTransaIds().size(); i++)
	 	             {
	 	                WmsInveTransa wInveTransa = new WmsInveTransa();
	 	                wInveTransa.setWms_inve_transa_id(Integer.valueOf(wDebtWorkFlowVO.getwInveTransaIds().get(i)));
	 	                wInveTransa.setData_status("6");//已完成
	 	                wInveTransaDao.updateForRedeem(wInveTransa);
	 	             }
	        	}
	        	else if(wmsInveRedeemDao.get(wmsInveRedeem.getWms_inve_redeem_id()).getIs_fully_redeemed().equals("0")){//部分赎回
	        		 wmsInveRedeem.setData_status("6");
	        		 formPropertiesMap.put("pass", "true");
	 	             formPropertiesMap.put("advice", "回款完成");
	        	}
                sendJGHandler("5", Integer.valueOf(wDebtWorkFlowVO.getBusinessId()), "");
	        }
	        else if ("6".equals(wDebtWorkFlowVO.getDebtkey()))// 当Invekey=6为特批申请审批
	        {
	            String task_id = wDebtWorkFlowVO.getTaskId();
	            if(StringUtil.isBlank(task_id)){
                    Task task = workflowService.getTaskInfobyKey(wDebtWorkFlowVO.getBusinessId(), WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS_JLSP, wDebtWorkFlowVO.getProcessDefinitionKey());
                    wDebtWorkFlowVO.setTaskId(task.getId());
	            }
	            if ("true".equals(wDebtWorkFlowVO.getPass()))
	            {
	                // 状态：待回款 5
	                wmsInveRedeem.setData_status("5");
                    sendJGHandler("6", Integer.valueOf(wDebtWorkFlowVO.getBusinessId()), "");
	            }
                else if ("is_over".equals(wDebtWorkFlowVO.getPass()))
	            {
	                // 状态:已终止
	            	 wmsInveRedeem.setData_status("7");
	            }
	        }
            else if ("7".equals(wDebtWorkFlowVO.getDebtkey()))// 当Invekey=7单据作废
            {
                if (StringUtil.isBlank(wDebtWorkFlowVO.getTaskId()))
                {
                    String task_id = workflowService.getTaskIdByBusinessId(wDebtWorkFlowVO.getBusinessId(), wDebtWorkFlowVO.getProcessDefinitionKey());
                    wDebtWorkFlowVO.setTaskId(task_id);
                }
                wmsInveRedeem.setData_status("7");
            }
	        wmsInveRedeemDao.update(wmsInveRedeem);
            
		}else if(wDebtWorkFlowVO.getProcessDefinitionKey().equals(WorkflowConstantHelp.DEBTADJUSTMENTPROCESS)){//债权调整流程
			WmsInveDebtHead wmsInveDebtHead = new WmsInveDebtHead();
			wmsInveDebtHead.setWms_inve_debt_head_id(Integer.valueOf(wDebtWorkFlowVO.getBusinessId()));
			if("1".equals(wDebtWorkFlowVO.getDebtkey())){//待团队经理或者待见习团队经理审批
				if("true".equals(wDebtWorkFlowVO.getPass()))
				{
					wmsInveDebtHead.setData_status("2");//待副总经理审批
				}
				else if("false".equals(wDebtWorkFlowVO.getPass()))
				{
					wmsInveDebtHead.setData_status("4");//待修订
				}
				wmsInveDebtHead.setSup_user_id(Integer.valueOf(wDebtWorkFlowVO.getUserID()));
				if(wDebtWorkFlowVO.getPass().equals("true")){
					wmsInveDebtHead.setSup_result("1");
				}else if(wDebtWorkFlowVO.getPass().equals("false")){
					wmsInveDebtHead.setSup_result("0");
				}
				wmsInveDebtHead.setSup_dept_id(pmpersonnelDao.get(Integer.valueOf(wDebtWorkFlowVO.getUserID())).getPersonnel_deptid());
				wmsInveDebtHead.setSup_date(systime);
				wmsInveDebtHead.setSup_advice(wDebtWorkFlowVO.getAdvice());
				
			}else if("2".equals(wDebtWorkFlowVO.getDebtkey())){//代表副总经理审批
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setData_status("3");//待总经理审批
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setData_status("4");//待修订
				}
				wmsInveDebtHead.setSubmger_user_id(Integer.valueOf(wDebtWorkFlowVO.getUserID()));
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setSubmger_result("1");
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setSubmger_result("0");
				}
				wmsInveDebtHead.setSubmger_dept_id(pmpersonnelDao.get(Integer.valueOf(wDebtWorkFlowVO.getUserID())).getPersonnel_deptid());
				wmsInveDebtHead.setSubmger_date(systime);
				wmsInveDebtHead.setSubmger_advice(wDebtWorkFlowVO.getAdvice());
				formPropertiesMap.put("userId", getUserID(pmpersonnelDao.get(Integer.valueOf(wDebtWorkFlowVO.getUserID())).getPersonnel_deptid().toString(), "2").get(0).get("personnel_id").toString());// 添加需要指派的人ID
				
			}else if("3".equals(wDebtWorkFlowVO.getDebtkey())){//代表总经理审批
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setData_status("5");//待确认
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setData_status("4");//待修订
				}
				wmsInveDebtHead.setMger_user_id(Integer.valueOf(wDebtWorkFlowVO.getUserID()));
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setMger_result("1");
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setMger_result("0");
				}
				wmsInveDebtHead.setMger_dept_id(pmpersonnelDao.get(Integer.valueOf(wDebtWorkFlowVO.getUserID())).getPersonnel_deptid());
				wmsInveDebtHead.setMger_date(systime);
				wmsInveDebtHead.setMger_advice(wDebtWorkFlowVO.getAdvice());
				formPropertiesMap.put("userId", getUserID(pmpersonnelDao.get(Integer.valueOf(wDebtWorkFlowVO.getUserID())).getPersonnel_deptid().toString(), "3").get(0).get("personnel_id").toString());// 添加需要指派的人ID
				
			}else if("4".equals(wDebtWorkFlowVO.getDebtkey())){//代表修订
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setData_status("1");//待团队经理或见习团队经理审批
					wDebtWorkFlowVO.setAdvice("修订已完成");
					formPropertiesMap.put("userId", getUserID(wDebtWorkFlowVO.getCxdept_id(), "1").get(0).get("personnel_id").toString());// 添加需要指派的人ID
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wDebtWorkFlowVO.setAdvice("取消修订");
					wmsInveDebtHead.setData_status("7");//已终止
				}

			}else if("5".equals(wDebtWorkFlowVO.getDebtkey())){//代表确认
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setData_status("8");//待债权调整
					wDebtWorkFlowVO.setAdvice("确认完毕");
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wDebtWorkFlowVO.setAdvice("单据作废");
					wmsInveDebtHead.setData_status("7");//已终止
				}
				
			}else if("6".equals(wDebtWorkFlowVO.getDebtkey())){//代表特批申请
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setData_status("5");//待确认
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setData_status("7");//已终止
				}
				
			}else if("7".equals(wDebtWorkFlowVO.getDebtkey())){//代表债权调整
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setData_status("9");//待协议签订
					wDebtWorkFlowVO.setAdvice("债权调整完毕");
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wDebtWorkFlowVO.setAdvice("单据作废");
					wmsInveDebtHead.setData_status("7");//已终止
				}
				
			}else if("8".equals(wDebtWorkFlowVO.getDebtkey())){//代表协议打印
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveDebtHead.setData_status("6");//已完成
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wDebtWorkFlowVO.setAdvice("单据作废");
					wmsInveDebtHead.setData_status("7");//已终止
				}
			}
			//更改状态
			wmsInveDebtHeadDao.update(wmsInveDebtHead);
		}else if(wDebtWorkFlowVO.getProcessDefinitionKey().equals(WorkflowConstantHelp.FINANCIALSINGLEROCESS)){//理财上单流程
			WmsInveTransa wmsInveTransa = new WmsInveTransa();
			wmsInveTransa.setWms_inve_transa_id(Integer.valueOf(wDebtWorkFlowVO.getBusinessId()));
			if("1".equals(wDebtWorkFlowVO.getDebtkey())){//复核
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("2");//待支付
					formPropertiesMap.put("overtime",WorkflowConstantHelp.FINANCIALSINGLEROCESS_ZF_TIME);//定时结束时间 
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("10");//复核修订
				}
			}else if("2".equals(wDebtWorkFlowVO.getDebtkey())){//支付
				if("true".equals(wDebtWorkFlowVO.getPass())){
                    wmsInveTransa.setData_status("13");// 签约
					formPropertiesMap.put("advice","支付已完成");
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("17");//支付退回
				}
            }
            else if ("3".equals(wDebtWorkFlowVO.getDebtkey()))
            {// 签约
				if("true".equals(wDebtWorkFlowVO.getPass())){
                    wmsInveTransa.setData_status("11");// 待审核
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("12");//待审核退回
				}
                // else if("nullify".equals(wDebtWorkFlowVO.getPass())){//单据作废
                // wmsInveTransa.setData_status("7");//已终止
                // }else if("compe".equals(wDebtWorkFlowVO.getPass())){//节流处理
                // wmsInveTransa.setData_status("4");//收益中
                // }
			}else if("4".equals(wDebtWorkFlowVO.getDebtkey())){//协议签订
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("14");//待客户确认
					formPropertiesMap.put("advice","[协议签订]-已签约");
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("16");//待退单确认
					formPropertiesMap.put("advice","[协议签订]-已退单确认");
				}
            }
            else if ("5".equals(wDebtWorkFlowVO.getDebtkey()))
            {// 上单审核通过
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("4");//收益中
                    formPropertiesMap.put("advice", "");
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("16");//待退单确认
					formPropertiesMap.put("advice","[客户确认]-已退单申请");
				}
			}else if("6".equals(wDebtWorkFlowVO.getDebtkey())){//复核修订
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("9");//待复核
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("7");//已终止
				}
			}else if("7".equals(wDebtWorkFlowVO.getDebtkey())){//支付退回
				if("true".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("2");//待支付
				}else if("nullify".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("7");//已终止
				}
			}else if("8".equals(wDebtWorkFlowVO.getDebtkey())){//审核退回
					wmsInveTransa.setData_status("11");//待审核
					formPropertiesMap.put("pass","true");
					formPropertiesMap.put("advice","[审核退回]-已重新审核");
			}else if("9".equals(wDebtWorkFlowVO.getDebtkey())){//退单退回
					wmsInveTransa.setData_status("16");//待退单确认
					formPropertiesMap.put("pass","true");
					formPropertiesMap.put("advice","[退单退回]-已重新申请");
			}else if("10".equals(wDebtWorkFlowVO.getDebtkey())){//退单确认
				if ("true".equals(wDebtWorkFlowVO.getPass())) {
					wmsInveTransa.setData_status("7");//已终止
					formPropertiesMap.put("inputkey","0");
				}else if("false".equals(wDebtWorkFlowVO.getPass())){
					wmsInveTransa.setData_status("15");//待退单退回
					formPropertiesMap.put("inputkey","0");
				}else{//执行退单撤销
					//需要判断属于哪个是返回去的路，需要查询流程历程倒序,判断协议签订节点和客户确认节点并且result等于false
					WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
			        // 设置流程实例key
			        workflowSearchInfoHelp.setProcessDefinitionKey(wDebtWorkFlowVO.getProcessDefinitionKey());
			        workflowSearchInfoHelp.setFinish(true);// 查询完成
			        workflowSearchInfoHelp.setBusinessKey(wDebtWorkFlowVO.getBusinessId());//流程中单据主键
			        // 读取流程返回的结果
			        List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(workflowSearchInfoHelp);
				    // 无论循环多少次，都是这样判断，一旦成立，就存入相对应的值并且跳出循环体
			        for(int i=workflowHistoryInfoHelps.size()-1;i>=0;i--){
			        	if(workflowHistoryInfoHelps.get(i).getTaskName().equals(WorkflowConstantHelp.FINANCIALSINGLEROCESS_XYQD)&&workflowHistoryInfoHelps.get(i).getApproveResult().equals("false")){
			        		formPropertiesMap.put("pass","undo1");//指向协议签订
			        		formPropertiesMap.put("inputkey","1");//指向协议签订
			        		formPropertiesMap.put("advice","[退单确认]-->协议签订");//指向协议签订
			        		wmsInveTransa.setData_status("13");//待签约
			        		break;
			        	}else if(workflowHistoryInfoHelps.get(i).getTaskName().equals(WorkflowConstantHelp.FINANCIALSINGLEROCESS_KHQR)&&workflowHistoryInfoHelps.get(i).getApproveResult().equals("false")){
			        		formPropertiesMap.put("pass","undo2");//指向客户确认
			        		formPropertiesMap.put("inputkey","2");//指向客户确认
			        		formPropertiesMap.put("advice","[退单确认]-->客户确认");//指向协议签订
			        		wmsInveTransa.setData_status("14");//待确认
			        		break;
			        	}
			        	
			        }
				}
			}
            else if ("11".equals(wDebtWorkFlowVO.getDebtkey()))
            {// 撤单
                if ("is_cancel".equals(wDebtWorkFlowVO.getPass()))
                {
                    wmsInveTransa.setData_status("20");// 待修订（主动撤单）
                    formPropertiesMap.put("advice", wDebtWorkFlowVO.getAdvice());
                }
            }
            else if ("12".equals(wDebtWorkFlowVO.getDebtkey()))
            {// 作废
                if ("nullify".equals(wDebtWorkFlowVO.getPass()))
                {
                    wmsInveTransa.setData_status("7");
                    formPropertiesMap.put("advice", wDebtWorkFlowVO.getAdvice());
                }
            }
            else if ("13".equals(wDebtWorkFlowVO.getDebtkey()))
            {// 重新提交
                if ("true".equals(wDebtWorkFlowVO.getPass()))
                {
                    // 待修订（审核退回）
                    if ("12".equals(wDebtWorkFlowVO.getStatus()))
                    {
                        wmsInveTransa.setData_status("11");
                    }
                    // 待修订（支付退回）
                    else if ("17".equals(wDebtWorkFlowVO.getStatus()))
                    {
                        wmsInveTransa.setData_status("2");
                    }
                    // 待修订（签约退回）
                    else if ("19".equals(wDebtWorkFlowVO.getStatus()))
                    {
                        wmsInveTransa.setData_status("13");
                    }
                    // 待修订（主动撤单）
                    else if ("20".equals(wDebtWorkFlowVO.getStatus()))
                    {
                        wmsInveTransa.setData_status("2");
                    }

                    formPropertiesMap.put("advice", "重新提交");
                }
            }
            else if ("14".equals(wDebtWorkFlowVO.getDebtkey()))
            {// 退回流程操作
                if ("false".equals(wDebtWorkFlowVO.getPass()))
                {
                    // 待支付
                    if ("2".equals(wDebtWorkFlowVO.getStatus()))
                    {
                        wmsInveTransa.setData_status("17");
                    }
                    // 待签约
                    else if ("13".equals(wDebtWorkFlowVO.getStatus()))
                    {
                        wmsInveTransa.setData_status("19");
                    }
                    // 待审核
                    else if ("11".equals(wDebtWorkFlowVO.getStatus()))
                    {
                        wmsInveTransa.setData_status("12");
                    }

                    formPropertiesMap.put("advice", wDebtWorkFlowVO.getAdvice());
                }
            }
			//更改状态
			wmsInveTransa.setLast_update_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
			wmsInveTransaDao.update(wmsInveTransa);
		}
		//调用流程
		workflowService.completeTask(wDebtWorkFlowVO.getUserID(), wDebtWorkFlowVO.getTaskId(), formPropertiesMap);
	}
    /**
     * 实现查找下一节点审批人ID
     * 
     * @param salesman_dept_id 业务员所在部门ID
     * @param key 实现打开要实现查找什么？ key=1 代表查找该业务员对应的部门经理 key=2
     *            代表查找该业务员对应部门上一级对应的副总经理 key=3 代表查找该业务员对应部门上一级对应的总经理 key=0
     *            处理特殊情况
     * @return idList
     */
    private List<Map<String, Object>> getUserID(String salesman_dept_id, String key)
    {
        List<Map<String, Object>> idList = null;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dept_Id", salesman_dept_id);
        //特批人
        if ("0".equals(key))
        {
            String userCode = PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.usercode");
            parameters.put("personnel_shortcode", userCode);
            idList = pmpersonnelDao.searchforNodept(parameters);
        }
        //查找团队经理
        else if ("1".equals(key))
        {
            // 通过业务员部门ID，以及职务编码，查找该业务员的所在部门的经理ID
            //parameters.put("post_number", PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.jl"));
            // 通过业务员部门ID，以及职务编码，查找该业务员的所在部门的团队经理ID  2015-7-7
            parameters.put("post_number", PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.jl"));
            idList = pmpersonnelDao.searchforlcjl(parameters);
        }
        //查找副总经理
        else if ("2".equals(key))
        {
            // 通过业务员部门ID，以及职务编码，查找该业务员的所在部门上一级部门副总经理
            parameters.put("post_number", PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.fzjl"));
            idList = pmpersonnelDao.searchforlcfzjl(parameters);
        }
        //查找总经理
        else if ("3".equals(key))
        {
            // 通过业务员部门ID，以及职务编码，查找该业务员的所在部门上一级部门 总经理
            parameters.put("post_number", PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.zjl"));
            idList = pmpersonnelDao.searchforlczjl(parameters);
        }
        //查找见习团队经理
        else if("4".equals(key))
        {
        	 parameters.put("post_number", PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.jxtdjl"));
        	 idList = pmpersonnelDao.searchforlcjxjl(parameters);
        }
        return idList;
    }
    
    /**
     * 实现公共获取流程节点查询该节点数据
     * @param processDefinitionKey 流程实例key
     * @param userId 当前审批ID
     * @param taskName 当前节点名称
     * @return
     */
    private List<WorkflowInfoHelp> setWorkFlowList(String processDefinitionKey,String userId,String taskName){
    	WorkflowSearchInfoHelp wInfoHelp = new WorkflowSearchInfoHelp();
    	wInfoHelp.setProcessDefinitionKey(processDefinitionKey);
    	wInfoHelp.setUserId(userId);
    	wInfoHelp.setTaskName(taskName);
    	List<WorkflowInfoHelp> wHelps = workflowService.findPackageTodoTasks(wInfoHelp);
    	return wHelps;
    }
    /**
     * 实现公共获取流程节点查询该节点数据--根据业务主见查询当前单据的taskId
     * @param processDefinitionKey 流程实例key
     * @param businessKey 当前节点名称
     * @return
     */
    private List<WorkflowInfoHelp> getTaskIdbyBusinessKey(String processDefinitionKey,String businessKey){
    	WorkflowSearchInfoHelp wInfoHelp = new WorkflowSearchInfoHelp();
    	wInfoHelp.setProcessDefinitionKey(processDefinitionKey);
    	wInfoHelp.setBusinessKey(businessKey);
    	List<WorkflowInfoHelp> wHelps = workflowService.findPackageTodoTasks(wInfoHelp);
    	return wHelps;
    }
    /**
     * 实现公共获取流程节点查询数据返回结果
     * @param wHelps
     * @return
     */
    private Map<String,Object> setWorkFlowMap(List<WorkflowInfoHelp> wHelps,String processDefinitionKey){
    	 Map<String, Object> parmMap = new HashMap<String, Object>();
         List<Integer> idList = new ArrayList<Integer>();
         List<String> taskIdList = new ArrayList<String>();
         for (WorkflowInfoHelp workflowInfoHelp : wHelps)
         {
             idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
             taskIdList.add(workflowInfoHelp.getTaskId());
         }
         parmMap.put("idList", idList);
         parmMap.put("taskIdList", taskIdList);
         parmMap.put("processDefinitionKey", processDefinitionKey);
         return parmMap;
    }
    
    /**
     * 根据人员获取当前人的电话()
     * @param userId
     * @param sendMap
     */
    public void getPmInfo(String userId, Map<String, String> sendMap)
    {
    	 //发送短信参数
        // if(userId!=null){
        // try {
        // PmPersonnel pmpersonnel=pmpersonnelDao.get(Integer.valueOf(userId));
        // if(pmpersonnel!=null){
        // sendMap.put("tel", pmpersonnel.getPersonnel_contacttel());//电话
        // }
        // //发送短信
        // sendMap.put("tpl_id", "1689");
        //
        // //参数map
        // Map<String, Object> paramMap = new HashMap<String, Object>();
        // paramMap.put("url", PlatformGlobalVar.SYS_PROPERTIES.get("zshUrl"));
        // Gson gson = new Gson();
        // sendMap.put("paramJson", gson.toJson(paramMap));
        //
        // SysUtil.sendMsg(sendMap);
        // }catch(Exception e){
        // }
        // }
    }
    /**
     * 根据人员获取当前人的电话
     * @param userId
     * @param sendMap
     */
    public void  getPmInfo(Map<String,String> sendMap){
    		try {
	    		//发送短信
    		    sendMap.put("tpl_id", "1689");

                //参数map
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("url", PlatformGlobalVar.SYS_PROPERTIES.get("zshUrl"));
                Gson gson = new Gson();
                sendMap.put("paramJson", gson.toJson(paramMap));
    		    
		        SysUtil.sendMsg(sendMap);
    		}catch(Exception e){
    		}
    }

    /**
     * 
     * @Title: sendJGHandler
     * @Description: 极光推送消息处理
     * @param data_status 三级审批(01表示为业务员提交赎回申请,1表示为部门经理提交审核,2表示副总提交审核,3表示总提交审核)
     *                    非三级审批(03表示为提交赎回申请,6表示为冯总特批,5表示为财务审批)
     * @param wms_inve_redeem_id
     * @param userId 待审批人的userId
     * @author: DongHao
     * @time:2016年12月9日 上午11:53:39
     * history:
     * 1、2016年12月9日 DongHao 创建方法
     */
    public void sendJGHandler(String data_status, int wms_inve_redeem_id, String userId)
    {
        List<SysSendInfoVO> list = new ArrayList<SysSendInfoVO>();
        // 查询数据单据信息参数map集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("wms_inve_redeem_id", wms_inve_redeem_id);
        Map<String, Object> wmsInveRedeemMap = wmsInveRedeemDao.getRedeemInfo(paramsMap);

        if ("01".equals(data_status))
        {
            // 设置极光推送的附加信息
            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            paramMap1.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap1.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            paramMap1.put("is_approval", 0);
            // 推送给客户经理
            List<String> codeLis = new ArrayList<String>();
            Map<String, String> msgMap1 = new HashMap<String, String>();
            msgMap1.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap1.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            msgMap1.put("depart_manager", wmsInveRedeemMap.get("department_manager_name").toString() + " " + wmsInveRedeemMap.get("department_manager_code").toString());
            codeLis.add(wmsInveRedeemMap.get("salesman_ishortCode").toString());
            SysSendInfoVO sysSendInfoVO1 = new SysSendInfoVO();
            sysSendInfoVO1.setUser_code(codeLis);// 人员短工号
            sysSendInfoVO1.setMsg_code("30001");// 消息编码
            sysSendInfoVO1.setMap(msgMap1);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO1.setExtras(paramMap1);// 消息附加数据
            list.add(sysSendInfoVO1);

            // 设置极光推送的附加信息
            Map<String, Object> paramMap2 = new HashMap<String, Object>();
            paramMap2.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap2.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());

            if (!"".equals(userId) && userId.equals(wmsInveRedeemMap.get("bel_department_manager_id").toString()))
            {
                paramMap2.put("is_approval", 1);
            }
            else
            {
                paramMap2.put("is_approval", 0);
            }

            // 推送给部门经理
            List<String> codeLis1 = new ArrayList<String>();
            Map<String, String> msgMap2 = new HashMap<String, String>();
            msgMap2.put("salesman_manager", wmsInveRedeemMap.get("salesman_name").toString() + " " + wmsInveRedeemMap.get("salesman_ishortCode").toString());
            msgMap2.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap2.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            codeLis1.add(wmsInveRedeemMap.get("department_manager_code").toString());
            SysSendInfoVO sysSendInfoVO2 = new SysSendInfoVO();
            sysSendInfoVO2.setUser_code(codeLis1);// 人员短工号
            sysSendInfoVO2.setMsg_code("30002");// 消息编码
            sysSendInfoVO2.setMap(msgMap2);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO2.setExtras(paramMap2);// 消息附加数据
            list.add(sysSendInfoVO2);

            // 设置极光推送的附加信息
            Map<String, Object> paramMap3 = new HashMap<String, Object>();
            paramMap3.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap3.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());

            if (!"".equals(userId) && userId.equals(wmsInveRedeemMap.get("bel_vice_general_manager_id").toString()))
            {
                paramMap3.put("is_approval", 1);
            }
            else
            {
                paramMap3.put("is_approval", 0);
            }
            // 推送给副总经理
            List<String> codeLis2 = new ArrayList<String>();
            Map<String, String> msgMap3 = new HashMap<String, String>();
            msgMap3.put("salesman_manager", wmsInveRedeemMap.get("salesman_name").toString() + " " + wmsInveRedeemMap.get("salesman_ishortCode").toString());
            msgMap3.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap3.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            msgMap3.put("depart_manager", wmsInveRedeemMap.get("department_manager_name").toString() + " " + wmsInveRedeemMap.get("department_manager_code").toString());
            codeLis2.add(wmsInveRedeemMap.get("vice_general_manager_code").toString());
            SysSendInfoVO sysSendInfoVO3 = new SysSendInfoVO();
            sysSendInfoVO3.setUser_code(codeLis2);// 人员短工号
            sysSendInfoVO3.setMsg_code("30003");// 消息编码
            sysSendInfoVO3.setMap(msgMap3);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO3.setExtras(paramMap3);// 消息附加数据
            list.add(sysSendInfoVO3);
        }
        else if ("1".equals(data_status))
        {
            // 设置极光推送的附加信息
            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            paramMap1.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap1.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            paramMap1.put("is_approval", 0);
            // 推送给客户经理
            List<String> codeLis = new ArrayList<String>();
            Map<String, String> msgMap1 = new HashMap<String, String>();
            msgMap1.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap1.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            msgMap1.put("vice_general_manager", wmsInveRedeemMap.get("vice_general_manager_name").toString() + " " + wmsInveRedeemMap.get("vice_general_manager_code").toString());
            codeLis.add(wmsInveRedeemMap.get("salesman_ishortCode").toString());
            SysSendInfoVO sysSendInfoVO1 = new SysSendInfoVO();
            sysSendInfoVO1.setUser_code(codeLis);// 人员短工号
            sysSendInfoVO1.setMsg_code("30004");// 消息编码
            sysSendInfoVO1.setMap(msgMap1);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO1.setExtras(paramMap1);// 消息附加数据
            list.add(sysSendInfoVO1);

            // 设置极光推送的附加信息
            Map<String, Object> paramMap2 = new HashMap<String, Object>();
            paramMap2.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap2.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            if (!"".equals(userId) && userId.equals(wmsInveRedeemMap.get("bel_department_manager_id").toString()))
            {
                paramMap2.put("is_approval", 1);
            }
            else
            {
                paramMap2.put("is_approval", 0);
            }

            // 推送给部门经理
            List<String> codeLis1 = new ArrayList<String>();
            Map<String, String> msgMap2 = new HashMap<String, String>();
            msgMap2.put("salesman_manager", wmsInveRedeemMap.get("salesman_name").toString() + " " + wmsInveRedeemMap.get("salesman_ishortCode").toString());
            msgMap2.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap2.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            msgMap2.put("vice_general_manager", wmsInveRedeemMap.get("vice_general_manager_name").toString() + " " + wmsInveRedeemMap.get("vice_general_manager_code").toString());
            codeLis1.add(wmsInveRedeemMap.get("department_manager_code").toString());
            SysSendInfoVO sysSendInfoVO2 = new SysSendInfoVO();
            sysSendInfoVO2.setUser_code(codeLis1);// 人员短工号
            sysSendInfoVO2.setMsg_code("30005");// 消息编码
            sysSendInfoVO2.setMap(msgMap2);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO2.setExtras(paramMap2);// 消息附加数据
            list.add(sysSendInfoVO2);

            // 设置极光推送的附加信息
            Map<String, Object> paramMap3 = new HashMap<String, Object>();
            paramMap3.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap3.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            if (!"".equals(userId) && userId.equals(wmsInveRedeemMap.get("bel_vice_general_manager_id").toString()))
            {
                paramMap3.put("is_approval", 1);
            }
            else
            {
                paramMap3.put("is_approval", 0);
            }
            // 推送给副总经理
            List<String> codeLis2 = new ArrayList<String>();
            Map<String, String> msgMap3 = new HashMap<String, String>();
            msgMap3.put("salesman_manager", wmsInveRedeemMap.get("salesman_name").toString() + " " + wmsInveRedeemMap.get("salesman_ishortCode").toString());
            msgMap3.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap3.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            codeLis2.add(wmsInveRedeemMap.get("vice_general_manager_code").toString());
            SysSendInfoVO sysSendInfoVO3 = new SysSendInfoVO();
            sysSendInfoVO3.setUser_code(codeLis2);// 人员短工号
            sysSendInfoVO3.setMsg_code("30006");// 消息编码
            sysSendInfoVO3.setMap(msgMap3);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO3.setExtras(paramMap3);// 消息附加数据
            list.add(sysSendInfoVO3);
        }
        else if ("2".equals(data_status))
        {
            // 设置极光推送的附加信息
            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            paramMap1.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap1.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            paramMap1.put("is_approval", 0);
            // 推送给客户经理
            List<String> codeLis = new ArrayList<String>();
            Map<String, String> msgMap1 = new HashMap<String, String>();
            msgMap1.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap1.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            msgMap1.put("general_manager", wmsInveRedeemMap.get("general_manager_name").toString() + " " + wmsInveRedeemMap.get("general_manager_code").toString());
            codeLis.add(wmsInveRedeemMap.get("salesman_ishortCode").toString());
            SysSendInfoVO sysSendInfoVO1 = new SysSendInfoVO();
            sysSendInfoVO1.setUser_code(codeLis);// 人员短工号
            sysSendInfoVO1.setMsg_code("30007");// 消息编码
            sysSendInfoVO1.setMap(msgMap1);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO1.setExtras(paramMap1);// 消息附加数据
            list.add(sysSendInfoVO1);

            // 设置极光推送的附加信息
            Map<String, Object> paramMap2 = new HashMap<String, Object>();
            paramMap2.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap2.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            if (!"".equals(userId) && userId.equals(wmsInveRedeemMap.get("bel_department_manager_id").toString()))
            {
                paramMap2.put("is_approval", 1);
            }
            else
            {
                paramMap2.put("is_approval", 0);
            }
            // 推送给部门经理
            List<String> codeLis1 = new ArrayList<String>();
            Map<String, String> msgMap2 = new HashMap<String, String>();
            msgMap2.put("salesman_manager", wmsInveRedeemMap.get("salesman_name").toString() + " " + wmsInveRedeemMap.get("salesman_ishortCode").toString());
            msgMap2.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap2.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            msgMap2.put("general_manager", wmsInveRedeemMap.get("general_manager_name").toString() + " " + wmsInveRedeemMap.get("general_manager_code").toString());
            codeLis1.add(wmsInveRedeemMap.get("department_manager_code").toString());
            SysSendInfoVO sysSendInfoVO2 = new SysSendInfoVO();
            sysSendInfoVO2.setUser_code(codeLis1);// 人员短工号
            sysSendInfoVO2.setMsg_code("30008");// 消息编码
            sysSendInfoVO2.setMap(msgMap2);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO2.setExtras(paramMap2);// 消息附加数据
            list.add(sysSendInfoVO2);

            // 设置极光推送的附加信息
            Map<String, Object> paramMap3 = new HashMap<String, Object>();
            paramMap3.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap3.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            if (!"".equals(userId) && userId.equals(wmsInveRedeemMap.get("bel_vice_general_manager_id").toString()))
            {
                paramMap3.put("is_approval", 1);
            }
            else
            {
                paramMap3.put("is_approval", 0);
            }
            // 推送给副总经理
            List<String> codeLis2 = new ArrayList<String>();
            Map<String, String> msgMap3 = new HashMap<String, String>();
            msgMap3.put("salesman_manager", wmsInveRedeemMap.get("salesman_name").toString() + " " + wmsInveRedeemMap.get("salesman_ishortCode").toString());
            msgMap3.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap3.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            msgMap3.put("general_manager", wmsInveRedeemMap.get("general_manager_name").toString() + " " + wmsInveRedeemMap.get("general_manager_code").toString());
            codeLis2.add(wmsInveRedeemMap.get("vice_general_manager_code").toString());
            SysSendInfoVO sysSendInfoVO3 = new SysSendInfoVO();
            sysSendInfoVO3.setUser_code(codeLis2);// 人员短工号
            sysSendInfoVO3.setMsg_code("30009");// 消息编码
            sysSendInfoVO3.setMap(msgMap3);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO3.setExtras(paramMap3);// 消息附加数据
            list.add(sysSendInfoVO3);

            // 设置极光推送的附加信息
            Map<String, Object> paramMap4 = new HashMap<String, Object>();
            paramMap4.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap4.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            if (!"".equals(userId) && userId.equals(wmsInveRedeemMap.get("bel_general_manager_id").toString()))
            {
                paramMap4.put("is_approval", 1);
            }
            else
            {
                paramMap4.put("is_approval", 0);
            }
            // 推送给总经理
            List<String> codeLis4 = new ArrayList<String>();
            Map<String, String> msgMap4 = new HashMap<String, String>();
            msgMap4.put("salesman_manager", wmsInveRedeemMap.get("salesman_name").toString() + " " + wmsInveRedeemMap.get("salesman_ishortCode").toString());
            msgMap4.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap4.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            codeLis4.add(wmsInveRedeemMap.get("general_manager_code").toString());
            SysSendInfoVO sysSendInfoVO4 = new SysSendInfoVO();
            sysSendInfoVO4.setUser_code(codeLis4);// 人员短工号
            sysSendInfoVO4.setMsg_code("30010");// 消息编码
            sysSendInfoVO4.setMap(msgMap4);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO4.setExtras(paramMap4);// 消息附加数据
            list.add(sysSendInfoVO4);
        }
        else if ("3".equals(data_status))
        {
            // 设置极光推送的附加信息
            Map<String, Object> paramMap4 = new HashMap<String, Object>();
            paramMap4.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap4.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            paramMap4.put("is_approval", 0);

            // 推送给客户经理
            List<String> codeLis = new ArrayList<String>();
            Map<String, String> msgMap1 = new HashMap<String, String>();
            msgMap1.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap1.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            codeLis.add(wmsInveRedeemMap.get("salesman_ishortCode").toString());
            SysSendInfoVO sysSendInfoVO1 = new SysSendInfoVO();
            sysSendInfoVO1.setUser_code(codeLis);// 人员短工号
            sysSendInfoVO1.setMsg_code("30011");// 消息编码
            sysSendInfoVO1.setMap(msgMap1);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO1.setExtras(paramMap4);// 消息附加数据
            list.add(sysSendInfoVO1);

            // 推送给部门经理
            List<String> codeLis1 = new ArrayList<String>();
            Map<String, String> msgMap2 = new HashMap<String, String>();
            msgMap2.put("salesman_manager", wmsInveRedeemMap.get("salesman_name").toString() + " " + wmsInveRedeemMap.get("salesman_ishortCode").toString());
            msgMap2.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap2.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            codeLis1.add(wmsInveRedeemMap.get("department_manager_code").toString());
            SysSendInfoVO sysSendInfoVO2 = new SysSendInfoVO();
            sysSendInfoVO2.setUser_code(codeLis1);// 人员短工号
            sysSendInfoVO2.setMsg_code("30012");// 消息编码
            sysSendInfoVO2.setMap(msgMap2);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO2.setExtras(paramMap4);// 消息附加数据
            list.add(sysSendInfoVO2);

            // 推送给副总经理
            List<String> codeLis2 = new ArrayList<String>();
            Map<String, String> msgMap3 = new HashMap<String, String>();
            msgMap3.put("salesman_manager", wmsInveRedeemMap.get("salesman_name").toString() + " " + wmsInveRedeemMap.get("salesman_ishortCode").toString());
            msgMap3.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap3.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            codeLis2.add(wmsInveRedeemMap.get("vice_general_manager_code").toString());
            SysSendInfoVO sysSendInfoVO3 = new SysSendInfoVO();
            sysSendInfoVO3.setUser_code(codeLis2);// 人员短工号
            sysSendInfoVO3.setMsg_code("30013");// 消息编码
            sysSendInfoVO3.setMap(msgMap3);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO3.setExtras(paramMap4);// 消息附加数据
            list.add(sysSendInfoVO3);
        }
        else if ("03".equals(data_status))
        {
            // 设置极光推送的附加信息
            Map<String, Object> paramMap4 = new HashMap<String, Object>();
            paramMap4.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap4.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            paramMap4.put("is_approval", 0);

            // 推送给客户经理
            List<String> codeLis = new ArrayList<String>();
            Map<String, String> msgMap1 = new HashMap<String, String>();
            msgMap1.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap1.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            msgMap1.put("general_manager", wmsInveRedeemMap.get("general_manager_name").toString() + " " + wmsInveRedeemMap.get("general_manager_code").toString());
            codeLis.add(wmsInveRedeemMap.get("salesman_ishortCode").toString());
            SysSendInfoVO sysSendInfoVO1 = new SysSendInfoVO();
            sysSendInfoVO1.setUser_code(codeLis);// 人员短工号
            sysSendInfoVO1.setMsg_code("30015");// 消息编码
            sysSendInfoVO1.setMap(msgMap1);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO1.setExtras(paramMap4);// 消息附加数据
            list.add(sysSendInfoVO1);

            // 设置极光推送的附加信息
            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            paramMap1.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap1.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            if (!"".equals(userId) && userId.equals(wmsInveRedeemMap.get("bel_general_manager_id").toString()))
            {
                paramMap1.put("is_approval", 1);
            }
            else
            {
                paramMap1.put("is_approval", 0);
            }
            // 推送给总经理
            List<String> codeLis1 = new ArrayList<String>();
            Map<String, String> msgMap2 = new HashMap<String, String>();
            msgMap2.put("salesman_manager", wmsInveRedeemMap.get("salesman_name").toString() + " " + wmsInveRedeemMap.get("salesman_ishortCode").toString());
            msgMap2.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap2.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            codeLis1.add(wmsInveRedeemMap.get("general_manager_code").toString());
            SysSendInfoVO sysSendInfoVO2 = new SysSendInfoVO();
            sysSendInfoVO2.setUser_code(codeLis1);// 人员短工号
            sysSendInfoVO2.setMsg_code("30016");// 消息编码
            sysSendInfoVO2.setMap(msgMap2);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO2.setExtras(paramMap1);// 消息附加数据
            list.add(sysSendInfoVO2);
        }
        else if ("6".equals(data_status))
        {
            // 设置极光推送的附加信息
            Map<String, Object> paramMap4 = new HashMap<String, Object>();
            paramMap4.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap4.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            paramMap4.put("is_approval", 0);

            // 推送给客户经理
            List<String> codeLis = new ArrayList<String>();
            Map<String, String> msgMap1 = new HashMap<String, String>();
            msgMap1.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap1.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            codeLis.add(wmsInveRedeemMap.get("salesman_ishortCode").toString());
            SysSendInfoVO sysSendInfoVO1 = new SysSendInfoVO();
            sysSendInfoVO1.setUser_code(codeLis);// 人员短工号
            sysSendInfoVO1.setMsg_code("30017");// 消息编码
            sysSendInfoVO1.setMap(msgMap1);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO1.setExtras(paramMap4);// 消息附加数据
            list.add(sysSendInfoVO1);
        }
        else if ("5".equals(data_status))
        {
            // 设置极光推送的附加信息
            Map<String, Object> paramMap4 = new HashMap<String, Object>();
            paramMap4.put("wms_inve_redeem_id", wmsInveRedeemMap.get("wms_inve_redeem_id").toString());
            paramMap4.put("bill_title", wmsInveRedeemMap.get("bill_title").toString());
            paramMap4.put("is_approval", 0);

            // 推送给客户经理
            List<String> codeLis = new ArrayList<String>();
            Map<String, String> msgMap1 = new HashMap<String, String>();
            msgMap1.put("cus_name", wmsInveRedeemMap.get("cus_name").toString());
            msgMap1.put("redeem_amount", wmsInveRedeemMap.get("redeem_amount").toString());
            codeLis.add(wmsInveRedeemMap.get("salesman_ishortCode").toString());
            SysSendInfoVO sysSendInfoVO1 = new SysSendInfoVO();
            sysSendInfoVO1.setUser_code(codeLis);// 人员短工号
            sysSendInfoVO1.setMsg_code("30014");// 消息编码
            sysSendInfoVO1.setMap(msgMap1);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO1.setExtras(paramMap4);// 消息附加数据
            list.add(sysSendInfoVO1);
        }

        asynSendJGInfo(list);
    }

    /**
     * 
     * @Title: asynSendJGInfo
     * @Description: 异步推送极光信息
     * @param list 
     * @author: DongHao
     * @time:2017年4月2日 下午3:48:31
     * history:
     * 1、2017年4月2日 DongHao 创建方法
     */
    public void asynSendJGInfo(final List<SysSendInfoVO> list)
    {
        try
        {
            Thread thread = new Thread(new Runnable()
            {

                @Override
                public void run()
                {
                    // 进行推送消息
                    for (int i = 0; i < list.size(); i++)
                    {
                        SysSendInfoVO sysSendInfoVO = list.get(i);
                        sysSendInfoVO.setApp_name("MOA");
                        sendJGInfo(sysSendInfoVO);
                    }
                }
            });
            thread.start();
        }
        catch (Exception e)
        {
            log.error("极光推送信息报错:" + e.getMessage());
        }
    }


    /**
     * 
     * @Title: sendJGInfo
     * @Description: 极光推送
     * @param sysSendInfoVO
     * @return 
     * @author: DongHao
     * @time:2016年12月9日 上午9:31:32
     * history:
     * 1、2016年12月9日 DongHao 创建方法
     */
    public String sendJGInfo(SysSendInfoVO sysSendInfoVO)
    {
        // 极光推送需要调用moa项目里面的方法
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        RestTemplate restTemplate = new RestTemplate();
        String url = PlatformGlobalVar.SYS_PROPERTIES.get("moaUrl") + "/wms/pushManageForWMS.do";
        log.info("极光服务器地址" + url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("sysSendInfoVO", new Gson().toJson(sysSendInfoVO)));
        Map<String, Object> resMap = new HashMap<String, Object>();
        try
        {
            mapInfo = HttpClientUtil.post(url, nvps, Map.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            resMap.put("message", e.getMessage());
        }
        if (mapInfo != null)
        {
            if ("000".equals(mapInfo.get("ret_code")))
            {
                return "success";
            }
        }
        return "error";
    }


    /**
     * 
     * @Title: startSalarSetProcess
     * @Description: 启动工资设定流程
     * @param userId 当前登录人id
     * @param businessId 单据id
     * @param create_user_id 创建人id
     * @param processDefinitionKey 流程名称
     * @return 
     * @author: DongHao
     * @time:2017年1月8日 下午3:53:38
     * history:
     * 1、2017年1月8日 DongHao 创建方法
     */
    public String startSalarSetProcess(String userId, String businessId, String create_user_id)
    {
        String resultStr = "success";

        Map<String, Object> jobTimeMap = wmsInveSalaryPreTotalDao.getWmsInveJobTimeToEndOfDate();

        String end_of_date = jobTimeMap.get("job_date_str").toString() + "T00:00:00";// 组合结束时间格式

        Map<String, Object> paramsMap = new HashMap<String, Object>();// 定义需要修改的参数集合

        Map<String, Object> fromMap = new HashMap<String, Object>();// 定义启动流程时需要的参数

        // 根据人员id获取对应的人员信息
        Map<String, Object> personnelMap = wmsInveSalaryPreTotalDao.getPersonnelByPersonnelId(create_user_id);

        // 判断获取的人员信息是否为空
        if (personnelMap != null && personnelMap.size() > 0)
        {
            /*
             * 判断是否是中分中、或者是副总提交的工资设定单
             * 1. 部门经理提交的工资设定单需要通过副总和总审批
             * 2. 中分总和副总提交的工资设定单需要通过总审批
             */
            if (personnelMap.get("dept_level") != null && Integer.parseInt(personnelMap.get("dept_level").toString()) == 6)
            {
                // 需要通过副总和分总审批
                // 设置需要修改的工资设定单据参数
                paramsMap.put("data_status", "2");
                paramsMap.put("wms_inve_salary_pre_total_id", businessId);
                paramsMap.put("last_update_user_id", userId);
                paramsMap.put("last_update_timestamp", new Timestamp(System.currentTimeMillis()));

                String vice_manager_id = "";

                if (personnelMap.get("center_manager_id") != null && !"".equals(personnelMap.get("center_manager_id").toString()))
                {
                    vice_manager_id = personnelMap.get("center_manager_id").toString();
                }
                else
                {
                    vice_manager_id = personnelMap.get("vice_manager_id").toString();
                }
                // 设置执行流程需要的参数
                fromMap.put("vice_general_id", vice_manager_id);
                fromMap.put("userId", vice_manager_id);
                fromMap.put("end_of_date", end_of_date);

                // 部门经理提交工资设定单发送短信
                String user_info = personnelMap.get("dept_name").toString() + "部门经理" + personnelMap.get("user_info").toString();
                sendSalaryMsg("2243", vice_manager_id, user_info);

                //部门经理提交进行极光推送信息
                sendSalaryJGHandler("01", businessId, vice_manager_id, "");
            }
            else
            {
                // 需要通过总审批
                // 设置需要修改的工资设定单据参数
                paramsMap.put("data_status", "6");
                paramsMap.put("wms_inve_salary_pre_total_id", businessId);
                paramsMap.put("last_update_user_id", userId);
                paramsMap.put("last_update_timestamp", new Timestamp(System.currentTimeMillis()));

                // 设置执行流程需要的参数
                fromMap.put("vice_general_id", null);
                fromMap.put("general_id", "184");
                fromMap.put("userId", "184");
                fromMap.put("end_of_date", end_of_date);

                // 副总提交工资设定申请发送短信
                String vice_name = "";
                if (personnelMap.get("center_manager_id") != null && !"".equals(personnelMap.get("center_manager_id").toString()))
                {
                    vice_name = "中分总经理";
                }
                else
                {
                    vice_name = "副总经理";
                }
                String user_info = personnelMap.get("dept_name").toString() + vice_name + personnelMap.get("user_info").toString();
                sendSalaryMsg("2243", "184", user_info);
                
                //部门经理提交进行极光推送信息
                sendSalaryJGHandler("02", businessId, "184", "");
            }
        }

        // 修改工资设定单据对应的状态
        wmsInveSalaryPreTotalDao.updateWmsInveSalaryPreTotal(paramsMap);

        // 启动工资设定流程
        workflowService.startWorkflow(userId, WorkflowConstantHelp.INVESALARYSET, businessId, fromMap);

        return resultStr;
    }


    /**
     * 
     * @Title: getSalarSetWorkFlowToIdList
     * @Description: 
     * @param userId 当前登录人id
     * @param invekey 1表示为待修订,2表示待副总审批, 3表示待总审批
     * @return 
     * @author: DongHao
     * @time:2017年1月8日 下午5:10:51
     * history:
     * 1、2017年1月8日 DongHao 创建方法
     */
    public Map<String, Object> getSalarSetWorkFlowToIdList(String userId, String invekey)
    {
        Map<String, Object> paramsMap = new HashMap<String, Object>();// 定义返回结果map

        // 待修订
        if ("1".equals(invekey))
        {
            List<WorkflowInfoHelp> workflowInfoHelps = setWorkFlowList(WorkflowConstantHelp.INVESALARYSET, userId, WorkflowConstantHelp.INVESALARYSET_CXXD);

            if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
            {
                Map<String, Object> pMap = new HashMap<String, Object>();
                return pMap;
            }

            paramsMap = setWorkFlowMap(workflowInfoHelps, WorkflowConstantHelp.INVESALARYSET);
        }

        // 待副总审批
        if ("2".equals(invekey))
        {
            List<WorkflowInfoHelp> workflowInfoHelps = setWorkFlowList(WorkflowConstantHelp.INVESALARYSET, userId, WorkflowConstantHelp.INVESALARYSET_FZSP);

            if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
            {
                Map<String, Object> pMap = new HashMap<String, Object>();
                return pMap;
            }

            paramsMap = setWorkFlowMap(workflowInfoHelps, WorkflowConstantHelp.INVESALARYSET);
        }

        // 待总经理审批
        if ("3".equals(invekey))
        {
            List<WorkflowInfoHelp> workflowInfoHelps = setWorkFlowList(WorkflowConstantHelp.INVESALARYSET, userId, WorkflowConstantHelp.INVESALARYSET_ZJLSP);

            if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
            {
                Map<String, Object> pMap = new HashMap<String, Object>();
                return pMap;
            }

            paramsMap = setWorkFlowMap(workflowInfoHelps, WorkflowConstantHelp.INVESALARYSET);
        }

        return paramsMap;
    }

    /**
     * 
     * @Title: inveSalarySetAudit
     * @Description: 执行审批操作
     * @param vo 
     * @author: DongHao
     * @time:2017年1月27日 上午11:05:03
     * history:
     * 1、2017年1月27日 DongHao 创建方法
     */
    public void inveSalarySetAudit(WmsInveSalarySetWorkFlowVO vo)
    {

        Map<String, Object> jobTimeMap = wmsInveSalaryPreTotalDao.getWmsInveJobTimeToEndOfDate();

        String end_of_date = jobTimeMap.get("job_date_str").toString() + "T00:00:00";// 组合结束时间格式

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        List<Map<String, Object>> totals = new ArrayList<Map<String, Object>>();

        // 副总审批
        if ("1".equals(vo.getDebtkey()))
        {
            List<Map<String, Object>> params = getParamMap(vo, WorkflowConstantHelp.INVESALARYSET_FZSP);
            if ("true".equals(vo.getPass()))
            {
                for (Map<String, Object> map : params)
                {

                    Map<String, String> fromMap = new HashMap<String, String>();
                    fromMap.put("taskId", map.get("taskId").toString());
                    fromMap.put("bussinessId", map.get("bussinessId").toString());
                    fromMap.put("pass", "true");
                    fromMap.put("general_id", "184");
                    fromMap.put("userId", "184");
                    fromMap.put("end_of_date", end_of_date);
                    fromMap.put("advice", vo.getAdvice());
                    list.add(fromMap);

                    Map<String, Object> wmsInveSalaryPreTotalMap = new HashMap<String, Object>();
                    wmsInveSalaryPreTotalMap.put("data_status", 6);
                    wmsInveSalaryPreTotalMap.put("wms_inve_salary_pre_total_id", Integer.parseInt(map.get("bussinessId").toString()));
                    wmsInveSalaryPreTotalMap.put("vice_general_id", Integer.parseInt(vo.getUserID()));
                    wmsInveSalaryPreTotalMap.put("vice_general_datetime", new Timestamp(System.currentTimeMillis()));
                    wmsInveSalaryPreTotalMap.put("vice_general_advice", vo.getAdvice());
                    wmsInveSalaryPreTotalMap.put("last_update_user_id", vo.getUserID());
                    wmsInveSalaryPreTotalMap.put("last_update_timestamp", new Timestamp(System.currentTimeMillis()));
                    totals.add(wmsInveSalaryPreTotalMap);

                    // 副总审核通过给总发送工资设定审批短信
                    Map<String, Object> personnelMap = wmsInveSalaryPreTotalDao.getPersonnelByPersonnelId(vo.getUserID());
                    String vice_name = "";
                    if (personnelMap.get("center_manager_id") != null && !"".equals(personnelMap.get("center_manager_id").toString()))
                    {
                        vice_name = "中分总经理";
                    }
                    else
                    {
                        vice_name = "副总经理";
                    }
                    String user_info = personnelMap.get("dept_name").toString() + vice_name + personnelMap.get("user_info").toString();
                    sendSalaryMsg("2243", "184", user_info);
                    
                    //部门经理提交进行极光推送信息
                    sendSalaryJGHandler("02", map.get("bussinessId").toString(), "184", "");
                }
                
                
            }
            else
            {
                
                for (Map<String, Object> map : params)
                {
                    WmsInveSalaryPreTotal totalBean = wmsInveSalaryPreTotalDao.get(Integer.parseInt(map.get("bussinessId").toString()));

                    Map<String, String> fromMap = new HashMap<String, String>();
                    fromMap.put("taskId", map.get("taskId").toString());
                    fromMap.put("bussinessId", map.get("bussinessId").toString());
                    fromMap.put("pass", "false");
                    fromMap.put("startUser", totalBean.getCreate_user_id().toString());
                    fromMap.put("end_of_date", end_of_date);
                    fromMap.put("advice", vo.getAdvice());
                    list.add(fromMap);

                    Map<String, Object> wmsInveSalaryPreTotalMap = new HashMap<String, Object>();
                    wmsInveSalaryPreTotalMap.put("data_status", 5);
                    wmsInveSalaryPreTotalMap.put("wms_inve_salary_pre_total_id", Integer.parseInt(map.get("bussinessId").toString()));
                    wmsInveSalaryPreTotalMap.put("vice_general_id", Integer.parseInt(vo.getUserID().toString()));
                    wmsInveSalaryPreTotalMap.put("vice_general_datetime", new Timestamp(System.currentTimeMillis()));
                    wmsInveSalaryPreTotalMap.put("vice_general_advice", vo.getAdvice());
                    wmsInveSalaryPreTotalMap.put("last_update_user_id", vo.getUserID());
                    wmsInveSalaryPreTotalMap.put("last_update_timestamp", new Timestamp(System.currentTimeMillis()));
                    totals.add(wmsInveSalaryPreTotalMap);

                    // 副总审核不通过给提交工资设定单的人发送短信
                    String user_info = vo.getAdvice();
                    sendSalaryMsg("2244", totalBean.getCreate_user_id().toString(), user_info);
                
                    //部门经理提交进行极光推送信息
                    sendSalaryJGHandler("03", map.get("bussinessId").toString(), totalBean.getCreate_user_id().toString(), user_info);
                }
                
                
            }

            if (totals != null && totals.size() > 0)
            {
                // 批量更新审核的单据信息
                wmsInveSalaryPreTotalDao.updateBatchWmsInveSalaryPreTotalInfos(totals);
            }

        }

        // 总审批
        if ("2".equals(vo.getDebtkey()))
        {
            List<Map<String, Object>> params = getParamMap(vo, WorkflowConstantHelp.INVESALARYSET_ZJLSP);

            if ("true".equals(vo.getPass()))
            {
                
                for (Map<String, Object> map : params)
                {
                    Map<String, String> fromMap = new HashMap<String, String>();
                    fromMap.put("taskId", map.get("taskId").toString());
                    fromMap.put("bussinessId", map.get("bussinessId").toString());
                    fromMap.put("pass", "true");
                    fromMap.put("advice", vo.getAdvice());
                    list.add(fromMap);

                    Map<String, Object> wmsInveSalaryPreTotalMap = new HashMap<String, Object>();
                    wmsInveSalaryPreTotalMap.put("data_status", 3);
                    wmsInveSalaryPreTotalMap.put("wms_inve_salary_pre_total_id", Integer.parseInt(map.get("bussinessId").toString()));
                    wmsInveSalaryPreTotalMap.put("general_id", Integer.parseInt(vo.getUserID().toString()));
                    wmsInveSalaryPreTotalMap.put("general_datetime", new Timestamp(System.currentTimeMillis()));
                    wmsInveSalaryPreTotalMap.put("general_advice", vo.getAdvice());
                    wmsInveSalaryPreTotalMap.put("last_update_user_id", vo.getUserID());
                    wmsInveSalaryPreTotalMap.put("last_update_timestamp", new Timestamp(System.currentTimeMillis()));
                    totals.add(wmsInveSalaryPreTotalMap);
                }
            }
            else
            {
                
                for (Map<String, Object> map : params)
                {
                    WmsInveSalaryPreTotal totalBean = wmsInveSalaryPreTotalDao.get(Integer.parseInt(map.get("bussinessId").toString()));

                    Map<String, String> fromMap = new HashMap<String, String>();
                    fromMap.put("taskId", map.get("taskId").toString());
                    fromMap.put("bussinessId", map.get("bussinessId").toString());
                    fromMap.put("pass", "false");
                    fromMap.put("startUser", totalBean.getCreate_user_id().toString());
                    fromMap.put("end_of_date", end_of_date);
                    fromMap.put("advice", vo.getAdvice());
                    list.add(fromMap);

                    Map<String, Object> wmsInveSalaryPreTotalMap = new HashMap<String, Object>();
                    wmsInveSalaryPreTotalMap.put("data_status", 5);
                    wmsInveSalaryPreTotalMap.put("wms_inve_salary_pre_total_id", Integer.parseInt(map.get("bussinessId").toString()));
                    wmsInveSalaryPreTotalMap.put("general_id", Integer.parseInt(vo.getUserID().toString()));
                    wmsInveSalaryPreTotalMap.put("general_datetime", new Timestamp(System.currentTimeMillis()));
                    wmsInveSalaryPreTotalMap.put("general_advice", vo.getAdvice());
                    wmsInveSalaryPreTotalMap.put("last_update_user_id", vo.getUserID());
                    wmsInveSalaryPreTotalMap.put("last_update_timestamp", new Timestamp(System.currentTimeMillis()));
                    totals.add(wmsInveSalaryPreTotalMap);

                    // 副总审核不通过给提交工资设定单的人发送短信
                    String user_info = vo.getAdvice();
                    sendSalaryMsg("2244", totalBean.getCreate_user_id().toString(), user_info);
                    
                    //部门经理提交进行极光推送信息
                    sendSalaryJGHandler("04", map.get("bussinessId").toString(), totalBean.getCreate_user_id().toString(), user_info);
                }
                
            }

            if (totals != null && totals.size() > 0)
            {
                // 批量更新审核的单据信息
                wmsInveSalaryPreTotalDao.updateBatchWmsInveSalaryPreTotalInfos(totals);
            }
        }
        
        // 重新提交
        if ("3".equals(vo.getDebtkey()))
        {
            List<Map<String, Object>> params = getParamMap(vo, WorkflowConstantHelp.INVESALARYSET_CXXD);
            
            // 根据人员id获取对应的人员信息
            Map<String, Object> personnelMap = wmsInveSalaryPreTotalDao.getPersonnelByPersonnelId(vo.getUserID());

            // 判断获取的人员信息是否为空
            if (personnelMap != null && personnelMap.size() > 0)
            {
                if ("true".equals(vo.getPass()))
                {
                    /*
                     * 判断是否是中分中、或者是副总提交的工资设定单
                     * 1. 部门经理提交的工资设定单需要通过副总和总审批
                     * 2. 中分总和副总提交的工资设定单需要通过总审批
                     */
                    if (personnelMap.get("dept_level") != null && Integer.parseInt(personnelMap.get("dept_level").toString()) == 6)
                    {
                        for (Map<String, Object> map : params)
                        {
                            Map<String, String> fromMap = new HashMap<String, String>();
                            fromMap.put("taskId", map.get("taskId").toString());
                            fromMap.put("bussinessId", map.get("bussinessId").toString());
                            fromMap.put("pass", "true");
                            fromMap.put("advice", vo.getAdvice());
                            
                            String vice_manager_id = "";

                            if (personnelMap.get("center_manager_id") != null && !"".equals(personnelMap.get("center_manager_id").toString()))
                            {
                                vice_manager_id = personnelMap.get("center_manager_id").toString();
                            }
                            else
                            {
                                vice_manager_id = personnelMap.get("vice_manager_id").toString();
                            }
                            // 设置执行流程需要的参数
                            fromMap.put("vice_general_id", vice_manager_id);
                            fromMap.put("userId", vice_manager_id);
                            fromMap.put("end_of_date", end_of_date);
                            
                            list.add(fromMap);

                            Map<String, Object> wmsInveSalaryPreTotalMap = new HashMap<String, Object>();
                            wmsInveSalaryPreTotalMap.put("data_status", "2");
                            wmsInveSalaryPreTotalMap.put("wms_inve_salary_pre_total_id", Integer.parseInt(map.get("bussinessId").toString()));
                            wmsInveSalaryPreTotalMap.put("last_update_user_id", vo.getUserID());
                            wmsInveSalaryPreTotalMap.put("last_update_timestamp", new Timestamp(System.currentTimeMillis()));
                            
                            totals.add(wmsInveSalaryPreTotalMap);
                            
                            //部门经理提交进行极光推送信息
                            sendSalaryJGHandler("01", map.get("bussinessId").toString(), vice_manager_id, "");
                        }
                    }
                    else
                    {
                        for (Map<String, Object> map : params)
                        {
                            Map<String, String> fromMap = new HashMap<String, String>();
                            fromMap.put("taskId", map.get("taskId").toString());
                            fromMap.put("bussinessId", map.get("bussinessId").toString());
                            fromMap.put("pass", "true");
                            fromMap.put("advice", vo.getAdvice());
                            // 设置执行流程需要的参数
                            fromMap.put("vice_general_id", null);
                            fromMap.put("general_id", "184");
                            fromMap.put("userId", "184");
                            fromMap.put("end_of_date", end_of_date);
                            list.add(fromMap);

                            Map<String, Object> wmsInveSalaryPreTotalMap = new HashMap<String, Object>();
                            wmsInveSalaryPreTotalMap.put("data_status", "6");
                            wmsInveSalaryPreTotalMap.put("wms_inve_salary_pre_total_id", Integer.parseInt(map.get("bussinessId").toString()));
                            wmsInveSalaryPreTotalMap.put("last_update_user_id", vo.getUserID());
                            wmsInveSalaryPreTotalMap.put("last_update_timestamp", new Timestamp(System.currentTimeMillis()));
                            
                            totals.add(wmsInveSalaryPreTotalMap);
                            
                            //部门经理提交进行极光推送信息
                            sendSalaryJGHandler("02", map.get("bussinessId").toString(), "184", "");
                        }
                    }
                }
            }

            if (totals != null && totals.size() > 0)
            {
                // 批量更新审核的单据信息
                wmsInveSalaryPreTotalDao.updateBatchWmsInveSalaryPreTotalInfos(totals);
            }
        }

        // 批量提交审核
        for (Map<String, String> map : list)
        {
            workflowService.completeTask(vo.getUserID(), map.get("taskId").toString(), map);
        }
    }

    /**
     * @Title: getParamMap
     * @Description: 根据对象参数中的值进行获取任务taskId和业务单据id
     * @param vo
     * @return 
     * @author: DongHao
     * @time:2017年1月27日 上午11:30:30
     * history:
     * 1、2017年1月27日 DongHao 创建方法
    */
    private List<Map<String, Object>> getParamMap(WmsInveSalarySetWorkFlowVO vo, String taskName)
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (vo.getTaskId() != null && !"".equals(vo.getTaskId()))
        {
            String[] taskIds = vo.getTaskId().split(",");
            String[] bussinessIds = vo.getBusinessId().split(",");
            for (int i = 0; i < taskIds.length; i++)
            {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("taskId", taskIds[i]);
                map.put("bussinessId", bussinessIds[i]);
                list.add(map);
            }
        }
        else
        {
            if (vo.getBusinessId() != null && !"".equals(vo.getBusinessId()))
            {
                String[] bussinessIds = vo.getBusinessId().split(",");
                for (String bussinessId : bussinessIds)
                {
                    Map<String, Object> map = new HashMap<String, Object>();
                    Task task = workflowService.getTaskInfobyKey(bussinessId, taskName, WorkflowConstantHelp.INVESALARYSET);
                    map.put("taskId", task.getId());
                    map.put("bussinessId", bussinessId);
                    list.add(map);
                }
            }
        }
        return list;
    }

    /**
     * 
     * @Title: sendMsg
     * @Description: 工资设定发送短信
     * @param tpl_id
     * @param userId 
     * @author: DongHao
     * @time:2017年1月12日 上午9:36:55
     * history:
     * 1、2017年1月12日 DongHao 创建方法
     */
    public void sendSalaryMsg(String tpl_id, String userId, String user_info)
    {
        Map<String, String> sendMap = new HashMap<String, String>();

        try
        {
            if (userId != null && !"".equals(userId))
            {
                PmPersonnel pmpersonnel = pmpersonnelDao.get(Integer.valueOf(userId));

                if (pmpersonnel != null)
                {
                    sendMap.put("tel", pmpersonnel.getPersonnel_contacttel());// 电话
                }

                // 发送短信
                sendMap.put("tpl_id", tpl_id);

                // 参数map
                Map<String, Object> paramMap = new HashMap<String, Object>();

                paramMap.put("user_info", user_info);

                Gson gson = new Gson();

                sendMap.put("paramJson", gson.toJson(paramMap));

                SysUtil.sendMsg(sendMap);
            }
        }
        catch (Exception e)
        {
            log.error("发送短信报错内容：" + e.getMessage());
        }
    }

    /**
     * 
     * @Title: getSignedProcessTaskId
     * @Description: 根据业务单据id获取taskId
     * @param wms_inve_transa_id
     * @param taskName
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午6:42:42
     * history:
     * 1、2017年2月13日 DongHao 创建方法
     */
    public String getSignedProcessTaskId(String wms_inve_transa_id, String taskName)
    {
        Task task = workflowService.getTaskInfobyKey(wms_inve_transa_id, taskName, WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        return task == null ? "" : task.getId();
    }
    /**
     * @Title: getSalarySetProcessInfos
     * @Description:  根据流程名称和业务主键进行查询流程历程信息
     * @param invesalaryset 流程名称
     * @param businessKey 业务主键
     * @return 返回流程流程信息
     * @author: DongHao
     * @time:2017年5月1日 下午10:29:24
     * @see com.zx.emanage.inve.service.IWmsInveWorkFlowService#getSalarySetProcessInfos(java.lang.String, java.lang.Integer)
     * history:
     * 1、2017年5月1日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getSalarySetProcessInfos(String invesalaryset, Integer businessKey)
    {
        
        // 工资设定流程历程查看
        Map<String, Object> paMap = new HashMap<String, Object>();
        
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        
        // 设置流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(invesalaryset);
        
        // 查询完成
        workflowSearchInfoHelp.setFinish(true);
        
        //流程中单据主键
        workflowSearchInfoHelp.setBusinessKey(businessKey.toString());
        
        // 读取流程返回的结果
        List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(workflowSearchInfoHelp);
        
        //遍历流程结果(获取相应的职务)
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            // 根据流程里面的提供审批人id :approverids
            String approverids = workflowHistoryInfoHelp.getApproverids();
            
            boolean isNum = approverids.matches("[0-9]+");
            
            if (isNum)
            {
                PmPersonnel person = pmpersonnelDao.get(Integer.valueOf(workflowHistoryInfoHelp.getApproverids()));
                if (person != null)
                {
                    // 相应的id，获取相对应的部门名称
                    workflowHistoryInfoHelp.setPersonnel_deptName(person.getPersonnel_deptname());
                    // 相应的id，获取相对应的职务名称
                    workflowHistoryInfoHelp.setPersonnel_postName(person.getPersonnel_postname());

                    workflowHistoryInfoHelp.setPersonnel_shortCode(person.getPersonnel_shortcode());
                }
            }
        }
        
        List<WorkflowHistoryInfoHelp> works = new ArrayList<WorkflowHistoryInfoHelp>();
        
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            
            if ("true".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                if ("未审批".equals(workflowHistoryInfoHelp.getApproveAdvice()))
                {
                    // 当出现审批结果为true,但审批意见为'未审批'，说明属于特批，其他审批环节系统自动完成,不需要显示。
                    continue;
                }
                else
                {
                    // 当出现审批结果为true,审批意见为其他内容，说明属于正常审批通过。
                    workflowHistoryInfoHelp.setApproveResult("审批通过");
                }
            }
            else if ("false".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                // 当出现false的值时,说明审批结果为不通过
                workflowHistoryInfoHelp.setApproveResult("审批不通过");
            }
            else if(workflowHistoryInfoHelp.getApproveResult() == null || "".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                WmsInveSalaryPreTotal wmsInveSalaryPreTotal = wmsInveSalaryPreTotalDao.get(businessKey);
                if(wmsInveSalaryPreTotal.getData_status() != null && "4".equals(wmsInveSalaryPreTotal.getData_status()))
                {
                    workflowHistoryInfoHelp.setApproveResult("审批通过");
                    workflowHistoryInfoHelp.setApproveAdvice("已完成(系统自动)");
                }
            }
            
            // 把所有true,false,noagree,nullify,over进行中文翻译，添加到works中
            works.add(workflowHistoryInfoHelp);
        }
        paMap.put("Rows", works);
        return paMap;
    }
    
    /**
     * 
     * @Title: sendSalaryJGHandler
     * @Description: 绩效工资审核极光发送
     * @param type 类型(01表示为部门经理提交申请待副总或者中分总审核,02表示中分总或者副总提交待总审核, 03表示副总或者中分总进行驳回,04表示中进行驳回)
     * @param wms_inve_salary_pre_total_id 工资设定单总表的主键
     * @param manager_id 审批人的id
     * @param advice  审批意见
     * @author: DongHao
     * @time:2017年4月1日 上午11:52:29
     * history:
     * 1、2017年4月1日 DongHao 创建方法
     */
    public void sendSalaryJGHandler(String type, String wms_inve_salary_pre_total_id, String manager_id, String advice)
    {

        List<SysSendInfoVO> list = new ArrayList<SysSendInfoVO>();
        
        //根据人员id进行获取对应的人员信息
        PmPersonnel pmPersonnel = pmpersonnelDao.get(Integer.valueOf(manager_id));
        
        // 查询数据单据信息参数map集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("wms_inve_salary_pre_total_id", wms_inve_salary_pre_total_id);
        
        //根据查询参数进行查询绩效工资总表的对应的单据信息
        Map<String, Object> salaryMap = wmsInveSalaryPreTotalDao.getSalaryPreInfos(paramsMap);

        // 部门经理提交申请给(中分总或者副总发送极光推送消息)
        if ("01".equals(type))
        {
            // 设置极光推送的附加信息
            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            paramMap1.put("wms_inve_salary_pre_total_ids", salaryMap.get("wms_inve_salary_pre_total_id").toString());
            paramMap1.put("bill_attr", 1);

            // 推送信息(中分总或者副总)
            List<String> codeLis = new ArrayList<String>();
            codeLis.add(pmPersonnel.getPersonnel_shortcode());

            // 设置模板中对应的值
            Map<String, String> msgMap1 = new HashMap<String, String>();
            msgMap1.put("team_name", salaryMap.get("team_name").toString());

            // 封装极光推送的信息
            SysSendInfoVO sysSendInfoVO1 = new SysSendInfoVO();
            sysSendInfoVO1.setUser_code(codeLis);// 人员短工号
            sysSendInfoVO1.setMsg_code("40001");// 消息编码
            sysSendInfoVO1.setMap(msgMap1);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO1.setExtras(paramMap1);// 消息附加数据
            list.add(sysSendInfoVO1);
        }
        else if ("02".equals(type))// 副总或者中分总提交绩效工资审核单据(推送给总极光信息)
        {
            // 设置极光推送的附加信息
            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            paramMap1.put("wms_inve_salary_pre_total_ids", salaryMap.get("wms_inve_salary_pre_total_id").toString());
            paramMap1.put("bill_attr", 1);

            // 推送信息(中分总或者副总)
            List<String> codeLis = new ArrayList<String>();
            codeLis.add(pmPersonnel.getPersonnel_shortcode());

            // 设置模板中的值
            Map<String, String> msgMap1 = new HashMap<String, String>();
            msgMap1.put("team_name", salaryMap.get("team_name").toString());

            SysSendInfoVO sysSendInfoVO1 = new SysSendInfoVO();
            sysSendInfoVO1.setUser_code(codeLis);// 人员短工号
            sysSendInfoVO1.setMsg_code("40002");// 消息编码
            sysSendInfoVO1.setMap(msgMap1);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO1.setExtras(paramMap1);// 消息附加数据
            list.add(sysSendInfoVO1);
        }
        else if ("03".equals(type))// 副总驳回时推送给部门经理极光推送信息
        {
            // 设置附加信息
            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            paramMap1.put("wms_inve_salary_pre_total_ids", salaryMap.get("wms_inve_salary_pre_total_id").toString());
            paramMap1.put("bill_attr", 0);

            // 推送信息(中分总或者副总)
            // 设置人员信息
            List<String> codeLis = new ArrayList<String>();
            codeLis.add(pmPersonnel.getPersonnel_shortcode());

            // 设置模板中的值
            Map<String, String> msgMap1 = new HashMap<String, String>();
            msgMap1.put("advice", advice);

            // 封装发送的信息
            SysSendInfoVO sysSendInfoVO1 = new SysSendInfoVO();
            sysSendInfoVO1.setUser_code(codeLis);// 人员短工号
            sysSendInfoVO1.setMsg_code("40003");// 消息编码
            sysSendInfoVO1.setMap(msgMap1);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO1.setExtras(paramMap1);// 消息附加数据
            list.add(sysSendInfoVO1);
        }
        else if ("04".equals(type))// 总驳回时推送给部门经理极光推送信息
        {
            // 设置附加信息
            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            paramMap1.put("wms_inve_salary_pre_total_ids", salaryMap.get("wms_inve_salary_pre_total_id").toString());
            paramMap1.put("bill_attr", 0);

            // 设置推送的人员信息
            List<String> codeLis = new ArrayList<String>();
            codeLis.add(pmPersonnel.getPersonnel_shortcode());

            // 设置模板中的值
            Map<String, String> msgMap1 = new HashMap<String, String>();
            msgMap1.put("advice", advice);

            // 封装发送的信息
            SysSendInfoVO sysSendInfoVO1 = new SysSendInfoVO();
            sysSendInfoVO1.setUser_code(codeLis);// 人员短工号
            sysSendInfoVO1.setMsg_code("40003");// 消息编码
            sysSendInfoVO1.setMap(msgMap1);// 推送消息参数（模版生成消息时的参数）
            sysSendInfoVO1.setExtras(paramMap1);// 消息附加数据
            list.add(sysSendInfoVO1);
        }
  
        
        asynSendJGInfo(list);
    }
    
    /**
     * 
     * @Title: startPadRedeemProcess
     * @Description: pad端发起赎回申请
     * @param userId 当前登录人id
     * @param businessId 当前业务单据id
     * @param processDefinitionKey 流程名称
     * @author: DongHao
     * @time:2017年6月1日 下午3:14:30
     * @see com.zx.emanage.inve.service.IWmsInveWorkFlowService#startPadRedeemProcess(java.lang.String, java.lang.String, java.lang.String)
     * history:
     * 1、2017年6月1日 DongHao 创建方法
     */
    @Override
    public void startPadRedeemProcess(String userId, String businessId, String processDefinitionKey)
    {
        
        Map<String, Object> form = new HashMap<String, Object>();
        form.put("userId", userId);
        form.put("pass", "true");
        
        WmsInveRedeem redeem = new WmsInveRedeem();
        redeem.setWms_inve_redeem_id(Integer.parseInt(businessId));
        redeem.setData_status("5");
        
        wmsInveRedeemDao.update(redeem);
        
        workflowService.startWorkflow(userId, processDefinitionKey, businessId, form);
    }
    
    

    
}
