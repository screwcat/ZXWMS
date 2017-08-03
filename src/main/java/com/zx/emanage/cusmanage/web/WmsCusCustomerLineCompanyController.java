package com.zx.emanage.cusmanage.web;

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

import com.zx.emanage.cusmanage.service.IWmsCusCustomerLineCompanyService;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineCompanySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCusCustomerLineCompanyController
{
    private static Logger log = LoggerFactory.getLogger(WmsCusCustomerLineCompanyController.class);

    @Autowired
    private IWmsCusCustomerLineCompanyService wmscuscustomerlinecompanyService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlinecompanywithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerLineCompanySearchBeanVO queryInfo)
    {
        return wmscuscustomerlinecompanyService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlinecompanywithpaginglist.do", method = { RequestMethod.GET,
                                                                                               RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCusCustomerLineCompanySearchBeanVO queryInfo)
    {
        return wmscuscustomerlinecompanyService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerLineCompanyVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlinecompanyinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCusCustomerLineCompany getInfoByPK(Integer wms_cus_customer_line_company_id)
    {
        return wmscuscustomerlinecompanyService.getInfoByPK(wms_cus_customer_line_company_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlinecompanysave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCusCustomerLineCompany bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscuscustomerlinecompanyService.save(bean, user);
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
    @RequestMapping(value = "/cusmanage/wmscuscustomerlinecompanyupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCusCustomerLineCompany bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscuscustomerlinecompanyService.update(bean, user);
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