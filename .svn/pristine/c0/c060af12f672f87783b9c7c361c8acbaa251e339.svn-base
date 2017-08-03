package com.zx.sframe.util.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.domain.SysRole;

/**
 * 登录用户信息表
 * 
 * @author gx
 */
public class UserBean implements Serializable
{

    private static final long serialVersionUID = -6289280101558471306L;

    private Integer userId;// 该字段为登录用户在hr中的persion_id

    private String userCode;

    private String userPasswd;

    private String realname;

    private Integer deptId;

    private String deptSimpleName;

    private String user_province;// 用户所在省

    private String user_city;// 用户所在市

    private String user_regionNumber;// 用户所在区域编码

    private String postName;// 职务名称

    private String region_province_dict_id;// 所属省id

    private String region_city_dict_id;// 所属市id
    

    public String getRegion_province_dict_id()
    {
        return region_province_dict_id;
    }

    public void setRegion_province_dict_id(String region_province_dict_id)
    {
        this.region_province_dict_id = region_province_dict_id;
    }

    public String getRegion_city_dict_id()
    {
        return region_city_dict_id;
    }

    public void setRegion_city_dict_id(String region_city_dict_id)
    {
        this.region_city_dict_id = region_city_dict_id;
    }

    private Map<String, String> funcMap = new HashMap<String, String>();

    public String getUser_province()
    {
        return user_province;
    }

    public void setUser_province(String user_province)
    {
        this.user_province = user_province;
    }

    public String getUser_city()
    {
        return user_city;
    }

    public void setUser_city(String user_city)
    {
        this.user_city = user_city;
    }

    public Map<String, String> getFuncMap()
    {
        return funcMap;
    }

    public void setFuncMap(Map<String, String> funcMap)
    {
        this.funcMap = funcMap;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Integer deptId)
    {
        this.deptId = deptId;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public String getUserPasswd()
    {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd)
    {
        this.userPasswd = userPasswd;
    }

    public void putFuncName(String funcName)
    {
        funcMap.put(funcName, "1");
    }

    public boolean hasFuncName(String funcName)
    {
        if (funcMap.get(funcName) == null)
        {
            return false;
        }
        return true;
    }

    public void cleanFuncNameMap()
    {
        funcMap = new HashMap<String, String>();
    }

    public String getRealname()
    {
        return realname;
    }

    public void setRealname(String realname)
    {
        this.realname = realname;
    }

    public String getDeptSimpleName()
    {
        return deptSimpleName;
    }

    public void setDeptSimpleName(String deptSimpleName)
    {
        this.deptSimpleName = deptSimpleName;
    }

    public String getUser_regionNumber()
    {
        return user_regionNumber;
    }

    public void setUser_regionNumber(String user_regionNumber)
    {
        this.user_regionNumber = user_regionNumber;
    }

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

}
