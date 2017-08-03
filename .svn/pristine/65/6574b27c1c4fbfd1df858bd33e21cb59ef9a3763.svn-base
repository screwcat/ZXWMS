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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineEnteFixedAssetHouseService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineEnteFixedAssetHouseSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEnteFixedAssetHouse;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineEnteFixedAssetHouseController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineEnteFixedAssetHouseController.class);

    @Autowired
    private IWmsCreCreditLineEnteFixedAssetHouseService wmscrecreditlineentefixedassethouseService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentefixedassethousewithoutpaginglist.do", method = {
                                                                                                            RequestMethod.GET,
                                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineEnteFixedAssetHouseSearchBeanVO queryInfo)
    {
        return wmscrecreditlineentefixedassethouseService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentefixedassethousewithpaginglist.do", method = {
                                                                                                         RequestMethod.GET,
                                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineEnteFixedAssetHouseSearchBeanVO queryInfo)
    {
        return wmscrecreditlineentefixedassethouseService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineEnteFixedAssetHouseVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentefixedassethouseinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineEnteFixedAssetHouse getInfoByPK(Integer wms_cre_credit_line_ente_fixed_asset_house_id)
    {
        return wmscrecreditlineentefixedassethouseService.getInfoByPK(wms_cre_credit_line_ente_fixed_asset_house_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentefixedassethousesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineEnteFixedAssetHouse bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlineentefixedassethouseService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlineentefixedassethouseupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineEnteFixedAssetHouse bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlineentefixedassethouseService.update(bean, user);
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