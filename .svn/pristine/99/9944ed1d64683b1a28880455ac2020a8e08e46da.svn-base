package com.zx.emanage.workflow.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.Page;
import com.zx.emanage.workflow.util.PageUtil;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 流程管理控制器
 * 
 * @author HenryYan
 */
@Controller
@RequestMapping(value = "/workflow")
public class ActivitiController
{

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected static Map<String, ProcessDefinition> PROCESS_DEFINITION_CACHE = new HashMap<String, ProcessDefinition>();

    @Autowired
    ProcessEngineFactoryBean processEngine;

    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private FormService formService;

    @Autowired
    private IdentityService identityService;

    /**
     * 流程定义列表
     * 
     * @return
     */
    @RequestMapping(value = "/process-list")
    public ModelAndView processList(HttpServletRequest request)
    {

        ModelAndView mav = new ModelAndView("workflow/process-list");

        /*
         * 保存两个对象，一个是ProcessDefinition（流程定义），一个是Deployment（流程部署）
         */
        List<Object[]> objects = new ArrayList<Object[]>();

        Page<Object[]> page = new Page<Object[]>(PageUtil.PAGE_SIZE);
        int[] pageParams = PageUtil.init(page, request);
        List<ProcessDefinition> processDefinitionList = workflowService.findProcessDefinitionListPage(pageParams[0],
                                                                                                      pageParams[1]);
        for (ProcessDefinition processDefinition : processDefinitionList)
        {
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            objects.add(new Object[] { processDefinition, deployment });
        }

        page.setTotalCount(workflowService.findProcessDefinitionListCount());
        page.setResult(objects);
        mav.addObject("page", page);

        return mav;
    }

    /**
     * 发起流程
     * 
     * @return
     */
    @RequestMapping(value = "/startP")
    public String startP(HttpServletRequest request)
    {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        // workflowService1.startWorkflow(String.valueOf(user.getUserId()),
        // "creditProcess", "1", new HashMap<String,Object>());
        // workflowService1.findTodoTasks("1001", "creditProcess", "贷款复核");
        // workflowService.startWorkflow( "1101", "creditProcess", "7", new
        // HashMap() );
        // workflowService.startWorkflow( "1102", "creditProcess", "7", new
        // HashMap() );
        // workflowService.startWorkflow( "1103", "creditProcess", "7", new
        // HashMap() );
        Map m = new HashMap();
        m.put("vice_general_id", "185");
        // m.put("general_id", "185");
        m.put("end_of_date", "2016-12-31T12:00:00");
        m.put("userId", user.getUserId().toString());
        // m.put(WorkflowConstantHelp.ADDUSERS, "1106");
        // workflowService.startWorkflow( "1102", "housingLoanProcess", "57",
        // m);
        // workflowService.startWorkflow(user.getUserId().toString(),
        // "financialRedemptionProcess", "100", m);
        // workflowService.startWorkflow(user.getUserId().toString(),
        // "financialRedemptionProcess", "101", m);
        // workflowService.startWorkflow(user.getUserId().toString(),
        // "financialRedemptionProcess", "102", m);
        // workflowService.startWorkflow(user.getUserId().toString(),
        // "financialRedemptionProcess", "103", m);
        // workflowService.startWorkflow(user.getUserId().toString(),
        // "financialRedemptionProcess", "104", m);
        workflowService.startWorkflow(user.getUserId().toString(), "inveSalarySet", "1", m);
        return "redirect:/workflow/process-list";
    }

    /**
     * 读取启动流程的表单字段
     */
    @RequestMapping(value = "get-form/start/{processDefinitionId}")
    @ResponseBody
    public Object findStartForm(@PathVariable("processDefinitionId")
    String processDefinitionId) throws Exception
    {

        // 根据流程定义ID读取外置表单
        Object startForm = workflowService.findStartForm(processDefinitionId);

        return startForm;
    }

