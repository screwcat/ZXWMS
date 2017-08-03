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
import com.zx.emanage.util.gen.vo.SysFunctionVO;

/*
 * @version 2.0
 */

public class SysFunction implements Serializable
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
        if (paramMap.get("func_name") == null)
        {
            paramMap.remove("func_name");
        }
        if (paramMap.get("func_method") == null)
        {
            paramMap.remove("func_method");
        }
        if (paramMap.get("func_sort") == null)
        {
            paramMap.remove("func_sort");
        }
        if (paramMap.get("menu_id") == null)
        {
            paramMap.remove("menu_id");
        }
        if (paramMap.get("default_flag") == null)
        {
            paramMap.remove("default_flag");
        }
        if (paramMap.get("func_remark") == null)
        {
            paramMap.remove("func_remark");
        }
        return paramMap;
    }

    /**
     * this table insert function, nonsupport null val
     */
    public int insertRecord(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_FUNCTION_INSERT, setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return dao.updateByTemplate(SqlString.AUTO2_SYS_FUNCTION_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_FUNCTION_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_FUNCTION_UPDATE,
                                    setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static SysFunction getRecordDomainByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        SysFunction bean = dao.qryObj(SqlString.AUTO2_SYS_FUNCTION_LIST, paramMap, SysFunction.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static SysFunctionVO getRecordVOByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_SYS_FUNCTION_LIST, paramMap);
        SysFunctionVO bean = new SysFunctionVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("id") != null)
            {
                bean.setId((Integer) row.get("id"));
            }
            if (row.get("func_name") != null)
            {
                bean.setFunc_name((String) row.get("func_name"));
            }
            if (row.get("func_method") != null)
            {
                bean.setFunc_method((String) row.get("func_method"));
            }
            if (row.get("func_sort") != null)
            {
                bean.setFunc_sort((Integer) row.get("func_sort"));
            }
            if (row.get("menu_id") != null)
            {
                bean.setMenu_id((Integer) row.get("menu_id"));
            }
            if (row.get("default_flag") != null)
            {
                bean.setDefault_flag((String) row.get("default_flag"));
            }
            if (row.get("func_remark") != null)
            {
                bean.setFunc_remark((String) row.get("func_remark"));
            }
        }
        return bean;
    }

    /**
     * delete records by domain, nonsupport null val
     */
    public int deleteRecordsByDomain(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_FUNCTION_DELETE, dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new SysFunction() include query-params
     */
    public static List<SysFunction> getSingleTableListByAndMethod(AbstractSimpleDao dao, SysFunction queryInfo,
                                                                  Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getId() != null)
        {
            paramMap.put("id", queryInfo.getId());
        }
        if (queryInfo.getFunc_name() != null)
        {
            paramMap.put("func_name", queryInfo.getFunc_name());
        }
        if (queryInfo.getFunc_method() != null)
        {
            paramMap.put("func_method", queryInfo.getFunc_method());
        }
        if (queryInfo.getFunc_sort() != null)
        {
            paramMap.put("func_sort", queryInfo.getFunc_sort());
        }
        if (queryInfo.getMenu_id() != null)
        {
            paramMap.put("menu_id", queryInfo.getMenu_id());
        }
        if (queryInfo.getDefault_flag() != null)
        {
            paramMap.put("default_flag", queryInfo.getDefault_flag());
        }
        if (queryInfo.getFunc_remark() != null)
        {
            paramMap.put("func_remark", queryInfo.getFunc_remark());
        }
        return dao.qryObjList(SqlString.AUTO2_SYS_FUNCTION_LIST, setSymbolPKPrior(paramMap, isExcludePKFlag, false),
                              SysFunction.class);
    }

    /**
     * get list by "or" method, need new SysFunction() include query-params
     */
    public static List<SysFunction> getSingleTableListByOrMethod(AbstractSimpleDao dao, SysFunction queryInfo,
                                                                 Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getId() != null)
        {
            paramMap.put("id", queryInfo.getId());
        }
        if (queryInfo.getFunc_name() != null)
        {
            paramMap.put("func_name", queryInfo.getFunc_name());
        }
        if (queryInfo.getFunc_method() != null)
        {
            paramMap.put("func_method", queryInfo.getFunc_method());
        }
        if (queryInfo.getFunc_sort() != null)
        {
            paramMap.put("func_sort", queryInfo.getFunc_sort());
        }
        if (queryInfo.getMenu_id() != null)
        {
            paramMap.put("menu_id", queryInfo.getMenu_id());
        }
        if (queryInfo.getDefault_flag() != null)
        {
            paramMap.put("default_flag", queryInfo.getDefault_flag());
        }
        if (queryInfo.getFunc_remark() != null)
        {
            paramMap.put("func_remark", queryInfo.getFunc_remark());
        }
        return dao.qryObjList(SqlString.AUTO2_SYS_FUNCTION_LIST_BY_OR,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, true), SysFunction.class);
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