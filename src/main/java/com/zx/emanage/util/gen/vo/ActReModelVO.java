package com.zx.emanage.util.gen.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @version 2.0
 */

public class ActReModelVO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String id_;

    private Integer rev_;

    private String name_;

    private String key_;

    private String category_;

    private String create_time_;

    private String last_update_time_;

    private Integer version_;

    private String meta_info_;

    private String deployment_id_;

    private String editor_source_value_id_;

    private String editor_source_extra_value_id_;

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

    public String getCreate_time_()
    {
        return create_time_;
    }

    public void setCreate_time_(String obj)
    {
        create_time_ = obj;
    }

    public String getLast_update_time_()
    {
        return last_update_time_;
    }

    public void setLast_update_time_(String obj)
    {
        last_update_time_ = obj;
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
}