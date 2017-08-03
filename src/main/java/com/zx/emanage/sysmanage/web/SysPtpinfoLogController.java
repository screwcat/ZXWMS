package com.zx.emanage.sysmanage.web;

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

import com.zx.emanage.sysmanage.service.ISysPtpinfoLogService;
import com.zx.emanage.util.gen.entity.SysPtpinfoLog;
import com.zx.emanage.sysmanage.vo.SysPtpinfoLogSearchBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class SysPtpinfoLogController
{
    private static Logger log = LoggerFactory.getLogger(SysPtpinfoLogController.class);

    @Autowired
    private ISysPtpinfoLogService sysptpinfologService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysptpinfologwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                      RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(SysPtpinfoLogSearchBeanVO queryInfo)
    {
        return sysptpinfologService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysptpinfologwithpaginglist.do", method = { RequestMethod.GET,
                                                                                   RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(SysPtpinfoLogSearchBeanVO queryInfo)
    {
        return sysptpinfologService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysPtpinfoLogVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysptpinfologinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public SysPtpinfoLog getInfoByPK(Integer sys_ptpinfo_log_id)
    {
        return sysptpinfologService.getInfoByPK(sys_ptpinfo_log_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysptpinfologsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, SysPtpinfoLog bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysptpinfologService.save(bean, user);
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
    @RequestMapping(value = "/sysmanage/sysptpinfologupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, SysPtpinfoLog bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysptpinfologService.update(bean, user);
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