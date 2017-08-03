package com.zx.sframe.util.filter;

import java.io.IOException;
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
import com.zx.emanage.inve.service.IWmsInvePadPowerService;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

public class LoginPadFilter implements Filter
{
    private static Logger log = LoggerFactory.getLogger(LoginPadFilter.class);

    @Override
    public void init(FilterConfig arg0) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
                                                                                     ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String url = request.getRequestURI();

        HttpSession session = request.getSession();

        Map<String, String> map = new HashMap<String, String>();

        response.setContentType("text/html; charset=UTF-8");

        if (!url.contains("Pad.do") || url.endsWith("authorPad.do"))
        {
            chain.doFilter(req, resp);
        }else if(request.getParameter("device_num") == null){
            // 登陆未传设备序列号时 直接返回
            map.put("ret_code", "001");
            map.put("ret_msg", "序列号不能为空");
            response.getWriter().print(new Gson().toJson(map));
        }
        else if (url.endsWith("loginPad.do") || url.endsWith("loginOutPad.do"))
        {
            chain.doFilter(req, resp);
        }
        else if (session != null && session.getAttribute(GlobalVal.USER_SESSION) != null)
        {
            // 判断用户是否有使用pad权限
            IWmsInvePadPowerService wmsInvePadPowerService = (IWmsInvePadPowerService) PlatformGlobalVar.APPLICATION_CONTEXT.getBean("wmsinvepadpowerService");
            Boolean isAuthor = wmsInvePadPowerService.getWmsInvePadPowerAuthorByDeviceNum(request.getParameter("device_num"), (UserBean) session.getAttribute(GlobalVal.USER_SESSION));
            if (isAuthor)
            {
                chain.doFilter(req, resp);
            }
            else
            {
                map.put("ret_code", "003");
                map.put("ret_msg", "操作权限失效");
                response.getWriter().print(new Gson().toJson(map));
            }
        }
        else
        {
            map.put("ret_code", "998");
            map.put("ret_msg", "登录超时，请重新登录!");
            response.getWriter().print(new Gson().toJson(map));
        }
    }

    @Override
    public void destroy()
    {
    }
}
