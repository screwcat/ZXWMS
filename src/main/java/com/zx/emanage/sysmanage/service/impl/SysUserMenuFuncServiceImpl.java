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

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.platform.syscontext.vo.TreeBean;
import com.google.gson.Gson;
import com.zx.emanage.sysmanage.persist.ISysUserMenuFuncDao;
import com.zx.emanage.sysmanage.persist.SysUserMenuFuncDao;
import com.zx.emanage.sysmanage.service.ISysUserMenuFuncService;
import com.zx.emanage.sysmanage.vo.MenuTreeBean;
import com.zx.emanage.sysmanage.vo.SysSelectMeun;
import com.zx.emanage.sysmanage.vo.SysUserMenuFuncSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.SysUserMenuFunc;
import com.zx.emanage.util.gen.entity.SysMenu;
import com.zx.emanage.util.gen.vo.SysUserMenuFuncVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("sysusermenufuncService")
public class SysUserMenuFuncServiceImpl implements ISysUserMenuFuncService
{
    private static Logger log = LoggerFactory.getLogger(SysUserMenuFuncServiceImpl.class);

    @Autowired
    private ISysUserMenuFuncDao sysusermenufuncDao;

    @Autowired
    private SysUserMenuFuncDao sysusermenufuncDao_m;

    @Override
    public List<Map<String, Object>> getComboxList(SysUserMenuFuncSearchBeanVO queryInfo)
    {
        return sysusermenufuncDao.getListWithoutPaging(queryInfo);
    }

    @Override
    public Map<String, Object> getListWithoutPaging(SysUserMenuFuncSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", sysusermenufuncDao.getListWithoutPaging(queryInfo));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(SysUserMenuFuncSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), sysusermenufuncDao.getListCountNum(queryInfo),
                                             sysusermenufuncDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public SysUserMenuFuncVO getInfoByPK(Integer id)
    {
        return sysusermenufuncDao.getInfoByPK(id);
    }

    @Override
    @Transactional
    public String save(String menuStr, UserBean user, Integer user_id)
    {
        List<MenuTreeBean> menuList = JsonUtil.jsonArrayToList(menuStr, MenuTreeBean.class);
        sysusermenufuncDao_m.deleteByUserId(user_id);
        if (menuList == null || menuList.size() == 0)
        {
            return "success";
        }
        List<MenuTreeBean> myMenuList = SysUtil.getSamePropertyListBean(menuList, "type", 0);
        List<MenuTreeBean> myFuncList = SysUtil.getSamePropertyListBean(menuList, "type", 1);
        List<com.zx.emanage.util.gen.entity.SysUserMenuFunc> userMenuList = new ArrayList<com.zx.emanage.util.gen.entity.SysUserMenuFunc>();
        if (myMenuList != null)
        {
            for (MenuTreeBean bean : myMenuList)
            {
                com.zx.emanage.util.gen.entity.SysUserMenuFunc entity = new com.zx.emanage.util.gen.entity.SysUserMenuFunc();
                String id = bean.getId();
                entity.setUser_id(user_id);
                entity.setMenu_id(new Integer(id.substring(1)));
                String refId = bean.getRefId();
                if (refId != null && !refId.trim().equals(""))
                {
                    String ids[] = refId.split("\\|");
                    for (String f_id : ids)
                    {
                        String json = new Gson().toJson(entity);
                        com.zx.emanage.util.gen.entity.SysUserMenuFunc bean_copy = new Gson().fromJson(json,
                                                                                                       com.zx.emanage.util.gen.entity.SysUserMenuFunc.class);
                        bean_copy.setFunc_id(new Integer(f_id.substring(1)));
                        userMenuList.add(bean_copy);
                    }
                }
                else
                {
                    userMenuList.add(entity);
                }
            }
        }
        if (myFuncList != null)
        {
            for (MenuTreeBean bean : myFuncList)
            {
                com.zx.emanage.util.gen.entity.SysUserMenuFunc entity = new com.zx.emanage.util.gen.entity.SysUserMenuFunc();
                String id = bean.getId();
                entity.setUser_id(user_id);
                String refId = bean.getRefId();
                if (refId != null && !refId.trim().equals(""))
                {
                    entity.setMenu_id(new Integer(refId.substring(1)));
                }
                entity.setFunc_id(new Integer(id.substring(1)));
                userMenuList.add(entity);
            }
        }
        if (userMenuList != null)
        {
            for (com.zx.emanage.util.gen.entity.SysUserMenuFunc m : userMenuList)
            {
                sysusermenufuncDao_m.save(m);
            }
        }
        return "success";
    }

    @Override
    @Transactional
    public String update(SysUserMenuFunc bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysusermenufuncDao.updateRecordAll(bean); // update a record
                                                        // replace all, support
                                                        // null val
        // ret = sysusermenufuncDao.updateRecordCols(bean); // update a record
        // replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(SysUserMenuFunc bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = sysusermenufuncDao.deleteRecordByDomain(bean); // nonsupport
                                                                 // null val
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resStr = "fkerror"; // default foreign key error
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * SysUserMenuFunc() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(SysUserMenuFunc queryInfo, Boolean isExcludePKFlag)
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
        List<SysUserMenuFunc> beanList = sysusermenufuncDao.getSingleTableListByAndMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * SysUserMenuFunc() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(SysUserMenuFunc queryInfo, Boolean isExcludePKFlag)
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
        List<SysUserMenuFunc> beanList = sysusermenufuncDao.getSingleTableListByOrMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    @Override
    public List<MenuTreeBean> getUserMenuTree(Integer user_id)
    {
        List<Map<String, Object>> menuList = null;
        List<Map<String, Object>> funcList = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("user_id", user_id);
        menuList = sysusermenufuncDao_m.getSysUserMenuCheck(paramMap);
        funcList = sysusermenufuncDao_m.getSysUserFuncCheck(paramMap);
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

}
