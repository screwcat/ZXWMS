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

public class ActIdInfo implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id_;

    private Integer rev_;

    private String user_id_;

    private String type_;

    private String key_;

    private String value_;

    private byte[] password_;

    private String parent_id_;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "rev_", "user_id_", "type_", "key_", "value_", "password_",
                                             "parent_id_" };

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

    public String getUser_id_()
    {
        return user_id_;
    }

    public void setUser_id_(String obj)
    {
        user_id_ = obj;
    }

    public String getType_()
    {
        return type_;
    }

    public void setType_(String obj)
    {
        type_ = obj;
    }

    public String getKey_()
    {
        return key_;
    }

    public void setKey_(String obj)
    {
        key_ = obj;
    }

    public String getValue_()
    {
        return value_;
    }

    public void setValue_(String obj)
    {
        value_ = obj;
    }

    public byte[] getPassword_()
    {
        return password_;
    }

    public void setPassword_(byte[] obj)
    {
        password_ = obj;
    }

    public String getParent_id_()
    {
        return parent_id_;
    }

    public void setParent_id_(String obj)
    {
        parent_id_ = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id_", this.id_);
        paramMap.put("rev_", this.rev_);
        paramMap.put("user_id_", this.user_id_);
        paramMap.put("type_", this.type_);
        paramMap.put("key_", this.key_);
        paramMap.put("value_", this.value_);
        paramMap.put("password_", this.password_);
        paramMap.put("parent_id_", this.parent_id_);
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