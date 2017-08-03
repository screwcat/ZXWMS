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
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineWorkinfoVO;

/*
 * @version 2.0
 */

public class WmsCreCustomerChangeLineWorkinfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_customer_change_line_workinfo_id;

    private String work_unit_full_name;

    private String work_unit_address_province;

    private String work_unit_address_city;

    private String work_unit_address_district;

    private String work_unit_address_more;

    private String work_unit_telephone;

    private java.sql.Date work_unit_entry_date;

    private String work_unit_department;

    private String work_unit_duty;

    private String work_unit_property;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private Integer create_user_id;

    private java.sql.Timestamp create_timestamp;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String enable_flag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_customer_change_line_workinfo_id" };

    private static String[] columnNameArr = { "wms_cre_customer_change_line_workinfo_id", "work_unit_full_name",
                                             "work_unit_address_province", "work_unit_address_city",
                                             "work_unit_address_district", "work_unit_address_more",
                                             "work_unit_telephone", "work_unit_entry_date", "work_unit_department",
                                             "work_unit_duty", "work_unit_property",
                                             "wms_cre_credit_line_customer_change_head_id", "create_user_id",
                                             "create_timestamp", "last_update_user_id", "last_update_timestamp",
                                             "enable_flag" };

    public Integer getWms_cre_customer_change_line_workinfo_id()
    {
        return wms_cre_customer_change_line_workinfo_id;
    }

    public void setWms_cre_customer_change_line_workinfo_id(Integer obj)
    {
        wms_cre_customer_change_line_workinfo_id = obj;
    }

    public String getWork_unit_full_name()
    {
        return work_unit_full_name;
    }

    public void setWork_unit_full_name(String obj)
    {
        work_unit_full_name = obj;
    }

    public String getWork_unit_address_province()
    {
        return work_unit_address_province;
    }

    public void setWork_unit_address_province(String obj)
    {
        work_unit_address_province = obj;
    }

    public String getWork_unit_address_city()
    {
        return work_unit_address_city;
    }

    public void setWork_unit_address_city(String obj)
    {
        work_unit_address_city = obj;
    }

    public String getWork_unit_address_district()
    {
        return work_unit_address_district;
    }

    public void setWork_unit_address_district(String obj)
    {
        work_unit_address_district = obj;
    }

    public String getWork_unit_address_more()
    {
        return work_unit_address_more;
    }

    public void setWork_unit_address_more(String obj)
    {
        work_unit_address_more = obj;
    }

    public String getWork_unit_telephone()
    {
        return work_unit_telephone;
    }

    public void setWork_unit_telephone(String obj)
    {
        work_unit_telephone = obj;
    }

    public java.sql.Date getWork_unit_entry_date()
    {
        return work_unit_entry_date;
    }

    public void setWork_unit_entry_date(java.sql.Date obj)
    {
        work_unit_entry_date = obj;
    }

    public String getWork_unit_department()
    {
        return work_unit_department;
    }

    public void setWork_unit_department(String obj)
    {
        work_unit_department = obj;
    }

    public String getWork_unit_duty()
    {
        return work_unit_duty;
    }

    public void setWork_unit_duty(String obj)
    {
        work_unit_duty = obj;
    }

    public String getWork_unit_property()
    {
        return work_unit_property;
    }

    public void setWork_unit_property(String obj)
    {
        work_unit_property = obj;
    }

    public Integer getWms_cre_credit_line_customer_change_head_id()
    {
        return wms_cre_credit_line_customer_change_head_id;
    }

    public void setWms_cre_credit_line_customer_change_head_id(Integer obj)
    {
        wms_cre_credit_line_customer_change_head_id = obj;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer obj)
    {
        create_user_id = obj;
    }

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp obj)
    {
        create_timestamp = obj;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer obj)
    {
        last_update_user_id = obj;
    }

    public java.sql.Timestamp getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(java.sql.Timestamp obj)
    {
        last_update_timestamp = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_customer_change_line_workinfo_id", this.wms_cre_customer_change_line_workinfo_id);
        paramMap.put("work_unit_full_name", this.work_unit_full_name);
        paramMap.put("work_unit_address_province", this.work_unit_address_province);
        paramMap.put("work_unit_address_city", this.work_unit_address_city);
        paramMap.put("work_unit_address_district", this.work_unit_address_district);
        paramMap.put("work_unit_address_more", this.work_unit_address_more);
        paramMap.put("work_unit_telephone", this.work_unit_telephone);
        paramMap.put("work_unit_entry_date", this.work_unit_entry_date);
        paramMap.put("work_unit_department", this.work_unit_department);
        paramMap.put("work_unit_duty", this.work_unit_duty);
        paramMap.put("work_unit_property", this.work_unit_property);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("enable_flag", this.enable_flag);
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
        if (paramMap.get("wms_cre_customer_change_line_workinfo_id") == null)
        {
            paramMap.remove("wms_cre_customer_change_line_workinfo_id");
        }
        if (paramMap.get("work_unit_full_name") == null)
        {
            paramMap.remove("work_unit_full_name");
        }
        if (paramMap.get("work_unit_address_province") == null)
        {
            paramMap.remove("work_unit_address_province");
        }
        if (paramMap.get("work_unit_address_city") == null)
        {
            paramMap.remove("work_unit_address_city");
        }
        if (paramMap.get("work_unit_address_district") == null)
        {
            paramMap.remove("work_unit_address_district");
        }
        if (paramMap.get("work_unit_address_more") == null)
        {
            paramMap.remove("work_unit_address_more");
        }
        if (paramMap.get("work_unit_telephone") == null)
        {
            paramMap.remove("work_unit_telephone");
        }
        if (paramMap.get("work_unit_entry_date") == null)
        {
            paramMap.remove("work_unit_entry_date");
        }
        if (paramMap.get("work_unit_department") == null)
        {
            paramMap.remove("work_unit_department");
        }
        if (paramMap.get("work_unit_duty") == null)
        {
            paramMap.remove("work_unit_duty");
        }
        if (paramMap.get("work_unit_property") == null)
        {
            paramMap.remove("work_unit_property");
        }
        if (paramMap.get("wms_cre_credit_line_customer_change_head_id") == null)
        {
            paramMap.remove("wms_cre_credit_line_customer_change_head_id");
        }
        if (paramMap.get("create_user_id") == null)
        {
            paramMap.remove("create_user_id");
        }
        if (paramMap.get("create_timestamp") == null)
        {
            paramMap.remove("create_timestamp");
        }
        if (paramMap.get("last_update_user_id") == null)
        {
            paramMap.remove("last_update_user_id");
        }
        if (paramMap.get("last_update_timestamp") == null)
        {
            paramMap.remove("last_update_timestamp");
        }
        if (paramMap.get("enable_flag") == null)
        {
            paramMap.remove("enable_flag");
        }
        return paramMap;
    }

    /**
     * this table insert function, nonsupport null val
     */
    public int insertRecord(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_INSERT,
                                    setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer wms_cre_customer_change_line_workinfo_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_customer_change_line_workinfo_id", wms_cre_customer_change_line_workinfo_id);
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_UPDATE,
                                    setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static WmsCreCustomerChangeLineWorkinfo getRecordDomainByPK(AbstractSimpleDao dao,
                                                                       Integer wms_cre_customer_change_line_workinfo_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_customer_change_line_workinfo_id", wms_cre_customer_change_line_workinfo_id);
        WmsCreCustomerChangeLineWorkinfo bean = dao.qryObj(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_LIST,
                                                           paramMap, WmsCreCustomerChangeLineWorkinfo.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static WmsCreCustomerChangeLineWorkinfoVO getRecordVOByPK(AbstractSimpleDao dao,
                                                                     Integer wms_cre_customer_change_line_workinfo_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_customer_change_line_workinfo_id", wms_cre_customer_change_line_workinfo_id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_LIST,
                                                                       paramMap);
        WmsCreCustomerChangeLineWorkinfoVO bean = new WmsCreCustomerChangeLineWorkinfoVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("wms_cre_customer_change_line_workinfo_id") != null)
            {
                bean.setWms_cre_customer_change_line_workinfo_id((Integer) row.get("wms_cre_customer_change_line_workinfo_id"));
            }
            if (row.get("work_unit_full_name") != null)
            {
                bean.setWork_unit_full_name((String) row.get("work_unit_full_name"));
            }
            if (row.get("work_unit_address_province") != null)
            {
                bean.setWork_unit_address_province((String) row.get("work_unit_address_province"));
            }
            if (row.get("work_unit_address_city") != null)
            {
                bean.setWork_unit_address_city((String) row.get("work_unit_address_city"));
            }
            if (row.get("work_unit_address_district") != null)
            {
                bean.setWork_unit_address_district((String) row.get("work_unit_address_district"));
            }
            if (row.get("work_unit_address_more") != null)
            {
                bean.setWork_unit_address_more((String) row.get("work_unit_address_more"));
            }
            if (row.get("work_unit_telephone") != null)
            {
                bean.setWork_unit_telephone((String) row.get("work_unit_telephone"));
            }
            if (row.get("work_unit_entry_date") != null)
            {
                bean.setWork_unit_entry_date(DateUtil.date2String((java.util.Date) row.get("work_unit_entry_date"),
                                                                  "yyyy-MM-dd"));
            }
            if (row.get("work_unit_department") != null)
            {
                bean.setWork_unit_department((String) row.get("work_unit_department"));
            }
            if (row.get("work_unit_duty") != null)
            {
                bean.setWork_unit_duty((String) row.get("work_unit_duty"));
            }
            if (row.get("work_unit_property") != null)
            {
                bean.setWork_unit_property((String) row.get("work_unit_property"));
            }
            if (row.get("wms_cre_credit_line_customer_change_head_id") != null)
            {
                bean.setWms_cre_credit_line_customer_change_head_id((Integer) row.get("wms_cre_credit_line_customer_change_head_id"));
            }
            if (row.get("create_user_id") != null)
            {
                bean.setCreate_user_id((Integer) row.get("create_user_id"));
            }
            if (row.get("create_timestamp") != null)
            {
                bean.setCreate_timestamp(DateUtil.date2String((java.util.Date) row.get("create_timestamp"),
                                                              "yyyy-MM-dd HH:mm:ss"));
            }
            if (row.get("last_update_user_id") != null)
            {
                bean.setLast_update_user_id((Integer) row.get("last_update_user_id"));
            }
            if (row.get("last_update_timestamp") != null)
            {
                bean.setLast_update_timestamp(DateUtil.date2String((java.util.Date) row.get("last_update_timestamp"),
                                                                   "yyyy-MM-dd HH:mm:ss"));
            }
            if (row.get("enable_flag") != null)
            {
                bean.setEnable_flag((String) row.get("enable_flag"));
            }
        }
        return bean;
    }

    /**
     * delete records by domain, nonsupport null val
     */
    public int deleteRecordsByDomain(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_DELETE,
                                    dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new WmsCreCustomerChangeLineWorkinfo()
     * include query-params
     */
    public static List<WmsCreCustomerChangeLineWorkinfo> getSingleTableListByAndMethod(AbstractSimpleDao dao,
                                                                                       WmsCreCustomerChangeLineWorkinfo queryInfo,
                                                                                       Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_customer_change_line_workinfo_id() != null)
        {
            paramMap.put("wms_cre_customer_change_line_workinfo_id",
                         queryInfo.getWms_cre_customer_change_line_workinfo_id());
        }
        if (queryInfo.getWork_unit_full_name() != null)
        {
            paramMap.put("work_unit_full_name", queryInfo.getWork_unit_full_name());
        }
        if (queryInfo.getWork_unit_address_province() != null)
        {
            paramMap.put("work_unit_address_province", queryInfo.getWork_unit_address_province());
        }
        if (queryInfo.getWork_unit_address_city() != null)
        {
            paramMap.put("work_unit_address_city", queryInfo.getWork_unit_address_city());
        }
        if (queryInfo.getWork_unit_address_district() != null)
        {
            paramMap.put("work_unit_address_district", queryInfo.getWork_unit_address_district());
        }
        if (queryInfo.getWork_unit_address_more() != null)
        {
            paramMap.put("work_unit_address_more", queryInfo.getWork_unit_address_more());
        }
        if (queryInfo.getWork_unit_telephone() != null)
        {
            paramMap.put("work_unit_telephone", queryInfo.getWork_unit_telephone());
        }
        if (queryInfo.getWork_unit_entry_date() != null)
        {
            paramMap.put("work_unit_entry_date", queryInfo.getWork_unit_entry_date());
        }
        if (queryInfo.getWork_unit_department() != null)
        {
            paramMap.put("work_unit_department", queryInfo.getWork_unit_department());
        }
        if (queryInfo.getWork_unit_duty() != null)
        {
            paramMap.put("work_unit_duty", queryInfo.getWork_unit_duty());
        }
        if (queryInfo.getWork_unit_property() != null)
        {
            paramMap.put("work_unit_property", queryInfo.getWork_unit_property());
        }
        if (queryInfo.getWms_cre_credit_line_customer_change_head_id() != null)
        {
            paramMap.put("wms_cre_credit_line_customer_change_head_id",
                         queryInfo.getWms_cre_credit_line_customer_change_head_id());
        }
        if (queryInfo.getCreate_user_id() != null)
        {
            paramMap.put("create_user_id", queryInfo.getCreate_user_id());
        }
        if (queryInfo.getCreate_timestamp() != null)
        {
            paramMap.put("create_timestamp", queryInfo.getCreate_timestamp());
        }
        if (queryInfo.getLast_update_user_id() != null)
        {
            paramMap.put("last_update_user_id", queryInfo.getLast_update_user_id());
        }
        if (queryInfo.getLast_update_timestamp() != null)
        {
            paramMap.put("last_update_timestamp", queryInfo.getLast_update_timestamp());
        }
        if (queryInfo.getEnable_flag() != null)
        {
            paramMap.put("enable_flag", queryInfo.getEnable_flag());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_LIST,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, false),
                              WmsCreCustomerChangeLineWorkinfo.class);
    }

    /**
     * get list by "or" method, need new WmsCreCustomerChangeLineWorkinfo()
     * include query-params
     */
    public static List<WmsCreCustomerChangeLineWorkinfo> getSingleTableListByOrMethod(AbstractSimpleDao dao,
                                                                                      WmsCreCustomerChangeLineWorkinfo queryInfo,
                                                                                      Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_customer_change_line_workinfo_id() != null)
        {
            paramMap.put("wms_cre_customer_change_line_workinfo_id",
                         queryInfo.getWms_cre_customer_change_line_workinfo_id());
        }
        if (queryInfo.getWork_unit_full_name() != null)
        {
            paramMap.put("work_unit_full_name", queryInfo.getWork_unit_full_name());
        }
        if (queryInfo.getWork_unit_address_province() != null)
        {
            paramMap.put("work_unit_address_province", queryInfo.getWork_unit_address_province());
        }
        if (queryInfo.getWork_unit_address_city() != null)
        {
            paramMap.put("work_unit_address_city", queryInfo.getWork_unit_address_city());
        }
        if (queryInfo.getWork_unit_address_district() != null)
        {
            paramMap.put("work_unit_address_district", queryInfo.getWork_unit_address_district());
        }
        if (queryInfo.getWork_unit_address_more() != null)
        {
            paramMap.put("work_unit_address_more", queryInfo.getWork_unit_address_more());
        }
        if (queryInfo.getWork_unit_telephone() != null)
        {
            paramMap.put("work_unit_telephone", queryInfo.getWork_unit_telephone());
        }
        if (queryInfo.getWork_unit_entry_date() != null)
        {
            paramMap.put("work_unit_entry_date", queryInfo.getWork_unit_entry_date());
        }
        if (queryInfo.getWork_unit_department() != null)
        {
            paramMap.put("work_unit_department", queryInfo.getWork_unit_department());
        }
        if (queryInfo.getWork_unit_duty() != null)
        {
            paramMap.put("work_unit_duty", queryInfo.getWork_unit_duty());
        }
        if (queryInfo.getWork_unit_property() != null)
        {
            paramMap.put("work_unit_property", queryInfo.getWork_unit_property());
        }
        if (queryInfo.getWms_cre_credit_line_customer_change_head_id() != null)
        {
            paramMap.put("wms_cre_credit_line_customer_change_head_id",
                         queryInfo.getWms_cre_credit_line_customer_change_head_id());
        }
        if (queryInfo.getCreate_user_id() != null)
        {
            paramMap.put("create_user_id", queryInfo.getCreate_user_id());
        }
        if (queryInfo.getCreate_timestamp() != null)
        {
            paramMap.put("create_timestamp", queryInfo.getCreate_timestamp());
        }
        if (queryInfo.getLast_update_user_id() != null)
        {
            paramMap.put("last_update_user_id", queryInfo.getLast_update_user_id());
        }
        if (queryInfo.getLast_update_timestamp() != null)
        {
            paramMap.put("last_update_timestamp", queryInfo.getLast_update_timestamp());
        }
        if (queryInfo.getEnable_flag() != null)
        {
            paramMap.put("enable_flag", queryInfo.getEnable_flag());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_WORKINFO_LIST_BY_OR,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, true), WmsCreCustomerChangeLineWorkinfo.class);
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