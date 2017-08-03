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

public class ActRuTask implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id_;

    private Integer rev_;

    private String execution_id_;

    private String proc_inst_id_;

    private String proc_def_id_;

    private String name_;

    private String parent_task_id_;

    private String description_;

    private String task_def_key_;

    private String owner_;

    private String assignee_;

    private String delegation_;

    private Integer priority_;

    private java.sql.Timestamp create_time_;

    private String create_time__str;

    private java.sql.Timestamp due_date_;

    private String due_date__str;

    private String category_;

    private Integer suspension_state_;

    private String tenant_id_;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "rev_", "execution_id_", "proc_inst_id_", "proc_def_id_", "name_",
                                             "parent_task_id_", "description_", "task_def_key_", "owner_", "assignee_",
                                             "delegation_", "priority_", "create_time_", "create_time__str",
                                             "due_date_", "due_date__str", "category_", "suspension_state_",
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

    public String getExecution_id_()
    {
        return execution_id_;
    }

    public void setExecution_id_(String obj)
    {
        execution_id_ = obj;
    }

    public String getProc_inst_id_()
    {
        return proc_inst_id_;
    }

    public void setProc_inst_id_(String obj)
    {
        proc_inst_id_ = obj;
    }

    public String getProc_def_id_()
    {
        return proc_def_id_;
    }

    public void setProc_def_id_(String obj)
    {
        proc_def_id_ = obj;
    }

    public String getName_()
    {
        return name_;
    }

    public void setName_(String obj)
    {
        name_ = obj;
    }

    public String getParent_task_id_()
    {
        return parent_task_id_;
    }

    public void setParent_task_id_(String obj)
    {
        parent_task_id_ = obj;
    }

    public String getDescription_()
    {
        return description_;
    }

    public void setDescription_(String obj)
    {
        description_ = obj;
    }

    public String getTask_def_key_()
    {
        return task_def_key_;
    }

    public void setTask_def_key_(String obj)
    {
        task_def_key_ = obj;
    }

    public String getOwner_()
    {
        return owner_;
    }

    public void setOwner_(String obj)
    {
        owner_ = obj;
    }

    public String getAssignee_()
    {
        return assignee_;
    }

    public void setAssignee_(String obj)
    {
        assignee_ = obj;
    }

    public String getDelegation_()
    {
        return delegation_;
    }

    public void setDelegation_(String obj)
    {
        delegation_ = obj;
    }

    public Integer getPriority_()
    {
        return priority_;
    }

    public void setPriority_(Integer obj)
    {
        priority_ = obj;
    }

    public java.sql.Timestamp getCreate_time_()
    {
        return create_time_;
    }

    public void setCreate_time_(java.sql.Timestamp obj)
    {
        create_time_ = obj;
    }

    public String getCreate_time_Str()
    {
        return create_time__str;
    }

    public void setCreate_time_Str(String obj)
    {
        create_time__str = obj;
    }

    public java.sql.Timestamp getDue_date_()
    {
        return due_date_;
    }

    public void setDue_date_(java.sql.Timestamp obj)
    {
        due_date_ = obj;
    }

    public String getDue_date_Str()
    {
        return due_date__str;
    }

    public void setDue_date_Str(String obj)
    {
        due_date__str = obj;
    }

    public String getCategory_()
    {
        return category_;
    }

    public void setCategory_(String obj)
    {
        category_ = obj;
    }

    public Integer getSuspension_state_()
    {
        return suspension_state_;
    }

    public void setSuspension_state_(Integer obj)
    {
        suspension_state_ = obj;
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
        paramMap.put("execution_id_", this.execution_id_);
        paramMap.put("proc_inst_id_", this.proc_inst_id_);
        paramMap.put("proc_def_id_", this.proc_def_id_);
        paramMap.put("name_", this.name_);
        paramMap.put("parent_task_id_", this.parent_task_id_);
        paramMap.put("description_", this.description_);
        paramMap.put("task_def_key_", this.task_def_key_);
        paramMap.put("owner_", this.owner_);
        paramMap.put("assignee_", this.assignee_);
        paramMap.put("delegation_", this.delegation_);
        paramMap.put("priority_", this.priority_);
        paramMap.put("create_time_", this.create_time_);
        paramMap.put("create_time__str", this.create_time__str);
        paramMap.put("due_date_", this.due_date_);
        paramMap.put("due_date__str", this.due_date__str);
        paramMap.put("category_", this.category_);
        paramMap.put("suspension_state_", this.suspension_state_);
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