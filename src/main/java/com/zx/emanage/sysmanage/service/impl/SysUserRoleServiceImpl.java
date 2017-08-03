package com.zx.emanage.sysmanage.service.impl;

/**
 * 版权所有 ：版权所有(C) 2014，卓信财富
 * 文件名称 : SysUserRoleServiceImpl.Java
 * 系统名称 ：WMS
 * 模块名称 ：人员角色管理
 * 完成日期 ：
 * 作    者    ：
 * 内容摘要 ：
 * 
 * 文件调用 ：
 * 修改记录 ：01
 * 修改时间 ：20141229-12:29
 * 修 改 人   ：hancd
 * 修改内容 ：解决:新创建人员和角色关联表中人员角色和流程角色互相冲突的问题
 * 关联BUG ：
 * 修改方法：创建一个函数来动态获取系统中的在固定范围内的主键值
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.sysmanage.persist.FlowActIdMembershipDao;
import com.zx.emanage.sysmanage.persist.ISysUserRoleDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.sysmanage.service.ISysUserRoleService;
import com.zx.emanage.sysmanage.vo.SysSelectMeun;
import com.zx.emanage.sysmanage.vo.SysUserRoleSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysUserRole;
import com.zx.emanage.util.gen.entity.ActIdMembership;
import com.zx.emanage.util.gen.entity.SysMenu;
import com.zx.emanage.util.gen.entity.SysRole;
import com.zx.emanage.util.gen.vo.SysUserRoleVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("sysuserroleService")
public class SysUserRoleServiceImpl implements ISysUserRoleService
{
    private static Logger log = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

    @Autowired
    private ISysUserRoleDao sysuserroleDao;

    @Autowired
    private SysUserRoleDao sysuserroleDao_m;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private FlowActIdMembershipDao flowActIdMembershipDao;

    @Override
    public List<Map<String, Object>> getComboxList(SysUserRoleSearchBeanVO queryInfo)
    {
        return sysuserroleDao.getListWithoutPaging(queryInfo);
    }

    @Override
    public List<Map<String, Object>> getListWithoutPaging(SysUserRoleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("user_id", queryInfo.getUser_id());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return sysuserroleDao_m.search(paramMap);
    }

    @Override
    public Map<String, Object> getListWithPaging(SysUserRoleSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), sysuserroleDao.getListCountNum(queryInfo),
                                             sysuserroleDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public SysUserRoleVO getInfoByPK(Integer id)
    {
        return sysuserroleDao.getInfoByPK(id);
    }

    @Override
    @Transactional
    public String save(String roleArr, UserBean user, Integer user_id)
    {
        String resStr = "success";
        int ret = 0;
        // first clean
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("user_id", user_id);
        sysuserroleDao_m.deleteByUserRoleMap(paramMap);
        flowActIdMembershipDao.delete(new Integer[] { user_id });
        if (StringUtil.isNotBlank(roleArr))
        {
            String[] roleList = roleArr.split(",");
            List<com.zx.emanage.util.gen.entity.SysUserRole> list = new ArrayList<com.zx.emanage.util.gen.entity.SysUserRole>();
            for (String roleId : roleList)
            {
                com.zx.emanage.util.gen.entity.SysUserRole role = new com.zx.emanage.util.gen.entity.SysUserRole();
                role.setUser_id(user_id);
                role.setRole_id(Integer.valueOf(roleId));
                list.add(role);
            }
            if (list.size() > 0)
            {
                for (com.zx.emanage.util.gen.entity.SysUserRole s : list)
                {
                    SysRole sysRole = sysRoleDao.get(s.getRole_id());
                    if ("1".equals(sysRole.getIs_workflow()))
                    {
                        ActIdMembership m = new ActIdMembership();
                        m.setUser_id_("" + user_id);
                        m.setGroup_id_(sysRole.getWork_flow_id());
                        flowActIdMembershipDao.save(m);
                        // /修改记录：01 判断如果此角色ID相对应的是流程角色，需要把他存储到人员角色关联表的1~10000之前
                        s.setId(sysuserroleDao_m.getNextId(10000));
                        sysuserroleDao_m.saveInfo(s);
                    }
                    else
                    {
                        sysuserroleDao_m.save(s);
                    }
                }
            }
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(SysUserRole bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysuserroleDao.updateRecordAll(bean); // update a record replace
                                                    // all, support null val
        // ret = sysuserroleDao.updateRecordCols(bean); // update a record
        // replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(SysUserRole bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = sysuserroleDao.deleteRecordByDomain(bean); // nonsupport null
                                                             // val
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
     * SysUserRole() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(SysUserRole queryInfo, Boolean isExcludePKFlag)
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
        List<SysUserRole> beanList = sysuserroleDao.getSingleTableListByAndMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * SysUserRole() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(SysUserRole queryInfo, Boolean isExcludePKFlag)
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
        List<SysUserRole> beanList = sysuserroleDao.getSingleTableListByOrMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * 用于首页 跳转页面 如 点击客户管理跳转到 新增客户页面等
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public SysMenu getUserMenu(SysSelectMeun meun)
    {
        SysMenu smeun = sysuserroleDao_m.selectByUserId(meun);
        return smeun;
    }
}
