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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineEnteFixedAssetVehicleService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineEnteFixedAssetVehicleSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEnteFixedAssetVehicle;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineEnteFixedAssetVehicleController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineEnteFixedAssetVehicleController.class);

    @Autowired
    private IWmsCreCreditLineEnteFixedAssetVehicleService wmscrecreditlineentefixedassetvehicleService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentefixedassetvehiclewithoutpaginglist.do", method = {
                                                                                                              RequestMethod.GET,
                                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineEnteFixedAssetVehicleSearchBeanVO queryInfo)
    {
        return wmscrecreditlineentefixedassetvehicleService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentefixedassetvehiclewithpaginglist.do", method = {
                                                                                                           RequestMethod.GET,
                                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineEnteFixedAssetVehicleSearchBeanVO queryInfo)
    {
        return wmscrecreditlineentefixedassetvehicleService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineEnteFixedAssetVehicleVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentefixedassetvehicleinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineEnteFixedAssetVehicle getInfoByPK(Integer wms_cre_credit_line_ente_fixed_asset_vehicle_id)
    {
        return wmscrecreditlineentefixedassetvehicleService.getInfoByPK(wms_cre_credit_line_ente_fixed_asset_vehicle_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentefixedassetvehiclesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineEnteFixedAssetVehicle bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlineentefixedassetvehicleService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlineentefixedassetvehicleupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineEnteFixedAssetVehicle bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlineentefixedassetvehicleService.update(bean, user);
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