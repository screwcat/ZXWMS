package com.zx.emanage.loanappro.service.impl;

/**
 * 版权所有：版权所有(C) 2014，卓信财富
 * 文件名称: WmsCreCreditApproServiceImpl.java
 * 系统名称：WMS
 * 模块名称：
 * 完成日期：
 * 作    者：
 * 内容摘要：  
 * 
 * 文件调用：
 * 修改记录：01
 * 修改时间：20141121
 * 修 改 人: hancd
 * 修改内容：增加信贷面签 划分区域进行显示
 * 关联BUG：
 * 修改方法：在parmMap中传入登录人的渔区编码和单据录单员的区域编码进行对比
 * 
 * 修改记录：02
 * 修改时间：20141203
 * 修 改 人: hancd
 * 修改内容：增加筛选条件 城市 和 单据状态
 * 关联BUG：
 * 修改方法：在parmMap中传入城市或单据状态
 * 
 * 修改记录：03
 * 修改时间：20141212
 * 修 改 人: hancd
 * 修改内容：终审查询中增加对终审金额，面签金额，合同金额的查询功能以及相应数量的查询
 * 关联BUG：
 * 修改方法：修改相应的SQL语句
 * 
 * 修改记录：04
 * 修改时间：20141222
 * 修 改 人: hancd
 * 修改内容：增加终审 审批查询 筛选条件  申请时间区间,贷款金额区间,是否逾期,放款金额区间
 * 关联BUG：
 * 修改方法：修改相应的SQL语句以及页面处理逻辑
 * 
 * 修改记录：05
 * 修改时间：2015-01-29
 * 修改人：hancd
 * 修改内容：增加信贷管理  初审面签  针对验点审核
 * 关联BUG:
 * 修改方法:
 * 
 * 修改记录：06
 * 修改时间：2015-02-10
 * 修改人：hancd
 * 修改内容：针对终审管理>审批查询  对验点 初面  信审 页面提示细化
 * 关联BUG:
 * 修改方法:更具工作流读取处于验点环节上的所有单据ID，进而区分
 */

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.typeconverter.Convert;
import jodd.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.service.IWmsCarLoanWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.persist.WmsCreCreditApproDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditServiceTypeDao;
import com.zx.emanage.loanappro.service.IWmsCreCreditApproService;
import com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO;
import com.zx.emanage.loanfina.persist.WmsFinaCreLoanAppDao;
import com.zx.emanage.loanfina.service.IWmsFinaCreLoanAppService;
import com.zx.emanage.loanfina.vo.WmsFinaCreCancelBeanVo;
import com.zx.emanage.remind.persist.WmsCreCreditNotaryWarnDao;
import com.zx.emanage.remind.service.IWmsCreCreditNotaryWarnService;
import com.zx.emanage.remind.vo.CreditMessageToRepayWarnBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotaryWarn;
import com.zx.emanage.util.gen.entity.WmsCreCreditServiceType;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanApp;
import com.zx.emanage.util.gen.vo.WmsCreCreditHeadVO;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

