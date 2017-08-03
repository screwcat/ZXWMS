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

import com.zx.emanage.loanreview.service.IWmsCreRevWaterRepayLoanLineService;
import com.zx.emanage.loanreview.vo.WmsCreRevWaterRepayLoanLineSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterRepayLoanLine;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevWaterRepayLoanLineController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevWaterRepayLoanLineController.class);

    @Autowired
    private IWmsCreRevWaterRepayLoanLineService wmscrerevwaterrepayloanlineService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterrepayloanlinewithoutpaginglist.do", method = {
                                                                                                     RequestMethod.GET,
                                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterRepayLoanLineSearchBeanVO queryInfo)
    {
        return wmscrerevwaterrepayloanlineService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterrepayloanlinewithpaginglist.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreRevWaterRepayLoanLineSearchBeanVO queryInfo)
    {
        return wmscrerevwaterrepayloanlineService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevWaterRepayLoanLineVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterrepayloanlineinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevWaterRepayLoanLine getInfoByPK(Integer wms_cre_rev_water_repay_loan_line_id)
    {
        return wmscrerevwaterrepayloanlineService.getInfoByPK(wms_cre_rev_water_repay_loan_line_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterrepayloanlinesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreRevWaterRepayLoanLine bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevwaterrepayloanlineService.save(bean, user);
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
    @RequestMapping(value = "/loanreview/wmscrerevwaterrepayloanlineupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreRevWaterRepayLoanLine bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevwaterrepayloanlineService.update(bean, user);
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