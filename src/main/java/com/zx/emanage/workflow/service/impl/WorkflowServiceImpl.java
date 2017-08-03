package com.zx.emanage.workflow.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zx.emanage.cremanage.persist.WmsCreHousingAgainApplDao;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowFormHelp;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;

/**
 * 工作流对外提供的服务类Service
 * 
 * @author Tianyu
 */
@Service("workflowService")
@Transactional
public class WorkflowServiceImpl implements IWorkflowService
{

    private static Logger logger = LoggerFactory.getLogger(WorkflowServiceImpl.class);

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private FormService formService;

    @Autowired
    private ManagementService managementService;
    @Autowired
    private WmsCreHousingAgainApplDao wmscrehousingagainapplDao;//复议申请表

    @Override
    public ProcessInstance startWorkflow(String userId, String processDefinitionKey, String businessId,
                                         Map<String, Object> variables)
    {

        // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(userId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessId,
                                                                                   variables);
        String processInstanceId = processInstance.getId();
        logger.debug("start process of {key={}, bkey={}, pid={}, variables={}}", new Object[] { processDefinitionKey,
                                                                                               businessId,
                                                                                               processInstanceId,
                                                                                               variables });
        return processInstance;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findTodoTasks(WorkflowSearchInfoHelp workflowSearchInfoHelp)
    {
        List<Task> todoList = new ArrayList<Task>();
        // 当前登录用户id
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getUserId()))
        {
            TaskQuery taskQuery = taskService.createTaskQuery();
            // 业务类型（流程定义的KEY）必须
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessDefinitionKey()))
            {
                taskQuery.processDefinitionKey(workflowSearchInfoHelp.getProcessDefinitionKey());
            }
            // 节点名字，可以对应单据状态
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskName()))
            {
                taskQuery.taskName(workflowSearchInfoHelp.getTaskName());
            }
            // 业务表单的ID 转化为对流程实例的ID的查询
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getBusinessKey()))
            {
                taskQuery.processInstanceId(runtimeService.createProcessInstanceQuery()
                                                          .processInstanceBusinessKey(workflowSearchInfoHelp.getBusinessKey())
                                                          .singleResult().getProcessInstanceId());
            }
            // 流程实例的ID
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessInstanceId()))
            {
                taskQuery.processInstanceId(workflowSearchInfoHelp.getProcessInstanceId());
            }
            // 流程任务的ID
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskId()))
            {
                taskQuery.taskId(workflowSearchInfoHelp.getTaskId());
            }

            // 根据当前人的ID查询
            // List<Task> todoList =
            // taskQuery.taskAssignee(workflowSearchInfoHelp.getUserId()).active().orderByTaskId().desc().orderByTaskCreateTime().desc().list();
            // 后来发现更好的方法
            todoList = taskQuery.taskCandidateOrAssigned(workflowSearchInfoHelp.getUserId()).orderByTaskId().desc()
                                .orderByTaskCreateTime().desc().list();

            // TaskQuery taskQuery2 = taskService.createTaskQuery();
            // if
            // (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessDefinitionKey()))
            // {
            // taskQuery2.processDefinitionKey(workflowSearchInfoHelp.getProcessDefinitionKey());
            // }
            // if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskName()))
            // {
            // taskQuery2.taskName(workflowSearchInfoHelp.getTaskName());
            // }
            // if
            // (StringUtils.isNotBlank(workflowSearchInfoHelp.getBusinessKey()))
            // {
            // taskQuery2.processInstanceId(runtimeService.createProcessInstanceQuery()
            // .processInstanceBusinessKey(workflowSearchInfoHelp.getBusinessKey())
            // .singleResult().getProcessInstanceId());
            // }
            // if
            // (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessInstanceId()))
            // {
            // taskQuery2.processInstanceId(workflowSearchInfoHelp.getProcessInstanceId());
            // }
            // if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskId()))
            // {
            // taskQuery2.taskId(workflowSearchInfoHelp.getTaskId());
            // }
            // 根据当前人未签收的任务
            // List<Task> unsignedTasks =
            // taskQuery2.taskCandidateUser(workflowSearchInfoHelp.getUserId()).active()
            // .orderByTaskId().desc().orderByTaskCreateTime().desc().list();
            // 合并
            // tasks.addAll(todoList);
            // tasks.addAll(unsignedTasks);
        }
        return todoList;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Task> findTodoTasksNoUser(WorkflowSearchInfoHelp workflowSearchInfoHelp)
    {
        List<Task> todoList = new ArrayList<Task>();
        
            TaskQuery taskQuery = taskService.createTaskQuery();
            // 业务类型（流程定义的KEY）必须
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessDefinitionKey()))
            {
                taskQuery.processDefinitionKey(workflowSearchInfoHelp.getProcessDefinitionKey());
            }
            // 节点名字，可以对应单据状态
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskName()))
            {
                taskQuery.taskName(workflowSearchInfoHelp.getTaskName());
            }
            // 业务表单的ID 转化为对流程实例的ID的查询
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getBusinessKey()))
            {
                taskQuery.processInstanceId(runtimeService.createProcessInstanceQuery()
                                                          .processInstanceBusinessKey(workflowSearchInfoHelp.getBusinessKey())
                                                          .singleResult().getProcessInstanceId());
            }
            // 流程实例的ID
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessInstanceId()))
            {
                taskQuery.processInstanceId(workflowSearchInfoHelp.getProcessInstanceId());
            }
            // 流程任务的ID
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskId()))
            {
                taskQuery.taskId(workflowSearchInfoHelp.getTaskId());
            }

            // 根据当前人的ID查询
            // List<Task> todoList =
            // taskQuery.taskAssignee(workflowSearchInfoHelp.getUserId()).active().orderByTaskId().desc().orderByTaskCreateTime().desc().list();
            // 后来发现更好的方法
            todoList = taskQuery.taskCandidateOrAssigned(workflowSearchInfoHelp.getUserId()).orderByTaskId().desc()
                                .orderByTaskCreateTime().desc().list();
            return todoList;
    }

    @Override
    @Transactional(readOnly = true)
    public int findTodoTasksCount(WorkflowSearchInfoHelp workflowSearchInfoHelp)
    {
        long todoListCount = 0l;
        // 当前登录用户id
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getUserId()))
        {
            TaskQuery taskQuery = taskService.createTaskQuery();
            // 业务类型（流程定义的KEY）必须
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessDefinitionKey()))
            {
                taskQuery.processDefinitionKey(workflowSearchInfoHelp.getProcessDefinitionKey());
            }
            // 节点名字，可以对应单据状态
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskName()))
            {
                taskQuery.taskName(workflowSearchInfoHelp.getTaskName());
            }
            // 业务表单的ID 转化为对流程实例的ID的查询
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getBusinessKey()))
            {
                taskQuery.processInstanceId(runtimeService.createProcessInstanceQuery()
                                                          .processInstanceBusinessKey(workflowSearchInfoHelp.getBusinessKey())
                                                          .singleResult().getProcessInstanceId());
            }
            // 流程实例的ID
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessInstanceId()))
            {
                taskQuery.processInstanceId(workflowSearchInfoHelp.getProcessInstanceId());
            }
            // 流程任务的ID
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskId()))
            {
                taskQuery.taskId(workflowSearchInfoHelp.getTaskId());
            }

            // 根据当前人的ID查询
            // List<Task> todoList =
            // taskQuery.taskAssignee(workflowSearchInfoHelp.getUserId()).active().orderByTaskId().desc().orderByTaskCreateTime().desc().list();
            // 后来发现更好的方法
            todoListCount = taskQuery.taskCandidateOrAssigned(workflowSearchInfoHelp.getUserId()).orderByTaskId()
                                     .desc().orderByTaskCreateTime().desc().count();

            // TaskQuery taskQuery2 = taskService.createTaskQuery();
            // if
            // (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessDefinitionKey()))
            // {
            // taskQuery2.processDefinitionKey(workflowSearchInfoHelp.getProcessDefinitionKey());
            // }
            // if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskName()))
            // {
            // taskQuery2.taskName(workflowSearchInfoHelp.getTaskName());
            // }
            // if
            // (StringUtils.isNotBlank(workflowSearchInfoHelp.getBusinessKey()))
            // {
            // taskQuery2.processInstanceId(runtimeService.createProcessInstanceQuery()
            // .processInstanceBusinessKey(workflowSearchInfoHelp.getBusinessKey())
            // .singleResult().getProcessInstanceId());
            // }
            // if
            // (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessInstanceId()))
            // {
            // taskQuery2.processInstanceId(workflowSearchInfoHelp.getProcessInstanceId());
            // }
            // if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskId()))
            // {
            // taskQuery2.taskId(workflowSearchInfoHelp.getTaskId());
            // }
            // 根据当前人未签收的任务
            // List<Task> unsignedTasks =
            // taskQuery2.taskCandidateUser(workflowSearchInfoHelp.getUserId()).active()
            // .orderByTaskId().desc().orderByTaskCreateTime().desc().list();
            // 合并
            // tasks.addAll(todoList);
            // tasks.addAll(unsignedTasks);
        }
        return Long.valueOf(todoListCount).intValue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findTodoTasks(WorkflowSearchInfoHelp workflowSearchInfoHelp, int firstResult, int maxResults)
    {
        List<Task> todoList = new ArrayList<Task>();
        // 当前登录用户id
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getUserId()))
        {
            TaskQuery taskQuery = taskService.createTaskQuery();
            // 业务类型（流程定义的KEY）必须
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessDefinitionKey()))
            {
                taskQuery.processDefinitionKey(workflowSearchInfoHelp.getProcessDefinitionKey());
            }
            // 节点名字，可以对应单据状态
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskName()))
            {
                taskQuery.taskName(workflowSearchInfoHelp.getTaskName());
            }
            // 业务表单的ID 转化为对流程实例的ID的查询
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getBusinessKey()))
            {
                taskQuery.processInstanceId(runtimeService.createProcessInstanceQuery()
                                                          .processInstanceBusinessKey(workflowSearchInfoHelp.getBusinessKey())
                                                          .singleResult().getProcessInstanceId());
            }
            // 流程实例的ID
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessInstanceId()))
            {
                taskQuery.processInstanceId(workflowSearchInfoHelp.getProcessInstanceId());
            }
            // 流程任务的ID
            if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskId()))
            {
                taskQuery.taskId(workflowSearchInfoHelp.getTaskId());
            }
            todoList = taskQuery.taskCandidateOrAssigned(workflowSearchInfoHelp.getUserId()).orderByTaskId().desc()
                                .orderByTaskCreateTime().desc().listPage(firstResult, maxResults);
        }
        return todoList;
    }
    /**
     * 当前登录人不可以为空//原方法
     */
    @Override
    @Transactional(readOnly = true)
    public List<WorkflowInfoHelp> findPackageTodoTasks(WorkflowSearchInfoHelp workflowSearchInfoHelp)
    {
        List<Task> tasks = findTodoTasks(workflowSearchInfoHelp);
        return packageTaskInfo(tasks);
        // 优化流程
        // return packageTaskInfoStreamline(tasks);
    }
    
    /**
     * 当前登录人可以为空//新方法 特殊地方使用
     */
    @Override
    @Transactional(readOnly = true)
    public List<WorkflowInfoHelp> findPackageTodoTasksNoUser(WorkflowSearchInfoHelp workflowSearchInfoHelp)
    {
        List<Task> tasks = findTodoTasksNoUser(workflowSearchInfoHelp);
        return packageTaskInfo(tasks);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowInfoHelp> findPackageTodoTasks(WorkflowSearchInfoHelp workflowSearchInfoHelp, int firstResult,
                                                       int maxResults)
    {
        List<Task> tasks = findTodoTasks(workflowSearchInfoHelp, firstResult, maxResults);
        return packageTaskInfo(tasks);
    }

    private List<WorkflowInfoHelp> packageTaskInfo(List<Task> tasks)
    {

        List<WorkflowInfoHelp> packageTodoTasksList = new ArrayList<WorkflowInfoHelp>();
        for (Task task : tasks)
        {
            WorkflowInfoHelp workflowInfoHelp = new WorkflowInfoHelp();
            // 流程定义的KEY和Name
            String processDefinitionId = task.getProcessDefinitionId();
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                                                                   .processDefinitionId(processDefinitionId)
                                                                   .singleResult();
            workflowInfoHelp.setProcessDefinitionKey(processDefinition.getKey());
            workflowInfoHelp.setProcessDefinitionName(processDefinition.getName());
            // 流程实例的ID
            String processInstanceId = task.getProcessInstanceId();
            workflowInfoHelp.setProcessInstanceId(processInstanceId);
            // 流程任务的ID
            String taskId = task.getId();
            workflowInfoHelp.setTaskId(taskId);
            // 业务表单的ID
            String businessKey = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
                                               .singleResult().getBusinessKey();
            workflowInfoHelp.setBusinessKey(businessKey);
            // 流程发起时间
            Date startTime = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId)
                                           .singleResult().getStartTime();
            String startTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
            workflowInfoHelp.setStartTime(startTimeString);
            // 发起人的ID,可以对应录入人的ID
            String startUserId = historyService.createHistoricProcessInstanceQuery()
                                               .processInstanceId(processInstanceId).singleResult().getStartUserId();
            workflowInfoHelp.setStartUserId(startUserId);
            // 发起人的Name,可以对应录入人的名字
            User startUser = identityService.createUserQuery().userId(startUserId).singleResult();
            workflowInfoHelp.setStartUser((StringUtils.isNotEmpty(startUser.getFirstName()) ? startUser.getFirstName()
                                                                                           : "")
                                          + (StringUtils.isNotEmpty(startUser.getLastName()) ? startUser.getLastName()
                                                                                            : ""));
            // 节点名字，可以对应单据状态
            String taskName = task.getName();
            workflowInfoHelp.setTaskName(taskName);
            // 指定的任务分配人，可以对应任务是认领还是办理
            String assignee = task.getAssignee();
            workflowInfoHelp.setAssignee(assignee);

            // 当前审批人
            if (StringUtils.isNotEmpty(assignee))
            {
                // 当前审批人的id
                workflowInfoHelp.setApproverids(assignee);
                // 当前审批人的名字
                User user1 = identityService.createUserQuery().userId(assignee).singleResult();
                if(user1==null){
                	 workflowInfoHelp.setApprovers("");
                }else{
                	workflowInfoHelp.setApprovers((StringUtils.isNotEmpty(user1.getFirstName()) ? user1.getFirstName() : "")
                            + (StringUtils.isNotEmpty(user1.getLastName()) ? user1.getLastName() : ""));
                }    
            }
            else
            {
                String approverids = "";
                String approvers = "";

                List<IdentityLink> identityLinks = taskService.getIdentityLinksForTask(taskId);
                for (IdentityLink identityLink : identityLinks)
                {
                    // 处理候选人。。。
                    String userId = identityLink.getUserId();
                    if (StringUtils.isNotEmpty(userId))
                    {
                        approverids = approverids + userId + ",";
                        User user = identityService.createUserQuery().userId(userId).singleResult();
                        approvers = approvers
                                    + ((StringUtils.isNotEmpty(user.getFirstName()) ? user.getFirstName() : "") + (StringUtils.isNotEmpty(user.getLastName()) ? user.getLastName()
                                                                                                                                                             : ""))
                                    + ",";
                    }

                    // 处理候选组
                    String identityGroupId = identityLink.getGroupId();
                    if (StringUtils.isNotEmpty(identityGroupId))
                    {
                        // 下面注释掉的3行是直接显示组的信息
                        // String groupName =
                        // identityService.createGroupQuery().groupId(
                        // identityGroupId ).singleResult().getName();
                        // approverids=approverids + identityGroupId + ",";
                        // approvers = approvers + groupName + ",";
                        List<User> userList = identityService.createUserQuery().memberOfGroup(identityGroupId).list();
                        if (null != userList)
                        {
                            for (User user : userList)
                            {
                                approverids = approverids + user.getId() + ",";
                                approvers = approvers
                                            + ((StringUtils.isNotEmpty(user.getFirstName()) ? user.getFirstName() : "") + (StringUtils.isNotEmpty(user.getLastName()) ? user.getLastName()
                                                                                                                                                                     : ""))
                                            + ",";
                            }
                        }
                    }
                }// for (IdentityLink identityLink : identityLinks)结束
                if (StringUtils.isNotEmpty(approverids))
                {
                    approverids = approverids.substring(0, approverids.length() - 1);
                    approvers = approvers.substring(0, approvers.length() - 1);
                }
                workflowInfoHelp.setApproverids(approverids);
                workflowInfoHelp.setApprovers(approvers);
                workflowInfoHelp.setProcessStatus(WorkflowConstantHelp.PROCESSSTATUS_RUNNING);
            }
            packageTodoTasksList.add(workflowInfoHelp);
        }
        return packageTodoTasksList;
    }

    /**
     * 
     * @Title: packageTaskInfoStreamline
     * @Description: TODO(对tasklist处理)
     * @param tasks
     * @return List<WorkflowInfoHelp>
     * @author: baisong
     * @time:2016年12月15日 下午4:42:03
     * history:
     * 1、2016年12月15日 baisong 创建方法
     */
    private List<WorkflowInfoHelp> packageTaskInfoStreamline(List<Task> tasks)
    {

        List<WorkflowInfoHelp> packageTodoTasksList = new ArrayList<WorkflowInfoHelp>();
        for (Task task : tasks)
        {
            WorkflowInfoHelp workflowInfoHelp = new WorkflowInfoHelp();
            // 流程实例的ID
            String processInstanceId = task.getProcessInstanceId();
            // 业务表单的ID
            String businessKey = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getBusinessKey();
            workflowInfoHelp.setBusinessKey(businessKey);
            packageTodoTasksList.add(workflowInfoHelp);
        }
        return packageTodoTasksList;
    }
    @Override
    @Transactional(readOnly = true)
    public List<HistoricTaskInstance> findHistoricTaskInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp)
    {

        HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery();
        // 业务类型（流程定义的KEY）必须
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessDefinitionKey()))
        {
            historicTaskInstanceQuery.processDefinitionKey(workflowSearchInfoHelp.getProcessDefinitionKey());
        }
        // 流程实例的ID
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessInstanceId()))
        {
            historicTaskInstanceQuery.processInstanceId(workflowSearchInfoHelp.getProcessInstanceId());
        }
        // 流程任务的ID
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskId()))
        {
            historicTaskInstanceQuery.taskId(workflowSearchInfoHelp.getTaskId());
        }
        // 业务表单的ID
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getBusinessKey()))
        {
            historicTaskInstanceQuery.processInstanceBusinessKey(workflowSearchInfoHelp.getBusinessKey());
        }
        // 节点名字，可以对应单据状态
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getTaskName()))
        {
            historicTaskInstanceQuery.taskName(workflowSearchInfoHelp.getTaskName());
        }
        // 是否完成
        if (BooleanUtils.isTrue((workflowSearchInfoHelp.isFinish())))
        {
            historicTaskInstanceQuery.finished().orderByHistoricTaskInstanceEndTime().asc();
        }
        // 是否未完成
        if (BooleanUtils.isTrue((workflowSearchInfoHelp.isUnFinish())))
        {
            historicTaskInstanceQuery.unfinished();
        }
        List<HistoricTaskInstance> historicTaskInstances = historicTaskInstanceQuery.list();
        return historicTaskInstances;
    }

    /**
     * 
     * @Title: getTaskIdByBusinessId
     * @Description: 根据业务主键ID获取流程ID
     * @param businessKey
     * @param processDefinitionKey
     * @return 
     * @author: Guanxu
     * @time:2016年11月7日 下午1:30:47
     * history:
     * 1、2016年11月7日 Guanxu 创建方法
     * @see
     */
    @Override
    public String getTaskIdByBusinessId(String businessKey, String processDefinitionKey)
    {
        List<Task> list = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).processDefinitionKey(processDefinitionKey).list();
        if (list != null && list.size() > 0)
        {
            return list.get(0).getId();
        }
        else
        {
            return null;
        }
        /* TaskQuery taskQuery = taskService.createTaskQuery();
         taskQuery.processDefinitionKey(processDefinitionKey);
         List<ProcessInstance> runList = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).list();
         taskQuery.processInstanceId(runList.get(0).getProcessInstanceId());
         List<Task> list = taskQuery.list();
         if (list != null && list.size() > 0)
         {
             return list.get(0).getId();
         }
         else
         {
             return null;
         }*/
    }

    /*
     * (non-Javadoc)
     * @see com.zx.emanage.workflow.service.impl.IWorkflowService#
     * findPackageHistoricTaskInstances(java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public List<WorkflowHistoryInfoHelp> findPackageHistoricTaskInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp)
    {

        List<WorkflowHistoryInfoHelp> packageHistoricTaskInstancesList = new ArrayList<WorkflowHistoryInfoHelp>();

        // 封装发起业务表单环节信息
        // 重新构造WorkflowSearchInfoHelp，因为参数workflowSearchInfoHelp传进来isFinish属性是不需要的
        WorkflowSearchInfoHelp workflowSearchInfoHelpForStart = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelpForStart.setProcessDefinitionKey(workflowSearchInfoHelp.getProcessDefinitionKey());
        workflowSearchInfoHelpForStart.setBusinessKey(workflowSearchInfoHelp.getBusinessKey());
        List<HistoricProcessInstance> historicProcessInstances = findHistoricProcessInstances(workflowSearchInfoHelpForStart);
        if(null == historicProcessInstances || historicProcessInstances.size() == 0) {
            return packageHistoricTaskInstancesList;
        }
        HistoricProcessInstance historicProcessInstance = historicProcessInstances.get(0);
        

        WorkflowHistoryInfoHelp workflowHistoryInfoHelpForStart = new WorkflowHistoryInfoHelp();
        // 流程实例的ID
        workflowHistoryInfoHelpForStart.setProcessInstanceId(historicProcessInstance.getId());
        // 流程任务的ID
        workflowHistoryInfoHelpForStart.setTaskId(historicProcessInstance.getStartActivityId());
        // 业务表单的ID
        workflowHistoryInfoHelpForStart.setBusinessKey(historicProcessInstance.getBusinessKey());
        // 发起人的ID,换成审批人的ID
        workflowHistoryInfoHelpForStart.setApproverids(historicProcessInstance.getStartUserId());
        // 发起人的Name,换成审批人的名字
        User startUser = identityService.createUserQuery().userId(historicProcessInstance.getStartUserId())
                                        .singleResult();
        workflowHistoryInfoHelpForStart.setApprovers((StringUtils.isNotEmpty(startUser.getFirstName()) ? startUser.getFirstName()
                                                                                                      : "")
                                                     + (StringUtils.isNotEmpty(startUser.getLastName()) ? startUser.getLastName()
                                                                                                       : ""));
        // 发起人角色（组）
        String startUserGroupNames = "";
        List<Group> startUserGroups = identityService.createGroupQuery()
                                                     .groupMember(historicProcessInstance.getStartUserId())
                                                     .orderByGroupId().asc().list();
        for (Group group : startUserGroups)
        {
            startUserGroupNames = startUserGroupNames + group.getName() + ",";
        }
        if(startUserGroupNames!=null&&!"".equals(startUserGroupNames)){
        	startUserGroupNames = startUserGroupNames.substring(0, startUserGroupNames.length() - 1);	
        }
        workflowHistoryInfoHelpForStart.setApproversGroup(startUserGroupNames);
        // 节点名字，可以对应单据状态
        workflowHistoryInfoHelpForStart.setTaskName("提交业务表单，发起流程");
        // 审批时间
        DateFormat simpleStartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        workflowHistoryInfoHelpForStart.setApproveTime(simpleStartDateFormat.format(historicProcessInstance.getStartTime()));
        // 审批结果
        workflowHistoryInfoHelpForStart.setApproveResult("已提交");
        // 审批意见
        workflowHistoryInfoHelpForStart.setApproveAdvice("提交成功");

        packageHistoricTaskInstancesList.add(workflowHistoryInfoHelpForStart);

        List<HistoricTaskInstance> historicTaskInstances = findHistoricTaskInstances(workflowSearchInfoHelp);
        int is_num=1;//记录复议次数
        int numVal=0;//记录循环次数
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances)
        {
            WorkflowHistoryInfoHelp workflowHistoryInfoHelp = new WorkflowHistoryInfoHelp();
            // 流程实例的ID
            workflowHistoryInfoHelp.setProcessInstanceId(historicTaskInstance.getProcessInstanceId());
            // 流程任务的ID
            String taskId = historicTaskInstance.getId();
            workflowHistoryInfoHelp.setTaskId(taskId);
            // 业务表单的ID
            String businessKey = historyService.createHistoricProcessInstanceQuery()
                                               .processInstanceId(historicTaskInstance.getProcessInstanceId())
                                               .singleResult().getBusinessKey();
            workflowHistoryInfoHelp.setBusinessKey(businessKey);
            // 发起人的ID,可以对应录入人的ID
            // String startUserId =
            // historyService.createHistoricProcessInstanceQuery().processInstanceId(
            // historicTaskInstance.getProcessInstanceId()
            // ).singleResult().getStartUserId();
            // workflowHistoryInfoHelp.setStartUserId( startUserId );
            // 发起人的Name,可以对应录入人的名字
            // User startUser =
            // identityService.createUserQuery().userId(startUserId
            // ).singleResult();
            // workflowHistoryInfoHelp.setStartUser(
            // (StringUtils.isNotEmpty(startUser.getFirstName())?startUser.getFirstName():"")
            // +(StringUtils.isNotEmpty(startUser.getLastName())?startUser.getLastName():"")
            // );
            // 节点名字，可以对应单据状态
            workflowHistoryInfoHelp.setTaskName(historicTaskInstance.getName());
            // 审批时间
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // workflowHistoryInfoHelp.setApproveTime( simpleDateFormat.format(
            // historicTaskInstance.getStartTime()) );
            if (null != historicTaskInstance.getEndTime())
            {
                workflowHistoryInfoHelp.setApproveTime(simpleDateFormat.format(historicTaskInstance.getEndTime()));
            }
            else
            {
                workflowHistoryInfoHelp.setApproveTime("");
            }
            // 审批人
            String assignee = historicTaskInstance.getAssignee();
            if (StringUtils.isNotEmpty(assignee))
            {
                // 审批人的id
                workflowHistoryInfoHelp.setApproverids(assignee);
                // 审批人的名字
                User user1 = identityService.createUserQuery().userId(assignee).singleResult();
                workflowHistoryInfoHelp.setApprovers((StringUtils.isNotEmpty(user1.getFirstName()) ? user1.getFirstName()
                                                                                                  : "")
                                                     + (StringUtils.isNotEmpty(user1.getLastName()) ? user1.getLastName()
                                                                                                   : ""));
                // 审批人角色（组）
                // String groupName =
                // identityService.createGroupQuery().groupMember( assignee
                // ).singleResult().getName();
                String groupNames = "";
                List<Group> groups = identityService.createGroupQuery().groupMember(assignee).orderByGroupId().asc()
                                                    .list();
                // 对审批人在角色组里面执行下面的语句否则不执行
                if (groups.size() > 0)
                {
                    for (Group group : groups)
                    {
                        groupNames = groupNames + group.getName() + ",";
                    }
                    groupNames = groupNames.substring(0, groupNames.length() - 1);
                }
                workflowHistoryInfoHelp.setApproversGroup(groupNames);
            }
            else
            {
                String approverids = "";
                String approvers = "";
                List<HistoricIdentityLink> identityLinks = historyService.getHistoricIdentityLinksForTask(taskId);
                for (HistoricIdentityLink identityLink : identityLinks)
                {
                    // 处理候选人。。。
                    String userId = identityLink.getUserId();
                    if (StringUtils.isNotEmpty(userId))
                    {
                        approverids = approverids + userId + ",";
                        User user = identityService.createUserQuery().userId(userId).singleResult();
                        approvers = approvers
                                    + ((StringUtils.isNotEmpty(user.getFirstName()) ? user.getFirstName() : "") + (StringUtils.isNotEmpty(user.getLastName()) ? user.getLastName()
                                                                                                                                                             : ""))
                                    + ",";
                    }

                    // 处理候选组
                    String identityGroupId = identityLink.getGroupId();
                    if (StringUtils.isNotEmpty(identityGroupId))
                    {
                        String groupName = identityService.createGroupQuery().groupId(identityGroupId).singleResult()
                                                          .getName();
                        approverids = approverids + identityGroupId + ",";
                        approvers = approvers + groupName + ",";
                    }
                }
                if (StringUtils.isNotEmpty(approverids))
                {
                    approverids = approverids.substring(0, approverids.length() - 1);
                    approvers = approvers.substring(0, approvers.length() - 1);
                }
                workflowHistoryInfoHelp.setApproverids(approverids);
                workflowHistoryInfoHelp.setApprovers(approvers);
                // 审批人角色（组）
                workflowHistoryInfoHelp.setApproversGroup(approvers);
            }
            // 流程表单历史信息
            List<HistoricDetail> historicDetails = historyService.createHistoricDetailQuery().taskId(taskId)
                                                                 .formProperties().list();
            if (null != historicDetails)
            {
                for (HistoricDetail historicDetail : historicDetails)
                {
                    HistoricFormProperty historicFormProperty = (HistoricFormProperty) historicDetail;
                    String id = historicFormProperty.getPropertyId();
                    if (StringUtils.isNotEmpty(id))
                    {
                        if (id.equals(WorkflowFormHelp.FORM_PASS))
                        {
                            workflowHistoryInfoHelp.setApproveResult(historicFormProperty.getPropertyValue());
                        }
                        if (id.equals(WorkflowFormHelp.FORM_ADVICE))
                        {
                            workflowHistoryInfoHelp.setApproveAdvice(historicFormProperty.getPropertyValue());
                        }
                    }
                }
            }
            packageHistoricTaskInstancesList.add(workflowHistoryInfoHelp);
            if(historicProcessInstances.size()>is_num){//historicProcessInstance流程啟動次數   historicTaskInstances流程節點
            	if((historicTaskInstances.size()>(numVal+1)&&historicProcessInstances.get(is_num).getStartTime().after(historicTaskInstances.get(numVal).getEndTime())
            		&&historicProcessInstances.get(is_num).getStartTime().before(historicTaskInstances.get(numVal+1).getEndTime()))
            		|| historicTaskInstances.size()==(numVal)){
        		
            	   //添加复议内容
            	   WorkflowHistoryInfoHelp	againworkflowHistoryInfoHelp= getAgainApplInfo(historicProcessInstances,is_num,workflowSearchInfoHelp.getBusinessKey());
            	   if(againworkflowHistoryInfoHelp!=null){
            		   packageHistoricTaskInstancesList.add(againworkflowHistoryInfoHelp);
                	   is_num++;
            	   }
        		}
            }
            numVal++;
        }
        return packageHistoricTaskInstancesList;
    }

    /** 处理复议审批节点问题
     *  @date 2016-10-8
     *  @author baisong
     */
	private WorkflowHistoryInfoHelp getAgainApplInfo(List<HistoricProcessInstance> historicProcessInstances,int is_num,String businessKey){
		
		HistoricProcessInstance historicProcessInstance=historicProcessInstances.get(is_num);
		
		WorkflowHistoryInfoHelp workflowHistoryInfoHelpForStart = new WorkflowHistoryInfoHelp();
	    // 流程实例的ID
	    workflowHistoryInfoHelpForStart.setProcessInstanceId(historicProcessInstance.getId());
	    // 流程任务的ID
	    workflowHistoryInfoHelpForStart.setTaskId(historicProcessInstance.getStartActivityId());
	    // 业务表单的ID
	    workflowHistoryInfoHelpForStart.setBusinessKey(historicProcessInstance.getBusinessKey());
	    // 发起人的ID,换成审批人的ID
	    workflowHistoryInfoHelpForStart.setApproverids(historicProcessInstance.getStartUserId());
	    // 发起人的Name,换成审批人的名字
	    User startUser = identityService.createUserQuery().userId(historicProcessInstance.getStartUserId())
	                                    .singleResult();
	    workflowHistoryInfoHelpForStart.setApprovers((StringUtils.isNotEmpty(startUser.getFirstName()) ? startUser.getFirstName()
	                                                                                                  : "")
	                                                 + (StringUtils.isNotEmpty(startUser.getLastName()) ? startUser.getLastName()
	                                                                                                   : ""));
	    // 发起人角色（组）
	    String startUserGroupNames = "";
	    List<Group> startUserGroups = identityService.createGroupQuery()
	                                                 .groupMember(historicProcessInstance.getStartUserId())
	                                                 .orderByGroupId().asc().list();
	    for (Group group : startUserGroups)
	    {
	        startUserGroupNames = startUserGroupNames + group.getName() + ",";
	    }
	    if(startUserGroupNames!=null&&!"".equals(startUserGroupNames)){
	    	startUserGroupNames = startUserGroupNames.substring(0, startUserGroupNames.length() - 1);	
	    }
	    workflowHistoryInfoHelpForStart.setApproversGroup(startUserGroupNames);
	    // 节点名字，可以对应单据状态
	    workflowHistoryInfoHelpForStart.setTaskName("复议申请");
	    // 审批时间
	    DateFormat simpleStartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    workflowHistoryInfoHelpForStart.setApproveTime(simpleStartDateFormat.format(historicProcessInstance.getStartTime()));
	    // 审批结果
	    workflowHistoryInfoHelpForStart.setApproveResult("复议成功");

	  
	    Map<String, Object> parMap=new HashMap<>();
    	parMap.put("wms_sys_dict_id", WmsHelp.SYS_DICT_ID_APPL_REASON);//复议原因主键
    	parMap.put("wms_cre_credit_head_id", businessKey);//单据主键
    	String advice="提交成功";
        List<Map<String,Object>> list = wmscrehousingagainapplDao.getAgainDictDataByCode(parMap);
        if(list!=null&&list.size()>is_num-1&&list.get(is_num-1)!=null&&list.get(is_num-1).get("value_meaning")!=null){
        	advice=list.get(is_num-1).get("value_meaning").toString();
        	
        }
        //审批意见
	    workflowHistoryInfoHelpForStart.setApproveAdvice(advice);
	    return workflowHistoryInfoHelpForStart;
	}
    @Override
    @Transactional(readOnly = true)
    public List<HistoricProcessInstance> findHistoricProcessInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp)
    {

        List<HistoricProcessInstance> historicProcessInstanceList = new ArrayList<HistoricProcessInstance>();
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessDefinitionKey()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.processDefinitionKey(workflowSearchInfoHelp.getProcessDefinitionKey());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessInstanceId()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.processInstanceId(workflowSearchInfoHelp.getProcessInstanceId());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getBusinessKey()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.processInstanceBusinessKey((workflowSearchInfoHelp.getBusinessKey()));
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getUserId()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.involvedUser(workflowSearchInfoHelp.getBusinessKey());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getStartUserId()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.startedBy(workflowSearchInfoHelp.getStartUserId());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getStartTime()))
        {
            // 对发起时间进行处理
            String date = workflowSearchInfoHelp.getStartTime();
            Date result = null;
            try
            {
                result = DateUtils.parseDate(date, "yyyy-MM-dd");
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            historicProcessInstanceQuery = historicProcessInstanceQuery.startedAfter(result)
                                                                       .startedBefore(DateUtils.addDays(result, 1));
        }
        if (BooleanUtils.isTrue((workflowSearchInfoHelp.isFinish())))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.finished();
        }
        if (BooleanUtils.isTrue((workflowSearchInfoHelp.isUnFinish())))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.unfinished();
        }
        historicProcessInstanceList = historicProcessInstanceQuery.orderByProcessDefinitionId().asc()
                                                                  .orderByProcessInstanceBusinessKey().desc().list();
        return historicProcessInstanceList;
    }

    @Override
    @Transactional(readOnly = true)
    public int findHistoricProcessInstancesCount(WorkflowSearchInfoHelp workflowSearchInfoHelp)
    {

        long historicProcessInstanceListCount = 0l;
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessDefinitionKey()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.processDefinitionKey(workflowSearchInfoHelp.getProcessDefinitionKey());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessInstanceId()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.processInstanceId(workflowSearchInfoHelp.getProcessInstanceId());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getBusinessKey()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.processInstanceBusinessKey((workflowSearchInfoHelp.getBusinessKey()));
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getUserId()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.involvedUser(workflowSearchInfoHelp.getBusinessKey());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getStartUserId()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.startedBy(workflowSearchInfoHelp.getStartUserId());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getStartTime()))
        {
            // 对发起时间进行处理
            String date = workflowSearchInfoHelp.getStartTime();
            Date result = null;
            try
            {
                result = DateUtils.parseDate(date, "yyyy-MM-dd");
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            historicProcessInstanceQuery = historicProcessInstanceQuery.startedAfter(result)
                                                                       .startedBefore(DateUtils.addDays(result, 1));
        }
        if (BooleanUtils.isTrue((workflowSearchInfoHelp.isFinish())))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.finished();
        }
        if (BooleanUtils.isTrue((workflowSearchInfoHelp.isUnFinish())))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.unfinished();
        }
        historicProcessInstanceListCount = historicProcessInstanceQuery.orderByProcessDefinitionId().asc()
                                                                       .orderByProcessInstanceBusinessKey().desc()
                                                                       .count();
        return Long.valueOf(historicProcessInstanceListCount).intValue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoricProcessInstance> findHistoricProcessInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp,
                                                                      int firstResult, int maxResults)
    {

        List<HistoricProcessInstance> historicProcessInstanceList = new ArrayList<HistoricProcessInstance>();
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessDefinitionKey()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.processDefinitionKey(workflowSearchInfoHelp.getProcessDefinitionKey());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getProcessInstanceId()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.processInstanceId(workflowSearchInfoHelp.getProcessInstanceId());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getBusinessKey()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.processInstanceBusinessKey((workflowSearchInfoHelp.getBusinessKey()));
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getUserId()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.involvedUser(workflowSearchInfoHelp.getBusinessKey());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getStartUserId()))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.startedBy(workflowSearchInfoHelp.getStartUserId());
        }
        if (StringUtils.isNotBlank(workflowSearchInfoHelp.getStartTime()))
        {
            // 对发起时间进行处理
            String date = workflowSearchInfoHelp.getStartTime();
            Date result = null;
            try
            {
                result = DateUtils.parseDate(date, "yyyy-MM-dd");
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            historicProcessInstanceQuery = historicProcessInstanceQuery.startedAfter(result)
                                                                       .startedBefore(DateUtils.addDays(result, 1));
        }
        if (BooleanUtils.isTrue((workflowSearchInfoHelp.isFinish())))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.finished();
        }
        if (BooleanUtils.isTrue((workflowSearchInfoHelp.isUnFinish())))
        {
            historicProcessInstanceQuery = historicProcessInstanceQuery.unfinished();
        }
        historicProcessInstanceList = historicProcessInstanceQuery.orderByProcessDefinitionId().asc()
                                                                  .orderByProcessInstanceBusinessKey().desc()
                                                                  .listPage(firstResult, maxResults);
        return historicProcessInstanceList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowInfoHelp> findPackageHistoricProcessInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp)
    {

        List<HistoricProcessInstance> historicProcessInstanceList = findHistoricProcessInstances(workflowSearchInfoHelp);
        return packageProcessInfo(historicProcessInstanceList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowInfoHelp> findPackageHistoricProcessInstances(WorkflowSearchInfoHelp workflowSearchInfoHelp,
                                                                      int firstResult, int maxResults)
    {

        List<HistoricProcessInstance> historicProcessInstanceList = findHistoricProcessInstances(workflowSearchInfoHelp,
                                                                                                 firstResult,
                                                                                                 maxResults);
        return packageProcessInfo(historicProcessInstanceList);
    }

    private List<WorkflowInfoHelp> packageProcessInfo(List<HistoricProcessInstance> historicProcessInstanceList)
    {
        List<WorkflowInfoHelp> packageToWorkflowInfoHelpList = new ArrayList<WorkflowInfoHelp>();
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList)
        {
            // 就是流程实例的ID
            String processInstanceId = historicProcessInstance.getId();

            WorkflowInfoHelp workflowInfoHelp = new WorkflowInfoHelp();

            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                                                                   .processDefinitionId(historicProcessInstance.getProcessDefinitionId())
                                                                   .singleResult();
            // 业务类型（流程定义的KEY）
            workflowInfoHelp.setProcessDefinitionKey(processDefinition.getKey());
            // 流程定义的Name
            workflowInfoHelp.setProcessDefinitionName(processDefinition.getName());
            // 流程实例的ID
            workflowInfoHelp.setProcessInstanceId(processInstanceId);

            List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
                                                                                .processInstanceId(processInstanceId)
                                                                                .unfinished().list();
            if (null != historicTaskInstanceList)
            {
                String taskId = "";
                String taskName = "";
                String assignee = "";
                String approverids = "";
                String approvers = "";
                for (HistoricTaskInstance unFinishHistoricTaskInstance : historicTaskInstanceList)
                {

                    // 指定的任务分配人
                    assignee = unFinishHistoricTaskInstance.getAssignee();

                    // 当前审批人
                    if (StringUtils.isNotEmpty(assignee))
                    {
                        approverids = assignee;
                        // 当前审批人的名字
                        User user1 = identityService.createUserQuery().userId(assignee).singleResult();
                        approvers = (StringUtils.isNotEmpty(user1.getFirstName()) ? user1.getFirstName() : "")
                                    + (StringUtils.isNotEmpty(user1.getLastName()) ? user1.getLastName() : "");
                    }
                    else
                    {
                        List<HistoricIdentityLink> identityLinks = historyService.getHistoricIdentityLinksForTask(unFinishHistoricTaskInstance.getId());
                        for (HistoricIdentityLink identityLink : identityLinks)
                        {
                            // 处理候选人。。。
                            String userId = identityLink.getUserId();
                            if (StringUtils.isNotEmpty(userId))
                            {
                                approverids = approverids + userId + ",";
                                User user = identityService.createUserQuery().userId(userId).singleResult();
                                approvers = approvers
                                            + ((StringUtils.isNotEmpty(user.getFirstName()) ? user.getFirstName() : "") + (StringUtils.isNotEmpty(user.getLastName()) ? user.getLastName()
                                                                                                                                                                     : ""))
                                            + ",";
                            }

                            // 处理候选组
                            String identityGroupId = identityLink.getGroupId();
                            if (StringUtils.isNotEmpty(identityGroupId))
                            {
                                // String groupName =
                                // identityService.createGroupQuery().groupId(
                                // identityGroupId ).singleResult().getName();
                                // approverids=approverids + identityGroupId +
                                // ",";
                                // approvers = approvers + groupName + ",";
                                List<User> userList = identityService.createUserQuery().memberOfGroup(identityGroupId)
                                                                     .list();
                                if (null != userList)
                                {
                                    for (User user : userList)
                                    {
                                        approverids = approverids + user.getId() + ",";
                                        approvers = approvers
                                                    + ((StringUtils.isNotEmpty(user.getFirstName()) ? user.getFirstName()
                                                                                                   : "") + (StringUtils.isNotEmpty(user.getLastName()) ? user.getLastName()
                                                                                                                                                      : ""))
                                                    + ",";
                                    }
                                }
                            }
                        }
                        // 处理for (HistoricIdentityLink identityLink :
                        // identityLinks)所产生的多余逗号
                        if (StringUtils.isNotEmpty(approverids))
                        {
                            approverids = approverids.substring(0, approverids.length() - 1);
                            approvers = approvers.substring(0, approvers.length() - 1);
                        }

                    }

                    // 流程任务的ID
                    taskId = taskId + unFinishHistoricTaskInstance.getId() + ",";
                    // 节点名字
                    taskName = taskName + unFinishHistoricTaskInstance.getName() + ",";
                    assignee = assignee + ",";
                    approverids = approverids + ",";
                    approvers = approvers + ",";

                }// 任务for循环结束

                if (StringUtils.isNotEmpty(taskId))
                {
                    taskId = taskId.substring(0, taskId.length() - 1);
                    taskName = taskName.substring(0, taskName.length() - 1);
                }
                if (StringUtils.isNotEmpty(assignee))
                {
                    assignee = assignee.substring(0, assignee.length() - 1);
                }
                if (StringUtils.isNotEmpty(approverids))
                {
                    approverids = approverids.substring(0, approverids.length() - 1);
                    approvers = approvers.substring(0, approvers.length() - 1);
                }

                workflowInfoHelp.setTaskId(taskId);
                workflowInfoHelp.setTaskName(taskName);
                workflowInfoHelp.setAssignee(assignee);
                workflowInfoHelp.setApproverids(approverids);
                workflowInfoHelp.setApprovers(approvers);
            }
            // 业务表单的ID
            String businessKey = historicProcessInstance.getBusinessKey();
            workflowInfoHelp.setBusinessKey(businessKey);
            // 发起人的ID,可以对应录入人的ID
            String startUserId = historicProcessInstance.getStartUserId();
            workflowInfoHelp.setStartUserId(startUserId);

            // 流程发起时间
            Date startTime = historicProcessInstance.getStartTime();
            String startTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
            workflowInfoHelp.setStartTime(startTimeString);

            // 发起人的Name,可以对应录入人的名字
            User startUser = identityService.createUserQuery().userId(startUserId).singleResult();
            workflowInfoHelp.setStartUser((StringUtils.isNotEmpty(startUser.getFirstName()) ? startUser.getFirstName()
                                                                                           : "")
                                          + (StringUtils.isNotEmpty(startUser.getLastName()) ? startUser.getLastName()
                                                                                            : ""));
            // 流程状态
            workflowInfoHelp.setProcessStatus((null == historicProcessInstance.getEndTime()) ? WorkflowConstantHelp.PROCESSSTATUS_RUNNING
                                                                                            : WorkflowConstantHelp.PROCESSSTATUS_FINISH);
            packageToWorkflowInfoHelpList.add(workflowInfoHelp);
        }
        return packageToWorkflowInfoHelpList;
    }

    @Override
    @Transactional(readOnly = true)
    public Object findStartForm(String processDefinitionId) throws Exception
    {

        // 根据流程定义ID读取外置表单
        Object startForm = formService.getRenderedStartForm(processDefinitionId);
        return startForm;
    }

    @Override
    public void submitStartFormAndStartProcessInstance(String userId, String processDefinitionId,
                                                       Map<String, String> formPropertiesMap)
    {

        identityService.setAuthenticatedUserId(userId);
        formService.submitStartFormData(processDefinitionId, formPropertiesMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Object findTaskForm(String taskId) throws Exception
    {

        Object renderedTaskForm = formService.getRenderedTaskForm(taskId);
        return renderedTaskForm;
    }

    @Override
    public void completeTask(String userId, String taskId, Map<String, String> formPropertiesMap)
    {
        // 系统没有先认领的概念，所以先认领吧。。。
        claimTask(userId, taskId);
        identityService.setAuthenticatedUserId(userId);
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        DelegationState delegationState = task.getDelegationState();
        if (null != delegationState && delegationState.equals(DelegationState.PENDING))
        {
            Map<String, Object> map = new HashMap<String, Object>();
            // Set<String> keySet = formPropertiesMap.keySet();
            // for(String key : keySet){
            // String value = formPropertiesMap.get(key);
            // map.put(key, value);
            // }
            Set<Entry<String, String>> entrySet = formPropertiesMap.entrySet();
            for (Entry<String, String> entry : entrySet)
            {
                map.put(entry.getKey(), entry.getValue());
            }
            taskService.resolveTask(taskId, map);
            formService.submitTaskFormData(taskId, formPropertiesMap);
        }
        else
        {
            formService.submitTaskFormData(taskId, formPropertiesMap);
        }
    }

    @Override
    public void claimTask(String userId, String taskId)
    {
        identityService.setAuthenticatedUserId(userId);
        taskService.claim(taskId, userId);
    }

    @Override
    public void completeTask(String userId, String taskId)
    {
        identityService.setAuthenticatedUserId(userId);
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        DelegationState delegationState = task.getDelegationState();
        if (null != delegationState && delegationState.equals(DelegationState.PENDING))
        {
            taskService.resolveTask(taskId);
        }
        else
        {
            taskService.complete(taskId);
        }
    }

    /**
     * 查询流程定义对象
     * 
     * @param processDefinitionId 流程定义ID
     * @return
     */
    @Transactional(readOnly = true)
    protected ProcessDefinition getProcessDefinition(String processDefinitionId)
    {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                                                               .processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }

    @Override
    @Transactional(readOnly = true)
    public long findProcessDefinitionListCount()
    {

        long processDefinitionListCount = repositoryService.createProcessDefinitionQuery().count();
        return processDefinitionListCount;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProcessDefinition> findProcessDefinitionListPage(int firstResult, int maxResults)
    {

        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
                                                                         .orderByDeploymentId().desc()
                                                                         .listPage(firstResult, maxResults);
        return processDefinitionList;
    }

    @Override
    public void deployProcessDefinition(MultipartFile file)
    {

        String fileName = file.getOriginalFilename();
        InputStream fileInputStream = null;
        try
        {
            fileInputStream = file.getInputStream();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String extension = FilenameUtils.getExtension(fileName);
        if (extension.equals("zip") || extension.equals("bar"))
        {
            ZipInputStream zip = new ZipInputStream(fileInputStream);
            repositoryService.createDeployment().addZipInputStream(zip).deploy();
        }
        else
        {
            repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Object findTaskVariable(String taskId, String variableName)
    {
        return taskService.getVariable(taskId, variableName);
    }

    @Override
    public void setTaskVariable(String taskId, String variableName, Object value)
    {
        taskService.setVariable(taskId, variableName, value);
    }

    @Override
    public void delegateTask(String taskId, String userId)
    {
        taskService.delegateTask(taskId, userId);
    }
    @Override
    public Task getTaskInfo(String taskId){
    	Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		return task;
    }
    /**
     * 根据业务信息查询task信息
     */
    @Override
    public Task getTaskInfobyKey(String businessKey,String taskName,String processDefinitionKey){
    	Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).taskName(taskName).processDefinitionKey(processDefinitionKey).singleResult();
		return task;
    }


    /**
     * 
     * @Title: getTaskListInfo
     * @Description: TODO(根据业务主键获取task信息 list)
     * @param userId
     * @param taskName
     * @param processDefinitionKey
     * @return 
     * @author: baisong
     * @time:2016年12月20日 下午1:30:59
     * @see com.zx.emanage.workflow.service.IWorkflowService#getTaskListInfo(java.lang.String, java.lang.String, java.lang.String)
     * history:
     * 1、2016年12月20日 baisong 创建方法
     */
    @Override
    public List<Task> getTaskListInfo(String userId, String taskName, String processDefinitionKey)
    {
        List<Task> tasks = taskService.createTaskQuery().taskName(taskName).processDefinitionKey(processDefinitionKey).taskCandidateOrAssigned(userId).list();
        return tasks;
    }

    /**
     * 
     * @Title: getTaskInfo
     * @Description: TODO(获取task信息)
     * @param workflowSearchInfoHelp
     * @return 
     * @author: baisong
     * @time:2016年12月20日 下午1:28:23
     * @see com.zx.emanage.workflow.service.IWorkflowService#getTaskInfo(com.zx.emanage.workflow.util.WorkflowSearchInfoHelp)
     * history:
     * 1、2016年12月20日 baisong 创建方法
     */
    public Task getTaskInfo(WorkflowSearchInfoHelp workflowSearchInfoHelp)
    {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(workflowSearchInfoHelp.getBusinessKey()).taskName(workflowSearchInfoHelp.getTaskName()).singleResult();
        return task;
    }

    /**
     * @Title: getTaskFormData
     * @Description: 根据taskid获取form key 值
     * @param taskId
     * @return String
     * @author: baisong
     * @time:2016年12月16日 下午4:21:19
     * history:
     * 1、2016年12月16日 baisong 创建方法
     */
    public String getTaskFormData(String taskId,String objId)
    {
        TaskFormData formData = formService.getTaskFormData(taskId);
        String formKey = formData.getFormKey();
        StringBuffer strb = new StringBuffer();
        strb.append(formKey);
        // 判断业务id是否为空
        // objId为业务对象Id
        // taskId为任务Id
        // 这样就可以在任务表单获取到想要的信息
        if (objId != null && !"".equals(objId))
        {
            strb.append("?id=" + objId + "&taskId=" + taskId);
        }
        return strb.toString();
    }
}
