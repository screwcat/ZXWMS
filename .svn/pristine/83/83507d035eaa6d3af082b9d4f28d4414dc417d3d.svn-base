package com.zx.emanage.loanpost.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.loanpost.service.IWmsPostLoanMigrationService;
import com.zx.emanage.loanpost.service.IWmsPostLoanWorkFlowService;
import com.zx.emanage.loanpost.vo.WmsPostLoanMigrationSearchBeanVO;
import com.zx.emanage.loanpost.vo.WmsPostLoanWorkFlowBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsPostLoanMigration;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsPostLoanMigrationController
{
    private static Logger log = LoggerFactory.getLogger(WmsPostLoanMigrationController.class);

    @Autowired
    private IWmsPostLoanMigrationService wmspostloanmigrationService;

    @Autowired
    private IWmsPostLoanWorkFlowService wmsPostLoanWorkFlowService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsPostLoanMigrationSearchBeanVO queryInfo)
    {
        return wmspostloanmigrationService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationwithpaginglist.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsPostLoanMigrationSearchBeanVO queryInfo)
    {
        return wmspostloanmigrationService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsPostLoanMigrationVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsPostLoanMigration getInfoByPK(Integer wms_post_loan_migration_id)
    {
        return wmspostloanmigrationService.getInfoByPK(wms_post_loan_migration_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsPostLoanMigration bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmspostloanmigrationService.save(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }

    /**
     * Description :update method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsPostLoanMigration bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmspostloanmigrationService.update(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }

    /**
     * Description :获得转件管理的转件申请的分页数据
     * 
     * @param 合同编号、客户姓名、联系电话、客户专员、贷款产品、转件状态
     * @return 分页数据
     * @author ry
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationzjsqwithpaginglistbyqueryinfo.do", method = {
                                                                                                        RequestMethod.GET,
                                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getZJSQListWithPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmspostloanmigrationService.getZJSQListWithPagingByQueryInfo(queryInfo, user);
    }

    /**
     * Description :获得转件管理的转件审核的分页数据
     * 
     * @param 合同编号、客户姓名、联系电话、客户专员、贷款产品、转件状态
     * @return 分页数据
     * @author ry
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationzjshwithpaginglistbyqueryinfo.do", method = {
                                                                                                        RequestMethod.GET,
                                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getZJSHListWithPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmspostloanmigrationService.getZJSHListWithPagingByQueryInfo(queryInfo, user);
    }

    /**
     * Description :获得转件管理的转件确认的分页数据
     * 
     * @param 合同编号、客户姓名、联系电话、客户专员、贷款产品、转件状态
     * @return 分页数据
     * @author ry
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationzjqrwithpaginglistbyqueryinfo.do", method = {
                                                                                                        RequestMethod.GET,
                                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getZJQRListWithPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmspostloanmigrationService.getZJQRListWithPagingByQueryInfo(queryInfo, user);
    }

    /**
     * Description :通过贷款表ID获得还款、合同信息
     * 
     * @param wms_cre_credit_head_id
     * @return 单条数据
     * @author ry
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationinfobymcchid.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getMigrationInfoByMCCHID(String wms_cre_credit_head_id)
    {
        return wmspostloanmigrationService.getMigrationInfoByMCCHID(wms_cre_credit_head_id);
    }

    /**
     * Description :转件申请 提交
     * 
     * @param 上传文件、贷款ID
     * @return "success" or "error" or user defined
     * @author ry
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationsqsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSQSave(HttpServletRequest request, String wms_cre_credit_head_id, String fileArr, String taskId)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmspostloanmigrationService.doSQSave(wms_cre_credit_head_id, fileArr, user, taskId);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("OK".equals(result))
        {
            String msg = "贷后管理-转件管理-转件申请";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * Description :转件确认 提交
     * 
     * @param 贷款ID
     * @return "success" or "error" or user defined
     * @author ry
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationqrsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doQRSave(HttpServletRequest request, String wms_cre_credit_head_id)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmspostloanmigrationService.doQRSave(wms_cre_credit_head_id, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("OK".equals(result))
        {
            String msg = "贷后管理-转件管理-转件确认";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * 实现流程历程查看功能 转件审核查看
     * 
     * @param request
     */
    @RequestMapping(value = "/loanpost/wmspostloanMigrationProcessView.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getMigrationProcessView(Integer wms_cre_credit_head_id)
    {
        return wmsPostLoanWorkFlowService.postLoanApproveProcessTransferPaper(wms_cre_credit_head_id);
    }

    /**
     * 实现开启转件流程
     * 
     * @param request
     */
    @RequestMapping(value = "/loanpost/wmspostloanMigrationStart.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getMigrationStart(HttpServletRequest request, WmsPostLoanWorkFlowBeanVO wmsPostLoanWorkFlowBeanVO)
    {
        String result = "success";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        wmsPostLoanWorkFlowBeanVO.setUser_id(user.getUserId());
        try
        {
            wmsPostLoanWorkFlowService.wmsPostLoanApproveTransferPaper(wmsPostLoanWorkFlowBeanVO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }

    /**
     * Description :转件申请查询页数据导出
     * 
     * @param queryInfo
     * @return map
     * @author ry
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationzjsqwithoutpaginglistbyqueryinfo.do", method = {
                                                                                                           RequestMethod.GET,
                                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getZJSQListWithOutPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                   HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmspostloanmigrationService.getZJSQListWithOutPagingByQueryInfo(queryInfo, user);
    }

    /**
     * Description :转件审核查询页数据导出
     * 
     * @param queryInfo
     * @return map
     * @author ry
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationzjshwithoutpaginglistbyqueryinfo.do", method = {
                                                                                                           RequestMethod.GET,
                                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getZJSHListWithOutPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                   HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmspostloanmigrationService.getZJSHListWithOutPagingByQueryInfo(queryInfo, user);
    }

    /**
     * Description :转件确认查询页数据导出
     * 
     * @param queryInfo
     * @return map
     * @author ry
     */
    @RequestMapping(value = "/loanpost/wmspostloanmigrationzjqrwithoutpaginglistbyqueryinfo.do", method = {
                                                                                                           RequestMethod.GET,
                                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getZJQRListWithOutPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                   HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmspostloanmigrationService.getZJQRListWithOutPagingByQueryInfo(queryInfo, user);
    }
}