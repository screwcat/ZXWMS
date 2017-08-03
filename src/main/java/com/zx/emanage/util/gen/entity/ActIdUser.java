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

public class ActIdUser implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id_;

    private Integer rev_;

    private String first_;

    private String last_;

    private String email_;

    private String pwd_;

    private String picture_id_;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "rev_", "first_", "last_", "email_", "pwd_", "picture_id_" };

    public String getId_()
    {
        return id_;
    }

    public void setId_(String obj)
    {
        id_ = obj;
    }

    public Integer getRev_()
    {
        return rev_;
    }

    public void setRev_(Integer obj)
    {
        rev_ = obj;
    }

    public String getFirst_()
    {
        return first_;
    }

    public void setFirst_(String obj)
    {
        first_ = obj;
    }

    public String getLast_()
    {
        return last_;
    }

    public void setLast_(String obj)
    {
        last_ = obj;
    }

    public String getEmail_()
    {
        return email_;
    }

    public void setEmail_(String obj)
    {
        email_ = obj;
    }

    public String getPwd_()
    {
        return pwd_;
    }

    public void setPwd_(String obj)
    {
        pwd_ = obj;
    }

    public String getPicture_id_()
    {
        return picture_id_;
    }

    public void setPicture_id_(String obj)
    {
        picture_id_ = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id_", this.id_);
        paramMap.put("rev_", this.rev_);
        paramMap.put("first_", this.first_);
        paramMap.put("last_", this.last_);
        paramMap.put("email_", this.email_);
        paramMap.put("pwd_", this.pwd_);
        paramMap.put("picture_id_", this.picture_id_);
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