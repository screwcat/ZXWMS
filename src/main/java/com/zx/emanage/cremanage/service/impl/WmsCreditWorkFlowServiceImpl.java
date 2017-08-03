package com.zx.emanage.cremanage.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;

/**
 * 实现信贷流程
 * 
 * @author hancd
 * @version 2.0
 */
@Service("approveworkflowService")
public class WmsCreditWorkFlowServiceImpl implements IWmsCreditWorkFlowService
{
    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheadDao_m;

    @Autowired
    private PmPersonnelDao pmpersonnelDao;

    private static Logger log = LoggerFactory.getLogger(WmsCreditWorkFlowServiceImpl.class);

    /**
     * 信贷流程的开启
     * 
     * @param userId 当前登录用户ID
     * @param businessId 业务单据ID
     * @param inputkey=1代表信贷新单据开启
     * @author hancd
     */
    @Override
    public void startWorkFlow(String userId, String businessId)
    {
        Map<String, Object> parmMap = new HashMap<>();
        // 代表新流程inputkey=1
        parmMap.put("inputkey", "1");
        workflowService.startWorkflow(userId, WorkflowConstantHelp.CREDITPROCESS, businessId, parmMap);
    }

    /**
     * 信贷流程的二次开启
     * 
     * @param userId 当前登录用户ID
     * @param businessId 业务单据ID
     * @param startUserId 单据原始人ID
     * @param inputkey=2代表信贷终止单据二次开启
     * @author hancd
     */
    @Override
    public void startSecondaryWorkFlow(String userId, String businessId, String startUserId)
    {
        Map<String, Object> parmMap = new HashMap<>();
        // 添加指派人ID
        parmMap.put("startUserId", startUserId);
        parmMap.put("inputkey", "2");
        workflowService.startWorkflow(userId, WorkflowConstantHelp.CREDITPROCESS, businessId, parmMap);
    }

