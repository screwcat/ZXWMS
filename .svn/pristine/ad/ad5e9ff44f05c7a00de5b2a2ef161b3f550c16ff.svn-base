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

import com.zx.emanage.loancheck.service.IWmsCreCreditLinePersonCreditService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLinePersonCreditSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLinePersonCredit;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLinePersonCreditController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLinePersonCreditController.class);

    @Autowired
    private IWmsCreCreditLinePersonCreditService wmscrecreditlinepersoncreditService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinepersoncreditwithoutpaginglist.do", method = {
                                                                                                     RequestMethod.GET,
                                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLinePersonCreditSearchBeanVO queryInfo)
    {
        return wmscrecreditlinepersoncreditService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinepersoncreditwithpaginglist.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLinePersonCreditSearchBeanVO queryInfo)
    {
        return wmscrecreditlinepersoncreditService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLinePersonCreditVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinepersoncreditinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLinePersonCredit getInfoByPK(Integer wms_cre_credit_line_person_credit_id)
    {
        return wmscrecreditlinepersoncreditService.getInfoByPK(wms_cre_credit_line_person_credit_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinepersoncreditsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLinePersonCredit bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinepersoncreditService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlinepersoncreditupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLinePersonCredit bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinepersoncreditService.update(bean, user);
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