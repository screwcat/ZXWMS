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

public class ActHiActinst implements BaseEntity
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

    private java.sql.Timestamp start_time_;

    private String start_time__str;

    private java.sql.Timestamp end_time_;

    private String end_time__str;

    private Long duration_;

    private String tenant_id_;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "proc_def_id_", "proc_inst_id_", "execution_id_", "act_id_",
                                             "task_id_", "call_proc_inst_id_", "act_name_", "act_type_", "assignee_",
                                             "start_time_", "start_time__str", "end_time_", "end_time__str",
                                             "duration_", "tenant_id_" };

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

    public java.sql.Timestamp getStart_time_()
    {
        return start_time_;
    }

    public void setStart_time_(java.sql.Timestamp obj)
    {
        start_time_ = obj;
    }

    public String getStart_time_Str()
    {
        return start_time__str;
    }

    public void setStart_time_Str(String obj)
    {
        start_time__str = obj;
    }

    public java.sql.Timestamp getEnd_time_()
    {
        return end_time_;
    }

    public void setEnd_time_(java.sql.Timestamp obj)
    {
        end_time_ = obj;
    }

    public String getEnd_time_Str()
    {
        return end_time__str;
    }

    public void setEnd_time_Str(String obj)
    {
        end_time__str = obj;
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

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id_", this.id_);
        paramMap.put("proc_def_id_", this.proc_def_id_);
        paramMap.put("proc_inst_id_", this.proc_inst_id_);
        paramMap.put("execution_id_", this.execution_id_);
        paramMap.put("act_id_", this.act_id_);
        paramMap.put("task_id_", this.task_id_);
        paramMap.put("call_proc_inst_id_", this.call_proc_inst_id_);
        paramMap.put("act_name_", this.act_name_);
        paramMap.put("act_type_", this.act_type_);
        paramMap.put("assignee_", this.assignee_);
        paramMap.put("start_time_", this.start_time_);
        paramMap.put("start_time__str", this.start_time__str);
        paramMap.put("end_time_", this.end_time_);
        paramMap.put("end_time__str", this.end_time__str);
        paramMap.put("duration_", this.duration_);
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