@Service("wmscrecreditapproService")
public class WmsCreCreditApproServiceImpl implements IWmsCreCreditApproService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditApproServiceImpl.class);

    @Autowired
    private WmsCreCreditApproDao wmscrecreditapproDao;

    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;

    @Autowired
    private WmsCreCreditHeadDao wCreCreditHeadDao;
    
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private  WmsCreCreditHeadDao wmsCreCreditHeadDao;
    @Autowired
	private IWmsCarLoanWorkFlowService carLoanWorkFlowService;
    @Autowired
    private WmsCreCreditApproDao wmsCreCreditApproDao;
    @Autowired
    private IWmsLoanWorkFlowService   wmsLoanWorkFlowService;//新房贷贷款流程
    @Autowired
    private IWmsFinaCreLoanAppService wmsfinacreloanappservice;
    @Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;
    @Autowired
    private WmsCreCreditNotaryWarnDao wmsCreCreditNotaryWarnDao;// 公正表
    @Autowired
    private WmsCreCreditServiceTypeDao wmsCreCreditServiceTypeDao;// 业务状态表
    @Autowired
    private IWmsCreCreditNotaryWarnService wmsCreCreditNotaryWarnService;// 还款表
    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;
    @Autowired
    private PmPersonnelDao pmPersonnelDao;// 人员表

    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private WmsFinaCreLoanAppDao wmsFinaCreLoanAppDao;

    @Override
    public Map<String, Object> getLoanApproApproveListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 添加流程处理
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        // 获取当前审批人id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 获取节点名称 终审审批
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_DKZS);
        // 根据用户ID，节点名称，流程实例key 查询流信息 注入流程sevice
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        // 判断查询信息是否为空 大小是否为0
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> resMap = new HashMap<String, Object>();
            resMap.put("Rows", new ArrayList<Map<String, Object>>());
            return resMap;
        }
        // 如果存在数据就开始迭代里面的数据
        for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = wmscrecreditapproDao.getLoanApproApproveListWithoutPaging(paramMap);

        paramMap.put("Rows", list);
        return paramMap;
    }

    @Override
    public Map<String, Object> getLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // 添加流程处理
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        // 获取当前审批人id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 获取节点名称 终审审批
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_DKZS);
        // 根据用户ID，节点名称，流程实例key 查询流信息 注入流程sevice
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        // 判断查询信息是否为空 大小是否为0
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> resMap = new HashMap<String, Object>();
            return resMap;
        }
        // 如果存在数据就开始迭代里面的数据
        for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = wmscrecreditapproDao.getLoanApproApproveListWithPaging(paramMap);

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getLoanApproApproveCount(paramMap),
                                                                                       list);

        // 把获取流里面taskId添加到paramMap里面
        for (Map<String, Object> map : list)
        {
            Integer wms_cre_credit_head_id = (Integer) map.get("wms_cre_credit_head_id");
            for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
            {
                if (Integer.valueOf(workflowinfohelp.getBusinessKey()).compareTo(wms_cre_credit_head_id) == 0)
                {
                    map.put("taskId", workflowinfohelp.getTaskId());
                    String iscontinueenable = (String) workflowService.findTaskVariable(workflowinfohelp.getTaskId(),
                                                                                        "iscontinueenable");
                    map.put("iscontinueenable", iscontinueenable);// 添加一个新的属性
                                                                  // 表单情况是否需要继续贷前审批
                    break;
                }
            }
        }
        return bean.getGridData();
    }

    /**
     * 实现终审面签数据导出功能
     */
    @Override
    public Map<String, Object> getLoanApproInterViewListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 获取流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        // 获取当前登录用户的id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 获取流程节点名字 终审面签节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_ZSMQ);
        // 根据流程key,当前登录用户,节点名称,查询条件 查询流里面处于该节点的数据
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        // 判断查询信息是否为空 大小是否为0
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> reparm = new HashMap<String, Object>();
            return reparm;
        }
        // 如果存在数据就开始迭代里面的数据
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.parseInt(workflowInfoHelp.getBusinessKey()));
        }
        // 获取的值填充到paramMap
        paramMap.put("idList", idList);
        //修改记录:01 对面签审批导出增加区域编码
        // paramMap.put("create_user_city_code", user.getUser_regionNumber());
        //修改记录:02 2016-10-19 修改验点才菜单权限根据配置来控制--取代区域编码控制
        paramMap.put("salesman_id", user.getUserId());//登陆人id
        paramMap.put("menu_url", WmsHelp.MENU_URL_MQ_LIST); //菜单url 面签
        paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); //获取可查看的部门
        paramMap.put("Rows", wmscrecreditapproDao.getLoanApproInterViewListWithoutPaging(paramMap));
        return paramMap;
    }

    /**
     * 实现信贷终审面签列表信息查询
     */
    @Override
    public Map<String, Object> getLoanApproInterViewListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 获取流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        // 获取当前登录用户的id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 获取流程节点名字 终审面签节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_ZSMQ);
        // 根据流程key,当前登录用户,节点名称,查询条件 查询流里面处于该节点的数据
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        // 判断查询信息是否为空 大小是否为0
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> reparm = new HashMap<String, Object>();
            return reparm;
        }
        // 如果存在数据就开始迭代里面的数据
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.parseInt(workflowInfoHelp.getBusinessKey()));
        }
        // 获取的值填充到paramMap
        paramMap.put("idList", idList);
        // /修改记录:01 区分地区数据 添加区域编码
        //特殊注意:因101098这个人拥有面签长春和沈阳的权限,为了简化2015-03-27。
        //在此只允许这个账号可以查看到长春和沈阳的面签单据。
        /*if(!user.getUserCode().equals("101098")){
            paramMap.put("create_user_city_code", user.getUser_regionNumber());               
        }*/
        // 2016-10-29 修改验点才菜单权限根据配置来控制--取代区域编码控制
        paramMap.put("salesman_id", user.getUserId());//登陆人id
        paramMap.put("menu_url", WmsHelp.MENU_URL_MQ_LIST); //菜单url 面签
        paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); //获取可查看的部门
        List<Map<String, Object>> list = wmscrecreditapproDao.getLoanApproInterViewListWithPaging(paramMap);

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getLoanApproInterViewCount(paramMap),
                                                                                       list);
        // 把获取流里面taskId添加到paramMap里面
        for (Map<String, Object> map : list)
        {
            // 获取GridDataBean里面map<String,Object>的主表ID
            Integer wms_cre_credit_head_id = (Integer) map.get("wms_cre_credit_head_id");
            // 迭代workflowInfoHelps，找出与id对应的taskId，添加到map.
            for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
            {
                if (Integer.valueOf(workflowInfoHelp.getBusinessKey()).compareTo(wms_cre_credit_head_id) == 0)
                {
                    map.put("taskId", workflowInfoHelp.getTaskId());
                    break;
                }
            }

        }
        return bean.getGridData();
    }

    /**
     * 终审管理>审批查询导出功能
     */
    @Override
    public Map<String, Object> getLoanApproSearchListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (queryInfo.getCre_type() == null || queryInfo.getCre_type() == "")
        {
            paramMap.put("cre_type", null);
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        // /修改记录:02 增加城市筛选条件
        if (StringUtil.isNotBlank(queryInfo.getCity()))
        {
            paramMap.put("city", SysTools.getSqlLikeParam(queryInfo.getCity()));
        }
        // /修改记录:02 增加单据状态筛选条件
        if (StringUtil.isNotBlank(queryInfo.getBill_status()) && !queryInfo.getBill_status().equals("-1"))
        {
            paramMap.put("bill_status", queryInfo.getBill_status());
        }
        // /修改记录:04 贷款金额区间
        if (StringUtil.isNotBlank(queryInfo.getCredit_limit_begin())
            && StringUtil.isNotBlank(queryInfo.getCredit_limit_end()))
        {
            paramMap.put("credit_limit_begin", queryInfo.getCredit_limit_begin());
            paramMap.put("credit_limit_end", queryInfo.getCredit_limit_end());
        }
        // /修改记录:04 放款金额区间
        if (StringUtil.isNotBlank(queryInfo.getLoan_amount_str_begin())
            && StringUtil.isNotBlank(queryInfo.getLoan_amount_str_end()))
        {
            paramMap.put("loan_amount_str_begin", queryInfo.getLoan_amount_str_begin());
            paramMap.put("loan_amount_str_end", queryInfo.getLoan_amount_str_end());
        }
        // /修改记录:04 申请时间区间
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_begin", Convert.toDate(queryInfo.getCreate_timestamp_begin()));
            paramMap.put("create_timestamp_end",
                         Convert.toDate(new StringBuffer(queryInfo.getCreate_timestamp_end()).append(" 23:59:59.9")));
        }
        // /修改记录:04 是否逾期
        if (StringUtil.isNotBlank(queryInfo.getRepay_status()) && !queryInfo.getRepay_status().equals("-1"))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        
        if (StringUtil.isNotBlank(queryInfo.getDept_city_sel())) {
        	paramMap.put("dept_city_sel", queryInfo.getDept_city_sel());
        }
        if (StringUtil.isNotBlank(queryInfo.getDept_name_sel())) {
        	paramMap.put("dept_name_sel", queryInfo.getDept_name_sel());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_city_code())) {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag())) {
            paramMap.put("mort_flag", queryInfo.getMort_flag());
        }
        // /修改记录:03 增加页面显示终审金额，面签金额，合同金额 的查询
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_HTCX_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        paramMap.put("Rows", wmscrecreditapproDao.getLoanApproSearchListWithoutPaging(paramMap));
        return paramMap;
    }

    /**
     * 终审管理>审批查询页面显示功能
     */
    @Override
    public Map<String, Object> getLoanApproSearchListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 单据编号
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        // 客户姓名
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        // 业务员和业务员短工号
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        // 身份证号
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        // 产品类型
        if (queryInfo.getCre_type() == null || queryInfo.getCre_type() == " ")
        {
            paramMap.put("cre_type", null);
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_loan_type()))
        {
            paramMap.put("cre_loan_type", queryInfo.getCre_loan_type());
        }
        if (StringUtils.isNotBlank(queryInfo.getSalesman_city_code())) {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        // /修改记录：02 增加城市筛选条件
        if (StringUtil.isNotBlank(queryInfo.getCity()))
        {
            paramMap.put("city", SysTools.getSqlLikeParam(queryInfo.getCity()));
        }
        // /修改记录:02 增加单据状态筛选条件
        if (StringUtil.isNotBlank(queryInfo.getBill_status()) && !queryInfo.getBill_status().equals("-1"))
        {
            paramMap.put("bill_status", queryInfo.getBill_status());
        }
        // /修改记录:04 贷款金额区间
        if (StringUtil.isNotBlank(queryInfo.getCredit_limit_begin())
            && StringUtil.isNotBlank(queryInfo.getCredit_limit_end()))
        {
            paramMap.put("credit_limit_begin", queryInfo.getCredit_limit_begin());
            paramMap.put("credit_limit_end", queryInfo.getCredit_limit_end());
        }
        // /修改记录:04 放款金额区间
        if (StringUtil.isNotBlank(queryInfo.getLoan_amount_str_begin())
            && StringUtil.isNotBlank(queryInfo.getLoan_amount_str_end()))
        {
            paramMap.put("loan_amount_str_begin", queryInfo.getLoan_amount_str_begin());
            paramMap.put("loan_amount_str_end", queryInfo.getLoan_amount_str_end());
        }
        // /修改记录:04 申请时间区间
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_begin", Convert.toDate(queryInfo.getCreate_timestamp_begin()));
            paramMap.put("create_timestamp_end",
                         Convert.toDate(new StringBuffer(queryInfo.getCreate_timestamp_end()).append(" 23:59:59.9")));
        }
        // /修改记录:04 是否逾期
        if (StringUtil.isNotBlank(queryInfo.getRepay_status()) && !queryInfo.getRepay_status().equals("-1"))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        if (StringUtil.isNotBlank(queryInfo.getDept_city_sel())) {
        	paramMap.put("dept_city_sel", queryInfo.getDept_city_sel());
        }
        if (StringUtil.isNotBlank(queryInfo.getDept_name_sel())) {
        	paramMap.put("dept_name_sel", queryInfo.getDept_name_sel());
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag())) {
        	paramMap.put("mort_flag", queryInfo.getMort_flag());
        }
        paramMap.put("hprocess_time",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS_TIME);
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_SPCX_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        // /修改记录:03 修改查询语句在原来的基础上 查询出终审金额 面签金额 合同金额 和相应的数据数量
        List<Map<String, Object>> list = wmscrecreditapproDao.getLoanApproSearchListWithPaging(paramMap);
        ///修改记录: 06
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setUserId(sysUserRoleDao.findUserIDs().get(0));
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_YDSP);
        List<WorkflowInfoHelp> workflowInfoHelps =workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer>idList = new ArrayList<>();
        for(WorkflowInfoHelp workflowInfoHelp:workflowInfoHelps){
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
        }
        for(Map<String,Object>map:list){
            Integer wms_cre_credit_head_id =(Integer)map.get("wms_cre_credit_head_id");
            for(int i=0;i<idList.size();i++){
                if(idList.get(i).compareTo(wms_cre_credit_head_id)==0){
                    map.put("keyValue", "1");
                    break;
                }
            }
        }
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getLoanApproSearchCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getMakeloansListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 需要跟流程对接并添加taskId属性
        // 查询条件(代办任务)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 设置流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        // 设置当天登录人id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 设置需要查询的流程节点名称
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_FKSQ);
        // 根据用户ID，流程节点名称，流程实例key 查询代办任务下有的数据信息
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        // 定义一个空的list集合存放从流里面获取符合条件表单id
        List<Integer> idList = new ArrayList<Integer>();
        // 1判断如果为空或者大小为为零返回空map
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            return paramMap;
        }
        // 2.叠加workflowInfoHelps里面的表单id
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
        }
        // 3.把获取的id集合放入map
        paramMap.put("idList", idList);
        paramMap.put("Rows", wmscrecreditapproDao.getMakeLoansListWithoutPaging(paramMap));
        return paramMap;
    }

    @Override
    public Map<String, Object> getMakeloanApprovalListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 需要跟流程对接并添加taskId属性
        // 查询条件(代办任务)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 设置流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        // 设置当天登录人id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 设置需要查询的流程节点名称
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_FKSQSP);
        // 根据用户ID，流程节点名称，流程实例key 查询代办任务下有的数据信息
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        // 定义一个空的list集合存放从流里面获取符合条件表单id
        List<Integer> idList = new ArrayList<Integer>();
        // 1判断如果为空或者大小为为零返回空map
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            return paramMap;
        }
        // 2.叠加workflowInfoHelps里面的表单id
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
        }
        // 3.把获取的id集合放入map
        paramMap.put("idList", idList);
        paramMap.put("Rows", wmscrecreditapproDao.getMakeLoansListWithoutPaging(paramMap));
        return paramMap;
    }

    @Override
    public Map<String, Object> getMakeLoansListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // 需要跟流程对接并添加taskId属性
        // 查询条件(代办任务)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 设置流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        // 设置当天登录人id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 设置需要查询的流程节点名称
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_FKSQ);
        // 根据用户ID，流程节点名称，流程实例key 查询代办任务下有的数据信息
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        // 定义一个空的list集合存放从流里面获取符合条件表单id
        List<Integer> idList = new ArrayList<Integer>();
        // 1判断如果为空或者大小为为零返回空map
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            return paramMap;
        }
        // 2.叠加workflowInfoHelps里面的表单id
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
        }
        // 3.把获取的id集合放入map
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = wmscrecreditapproDao.getMakeLoansListWithPaging(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getMakeLoansCount(paramMap),
                                                                                       list);
        // 4.把对应的表单id对应的taskId加入到集合里面
        for (Map<String, Object> map : list)
        {
            Integer wms_cre_credit_head_id = (Integer) map.get("wms_cre_credit_head_id");
            for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
            {
                if (Integer.valueOf(workflowinfohelp.getBusinessKey()).compareTo(wms_cre_credit_head_id) == 0)
                {
                    map.put("taskId", workflowinfohelp.getTaskId());
                    break;
                }
            }
        }
        return bean.getGridData();
    }
	/**
	 * 放款确认
	 */
    @Override
    public Map<String, Object> getPaymentConfirmationListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = houseCreditWorkFlowService.getCreditOrMortgageIdListWorkFlow(String.valueOf(user.getUserId()));
        //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap, user.getUserId(), "12");
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "12",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
		paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "12",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	
        if (paramMap.get("idList") == null)
        {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("Rows", new ArrayList<Map<String, Object>>());
            return resultMap;
        }
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (queryInfo.getCre_type() == null || queryInfo.getCre_type() == " ")
        {
            paramMap.put("cre_type", "1");
        }
        if (StringUtils.isNotBlank(queryInfo.getSalesman_city_code())) {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("Rows", wmscrecreditapproDao.getPaymentConfirmationListWithoutPaging(paramMap));
        return paramMap;
    }

    @Override
    public Map<String, Object> getPaymentConfirmationListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
    	//放款确认：查询列表经过  信贷  房贷  车贷  集合单据
        Map<String, Object> paramMap = houseCreditWorkFlowService.getCreditOrMortgageIdListWorkFlow(String.valueOf(user.getUserId()));
        //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap, user.getUserId(), "12");
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "12",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
		paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "12",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	
        if (paramMap.get("idList") == null)
        {
        	 Map<String, Object> resultMap = new HashMap<String, Object>();
             resultMap.put("Rows", new ArrayList<Map<String, Object>>());
             return resultMap;
        }
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (queryInfo.getCre_type() != null && queryInfo.getCre_type() != "")
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (StringUtils.isNotBlank(queryInfo.getSalesman_city_code())) {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());

        List<Map<String, Object>> list = wmscrecreditapproDao.getPaymentConfirmationListWithPaging(paramMap);
        list = houseCreditWorkFlowService.addTaskIDCreditOrHouse(list, (List<Integer>) paramMap.get("idList"),
                                                                 (List<String>) paramMap.get("taskIdList"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getPaymentConfirmationCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 终审审批信息列表分页--房产模块--房产终审
     * 
     * @param queryInfo
     * @return baisong
     */
    @Override
    public Map<String, Object> getHouseLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap=new HashMap<>();

        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        //城市
        if (StringUtil.isNotBlank(queryInfo.getSalesman_city_code()))
        {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        //客户姓名
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        // 客户姓名
        if (StringUtil.isNotBlank(queryInfo.getBill_status()))
        {
            paramMap.put("bill_status", queryInfo.getBill_status());
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_ZSSP_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        // 判断是否需要分页 0不需要
        if (!"0".equals(queryInfo.getIs_page()))
        {
            paramMap.put("offset", queryInfo.getOffset());
            paramMap.put("pagesize", queryInfo.getPagesize());
        }
        List<Map<String, Object>> list = wmscrecreditapproDao.searchforhouse(paramMap);
        // 判断是否需要分页 0不需要
        if ("0".equals(queryInfo.getIs_page()))
        {
            Map<String,Object> map=new HashMap<>();
            
            map.put("Rows", list);
            return map;
        }
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.findCountforhouse(paramMap),
                                                                                       list);

        return bean.getGridData();
    }

    /**
     * 终审审批信息列表分页--房产模块--房产终审--数据导出
     * 
     * @param queryInfo
     * @return baisong
     */
    @Override
    public Map<String, Object> getHouseLoanApproApproveListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                         UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();
        // 改变查询方式 减少查询时间 2016-12-19 baisong
//        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
//                                                                                    "3");
//        //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "6");//合并俩个流程
//        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "6",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
//		paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "6",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	
//        if (paramMap.get("idList") == null)
//        {
//            Map<String, Object> resultMap = new HashMap<String, Object>();
//            resultMap.put("Rows", new ArrayList<Map<String, Object>>());
//            return resultMap;
//        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        //城市
        if (StringUtil.isNotBlank(queryInfo.getSalesman_city_code()))
        {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("Rows", wmscrecreditapproDao.searchforhouse(paramMap));
        return paramMap;
    }

    /**
     * 签合同
     * 
     * @param queryInfo
     * @return baisong
     */
    @Override
    public Map<String, Object> getAgreeLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();
        // 改变查询方式 减少查询时间
        // Map<String, Object> paramMap =
        // houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
        // "4");
        // //paramMap =
        // wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(),
        // "7");//合并俩个流程
        // paramMap =
        // wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(),
        // "7",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
        // paramMap =
        // wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(),
        // "7",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程
        // if (paramMap.get("idList") == null)
        // {
        // Map<String, Object> resultMap = new HashMap<String, Object>();
        // resultMap.put("Rows", new ArrayList<Map<String, Object>>());
        // return resultMap;
        // }
        
//        if (StringUtil.isNotBlank(queryInfo.getRepay_status()))
//        {
//            paramMap.put("repay_status", queryInfo.getRepay_status());
//        }
        
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
//        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
//        {
//            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
//            sb.append(" 23:59:59.9");
//            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
//            paramMap.put("create_timestamp1", sb.toString());
//        }
        if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }         
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_QDHT_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        List<Map<String, Object>> list = wmscrecreditapproDao.searchforhouseprotocol(paramMap);
        // 添加taskId
        // list = houseCreditWorkFlowService.addTaskIDHouse(list,
        // (List<Integer>) paramMap.get("idList"),
        // (List<String>) paramMap.get("taskIdList"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.findCountforhouseProtocol(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 终审审批信息列表分页--房产模块-签合同--数据导出
     * 
     * @return baisong
     */
    @Override
    public Map<String, Object> getAgreeLoanListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();
//        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
//                                                                                    "4");
//        //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "7");//合并俩个流程
//        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "7",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
//		paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "7",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	
//        if (paramMap.get("idList") == null)
//        {
//            Map<String, Object> resultMap = new HashMap<String, Object>();
//            resultMap.put("Rows", new ArrayList<Map<String, Object>>());
//            return resultMap;
//        }
    	if (StringUtil.isNotBlank(user.getUser_regionNumber())) {//区域编码
            paramMap.put("create_user_city_code", user.getUser_regionNumber());
        }
    	 if (StringUtil.isNotBlank(queryInfo.getBill_code()))
         {
             paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
         }
         if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
         {
             paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
         }
         if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
         {
             paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
         }         
         if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
         {
             paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
             paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
         }
         if (StringUtil.isNotBlank(queryInfo.getId_card()))
         {
             paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
         }
         if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
         {
             paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
         }
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_QDHT_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("Rows", wmscrecreditapproDao.searchforhouseprotocol(paramMap));
        return paramMap;
    }

    /**
     * 公正列表数据导出
     */
    @Override
    public Map<String, Object> getNotarizationListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
    	Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                 "5");
		//paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "8");//合并俩个流程	
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "8",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
		if (paramMap.get("idList") == null)
		{
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("Rows", new ArrayList<Map<String, Object>>());
			return resultMap;	
		}
		  if (StringUtil.isNotBlank(queryInfo.getBill_code()))
	        {
	            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
	        }
	        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
	        {
	            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	        }
	        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
	        {
	            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
	            sb.append(" 23:59:59.9");
	            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
	            paramMap.put("create_timestamp1", sb.toString());
	        }
	        if (StringUtil.isNotBlank(queryInfo.getId_card()))
	        {
	            paramMap.put("id_card", queryInfo.getId_card());
	        }
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("Rows", wmscrecreditapproDao.getNotarizationListWithoutPaging(paramMap));
        return paramMap;
    }

    // 公正列表
    @Override
    public Map<String, Object> getNotarizationListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "5");
        //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "8");//合并俩个流程
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "8",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
        if (paramMap.get("idList") == null)
        {
        	 Map<String, Object> resultMap = new HashMap<String, Object>();
             resultMap.put("Rows", new ArrayList<Map<String, Object>>());
             return resultMap;	
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditapproDao.getNotarizationListWithPaging(paramMap);
        list = houseCreditWorkFlowService.addTaskIDHouse(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getNotarizationCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 他项列表数据导出
     */
    @Override
    public Map<String, Object> getOthersListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                "6");
		//paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "9");//合并俩个流程
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "9",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
		if (paramMap.get("idList") == null)
		{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("Rows", new ArrayList<Map<String, Object>>());
		return resultMap;
		}
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("Rows", wmscrecreditapproDao.getOthersListWithoutPaging(paramMap));
        return paramMap;
    }

    /**
     * 他项列表显示
     */
    @Override
    public Map<String, Object> getOthersListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "6");
        //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "9");//合并俩个流程	
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "9",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
        if (paramMap.get("idList") == null)
        {
        	 Map<String, Object> resultMap = new HashMap<String, Object>();
             resultMap.put("Rows", new ArrayList<Map<String, Object>>());
             return resultMap;
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditapproDao.getOthersListWithPaging(paramMap);
        list = houseCreditWorkFlowService.addTaskIDHouse(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getOthersCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }
	/**
	 * 放款申请到处excel
	 */
    @Override
    public Map<String, Object> getHousingMakeloansListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();
        /* Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                     "7");
         //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "10");//合并俩个流程
         paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "10",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "10",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	
         if (paramMap.get("idList") == null)
         {
             Map<String, Object> result = new HashMap<String, Object>();
             result.put("Rows", new ArrayList<Map<String, Object>>());
             return result;
         }*/
        if (StringUtil.isNotBlank(user.getUser_regionNumber())) {//区域编码
            paramMap.put("create_user_city_code", user.getUser_regionNumber());
        }
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtils.isNotBlank(queryInfo.getSalesman_city_code())) {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("bill_status", "F");
        paramMap.put("Rows", wmscrecreditapproDao.getHousingMakeLoansListWithoutPaging(paramMap));
        return paramMap;
    }
    @Override
    public Map<String, Object> getCarMakeloansListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "8");
        if (paramMap.get("idList") == null)
        {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("Rows", new ArrayList<Map<String, Object>>());
            return result;
        }
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("Rows", wmscrecreditapproDao.getCarMakeLoansListWithoutPaging(paramMap));
        return paramMap;
    }

    @Override
    public Map<String, Object> getHouseMakeloanApprovalListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                         UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();

        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtils.isNotBlank(queryInfo.getSalesman_city_code())) {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        if (StringUtils.isNotBlank(queryInfo.getProtocol_date_begin())) {
            paramMap.put("protocol_date_begin", queryInfo.getProtocol_date_begin());
        }
        if (StringUtils.isNotBlank(queryInfo.getProtocol_date_end())) {
            paramMap.put("protocol_date_end", queryInfo.getProtocol_date_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("bill_status", 'K');
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_FKSP_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        paramMap.put("Rows", wmscrecreditapproDao.getHousingMakeLoansListWithoutPaging(paramMap));
        return paramMap;
    }
    /**
     * 车贷放款审批导出excel
     */
    @Override
    public Map<String, Object> getCarMakeloanApprovalListWithoutPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                         UserBean user)
    {	
    	//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "9");
        if (paramMap.get("idList") == null)
        {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("Rows", new ArrayList<Map<String, Object>>());
            return result;
        }
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("Rows", wmscrecreditapproDao.getCaringMakeLoansListWithoutPaging(paramMap));
        return paramMap;
    }

    /**
     *
     * @Title: getHousingMakeLoansListWithPaging
     * @Description: TODO( 放款申请)
     * @param queryInfo
     * @param user
     * @return map
     * @author: baisong
     * @time:2016年12月29日 下午1:40:41
     * @see com.zx.emanage.loanappro.service.IWmsCreCreditApproService#getHousingMakeLoansListWithPaging(com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月29日 baisong 创建方法
     */
    @Override
    public Map<String, Object> getHousingMakeLoansListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();
        /* Map<String, Object> paramMap = houseCreditWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "7");
        //paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "10");//合并俩个流程	
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "10",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
        paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "10",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	
        if (paramMap.get("idList") == null)
        {
            return new HashMap<String, Object>();
        }*/
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(user.getUser_regionNumber())) {//区域编码
            paramMap.put("create_user_city_code", user.getUser_regionNumber());
        }
        if (StringUtils.isNotBlank(queryInfo.getSalesman_city_code())) {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // F待放款审核
        paramMap.put("bill_status", 'F');
        List<Map<String, Object>> list = wmscrecreditapproDao.getHousingMakeLoansListWithPaging(paramMap);
        /*list = houseCreditWorkFlowService.addTaskIDHouse(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"));*/
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getHousingMakeLoansCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }
    @Override
    public Map<String, Object> getCarMakeLoansListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {	////carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "8");
        if (paramMap.get("idList") == null)
        {
            return new HashMap<String, Object>();
        }
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditapproDao.getCarMakeLoansListWithPaging(paramMap);
        list = carLoanWorkFlowService.setWorkFlowTaskID(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"), (List<String>)paramMap.get("approvesGroups"), (List<String>)paramMap.get("approveAdvices"), (List<String>)paramMap.get("approveTimes"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getCarMakeLoansCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getWmsFinaCreLoanAppByEntity(WmsFinaCreLoanApp entity)
    {
        return wmscrecreditapproDao.getWmsFinaCreLoanAppByEntity(entity);
    }

    /**
     * 实现信贷合同导出操作
     */
    @Override
    public Map<String, Object> getAgreePrintLoanListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 获取流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        // 获取当前登录用户的id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 获取流程节点名字 合同签订节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_HTQD);
        // 根据流程key,当前登录用户,节点名称,查询条件 查询流里面处于该节点的数据
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        // 判断查询信息是否为空 大小是否为0
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> reparm = new HashMap<String, Object>();
            return reparm;
        }
        // 如果存在数据就开始迭代里面的数据
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.parseInt(workflowInfoHelp.getBusinessKey()));
        }
        // 获取的值填充到paramMap
        paramMap.put("idList", idList);
        // /修改记录：01 增加录单员区域编码和登录人区域编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        List<Map<String, Object>> list = wmscrecreditapproDao.getAgreePrintLoanListWithoutPaging(paramMap);
        paramMap.put("Rows", list);
        return paramMap;
    }

    /**
     * 实现信贷合同签订数据查询
     */
    @Override
    public Map<String, Object> getAgreePrintLoanListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 获取流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        // 获取当前登录用户的id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 获取流程节点名字 合同签订节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_HTQD);
        // 根据流程key,当前登录用户,节点名称,查询条件 查询流里面处于该节点的数据
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        // 判断查询信息是否为空 大小是否为0
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> reparm = new HashMap<String, Object>();
            return reparm;
        }
        // 如果存在数据就开始迭代里面的数据
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.parseInt(workflowInfoHelp.getBusinessKey()));
        }
        // 获取的值填充到paramMap
        paramMap.put("idList", idList);
        // /修改记录:01 为信贷签订合同页面显示数据进行区域划分
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        List<Map<String, Object>> list = wmscrecreditapproDao.getAgreePrintLoanListWithPaging(paramMap);

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getAgreePrintLoanCount(paramMap),
                                                                                       list);
        // 把获取流里面taskId添加到paramMap里面
        for (Map<String, Object> map : list)
        {
            // 获取GridDataBean里面map<String,Object>的主表ID
            Integer wms_cre_credit_head_id = (Integer) map.get("wms_cre_credit_head_id");
            // 迭代workflowInfoHelps，找出与id对应的taskId，添加到map.
            for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
            {
                if (Integer.valueOf(workflowInfoHelp.getBusinessKey()).compareTo(wms_cre_credit_head_id) == 0)
                {
                    map.put("taskId", workflowInfoHelp.getTaskId());
                    break;
                }
            }
        }
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getMakeLoanApprovalListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // 需要跟流程对接并添加taskId属性
        // 查询条件(代办任务)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 设置流程实例key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        // 设置当天登录人id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 设置需要查询的流程节点名称
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_FKSQSP);
        // 根据用户ID，流程节点名称，流程实例key 查询代办任务下有的数据信息
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        // 定义一个空的list集合存放从流里面获取符合条件表单id
        List<Integer> idList = new ArrayList<Integer>();
        // 1判断如果为空或者大小为为零返回空map
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            return paramMap;
        }
        // 2.叠加workflowInfoHelps里面的表单id
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
        }
        // 3.把获取的id集合放入map
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = wmscrecreditapproDao.getMakeLoansListWithPaging(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getMakeLoansCount(paramMap),
                                                                                       list);
        // 4.把对应的表单id对应的taskId加入到集合里面
        for (Map<String, Object> map : list)
        {
            Integer wms_cre_credit_head_id = (Integer) map.get("wms_cre_credit_head_id");
            for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
            {
                if (Integer.valueOf(workflowinfohelp.getBusinessKey()).compareTo(wms_cre_credit_head_id) == 0)
                {
                    map.put("taskId", workflowinfohelp.getTaskId());
                    break;
                }
            }
        }
        return bean.getGridData();
    }

    @Override
    @Transactional
    public String makeLoanApprovalSave(WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        creditWorkFlowService.makeLoansExaminationAndApproval(approveWorkFlowVO);
        return "success";
    }

    @Override
    @Transactional
    public String makeLoanApprovalSaveCancel(WmsCreditWorkFlowVO approveWorkFlowVO, WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean,UserBean user)
    {        
    	String resStr = "success";
	
	    Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
	    hbean.setNullify_user_name(user.getRealname());
	    hbean.setNullify_user_id(user.getUserId());
	    hbean.setNullify_timestamp(sysTime);
	    int ret= wmsCreCreditHeadDao.update(hbean);//更新贷款主表
	    if (ret == 0)
	    {
	    	resStr = "error";
	    	return resStr;
	    }
        WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(hbean.getWms_cre_credit_head_id());
        if(wmsCreCreditHead.getCreate_timestamp().before(new Timestamp(bean.getTimestamp_val().getTime()))){
        	 approveWorkFlowVO.setPass("true");//单据作废
        }else{
        	approveWorkFlowVO.setPass("nullify");//单据作废
        }
        approveWorkFlowVO.setAdvice(bean.getAdvice());
        creditWorkFlowService.makeLoansExaminationAndApproval(approveWorkFlowVO);
        return resStr;
    }
    @Override
    @Transactional
    public String makeLoanApprovalSaveForFd(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user)
    {
          
        // 并发校验(单据状态)
        WmsCreCreditHead wmsCreCreditHead = this.wmsCreCreditHeadDao.get(Integer.parseInt(approveHouseWorkFlowVO.getWms_cre_credit_head_id()));
        if (!wmsCreCreditHead.getBill_status().equals("K"))
        {
            return "change";
        }
        // 更改放款申请表，放款申请时间
        Map<String, Object> appMap = new HashMap<String, Object>();
        appMap.put("loan_date", new Date(System.currentTimeMillis()));
        appMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        wmsFinaCreLoanAppDao.updateLoanDate(appMap);
        // 如果单据通过则同步信息并处理还款信息
        if ("true".equals(approveHouseWorkFlowVO.getPass()))
        {
            CreditMessageToRepayWarnBeanVO beanvo = new CreditMessageToRepayWarnBeanVO();
            // 获取信息放款数据同步到还款提醒模块
            beanvo.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id().toString());
            // 贷款类型-房贷
            beanvo.setCre_type("2");
            String result = wmsCreCreditNotaryWarnService.getHouseCreditMessageToRepayWarn(beanvo, user);
            /*// 判断是否同步成功
            if ("listNull".equals(result) || !"exceptionError".equals(result) || !"error".equals(result))
            {
                return result;
            }*/
            // 单据类型--业务状态： 01 新增、02 展期、03 续期--单据通过
            if ("03".equals(wmsCreCreditHead.getBill_type()))
            {
                /* CreditMessageToRepayWarnBeanVO beanvo = new CreditMessageToRepayWarnBeanVO();
                   // 获取信息放款数据同步到还款提醒模块
                   beanvo.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                   // 贷款类型-房贷
                   beanvo.setCre_type("2");
                   String result = wmsCreCreditNotaryWarnService.getHouseCreditMessageToRepayWarn(beanvo, user);
                */
                WmsCreCreditServiceType wmsCreCreditServiceType = new WmsCreCreditServiceType();
                wmsCreCreditServiceType.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id());
                // 获取贷款表和还款提醒表的中间表
                List<WmsCreCreditServiceType> list = wmsCreCreditServiceTypeDao.getListByEntity(wmsCreCreditServiceType);
                if (list != null && list.size() > 0)
                {
                    wmsCreCreditServiceType = list.get(0);
                    // 单据还款状态1、正常2、逾期3、结清4、续贷7、移交公司
                    WmsCreCreditNotaryWarn wmsCreCreditNotaryWarn = new WmsCreCreditNotaryWarn();
                    wmsCreCreditNotaryWarn.setRepay_status("3");
                    wmsCreCreditNotaryWarn.setClean_up_date(new Date(System.currentTimeMillis()));// 结清日期
                    wmsCreCreditNotaryWarn.setClean_up_info("展期后原单据结清");
                    wmsCreCreditNotaryWarn.setWms_cre_credit_notary_warn_id(wmsCreCreditServiceType.getPre_wms_cre_credit_notary_warn_id());
                    wmsCreCreditNotaryWarnDao.update(wmsCreCreditNotaryWarn);
                }
                // 判断是否同步成功
                if (!"listNull".equals(result) && !"exceptionError".equals(result) && !"error".equals(result))
                {
                    wmsCreCreditServiceType.setWms_cre_credit_notary_warn_id(Integer.valueOf(result));
                    wmsCreCreditServiceTypeDao.updateSyn(wmsCreCreditServiceType);
                }
            }
        }
        /*else
        { // 保存贷款表和还款提醒表的中间表
            WmsCreCreditServiceType wmsCreCreditServiceType = new WmsCreCreditServiceType();
            wmsCreCreditServiceType.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id());
            // 判断是否同步成功
            if (!"listNull".equals(result) && !"exceptionError".equals(result) && !"error".equals(result))
            {
                wmsCreCreditServiceType.setWms_cre_credit_notary_warn_id(Integer.valueOf(result));
            }
            wmsCreCreditServiceType.setBill_code(wmsCreCreditHead.getBill_code());
            wmsCreCreditServiceTypeDao.save(wmsCreCreditServiceType);
        }*/

        approveHouseWorkFlowVO.setDebtkey("FKSP");
        wmsLoanWorkFlowService.publicApprovalStatus(approveHouseWorkFlowVO);
        // 新流程发送短信
        // 查询客户姓名
        Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
        customerChangeParamMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
        customerChangeParamMap.put("is_major", "1");
        customerChangeParamMap.put("enable_flag", "1");
        List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
        PmPersonnel pmPersonnel = new PmPersonnel();
        pmPersonnel.setPersonnel_id(wmsCreCreditHead.getSalesman_id());
        List<PmPersonnel> listp = pmPersonnelDao.getListByEntity(pmPersonnel);
        pmPersonnel = listp.get(0);
        // 获取放款金额
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id());
        List<Map<String, Object>> lisMap = wmsCreCreditNotaryWarnDao.search(parameters);
        String appro_limit = null;
        if (lisMap != null && lisMap.size() > 0)
        {
            Map<String, Object> map = lisMap.get(0);
            if (map.get("appro_limit") != null)
            {
                appro_limit = new BigDecimal(map.get("appro_limit").toString()).divide(new BigDecimal(10000), 0, BigDecimal.ROUND_DOWN).toString().replace(".00000000", "");
            }
        }
        // 调用发送信息的接口出现异常 不会影响正常流程--给业务员 通过不通过拒贷
        try
        {
            if (customerChangeList != null && customerChangeList.size() == 1)
            {
                // 调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                // 发送短信map
                Map<String, String> msgMap = new HashMap<String, String>();
                // 极光参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                // 极光 附加参数map
                Map<String, String> msg_extras = new HashMap<String, String>();
                // 初评通过和直接验房都是通过
                if ("true".equals(approveHouseWorkFlowVO.getPass()))
                {
                    sendMap.put("msg_code", "10017");// 极光消息中心消息编码
                    sendMap.put("msg_code_role", "10017");// 极光消息中心消息编码--流程角色
                    if (appro_limit != null)
                    {
                        paramMap.put("appro_limit", appro_limit.toString());
                    }
                }
                // 初评退回 短信编码和极光消息中心编码
                else if ("back".equals(approveHouseWorkFlowVO.getPass()))
                {
                    sendMap.put("msg_code", "10018");// 极光消息中心消息编码
                    sendMap.put("msg_code_role", "10018");// 极光消息中心消息编码--流程角色
                }
                // 初评拒贷 极光和消息中心编码
                else if ("nullify".equals(approveHouseWorkFlowVO.getPass()))
                {
                    sendMap.put("msg_code", "10019");// 极光消息中心消息编码
                    sendMap.put("msg_code_role", "10019");// 极光消息中心消息编码--流程角色
                }
                msgMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                msgMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                msgMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());

                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                paramMap.put("advice", approveHouseWorkFlowVO.getAdvice());// 审核意见
                paramMap.put("city", wmsCreCreditHead.getCity());

                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                msgMap.put("paramJson", JSON.toJSONString(paramMap));
                sendMap.put("msgMap", msgMap);
                // 激光推送
                sendMap.put("quart_message", "1");// 消息附加参数
                // 如果是消息中心
                sendMap.put("message_center", "1");

                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                sendMap.put("msg_extras", msg_extras);// 消息附加参数

                sendMap.put("is_oneself", "1");// 给业务员自己发送消息

                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getUserId().toString());
                paramMap.put("create_user_name", user.getRealname());
                paramMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                paramMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                paramMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                sendMap.put("msg_map", paramMap);// 极光推送和消息中心的消息内容参数
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if ("true".equals(approveHouseWorkFlowVO.getPass()))
        {
            // 调用发送信息的接口出现异常 不会影响正常流程 --团队经理
            try
            {
                // 调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                // 参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                // 参数map
                Map<String, String> msg_extras = new HashMap<String, String>();

                sendMap.put("user_id", wmsCreCreditHead.getSalesman_id());
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                if (appro_limit != null)
                {
                    paramMap.put("appro_limit", appro_limit.toString());
                }
                // 消息中心使用
                sendMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                sendMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                sendMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());

                // 极光推送需要的数据参数
                sendMap.put("quart_message", "1");// 消息附加参数
                paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                paramMap.put("advice", approveHouseWorkFlowVO.getAdvice());// 审核意见
                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                sendMap.put("msg_extras", msg_extras);// 消息附加参数
                // 发送消息中心 焦德龙
                // 如果是消息中心
                sendMap.put("message_center", "1");
                // 提交人
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getUserId().toString());
                paramMap.put("create_user_name", user.getRealname());
                paramMap.put("city", wmsCreCreditHead.getCity());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                sendMap.put("role_outside", "1");// 判断获取人
                List<String> post_number_list = new ArrayList<>();
                post_number_list.add("TDJL");
                sendMap.put("post_number_list", post_number_list);// 判断获取人
                sendMap.put("msg_code", "10020");// 消息编码
                sendMap.put("msg_code_role", "10020");// 消息编码
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            // 调用发送信息的接口出现异常 不会影响正常流程--门店经理
            try
            {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put("dept_id", wmsCreCreditHead.getSalesman_dept_id());
                deptMap.put("dept_level", "6");
                String dept_info = sysDeptDao.getDeptInfoForSendInfo(deptMap);
                // 调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                // 参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                // 参数map
                Map<String, String> msg_extras = new HashMap<String, String>();

                sendMap.put("user_id", wmsCreCreditHead.getSalesman_id());
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                if (appro_limit != null)
                {
                    paramMap.put("appro_limit", appro_limit.toString());
                }
                // 消息中心使用
                sendMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                sendMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                sendMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());

                // 极光推送需要的数据参数
                sendMap.put("quart_message", "1");// 消息附加参数
                paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                paramMap.put("advice", approveHouseWorkFlowVO.getAdvice());// 审核意见
                paramMap.put("dept_info", dept_info);// 部门信息
                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                sendMap.put("msg_extras", msg_extras);// 消息附加参数
                sendMap.put("dept_info", dept_info);// 部门信息
                // 发送消息中心 焦德龙
                // 如果是消息中心
                sendMap.put("message_center", "1");
                // 提交人
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getUserId().toString());
                paramMap.put("create_user_name", user.getRealname());
                paramMap.put("city", wmsCreCreditHead.getCity());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                sendMap.put("role_outside", "1");// 判断获取人
                List<String> post_number_list = new ArrayList<>();
                post_number_list.add("MDJL");
                sendMap.put("post_number_list", post_number_list);// 判断获取人
                sendMap.put("msg_code", "10021");// 消息编码
                sendMap.put("msg_code_role", "10021");// 消息编码
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            // 调用发送信息的接口出现异常 不会影响正常流程--业务部
            try
            {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put("dept_id", wmsCreCreditHead.getSalesman_dept_id());
                deptMap.put("dept_level", "5,6");
                String dept_info = sysDeptDao.getDeptInfoForSendInfo(deptMap);
                // 调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                // 参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                // 参数map
                Map<String, String> msg_extras = new HashMap<String, String>();

                sendMap.put("user_id", wmsCreCreditHead.getSalesman_id());
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                if (appro_limit != null)
                {
                    paramMap.put("appro_limit", appro_limit.toString());
                }
                // 消息中心使用
                sendMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                sendMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                sendMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());

                // 极光推送需要的数据参数
                sendMap.put("quart_message", "1");// 消息附加参数
                paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                paramMap.put("advice", approveHouseWorkFlowVO.getAdvice());// 审核意见
                paramMap.put("dept_info_md", dept_info);// 部门信息
                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                sendMap.put("msg_extras", msg_extras);// 消息附加参数
                sendMap.put("dept_info_md", dept_info);// 部门信息
                // 如果是消息中心
                sendMap.put("message_center", "1");
                // 提交人
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getUserId().toString());
                paramMap.put("create_user_name", user.getRealname());
                paramMap.put("city", wmsCreCreditHead.getCity());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                sendMap.put("role_value", "1");// 判断获取人
                sendMap.put("role_name", WmsHelp.YWB_ROLE_NAME);// 判断获取人
                sendMap.put("msg_code", "20012");// 消息编码
                sendMap.put("msg_code_role", "20012");// 消息编码
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return "success";
    }
    //车贷放款审批审核
    @Override
    @Transactional
    public String makeLoanApprovalSaveForCar(WmsCarLoanWorkFlowVO wVo)
    {
    	//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
    	carLoanWorkFlowService.carLoanApprovalProcess(wVo,"9");
        return "success";
    }
	/**
	 * 放款审核
	 */
    @Override
    public Map<String, Object> getHouseMakeLoanApprovalListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap=new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
