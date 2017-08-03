package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.domain.SysFunction;
import com.zx.emanage.util.gen.domain.SysUser;
import com.zx.sframe.util.vo.UserBean;

public interface ILoginManageDao
{
    // 检查用户名密码，如果成功填充userbena对象
    public String checkLoginUser(UserBean user);

    // 根据指定角色ID得到其所有对应的菜单Id
    public List<Map<String, Object>> getUserMenuList(Integer user_id);

    public List<Map<String, Object>> getUserFunctionList(Integer user_id);

    public SysUser getUserById(Integer userId);

    public void saveCurrentUserNewPasswd(Integer userId, String newPasswd);

    // 修改默认登录密码
    public void changeDefaultPasswd(String userCode, String newPasswd);

    public List<Map<String, Object>> getAllMenu();

    public List<SysFunction> getAllFunc();

    public Long getCodeNo(String cUS_CODE);

    public Long getRepeatCodeNo(String cUS_CRE_CODE);
}