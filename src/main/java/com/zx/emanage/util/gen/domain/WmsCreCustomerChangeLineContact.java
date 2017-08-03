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
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineContactVO;

/*
 * @version 2.0
 */

public class WmsCreCustomerChangeLineContact implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_customer_change_line_contact_id;

    private String contact_name;

    private String contact_relation_type;

    private String contact_relation_description;

    private String contact_mobile_phone;

    private String audit_result1;

    private String audit_result2;

    private String audit_result3;

    private Integer wms_cre_credit_head_id;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_customer_change_line_contact_id" };

    private static String[] columnNameArr = { "wms_cre_customer_change_line_contact_id", "contact_name",
                                             "contact_relation_type", "contact_relation_description",
                                             "contact_mobile_phone", "audit_result1", "audit_result2", "audit_result3",
                                             "wms_cre_credit_head_id" };

    public Integer getWms_cre_customer_change_line_contact_id()
    {
        return wms_cre_customer_change_line_contact_id;
    }

    public void setWms_cre_customer_change_line_contact_id(Integer obj)
    {
        wms_cre_customer_change_line_contact_id = obj;
    }

    public String getContact_name()
    {
        return contact_name;
    }

    public void setContact_name(String obj)
    {
        contact_name = obj;
    }

    public String getContact_relation_type()
    {
        return contact_relation_type;
    }

    public void setContact_relation_type(String obj)
    {
        contact_relation_type = obj;
    }

    public String getContact_relation_description()
    {
        return contact_relation_description;
    }

    public void setContact_relation_description(String obj)
    {
        contact_relation_description = obj;
    }

    public String getContact_mobile_phone()
    {
        return contact_mobile_phone;
    }

    public void setContact_mobile_phone(String obj)
    {
        contact_mobile_phone = obj;
    }

    public String getAudit_result1()
    {
        return audit_result1;
    }

    public void setAudit_result1(String obj)
    {
        audit_result1 = obj;
    }

    public String getAudit_result2()
    {
        return audit_result2;
    }

    public void setAudit_result2(String obj)
    {
        audit_result2 = obj;
    }

    public String getAudit_result3()
    {
        return audit_result3;
    }

    public void setAudit_result3(String obj)
    {
        audit_result3 = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_customer_change_line_contact_id", this.wms_cre_customer_change_line_contact_id);
        paramMap.put("contact_name", this.contact_name);
        paramMap.put("contact_relation_type", this.contact_relation_type);
        paramMap.put("contact_relation_description", this.contact_relation_description);
        paramMap.put("contact_mobile_phone", this.contact_mobile_phone);
        paramMap.put("audit_result1", this.audit_result1);
        paramMap.put("audit_result2", this.audit_result2);
        paramMap.put("audit_result3", this.audit_result3);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
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
        if (paramMap.get("wms_cre_customer_change_line_contact_id") == null)
        {
            paramMap.remove("wms_cre_customer_change_line_contact_id");
        }
        if (paramMap.get("contact_name") == null)
        {
            paramMap.remove("contact_name");
        }
        if (paramMap.get("contact_relation_type") == null)
        {
            paramMap.remove("contact_relation_type");
        }
        if (paramMap.get("contact_relation_description") == null)
        {
            paramMap.remove("contact_relation_description");
        }
        if (paramMap.get("contact_mobile_phone") == null)
        {
            paramMap.remove("contact_mobile_phone");
        }
        if (paramMap.get("audit_result1") == null)
        {
            paramMap.remove("audit_result1");
        }
        if (paramMap.get("audit_result2") == null)
        {
            paramMap.remove("audit_result2");
        }
        if (paramMap.get("audit_result3") == null)
        {
            paramMap.remove("audit_result3");
        }
        if (paramMap.get("wms_cre_credit_head_id") == null)
        {
            paramMap.remove("wms_cre_credit_head_id");
        }
        return paramMap;
    }

    /**
     * this table insert function, nonsupport null val
     */
    public int insertRecord(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_INSERT,
                                    setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer wms_cre_customer_change_line_contact_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_customer_change_line_contact_id", wms_cre_customer_change_line_contact_id);
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_UPDATE,
                                    setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static WmsCreCustomerChangeLineContact getRecordDomainByPK(AbstractSimpleDao dao,
                                                                      Integer wms_cre_customer_change_line_contact_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_customer_change_line_contact_id", wms_cre_customer_change_line_contact_id);
        WmsCreCustomerChangeLineContact bean = dao.qryObj(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_LIST,
                                                          paramMap, WmsCreCustomerChangeLineContact.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static WmsCreCustomerChangeLineContactVO getRecordVOByPK(AbstractSimpleDao dao,
                                                                    Integer wms_cre_customer_change_line_contact_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_customer_change_line_contact_id", wms_cre_customer_change_line_contact_id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_LIST,
                                                                       paramMap);
        WmsCreCustomerChangeLineContactVO bean = new WmsCreCustomerChangeLineContactVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("wms_cre_customer_change_line_contact_id") != null)
            {
                bean.setWms_cre_customer_change_line_contact_id((Integer) row.get("wms_cre_customer_change_line_contact_id"));
            }
            if (row.get("contact_name") != null)
            {
                bean.setContact_name((String) row.get("contact_name"));
            }
            if (row.get("contact_relation_type") != null)
            {
                bean.setContact_relation_type((String) row.get("contact_relation_type"));
            }
            if (row.get("contact_relation_description") != null)
            {
                bean.setContact_relation_description((String) row.get("contact_relation_description"));
            }
            if (row.get("contact_mobile_phone") != null)
            {
                bean.setContact_mobile_phone((String) row.get("contact_mobile_phone"));
            }
            if (row.get("audit_result1") != null)
            {
                bean.setAudit_result1((String) row.get("audit_result1"));
            }
            if (row.get("audit_result2") != null)
            {
                bean.setAudit_result2((String) row.get("audit_result2"));
            }
            if (row.get("audit_result3") != null)
            {
                bean.setAudit_result3((String) row.get("audit_result3"));
            }
            if (row.get("wms_cre_credit_head_id") != null)
            {
                bean.setWms_cre_credit_head_id((Integer) row.get("wms_cre_credit_head_id"));
            }
        }
        return bean;
    }

    /**
     * delete records by domain, nonsupport null val
     */
    public int deleteRecordsByDomain(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_DELETE,
                                    dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new WmsCreCustomerChangeLineContact()
     * include query-params
     */
    public static List<WmsCreCustomerChangeLineContact> getSingleTableListByAndMethod(AbstractSimpleDao dao,
                                                                                      WmsCreCustomerChangeLineContact queryInfo,
                                                                                      Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_customer_change_line_contact_id() != null)
        {
            paramMap.put("wms_cre_customer_change_line_contact_id",
                         queryInfo.getWms_cre_customer_change_line_contact_id());
        }
        if (queryInfo.getContact_name() != null)
        {
            paramMap.put("contact_name", queryInfo.getContact_name());
        }
        if (queryInfo.getContact_relation_type() != null)
        {
            paramMap.put("contact_relation_type", queryInfo.getContact_relation_type());
        }
        if (queryInfo.getContact_relation_description() != null)
        {
            paramMap.put("contact_relation_description", queryInfo.getContact_relation_description());
        }
        if (queryInfo.getContact_mobile_phone() != null)
        {
            paramMap.put("contact_mobile_phone", queryInfo.getContact_mobile_phone());
        }
        if (queryInfo.getAudit_result1() != null)
        {
            paramMap.put("audit_result1", queryInfo.getAudit_result1());
        }
        if (queryInfo.getAudit_result2() != null)
        {
            paramMap.put("audit_result2", queryInfo.getAudit_result2());
        }
        if (queryInfo.getAudit_result3() != null)
        {
            paramMap.put("audit_result3", queryInfo.getAudit_result3());
        }
        if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_LIST,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, false), WmsCreCustomerChangeLineContact.class);
    }

    /**
     * get list by "or" method, need new WmsCreCustomerChangeLineContact()
     * include query-params
     */
    public static List<WmsCreCustomerChangeLineContact> getSingleTableListByOrMethod(AbstractSimpleDao dao,
                                                                                     WmsCreCustomerChangeLineContact queryInfo,
                                                                                     Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_customer_change_line_contact_id() != null)
        {
            paramMap.put("wms_cre_customer_change_line_contact_id",
                         queryInfo.getWms_cre_customer_change_line_contact_id());
        }
        if (queryInfo.getContact_name() != null)
        {
            paramMap.put("contact_name", queryInfo.getContact_name());
        }
        if (queryInfo.getContact_relation_type() != null)
        {
            paramMap.put("contact_relation_type", queryInfo.getContact_relation_type());
        }
        if (queryInfo.getContact_relation_description() != null)
        {
            paramMap.put("contact_relation_description", queryInfo.getContact_relation_description());
        }
        if (queryInfo.getContact_mobile_phone() != null)
        {
            paramMap.put("contact_mobile_phone", queryInfo.getContact_mobile_phone());
        }
        if (queryInfo.getAudit_result1() != null)
        {
            paramMap.put("audit_result1", queryInfo.getAudit_result1());
        }
        if (queryInfo.getAudit_result2() != null)
        {
            paramMap.put("audit_result2", queryInfo.getAudit_result2());
        }
        if (queryInfo.getAudit_result3() != null)
        {
            paramMap.put("audit_result3", queryInfo.getAudit_result3());
        }
        if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_CRE_CUSTOMER_CHANGE_LINE_CONTACT_LIST_BY_OR,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, true), WmsCreCustomerChangeLineContact.class);
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