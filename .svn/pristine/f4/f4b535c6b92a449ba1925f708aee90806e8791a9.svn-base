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

public class WmsInveTransaProdReward implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_transa_prod_reward_id;

    private Integer wms_inve_pruduct_category_id;

    private Integer product_deadline_month;

    private java.math.BigDecimal reward_interest;

    private Integer wms_inve_transa_prod_id;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_inve_transa_prod_reward_id" };

    private static String[] columnNameArr = { "wms_inve_transa_prod_reward_id", "wms_inve_pruduct_category_id",
                                             "product_deadline_month", "reward_interest", "wms_inve_transa_prod_id",
                                             "isExcludePKFlag" };

    public Integer getWms_inve_transa_prod_reward_id()
    {
        return wms_inve_transa_prod_reward_id;
    }

    public void setWms_inve_transa_prod_reward_id(Integer obj)
    {
        wms_inve_transa_prod_reward_id = obj;
    }

    public Integer getWms_inve_pruduct_category_id()
    {
        return wms_inve_pruduct_category_id;
    }

    public void setWms_inve_pruduct_category_id(Integer obj)
    {
        wms_inve_pruduct_category_id = obj;
    }

    public Integer getProduct_deadline_month()
    {
        return product_deadline_month;
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

    public Integer getWms_inve_transa_prod_id()
    {
        return wms_inve_transa_prod_id;
    }

    public void setWms_inve_transa_prod_id(Integer obj)
    {
        wms_inve_transa_prod_id = obj;
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
        paramMap.put("wms_inve_transa_prod_reward_id", this.wms_inve_transa_prod_reward_id);
        paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
        paramMap.put("product_deadline_month", this.product_deadline_month);
        paramMap.put("reward_interest", this.reward_interest);
        paramMap.put("wms_inve_transa_prod_id", this.wms_inve_transa_prod_id);
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