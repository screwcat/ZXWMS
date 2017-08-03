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

public class ActRuExecution implements BaseEntity
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

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "rev_", "proc_inst_id_", "business_key_", "parent_id_",
                                             "proc_def_id_", "super_exec_", "act_id_", "is_active_", "is_concurrent_",
                                             "is_scope_", "is_event_scope_", "suspension_state_", "cached_ent_state_",
                                             "tenant_id_" };

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

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id_", this.id_);
        paramMap.put("rev_", this.rev_);
        paramMap.put("proc_inst_id_", this.proc_inst_id_);
        paramMap.put("business_key_", this.business_key_);
        paramMap.put("parent_id_", this.parent_id_);
        paramMap.put("proc_def_id_", this.proc_def_id_);
        paramMap.put("super_exec_", this.super_exec_);
        paramMap.put("act_id_", this.act_id_);
        paramMap.put("is_active_", this.is_active_);
        paramMap.put("is_concurrent_", this.is_concurrent_);
        paramMap.put("is_scope_", this.is_scope_);
        paramMap.put("is_event_scope_", this.is_event_scope_);
        paramMap.put("suspension_state_", this.suspension_state_);
        paramMap.put("cached_ent_state_", this.cached_ent_state_);
        paramMap.put("tenant_id_", this.tenant_id_);
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