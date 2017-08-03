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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineCustomerVehicleService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineCustomerVehicleSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerVehicle;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineCustomerVehicleController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineCustomerVehicleController.class);

    @Autowired
    private IWmsCreCreditLineCustomerVehicleService wmscrecreditlinecustomervehicleService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomervehiclewithoutpaginglist.do", method = {
                                                                                                        RequestMethod.GET,
                                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineCustomerVehicleSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecustomervehicleService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomervehiclewithpaginglist.do", method = {
                                                                                                     RequestMethod.GET,
                                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineCustomerVehicleSearchBeanVO queryInfo)
    {
        return wmscrecreditlinecustomervehicleService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineCustomerVehicleVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomervehicleinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineCustomerVehicle getInfoByPK(Integer wms_cre_credit_line_customer_vehicle_id)
    {
        return wmscrecreditlinecustomervehicleService.getInfoByPK(wms_cre_credit_line_customer_vehicle_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomervehiclesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineCustomerVehicle bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecustomervehicleService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomervehicleupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineCustomerVehicle bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlinecustomervehicleService.update(bean, user);
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
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlinecustomervehiclebyfkwithoutpaginglist.do", method = {
                                                                                                            RequestMethod.GET,
                                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListByFKWithoutPaging(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlinecustomervehicleService.getListByFKWithoutPaging(wms_cre_credit_head_id);
    }
}