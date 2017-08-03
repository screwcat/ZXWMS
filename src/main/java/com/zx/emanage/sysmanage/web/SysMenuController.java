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

import com.zx.emanage.sysmanage.service.ISysMenuService;
import com.zx.emanage.sysmanage.vo.SysMenuSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysMenu;
import com.zx.emanage.util.gen.vo.SysMenuVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class SysMenuController
{
    private static Logger log = LoggerFactory.getLogger(SysMenuController.class);

    @Autowired
    private ISysMenuService sysmenuService;

    /**
     * Description :get combox list by vo queryInfo
     * 
     * @param queryInfo
     * @return combox list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysmenugetcomboxlist.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<Map<String, Object>> getComboxList(SysMenuSearchBeanVO queryInfo)
    {
        return sysmenuService.getComboxList(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysmenuwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(SysMenuSearchBeanVO queryInfo)
    {
        return sysmenuService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysmenuwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(SysMenuSearchBeanVO queryInfo)
    {
        return sysmenuService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysMenuVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysmenuinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public SysMenuVO getInfoByPK(Integer id)
    {
        return sysmenuService.getInfoByPK(id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysmenusave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, SysMenu bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysmenuService.save(bean, user);
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
    @RequestMapping(value = "/sysmanage/sysmenuupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, SysMenu bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysmenuService.update(bean, user);
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
    @RequestMapping(value = "/sysmanage/sysmenudelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, SysMenu bean)
    {
        String result = sysmenuService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }
}