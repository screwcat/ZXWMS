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
import com.zx.emanage.util.gen.vo.WmsSysSequenceVO;

/*
 * @version 2.0
 */

public class WmsSysSequence implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer wms_sys_sequence_id;

    private String sequence_code;

    private String sequence_meaning;

    private Long sequence_value;

    private java.sql.Date update_date;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_sys_sequence_id" };

    private static String[] columnNameArr = { "wms_sys_sequence_id", "sequence_code", "sequence_meaning",
                                             "sequence_value", "update_date" };

    public Integer getWms_sys_sequence_id()
    {
        return wms_sys_sequence_id;
    }

    public void setWms_sys_sequence_id(Integer obj)
    {
        wms_sys_sequence_id = obj;
    }

    public String getSequence_code()
    {
        return sequence_code;
    }

    public void setSequence_code(String obj)
    {
        sequence_code = obj;
    }

    public String getSequence_meaning()
    {
        return sequence_meaning;
    }

    public void setSequence_meaning(String obj)
    {
        sequence_meaning = obj;
    }

    public Long getSequence_value()
    {
        return sequence_value;
    }

    public void setSequence_value(Long obj)
    {
        sequence_value = obj;
    }

    public java.sql.Date getUpdate_date()
    {
        return update_date;
    }

    public void setUpdate_date(java.sql.Date obj)
    {
        update_date = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_sys_sequence_id", this.wms_sys_sequence_id);
        paramMap.put("sequence_code", this.sequence_code);
        paramMap.put("sequence_meaning", this.sequence_meaning);
        paramMap.put("sequence_value", this.sequence_value);
        paramMap.put("update_date", this.update_date);
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
        if (paramMap.get("wms_sys_sequence_id") == null)
        {
            paramMap.remove("wms_sys_sequence_id");
        }
        if (paramMap.get("sequence_code") == null)
        {
            paramMap.remove("sequence_code");
        }
        if (paramMap.get("sequence_meaning") == null)
        {
            paramMap.remove("sequence_meaning");
        }
        if (paramMap.get("sequence_value") == null)
        {
            paramMap.remove("sequence_value");
        }
        if (paramMap.get("update_date") == null)
        {
            paramMap.remove("update_date");
        }
        return paramMap;
    }

    /**
     * this table insert function, nonsupport null val
     */
    public int insertRecord(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_SEQUENCE_INSERT, setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer wms_sys_sequence_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_sequence_id", wms_sys_sequence_id);
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_SEQUENCE_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_SEQUENCE_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_SEQUENCE_UPDATE,
                                    setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static WmsSysSequence getRecordDomainByPK(AbstractSimpleDao dao, Integer wms_sys_sequence_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_sequence_id", wms_sys_sequence_id);
        WmsSysSequence bean = dao.qryObj(SqlString.AUTO2_WMS_SYS_SEQUENCE_LIST, paramMap, WmsSysSequence.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static WmsSysSequenceVO getRecordVOByPK(AbstractSimpleDao dao, Integer wms_sys_sequence_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_sequence_id", wms_sys_sequence_id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_WMS_SYS_SEQUENCE_LIST, paramMap);
        WmsSysSequenceVO bean = new WmsSysSequenceVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("wms_sys_sequence_id") != null)
            {
                bean.setWms_sys_sequence_id((Integer) row.get("wms_sys_sequence_id"));
            }
            if (row.get("sequence_code") != null)
            {
                bean.setSequence_code((String) row.get("sequence_code"));
            }
            if (row.get("sequence_meaning") != null)
            {
                bean.setSequence_meaning((String) row.get("sequence_meaning"));
            }
            if (row.get("sequence_value") != null)
            {
                bean.setSequence_value((Long) row.get("sequence_value"));
            }
            if (row.get("update_date") != null)
            {
                bean.setUpdate_date(DateUtil.date2String((java.util.Date) row.get("update_date"), "yyyy-MM-dd"));
            }
        }
        return bean;
    }

    /**
     * delete records by domain, nonsupport null val
     */
    public int deleteRecordsByDomain(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_SYS_SEQUENCE_DELETE, dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new WmsSysSequence() include query-params
     */
    public static List<WmsSysSequence> getSingleTableListByAndMethod(AbstractSimpleDao dao, WmsSysSequence queryInfo,
                                                                     Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_sys_sequence_id() != null)
        {
            paramMap.put("wms_sys_sequence_id", queryInfo.getWms_sys_sequence_id());
        }
        if (queryInfo.getSequence_code() != null)
        {
            paramMap.put("sequence_code", queryInfo.getSequence_code());
        }
        if (queryInfo.getSequence_meaning() != null)
        {
            paramMap.put("sequence_meaning", queryInfo.getSequence_meaning());
        }
        if (queryInfo.getSequence_value() != null)
        {
            paramMap.put("sequence_value", queryInfo.getSequence_value());
        }
        if (queryInfo.getUpdate_date() != null)
        {
            paramMap.put("update_date", queryInfo.getUpdate_date());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_SYS_SEQUENCE_LIST,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, false), WmsSysSequence.class);
    }

    /**
     * get list by "or" method, need new WmsSysSequence() include query-params
     */
    public static List<WmsSysSequence> getSingleTableListByOrMethod(AbstractSimpleDao dao, WmsSysSequence queryInfo,
                                                                    Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_sys_sequence_id() != null)
        {
            paramMap.put("wms_sys_sequence_id", queryInfo.getWms_sys_sequence_id());
        }
        if (queryInfo.getSequence_code() != null)
        {
            paramMap.put("sequence_code", queryInfo.getSequence_code());
        }
        if (queryInfo.getSequence_meaning() != null)
        {
            paramMap.put("sequence_meaning", queryInfo.getSequence_meaning());
        }
        if (queryInfo.getSequence_value() != null)
        {
            paramMap.put("sequence_value", queryInfo.getSequence_value());
        }
        if (queryInfo.getUpdate_date() != null)
        {
            paramMap.put("update_date", queryInfo.getUpdate_date());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_SYS_SEQUENCE_LIST_BY_OR,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, true), WmsSysSequence.class);
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