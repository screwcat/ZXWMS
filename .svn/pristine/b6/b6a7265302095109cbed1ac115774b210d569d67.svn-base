package com.zx.emanage.sysmanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysOrganizationDao;
import com.zx.emanage.sysmanage.service.ISysDeptService;
import com.zx.emanage.sysmanage.vo.ETreeBean;
import com.zx.emanage.sysmanage.vo.SysDeptSearchBeanVO;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.SysDept;
import com.zx.emanage.util.gen.entity.SysDeptData;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.platform.syscontext.vo.TreeBean;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("sysdeptService")
public class SysDeptServiceImpl implements ISysDeptService
{
    private static Logger log = LoggerFactory.getLogger(SysDeptServiceImpl.class);

    @Autowired
    private SysDeptDao sysdeptDao;

    @Autowired
    private SysOrganizationDao sysOrganizationDao;

    @Override
    public Map<String, Object> getListWithoutPaging(SysDeptSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = sysdeptDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(SysDeptSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = sysdeptDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(),
                                                                                       sysdeptDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public SysDept getInfoByPK(Integer dept_id)
    {
        return sysdeptDao.get(dept_id);
    }

    @Override
    @Transactional
    public String save(SysDept bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysdeptDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(SysDept bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysdeptDao.update(bean); // update a record replace columns,
                                       // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * SysDept() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<SysDept> getListByEntity(SysDept queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<SysDept> beanList = sysdeptDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public List<ETreeBean> getDeptTree()
    {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sortname", "dept_id");
        params.put("sortorder", "asc");
        List<ETreeBean> deptList = new ArrayList<ETreeBean>();
        List<Map<String, Object>> deptDataList = sysdeptDao.search(params);
        if (deptDataList == null || deptDataList.size() == 0)
        {
            return deptList;
        }
        List<Map<String, Object>> topMenuList = SysUtil.getSamePropertyList(deptDataList, "dept_pid",
                                                                            GlobalVal.P_DEPT_ID);// 获取一级部门
        for (Map<String, Object> map : topMenuList)
        {
            String id = "" + map.get("dept_id");
            String dept_name = (String) map.get("dept_name");
            ETreeBean bean = new ETreeBean(dept_name, "", id);
            bean.setOrgan_id("" + map.get("dept_organizationid"));
            bean.setIsexpand(true);
            bean.setChildren(getChild(deptDataList, id));
            deptList.add(bean);
        }
        return deptList;
    }

    private List<TreeBean> getOrganizationChild(List<Map<String, Object>> organizationDataList, String pid,
                                                List<ETreeBean> deptDatalist)
    {
        List<TreeBean> deptList = new ArrayList<TreeBean>();
        for (Map<String, Object> map : organizationDataList)
        {
            String p_dept_id = "" + map.get("organization_pid");
            if (p_dept_id.equals(pid))
            {
                String id = "" + map.get("organization_id");
                String dept_name = (String) map.get("organization_name");
                ETreeBean bean = new ETreeBean(dept_name, "", "P" + id);
                deptList.add(bean);
                bean.setIsexpand(true);
                List<TreeBean> organList = getOrganizationChild(organizationDataList, id, deptDatalist);
                List<TreeBean> childrenDeptList = getDeptChild(deptDatalist, id);
                if (organList != null && childrenDeptList.size() > 0)
                {
                    organList.addAll(childrenDeptList);
                }
                else if (childrenDeptList.size() > 0)
                {
                    organList = childrenDeptList;
                }
                bean.setChildren(organList);

            }
        }

        if (deptList.size() == 0)
        {
            return null;
        }
        return deptList;
    }

    private List<TreeBean> getOrganizationChild(List<Map<String, Object>> organizationDataList, String pid)
    {
        List<TreeBean> deptList = new ArrayList<TreeBean>();
        for (Map<String, Object> map : organizationDataList)
        {
            String p_dept_id = "" + map.get("organization_pid");
            if (p_dept_id.equals(pid))
            {
                String id = "" + map.get("organization_id");
                String dept_name = (String) map.get("organization_name");
                ETreeBean bean = new ETreeBean(dept_name, "", id);
                deptList.add(bean);
                bean.setIsexpand(true);
                bean.setChildren(getOrganizationChild(organizationDataList, id));

            }
        }

        if (deptList.size() == 0)
        {
            return null;
        }
        return deptList;
    }

    private List<TreeBean> getChild(List<Map<String, Object>> deptDataList, String pid)
    {
        List<TreeBean> deptList = new ArrayList<TreeBean>();
        for (Map<String, Object> map : deptDataList)
        {
            String p_dept_id = "" + map.get("dept_pid");
            if (p_dept_id.equals(pid))
            {
                String id = "" + map.get("dept_id");
                String dept_name = (String) map.get("dept_name");
                ETreeBean bean = new ETreeBean(dept_name, "", id);
                deptList.add(bean);
                bean.setIsexpand(true);
                bean.setChildren(getChild(deptDataList, id));
            }
        }
        if (deptList.size() == 0)
        {
            return null;
        }
        return deptList;
    }

    @Override
    public List<ETreeBean> getOrganizationTree()
    {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sortname", "organization_id");
        params.put("sortorder", "asc");
        List<ETreeBean> deptList = new ArrayList<ETreeBean>();
        List<Map<String, Object>> organizationDataList = sysOrganizationDao.search(params);
        if (organizationDataList == null || organizationDataList.size() == 0)
        {
            return deptList;
        }
        List<Map<String, Object>> topMenuList = SysUtil.getSamePropertyList(organizationDataList, "organization_pid",
                                                                            GlobalVal.P_DEPT_ID);// 获取一级部门
        List<ETreeBean> organlist = new ArrayList<>();
        List<ETreeBean> deptTreelist = getDeptTree();
        for (Map<String, Object> map : topMenuList)
        {
            String id = "" + map.get("organization_id");
            String dept_name = (String) map.get("organization_name");
            ETreeBean bean = new ETreeBean(dept_name, "", "P" + id);
            bean.setIsexpand(true);
            List<TreeBean> childrenList = getOrganizationChild(organizationDataList, id, deptTreelist);
            List<TreeBean> childrenDeptList = getDeptChild(deptTreelist, id);
            if (childrenList == null)
            {
                childrenList = childrenDeptList;
            }
            else
            {
                childrenList.addAll(childrenDeptList);
            }

            bean.setChildren(childrenList);
            organlist.add(bean);
        }
        return organlist;
    }

    private List<TreeBean> getDeptChild(List<ETreeBean> deptlist, String id)
    {
        List<TreeBean> list = new ArrayList<>();
        for (ETreeBean bean : deptlist)
        {
            if (id.equals(bean.getOrgan_id()))
            {
                list.add(bean);
            }
        }
        return list;
    }
   
    
    /**
     * 获取部门名称以及ID
     * @param isEmpty 代表"请选择"
     * @param isAll 代表 "全部"
     * @param deptLevel 代表 "部门级别"
     * @param deptIds 代表 "需要查询哪些City"
     * @return
     */
    @Override
    public List<SysDeptData> getSysCityCodeData(String isEmpty, String isAll,String deptLevel,String deptIds)
    {
        List<SysDeptData> list =new ArrayList<SysDeptData>();
        for(int i=0 ; i < deptIds.split(",").length ; i++){
            SysDept sDept = new SysDept();
            sDept.setDept_id(Integer.valueOf(deptIds.split(",")[i]));
            List<SysDept> sysdeptList=sysdeptDao.getListByEntityCity(sDept);
            for(SysDept sysdept:sysdeptList){
                SysDeptData sysDeptData= new SysDeptData();
                sysDeptData.setDeptId(sysdept.getDept_id().toString());
                sysDeptData.setDeptName(sysdept.getDept_name());
                list.add(sysDeptData);
            }
        }
        if ("true".equals(isEmpty))
        {
            SysDeptData entity = new SysDeptData();
            entity.setDeptId("-1");
            entity.setDeptName("---请选择---");
            list.add(0, entity);
        }
        if ("true".equals(isAll))
        {
            SysDeptData entity = new SysDeptData();
            entity.setDeptId("-2");
            entity.setDeptName("---全部---");
            list.add(0, entity);
        }
        return list;
    }
    /**
     * 获取部门名称以及ID--根据上级id获取当前部门下面的部门
     * @param isEmpty 代表"请选择"
     * @param isAll 代表 "全部"
     * @param deptLevel 代表 "部门级别"
     * @param deptIds 代表 "需要查询哪些City"
     * @return
     */
    @Override
    public List<Map<String, Object>> getSysCityCodeDataList(String isEmpty, String isAll, String deptLevel, String deptIds) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SysDept sDept = new SysDept();
        sDept.setDept_code(WmsHelp.TOP_DEPT_CODE);//产品管理部的部门编码
        List<SysDept> systopdeptList = sysdeptDao.getListByEntity(sDept);
        sDept.setDept_code(null);
        if(systopdeptList == null||systopdeptList.size() == 0) {
        	return new ArrayList<>();
        }
        sDept.setDept_pid(systopdeptList.get(0).getDept_id());
        List<SysDept> sysdeptList = sysdeptDao.getListByEntityCity(sDept);
        Map<String, Object> tempMap = new HashMap<String, Object>();
        if ("true".equals(isEmpty)) {
            tempMap = new HashMap<String, Object>();
            tempMap.put("storesId", "-1");
            tempMap.put("storesName", "---请选择---");
            list.add(tempMap);
        }
        if ("true".equals(isAll)) {
            tempMap = new HashMap<String, Object>();
            tempMap.put("storesId", "-2");
            tempMap.put("storesName", "---全部---");
            list.add(tempMap);
        }
        for(SysDept sysdept : sysdeptList) {
            tempMap = new HashMap<String, Object>();
            tempMap.put("storesId", sysdept.getDept_id().toString());
            tempMap.put("storesName", sysdept.getDept_name());
            tempMap.put("storesCode", sysdept.getDept_code());
            list.add(tempMap);
        }
        return list;
    }
    /**
     * 获取部门名称以及ID
     * @param isEmpty 代表"请选择"
     * @param isAll 代表 "全部"
     * @param deptLevel 代表 "部门级别"
     * @param deptIds 代表 "需要查询哪些公司"
     * @return
     */
    @Override
    public List<SysDeptData> getSysDeptData(String isEmpty, String isAll,String deptLevel,String companyids)
    {
        List<SysDeptData> list =new ArrayList<SysDeptData>();
        for(int i=0 ; i < companyids.split(",").length ; i++){
            SysDept sDept = new SysDept();
            sDept.setDept_id(Integer.valueOf(companyids.split(",")[i]));
            List<SysDept> sysdeptList=sysdeptDao.getListByEntity(sDept);
            for(SysDept sysdept:sysdeptList){
                SysDeptData sysDeptData= new SysDeptData();
                sysDeptData.setCompanyId(sysdept.getDept_id().toString());
                sysDeptData.setCompanyName(sysdept.getDept_name());
                list.add(sysDeptData);
            }
        }
        if ("true".equals(isEmpty))
        {
            SysDeptData entity = new SysDeptData();
            entity.setCompanyId("-1");
            entity.setCompanyName("---请选择---");
            list.add(0, entity);
        }
        if ("true".equals(isAll))
        {
            SysDeptData entity = new SysDeptData();
            entity.setCompanyId("-2");
            entity.setCompanyName("---全部---");
            list.add(0, entity);
        }
        return list;
    }
    /**
     * 根据提供的公司ID，获取下面所有部门的ID
     * @param isEmpty代表"请选择"
     * @param isAll代表 "全部"
     * @param companyId 代表 "组织架构中选定某一公司的ID"
     * @return
     */
    @Override
    public List<SysDeptData> getAllSysDeptsWithList(String isEmpty, String isAll, String companyId)
    {
        List<SysDeptData>list = new ArrayList<SysDeptData>();
        	 if ("true".equals(isAll))
             {
                 SysDeptData entity = new SysDeptData();
                 entity.setDeptId("-1");
                 entity.setDeptName("---全部---");
                 list.add(0, entity);
             }
        	 if ("true".equals(isEmpty))
             {
                 SysDeptData entity = new SysDeptData();
                 entity.setDeptId("-2");
                 entity.setDeptName("---请选择---");
                 list.add(0, entity);
             }
        if(!companyId.equals("-2")||!companyId.equals("-1")){
            List<Integer> sysIntegerslist=sysdeptDao.getDeptId(Integer.valueOf(companyId)); 
            for(int i=0;i<sysIntegerslist.size();i++){
                SysDept sysdept=sysdeptDao.get(sysIntegerslist.get(i));
                SysDeptData sysDeptData = new SysDeptData();
                sysDeptData.setDeptId(sysdept.getDept_id().toString());
                sysDeptData.setDeptName(sysdept.getDept_name());
                list.add(sysDeptData);
            }
         }
        return list;
    }
    /**
     * Description:根据提供的部门ID，获取下面所有团队ID以及名称
     * @param isEmpty 代表请选择
     * @param isAll 代表全部
     * @param deptpId 代表部门ID
     */
    @Override
    public List<SysDeptData> getAllSysTeamsWithList(String isEmpty,String isAll, String deptpId)
    {
        List<SysDeptData>list = new ArrayList<SysDeptData>();
        if ("true".equals(isEmpty))
        {
            SysDeptData entity = new SysDeptData();
            entity.setTeamId("-1");
            entity.setTeamName("---请选择---");
            list.add(0, entity);
        }
        if ("true".equals(isAll))
        {
            SysDeptData entity = new SysDeptData();
            entity.setTeamId("-2");
            entity.setTeamName("---全部---");
            list.add(0, entity);
        }
        if(!deptpId.equals("-2")){
            List<Integer> sysIntegerslist=sysdeptDao.getDeptId(Integer.valueOf(deptpId));
            for(int i=0;i<sysIntegerslist.size();i++){
                SysDept sysdept=sysdeptDao.get(sysIntegerslist.get(i));
                SysDeptData sysDeptData = new SysDeptData();
                sysDeptData.setTeamId(sysdept.getDept_id().toString());
                sysDeptData.setTeamName(sysdept.getDept_name());
                list.add(sysDeptData);
            }
         }
        return list;
    }
    /**
     * Description:根据提供的部门ID，获取下面所有团队ID以及名称
     * @param isEmpty 代表请选择
     * @param isAll 代表全部
     * @param deptpId 代表部门ID
     */
    @Override
    public List<SysDeptData> getAllSysGroupsWithList(String isEmpty,String isAll, String teampId)
    {
        List<SysDeptData>list = new ArrayList<SysDeptData>();
        if ("true".equals(isEmpty))
        {
            SysDeptData entity = new SysDeptData();
            entity.setGroupId("-1");
            entity.setGroupName("---请选择---");
            list.add(0, entity);
        }
        if ("true".equals(isAll))
        {
            SysDeptData entity = new SysDeptData();
            entity.setGroupId("-2");
            entity.setGroupName("---全部---");
            list.add(0, entity);
        }
        if(!teampId.equals("-2")){
            List<Integer> sysIntegerslist=sysdeptDao.getDeptId(Integer.valueOf(teampId));
            for(int i=0;i<sysIntegerslist.size();i++){
                SysDept sysdept=sysdeptDao.get(sysIntegerslist.get(i));
                SysDeptData sysDeptData = new SysDeptData();
                sysDeptData.setGroupId(sysdept.getDept_id().toString());
                sysDeptData.setGroupName(sysdept.getDept_name());
                list.add(sysDeptData);
            }
         }
        return list;
    }

    /**
     * 获取新的组织机构树形菜单
     */
	@Override
	public List<ETreeBean> getNewOrganizationTree() {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("sortname", "dept_id");
        params.put("sortorder", "asc");
		//(1)获取部门表中全部的数据
        List<Map<String, Object>> deptDataList = sysdeptDao.search(params);
        //(2)遍历集合中的元素
        List<ETreeBean> alls = getTreeNode(GlobalVal.P_DEPT_ID, deptDataList);
		return alls;
	}

	/**
	 * 根据指定的pid进行筛选出根目录的元素
	 * 
	 * @param pid
	 * @param deptDataList
	 * @return
	 */
	private List<ETreeBean> getTreeNode(String pid, List<Map<String, Object>> deptDataList) {
		List<ETreeBean> list = new ArrayList<ETreeBean>();
		for(Map<String, Object> map : deptDataList){
			if((map.get("dept_pid") + "").equals(pid)){
				if((map.get("dept_organizationid") + "").equals("1")){
					String id = map.get("dept_id") + "";
					String name = map.get("dept_name") + "";
					
					ETreeBean eTreeBean = new ETreeBean(name, "", id);
					eTreeBean.setChildren(getTreeChilds(id, deptDataList));
					eTreeBean.setLevel(Integer.parseInt(map.get("dept_level") + ""));
					eTreeBean.setIsexpand(true);
					list.add(eTreeBean);
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据传入的pid,获取对应的元素,然后针对回去的子元素进行递归查询遍历出子级别元素
	 * 
	 * @param id
	 * @param deptDataList
	 * @return
	 */
	private List<TreeBean> getTreeChilds(String pid, List<Map<String, Object>> deptDataList) {
		List<TreeBean> list = getChilds(pid, deptDataList);
		if(list.size() > 0){
			for(TreeBean bean : list){
				List<TreeBean> childers = getTreeChilds(bean.getId(), deptDataList);
				if(childers.size() > 0){
					bean.setChildren(childers);
				}else{
					bean.setChildren(null);
				}
				bean.setIsexpand(true);
			}
		}
		return list;
	}

	/**
	 * 根据传入的pid获取对应的元素
	 * 
	 * @param pid
	 * @param deptDataList
	 * @return
	 */
	private List<TreeBean> getChilds(String pid, List<Map<String, Object>> deptDataList) {
		List<TreeBean> list = new ArrayList<TreeBean>();
		for(Map<String, Object> map : deptDataList){
			if((map.get("dept_pid") + "").equals(pid)){
				String deptid = map.get("dept_id") + "";
				String name = map.get("dept_name") + "";
				ETreeBean eTreeBean = new ETreeBean(name, "", deptid);
				eTreeBean.setLevel(Integer.parseInt(map.get("dept_level") + ""));
				list.add(eTreeBean);
			}
		}
		return list;
	}
	
}
