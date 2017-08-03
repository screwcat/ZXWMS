package com.zx.emanage.inve.web;

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

import com.zx.emanage.inve.service.IWmsInveCustomerService;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.inve.vo.WmsInveCustomerSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveCustomerController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveCustomerController.class);

    @Autowired
    private IWmsInveCustomerService wmsinvecustomerService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvecustomerwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsInveCustomerSearchBeanVO queryInfo)
    {
        return wmsinvecustomerService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvecustomerwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsInveCustomerSearchBeanVO queryInfo)
    {
        return wmsinvecustomerService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveCustomerVO
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvecustomerinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsInveCustomer getInfoByPK(Integer wms_inve_customer_id)
    {
        return wmsinvecustomerService.getInfoByPK(wms_inve_customer_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/inve/wmsinvecustomersave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsInveCustomer bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvecustomerService.save(bean, user);
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
    @RequestMapping(value = "/inve/wmsinvecustomerupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsInveCustomer bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinvecustomerService.update(bean, user);
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
     * Description :根据客户提供的身份证信息 查询相关客户详细信息
     * 
     * @param primary key
     * @return WmsInveCustomerVO
     * @author wangyishun
     */
    @RequestMapping(value = "/inve/getwmsinvecustomerbyentity.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getWmsInveCustomerByEntity(String id_card)
    {
        return wmsinvecustomerService.getWmsInveCustomerByEntity(id_card);
    }
}