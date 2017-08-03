package com.zx.emanage.loanfina.web;

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

import com.zx.emanage.loanfina.service.IWmsFinaCrePreRepayService;
import com.zx.emanage.loanfina.vo.WmsFinaCrePreRepaySearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsFinaCrePreRepay;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaCrePreRepayController
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCrePreRepayController.class);

    @Autowired
    private IWmsFinaCrePreRepayService wmsfinacreprerepayService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreprerepaywithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                          RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsFinaCrePreRepaySearchBeanVO queryInfo)
    {
        return wmsfinacreprerepayService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreprerepaywithpaginglist.do", method = { RequestMethod.GET,
                                                                                       RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsFinaCrePreRepaySearchBeanVO queryInfo)
    {
        return wmsfinacreprerepayService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsFinaCrePreRepayVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreprerepayinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsFinaCrePreRepay getInfoByPK(Integer wms_fina_cre_pre_repay_id)
    {
        return wmsfinacreprerepayService.getInfoByPK(wms_fina_cre_pre_repay_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    @RequestMapping(value = "/loanfina/wmsfinacreprerepaysave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsFinaCrePreRepay bean, String taskId)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacreprerepayService.save(bean, user, taskId);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "贷后管理-提前还款申请-提前还款申请";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * Description :update method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreprerepayupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsFinaCrePreRepay bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacreprerepayService.update(bean, user);
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