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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineReceivablePayableService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineReceivablePayableSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineReceivablePayable;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineReceivablePayableController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineReceivablePayableController.class);

    @Autowired
    private IWmsCreCreditLineReceivablePayableService wmscrecreditlinereceivablepayableService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinereceivablepayablewithoutpaginglist.do", method = {
                                                                                                          RequestMethod.GET,
                                                                                                          RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineReceivablePayableSearchBeanVO queryInfo)
    {
        return wmscrecreditlinereceivablepayableService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinereceivablepayablewithpaginglist.do", method = {
                                                                                                       RequestMethod.GET,
                                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineReceivablePayableSearchBeanVO queryInfo)
    {
        return wmscrecreditlinereceivablepayableService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineReceivablePayableVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinereceivablepayableinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineReceivablePayable getInfoByPK(Integer wms_cre_credit_line_receivable_payable_id)
    {
        return wmscrecreditlinereceivablepayableService.getInfoByPK(wms_cre_credit_line_receivable_payable_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinereceivablepayablesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineReceivablePayable bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinereceivablepayableService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlinereceivablepayableupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineReceivablePayable bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinereceivablepayableService.update(bean, user);
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
}