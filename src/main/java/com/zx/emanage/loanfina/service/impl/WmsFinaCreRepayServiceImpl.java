package com.zx.emanage.loanfina.service.impl;

/**
 * 版权所有 ：版权所有(C) 2014，沈阳新融金融信息服务有限公司
 * 文件名称 : WmsFinaCreRepayServiceImpl.Java
 * 系统名称 ：WMS
 * 模块名称 ：
 * 完成日期 ：2014-05-12 
 * 作    者    ：HANCD
 * 内容摘要 ：
 * 
 * 文件调用 ：
 * 修改记录 ：001
 * 修改时间 ：2015-01-09
 * 修 改 人   ：hancd
 * 修改内容 ：当放款确认的时候，向账单还款信息表中初始化p2p占用债权默认0
 * 关联BUG ：
 * 修改方法：
 */
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import com.zx.emanage.loanappro.persist.WmsCreApproBorrowProtocolDao;
import com.zx.emanage.loanappro.persist.WmsCreApproServiceProtocolDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditServiceTypeDao;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.loanappro.service.IWmsCreCreditApproService;
import com.zx.emanage.loanfina.persist.WmsFinaCreLoanAppAttDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreLoanAppDao;
import com.zx.emanage.loanfina.persist.WmsFinaCrePeriodRepayDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRealrepayInfoDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.loanfina.persist.WmsFinaTerminationContractDao;
import com.zx.emanage.loanfina.service.IWmsFinaCreRepayService;
import com.zx.emanage.loanfina.vo.WmsFinaCreAdvanceBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaCreCancelBeanVo;
import com.zx.emanage.loanfina.vo.WmsFinaCreLoanAppSearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaCrePeriodRepaySearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaySearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaGetInfoStopBeanVO;
import com.zx.emanage.loanpost.persist.WmsPostDunningHeadDao;
import com.zx.emanage.loanpost.service.IWmsPostLoanWorkFlowService;
import com.zx.emanage.loanpost.vo.WmsPostLoanWorkFlowBeanVO;
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneMainDao;
import com.zx.emanage.remind.persist.WmsCreCreditNotaryWarnDao;
import com.zx.emanage.remind.service.IWmsCreCreditNotaryWarnService;
import com.zx.emanage.remind.vo.CreditMessageToRepayWarnBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsCreApproServiceProtocol;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotaryWarn;
import com.zx.emanage.util.gen.entity.WmsCreCreditServiceType;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanApp;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanAppAtt;
import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.emanage.util.gen.entity.WmsFinaCreRealrepayInfo;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContract;
import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName: WmsFinaCreRepayServiceImpl
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年11月19日
 * @version V1.0 
 * history: 
 * 1、2016年11月19日 baisong 修改 放款确认的时候同步数据到还款提醒
 */
