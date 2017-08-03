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

public class ActRuEventSubscr implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id_;

    private Integer rev_;

    private String event_type_;

    private String event_name_;

    private String execution_id_;

    private String proc_inst_id_;

    private String activity_id_;

    private String configuration_;

    private java.sql.Timestamp created_;

    private String created__str;

    private String proc_def_id_;

    private String tenant_id_;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "rev_", "event_type_", "event_name_", "execution_id_",
                                             "proc_inst_id_", "activity_id_", "configuration_", "created_",
                                             "created__str", "proc_def_id_", "tenant_id_" };

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

    public String getEvent_type_()
    {
        return event_type_;
    }

    public void setEvent_type_(String obj)
    {
        event_type_ = obj;
    }

    public String getEvent_name_()
    {
        return event_name_;
    }

    public void setEvent_name_(String obj)
    {
        event_name_ = obj;
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

    public String getActivity_id_()
    {
        return activity_id_;
    }

    public void setActivity_id_(String obj)
    {
        activity_id_ = obj;
    }

    public String getConfiguration_()
    {
        return configuration_;
    }

    public void setConfiguration_(String obj)
    {
        configuration_ = obj;
    }

    public java.sql.Timestamp getCreated_()
    {
        return created_;
    }

    public void setCreated_(java.sql.Timestamp obj)
    {
        created_ = obj;
    }

    public String getCreated_Str()
    {
        return created__str;
    }

    public void setCreated_Str(String obj)
    {
        created__str = obj;
    }

    public String getProc_def_id_()
    {
        return proc_def_id_;
    }

    public void setProc_def_id_(String obj)
    {
        proc_def_id_ = obj;
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
        paramMap.put("event_type_", this.event_type_);
        paramMap.put("event_name_", this.event_name_);
        paramMap.put("execution_id_", this.execution_id_);
        paramMap.put("proc_inst_id_", this.proc_inst_id_);
        paramMap.put("activity_id_", this.activity_id_);
        paramMap.put("configuration_", this.configuration_);
        paramMap.put("created_", this.created_);
        paramMap.put("created__str", this.created__str);
        paramMap.put("proc_def_id_", this.proc_def_id_);
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