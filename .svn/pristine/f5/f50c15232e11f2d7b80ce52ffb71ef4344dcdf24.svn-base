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

public class WmsCreRevInfoModel implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_info_model_id;

    private Integer wms_cre_credit_head_id;

    private String work_unit_full_name;

    private Integer work_unit_property;

    private Integer comp_industry;

    private Integer work_year;

    private Integer duty_of_work;

    private Integer comp_industry_year;

    private String unit_tel;

    private Integer annual_income;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_info_model_id" };

    private static String[] columnNameArr = { "wms_cre_rev_info_model_id", "wms_cre_credit_head_id",
                                             "work_unit_full_name", "work_unit_property", "comp_industry", "work_year",
                                             "duty_of_work", "comp_industry_year", "unit_tel", "annual_income" };

    public Integer getWms_cre_rev_info_model_id()
    {
        return wms_cre_rev_info_model_id;
    }

    public void setWms_cre_rev_info_model_id(Integer obj)
    {
        wms_cre_rev_info_model_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public String getWork_unit_full_name()
    {
        return work_unit_full_name;
    }

    public void setWork_unit_full_name(String obj)
    {
        work_unit_full_name = obj;
    }

    public Integer getWork_unit_property()
    {
        return work_unit_property;
    }

    public void setWork_unit_property(Integer obj)
    {
        work_unit_property = obj;
    }

    public Integer getComp_industry()
    {
        return comp_industry;
    }

    public void setComp_industry(Integer obj)
    {
        comp_industry = obj;
    }

    public Integer getWork_year()
    {
        return work_year;
    }

    public void setWork_year(Integer obj)
    {
        work_year = obj;
    }

    public Integer getDuty_of_work()
    {
        return duty_of_work;
    }

    public void setDuty_of_work(Integer obj)
    {
        duty_of_work = obj;
    }

    public Integer getComp_industry_year()
    {
        return comp_industry_year;
    }

    public void setComp_industry_year(Integer obj)
    {
        comp_industry_year = obj;
    }

    public String getUnit_tel()
    {
        return unit_tel;
    }

    public void setUnit_tel(String obj)
    {
        unit_tel = obj;
    }

    public Integer getAnnual_income()
    {
        return annual_income;
    }

    public void setAnnual_income(Integer obj)
    {
        annual_income = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_info_model_id", this.wms_cre_rev_info_model_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("work_unit_full_name", this.work_unit_full_name);
        paramMap.put("work_unit_property", this.work_unit_property);
        paramMap.put("comp_industry", this.comp_industry);
        paramMap.put("work_year", this.work_year);
        paramMap.put("duty_of_work", this.duty_of_work);
        paramMap.put("comp_industry_year", this.comp_industry_year);
        paramMap.put("unit_tel", this.unit_tel);
        paramMap.put("annual_income", this.annual_income);
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