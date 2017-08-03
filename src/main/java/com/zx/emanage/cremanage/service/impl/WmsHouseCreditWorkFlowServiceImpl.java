package com.zx.emanage.cremanage.service.impl;

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
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;

/**
 * 实现房贷流程业务实现
 * 
 * @author hancd
 */
@Service("approveHouseCreditWorkFlowService")
public class WmsHouseCreditWorkFlowServiceImpl implements IWmsHouseCreditWorkFlowService
{
    private static Logger log = LoggerFactory.getLogger(WmsHouseCreditWorkFlowServiceImpl.class);

    @Autowired
    private IWorkflowService workflowService;// 流程service

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheadDao;// 贷款主表dao

    @Autowired
    private PmPersonnelDao pmpersonnelDao;// hr人员信息dao

    @Override
    @Transactional
    public void startHouseCreditWorkFlow(String userId, String businessId)
    {
        // 开启流程
        workflowService.startWorkflow(userId, WorkflowConstantHelp.HOUSINGLOANPROCESS, businessId,
                                      new HashMap<String, Object>());
        // 改变表单状态 B.待复核
        // 通过用户提供的主表单ID，实现对期状态进行改变
        Map<String, Object> parmmap = new HashMap<String, Object>();
        parmmap.put("wms_cre_credit_head_id", businessId);
        parmmap.put("bill_status", "B");// 待复核
        wmscrecreditheadDao.updateRecord(parmmap);
    }

