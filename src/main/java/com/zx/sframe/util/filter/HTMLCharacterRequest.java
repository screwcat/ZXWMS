package com.zx.sframe.util.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HTMLCharacterRequest extends HttpServletRequestWrapper
{

    public HTMLCharacterRequest(HttpServletRequest request)
    {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name)
    {
        String[] val = super.getParameterValues(name);
        if (val != null)
        {
            for (int i = 0; i < val.length; i++)
            {
                val[i] = filter(val[i]);
            }
        }
        return val;
    }

    @Override
    public String getParameter(String name)
    {
        return filter(super.getParameter(name));
    }

    private String filter(String parameter)
    {
        if (null == parameter)
            return null;
        return parameter.replaceAll(">", "＞").replaceAll("<", "＜").replaceAll("%", "％").replaceAll("&", "＆");
    }

}
