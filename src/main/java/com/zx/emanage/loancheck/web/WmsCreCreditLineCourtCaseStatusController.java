package com.zx.emanage.loancheck.web;

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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineCourtCaseStatusService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCourtCaseStatusSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCourtCaseStatus;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineCourtCaseStatusController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCourtCaseStatusController.class);

    @Autowired
    private IWmsCreCreditLineCourtCaseStatusService wmscrecreditlinecourtcasestatusService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecourtcasestatuswithoutpaginglist.do", method = {
                                                                                                        RequestMethod.GET,
                                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCourtCaseStatusSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecourtcasestatusService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecourtcasestatuswithpaginglist.do", method = {
                                                                                                     RequestMethod.GET,
                                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCourtCaseStatusSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecourtcasestatusService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCourtCaseStatusVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecourtcasestatusinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineCourtCaseStatus getInfoByPK(Integer wms_cre_credit_line_court_case_status_id)
    {
        return wmscrecreditlinecourtcasestatusService.getInfoByPK(wms_cre_credit_line_court_case_status_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecourtcasestatussave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineCourtCaseStatus bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecourtcasestatusService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlinecourtcasestatusupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineCourtCaseStatus bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecourtcasestatusService.update(bean, user);
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
     * 通过外键贷款表主键ID，获取法院网执行案件信息
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecourtcasestatusbyfk.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinecourtcasestatusService.getListByFK(wms_cre_credit_head_id);
    }
}