package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class ActIdUserVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String id_;

    private Integer rev_;

    private String first_;

    private String last_;

    private String email_;

    private String pwd_;

    private String picture_id_;

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
}