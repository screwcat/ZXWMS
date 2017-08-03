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

import com.zx.emanage.loancheck.service.IWmsCreCreditLineInventoryGoodMaterialService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineInventoryGoodMaterialSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineInventoryGoodMaterial;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreCreditLineInventoryGoodMaterialController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineInventoryGoodMaterialController.class);

    @Autowired
    private IWmsCreCreditLineInventoryGoodMaterialService wmscrecreditlineinventorygoodmaterialService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineinventorygoodmaterialwithoutpaginglist.do", method = {
                                                                                                              RequestMethod.GET,
                                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineInventoryGoodMaterialSearchBeanVO queryInfo)
    {
        return wmscrecreditlineinventorygoodmaterialService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineinventorygoodmaterialwithpaginglist.do", method = {
                                                                                                           RequestMethod.GET,
                                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreCreditLineInventoryGoodMaterialSearchBeanVO queryInfo)
    {
        return wmscrecreditlineinventorygoodmaterialService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineInventoryGoodMaterialVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineinventorygoodmaterialinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreCreditLineInventoryGoodMaterial getInfoByPK(Integer wms_cre_credit_line_inventory_goods_materials_id)
    {
        return wmscrecreditlineinventorygoodmaterialService.getInfoByPK(wms_cre_credit_line_inventory_goods_materials_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loancheck/wmscrecreditlineinventorygoodmaterialsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditLineInventoryGoodMaterial bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlineinventorygoodmaterialService.save(bean, user);
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
    @RequestMapping(value = "/loancheck/wmscrecreditlineinventorygoodmaterialupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditLineInventoryGoodMaterial bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrecreditlineinventorygoodmaterialService.update(bean, user);
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