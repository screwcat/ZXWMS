package com.zx.emanage.sysmanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.zx.emanage.sysmanage.persist.ILoginManageDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysFunctionDao;
import com.zx.emanage.sysmanage.persist.SysMenuDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.sysmanage.persist.SysUserDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.sysmanage.service.ILoginManageService;
import com.zx.emanage.sysmanage.util.UserCityUtil;
import com.zx.emanage.sysmanage.vo.EMenuBean;
import com.zx.emanage.sysmanage.vo.MenuTreeBean;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.SysRole;
import com.zx.emanage.util.gen.entity.SysUserRole;
import com.zx.platform.syscontext.util.MD5Util;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.MenuBean;
import com.zx.platform.syscontext.vo.TreeBean;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

@Service("loginmanageService")
public class LoginManageServiceImpl implements ILoginManageService
{
    private static Logger log = LoggerFactory.getLogger(LoginManageServiceImpl.class);

    @Autowired
    private ILoginManageDao loginmanageDao;

    @Autowired
    private SysDeptDao sysdeptDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private PmPersonnelDao pmpersonnelDao;

    @Autowired
    private SysFunctionDao sysFunctionDao;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysUserRoleDao sysuserroleDao;

    @Autowired
    private SysRoleDao sysroleDao;

    @Autowired
    private SysRoleDao sysroleDao_m;

    /*
     * 检测登录用户名密码，并将该用户的功能点角色列表等填入项目 return: 100100--用户名密码错 100101--没有对应角色
     * 100109--存在用户但是密码为null
     */
    @Override
    public String checkAndGetUser(UserBean user)
    {
        String error = "";
        if (GlobalVal.ADMIN_NAME.equals(user.getUserCode()))
        {
            String userPasswd = MD5Util.get32BitMd5EncString(user.getUserPasswd());
            if (!GlobalVal.ADMIN_PASS_WD.equals(userPasswd))
            {
                error = "100100";
            }
        }
        else
        {
            // 通过EKP登录
            if ("1".equals(GlobalVal.IS_P_LOGIN))
            {
                RestTemplate restTemplate = new RestTemplate();
                MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
                // 这3个参数是每个接口都要传的(ekp登录的时候不用传user_id)
                form.add("interface_num", "LG_OUT_001");// 接口编号
                form.add("sys_num", "WMS");// 系统编号
                form.add("user_id", "0");
                // 下边参数不同接口,参数不同
                form.add("userCode", user.getUserCode());// 用户名
                form.add("userPasswd", user.getUserPasswd());// 用户密码
                @SuppressWarnings("unchecked")
                Map<String, Object> map = restTemplate.postForObject(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple",
                                                                     form, Map.class);// 接口平台登录

                if ((Boolean) map.get("flag"))
                {
                    String encryptionId = (String) map.get(GlobalVal.PERSONNEL_ENCRYPTIONID);
                    PmPersonnel person = new PmPersonnel();
                    person.setPersonnel_encryptionid(encryptionId);
                    List<PmPersonnel> beanList = pmpersonnelDao.getListByEntity(person);
                    if (beanList != null && beanList.size() > 0)
                    {
                        person = beanList.get(0);
                    }
                    user.setUserId(person.getPersonnel_id());
                    user.setRealname(person.getPersonnel_name());
                    user.setDeptId(person.getPersonnel_deptid());
                    user.setDeptSimpleName(person.getPersonnel_deptname());
                    user.setUser_city(UserCityUtil.getUserCity(person.getPersonnel_regionnumber()));
                    user.setUser_province(UserCityUtil.getUserProv(person.getPersonnel_regionnumber()));
                    user.setPostName(person.getPersonnel_postname());
                    // 修改内容:给UserBean增加区域编号
                    user.setUser_regionNumber(person.getPersonnel_regionnumber());
                }
                else
                {
                    return (String) map.get("message");// 登录失败
                }
            }
            else
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

                List<Map<String, Object>> resList = sysUserDao.checkUser(paramMap);

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
                if (((String) row.get("user_code")).equals("gx"))
                {
                    user.setDeptId(19);// 理财一部
                }
                else if (((String) row.get("user_code")).equals("zfx"))
                {
                    user.setDeptId(19);// 理财一部
                    user.setUserId(67);
                }
                else if (((String) row.get("user_code")).equals("wy"))
                {
                    user.setDeptId(15);// 理财部
                    user.setUserId(68);
                }
                else if (((String) row.get("user_code")).equals("hs"))
                {
                    user.setDeptId(15);// 理财部
                    user.setUserId(69);
                }
            }

        }