    /**
     * 读取Task的表单字段
     */
    @RequestMapping(value = "get-form/task/{taskId}/{processInstanceId}/{processDefinitionKey}")
    public ModelAndView findTaskForm(@PathVariable("taskId")
    String taskId, @PathVariable("processInstanceId")
    String processInstanceId, @PathVariable("processDefinitionKey")
    String processDefinitionKey, HttpServletRequest request) throws Exception
    {

        // Object renderedTaskForm = formService.getRenderedTaskForm(taskId);
        ModelAndView mav = new ModelAndView("workflow/commonForm");
        Object renderedTaskForm = workflowService.findTaskForm(taskId);
        // 决定页面有没有继续审批按钮
        String iscontinueenable = (String) workflowService.findTaskVariable(taskId, "iscontinueenable");
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(processDefinitionKey);
        workflowSearchInfoHelp.setProcessInstanceId(processInstanceId);
        // workflowSearchInfoHelp.setBusinessKey( "7" );
        workflowSearchInfoHelp.setFinish(true);
        List<WorkflowHistoryInfoHelp> workflowHistoryInfoHelps = workflowService.findPackageHistoricTaskInstances(workflowSearchInfoHelp);
        mav.addObject("taskId", taskId);
        // 值放入页面
        mav.addObject("iscontinueenable", iscontinueenable);
        mav.addObject("renderedTaskForm", renderedTaskForm);
        mav.addObject("workflowHistoryInfoHelps", workflowHistoryInfoHelps);
        return mav;
    }

    /**
     * 提交task的并保存form
     */
    @RequestMapping(value = "task/complete/{taskId}")
    @SuppressWarnings("unchecked")
    public String completeTask(@PathVariable("taskId")
    String taskId, HttpServletRequest request)
    {

        Map<String, String> formProperties = new HashMap<String, String>();

        // 从request中读取参数然后转换
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
        for (Entry<String, String[]> entry : entrySet)
        {
            String key = entry.getKey();

            /*
             * 参数结构：fq_reason，用_分割 fp的意思是form paremeter 最后一个是属性名称
             */
            // if (StringUtils.defaultString(key).startsWith("fp_")) {
            // String[] paramSplit = key.split("_");
            // formProperties.put(paramSplit[1], entry.getValue()[0]);
            // }
            formProperties.put(key, entry.getValue()[0]);
        }
        // formProperties.put(WorkflowConstantHelp.ADDUSERS, "1105");
        logger.debug("start form parameters: {}", formProperties);
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        // workflowService.setTaskVariable(taskId,
        // WorkflowConstantHelp.ADDUSERS,"1105" );
        workflowService.completeTask(String.valueOf(user.getUserId()), taskId, formProperties);

        return "redirect:/workflow/taskList";
    }

    /**
     * 委派task
     */
    @RequestMapping(value = "task/delegateTask/{taskId}")
    @SuppressWarnings("unchecked")
    public String delegateTask(@PathVariable("taskId")
    String taskId, HttpServletRequest request)
    {
        workflowService.delegateTask(taskId, "1102");

        return "redirect:/workflow/taskList";
    }

    /**
     * 办理任务 （继续审批按钮）
     */
    @RequestMapping(value = "task/completeNoForm/{taskId}")
    @SuppressWarnings("unchecked")
    public String completeTaskNoForm(@PathVariable("taskId")
    String taskId, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        // 设置继续审批的流程变量
        workflowService.setTaskVariable(taskId, "iscontinue", "true");
        Map<String, String> formProperties = new HashMap<String, String>();

        // 从request中读取参数然后转换
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
        for (Entry<String, String[]> entry : entrySet)
        {
            String key = entry.getKey();

            /*
             * 参数结构：fq_reason，用_分割 fp的意思是form paremeter 最后一个是属性名称
             */
            // if (StringUtils.defaultString(key).startsWith("fp_")) {
            // String[] paramSplit = key.split("_");
            // formProperties.put(paramSplit[1], entry.getValue()[0]);
            // }
            formProperties.put(key, entry.getValue()[0]);
        }
        workflowService.completeTask(String.valueOf(user.getUserId()), taskId, formProperties);
        return "redirect:/workflow/taskList";
    }

