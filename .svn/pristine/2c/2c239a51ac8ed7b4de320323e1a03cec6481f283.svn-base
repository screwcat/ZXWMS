package com.zx.emanage.sysmanage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zx.emanage.sysmanage.persist.ISysRoleDao;
import com.zx.emanage.sysmanage.persist.ISysRoleMenuFunctionDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.sysmanage.persist.SysRoleMenuFunctionDao;
import com.zx.emanage.sysmanage.service.ISysRoleService;
import com.zx.emanage.sysmanage.vo.MenuTreeBean;
import com.zx.emanage.sysmanage.vo.SysRoleSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.SysRole;
import com.zx.emanage.util.gen.domain.SysRoleMenuFunction;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.platform.syscontext.vo.TreeBean;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("sysroleService")
public class SysRoleServiceImpl implements ISysRoleService
{
    private static Logger log = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private ISysRoleDao sysroleDao;

    @Autowired
    private ISysRoleMenuFunctionDao sysRoleMenuFunctionDao;

    @Autowired
    private SysRoleDao sysroleDao_m;

    @Autowired
    private SysRoleMenuFunctionDao sysRoleMenuFunctionDao_m;

    @Override
    public List<Map<String, Object>> getComboxList(SysRoleSearchBeanVO queryInfo)
    {
        return sysroleDao.getListWithoutPaging(queryInfo);
    }

