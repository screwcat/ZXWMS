package com.zx.emanage.util.gen.entity;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: ColumnInfo
 * 模块名称：
 * @Description: 内容摘要：生成动态列表 封装ligerui的column
 * @author zhangyunfei
 * @date 2017年2月20日
 * @version V1.0
 * history:
 * 1、2017年2月20日 Administrator 创建文件
 */
public class ColumnInfo
{
    private String display;// 列字段名称

    private String name;// 列标题文本

    private int width;// 列的宽度

    private int minWidth;// 列的宽度

    private int height;

    private boolean frozen;

    public String getDisplay()
    {
        return display;
    }

    public void setDisplay(String display)
    {
        this.display = display;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getMinWidth()
    {
        return minWidth;
    }

    public void setMinWidth(int minWidth)
    {
        this.minWidth = minWidth;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public boolean isFrozen()
    {
        return frozen;
    }

    public void setFrozen(boolean frozen)
    {
        this.frozen = frozen;
    }

}