    /**
     * 读取启动流程的表单字段
     */
    @RequestMapping(value = "start-process/{processDefinitionId}")
    @SuppressWarnings("unchecked")
    public String submitStartFormAndStartProcessInstance(@PathVariable("processDefinitionId")
    String processDefinitionId, HttpServletRequest request)
    {

        Map<String, String> formProperties = new HashMap<String, String>();

        // 从request中读取参数然后转换
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
        for (Entry<String, String[]> entry : entrySet)
        {
            String key = entry.getKey();

            // fp_的意思是form paremeter
            // if( StringUtils.defaultString( key ).startsWith( "fp_" ) ) {
            // formProperties.put( key.split( "_" )[ 1 ], entry.getValue()[ 0 ]
            // );
            // }
            formProperties.put(key, entry.getValue()[0]);
        }

        logger.debug("start form parameters: {}", formProperties);

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        // 用户未登录不能操作，实际应用使用权限框架实现，例如Spring Security、Shiro等
        // if( user == null || StringUtils.isBlank(
        // String.valueOf(user.getUserId() )) ) {
        // return "redirect:/login?timeout=true";
        // }
        // try {
        identityService.setAuthenticatedUserId(String.valueOf(user.getUserId()));
        workflowService.submitStartFormAndStartProcessInstance(String.valueOf(user.getUserId()), processDefinitionId,
                                                               formProperties);
        // logger.debug( "start a processinstance: {}", processInstance );

        // } finally {
        // identityService.setAuthenticatedUserId( null );
        // }

        return "redirect:/workflow/taskList";
    }

    /**
     * 任务列表
     * 
     * @param leave
     */
    @RequestMapping(value = "/taskList")
    public ModelAndView taskList(HttpSession session, HttpServletRequest request)
    {

        ModelAndView mav = new ModelAndView("workflow/taskList");
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        String userId = String.valueOf(user.getUserId());
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(request.getParameter("processDefinitionKey"));
        workflowSearchInfoHelp.setUserId(userId);
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        mav.addObject("workflowInfoHelps", workflowInfoHelps);
        // mav.addObject( "processDefinitionKey", request.getParameter(
        // "processDefinitionKey" ) );
        return mav;
    }

    /**
     * 我发起的流程列表
     * 
     * @param leave
     */
    @RequestMapping(value = "/startProcessList")
    public ModelAndView startProcessList(HttpSession session, HttpServletRequest request)
    {

        ModelAndView mav = new ModelAndView("workflow/processInfoList");
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        String userId = String.valueOf(user.getUserId());
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setStartUserId(userId);
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageHistoricProcessInstances(workflowSearchInfoHelp);
        mav.addObject("workflowInfoHelps", workflowInfoHelps);
        // mav.addObject( "processDefinitionKey", request.getParameter(
        // "processDefinitionKey" ) );
        return mav;
    }

    /**
     * 我参与的流程列表
     * 
     * @param leave
     */
    @RequestMapping(value = "/involveProcessList")
    public ModelAndView involveProcessList(HttpSession session, HttpServletRequest request)
    {

        ModelAndView mav = new ModelAndView("workflow/processInfoList");
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        String userId = String.valueOf(user.getUserId());
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setUserId(userId);
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageHistoricProcessInstances(workflowSearchInfoHelp);
        mav.addObject("workflowInfoHelps", workflowInfoHelps);
        // mav.addObject( "processDefinitionKey", request.getParameter(
        // "processDefinitionKey" ) );
        return mav;
    }

