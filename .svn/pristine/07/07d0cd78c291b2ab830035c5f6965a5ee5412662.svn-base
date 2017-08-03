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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineCompanyConditionService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCompanyConditionSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyCondition;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineCompanyConditionController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCompanyConditionController.class);

    @Autowired
    private IWmsCreCreditLineCompanyConditionService wmscrecreditlinecompanyconditionService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditionwithoutpaginglist.do", method = {
                                                                                                         RequestMethod.GET,
                                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCompanyConditionSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecompanyconditionService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditionwithpaginglist.do", method = {
                                                                                                      RequestMethod.GET,
                                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCompanyConditionSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecompanyconditionService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCompanyConditionVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditioninfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineCompanyCondition getInfoByPK(Integer wms_cre_credit_line_company_condition_id)
    {
        return wmscrecreditlinecompanyconditionService.getInfoByPK(wms_cre_credit_line_company_condition_id);
    }

    /**
     * 实现对信息审批查询，企业基本信息的显示
     * 
     * @param primary key
     * @return WmsCreCreditLineCompanyCondition
     * @author hancd
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinehouserecordinfobyfkView.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<WmsCreCreditLineCompanyCondition> searchInfoByFKVIEW(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinecompanyconditionService.searchInfoByFKVIEW(wms_cre_credit_head_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditionsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineCompanyCondition bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecompanyconditionService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditionupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineCompanyCondition bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecompanyconditionService.update(bean, user);
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
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineApplicantVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecompanyconditioninfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineCompanyCondition getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinecompanyconditionService.getInfoByFK(wms_cre_credit_head_id);
    }
}