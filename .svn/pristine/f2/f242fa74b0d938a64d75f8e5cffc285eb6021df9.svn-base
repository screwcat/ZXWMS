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

public class ActHiProcinst implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id_;

    private String proc_inst_id_;

    private String business_key_;

    private String proc_def_id_;

    private java.sql.Timestamp start_time_;

    private String start_time__str;

    private java.sql.Timestamp end_time_;

    private String end_time__str;

    private Long duration_;

    private String start_user_id_;

    private String start_act_id_;

    private String end_act_id_;

    private String super_process_instance_id_;

    private String delete_reason_;

    private String tenant_id_;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "proc_inst_id_", "business_key_", "proc_def_id_", "start_time_",
                                             "start_time__str", "end_time_", "end_time__str", "duration_",
                                             "start_user_id_", "start_act_id_", "end_act_id_",
                                             "super_process_instance_id_", "delete_reason_", "tenant_id_" };

    public String getId_()
    {
        return id_;
    }

    public void setId_(String obj)
    {
        id_ = obj;
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

    public String getProc_def_id_()
    {
        return proc_def_id_;
    }

    public void setProc_def_id_(String obj)
    {
        proc_def_id_ = obj;
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

    public String getStart_user_id_()
    {
        return start_user_id_;
    }

    public void setStart_user_id_(String obj)
    {
        start_user_id_ = obj;
    }

    public String getStart_act_id_()
    {
        return start_act_id_;
    }

    public void setStart_act_id_(String obj)
    {
        start_act_id_ = obj;
    }

    public String getEnd_act_id_()
    {
        return end_act_id_;
    }

    public void setEnd_act_id_(String obj)
    {
        end_act_id_ = obj;
    }

    public String getSuper_process_instance_id_()
    {
        return super_process_instance_id_;
    }

    public void setSuper_process_instance_id_(String obj)
    {
        super_process_instance_id_ = obj;
    }

    public String getDelete_reason_()
    {
        return delete_reason_;
    }

    public void setDelete_reason_(String obj)
    {
        delete_reason_ = obj;
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
        paramMap.put("proc_inst_id_", this.proc_inst_id_);
        paramMap.put("business_key_", this.business_key_);
        paramMap.put("proc_def_id_", this.proc_def_id_);
        paramMap.put("start_time_", this.start_time_);
        paramMap.put("start_time__str", this.start_time__str);
        paramMap.put("end_time_", this.end_time_);
        paramMap.put("end_time__str", this.end_time__str);
        paramMap.put("duration_", this.duration_);
        paramMap.put("start_user_id_", this.start_user_id_);
        paramMap.put("start_act_id_", this.start_act_id_);
        paramMap.put("end_act_id_", this.end_act_id_);
        paramMap.put("super_process_instance_id_", this.super_process_instance_id_);
        paramMap.put("delete_reason_", this.delete_reason_);
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