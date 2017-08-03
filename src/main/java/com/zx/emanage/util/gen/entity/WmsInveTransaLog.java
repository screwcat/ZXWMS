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

public class WmsInveTransaLog implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_transa_log_id;

    private Integer wms_inve_transa_id;

    private java.math.BigDecimal product_account;

    private java.math.BigDecimal product_interest_account;

    private String operate_item;

    private java.sql.Date change_date;

    private String change_date_str;

    private String remark;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_inve_transa_log_id" };

    private static String[] columnNameArr = { "wms_inve_transa_log_id", "wms_inve_transa_id", "product_account",
                                             "product_interest_account", "operate_item", "change_date",
                                             "change_date_str", "remark", "create_user_id", "create_user_name",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str", "isExcludePKFlag" };

    public Integer getWms_inve_transa_log_id()
    {
        return wms_inve_transa_log_id;
    }

    public void setWms_inve_transa_log_id(Integer obj)
    {
        wms_inve_transa_log_id = obj;
    }

    public Integer getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    public void setWms_inve_transa_id(Integer obj)
    {
        wms_inve_transa_id = obj;
    }

    public java.math.BigDecimal getProduct_account()
    {
        return product_account;
    }

    public void setProduct_account(java.math.BigDecimal obj)
    {
        product_account = obj;
    }

    public java.math.BigDecimal getProduct_interest_account()
    {
        return product_interest_account;
    }

    public void setProduct_interest_account(java.math.BigDecimal obj)
    {
        product_interest_account = obj;
    }

    public String getOperate_item()
    {
        return operate_item;
    }

    public void setOperate_item(String obj)
    {
        operate_item = obj;
    }

    public java.sql.Date getChange_date()
    {
        return change_date;
    }

    public void setChange_date(java.sql.Date obj)
    {
        change_date = obj;
    }

    public String getChange_date_str()
    {
        return change_date_str;
    }

    public void setChange_date_str(String obj)
    {
        change_date_str = obj;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String obj)
    {
        remark = obj;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer obj)
    {
        create_user_id = obj;
    }

    public String getCreate_user_name()
    {
        return create_user_name;
    }

    public void setCreate_user_name(String obj)
    {
        create_user_name = obj;
    }

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp obj)
    {
        create_timestamp = obj;
    }

    public String getCreate_timestamp_str()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestamp_str(String obj)
    {
        create_timestamp_str = obj;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer obj)
    {
        last_update_user_id = obj;
    }

    public java.sql.Timestamp getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(java.sql.Timestamp obj)
    {
        last_update_timestamp = obj;
    }

    public String getLast_update_timestamp_str()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestamp_str(String obj)
    {
        last_update_timestamp_str = obj;
    }

    public boolean getgetIsExcludePKFlag()
    {
        return isExcludePKFlag;
    }

    public void setgetIsExcludePKFlag(boolean obj)
    {
        isExcludePKFlag = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_inve_transa_log_id", this.wms_inve_transa_log_id);
        paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
        paramMap.put("product_account", this.product_account);
        paramMap.put("product_interest_account", this.product_interest_account);
        paramMap.put("operate_item", this.operate_item);
        paramMap.put("change_date", this.change_date);
        paramMap.put("change_date_str", this.change_date_str);
        paramMap.put("remark", this.remark);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
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