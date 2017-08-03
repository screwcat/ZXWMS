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

public class ActRuJob implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id_;

    private Integer rev_;

    private String type_;

    private java.sql.Timestamp lock_exp_time_;

    private String lock_exp_time__str;

    private String lock_owner_;

    private Boolean exclusive_;

    private String execution_id_;

    private String process_instance_id_;

    private String proc_def_id_;

    private Integer retries_;

    private String exception_stack_id_;

    private String exception_msg_;

    private java.sql.Timestamp duedate_;

    private String duedate__str;

    private String repeat_;

    private String handler_type_;

    private String handler_cfg_;

    private String tenant_id_;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "rev_", "type_", "lock_exp_time_", "lock_exp_time__str",
                                             "lock_owner_", "exclusive_", "execution_id_", "process_instance_id_",
                                             "proc_def_id_", "retries_", "exception_stack_id_", "exception_msg_",
                                             "duedate_", "duedate__str", "repeat_", "handler_type_", "handler_cfg_",
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

    public String getType_()
    {
        return type_;
    }

    public void setType_(String obj)
    {
        type_ = obj;
    }

    public java.sql.Timestamp getLock_exp_time_()
    {
        return lock_exp_time_;
    }

    public void setLock_exp_time_(java.sql.Timestamp obj)
    {
        lock_exp_time_ = obj;
    }

    public String getLock_exp_time_Str()
    {
        return lock_exp_time__str;
    }

    public void setLock_exp_time_Str(String obj)
    {
        lock_exp_time__str = obj;
    }

    public String getLock_owner_()
    {
        return lock_owner_;
    }

    public void setLock_owner_(String obj)
    {
        lock_owner_ = obj;
    }

    public Boolean getExclusive_()
    {
        return exclusive_;
    }

    public void setExclusive_(Boolean obj)
    {
        exclusive_ = obj;
    }

    public String getExecution_id_()
    {
        return execution_id_;
    }

    public void setExecution_id_(String obj)
    {
        execution_id_ = obj;
    }

    public String getProcess_instance_id_()
    {
        return process_instance_id_;
    }

    public void setProcess_instance_id_(String obj)
    {
        process_instance_id_ = obj;
    }

    public String getProc_def_id_()
    {
        return proc_def_id_;
    }

    public void setProc_def_id_(String obj)
    {
        proc_def_id_ = obj;
    }

    public Integer getRetries_()
    {
        return retries_;
    }

    public void setRetries_(Integer obj)
    {
        retries_ = obj;
    }

    public String getException_stack_id_()
    {
        return exception_stack_id_;
    }

    public void setException_stack_id_(String obj)
    {
        exception_stack_id_ = obj;
    }

    public String getException_msg_()
    {
        return exception_msg_;
    }

    public void setException_msg_(String obj)
    {
        exception_msg_ = obj;
    }

    public java.sql.Timestamp getDuedate_()
    {
        return duedate_;
    }

    public void setDuedate_(java.sql.Timestamp obj)
    {
        duedate_ = obj;
    }

    public String getDuedate_Str()
    {
        return duedate__str;
    }

    public void setDuedate_Str(String obj)
    {
        duedate__str = obj;
    }

    public String getRepeat_()
    {
        return repeat_;
    }

    public void setRepeat_(String obj)
    {
        repeat_ = obj;
    }

    public String getHandler_type_()
    {
        return handler_type_;
    }

    public void setHandler_type_(String obj)
    {
        handler_type_ = obj;
    }

    public String getHandler_cfg_()
    {
        return handler_cfg_;
    }

    public void setHandler_cfg_(String obj)
    {
        handler_cfg_ = obj;
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
        paramMap.put("type_", this.type_);
        paramMap.put("lock_exp_time_", this.lock_exp_time_);
        paramMap.put("lock_exp_time__str", this.lock_exp_time__str);
        paramMap.put("lock_owner_", this.lock_owner_);
        paramMap.put("exclusive_", this.exclusive_);
        paramMap.put("execution_id_", this.execution_id_);
        paramMap.put("process_instance_id_", this.process_instance_id_);
        paramMap.put("proc_def_id_", this.proc_def_id_);
        paramMap.put("retries_", this.retries_);
        paramMap.put("exception_stack_id_", this.exception_stack_id_);
        paramMap.put("exception_msg_", this.exception_msg_);
        paramMap.put("duedate_", this.duedate_);
        paramMap.put("duedate__str", this.duedate__str);
        paramMap.put("repeat_", this.repeat_);
        paramMap.put("handler_type_", this.handler_type_);
        paramMap.put("handler_cfg_", this.handler_cfg_);
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