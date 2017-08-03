package com.zx.sframe.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.zx.emanage.sysmanage.service.ILoginManageService;
import com.zx.platform.syscontext.PlatformGlobalVar;

public class GlobalVal
{
    private static Logger log = LoggerFactory.getLogger(GlobalVal.class);

    public static ApplicationContext ctx;

    public static final String USER_SESSION = "userSession";

    public static final String ALL_DEFAULT_VAL = "0";

    public static final String ADMIN_NAME = PlatformGlobalVar.SYS_PROPERTIES.get("adminName");

    public static final String ADMIN_PASS_WD = PlatformGlobalVar.SYS_PROPERTIES.get("adminPassWd");

    // 是否需要在接口平台登录
    public static final String IS_P_LOGIN = PlatformGlobalVar.SYS_PROPERTIES.get("is_p_login");

    public static final String EKP_LOGIN_URL = PlatformGlobalVar.SYS_PROPERTIES.get("ekp_login_url");

    public static final String SERVER_WMS_URL = PlatformGlobalVar.SYS_PROPERTIES.get("server_wms_url");

    public static final String SERVER_UP_URL = PlatformGlobalVar.SYS_PROPERTIES.get("server_up_url");

    public static final String SERVER_UPLOAD_URL = PlatformGlobalVar.SYS_PROPERTIES.get("server_upload_url");

    // 用户登录后接口平台返回的用户信息
    public static final String PERSONNEL_ENCRYPTIONID = "encryptionId";

    /**
     * 最高级菜单的定义
     */
    public final static String UNIT_LEVEL = "1";

    public final static String P_DEPT_ID = "0";

    private static List<com.zx.emanage.util.gen.entity.SysFunction> funcList = new ArrayList<com.zx.emanage.util.gen.entity.SysFunction>();

    public static synchronized void init()
    {
        try
        {
            ctx = PlatformGlobalVar.APPLICATION_CONTEXT;
            ILoginManageService loginService = (ILoginManageService) ctx.getBean("loginmanageService");
            setFuncList(loginService.getAllFunc());
        }
        catch (Exception e)
        {

            log.error("==Application init ERROR!!==" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<com.zx.emanage.util.gen.entity.SysFunction> getFuncList()
    {
        return funcList;
    }

    public static void setFuncList(List<com.zx.emanage.util.gen.entity.SysFunction> funcList)
    {
        GlobalVal.funcList = funcList;
    }

}
