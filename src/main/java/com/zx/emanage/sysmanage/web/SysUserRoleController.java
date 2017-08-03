package com.zx.emanage.sysmanage.web;

import java.util.List;
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

import com.zx.emanage.sysmanage.service.ISysUserRoleService;
import com.zx.emanage.sysmanage.vo.SysSelectMeun;
import com.zx.emanage.sysmanage.vo.SysUserRoleSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.SysUserRole;
import com.zx.emanage.util.gen.entity.SysMenu;
import com.zx.emanage.util.gen.vo.SysUserRoleVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class SysUserRoleController
{
    private static Logger log = LoggerFactory.getLogger(SysUserRoleController.class);

    @Autowired
    private ISysUserRoleService sysuserroleService;

    /**
     * Description :get combox list by vo queryInfo
     * 
     * @param queryInfo
     * @return combox list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysuserrolegetcomboxlist.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<Map<String, Object>> getComboxList(SysUserRoleSearchBeanVO queryInfo)
    {
        return sysuserroleService.getComboxList(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysuserrolewithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getListWithoutPaging(SysUserRoleSearchBeanVO queryInfo)
    {
        return sysuserroleService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysuserrolewithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(SysUserRoleSearchBeanVO queryInfo)
    {
        return sysuserroleService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysUserRoleVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysuserroleinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public SysUserRoleVO getInfoByPK(Integer id)
    {
        return sysuserroleService.getInfoByPK(id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysuserrolesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, com.zx.emanage.util.gen.entity.SysUserRole bean, String roleArr,
                         Integer user_id)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysuserroleService.save(roleArr, user, user_id);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }

        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理—权限管理-角色分配";
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
    @RequestMapping(value = "/sysmanage/sysuserroleupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, SysUserRole bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysuserroleService.update(bean, user);
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
    @RequestMapping(value = "/sysmanage/sysuserroledelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, SysUserRole bean)
    {
        String result = sysuserroleService.delete(bean);
        /*
         * // record log if("success".equals(result)){ String msg =
         * "log content"; SysTools.saveLog(request, msg); // record log method }
         */
        return result;
    }

    /**
     * 用于首页 跳转页面 如 点击客户管理跳转到 新增客户页面等
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @RequestMapping(value = "/sysmanage/getusermenuforpage.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public SysMenu getUserMenu(HttpServletRequest request, SysSelectMeun meun)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        meun.setUser_id(user.getUserId());
        return sysuserroleService.getUserMenu(meun);
    }
}