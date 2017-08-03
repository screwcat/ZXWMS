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

public class WmsPostLoanMigration implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_post_loan_migration_id;

    private Integer wms_cre_credit_head_id;

    private String attachment_old_name;

    private String attachment_new_name;

    private String attachment_address;

    private String attachment_type;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_post_loan_migration_id" };

    private static String[] columnNameArr = { "wms_post_loan_migration_id", "wms_cre_credit_head_id",
                                             "attachment_old_name", "attachment_new_name", "attachment_address",
                                             "attachment_type", "create_user_id", "create_user_name",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str", "enable_flag", };

    public Integer getWms_post_loan_migration_id()
    {
        return wms_post_loan_migration_id;
    }

    public void setWms_post_loan_migration_id(Integer obj)
    {
        wms_post_loan_migration_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public String getAttachment_old_name()
    {
        return attachment_old_name;
    }

    public void setAttachment_old_name(String obj)
    {
        attachment_old_name = obj;
    }

    public String getAttachment_new_name()
    {
        return attachment_new_name;
    }

    public void setAttachment_new_name(String obj)
    {
        attachment_new_name = obj;
    }

    public String getAttachment_address()
    {
        return attachment_address;
    }

    public void setAttachment_address(String obj)
    {
        attachment_address = obj;
    }

    public String getAttachment_type()
    {
        return attachment_type;
    }

    public void setAttachment_type(String obj)
    {
        attachment_type = obj;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer create_user_id)
    {
        this.create_user_id = create_user_id;
    }

    public String getCreate_user_name()
    {
        return create_user_name;
    }

    public void setCreate_user_name(String create_user_name)
    {
        this.create_user_name = create_user_name;
    }

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp create_timestamp)
    {
        this.create_timestamp = create_timestamp;
    }

    public String getCreate_timestamp_str()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestamp_str(String create_timestamp_str)
    {
        this.create_timestamp_str = create_timestamp_str;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer last_update_user_id)
    {
        this.last_update_user_id = last_update_user_id;
    }

    public java.sql.Timestamp getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(java.sql.Timestamp last_update_timestamp)
    {
        this.last_update_timestamp = last_update_timestamp;
    }

    public String getLast_update_timestamp_str()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestamp_str(String last_update_timestamp_str)
    {
        this.last_update_timestamp_str = last_update_timestamp_str;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String enable_flag)
    {
        this.enable_flag = enable_flag;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_post_loan_migration_id", this.wms_post_loan_migration_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("attachment_old_name", this.attachment_old_name);
        paramMap.put("attachment_new_name", this.attachment_new_name);
        paramMap.put("attachment_address", this.attachment_address);
        paramMap.put("attachment_type", this.attachment_type);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
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