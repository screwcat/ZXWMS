package com.zx.emanage.sysmanage.web;

import java.util.HashMap;
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

import com.zx.emanage.sysmanage.service.IPmPersonnelService;
import com.zx.emanage.sysmanage.vo.PmPersonnelSearchBeanVO;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class PmPersonnelController
{
    private static Logger log = LoggerFactory.getLogger(PmPersonnelController.class);

    @Autowired
    private IPmPersonnelService pmpersonnelService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/pmpersonnelwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                    RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(PmPersonnelSearchBeanVO queryInfo)
    {
        return pmpersonnelService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/pmpersonnelwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(PmPersonnelSearchBeanVO queryInfo)
    {
        return pmpersonnelService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return PmPersonnelVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/pmpersonnelinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public PmPersonnel getInfoByPK(Integer personnel_id)
    {
        return pmpersonnelService.getInfoByPK(personnel_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/pmpersonnelsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, PmPersonnel bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = pmpersonnelService.save(bean, user);
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
    @RequestMapping(value = "/sysmanage/pmpersonnelupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, PmPersonnel bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = pmpersonnelService.update(bean, user);
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
     * 获取指定部门下的人员列表
     * 
     * @return Map 返回相应的人员信息
     * @return "success" or "error"
     * @author hancd
     */
    @RequestMapping(value = "/sysmanage/getListByDeptId.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListByDeptId(String deptId)
    {
        PmPersonnel pmPersonnel = new PmPersonnel();
        Map<String, Object> resMap = new HashMap<String, Object>();
        // 如果传递部门ID不为空 就按照部门查询
        if (StringUtil.isNotBlank(deptId))
        {
            pmPersonnel.setPersonnel_deptid(Integer.parseInt(deptId));
            if (deptId.startsWith("P"))
            {
                return resMap;
            }
        }
        pmPersonnel.setEnable_flag("1");
        return pmpersonnelService.getListByEntity(pmPersonnel);
    }

    /**
     * 获取指定部门下的人员列表 获取指定查询条件,实现根据查询条件查询出相应员工的信息 例如：员工编号或者是姓名
     * 
     * @param personnel_shortcode 员工编号
     * @param personnel_name 员工姓名
     * @return Map 返回相应的人员信息
     * @return "success" or "error"
     * @author hancd
     */
    @RequestMapping(value = "/sysmanage/getListByDeptIdorCode.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListByDeptIdorCode(String deptId, String personnel_shortcode, String personnel_name)
    {
        PmPersonnel pmPersonnel = new PmPersonnel();
        // 如果传递部门ID不为空 就按照部门查询
        if (StringUtil.isNotBlank(deptId))
        {
            Map<String, Object> resMap = new HashMap<String, Object>();
            pmPersonnel.setPersonnel_deptid(Integer.parseInt(deptId));
            if (deptId.startsWith("P"))
            {
                return resMap;
            }
        }
        else
        {
            // 否者就按照查询的条件查
            if (StringUtil.isBlank(personnel_shortcode) && StringUtil.isBlank(personnel_name))
            {
                Map<String, Object> resMap = new HashMap<>();
                return resMap;
            }
            else
            {
                if (StringUtil.isNotBlank(personnel_shortcode))
                {
                    pmPersonnel.setPersonnel_shortcode(personnel_shortcode);
                }
                if (StringUtil.isNotBlank(personnel_name))
                {
                    pmPersonnel.setPersonnel_name(personnel_name);
                }
            }
        }
        pmPersonnel.setEnable_flag("1");
        return pmpersonnelService.getListByEntity(pmPersonnel);
    }

    /**
     * 实现：通过给定业务员ID 查询业务员相关的具体信息
     * 
     * @param salesman_id
     * @return 返回相关内容
     * @author hancd
     */
    @RequestMapping(value = "/sysmanage/getPmPersonnelInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getPmPersonnelInfo(Integer salesman_id)
    {
        PmPersonnel pmPersonnel = new PmPersonnel();
        pmPersonnel.setPersonnel_id(salesman_id);
        pmPersonnel.setEnable_flag("1");
        return pmpersonnelService.getListByEntity(pmPersonnel);
    }
    
    /**
     * @Title: getPmPersonnelByIds 
     * @Description: 根据人员id集合查询人员
     * @param personnelIds
     * @return List<Map<String,Object>> 
     * @throws
     * @author lvtu 
     * @date 2015年9月17日 下午5:10:24
     */
    @RequestMapping(value = "/sysmanage/getPmPersonnelByIds.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getPmPersonnelByIds(String personnelIds) {
        return pmpersonnelService.getPmPersonnelByIds(personnelIds);
    }
}