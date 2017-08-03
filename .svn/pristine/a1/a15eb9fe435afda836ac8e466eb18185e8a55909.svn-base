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

import com.zx.emanage.sysmanage.service.ISysUserMenuFuncService;
import com.zx.emanage.sysmanage.vo.MenuTreeBean;
import com.zx.emanage.sysmanage.vo.SysSelectMeun;
import com.zx.emanage.sysmanage.vo.SysUserMenuFuncSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.SysUserMenuFunc;
import com.zx.emanage.util.gen.entity.SysMenu;
import com.zx.emanage.util.gen.vo.SysUserMenuFuncVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class SysUserMenuFuncController
{
    private static Logger log = LoggerFactory.getLogger(SysUserMenuFuncController.class);

    @Autowired
    private ISysUserMenuFuncService sysusermenufuncService;

    /**
     * Description :get combox list by vo queryInfo
     * 
     * @param queryInfo
     * @return combox list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysusermenufuncgetcomboxlist.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<Map<String, Object>> getComboxList(SysUserMenuFuncSearchBeanVO queryInfo)
    {
        return sysusermenufuncService.getComboxList(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysusermenufuncwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(SysUserMenuFuncSearchBeanVO queryInfo)
    {
        return sysusermenufuncService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysusermenufuncwithpaginglist.do", method = { RequestMethod.GET,
                                                                                     RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(SysUserMenuFuncSearchBeanVO queryInfo)
    {
        return sysusermenufuncService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysUserMenuFuncVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysusermenufuncinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public SysUserMenuFuncVO getInfoByPK(Integer id)
    {
        return sysusermenufuncService.getInfoByPK(id);
    }

    @RequestMapping(value = "/sysmanage/getusermenutree.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<MenuTreeBean> getUserMenuTree(Integer user_id)
    {
        return sysusermenufuncService.getUserMenuTree(user_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysusermenufuncsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, com.zx.emanage.util.gen.entity.SysUserMenuFunc bean,
                         String menuArr, Integer user_id)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysusermenufuncService.save(menuArr, user, user_id);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理-权限管理-权限分配";
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
    @RequestMapping(value = "/sysmanage/sysusermenufuncupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, SysUserMenuFunc bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysusermenufuncService.update(bean, user);
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
    @RequestMapping(value = "/sysmanage/sysusermenufuncdelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, SysUserMenuFunc bean)
    {
        String result = sysusermenufuncService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }

}