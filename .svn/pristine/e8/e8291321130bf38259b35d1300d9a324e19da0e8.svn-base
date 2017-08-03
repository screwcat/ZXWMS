package com.zx.emanage.sysmanage.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.ETreeBean;
import com.zx.emanage.sysmanage.vo.SysDeptSearchBeanVO;
import com.zx.emanage.util.gen.entity.SysDept;
import com.zx.emanage.util.gen.entity.SysDeptData;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface ISysDeptService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(SysDeptSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(SysDeptSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return SysDeptVO
     * @author auto_generator
     */
    public SysDept getInfoByPK(Integer dept_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(SysDept bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(SysDept bean, UserBean user);

    public List<ETreeBean> getDeptTree();

    public List<ETreeBean> getOrganizationTree();
    /**
     * 提供所有公司ID,获取相对应的公司ID以及公司名称
     * @param isEmpty 代表"请选择"
     * @param isAll 代表 "全部"
     * @param deptLevel 代表 "部门级别"
     * @param companyids 代表 "需要查询公司ID"
     * @return
     */
    public List<SysDeptData> getSysDeptData(String isEmpty, String isAll,String deptLevel,String companyids);
    /**
     * 根据提供的公司ID，获取相对应的组织架构中所有部门信息
     * @param isEmpty代表"请选择"
     * @param isAll代表 "全部"
     * @param companyId 代表 "组织架构中某一公司的ID"
     * @return
     */
    public List<SysDeptData> getAllSysDeptsWithList(String isEmpty, String isAll, String companyId);
    /**
     * Description:根据提供的部门ID，获取下面所有团队ID以及名称
     * @param isEmpty 代表请选择
     * @param isAll 代表全部
     * @param deptpId 代表部门ID
     */
    public List<SysDeptData> getAllSysTeamsWithList(String isEmpty,String isAll, String deptpId);
    /**
     * 获取部门名称以及ID
     * @param isEmpty 代表"请选择"
     * @param isAll 代表 "全部"
     * @param deptLevel 代表 "部门级别"
     * @param deptIds 代表 "需要查询哪些City"
     * @return
     */
    public List<SysDeptData> getSysCityCodeData(String isEmpty, String isAll,String deptLevel,String deptIds);
    /**
     * 获取部门名称以及ID--根据上级id获取当前部门下面的部门
     * @param isEmpty 代表"请选择"
     * @param isAll 代表 "全部"
     * @param deptLevel 代表 "部门级别"
     * @param deptIds 代表 "需要查询哪些City"
     * @return
     */
    public List<Map<String, Object>> getSysCityCodeDataList(String isEmpty, String isAll, String deptLevel, String deptIds);
    /**
     * Description:根据提供的部门ID，获取下面所有团队ID以及名称
     * @param isEmpty 代表请选择
     * @param isAll 代表全部
     * @param teampId 代表部门ID
     */
    public List<SysDeptData> getAllSysGroupsWithList(String isEmpty,String isAll, String teampId);

    /**
     * 获取新的组织架构的树形结构菜单
     * @return
     * @author donghao
     * @date 2016年8月5日15:16:09
     */
	public List<ETreeBean> getNewOrganizationTree();

    
}