    /**
     * 我参与的未完成流程列表
     * 
     * @param leave
     */
    @RequestMapping(value = "/unfinishProcessList")
    public ModelAndView unfinishProcessList(HttpSession session, HttpServletRequest request)
    {

        ModelAndView mav = new ModelAndView("workflow/processInfoList");
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        String userId = String.valueOf(user.getUserId());
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setUserId(userId);
        workflowSearchInfoHelp.setUnFinish(true);
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageHistoricProcessInstances(workflowSearchInfoHelp);
        mav.addObject("workflowInfoHelps", workflowInfoHelps);
        // mav.addObject( "processDefinitionKey", request.getParameter(
        // "processDefinitionKey" ) );
        return mav;
    }

    /**
     * 我参与的已完成流程列表
     * 
     * @param leave
     */
    @RequestMapping(value = "/finishProcessList")
    public ModelAndView finishProcessList(HttpSession session, HttpServletRequest request)
    {

        ModelAndView mav = new ModelAndView("workflow/processInfoList");
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        String userId = String.valueOf(user.getUserId());
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setUserId(userId);
        workflowSearchInfoHelp.setFinish(true);
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageHistoricProcessInstances(workflowSearchInfoHelp);
        mav.addObject("workflowInfoHelps", workflowInfoHelps);
        // mav.addObject( "processDefinitionKey", request.getParameter(
        // "processDefinitionKey" ) );
        return mav;
    }

    /**
     * 签收任务
     * 
     * @param leave
     */
    @RequestMapping(value = "/claimTask")
    public String claimTask(HttpSession session, HttpServletRequest request)
    {

        String taskId = request.getParameter("taskId");
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        String userId = String.valueOf(user.getUserId());
        workflowService.claimTask(userId, taskId);
        return "redirect:/workflow/taskList";
    }

    /**
     * 流程定义列表
     * 
     * @return
     */
    @RequestMapping(value = "/graph")
    public ModelAndView graph(HttpServletRequest request)
    {

        ModelAndView mav = new ModelAndView("workflow/graph");
        String processInstanceId = request.getParameter("processInstanceId");
        request.setAttribute("processInstanceId", processInstanceId);
        return mav;
    }

    /**
     * 读取资源，通过部署ID
     * 
     * @param processDefinitionId 流程定义
     * @param resourceType 资源类型(xml|image)
     * @throws Exception
     */
    @RequestMapping(value = "/resource/read")
    public void loadByDeployment(@RequestParam("processDefinitionId")
    String processDefinitionId, @RequestParam("resourceType")
    String resourceType, HttpServletResponse response) throws Exception
    {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                                                               .processDefinitionId(processDefinitionId).singleResult();
        String resourceName = "";
        if (resourceType.equals("image"))
        {
            resourceName = processDefinition.getDiagramResourceName();
        }
        else if (resourceType.equals("xml"))
        {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                                                                             resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1)
        {
            response.getOutputStream().write(b, 0, len);
        }
    }

    /**
     * 读取资源，通过流程ID
     * 
     * @param resourceType 资源类型(xml|image)
     * @param processInstanceId 流程实例ID
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/resource/process-instance")
    public void loadByProcessInstance(@RequestParam("type")
    String resourceType, @RequestParam("pid")
    String processInstanceId, HttpServletResponse response) throws Exception
    {

        InputStream resourceAsStream = null;
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                                                        .processInstanceId(processInstanceId).singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                                                               .processDefinitionId(processInstance.getProcessDefinitionId())
                                                               .singleResult();

        String resourceName = "";
        if (resourceType.equals("image"))
        {
            resourceName = processDefinition.getDiagramResourceName();
        }
        else if (resourceType.equals("xml"))
        {
            resourceName = processDefinition.getResourceName();
        }
        resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1)
        {
            response.getOutputStream().write(b, 0, len);
        }
    }

    /**
     * 删除部署的流程，级联删除流程实例
     * 
     * @param deploymentId 流程部署ID
     */
    @RequestMapping(value = "/process/delete")
    public String delete(@RequestParam("deploymentId")
    String deploymentId)
    {

        repositoryService.deleteDeployment(deploymentId, true);
        return "redirect:/workflow/process-list";
    }