@Service("wmsfinacrerepayService")
public class WmsFinaCreRepayServiceImpl implements IWmsFinaCreRepayService
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCreRepayServiceImpl.class);

    @Autowired
    private WmsFinaCreLoanAppDao wmsfinacreloanappDao;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheadDao;

    @Autowired
    private WmsFinaCreRepayDao wmsfinacrerepayDao;

    @Autowired
    private WmsFinaCrePeriodRepayDao wmsfinacreperiodrepayDao;

    @Autowired
    private WmsCreApproBorrowProtocolDao wmscreapproborrowprotocolDao;// 借款合同

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private WmsCreApproServiceProtocolDao wmscreapproserviceprotocolDao;// 居间服务

    @Autowired
    private IWmsPostLoanWorkFlowService wmsPostLoanWorkFlowService;// 提前还款流程

    @Autowired
    private WmsSysPropertyDao wmssyspropertyDao;// 字典表 --用于获取字典表值

    @Autowired
    private WmsCreRevPhoneMainDao wmsCreRevPhoneMainDao;
    
    @Autowired
    private WmsPostDunningHeadDao dunningHeadDao;
  
    @Autowired
    private WmsFinaCreRealrepayInfoDao wmsFinaCreRealrepayInfoDao;
    @Autowired
	private WmsFinaTerminationContractDao wmsfinaterminationcontractDao;
	@Autowired
	private WmsFinaCrePeriodRepayDao wmsFinaCrePeriodRepayDao;//台账信息表
	@Autowired
	private  WmsCreCreditHeadDao wmsCreCreditHeadDao;
 	@Autowired
	private IWmsCarLoanWorkFlowService carLoanWorkFlowService;//流程
    @Autowired
    private IWmsCreCreditApproService wmscrecreditapproService;//用于在放款成功后将客户信息传递给PTP
    @Autowired
    private IWmsLoanWorkFlowService wmsLoanWorkFlowService;//新房贷贷款流程
    @Autowired
    private IWmsCreCreditNotaryWarnService wmsCreCreditNotaryWarnService;//公正还款提醒
    @Autowired
    private WmsFinaCreLoanAppAttDao wmsfinacreloanappattDao;
    @Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;
    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;
    @Autowired
    private WmsCreCreditServiceTypeDao wmsCreCreditServiceTypeDao;// 贷款还款中间表
    @Autowired
    private WmsCreCreditNotaryWarnDao wmsCreCreditNotaryWarnDao;// 还款表
    @Autowired
    private PmPersonnelDao pmPersonnelDao;
    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private WmsFinaCreLoanAppDao wmsFinaCreLoanAppDao;
    
    @Override
    public Map<String, Object> getListWithoutPaging(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 查询代办任务
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 流程key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.BEFOREREPAYPROCESS);
        // 流程节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.BEFOREREPAYPROCESS_CXSQ);
        // 当前登录id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 查找代办任务
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        // 查找对应流程节点里面的主表单id添加到idList集合里面
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = wmsfinacrerepayDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    /**
     * 用于提前还款导出excel baisong
     */
    @Override
    public Map<String, Object> getListWithoutPagingforadv(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 1.查询是财务部门主管cwbmzg
        Map<String, Object> userIds = new HashMap<String, Object>();
        userIds.put("user_id", String.valueOf(user.getUserId()));
        List<String> workflowid = sysUserRoleDao.searchforbeforeortransfer(userIds);
        // 查询代办任务
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 提前还款审核流程定义key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.BEFOREREPAYPROCESS);
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        if (WorkflowConstantHelp.CWBMZG.equals(workflowid.get(0)))
        {
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.BEFOREREPAYPROCESS_CWSP);
        }
        // 2.根据不同流程节点 用户ID 节点流程实例key
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> reammap = new HashMap<String, Object>();
            return reammap;
        }
        for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = wmsfinacrerepayDao.search2(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }
    /**
     * 提前还款审核导出功能
     */
    @Override
    public Map<String, Object> getListWithoutPagingForAdd(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<Integer> idList = new ArrayList<Integer>();
        // 1.通过用户id 查询是否是销售部主管xsglbzg 还是货后部主管dhbmzg
        Map<String, Object> userIds = new HashMap<String, Object>();
        userIds.put("user_id", String.valueOf(user.getUserId()));
        List<String> workflowid = sysUserRoleDao.searchforbeforeortransfer(userIds);
        if(workflowid.size()==1&&(workflowid.get(0).equals("xsglbzg")||workflowid.get(0).equals("dhbmzg"))){
            // 查询代办任务
            WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
            // 提前还款审核流程定义key
            workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.BEFOREREPAYPROCESS);
            workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
            if (WorkflowConstantHelp.XSGLBZG.equals(workflowid))
            {// 销售管理部门主管
                // 定义节点
                workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.BEFOREREPAYPROCESS_XSSP);
            }
            else if (WorkflowConstantHelp.DHBMZG.equals(workflowid))
            {// 贷后部门主管
                // 定义节点
                workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.BEFOREREPAYPROCESS_DHSP);
            }
            List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
            idList = new ArrayList<Integer>();
            if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
            {
                Map<String, Object> aMap = new HashMap<String, Object>();
                return aMap;
            }
            for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
            {
                idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
            }
            paramMap.put("idList", idList);
            List<Map<String, Object>> list = wmsfinacrerepayDao.search2(paramMap);
            resMap.put("Rows", list);
        }else if(workflowid.size()>1){
            boolean flag =false;
            for(int i=0;i<workflowid.size();i++){
                if(workflowid.get(i).equals("xsglbzg")||workflowid.get(i).equals("dhbmzg")){
                    flag=true;
                }
            }
            //说明该角色是销售部门主管和贷后部门主管
            if(flag){
                WorkflowSearchInfoHelp workflowSearchInfoHelp1 = new WorkflowSearchInfoHelp();
                workflowSearchInfoHelp1.setProcessDefinitionKey(WorkflowConstantHelp.BEFOREREPAYPROCESS);
                workflowSearchInfoHelp1.setUserId(String.valueOf(user.getUserId()));
                List<WorkflowInfoHelp> workflowInfoHelps1 = workflowService.findPackageTodoTasks(workflowSearchInfoHelp1);
                idList = new ArrayList<Integer>();
                if (workflowInfoHelps1 == null || workflowInfoHelps1.size() == 0)
                {
                    Map<String, Object> reammap = new HashMap<String, Object>();
                    return reammap;
                }
                for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps1)
                {
                    idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
                }
                
                WorkflowSearchInfoHelp workflowSearchInfoHelp2 = new WorkflowSearchInfoHelp();
                workflowSearchInfoHelp2.setProcessDefinitionKey(WorkflowConstantHelp.BEFOREREPAYPROCESS);
                workflowSearchInfoHelp2.setUserId(String.valueOf(user.getUserId()));
                List<WorkflowInfoHelp> workflowInfoHelps2 = workflowService.findPackageTodoTasks(workflowSearchInfoHelp2);
                if (workflowInfoHelps2 == null || workflowInfoHelps2.size() == 0)
                {
                    Map<String, Object> reammap = new HashMap<String, Object>();
                    return reammap;
                }
                for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps2)
                {
                    idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
                }
                paramMap.put("idList", idList);
                List<Map<String, Object>> list = wmsfinacrerepayDao.search2(paramMap);
                workflowInfoHelps1.addAll(workflowInfoHelps2);//合并两个结果集
                resMap.put("Rows", list);
        }
    }
        return resMap;
}

    @Override
    public Map<String, Object> getListWithPaging(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        // 查询代办任务
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 流程key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.BEFOREREPAYPROCESS);
        // 流程节点
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.BEFOREREPAYPROCESS_CXSQ);
        // 当前登录id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // 查找代办任务
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        // 查找对应流程节点里面的主表单id添加到idList集合里面
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = wmsfinacrerepayDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsfinacrerepayDao.findCount(paramMap),
                                                                                       list);
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
     * 用于提前还款查询列表 baisong
     */
    @Override
    public Map<String, Object> getListWithPagingforadv(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        // 1.财务主管
        Map<String, Object> userIds = new HashMap<String, Object>();
        userIds.put("user_id", String.valueOf(user.getUserId()));
        List<String> workflowid = sysUserRoleDao.searchforbeforeortransfer(userIds);
        // 查询代办任务
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 提前还款审核流程定义key
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.BEFOREREPAYPROCESS);
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        // if (WorkflowConstantHelp.CWBMZG.equals(workflowid.get(0)))
        if (workflowid.contains(WorkflowConstantHelp.CWBMZG))
        {
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.BEFOREREPAYPROCESS_CWSP);
        }
        // 2.根据不同流程节点 用户ID 节点流程实例key
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
        {
            Map<String, Object> reammap = new HashMap<String, Object>();
            return reammap;
        }
        for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = wmsfinacrerepayDao.search2(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsfinacrerepayDao.findCount2(paramMap),
                                                                                       list);
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

    // 用于逾期查询列表baisong
    @Override
    public Map<String, Object> getListWithPagingforyuqi(WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap =new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (StringUtil.isNotBlank(queryInfo.getDunning_status()) && !"-1".equals(queryInfo.getDunning_status()))
        {
            paramMap.put("dunning_status", queryInfo.getDunning_status());
        }
        if (queryInfo.getRepayment_date_start()!=null)
        {
            paramMap.put("repayment_date_start", queryInfo.getRepayment_date_start());
        }
        if (queryInfo.getRepayment_date_end()!=null)
        {
            paramMap.put("repayment_date_end", queryInfo.getRepayment_date_end());
        }
        if (queryInfo.getCurrent_repay_date_start()!=null)
        {
            paramMap.put("current_repay_date_start", queryInfo.getCurrent_repay_date_start());
        }
        if (queryInfo.getCurrent_repay_date_end()!=null)
        {
            paramMap.put("current_repay_date_end", queryInfo.getCurrent_repay_date_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsfinacrerepayDao.searchforyuqi(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsfinacrerepayDao.findCountforyuqi(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 实现正常还款查询列表显示
     */
    @Override
    public Map<String, Object> getListWithPagingfornormal(WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (queryInfo.getRepayment_date_start()!=null)
        {
            paramMap.put("repayment_date_start", queryInfo.getRepayment_date_start());
        }
        if (queryInfo.getRepayment_date_end()!=null)
        {
            paramMap.put("repayment_date_end", queryInfo.getRepayment_date_end());
        }
        if (queryInfo.getCurrent_repay_date_start()!=null)
        {
            paramMap.put("current_repay_date_start", queryInfo.getCurrent_repay_date_start());
        }
        if (queryInfo.getCurrent_repay_date_end()!=null)
        {
            paramMap.put("current_repay_date_end", queryInfo.getCurrent_repay_date_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsfinacrerepayDao.searchfornormal(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsfinacrerepayDao.findCountfornormal(paramMap),
                                                                                       list);
        return bean.getGridData();
    }
    /**
     * 正常还款确认导出excel baisong
     */
    @Override
    public Map<String, Object> getListWithoutPagingfornormal(WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (queryInfo.getRepayment_date_start()!=null)
        {
            paramMap.put("repayment_date_start", queryInfo.getRepayment_date_start());
        }
        if (queryInfo.getRepayment_date_end()!=null)
        {
            paramMap.put("repayment_date_end", queryInfo.getRepayment_date_end());
        }
        if (queryInfo.getCurrent_repay_date_start()!=null)
        {
            paramMap.put("current_repay_date_start", queryInfo.getCurrent_repay_date_start());
        }
        if (queryInfo.getCurrent_repay_date_end()!=null)
        {
            paramMap.put("current_repay_date_end", queryInfo.getCurrent_repay_date_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsfinacrerepayDao.searchfornormal(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }
    @Override
    public Map<String, Object> getListWithPagingForAdd(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        // 1.通过用户id 查询是否是销售部主管xsglbzg 还是货后部主管dhbmzg结果集
        Map<String, Object> userIds = new HashMap<String, Object>();
        userIds.put("user_id", String.valueOf(user.getUserId()));
        List<String> workflowid = sysUserRoleDao.searchforbeforeortransfer(userIds);
        // // 如果是财务部门主管则直接返回
        // if (workflowid.contains(WorkflowConstantHelp.CWBMZG))
        // {
        // Map<String, Object> reammap = new HashMap<String, Object>();
        // return reammap;
        // }
        List<Integer> idList = new ArrayList<Integer>();
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>();
        //如果查询的结果为1 并且 是销售部门主管或者贷后部主管中扥任意一种
        if(workflowid.size()==1&&(workflowid.get(0).equals("xsglbzg")|| workflowid.get(0).equals("dhbmzg")))
        {
            // 查询代办任务
            WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
            // 提前还款审核流程定义key
            workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.BEFOREREPAYPROCESS);
            workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
            if (WorkflowConstantHelp.XSGLBZG.equals(workflowid))
            {// 销售管理部门主管
                // 定义节点
                workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.BEFOREREPAYPROCESS_XSSP);
            }
            else if (WorkflowConstantHelp.DHBMZG.equals(workflowid))
            {// 贷后部门主管
                // 定义节点
                workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.BEFOREREPAYPROCESS_DHSP);
            }
            List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
            idList = new ArrayList<Integer>();
            if (workflowInfoHelps == null || workflowInfoHelps.size() == 0)
            {
                Map<String, Object> reammap = new HashMap<String, Object>();
                return reammap;
            }
            for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps)
            {
                idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
            }
            paramMap.put("idList", idList);
            List<Map<String, Object>> list = wmsfinacrerepayDao.search2(paramMap);
            bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(),wmsfinacrerepayDao.findCount2(paramMap),list);
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
        }
        else if(workflowid.size()>1)//说明该ID同时具有多个角色 分别用两个节点查询的数据合并输出
        {
            boolean flag =false;
            for(int i=0;i<workflowid.size();i++){
                if(workflowid.get(i).equals("xsglbzg")||workflowid.get(i).equals("dhbmzg")){
                    flag=true;
                }
            }
            //说明该角色是销售部门主管和贷后部门主管
            if(flag){
                WorkflowSearchInfoHelp workflowSearchInfoHelp1 = new WorkflowSearchInfoHelp();
                workflowSearchInfoHelp1.setProcessDefinitionKey(WorkflowConstantHelp.BEFOREREPAYPROCESS);
                workflowSearchInfoHelp1.setUserId(String.valueOf(user.getUserId()));
                List<WorkflowInfoHelp> workflowInfoHelps1 = workflowService.findPackageTodoTasks(workflowSearchInfoHelp1);
                idList = new ArrayList<Integer>();
                if (workflowInfoHelps1 == null || workflowInfoHelps1.size() == 0)
                {
                    Map<String, Object> reammap = new HashMap<String, Object>();
                    return reammap;
                }
                for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps1)
                {
                    idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
                }
                
                WorkflowSearchInfoHelp workflowSearchInfoHelp2 = new WorkflowSearchInfoHelp();
                workflowSearchInfoHelp2.setProcessDefinitionKey(WorkflowConstantHelp.BEFOREREPAYPROCESS);
                workflowSearchInfoHelp2.setUserId(String.valueOf(user.getUserId()));
                List<WorkflowInfoHelp> workflowInfoHelps2 = workflowService.findPackageTodoTasks(workflowSearchInfoHelp2);
                if (workflowInfoHelps2 == null || workflowInfoHelps2.size() == 0)
                {
                    Map<String, Object> reammap = new HashMap<String, Object>();
                    return reammap;
                }
                for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps2)
                {
                    idList.add(Integer.valueOf(workflowinfohelp.getBusinessKey()));
                }
                paramMap.put("idList", idList);
                List<Map<String, Object>> list = wmsfinacrerepayDao.search2(paramMap);
                bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(),wmsfinacrerepayDao.findCount2(paramMap),list);
                workflowInfoHelps1.addAll(workflowInfoHelps2);//合并两个结果集
                for (Map<String, Object> map : list)
                {
                    Integer wms_cre_credit_head_id = (Integer) map.get("wms_cre_credit_head_id");
                    for (WorkflowInfoHelp workflowinfohelp : workflowInfoHelps1)
                    {
                        if (Integer.valueOf(workflowinfohelp.getBusinessKey()).compareTo(wms_cre_credit_head_id) == 0)
                        {
                            map.put("taskId", workflowinfohelp.getTaskId());
                            break;
                        }
                    }
                }
            }
        }
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getInfoByPK(Integer wms_fina_cre_pay_id)
    {
        Map<String, Object> resultMap = wmsfinacrerepayDao.getInfoByPK(wms_fina_cre_pay_id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        resultMap.put("current_repay_date", sdf.format(new Date(System.currentTimeMillis())));
        return resultMap;
    }

    @Override
    @Transactional
    public String save(WmsFinaCreRepay bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsfinacrerepayDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }
	/**
	 * 终止合同确认 ---废弃
	 * baisong
	 */
    @Override
    @Transactional
    public String update(WmsFinaCreRepay bean, UserBean user,WmsFinaTerminationContract tc)
    {
        String resStr = "success";
        int ret = 0;
        ////计算应还款表和期还款台账表wms_cre_credit_head_id
        WmsFinaCreRealrepayInfo rinfo=new WmsFinaCreRealrepayInfo();
        rinfo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//根据id查询所有对应的应还款表
        List<WmsFinaCreRealrepayInfo> listr= wmsFinaCreRealrepayInfoDao.getListByEntity(rinfo);//应还款表中所有期的信息
        if(listr==null||listr.size()<1){
        	resStr = "error";
        	return resStr;
        }
        WmsFinaCrePeriodRepay periodRepay=new WmsFinaCrePeriodRepay();
        periodRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//根据id查询所有对应的期还款台账
        List<WmsFinaCrePeriodRepay> listp= wmsFinaCrePeriodRepayDao.getListByEntity(periodRepay);//期还款台账表对应所有期数信息
        if(listp==null||listp.size()<1){
        	resStr = "error";
        	return resStr;
        }
        BigDecimal amount_m=new BigDecimal(0);//滞纳金
        bean= wmsfinacrerepayDao.get(bean.getWms_fina_cre_pay_id());//根据id查询
        BigDecimal period=new BigDecimal(0);
        if(bean.getRepay_period()>0){
        	listr.get(bean.getRepay_period()-1).getAdjustment_amount();
        }
        BigDecimal amount=tc.getTotal_amount_repayment().add(period);//总计收款金额+上期调整额    的总和
        bean.setClean_up_status("4");//存档结清
        bean.setClean_up_date(new java.sql.Date(System.currentTimeMillis()));
        //平账--贷款还款信息表
        if(amount.compareTo(bean.getUn_pay_principal().add(bean.getUn_pay_interest()))<0){  //如果总计收款金额小于未还本金加上利息的和 则错误
        	resStr = "erroramount";//总计收款金额错误
        	return resStr;
        }
        amount=amount.subtract(bean.getUn_pay_principal().add(bean.getUn_pay_interest()));//收款金额去掉本金利息后剩余金额
        bean.setRepay_period(bean.getBorrow_deadline());//已还期数
        bean.setRepay_principal(bean.getPrincipal());//已还本金
        bean.setRepay_interest(bean.getInterest());//已还利息
        bean.setUn_pay_period(0);//未还期数
        bean.setUn_pay_principal(new BigDecimal(0));//剩余本金
        bean.setUn_pay_interest(new BigDecimal(0));//剩余利息
        if(bean.getCur_late_fee().compareTo(amount)>-1){//如果滞纳金 大于还款滞纳金则下面处理
        	bean.setCur_late_fee(bean.getCur_late_fee().subtract(amount));//本期滞纳金额
        	bean.setTotal_pay_late_fee(bean.getTotal_pay_late_fee().add(amount));//已缴纳滞纳金额
        	amount_m=amount;
        }else{
        	bean.setTotal_pay_late_fee(bean.getTotal_pay_late_fee().add(bean.getCur_late_fee()));//已缴纳滞纳金额
        	amount_m=bean.getCur_late_fee();
        	bean.setCur_late_fee(new BigDecimal(0));//本期滞纳金额
        }
        bean.setTotal_derate(tc.getTotal_derate());//总减免额
        ret = wmsfinacrerepayDao.update(bean); // update a record replace                                     // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
            return resStr;
        }
        ret =wmsfinaterminationcontractDao.update(tc);//终止合同表
        if (ret == 0)
        {
        	resStr = "error";
        	return resStr;
        }

        BigDecimal total_derate=new BigDecimal(0);
         for(int i=bean.getRepay_period();i<bean.getBorrow_deadline();i++){
        	rinfo=listr.get(i);//应还款表
        	periodRepay=listp.get(i);//期还款台账表
        	rinfo.setRepay_principal(rinfo.getOrg_repay_principal());//已还本金=应还款本金
        	rinfo.setUn_repay_principal(new BigDecimal(0));//未还本金
        	rinfo.setRepay_interest(rinfo.getOrg_repay_interest());//已还利息=应还款利息
        	rinfo.setUn_repay_interest(new BigDecimal(0));//未还利息
        	periodRepay.setRepay_date(new Date(System.currentTimeMillis()));//实际还款日期
        	periodRepay.setRepay_total(periodRepay.getOrg_repay_principal().add(periodRepay.getOrg_repay_interest()));//实际还款总额
        	periodRepay.setRepay_principal(periodRepay.getOrg_repay_principal());//实际还款本金
        	periodRepay.setRepay_interest(periodRepay.getOrg_repay_interest());//实际还款利息
        	periodRepay.setIs_upload("2");//是否上传
        	periodRepay.setIs_overdue("1");//设置为逾期
        	if(i==bean.getRepay_period()){
	        	rinfo.setCur_late_fee(amount_m);//已还滞纳金
	        	rinfo.setTotal_derate(rinfo.getTotal_derate().add(tc.getTotal_derate()));//历史减免额
	        	total_derate=rinfo.getTotal_derate();
	        	periodRepay.setCur_late_fee(amount_m);//滞纳金额
	        	periodRepay.setDerate(rinfo.getTotal_derate());
	        	rinfo.setIs_total_repayment(rinfo.getOrg_repay_principal().add(rinfo.getOrg_repay_interest()).add(amount_m));//本期已还总额
	        	rinfo.setun_total_repayment(rinfo.getTotal_repayment().subtract(rinfo.getIs_total_repayment()));//本期未还总额
        	}else{
        		rinfo.setCur_late_fee(new BigDecimal(0));//已还滞纳金
        		rinfo.setTotal_derate(total_derate);//历史减免额
        		periodRepay.setCur_late_fee(new BigDecimal(0));//滞纳金额
	        	periodRepay.setDerate(new BigDecimal(0));//减免金额
	        	rinfo.setIs_total_repayment(rinfo.getTotal_repayment());//本期已还总额
	        	rinfo.setun_total_repayment(new BigDecimal(0));//本期未还总额
        	}
        	wmsFinaCreRealrepayInfoDao.update(rinfo);//更新
        	wmsFinaCrePeriodRepayDao.updateforv(periodRepay);//更新
        }
        return resStr;
    }
    /**
	 * 终止合同确认 ---废弃
	 * baisong
	 */
    @Override
    @Transactional
    public String update_new(WmsFinaCreRepay bean, UserBean user,WmsFinaTerminationContract tc)
    {
        String resStr = "success";
        int ret = 0;
        ////计算应还款表和期还款台账表wms_cre_credit_head_id
        WmsFinaCreRealrepayInfo rinfo=new WmsFinaCreRealrepayInfo();
        rinfo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//根据id查询所有对应的应还款表
        List<WmsFinaCreRealrepayInfo> listr= wmsFinaCreRealrepayInfoDao.getListByEntity(rinfo);//应还款表中所有期的信息
        if(listr==null||listr.size()<1){
        	resStr = "error";
        	return resStr;
        }
        WmsFinaCrePeriodRepay periodRepay=new WmsFinaCrePeriodRepay();
        periodRepay.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());//根据id查询所有对应的期还款台账
        List<WmsFinaCrePeriodRepay> listp= wmsFinaCrePeriodRepayDao.getListByEntity(periodRepay);//期还款台账表对应所有期数信息
        if(listp==null||listp.size()<1){
        	resStr = "error";
        	return resStr;
        }
        BigDecimal amount_m=new BigDecimal(0);//滞纳金
        bean= wmsfinacrerepayDao.get(bean.getWms_fina_cre_pay_id());//根据id查询
        BigDecimal period=new BigDecimal(0);
        if(bean.getRepay_period()>0){
        	listr.get(bean.getRepay_period()-1).getAdjustment_amount();
        }
        BigDecimal amount=tc.getCash_payments().add(tc.getMortgage_sums()).add(period);//现金收款+抵账金额+上期调整额    的总和 是本次平账数值
        bean.setClean_up_status("4");//存档结清
        bean.setClean_up_date(new java.sql.Date(System.currentTimeMillis()));
        //平账--贷款还款信息表
        if(amount.compareTo(bean.getUn_pay_principal().add(bean.getUn_pay_interest()))<0){  //如果总计收款金额小于未还本金加上利息的和 则错误
        	amount_m=tc.getTotal_derate();//减免额
        }else{
        	amount_m=tc.getTotal_derate().add(amount.subtract(bean.getUn_pay_principal().add(bean.getUn_pay_interest())));
        }
        //amount=amount.subtract(bean.getUn_pay_principal().add(bean.getUn_pay_interest()));//收款金额去掉本金利息后剩余金额
        if(bean.getCur_late_fee().compareTo(amount)>-1){//如果滞纳金 大于还款滞纳金则下面处理
        	bean.setCur_late_fee(bean.getCur_late_fee().subtract(amount));//本期滞纳金额
        	bean.setTotal_pay_late_fee(bean.getTotal_pay_late_fee().add(amount));//已缴纳滞纳金额
        	amount_m=amount;
        }else{
        	bean.setTotal_pay_late_fee(bean.getTotal_pay_late_fee().add(bean.getCur_late_fee()));//已缴纳滞纳金额
        	amount_m=bean.getCur_late_fee();
        	bean.setCur_late_fee(new BigDecimal(0));//本期滞纳金额
        }
        bean.setTotal_derate( bean.getTotal_derate().add(tc.getTotal_derate()));//总减免额
        
        ret =wmsfinaterminationcontractDao.update(tc);//终止合同表
        if (ret == 0)
        {
        	resStr = "error";
        	return resStr;
        }
        BigDecimal total_derate=new BigDecimal(0);
        BigDecimal principal=new BigDecimal(0);//本金
        BigDecimal interest=new BigDecimal(0);//利息
        int per=bean.getRepay_period();//已还期数
         for(int i=per;i<bean.getBorrow_deadline();i++){
        	if(amount.compareTo(new BigDecimal(0))<1){//如果金额小于0则返回
        		break; 
        	}
        	rinfo=listr.get(i);//应还款表
        	periodRepay=listp.get(i);//期还款台账表
        	periodRepay.setRepay_date(new Date(System.currentTimeMillis()));//实际还款日期
        	amount=amount.add(rinfo.getRepay_principal().add(rinfo.getRepay_interest()));
        	if(amount.compareTo(periodRepay.getOrg_repay_principal())>-1){//本金
        		principal=periodRepay.getOrg_repay_principal();
        		amount=amount.subtract(periodRepay.getOrg_repay_principal());
        	}else{
        		principal=amount;
        		amount=new BigDecimal(0);//赋值0
        	}
        	if(amount.compareTo(periodRepay.getOrg_repay_interest())>-1){//利息
        		interest=periodRepay.getOrg_repay_interest();
        		amount=amount.subtract(periodRepay.getOrg_repay_interest());
        	}else{
        		interest=amount;
        		amount=new BigDecimal(0);//赋值0
        	}
            //期还款台账
        	periodRepay.setRepay_total(principal.add(interest));//实际还款总额
        	periodRepay.setRepay_principal(principal);//实际还款本金
        	periodRepay.setRepay_interest(interest);//实际还款利息
        	periodRepay.setIs_upload("2");//是否上传
        	periodRepay.setIs_overdue("1");//设置为逾期
        	if(rinfo.getRepay_no_count()!=null&&!"".equals(rinfo.getRepay_no_count())){
        		if(rinfo.getRepay_no_count()>1){
        			rinfo.setOrg_repay_principal(periodRepay.getOrg_repay_principal());//本金
        			rinfo.setUn_repay_principal(rinfo.getOrg_repay_principal().subtract(rinfo.getRepay_principal()));//本金
        			rinfo.setOrg_repay_interest(rinfo.getOrg_repay_interest().subtract(periodRepay.getOrg_repay_interest().multiply(new BigDecimal(rinfo.getRepay_no_count()-1))));//利息
        			rinfo.setUn_repay_interest(rinfo.getOrg_repay_interest().subtract(rinfo.getRepay_interest()));//lixi 
        			rinfo.setTotal_repayment(rinfo.getOrg_repay_principal().add(rinfo.getOrg_repay_interest()).add(rinfo.getTotal_cur_late_fee()));//总
        			rinfo.setun_total_repayment(rinfo.getTotal_repayment().subtract(rinfo.getIs_total_repayment()));//未还
        			rinfo.setRepay_no_count(1);//合并逾期期数和
        			rinfo.setRepay_no_detail("");//合并逾期期数明细
        		}
        	}
        	rinfo.setRepay_principal(principal);//已还本金
        	rinfo.setUn_repay_principal(rinfo.getUn_repay_principal().subtract(principal));//未还本金
        	rinfo.setRepay_interest(interest);//已还利息
        	rinfo.setUn_repay_interest(rinfo.getUn_repay_interest().subtract(interest));//未还利息
        	if(i==per){
	        	rinfo.setCur_late_fee(amount_m);//已还滞纳金
	        	rinfo.setTotal_derate(rinfo.getTotal_derate().add(tc.getTotal_derate()));//历史减免额
	        	total_derate=rinfo.getTotal_derate();
	        	periodRepay.setCur_late_fee(amount_m);//滞纳金额
	        	periodRepay.setDerate(rinfo.getTotal_derate());
	        	rinfo.setIs_total_repayment(rinfo.getIs_total_repayment().add(rinfo.getRepay_principal()).add(rinfo.getRepay_interest()).add(amount_m));//本期已还总额
	        	rinfo.setun_total_repayment(rinfo.getTotal_repayment().subtract(rinfo.getIs_total_repayment()));//本期未还总额
        	}else{
        		rinfo.setCur_late_fee(new BigDecimal(0));//已还滞纳金
        		rinfo.setTotal_derate(total_derate);//历史减免额
        		periodRepay.setCur_late_fee(new BigDecimal(0));//滞纳金额
	        	periodRepay.setDerate(new BigDecimal(0));//减免金额
	        	rinfo.setIs_total_repayment(rinfo.getRepay_principal().add(rinfo.getRepay_interest()));//本期已还总额
	        	rinfo.setun_total_repayment(rinfo.getTotal_repayment().subtract(rinfo.getIs_total_repayment()));//本期未还总额
        	}
        	wmsFinaCreRealrepayInfoDao.update(rinfo);//更新
        	wmsFinaCrePeriodRepayDao.updateforv(periodRepay);//更新
        	//贷款还款信息表
            bean.setRepay_period(bean.getRepay_period()+1);//已还期数
            bean.setRepay_principal(bean.getRepay_principal().add(principal));//已还本金
            bean.setRepay_interest(bean.getRepay_interest().add(interest));//已还利息
            bean.setUn_pay_period(bean.getUn_pay_period()-1);//未还期数
            bean.setUn_pay_principal(bean.getUn_pay_principal().subtract(principal));//剩余本金
            bean.setUn_pay_interest(bean.getUn_pay_interest().subtract(interest));//剩余利息
        }
         ret = wmsfinacrerepayDao.update(bean); // update a record replace                                     // columns, nonsupport null val
         if (ret == 0)
         {
             resStr = "error";
             return resStr;
         }
        return resStr;
    }
    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsFinaCreRepay() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsFinaCreRepay> getListByEntity(WmsFinaCreRepay queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsFinaCreRepay> beanList = wmsfinacrerepayDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 获取逾期还款确认信息
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerInfoVO
     * @author baisong
     */
    @Override
    public WmsFinaCrePeriodRepaySearchBeanVO getInfoByFK(Integer wms_cre_credit_head_id)
    {

        WmsFinaCrePeriodRepaySearchBeanVO bean = wmsfinacrerepayDao.getbyfk2(wms_cre_credit_head_id);
        return bean;
    }

    /**
     * 获取正常还款确认信息
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerInfoVO
     * @author baisong
     */
    @Override
    public Map<String,Object> getInfoByFKForNor(Integer wms_cre_credit_head_id)
    {
    	Map<String,Object> bean = wmsfinacrerepayDao.getbyfkfornor(wms_cre_credit_head_id);
        return bean;
    }

    /**
     * 逾期还款确认保存
     * 
     * @param primary key
     * @return
     * @author baisong
     */
    @Override
    @Transactional
    public String updateInfo(WmsFinaCrePeriodRepaySearchBeanVO bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        //系统当前日期
        Date nowDate= getNow();
        //根据传递的单据主键获取贷款还款表信息
        WmsFinaCreRepay repay = wmsfinacrerepayDao.getbyid(bean.getWms_cre_credit_head_id());
        //获取减免额
        BigDecimal derate = bean.getDerate();
        //根据专递的单据主键获取期还款台账该单据的详细台账信息
        List<WmsFinaCrePeriodRepay> list = wmsfinacreperiodrepayDao.getbyfk(bean.getWms_cre_credit_head_id());
        //计算逾期期数
        int sum=list.size();
        for(int i = 0;i < sum ; i++){
             WmsFinaCrePeriodRepay period=(WmsFinaCrePeriodRepay)list.get(i);
             //滞纳金和逾期天数计算等
             setInfo(nowDate,sum,i, list, repay,derate,period);
             //保存五个必要信息 创建人等
             saveRequP(period,user);
            //更新数据库
             wmsfinacreperiodrepayDao.update(period);
        }
        // 贷款还款信息表
        repay.setRepay_period(repay.getRepay_period() + sum);// 已还期数
        repay.setUn_pay_period(repay.getUn_pay_period() - sum);// 未还期数
        repay.setTotal_overdue_period(repay.getTotal_overdue_period() + sum);// 总逾期数
        repay.setTotal_pay_late_fee(repay.getTotal_pay_late_fee().add(bean.getCur_late_fee()));// 已缴纳的滞纳金额
        repay.setTotal_derate(repay.getTotal_derate().add(bean.getDerate()));// 总减免额
        if (repay.getUn_pay_period() > 0)
        {
            WmsFinaCrePeriodRepay cpr = new WmsFinaCrePeriodRepay();
            cpr.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());// 存入id
            cpr.setRepay_no(repay.getRepay_period() + 1);// 应还款期数
            cpr = wmsfinacreperiodrepayDao.getbyobject(cpr);// 获取下期还款信息
                                                            // 从中获取到贷款还款信息表中的本期还款日期
            repay.setCurrent_repay_date(cpr.getCurrent_repay_date());// 本期应还款日期
        }
        repay.setIs_upload("2");// 如果为结清状态 是否上传为2
        getRepay(repay, bean, user, nowDate);// 公共方法 集中处理 为repay更改数据
        if(bean.getWms_post_dunning_head_id()!=null){//修改催缴单状态为已完成
            Map<String,Object> paMap = new HashMap<>();
            paMap.put("wms_post_dunning_head_id",bean.getWms_post_dunning_head_id());
            paMap.put("dunning_status", 6);
            dunningHeadDao.updateReocod(paMap);
        }
        return resStr;
    }

    /**
     * 正常还款确认保存
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerInfoVO
     * @author baisong
     */
    @Override
    @Transactional
    public String updateInfoForNor(WmsFinaCrePeriodRepaySearchBeanVO bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        // 系统当前日期
        Date nowDate = getNow();

        WmsFinaCreRepay repay = wmsfinacrerepayDao.getbyid(bean.getWms_cre_credit_head_id());// 获取贷款还款表信息repay
        WmsFinaCrePeriodRepay period = wmsfinacreperiodrepayDao.getbyfkfornor(bean.getWms_cre_credit_head_id());// 获取期还款台账信息
        // 期还款台账表
        period.setRepay_date(nowDate);// 实际还款时间
        period.setRepay_principal(period.getOrg_repay_principal());// 应还款本金给了实际还款本金
        period.setRepay_interest(period.getOrg_repay_interest()); // 应还款利息赋值给了实际还款利息

        period.setCur_late_fee(new BigDecimal(0));// 日滞纳金乘以天数等于本期滞纳金
        period.setCur_overdue_day(0);// 本期逾期天数
        period.setIs_overdue("0");// 设置是否逾期 0为否 1为是
        period.setDerate(new BigDecimal(0));// 设置减免额为0
        period.setRepay_total(period.getOrg_repay_principal().add(period.getOrg_repay_interest())
                                    .add(period.getCur_late_fee()).subtract(period.getDerate()));// 实际还款总额
        saveRequP(period, user);// 保存五个必要信息 创建人等
        period.setIs_upload("2");// 更改是否上传为2
        wmsfinacreperiodrepayDao.update(period);// 更新数据库

        // 贷款还款信息表
        repay.setRepay_period(repay.getRepay_period() + 1);// 已还期数
        repay.setUn_pay_period(repay.getUn_pay_period() - 1);// 未还期数
        if (repay.getUn_pay_period() > 0)
        {
            WmsFinaCrePeriodRepay cpr = new WmsFinaCrePeriodRepay();
            cpr.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());// 存入id
            cpr.setRepay_no(repay.getRepay_period() + 1);// 应还款期数
            cpr = wmsfinacreperiodrepayDao.getbyobject(cpr);// 获取下期还款信息
                                                            // 从中获取到贷款还款信息表中的本期还款日期
            repay.setCurrent_repay_date(cpr.getCurrent_repay_date());// 本期应还款日期
        }
        getRepay(repay, bean, user, nowDate);// 公共方法 集中处理 为repay更改数据
        return resStr;
    }

    /**
     * 提前还款确认保存
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerInfoVO
     * @author baisong
     */
    @Override
    @Transactional
    public String updateInfoForAdv(WmsFinaCreAdvanceBeanVO bean, UserBean user,
                                   WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO)
    {
        String resStr = "success";
        int ret = 0;
        // 系统当前日期
        Date nowDate = getNow();
        // 期还款台账表
        WmsFinaCreRepay repay = wmsfinacrerepayDao.getbyid(bean.getWms_cre_credit_head_id());// 获取贷款还款表信息repay
        List<WmsFinaCrePeriodRepay> list = wmsfinacreperiodrepayDao.getbyno(bean.getWms_cre_credit_head_id());// 获取期还款台账信息
        int sum = list.size();//
        int qishu = 0;// 逾期还款期数
        BigDecimal derate = bean.getDerate();// 获取减免额
        BigDecimal cur_late_fee = bean.getCur_late_fee();// 获取滞纳金
        BigDecimal un_pay_principal = bean.getUn_pay_principal();// 获取未还本金
        BigDecimal interest = new BigDecimal(0);// interest 以便于贷款还款信息表用到
        for (int i = 0; i < sum; i++)
        {
            WmsFinaCrePeriodRepay period = (WmsFinaCrePeriodRepay) list.get(i);
            // /////////需求变更修改内容
            if (i == 0)
            {
                period.setRepay_date(nowDate);// 实际还款时间
                period.setRepay_principal(period.getOrg_repay_principal());// 应还款本金给了实际还款本金
                period.setRepay_interest(period.getOrg_repay_interest()); // 应还款利息赋值给了实际还款利息
                period.setRepay_principal(un_pay_principal);// 设置本金为提前还款页面的剩余本金
                period.setCur_late_fee(cur_late_fee);// 设置滞纳金为提前还款页面上的滞纳金
                period.setDerate(derate);// 设置减免额为提前还款页面上的减免金额
                period.setIs_upload("2");// 逾期到正常更改是否上传为2
                period.setRepay_total(bean.getTotal_ammont());// 应还款总额 提前还款页面获取
                period.setRepay_interest(bean.getTotal_ammont().subtract(un_pay_principal).subtract(cur_late_fee)
                                             .add(derate));// 设置利息为应还款总额-本金-滞纳金+减免额
                interest = period.getRepay_interest();// 把利息赋值给interest
                                                      // 以便于贷款还款信息表用到
                long time1 = period.getCurrent_repay_date().getTime();// 本期应还款日期
                long time3 = nowDate.getTime();//
                long between_days = (time3 - time1) / (1000 * 3600 * 24);
                // 如果逾期天数是负数说明是在还款日之前还款
                if (getInt(between_days) <= 0)
                {
                    period.setCur_overdue_day(0);// 设置逾期天数
                }
                else
                {
                    period.setCur_overdue_day(getInt(between_days));// 设置逾期天数
                }
                if (period.getCur_overdue_day() > 0)
                {
                    period.setIs_overdue("1");// 是否逾期 是
                }
                else
                {
                    period.setIs_overdue("0");// 是否逾期 否
                }
                saveRequP(period, user);// 保存五个必要信息 创建人等
                wmsfinacreperiodrepayDao.update(period);// 更新数据库
            }
            // 本期应还款日期
            if (period.getCurrent_repay_date().before(nowDate))
            {
                qishu = +1;
            }
            else
            {
                break;
            }
        }
        // -----------------------------------------------------------
        // 贷款还款信息表
        repay.setRepay_principal(repay.getRepay_principal().add(bean.getUn_pay_principal()));// 已还本金org_repay_principal
        // repay.setRepay_interest(repay.getRepay_interest().add(bean.getUn_pay_interest()));//已还利息
        repay.setRepay_interest(repay.getRepay_interest().add(interest));// 已还利息
        repay.setRepay_period(repay.getRepay_period() + bean.getUn_pay_period());// 已还期数
        repay.setUn_pay_period(repay.getUn_pay_period() - bean.getUn_pay_period());// 未还期数
        repay.setTotal_overdue_period(repay.getTotal_overdue_period() + qishu);// 总逾期数
        repay.setUn_pay_principal(new BigDecimal(0));// 剩余本金
        repay.setUn_pay_interest(new BigDecimal(0));// 剩余利息
        repay.setBack_interest_period(bean.getBack_interest_period());// 返利期数back_interest_period
        repay.setBack_ammont(bean.getBack_ammont());// 返利金额back_ammont
        repay.setCur_late_fee(new BigDecimal(0));// 本期滞纳金额
        repay.setCur_overdue_type("4");
        repay.setRepay_status("3");// 设置还款状态为结清
        repay.setCur_overdue_day(0);// 本期逾期天数total_derate
        repay.setTotal_derate(bean.getDerate());// 总减免额
        repay.setClean_up_date(nowDate);// 设置结清日期为今天
        repay.setClean_up_status("2");// 设置结清状态 为提前结清
        repay.setCurrent_repay_date(Date.valueOf("1900-01-01"));// 本期应还款日期
        saveRequR(repay, user);// 保存5个必要信息 时间创建人修改人等
        wmsfinacrerepayDao.update(repay);// 更新贷款还款信息表
        wmsPostLoanWorkFlowBeanVO.setUser_id(user.getUserId());// 赋值id
        wmsPostLoanWorkFlowBeanVO.setPass("true");
        wmsPostLoanWorkFlowBeanVO.setAdvice("同意");
        wmsPostLoanWorkFlowService.wmsPostLoanApproveBefore(wmsPostLoanWorkFlowBeanVO);
        return resStr;

    }

    // 公共方法获取当前日期
    public Date getNow()
    {
        Calendar nowcalendar = Calendar.getInstance();
        return new Date(nowcalendar.getTimeInMillis());
    }

    // long 日期 转换成 天数
    public int getInt(long between_days)
    {
        int days = Integer.parseInt(String.valueOf(between_days));
        return days;
    }

    public void getRepay(WmsFinaCreRepay repay, WmsFinaCrePeriodRepaySearchBeanVO bean, UserBean user, Date nowDate)
    {

        repay.setRepay_principal(repay.getRepay_principal().add(bean.getOrg_repay_principal()));// 已还本金org_repay_principal
        repay.setRepay_interest(repay.getRepay_interest().add(bean.getOrg_repay_interest()));// 已还利息
        repay.setUn_pay_principal(repay.getUn_pay_principal().subtract(bean.getOrg_repay_principal()));
        // 剩余本金
        repay.setUn_pay_interest(repay.getUn_pay_interest().subtract(bean.getOrg_repay_interest()));// 剩余利息
        repay.setCur_late_fee(new BigDecimal(0));// 本期滞纳金额
        repay.setCur_overdue_type("4");
        repay.setRepay_status("1");// 设置还款状态为正常
        repay.setCur_overdue_day(0);// 本期逾期天数
        if (repay.getUn_pay_period() == 0)
        {
            // 如果未还期数为0则为结清状态
            repay.setClean_up_date(nowDate);// 设置结清日期为今天
            repay.setClean_up_status("1");// 设置结清状态
            repay.setCurrent_repay_date(Date.valueOf("1900-01-01"));// 本期应还款日期
            repay.setIs_upload("2");// 如果为结清状态 是否上传为2
            repay.setRepay_status("3");// 设置还款状态为结清
        }
        saveRequR(repay, user);// 保存5个必要信息 时间创建人修改人等
        wmsfinacrerepayDao.update(repay);// 更新贷款还款信息表
    }

    // 保存5个必要信息 时间创建人修改人等
    public void saveRequR(WmsFinaCreRepay repay, UserBean user)
    {
        repay.setCreate_user_id(user.getUserId());// 保存创建人信息
        repay.setCreate_user_name(user.getRealname());
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());// 保存当前时间
        repay.setCreate_timestamp(sysTime);
        repay.setLast_update_user_id(user.getUserId());
        repay.setLast_update_timestamp(sysTime);
    }

    // 保存5个必要信息 时间创建人修改人等
    public void saveRequP(WmsFinaCrePeriodRepay period, UserBean user)
    {
        period.setCreate_user_id(user.getUserId());// 保存创建人信息
        period.setCreate_user_name(user.getRealname());
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());// 保存当前时间
        period.setCreate_timestamp(sysTime);
        period.setLast_update_user_id(user.getUserId());
        period.setLast_update_timestamp(sysTime);
    }
    //滞纳金和逾期天数计算等
    public void setInfo(Date nowDate, int sum, int i, List<WmsFinaCrePeriodRepay> list, WmsFinaCreRepay repay,
                        BigDecimal derate, WmsFinaCrePeriodRepay period)
    {
        BigDecimal damages = repay.getLiquidated_damages();// 获取日滞纳金

        period.setRepay_date(nowDate);// 实际还款时间
        period.setRepay_principal(period.getOrg_repay_principal());// 应还款本金给了实际还款本金
        period.setRepay_interest(period.getOrg_repay_interest()); // 应还款利息赋值给了实际还款利息
        period.setIs_overdue("1");
        if (i < sum - 1)
        {
            // period.getCurrent_repay_date(); //本期应还款日期
            WmsFinaCrePeriodRepay per = (WmsFinaCrePeriodRepay) list.get(i + 1);
            // per.getCurrent_repay_date();//下期应还款日期
            long time1 = period.getCurrent_repay_date().getTime();
            long time2 = per.getCurrent_repay_date().getTime();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            if (period.getCurrent_repay_date().before(nowDate) && !per.getCurrent_repay_date().before(nowDate))
            {
                long time3 = nowDate.getTime();// 如果本期应还款日期小于等当前时间 并且
                                               // 下期还款日期不小于当前时间则 逾期天数为当前时间减去
                                               // 本期应还款时间
                between_days = (time3 - time1) / (1000 * 3600 * 24);
            }
            else if (!period.getCurrent_repay_date().before(nowDate))
            {
                between_days = 0;
                period.setIs_overdue("0");
            }
            BigDecimal days = new BigDecimal(getInt(between_days));// 天数转换成BigDecimal类型
            period.setCur_late_fee(damages.multiply(days));// 日滞纳金乘以天数等于本期滞纳金
            period.setCur_overdue_day(getInt(between_days));// 本期逾期天数

            // 如果 输入的减免额大于当前期的滞纳金 则本期减免额为滞纳金额
            if (derate.compareTo(damages.multiply(days)) == 1 || derate.compareTo(damages.multiply(days)) == 0)
            {
                period.setDerate(damages.multiply(days));
                derate = derate.subtract(damages.multiply(days));// 总的减免额应减去当前期的减免额为剩下期数的减免额
            }
            else
            {
                period.setDerate(derate);
                derate = derate.subtract(derate);// 设置减免额为0
            }

        }
        else if (i == sum - 1)
        {
            // period.getCurrent_repay_date(); //本期应还款日期
            long time1 = period.getCurrent_repay_date().getTime();
            long time2 = nowDate.getTime();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            if (period.getCurrent_repay_date().after(nowDate))
            {
                between_days = 0;
            }
            BigDecimal days = new BigDecimal(getInt(between_days));// 天数转换成BigDecimal类型
            period.setCur_late_fee(damages.multiply(days));// 日滞纳金乘以天数等于本期滞纳金
            period.setCur_overdue_day(getInt(between_days));// 本期逾期天数
            if (period.getCurrent_repay_date().after(nowDate))
            {
                period.setIs_overdue("0");// 如果当前期应还款日期等于当前时间 本期还款是否逾期为 否
            }
            else
            {
                period.setIs_overdue("1");// 如果当前期应还款日期等于当前时间 本期还款是否逾期为 是
            }
            // 如果 输入的减免额大于当前期的滞纳金 则本期减免额为滞纳金额
            if (derate.compareTo(damages.multiply(days)) == 1 || derate.compareTo(damages.multiply(days)) == 0)
            {
                period.setDerate(damages.multiply(days));
                derate = derate.subtract(damages.multiply(days));// 总的减免额应减去当前期的减免额为剩下期数的减免额
            }
            else
            {
                period.setDerate(derate);
                derate = derate.subtract(derate);// 设置减免额为0
            }
        }
        period.setIs_upload("2");// 逾期到正常更改是否上传为2
        period.setRepay_total(period.getOrg_repay_principal().add(period.getOrg_repay_interest())
                                    .add(period.getCur_late_fee()).subtract(period.getDerate()));// 实际还款总额
    }

    /**
     * 实现结清查询列表显示
     */
    @Override
    public Map<String, Object> getListWithoutPagingForClear(WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsfinacrerepayDao.searchforClear(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsfinacrerepayDao.findCountforClear(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getBorrowAndLoanAppByPK(Integer wms_cre_credit_head_id)
    {
        return wmsfinacrerepayDao.getBorrowAndLoanAppByPK(wms_cre_credit_head_id);
    }

    /**
     * 放款确认 baisong
     * @throws ParseException 
     */

    @Override
    @Transactional
    public String periodAndCreRepaySave(UserBean user, Integer wms_cre_credit_head_id, String taskId, String cre_type, String edition_num) throws ParseException
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        WmsCreApproBorrowProtocol borrowProtocol = getWmsCreApproBorrowProtocol(wms_cre_credit_head_id);
        if (borrowProtocol.getProtocol_id_num().length() < 6)
        {
            paramMap.put("loan_date", borrowProtocol.getProtocol_date());
        }
        else
        {
            paramMap.put("loan_date", new java.util.Date());
        }
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        wmsfinacreloanappDao.updateLoanDate(paramMap);

        WmsCreCreditHead wmsCreCreditHead = getWmsCreCreditHead(wms_cre_credit_head_id);
        WmsFinaCreLoanApp wmsFinaCreLoanApp = getWmsFinaCreLoanApp(wms_cre_credit_head_id);
        WmsCreApproServiceProtocol wmsCreApproServiceProtocol = wmscreapproserviceprotocolDao.getbypk(wms_cre_credit_head_id);
        String beanStr = "success";
        int beanRet = 0;
        int bean1Ret = 0;
        WmsFinaCreRepay bean = new WmsFinaCreRepay();
        WmsFinaCrePeriodRepay bean1 = new WmsFinaCrePeriodRepay();
       
        if (cre_type.equals("1"))
        {
            // 要根据不同提供查询的产品类型决定需要调用哪个方法 1为信贷产品
            bean = setWmsFinaCreRepay(user, wms_cre_credit_head_id, borrowProtocol, wmsCreCreditHead,
                                      wmsFinaCreLoanApp, wmsCreApproServiceProtocol);
            //保存贷后专员信息
            Map<String, Object> paraMap=new HashMap<>();
            paraMap.put("DEPT_CODE_KFB", WmsHelp.DEPT_CODE_KFB);//贷款端--客服部id --放款确认使用的催缴部门人员 
            Map<String, Object> map=wmsfinacrerepayDao.seachCount(paraMap);//查询符合条件的催缴专员-贷后专员
            bean.setDunning_id((int) map.get("personnel_id"));//贷后专员id
            bean.setDunning_name((String)map.get("personnel_name"));//贷后专员姓名
            bean.setDunning_deptid((int)map.get("personnel_deptId"));//贷后专员部门id
            bean.setDunning_datetime(new Timestamp(System.currentTimeMillis()));//贷后专员时间
            beanRet = wmsfinacrerepayDao.save(bean);// 保存贷款还款信息表
            if (beanRet == 0)
            {
                beanStr = "error";
            }
            for (int i = 0; i < borrowProtocol.getBorrow_deadline(); i++)
            {
            	
            	bean1 = setWmsFinaCrePeriodRepay(user, wms_cre_credit_head_id, borrowProtocol, wmsFinaCreLoanApp,
                                                 wmsCreCreditHead, i, bean);
                bean1Ret = wmsfinacreperiodrepayDao.save(bean1);// 期还款台帐
                if (bean1Ret == 0)
                {
                    beanStr = "error";
                }
            }
            /**
        	 * @Description:修改- 获取信息放款数据同步到还款提醒模块
        	 * @author: baisong
        	 * @time:2016年11月15日 下午1:30:40 
        	 */
        	CreditMessageToRepayWarnBeanVO beanvo=new CreditMessageToRepayWarnBeanVO();
            beanvo.setWms_cre_credit_head_id(wms_cre_credit_head_id.toString());
            //贷款类型-房贷
            beanvo.setCre_type("1");
            wmsCreCreditNotaryWarnService.getCreditMessageToRepayWarn(beanvo, user);	
        
            if (beanRet != 0 && bean1Ret != 0)
            {
                // 信贷流程
                WmsCreditWorkFlowVO approveWorkFlowVO = new WmsCreditWorkFlowVO();
                approveWorkFlowVO.setWms_cre_credit_head_id(wms_cre_credit_head_id);
                approveWorkFlowVO.setTaskId(taskId);
                approveWorkFlowVO.setUser_id(user.getUserId());
                approveWorkFlowVO.setPass("true");
                creditWorkFlowService.makeLoansaffirm(approveWorkFlowVO);
            }
        }
        else if (cre_type.equals("2"))
        {
            // 要根据不同提供查询的产品类型决定需要调用哪个方法 2为房贷产品
            bean = setWmsFinaCreRepay(user, wms_cre_credit_head_id, borrowProtocol, wmsCreCreditHead,
                                      wmsFinaCreLoanApp, wmsCreApproServiceProtocol);
            //保存贷后专员信息
            Map<String, Object> paraMap=new HashMap<>();
            paraMap.put("DEPT_CODE_KFB", WmsHelp.DEPT_CODE_KFB);//贷款端--客服部id --放款确认使用的催缴部门人员 
            Map<String, Object> map=wmsfinacrerepayDao.seachCount(paraMap);//查询符合条件的催缴专员-贷后专员
            bean.setDunning_id((int) map.get("personnel_id"));//贷后专员id
            bean.setDunning_name((String)map.get("personnel_name"));//贷后专员姓名
            bean.setDunning_deptid((int)map.get("personnel_deptId"));//贷后专员部门id
            bean.setDunning_datetime(new Timestamp(System.currentTimeMillis()));//贷后专员时间
            beanRet = wmsfinacrerepayDao.save(bean);// 保存贷款还款信息表
            if (beanRet == 0)
            {
                beanStr = "error";
            }
            for (int i = 0; i < borrowProtocol.getBorrow_deadline(); i++)
            {
                bean1 = setWmsFinaCrePeriodRepay(user, wms_cre_credit_head_id, borrowProtocol, wmsFinaCreLoanApp,
                                                 wmsCreCreditHead, i, bean);
                bean1Ret = wmsfinacreperiodrepayDao.save(bean1);// 期还款台帐
                if (bean1Ret == 0)
                {
                    beanStr = "error";
                }
            }
            if (beanRet != 0 && bean1Ret != 0)
            {
                // 房贷流程
                WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                approveHouseWorkFlowVO.setWms_cre_credit_head_id(String.valueOf(wms_cre_credit_head_id));
                approveHouseWorkFlowVO.setTaskId(taskId);
                approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
                
                if("1".equals(edition_num)) {//旧流程
                    houseCreditWorkFlowService.mortgageLoanExaminationAndApproval(approveHouseWorkFlowVO);
                }else if("2".equals(edition_num)) {//新流程
                    //数据来源1为pc 2为移动端
    		        if("2".equals(wmsCreCreditHead.getVersion_number())){//2016/5/10版本
    		        	 approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
    		        }else{//2016/2/10版本
    		        	 approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
    		        }
                	//approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
                    approveHouseWorkFlowVO.setDebtkey("11");
                    approveHouseWorkFlowVO.setPass("true");
                    wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
                }
            }
            /**
        	 * @Description: 修改-获取信息放款数据同步到还款提醒模块
        	 * @author: baisong
        	 * @time:2016年11月15日 下午1:30:40 
        	 */
            CreditMessageToRepayWarnBeanVO beanvo = new CreditMessageToRepayWarnBeanVO();
            beanvo.setWms_cre_credit_head_id(wms_cre_credit_head_id.toString());
            // 贷款类型-房贷
            beanvo.setCre_type("2");
            wmsCreCreditNotaryWarnService.getCreditMessageToRepayWarn(beanvo, user);
        
        }
        //初始化应还款信息表中的数据
        setWmsFinaCreRealrepayInfo(wms_cre_credit_head_id);
        //放款成功后将客户信息传递给PTP
        if(beanStr.equals("success")){
          	 Map<String,Object> map=new HashMap<String, Object>();
             map.put("wms_cre_credit_head_id",wms_cre_credit_head_id);
             wmscrecreditapproService.giveInfoToPTP(map, user.getUserId());
        }
        return beanStr;
    }
    /**
     * 放款确认 baisong--车贷
     * @throws ParseException 
     */

    @Override
    @Transactional
    public String periodAndCreRepaySaveCar(UserBean user, Integer wms_cre_credit_head_id, String taskId, String cre_type) throws ParseException
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        WmsCreApproBorrowProtocol borrowProtocol = getWmsCreApproBorrowProtocol(wms_cre_credit_head_id);
        if (borrowProtocol.getProtocol_id_num().length() < 6)
        {
            paramMap.put("loan_date", borrowProtocol.getProtocol_date());
        }
        else
        {
            paramMap.put("loan_date", new java.util.Date());
        }
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        wmsfinacreloanappDao.updateLoanDate(paramMap);

        WmsCreCreditHead wmsCreCreditHead = getWmsCreCreditHead(wms_cre_credit_head_id);
        WmsFinaCreLoanApp wmsFinaCreLoanApp = getWmsFinaCreLoanApp(wms_cre_credit_head_id);
        WmsCreApproServiceProtocol wmsCreApproServiceProtocol = wmscreapproserviceprotocolDao.getbypk(wms_cre_credit_head_id);
        String beanStr = "success";
        int beanRet = 0;
        int bean1Ret = 0;
        WmsFinaCreRepay bean = new WmsFinaCreRepay();
        WmsFinaCrePeriodRepay bean1 = new WmsFinaCrePeriodRepay();
       
      
        // 要根据不同提供查询的产品类型决定需要调用哪个方法 1为信贷产品
        bean = setWmsFinaCreRepayCar(user, wms_cre_credit_head_id, borrowProtocol, wmsCreCreditHead,
                                  wmsFinaCreLoanApp, wmsCreApproServiceProtocol);
        //保存贷后专员信息
        Map<String, Object> paraMap=new HashMap<>();
        paraMap.put("DEPT_CODE_KFB", WmsHelp.DEPT_CODE_KFB);//贷款端--客服部id --放款确认使用的催缴部门人员 
        Map<String, Object> map=wmsfinacrerepayDao.seachCount(paraMap);//查询符合条件的催缴专员-贷后专员
        bean.setDunning_id((int) map.get("personnel_id"));//贷后专员id
        bean.setDunning_name((String)map.get("personnel_name"));//贷后专员姓名
        bean.setDunning_deptid((int)map.get("personnel_deptId"));//贷后专员部门id
        bean.setDunning_datetime(new Timestamp(System.currentTimeMillis()));//贷后专员时间
        beanRet = wmsfinacrerepayDao.save(bean);// 保存贷款还款信息表
        if (beanRet == 0)
        {
            beanStr = "error";
        }
        for (int i = 0; i < borrowProtocol.getBorrow_deadline(); i++)
        {
        	
        	bean1 = setWmsFinaCrePeriodRepayCar(user, wms_cre_credit_head_id, borrowProtocol, wmsFinaCreLoanApp,
                                             wmsCreCreditHead, i, bean);
            bean1Ret = wmsfinacreperiodrepayDao.save(bean1);// 期还款台帐
            if (bean1Ret == 0)
            {
                beanStr = "error";
            }
        }
        if (beanRet != 0 && bean1Ret != 0)
        {
            WmsCarLoanWorkFlowVO wVo=new WmsCarLoanWorkFlowVO();//流程
   			 //给流程赋值
   	         wVo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
   	         wVo.setPass("true");//是否通过
   	         wVo.setAdvice("通过");//意见
   	         wVo.setUserId(String.valueOf(user.getUserId()));//操作人id
   	         wVo.setTaskId(taskId);//流程节点id
   	         //carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
   	         carLoanWorkFlowService.carLoanApprovalProcess(wVo,"10");
        }
   
        //初始化应还款信息表中的数据
        int realrepayCount = setWmsFinaCreRealrepayInfo(wms_cre_credit_head_id);
        if(realrepayCount != bean1Ret) {
        	beanStr = "error";
        }
        //放款成功后将客户信息传递给PTP
        if(beanStr.equals("success")){
        	Map<String,Object> map1=new HashMap<String, Object>();
            map1.put("wms_cre_credit_head_id",wms_cre_credit_head_id);
            wmscrecreditapproService.giveInfoToPTP(map1,user.getUserId());
        }
        return beanStr;
    }

    /**
     * 期还款台账表初始化数据
     * 
     * @param user
     * @param bean1
     * @param wms_cre_credit_head_id
     * @param borrowProtocol
     * @param wmsFinaCreLoanApp
     * @param wmsCreCreditHead
     * @return
     * @throws ParseException 
     */
    @Override
    @Transactional
    public WmsFinaCrePeriodRepay setWmsFinaCrePeriodRepay(UserBean user, int wms_cre_credit_head_id,
                                                           WmsCreApproBorrowProtocol borrowProtocol,
                                                           WmsFinaCreLoanApp wmsFinaCreLoanApp,
                                                           WmsCreCreditHead wmsCreCreditHead, int i,
                                                           WmsFinaCreRepay bean)
    {
        WmsFinaCrePeriodRepay bean1 = new WmsFinaCrePeriodRepay();
        Date current_repay_date = null;// 贷款产品1、信贷产品 2、房贷产品 （以后增加类别后再进行完善）
        if (wmsCreCreditHead.getCre_type().equals("1"))
        {
            current_repay_date = setCurrent_repay_date(borrowProtocol.getProtocol_date(), i);//
        }
        else if (wmsCreCreditHead.getCre_type().equals("2"))
        {
            //current_repay_date = setCurrent_repay_date(wmsFinaCreLoanApp.getLoan_date(), i);
        	current_repay_date = setCurrent_repay_date(borrowProtocol.getProtocol_date(), i);//2015-07-03
        }
        // Date current_repay_date=
        // setCurrent_repay_date(wmsFinaCreLoanApp.getLoan_date(),i);
        // Date current_repay_date=
        // setCurrent_repay_date(borrowProtocol.getProtocol_date(),i);//
        // 20141029 老数据需要
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());// 获取当前系统时间
        bean1.setWms_cre_credit_head_id(wms_cre_credit_head_id);// 设置贷款单据主键
        bean1.setRepay_no(i + 1);// 应还款期数
        bean1.setCurrent_repay_date(current_repay_date);// 应还款日
        // if(borrowProtocol.getProtocol_type().equals("3")||borrowProtocol.getProtocol_type().equals("4")){
        // //添加房贷1号 房贷2号的改变
//        if (borrowProtocol.getProtocol_type().equals("3") || borrowProtocol.getProtocol_type().equals("4")
//            || borrowProtocol.getProtocol_type().equals("1") || borrowProtocol.getProtocol_type().equals("2")
//            || borrowProtocol.getProtocol_type().equals("11")|| borrowProtocol.getProtocol_type().equals("12")
//            || borrowProtocol.getProtocol_type().equals("21")|| borrowProtocol.getProtocol_type().equals("22"))
        //1代表等额本息
        //2代表先息后本
        if ("2".equals(borrowProtocol.getPayment_contract_type())) {//房贷3号担责为11号  不但则为12号
            if (i == borrowProtocol.getBorrow_deadline() - 1)
            {
                bean1.setOrg_repay_principal(borrowProtocol.getPrincipal_lower());// 如果合同类型为3和4（先息后本）则除最后一期外
                                                                                  // 为合同金额
            }
            else
            {
                bean1.setOrg_repay_principal(new BigDecimal(0));// 如果合同类型为3和4（先息后本）则除最后一期外
                                                                // 应还本金为0(此值从贷款还款信息表中获取
                                                                // 其中值为0)
            }
            // 添加房贷1号 房贷2号的改变
            // }else
            // if(borrowProtocol.getProtocol_type().equals("5")||borrowProtocol.getProtocol_type().equals("1")||borrowProtocol.getProtocol_type().equals("2")||borrowProtocol.getProtocol_type().equals("6")||borrowProtocol.getProtocol_type().equals("7")){
        }
//        else if (borrowProtocol.getProtocol_type().equals("5") || borrowProtocol.getProtocol_type().equals("6")
//                 || borrowProtocol.getProtocol_type().equals("7")|| borrowProtocol.getProtocol_type().equals("9")
//                 || borrowProtocol.getProtocol_type().equals("10")
//                 || borrowProtocol.getProtocol_type().equals("23")|| borrowProtocol.getProtocol_type().equals("24"))//房贷4号担责为9号  不但则为10号
        //1代表等额本息 2代表先息后本
        else if ("1".equals(borrowProtocol.getPayment_contract_type())){//
        	if(i == borrowProtocol.getBorrow_deadline() - 1){
        		bean1.setOrg_repay_principal(bean.getPrincipal().subtract((bean.getRefund_limit_month().subtract(bean.getInterest_limit_month())).multiply(new BigDecimal(i))));// 最后一期为剩余本金
        	}else{
	            // 应还款本金 =月还款额-月利息
	            bean1.setOrg_repay_principal(bean.getRefund_limit_month().subtract(bean.getInterest_limit_month()));
        	}
        }
        // 应还款利息
        if(i == borrowProtocol.getBorrow_deadline() - 1){//interest
        	/* bean1.setOrg_repay_interest(bean.getInterest().subtract(bean.getInterest_limit_month().multiply(new BigDecimal(i))));// 最后一期为剩余利息
    		 DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    		 java.util.Date myDate =  dateFormat1.parse("2015-05-05");
    		 Date myDate1=new Date(myDate.getTime());//对旧数据的处理
        	 if(wmsCreCreditHead.getCre_loan_type() == 284 &&borrowProtocol.getProtocol_date().before(myDate1)){
        		 bean1.setOrg_repay_interest(bean.getInterest_limit_month());// 为贷款还款信息表中的月利息	*/
//        	if(wmsCreCreditHead.getCre_loan_type() == 284 || borrowProtocol.getProtocol_type().equals("3") || borrowProtocol.getProtocol_type().equals("4")//先息后本的最后一期利息为0
//	            || borrowProtocol.getProtocol_type().equals("1") || borrowProtocol.getProtocol_type().equals("2")
//	            || borrowProtocol.getProtocol_type().equals("11")|| borrowProtocol.getProtocol_type().equals("12")
//	            || borrowProtocol.getProtocol_type().equals("21")|| borrowProtocol.getProtocol_type().equals("22"))
        	if ("2".equals(borrowProtocol.getPayment_contract_type())) {//特色贷--先息后本  1代表等额本息2代表先息后本
        		bean1.setOrg_repay_interest(new BigDecimal(0));// 为贷款还款信息表中的月利息  	
        	 }else{
        		 bean1.setOrg_repay_interest(bean.getInterest().subtract(bean.getInterest_limit_month().multiply(new BigDecimal(i))));// 最后一期为剩余利息 
        	 }
        }else{
        	 bean1.setOrg_repay_interest(bean.getInterest_limit_month());// 为贷款还款信息表中的月利息	
        }
        bean1.setRepay_principal(new BigDecimal(0));// 实际还款本金为0
        bean1.setRepay_interest(new BigDecimal(0));// 实际还款利息为0
        bean1.setRepay_total(new BigDecimal(0));// 实际还款总额0
        bean1.setIs_overdue("0");// 是否逾期
        bean1.setCur_overdue_day(0);// 逾期天数
        bean1.setCur_late_fee(new BigDecimal(0));// 滞纳金额
        bean1.setDerate(new BigDecimal(0));// 减免金额
        bean1.setIs_upload("0");// 是否上传
        bean1.setCreate_user_id(user.getUserId());// 创建人id
        bean1.setCreate_user_name(user.getRealname());// 创建人名字
        bean1.setCreate_timestamp(sysTime);// 创建时间
        bean1.setEnable_flag("1");// 数据状态 0无效 1有效
        return bean1;

    }
    /**
     * 期还款台账表初始化数据--车贷
     * 
     * @param user
     * @param bean1
     * @param wms_cre_credit_head_id
     * @param borrowProtocol
     * @param wmsFinaCreLoanApp
     * @param wmsCreCreditHead
     * @return
     * @throws ParseException 
     */
    @Transactional
    private WmsFinaCrePeriodRepay setWmsFinaCrePeriodRepayCar(UserBean user, int wms_cre_credit_head_id,
                                                           WmsCreApproBorrowProtocol borrowProtocol,
                                                           WmsFinaCreLoanApp wmsFinaCreLoanApp,
                                                           WmsCreCreditHead wmsCreCreditHead, int i,
                                                           WmsFinaCreRepay bean) throws ParseException
    {
        WmsFinaCrePeriodRepay bean1 = new WmsFinaCrePeriodRepay();
        Date current_repay_date = null;// 贷款产品1、信贷产品 2、房贷产品 （以后增加类别后再进行完善）
        current_repay_date = setCurrent_repay_date(borrowProtocol.getProtocol_date(), i);//
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());// 获取当前系统时间
        bean1.setWms_cre_credit_head_id(wms_cre_credit_head_id);// 设置贷款单据主键
        bean1.setRepay_no(i + 1);// 应还款期数
        bean1.setCurrent_repay_date(current_repay_date);// 应还款日
        ////1代表等额本息 2代表先息后本
        if (borrowProtocol.getPayment_contract_type().equals("2") )
        {//先息后本
            if (i == borrowProtocol.getBorrow_deadline() - 1)
            {
                bean1.setOrg_repay_principal(borrowProtocol.getPrincipal_lower());//（先息后本）则除最后一期外 // 为合同金额                                                
            }
            else
            {
                bean1.setOrg_repay_principal(new BigDecimal(0));// （先息后本）则除最后一期外// 应还本金为0( 其中值为0)                                           
            }
        }
        else if (borrowProtocol.getPayment_contract_type().equals("1"))
        {//等额本息
        	if(i == borrowProtocol.getBorrow_deadline() - 1){
        		bean1.setOrg_repay_principal(bean.getPrincipal().subtract((bean.getRefund_limit_month().subtract(bean.getInterest_limit_month())).multiply(new BigDecimal(i))));// 最后一期为剩余本金
        	}else{
	            // 应还款本金 =月还款额-月利息
	            bean1.setOrg_repay_principal(bean.getRefund_limit_month().subtract(bean.getInterest_limit_month()));
        	}
        }
        // 应还款利息
        if(i == borrowProtocol.getBorrow_deadline() - 1){//interest
        	 if(borrowProtocol.getPayment_contract_type().equals("2")){//
        		bean1.setOrg_repay_interest(new BigDecimal(0));// 为贷款还款信息表中的月利息  	
        	 }else{
        		bean1.setOrg_repay_interest(bean.getInterest().subtract(bean.getInterest_limit_month().multiply(new BigDecimal(i))));// 最后一期为剩余利息 
        	 }
        }else{
        	 bean1.setOrg_repay_interest(bean.getInterest_limit_month());// 为贷款还款信息表中的月利息	
        }
        bean1.setRepay_principal(new BigDecimal(0));// 实际还款本金为0
        bean1.setRepay_interest(new BigDecimal(0));// 实际还款利息为0
        bean1.setRepay_total(new BigDecimal(0));// 实际还款总额0
        bean1.setIs_overdue("0");// 是否逾期
        bean1.setCur_overdue_day(0);// 逾期天数
        bean1.setCur_late_fee(new BigDecimal(0));// 滞纳金额
        bean1.setDerate(new BigDecimal(0));// 减免金额
        bean1.setIs_upload("0");// 是否上传
        bean1.setCreate_user_id(user.getUserId());// 创建人id
        bean1.setCreate_user_name(user.getRealname());// 创建人名字
        bean1.setCreate_timestamp(sysTime);// 创建时间
        bean1.setEnable_flag("1");// 数据状态 0无效 1有效
        return bean1;

    }
    /**
     * 贷款还款信息表初始化数据--车贷
     * 
     * @param user
     * @param bean
     * @param wms_cre_credit_head_id
     * @param borrowProtocol
     * @param wmsCreCreditHead
     * @param wmsFinaCreLoanApp
     * @return
     */
    @Transactional
    private WmsFinaCreRepay setWmsFinaCreRepayCar(UserBean user, int wms_cre_credit_head_id,
                                               WmsCreApproBorrowProtocol borrowProtocol,
                                               WmsCreCreditHead wmsCreCreditHead, WmsFinaCreLoanApp wmsFinaCreLoanApp,
                                               WmsCreApproServiceProtocol wmsCreApproServiceProtocol)
    {
        WmsFinaCreRepay bean = new WmsFinaCreRepay();
        // /修改记录：001 初始化p2p占用债权 默认0
        bean.setAct_creditor(new BigDecimal(0));
        Date current_repay_date = null;// 贷款产品1、信贷产品 2、房贷产品 （以后增加类别后再进行完善）
        current_repay_date = setCurrent_repay_date(borrowProtocol.getProtocol_date(), 0);//

        Timestamp sysTime = new Timestamp(System.currentTimeMillis());// 获取当前系统时间

        bean.setWms_cre_credit_head_id(wms_cre_credit_head_id);// 贷款表主键
        bean.setCre_type(wmsCreCreditHead.getCre_type());// 贷款产品 1信贷 2房贷
        bean.setWms_cre_appro_borrow_protocol_id(borrowProtocol.getWms_cre_appro_id());// 合同表主键
        bean.setProtocol_code(borrowProtocol.getProtocol_id_year_num());// 合同编号
        bean.setProtocol_type(borrowProtocol.getProtocol_type());// 合同表类型 1
                                                                 // 等额本息不担责任 2
                                                                 // 等额本息担责任
                                                                 // 3先息后本不担责任
                                                                 // 4先息后本担责

        bean.setBorrow_deadline(borrowProtocol.getBorrow_deadline());// 借款期限
        bean.setBorrow_interest(borrowProtocol.getBorrow_interest());// 借款利率
        // 合同利率为合同表中的利率加上居间服务协议中的每月应支付的利率
        BigDecimal borrow_interest = (borrowProtocol.getBorrow_interest().add(wmsCreApproServiceProtocol.getService_cost_month())).divide(new BigDecimal(100));

        bean.setLoan_date(wmsFinaCreLoanApp.getLoan_date());// 放款日期
        // bean.setLoan_date(borrowProtocol.getProtocol_date());//放款日期//
        // 20141029 老数据需要
        bean.setLoan_amount(wmsFinaCreLoanApp.getLoan_amount());// 放款金额
        bean.setCurrent_repay_date(current_repay_date);// 本期应还款日
        bean.setRepay_begin_date(borrowProtocol.getBorrow_begin_date());// 还款开始日期
        bean.setRepay_end_date(borrowProtocol.getBorrow_end_date());// 还款结束日期
        // 合同金额
        BigDecimal borrow = borrowProtocol.getPrincipal_lower();
        // 贷款期数
        BigDecimal deadline = new BigDecimal(borrowProtocol.getBorrow_deadline());
        // 其中的本金=合同金额
        bean.setPrincipal(borrow);
        if (borrowProtocol.getPayment_contract_type().equals("2"))//1代表等额本息  2代表先息后本	
        { //--先洗后本
            // 月还款额 = 合同金额*n%(利率)
            BigDecimal refund_limit_month = borrow.multiply(borrow_interest);
            bean.setRefund_limit_month(refund_limit_month);// ROUND_UNNECESSARY为取绝对精度
                                                           // 断言请求的操作具有精确的结果，因此不需要舍入
            // 月还款本金 = 前n-1期：0 最后一期：合同金额
            BigDecimal cipal_limit_month = new BigDecimal(0);
            // 月利息 = 月还款额 - 月还款本金
            bean.setInterest_limit_month((refund_limit_month.subtract(cipal_limit_month)).divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP));
        }
        else
        {//等额本息
        	WmsCreCreditHead wmsHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
        	borrow = wmsHead.getAppro_limit();// 面签金额==其中本金
            // 其中的本金=合同金额
            bean.setPrincipal(borrow);
            // 月还款额 = 合同金额/期数+合同金额*1.27%（和之后四舍五入取整）
            BigDecimal refund_limit_month = (borrow.divide(deadline, 8, BigDecimal.ROUND_HALF_UP).add(borrow.multiply(borrow_interest))).divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
            bean.setRefund_limit_month(refund_limit_month);// ROUND_UNNECESSARY为取绝对精度
                                                           // 断言请求的操作具有精确的结果，因此不需要舍入
            // 月还款本金 = 其中本金/期数（四舍五入取整）
            BigDecimal cipal_limit_month = borrow.divide(new BigDecimal(borrowProtocol.getBorrow_deadline()),0,BigDecimal.ROUND_HALF_UP);
            // 月利息 = 月还款额 - 月还款本金
            bean.setInterest_limit_month(refund_limit_month.subtract(cipal_limit_month));
        }
        // 剩余本金 公式=合同金额
        bean.setUn_pay_principal(borrow);
        // 其中的利息=月利息*期数
        bean.setInterest(bean.getInterest_limit_month()
                             .multiply(new BigDecimal(borrowProtocol.getBorrow_deadline())));
        // 剩余利息=月利息*期数
        bean.setUn_pay_interest(bean.getInterest_limit_month().multiply(new BigDecimal(borrowProtocol.getBorrow_deadline())));
        bean.setProtocol_creat_date(borrowProtocol.getProtocol_date());// 合同签订日期
        bean.setProtocol_amount(borrowProtocol.getPrincipal_lower());// 合同金额

        bean.setLiquidated_damages(new BigDecimal(borrowProtocol.getLiquidated_damages()));// 日滞纳金
        bean.setRepay_period(0);// 已还期数
        bean.setRepay_principal(new BigDecimal(0));// 已还本金
    	if(borrowProtocol.getPayment_contract_type().equals("2")){//等额本息1代表等额本息2代表先息后本
    		bean.setRepay_interest(bean.getInterest_limit_month());// 已还利息
        	bean.setUn_pay_interest(bean.getUn_pay_interest().subtract(bean.getRepay_interest()));//未还利息
    	}else{
    		bean.setRepay_interest(new BigDecimal(0));// 已还利息
    	}
        bean.setUn_pay_period(borrowProtocol.getBorrow_deadline());// 未还期数
        bean.setCur_overdue_type("4");// 本期逾期类型 4 为正常
        bean.setCur_overdue_day(0);// 本期逾期天数为0
        bean.setCur_late_fee(new BigDecimal(0));// 本期滞纳金额
        bean.setTotal_overdue_period(0);// 总逾期期数
        bean.setTotal_overdue_day(0);// 总逾期天数
        bean.setTotal_late_fee(0);// 总应滞纳金额
        bean.setTotal_pay_late_fee(new BigDecimal(0));// 已缴纳滞纳金额
        bean.setTotal_derate(new BigDecimal(0));// 总减免额

        bean.setBack_interest_period(0);// 返利期数
        bean.setBack_ammont(new BigDecimal(0));// 返利金额
        bean.setRepay_status("1");// 还款状态为正常
        bean.setSalesman_id(wmsCreCreditHead.getSalesman_id());// 业务员id
        bean.setSalesman_name(wmsCreCreditHead.getSalesman_name());// 业务员姓名
        bean.setIs_upload("0");// 是否上传
        bean.setCreate_user_id(user.getUserId());// 创建人id
        bean.setCreate_user_name(user.getRealname());// 创建人名字
        bean.setCreate_timestamp(sysTime);// 创建时间
        bean.setEnable_flag("1");// 数据状态 0无效 1 有效
        bean.setMigration_status("0");// 是否申请转件 0 否 1 是
        bean.setPre_repay_status("0");// 是否申请提前还款0 否 1 是
        if (wmsCreCreditHead.getCre_type().equals("3"))
        {// 如果是信贷可匹配的乘以系数
            // 设置贷款还款信息表中的可匹配债权 = 合同金额*属性表中配置的值 现在为1.5
            List<Integer> list = new ArrayList<Integer>();
            list.add(23);
            List<WmsSysProperty> list1 = wmssyspropertyDao.searchforhouse(list);
            WmsSysProperty wms = (WmsSysProperty) list1.get(0);
            BigDecimal bd = new BigDecimal(wms.getProperty_value());
            BigDecimal matching_creditor = bd.multiply(bean.getProtocol_amount());
            bean.setMatching_creditor(matching_creditor);// 可匹配债权
        }
        else if (wmsCreCreditHead.getCre_type().equals("2"))
        {// 如果是房贷可匹配的等于合同金额
            bean.setMatching_creditor(bean.getProtocol_amount());// 可匹配债权
        }
        return bean;

    }
    /**
     * 贷款还款信息表初始化数据
     * 
     * @param user
     * @param bean
     * @param wms_cre_credit_head_id
     * @param borrowProtocol
     * @param wmsCreCreditHead
     * @param wmsFinaCreLoanApp
     * @return
     */
    @Override
    @Transactional
    public WmsFinaCreRepay setWmsFinaCreRepay(UserBean user, 
        int wms_cre_credit_head_id,
        WmsCreApproBorrowProtocol borrowProtocol,
        WmsCreCreditHead wmsCreCreditHead, WmsFinaCreLoanApp wmsFinaCreLoanApp,
        WmsCreApproServiceProtocol wmsCreApproServiceProtocol)
    {
        WmsFinaCreRepay bean = new WmsFinaCreRepay();
        // /修改记录：001 初始化p2p占用债权 默认0
        bean.setAct_creditor(new BigDecimal(0));
        Date current_repay_date = null;// 贷款产品1、信贷产品 2、房贷产品 （以后增加类别后再进行完善）
        if (wmsCreCreditHead.getCre_type().equals("1"))
        {
            current_repay_date = setCurrent_repay_date(borrowProtocol.getProtocol_date(), 0);//
        }
        else if (wmsCreCreditHead.getCre_type().equals("2"))
        {
        	current_repay_date = setCurrent_repay_date(borrowProtocol.getProtocol_date(), 0);//
            //current_repay_date = setCurrent_repay_date(wmsFinaCreLoanApp.getLoan_date(), 0);
        }
        // Date repay_begin_date=current_repay_date;//还款开始日期=本期应还款日期
        Date repay_end_date = null;
        if (wmsCreCreditHead.getCre_type().equals("1"))
        {
            repay_end_date = setCurrent_repay_date(borrowProtocol.getProtocol_date(),
                                                   borrowProtocol.getBorrow_deadline() - 1);//
        }
        else if (wmsCreCreditHead.getCre_type().equals("2"))
        {
        	repay_end_date = setCurrent_repay_date(borrowProtocol.getProtocol_date(),
                    borrowProtocol.getBorrow_deadline() - 1);//
           //repay_end_date = setCurrent_repay_date(wmsFinaCreLoanApp.getLoan_date(),
           //                                        borrowProtocol.getBorrow_deadline() - 1);
        }
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());// 获取当前系统时间

        bean.setWms_cre_credit_head_id(wms_cre_credit_head_id);// 贷款表主键
        bean.setCre_type(wmsCreCreditHead.getCre_type());// 贷款产品 1信贷 2房贷
        bean.setWms_cre_appro_borrow_protocol_id(borrowProtocol.getWms_cre_appro_id());// 合同表主键
        bean.setProtocol_code(borrowProtocol.getProtocol_id_year_num());// 合同编号

        bean.setProtocol_type(borrowProtocol.getProtocol_type());// 合同表类型 1
                                                                 // 等额本息不担责任 2
                                                                 // 等额本息担责任
                                                                 // 3先息后本不担责任
                                                                 // 4先息后本担责

        bean.setBorrow_deadline(borrowProtocol.getBorrow_deadline());// 借款期限
        bean.setBorrow_interest(borrowProtocol.getBorrow_interest());// 借款利率
        // 合同利率为合同表中的利率加上居间服务协议中的每月应支付的利率
        BigDecimal borrow_interest = (borrowProtocol.getBorrow_interest().add(wmsCreApproServiceProtocol.getService_cost_month())).divide(new BigDecimal(
                                                                                                                                                         100));

        bean.setLoan_date(wmsFinaCreLoanApp.getLoan_date());// 放款日期
        // bean.setLoan_date(borrowProtocol.getProtocol_date());//放款日期//
        // 20141029 老数据需要
        bean.setLoan_amount(wmsFinaCreLoanApp.getLoan_amount());// 放款金额
        bean.setCurrent_repay_date(current_repay_date);// 本期应还款日
        bean.setRepay_begin_date(borrowProtocol.getBorrow_begin_date());// 还款开始日期
        bean.setRepay_end_date(borrowProtocol.getBorrow_end_date());// 还款结束日期
        // 判断是否是房贷的合同
        /*if (borrowProtocol.getProtocol_type().equals("1") || borrowProtocol.getProtocol_type().equals("2")
            || borrowProtocol.getProtocol_type().equals("3") || borrowProtocol.getProtocol_type().equals("4")
            || borrowProtocol.getProtocol_type().equals("6") || borrowProtocol.getProtocol_type().equals("7")
            || borrowProtocol.getProtocol_type().equals("12") || borrowProtocol.getProtocol_type().equals("11")
            || borrowProtocol.getProtocol_type().equals("9")|| borrowProtocol.getProtocol_type().equals("10")
            || borrowProtocol.getProtocol_type().equals("21")|| borrowProtocol.getProtocol_type().equals("22")
            || borrowProtocol.getProtocol_type().equals("23")|| borrowProtocol.getProtocol_type().equals("24"))*/
        if (wmsCreCreditHead.getCre_type().equals("2"))
        {
            // 合同金额
            BigDecimal borrow = borrowProtocol.getPrincipal_lower();
            // 贷款期数
            BigDecimal deadline = new BigDecimal(borrowProtocol.getBorrow_deadline());
            // 其中的本金=合同金额
            bean.setPrincipal(borrow);
            // 添加房贷1号 房贷2号的改变--先洗后本
            // if(borrowProtocol.getProtocol_type().equals("3")||borrowProtocol.getProtocol_type().equals("4")){
           /* if (borrowProtocol.getProtocol_type().equals("3") || borrowProtocol.getProtocol_type().equals("4")
                || borrowProtocol.getProtocol_type().equals("1") || borrowProtocol.getProtocol_type().equals("2")
                || borrowProtocol.getProtocol_type().equals("12") || borrowProtocol.getProtocol_type().equals("11")
                || borrowProtocol.getProtocol_type().equals("21") || borrowProtocol.getProtocol_type().equals("22"))*/
            //2016/4/12 baisong 不用原方法判断
            if("2".equals(borrowProtocol.getPayment_contract_type()))
    		/*
    		 * 1代表等额本息
			   2代表先息后本
			 */
            {
                // 月还款额 = 合同金额*2%(利率)
                BigDecimal refund_limit_month = borrow.multiply(borrow_interest);
                bean.setRefund_limit_month(refund_limit_month);// ROUND_UNNECESSARY为取绝对精度
                                                               // 断言请求的操作具有精确的结果，因此不需要舍入
                // 月还款本金 = 前n-1期：0 最后一期：合同金额
                BigDecimal cipal_limit_month = new BigDecimal(0);
                // 月利息 = 月还款额 - 月还款本金
                bean.setInterest_limit_month((refund_limit_month.subtract(cipal_limit_month)).divide(new BigDecimal(1),
                                                                                                     0,
                                                                                                     BigDecimal.ROUND_HALF_UP));
            }
            else
            {//等额本息
            	WmsCreCreditHead wmsHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
            	borrow = wmsHead.getAppro_limit().multiply(new BigDecimal(10000));// 面签金额==其中本金
                // 其中的本金=合同金额
                bean.setPrincipal(borrow);
                // 月还款额 = 合同金额/期数+合同金额*1.27%（和之后四舍五入取整）
                BigDecimal refund_limit_month = (borrow.divide(deadline, 8, BigDecimal.ROUND_HALF_UP).add(borrow.multiply(borrow_interest))).divide(new BigDecimal(
                                                                                                                                                                   1),
                                                                                                                                                    0,
                                                                                                                                                    BigDecimal.ROUND_HALF_UP);
                bean.setRefund_limit_month(refund_limit_month);// ROUND_UNNECESSARY为取绝对精度
                                                               // 断言请求的操作具有精确的结果，因此不需要舍入
                // 月还款本金 = 其中本金/期数（四舍五入取整）
                BigDecimal cipal_limit_month = borrow.divide(new BigDecimal(borrowProtocol.getBorrow_deadline()), 0,
                                                             BigDecimal.ROUND_HALF_UP);
                // 月利息 = 月还款额 - 月还款本金
                bean.setInterest_limit_month(refund_limit_month.subtract(cipal_limit_month));
            }
            // 剩余本金 公式=合同金额
            bean.setUn_pay_principal(borrow);
            // 其中的利息=月利息*期数
            bean.setInterest(bean.getInterest_limit_month()
                                 .multiply(new BigDecimal(borrowProtocol.getBorrow_deadline())));
            // 剩余利息=月利息*期数
            bean.setUn_pay_interest(bean.getInterest_limit_month()
                                        .multiply(new BigDecimal(borrowProtocol.getBorrow_deadline())));
        }
        //else if (borrowProtocol.getProtocol_type().equals("5") || borrowProtocol.getProtocol_type().equals("8"))
        //	2016/4/12  baisong 不用原方法判断是合同类型   1信贷2房贷3车贷
        else if (wmsCreCreditHead.getCre_type().equals("1"))
        {// 判断是否为信贷合同
            int number = wmsCreCreditHead.getCre_loan_type();// 信贷合同中贷款合同类型的id
            // (1-(月利率+居间服务利率)*期数),进位取整
            // BigDecimal sum = new
            // BigDecimal(1).subtract(borrow_interest.multiply(new
            // BigDecimal(borrowProtocol.getBorrow_deadline())));
            // 其中本金 = 面签确认金额=合同金额*(1-(月利率+居间服务利率)*期数),进位取整
            // BigDecimal cipal = new
            // BigDecimal(wmsCreCreditHead.getCredit_limit()).multiply(new
            // BigDecimal(10000));
            BigDecimal cipal = borrowProtocol.getPrincipal();
            // 其中本金
            bean.setPrincipal(cipal);
            // 其中利息= 合同金额-其中本金
            // BigDecimal interest =
            // borrowProtocol.getPrincipal_lower().subtract(cipal);
            BigDecimal interest = borrowProtocol.getInterest();
            bean.setInterest(interest);
            if (number == 284)
            {// 如果number==284则该合同为先息后本的特色贷
                // 月还款额 = 合同金额*2%(利率)
                BigDecimal refund_limit_month = borrowProtocol.getPrincipal_lower().multiply(borrow_interest);
                bean.setRefund_limit_month(refund_limit_month);// ROUND_UNNECESSARY为取绝对精度
                                                               // 断言请求的操作具有精确的结果，因此不需要舍入
                // 月还款本金 = 前n-1期：0 最后一期：合同金额
                BigDecimal cipal_limit_month = new BigDecimal(0);
                // 月利息 = 月还款额 - 月还款本金
                bean.setInterest_limit_month((refund_limit_month.subtract(cipal_limit_month)).divide(new BigDecimal(1),
                                                                                                     0,
                                                                                                     BigDecimal.ROUND_UP));
            }
            else
            {
                // 月还款额 = 合同金额/期数
                BigDecimal refund_limit_month = borrowProtocol.getPrincipal_lower()
                                                              .divide(new BigDecimal(
                                                                                     borrowProtocol.getBorrow_deadline()),
                                                                      0, BigDecimal.ROUND_UP);
                bean.setRefund_limit_month(refund_limit_month);
                // 月还款本金 = 其中本金/期数（进位取整）
                // BigDecimal cipal_limit_month = cipal.divide(new
                // BigDecimal(borrowProtocol.getBorrow_deadline()), 0,
                // BigDecimal.ROUND_UP);
                BigDecimal cipal_limit_month = borrowProtocol.getOrg_repay_principal();
                // 月利息 = 月还款额 - 月还款本金
                bean.setInterest_limit_month(refund_limit_month.subtract(cipal_limit_month));
            }
            // 剩余本金 公式=合同金额-总利息
            bean.setUn_pay_principal(cipal);
            // 剩余利息=其中利息=合同金额-其中本金
            bean.setUn_pay_interest(interest);
        }
        bean.setProtocol_creat_date(borrowProtocol.getProtocol_date());// 合同签订日期
        bean.setProtocol_amount(borrowProtocol.getPrincipal_lower());// 合同金额

        bean.setLiquidated_damages(new BigDecimal(borrowProtocol.getLiquidated_damages()));// 日滞纳金
        bean.setRepay_period(0);// 已还期数
        bean.setRepay_principal(new BigDecimal(0));// 已还本金
        if ((wmsCreCreditHead.getCre_loan_type()!=null&&wmsCreCreditHead.getCre_loan_type() == 284)||wmsCreCreditHead.getCre_type().equals("2")){
//        	if(borrowProtocol.getProtocol_type().equals("9")|| borrowProtocol.getProtocol_type().equals("10")
//        	||borrowProtocol.getProtocol_type().equals("23")|| borrowProtocol.getProtocol_type().equals("24"))
        	if("1".equals(borrowProtocol.getPayment_contract_type())){//等额本息
        		bean.setRepay_interest(new BigDecimal(0));// 已还利息
        	}else{
        		bean.setRepay_interest(bean.getInterest_limit_month());// 已还利息
            	bean.setUn_pay_interest(bean.getUn_pay_interest().subtract(bean.getRepay_interest()));//未还利息	
        	}
        }else{
        	bean.setRepay_interest(new BigDecimal(0));// 已还利息
        }
        bean.setUn_pay_period(borrowProtocol.getBorrow_deadline());// 未还期数

        bean.setCur_overdue_type("4");// 本期逾期类型 4 为正常
        bean.setCur_overdue_day(0);// 本期逾期天数为0
        bean.setCur_late_fee(new BigDecimal(0));// 本期滞纳金额
        bean.setTotal_overdue_period(0);// 总逾期期数
        bean.setTotal_overdue_day(0);// 总逾期天数
        bean.setTotal_late_fee(0);// 总应滞纳金额
        bean.setTotal_pay_late_fee(new BigDecimal(0));// 已缴纳滞纳金额
        bean.setTotal_derate(new BigDecimal(0));// 总减免额

        bean.setBack_interest_period(0);// 返利期数
        bean.setBack_ammont(new BigDecimal(0));// 返利金额
        bean.setRepay_status("1");// 还款状态为正常
        bean.setSalesman_id(wmsCreCreditHead.getSalesman_id());// 业务员id
        bean.setSalesman_name(wmsCreCreditHead.getSalesman_name());// 业务员姓名
        bean.setIs_upload("0");// 是否上传
        bean.setCreate_user_id(user.getUserId());// 创建人id
        bean.setCreate_user_name(user.getRealname());// 创建人名字
        bean.setCreate_timestamp(sysTime);// 创建时间
        bean.setEnable_flag("1");// 数据状态 0无效 1 有效
        bean.setMigration_status("0");// 是否申请转件 0 否 1 是
        bean.setPre_repay_status("0");// 是否申请提前还款0 否 1 是
        if (wmsCreCreditHead.getCre_type().equals("1"))
        {// 如果是信贷可匹配的乘以系数
            // 设置贷款还款信息表中的可匹配债权 = 合同金额*属性表中配置的值 现在为1.5
            List<Integer> list = new ArrayList<Integer>();
            list.add(23);
            List<WmsSysProperty> list1 = wmssyspropertyDao.searchforhouse(list);
            WmsSysProperty wms = (WmsSysProperty) list1.get(0);
            BigDecimal bd = new BigDecimal(wms.getProperty_value());
            BigDecimal matching_creditor = bd.multiply(bean.getProtocol_amount());
            bean.setMatching_creditor(matching_creditor);// 可匹配债权
        }
        else if (wmsCreCreditHead.getCre_type().equals("2"))
        {// 如果是房贷可匹配的等于合同金额
            bean.setMatching_creditor(bean.getProtocol_amount());// 可匹配债权
        }
        return bean;

    }
    
    /**
     * @Title: setWmsFinaCreRealrepayInfo 
     * @Description: (初始化应还款信息表，并返回初始化插入信息条数) 
     * @param wms_cre_credit_head_id
     * @return    
     * @return int    返回类型
     * @throws
     * @author lvtu
     */
    @Override
    public int setWmsFinaCreRealrepayInfo(int wms_cre_credit_head_id) {
    	List<WmsFinaCreRealrepayInfo> wmsFinaCreRealrepayInfoList = 
    			wmsFinaCreRealrepayInfoDao.getInitRealrepayInfoList(wms_cre_credit_head_id);
    	return wmsFinaCreRealrepayInfoDao.addBatchRealrepayInfo(wmsFinaCreRealrepayInfoList);
    }

    /**
     * 设置本期应还款日--2015-8-1暂时作废
     * 
     * @param wmsFinaCreLoanApp
     * @return
     */
    private  Date setCurrent_repay_date_old(Date date, int i)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        java.sql.Date date1 = new Date(date.getTime());
        if (calendar.get(Calendar.DAY_OF_MONTH) - 1 == 0)
        {
            calendar.add(Calendar.MONTH, +1 + i);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            java.util.Date date_ = calendar.getTime();
            date1 = new java.sql.Date(date_.getTime());
        }
        else
        {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            calendar.add(Calendar.MONTH, +1 + i);
            java.util.Date date_ = calendar.getTime();
            date1 = new java.sql.Date(date_.getTime());
        }
        return date1;
    }
    /**
     * 查询 WmsFinaCreLoanApp 对象
     * 
     * @param wms_cre_credit_head_id
     * @return WmsFinaCreLoanApp
     */
    public WmsFinaCreLoanApp getWmsFinaCreLoanApp(int wms_cre_credit_head_id)
    {
        WmsFinaCreLoanApp wmsFinaCreLoanApp = wmsfinacreloanappDao.getWmsFinaCreLoanAppByFk(wms_cre_credit_head_id);

        return wmsFinaCreLoanApp;
    }

    /**
     * 查询 WmsCreCreditHead 对象
     * 
     * @param wms_cre_credit_head_id
     * @return WmsCreCreditHead
     */
    public WmsCreCreditHead getWmsCreCreditHead(int wms_cre_credit_head_id)
    {
        WmsCreCreditHead wmsCreCreditHead = wmscrecreditheadDao.get(wms_cre_credit_head_id);
        return wmsCreCreditHead;
    }

    /**
     * 查询 WmsCreApproBorrowProtocol对象
     * 
     * @param wms_cre_credit_head_id
     * @return WmsCreApproBorrowProtocol
     */
    public WmsCreApproBorrowProtocol getWmsCreApproBorrowProtocol(int wms_cre_credit_head_id)
    {
        WmsCreApproBorrowProtocol borrowProtocol = wmscreapproborrowprotocolDao.getBorrowProtocolByFK(wms_cre_credit_head_id);
        return borrowProtocol;

    }

   

    /**
     * 逾期还款确认导出excel baisong
     */
    @Override
    public Map<String, Object> getListWithoutPagingforyuqi(WmsFinaCreRepaySearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (StringUtil.isNotBlank(queryInfo.getDunning_status()) && !"-1".equals(queryInfo.getDunning_status()))
        {
            paramMap.put("dunning_status", queryInfo.getDunning_status());
        }
        if (queryInfo.getRepayment_date_start()!=null)
        {
            paramMap.put("repayment_date_start", queryInfo.getRepayment_date_start());
        }
        if (queryInfo.getRepayment_date_end()!=null)
        {
            paramMap.put("repayment_date_end", queryInfo.getRepayment_date_end());
        }
        if (queryInfo.getCurrent_repay_date_start()!=null)
        {
            paramMap.put("current_repay_date_start", queryInfo.getCurrent_repay_date_start());
        }
        if (queryInfo.getCurrent_repay_date_end()!=null)
        {
            paramMap.put("current_repay_date_end", queryInfo.getCurrent_repay_date_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsfinacrerepayDao.searchforyuqiexcel(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        for(int i=0;i<list.size();i++){
        	Map<String, Object> map=list.get(i);
        	if(("".equals(map.get("home_dunning_status"))||map.get("home_dunning_status")==null)&& "1".equals(map.get("loansupervisor_result"))){
        		map.put("home_dunning_status_str", "电话催缴");
        	}else if((!"".equals(map.get("home_dunning_status"))&&map.get("home_dunning_status")!=null)||("2".equals(map.get("loansupervisor_result")))){
        		map.put("home_dunning_status_str", "上门催缴");
        	}
        	list.remove(i);
        	list.add(i, map);
        }
        resMap.put("Rows", list);
        return resMap;
    }
//    /**
//     * 服务管理--贷后管理--贷后查询
//     * @author hancd
//     */
//    @Override
//    public Map<String, Object> getPostLoanSearchListWithPaging(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
//    {
//        Map<String,Object>paramMap = new HashMap<>();
//        //合同编号
//        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
//        {
//            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
//        }
//        //客户姓名
//        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
//        {
//            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
//        }
//        //客户电话
//        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
//        {
//            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
//        }
//        //业务员姓名或编号
//        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
//        {
//            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
//        }
//        //产品
//        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
//        {
//            paramMap.put("cre_type", queryInfo.getCre_type());
//        }
//        //还款状态
//        if(StringUtil.isNotBlank(queryInfo.getRepay_status()) && !"-1".equals(queryInfo.getRepay_status()))
//        {
//            paramMap.put("repay_status", queryInfo.getRepay_status());
//        }
//        //上门催缴状态
//        if(StringUtil.isNotBlank(queryInfo.getHome_dunning_status())&&!"-1".equals(queryInfo.getHome_dunning_status()))
//        {   
//            paramMap.put("home_dunning_status", queryInfo.getHome_dunning_status());
//        }
//        //催缴状态
//        if(StringUtil.isNotBlank(queryInfo.getDunning_status())&&!"-1".equals(queryInfo.getDunning_status()))
//        {   
//            paramMap.put("dunning_status", queryInfo.getDunning_status());
//        }
//        //贷后专员
//        if(StringUtil.isNotBlank(queryInfo.getDunning_name())){
//            paramMap.put("dunning_name", SysUtil.getSqlLikeParam(queryInfo.getDunning_name()));
//        }
//        paramMap.put("sortname", queryInfo.getSortname());
//        paramMap.put("sortorder", queryInfo.getSortorder());
//        paramMap.put("pagesize", queryInfo.getPagesize());
//        paramMap.put("offset", queryInfo.getOffset());
//        List<Map<String,Object>> list = wmsfinacrerepayDao.searchforPostLoan(paramMap);
//        GridDataBean<Map<String,Object>> bean = new 
//                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacrerepayDao.findCountforPostLoan(paramMap),list);
//        return bean.getGridData();
//    }
//    /**
//     * 服务管理--贷后管理--贷后查询导出
//     * @author hancd
//     */
//    @Override
//    public Map<String, Object> getPostLoanSearchWithoutPagingList(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
//    {
//        Map<String,Object>paramMap = new HashMap<>();
//        //合同编号
//        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
//        {
//            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
//        }
//        //客户姓名
//        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
//        {
//            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
//        }
//        //客户电话
//        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
//        {
//            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
//        }
//        //业务员姓名或编号
//        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
//        {
//            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
//        }
//        //产品
//        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
//        {
//            paramMap.put("cre_type", queryInfo.getCre_type());
//        }
//        //还款状态
//        if(StringUtil.isNotBlank(queryInfo.getRepay_status()) && !"-1".equals(queryInfo.getRepay_status()))
//        {
//            paramMap.put("repay_status", queryInfo.getRepay_status());
//        }
//        //上门催缴状态
//        if(StringUtil.isNotBlank(queryInfo.getHome_dunning_status())&&!"-1".equals(queryInfo.getHome_dunning_status()))
//        {   
//            paramMap.put("home_dunning_status", queryInfo.getHome_dunning_status());
//        }
//        //催缴状态
//        if(StringUtil.isNotBlank(queryInfo.getDunning_status())&&!"-1".equals(queryInfo.getDunning_status()))
//        {   
//            paramMap.put("dunning_status", queryInfo.getDunning_status());
//        }
//        //贷后专员
//        if(StringUtil.isNotBlank(queryInfo.getDunning_name())){
//            paramMap.put("dunning_name", SysUtil.getSqlLikeParam(queryInfo.getDunning_name()));
//        }
//        paramMap.put("dunning_id", user.getUserId());
//        paramMap.put("sortname", queryInfo.getSortname());
//        paramMap.put("sortorder", queryInfo.getSortorder());
//        List<Map<String,Object>> list =wmsfinacrerepayDao.searchforPostLoan(paramMap);
//        Map<String, Object> resMap = new HashMap<String, Object>();
//        resMap.put("Rows",list);
//        return resMap;
//    }
    /**
     * 服务管理--贷后管理--贷后查询导出--电话催缴--excel
     * @author baisong
     */
    @Override
    public Map<String, Object> getPostLoanSearchWithoutPagingListCommissionerexcel(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String,Object>paramMap = new HashMap<>();
        //合同编号
        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        //业务员姓名或编号
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //还款状态
        if(StringUtil.isNotBlank(queryInfo.getRepay_status()) && !"-1".equals(queryInfo.getRepay_status()))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        //上门催缴状态
        if(StringUtil.isNotBlank(queryInfo.getHome_dunning_status())&&!"-1".equals(queryInfo.getHome_dunning_status()))
        {   
            paramMap.put("home_dunning_status", queryInfo.getHome_dunning_status());
        }
        //催缴状态
        if(StringUtil.isNotBlank(queryInfo.getDunning_status())&&!"-1".equals(queryInfo.getDunning_status()))
        {   
            paramMap.put("dunning_status", queryInfo.getDunning_status());
        }
        //贷后专员
        if(StringUtil.isNotBlank(queryInfo.getDunning_name())){
            paramMap.put("dunning_name", SysUtil.getSqlLikeParam(queryInfo.getDunning_name()));
        }
        paramMap.put("dunning_id", user.getUserId());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String,Object>> list =wmsfinacrerepayDao.searchforPostLoanCommissioner(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows",list);
        return resMap;
    }
    /**
     * 服务管理--贷后管理--贷后查询导出--上门催缴--excel
     * @author baisong
     */
    @Override
    public Map<String, Object> getPostLoanSearchWithoutPagingListexcel(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String,Object>paramMap = new HashMap<>();
        //合同编号
        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        //业务员姓名或编号
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //还款状态
        if(StringUtil.isNotBlank(queryInfo.getRepay_status()) && !"-1".equals(queryInfo.getRepay_status()))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        //上门催缴状态
        if(StringUtil.isNotBlank(queryInfo.getHome_dunning_status())&&!"-1".equals(queryInfo.getHome_dunning_status()))
        {   
            paramMap.put("home_dunning_status", queryInfo.getHome_dunning_status());
        }
        //催缴状态
        if(StringUtil.isNotBlank(queryInfo.getDunning_status())&&!"-1".equals(queryInfo.getDunning_status()))
        {   
            paramMap.put("dunning_status", queryInfo.getDunning_status());
        }
        //贷后专员
        if(StringUtil.isNotBlank(queryInfo.getDunning_name())){
            paramMap.put("dunning_name", SysUtil.getSqlLikeParam(queryInfo.getDunning_name()));
        }
        paramMap.put("dunning_id", user.getUserId());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String,Object>> list =wmsfinacrerepayDao.searchforPostLoanVisit(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows",list);
        return resMap;
    }
    
    /**
     * 服务管理--贷后管理--贷后查询-查看客户信息
     * @param wms_cre_credit_head_id
     * @param wms_fina_cre_pay_id
     * @return list
     * @author hancd
     */
    @Override
    public Map<String, Object> getWmsFinaCreRepayBypk(UserBean user , Integer wms_cre_credit_head_id, Integer wms_fina_cre_pay_id)
    {
        Map<String,Object>paramMap = new HashMap<>();
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        paramMap.put("wms_fina_cre_pay_id", wms_fina_cre_pay_id);
        Map<String,Object> paMap = wmsfinacrerepayDao.getInfo(paramMap);
        paMap.put("userId", user.getUserId());//id
        paMap.put("userName", user.getRealname());//姓名
        return paMap;
    }
    /**
     * 服务管理--贷后管理--贷后查询--贷后专员查询--电话催缴
     * @author baisong
     */
    @Override
    public Map<String, Object> getListWithoutPagingCommissioner(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String,Object>paramMap = new HashMap<>();
        //合同编号
        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        //业务员姓名或编号
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //还款状态
        if(StringUtil.isNotBlank(queryInfo.getRepay_status()) && !"-1".equals(queryInfo.getRepay_status()))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        paramMap.put("dunning_id", user.getUserId());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinacrerepayDao.searchforPostLoanCommissioner(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacrerepayDao.findCountCommissioner(paramMap),list);
        return bean.getGridData();
    }
    /**
     * 服务管理--贷后管理--贷后查询--贷后专员查询--上门催缴列表 
     * @author baisong
     */
    @Override
    public Map<String, Object> postLoanVisitSearchWithPagingList(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String,Object>paramMap = new HashMap<>();
        //合同编号
        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        //业务员姓名或编号
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //业务员姓名或编号
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //上门催缴状态
        if(StringUtil.isNotBlank(queryInfo.getHome_dunning_status()) && !"-1".equals(queryInfo.getHome_dunning_status()))
        {
            paramMap.put("home_dunning_status", SysTools.getSqlLikeParam(queryInfo.getHome_dunning_status()));
        }
        //上门催缴状态
        if(StringUtil.isNotBlank(queryInfo.getDunning_name()))
        {
            paramMap.put("dunning_name", queryInfo.getDunning_name());
        }
        paramMap.put("dunning_id", user.getUserId());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinacrerepayDao.searchforPostLoanVisit(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacrerepayDao.findCountVisit(paramMap),list);
        return bean.getGridData();
    }
    

    /**
     * 服务管理--贷后管理--贷后查询--贷后专员查询--催缴分配查询
     * @author baisong
     */
    @Override
    public Map<String, Object> getListWithoutPagingAllocation(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String,Object>paramMap = new HashMap<>();
        //合同编号
        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        //客户姓名
        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        //客户电话
        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        //业务员姓名或编号
        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        //产品
        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        //催缴人姓名
        if(StringUtil.isNotBlank(queryInfo.getDunning_name()))
        {
        	paramMap.put("dunning_name", SysTools.getSqlLikeParam(queryInfo.getDunning_name()));
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinacrerepayDao.searchforPostLoanAllocation(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinacrerepayDao.findCountforPostLoanAllocation(paramMap),list);
        return bean.getGridData();
    }
    /**
     * 贷后专员查询贷款单据--贷后专员分配--导出excel--催缴分配
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
	@Override
	public Map<String, Object> getListWithoutAllocation(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
	    {
		 Map<String,Object>paramMap = new HashMap<>();
	        //合同编号
	        if(StringUtil.isNotBlank(queryInfo.getProtocol_code()))
	        {
	            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
	        }
	        //客户姓名
	        if(StringUtil.isNotBlank(queryInfo.getDebtor_name()))
	        {
	            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
	        }
	        //客户电话
	        if(StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
	        {
	            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
	        }
	        //业务员姓名或编号
	        if(StringUtil.isNotBlank(queryInfo.getSalesman_name()))
	        {
	            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
	        }
	        //产品
	        if(StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
	        {
	            paramMap.put("cre_type", queryInfo.getCre_type());
	        }
	        //催缴人姓名
	        if(StringUtil.isNotBlank(queryInfo.getDunning_name()))
	        {
	        	paramMap.put("dunning_name", SysTools.getSqlLikeParam(queryInfo.getDunning_name()));
	        }
	        paramMap.put("sortname", queryInfo.getSortname());
	        paramMap.put("sortorder", queryInfo.getSortorder());
	        List<Map<String,Object>> list =wmsfinacrerepayDao.getListWithoutAllocation(paramMap);
	        Map<String, Object> resMap = new HashMap<String, Object>();
	        resMap.put("Rows",list);
	        return resMap;
	}
	 /**
     * 清空数据库贷款还款表中的 贷后专员信息 id  name  deptid
     * @param wms_cre_credit_head_id
     * @return
     * baisong
     */
	  @Override
	    public String udpatenull(Integer wms_fina_cre_pay_id)
	    {
		  String result="success";
		  int ret=0;
		  WmsFinaCreRepay wmsFinaCreRepay =new WmsFinaCreRepay();
		  wmsFinaCreRepay.setWms_fina_cre_pay_id(wms_fina_cre_pay_id);
		  ret= wmsfinacrerepayDao.udpatenull(wmsFinaCreRepay);
		  if(ret==0){
			  result="error";
		  }
	        return result;
	    }

   /**
     * 新增获取房贷债权详情接口
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
	@Override
	public Map<String, Object> getInfoforPTPhosue(
			String idList, UserBean user) {
		 Map<String,Object>paramMap = new HashMap<>();
		 List<Integer> list1= JsonUtil.jsonArrayToList(idList, Integer.class);
		 paramMap.put("idList", list1);
		 List<Map<String, Object>> list=	wmsfinacrerepayDao.getInfoforPTPhosue(paramMap);
		 paramMap.clear();
		 paramMap.put("val", list);
		return paramMap;
	}
	   /**
     * 新增获取信贷债权详情接口
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
	@Override
	public Map<String, Object> getInfoforPTP(
			String idList, UserBean user) {
		 Map<String,Object> paramMap = new HashMap<>();
		 List<Integer> list1= JsonUtil.jsonArrayToList(idList, Integer.class);
		 paramMap.put("idList", list1);
		 List<Map<String, Object>> list=wmsfinacrerepayDao.getInfoforPTP(paramMap);
		 paramMap.clear();
		 paramMap.put("val", list);
		return paramMap;
	}
	

	 /**
     * 终止合同审核 列表
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @Override
    public Map<String, Object> getListWithPagingforstop(WmsFinaGetInfoStopBeanVO queryInfo)
    {
        Map<String, Object> paramMap =new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (queryInfo.getDamages_date_start()!=null)
        {
            paramMap.put("damages_date_start", queryInfo.getDamages_date_start());
        }
        if (queryInfo.getDamages_date_end()!=null)
        {
            paramMap.put("damages_date_end", queryInfo.getDamages_date_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getRepay_status()))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsfinacrerepayDao.searchforstop(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsfinacrerepayDao.findCountforstop(paramMap),
                                                                                       list);
        return bean.getGridData();
    }
    /**
     * 终止合同确认列表
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @Override
    public Map<String, Object> getListWithPagingforaffirm(WmsFinaGetInfoStopBeanVO queryInfo)
    {
        Map<String, Object> paramMap =new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (StringUtil.isNotBlank(queryInfo.getRepay_status()))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsfinacrerepayDao.searchforaffirm(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsfinacrerepayDao.findCountforaffirm(paramMap),
                                                                                       list);
        return bean.getGridData();
    }
    /**
     * 终止合同审核 导出excel
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @Override
    public Map<String, Object> getListWithPagingforstopexcel(WmsFinaGetInfoStopBeanVO queryInfo)
    {
        Map<String, Object> paramMap =new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (queryInfo.getDamages_date_start()!=null)
        {
            paramMap.put("damages_date_start", queryInfo.getDamages_date_start());
        }
        if (queryInfo.getDamages_date_end()!=null)
        {
            paramMap.put("damages_date_end", queryInfo.getDamages_date_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getRepay_status()))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsfinacrerepayDao.searchforstop(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }
    /**
     * 终止合同确认导出excel
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @Override
    public Map<String, Object> getListWithPagingforaffirmexcel(WmsFinaGetInfoStopBeanVO queryInfo)
    {
        Map<String, Object> paramMap =new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_type()) && !"-1".equals(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (StringUtil.isNotBlank(queryInfo.getRepay_status()))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsfinacrerepayDao.searchforaffirm(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

	 /**
     * 终止合同审核 获取页面数据
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @Override
    public Map<String, Object> getInfoforstop(Integer wms_cre_credit_head_id) {
    	 Map<String, Object> params =new HashMap<>();
    	 params.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
    	 params.put("post_code", WmsHelp.POST_TDJL_CODE);//团队经理职务编码
		 Map<String, Object> paramMap=wmsfinacrerepayDao.getinfostop(params);
		return paramMap;
	}
		/***
		 * 单据作废信贷签合同
		 */
	@Override
	public String periodAndCreRepaySaveCancel(UserBean user,
			WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean, String cre_type)
			throws ParseException {
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
        if("1".equals(cre_type)){//信贷
			 // 信贷流程
	        WmsCreditWorkFlowVO approveWorkFlowVO = new WmsCreditWorkFlowVO();
	        approveWorkFlowVO.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
	        approveWorkFlowVO.setTaskId(bean.getTaskId());
	        approveWorkFlowVO.setUser_id(user.getUserId());
	        approveWorkFlowVO.setAdvice(bean.getAdvice());
	        WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(hbean.getWms_cre_credit_head_id());
	        /*if(wmsCreCreditHead.getCreate_timestamp().before(new Timestamp(bean.getTimestamp_val().getTime()))){
	        	 approveWorkFlowVO.setPass("true");//单据作废
	        }else{*/
	        	approveWorkFlowVO.setPass("false");//单据作废	
	        //}
	        creditWorkFlowService.makeLoansaffirm(approveWorkFlowVO);
        }
		return resStr;
	}
	/***
     * 单据作废--车贷
     * @param request
     * @param bean
     * @param hbean
     * @return
     * baisong
     */
	@Override
	public String carPeriodAndCreRepaySaveCancel(UserBean user,WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean)throws ParseException {
        String resStr = "success";
        //贷款主表处理
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
        WmsCarLoanWorkFlowVO wVo=new WmsCarLoanWorkFlowVO();//流程
		//给流程赋值
        wVo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
        if("9".equals(bean.getCarkey())){//nullify
        	wVo.setPass("nullify");//是否通过作废
        }else{
        	wVo.setPass("false");//是否通过
        }
        wVo.setAdvice(bean.getAdvice());//意见
        wVo.setUserId(String.valueOf(user.getUserId()));//操作人id
        wVo.setTaskId(bean.getTaskId());//流程节点id
        //carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
        carLoanWorkFlowService.carLoanApprovalProcess(wVo,bean.getCarkey());
		return resStr;
	}
	/**
	 * 实现结清证明打印功能
	 */
	@Override
	public Map<String, Object> getWmsPostforClearById(
			Integer wms_cre_credit_head_id) {
		Map<String,Object> param = new HashMap<>();
		param.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
		return wmsfinacrerepayDao.getWmsPostforClearById(param);
	}
	/**
     * 设置本期应还款日-2015-8-1启用
     * 
     * @param wmsFinaCreLoanApp
     * @return
     */
    private  Date setCurrent_repay_date(Date date, int i)
    {
    	int days=0;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        java.sql.Date date1 = new Date(date.getTime());
        if (calendar.get(Calendar.DAY_OF_MONTH) - 1 == 0 && days==0)
        {
            calendar.add(Calendar.MONTH, +1 + i);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            if( calendar.get(Calendar.DAY_OF_MONTH)==31)
            {//判断如果是二月份
            	calendar.add(Calendar.DAY_OF_MONTH, -1);
            }
            if( calendar.get(Calendar.MONTH)==2)
            {//判断如果是二月份
            	days=calendar.get(Calendar.DAY_OF_MONTH);
            	calendar.add(Calendar.DAY_OF_MONTH,0);
            }
            java.util.Date date_ = calendar.getTime();
            date1 = new java.sql.Date(date_.getTime());
        }
        else if(calendar.get(Calendar.DAY_OF_MONTH)==31)
        {
        	
        	 calendar.add(Calendar.MONTH, +1 + i);
             if( calendar.get(Calendar.DAY_OF_MONTH)==31)
             {//判断如果是月份天数31
             	calendar.add(Calendar.DAY_OF_MONTH, -1);
             }
             if( calendar.get(Calendar.MONTH)==2)
             {//判断如果是二月份
             	days=calendar.get(Calendar.DAY_OF_MONTH);
             	calendar.add(Calendar.DAY_OF_MONTH,0);
             }
             java.util.Date date_ = calendar.getTime();
             date1 = new java.sql.Date(date_.getTime());
        }
        else
        {
            calendar.add(Calendar.MONTH, +1 + i);
            if(days!=0)
            {
            	int sub=30-calendar.get(Calendar.DAY_OF_MONTH);
            	if(sub>0)
            	{
            		sub=0;
            	}
            	calendar.add(Calendar.DAY_OF_MONTH,sub);
            }
            else
            {
            	calendar.add(Calendar.DAY_OF_MONTH, -1);
            }
            java.util.Date date_ = calendar.getTime();
            date1 = new java.sql.Date(date_.getTime());
        }
        return date1;
    }

    /**
     * @Title: houseCancel
     * @Description: (单据作废 房贷)
     * @param user
     * @param bean
     * @param hbean
     * @param cre_type
     * @return String
     * @author: baisong
     * @time:2016年12月28日 下午4:15:15
     * @see com.zx.emanage.loanfina.service.IWmsFinaCreRepayService#houseCancel(com.zx.sframe.util.vo.UserBean, com.zx.emanage.loanfina.vo.WmsFinaCreCancelBeanVo, com.zx.emanage.util.gen.entity.WmsCreCreditHead, java.lang.String)
     * history:
     * 1、2016年12月28日 baisong 创建方法
     */
	@Override
	@Transactional
    public String houseCancel(UserBean user, WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean, String cre_type)
    {
	    String resStr = "success";
	    Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        // (获取组合贷单据情况)
        List<Map<String, Object>> list = wmsCreCreditHeadDao.getGroupHeadByRepaymentType(hbean.getWms_cre_credit_head_id());
        // 是否是全部作废
        boolean is_all = false;
        int num = 0;
        // 2条都为等额本息单据，作废其中一条，另一条不作废（3条组合贷，2条等额本息，1条先息后本，作废其中一条等额本息，其它两条不会被作废）
        // 循环单据判断还款类型
        for (Map<String, Object> param : list)
        {
            // 判断是否是等额本金 payment_contract_type 1等额本金2先息后本
            if (param != null && param.get("payment_contract_type") != null && "1".equals(param.get("payment_contract_type").toString())
                    && param.get("wms_cre_credit_head_id") != null 
                    && hbean.getWms_cre_credit_head_id().toString().equals(param.get("wms_cre_credit_head_id").toString()))
            {
                is_all = true;

            }
            // 判断是否是等额本息
            if (param != null && param.get("payment_contract_type") != null && "1".equals(param.get("payment_contract_type").toString()))
            {
                num++;
            }
        }
        // 判断是否是多条等额本息
        if (num > 1)
        {
            is_all = false;
        }
        if (list != null && list.size() > 0)
        {
            // 循环贷款单据
            for (Map<String, Object> map : list)
            {
                // 如果作废的是等额本金则全部作废 如果作废的不是等额本金 则作废用户选择的那条
                if (is_all || hbean.getWms_cre_credit_head_id().toString().equals(map.get("wms_cre_credit_head_id").toString()))
                {
                    WmsCreCreditHead wHead=new WmsCreCreditHead();
                    wHead.setWms_cre_credit_head_id(Integer.valueOf(map.get("wms_cre_credit_head_id").toString()));
                    wHead.setNullify_type(bean.getNullify_type());//房贷单据作废  5 贷款终审  6--合同 7--公证    8--他项   9--放款申请   10--放款审批   11--放款确认
                    wHead.setNullify_user_name(user.getRealname());
                    wHead.setNullify_user_id(user.getUserId());
                    wHead.setNullify_timestamp(sysTime);
                    wHead.setNullify_reason(bean.getAdvice());//
                    int ret= wmsCreCreditHeadDao.update(wHead);//更新贷款主表
                    if (ret == 0)
                    {
                        resStr = "error";
                        return resStr;
                    }
                    // 流程
                    WmsHouseCreditWorkFlowVO approveWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                    approveWorkFlowVO.setWms_cre_credit_head_id(map.get("wms_cre_credit_head_id").toString());
                    approveWorkFlowVO.setUserId(user.getUserId().toString());
                    approveWorkFlowVO.setAdvice(bean.getAdvice());
                    approveWorkFlowVO.setPass("nullify");//单据作废
                    approveWorkFlowVO.setDebtkey(bean.getNullify_type());//标示当前节点
                    wmsLoanWorkFlowService.publicApprovalStatus(approveWorkFlowVO);
                }
            }   
        }
        return resStr;
	}
	
	/**
     * 
     * @Title: housingMakeLoansSaveForCobine
     * @Description: discrpition：放款申请组合贷保存
     * @param queryInfo
     * @return 
     * @author: wangyihan
     * @time:2016年12月26日 下午5:03:03
     * history:
     * 1、2016年12月26日 wangyihan 创建方法
     */
    @Override
    @Transactional
    public WmsFinaCreLoanAppSearchBeanVO housingMakeLoansSaveForCobine(WmsFinaCreLoanAppSearchBeanVO queryInfo, UserBean user) {
        String resStr = "success";

        List<Map<String, Object>> wmsCreCreditHeadList = 
                new Gson().fromJson(queryInfo.getWms_cre_credit_head_list_json(), new TypeToken<List<HashMap<String, Object>>>(){}.getType());  
        
        if(StringUtils.isNotEmpty(queryInfo.getSave_type())) {
            if("1".equals(queryInfo.getSave_type())) {//放款申请
                Map<String, Object> paramMap = new HashMap<String, Object>();
                if(queryInfo.getWms_fina_cre_loan_app_arr() != null &&
                        queryInfo.getWms_fina_cre_loan_app_arr().length > 0) {
                    paramMap.put("wms_fina_cre_loan_app_arr", queryInfo.getWms_fina_cre_loan_app_arr());
                    //删除原有放款申请数据(多条单据)
                    this.wmsfinacreloanappDao.deleteByMap(paramMap);
                    //删除原有放款申请相关附件(多条单据)
                    wmsfinacreloanappattDao.deleteByMap(paramMap);
                }
                
                Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间

                //新增放款申请数据(多条单据)
                List<WmsFinaCreLoanApp> wmsFinaCreLoanAppList = JSON.parseArray(queryInfo.getWms_fina_cre_loan_app_list_json(), WmsFinaCreLoanApp.class);
                //附件list
                List<WmsFinaCreLoanAppAtt> attachlist = JsonUtil.jsonArrayToList(queryInfo.getFileArr(), WmsFinaCreLoanAppAtt.class);
                
                WmsFinaCreLoanApp bean = new WmsFinaCreLoanApp();
                WmsFinaCreLoanAppAtt wmsFinaCreLoanAppAtt = new WmsFinaCreLoanAppAtt();
                //新增放款申请数据
                for (int i = 0; i < wmsFinaCreLoanAppList.size(); i++)
                {
                    bean = wmsFinaCreLoanAppList.get(i);
                    bean.setWms_fina_cre_loan_app(null);
                    bean.setCreate_user_id(user.getUserId()); // 创建人id
                    bean.setCreate_user_name(user.getRealname());// 创建人名字
                    bean.setCreate_timestamp(sysTime);// 创建时间
                    bean.setEnable_flag("1");// 是否有效
                    bean.setLast_update_user_id(user.getUserId());
                    bean.setLast_update_timestamp(sysTime);
                    wmsfinacreloanappDao.save(bean);
                    //新增放款申请相关附件(多条单据)
                    for (int j = 0; j < attachlist.size(); j++)
                    {
                        wmsFinaCreLoanAppAtt = attachlist.get(j);
                        wmsFinaCreLoanAppAtt.setWms_fina_cre_loan_app_id(bean.getWms_fina_cre_loan_app());
                        wmsFinaCreLoanAppAtt.setCreate_user_id(user.getUserId()); // 创建人id
                        wmsFinaCreLoanAppAtt.setCreate_user_name(user.getRealname());// 创建人名字
                        wmsFinaCreLoanAppAtt.setCreate_timestamp(sysTime);// 创建时间
                        wmsFinaCreLoanAppAtt.setEnable_flag("1");// 是否有效
                        wmsfinacreloanappattDao.save(wmsFinaCreLoanAppAtt);
                    }
                }
                
                WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                for(Map<String, Object> tmpMap : wmsCreCreditHeadList) {
                        approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                        approveHouseWorkFlowVO.setWms_cre_credit_head_id(tmpMap.get("wms_cre_credit_head_id").toString());
                        approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
                    // 判断流程标示是否为空
                    if (tmpMap.get("is_workflow") != null)
                    {
                        approveHouseWorkFlowVO.setIs_workflow(tmpMap.get("is_workflow").toString());
                    }
                        if (tmpMap.get("edition_num") != null && "2".equals(tmpMap.get("edition_num").toString()))
                        {// 1为旧流程2为新流程
                            // 数据来源1为pc 2为移动端
                            if (tmpMap.get("version_number") != null && "2".equals(tmpMap.get("version_number").toString()))
                            {// 2016/5/10版本
                                approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
                            }
                            else
                            {// 2016/2/10版本
                                approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
                            }
                            approveHouseWorkFlowVO.setDebtkey("9");
                            approveHouseWorkFlowVO.setPass("true");
                            wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
                        }
                        else if ("1".equals(queryInfo.getEdition_num()))
                        {
                            houseCreditWorkFlowService.theMortgageLoanWorkFlow(approveHouseWorkFlowVO);
                        }
                }
            } else if("2".equals(queryInfo.getSave_type())) {//放款审核：逻辑参照WmsCreCreditApproServiceImpl 1835行
                WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                for(Map<String, Object> tmpMap : wmsCreCreditHeadList) {
                    WmsCreCreditHead wmsCreCreditHead = this.wmsCreCreditHeadDao.get(Integer.parseInt(tmpMap.get("wms_cre_credit_head_id").toString()));
                    // 如果单据通过则同步信息并处理还款信息
                    if ("true".equals(queryInfo.getPass()))
                    {

                        CreditMessageToRepayWarnBeanVO beanvo = new CreditMessageToRepayWarnBeanVO();
                        // 获取信息放款数据同步到还款提醒模块
                        beanvo.setWms_cre_credit_head_id(wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                        // 贷款类型-房贷
                        beanvo.setCre_type("2");
                        String result = wmsCreCreditNotaryWarnService.getHouseCreditMessageToRepayWarn(beanvo, user);
                        // 单据类型--业务状态： 01 新增、02 展期、03 续期--单据通过
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

                    approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                    approveHouseWorkFlowVO.setWms_cre_credit_head_id(tmpMap.get("wms_cre_credit_head_id").toString());
                    approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
                    approveHouseWorkFlowVO.setPass(queryInfo.getPass());
                    approveHouseWorkFlowVO.setAdvice(queryInfo.getAdvice());
                    approveHouseWorkFlowVO.setDebtkey("FKSP");
                    wmsLoanWorkFlowService.publicApprovalStatus(approveHouseWorkFlowVO);
                    // 更改放款申请表，放款审批时间
                    Map<String, Object> appMap = new HashMap<String, Object>();
                    appMap.put("loan_date", new Date(System.currentTimeMillis()));
                    appMap.put("wms_cre_credit_head_id", approveHouseWorkFlowVO.getWms_cre_credit_head_id());
                    wmsFinaCreLoanAppDao.updateLoanDate(appMap);

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
                }
            } else if("3".equals(queryInfo.getSave_type())) {//放款确认：逻辑参照WmsFinaCreRepayServiceImpl 1548行
                Map<String, Object> paramMap = new HashMap<String, Object>();
                WmsCreApproBorrowProtocol borrowProtocol = new WmsCreApproBorrowProtocol();
                Integer wms_cre_credit_head_id = queryInfo.getWms_cre_credit_head_id();
                WmsFinaCreRepay bean = new WmsFinaCreRepay();
                CreditMessageToRepayWarnBeanVO beanvo = new CreditMessageToRepayWarnBeanVO();
                WmsFinaCreLoanApp wmsFinaCreLoanApp = new WmsFinaCreLoanApp();
                WmsCreCreditHead wmsCreCreditHead = new WmsCreCreditHead();
                WmsCreApproServiceProtocol wmsCreApproServiceProtocol = new WmsCreApproServiceProtocol();
                int beanRet = 0;
                int bean1Ret = 0;
                WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                for(Map<String, Object> tmpMap : wmsCreCreditHeadList) {
                    paramMap = new HashMap<String, Object>();
                    wms_cre_credit_head_id = Integer.parseInt(tmpMap.get("wms_cre_credit_head_id").toString());
                    borrowProtocol = wmscreapproborrowprotocolDao.getBorrowProtocolByFK(wms_cre_credit_head_id);
                    if (borrowProtocol.getProtocol_id_num().length() < 6) {
                        paramMap.put("loan_date", borrowProtocol.getProtocol_date());
                    } else {
                        paramMap.put("loan_date", new java.util.Date());
                    }
                    paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
                    wmsfinacreloanappDao.updateLoanDate(paramMap);

                    wmsCreCreditHead = this.wmsCreCreditHeadDao.get(wms_cre_credit_head_id);

                    paramMap.put("sortname", "");
                    paramMap.put("sortorder", "");

                    wmsFinaCreLoanApp = JSONArray.parseObject(JSONArray.toJSONString(this.wmsfinacreloanappDao.search(paramMap).get(0)), WmsFinaCreLoanApp.class);
                    wmsCreApproServiceProtocol = wmscreapproserviceprotocolDao.getbypk(wms_cre_credit_head_id);
                    beanRet = 0;
                    bean1Ret = 0;
                    bean = new WmsFinaCreRepay();
                    WmsFinaCrePeriodRepay bean1 = new WmsFinaCrePeriodRepay();
                   
                    // 要根据不同提供查询的产品类型决定需要调用哪个方法 2为房贷产品
                    bean = this.setWmsFinaCreRepay(user, wms_cre_credit_head_id, borrowProtocol, wmsCreCreditHead,
                                              wmsFinaCreLoanApp, wmsCreApproServiceProtocol);
                    //保存贷后专员信息
                    Map<String, Object> paraMap = new HashMap<>();
                    paraMap.put("DEPT_CODE_KFB", WmsHelp.DEPT_CODE_KFB);//贷款端--客服部id --放款确认使用的催缴部门人员 
                    Map<String, Object> map = wmsfinacrerepayDao.seachCount(paraMap);//查询符合条件的催缴专员-贷后专员
                    bean.setDunning_id((int) map.get("personnel_id"));//贷后专员id
                    bean.setDunning_name((String)map.get("personnel_name"));//贷后专员姓名
                    bean.setDunning_deptid((int)map.get("personnel_deptId"));//贷后专员部门id
                    bean.setDunning_datetime(new Timestamp(System.currentTimeMillis()));//贷后专员时间
                    beanRet = wmsfinacrerepayDao.save(bean);// 保存贷款还款信息表
                    for (int i = 0; i < borrowProtocol.getBorrow_deadline(); i++) {
                        bean1 = setWmsFinaCrePeriodRepay(user, wms_cre_credit_head_id, borrowProtocol, wmsFinaCreLoanApp,
                                                         wmsCreCreditHead, i, bean);
                        bean1Ret = wmsfinacreperiodrepayDao.save(bean1);// 期还款台帐
                    }
                    if (beanRet != 0 && bean1Ret != 0) {
                        // 房贷流程
                        approveHouseWorkFlowVO = new WmsHouseCreditWorkFlowVO();
                        approveHouseWorkFlowVO.setWms_cre_credit_head_id(String.valueOf(wms_cre_credit_head_id));
                        approveHouseWorkFlowVO.setUserId(String.valueOf(user.getUserId()));
                        // 判断流程标示是否为空
                        if (tmpMap.get("is_workflow") != null)
                        {
                            approveHouseWorkFlowVO.setIs_workflow(tmpMap.get("is_workflow").toString());
                        }
                        if (tmpMap.get("edition_num") != null && "2".equals(tmpMap.get("edition_num").toString()))
                        {// 1为旧流程2为新流程
                         // 数据来源1为pc 2为移动端
                            if (tmpMap.get("version_number") != null && "2".equals(tmpMap.get("version_number").toString()))
                            {// 2016/5/10版本
                                approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.UPHOUSINGLOANPROCESS);
                            }
                            else
                            {// 2016/2/10版本
                                approveHouseWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.PERFECTHOUSINGLOANPROCESS);
                            }
                            approveHouseWorkFlowVO.setDebtkey("11");
                            approveHouseWorkFlowVO.setPass("true");
                            wmsLoanWorkFlowService.publicApproval(approveHouseWorkFlowVO);
                        }
                        else if ("1".equals(queryInfo.getEdition_num()))
                        {
                            houseCreditWorkFlowService.theMortgageLoanWorkFlow(approveHouseWorkFlowVO);
                        }
                    }
                        
                    //修改-获取信息放款数据同步到还款提醒模块
                    beanvo = new CreditMessageToRepayWarnBeanVO();
                    beanvo.setWms_cre_credit_head_id(wms_cre_credit_head_id.toString());
                    // 贷款类型-房贷
                    beanvo.setCre_type("2");
                    wmsCreCreditNotaryWarnService.getCreditMessageToRepayWarn(beanvo, user);
                    //初始化应还款信息表中的数据
                    setWmsFinaCreRealrepayInfo(wms_cre_credit_head_id);
                    //放款成功后将客户信息传递给PTP
                    map = new HashMap<String, Object>();
                    map.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
                    wmscrecreditapproService.giveInfoToPTP(map, user.getUserId());
                }
            }
        }
        
        queryInfo.setResult(resStr);
        return queryInfo;
    }
    
    /**
     * 
     * @Title: searchCombineLoanList
     * @Description: 查询组合贷单据
     * @param paramMap
     * @return 
     * @author: wangyihan
     * @time:2017年1月10日 下午2:03:30
     * history:
     * 1、2017年1月10日 wangyihan 创建方法
     */
    @Override
    public WmsFinaCreLoanAppSearchBeanVO searchCombineLoanList(WmsFinaCreLoanAppSearchBeanVO queryInfo, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(queryInfo.getWms_cre_credit_group_id() != null) {
            paramMap.put("wms_cre_credit_group_id", queryInfo.getWms_cre_credit_group_id());
        }
        if(StringUtils.isNotEmpty(queryInfo.getBill_status())) {
            paramMap.put("bill_status", queryInfo.getBill_status());
        }
        queryInfo.setCombineLoanList(this.wmsfinacreloanappDao.searchCombineLoanList(paramMap));
        return queryInfo;
    }

    /**
     * @Title: getGroupTpye
     * @Description: TODO(提前还款申请判断是否是组合贷)
     * @param queryInfo
     * @param user
     * @return 
     * @author: baisong
     * @time:2017年1月16日 上午10:46:04
     * @see com.zx.emanage.loanfina.service.IWmsFinaCreRepayService#getGroupTpye(com.zx.emanage.loanfina.vo.WmsFinaCreRepaySearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年1月16日 baisong 创建方法
    */
    @Override
    public Map<String, Object> getGroupTpye(WmsFinaCreRepaySearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paraMap=new HashMap<>();
        paraMap.put("wms_cre_credit_group_id", queryInfo.getWms_cre_credit_group_id());
        // (获取组合贷单据情况)
        List<Map<String, Object>> list = wmsCreCreditHeadDao.getGroupType(paraMap);
        if (list != null && list.size() < 2)
        {
            paraMap.put("result", "success");
            return paraMap;
        }
        // 是否是等额本息
        boolean is_all = false;
        int num = 0;
        for (Map<String, Object> param : list)
        {
            // 判断是否是等额本金 payment_contract_type 1等额本金2先息后本
            if (param != null && param.get("payment_contract_type") != null && "1".equals(param.get("payment_contract_type").toString()) && param.get("wms_cre_credit_head_id") != null && queryInfo.getWms_cre_credit_head_id().toString().equals(param.get("wms_cre_credit_head_id").toString()))
            {
                is_all = true;

            }
            // 判断是否是等额本息
            if (param != null && param.get("payment_contract_type") != null && "1".equals(param.get("payment_contract_type").toString()))
            {
                num++;
            }
        }
        if (is_all && num == 1)
        {
            paraMap.put("result", "error");
            return paraMap;
        }
        paraMap.put("result", "success");
        return paraMap;
    }

    /**
     * @Title: houseToBack
     * @Description: (合同退回)
     * @param user
     * @param bean
     * @param hbean
     * @param cre_type
     * @return 
     * @author: baisong
     * @time:2017年2月28日 上午9:50:20
     * @see com.zx.emanage.loanfina.service.IWmsFinaCreRepayService#houseToBack(com.zx.sframe.util.vo.UserBean, com.zx.emanage.loanfina.vo.WmsFinaCreCancelBeanVo, com.zx.emanage.util.gen.entity.WmsCreCreditHead, java.lang.String)
     * history:
     * 1、2017年2月28日 baisong 创建方法
    */
    @Override
    public String houseToBack(UserBean user, WmsFinaCreCancelBeanVo bean, WmsCreCreditHead hbean, String cre_type)
    {
        // 流程
        WmsHouseCreditWorkFlowVO approveWorkFlowVO = new WmsHouseCreditWorkFlowVO();
        approveWorkFlowVO.setWms_cre_credit_head_id(hbean.getWms_cre_credit_head_id().toString());
        approveWorkFlowVO.setUserId(user.getUserId().toString());
        approveWorkFlowVO.setAdvice(bean.getAdvice());
        approveWorkFlowVO.setPass("back");// 单据退回
        approveWorkFlowVO.setDebtkey(bean.getNullify_type());// 标示当前节点
        Map<String, Object> map = wmsLoanWorkFlowService.publicApprovalStatus(approveWorkFlowVO);

        if (map != null)
        {
            if ("QDHT".equals(bean.getNullify_type()) && "success".equals(map.get("result").toString()))
            {
                WmsCreCreditHead wmsCreCreditHead = wmscrecreditheadDao.get(hbean.getWms_cre_credit_head_id());
                // 查询客户姓名
                Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
                customerChangeParamMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
                customerChangeParamMap.put("is_major", "1");
                customerChangeParamMap.put("enable_flag", "1");
                List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
                if (customerChangeList != null && customerChangeList.size() > 0)
                {
                    WmsCreCreditLineCustomerChangeHead wmsCreCreditLineCustomerChangeHead = new WmsCreCreditLineCustomerChangeHead();
                    wmsCreCreditLineCustomerChangeHead.setIs_finish("0");
                    wmsCreCreditLineCustomerChangeHead.setWms_cre_credit_head_id(hbean.getWms_cre_credit_head_id());
                    wmsCreCreditLineCustomerChangeHead.setWms_cre_credit_line_customer_change_head_id(Integer.valueOf(customerChangeList.get(0).get("wms_cre_credit_line_customer_change_head_id").toString()));
                    wmsCreCreditLineCustomerChangeHeadDao.update(wmsCreCreditLineCustomerChangeHead);
                }
                // 调用发送信息的接口出现异常 不会影响正常流程
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

                        sendMap.put("msg_code", "10014");// 极光消息中心消息编码
                        sendMap.put("msg_code_role", "10014");// 极光消息中心消息编码

                        msgMap.put("personnel_id", wmsCreCreditHead.getSalesman_id().toString());
                        msgMap.put("personnel_shortCode", wmsCreCreditHead.getSalesman_shortcode());
                        msgMap.put("personnel_name", wmsCreCreditHead.getSalesman_name());

                        paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                        paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                        paramMap.put("advice", approveWorkFlowVO.getAdvice());// 审核意见
                        paramMap.put("city", wmsCreCreditHead.getCity());
                        Gson gson = new Gson();
                        msgMap.put("paramJson", gson.toJson(paramMap));
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
                        paramMap.put("app_name", WmsHelp.APP_NAME_MIS);// app_name
                        sendMap.put("msg_map", paramMap);// 极光推送和消息中心的消息内容参数
                        sendMap.put("app_name", WmsHelp.APP_NAME_MIS);// app_name
                        // 线程发送消息
                        wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return map.get("result").toString();
        }
        else
        {
            return "error";
        }
    }
}
