package com.zx.emanage.util.gen.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.math.BigDecimal;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.DateUtil;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.vo.SysMenuVO;

/*
 * @version 2.0
 */

public class SysMenu implements Serializable
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

    public Map<String, Object> setSymbolInsert(Map<String, Object> paramMap)
    {
        paramMap = dealWithMap(paramMap);
        Boolean flag = true;
        for (String ss : columnNameArr)
        {
            if (flag)
            {
                if (paramMap.containsKey(ss) && paramMap.get(ss) != null)
                {
                    paramMap.put(ss + "Symbol", " ");
                    flag = false;
                }
            }
            else
            {
                paramMap.put(ss + "Symbol", ", ");
            }
        }
        return paramMap;
    }

    private Map<String, Object> setSymbolUpdateWithNullValue(Map<String, Object> paramMap)
    {
        return setSymbolUpdate(dealWithMap(paramMap));
    }

    private Map<String, Object> setSymbolUpdateWithoutNullValue(Map<String, Object> paramMap)
    {
        return setSymbolUpdate(dealWithMapNullVal(paramMap));
    }

    public Map<String, Object> setSymbolUpdate(Map<String, Object> paramMap)
    {
        Boolean flag = true;
        for (String ss : columnNameArr)
        {
            if (flag)
            {
                if (paramMap.containsKey(ss) && paramMap.get(ss) != null && !Arrays.asList(pkColArr).contains(ss))
                {
                    paramMap.put(ss + "Symbol", " ");
                    flag = false;
                }
            }
            else
            {
                paramMap.put(ss + "Symbol", ", ");
            }
        }
        return paramMap;
    }

    /**
     * remove null
     */
    private Map<String, Object> dealWithMapNullVal(Map<String, Object> paramMap)
    {
        if (paramMap.get("id") == null)
        {
            paramMap.remove("id");
        }
        if (paramMap.get("menu_name") == null)
        {
            paramMap.remove("menu_name");
        }
        if (paramMap.get("menu_url") == null)
        {
            paramMap.remove("menu_url");
        }
        if (paramMap.get("menu_sort") == null)
        {
            paramMap.remove("menu_sort");
        }
        if (paramMap.get("menu_level") == null)
        {
            paramMap.remove("menu_level");
        }
        if (paramMap.get("p_menu_id") == null)
        {
            paramMap.remove("p_menu_id");
        }
        if (paramMap.get("menu_remark") == null)
        {
            paramMap.remove("menu_remark");
        }
        return paramMap;
    }

    /**
     * this table insert function, nonsupport null val
     */
    public int insertRecord(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_MENU_INSERT, setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return dao.updateByTemplate(SqlString.AUTO2_SYS_MENU_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_MENU_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_MENU_UPDATE, setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static SysMenu getRecordDomainByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        SysMenu bean = dao.qryObj(SqlString.AUTO2_SYS_MENU_LIST, paramMap, SysMenu.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static SysMenuVO getRecordVOByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_SYS_MENU_LIST, paramMap);
        SysMenuVO bean = new SysMenuVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("id") != null)
            {
                bean.setId((Integer) row.get("id"));
            }
            if (row.get("menu_name") != null)
            {
                bean.setMenu_name((String) row.get("menu_name"));
            }
            if (row.get("menu_url") != null)
            {
                bean.setMenu_url((String) row.get("menu_url"));
            }
            if (row.get("menu_sort") != null)
            {
                bean.setMenu_sort((Integer) row.get("menu_sort"));
            }
            if (row.get("menu_level") != null)
            {
                bean.setMenu_level((Integer) row.get("menu_level"));
            }
            if (row.get("p_menu_id") != null)
            {
                bean.setP_menu_id((Integer) row.get("p_menu_id"));
            }
            if (row.get("menu_remark") != null)
            {
                bean.setMenu_remark((String) row.get("menu_remark"));
            }
        }
        return bean;
    }

    /**
     * delete records by domain, nonsupport null val
     */
    public int deleteRecordsByDomain(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_MENU_DELETE, dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new SysMenu() include query-params
     */
    public static List<SysMenu> getSingleTableListByAndMethod(AbstractSimpleDao dao, SysMenu queryInfo,
                                                              Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getId() != null)
        {
            paramMap.put("id", queryInfo.getId());
        }
        if (queryInfo.getMenu_name() != null)
        {
            paramMap.put("menu_name", queryInfo.getMenu_name());
        }
        if (queryInfo.getMenu_url() != null)
        {
            paramMap.put("menu_url", queryInfo.getMenu_url());
        }
        if (queryInfo.getMenu_sort() != null)
        {
            paramMap.put("menu_sort", queryInfo.getMenu_sort());
        }
        if (queryInfo.getMenu_level() != null)
        {
            paramMap.put("menu_level", queryInfo.getMenu_level());
        }
        if (queryInfo.getP_menu_id() != null)
        {
            paramMap.put("p_menu_id", queryInfo.getP_menu_id());
        }
        if (queryInfo.getMenu_remark() != null)
        {
            paramMap.put("menu_remark", queryInfo.getMenu_remark());
        }
        return dao.qryObjList(SqlString.AUTO2_SYS_MENU_LIST, setSymbolPKPrior(paramMap, isExcludePKFlag, false),
                              SysMenu.class);
    }

    /**
     * get list by "or" method, need new SysMenu() include query-params
     */
    public static List<SysMenu> getSingleTableListByOrMethod(AbstractSimpleDao dao, SysMenu queryInfo,
                                                             Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getId() != null)
        {
            paramMap.put("id", queryInfo.getId());
        }
        if (queryInfo.getMenu_name() != null)
        {
            paramMap.put("menu_name", queryInfo.getMenu_name());
        }
        if (queryInfo.getMenu_url() != null)
        {
            paramMap.put("menu_url", queryInfo.getMenu_url());
        }
        if (queryInfo.getMenu_sort() != null)
        {
            paramMap.put("menu_sort", queryInfo.getMenu_sort());
        }
        if (queryInfo.getMenu_level() != null)
        {
            paramMap.put("menu_level", queryInfo.getMenu_level());
        }
        if (queryInfo.getP_menu_id() != null)
        {
            paramMap.put("p_menu_id", queryInfo.getP_menu_id());
        }
        if (queryInfo.getMenu_remark() != null)
        {
            paramMap.put("menu_remark", queryInfo.getMenu_remark());
        }
        return dao.qryObjList(SqlString.AUTO2_SYS_MENU_LIST_BY_OR, setSymbolPKPrior(paramMap, isExcludePKFlag, true),
                              SysMenu.class);
    }

    private static Map<String, Object> setSymbolPKPrior(Map<String, Object> paramMap, Boolean exclude_pk, Boolean isOr)
    {
        if (paramMap == null || paramMap.size() == 0)
        {
            return paramMap;
        }
        if (exclude_pk)
        {
            for (String ss : pkColArr)
            {
                paramMap.put(ss + "Symbol", "exists");
            }
            paramMap.put("exclude_pk", true);
        }
        Boolean flag = true;
        if (isOr)
        {
            paramMap.put("params_exists", true);
            for (String ss : columnNameArr)
            {
                if (flag)
                {
                    if (paramMap.containsKey(ss) && !paramMap.containsKey(ss + "Symbol"))
                    {
                        paramMap.put(ss + "Symbol", " ");
                        flag = false;
                    }
                }
                else
                {
                    paramMap.put(ss + "Symbol", " or ");
                }
            }
        }
        return paramMap;
    }
}