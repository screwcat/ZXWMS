package com.zx.emanage.sysmanage.vo;

import java.util.List;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: Message
 * 模块名称：
 * @Description: 内容摘要：
 * @author Administrator
 * @date 2017年6月12日
 * @version V1.0
 * history:
 * 1、2017年6月12日 Administrator 创建文件
 */
public class MessageVo
{
    private List<String> userCode;
    private String message;

    public List<String> getUserCode()
    {
        return userCode;
    }

    public void setUserCode(List<String> userCode)
    {
        this.userCode = userCode;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