    /**
     * 信贷贷款复核
     * 
     * @param approveWorkFlowVO 信贷流程需要的公共VO类
     */
    @Override
    @Transactional
    public void creCheckCreditApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 复核审批
        // 提交task，并且保存form结果到流
        creapproveWorkFlow(approveWorkFlowVO);
        // 修改表单状态操作
        Map<String, Object> crecheck = new HashMap<String, Object>();
        if ("true".equals(approveWorkFlowVO.getPass()))
        {
            // 通过表单状态2.待贷前审批状态
            crecheck.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            crecheck.put("bill_status", "2");// <2.待贷前审批状态>
        }
        else if ("false".equals(approveWorkFlowVO.getPass()))
        {
            // 查询流程是否是重新申请节点
            WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
            workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);// 房产流程实例key
            workflowSearchInfoHelp.setBusinessKey(String.valueOf(approveWorkFlowVO.getWms_cre_credit_head_id()));
            // 设置节点：重新申请
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_CXSQ);
            workflowSearchInfoHelp.setUnFinish(true);
            List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
            if (historicTaskInstances == null || historicTaskInstances.size() == 0)
            {
                // 不通过表单状态2.待贷前审批状态
                crecheck.put("bill_status", "2");// <2.待贷前审批状态>
            }
            else
            {
                // 通过表单状态：12.复核退回
                crecheck.put("bill_status", "12");// <12复核退回>
                crecheck.put("check_back_remark", approveWorkFlowVO.getAdvice());
            }
            crecheck.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        }
        wmscrecreditheadDao_m.updateRecord(crecheck);
    }

    /**
     * 处理信贷复核退回，贷前退回,复议修订中主键ID与流程中存在此节点的单据进行匹配查询
     */
    @Override
    public Map<String, Object> getIdTaskIdCreditList(String useId, String creditKey)
    {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 用户流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        // 用户ID
        workflowSearchInfoHelp.setUserId(useId);
        if ("12".equals(creditKey))
        {
            // 12代表复核退回
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_CXSQ);
        }
        else if ("13".equals(creditKey))
        {
            // 13代表补件
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_BJ);
        }
        else if ("15".equals(creditKey))
        {
            // 15待复议修订
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_FYXD);
        }
        // 根据条件查找代办任务信息
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        // ID List
        List<Integer> idList = new ArrayList<Integer>();
        // taskID List
        List<String> taskIdList = new ArrayList<String>();
        // 审核组名称
        List<String> approvesGroups = new ArrayList<String>();
        // 退件原因
        List<String> approveAdvices = new ArrayList<String>();
        // 退件时间
        List<String> approveTimes = new ArrayList<String>();
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> pMap = new HashMap<String, Object>();
            return pMap;
        }
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
            taskIdList.add(workflowInfoHelp.getTaskId());
            // 访问流程
            WorkflowSearchInfoHelp w = new WorkflowSearchInfoHelp();
            w.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);// 设置流程实例key
            w.setFinish(true);// 查询完成
            w.setBusinessKey(String.valueOf(workflowInfoHelp.getBusinessKey()));// 添加当前表单id
            // 读取流程返回的结果
            List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(w);
            // 补件
            if ("13".equals(creditKey))
            {
                for (int i = workflowHistoryInfoHelps.size() - 1; i >= 0; i--)
                {
                    if ("supply".equals(workflowHistoryInfoHelps.get(i).getApproveResult()))
                    {
                        // 说明就是退件的节点
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
            // 复议修订
            if ("15".equals(creditKey))
            {
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
                        }
                    }
                }
                // 添加审批组名称
                approvesGroups.add(workflowHistoryInfoHelps.get(workflowHistoryInfoHelps.size() - 1)
                                                           .getPersonnel_deptName());
            }
        }
        parmMap.put("idList", idList);
        parmMap.put("taskIdList", taskIdList);
        parmMap.put("approvesGroups", approvesGroups);
        parmMap.put("approveAdvices", approveAdvices);
        parmMap.put("approveTimes", approveTimes);
        return parmMap;
    }

    /**
     * 实现对退回操作单据添加的新属性
     */
    @Override
    public java.util.List<java.util.Map<String, Object>> addTaskCredit(java.util.List<java.util.Map<String, Object>> list,
                                                                       java.util.List<Integer> idLists,
                                                                       java.util.List<String> taskIdLists,
                                                                       java.util.List<String> approvesGroups,
                                                                       java.util.List<String> approveAdvices,
                                                                       java.util.List<String> approveTimes)
    {
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
                        map.put("check_name", approvesGroups.get(i));
                        map.put("complete_idea", approveAdvices.get(i));
                        map.put("back_timestamp", approveTimes.get(i));
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
     * 实现复议修订页面审核组显示
     */
    @Override
    public List<Map<String, Object>> addTaskCredit(List<Map<String, Object>> list, List<Integer> idLists,
                                                   List<String> taskIdLists, List<String> approvesGroups)
    {
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
                        map.put("personnel_postName", approvesGroups.get(i));
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
     * 实现正常流程为单据添加新属性taskId
     */
    @Override
    public List<Map<String, Object>> addTaskCredit(List<Map<String, Object>> list, List<Integer> idLists,
                                                   List<String> taskIdLists)
    {
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
                        break;
                    }
                }
            }
        }
        return list;
    }

    /**
     * 实现信贷待前退回
     */
    @Override
    public String creCheckCreditSupply(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "success";
        try
        {
            // 实现贷前退回 useID taskId advice
            approveWorkFlowVO.setPass("supply");
            creapproveWorkFlow(approveWorkFlowVO);
            // 修改表单的状态：bill_status=13
            Map<String, Object> approveSupply = new HashMap<>();
            approveSupply.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            // 状态为：补件中
            approveSupply.put("bill_status", "13");// 补件中
            wmscrecreditheadDao_m.updateRecord(approveSupply);
        }
        catch (Throwable e)
        {
            return result = "error";
        }
        return result;
    }

    /**
     * 信贷流水审批
     */
    @Override
    public String runTeamCheckApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 流水
        Map<String, Object> approves = new HashMap<String, Object>();
        approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        approves.put("water_appro_result", approveWorkFlowVO.getPass().equals("true") ? "1" : "0");
        // 信贷-处理流水意见
        approves.put("water_appro_advice", approveWorkFlowVO.getYadvice());
        wmscrecreditheadDao_m.updateRecord(approves);
        // 判断当前单据是否被补件:如果成立 则不执行下面的语句 不成立 则执行下面的语句
        WorkflowSearchInfoHelp wHelp = new WorkflowSearchInfoHelp();
        wHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);// 信贷流程
        wHelp.setBusinessKey(String.valueOf(approveWorkFlowVO.getWms_cre_credit_head_id()));
        wHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_BJ);// 补件
        wHelp.setUnFinish(true);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(wHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            creapproveWorkFlow(approveWorkFlowVO);
            creCheckCreditBillStaus(approves, approveWorkFlowVO);
            return "success";
        }
        else
        {
            return "supply";
        }
    }

    /**
     * 信贷信息审批
     */
    @Override
    public String infoTeamCheckApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 信审
        Map<String, Object> approves = new HashMap<String, Object>();
        approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        approves.put("info_appro_result", approveWorkFlowVO.getPass().equals("true") ? "1" : "0");
        // 信贷-处理信审意见
        approves.put("info_appro_advice", approveWorkFlowVO.getAdvice());
        wmscrecreditheadDao_m.updateRecord(approves);
        // 判断当前单据是否被补件:如果成立 则不执行下面的语句 不成立 则执行下面的语句
        WorkflowSearchInfoHelp wHelp = new WorkflowSearchInfoHelp();
        wHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);// 信贷流程
        wHelp.setBusinessKey(String.valueOf(approveWorkFlowVO.getWms_cre_credit_head_id()));
        wHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_BJ);// 补件
        wHelp.setUnFinish(true);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(wHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            creapproveWorkFlow(approveWorkFlowVO);
            creCheckCreditBillStaus(approves, approveWorkFlowVO);
            return "success";
        }
        else
        {
            return "supply";
        }
    }

    /**
     * 信贷电审审批
     */
    @Override
    public String telTeamCheckApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 电审
        Map<String, Object> approves = new HashMap<String, Object>();
        approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        approves.put("phone_appro_result", approveWorkFlowVO.getPass().equals("true") ? "1" : "0");
        // 信贷-处理电审意见
        approves.put("phone_appro_advice", approveWorkFlowVO.getYadvice());
        wmscrecreditheadDao_m.updateRecord(approves);
        // 判断当前单据是否被补件:如果成立 则不执行下面的语句 不成立 则执行下面的语句
        WorkflowSearchInfoHelp wHelp = new WorkflowSearchInfoHelp();
        wHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);// 信贷流程
        wHelp.setBusinessKey(String.valueOf(approveWorkFlowVO.getWms_cre_credit_head_id()));
        wHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_BJ);// 补件
        wHelp.setUnFinish(true);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(wHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            creapproveWorkFlow(approveWorkFlowVO);
            creCheckCreditBillStaus(approves, approveWorkFlowVO);
            return "success";
        }
        else
        {
            return "supply";
        }
    }

    /**
     * 信贷证信审批
     */
    @Override
    public String creTeamCheckApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 证信
        Map<String, Object> approves = new HashMap<String, Object>();
        approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        approves.put("certificate_appro_result", approveWorkFlowVO.getPass().equals("true") ? "1" : "0");
        // 信贷-处理证信意见
        approves.put("certificate_appro_advice", approveWorkFlowVO.getAdvice());
        wmscrecreditheadDao_m.updateRecord(approves);
        // 判断当前单据是否被补件:如果成立 则不执行下面的语句 不成立 则执行下面的语句
        WorkflowSearchInfoHelp wHelp = new WorkflowSearchInfoHelp();
        wHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);// 信贷流程
        wHelp.setBusinessKey(String.valueOf(approveWorkFlowVO.getWms_cre_credit_head_id()));
        wHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_BJ);// 补件
        wHelp.setUnFinish(true);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(wHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            creapproveWorkFlow(approveWorkFlowVO);
            creCheckCreditBillStaus(approves, approveWorkFlowVO);
            return "success";
        }
        else
        {
            return "supply";
        }

    }

    /**
     * 信贷验点审批
     */
    @Override
    public String ydTeamCheckApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 验点
        Map<String, Object> approves = new HashMap<String, Object>();
        approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        approves.put("inspection_appro_result", approveWorkFlowVO.getPass().equals("true") ? "1" : "0");
        // 信贷-处理验点意见
        approves.put("inspection_appro_advice", approveWorkFlowVO.getAdvice());
        wmscrecreditheadDao_m.updateRecord(approves);
        // 判断当前单据是否被补件：如果成立 则不执行下面的语句 不成立 则执行小面的语句
        WorkflowSearchInfoHelp wHelp = new WorkflowSearchInfoHelp();
        wHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);// 信贷流程
        wHelp.setBusinessKey(String.valueOf(approveWorkFlowVO.getWms_cre_credit_head_id()));
        wHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_BJ);// 补件
        wHelp.setUnFinish(true);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(wHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            creapproveWorkFlow(approveWorkFlowVO);
            creCheckCreditBillStaus(approves, approveWorkFlowVO);
            return "success";
        }
        else
        {
            return "supply";
        }
    }

    /**
     * 信贷终审审批
     */
    @Override
    @Transactional
    public void creCheckloanApproApproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 实现信贷终审审批
        creapproveWorkFlow(approveWorkFlowVO);
        Map<String, Object> crecheckloanapprove = new HashMap<String, Object>();
        crecheckloanapprove.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        if ("true".equals(approveWorkFlowVO.getPass()))
        {
            // 修改表单状态
            crecheckloanapprove.put("bill_status", "4");// 待面签
        }
        else if ("false".equals(approveWorkFlowVO.getPass()))
        {
            // 修改表单状态
        	crecheckloanapprove.put("bill_status", "9");// 终止
            //终审审批作废单据
        	crecheckloanapprove.put("nullify_type", "1");
    		//作废操作人ID
            crecheckloanapprove.put("nullify_user_id", approveWorkFlowVO.getUser_id());
    		//作废操作人名称
            crecheckloanapprove.put("nullify_user_name", pmpersonnelDao.get(approveWorkFlowVO.getUser_id()).getPersonnel_name());
    		//作废原因
            crecheckloanapprove.put("nullify_reason", approveWorkFlowVO.getAdvice());
    		//作废时间
            crecheckloanapprove.put("nullify_timestamp",new Timestamp(System.currentTimeMillis()));
        }
        wmscrecreditheadDao_m.updateRecord(crecheckloanapprove);
    }

    /**
     * 信贷终审面签
     */
    @Override
    @Transactional
    public void creCheckloanApproInterView(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 实现面签审批
        creapproveWorkFlow(approveWorkFlowVO);
        // 根据流程获取该表单的下一个节点
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setBusinessKey(String.valueOf(approveWorkFlowVO.getWms_cre_credit_head_id()));
        // 新流程加合同签订节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_HTQD);
        workflowSearchInfoHelp.setUnFinish(true);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
        // 根据意见修改状态
        Map<String, Object> crecheckInterView = new HashMap<String, Object>();
        crecheckInterView.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            // 说明就是旧流程
            if (approveWorkFlowVO.getPass().equals("true"))
            {
                crecheckInterView.put("bill_status", "5");// 面签通过
            }
            else if (approveWorkFlowVO.getPass().equals("false"))
            {
            	//面签作废单据
                crecheckInterView.put("nullify_type", "2");
        		//作废操作人ID
                crecheckInterView.put("nullify_user_id", approveWorkFlowVO.getUser_id());
        		//作废操作人名称
                crecheckInterView.put("nullify_user_name", pmpersonnelDao.get(approveWorkFlowVO.getUser_id()).getPersonnel_name());
        		//作废原因
                crecheckInterView.put("nullify_reason", approveWorkFlowVO.getAdvice());
        		//作废时间
                crecheckInterView.put("nullify_timestamp",new Timestamp(System.currentTimeMillis()));
                crecheckInterView.put("bill_status", "9");// 终止
            }
        }
        else
        {
            // 说明就是新流程
            if (approveWorkFlowVO.getPass().equals("true"))
            {
                crecheckInterView.put("bill_status", "10");// 待签约
            }
            else if (approveWorkFlowVO.getPass().equals("false"))
            {
            	//面签作废单据
                crecheckInterView.put("nullify_type", "2");
        		//作废操作人ID
                crecheckInterView.put("nullify_user_id", approveWorkFlowVO.getUser_id());
        		//作废操作人名称
                crecheckInterView.put("nullify_user_name", pmpersonnelDao.get(approveWorkFlowVO.getUser_id()).getPersonnel_name());
        		//作废原因
                crecheckInterView.put("nullify_reason", approveWorkFlowVO.getAdvice());
        		//作废时间
                crecheckInterView.put("nullify_timestamp",new Timestamp(System.currentTimeMillis()));
                crecheckInterView.put("bill_status", "9");// 终止
            }
        }
        wmscrecreditheadDao_m.updateRecord(crecheckInterView);
    }

    /**
     * 信贷判断是含有合同签订流程的新旧对比
     */
    @Override
    public String BooleanCheckloanApproInterView(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 根据流程获取该表单的下一个节点
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setBusinessKey(String.valueOf(approveWorkFlowVO.getWms_cre_credit_head_id()));
        // 新流程加合同签订节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_HTQD);
        workflowSearchInfoHelp.setUnFinish(true);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);

        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            // 说明就是旧流程
            return "oldwork";
        }
        else
        {
            // 说明就是新流程
            return "newwork";
        }
    }

    /**
     * 信贷流程历程查看
     */
    @Override
    public Map<String, Object> checkApprovalProcess(Integer wms_cre_credit_head_id)
    {
        // 信贷流程历程查看
        Map<String, Object> repam = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);// 设置流程实例key
        workflowSearchInfoHelp.setFinish(true);// 查询完成
        workflowSearchInfoHelp.setBusinessKey(String.valueOf(wms_cre_credit_head_id));// 添加当前表单id
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
            else if ("iscontinue".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                workflowHistoryInfoHelp.setApproveResult("继续贷前审核");
            }
            else if ("supply".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                workflowHistoryInfoHelp.setApproveResult("退件成功");
            }
            else if (workflowHistoryInfoHelp.getApproveResult() == null)
            {
                continue;
            }
            else if("nullify".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
            	workflowHistoryInfoHelp.setApproveResult("审批不通过");
            }
            works.add(workflowHistoryInfoHelp);
        }
        repam.put("Rows", works);
        return repam;
    }

    /**
     * 判断是否需要继续待前审批 现在已经不需要
     */
    @Override
    @Transactional
    public void contiuneCreditAprove(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 实现信贷继续贷前审批
        // 设置继续审批的流程变量
        workflowService.setTaskVariable(approveWorkFlowVO.getTaskId(), "iscontinue", "true");
        // 实现当继续贷前审批-->存储流意见以及结果
        creapproveWorkFlow(approveWorkFlowVO);
        // 修改表单状态-->2.待前贷前管理状态
        Map<String, Object> approves = new HashMap<String, Object>();
        approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        approves.put("bill_status", "2");
        wmscrecreditheadDao_m.updateRecord(approves);
    }

    /**
     * 信贷合同签订
     */
    @Override
    @Transactional
    public void creCheckConcludeAndSignApprove(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        Map<String, Object> approves = new HashMap<>();
        // 说明实现单据作废操作
        if ("false".equals(approveWorkFlowVO.getPass()))
        {
            creapproveWorkFlow(approveWorkFlowVO);
            approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            approves.put("bill_status", "9");// 终止
            //合同签订作废单据
            approves.put("nullify_type", "3");
    		//作废操作人ID
    		approves.put("nullify_user_id", approveWorkFlowVO.getUser_id());
    		//作废操作人名称
    		approves.put("nullify_user_name", pmpersonnelDao.get(approveWorkFlowVO.getUser_id()).getPersonnel_name());
    		//作废原因
    		approves.put("nullify_reason", approveWorkFlowVO.getAdvice());
    		//作废时间
    		approves.put("nullify_timestamp",new Timestamp(System.currentTimeMillis()));
        }
        else
        {
            approveWorkFlowVO.setPass("true");// 保存同意
            approveWorkFlowVO.setAdvice("已签约");// 保存审批意见
            creapproveWorkFlow(approveWorkFlowVO);
            approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            approves.put("bill_status", "11");// 已签约
        }
        wmscrecreditheadDao_m.updateRecord(approves);
    }

    /**
     * 信贷放款申请
     */
    @Override
    @Transactional
    public void makeLoansAprove(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
    	 creapproveWorkFlow(approveWorkFlowVO);
    	 // 修改表单状态
         Map<String, Object> approves = new HashMap<String, Object>();
    	 //放款申请通过
    	if("true".equals(approveWorkFlowVO.getPass())){
    		approveWorkFlowVO.setAdvice("放款申请通过");
            // 进行判断下一节点是否是放款申请审批节点
            WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
            workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
            workflowSearchInfoHelp.setBusinessKey(String.valueOf(approveWorkFlowVO.getWms_cre_credit_head_id()));
            // 新流程加放款申请审批订节点
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_FKSQSP);
            workflowSearchInfoHelp.setUnFinish(true);
            List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
            if (historicTaskInstances == null || historicTaskInstances.size() == 0)
            {
                approves.put("bill_status", "6");// 待放款 需要添加信息的定义
            }
            else
            {
                approves.put("bill_status", "14");// 待审批 需要添加信息的定义
            }
    	}
    	//单据作废
    	else if("false".equals(approveWorkFlowVO.getPass())){
    		//作废环节：4代表放款申请 
    		approves.put("nullify_type", "4");
    		//作废操作人ID
    		approves.put("nullify_user_id", approveWorkFlowVO.getUser_id());
    		//作废操作人名称
    		approves.put("nullify_user_name", pmpersonnelDao.get(approveWorkFlowVO.getUser_id()).getPersonnel_name());
    		//作废原因
    		approves.put("nullify_reason", approveWorkFlowVO.getAdvice());
    		//作废时间
    		approves.put("nullify_timestamp",new Timestamp(System.currentTimeMillis()));
    		//单据状态:终止
    		approves.put("bill_status", "9");
    	}
        approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheadDao_m.updateRecord(approves);
    }

    /**
     * 信贷放款申请审批
     */
    @Override
    @Transactional
    public void makeLoansExaminationAndApproval(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
    	creapproveWorkFlow(approveWorkFlowVO);
    	// 修改表单状态
        Map<String, Object> approves = new HashMap<>();
    	//放款审批通过到放款申请页面 单据状态不改变：待放款
        if("true".equals(approveWorkFlowVO.getPass())||"false".equals(approveWorkFlowVO.getPass())){
        	 approves.put("bill_status", "6");// 待放款 需要添加信息的定义
        }
        //单据作废
        else if("nullify".equals(approveWorkFlowVO.getPass())){
        	//放款申请审批环节  作废
        	approves.put("nullify_type", "5");
    		//作废操作人ID
    		approves.put("nullify_user_id", approveWorkFlowVO.getUser_id());
    		//作废操作人名称
    		approves.put("nullify_user_name", pmpersonnelDao.get(approveWorkFlowVO.getUser_id()).getPersonnel_name());
    		//作废原因
    		approves.put("nullify_reason", approveWorkFlowVO.getAdvice());
    		//作废时间
    		approves.put("nullify_timestamp",new Timestamp(System.currentTimeMillis()));
    		//单据状态:终止
    		approves.put("bill_status", "9");
        }
        approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheadDao_m.updateRecord(approves);
    }
    /**
     * 放款确认审核
     */
    @Override
    @Transactional
    public void makeLoansaffirm(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
    	// 修改表单状态
        Map<String, Object> approves = new HashMap<String, Object>();
        if("true".equals(approveWorkFlowVO.getPass())){
        	approveWorkFlowVO.setAdvice("放款已确认");
        	creapproveWorkFlow(approveWorkFlowVO);
        	approves.put("bill_status", "7");// 已完成
        }
        else if("false".equals(approveWorkFlowVO.getPass())){
        	creapproveWorkFlow(approveWorkFlowVO);
        	//放款确认环节作废单据
        	approves.put("nullify_type", "6");
    		//作废操作人ID
    		approves.put("nullify_user_id", approveWorkFlowVO.getUser_id());
    		//作废操作人名称
    		approves.put("nullify_user_name", pmpersonnelDao.get(approveWorkFlowVO.getUser_id()).getPersonnel_name());
    		//作废原因
    		approves.put("nullify_reason", approveWorkFlowVO.getAdvice());
    		//作废时间
    		approves.put("nullify_timestamp",new Timestamp(System.currentTimeMillis()));
    		//单据状态:终止
    		approves.put("bill_status", "9");
        }
        approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheadDao_m.updateRecord(approves);
    }

    @Override
    @Transactional
    public void creditInquiryTreatment(WmsCreditWorkFlowVO aWorkFlowVO, String key)
    {
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setBusinessKey(String.valueOf(aWorkFlowVO.getWms_cre_credit_head_id()));
        workflowSearchInfoHelp.setUserId(String.valueOf(aWorkFlowVO.getUser_id()));
        if ("1".equals(key))
        {
            // 实现信贷草稿 实现流程开启
            startWorkFlow(String.valueOf(aWorkFlowVO.getUser_id()),
                          String.valueOf(aWorkFlowVO.getWms_cre_credit_head_id()));
            // 修改其状态：待复核
            Map<String, Object> approve = new HashMap<>();
            // approve.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            approve.put("wms_cre_credit_head_id", String.valueOf(aWorkFlowVO.getWms_cre_credit_head_id()));
            approve.put("bill_status", "1");// 待复核状态
            wmscrecreditheadDao_m.updateRecord(approve);
        }
        else if ("2".equals(key))
        {
            // 实现信贷复核退回
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_CXSQ);
            // 根据条件查找代办任务信息
            List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
            // 提交给流程处理 该表表单状态
            // ApproveWorkFlowVO approveVo = new ApproveWorkFlowVO();
            // approveVo.setUser_id(Integer.valueOf(userId));
            // approveVo.setPass("true");
            // approveVo.setAdvice("重新申请已提交");
            // approveVo.setCredit_limit(credit_limit);//设置申请金额
            // approveVo.setCre_loan_type(cre_loan_type);//设置信贷产品类型
            // approveVo.setTaskId(workflowInfoHelps.get(0).getTaskId());
            // creapproveWorkFlow(approveVo);
            aWorkFlowVO.setPass("true");
            aWorkFlowVO.setAdvice("重新申请已提交");
            aWorkFlowVO.setTaskId(workflowInfoHelps.get(0).getTaskId());
            creapproveWorkFlow(aWorkFlowVO);
            Map<String, Object> approve = new HashMap<>();
            // approve.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            approve.put("wms_cre_credit_head_id", String.valueOf(aWorkFlowVO.getWms_cre_credit_head_id()));
            approve.put("bill_status", "1");// 待复核状态
            wmscrecreditheadDao_m.updateRecord(approve);
        }
        else if ("3".equals(key))
        {
            // 实现信贷补件
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_BJ);
            // 根据条件查找代办任务信息
            List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
            // 流程处理 修改状态
            // ApproveWorkFlowVO approveVo = new ApproveWorkFlowVO();
            // approveVo.setUser_id(Integer.valueOf(userId));
            // approveVo.setPass("true");
            // approveVo.setAdvice("补件重新提交");
            // approveVo.setCredit_limit(credit_limit);//设置申请金额
            // approveVo.setCre_loan_type(cre_loan_type);//设置信贷产品类型
            // approveVo.setTaskId(workflowInfoHelps.get(0).getTaskId());
            // creapproveWorkFlow(approveVo);
            aWorkFlowVO.setPass("true");
            aWorkFlowVO.setAdvice("补件重新提交");
            aWorkFlowVO.setTaskId(workflowInfoHelps.get(0).getTaskId());
            creapproveWorkFlow(aWorkFlowVO);
            Map<String, Object> approve = new HashMap<>();
            // approve.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            approve.put("wms_cre_credit_head_id", String.valueOf(aWorkFlowVO.getWms_cre_credit_head_id()));
            approve.put("bill_status", "2");// 待贷前审批状态
            // 把所有组显示的结果清空
            approve.put("set_cre_null_status", "1");// 传递只要不为空即可，实现制空操作
            wmscrecreditheadDao_m.updateRecord(approve);
        }
        else if ("4".equals(key))
        {
            aWorkFlowVO.setPass("true");
            aWorkFlowVO.setAdvice("修订完成");
            creapproveWorkFlow(aWorkFlowVO);
            Map<String, Object> approve = new HashMap<>();
            approve.put("wms_cre_credit_head_id", String.valueOf(aWorkFlowVO.getWms_cre_credit_head_id()));
            approve.put("bill_status", "2");// 待贷前审批状态
            // 把所有组显示的结果清空
            approve.put("set_cre_null_status", "1");// 传递只要不为空即可，实现制空操作
            wmscrecreditheadDao_m.updateRecord(approve);
        }
    }

    /**
     * 实现初审面签不行实地验点：对单据进行自动流程释放
     */
    @Override
    public String loanApproInitialInterWorkflow(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 判断当前单据是否被补件：如果成立 则不执行下面的语句 不成立 则执行小面的语句
        WorkflowSearchInfoHelp wHelp = new WorkflowSearchInfoHelp();
        wHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);// 信贷流程
        wHelp.setBusinessKey(String.valueOf(approveWorkFlowVO.getWms_cre_credit_head_id()));
        wHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_BJ);// 补件
        wHelp.setUnFinish(true);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(wHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            creapproveWorkFlow(approveWorkFlowVO);
            Map<String, Object> approves = new HashMap<>();
            approves.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
            creCheckCreditBillStaus(approves, approveWorkFlowVO);
            return "success";
        }
        else
        {
            return "supply";
        }
    }

    /**
     * 抽取出公共方法 判断四组审批情况
     * 
     * @param approves
     * @param approveWorkFlowVO
     */
    @Transactional
    private void creCheckCreditBillStaus(Map<String, Object> approves, WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        // 判断四组审批情况 只有四组全部审批过 才能进入到待终审
        // 根据流程获取该表单的下一个节点
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setBusinessKey(String.valueOf(approveWorkFlowVO.getWms_cre_credit_head_id()));
        workflowSearchInfoHelp.setTaskName("贷款终审");
        workflowSearchInfoHelp.setUnFinish(true);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            return;
        }
        else
        {
            approves.put("bill_status", "3");
        }
        wmscrecreditheadDao_m.updateRecord(approves);
    }

    /**
     * 提交审批结果和意见与流对接的方法
     */
    private void creapproveWorkFlow(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        Map<String, String> formPropertiesMap = new HashMap<String, String>();
        formPropertiesMap.put("pass", approveWorkFlowVO.getPass());// 存储审批结果
        formPropertiesMap.put("advice", approveWorkFlowVO.getAdvice());// 存储表单审批意见
        formPropertiesMap.put("credit_limit", approveWorkFlowVO.getCredit_limit());// 获取申请贷款金额
        formPropertiesMap.put("cre_loan_type", approveWorkFlowVO.getCre_loan_type());// 获取贷款单据产品类型
        workflowService.completeTask(String.valueOf(approveWorkFlowVO.getUser_id()), approveWorkFlowVO.getTaskId(),
                                     formPropertiesMap);
    }
}