    @Override
    public Map<String, Object> getIdListWorkFlow(String userId, String housekey)
    {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 用户房产信息流程Key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
        // 用户ID
        workflowSearchInfoHelp.setUserId(userId);
        if (housekey.equals("0"))
        {
            // 放款复核
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_DKFH);
        }
        else if (housekey.equals("1"))
        {
            // 流水审批
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_LSSP);
        }
        else if (housekey.equals("2"))
        {
            // 办件审批
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_BJSP);
        }
        else if (housekey.equals("3"))
        {
            // 贷款终审
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_DKZS);
        }
        else if (housekey.equals("4"))
        {
            // 签订合同
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_QDHT);
        }
        else if (housekey.equals("5"))
        {
            // 公证
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_GZ);
        }
        else if (housekey.equals("6"))
        {
            // 他项
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_TX);
        }
        else if (housekey.equals("7"))
        {
            // 放款申请
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_FKSQ);
        }
        else if (housekey.equals("8"))
        {
            // 证信审批
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_ZXSP);
        }
        else if (housekey.equals("9"))
        {
            // 信息审批
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_XXSP);
        }
        else if (housekey.equals("10"))
        {
            // 电审审批
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_DSSP);
        }
        else if (housekey.equals("11"))
        {
            // 复核退回
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_CXSQ);
        }
        else if (housekey.equals("12"))
        {
            // 贷前退回即补件
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_BJ);
        }
        else if (housekey.equals("13"))
        {
            // 贷款申请审批
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_FKSQSP);
        }
        // 根据条件查找代办任务信息
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

            if (housekey.equals("12"))
            {
                WorkflowSearchInfoHelp w = new WorkflowSearchInfoHelp();
                w.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
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
        parmMap.put("idList", idList);
        parmMap.put("taskIdList", taskIdList);
        parmMap.put("approvesGroups", approvesGroups);
        parmMap.put("approveAdvices", approveAdvices);
        parmMap.put("approveTimes", approveTimes);
        return parmMap;
    }
    /**
     * 查询taskid userid可以为空 当前方法给MOA查询退件原因使用
     */
    @Override
    public Map<String, Object> getIdListWorkFlowNoUser(String userId, String housekey)
    {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 用户房产信息流程Key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
        // 用户ID
        workflowSearchInfoHelp.setUserId(userId);
        if (housekey.equals("12"))
        {
            // 贷前退回即补件
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_BJ);
        }
        
        // 根据条件查找代办任务信息
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasksNoUser(workflowSearchInfoHelp);

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

            if (housekey.equals("12"))
            {
                WorkflowSearchInfoHelp w = new WorkflowSearchInfoHelp();
                w.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
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
        parmMap.put("idList", idList);
        parmMap.put("taskIdList", taskIdList);
        parmMap.put("approvesGroups", approvesGroups);
        parmMap.put("approveAdvices", approveAdvices);
        parmMap.put("approveTimes", approveTimes);
        return parmMap;
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> addTaskIDHouse(java.util.List<java.util.Map<String, Object>> list,
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

    @Override
    public List<Map<String, Object>> addTaskIDHouse(List<Map<String, Object>> list, List<Integer> idLists,
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
                        // String iscontinueenable =
                        // (String)workflowService.findTaskVariable(taskIdLists.get(i),
                        // "iscontinueenable" );
                        // map.put("iscontinueenable",iscontinueenable);//添加一个新的属性
                        // 表单情况是否需要继续贷前审批
                        break;
                    }
                }
            }
        }
        return list;
    }

    @Override
    @Transactional
    public void creCheckHouseCreditWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
        // 改变表单状态C.待贷前审核
        Map<String, Object> parmmap = new HashMap<String, Object>();
        if ("true".equals(approveHouseWorkFlowVO.getPass()))
        {
            parmmap.put("bill_status", "C"); // C.待贷前审核
            parmmap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        }
        else if ("false".equals(approveHouseWorkFlowVO.getPass()))
        {
            // 查询流程是否是重新申请节点
            WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
            workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);// 房产流程实例key
            workflowSearchInfoHelp.setBusinessKey(String.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));
            workflowSearchInfoHelp.setTaskName("重新申请");
            workflowSearchInfoHelp.setUnFinish(true);
            List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
            if (historicTaskInstances == null || historicTaskInstances.size() == 0)
            {
                parmmap.put("bill_status", "C"); // C.待贷前审核
            }
            else
            {
                parmmap.put("bill_status", "I");// I.完善退回
            }
            parmmap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        }
        wmscrecreditheadDao.updateRecord(parmmap);
    }

    @Override
    public String creCheckHouseSupply(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        String result = "";
        try
        {
            approveHouseWorkFlowVO.setPass("supply");
            approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
            Map<String, Object> approveHouseSupply = new HashMap<>();
            approveHouseSupply.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            approveHouseSupply.put("bill_status", "J");// 补件中
            wmscrecreditheadDao.updateRecord(approveHouseSupply);
        }
        catch (Exception e)
        {
            return result = "error";
        }
        return result = "success";
    }

    @Override
    public String runTeamHouseCheckApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        // 流水
        WorkflowSearchInfoHelp wHelp = new WorkflowSearchInfoHelp();
        wHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
        wHelp.setBusinessKey(approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        wHelp.setUnFinish(true);
        wHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_BJ);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(wHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
            // 判断流水 状态
            JudgeCreditHouseBillStatus(approveHouseWorkFlowVO);
            return "success";
        }
        else
        {
            return "supply";
        }
    }

    @Override
    public String doapprovalHouseWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        // 办件
        WorkflowSearchInfoHelp wHelp = new WorkflowSearchInfoHelp();
        wHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
        wHelp.setBusinessKey(approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        wHelp.setUnFinish(true);
        wHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_BJ);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(wHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
            // 判断办件状态
            JudgeCreditHouseBillStatus(approveHouseWorkFlowVO);
            return "success";
        }
        else
        {
            return "supply";
        }
    }

    @Override
    public String creTeamHouseCheckApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        // 征信
        WorkflowSearchInfoHelp wHelp = new WorkflowSearchInfoHelp();
        wHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
        wHelp.setBusinessKey(approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        wHelp.setUnFinish(true);
        wHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_BJ);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(wHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
            // 判断证信 状态
            JudgeCreditHouseBillStatus(approveHouseWorkFlowVO);
            return "success";
        }
        else
        {
            return "supply";
        }
    }

    @Override
    public String creInfoTeamHouseCheckApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        // 信息
        WorkflowSearchInfoHelp wHelp = new WorkflowSearchInfoHelp();
        wHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
        wHelp.setBusinessKey(approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        wHelp.setUnFinish(true);
        wHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_BJ);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(wHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
            // 判断信息 状态
            JudgeCreditHouseBillStatus(approveHouseWorkFlowVO);
            return "success";
        }
        else
        {
            return "supply";
        }
    }

    @Override
    public String creTelTeamHouseCheckApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        // 电审
        WorkflowSearchInfoHelp wHelp = new WorkflowSearchInfoHelp();
        wHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
        wHelp.setBusinessKey(approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        wHelp.setUnFinish(true);
        wHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_BJ);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(wHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
            // 判断电审 状态
            JudgeCreditHouseBillStatus(approveHouseWorkFlowVO);
            return "success";
        }
        else
        {
            return "supply";
        }
    }

    @Override
    @Transactional
    public void theMortgageLoanApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        Map<String, Object> aMap = new HashMap<String, Object>();
        // 先把结果意见存储到流程中
        approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
        aMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        // 在改变表单的状态：如果同意 E待放款准备 不同意 Z终止
        if ("true".equals(approveHouseWorkFlowVO.getPass()))
        {
            aMap.put("bill_status", "E");// 待放款准备
            wmscrecreditheadDao.updateRecord(aMap);
        }
        else
        {
            aMap.put("bill_status", "Z");// 终止
            wmscrecreditheadDao.updateRecord(aMap);
        }
    }

    @Override
    @Transactional
    public void contiuneMortgageLoanBeforeApproveWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        Map<String, Object> aMap = new HashMap<String, Object>();
        // 设置继续审批的流程变量
        workflowService.setTaskVariable(approveHouseWorkFlowVO.getTaskId(), "iscontinue", "true");
        // 提交继续审批的结果和意见
        approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
        // 修改其表单的状态：C待贷前审核
        aMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        aMap.put("bill_status", "C");
        wmscrecreditheadDao.updateRecord(aMap);
    }

    @Override
    @Transactional
    public void theContractOrNotarizationOrOther(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String key)
    {
        // 根据存储的状态来判断是否要改变表的状态
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 获取流程key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
        workflowSearchInfoHelp.setFinish(true);
        workflowSearchInfoHelp.setBusinessKey(approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        approveHouseWorkFlowVO.setPass("true");
        if ("0".equals(key))
        {
            approveHouseWorkFlowVO.setAdvice("签订合同审批通过");
        }
        else if ("1".equals(key))
        {
            approveHouseWorkFlowVO.setAdvice("公证审批通过");
        }
        else if ("2".equals(key))
        {
            approveHouseWorkFlowVO.setAdvice("他项审批通过");
        }
        // 保存存储意见
        approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);

        workflowSearchInfoHelp.setTaskName("签订合同");
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            return;
        }
        workflowSearchInfoHelp.setTaskName("公证");
        historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            return;
        }
        workflowSearchInfoHelp.setTaskName("他项");
        historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            return;
        }

        Map<String, Object> aMap = new HashMap<>();
        aMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        // 改变表单状态：F待放款审核
        aMap.put("bill_status", "F");
        wmscrecreditheadDao.updateRecord(aMap);
    }

    @Override
    @Transactional
    public void theMortgageLoanWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        Map<String, Object> aMap = new HashMap<String, Object>();
        // 向流存储放贷放款申请审批的意见和结果
        approveHouseWorkFlowVO.setPass("true");
        approveHouseWorkFlowVO.setAdvice("放款申请通过");
        approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);// 房产流程实例key
        workflowSearchInfoHelp.setBusinessKey(String.valueOf(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));
        // 放款申请审批节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_FKSQSP);
        workflowSearchInfoHelp.setUnFinish(true);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            // 改变表单状态：G待放款
            aMap.put("bill_status", "G");
        }
        else
        {
            // 改变表单状态：K待审批
            aMap.put("bill_status", "K");
        }
        aMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheadDao.updateRecord(aMap);
    }

    @Override
    public void theMortgageLoanApprovelWorkFlow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        // 实现放款申请审批
        approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
        // 该表表单状态:G待放款
        Map<String, Object> aMap = new HashMap<>();
        aMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        aMap.put("bill_status", "G");
        wmscrecreditheadDao.updateRecord(aMap);
    }

    @Override
    @Transactional
    public void mortgageLoanExaminationAndApproval(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        Map<String, Object> aMap = new HashMap<String, Object>();
        // 向流存储房贷贷款确认审批的意见和结果
        approveHouseWorkFlowVO.setPass("true");
        approveHouseWorkFlowVO.setAdvice("放款确认审批通过");
        approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
        // 改变表单状态：H已完成
        aMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        aMap.put("bill_status", "H");
        wmscrecreditheadDao.updateRecord(aMap);
    }

    @Override
    public Map<String, Object> houseCreditWorkFlowView(String wms_cre_credit_head_id)
    {
        Map<String, Object> pMap = new HashMap<String, Object>();
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 获取流程key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
        workflowSearchInfoHelp.setFinish(true);
        workflowSearchInfoHelp.setBusinessKey(wms_cre_credit_head_id);
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
            	if(workflowHistoryInfoHelp.getApproveAdvice().equals("忽略")){
            		workflowHistoryInfoHelp.setApproveResult("忽略");
            	}else{
            		workflowHistoryInfoHelp.setApproveResult("审批通过");            		
            	}
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
            else if ("nullify".equals(workflowHistoryInfoHelp.getApproveResult()))
            {
                workflowHistoryInfoHelp.setApproveResult("退件成功");
            }
            else if (workflowHistoryInfoHelp.getApproveResult() == null)
            {
                continue;
            }
            works.add(workflowHistoryInfoHelp);
        }
        pMap.put("Rows", works);
        return pMap;
    }

    @Override
    public Map<String, Object> getCreditOrMortgageIdListWorkFlow(String userId)
    {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        //信贷
        WorkflowSearchInfoHelp workflowSearchInfoHelp1 = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp1.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp1.setTaskName(WorkflowConstantHelp.CREDITPROCESS_FKQR);
        workflowSearchInfoHelp1.setUserId(userId);
        //房贷
        WorkflowSearchInfoHelp workflowSearchInfoHelp2 = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp2.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);
        workflowSearchInfoHelp2.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_FKQR);
        workflowSearchInfoHelp2.setUserId(userId);
        //车贷
        WorkflowSearchInfoHelp workflowSearchInfoHelp3 = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp3.setProcessDefinitionKey(WorkflowConstantHelp.CARLOANPROCESS);
        workflowSearchInfoHelp3.setTaskName(WorkflowConstantHelp.CARLOANPROCESS_FKQR);
        workflowSearchInfoHelp3.setUserId(userId);
        
        List<Integer> idList = new ArrayList<Integer>();
        List<String> taskIdList = new ArrayList<String>();
        // 信贷 ：放款确认 流程中存在的主表单ID
        List<WorkflowInfoHelp> workflowInfoHelps1 = workflowService.findPackageTodoTasks(workflowSearchInfoHelp1);
        // 房贷 ：放款确认 流程中存在的主表单ID
        List<WorkflowInfoHelp> workflowInfoHelps2 = workflowService.findPackageTodoTasks(workflowSearchInfoHelp2);
        // 车贷 ：放款确认 流程中存在的主表单ID
        List<WorkflowInfoHelp> workflowInfoHelps3 = workflowService.findPackageTodoTasks(workflowSearchInfoHelp3);
        
        if ((workflowInfoHelps1 == null || workflowInfoHelps1.size() == 0)
            && (workflowInfoHelps2 == null || workflowInfoHelps2.size() == 0)
            && (workflowInfoHelps3 == null || workflowInfoHelps3.size() == 0))
        {
            Map<String, Object> aMap = new HashMap<String, Object>();
            return aMap;
        }
        for (WorkflowInfoHelp workflowInfoHelp1 : workflowInfoHelps1)
        {
            idList.add(Integer.valueOf(workflowInfoHelp1.getBusinessKey()));
            taskIdList.add(workflowInfoHelp1.getTaskId());
        }
        for (WorkflowInfoHelp workflowInfoHelp2 : workflowInfoHelps2)
        {
            idList.add(Integer.valueOf(workflowInfoHelp2.getBusinessKey()));
            taskIdList.add(workflowInfoHelp2.getTaskId());
        }
        for (WorkflowInfoHelp workflowInfoHelp3 : workflowInfoHelps3)
        {
            idList.add(Integer.valueOf(workflowInfoHelp3.getBusinessKey()));
            taskIdList.add(workflowInfoHelp3.getTaskId());
        }
        parmMap.put("idList", idList);
        parmMap.put("taskIdList", taskIdList);
        return parmMap;

    }

    @Override
    public List<Map<String, Object>> addTaskIDCreditOrHouse(List<Map<String, Object>> list, List<Integer> idLists,
                                                            List<String> taskIdLists)
    {
    	
    	if(list == null || idLists == null || taskIdLists == null) {
    		return null;
    	}
    	
    	for (Map<String, Object> map : list)
        {
            Integer wms_cre_credit_head_id = (Integer) map.get("wms_cre_credit_head_id");
            // 信贷房贷
            for (int i = 0; i < idLists.size(); i++)
            {
                if (idLists.get(i).compareTo(wms_cre_credit_head_id) == 0)
                {
                    map.put("taskId", taskIdLists.get(i));
                    break;
                }
            }

        }
        return list;
    }

    @Override
    public void houseCreditInquiryTreatment(String userId, String wms_cre_credit_head_id, String key)
    {
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);// 房贷key
        workflowSearchInfoHelp.setBusinessKey(wms_cre_credit_head_id);
        workflowSearchInfoHelp.setUserId(userId);
        if ("1".equals(key))
        {
            // 实现房贷草稿 实现流程开启
            startHouseCreditWorkFlow(userId, wms_cre_credit_head_id);
        }
        else if ("2".equals(key))
        {
            // 实现信贷复核退回
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.HOUSINGLOANPROCESS_CXSQ);
            // 根据条件查找代办任务信息
            List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
            // 提交给流程处理 该表表单状态
            WmsHouseCreditWorkFlowVO approveVo = new WmsHouseCreditWorkFlowVO();
            approveVo.setUserId(userId);
            approveVo.setPass("true");
            approveVo.setAdvice("重新申请已提交");
            approveVo.setTaskId(workflowInfoHelps.get(0).getTaskId());
            approveWorkFlowPassOrAdvice(approveVo);
            Map<String, Object> approve = new HashMap<>();
            approve.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            approve.put("bill_status", "B");// 待复核状态
            wmscrecreditheadDao.updateRecord(approve);
        }
        else if ("3".equals(key))
        {
            // 实现信贷补件
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_BJ);
            // 根据条件查找代办任务信息
            List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
            // 提交给流程处理 修改该表单状态
            WmsHouseCreditWorkFlowVO approveVo = new WmsHouseCreditWorkFlowVO();
            approveVo.setUserId(userId);
            approveVo.setPass("true");
            approveVo.setAdvice("补件重新提交");
            approveVo.setTaskId(workflowInfoHelps.get(0).getTaskId());
            approveWorkFlowPassOrAdvice(approveVo);
            Map<String, Object> approve = new HashMap<>();
            approve.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            approve.put("bill_status", "C");// 待贷前审批状态
            approve.put("set_cre_null_status", "1");// 传递只要不为空即可，实现制空操作
            wmscrecreditheadDao.updateRecord(approve);
        }
    }

    /**
     * 统一提交表单意见和存储意见
     */
    private void approveWorkFlowPassOrAdvice(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        Map<String, String> formPropertiesMap = new HashMap<String, String>();
        formPropertiesMap.put("pass", approveHouseWorkFlowVO.getPass());
        formPropertiesMap.put("advice", approveHouseWorkFlowVO.getAdvice());
        workflowService.completeTask(approveHouseWorkFlowVO.getUserId(), approveHouseWorkFlowVO.getTaskId(),
                                     formPropertiesMap);
    }

    /**
     * 判断流水审批组和办件审批组审批情况
     */
    @Transactional
    private void JudgeCreditHouseBillStatus(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO)
    {
        Map<String, Object> approves = new HashMap<String, Object>();
        // 判断四组审批情况 只有四组全部审批过 才能进入到待终审
        // 根据流程获取该表单的下一个节点
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.HOUSINGLOANPROCESS);// 房产流程实例key
        workflowSearchInfoHelp.setBusinessKey(approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        workflowSearchInfoHelp.setTaskName("贷款终审");
        workflowSearchInfoHelp.setUnFinish(true);
        List<HistoricTaskInstance> historicTaskInstances = workflowService.findHistoricTaskInstances(workflowSearchInfoHelp);
        if (historicTaskInstances == null || historicTaskInstances.size() == 0)
        {
            return;
        }
        else
        {
            approves.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
            approves.put("bill_status", "D");
            // 通过查处的状态改变表单状态
            wmscrecreditheadDao.updateRecord(approves);
        }
    }
    /**
     * 实现房贷  电审 流水 信调  审核 忽略单据功能
     */
    @Override
    public void houseCreditApprovalIgonre(
    		WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO) {
    	approveHouseWorkFlowVO.setPass("true");
    	approveHouseWorkFlowVO.setAdvice("忽略");
    	approveWorkFlowPassOrAdvice(approveHouseWorkFlowVO);
    }
}
