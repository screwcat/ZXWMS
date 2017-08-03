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

public class SysFunction implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String func_name;

    private String func_method;

    private Integer func_sort;

    private Integer menu_id;

    private String default_flag;

    private String func_remark;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id" };

    private static String[] columnNameArr = { "id", "func_name", "func_method", "func_sort", "menu_id", "default_flag",
                                             "func_remark" };

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer obj)
    {
        id = obj;
    }

    public String getFunc_name()
    {
        return func_name;
    }

    public void setFunc_name(String obj)
    {
        func_name = obj;
    }

    public String getFunc_method()
    {
        return func_method;
    }

    public void setFunc_method(String obj)
    {
        func_method = obj;
    }

    public Integer getFunc_sort()
    {
        return func_sort;
    }

    public void setFunc_sort(Integer obj)
    {
        func_sort = obj;
    }

    public Integer getMenu_id()
    {
        return menu_id;
    }

    public void setMenu_id(Integer obj)
    {
        menu_id = obj;
    }

    public String getDefault_flag()
    {
        return default_flag;
    }

    public void setDefault_flag(String obj)
    {
        default_flag = obj;
    }

    public String getFunc_remark()
    {
        return func_remark;
    }

    public void setFunc_remark(String obj)
    {
        func_remark = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id", this.id);
        paramMap.put("func_name", this.func_name);
        paramMap.put("func_method", this.func_method);
        paramMap.put("func_sort", this.func_sort);
        paramMap.put("menu_id", this.menu_id);
        paramMap.put("default_flag", this.default_flag);
        paramMap.put("func_remark", this.func_remark);
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