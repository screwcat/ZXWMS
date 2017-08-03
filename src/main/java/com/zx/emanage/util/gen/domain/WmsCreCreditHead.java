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
import com.zx.emanage.util.gen.vo.WmsCreCreditHeadVO;

/*
 * @version 2.0
 */

public class WmsCreCreditHead implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_head_id;

    private String bill_code;

    private String credit_purpose;

    private Double max_repayment_limit_per_month;

    private Double credit_limit;

    private Integer max_repayment_time_limit;

    private String enter_file_way;

    private String payroll_payment_way;

    private String data_type;

    private String is_complete;

    private String reference_type;

    private String is_other_corporation_done;

    private String is_to_public_stream;

    private String is_house_certificate_original;

    private String is_check;

    private String person_condition;

    private Integer salesman_id;

    private String salesman_code;

    private String salesman_name;

    private String bill_status;

    private Integer create_user_id;

    private String create_user_name;

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
    private static String[] pkColArr = { "wms_cre_credit_head_id" };

    private static String[] columnNameArr = { "wms_cre_credit_head_id", "bill_code", "credit_purpose",
                                             "max_repayment_limit_per_month", "credit_limit",
                                             "max_repayment_time_limit", "enter_file_way", "payroll_payment_way",
                                             "data_type", "is_complete", "reference_type", "is_other_corporation_done",
                                             "is_to_public_stream", "is_house_certificate_original", "is_check",
                                             "person_condition", "salesman_id", "salesman_code", "salesman_name",
                                             "bill_status", "create_user_id", "create_user_name", "create_timestamp",
                                             "last_update_user_id", "last_update_timestamp", "enable_flag" };

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String obj)
    {
        bill_code = obj;
    }

    public String getCredit_purpose()
    {
        return credit_purpose;
    }

    public void setCredit_purpose(String obj)
    {
        credit_purpose = obj;
    }

    public Double getMax_repayment_limit_per_month()
    {
        return max_repayment_limit_per_month;
    }

    public void setMax_repayment_limit_per_month(Double obj)
    {
        max_repayment_limit_per_month = obj;
    }

    public Double getCredit_limit()
    {
        return credit_limit;
    }

    public void setCredit_limit(Double obj)
    {
        credit_limit = obj;
    }

    public Integer getMax_repayment_time_limit()
    {
        return max_repayment_time_limit;
    }

    public void setMax_repayment_time_limit(Integer obj)
    {
        max_repayment_time_limit = obj;
    }

    public String getEnter_file_way()
    {
        return enter_file_way;
    }

    public void setEnter_file_way(String obj)
    {
        enter_file_way = obj;
    }

    public String getPayroll_payment_way()
    {
        return payroll_payment_way;
    }

    public void setPayroll_payment_way(String obj)
    {
        payroll_payment_way = obj;
    }

    public String getData_type()
    {
        return data_type;
    }

    public void setData_type(String obj)
    {
        data_type = obj;
    }

    public String getIs_complete()
    {
        return is_complete;
    }

    public void setIs_complete(String obj)
    {
        is_complete = obj;
    }

    public String getReference_type()
    {
        return reference_type;
    }

    public void setReference_type(String obj)
    {
        reference_type = obj;
    }

    public String getIs_other_corporation_done()
    {
        return is_other_corporation_done;
    }

    public void setIs_other_corporation_done(String obj)
    {
        is_other_corporation_done = obj;
    }

    public String getIs_to_public_stream()
    {
        return is_to_public_stream;
    }

    public void setIs_to_public_stream(String obj)
    {
        is_to_public_stream = obj;
    }

    public String getIs_house_certificate_original()
    {
        return is_house_certificate_original;
    }

    public void setIs_house_certificate_original(String obj)
    {
        is_house_certificate_original = obj;
    }

    public String getIs_check()
    {
        return is_check;
    }

    public void setIs_check(String obj)
    {
        is_check = obj;
    }

    public String getPerson_condition()
    {
        return person_condition;
    }

    public void setPerson_condition(String obj)
    {
        person_condition = obj;
    }

    public Integer getSalesman_id()
    {
        return salesman_id;
    }

    public void setSalesman_id(Integer obj)
    {
        salesman_id = obj;
    }

    public String getSalesman_code()
    {
        return salesman_code;
    }

    public void setSalesman_code(String obj)
    {
        salesman_code = obj;
    }

    public String getSalesman_name()
    {
        return salesman_name;
    }

    public void setSalesman_name(String obj)
    {
        salesman_name = obj;
    }

    public String getBill_status()
    {
        return bill_status;
    }

    public void setBill_status(String obj)
    {
        bill_status = obj;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer obj)
    {
        create_user_id = obj;
    }

    public String getCreate_user_name()
    {
        return create_user_name;
    }

    public void setCreate_user_name(String obj)
    {
        create_user_name = obj;
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
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("bill_code", this.bill_code);
        paramMap.put("credit_purpose", this.credit_purpose);
        paramMap.put("max_repayment_limit_per_month", this.max_repayment_limit_per_month);
        paramMap.put("credit_limit", this.credit_limit);
        paramMap.put("max_repayment_time_limit", this.max_repayment_time_limit);
        paramMap.put("enter_file_way", this.enter_file_way);
        paramMap.put("payroll_payment_way", this.payroll_payment_way);
        paramMap.put("data_type", this.data_type);
        paramMap.put("is_complete", this.is_complete);
        paramMap.put("reference_type", this.reference_type);
        paramMap.put("is_other_corporation_done", this.is_other_corporation_done);
        paramMap.put("is_to_public_stream", this.is_to_public_stream);
        paramMap.put("is_house_certificate_original", this.is_house_certificate_original);
        paramMap.put("is_check", this.is_check);
        paramMap.put("person_condition", this.person_condition);
        paramMap.put("salesman_id", this.salesman_id);
        paramMap.put("salesman_code", this.salesman_code);
        paramMap.put("salesman_name", this.salesman_name);
        paramMap.put("bill_status", this.bill_status);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
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
        if (paramMap.get("wms_cre_credit_head_id") == null)
        {
            paramMap.remove("wms_cre_credit_head_id");
        }
        if (paramMap.get("bill_code") == null)
        {
            paramMap.remove("bill_code");
        }
        if (paramMap.get("credit_purpose") == null)
        {
            paramMap.remove("credit_purpose");
        }
        if (paramMap.get("max_repayment_limit_per_month") == null)
        {
            paramMap.remove("max_repayment_limit_per_month");
        }
        if (paramMap.get("credit_limit") == null)
        {
            paramMap.remove("credit_limit");
        }
        if (paramMap.get("max_repayment_time_limit") == null)
        {
            paramMap.remove("max_repayment_time_limit");
        }
        if (paramMap.get("enter_file_way") == null)
        {
            paramMap.remove("enter_file_way");
        }
        if (paramMap.get("payroll_payment_way") == null)
        {
            paramMap.remove("payroll_payment_way");
        }
        if (paramMap.get("data_type") == null)
        {
            paramMap.remove("data_type");
        }
        if (paramMap.get("is_complete") == null)
        {
            paramMap.remove("is_complete");
        }
        if (paramMap.get("reference_type") == null)
        {
            paramMap.remove("reference_type");
        }
        if (paramMap.get("is_other_corporation_done") == null)
        {
            paramMap.remove("is_other_corporation_done");
        }
        if (paramMap.get("is_to_public_stream") == null)
        {
            paramMap.remove("is_to_public_stream");
        }
        if (paramMap.get("is_house_certificate_original") == null)
        {
            paramMap.remove("is_house_certificate_original");
        }
        if (paramMap.get("is_check") == null)
        {
            paramMap.remove("is_check");
        }
        if (paramMap.get("person_condition") == null)
        {
            paramMap.remove("person_condition");
        }
        if (paramMap.get("salesman_id") == null)
        {
            paramMap.remove("salesman_id");
        }
        if (paramMap.get("salesman_code") == null)
        {
            paramMap.remove("salesman_code");
        }
        if (paramMap.get("salesman_name") == null)
        {
            paramMap.remove("salesman_name");
        }
        if (paramMap.get("bill_status") == null)
        {
            paramMap.remove("bill_status");
        }
        if (paramMap.get("create_user_id") == null)
        {
            paramMap.remove("create_user_id");
        }
        if (paramMap.get("create_user_name") == null)
        {
            paramMap.remove("create_user_name");
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
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_HEAD_INSERT, setSymbolInsert(this.getInfoMap()));
    }

    /**
     * delete records by primary key
     */
    public static int deleteRecordsByPK(AbstractSimpleDao dao, Integer wms_cre_credit_head_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_HEAD_DELETE, paramMap);
    }

    /**
     * this table update row function, need primary key, support null val
     */
    public int updateRecordAll(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_HEAD_UPDATE_ALL,
                                    setSymbolUpdateWithNullValue(this.getInfoMap()));
    }

    /**
     * this table update column function, need primary key, nonsupport null val
     */
    public int updateRecordColumn(AbstractSimpleDao dao)
    {
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_HEAD_UPDATE,
                                    setSymbolUpdateWithoutNullValue(this.getInfoMap()));
    }

    /**
     * return single record domain by primary key
     */
    public static WmsCreCreditHead getRecordDomainByPK(AbstractSimpleDao dao, Integer wms_cre_credit_head_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        WmsCreCreditHead bean = dao.qryObj(SqlString.AUTO2_WMS_CRE_CREDIT_HEAD_LIST, paramMap, WmsCreCreditHead.class);
        return bean;
    }

    /**
     * return single record vo by primary key
     */
    public static WmsCreCreditHeadVO getRecordVOByPK(AbstractSimpleDao dao, Integer wms_cre_credit_head_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        List<Map<String, Object>> resList = dao.queryForListByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_HEAD_LIST,
                                                                       paramMap);
        WmsCreCreditHeadVO bean = new WmsCreCreditHeadVO();
        if (resList.size() > 0)
        {
            Map<String, Object> row = resList.get(0);
            if (row.get("wms_cre_credit_head_id") != null)
            {
                bean.setWms_cre_credit_head_id((Integer) row.get("wms_cre_credit_head_id"));
            }
            if (row.get("bill_code") != null)
            {
                bean.setBill_code((String) row.get("bill_code"));
            }
            if (row.get("credit_purpose") != null)
            {
                bean.setCredit_purpose((String) row.get("credit_purpose"));
            }
            if (row.get("max_repayment_limit_per_month") != null)
            {
                bean.setMax_repayment_limit_per_month((Double) row.get("max_repayment_limit_per_month"));
            }
            if (row.get("credit_limit") != null)
            {
                bean.setCredit_limit((Double) row.get("credit_limit"));
            }
            if (row.get("max_repayment_time_limit") != null)
            {
                bean.setMax_repayment_time_limit((Integer) row.get("max_repayment_time_limit"));
            }
            if (row.get("enter_file_way") != null)
            {
                bean.setEnter_file_way((String) row.get("enter_file_way"));
            }
            if (row.get("payroll_payment_way") != null)
            {
                bean.setPayroll_payment_way((String) row.get("payroll_payment_way"));
            }
            if (row.get("data_type") != null)
            {
                bean.setData_type((String) row.get("data_type"));
            }
            if (row.get("is_complete") != null)
            {
                bean.setIs_complete((String) row.get("is_complete"));
            }
            if (row.get("reference_type") != null)
            {
                bean.setReference_type((String) row.get("reference_type"));
            }
            if (row.get("is_other_corporation_done") != null)
            {
                bean.setIs_other_corporation_done((String) row.get("is_other_corporation_done"));
            }
            if (row.get("is_to_public_stream") != null)
            {
                bean.setIs_to_public_stream((String) row.get("is_to_public_stream"));
            }
            if (row.get("is_house_certificate_original") != null)
            {
                bean.setIs_house_certificate_original((String) row.get("is_house_certificate_original"));
            }
            if (row.get("is_check") != null)
            {
                bean.setIs_check((String) row.get("is_check"));
            }
            if (row.get("person_condition") != null)
            {
                bean.setPerson_condition((String) row.get("person_condition"));
            }
            if (row.get("salesman_id") != null)
            {
                bean.setSalesman_id((Integer) row.get("salesman_id"));
            }
            if (row.get("salesman_code") != null)
            {
                bean.setSalesman_code((String) row.get("salesman_code"));
            }
            if (row.get("salesman_name") != null)
            {
                bean.setSalesman_name((String) row.get("salesman_name"));
            }
            if (row.get("bill_status") != null)
            {
                bean.setBill_status((String) row.get("bill_status"));
            }
            if (row.get("create_user_id") != null)
            {
                bean.setCreate_user_id((Integer) row.get("create_user_id"));
            }
            if (row.get("create_user_name") != null)
            {
                bean.setCreate_user_name((String) row.get("create_user_name"));
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
        return dao.updateByTemplate(SqlString.AUTO2_WMS_CRE_CREDIT_HEAD_DELETE, dealWithMapNullVal(this.getInfoMap()));
    }

    /**
     * get list by "and" method, need new WmsCreCreditHead() include
     * query-params
     */
    public static List<WmsCreCreditHead> getSingleTableListByAndMethod(AbstractSimpleDao dao,
                                                                       WmsCreCreditHead queryInfo,
                                                                       Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }
        if (queryInfo.getBill_code() != null)
        {
            paramMap.put("bill_code", queryInfo.getBill_code());
        }
        if (queryInfo.getCredit_purpose() != null)
        {
            paramMap.put("credit_purpose", queryInfo.getCredit_purpose());
        }
        if (queryInfo.getMax_repayment_limit_per_month() != null)
        {
            paramMap.put("max_repayment_limit_per_month", queryInfo.getMax_repayment_limit_per_month());
        }
        if (queryInfo.getCredit_limit() != null)
        {
            paramMap.put("credit_limit", queryInfo.getCredit_limit());
        }
        if (queryInfo.getMax_repayment_time_limit() != null)
        {
            paramMap.put("max_repayment_time_limit", queryInfo.getMax_repayment_time_limit());
        }
        if (queryInfo.getEnter_file_way() != null)
        {
            paramMap.put("enter_file_way", queryInfo.getEnter_file_way());
        }
        if (queryInfo.getPayroll_payment_way() != null)
        {
            paramMap.put("payroll_payment_way", queryInfo.getPayroll_payment_way());
        }
        if (queryInfo.getData_type() != null)
        {
            paramMap.put("data_type", queryInfo.getData_type());
        }
        if (queryInfo.getIs_complete() != null)
        {
            paramMap.put("is_complete", queryInfo.getIs_complete());
        }
        if (queryInfo.getReference_type() != null)
        {
            paramMap.put("reference_type", queryInfo.getReference_type());
        }
        if (queryInfo.getIs_other_corporation_done() != null)
        {
            paramMap.put("is_other_corporation_done", queryInfo.getIs_other_corporation_done());
        }
        if (queryInfo.getIs_to_public_stream() != null)
        {
            paramMap.put("is_to_public_stream", queryInfo.getIs_to_public_stream());
        }
        if (queryInfo.getIs_house_certificate_original() != null)
        {
            paramMap.put("is_house_certificate_original", queryInfo.getIs_house_certificate_original());
        }
        if (queryInfo.getIs_check() != null)
        {
            paramMap.put("is_check", queryInfo.getIs_check());
        }
        if (queryInfo.getPerson_condition() != null)
        {
            paramMap.put("person_condition", queryInfo.getPerson_condition());
        }
        if (queryInfo.getSalesman_id() != null)
        {
            paramMap.put("salesman_id", queryInfo.getSalesman_id());
        }
        if (queryInfo.getSalesman_code() != null)
        {
            paramMap.put("salesman_code", queryInfo.getSalesman_code());
        }
        if (queryInfo.getSalesman_name() != null)
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        if (queryInfo.getBill_status() != null)
        {
            paramMap.put("bill_status", queryInfo.getBill_status());
        }
        if (queryInfo.getCreate_user_id() != null)
        {
            paramMap.put("create_user_id", queryInfo.getCreate_user_id());
        }
        if (queryInfo.getCreate_user_name() != null)
        {
            paramMap.put("create_user_name", queryInfo.getCreate_user_name());
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
        return dao.qryObjList(SqlString.AUTO2_WMS_CRE_CREDIT_HEAD_LIST,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, false), WmsCreCreditHead.class);
    }

    /**
     * get list by "or" method, need new WmsCreCreditHead() include query-params
     */
    public static List<WmsCreCreditHead> getSingleTableListByOrMethod(AbstractSimpleDao dao,
                                                                      WmsCreCreditHead queryInfo,
                                                                      Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }
        if (queryInfo.getBill_code() != null)
        {
            paramMap.put("bill_code", queryInfo.getBill_code());
        }
        if (queryInfo.getCredit_purpose() != null)
        {
            paramMap.put("credit_purpose", queryInfo.getCredit_purpose());
        }
        if (queryInfo.getMax_repayment_limit_per_month() != null)
        {
            paramMap.put("max_repayment_limit_per_month", queryInfo.getMax_repayment_limit_per_month());
        }
        if (queryInfo.getCredit_limit() != null)
        {
            paramMap.put("credit_limit", queryInfo.getCredit_limit());
        }
        if (queryInfo.getMax_repayment_time_limit() != null)
        {
            paramMap.put("max_repayment_time_limit", queryInfo.getMax_repayment_time_limit());
        }
        if (queryInfo.getEnter_file_way() != null)
        {
            paramMap.put("enter_file_way", queryInfo.getEnter_file_way());
        }
        if (queryInfo.getPayroll_payment_way() != null)
        {
            paramMap.put("payroll_payment_way", queryInfo.getPayroll_payment_way());
        }
        if (queryInfo.getData_type() != null)
        {
            paramMap.put("data_type", queryInfo.getData_type());
        }
        if (queryInfo.getIs_complete() != null)
        {
            paramMap.put("is_complete", queryInfo.getIs_complete());
        }
        if (queryInfo.getReference_type() != null)
        {
            paramMap.put("reference_type", queryInfo.getReference_type());
        }
        if (queryInfo.getIs_other_corporation_done() != null)
        {
            paramMap.put("is_other_corporation_done", queryInfo.getIs_other_corporation_done());
        }
        if (queryInfo.getIs_to_public_stream() != null)
        {
            paramMap.put("is_to_public_stream", queryInfo.getIs_to_public_stream());
        }
        if (queryInfo.getIs_house_certificate_original() != null)
        {
            paramMap.put("is_house_certificate_original", queryInfo.getIs_house_certificate_original());
        }
        if (queryInfo.getIs_check() != null)
        {
            paramMap.put("is_check", queryInfo.getIs_check());
        }
        if (queryInfo.getPerson_condition() != null)
        {
            paramMap.put("person_condition", queryInfo.getPerson_condition());
        }
        if (queryInfo.getSalesman_id() != null)
        {
            paramMap.put("salesman_id", queryInfo.getSalesman_id());
        }
        if (queryInfo.getSalesman_code() != null)
        {
            paramMap.put("salesman_code", queryInfo.getSalesman_code());
        }
        if (queryInfo.getSalesman_name() != null)
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        if (queryInfo.getBill_status() != null)
        {
            paramMap.put("bill_status", queryInfo.getBill_status());
        }
        if (queryInfo.getCreate_user_id() != null)
        {
            paramMap.put("create_user_id", queryInfo.getCreate_user_id());
        }
        if (queryInfo.getCreate_user_name() != null)
        {
            paramMap.put("create_user_name", queryInfo.getCreate_user_name());
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
        return dao.qryObjList(SqlString.AUTO2_WMS_CRE_CREDIT_HEAD_LIST_BY_OR,
                              setSymbolPKPrior(paramMap, isExcludePKFlag, true), WmsCreCreditHead.class);
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