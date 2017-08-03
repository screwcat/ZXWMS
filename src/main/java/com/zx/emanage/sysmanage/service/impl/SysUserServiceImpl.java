package com.zx.emanage.sysmanage.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.util.MD5Util;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.sysmanage.persist.FlowActIdUserDao;
import com.zx.emanage.sysmanage.persist.ISysUserDao;
import com.zx.emanage.sysmanage.persist.ISysUserMenuFuncDao;
import com.zx.emanage.sysmanage.persist.ISysUserRoleDao;
import com.zx.emanage.sysmanage.persist.SysUserDao;
import com.zx.emanage.sysmanage.persist.SysUserMenuFuncDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.sysmanage.persist.impl.SysUserMenuFuncDaoImpl;
import com.zx.emanage.sysmanage.service.ISysUserService;
import com.zx.emanage.sysmanage.vo.SysUserSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysUser;
import com.zx.emanage.util.gen.domain.SysUserMenuFunc;
import com.zx.emanage.util.gen.domain.SysUserRole;
import com.zx.emanage.util.gen.entity.ActIdUser;
import com.zx.emanage.util.gen.vo.SysUserVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("sysuserService")
public class SysUserServiceImpl implements ISysUserService
{
    private static Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private ISysUserDao sysuserDao;

    @Autowired
    private ISysUserRoleDao sysuserroleDao;

    @Autowired
    private ISysUserMenuFuncDao sysusermenufuncDao;

    @Autowired
    private SysUserRoleDao sysuserroleDao_m;

    @Autowired
    private SysUserMenuFuncDao sysusermenufuncDao_m;

    @Autowired
    private SysUserDao sysuserDao_m;

    @Autowired
    private FlowActIdUserDao flowActIdUserDao;

    @Override
    public List<Map<String, Object>> getComboxList(SysUserSearchBeanVO queryInfo)
    {
        return sysuserDao.getListWithoutPaging(queryInfo);
    }

    @Override
    public Map<String, Object> getListWithoutPaging(SysUserSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = sysuserDao_m.search(paramMap); // mybatis查询列表
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        // resMap.put("Rows", sysuserDao.getListWithoutPaging(queryInfo));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(SysUserSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getUser_code()))
        {
            paramMap.put("user_code", "%" + queryInfo.getUser_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getUser_realname()))
        {
            paramMap.put("user_realname", "%" + queryInfo.getUser_realname() + "%");
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = sysuserDao_m.search2(paramMap); // mybatis查询列表
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       sysuserDao_m.findCount2(paramMap),
                                                                                       list);// mybatis构建前台需要数据结构
        // GridDataBean bean = new GridDataBean(queryInfo.getPage(),
        // sysuserDao.getListCountNum(queryInfo),
        // sysuserDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public com.zx.emanage.util.gen.entity.SysUser getInfoByPK(Integer id)
    {
        // com.zx.emanage.util.gen.entity.SysUser user =
        // sysuserDao_m.get(1);//mybatis根据主键获取数据
        return sysuserDao_m.get(id);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.gold.emanage.sysmanage.service.ISysUserService#save(com.gold.emanage
     * .util.gen.domain.SysUser, com.gold.sframe.util.vo.UserBean)
     */
    @Override
    @Transactional
    public String save(com.zx.emanage.util.gen.entity.SysUser bean, UserBean user)
    {
        String resStr = "success";
        Integer user_id = bean.getId();
        SysUser queryInfo = new SysUser();
        queryInfo.setUser_code(bean.getUser_code());
        String result = this.checkParamsRepeatByAndMethod(queryInfo, user_id == null ? false : true);
        if (result.equals("repeat"))
        {
            return "repeat";
        }
        int ret = 0;
        String userName = user.getRealname();
        bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        if (user_id != null)
        {
            bean.setLast_update_user(userName);
            // 删除用户角色关联表
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("user_id", user_id);
            sysuserroleDao_m.deleteByUserRoleMap(paramMap);
            sysusermenufuncDao_m.deleteBySysUserMenuFuncMap(paramMap);
            // 删除用户权限关联表
            ret = sysuserDao_m.updateRecordCols(bean);
            ActIdUser actIdUser = new ActIdUser();
            actIdUser.setFirst_(bean.getUser_realname());
            actIdUser.setId_("" + user_id);
            flowActIdUserDao.update(actIdUser);
        }
        else
        {
            bean.setUser_passwd(MD5Util.get32BitMd5EncString("123"));
            bean.setCreate_user(userName);
            com.zx.emanage.util.gen.entity.SysUser user2 = new com.zx.emanage.util.gen.entity.SysUser();
            user2.setUser_code(bean.getUser_code());
            user2.setUser_passwd(MD5Util.get32BitMd5EncString("123"));
            user2.setUser_realname(bean.getUser_realname());
            user2.setCreate_timestamp(new Timestamp(new Date().getTime()));
            user2.setLast_update_timestamp(new Timestamp(new Date().getTime()));
            ret = sysuserDao_m.save(user2);// mybatis保存,并返回主键值,ret为返回的受影响数据条数，主键值已经在user2中
            // ret = sysuserDao.addNewRecord(bean);
            ActIdUser actIdUser = new ActIdUser();
            actIdUser.setId_("" + user2.getId());
            actIdUser.setRev_(1);
            actIdUser.setFirst_(user2.getUser_realname());
            flowActIdUserDao.save(actIdUser);
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(SysUser bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysuserDao.updateRecordAll(bean); // update a record replace all,
                                                // support null val
        // ret = sysuserDao.updateRecordCols(bean); // update a record replace
        // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(Integer id)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            // sysuserDao_m.delete(new Integer[]{id}); // nonsupport null val
            sysuserDao_m.setEnableFlagFalse(id);
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
     * SysUser() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(SysUser queryInfo, Boolean isExcludePKFlag)
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
        List<SysUser> beanList = sysuserDao.getSingleTableListByAndMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * SysUser() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(SysUser queryInfo, Boolean isExcludePKFlag)
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
        List<SysUser> beanList = sysuserDao.getSingleTableListByOrMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }
}
