package com.zx.emanage.util.gen.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.math.BigDecimal;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class SysUser implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String user_code;

    private String user_passwd;

    private String user_realname;

    private String enable_flag;

    private String create_user;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private String last_update_user;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String last_update_remark;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id" };

    private static String[] columnNameArr = { "id", "user_code", "user_passwd", "user_realname", "dept_id",
                                             "enable_flag", "create_user", "create_timestamp", "create_timestamp_str",
                                             "last_update_user", "last_update_timestamp", "last_update_timestamp_str",
                                             "last_update_remark" };

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer obj)
    {
        id = obj;
    }

    public String getUser_code()
    {
        return user_code;
    }

    public void setUser_code(String obj)
    {
        user_code = obj;
    }

    public String getUser_passwd()
    {
        return user_passwd;
    }

    public void setUser_passwd(String obj)
    {
        user_passwd = obj;
    }

    public String getUser_realname()
    {
        return user_realname;
    }

    public void setUser_realname(String obj)
    {
        user_realname = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }

    public String getCreate_user()
    {
        return create_user;
    }

    public void setCreate_user(String obj)
    {
        create_user = obj;
    }

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp obj)
    {
        create_timestamp = obj;
    }

    public String getCreate_timestampStr()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestampStr(String obj)
    {
        create_timestamp_str = obj;
    }

    public String getLast_update_user()
    {
        return last_update_user;
    }

    public void setLast_update_user(String obj)
    {
        last_update_user = obj;
    }

    public java.sql.Timestamp getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(java.sql.Timestamp obj)
    {
        last_update_timestamp = obj;
    }

    public String getLast_update_timestampStr()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestampStr(String obj)
    {
        last_update_timestamp_str = obj;
    }

    public String getLast_update_remark()
    {
        return last_update_remark;
    }

    public void setLast_update_remark(String obj)
    {
        last_update_remark = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id", this.id);
        paramMap.put("user_code", this.user_code);
        paramMap.put("user_passwd", this.user_passwd);
        paramMap.put("user_realname", this.user_realname);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("create_user", this.create_user);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user", this.last_update_user);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("last_update_remark", this.last_update_remark);
    }

    /**
     * return the columns map
     */
    public Map<String, Object> getInfoMap()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        this.putInMap(paramMap);
        return paramMap;
    }

    /**
     * remove default value and pk if it is null
     */
    private Map<String, Object> dealWithMap(Map<String, Object> paramMap)
    {
        Set<String> set = new HashSet<String>();
        for (String colName : defaultValColArr)
        {
            set.add(colName);
        }
        for (String colName : pkColArr)
        {
            set.add(colName);
        }
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext())
        {
            String colName = iterator.next();
            if (paramMap.get(colName) == null)
            {
                paramMap.remove(colName);
            }
        }
        return paramMap;
    }
}