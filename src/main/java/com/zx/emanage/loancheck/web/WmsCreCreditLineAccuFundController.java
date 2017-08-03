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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineAccuFundService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineAccuFundSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineAccuFund;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineAccuFundController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineAccuFundController.class);

    @Autowired
    private IWmsCreCreditLineAccuFundService wmscrecreditlineaccufundService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineaccufundwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineAccuFundSearchBeanVO queryInfo)
    {
        return wmscrecreditlineaccufundService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineaccufundwithpaginglist.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineAccuFundSearchBeanVO queryInfo)
    {
        return wmscrecreditlineaccufundService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineAccuFundVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineaccufundinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineAccuFund getInfoByPK(Integer wms_cre_credit_line_accu_fund_id)
    {
        return wmscrecreditlineaccufundService.getInfoByPK(wms_cre_credit_line_accu_fund_id);
    }

    /**
     * 通过主表单ID，获取公积金详细显示
     * 
     * @param FK
     * @author hancd
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineaccufundinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> searchInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlineaccufundService.searchInfoByFK(wms_cre_credit_head_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineaccufundsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineAccuFund bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlineaccufundService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlineaccufundupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineAccuFund bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlineaccufundService.update(bean, user);
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