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

public class WmsCreRevInfoInsurance implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_info_insurance_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private String lnsurance_type;

    private String lookup_mode;

    private String lookup_result;

    private String lnsurance_com_same;

    private String lnsurance_status;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_info_insurance_id" };

    private static String[] columnNameArr = { "wms_cre_rev_info_insurance_id", "wms_cre_credit_head_id",
                                             "wms_cre_credit_line_customer_change_head_id", "lnsurance_type",
                                             "lookup_mode", "lookup_result", "lnsurance_com_same", "lnsurance_status" };

    public Integer getWms_cre_rev_info_insurance_id()
    {
        return wms_cre_rev_info_insurance_id;
    }

    public void setWms_cre_rev_info_insurance_id(Integer obj)
    {
        wms_cre_rev_info_insurance_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public Integer getWms_cre_credit_line_customer_change_head_id()
    {
        return wms_cre_credit_line_customer_change_head_id;
    }

    public void setWms_cre_credit_line_customer_change_head_id(Integer obj)
    {
        wms_cre_credit_line_customer_change_head_id = obj;
    }

    public String getLnsurance_type()
    {
        return lnsurance_type;
    }

    public void setLnsurance_type(String obj)
    {
        lnsurance_type = obj;
    }

    public String getLookup_mode()
    {
        return lookup_mode;
    }

    public void setLookup_mode(String obj)
    {
        lookup_mode = obj;
    }

    public String getLookup_result()
    {
        return lookup_result;
    }

    public void setLookup_result(String obj)
    {
        lookup_result = obj;
    }

    public String getLnsurance_com_same()
    {
        return lnsurance_com_same;
    }

    public void setLnsurance_com_same(String obj)
    {
        lnsurance_com_same = obj;
    }

    public String getLnsurance_status()
    {
        return lnsurance_status;
    }

    public void setLnsurance_status(String obj)
    {
        lnsurance_status = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_info_insurance_id", this.wms_cre_rev_info_insurance_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("lnsurance_type", this.lnsurance_type);
        paramMap.put("lookup_mode", this.lookup_mode);
        paramMap.put("lookup_result", this.lookup_result);
        paramMap.put("lnsurance_com_same", this.lnsurance_com_same);
        paramMap.put("lnsurance_status", this.lnsurance_status);
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