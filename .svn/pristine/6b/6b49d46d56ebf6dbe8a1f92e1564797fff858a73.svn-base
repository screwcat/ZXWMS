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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineEnteSuppPurchaService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineEnteSuppPurchaSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineEnteSuppPurcha;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineEnteSuppPurchaController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineEnteSuppPurchaController.class);

    @Autowired
    private IWmsCreCreditLineEnteSuppPurchaService wmscrecreditlineentesupppurchaService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentesupppurchawithoutpaginglist.do", method = {
                                                                                                       RequestMethod.GET,
                                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineEnteSuppPurchaSearchBeanVO queryInfo)
    {
        return wmscrecreditlineentesupppurchaService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentesupppurchawithpaginglist.do", method = { RequestMethod.GET,
                                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineEnteSuppPurchaSearchBeanVO queryInfo)
    {
        return wmscrecreditlineentesupppurchaService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineEnteSuppPurchaVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentesupppurchainfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineEnteSuppPurcha getInfoByPK(Integer wms_cre_credit_line_ente_fixed_asset_equip_id)
    {
        return wmscrecreditlineentesupppurchaService.getInfoByPK(wms_cre_credit_line_ente_fixed_asset_equip_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineentesupppurchasave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineEnteSuppPurcha bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlineentesupppurchaService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlineentesupppurchaupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineEnteSuppPurcha bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlineentesupppurchaService.update(bean, user);
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