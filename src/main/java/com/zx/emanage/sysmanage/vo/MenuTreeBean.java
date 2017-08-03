package com.zx.emanage.sysmanage.vo;

import com.zx.platform.syscontext.vo.TreeBean;

public class MenuTreeBean extends TreeBean
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private boolean ischecked;

    /**
     * 如果节点类型是菜单则该字段存储的是默认funId 如果节点类型是按钮则该字段存储的是所属menuId
     */
    private String refId;

    public String getMenuArrange()
    {
        return menuArrange;
    }

    private String menuArrange;

    public MenuTreeBean()
    {

    }

    public MenuTreeBean(String text, String url)
    {
        super(text, url);
    }

    public String getRefId()
    {
        return refId;
    }

    public void setRefId(String refId)
    {
        this.refId = refId;
    }

    public boolean isIschecked()
    {
        return ischecked;
    }

    public void setIschecked(boolean ischecked)
    {
        this.ischecked = ischecked;
    }

    public void setMenuArrange(String menuArrange)
    {
        this.menuArrange = menuArrange;

    }
}
