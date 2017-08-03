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

import com.zx.emanage.loanreview.service.IWmsCreRevWaterPrivSalaryService;
import com.zx.emanage.loanreview.vo.WmsCreRevWaterPrivSalarySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterPrivSalary;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreRevWaterPrivSalaryController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreRevWaterPrivSalaryController.class);

    @Autowired
    private IWmsCreRevWaterPrivSalaryService wmscrerevwaterprivsalaryService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterprivsalarywithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                                  RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterPrivSalarySearchBeanVO queryInfo)
    {
        return wmscrerevwaterprivsalaryService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterprivsalarywithpaginglist.do", method = { RequestMethod.GET,
                                                                                               RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCreRevWaterPrivSalarySearchBeanVO queryInfo)
    {
        return wmscrerevwaterprivsalaryService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevWaterPrivSalaryVO
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterprivsalaryinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public WmsCreRevWaterPrivSalary getInfoByPK(Integer wms_cre_rev_water_priv_salary_id)
    {
        return wmscrerevwaterprivsalaryService.getInfoByPK(wms_cre_rev_water_priv_salary_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/loanreview/wmscrerevwaterprivsalarysave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreRevWaterPrivSalary bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevwaterprivsalaryService.save(bean, user);
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
    @RequestMapping(value = "/loanreview/wmscrerevwaterprivsalaryupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreRevWaterPrivSalary bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmscrerevwaterprivsalaryService.update(bean, user);
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