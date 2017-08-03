package com.zx.emanage.cremanage.web;

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

import com.zx.emanage.cremanage.service.IWmsCreCreditLineCustomerDataAttachmentService;
import com.zx.emanage.cremanage.vo.WmsCreCreditLineCustomerDataAttachmentSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCreditLineCustomerDataAttachment;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerDataAttachmentVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineCustomerDataAttachmentController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCustomerDataAttachmentController.class);

    @Autowired
    private IWmsCreCreditLineCustomerDataAttachmentService wmscrecreditlinecustomerdataattachmentService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerdataattachmentwithoutpaginglist.do", method = {
                                                                                                               RequestMethod.GET,
                                                                                                               RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCustomerDataAttachmentSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecustomerdataattachmentService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by ids
     * 
     * @param ids
     * @return record list
     * @author
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerdataattachmentwithoutpaginglistbyids.do", method = {
                                                                                                                    RequestMethod.GET,
                                                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingByids(String wms_cre_credit_line_customer_data_attachment_ids)
    {
        return wmscrecreditlinecustomerdataattachmentService.getListWithoutPagingByIds(wms_cre_credit_line_customer_data_attachment_ids);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerdataattachmentwithpaginglist.do", method = {
                                                                                                            RequestMethod.GET,
                                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCustomerDataAttachmentSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecustomerdataattachmentService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerDataAttachmentVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerdataattachmentinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineCustomerDataAttachmentVO getInfoByPK(Integer wms_cre_credit_line_customer_data_attachment_id)
    {
        return wmscrecreditlinecustomerdataattachmentService.getInfoByPK(wms_cre_credit_line_customer_data_attachment_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerdataattachmentsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineCustomerDataAttachment bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecustomerdataattachmentService.save(bean, user);
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
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerdataattachmentupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineCustomerDataAttachment bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecustomerdataattachmentService.update(bean, user);
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
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecreditlinecustomerdataattachmentdelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, WmsCreCreditLineCustomerDataAttachment bean)
    {
        String result = wmscrecreditlinecustomerdataattachmentService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }
}