package com.zx.emanage.cremanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.service.IWmsCarLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;

@Service("wmsCarLoanWorkFlowService")
public class WmsCarLoanWorkFlowServiceImpl implements IWmsCarLoanWorkFlowService{
	private static Logger log = LoggerFactory.getLogger(WmsCarLoanWorkFlowServiceImpl.class);
	@Autowired
	private IWorkflowService workflowService;
	@Autowired
	private PmPersonnelDao personnelDao;
	@Autowired
	private WmsCreCreditHeadDao wmsCreCreditHeadDao;
	
	@Override
	public void startWorkFlow(String userId, String businessId) {
		Map<String,Object> parMap = new HashMap<String, Object>();
		parMap.put("inputkey","1");
		workflowService.startWorkflow(userId, WorkflowConstantHelp.CARLOANPROCESS, businessId, parMap);
		//改变单据状态:待复核
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("wms_cre_credit_head_id", businessId);
		paramMap.put("bill_status", "B1");
		wmsCreCreditHeadDao.updateRecord(paramMap);
	}

	@Override
	public Map<String, Object> getIdListWorkFlow(String userId, String carkey) {
		Map<String,Object>  paMap = new HashMap<>();
		WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
	    //车贷流程Key
	    workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CARLOANPROCESS);
	    //用户ID
	    workflowSearchInfoHelp.setUserId(userId);
	    //处理不同carKey对应流程环节数据抽取
	    if("1".equals(carkey)){
	    	//车贷复核
	    	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CARLOANPROCESS_CDFH);
	    }else if("2".equals(carkey)){
	    	//复核退回
	    	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CARLOANPROCESS_FHTH);
	    }else if("3".equals(carkey)){
	    	//评估审核
	    	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CARLOANPROCESS_PGSH);
	    }else if("4".equals(carkey)){
	    	//办件审批
	    	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CARLOANPROCESS_BJSP);
	    }else if("5".equals(carkey)){
	    	//贷前退件
	    	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CARLOANPROCESS_BJ);
	    }else if("6".equals(carkey)){
	    	//终审审批
	    	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CARLOANPROCESS_ZSSP);
	    }else if("7".equals(carkey)){
	    	//合同签订
	    	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CARLOANPROCESS_HTQD);
	    }else if("8".equals(carkey)){
	    	//放款申请
	    	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CARLOANPROCESS_FKSQ);
	    }else if("9".equals(carkey)){
	    	//放款审批
	    	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CARLOANPROCESS_FKSP);
	    }else if("10".equals(carkey)){
	    	//放款确认
	    	workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CARLOANPROCESS_FKQR);
	    }
	    //根据相应条件去流程中查询结果
	    List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
	    List<Integer> idList = new ArrayList<Integer>();
        List<String> taskIdList = new ArrayList<String>();
        List<String> approvesGroups = new ArrayList<>();
        List<String> approveAdvices = new ArrayList<>();
        List<String> approveTimes = new ArrayList<>();
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> pMap = new HashMap<String, Object>();
            return pMap;
        }
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
            taskIdList.add(workflowInfoHelp.getTaskId());
            if (carkey.equals("5"))
            {
                WorkflowSearchInfoHelp w = new WorkflowSearchInfoHelp();
                w.setProcessDefinitionKey(WorkflowConstantHelp.CARLOANPROCESS);
                w.setFinish(true);
                w.setBusinessKey(workflowInfoHelp.getBusinessKey());
                List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(w);
                for (int i = workflowHistoryInfoHelps.size() - 1; i >= 0; i--)
                {
                    if ("supply".equals(workflowHistoryInfoHelps.get(i).getApproveResult()))
                    {
                        if ("证信审批".equals(workflowHistoryInfoHelps.get(i).getTaskName()))
                        {
                            approvesGroups.add("征信审批");
                        }
                        else if ("信息审批".equals(workflowHistoryInfoHelps.get(i).getTaskName()))
                        {
                            approvesGroups.add("信息调查");
                        }
                        else
                        {
                            approvesGroups.add(workflowHistoryInfoHelps.get(i).getTaskName());
                        }
                        approveAdvices.add(workflowHistoryInfoHelps.get(i).getApproveAdvice());
                        approveTimes.add(workflowHistoryInfoHelps.get(i).getApproveTime());
                        break;
                    }
                }
            }
        }
        paMap.put("idList", idList);
        paMap.put("taskIdList", taskIdList);
        paMap.put("approvesGroups", approvesGroups);
        paMap.put("approveAdvices", approveAdvices);
        paMap.put("approveTimes", approveTimes);
        return paMap;
	}

	@Override
	public List<Map<String, Object>> setWorkFlowTaskID(
			List<Map<String, Object>> list, List<Integer> idLists,
			List<String> taskIdLists,List<String> approvesGroups,
            List<String> approveAdvices,
            List<String> approveTimes) {
	    if (idLists != null)
        {
            for (Map<String, Object> map : list)
            {
                Integer wms_cre_credit_head_id = (Integer) map.get("wms_cre_credit_head_id");
                for (int i = 0; i < idLists.size(); i++)
                {
                    if (idLists.get(i).compareTo(wms_cre_credit_head_id) == 0)
                    {
                        map.put("taskId", taskIdLists.get(i));
                        if((approvesGroups.size()==0&&approveAdvices.size()==0&&approveTimes.size()==0)
                        		||(approvesGroups.size()!=idLists.size()&&approveAdvices.size()!=idLists.size()&&approveTimes.size()!=idLists.size())){
                        	
                        }else{
                        	map.put("check_name", approvesGroups.get(i));
                        	map.put("complete_idea", approveAdvices.get(i));
                        	map.put("back_timestamp", approveTimes.get(i));
                        }
                        break;
                    }
                }
            }
        }
        return list;
	}

	@Override
	public Map<String, Object> getcarLoanWorkFlowView(String businessId) {
		Map<String, Object> pMap = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 获取流程key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CARLOANPROCESS);
        workflowSearchInfoHelp.setFinish(true);
        workflowSearchInfoHelp.setBusinessKey(businessId);
        List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(workflowSearchInfoHelp);
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            // 根据流程里面的提供审批人id :approverids
            String approverids = workflowHistoryInfoHelp.getApproverids();
            boolean isNum = approverids.matches("[0-9]+");
            if (isNum)
            {
                PmPersonnel person = personnelDao.get(Integer.valueOf(workflowHistoryInfoHelp.getApproverids()));
                if (person != null)
                {
                    // 相应的id，获取相对应的部门名称
                    workflowHistoryInfoHelp.setPersonnel_deptName(person.getPersonnel_deptname());
                    // 相应的id，获取相对应的职务名称
                    workflowHistoryInfoHelp.setPersonnel_postName(person.getPersonnel_postname());
                }
            }
        }

        List<WorkflowHistoryInfoHelp> works = new ArrayList<WorkflowHistoryInfoHelp>();
        for (WorkflowHistoryInfoHelp workflowHistoryInfoHelp : workflowHistoryInfoHelps)
        {
            if ("true".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
            		workflowHistoryInfoHelp.setApproveResult("审批通过");            		
            }
            else if ("false".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                if ("未审批".equals(workflowHistoryInfoHelp.getApproveAdvice()))
                {
                    workflowHistoryInfoHelp.setApproveResult("未审批");
                }
                else
                {
                    workflowHistoryInfoHelp.setApproveResult("审批不通过");
                }
            }
            else if ("supply".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                workflowHistoryInfoHelp.setApproveResult("退件成功");
            }
            else if (workflowHistoryInfoHelp.getApproveResult() == null)
            {
                continue;
            }
            else if("pgbj".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("评估通过(待办件)");       
            }
            else if("pgno".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("审批不通过");     
            }
            else if("pgzs".equals(workflowHistoryInfoHelp.getApproveResult())){
            	workflowHistoryInfoHelp.setApproveResult("评估通过(待终审)");     
            }
            works.add(workflowHistoryInfoHelp);
        }
        pMap.put("Rows", works);
        return pMap;
	}
    /**
     * carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 
     *         6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
     * 车贷状态:
     * A1 草稿    B1待复核   C1待评估         D1待验点  E1待终审  
     * F1待签约  G1已签约   H1待放款审批   I1待放款  
     * J1已完成  K1终止       L1补件中        M1复核退回    N1待复议修订   
     */
	@Override
	public String carLoanApprovalProcess(WmsCarLoanWorkFlowVO wVo, String carKey) 
	{
		String result="success";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("wms_cre_credit_head_id", wVo.getWms_cre_credit_head_id());
		if("1".equals(carKey))
		{//车贷复核
			//车贷复核
			carLoanWorkFlowMethod(wVo);
			//改变车贷单据状态
			if("true".equals(wVo.getPass()))
			{
				//待评估
				paramMap.put("bill_status", "C1");
			}
			else if("false".equals(wVo.getPass()))
			{
				//复核退回
				paramMap.put("bill_status", "M1");
				paramMap.put("check_back_remark", wVo.getAdvice());
			}
		}
		else if("2".equals(carKey))
		{//复核退回
			//复核退回
			wVo.setPass("true");
			wVo.setAdvice("重新申请已提交");
			carLoanWorkFlowMethod(wVo);
			//改变车贷单据状态:待复核
			paramMap.put("bill_status", "B1");
		}
		else if("3".equals(carKey)){//评估审核
			carLoanWorkFlowMethod(wVo);
			//改变单据状态
			if(wVo.getPass().equals("pgno"))
			{
				//单据终止
				paramMap.put("bill_status", "K1");
			}
			else if(wVo.getPass().equals("pgbj"))
			{
				//待办件审核
				paramMap.put("bill_status", "D1");
			}
			else if(wVo.getPass().equals("pgzs"))
			{
				//待终审审核
				paramMap.put("bill_status", "E1");
			}
		}
		else if("4".equals(carKey))
		{//车贷审核
			carLoanWorkFlowMethod(wVo);
			//改变单据状态
			if("supply".equals(wVo.getPass()))
			{
				//补件中
				paramMap.put("bill_status", "L1");
				result="supply";
			}
			else if("true".equals(wVo.getPass())||"false".equals(wVo))
			{
				//待终审
				paramMap.put("bill_status", "E1");
			}
		}
		else if("5".equals(carKey))
		{//贷前退回 
			wVo.setPass("true");
			wVo.setAdvice("补件重新提交");
			carLoanWorkFlowMethod(wVo);
			//改变单据状态
			if("true".equals(wVo.getPass()))
			{
				//待贷前审核
				paramMap.put("bill_status", "D1");
				paramMap.put("is_review_back", "1");
				
			}
		}
		else if("6".equals(carKey))
		{//终审审批
			carLoanWorkFlowMethod(wVo);
			//改变单据状态
			if("true".equals(wVo.getPass()))
			{
			    //待签约
				paramMap.put("bill_status", "F1");
			}
			else if("false".equals(wVo.getPass()))
			{
				//终止
				paramMap.put("bill_status", "K1");
			}
		}
		else if("7".equals(carKey))
		{//合同签订
			carLoanWorkFlowMethod(wVo);
			//改变单据状态：
			if("true".equals(wVo.getPass())){
				//已签约
				paramMap.put("bill_status", "G1");
			}else if("false".equals(wVo.getPass())){
				//终止
				paramMap.put("bill_status", "K1");
			}
		}
		else if("8".equals(carKey))
		{//放款申请
			wVo.setPass("true");
			wVo.setAdvice("放款申请通过");
			carLoanWorkFlowMethod(wVo);
			if("true".equals(wVo.getPass()))
			{
				//待放款审批
				paramMap.put("bill_status", "H1");
			}
		}
		else if("9".equals(carKey))
		{//放款审批
			carLoanWorkFlowMethod(wVo);
			//改变单据状态:
			if("nullify".equals(wVo.getPass()))
			{
				//终止
				paramMap.put("bill_status", "K1");
			}
			else if("true".equals(wVo.getPass()))
			{
				//待放款确认
				paramMap.put("bill_status", "I1");
			}
			else if("false".equals(wVo.getPass()))
			{
				//已签约
				paramMap.put("bill_status", "G1");
			}
		}
		else if("10".equals(carKey))
		{//放款确认
			carLoanWorkFlowMethod(wVo);
			//改变单据状态:
			if("true".equals(wVo.getPass()))
			{
				//已完成
				paramMap.put("bill_status", "J1");
			}
			else if("false".equals(wVo.getPass()))
			{
				//终止
				paramMap.put("bill_status", "K1");
			}
		}
		wmsCreCreditHeadDao.updateRecord(paramMap);
		return result;
	}
	/*
	 * 车贷流程处理公共方法
	 * */
	private void carLoanWorkFlowMethod(WmsCarLoanWorkFlowVO wVo){
		Map<String,String> formPropertiesMap = new HashMap<String,String>();
		formPropertiesMap.put("pass", wVo.getPass());
		formPropertiesMap.put("advice", wVo.getAdvice());
		workflowService.completeTask(wVo.getUserId(), wVo.getTaskId(), formPropertiesMap);
	}
}
