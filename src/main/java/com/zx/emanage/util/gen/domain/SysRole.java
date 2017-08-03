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
import com.zx.emanage.util.gen.vo.SysRoleVO;

/*
 * @version 2.0
 */

public class SysRole implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String role_name;

    private String enable_flag;

    private String create_user;

    private java.sql.Timestamp create_timestamp;

    private String last_update_user;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_remark;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "id" };

    private static String[] columnNameArr = { "id", "role_name", "enable_flag", "create_user", "create_timestamp",
                                             "last_update_user", "last_update_timestamp", "last_update_remark" };

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer obj)
    {
        id = obj;
    }

    public String getRole_name()
    {
        return role_name;
    }

    public void setRole_name(String obj)
    {
        role_name = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }

    public String getCreate_user()
    {
        return create_user;
    }

    public void setCreate_user(String obj)
    {
        create_user = obj;
    }

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp obj)
    {
        create_timestamp = obj;
    }

    public String getLast_update_user()
    {
        return last_update_user;
    }

    public void setLast_update_user(String obj)
    {
        last_update_user = obj;
    }

    public java.sql.Timestamp getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(java.sql.Timestamp obj)
    {
        last_update_timestamp = obj;
    }

    public String getLast_update_remark()
    {
        return last_update_remark;
    }

    public void setLast_update_remark(String obj)
    {
        last_update_remark = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("id", this.id);
        paramMap.put("role_name", this.role_name);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("create_user", this.create_user);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("last_update_user", this.last_update_user);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_remark", this.last_update_remark);
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
        if (paramMap.get("role_name") == null)
        {
            paramMap.remove("role_name");
        }
        if (paramMap.get("enable_flag") == null)
        {
            paramMap.remove("enable_flag");
        }
        if (paramMap.get("create_user") == null)
        {
            paramMap.remove("create_user");
        }
        if (paramMap.get("create_timestamp") == null)
        {
            paramMap.remove("create_timestamp");
        }
        if (paramMap.get("last_update_user") == null)
        {
            paramMap.remove("last_update_user");
        }
        if (paramMap.get("last_update_timestamp") == null)
        {
            paramMap.remove("last_update_timestamp");
        }
        if (paramMap.get("last_update_remark") == null)
        {
            paramMap.remove("last_update_remark");
        }
        return paramMap;
    }

    /**
     * this table insert function, nonsupport null val
     */
    public int insertRecord(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_ROLE_INSERT, setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return dao.updateByTemplate(SqlString.AUTO2_SYS_ROLE_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_ROLE_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_ROLE_UPDATE, setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static SysRole getRecordDomainByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        SysRole bean = dao.qryObj(SqlString.AUTO2_SYS_ROLE_LIST, paramMap, SysRole.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static SysRoleVO getRecordVOByPK(AbstractSimpleDao dao, Integer id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_SYS_ROLE_LIST, paramMap);
        SysRoleVO bean = new SysRoleVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("id") != null)
            {
                bean.setId((Integer) row.get("id"));
            }
            if (row.get("role_name") != null)
            {
                bean.setRole_name((String) row.get("role_name"));
            }
            if (row.get("enable_flag") != null)
            {
                bean.setEnable_flag((String) row.get("enable_flag"));
            }
            if (row.get("create_user") != null)
            {
                bean.setCreate_user((String) row.get("create_user"));
            }
            if (row.get("create_timestamp") != null)
            {
                bean.setCreate_timestamp(DateUtil.date2String((java.util.Date) row.get("create_timestamp"),
                                                              "yyyy-MM-dd HH:mm:ss"));
            }
            if (row.get("last_update_user") != null)
            {
                bean.setLast_update_user((String) row.get("last_update_user"));
            }
            if (row.get("last_update_timestamp") != null)
            {
                bean.setLast_update_timestamp(DateUtil.date2String((java.util.Date) row.get("last_update_timestamp"),
                                                                   "yyyy-MM-dd HH:mm:ss"));
            }
            if (row.get("last_update_remark") != null)
            {
                bean.setLast_update_remark((String) row.get("last_update_remark"));
            }
        }
        return bean;
    }

    /**
     * delete records by domain, nonsupport null val
     */
    public int deleteRecordsByDomain(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_SYS_ROLE_DELETE, dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new SysRole() include query-params
     */
    public static List<SysRole> getSingleTableListByAndMethod(AbstractSimpleDao dao, SysRole queryInfo,
                                                              Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getId() != null)
        {
            paramMap.put("id", queryInfo.getId());
        }
        if (queryInfo.getRole_name() != null)
        {
            paramMap.put("role_name", queryInfo.getRole_name());
        }
        if (queryInfo.getEnable_flag() != null)
        {
            paramMap.put("enable_flag", queryInfo.getEnable_flag());
        }
        if (queryInfo.getCreate_user() != null)
        {
            paramMap.put("create_user", queryInfo.getCreate_user());
        }
        if (queryInfo.getCreate_timestamp() != null)
        {
            paramMap.put("create_timestamp", queryInfo.getCreate_timestamp());
        }
        if (queryInfo.getLast_update_user() != null)
        {
            paramMap.put("last_update_user", queryInfo.getLast_update_user());
        }
        if (queryInfo.getLast_update_timestamp() != null)
        {
            paramMap.put("last_update_timestamp", queryInfo.getLast_update_timestamp());
        }
        if (queryInfo.getLast_update_remark() != null)
        {
            paramMap.put("last_update_remark", queryInfo.getLast_update_remark());
        }
        return dao.qryObjList(SqlString.AUTO2_SYS_ROLE_LIST, setSymbolPKPrior(paramMap, isExcludePKFlag, false),
                              SysRole.class);
    }

    /**
     * get list by "or" method, need new SysRole() include query-params
     */
    public static List<SysRole> getSingleTableListByOrMethod(AbstractSimpleDao dao, SysRole queryInfo,
                                                             Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getId() != null)
        {
            paramMap.put("id", queryInfo.getId());
        }
        if (queryInfo.getRole_name() != null)
        {
            paramMap.put("role_name", queryInfo.getRole_name());
        }
        if (queryInfo.getEnable_flag() != null)
        {
            paramMap.put("enable_flag", queryInfo.getEnable_flag());
        }
        if (queryInfo.getCreate_user() != null)
        {
            paramMap.put("create_user", queryInfo.getCreate_user());
        }
        if (queryInfo.getCreate_timestamp() != null)
        {
            paramMap.put("create_timestamp", queryInfo.getCreate_timestamp());
        }
        if (queryInfo.getLast_update_user() != null)
        {
            paramMap.put("last_update_user", queryInfo.getLast_update_user());
        }
        if (queryInfo.getLast_update_timestamp() != null)
        {
            paramMap.put("last_update_timestamp", queryInfo.getLast_update_timestamp());
        }
        if (queryInfo.getLast_update_remark() != null)
        {
            paramMap.put("last_update_remark", queryInfo.getLast_update_remark());
        }
        return dao.qryObjList(SqlString.AUTO2_SYS_ROLE_LIST_BY_OR, setSymbolPKPrior(paramMap, isExcludePKFlag, true),
                              SysRole.class);
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