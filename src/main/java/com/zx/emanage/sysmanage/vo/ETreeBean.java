package com.zx.emanage.sysmanage.vo;

import com.zx.platform.syscontext.vo.TreeBean;

public class ETreeBean extends TreeBean
{
    private static final long serialVersionUID = 4515008993168679815L;

    private String pid;// 父节点Id

    private String organ_id;

    /**
     * 是否处于选定状态
     */
    private boolean ischecked;

    private String treeType;

    public ETreeBean()
    {
    }

    public ETreeBean(String text, String url, String id)
    {
        setText(text);
        setId(id);
        setUrl(url);
    }

    public ETreeBean(String text, String url)
    {
        super(text, url);
    }

    public boolean isIschecked()
    {
        return ischecked;
    }

    public void setIschecked(boolean ischecked)
    {
        this.ischecked = ischecked;
    }

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public String getTreeType()
    {
        return treeType;
    }

    public void setTreeType(String treeType)
    {
        this.treeType = treeType;
    }

    public String getOrgan_id()
    {
        return organ_id;
    }

    public void setOrgan_id(String organ_id)
    {
        this.organ_id = organ_id;
    }
}
