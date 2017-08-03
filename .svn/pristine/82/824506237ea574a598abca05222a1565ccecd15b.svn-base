package com.zx.emanage.loanreview.web;

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

import com.zx.emanage.loanreview.service.IWmsCreRevWaterPrivPassbookService;
import com.zx.emanage.loanreview.vo.WmsCreRevWaterPrivPassbookSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterPrivPassbook;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevWaterPrivPassbookController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevWaterPrivPassbookController.class);

    @Autowired
    private IWmsCreRevWaterPrivPassbookService wmscrerevwaterprivpassbookService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterprivpassbookwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterPrivPassbookSearchBeanVO queryInfo)
    {
        return wmscrerevwaterprivpassbookService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterprivpassbookwithpaginglist.do", method = { RequestMethod.GET,
                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreRevWaterPrivPassbookSearchBeanVO queryInfo)
    {
        return wmscrerevwaterprivpassbookService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevWaterPrivPassbookVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterprivpassbookinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevWaterPrivPassbook getInfoByPK(Integer wms_cre_rev_water_priv_passbook_id)
    {
        return wmscrerevwaterprivpassbookService.getInfoByPK(wms_cre_rev_water_priv_passbook_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterprivpassbooksave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreRevWaterPrivPassbook bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevwaterprivpassbookService.save(bean, user);
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
    @RequestMapping(value = "/loanreview/wmscrerevwaterprivpassbookupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreRevWaterPrivPassbook bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevwaterprivpassbookService.update(bean, user);
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