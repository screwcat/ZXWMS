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

public class SysMenu implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String menu_name;

    private String menu_url;

    private Integer menu_sort;

    private Integer menu_level;

    private Integer p_menu_id;

    private String menu_remark;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id" };

    private static String[] columnNameArr = { "id", "menu_name", "menu_url", "menu_sort", "menu_level", "p_menu_id",
                                             "menu_remark" };

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer obj)
    {
        id = obj;
    }

    public String getMenu_name()
    {
        return menu_name;
    }

    public void setMenu_name(String obj)
    {
        menu_name = obj;
    }

    public String getMenu_url()
    {
        return menu_url;
    }

    public void setMenu_url(String obj)
    {
        menu_url = obj;
    }

    public Integer getMenu_sort()
    {
        return menu_sort;
    }

    public void setMenu_sort(Integer obj)
    {
        menu_sort = obj;
    }

    public Integer getMenu_level()
    {
        return menu_level;
    }

    public void setMenu_level(Integer obj)
    {
        menu_level = obj;
    }

    public Integer getP_menu_id()
    {
        return p_menu_id;
    }

    public void setP_menu_id(Integer obj)
    {
        p_menu_id = obj;
    }

    public String getMenu_remark()
    {
        return menu_remark;
    }

    public void setMenu_remark(String obj)
    {
        menu_remark = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id", this.id);
        paramMap.put("menu_name", this.menu_name);
        paramMap.put("menu_url", this.menu_url);
        paramMap.put("menu_sort", this.menu_sort);
        paramMap.put("menu_level", this.menu_level);
        paramMap.put("p_menu_id", this.p_menu_id);
        paramMap.put("menu_remark", this.menu_remark);
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