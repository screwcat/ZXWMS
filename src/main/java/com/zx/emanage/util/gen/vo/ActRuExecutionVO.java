package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class ActRuExecutionVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String id_;

    private Integer rev_;

    private String proc_inst_id_;

    private String business_key_;

    private String parent_id_;

    private String proc_def_id_;

    private String super_exec_;

    private String act_id_;

    private Integer is_active_;

    private Integer is_concurrent_;

    private Integer is_scope_;

    private Integer is_event_scope_;

    private Integer suspension_state_;

    private Integer cached_ent_state_;

    private String tenant_id_;

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

    public String getProc_inst_id_()
    {
        return proc_inst_id_;
    }

    public void setProc_inst_id_(String obj)
    {
        proc_inst_id_ = obj;
    }

    public String getBusiness_key_()
    {
        return business_key_;
    }

    public void setBusiness_key_(String obj)
    {
        business_key_ = obj;
    }

    public String getParent_id_()
    {
        return parent_id_;
    }

    public void setParent_id_(String obj)
    {
        parent_id_ = obj;
    }

    public String getProc_def_id_()
    {
        return proc_def_id_;
    }

    public void setProc_def_id_(String obj)
    {
        proc_def_id_ = obj;
    }

    public String getSuper_exec_()
    {
        return super_exec_;
    }

    public void setSuper_exec_(String obj)
    {
        super_exec_ = obj;
    }

    public String getAct_id_()
    {
        return act_id_;
    }

    public void setAct_id_(String obj)
    {
        act_id_ = obj;
    }

    public Integer getIs_active_()
    {
        return is_active_;
    }

    public void setIs_active_(Integer obj)
    {
        is_active_ = obj;
    }

    public Integer getIs_concurrent_()
    {
        return is_concurrent_;
    }

    public void setIs_concurrent_(Integer obj)
    {
        is_concurrent_ = obj;
    }

    public Integer getIs_scope_()
    {
        return is_scope_;
    }

    public void setIs_scope_(Integer obj)
    {
        is_scope_ = obj;
    }

    public Integer getIs_event_scope_()
    {
        return is_event_scope_;
    }

    public void setIs_event_scope_(Integer obj)
    {
        is_event_scope_ = obj;
    }

    public Integer getSuspension_state_()
    {
        return suspension_state_;
    }

    public void setSuspension_state_(Integer obj)
    {
        suspension_state_ = obj;
    }

    public Integer getCached_ent_state_()
    {
        return cached_ent_state_;
    }

    public void setCached_ent_state_(Integer obj)
    {
        cached_ent_state_ = obj;
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