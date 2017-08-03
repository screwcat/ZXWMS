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

import com.zx.emanage.sysmanage.service.ISysDeptService;
import com.zx.emanage.sysmanage.vo.ETreeBean;
import com.zx.emanage.sysmanage.vo.SysDeptSearchBeanVO;
import com.zx.emanage.util.gen.entity.SysDept;
import com.zx.emanage.util.gen.entity.SysDeptData;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class SysDeptController
{
    private static Logger log = LoggerFactory.getLogger(SysDeptController.class);

    @Autowired
    private ISysDeptService sysdeptService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysdeptwithoutpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(SysDeptSearchBeanVO queryInfo)
    {
        return sysdeptService.getListWithoutPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysdeptwithpaginglist.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(SysDeptSearchBeanVO queryInfo)
    {
        return sysdeptService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysDeptVO
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysdeptinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public SysDept getInfoByPK(Integer dept_id)
    {
        return sysdeptService.getInfoByPK(dept_id);
    }

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    @RequestMapping(value = "/sysmanage/sysdeptsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, SysDept bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysdeptService.save(bean, user);
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
    @RequestMapping(value = "/sysmanage/sysdeptupdate.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doUpdate(HttpServletRequest request, SysDept bean)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = sysdeptService.update(bean, user);
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

    @RequestMapping(value = "/sysmanage/getdepttree.do", method = RequestMethod.GET)
    @ResponseBody
    public List<ETreeBean> getDeptTree()
    {
    	//sysdeptService.getOrganizationTree();
        return sysdeptService.getNewOrganizationTree();
    }
    /**
     * Description :获取部门名(关联CITYCODE)称以及ID--
     * @param isEmpty 代表请选择
     * @param isAll 代表全部
     * @param deptLevel 代表部门级别
     * @param deptIds   代表部门ID
     */
    @RequestMapping(value = "/sysmanage/getAllSysCityCodeWithList.do", method = {RequestMethod.GET})
    @ResponseBody
    public List<SysDeptData> getAllSysCityCodeWithList(String isEmpty,String isAll,String deptLevel,String deptIds){
        List<SysDeptData> sysDeptList =sysdeptService.getSysCityCodeData(isEmpty,isAll,deptLevel,deptIds); 
        return sysDeptList;
    }
    /**
     * Description :获取部门名(关联CITYCODE)称以及ID--根据上级id获取当前部门下面的部门
     * @param isEmpty 代表请选择
     * @param isAll 代表全部
     * @param deptLevel 代表部门级别
     * @param deptIds   代表部门ID
     */
    @RequestMapping(value = "/sysmanage/getCityCodeWithList.do", method = {RequestMethod.GET})
    @ResponseBody
    public List<Map<String, Object>> getCityCodeWithList(String isEmpty, String isAll, String deptLevel, String deptIds) {
        List<Map<String, Object>> sysDeptList = sysdeptService.getSysCityCodeDataList(isEmpty, isAll, deptLevel, deptIds); 
        return sysDeptList;
    }
    /**
     * Description :根据提供的所要查询的公司ID，获取所有公司名称以及ID
     * @param isEmpty 代表请选择
     * @param isAll 代表全部
     * @param deptLevel 代表公司所属架构的级别
     * @param companyIds   代表公司ID
     * @author hancd
     */
    @RequestMapping(value = "/sysmanage/getAllSysCompantyWithList.do", method = {RequestMethod.GET})
    @ResponseBody
    public List<SysDeptData> getAllSysDeptWithList(String isEmpty,String isAll,String deptLevel,String companyIds){
        List<SysDeptData> sysDeptList =sysdeptService.getSysDeptData(isEmpty,isAll,deptLevel,companyIds); 
        return sysDeptList;
    }
    /**
     * Description:根据提供的某一个公司的组织ID获取相关下面所有部门的ID以及名称
     * @param isEmpty 代表请选择
     * @param isAll 代表全部
     * @param companyId 代表公司ID
     */
    @RequestMapping(value="/sysmanage/getAllSysDeptsWithList.do",method ={RequestMethod.GET})
    @ResponseBody
    public List<SysDeptData> getAllSysBusinessgroupWithList(String isEmpty,String isAll,String companyId){
        List<SysDeptData> sysDeptList =sysdeptService.getAllSysDeptsWithList(isEmpty,isAll,companyId);
        return sysDeptList;
    }
    /**
     * Description:根据提供的部门ID，获取下面所有团队ID以及名称
     * @param isEmpty 代表请选择
     * @param isAll 代表全部
     * @param deptpId 代表部门ID
     */
    @RequestMapping(value ="/sysmanage/getAllSysTeamsWithList.do",method ={RequestMethod.GET})
    @ResponseBody
    public List<SysDeptData> getAllSysTeamsWithList(String isEmpty,String isAll,String deptpId){
        List<SysDeptData> sysDeptList = sysdeptService.getAllSysTeamsWithList(isEmpty,isAll,deptpId);
        return sysDeptList;
    }
    /**
     * Description:根据提供的部门ID，获取下面所有团队ID以及名称
     * @param isEmpty 代表请选择
     * @param isAll 代表全部
     * @param deptpId 代表部门ID
     */
    @RequestMapping(value ="/sysmanage/getAllSysGroupsWithList.do",method ={RequestMethod.GET})
    @ResponseBody
    public List<SysDeptData> getAllSysGroupsWithList(String isEmpty,String isAll,String teampId){
        List<SysDeptData> sysDeptList = sysdeptService.getAllSysGroupsWithList(isEmpty,isAll,teampId);
        return sysDeptList;
    }
}