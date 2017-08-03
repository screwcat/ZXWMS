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

import com.zx.emanage.sysmanage.service.ISysRoleService;
import com.zx.emanage.sysmanage.vo.MenuTreeBean;
import com.zx.emanage.sysmanage.vo.SysRoleSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.SysRole;
import com.zx.emanage.util.gen.vo.SysRoleVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class SysRoleController
{
    private static Logger log = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private ISysRoleService sysroleService;

    /**
     * Description :get combox list by vo queryInfo
     * 
     * @param queryInfo
     * @return combox list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysrolegetcomboxlist.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<Map<String, Object>> getComboxList(SysRoleSearchBeanVO queryInfo)
    {
        return sysroleService.getComboxList(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysrolewithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(SysRoleSearchBeanVO queryInfo)
    {
        return sysroleService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysrolewithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(SysRoleSearchBeanVO queryInfo)
    {
        return sysroleService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysRoleVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysroleinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public com.zx.emanage.util.gen.entity.SysRole getInfoByPK(Integer id)
    {
        return sysroleService.getInfoByPK(id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysrolesave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, com.zx.emanage.util.gen.entity.SysRole bean, String menuArr)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysroleService.save(bean, user, menuArr);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理-角色管理-新增";
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
    @RequestMapping(value = "/sysmanage/sysroleupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, com.zx.emanage.util.gen.entity.SysRole bean, String menuArr)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysroleService.save(bean, user, menuArr);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理-角色管理-修改";
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
    @RequestMapping(value = "/sysmanage/sysroledelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, com.zx.emanage.util.gen.entity.SysRole bean)
    {
        String result = sysroleService.delete(bean);
        // record log
        if ("success".equals(result))
        {
            String msg = "系统管理-角色管理-删除";
            SysTools.saveLog(request, msg); // record log method
        }
        return result;
    }

    @RequestMapping(value = "/sysmanage/getrolemenutree.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<MenuTreeBean> getRoleMenuTree(Integer modifyRoleId)
    {
        return sysroleService.getMenuTreeBean(modifyRoleId);
    }
    
    /**
     * @return Map<String,Object> 
     * @throws
     * @author jiaodelong 
     * @date 2016年10月25日 
     */
    @RequestMapping(value = "/remind/getMessagePeople.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getMessagePeople() {
    	Map<String, Object> resMap = sysroleService.getMessagePeople();
        return resMap;
    }
    
    
    
    
    
}