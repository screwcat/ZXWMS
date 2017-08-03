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

import com.zx.emanage.loanfina.service.IWmsFinaCrePeriodRepayService;
import com.zx.emanage.loanfina.vo.WmsFinaCrePeriodRepaySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaCrePeriodRepayController
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCrePeriodRepayController.class);

    @Autowired
    private IWmsFinaCrePeriodRepayService wmsfinacreperiodrepayService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreperiodrepaywithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsFinaCrePeriodRepaySearchBeanVO queryInfo)
    {
        return wmsfinacreperiodrepayService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreperiodrepaywithpaginglist.do", method = { RequestMethod.GET,
                                                                                          RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsFinaCrePeriodRepaySearchBeanVO queryInfo)
    {
        return wmsfinacreperiodrepayService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsFinaCrePeriodRepayVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreperiodrepayinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsFinaCrePeriodRepay getInfoByPK(Integer wms_fina_cre_period_pay_id)
    {
        return wmsfinacreperiodrepayService.getInfoByPK(wms_fina_cre_period_pay_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreperiodrepaysave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsFinaCrePeriodRepay bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacreperiodrepayService.save(bean, user);
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
    @RequestMapping(value = "/loanfina/wmsfinacreperiodrepayupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsFinaCrePeriodRepay bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacreperiodrepayService.update(bean, user);
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
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineApplicantVO
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/wmsfinacreperiodrepayinfobyfk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsFinaCrePeriodRepay getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmsfinacreperiodrepayService.getInfoByFK(wms_cre_credit_head_id);
    }
    
    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineApplicantVO
     * @author baisong
     */
    @RequestMapping(value = "/loanfina/getinfoforphone.do", method = { RequestMethod.GET ,RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getinfoforphone(Integer wms_cre_credit_head_id)
    {
        return wmsfinacreperiodrepayService.getinfoforphone(wms_cre_credit_head_id);
    }
}