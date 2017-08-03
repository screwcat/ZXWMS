package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class ActHiActinstVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String id_;

    private String proc_def_id_;

    private String proc_inst_id_;

    private String execution_id_;

    private String act_id_;

    private String task_id_;

    private String call_proc_inst_id_;

    private String act_name_;

    private String act_type_;

    private String assignee_;

    private String start_time_;

    private String end_time_;

    private Long duration_;

    private String tenant_id_;

    public String getId_()
    {
        return id_;
    }

    public void setId_(String obj)
    {
        id_ = obj;
    }

    public String getProc_def_id_()
    {
        return proc_def_id_;
    }

    public void setProc_def_id_(String obj)
    {
        proc_def_id_ = obj;
    }

    public String getProc_inst_id_()
    {
        return proc_inst_id_;
    }

    public void setProc_inst_id_(String obj)
    {
        proc_inst_id_ = obj;
    }

    public String getExecution_id_()
    {
        return execution_id_;
    }

    public void setExecution_id_(String obj)
    {
        execution_id_ = obj;
    }

    public String getAct_id_()
    {
        return act_id_;
    }

    public void setAct_id_(String obj)
    {
        act_id_ = obj;
    }

    public String getTask_id_()
    {
        return task_id_;
    }

    public void setTask_id_(String obj)
    {
        task_id_ = obj;
    }

    public String getCall_proc_inst_id_()
    {
        return call_proc_inst_id_;
    }

    public void setCall_proc_inst_id_(String obj)
    {
        call_proc_inst_id_ = obj;
    }

    public String getAct_name_()
    {
        return act_name_;
    }

    public void setAct_name_(String obj)
    {
        act_name_ = obj;
    }

    public String getAct_type_()
    {
        return act_type_;
    }

    public void setAct_type_(String obj)
    {
        act_type_ = obj;
    }

    public String getAssignee_()
    {
        return assignee_;
    }

    public void setAssignee_(String obj)
    {
        assignee_ = obj;
    }

    public String getStart_time_()
    {
        return start_time_;
    }

    public void setStart_time_(String obj)
    {
        start_time_ = obj;
    }

    public String getEnd_time_()
    {
        return end_time_;
    }

    public void setEnd_time_(String obj)
    {
        end_time_ = obj;
    }

    public Long getDuration_()
    {
        return duration_;
    }

    public void setDuration_(Long obj)
    {
        duration_ = obj;
    }

    public String getTenant_id_()
    {
        return tenant_id_;
    }

    public void setTenant_id_(String obj)
    {
        tenant_id_ = obj;
    }
}