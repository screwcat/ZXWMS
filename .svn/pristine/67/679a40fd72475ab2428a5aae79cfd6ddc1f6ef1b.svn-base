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

public class ActReModel implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id_;

    private Integer rev_;

    private String name_;

    private String key_;

    private String category_;

    private java.sql.Timestamp create_time_;

    private String create_time__str;

    private java.sql.Timestamp last_update_time_;

    private String last_update_time__str;

    private Integer version_;

    private String meta_info_;

    private String deployment_id_;

    private String editor_source_value_id_;

    private String editor_source_extra_value_id_;

    private String tenant_id_;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "rev_", "name_", "key_", "category_", "create_time_",
                                             "create_time__str", "last_update_time_", "last_update_time__str",
                                             "version_", "meta_info_", "deployment_id_", "editor_source_value_id_",
                                             "editor_source_extra_value_id_", "tenant_id_" };

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

    public String getName_()
    {
        return name_;
    }

    public void setName_(String obj)
    {
        name_ = obj;
    }

    public String getKey_()
    {
        return key_;
    }

    public void setKey_(String obj)
    {
        key_ = obj;
    }

    public String getCategory_()
    {
        return category_;
    }

    public void setCategory_(String obj)
    {
        category_ = obj;
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

    public java.sql.Timestamp getLast_update_time_()
    {
        return last_update_time_;
    }

    public void setLast_update_time_(java.sql.Timestamp obj)
    {
        last_update_time_ = obj;
    }

    public String getLast_update_time_Str()
    {
        return last_update_time__str;
    }

    public void setLast_update_time_Str(String obj)
    {
        last_update_time__str = obj;
    }

    public Integer getVersion_()
    {
        return version_;
    }

    public void setVersion_(Integer obj)
    {
        version_ = obj;
    }

    public String getMeta_info_()
    {
        return meta_info_;
    }

    public void setMeta_info_(String obj)
    {
        meta_info_ = obj;
    }

    public String getDeployment_id_()
    {
        return deployment_id_;
    }

    public void setDeployment_id_(String obj)
    {
        deployment_id_ = obj;
    }

    public String getEditor_source_value_id_()
    {
        return editor_source_value_id_;
    }

    public void setEditor_source_value_id_(String obj)
    {
        editor_source_value_id_ = obj;
    }

    public String getEditor_source_extra_value_id_()
    {
        return editor_source_extra_value_id_;
    }

    public void setEditor_source_extra_value_id_(String obj)
    {
        editor_source_extra_value_id_ = obj;
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
        paramMap.put("name_", this.name_);
        paramMap.put("key_", this.key_);
        paramMap.put("category_", this.category_);
        paramMap.put("create_time_", this.create_time_);
        paramMap.put("create_time__str", this.create_time__str);
        paramMap.put("last_update_time_", this.last_update_time_);
        paramMap.put("last_update_time__str", this.last_update_time__str);
        paramMap.put("version_", this.version_);
        paramMap.put("meta_info_", this.meta_info_);
        paramMap.put("deployment_id_", this.deployment_id_);
        paramMap.put("editor_source_value_id_", this.editor_source_value_id_);
        paramMap.put("editor_source_extra_value_id_", this.editor_source_extra_value_id_);
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