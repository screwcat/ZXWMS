package com.zx.emanage.sysmanage.service;

import java.util.List;
import java.util.Map;

import com.zx.platform.syscontext.vo.MenuBean;
import com.zx.sframe.util.vo.UserBean;

public interface ILoginManageService
{
    // 检测以及得到session中的用户对象，填充其内的值
    public String checkAndGetUser(UserBean user);

    // 得到登录用户所属角色的菜单
    public List<MenuBean> getUserMenu(Integer roleId, Integer p_menu_id);

    // 保存当前登录用户密码
    public void saveCurrentUserNewPasswd(Integer userId, String newPasswd);

    // 修改默认登录密码
    public void changeDefaultPasswd(String userCode, String newPasswd);

    // 根据角色ID设置所有功能点
    public void setFuncName(UserBean user);

    // 得到系统全部菜单，用于构建全局菜单树
    public List<Map<String, Object>> getAllMenu();

    // 得到系统全部功能，用于构建全局菜单树
    public List<com.zx.emanage.util.gen.entity.SysFunction> getAllFunc();

    // 得到主菜单
    public List<Map<String, Object>> getMenus(UserBean user);

    public UserBean checkAndGetUser(String encryptionId);

    public String getUserRole(UserBean user);

    /**
     * 获取用户角色信息
     * 
     * @param user
     * @return
     */
    public Map<String, Object> checkUserRole(UserBean user);

    /**
     * @Title: checkPadUser
     * @Description: Pad登陆需要调用的接口
     * @param user
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月22日 下午12:48:01
     * history:
     * 1、2017年2月22日 Administrator 创建方法
    */
    String checkPadLoginUser(UserBean user);
}