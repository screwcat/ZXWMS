package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class ActGeBytearrayVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String id_;

    private Integer rev_;

    private String name_;

    private String deployment_id_;

    private byte[] bytes_;

    private Integer generated_;

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

    public String getName_()
    {
        return name_;
    }

    public void setName_(String obj)
    {
        name_ = obj;
    }

    public String getDeployment_id_()
    {
        return deployment_id_;
    }

    public void setDeployment_id_(String obj)
    {
        deployment_id_ = obj;
    }

    public byte[] getBytes_()
    {
        return bytes_;
    }

    public void setBytes_(byte[] obj)
    {
        bytes_ = obj;
    }

    public Integer getGenerated_()
    {
        return generated_;
    }

    public void setGenerated_(Integer obj)
    {
        generated_ = obj;
    }
}