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

public class WmsCreRevInspectionFamily implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_inspection_family_id;

    private Integer wms_cre_credit_head_id;

    private String contact_relation_type;

    private String name;

    private String age;

    private String work_unit_full_name;

    private String personal_income;

    private String car_brand;

    private String house_stat;

    private String enable_flag;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_inspection_family_id" };

    private static String[] columnNameArr = { "wms_cre_rev_inspection_family_id", "wms_cre_credit_head_id",
                                             "contact_relation_type", "name", "age", "work_unit_full_name",
                                             "personal_income", "car_brand", "house_stat", "enable_flag",
                                             "isExcludePKFlag" };

    public Integer getWms_cre_rev_inspection_family_id()
    {
        return wms_cre_rev_inspection_family_id;
    }

    public void setWms_cre_rev_inspection_family_id(Integer obj)
    {
        wms_cre_rev_inspection_family_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public String getContact_relation_type()
    {
        return contact_relation_type;
    }

    public void setContact_relation_type(String obj)
    {
        contact_relation_type = obj;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String obj)
    {
        name = obj;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String obj)
    {
        age = obj;
    }

    public String getWork_unit_full_name()
    {
        return work_unit_full_name;
    }

    public void setWork_unit_full_name(String obj)
    {
        work_unit_full_name = obj;
    }

    public String getPersonal_income()
    {
        return personal_income;
    }

    public void setPersonal_income(String obj)
    {
        personal_income = obj;
    }

    public String getCar_brand()
    {
        return car_brand;
    }

    public void setCar_brand(String obj)
    {
        car_brand = obj;
    }

    public String getHouse_stat()
    {
        return house_stat;
    }

    public void setHouse_stat(String obj)
    {
        house_stat = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
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
        paramMap.put("wms_cre_rev_inspection_family_id", this.wms_cre_rev_inspection_family_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("contact_relation_type", this.contact_relation_type);
        paramMap.put("name", this.name);
        paramMap.put("age", this.age);
        paramMap.put("work_unit_full_name", this.work_unit_full_name);
        paramMap.put("personal_income", this.personal_income);
        paramMap.put("car_brand", this.car_brand);
        paramMap.put("house_stat", this.house_stat);
        paramMap.put("enable_flag", this.enable_flag);
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