//        if (StringUtil.isNotBlank(user.getUser_regionNumber())) {//区域编码
//            paramMap.put("create_user_city_code", user.getUser_regionNumber());
//        }
        if (StringUtils.isNotBlank(queryInfo.getSalesman_city_code())) {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        if (StringUtils.isNotBlank(queryInfo.getProtocol_date_begin())) {
            paramMap.put("protocol_date_begin", queryInfo.getProtocol_date_begin());
        }
        if (StringUtils.isNotBlank(queryInfo.getProtocol_date_end())) {
            paramMap.put("protocol_date_end", queryInfo.getProtocol_date_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("bill_status", 'K');
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_FKSP_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        List<Map<String, Object>> list = wmscrecreditapproDao.getHousingMakeLoansListWithPaging(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getHousingMakeLoansCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }
    /***
     * 车贷放款审批列表
     */
    @Override
    public Map<String, Object> getCarMakeLoanApprovalListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "9");
        if (paramMap.get("idList") == null)
        {
            return new HashMap<String, Object>();
        }
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditapproDao.getCaringMakeLoansListWithPaging(paramMap);
        list = carLoanWorkFlowService.setWorkFlowTaskID(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"), (List<String>)paramMap.get("approvesGroups"), (List<String>)paramMap.get("approveAdvices"), (List<String>)paramMap.get("approveTimes"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getCaringMakeLoansCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 实现终审复议申请列表数据显示
     */
    @Override
    public Map<String, Object> getLoanReviewRevisionWithPagingList(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 筛选条件单据编号
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        // 筛选条件时间
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        // 筛选条件业务员姓名和短工号
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        // 筛选条件客户的身份证号码
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditapproDao.getLoanReviewRevision(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.getLoanReviewRevisionCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 实现终审复议申请列表数据导出
     */
    @Override
    public Map<String, Object> getLoanReviewRevisionWithOutPagingList(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditapproDao.getLoanReviewRevisionWithOutPagingList(paramMap);
        paramMap.put("Rows", list);
        return paramMap;
    }

    /**
     * 实现终审复议申请提交
     * 
     * @param queryInfo
     * @param user
     * @return
     * @author hancd
     */
    @Override
    public String toWmsReconsideration(WmsCreApproSearchBeanVO queryInfo, UserBean user, WmsCreditWorkFlowVO aWorkFlowVO)
    {
        String result = "success";
        // 二次开启流程当前登录人代理单据原始录单员开启流程
        creditWorkFlowService.startSecondaryWorkFlow(queryInfo.getCreate_user_id(),
                                                     aWorkFlowVO.getWms_cre_credit_head_id().toString(),
                                                     queryInfo.getCreate_user_id());
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        // 根据单据主键修改复议申请原因和复议申请时间和单据状态变成15 待复议修订状态
        Map<String, Object> paramMap = new HashMap<>();
        // 单据主键
        paramMap.put("wms_cre_credit_head_id", aWorkFlowVO.getWms_cre_credit_head_id());
        // 复议原因
        paramMap.put("review_the_reasons", aWorkFlowVO.getAdvice());
        // 复议时间
        paramMap.put("review_of_time", sysTime);
        // 单据状态15
        paramMap.put("bill_status", "15");
        // 修改方法
        wCreCreditHeadDao.updateRecord(paramMap);
        // 返回结果
        return result;
    }

    /**
     * 实现信贷终审合同签订 单据作废操作
     */
    @Override
    public String creCheckConcludeAndSignApprove(UserBean user,WmsCreditWorkFlowVO approveWorkFlowVO)
    {
        String result = "success";
        WmsCreCreditHead hbean=new WmsCreCreditHead();
        hbean.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
        hbean.setNullify_type("3");// 类型
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        hbean.setNullify_user_name(user.getRealname());
        hbean.setNullify_user_id(user.getUserId());
        hbean.setNullify_timestamp(sysTime);
        hbean.setNullify_reason(approveWorkFlowVO.getAdvice());
        int ret= wmsCreCreditHeadDao.update(hbean);//更新贷款主表
        if (ret == 0)
        {
        	result = "error";
            return result;
        }
        creditWorkFlowService.creCheckConcludeAndSignApprove(approveWorkFlowVO);
        return result;
    }

    /**
     * 修改记录：05 实现信贷终审>初审面签列表显示
     */
    @Override
    public Map<String, Object> getLoanApproInitialInterViewWithPagingList(WmsCreApproSearchBeanVO queryInfo,
                                                                          UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();
        // 筛选单据编号
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        // 筛选申请时间
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        // 筛选业务员/编号
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        // 筛选身份证信息
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // 查询条件(流程代办方面)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 验点审批节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_YDSP);// 自定义的节点名称
        // 根据用户ID,提供节点名字,流程实例key
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> resMap = new HashMap<String, Object>();
            return resMap;
        }
        for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = wmscrecreditapproDao.searchForcsyd(paramMap);

        GridDataBean bean = new GridDataBean(queryInfo.getPage(), wmscrecreditapproDao.findCountForcsyd(paramMap), list);
        // 对列表进行添加新属性
        for (Map<String, Object> map : list)
        {
            Integer wms_cre_credit_head_id = (Integer) map.get("wms_cre_credit_head_id");
            for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
            {
                if (Integer.valueOf(workflowinfohelp.getBusinessKey()).compareTo(wms_cre_credit_head_id) == 0)
                {
                    map.put("taskId", workflowinfohelp.getTaskId());
                    break;
                }
            }
        }
        return bean.getGridData();
    }

    /**
     * 修改记录：05 实现信贷终审>初审面签列表导出
     */
    @Override
    public Map<String, Object> getLoanApproInitialInterViewWithoutPagingList(WmsCreApproSearchBeanVO queryInfo,
                                                                             UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();
        // 筛选单据编号
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        // 筛选申请时间
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        // 筛选业务员/编号
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        // 筛选身份证信息
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 查询条件(流程代办方面)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.CREDITPROCESS);
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 验点审批节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.CREDITPROCESS_YDSP);// 自定义的节点名称
        // 根据用户ID,提供节点名字,流程实例key
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> resMap = new HashMap<String, Object>();
            return resMap;
        }
        for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = wmscrecreditapproDao.getLoanApproInitialInterViewWithoutPagingList(paramMap);
        paramMap.put("Rows", list);
        return paramMap;
    }

    /**
     * 修改记录：05 实现初审面签审核功能
     * 
     * @author hancd
     */
    @Override
    public String loanApproInitialInter(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user)
    {
        String result = "success";
        // 获取当前审批时间
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trial_interview_uid", user.getUserId());
        paramMap.put("trial_interview_uname", user.getRealname());
        paramMap.put("trial_interview_timestamp", time);
        // 设置流程人员ID
        approveWorkFlowVO.setUser_id(user.getUserId());
        paramMap.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        if ("true".equals(approveWorkFlowVO.getPass()))
        {
            // 审核为实地验点
            paramMap.put("trial_interview_result", 1);
            // 判断单据的状态是否已经处于补件中
            if (wCreCreditHeadDao.get(approveWorkFlowVO.getWms_cre_credit_head_id()).getBill_status().equals("13"))
            {
                result = "supply";
            }
        }
        else if ("false".equals(approveWorkFlowVO.getPass()))
        {
            // 审核为不需要实地验点，直接把该条单据释放不进行具体的验点操作
            paramMap.put("trial_interview_result", 0);
            approveWorkFlowVO.setPass("true");
            result = creditWorkFlowService.loanApproInitialInterWorkflow(approveWorkFlowVO);
        }
        paramMap.put("trial_interview_advice", approveWorkFlowVO.getAdvice());
        wCreCreditHeadDao.updateRecord(paramMap);
        return result;
    }
    /**
     * 终审审批信息列表分页--车模块--车终审
     * 
     * @param queryInfo
     * @return baisong
     */
    @Override
    public Map<String, Object> getCarLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
    	//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "6");
        if (paramMap.get("idList") == null)
        {
            return new HashMap<String, Object>();
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());

        List<Map<String, Object>> list = wmscrecreditapproDao.searchforcar(paramMap);
        list = carLoanWorkFlowService.setWorkFlowTaskID(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"),(List<String>)paramMap.get("approvesGroups"), (List<String>)paramMap.get("approveAdvices"), (List<String>)paramMap.get("approveTimes"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.findCountforcar(paramMap),
                                                                                       list);

        return bean.getGridData();
    }
    /**
     * 终审审批信息列表分页--车模块--车终审--导出excel
     * 
     * @param queryInfo
     * @return baisong
     */
    @Override
    public Map<String, Object> getCarLoanApproApproveListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
    	//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "6");
        if (paramMap.get("idList") == null)
        {
        	paramMap.put("Rows","");
			return paramMap;
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());

        List<Map<String, Object>> list = wmscrecreditapproDao.searchforcar(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;
    }
    /**
     * 终审审批信息列表分页--车贷模块-签合同
     * 
     * @param queryInfo
     * @return baisong
     */
    @Override
    public Map<String, Object> getAgreeLoanApproApproveListWithPagingCar(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {	//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "7");
        if (paramMap.get("idList") == null)
        {
        	paramMap.put("Rows","");
			return paramMap;
        }

        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());

        List<Map<String, Object>> list = wmscrecreditapproDao.searchforcarprotocol(paramMap);
        // 添加taskId
        list = carLoanWorkFlowService.setWorkFlowTaskID(list, (List<Integer>) paramMap.get("idList"),
                                                         (List<String>) paramMap.get("taskIdList"), (List<String>)paramMap.get("approvesGroups"), (List<String>)paramMap.get("approveAdvices"), (List<String>)paramMap.get("approveTimes"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.findCountforcarProtocol(paramMap),
                                                                                       list);
        return bean.getGridData();
    }
    /**
     * 终审审批信息列表分页--车贷模块-签合同--数据导出
     * 
     * @return baisong
     */
    @Override
    public Map<String, Object> getAgreeLoanListWithoutPagingCar(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),
                                                                                    "7");
        if (paramMap.get("idList") == null)
        {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("Rows", new ArrayList<Map<String, Object>>());
            return resultMap;
        }

        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("Rows", wmscrecreditapproDao.searchforcarprotocol(paramMap));
        return paramMap;
    }
    /**
     * @Title giveInfoToPTP
     * @description:将客户信息传递给PTP 
     * @param map
     * @param personnel_id
     * @return String
     * @author yangqiyu
     * @time 2015-9-29 10:12:36
     */
	@Override
	public String giveInfoToPTP(Map<String,Object> map,Integer personnel_id) {
		String result="success";
		//获取WMS贷款客户填写电话1的客户
		List<Map<String,Object>> list1 = wmsCreCreditApproDao.getNameAndPhone1(map);
		//获取WMS贷款客户填写电话2联系人信息
		List<Map<String,Object>> list2 = wmsCreCreditApproDao.getNameAndPhone2(map);
		//获取WMS贷款客户联系人信息
		List<Map<String,Object>> list3=  wmsCreCreditApproDao.getNameAndPhone3(map);
		//存放所有根据所传递ID查询到的人员信息
		List<Map<String,Object>> listAll=new ArrayList<Map<String,Object>>();
		//将WMS贷款客户填写电话1的客户插入到所有中
		for(int i=0;i<list1.size();i++){
			//如果需要Map插入的key顺序的话   Map<String,Object> listMap = new LinkedHashMap<String,Object>();
			Map<String,Object> listMap=new HashMap<String,Object>();
			listMap.put("user_phone",list1.get(i).get("mobile_telephone1"));
			listMap.put("user_type",1);
			listMap.put("user_id_card",list1.get(i).get("id_card"));
			listMap.put("user_real_name",list1.get(i).get("customer_name"));
			listAll.add(listMap);
		}
		//将WMS贷款客户填写电话2的客户插入到所有中
		for(int i=0;i<list2.size();i++){
			if(list2.get(i).get("mobile_telephone2")!=null
					&&list2.get(i).get("mobile_telephone2")!=""
					&&!list2.get(i).get("mobile_telephone2").equals("无")){
				Map<String,Object> listMap=new HashMap<String,Object>();
				listMap.put("user_phone",list2.get(i).get("mobile_telephone2"));
				listMap.put("user_type",1);
				listMap.put("user_id_card",list2.get(i).get("id_card"));
				listMap.put("user_real_name",list2.get(i).get("customer_name"));
				listAll.add(listMap);
			}
		}
		//将WMS贷款客户联系人信息插入到所有中
		for(int i=0;i<list3.size();i++){
			//Map<String,Object> listMap = new LinkedHashMap<String,Object>();
			Map<String,Object> listMap=new HashMap<String,Object>();
			listMap.put("user_phone",list3.get(i).get("contact_mobile_phone"));
			listMap.put("user_type",2);
			//listMap.put("user_id_card","\t")
			listMap.put("user_id_card","");
			listMap.put("user_real_name",list3.get(i).get("contact_name"));
			listAll.add(listMap);
		}
		Gson gson = new Gson();
		String userList=gson.toJson(listAll);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("interface_num","PTP_OUT_001"));
		nvps.add(new BasicNameValuePair("sys_num","WMS"));
		nvps.add(new BasicNameValuePair("user_id", "0"));
		nvps.add(new BasicNameValuePair("userList",userList));
		nvps.add(new BasicNameValuePair("personnel_id",personnel_id.toString()));
		HttpPost httpPost = new HttpPost(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple");
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
			CloseableHttpClient httpclient = HttpClients.createDefault();
	        CloseableHttpResponse response = httpclient.execute(httpPost);
	        System.out.println(response.getStatusLine());
//        	HttpEntity entity = response.getEntity();
//        	EntityUtils.consume(entity);
//        	result = EntityUtils.toString(entity);
//        	System.out.println(result);
        	response.close();
			httpclient.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			result="error";
			return result;
		}	
		return result;
	}

	@Override
	@Transactional
	public void sendResultsLoanApprovalUp(String pass, String advice,
			Integer wms_cre_credit_head_id,Integer userId,String userName) {
		//调用流程
        WmsHouseCreditWorkFlowVO vo = new WmsHouseCreditWorkFlowVO();
        vo.setWms_cre_credit_head_id(wms_cre_credit_head_id.toString());
        //核查结果
        if(pass.equals("1")) {
            vo.setPass("true");
        } else if(pass.equals("2")){
            vo.setPass("back");
        }else{
        	throw new RuntimeException("pass参数错误！");
        }
        vo.setUserId(userId.toString());
        vo.setAdvice(advice);
        vo.setDebtkey("FKSP");
        wmsLoanWorkFlowService.publicApprovalStatus(vo);

        // 并发校验(单据状态)
        WmsCreCreditHead wmsCreCreditHead = this.wmsCreCreditHeadDao.get(wms_cre_credit_head_id);
        // 单据通过--同步信息
        if ("true".equals(vo.getPass()))
        {
            UserBean user = new UserBean();
            user.setUserId(userId);
            user.setRealname(userName);
            CreditMessageToRepayWarnBeanVO beanvo = new CreditMessageToRepayWarnBeanVO();
            // 获取信息放款数据同步到还款提醒模块
            beanvo.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id().toString());
            // 贷款类型-房贷
            beanvo.setCre_type("2");
            String result = wmsCreCreditNotaryWarnService.getHouseCreditMessageToRepayWarn(beanvo, user);

            // 单据类型--业务状态： 01 新增、02 展期、03 续期
            if ("03".equals(wmsCreCreditHead.getBill_type()))
            {
                WmsCreCreditServiceType wmsCreCreditServiceType = new WmsCreCreditServiceType();
                wmsCreCreditServiceType.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id());
                // 获取贷款表和还款提醒表的中间表
                List<WmsCreCreditServiceType> list = wmsCreCreditServiceTypeDao.getListByEntity(wmsCreCreditServiceType);
                if (list != null && list.size() > 0)
                {
                    wmsCreCreditServiceType = list.get(0);
                    // 单据还款状态1、正常2、逾期3、结清4、续贷7、移交公司
                    WmsCreCreditNotaryWarn wmsCreCreditNotaryWarn = new WmsCreCreditNotaryWarn();
                    wmsCreCreditNotaryWarn.setRepay_status("3");
                    wmsCreCreditNotaryWarn.setClean_up_date(new Date(System.currentTimeMillis()));// 结清日期
                    wmsCreCreditNotaryWarn.setClean_up_info("展期后原单据结清");
                    wmsCreCreditNotaryWarn.setWms_cre_credit_notary_warn_id(wmsCreCreditServiceType.getPre_wms_cre_credit_notary_warn_id());
                    wmsCreCreditNotaryWarnDao.update(wmsCreCreditNotaryWarn);
                }
                // 判断是否同步成功
                if (!"listNull".equals(result) && !"exceptionError".equals(result) && !"error".equals(result))
                {
                    wmsCreCreditServiceType.setWms_cre_credit_notary_warn_id(Integer.valueOf(result));
                    wmsCreCreditServiceTypeDao.updateSyn(wmsCreCreditServiceType);
                }
            }
        }
        WmsFinaCreLoanApp bean = new WmsFinaCreLoanApp();
        bean.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        bean.setLoan_approval_user_id(userId);
        bean.setLoan_approval_user_name(userName);
        bean.setLoan_approval_result(pass);
        bean.setLoan_approval_advice(advice);
        bean.setLoan_approval_timestamp(new Timestamp(System.currentTimeMillis()));
        bean.setLoan_date(new Date(System.currentTimeMillis()));// 放款日期
        wmsfinacreloanappservice.updateWmsFinaCreLoanApp(bean);
        // 新流程发送短信
        // 查询客户姓名
        Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
        customerChangeParamMap.put("wms_cre_credit_head_id", vo.getWms_cre_credit_head_id());
        customerChangeParamMap.put("is_major", "1");
        customerChangeParamMap.put("enable_flag", "1");
        List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
        PmPersonnel pmPersonnel = new PmPersonnel();
        pmPersonnel.setPersonnel_id(wmsCreCreditHead.getSalesman_id());
        List<PmPersonnel> listp = pmPersonnelDao.getListByEntity(pmPersonnel);
        pmPersonnel = listp.get(0);
        PmPersonnel user = new PmPersonnel();
        user.setPersonnel_id(wmsCreCreditHead.getSalesman_id());
        List<PmPersonnel> userp = pmPersonnelDao.getListByEntity(user);
        user = listp.get(0);

        // 获取放款金额
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id());
        List<Map<String, Object>> lisMap = wmsCreCreditNotaryWarnDao.search(parameters);
        String appro_limit = null;
        if (lisMap != null && lisMap.size() > 0)
        {
            Map<String, Object> map = lisMap.get(0);
            if (map.get("appro_limit") != null)
            {
                appro_limit = new BigDecimal(map.get("appro_limit").toString()).divide(new BigDecimal(10000), 0, BigDecimal.ROUND_DOWN).toString().replace(".00000000", "");
            }
        }
        // 调用发送信息的接口出现异常 不会影响正常流程--给业务员 通过不通过拒贷
        try
        {
            if (customerChangeList != null && customerChangeList.size() == 1)
            {
                // 调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                // 发送短信map
                Map<String, String> msgMap = new HashMap<String, String>();
                // 极光参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                // 极光 附加参数map
                Map<String, String> msg_extras = new HashMap<String, String>();
                // 初评通过和直接验房都是通过
                if ("true".equals(vo.getPass()))
                {
                    sendMap.put("msg_code", "10017");// 极光消息中心消息编码
                    sendMap.put("msg_code_role", "10017");// 极光消息中心消息编码--流程角色
                    if (appro_limit != null)
                    {
                        paramMap.put("appro_limit", appro_limit.toString());
                    }
                }
                // 初评退回 短信编码和极光消息中心编码
                else if ("back".equals(vo.getPass()))
                {
                    sendMap.put("msg_code", "10018");// 极光消息中心消息编码
                    sendMap.put("msg_code_role", "10018");// 极光消息中心消息编码--流程角色
                }
                // 初评拒贷 极光和消息中心编码
                else if ("nullify".equals(vo.getPass()))
                {
                    sendMap.put("msg_code", "10019");// 极光消息中心消息编码
                    sendMap.put("msg_code_role", "10019");// 极光消息中心消息编码--流程角色
                }
                msgMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                msgMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                msgMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());

                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                paramMap.put("advice", vo.getAdvice());// 审核意见
                paramMap.put("city", wmsCreCreditHead.getCity());

                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                msgMap.put("paramJson", JSON.toJSONString(paramMap));
                sendMap.put("msgMap", msgMap);
                // 激光推送
                sendMap.put("quart_message", "1");// 消息附加参数
                // 如果是消息中心
                sendMap.put("message_center", "1");

                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                sendMap.put("msg_extras", msg_extras);// 消息附加参数

                sendMap.put("is_oneself", "1");// 给业务员自己发送消息

                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getPersonnel_id().toString());
                paramMap.put("create_user_name", user.getPersonnel_name());
                paramMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                paramMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                paramMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                sendMap.put("msg_map", paramMap);// 极光推送和消息中心的消息内容参数
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if ("true".equals(vo.getPass()))
        {
            // 调用发送信息的接口出现异常 不会影响正常流程 --团队经理
            try
            {
                // 调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                // 参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                // 参数map
                Map<String, String> msg_extras = new HashMap<String, String>();

                sendMap.put("user_id", wmsCreCreditHead.getSalesman_id());
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                if (appro_limit != null)
                {
                    paramMap.put("appro_limit", appro_limit.toString());
                }
                // 消息中心使用
                sendMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                sendMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                sendMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());

                // 极光推送需要的数据参数
                sendMap.put("quart_message", "1");// 消息附加参数
                paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                paramMap.put("advice", vo.getAdvice());// 审核意见
                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                sendMap.put("msg_extras", msg_extras);// 消息附加参数
                // 发送消息中心 焦德龙
                // 如果是消息中心
                sendMap.put("message_center", "1");
                // 提交人
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getPersonnel_id().toString());
                paramMap.put("create_user_name", user.getPersonnel_name());
                paramMap.put("city", wmsCreCreditHead.getCity());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                sendMap.put("role_outside", "1");// 判断获取人
                List<String> post_number_list = new ArrayList<>();
                post_number_list.add("TDJL");
                sendMap.put("post_number_list", post_number_list);// 判断获取人
                sendMap.put("msg_code", "10020");// 消息编码
                sendMap.put("msg_code_role", "10020");// 消息编码
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            // 调用发送信息的接口出现异常 不会影响正常流程--门店经理
            try
            {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put("dept_id", wmsCreCreditHead.getSalesman_dept_id());
                deptMap.put("dept_level", "6");
                String dept_info = sysDeptDao.getDeptInfoForSendInfo(deptMap);
                // 调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                // 参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                // 参数map
                Map<String, String> msg_extras = new HashMap<String, String>();

                sendMap.put("user_id", wmsCreCreditHead.getSalesman_id());
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                if (appro_limit != null)
                {
                    paramMap.put("appro_limit", appro_limit.toString());
                }
                // 消息中心使用
                sendMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                sendMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                sendMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());

                // 极光推送需要的数据参数
                sendMap.put("quart_message", "1");// 消息附加参数
                paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                paramMap.put("advice", vo.getAdvice());// 审核意见
                paramMap.put("dept_info", dept_info);// 部门信息
                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                sendMap.put("msg_extras", msg_extras);// 消息附加参数
                sendMap.put("dept_info", dept_info);// 部门信息
                // 发送消息中心 焦德龙
                // 如果是消息中心
                sendMap.put("message_center", "1");
                // 提交人
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getPersonnel_id().toString());
                paramMap.put("create_user_name", user.getPersonnel_name());
                paramMap.put("city", wmsCreCreditHead.getCity());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                sendMap.put("role_outside", "1");// 判断获取人
                List<String> post_number_list = new ArrayList<>();
                post_number_list.add("MDJL");
                sendMap.put("post_number_list", post_number_list);// 判断获取人
                sendMap.put("msg_code", "10021");// 消息编码
                sendMap.put("msg_code_role", "10021");// 消息编码
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            // 调用发送信息的接口出现异常 不会影响正常流程--业务部
            try
            {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put("dept_id", wmsCreCreditHead.getSalesman_dept_id());
                deptMap.put("dept_level", "5,6");
                String dept_info = sysDeptDao.getDeptInfoForSendInfo(deptMap);
                // 调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                // 参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                // 参数map
                Map<String, String> msg_extras = new HashMap<String, String>();

                sendMap.put("user_id", wmsCreCreditHead.getSalesman_id());
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                if (appro_limit != null)
                {
                    paramMap.put("appro_limit", appro_limit.toString());
                }
                // 消息中心使用
                sendMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                sendMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                sendMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());

                // 极光推送需要的数据参数
                sendMap.put("quart_message", "1");// 消息附加参数
                paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                paramMap.put("advice", vo.getAdvice());// 审核意见
                paramMap.put("dept_info_md", dept_info);// 部门信息
                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                sendMap.put("msg_extras", msg_extras);// 消息附加参数
                sendMap.put("dept_info_md", dept_info);// 部门信息
                // 如果是消息中心
                sendMap.put("message_center", "1");
                // 提交人
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", user.getPersonnel_id().toString());
                paramMap.put("create_user_name", user.getPersonnel_name());
                paramMap.put("city", wmsCreCreditHead.getCity());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIS);
                sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                sendMap.put("role_value", "1");// 判断获取人
                sendMap.put("role_name", WmsHelp.YWB_ROLE_NAME);// 判断获取人
                sendMap.put("msg_code", "20012");// 消息编码
                sendMap.put("msg_code_role", "20012");// 消息编码
                sendMap.put("app_name", WmsHelp.APP_NAME_MIS);
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
	}
