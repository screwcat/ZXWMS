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

import com.zx.emanage.loanfina.service.IWmsFinaCreLoanAppAttService;
import com.zx.emanage.loanfina.vo.WmsFinaCreLoanAppAttSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCreLoanAppAtt;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsFinaCreLoanAppAttController
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCreLoanAppAttController.class);

    @Autowired
    private IWmsFinaCreLoanAppAttService wmsfinacreloanappattService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreloanappattwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsFinaCreLoanAppAttSearchBeanVO queryInfo,
                                                    String wms_fina_cre_loan_app_id)
    {
        return wmsfinacreloanappattService.getListWithoutPaging(queryInfo, wms_fina_cre_loan_app_id);
    }
    
    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreloanappattwithpaginglist.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsFinaCreLoanAppAttSearchBeanVO queryInfo)
    {
        return wmsfinacreloanappattService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsFinaCreLoanAppVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreloanappattinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsFinaCreLoanAppAtt getInfoByPK(Integer wms_fina_cre_loan_app)
    {
        return wmsfinacreloanappattService.getInfoByPK(wms_fina_cre_loan_app);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanfina/wmsfinacreloanappattsave.do", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsFinaCreLoanAppAtt bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacreloanappattService.save(bean, user);
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
    @RequestMapping(value = "/loanfina/wmsfinacreloanappattupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsFinaCreLoanAppAtt bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsfinacreloanappattService.update(bean, user);
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