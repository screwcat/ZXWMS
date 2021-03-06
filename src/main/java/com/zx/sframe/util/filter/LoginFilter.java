package com.zx.sframe.util.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.sframe.util.GlobalVal;

public class LoginFilter implements Filter
{
    private static Logger log = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig arg0) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
                                                                                     ServletException
    {
        // 判断是否登录，访问系统.do必须登录后才能访问
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String url = request.getRequestURI();
        log.debug("REST CALL>>" + url);

        HttpSession session = request.getSession();

        // 如果是登录则不检查 login.do,也不检查修改默认密码changedefaultpasswd.do,
        // 也不检查验证码loginclearkaptcha.do
        // authorizedStub.do的时候会根据cookie设置情况处理自动登录问题，因此此处也跳过
        // 然后判断session，没有session则出错
        // 存在session则判断权限按钮
        // 2015-8-6日 对getIP.do请求不做拦截
        Map<String, String> map = new HashMap<String, String>();
        if (url.endsWith("login.do") || url.endsWith("ekplogin.do") || url.endsWith("changedefaultpasswd.do")
            || url.endsWith("loginclearkaptcha.do") || url.endsWith("authorizedStub.do")
 || url.endsWith("issessiontimeout.do") || url.endsWith("getIP.do") || url.contains("phone") || url.contains("Moa") || url.contains("MOA")
            || url.contains("Up")
            || url.contains("Isread")
            || url.endsWith(".dos")
            || url.endsWith(".pdf")
 || url.contains("send") || url.contains("Pad.do") || url.contains("Biz"))
        {
            chain.doFilter(req, resp);
        }
        else if (session != null && session.getAttribute(GlobalVal.USER_SESSION) != null)
        {
            if ("-1".equals(request.getParameter("_filterParms")))
            {
                // return empty array for ligerui grid
                Map<String, Object> tempMap = new HashMap<String, Object>();
                tempMap.put("Rows", new ArrayList<Map<String, Object>>());
                response.getWriter().print(new Gson().toJson(tempMap));
            }
            else
            {
                chain.doFilter(req, resp);
                // UserBean user = (UserBean)
                // session.getAttribute(GlobalVal.USER_SESSION);
                // 如果全部的功能点中没有该功能点那么直接跳过
                /*
                 * if(!hasTheFunction(getUrlStr(url))){ chain.doFilter(req,
                 * resp); }else{ if (user.hasFuncName(getUrlStr(url))) {
                 * chain.doFilter(req, resp); } else { map.put("error",
                 * "100091"); response.getWriter().print(new
                 * Gson().toJson(map)); } }
                 */
            }
        }
        else
        {
            // logout or session timeout or unauthority user
            map.put("error", "100092");
            response.getWriter().print(new Gson().toJson(map));
        }
    }

    @Override
    public void destroy()
    {
    }

    // 得到除了qasp外其他部分的url
    private String getUrlStr(String url)
    {
        String[] arrUrl = url.split("/");
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String string : arrUrl)
        {
            if (StringUtil.isNotBlank(string))
            {
                index++;
            }
            if (index > 1)
            {
                sb.append("/").append(string);
            }
        }
        return sb.toString();
    }

    // 判断该功能点是否在控制范围内
    private boolean hasTheFunction(String funcUrl)
    {
        for (com.zx.emanage.util.gen.entity.SysFunction funcBean : GlobalVal.getFuncList())
        {
            if (funcUrl.equals(funcBean.getFunc_method()))
            {
                return true;
            }
        }
        return false;
    }
}
