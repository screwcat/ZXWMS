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
import com.zx.emanage.util.gen.vo.SysRoleMenuFunctionVO;

/*
 * @version 2.0
 */

public class SysRoleMenuFunction implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer role_id;

    private Integer menu_id;

    private Integer func_id;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id" };

    private static String[] columnNameArr = { "id", "role_id", "menu_id", "func_id" };

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer obj)
    {
        id = obj;
    }

    public Integer getRole_id()
    {
        return role_id;
    }

    public void setRole_id(Integer obj)
    {
        role_id = obj;
    }

    public Integer getMenu_id()
    {
        return menu_id;
    }

    public void setMenu_id(Integer obj)
    {
        menu_id = obj;
    }

    public Integer getFunc_id()
    {
        return func_id;
    }

    public void setFunc_id(Integer obj)
    {
        func_id = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id", this.id);
        paramMap.put("role_id", this.role_id);
        paramMap.put("menu_id", this.menu_id);
        paramMap.put("func_id", this.func_id);
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
        if (paramMap.get("role_id") == null)
        {
            paramMap.remove("role_id");
        }
        if (paramMap.get("menu_id") == null)
        {
            paramMap.remove("menu_id");
        }
        if (paramMap.get("func_id") == null)
        {
            paramMap.remove("func_id");
        }
        return paramMap;
    }

    /**
     * this table insert function, nonsupport null val
     */
    public int insertRecord(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_ROLE_MENU_FUNCTION_INSERT, setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return dao.updateByTemplate(SqlString.AUTO2_SYS_ROLE_MENU_FUNCTION_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_ROLE_MENU_FUNCTION_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_ROLE_MENU_FUNCTION_UPDATE,
                                    setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static SysRoleMenuFunction getRecordDomainByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        SysRoleMenuFunction bean = dao.qryObj(SqlString.AUTO2_SYS_ROLE_MENU_FUNCTION_LIST, paramMap,
                                              SysRoleMenuFunction.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static SysRoleMenuFunctionVO getRecordVOByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_SYS_ROLE_MENU_FUNCTION_LIST,
                                                                       paramMap);
        SysRoleMenuFunctionVO bean = new SysRoleMenuFunctionVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("id") != null)
            {
                bean.setId((Integer) row.get("id"));
            }
            if (row.get("role_id") != null)
            {
                bean.setRole_id((Integer) row.get("role_id"));
            }
            if (row.get("menu_id") != null)
            {
                bean.setMenu_id((Integer) row.get("menu_id"));
            }
            if (row.get("func_id") != null)
            {
                bean.setFunc_id((Integer) row.get("func_id"));
            }
        }
        return bean;
    }

    /**
     * delete records by domain, nonsupport null val
     */
    public int deleteRecordsByDomain(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_ROLE_MENU_FUNCTION_DELETE,
                                    dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new SysRoleMenuFunction() include
     * query-params
     */
    public static List<SysRoleMenuFunction> getSingleTableListByAndMethod(AbstractSimpleDao dao,
                                                                          SysRoleMenuFunction queryInfo,
                                                                          Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getId() != null)
        {
            paramMap.put("id", queryInfo.getId());
        }
        if (queryInfo.getRole_id() != null)
        {
            paramMap.put("role_id", queryInfo.getRole_id());
        }
        if (queryInfo.getMenu_id() != null)
        {
            paramMap.put("menu_id", queryInfo.getMenu_id());
        }
        if (queryInfo.getFunc_id() != null)
        {
            paramMap.put("func_id", queryInfo.getFunc_id());
        }
        return dao.qryObjList(SqlString.AUTO2_SYS_ROLE_MENU_FUNCTION_LIST,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, false), SysRoleMenuFunction.class);
    }

    /**
     * get list by "or" method, need new SysRoleMenuFunction() include
     * query-params
     */
    public static List<SysRoleMenuFunction> getSingleTableListByOrMethod(AbstractSimpleDao dao,
                                                                         SysRoleMenuFunction queryInfo,
                                                                         Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getId() != null)
        {
            paramMap.put("id", queryInfo.getId());
        }
        if (queryInfo.getRole_id() != null)
        {
            paramMap.put("role_id", queryInfo.getRole_id());
        }
        if (queryInfo.getMenu_id() != null)
        {
            paramMap.put("menu_id", queryInfo.getMenu_id());
        }
        if (queryInfo.getFunc_id() != null)
        {
            paramMap.put("func_id", queryInfo.getFunc_id());
        }
        return dao.qryObjList(SqlString.AUTO2_SYS_ROLE_MENU_FUNCTION_LIST_BY_OR,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, true), SysRoleMenuFunction.class);
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