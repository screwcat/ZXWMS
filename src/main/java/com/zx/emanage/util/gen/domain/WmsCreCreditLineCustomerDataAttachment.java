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
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerDataAttachmentVO;

/*
 * @version 2.0
 */

public class WmsCreCreditLineCustomerDataAttachment implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_customer_data_attachment_id;

    private String data_type_name;

    private String data_detail_name;

    private String attachment_old_name;

    private String attachment_new_name;

    private String attachment_address;

    private String attachment_type;

    private Integer wms_cre_credit_head_id;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_credit_line_customer_data_attachment_id" };

    private static String[] columnNameArr = { "wms_cre_credit_line_customer_data_attachment_id", "data_type_name",
                                             "data_detail_name", "attachment_old_name", "attachment_new_name",
                                             "attachment_address", "attachment_type", "wms_cre_credit_head_id" };

    public Integer getWms_cre_credit_line_customer_data_attachment_id()
    {
        return wms_cre_credit_line_customer_data_attachment_id;
    }

    public void setWms_cre_credit_line_customer_data_attachment_id(Integer obj)
    {
        wms_cre_credit_line_customer_data_attachment_id = obj;
    }

    public String getData_type_name()
    {
        return data_type_name;
    }

    public void setData_type_name(String obj)
    {
        data_type_name = obj;
    }

    public String getData_detail_name()
    {
        return data_detail_name;
    }

    public void setData_detail_name(String obj)
    {
        data_detail_name = obj;
    }

    public String getAttachment_old_name()
    {
        return attachment_old_name;
    }

    public void setAttachment_old_name(String obj)
    {
        attachment_old_name = obj;
    }

    public String getAttachment_new_name()
    {
        return attachment_new_name;
    }

    public void setAttachment_new_name(String obj)
    {
        attachment_new_name = obj;
    }

    public String getAttachment_address()
    {
        return attachment_address;
    }

    public void setAttachment_address(String obj)
    {
        attachment_address = obj;
    }

    public String getAttachment_type()
    {
        return attachment_type;
    }

    public void setAttachment_type(String obj)
    {
        attachment_type = obj;
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
        paramMap.put("wms_cre_credit_line_customer_data_attachment_id",
                     this.wms_cre_credit_line_customer_data_attachment_id);
        paramMap.put("data_type_name", this.data_type_name);
        paramMap.put("data_detail_name", this.data_detail_name);
        paramMap.put("attachment_old_name", this.attachment_old_name);
        paramMap.put("attachment_new_name", this.attachment_new_name);
        paramMap.put("attachment_address", this.attachment_address);
        paramMap.put("attachment_type", this.attachment_type);
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
        if (paramMap.get("wms_cre_credit_line_customer_data_attachment_id") == null)
        {
            paramMap.remove("wms_cre_credit_line_customer_data_attachment_id");
        }
        if (paramMap.get("data_type_name") == null)
        {
            paramMap.remove("data_type_name");
        }
        if (paramMap.get("data_detail_name") == null)
        {
            paramMap.remove("data_detail_name");
        }
        if (paramMap.get("attachment_old_name") == null)
        {
            paramMap.remove("attachment_old_name");
        }
        if (paramMap.get("attachment_new_name") == null)
        {
            paramMap.remove("attachment_new_name");
        }
        if (paramMap.get("attachment_address") == null)
        {
            paramMap.remove("attachment_address");
        }
        if (paramMap.get("attachment_type") == null)
        {
            paramMap.remove("attachment_type");
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
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_INSERT,
                                    setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer wms_cre_credit_line_customer_data_attachment_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_line_customer_data_attachment_id", wms_cre_credit_line_customer_data_attachment_id);
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_UPDATE,
                                    setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static WmsCreCreditLineCustomerDataAttachment getRecordDomainByPK(AbstractSimpleDao dao,
                                                                             Integer wms_cre_credit_line_customer_data_attachment_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_line_customer_data_attachment_id", wms_cre_credit_line_customer_data_attachment_id);
        WmsCreCreditLineCustomerDataAttachment bean = dao.qryObj(SqlString.AUTO2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_LIST,
                                                                 paramMap, WmsCreCreditLineCustomerDataAttachment.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static WmsCreCreditLineCustomerDataAttachmentVO getRecordVOByPK(AbstractSimpleDao dao,
                                                                           Integer wms_cre_credit_line_customer_data_attachment_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_line_customer_data_attachment_id", wms_cre_credit_line_customer_data_attachment_id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_LIST,
                                                                       paramMap);
        WmsCreCreditLineCustomerDataAttachmentVO bean = new WmsCreCreditLineCustomerDataAttachmentVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("wms_cre_credit_line_customer_data_attachment_id") != null)
            {
                bean.setWms_cre_credit_line_customer_data_attachment_id((Integer) row.get("wms_cre_credit_line_customer_data_attachment_id"));
            }
            if (row.get("data_type_name") != null)
            {
                bean.setData_type_name((String) row.get("data_type_name"));
            }
            if (row.get("data_detail_name") != null)
            {
                bean.setData_detail_name((String) row.get("data_detail_name"));
            }
            if (row.get("attachment_old_name") != null)
            {
                bean.setAttachment_old_name((String) row.get("attachment_old_name"));
            }
            if (row.get("attachment_new_name") != null)
            {
                bean.setAttachment_new_name((String) row.get("attachment_new_name"));
            }
            if (row.get("attachment_address") != null)
            {
                bean.setAttachment_address((String) row.get("attachment_address"));
            }
            if (row.get("attachment_type") != null)
            {
                bean.setAttachment_type((String) row.get("attachment_type"));
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
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_DELETE,
                                    dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new
     * WmsCreCreditLineCustomerDataAttachment() include query-params
     */
    public static List<WmsCreCreditLineCustomerDataAttachment> getSingleTableListByAndMethod(AbstractSimpleDao dao,
                                                                                             WmsCreCreditLineCustomerDataAttachment queryInfo,
                                                                                             Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_credit_line_customer_data_attachment_id() != null)
        {
            paramMap.put("wms_cre_credit_line_customer_data_attachment_id",
                         queryInfo.getWms_cre_credit_line_customer_data_attachment_id());
        }
        if (queryInfo.getData_type_name() != null)
        {
            paramMap.put("data_type_name", queryInfo.getData_type_name());
        }
        if (queryInfo.getData_detail_name() != null)
        {
            paramMap.put("data_detail_name", queryInfo.getData_detail_name());
        }
        if (queryInfo.getAttachment_old_name() != null)
        {
            paramMap.put("attachment_old_name", queryInfo.getAttachment_old_name());
        }
        if (queryInfo.getAttachment_new_name() != null)
        {
            paramMap.put("attachment_new_name", queryInfo.getAttachment_new_name());
        }
        if (queryInfo.getAttachment_address() != null)
        {
            paramMap.put("attachment_address", queryInfo.getAttachment_address());
        }
        if (queryInfo.getAttachment_type() != null)
        {
            paramMap.put("attachment_type", queryInfo.getAttachment_type());
        }
        if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_LIST,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, false),
                              WmsCreCreditLineCustomerDataAttachment.class);
    }

    /**
     * get list by "or" method, need new
     * WmsCreCreditLineCustomerDataAttachment() include query-params
     */
    public static List<WmsCreCreditLineCustomerDataAttachment> getSingleTableListByOrMethod(AbstractSimpleDao dao,
                                                                                            WmsCreCreditLineCustomerDataAttachment queryInfo,
                                                                                            Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_credit_line_customer_data_attachment_id() != null)
        {
            paramMap.put("wms_cre_credit_line_customer_data_attachment_id",
                         queryInfo.getWms_cre_credit_line_customer_data_attachment_id());
        }
        if (queryInfo.getData_type_name() != null)
        {
            paramMap.put("data_type_name", queryInfo.getData_type_name());
        }
        if (queryInfo.getData_detail_name() != null)
        {
            paramMap.put("data_detail_name", queryInfo.getData_detail_name());
        }
        if (queryInfo.getAttachment_old_name() != null)
        {
            paramMap.put("attachment_old_name", queryInfo.getAttachment_old_name());
        }
        if (queryInfo.getAttachment_new_name() != null)
        {
            paramMap.put("attachment_new_name", queryInfo.getAttachment_new_name());
        }
        if (queryInfo.getAttachment_address() != null)
        {
            paramMap.put("attachment_address", queryInfo.getAttachment_address());
        }
        if (queryInfo.getAttachment_type() != null)
        {
            paramMap.put("attachment_type", queryInfo.getAttachment_type());
        }
        if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }
        return dao.qryObjList(SqlString.AUTO2_WMS_CRE_CREDIT_LINE_CUSTOMER_DATA_ATTACHMENT_LIST_BY_OR,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, true),
                              WmsCreCreditLineCustomerDataAttachment.class);
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