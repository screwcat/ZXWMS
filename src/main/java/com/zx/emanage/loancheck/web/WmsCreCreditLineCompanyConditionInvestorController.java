package com.zx.emanage.loancheck.web;

import java.util.List;
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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineCompanyConditionInvestorService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCompanyConditionInvestorSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyConditionInvestor;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineCompanyConditionInvestorController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCompanyConditionInvestorController.class);

    @Autowired
    private IWmsCreCreditLineCompanyConditionInvestorService wmscrecreditlinecompanyconditioninvestorService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditioninvestorwithoutpaginglist.do", method = {
                                                                                                                 RequestMethod.GET,
                                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCompanyConditionInvestorSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecompanyconditioninvestorService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditioninvestorwithpaginglist.do", method = {
                                                                                                              RequestMethod.GET,
                                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCompanyConditionInvestorSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecompanyconditioninvestorService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCompanyConditionInvestorVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditioninvestorinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineCompanyConditionInvestor getInfoByPK(Integer wms_cre_credit_line_company_condition_investor_id)
    {
        return wmscrecreditlinecompanyconditioninvestorService.getInfoByPK(wms_cre_credit_line_company_condition_investor_id);
    }

    /**
     * 通过过去到企业基本信息主表单ID外键，获取相应投资人信息的显示
     * 
     * @param primary key
     * @return WmsCreCreditLineCompanyConditionInvestor
     * @author hancd
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditioninvestorinfobyFk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> searchInfoByFK(Integer wms_cre_credit_line_company_condition_id)
    {
        return wmscrecreditlinecompanyconditioninvestorService.searchInfoByPK(wms_cre_credit_line_company_condition_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditioninvestorsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineCompanyConditionInvestor bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecompanyconditioninvestorService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditioninvestorupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineCompanyConditionInvestor bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecompanyconditioninvestorService.update(bean, user);
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
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditioninvestorbyfkwithoutpaginglist.do", method = {
                                                                                                                     RequestMethod.GET,
                                                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListByFKWithoutPaging(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinecompanyconditioninvestorService.getListByFKWithoutPaging(wms_cre_credit_head_id);
    }
}