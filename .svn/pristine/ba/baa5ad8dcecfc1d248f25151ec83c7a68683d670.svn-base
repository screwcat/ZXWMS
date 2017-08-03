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

import com.zx.emanage.cusmanage.service.IWmsCusCustomerLineWorkinfoService;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerLineWorkinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCusCustomerLineWorkinfo;
import com.zx.emanage.util.gen.vo.WmsCusCustomerLineWorkinfoVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCusCustomerLineWorkinfoController
{
    private static Logger log = LoggerFactory.getLogger(WmsCusCustomerLineWorkinfoController.class);

    @Autowired
    private IWmsCusCustomerLineWorkinfoService wmscuscustomerlineworkinfoService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlineworkinfowithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo)
    {
        return wmscuscustomerlineworkinfoService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlineworkinfowithpaginglist.do", method = { RequestMethod.GET,
                                                                                                RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCusCustomerLineWorkinfoSearchBeanVO queryInfo)
    {
        return wmscuscustomerlineworkinfoService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerLineWorkinfoVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlineworkinfoinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCusCustomerLineWorkinfoVO getInfoByPK(Integer wms_cus_customer_line_workinfo_id)
    {
        return wmscuscustomerlineworkinfoService.getInfoByPK(wms_cus_customer_line_workinfo_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerlineworkinfosave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCusCustomerLineWorkinfo bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscuscustomerlineworkinfoService.save(bean, user);
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
    @RequestMapping(value = "/cusmanage/wmscuscustomerlineworkinfoupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCusCustomerLineWorkinfo bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscuscustomerlineworkinfoService.update(bean, user);
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
    @RequestMapping(value = "/cusmanage/wmscuscustomerlineworkinfodelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, WmsCusCustomerLineWorkinfo bean)
    {
        String result = wmscuscustomerlineworkinfoService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }
}