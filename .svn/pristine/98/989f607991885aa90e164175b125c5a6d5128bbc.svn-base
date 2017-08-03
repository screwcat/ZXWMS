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

public class ActReDeployment implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id_;

    private String name_;

    private String category_;

    private String tenant_id_;

    private java.sql.Timestamp deploy_time_;

    private String deploy_time__str;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id_" };

    private static String[] columnNameArr = { "id_", "name_", "category_", "tenant_id_", "deploy_time_",
                                             "deploy_time__str" };

    public String getId_()
    {
        return id_;
    }

    public void setId_(String obj)
    {
        id_ = obj;
    }

    public String getName_()
    {
        return name_;
    }

    public void setName_(String obj)
    {
        name_ = obj;
    }

    public String getCategory_()
    {
        return category_;
    }

    public void setCategory_(String obj)
    {
        category_ = obj;
    }

    public String getTenant_id_()
    {
        return tenant_id_;
    }

    public void setTenant_id_(String obj)
    {
        tenant_id_ = obj;
    }

    public java.sql.Timestamp getDeploy_time_()
    {
        return deploy_time_;
    }

    public void setDeploy_time_(java.sql.Timestamp obj)
    {
        deploy_time_ = obj;
    }

    public String getDeploy_time_Str()
    {
        return deploy_time__str;
    }

    public void setDeploy_time_Str(String obj)
    {
        deploy_time__str = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id_", this.id_);
        paramMap.put("name_", this.name_);
        paramMap.put("category_", this.category_);
        paramMap.put("tenant_id_", this.tenant_id_);
        paramMap.put("deploy_time_", this.deploy_time_);
        paramMap.put("deploy_time__str", this.deploy_time__str);
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