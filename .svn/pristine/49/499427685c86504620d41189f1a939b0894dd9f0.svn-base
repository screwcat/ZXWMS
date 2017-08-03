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

import com.zx.emanage.sysmanage.service.ISysSpecialUserService;
import com.zx.emanage.sysmanage.service.ISysUserService;
import com.zx.emanage.sysmanage.vo.SysUserSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.SysUser;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.vo.SysUserVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class SysUserController
{
    private static Logger log = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private ISysUserService sysuserService;

    @Autowired
    private ISysSpecialUserService iSysSpecialUserService;
    
    /**
     * Description :get combox list by vo queryInfo
     * 
     * @param queryInfo
     * @return combox list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysusergetcomboxlist.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<Map<String, Object>> getComboxList(SysUserSearchBeanVO queryInfo)
    {
        return sysuserService.getComboxList(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysuserwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(SysUserSearchBeanVO queryInfo)
    {
        return sysuserService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysuserwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(SysUserSearchBeanVO queryInfo)
    {
        return sysuserService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysUserVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysuserinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public com.zx.emanage.util.gen.entity.SysUser getInfoByPK(Integer id)
    {
        return sysuserService.getInfoByPK(id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysusersave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, com.zx.emanage.util.gen.entity.SysUser bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysuserService.save(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理-用户管理-新增";
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
    @RequestMapping(value = "/sysmanage/sysuserupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, com.zx.emanage.util.gen.entity.SysUser bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysuserService.save(bean, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理-用户管理-修改";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysuserdelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, Integer id)
    {
        String result = sysuserService.delete(id);
        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理-用户管理-删除";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }
    
    /**
     * 判断当前用户是否包含特批人角色
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/sysmanage/isSpecialUser.do", method = { RequestMethod.POST })
    @ResponseBody
    public boolean isSpecialUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return SysTools.isSpecialUser(iSysSpecialUserService.getSysSpecialUserList(new SysSpecialUser(), user), user);
    }
    
}