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

import com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineCompanyService;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineCompanySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineCompany;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCustomerChangeLineCompanyController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCustomerChangeLineCompanyController.class);

    @Autowired
    private IWmsCreCustomerChangeLineCompanyService wmscrecustomerchangelinecompanyService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecompanywithoutpaginglist.do", method = {
                                                                                                        RequestMethod.GET,
                                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineCompanySearchBeanVO queryInfo)
    {
        return wmscrecustomerchangelinecompanyService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecompanywithpaginglist.do", method = {
                                                                                                     RequestMethod.GET,
                                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCustomerChangeLineCompanySearchBeanVO queryInfo)
    {
        return wmscrecustomerchangelinecompanyService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCustomerChangeLineCompanyVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecompanyinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCustomerChangeLineCompany getInfoByPK(Integer wms_cre_customer_change_line_company_id)
    {
        return wmscrecustomerchangelinecompanyService.getInfoByPK(wms_cre_customer_change_line_company_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecompanysave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCustomerChangeLineCompany bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecustomerchangelinecompanyService.save(bean, user);
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
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelinecompanyupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCustomerChangeLineCompany bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecustomerchangelinecompanyService.update(bean, user);
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