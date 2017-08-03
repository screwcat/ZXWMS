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
import com.zx.emanage.util.gen.vo.WmsSysDictVO;

/*
 * @version 2.0
 */

public class WmsSysDict implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer wms_sys_dict_id;

    private String dict_type;

    private String dict_code;

    private String dict_name;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_sys_dict_id" };

    private static String[] columnNameArr = { "wms_sys_dict_id", "dict_type", "dict_code", "dict_name" };

    public Integer getWms_sys_dict_id()
    {
        return wms_sys_dict_id;
    }

    public void setWms_sys_dict_id(Integer obj)
    {
        wms_sys_dict_id = obj;
    }

    public String getDict_type()
    {
        return dict_type;
    }

    public void setDict_type(String obj)
    {
        dict_type = obj;
    }

    public String getDict_code()
    {
        return dict_code;
    }

    public void setDict_code(String obj)
    {
        dict_code = obj;
    }

    public String getDict_name()
    {
        return dict_name;
    }

    public void setDict_name(String obj)
    {
        dict_name = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_sys_dict_id", this.wms_sys_dict_id);
        paramMap.put("dict_type", this.dict_type);
        paramMap.put("dict_code", this.dict_code);
        paramMap.put("dict_name", this.dict_name);
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
        if (paramMap.get("wms_sys_dict_id") == null)
        {
            paramMap.remove("wms_sys_dict_id");
        }
        if (paramMap.get("dict_type") == null)
        {
            paramMap.remove("dict_type");
        }
        if (paramMap.get("dict_code") == null)
        {
            paramMap.remove("dict_code");
        }
        if (paramMap.get("dict_name") == null)
        {
            paramMap.remove("dict_name");
        }
        return paramMap;
    }

    /**
     * this table insert function, nonsupport null val
     */
    public int insertRecord(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_DICT_INSERT, setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer wms_sys_dict_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_dict_id", wms_sys_dict_id);
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_DICT_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_DICT_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_DICT_UPDATE,
                                    setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static WmsSysDict getRecordDomainByPK(AbstractSimpleDao dao, Integer wms_sys_dict_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_dict_id", wms_sys_dict_id);
        WmsSysDict bean = dao.qryObj(SqlString.AUTO2_WMS_SYS_DICT_LIST, paramMap, WmsSysDict.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static WmsSysDictVO getRecordVOByPK(AbstractSimpleDao dao, Integer wms_sys_dict_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_dict_id", wms_sys_dict_id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_WMS_SYS_DICT_LIST, paramMap);
        WmsSysDictVO bean = new WmsSysDictVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("wms_sys_dict_id") != null)
            {
                bean.setWms_sys_dict_id((Integer) row.get("wms_sys_dict_id"));
            }
            if (row.get("dict_type") != null)
            {
                bean.setDict_type((String) row.get("dict_type"));
            }
            if (row.get("dict_code") != null)
            {
                bean.setDict_code((String) row.get("dict_code"));
            }
            if (row.get("dict_name") != null)
            {
                bean.setDict_name((String) row.get("dict_name"));
            }
        }
        return bean;
    }

    /**
     * delete records by domain, nonsupport null val
     */
    public int deleteRecordsByDomain(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_DICT_DELETE, dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new WmsSysDict() include query-params
     */
    public static List<WmsSysDict> getSingleTableListByAndMethod(AbstractSimpleDao dao, WmsSysDict queryInfo,
                                                                 Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_sys_dict_id() != null)
        {
            paramMap.put("wms_sys_dict_id", queryInfo.getWms_sys_dict_id());
        }
        if (queryInfo.getDict_type() != null)
        {
            paramMap.put("dict_type", queryInfo.getDict_type());
        }
        if (queryInfo.getDict_code() != null)
        {
            paramMap.put("dict_code", queryInfo.getDict_code());
        }
        if (queryInfo.getDict_name() != null)
        {
            paramMap.put("dict_name", queryInfo.getDict_name());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_SYS_DICT_LIST, setSymbolPKPrior(paramMap, isExcludePKFlag, false),
                              WmsSysDict.class);
    }

    /**
     * get list by "or" method, need new WmsSysDict() include query-params
     */
    public static List<WmsSysDict> getSingleTableListByOrMethod(AbstractSimpleDao dao, WmsSysDict queryInfo,
                                                                Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_sys_dict_id() != null)
        {
            paramMap.put("wms_sys_dict_id", queryInfo.getWms_sys_dict_id());
        }
        if (queryInfo.getDict_type() != null)
        {
            paramMap.put("dict_type", queryInfo.getDict_type());
        }
        if (queryInfo.getDict_code() != null)
        {
            paramMap.put("dict_code", queryInfo.getDict_code());
        }
        if (queryInfo.getDict_name() != null)
        {
            paramMap.put("dict_name", queryInfo.getDict_name());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_SYS_DICT_LIST_BY_OR,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, true), WmsSysDict.class);
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