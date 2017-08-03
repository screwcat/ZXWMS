package com.zx.emanage.cremanage.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
 * @version 2.0
 */

public class SysSendInfoVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private List<String> user_code;//人员短工号

    private String msg_code;// 消息编码
    
    private Map<String, String> map;//推送消息参数（模版生成消息时的参数）
    
    private Map<String, Object> extras;//消息附加数据

    private String app_name;



	public List<String> getUser_code() {
		return user_code;
	}

	public void setUser_code(List<String> user_code) {
		this.user_code = user_code;
	}

	public String getMsg_code() {
		return msg_code;
	}

	public void setMsg_code(String msg_code) {
		this.msg_code = msg_code;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Map<String, Object> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, Object> extras) {
		this.extras = extras;
	}

    public String getApp_name()
    {
        return app_name;
    }

    public void setApp_name(String app_name)
    {
        this.app_name = app_name;
    }

}