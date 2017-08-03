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

import com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineWorkinfoService;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineWorkinfoSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineWorkinfo;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineWorkinfoVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCustomerChangeLineWorkinfoController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCustomerChangeLineWorkinfoController.class);

    @Autowired
    private IWmsCreCustomerChangeLineWorkinfoService wmscrecustomerchangelineworkinfoService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelineworkinfowithoutpaginglist.do", method = {
                                                                                                         RequestMethod.GET,
                                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo)
    {
        return wmscrecustomerchangelineworkinfoService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelineworkinfowithpaginglist.do", method = {
                                                                                                      RequestMethod.GET,
                                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCustomerChangeLineWorkinfoSearchBeanVO queryInfo)
    {
        return wmscrecustomerchangelineworkinfoService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCustomerChangeLineWorkinfoVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelineworkinfoinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCustomerChangeLineWorkinfoVO getInfoByPK(Integer wms_cre_customer_change_line_workinfo_id)
    {
        return wmscrecustomerchangelineworkinfoService.getInfoByPK(wms_cre_customer_change_line_workinfo_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelineworkinfosave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCustomerChangeLineWorkinfo bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecustomerchangelineworkinfoService.save(bean, user);
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
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelineworkinfoupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCustomerChangeLineWorkinfo bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecustomerchangelineworkinfoService.update(bean, user);
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
    @RequestMapping(value = "/cremanage/wmscrecustomerchangelineworkinfodelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, WmsCreCustomerChangeLineWorkinfo bean)
    {
        String result = wmscrecustomerchangelineworkinfoService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }
}