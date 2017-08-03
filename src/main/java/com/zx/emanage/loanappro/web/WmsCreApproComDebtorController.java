package com.zx.emanage.loanappro.web;

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

import com.zx.emanage.loanappro.service.IWmsCreApproComDebtorService;
import com.zx.emanage.loanappro.vo.WmsCreApproComDebtorSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreApproComDebtor;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreApproComDebtorController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreApproComDebtorController.class);

    @Autowired
    private IWmsCreApproComDebtorService wmscreapprocomdebtorService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmscreapprocomdebtorwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreApproComDebtorSearchBeanVO queryInfo)
    {
        return wmscreapprocomdebtorService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmscreapprocomdebtorwithpaginglist.do", method = { RequestMethod.GET,
                                                                                          RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreApproComDebtorSearchBeanVO queryInfo)
    {
        return wmscreapprocomdebtorService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreApproComDebtorVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmscreapprocomdebtorinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreApproComDebtor getInfoByPK(Integer wms_cre_appro_com_debtor_id)
    {
        return wmscreapprocomdebtorService.getInfoByPK(wms_cre_appro_com_debtor_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/wmscreapprocomdebtorsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreApproComDebtor bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreapprocomdebtorService.save(bean, user);
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
    @RequestMapping(value = "/sysmanage/wmscreapprocomdebtorupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreApproComDebtor bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscreapprocomdebtorService.update(bean, user);
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