/**
 * 
 * @Title: getAgreeLoanApproApproveList
 * @Description: TODO(合同补充列表查询)
 * @param queryInfo
 * @param user
 * @return 
 * @author: jiaodelong
 * @time:2016年12月20日 下午2:46:49
 * @see com.zx.emanage.loanappro.service.IWmsCreCreditApproService#getAgreeLoanApproApproveList(com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO, com.zx.sframe.util.vo.UserBean)
 * history:
 * 1、2016年12月20日 jiaodelong 创建方法
 */
	@Override
	public Map<String, Object> getAgreeLoanApproApproveList(
			WmsCreApproSearchBeanVO queryInfo, UserBean user) {
		 Map<String, Object> paramMap = new HashMap<>();
		//paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "7");//合并俩个流程
//		paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "7",WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);//合并俩个流程
//		paramMap = wmsLoanWorkFlowService.setTaskList(paramMap,user.getUserId(), "7",WorkflowConstantHelp.UPHOUSINGLOANPROCESS);//合并俩个流程	
//		if (paramMap.get("idList") == null)
//		{
//			Map<String, Object> resultMap = new HashMap<String, Object>();
//			resultMap.put("Rows", new ArrayList<Map<String, Object>>());
//			return resultMap;
//		}
		
		if (StringUtil.isNotBlank(queryInfo.getBill_code()))
		{
			paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
		}
		if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
		{
			StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
			sb.append(" 23:59:59.9");
			paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
			paramMap.put("create_timestamp1", sb.toString());
		}
		if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
		{
			paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
			paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
		}
		if (StringUtil.isNotBlank(queryInfo.getId_card()))
		{
			paramMap.put("id_card", queryInfo.getId_card());
		}
		if (StringUtil.isNotBlank(user.getUser_regionNumber())) {//区域编码
            paramMap.put("create_user_city_code", user.getUser_regionNumber());
        }
		// 增加录入人城市编码
		paramMap.put("create_user_city_code", user.getUser_regionNumber());
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		paramMap.put("offset", queryInfo.getOffset());
		paramMap.put("pagesize", queryInfo.getPagesize());
		
		List<Map<String, Object>> list = wmscrecreditapproDao.searchforhouseprotocolList(paramMap);
		// 添加taskId
//		list = houseCreditWorkFlowService.addTaskIDHouse(list, (List<Integer>) paramMap.get("idList"),
//		(List<String>) paramMap.get("taskIdList"));
		GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
		                      queryInfo.getPage(),
		                      wmscrecreditapproDao.findCountforhouseprotocolList(paramMap),
		                      list);
		return bean.getGridData();
	}

	@Override
	public Map<String, Object> reviewRevisionOfHousingList(
			WmsCreCreditHeadVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	   	 if (StringUtil.isNotBlank(queryInfo.getBill_code()))
	        {
	            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
	        }
	   	  if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
	         {
	             paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
	         }
	         if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
	         {
	             paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	         }                   
	        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
	        {
	            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	        }
	        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
	        {
	            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
	        }
	        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone1()))
	        {
	            paramMap.put("mobile_telephone1",SysTools.getSqlLikeParam(queryInfo.getMobile_telephone1()));
	        }
	        paramMap.put("sortname", queryInfo.getSortname());
	        paramMap.put("sortorder", queryInfo.getSortorder());
	        paramMap.put("offset", queryInfo.getOffset());
	        paramMap.put("pagesize", queryInfo.getPagesize());
	        List<Map<String, Object>> list = wCreCreditHeadDao.getReviewRevisionOfHousingList(paramMap);
	        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
	                queryInfo.getPage(),
	                wCreCreditHeadDao.getReviewRevisionOfHousingListCount(paramMap),
	                list);
	        return bean.getGridData();
	}

	@Override
	public Map<String, Object> reviewRevisionOfHousingListOut(
			WmsCreCreditHeadVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	   	 if (StringUtil.isNotBlank(queryInfo.getBill_code()))
	        {
	            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
	        }
	   	  if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
	         {
	             paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
	         }
	         if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
	         {
	             paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
	         }                   
	        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
	        {
	            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	        }
	        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
	        {
	            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
	        }
	        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone1()))
	        {
	            paramMap.put("mobile_telephone1",queryInfo.getMobile_telephone1());
	        }
	        paramMap.put("sortname", queryInfo.getSortname());
	        paramMap.put("sortorder", queryInfo.getSortorder());
	        paramMap.put("Rows", wCreCreditHeadDao.getReviewRevisionOfHousingListOut(paramMap));
	        return paramMap;
	}

    /**
     * @Title: getSupplementAgreeLoanListWithoutPaging
     * @Description: TODO(合同补充数据导出)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2016年12月20日 下午2:45:47
     * @see com.zx.emanage.loanappro.service.IWmsCreCreditApproService#getSupplementAgreeLoanListWithoutPaging(com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月20日 jiaodelong 创建方法
    */
    @Override
    public Map<String, Object> getSupplementAgreeLoanListWithoutPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(user.getUser_regionNumber())) {//区域编码
            paramMap.put("create_user_city_code", user.getUser_regionNumber());
        }
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("Rows", wmscrecreditapproDao.searchforhouseprotocolList(paramMap));
        return paramMap;
    }

    /**
     * @Title: getcombinedLoanList
     * @Description: TODO(组合贷获取列表信息)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2016年12月27日 下午1:59:25
     * @see com.zx.emanage.loanappro.service.IWmsCreCreditApproService#getcombinedLoanList(com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月27日 jiaodelong 创建方法
    */
    @Override
    public Map<String, Object> getcombinedLoanList(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap =new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name_history()))
        {
            paramMap.put("customer_name_history", SysTools.getSqlLikeParam(queryInfo.getCustomer_name_history()));
        }
        if(StringUtils.isNotBlank(queryInfo.getBegin_time())) {
            paramMap.put("begin_time", queryInfo.getBegin_time() + " 00:00:00");
        }
        if(StringUtils.isNotBlank(queryInfo.getEnd_time())) {
            paramMap.put("end_time", queryInfo.getEnd_time() + " 23:59:59.9");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card_history()))
        {
            paramMap.put("id_card_history", SysTools.getSqlLikeParam(queryInfo.getId_card_history()));
        }
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        List<Map<String, Object>> list = wmscrecreditapproDao.getcombinedLoanList(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.findCountforgetcombinedLoan(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * @Title: getcombinedLoanListForIdCard
     * @Description: TODO(根据身份证号组合贷获取列表信息)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2016年12月27日 下午4:56:35
     * @see com.zx.emanage.loanappro.service.IWmsCreCreditApproService#getcombinedLoanListForIdCard(com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月27日 jiaodelong 创建方法
    */
    @Override
    public Map<String, Object> getcombinedLoanListForIdCard(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap =new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name_history()))
        {
            paramMap.put("customer_name_history", SysTools.getSqlLikeParam(queryInfo.getCustomer_name_history()));
        }
        if(StringUtils.isNotBlank(queryInfo.getBegin_time())) {
            paramMap.put("begin_time", queryInfo.getBegin_time() + " 00:00:00");
        }
        if(StringUtils.isNotBlank(queryInfo.getEnd_time())) {
            paramMap.put("end_time", queryInfo.getEnd_time() + " 23:59:59.9");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card_history()))
        {
            paramMap.put("id_card_history", SysTools.getSqlLikeParam(queryInfo.getId_card_history()));
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code_search()))
        {
            paramMap.put("bill_code_search", queryInfo.getBill_code_search());
        }
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());

        List<Map<String, Object>> list = wmscrecreditapproDao.getcombinedLoanListForIdCard(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.findCountForGetCombinedLoanListForIdCard(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * @Title: getcombinedLoanListAll
     * @Description: TODO(组合贷获取列表信息不含分页)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2017年1月3日 下午3:23:24
     * @see com.zx.emanage.loanappro.service.IWmsCreCreditApproService#getcombinedLoanListAll(com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年1月3日 jiaodelong 创建方法
    */
    @Override
    public Map<String, Object> getcombinedLoanListAll(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap =new HashMap<>();
        // 增加录入人城市编码
        paramMap.put("create_user_city_code", user.getUser_regionNumber());
        List<Map<String, Object>> list = wmscrecreditapproDao.getcombinedLoanListAll(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditapproDao.findCountforgetcombinedLoanAll(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * @Title: sendResultsLoanApprovalUpAgain
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param pass
     * @param advice
     * @param wms_cre_credit_head_id
     * @param userId
     * @param userName 
     * @author: jiaodelong
     * @time:2017年1月12日 下午3:23:52
     * @see com.zx.emanage.loanappro.service.IWmsCreCreditApproService#sendResultsLoanApprovalUpAgain(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
     * history:
     * 1、2017年1月12日 jiaodelong 创建方法
    */
    @Override
    public void sendResultsLoanApprovalUpAgain(String pass, String advice, String group_list, Integer userId, String userName)
    {
        Gson gson = new Gson();
        List<Integer> headIdList = gson.fromJson(group_list,new TypeToken<List<Integer>>(){}.getType());
        for(int i=0;i<headIdList.size();i++){
            //调用流程
            WmsHouseCreditWorkFlowVO vo = new WmsHouseCreditWorkFlowVO();
            vo.setWms_cre_credit_head_id(headIdList.get(i).toString());
            //核查结果
            if(pass.equals("1")) {
                vo.setPass("true");
            } else if(pass.equals("2")){
                vo.setPass("false");
            }else{
                throw new RuntimeException("pass参数错误！");
            }
            vo.setUserId(userId.toString());
            vo.setAdvice(advice);
            vo.setDebtkey("10");
            wmsLoanWorkFlowService.publicApprovalPhone(vo);
            
            
            WmsFinaCreLoanApp bean = new WmsFinaCreLoanApp();
            bean.setWms_cre_credit_head_id(headIdList.get(i));
            bean.setLoan_approval_user_id(userId);
            bean.setLoan_approval_user_name(userName);
            bean.setLoan_approval_result(pass);
            bean.setLoan_approval_advice(advice);
            bean.setLoan_approval_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsfinacreloanappservice.updateWmsFinaCreLoanApp(bean);
        }
    }

    /**
     * @Title: getHouseLoanApproVisaListWithPaging
     * @Description: TODO(终审审批-房贷终审-终审面签)
     * @param queryInfo
     * @param user
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午1:29:38
     * @see com.zx.emanage.loanappro.service.IWmsCreCreditApproService#getHouseLoanApproVisaListWithPaging(com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月21日 baisong 创建方法
    */
    @Override
    public Map<String, Object> getHouseLoanApproVisaListWithPaging(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();

        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        // 城市
        if (StringUtil.isNotBlank(queryInfo.getSalesman_city_code()))
        {
            paramMap.put("salesman_city_code", queryInfo.getSalesman_city_code());
        }
        // 客户姓名
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        // 客户姓名
        if (StringUtil.isNotBlank(queryInfo.getBill_status()))
        {
            paramMap.put("bill_status", queryInfo.getBill_status());
        }
        String mort_flag = queryInfo.getMort_flag();
        if (StringUtil.isNotBlank(mort_flag))
        {
            paramMap.put("mort_flag", mort_flag);// 抵押形式
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_ZSMQ_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        // 判断是否需要分页 0不需要
        if (!"0".equals(queryInfo.getIs_page()))
        {
            paramMap.put("offset", queryInfo.getOffset());
            paramMap.put("pagesize", queryInfo.getPagesize());
        }
        
        List<Map<String, Object>> list = wmscrecreditapproDao.searchforhousevisa(paramMap);
        // 判断是否需要分页 0不需要
        if ("0".equals(queryInfo.getIs_page()))
        {
            Map<String, Object> map = new HashMap<>();

            map.put("Rows", list);
            return map;
        }
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmscrecreditapproDao.findCountforhousevisa(paramMap), list);

            return bean.getGridData();
        }
}
