package com.zx.emanage.loanreview.web;

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

import com.zx.emanage.loanreview.service.IWmsCreRevWaterRepayLoanService;
import com.zx.emanage.loanreview.vo.WmsCreRevWaterRepayLoanSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterRepayLoan;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevWaterRepayLoanController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevWaterRepayLoanController.class);

    @Autowired
    private IWmsCreRevWaterRepayLoanService wmscrerevwaterrepayloanService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterrepayloanwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterRepayLoanSearchBeanVO queryInfo)
    {
        return wmscrerevwaterrepayloanService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterrepayloanwithpaginglist.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreRevWaterRepayLoanSearchBeanVO queryInfo)
    {
        return wmscrerevwaterrepayloanService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevWaterRepayLoanVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterrepayloaninfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevWaterRepayLoan getInfoByPK(Integer wms_cre_rev_water_repay_loan_id)
    {
        return wmscrerevwaterrepayloanService.getInfoByPK(wms_cre_rev_water_repay_loan_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterrepayloansave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreRevWaterRepayLoan bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevwaterrepayloanService.save(bean, user);
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
    @RequestMapping(value = "/loanreview/wmscrerevwaterrepayloanupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreRevWaterRepayLoan bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevwaterrepayloanService.update(bean, user);
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