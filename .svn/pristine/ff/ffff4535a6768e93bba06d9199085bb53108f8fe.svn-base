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

import com.zx.emanage.cusmanage.service.IWmsCusCustomerChildService;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerChildSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCusCustomerChild;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCusCustomerChildController
{
    private static Logger log = LoggerFactory.getLogger(WmsCusCustomerChildController.class);

    @Autowired
    private IWmsCusCustomerChildService wmscuscustomerchildService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerchildwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerChildSearchBeanVO queryInfo)
    {
        return wmscuscustomerchildService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerchildwithpaginglist.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCusCustomerChildSearchBeanVO queryInfo)
    {
        return wmscuscustomerchildService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerChildVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerchildinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCusCustomerChild getInfoByPK(Integer wms_cus_customer_child_id)
    {
        return wmscuscustomerchildService.getInfoByPK(wms_cus_customer_child_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerchildsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCusCustomerChild bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscuscustomerchildService.save(bean, user);
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
    @RequestMapping(value = "/cusmanage/wmscuscustomerchildupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCusCustomerChild bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscuscustomerchildService.update(bean, user);
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