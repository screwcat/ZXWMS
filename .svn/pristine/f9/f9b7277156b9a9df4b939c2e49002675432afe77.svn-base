package com.zx.emanage.sysmanage.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.zx.emanage.sysmanage.service.ILoginManageService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.util.MD5Util;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.MenuBean;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.SysIPUtil;
import com.zx.sframe.util.vo.UserBean;

@Controller
public class LoginManageController
{
    @Autowired
    private ILoginManageService loginmanageService;

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(UserBean user, HttpServletRequest request)
    {
        Map<String, Object> res = new HashMap<String, Object>();
        String error = loginmanageService.checkAndGetUser(user);

        if ("".equals(error))
        {
            // 将登录用户相关信息的VO保存到session中
            HttpSession session = request.getSession();
            session.setAttribute(GlobalVal.USER_SESSION, user);
            // 返回SessionID给前端
            res.put("success", session.getId());
            SysTools.saveLog(request, "用户登录");
        }
        else
        {
            // 登录失败，将错误代号返回给前端。100--用户名密码错 200--没有对应角色
            res.put("error", error);
        }
        return res;
    }

    @RequestMapping(value = "/ekplogin.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ekpLogin(String encryptionId, HttpServletRequest request)
    {
        Map<String, Object> res = new HashMap<String, Object>();
        UserBean user = loginmanageService.checkAndGetUser(encryptionId);
        HttpSession session = request.getSession();
        session.setAttribute(GlobalVal.USER_SESSION, user);
        // 返回SessionID给前端
        res.put("success", session.getId());
        SysTools.saveLog(request, "用户登录");
        return res;
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(GlobalVal.USER_SESSION) != null)
        {
            request.getSession().removeAttribute(GlobalVal.USER_SESSION);
        }
        return "ok";
    }