        if (!"".equals(error))
        {
            return error;
        }
        // setFuncName(user);

        return "";
    }

    @Override
    public UserBean checkAndGetUser(String encryptionId)
    {

        PmPersonnel person = new PmPersonnel();
        person.setPersonnel_encryptionid(encryptionId);
        List<PmPersonnel> beanList = pmpersonnelDao.getListByEntity(person);
        if (beanList != null && beanList.size() > 0)
        {
            person = beanList.get(0);
        }
        UserBean user = new UserBean();
        user.setUserCode(person.getPersonnel_shortcode());
        user.setUserId(person.getPersonnel_id());
        user.setRealname(person.getPersonnel_name());
        user.setDeptId(person.getPersonnel_deptid());
        user.setDeptSimpleName(person.getPersonnel_deptname());
        user.setUser_city(UserCityUtil.getUserCity(person.getPersonnel_regionnumber()));
        user.setUser_province(UserCityUtil.getUserProv(person.getPersonnel_regionnumber()));
        user.setPostName(person.getPersonnel_postname());
        // 修改内容:给UserBean增加区域编号
        user.setUser_regionNumber(person.getPersonnel_regionnumber());
        return user;
    }

    public void setFuncName(UserBean user)
    {
        user.cleanFuncNameMap();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (user.getUserId() != null)
        {
            paramMap.put("user_id", user.getUserId());
        }
        List<Map<String, Object>> funcMethodList = sysFunctionDao.queryForList(paramMap);
        for (Map<String, Object> map : funcMethodList)
        {
            user.putFuncName((String) map.get("func_method"));
        }
    }

    /*
     * 从全局菜单中，根据用户所属角色包含的菜单id列表中裁剪出用户菜单
     */
    @Override
    public List<MenuBean> getUserMenu(Integer user_id, Integer p_menu_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (user_id != null)
        {
            paramMap.put("user_id", user_id);
        }
        List<Map<String, Object>> menuDataList = sysMenuDao.queryForList(paramMap);
        List<MenuBean> menuList = new ArrayList<MenuBean>();
        List<Map<String, Object>> topMenuList = SysUtil.getSamePropertyList(menuDataList, "menu_level",
                                                                            GlobalVal.UNIT_LEVEL);// 获取一级菜单
        // 递归菜单下的所有菜单和功能按钮
        int index = 1;
        if (topMenuList == null)
        {
            return menuList;
        }
        if (user_id == null && p_menu_id.compareTo(1) != 0)
        {
            return menuList;
        }
        for (Map<String, Object> map : topMenuList)
        {
            String menuId = "" + map.get("id");
            if (menuId.equals("" + p_menu_id))
            {
                String menu_name = (String) map.get("menu_name");
                String remark = (String) map.get("menu_remark");
                String menuArrange = (String) map.get("menu_arrange");
                EMenuBean bean = new EMenuBean(menu_name, "");
                bean.setId(menuId);
                bean.setIcon(index);
                bean.setRemark(remark);
                bean.setMenuArrange(menuArrange);
                index++;
                bean.setChildrenTree(getChildMenu(menuDataList, menuId));
                menuList.add(bean);
                break;
            }
        }
        return menuList;
    }

    private static List<TreeBean> getChildMenu(List<Map<String, Object>> menuList, String parentId)
    {
        List<TreeBean> treeList = new ArrayList<TreeBean>();
        for (Map<String, Object> map : menuList)
        {
            String pid = map.get("p_menu_id").toString();
            if (parentId.equals(pid))
            {
                String menuId = "" + map.get("id");
                String menu_name = (String) map.get("menu_name");
                String menuUrl = map.get("menu_url").toString();
                String remark = (String) map.get("menu_remark");
                String menuArrange = (String) map.get("menu_arrange");
                MenuTreeBean treeBean = new MenuTreeBean(menu_name, "");
                treeBean.setUrl(menuUrl);
                treeBean.setId(menuId);
                treeBean.setRemark(remark);
                treeBean.setMenuArrange(menuArrange);
                treeList.add(treeBean);
                treeBean.setChildren(getChildMenu(menuList, menuId));
            }
        }
        if (treeList.size() == 0)
        {
            return null;
        }
        return treeList;

    }

    @Override
    public void saveCurrentUserNewPasswd(Integer userId, String newPasswd)
    {
        loginmanageDao.saveCurrentUserNewPasswd(userId, newPasswd);
    }

    @Override
    public void changeDefaultPasswd(String userCode, String newPasswd)
    {
        loginmanageDao.changeDefaultPasswd(userCode, newPasswd);
    }

    @Override
    public List<Map<String, Object>> getAllMenu()
    {
        return loginmanageDao.getAllMenu();
    }

    @Override
    public List<com.zx.emanage.util.gen.entity.SysFunction> getAllFunc()
    {
        return sysFunctionDao.getAllFunc();
    }

    /*
     * 从全局菜单中，根据用户所属角色包含的菜单id列表中裁剪出用户菜单
     */
    @Override
    public List<Map<String, Object>> getMenus(UserBean user)
    {
        if (GlobalVal.ADMIN_NAME.equals(user.getUserCode()))
        {
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("url", "");
            map.put("menu_name", "系统管理");
            List<Map<String, Object>> list = new ArrayList<>();
            list.add(map);
            return list;
        }
        return sysMenuDao.getMenus(user.getUserId());
    }

    /**
     * 根据登录的用户id获取对应的角色信息
     **/
    public String getUserRole(UserBean user)
    {
        List<SysUserRole> sysUserRoleList = sysuserroleDao.getUserRole(user.getUserId());
        for (int i = 0; i < sysUserRoleList.size(); i++)
        {
            SysUserRole sysUserRole = sysUserRoleList.get(i);
            SysRole sysRole = sysroleDao.get(sysUserRole.getRole_id());
            if ("ywbmldy".equals(sysRole.getWork_flow_id()))
            {
                return "success";
            }
        }
        return "error";
    }

    @Override
    public Map<String, Object> checkUserRole(UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<SysUserRole> sysyuserroles = sysuserroleDao.getUserRole(user.getUserId());
        for (SysUserRole sysyuserrole : sysyuserroles) {
            // 判断角色是否为理财财务专员
            if (sysyuserrole.getRole_id() == 138)
            {
                paramMap.put("rolezy", "lccwzy");
            }
            // 判断是否为理财业务接待专员
            //修改时间：2015年12月14日
            //修改内容：【理财接待专员】改为【财务柜员】
            //修改人：焦德龙
            if (sysyuserrole.getRole_id() == 150)
            {
                paramMap.put("rolegy", "lcgy");
            }
            
        }
        paramMap.put("userId", user.getUserId());
        return paramMap;
    }

    @Override
    public String checkPadLoginUser(UserBean user)
    {
        String error = "success";

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.add("interface_num", "LG_OUT_001");// 接口编号
        form.add("sys_num", "WMS"); // 系统编号
        form.add("user_id", "0");
        // 下边参数不同接口,参数不同
        form.add("userCode", user.getUserCode());// 用户名
        form.add("userPasswd", user.getUserPasswd());// 用户密码
        @SuppressWarnings("unchecked")
        Map<String, Object> map = restTemplate.postForObject(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple", form, Map.class);// 接口平台登录

        if ((Boolean) map.get("flag"))
        {
            String encryptionId = (String) map.get(GlobalVal.PERSONNEL_ENCRYPTIONID);
            PmPersonnel person = new PmPersonnel();
            person.setPersonnel_encryptionid(encryptionId);
            List<PmPersonnel> beanList = pmpersonnelDao.getListByEntity(person);
            if (beanList != null && beanList.size() > 0)
            {
                person = beanList.get(0);
            }
            user.setUserId(person.getPersonnel_id());
            user.setRealname(person.getPersonnel_name());
            user.setDeptId(person.getPersonnel_deptid());
            user.setDeptSimpleName(person.getPersonnel_deptname());
            user.setUser_city(UserCityUtil.getUserCity(person.getPersonnel_regionnumber()));
            user.setUser_province(UserCityUtil.getUserProv(person.getPersonnel_regionnumber()));
            user.setPostName(person.getPersonnel_postname());
            // 修改内容:给UserBean增加区域编号
            user.setUser_regionNumber(person.getPersonnel_regionnumber());
            // 所属省id
            user.setRegion_city_dict_id(person.getRegion_city_dict_id());
            // 所属省id
            user.setRegion_province_dict_id(person.getRegion_province_dict_id());

        }
        else
        {
            return (String) map.get("message");// 登录失败
        }
        return error;
    }

}
