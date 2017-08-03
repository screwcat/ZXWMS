package com.zx.emanage.loanpost.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jodd.typeconverter.Convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.loanpost.persist.WmsPostLoanMigrationDao;
import com.zx.emanage.loanpost.service.IWmsPostLoanMigrationService;
import com.zx.emanage.loanpost.service.IWmsPostLoanWorkFlowService;
import com.zx.emanage.loanpost.vo.WmsPostLoanMigrationSearchBeanVO;
import com.zx.emanage.sysmanage.persist.ISysUserRoleDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsPostLoanMigration;
import com.zx.emanage.workflow.service.IWorkflowService;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowInfoHelp;
import com.zx.emanage.workflow.util.WorkflowSearchInfoHelp;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmspostloanmigrationService")
public class WmsPostLoanMigrationServiceImpl implements IWmsPostLoanMigrationService
{
    private static Logger log = LoggerFactory.getLogger(WmsPostLoanMigrationServiceImpl.class);

    @Autowired
    private WmsPostLoanMigrationDao wmspostloanmigrationDao;

    @Autowired
    private WmsFinaCreRepayDao wmsFinaCreRepayDao;

    @Autowired
    private IWmsPostLoanWorkFlowService wmsPostLoanWorkFlowService;

    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsPostLoanMigrationSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        paramMap.put("enable_flag", "1");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmspostloanmigrationDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsPostLoanMigrationSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmspostloanmigrationDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmspostloanmigrationDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsPostLoanMigration getInfoByPK(Integer wms_post_loan_migration_id)
    {
        return wmspostloanmigrationDao.get(wms_post_loan_migration_id);
    }