    @Override
    public Map<String, Object> getListWithoutPaging(SysRoleSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", sysroleDao.getListWithoutPaging(queryInfo));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(SysRoleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getRole_name()))
        {
            paramMap.put("role_name", SysTools.getSqlLikeParam(queryInfo.getRole_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), sysroleDao_m.findCount(paramMap),
                                             sysroleDao_m.search(paramMap));
        return bean.getGridData();
    }

    @Override
    public com.zx.emanage.util.gen.entity.SysRole getInfoByPK(Integer id)
    {
        return sysroleDao_m.get(id);
    }

    @Override
    @Transactional
    public String save(com.zx.emanage.util.gen.entity.SysRole sysRole, UserBean user, String menuStr)
    {
        int roleId = 0;
        String userName = user.getRealname();
        List<MenuTreeBean> menuList = JsonUtil.jsonArrayToList(menuStr, MenuTreeBean.class);
        Integer role_id = sysRole.getId();
        com.zx.emanage.util.gen.entity.SysRole queryInfo = new com.zx.emanage.util.gen.entity.SysRole();
        queryInfo.setRole_name(sysRole.getRole_name());
        String result = this.checkParamsRepeatByAndMethod(queryInfo, role_id == null ? false : true);
        if (result.equals("repeat"))
        {
            return "repeat";
        }
        sysRole.setLast_update_timestamp(new java.sql.Timestamp(new Date().getTime()));
        if (role_id != null)
        {
            roleId = sysRole.getId();
            sysRole.setRole_name(sysRole.getRole_name());

            sysroleDao_m.update(sysRole);
            sysRoleMenuFunctionDao_m.deleteByRoleId(role_id);// 如果是修改则先删除所有的行为
        }
        else
        {

            sysRole.setEnable_flag("1");
            sysroleDao_m.save(sysRole);
            roleId = sysRole.getId();
        }
        if (menuList == null || menuList.size() == 0)
        {
            return "success";
        }
        List<MenuTreeBean> myMenuList = SysUtil.getSamePropertyListBean(menuList, "type", 0);
        List<MenuTreeBean> myFuncList = SysUtil.getSamePropertyListBean(menuList, "type", 1);
        List<com.zx.emanage.util.gen.entity.SysRoleMenuFunction> roleMenuList = new ArrayList<com.zx.emanage.util.gen.entity.SysRoleMenuFunction>();
        if (myMenuList != null)
        {
            for (MenuTreeBean bean : myMenuList)
            {
                com.zx.emanage.util.gen.entity.SysRoleMenuFunction vo = new com.zx.emanage.util.gen.entity.SysRoleMenuFunction();
                String id = bean.getId();
                vo.setRole_id(roleId);
                vo.setMenu_id(new Integer(id.substring(1)));
                String refId = bean.getRefId();
                if (refId != null && !refId.trim().equals(""))
                {
                    String ids[] = refId.split("\\|");
                    for (String f_id : ids)
                    {
                        String json = new Gson().toJson(vo);
                        com.zx.emanage.util.gen.entity.SysRoleMenuFunction bean_copy = new Gson().fromJson(json,
                                                                                                           com.zx.emanage.util.gen.entity.SysRoleMenuFunction.class);
                        bean_copy.setFunc_id(new Integer(f_id.substring(1)));
                        roleMenuList.add(bean_copy);
                    }
                }
                else
                {
                    roleMenuList.add(vo);
                }
            }
        }
        if (myFuncList != null)
        {
            for (MenuTreeBean bean : myFuncList)
            {
                com.zx.emanage.util.gen.entity.SysRoleMenuFunction vo = new com.zx.emanage.util.gen.entity.SysRoleMenuFunction();
                String id = bean.getId();
                vo.setRole_id(roleId);
                String refId = bean.getRefId();
                if (refId != null && !refId.trim().equals(""))
                {
                    vo.setMenu_id(new Integer(refId.substring(1)));
                }
                vo.setFunc_id(new Integer(id.substring(1)));
                roleMenuList.add(vo);
            }
        }
        if (roleMenuList != null)
        {
            for (com.zx.emanage.util.gen.entity.SysRoleMenuFunction s : roleMenuList)
            {
                sysRoleMenuFunctionDao_m.save(s);
            }
        }
        return "success";

    }

    @Override
    @Transactional
    public String update(SysRole bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysroleDao.updateRecordAll(bean); // update a record replace all,
                                                // support null val
        // ret = sysroleDao.updateRecordCols(bean); // update a record replace
        // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(com.zx.emanage.util.gen.entity.SysRole bean)
    {
        String resStr = "success";
        SysRoleMenuFunction roleMenuFunc = new SysRoleMenuFunction();
        roleMenuFunc.setRole_id(bean.getId());
        sysRoleMenuFunctionDao_m.deleteByRoleId(bean.getId());// 如果是修改则先删除所有的行为
        try
        {
            sysroleDao_m.delete(new Integer[] { bean.getId() }); // nonsupport
                                                                 // null val
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resStr = "fkerror"; // default foreign key error
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * SysRole() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(com.zx.emanage.util.gen.entity.SysRole queryInfo,
                                                Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getId() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getId() != null)
        {
            paramMap.put("id", queryInfo.getId());
        }
        if (queryInfo.getRole_name() != null)
        {
            paramMap.put("role_name", queryInfo.getRole_name());
        }
        if (queryInfo.getEnable_flag() != null)
        {
            paramMap.put("enable_flag", queryInfo.getEnable_flag());
        }

        if (queryInfo.getCreate_timestamp() != null)
        {
            paramMap.put("create_timestamp", queryInfo.getCreate_timestamp());
        }

        if (queryInfo.getLast_update_timestamp() != null)
        {
            paramMap.put("last_update_timestamp", queryInfo.getLast_update_timestamp());
        }
        if (queryInfo.getLast_update_remark() != null)
        {
            paramMap.put("last_update_remark", queryInfo.getLast_update_remark());
        }
        List<Map<String, Object>> beanList = sysroleDao_m.search2(paramMap);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * SysRole() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(SysRole queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getId() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<SysRole> beanList = sysroleDao.getSingleTableListByOrMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    @Override
    public List<MenuTreeBean> getMenuTreeBean(Integer modifyRoleId)
    {

        List<Map<String, Object>> menuList = null;
        List<Map<String, Object>> funcList = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (modifyRoleId != null)
        {
            paramMap.put("current_role_id", modifyRoleId);
        }
        else
        {
            paramMap.put("no_current_role_id", "true");
        }
        menuList = sysroleDao_m.getSysRoleMenuCheck(paramMap);
        funcList = sysroleDao_m.getSysRoleFuncCheck(paramMap);
        List<Map<String, Object>> topMenuList = SysUtil.getSamePropertyList(menuList, "menu_level",
                                                                            GlobalVal.UNIT_LEVEL);// 获取一级菜单
        if (topMenuList == null)
        {
            return null;
        }
        List<MenuTreeBean> menuTreeList = new ArrayList<MenuTreeBean>();
        // 递归菜单下的所有菜单和功能按钮
        for (Map<String, Object> map : topMenuList)
        {
            MenuTreeBean treeBean = new MenuTreeBean();
            String id = (String) map.get("id");
            treeBean.setIsexpand(true);
            treeBean.setId(id);
            treeBean.setText((String) map.get("menu_name"));
            treeBean.setType(0);// 菜单节点
            treeBean.setIschecked(map.get("ischeck") == null ? false : true);
            treeBean.setChildren(getChildMenuFunc(menuList, funcList, treeBean));
            List<TreeBean> childFuncList = transToTreeBean(SysUtil.getSamePropertyList(funcList, "menu_id", id),
                                                           treeBean);
            if (childFuncList != null)
            {
                if (treeBean.getChildren() != null)
                {
                    treeBean.getChildren().addAll(childFuncList);
                }
                else
                {
                    treeBean.setChildren(childFuncList);
                }
            }
            menuTreeList.add(treeBean);
        }
        return menuTreeList;

    }

    private List<TreeBean> getChildMenuFunc(List<Map<String, Object>> menuList, List<Map<String, Object>> funcList,
                                            MenuTreeBean parentTreeBean)
    {
        List<TreeBean> treeList = new ArrayList<TreeBean>();
        String parentId = parentTreeBean.getId();
        for (Map<String, Object> map : menuList)
        {
            String pid = (String) map.get("p_menu_id");
            String id = (String) map.get("id");
            if (parentId.equals(pid))
            {
                MenuTreeBean treeBean = new MenuTreeBean();
                treeBean.setId(id);
                treeBean.setText((String) map.get("menu_name"));
                treeBean.setType(0);// 菜单节点
                treeBean.setIschecked(map.get("ischeck") == null ? false : true);
                treeList.add(treeBean);
                treeBean.setChildren(getChildMenuFunc(menuList, funcList, treeBean));
            }
        }
        List<TreeBean> childFuncList = transToTreeBean(SysUtil.getSamePropertyList(funcList, "menu_id", parentId),
                                                       parentTreeBean);
        if (childFuncList != null)
        {
            treeList.addAll(childFuncList);
        }

        if (treeList.size() == 0)
        {
            return null;
        }
        return treeList;

    }

    private List<TreeBean> transToTreeBean(List<Map<String, Object>> funcList, MenuTreeBean parentTreeBean)
    {
        if (funcList == null)
        {
            return null;
        }
        List<TreeBean> treeList = new ArrayList<TreeBean>();
        String defaultFun = ",";
        for (Map<String, Object> map : funcList)
        {

            String defaultFlag = (String) map.get("default_flag");
            if ("1".equals(defaultFlag))
            {
                defaultFun = defaultFun + (String) map.get("id") + "|";
                continue;
            }
            MenuTreeBean treeBean = new MenuTreeBean();
            treeBean.setId((String) map.get("id"));
            treeBean.setText((String) map.get("func_name"));
            treeBean.setType(1);// 功能节点
            treeBean.setRefId((String) map.get("menu_id"));
            treeBean.setIschecked(map.get("ischeck") == null ? false : true);
            treeList.add(treeBean);
        }
        if (!defaultFun.equals(","))
        {
            parentTreeBean.setRefId(defaultFun.substring(1));// 父菜单的默认功能
        }
        return treeList;
    }

	@Override
	public Map<String, Object> getMessagePeople() {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SysRole> dyCategory = new ArrayList<SysRole>();
		dyCategory = sysroleDao_m.getMessagePeople();
		resMap.put("", dyCategory);
		return resMap;
	}
}
