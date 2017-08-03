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
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineHouseinfoVO;

/*
 * @version 2.0
 */

public class WmsCreCustomerChangeLineHouseinfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_customer_change_line_houseinfo_id;

    private java.sql.Date house_buy_date;

    private Double house_buy_money;

    private Double house_building_area;

    private String house_address_province;

    private String house_address_city;

    private String house_address_district;

    private String house_address_more;

    private String house_post_code;

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
    private static String[] pkColArr = { "wms_cre_customer_change_line_houseinfo_id" };

    private static String[] columnNameArr = { "wms_cre_customer_change_line_houseinfo_id", "house_buy_date",
                                             "house_buy_money", "house_building_area", "house_address_province",
                                             "house_address_city", "house_address_district", "house_address_more",
                                             "house_post_code", "wms_cre_credit_line_customer_change_head_id",
                                             "create_user_id", "create_timestamp", "last_update_user_id",
                                             "last_update_timestamp", "enable_flag" };

    public Integer getWms_cre_customer_change_line_houseinfo_id()
    {
        return wms_cre_customer_change_line_houseinfo_id;
    }

    public void setWms_cre_customer_change_line_houseinfo_id(Integer obj)
    {
        wms_cre_customer_change_line_houseinfo_id = obj;
    }

    public java.sql.Date getHouse_buy_date()
    {
        return house_buy_date;
    }

    public void setHouse_buy_date(java.sql.Date obj)
    {
        house_buy_date = obj;
    }

    public Double getHouse_buy_money()
    {
        return house_buy_money;
    }

    public void setHouse_buy_money(Double obj)
    {
        house_buy_money = obj;
    }

    public Double getHouse_building_area()
    {
        return house_building_area;
    }

    public void setHouse_building_area(Double obj)
    {
        house_building_area = obj;
    }

    public String getHouse_address_province()
    {
        return house_address_province;
    }

    public void setHouse_address_province(String obj)
    {
        house_address_province = obj;
    }

    public String getHouse_address_city()
    {
        return house_address_city;
    }

    public void setHouse_address_city(String obj)
    {
        house_address_city = obj;
    }

    public String getHouse_address_district()
    {
        return house_address_district;
    }

    public void setHouse_address_district(String obj)
    {
        house_address_district = obj;
    }

    public String getHouse_address_more()
    {
        return house_address_more;
    }

    public void setHouse_address_more(String obj)
    {
        house_address_more = obj;
    }

    public String getHouse_post_code()
    {
        return house_post_code;
    }

    public void setHouse_post_code(String obj)
    {
        house_post_code = obj;
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
        paramMap.put("wms_cre_customer_change_line_houseinfo_id", this.wms_cre_customer_change_line_houseinfo_id);
        paramMap.put("house_buy_date", this.house_buy_date);
        paramMap.put("house_buy_money", this.house_buy_money);
        paramMap.put("house_building_area", this.house_building_area);
        paramMap.put("house_address_province", this.house_address_province);
        paramMap.put("house_address_city", this.house_address_city);
        paramMap.put("house_address_district", this.house_address_district);
        paramMap.put("house_address_more", this.house_address_more);
        paramMap.put("house_post_code", this.house_post_code);
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
        if (paramMap.get("wms_cre_customer_change_line_houseinfo_id") == null)
        {
            paramMap.remove("wms_cre_customer_change_line_houseinfo_id");
        }
        if (paramMap.get("house_buy_date") == null)
        {
            paramMap.remove("house_buy_date");
        }
        if (paramMap.get("house_buy_money") == null)
        {
            paramMap.remove("house_buy_money");
        }
        if (paramMap.get("house_building_area") == null)
        {
            paramMap.remove("house_building_area");
        }
        if (paramMap.get("house_address_province") == null)
        {
            paramMap.remove("house_address_province");
        }
        if (paramMap.get("house_address_city") == null)
        {
            paramMap.remove("house_address_city");
        }
        if (paramMap.get("house_address_district") == null)
        {
            paramMap.remove("house_address_district");
        }
        if (paramMap.get("house_address_more") == null)
        {
            paramMap.remove("house_address_more");
        }
        if (paramMap.get("house_post_code") == null)
        {
            paramMap.remove("house_post_code");
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
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_INSERT,
                                    setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer wms_cre_customer_change_line_houseinfo_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_customer_change_line_houseinfo_id", wms_cre_customer_change_line_houseinfo_id);
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_UPDATE,
                                    setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static WmsCreCustomerChangeLineHouseinfo getRecordDomainByPK(AbstractSimpleDao dao,
                                                                        Integer wms_cre_customer_change_line_houseinfo_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_customer_change_line_houseinfo_id", wms_cre_customer_change_line_houseinfo_id);
        WmsCreCustomerChangeLineHouseinfo bean = dao.qryObj(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_LIST,
                                                            paramMap, WmsCreCustomerChangeLineHouseinfo.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static WmsCreCustomerChangeLineHouseinfoVO getRecordVOByPK(AbstractSimpleDao dao,
                                                                      Integer wms_cre_customer_change_line_houseinfo_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_customer_change_line_houseinfo_id", wms_cre_customer_change_line_houseinfo_id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_LIST,
                                                                       paramMap);
        WmsCreCustomerChangeLineHouseinfoVO bean = new WmsCreCustomerChangeLineHouseinfoVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("wms_cre_customer_change_line_houseinfo_id") != null)
            {
                bean.setWms_cre_customer_change_line_houseinfo_id((Integer) row.get("wms_cre_customer_change_line_houseinfo_id"));
            }
            if (row.get("house_buy_date") != null)
            {
                bean.setHouse_buy_date(DateUtil.date2String((java.util.Date) row.get("house_buy_date"), "yyyy-MM-dd"));
            }
            if (row.get("house_buy_money") != null)
            {
                bean.setHouse_buy_money((Double) row.get("house_buy_money"));
            }
            if (row.get("house_building_area") != null)
            {
                bean.setHouse_building_area((Double) row.get("house_building_area"));
            }
            if (row.get("house_address_province") != null)
            {
                bean.setHouse_address_province((String) row.get("house_address_province"));
            }
            if (row.get("house_address_city") != null)
            {
                bean.setHouse_address_city((String) row.get("house_address_city"));
            }
            if (row.get("house_address_district") != null)
            {
                bean.setHouse_address_district((String) row.get("house_address_district"));
            }
            if (row.get("house_address_more") != null)
            {
                bean.setHouse_address_more((String) row.get("house_address_more"));
            }
            if (row.get("house_post_code") != null)
            {
                bean.setHouse_post_code((String) row.get("house_post_code"));
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
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_DELETE,
                                    dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new WmsCreCustomerChangeLineHouseinfo()
     * include query-params
     */
    public static List<WmsCreCustomerChangeLineHouseinfo> getSingleTableListByAndMethod(AbstractSimpleDao dao,
                                                                                        WmsCreCustomerChangeLineHouseinfo queryInfo,
                                                                                        Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_customer_change_line_houseinfo_id() != null)
        {
            paramMap.put("wms_cre_customer_change_line_houseinfo_id",
                         queryInfo.getWms_cre_customer_change_line_houseinfo_id());
        }
        if (queryInfo.getHouse_buy_date() != null)
        {
            paramMap.put("house_buy_date", queryInfo.getHouse_buy_date());
        }
        if (queryInfo.getHouse_buy_money() != null)
        {
            paramMap.put("house_buy_money", queryInfo.getHouse_buy_money());
        }
        if (queryInfo.getHouse_building_area() != null)
        {
            paramMap.put("house_building_area", queryInfo.getHouse_building_area());
        }
        if (queryInfo.getHouse_address_province() != null)
        {
            paramMap.put("house_address_province", queryInfo.getHouse_address_province());
        }
        if (queryInfo.getHouse_address_city() != null)
        {
            paramMap.put("house_address_city", queryInfo.getHouse_address_city());
        }
        if (queryInfo.getHouse_address_district() != null)
        {
            paramMap.put("house_address_district", queryInfo.getHouse_address_district());
        }
        if (queryInfo.getHouse_address_more() != null)
        {
            paramMap.put("house_address_more", queryInfo.getHouse_address_more());
        }
        if (queryInfo.getHouse_post_code() != null)
        {
            paramMap.put("house_post_code", queryInfo.getHouse_post_code());
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
        return dao.qryObjList(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_LIST,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, false),
                              WmsCreCustomerChangeLineHouseinfo.class);
    }

    /**
     * get list by "or" method, need new WmsCreCustomerChangeLineHouseinfo()
     * include query-params
     */
    public static List<WmsCreCustomerChangeLineHouseinfo> getSingleTableListByOrMethod(AbstractSimpleDao dao,
                                                                                       WmsCreCustomerChangeLineHouseinfo queryInfo,
                                                                                       Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_customer_change_line_houseinfo_id() != null)
        {
            paramMap.put("wms_cre_customer_change_line_houseinfo_id",
                         queryInfo.getWms_cre_customer_change_line_houseinfo_id());
        }
        if (queryInfo.getHouse_buy_date() != null)
        {
            paramMap.put("house_buy_date", queryInfo.getHouse_buy_date());
        }
        if (queryInfo.getHouse_buy_money() != null)
        {
            paramMap.put("house_buy_money", queryInfo.getHouse_buy_money());
        }
        if (queryInfo.getHouse_building_area() != null)
        {
            paramMap.put("house_building_area", queryInfo.getHouse_building_area());
        }
        if (queryInfo.getHouse_address_province() != null)
        {
            paramMap.put("house_address_province", queryInfo.getHouse_address_province());
        }
        if (queryInfo.getHouse_address_city() != null)
        {
            paramMap.put("house_address_city", queryInfo.getHouse_address_city());
        }
        if (queryInfo.getHouse_address_district() != null)
        {
            paramMap.put("house_address_district", queryInfo.getHouse_address_district());
        }
        if (queryInfo.getHouse_address_more() != null)
        {
            paramMap.put("house_address_more", queryInfo.getHouse_address_more());
        }
        if (queryInfo.getHouse_post_code() != null)
        {
            paramMap.put("house_post_code", queryInfo.getHouse_post_code());
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
        return dao.qryObjList(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_HOUSEINFO_LIST_BY_OR,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, true),
                              WmsCreCustomerChangeLineHouseinfo.class);
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