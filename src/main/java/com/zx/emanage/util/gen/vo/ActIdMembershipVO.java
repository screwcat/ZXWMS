package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class ActIdMembershipVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String user_id_;

    private String group_id_;

    public String getUser_id_()
    {
        return user_id_;
    }

    public void setUser_id_(String obj)
    {
        user_id_ = obj;
    }

    public String getGroup_id_()
    {
        return group_id_;
    }

    public void setGroup_id_(String obj)
    {
        group_id_ = obj;
    }
}