    // /**
    // * 输出跟踪流程信息
    // *
    // * @param processInstanceId
    // * @return
    // * @throws Exception
    // */
    // @RequestMapping( value = "/process/trace" )
    // @ResponseBody
    // public List<Map<String, Object>> traceProcess( @RequestParam( "pid" )
    // String processInstanceId ) throws Exception {
    //
    // List<Map<String, Object>> activityInfos = workflowService.traceProcess(
    // processInstanceId );
    // return activityInfos;
    // }

    /**
     * 读取带跟踪的图片
     */
    @RequestMapping(value = "/process/trace/auto/{executionId}")
    public void readResource(@PathVariable("executionId")
    String executionId, HttpServletResponse response) throws Exception
    {

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(executionId)
                                                        .singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(executionId);
        // 不使用spring请使用下面的两行代码
        // ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl)
        // ProcessEngines.getDefaultProcessEngine();
        // Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());

        // 使用spring注入引擎请使用下面的这行代码
        Context.setProcessEngineConfiguration(processEngine.getProcessEngineConfiguration());

        InputStream imageStream = ProcessDiagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1)
        {
            response.getOutputStream().write(b, 0, len);
        }
    }

    @RequestMapping(value = "/deploy")
    // public String deploy(@Value("#{APP_PROPERTIES['export.diagram.path']}")
    // String exportDir, @RequestParam(value = "file", required = false)
    // MultipartFile file) {
    public String deploy(@RequestParam(value = "file", required = false)
    MultipartFile file)
    {
        try
        {
            workflowService.deployProcessDefinition(file);

            // List<ProcessDefinition> list =
            // repositoryService.createProcessDefinitionQuery().deploymentId(
            // deployment.getId() ).list();
            // for (ProcessDefinition processDefinition : list) {
            // WorkflowUtils.exportDiagramToFile(repositoryService,
            // processDefinition, exportDir);
            // }

        }
        catch (Exception e)
        {
            logger.error("error on deploy process, because of file input stream", e);
        }

        return "redirect:/workflow/process-list";
    }

    // @RequestMapping( value =
    // "/process/convert-to-model/{processDefinitionId}" )
    // public String convertToModel( @PathVariable( "processDefinitionId" )
    // String processDefinitionId ) throws UnsupportedEncodingException,
    // XMLStreamException {
    //
    // ProcessDefinition processDefinition =
    // repositoryService.createProcessDefinitionQuery().processDefinitionId(
    // processDefinitionId ).singleResult();
    // InputStream bpmnStream = repositoryService.getResourceAsStream(
    // processDefinition.getDeploymentId(), processDefinition.getResourceName()
    // );
    // XMLInputFactory xif = XMLInputFactory.newInstance();
    // InputStreamReader in = new InputStreamReader( bpmnStream, "UTF-8" );
    // XMLStreamReader xtr = xif.createXMLStreamReader( in );
    // BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel( xtr );
    //
    // BpmnJsonConverter converter = new BpmnJsonConverter();
    // ObjectNode modelNode = converter.convertToJson( bpmnModel );
    // Model modelData = repositoryService.newModel();
    // modelData.setKey( processDefinition.getKey() );
    // modelData.setName( processDefinition.getResourceName() );
    // modelData.setCategory( processDefinition.getDeploymentId() );
    //
    // ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
    // modelObjectNode.put( ModelDataJsonConstants.MODEL_NAME,
    // processDefinition.getName() );
    // modelObjectNode.put( ModelDataJsonConstants.MODEL_REVISION, 1 );
    // modelObjectNode.put( ModelDataJsonConstants.MODEL_DESCRIPTION,
    // processDefinition.getDescription() );
    // modelData.setMetaInfo( modelObjectNode.toString() );
    //
    // repositoryService.saveModel( modelData );
    //
    // repositoryService.addModelEditorSource( modelData.getId(),
    // modelNode.toString().getBytes( "utf-8" ) );
    //
    // return "redirect:/workflow/model/list";
    // }

    // /**
    // * 待办任务--Portlet
    // */
    // @RequestMapping( value = "/task/todo/list" )
    // @ResponseBody
    // public List<Map<String, Object>> todoList( HttpSession session ) throws
    // Exception {
    //
    // UserBean user = ( UserBean )session.getAttribute( GlobalVal.USER_SESSION
    // ); // 获取登陆人信息
    // List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    // SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
    //
    // // 已经签收的任务
    // List<Task> todoList = taskService.createTaskQuery().taskAssignee(
    // String.valueOf(user.getUserId() ) ).active().list();
    // for( Task task : todoList ) {
    // String processDefinitionId = task.getProcessDefinitionId();
    // ProcessDefinition processDefinition = getProcessDefinition(
    // processDefinitionId );
    //
    // Map<String, Object> singleTask = packageTaskInfo( sdf, task,
    // processDefinition );
    // singleTask.put( "status", "todo" );
    // result.add( singleTask );
    // }
    //
    // // 等待签收的任务
    // List<Task> toClaimList = taskService.createTaskQuery().taskCandidateUser(
    // String.valueOf(user.getUserId() ) ).active().list();
    // for( Task task : toClaimList ) {
    // String processDefinitionId = task.getProcessDefinitionId();
    // ProcessDefinition processDefinition = getProcessDefinition(
    // processDefinitionId );
    //
    // Map<String, Object> singleTask = packageTaskInfo( sdf, task,
    // processDefinition );
    // singleTask.put( "status", "claim" );
    // result.add( singleTask );
    // }
    //
    // return result;
    // }
    //
    //
    // private Map<String, Object> packageTaskInfo( SimpleDateFormat sdf, Task
    // task, ProcessDefinition processDefinition ) {
    //
    // Map<String, Object> singleTask = new HashMap<String, Object>();
    // singleTask.put( "id", task.getId() );
    // singleTask.put( "name", task.getName() );
    // singleTask.put( "createTime", sdf.format( task.getCreateTime() ) );
    // singleTask.put( "pdname", processDefinition.getName() );
    // singleTask.put( "pdversion", processDefinition.getVersion() );
    // singleTask.put( "pid", task.getProcessInstanceId() );
    // return singleTask;
    // }

    // private ProcessDefinition getProcessDefinition( String
    // processDefinitionId ) {
    //
    // ProcessDefinition processDefinition = PROCESS_DEFINITION_CACHE.get(
    // processDefinitionId );
    // if( processDefinition == null ) {
    // processDefinition =
    // repositoryService.createProcessDefinitionQuery().processDefinitionId(
    // processDefinitionId ).singleResult();
    // PROCESS_DEFINITION_CACHE.put( processDefinitionId, processDefinition );
    // }
    // return processDefinition;
    // }

    /**
     * 挂起、激活流程实例
     */
    @RequestMapping(value = "processdefinition/update/{state}/{processDefinitionId}")
    public String updateState(@PathVariable("state")
    String state, @PathVariable("processDefinitionId")
    String processDefinitionId
    // RedirectAttributes redirectAttributes
    )
    {
        if (state.equals("active"))
        {
            // redirectAttributes.addFlashAttribute("message", "已激活ID为[" +
            // processDefinitionId + "]的流程定义。");
            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
        }
        else if (state.equals("suspend"))
        {
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
            // redirectAttributes.addFlashAttribute("message", "已挂起ID为[" +
            // processDefinitionId + "]的流程定义。");
        }
        return "redirect:/workflow/process-list";
    }

}