    @RequestMapping(value = "/issessiontimeout.do", method = RequestMethod.GET)
    @ResponseBody
    public String isSessionTimeout(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(GlobalVal.USER_SESSION) == null)
        {
            return "ok";
        }
        return "exist";
    }

    /**
     * 得到当前用户信息
     */
    @RequestMapping(value = "/sysmanage/getcurrentuserinfo.do", method = RequestMethod.GET)
    @ResponseBody
    public UserBean getCurrentUserInfo(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        return (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
    }

    /**
     * 修改当前用户的密码信息
     */
    @RequestMapping(value = "/sysmanage/savecurrentusernewpasswd.do", method = RequestMethod.POST)
    @ResponseBody
    public String saveCurrentUserNewPasswd(String userPasswd, String newPasswd, HttpServletRequest request)
    {
        userPasswd = MD5Util.get32BitMd5EncString(userPasswd);
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        if (!userPasswd.trim().equals(user.getUserPasswd()))
        {
            return "100103";
        }

        loginmanageService.saveCurrentUserNewPasswd(user.getUserId(), newPasswd);
        user.setUserPasswd(MD5Util.get32BitMd5EncString(newPasswd));

        SysTools.saveLog(request, "修改用户密码");
        return "100004";
    }

    /**
     * 修改用户的默认密码
     */
    @RequestMapping(value = "/changedefaultpasswd.do", method = RequestMethod.POST)
    @ResponseBody
    public String changeDefaultPasswd(String userCode, String newPasswd, HttpServletRequest request)
    {
        loginmanageService.changeDefaultPasswd(userCode, newPasswd);
        SysTools.saveLog(request, "修改用户初始密码");
        return "100106";
    }

    /**
     * 返回当前用户的菜单对象
     */
    @RequestMapping(value = "/sysmanage/getusermenu.do", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuBean> getUserMenu(HttpServletRequest request, Integer p_menu_id)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return loginmanageService.getUserMenu(user.getUserId(), p_menu_id);
    }

    /**
     * 判断前台传入的功能点的访问权限， 返回值类似于1#0#1，1代表可访问，0不可
     */
    @RequestMapping(value = "/sysmanage/gettoolbar.do", method = RequestMethod.POST)
    @ResponseBody
    public String getToolbar(String urlStr, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);

        StringBuilder sb = new StringBuilder();

        String[] urlArr = urlStr.split("#");
        for (String url : urlArr)
        {
            if (StringUtil.isNotBlank(url))
            {
                sb.append("1");// 目前对功能按钮不做控制
                sb.append("#");
            }
            /*
             * for (String url : urlArr) { if(StringUtil.isNotBlank(url)){
             * if(user.hasFuncName(url)){ sb.append("1"); }else{ sb.append("0");
             * } sb.append("#"); }
             */
        }

        String resStr = sb.toString();
        if (resStr.length() > 0)
        {
            resStr = resStr.substring(0, resStr.length() - 1);
        }
        return resStr;
    }

    /**
     * 此处是进行权限控制的一个桩接口，如果访问没有通过filter的权限检验那么由filter返回错误信息，如果通过检验则由此处返回通过检验（与业务无关
     * ，纯框架级）
     */
    @RequestMapping(value = "/sysmanage/authorizedStub.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> authorizedStub(HttpServletRequest request, String cookieval_name, String cookieval_passwd)
    {
        if (StringUtil.isNotBlank(cookieval_name) && StringUtil.isNotBlank(cookieval_passwd))
        {
            UserBean user = new UserBean();
            user.setUserCode(cookieval_name);
            user.setUserPasswd(cookieval_passwd);
            return this.login(user, request);
        }
        else
        {
            HttpSession session = request.getSession();
            Map<String, Object> map = new HashMap<String, Object>();
            if (session == null || session.getAttribute(GlobalVal.USER_SESSION) == null)
            {
                map.put("error", "100092");
                return map;// session过期
            }
            return map;
        }

    }

    /**
     * 获取登录用户角色id，并判断是否显示贷款查询中的修改按钮
     */
    @RequestMapping(value = "/sysmanage/searchCreditButton.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> searchCreditButton(HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        String buttonFlag = loginmanageService.getUserRole(user);
        map.put("buttonFlag", buttonFlag);
        map.put("userId", user.getUserId());
        return map;
    }

    /**
     * 返回当前用户的菜单对象
     */
    @RequestMapping(value = "/sysmanage/getmenus.do", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getMenus(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return loginmanageService.getMenus(user);
    }

    /**
     * 获取登录用户角色id，并判断是否显示理财查询中的修改按钮
     */
    @RequestMapping(value = "/sysmanage/searchFinancialButton.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> searchFinancialButton(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return loginmanageService.checkUserRole(user);
    }
    
    /**
     * 过滤不能查看到IP限制
     */
    @RequestMapping(value = "/getIP.do",  method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getIP(HttpServletRequest request)
    {
       return SysIPUtil.JudgeIP(request);
    }
    
    /**
     *  获取PTP推送理财产品信息
     */
    @RequestMapping(value = "/getPTPInfo.do", method = { RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getPTPInfo(HttpServletRequest request,HttpServletResponse response)
    {
         String url = PlatformGlobalVar.SYS_PROPERTIES.get("ptpUrl") + "/index/xinErTipList.dos";
         RestTemplate restTemplate = new RestTemplate();
         Map<String, Object> map = restTemplate.getForObject(url, Map.class);
         map.put("url", PlatformGlobalVar.SYS_PROPERTIES.get("ptpUrl"));
    	 return map;
    }

    /**
     * @Title: padLogin
     * @Description: pad登陆接口
     * @param user
     * @param request
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月22日 下午1:03:33
     * history:
     * 1、2017年2月22日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/loginPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> padLogin(UserBean user, String device_num, HttpServletRequest request)
    {
        Map<String, Object> res = new HashMap<String, Object>();

        String result = loginmanageService.checkPadLoginUser(user);

        if ("success".equals(result))
        {
            sendMessageCrm(user.getUserCode(), device_num, "IOA_OUT_GETLoginPadInformation");

            HttpSession session = request.getSession();
            session.setAttribute(GlobalVal.USER_SESSION, user);
            res.put("ret_code", "000");
            res.put("ret_msg", "登录成功");
            Map<String,Object> map = new HashMap<>();
            map.put("region_province_dict_id", user.getRegion_province_dict_id());
            map.put("region_city_dict_id", user.getRegion_city_dict_id());
            res.put("ret_data", map);
        }
        else
        {
            res.put("ret_code", "001");
            res.put("ret_msg", result);
        }
        return res;
    }

    /**
     * 
     * @Title: loginOutPad
     * @Description: pad退出登陆接口
     * @param device_num
     * @param request
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月22日 下午3:44:32
     * history:
     * 1、2017年2月22日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/loginOutPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> loginOutPad(String device_num, HttpServletRequest request)
    {
        Map<String, Object> res = new HashMap<String, Object>();

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(GlobalVal.USER_SESSION) != null)
        {
            sendMessageCrm(((UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION)).getUserCode(), device_num, "IOA_OUT_GETQuitPadInformation");
            request.getSession().removeAttribute(GlobalVal.USER_SESSION);
        }
        res.put("ret_code", "000");
        res.put("ret_msg", "退出成功");

        return res;
    }

    /**
     * 
     * @Title: sendMessageCrm
     * @Description: pad登陆 登出向crm推送消息
     * @param personnel_shortCod
     * @param device_num
     * @param interface_num
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月23日 下午4:02:48
     * history:
     * 1、2017年2月23日 Administrator 创建方法
     */
    private String sendMessageCrm(String personnel_shortCod, String device_num, String interface_num)
    {
        String result = "success";
        // 调用crm接口
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("interface_num", interface_num));
        nvps.add(new BasicNameValuePair("sys_num", "WMS"));
        nvps.add(new BasicNameValuePair("user_id", "0"));

        nvps.add(new BasicNameValuePair("personnel_shortCode", personnel_shortCod));
        nvps.add(new BasicNameValuePair("device_num", device_num));

        try
        {
            HttpPost httpPost = new HttpPost(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(httpPost);
            /*
                        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        String result_str = rd.readLine();
                        JSONObject obj = JSONObject.parseObject(result_str);
                        if ("000".equals(obj.get("ret_code").toString()))
                        {
                            result = "success";
                        }
                        else
                        {
                            result = "error";
                        }*/

            response.close();
            httpclient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
