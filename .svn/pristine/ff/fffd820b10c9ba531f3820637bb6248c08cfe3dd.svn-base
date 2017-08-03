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
import com.zx.emanage.util.gen.vo.SysLogVO;

/*
 * @version 2.0
 */

public class SysLog implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String unit_name;

    private String user_code;

    private String user_name;

    private String oper_behavior;

    private java.sql.Timestamp oper_timestamp;

    private String oper_ip;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id" };

    private static String[] columnNameArr = { "id", "unit_name", "user_code", "user_name", "oper_behavior",
                                             "oper_timestamp", "oper_ip" };

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer obj)
    {
        id = obj;
    }

    public String getUnit_name()
    {
        return unit_name;
    }

    public void setUnit_name(String obj)
    {
        unit_name = obj;
    }

    public String getUser_code()
    {
        return user_code;
    }

    public void setUser_code(String obj)
    {
        user_code = obj;
    }

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String obj)
    {
        user_name = obj;
    }

    public String getOper_behavior()
    {
        return oper_behavior;
    }

    public void setOper_behavior(String obj)
    {
        oper_behavior = obj;
    }

    public java.sql.Timestamp getOper_timestamp()
    {
        return oper_timestamp;
    }

    public void setOper_timestamp(java.sql.Timestamp obj)
    {
        oper_timestamp = obj;
    }

    public String getOper_ip()
    {
        return oper_ip;
    }

    public void setOper_ip(String obj)
    {
        oper_ip = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id", this.id);
        paramMap.put("unit_name", this.unit_name);
        paramMap.put("user_code", this.user_code);
        paramMap.put("user_name", this.user_name);
        paramMap.put("oper_behavior", this.oper_behavior);
        paramMap.put("oper_timestamp", this.oper_timestamp);
        paramMap.put("oper_ip", this.oper_ip);
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
        if (paramMap.get("unit_name") == null)
        {
            paramMap.remove("unit_name");
        }
        if (paramMap.get("user_code") == null)
        {
            paramMap.remove("user_code");
        }
        if (paramMap.get("user_name") == null)
        {
            paramMap.remove("user_name");
        }
        if (paramMap.get("oper_behavior") == null)
        {
            paramMap.remove("oper_behavior");
        }
        if (paramMap.get("oper_timestamp") == null)
        {
            paramMap.remove("oper_timestamp");
        }
        if (paramMap.get("oper_ip") == null)
        {
            paramMap.remove("oper_ip");
        }
        return paramMap;
    }

    /**
     * this table insert function, nonsupport null val
     */
    public int insertRecord(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_LOG_INSERT, setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return dao.updateByTemplate(SqlString.AUTO2_SYS_LOG_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_LOG_UPDATE_ALL, setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_LOG_UPDATE, setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static SysLog getRecordDomainByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        SysLog bean = dao.qryObj(SqlString.AUTO2_SYS_LOG_LIST, paramMap, SysLog.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static SysLogVO getRecordVOByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_SYS_LOG_LIST, paramMap);
        SysLogVO bean = new SysLogVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("id") != null)
            {
                bean.setId((Integer) row.get("id"));
            }
            if (row.get("unit_name") != null)
            {
                bean.setUnit_name((String) row.get("unit_name"));
            }
            if (row.get("user_code") != null)
            {
                bean.setUser_code((String) row.get("user_code"));
            }
            if (row.get("user_name") != null)
            {
                bean.setUser_name((String) row.get("user_name"));
            }
            if (row.get("oper_behavior") != null)
            {
                bean.setOper_behavior((String) row.get("oper_behavior"));
            }
            if (row.get("oper_timestamp") != null)
            {
                bean.setOper_timestamp(DateUtil.date2String((java.util.Date) row.get("oper_timestamp"),
                                                            "yyyy-MM-dd HH:mm:ss"));
            }
            if (row.get("oper_ip") != null)
            {
                bean.setOper_ip((String) row.get("oper_ip"));
            }
        }
        return bean;
    }

    /**
     * delete records by domain, nonsupport null val
     */
    public int deleteRecordsByDomain(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_LOG_DELETE, dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new SysLog() include query-params
     */
    public static List<SysLog> getSingleTableListByAndMethod(AbstractSimpleDao dao, SysLog queryInfo,
                                                             Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getId() != null)
        {
            paramMap.put("id", queryInfo.getId());
        }
        if (queryInfo.getUnit_name() != null)
        {
            paramMap.put("unit_name", queryInfo.getUnit_name());
        }
        if (queryInfo.getUser_code() != null)
        {
            paramMap.put("user_code", queryInfo.getUser_code());
        }
        if (queryInfo.getUser_name() != null)
        {
            paramMap.put("user_name", queryInfo.getUser_name());
        }
        if (queryInfo.getOper_behavior() != null)
        {
            paramMap.put("oper_behavior", queryInfo.getOper_behavior());
        }
        if (queryInfo.getOper_timestamp() != null)
        {
            paramMap.put("oper_timestamp", queryInfo.getOper_timestamp());
        }
        if (queryInfo.getOper_ip() != null)
        {
            paramMap.put("oper_ip", queryInfo.getOper_ip());
        }
        return dao.qryObjList(SqlString.AUTO2_SYS_LOG_LIST, setSymbolPKPrior(paramMap, isExcludePKFlag, false),
                              SysLog.class);
    }

    /**
     * get list by "or" method, need new SysLog() include query-params
     */
    public static List<SysLog> getSingleTableListByOrMethod(AbstractSimpleDao dao, SysLog queryInfo,
                                                            Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getId() != null)
        {
            paramMap.put("id", queryInfo.getId());
        }
        if (queryInfo.getUnit_name() != null)
        {
            paramMap.put("unit_name", queryInfo.getUnit_name());
        }
        if (queryInfo.getUser_code() != null)
        {
            paramMap.put("user_code", queryInfo.getUser_code());
        }
        if (queryInfo.getUser_name() != null)
        {
            paramMap.put("user_name", queryInfo.getUser_name());
        }
        if (queryInfo.getOper_behavior() != null)
        {
            paramMap.put("oper_behavior", queryInfo.getOper_behavior());
        }
        if (queryInfo.getOper_timestamp() != null)
        {
            paramMap.put("oper_timestamp", queryInfo.getOper_timestamp());
        }
        if (queryInfo.getOper_ip() != null)
        {
            paramMap.put("oper_ip", queryInfo.getOper_ip());
        }
        return dao.qryObjList(SqlString.AUTO2_SYS_LOG_LIST_BY_OR, setSymbolPKPrior(paramMap, isExcludePKFlag, true),
                              SysLog.class);
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