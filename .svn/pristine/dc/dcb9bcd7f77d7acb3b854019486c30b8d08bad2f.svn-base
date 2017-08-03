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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineCallListService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCallListSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCallList;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineCallListController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCallListController.class);

    @Autowired
    private IWmsCreCreditLineCallListService wmscrecreditlinecalllistService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecalllistwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCallListSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecalllistService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecalllistwithpaginglist.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCallListSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecalllistService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCallListVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecalllistinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineCallList getInfoByPK(Integer wms_cre_credit_line_call_list_id)
    {
        return wmscrecreditlinecalllistService.getInfoByPK(wms_cre_credit_line_call_list_id);
    }

    /**
     * 通过贷款主ID 查询所有的通话信息记录
     * 
     * @param primary key
     * @return
     * @author hancd
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecalllistinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinecalllistService.searchInfoByFK(wms_cre_credit_head_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecalllistsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineCallList bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecalllistService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlinecalllistupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineCallList bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecalllistService.update(bean, user);
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