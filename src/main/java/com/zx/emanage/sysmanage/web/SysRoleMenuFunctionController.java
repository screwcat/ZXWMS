package com.zx.emanage.sysmanage.web;

import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.sysmanage.service.ISysRoleMenuFunctionService;
import com.zx.emanage.sysmanage.vo.SysRoleMenuFunctionSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysRoleMenuFunction;
import com.zx.emanage.util.gen.vo.SysRoleMenuFunctionVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class SysRoleMenuFunctionController
{
    private static Logger log = LoggerFactory.getLogger(SysRoleMenuFunctionController.class);

    @Autowired
    private ISysRoleMenuFunctionService sysrolemenufunctionService;

    /**
     * Description :get combox list by vo queryInfo
     * 
     * @param queryInfo
     * @return combox list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysrolemenufunctiongetcomboxlist.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<Map<String, Object>> getComboxList(SysRoleMenuFunctionSearchBeanVO queryInfo)
    {
        return sysrolemenufunctionService.getComboxList(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysrolemenufunctionwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                            RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(SysRoleMenuFunctionSearchBeanVO queryInfo)
    {
        return sysrolemenufunctionService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysrolemenufunctionwithpaginglist.do", method = { RequestMethod.GET,
                                                                                         RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(SysRoleMenuFunctionSearchBeanVO queryInfo)
    {
        return sysrolemenufunctionService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysRoleMenuFunctionVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysrolemenufunctioninfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public SysRoleMenuFunctionVO getInfoByPK(Integer id)
    {
        return sysrolemenufunctionService.getInfoByPK(id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysrolemenufunctionsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, SysRoleMenuFunction bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysrolemenufunctionService.save(bean, user);
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
    @RequestMapping(value = "/sysmanage/sysrolemenufunctionupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, SysRoleMenuFunction bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysrolemenufunctionService.update(bean, user);
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
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysrolemenufunctiondelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, SysRoleMenuFunction bean)
    {
        String result = sysrolemenufunctionService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }
}