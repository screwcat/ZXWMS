package com.zx.emanage.sysmanage.vo;

import com.zx.platform.syscontext.vo.MenuBean;

public class EMenuBean extends MenuBean
{

    private static final long serialVersionUID = 1L;

    private String menuArrange;// 是否为纵向菜单 0：是 1：否

    private boolean ischecked;

    public EMenuBean(String text, String url)
    {
        super(text, url);
    }

    public EMenuBean()
    {
    }

    public String getMenuArrange()
    {
        return menuArrange;
    }

    public void setMenuArrange(String menuArrange)
    {
        this.menuArrange = menuArrange;
    }

    public boolean isIschecked()
    {
        return ischecked;
    }

    public void setIschecked(boolean ischecked)
    {
        this.ischecked = ischecked;
    }
}