    @Override
    @Transactional
    public String save(WmsPostLoanMigration bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmspostloanmigrationDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsPostLoanMigration bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmspostloanmigrationDao.update(bean); // update a record replace
                                                    // columns, nonsupport null
                                                    // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsPostLoanMigration() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsPostLoanMigration> getListByEntity(WmsPostLoanMigration queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsPostLoanMigration> beanList = wmspostloanmigrationDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public Map<String, Object> getZJSQListWithPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 合同编号
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        // 客户姓名
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        // 客户电话
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        // 客户专员
        if (StringUtil.isNotBlank(queryInfo.getCustomer_officer_name()))
        {
            paramMap.put("customer_officer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_officer_name()));
        }
        // 贷款产品
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        // 是否已转件申请
        paramMap.put("migration_status", 0);

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // 需要读取流程显示需要重新申请的数据
        // 查询流程代办任务
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 设置转件流程ID
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.TRANSFERPAPERPROCESS);
        // 设置转件流程节点名称
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.TRANSFERPAPERPROCESS_CXSQ);
        // 设置当前登录申请登录id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        List<Map<String, Object>> list = wmspostloanmigrationDao.searchZJByQueryInfo1(paramMap);
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmspostloanmigrationDao.findCountZJByQueryInfo1(paramMap), list);
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
    public Map<String, Object> getZJSHListWithPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 合同编号
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        // 客户姓名
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        // 客户电话
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        // 客户专员
        if (StringUtil.isNotBlank(queryInfo.getCustomer_officer_name()))
        {
            paramMap.put("customer_officer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_officer_name()));
        }
        // 贷款产品
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        // 是否已转件申请
        paramMap.put("migration_status", 1);

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());

        // 1.通过用户id 查询是否是销售部主管xsglbzg 还是货后部主管dhbmzg
        Map<String, Object> userIds = new HashMap<String, Object>();
        userIds.put("user_id", String.valueOf(user.getUserId()));
        List<String> workflowid = sysUserRoleDao.searchforbeforeortransfer(userIds);
        // 查询条件(流程代办方面)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.TRANSFERPAPERPROCESS);
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        if ("xsglbzg".equals(workflowid.get(0)))
        {// 销售管理部门主管
            // 定义节点
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.TRANSFERPAPERPROCESS_XSSP);
        }
        else if ("dhbmzg".equals(workflowid.get(0)))
        {// 贷后部门主管
            // 定义节点
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.TRANSFERPAPERPROCESS_DHSP);
        }
        else if ("cwbmzg".equals(workflowid.get(0)))
        {
            // 定义节点
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.TRANSFERPAPERPROCESS_CWSP);
        }
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
        List<Map<String, Object>> list = wmspostloanmigrationDao.searchZJByQueryInfo(paramMap);
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmspostloanmigrationDao.findCountZJByQueryInfo(paramMap), list);

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
    public Map<String, Object> getZJQRListWithPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 合同编号
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        // 客户姓名
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        // 客户电话
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        // 客户专员
        if (StringUtil.isNotBlank(queryInfo.getCustomer_officer_name()))
        {
            paramMap.put("customer_officer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_officer_name()));
        }
        // 贷款产品
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        // 是否已转件申请
        paramMap.put("migration_status", 1);

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
        // 查询条件(流程代办方面)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.TRANSFERPAPERPROCESS);
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.TRANSFERPAPERPROCESS_FWSP);
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
        List<Map<String, Object>> list = wmspostloanmigrationDao.searchZJByQueryInfo(paramMap);
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmspostloanmigrationDao.findCountZJByQueryInfo(paramMap), list);

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
    public Map<String, Object> getZJSQListWithOutPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                   UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 合同编号
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        // 客户姓名
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        // 客户电话
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        // 客户专员
        if (StringUtil.isNotBlank(queryInfo.getCustomer_officer_name()))
        {
            paramMap.put("customer_officer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_officer_name()));
        }
        // 贷款产品
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        // 是否已转件申请
        paramMap.put("migration_status", 0);

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 需要读取流程显示需要重新申请的数据
        // 查询流程代办任务
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        // 设置转件流程ID
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.TRANSFERPAPERPROCESS);
        // 设置转件流程节点名称
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.TRANSFERPAPERPROCESS_CXSQ);
        // 设置当前登录申请登录id
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        List<WorkflowInfoHelp> workflowInfoHelps = workflowService.findPackageTodoTasks(workflowSearchInfoHelp);
        List<Integer> idList = new ArrayList<Integer>();
        for (WorkflowInfoHelp workflowInfoHelp : workflowInfoHelps)
        {
            idList.add(Integer.valueOf(workflowInfoHelp.getBusinessKey()));
        }
        paramMap.put("idList", idList);
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());

        paramMap.put("Rows", wmspostloanmigrationDao.getZJSQListWithoutPaging(paramMap));
        return paramMap;
    }

    @Override
    public Map<String, Object> getZJSHListWithOutPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                   UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 合同编号
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        // 客户姓名
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        // 客户电话
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        // 客户专员
        if (StringUtil.isNotBlank(queryInfo.getCustomer_officer_name()))
        {
            paramMap.put("customer_officer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_officer_name()));
        }
        // 贷款产品
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        // 是否已转件申请
        paramMap.put("migration_status", 1);

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());

        // 1.
        Map<String, Object> userIds = new HashMap<String, Object>();
        userIds.put("user_id", String.valueOf(user.getUserId()));
        List<String> workflowid = sysUserRoleDao.searchforbeforeortransfer(userIds);
        // 查询条件(流程代办方面)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.TRANSFERPAPERPROCESS);
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        if ("xsglbzg".equals(workflowid.get(0)))
        {// 销售管理部门主管
            // 定义节点
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.TRANSFERPAPERPROCESS_XSSP);
        }
        else if ("dhbmzg".equals(workflowid.get(0)))
        {// 贷后部门主管
            // 定义节点
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.TRANSFERPAPERPROCESS_DHSP);
        }
        else if ("cwbmzg".equals(workflowid.get(0)))
        {
            // 定义节点
            workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.TRANSFERPAPERPROCESS_CWSP);
        }
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
        paramMap.put("Rows", wmspostloanmigrationDao.getZJListWithoutPaging(paramMap));
        return paramMap;
    }

    @Override
    public Map<String, Object> getZJQRListWithOutPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                   UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 合同编号
        if (StringUtil.isNotBlank(queryInfo.getProtocol_code()))
        {
            paramMap.put("protocol_code", SysTools.getSqlLikeParam(queryInfo.getProtocol_code()));
        }
        // 客户姓名
        if (StringUtil.isNotBlank(queryInfo.getDebtor_name()))
        {
            paramMap.put("debtor_name", SysTools.getSqlLikeParam(queryInfo.getDebtor_name()));
        }
        // 客户电话
        if (StringUtil.isNotBlank(queryInfo.getDebtor_tel()))
        {
            paramMap.put("debtor_tel", SysTools.getSqlLikeParam(queryInfo.getDebtor_tel()));
        }
        // 客户专员
        if (StringUtil.isNotBlank(queryInfo.getCustomer_officer_name()))
        {
            paramMap.put("customer_officer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_officer_name()));
        }
        // 贷款产品
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        // 是否已转件申请
        paramMap.put("migration_status", 1);

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // 查询条件(流程代办方面)
        WorkflowSearchInfoHelp workflowSearchInfoHelp = new WorkflowSearchInfoHelp();
        workflowSearchInfoHelp.setProcessDefinitionKey(WorkflowConstantHelp.TRANSFERPAPERPROCESS);
        workflowSearchInfoHelp.setUserId(String.valueOf(user.getUserId()));
        workflowSearchInfoHelp.setTaskName(WorkflowConstantHelp.TRANSFERPAPERPROCESS_FWSP);
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
        paramMap.put("Rows", wmspostloanmigrationDao.getZJListWithoutPaging(paramMap));
        return paramMap;
    }

    @Override
    public Map<String, Object> getMigrationInfoByMCCHID(String wms_cre_credit_head_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(wms_cre_credit_head_id))
        {
            paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        }
        paramMap.put("Rows", wmspostloanmigrationDao.getMigrationInfoByMCCHID(paramMap));
        return paramMap;
    }

    @Override
    @Transactional
    public String doSQSave(String wms_cre_credit_head_id, String fileArr, UserBean user, String taskId)
    {
        String result = "OK";
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        List<WmsPostLoanMigration> Lattachment = JsonUtil.jsonArrayToList(fileArr, WmsPostLoanMigration.class); // 前台json附件路径数据转换为list
        /*----------------------------------------------WmsPostLoanMigration资料附件表 begin----------------------------------------------*/
        // 附件先更新再添加
        WmsPostLoanMigration mplmDropThisMig = new WmsPostLoanMigration();
        mplmDropThisMig.setWms_cre_credit_head_id(Integer.valueOf(wms_cre_credit_head_id));
        mplmDropThisMig.setEnable_flag("0");// 是否有效
        wmspostloanmigrationDao.updateEnable(mplmDropThisMig);
        // 更新后添加附件
        for (int i = 0; i < Lattachment.size(); i++)
        {
            WmsPostLoanMigration mplm = Lattachment.get(i);
            mplm.setWms_cre_credit_head_id(Integer.valueOf(wms_cre_credit_head_id));
            mplm.setCreate_user_id(user.getUserId()); // 创建人id
            mplm.setCreate_user_name(user.getRealname());// 创建人名字
            mplm.setCreate_timestamp(sysTime);// 创建时间
            mplm.setEnable_flag("1");// 是否有效
            wmspostloanmigrationDao.save(mplm);
        }
        /*----------------------------------------------WmsPostLoanMigration资料附件表 end----------------------------------------------*/
        // 更新还款表
        WmsFinaCreRepay mfcr = new WmsFinaCreRepay();
        mfcr.setWms_cre_credit_head_id(Integer.valueOf(wms_cre_credit_head_id));
        mfcr.setMigration_status("1");// 已提交转件申请
        wmsFinaCreRepayDao.updateByEntity(mfcr);
        // 转件管理申请流程的开启
        wmsPostLoanWorkFlowService.repeatOrBooleanWmsPostLoanWorkFlowTransferPaper(taskId, wms_cre_credit_head_id,
                                                                                   String.valueOf(user.getUserId()));
        return result;
    }

    @Override
    @Transactional
    public String doQRSave(String wms_cre_credit_head_id, UserBean user)
    {
        String result = "OK";
        // 更新还款表
        WmsFinaCreRepay mfcr = new WmsFinaCreRepay();
        mfcr.setWms_cre_credit_head_id(Integer.valueOf(wms_cre_credit_head_id));
        mfcr.setIs_upload("0");
        mfcr.setRepay_status("4");// 还款状态 变为贷账
        wmsFinaCreRepayDao.updateByEntity(mfcr);
        return result;
    }
}
