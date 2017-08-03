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

public class WmsInvePruductDeadlineReward implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_pruduct_deadline_reward_id;

    private Integer wms_inve_pruduct_category_id;

    private Integer product_deadline_month;

    private java.math.BigDecimal reward_interest;

    private String is_enable_recustomer;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_inve_pruduct_deadline_reward_id" };

    private static String[] columnNameArr = { "wms_inve_pruduct_deadline_reward_id", "wms_inve_pruduct_category_id",
                                             "product_deadline_month", "reward_interest", "is_enable_recustomer",
                                             "isExcludePKFlag" };

    public Integer getWms_inve_pruduct_deadline_reward_id()
    {
        return wms_inve_pruduct_deadline_reward_id;
    }

    public void setWms_inve_pruduct_deadline_reward_id(Integer obj)
    {
        wms_inve_pruduct_deadline_reward_id = obj;
    }

    public Integer getWms_inve_pruduct_category_id()
    {
        return wms_inve_pruduct_category_id;
    }

    public boolean isExcludePKFlag()
    {
        return isExcludePKFlag;
    }

    public void setExcludePKFlag(boolean isExcludePKFlag)
    {
        this.isExcludePKFlag = isExcludePKFlag;
    }

    public static String[] getDefaultValColArr()
    {
        return defaultValColArr;
    }

    public static void setDefaultValColArr(String[] defaultValColArr)
    {
        WmsInvePruductDeadlineReward.defaultValColArr = defaultValColArr;
    }

    public static String[] getPkColArr()
    {
        return pkColArr;
    }

    public static void setPkColArr(String[] pkColArr)
    {
        WmsInvePruductDeadlineReward.pkColArr = pkColArr;
    }

    public static String[] getColumnNameArr()
    {
        return columnNameArr;
    }

    public static void setColumnNameArr(String[] columnNameArr)
    {
        WmsInvePruductDeadlineReward.columnNameArr = columnNameArr;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    public Integer getProduct_deadline_month()
    {
        return product_deadline_month;
    }

    public void setWms_inve_pruduct_category_id(Integer wms_inve_pruduct_category_id)
    {
        this.wms_inve_pruduct_category_id = wms_inve_pruduct_category_id;
    }

    public void setProduct_deadline_month(Integer obj)
    {
        product_deadline_month = obj;
    }

    public java.math.BigDecimal getReward_interest()
    {
        return reward_interest;
    }

    public void setReward_interest(java.math.BigDecimal obj)
    {
        reward_interest = obj;
    }

    public String getIs_enable_recustomer()
    {
        return is_enable_recustomer;
    }

    public void setIs_enable_recustomer(String obj)
    {
        is_enable_recustomer = obj;
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
        paramMap.put("wms_inve_pruduct_deadline_reward_id", this.wms_inve_pruduct_deadline_reward_id);
        paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
        paramMap.put("product_deadline_month", this.product_deadline_month);
        paramMap.put("reward_interest", this.reward_interest);
        paramMap.put("is_enable_recustomer", this.is_enable_recustomer);
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