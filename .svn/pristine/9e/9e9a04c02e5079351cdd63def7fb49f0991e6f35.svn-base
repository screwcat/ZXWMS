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
import com.zx.emanage.util.gen.vo.WmsSysDictDataVO;

/*
 * @version 2.0
 */

public class WmsSysDictData implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer wms_sys_dict_data_id;

    private String value_code;

    private String value_meaning;

    private Integer wms_sys_dict_id;

    private Integer p_wms_sys_dict_data_id;

    private Integer sort_order;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_sys_dict_data_id" };

    private static String[] columnNameArr = { "wms_sys_dict_data_id", "value_code", "value_meaning", "wms_sys_dict_id",
                                             "p_wms_sys_dict_data_id", "sort_order" };

    public Integer getWms_sys_dict_data_id()
    {
        return wms_sys_dict_data_id;
    }

    public void setWms_sys_dict_data_id(Integer obj)
    {
        wms_sys_dict_data_id = obj;
    }

    public String getValue_code()
    {
        return value_code;
    }

    public void setValue_code(String obj)
    {
        value_code = obj;
    }

    public String getValue_meaning()
    {
        return value_meaning;
    }

    public void setValue_meaning(String obj)
    {
        value_meaning = obj;
    }

    public Integer getWms_sys_dict_id()
    {
        return wms_sys_dict_id;
    }

    public void setWms_sys_dict_id(Integer obj)
    {
        wms_sys_dict_id = obj;
    }

    public Integer getP_wms_sys_dict_data_id()
    {
        return p_wms_sys_dict_data_id;
    }

    public void setP_wms_sys_dict_data_id(Integer obj)
    {
        p_wms_sys_dict_data_id = obj;
    }

    public Integer getSort_order()
    {
        return sort_order;
    }

    public void setSort_order(Integer obj)
    {
        sort_order = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_sys_dict_data_id", this.wms_sys_dict_data_id);
        paramMap.put("value_code", this.value_code);
        paramMap.put("value_meaning", this.value_meaning);
        paramMap.put("wms_sys_dict_id", this.wms_sys_dict_id);
        paramMap.put("p_wms_sys_dict_data_id", this.p_wms_sys_dict_data_id);
        paramMap.put("sort_order", this.sort_order);
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
        if (paramMap.get("wms_sys_dict_data_id") == null)
        {
            paramMap.remove("wms_sys_dict_data_id");
        }
        if (paramMap.get("value_code") == null)
        {
            paramMap.remove("value_code");
        }
        if (paramMap.get("value_meaning") == null)
        {
            paramMap.remove("value_meaning");
        }
        if (paramMap.get("wms_sys_dict_id") == null)
        {
            paramMap.remove("wms_sys_dict_id");
        }
        if (paramMap.get("p_wms_sys_dict_data_id") == null)
        {
            paramMap.remove("p_wms_sys_dict_data_id");
        }
        if (paramMap.get("sort_order") == null)
        {
            paramMap.remove("sort_order");
        }
        return paramMap;
    }

    /**
     * this table insert function, nonsupport null val
     */
    public int insertRecord(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_DICT_DATA_INSERT, setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer wms_sys_dict_data_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_dict_data_id", wms_sys_dict_data_id);
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_DICT_DATA_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_DICT_DATA_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_DICT_DATA_UPDATE,
                                    setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static WmsSysDictData getRecordDomainByPK(AbstractSimpleDao dao, Integer wms_sys_dict_data_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_dict_data_id", wms_sys_dict_data_id);
        WmsSysDictData bean = dao.qryObj(SqlString.AUTO2_WMS_SYS_DICT_DATA_LIST, paramMap, WmsSysDictData.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static WmsSysDictDataVO getRecordVOByPK(AbstractSimpleDao dao, Integer wms_sys_dict_data_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_dict_data_id", wms_sys_dict_data_id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_WMS_SYS_DICT_DATA_LIST, paramMap);
        WmsSysDictDataVO bean = new WmsSysDictDataVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("wms_sys_dict_data_id") != null)
            {
                bean.setWms_sys_dict_data_id((Integer) row.get("wms_sys_dict_data_id"));
            }
            if (row.get("value_code") != null)
            {
                bean.setValue_code((String) row.get("value_code"));
            }
            if (row.get("value_meaning") != null)
            {
                bean.setValue_meaning((String) row.get("value_meaning"));
            }
            if (row.get("wms_sys_dict_id") != null)
            {
                bean.setWms_sys_dict_id((Integer) row.get("wms_sys_dict_id"));
            }
            if (row.get("p_wms_sys_dict_data_id") != null)
            {
                bean.setP_wms_sys_dict_data_id((Integer) row.get("p_wms_sys_dict_data_id"));
            }
            if (row.get("sort_order") != null)
            {
                bean.setSort_order((Integer) row.get("sort_order"));
            }
        }
        return bean;
    }

    /**
     * delete records by domain, nonsupport null val
     */
    public int deleteRecordsByDomain(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_DICT_DATA_DELETE, dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new WmsSysDictData() include query-params
     */
    public static List<WmsSysDictData> getSingleTableListByAndMethod(AbstractSimpleDao dao, WmsSysDictData queryInfo,
                                                                     Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_sys_dict_data_id() != null)
        {
            paramMap.put("wms_sys_dict_data_id", queryInfo.getWms_sys_dict_data_id());
        }
        if (queryInfo.getValue_code() != null)
        {
            paramMap.put("value_code", queryInfo.getValue_code());
        }
        if (queryInfo.getValue_meaning() != null)
        {
            paramMap.put("value_meaning", queryInfo.getValue_meaning());
        }
        if (queryInfo.getWms_sys_dict_id() != null)
        {
            paramMap.put("wms_sys_dict_id", queryInfo.getWms_sys_dict_id());
        }
        if (queryInfo.getP_wms_sys_dict_data_id() != null)
        {
            paramMap.put("p_wms_sys_dict_data_id", queryInfo.getP_wms_sys_dict_data_id());
        }
        if (queryInfo.getSort_order() != null)
        {
            paramMap.put("sort_order", queryInfo.getSort_order());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_SYS_DICT_DATA_LIST,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, false), WmsSysDictData.class);
    }

    /**
     * get list by "or" method, need new WmsSysDictData() include query-params
     */
    public static List<WmsSysDictData> getSingleTableListByOrMethod(AbstractSimpleDao dao, WmsSysDictData queryInfo,
                                                                    Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_sys_dict_data_id() != null)
        {
            paramMap.put("wms_sys_dict_data_id", queryInfo.getWms_sys_dict_data_id());
        }
        if (queryInfo.getValue_code() != null)
        {
            paramMap.put("value_code", queryInfo.getValue_code());
        }
        if (queryInfo.getValue_meaning() != null)
        {
            paramMap.put("value_meaning", queryInfo.getValue_meaning());
        }
        if (queryInfo.getWms_sys_dict_id() != null)
        {
            paramMap.put("wms_sys_dict_id", queryInfo.getWms_sys_dict_id());
        }
        if (queryInfo.getP_wms_sys_dict_data_id() != null)
        {
            paramMap.put("p_wms_sys_dict_data_id", queryInfo.getP_wms_sys_dict_data_id());
        }
        if (queryInfo.getSort_order() != null)
        {
            paramMap.put("sort_order", queryInfo.getSort_order());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_SYS_DICT_DATA_LIST_BY_OR,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, true), WmsSysDictData.class);
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