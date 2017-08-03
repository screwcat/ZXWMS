package com.zx.emanage.sysmanage.persist.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.MD5Util;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.sysmanage.persist.ILoginManageDao;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.SysDept;
import com.zx.emanage.util.gen.domain.SysFunction;
import com.zx.emanage.util.gen.domain.SysUser;
import com.zx.emanage.util.gen.vo.SysDeptVO;
import com.zx.sframe.util.vo.UserBean;

@Repository("loginmanageDao")
public class LoginManageDaoImpl extends AbstractSimpleDao implements ILoginManageDao
{
    private static Logger log = LoggerFactory.getLogger(LoginManageDaoImpl.class);

    @Override
    public String checkLoginUser(UserBean user)
    {
        if (StringUtil.isBlank(user.getUserPasswd()))
        {
            user.setUserPasswd(null);
        }
        else
        {
            user.setUserPasswd(MD5Util.get32BitMd5EncString(user.getUserPasswd()));
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("user_code", user.getUserCode());
        paramMap.put("user_passwd", user.getUserPasswd());

        List<Map<String, Object>> resList = this.queryForListByTemplate(SqlString.SYSMANAGE_LOGIN_SYS_USER_CHECKUSER,
                                                                        paramMap);

        // 存在该用户，但是密码为null
        if (resList.size() > 0 && user.getUserPasswd() == null)
        {
            return "100109";
        }

        // 不存在对应的用户名密码
        if (resList.size() == 0)
        {
            return "100100";
        }

        Map<String, Object> row = resList.get(0);
        user.setRealname((String) row.get("user_realname"));
        user.setUserId((Integer) row.get("id"));

        if (row.get("dept_id") != null)
        {
            Integer deptId = (Integer) row.get("dept_id");
            SysDeptVO deptBean = SysDept.getRecordVOByPK(this, deptId);
            // user.setDeptId(deptId);
            user.setDeptSimpleName(deptBean.getDept_name());
        }

        return "";
    }

    @Override
    public List<Map<String, Object>> getUserFunctionList(Integer user_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (user_id != null)
        {
            paramMap.put("user_id", user_id);
        }
        List<Map<String, Object>> resList = this.queryForListByTemplate(SqlString.SYSMANAGE_LOGIN_SYS_FUNC_FUNC_BY_USER,
                                                                        paramMap);
        return resList;
    }

    @Override
    public List<Map<String, Object>> getUserMenuList(Integer user_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (user_id != null)
        {
            paramMap.put("user_id", user_id);
        }
        List<Map<String, Object>> resList = this.queryForListByTemplate(SqlString.SYSMANAGE_LOGIN_SYS_MENU_MENU_BY_USER,
                                                                        paramMap);
        return resList;
    }

    @Override
    public void saveCurrentUserNewPasswd(Integer userId, String newPasswd)
    {
        SysUser user = SysUser.getRecordDomainByPK(this, userId);
        user.setUser_passwd(MD5Util.get32BitMd5EncString(newPasswd));
        user.updateRecordAll(this);
    }

    @Override
    public SysUser getUserById(Integer userId)
    {
        return SysUser.getRecordDomainByPK(this, userId);
    }

    @Override
    public void changeDefaultPasswd(String userCode, String newPasswd)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("user_code", userCode);
        paramMap.put("user_passwd", MD5Util.get32BitMd5EncString(newPasswd));

        this.updateByTemplate(SqlString.SYSMANAGE_LOGIN_SYS_USER_CHANGEDEFAULTPASSWD, paramMap);
    }

    @Override
    public List<Map<String, Object>> getAllMenu()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return this.queryForListByTemplate(SqlString.SYSMANAGE_LOGIN_SYS_MENU_ALLMENU, paramMap);
    }

    @Override
    public List<SysFunction> getAllFunc()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return this.qryObjList(SqlString.SYSMANAGE_LOGIN_SYS_FUNC_ALLFUNC, paramMap, SysFunction.class);
    }

    @Override
    public Long getCodeNo(String code)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", code);
        List<Map<String, Object>> list = this.queryForListByTemplate(SqlString.SYSMANAGE_SYSPUB_WMS_SYS_SEQUENCE_SELECT,
                                                                     paramMap);
        return (Long) list.get(0).get("code");
    }

    @Override
    public Long getRepeatCodeNo(String code)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", code);
        List<Map<String, Object>> list = this.queryForListByTemplate(SqlString.SYSMANAGE_SYSPUB_WMS_SYS_SEQUENCE_REPEAT_SELECT,
                                                                     paramMap);
        return (Long) list.get(0).get("code");
    }
}