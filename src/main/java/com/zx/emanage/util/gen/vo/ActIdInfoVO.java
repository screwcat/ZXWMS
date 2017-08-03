package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class ActIdInfoVO implements Serializable
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
}