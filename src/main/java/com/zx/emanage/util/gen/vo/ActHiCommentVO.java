package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class ActHiCommentVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String id_;

    private String type_;

    private String time_;

    private String user_id_;

    private String task_id_;

    private String proc_inst_id_;

    private String action_;

    private String message_;

    private byte[] full_msg_;

    public String getId_()
    {
        return id_;
    }

    public void setId_(String obj)
    {
        id_ = obj;
    }

    public String getType_()
    {
        return type_;
    }

    public void setType_(String obj)
    {
        type_ = obj;
    }

    public String getTime_()
    {
        return time_;
    }

    public void setTime_(String obj)
    {
        time_ = obj;
    }

    public String getUser_id_()
    {
        return user_id_;
    }

    public void setUser_id_(String obj)
    {
        user_id_ = obj;
    }

    public String getTask_id_()
    {
        return task_id_;
    }

    public void setTask_id_(String obj)
    {
        task_id_ = obj;
    }

    public String getProc_inst_id_()
    {
        return proc_inst_id_;
    }

    public void setProc_inst_id_(String obj)
    {
        proc_inst_id_ = obj;
    }

    public String getAction_()
    {
        return action_;
    }

    public void setAction_(String obj)
    {
        action_ = obj;
    }

    public String getMessage_()
    {
        return message_;
    }

    public void setMessage_(String obj)
    {
        message_ = obj;
    }

    public byte[] getFull_msg_()
    {
        return full_msg_;
    }

    public void setFull_msg_(byte[] obj)
    {
        full_msg_ = obj;
    }
}