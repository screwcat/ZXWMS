package com.zx.emanage.util.gen.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.math.BigDecimal;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsCreRevWaterRepayLoanLine implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_water_repay_loan_line_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cre_rev_water_repay_loan_id;

    private String repay_name;

    private String repay_date;

    private String repay_account;

    private String is_overdue;

    private String remark;

    private String overdue_days;

    private String late_fee;

    private String loan_date;

    private String loan_amount;

    private String loan_repay_date;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_water_repay_loan_line_id" };

    private static String[] columnNameArr = { "wms_cre_rev_water_repay_loan_line_id", "wms_cre_credit_head_id",
                                             "wms_cre_rev_water_repay_loan_id", "repay_name", "repay_date",
                                             "repay_account", "is_overdue", "remark", "overdue_days", "late_fee",
                                             "loan_date", "loan_amount", "loan_repay_date" };

    public Integer getWms_cre_rev_water_repay_loan_line_id()
    {
        return wms_cre_rev_water_repay_loan_line_id;
    }

    public void setWms_cre_rev_water_repay_loan_line_id(Integer obj)
    {
        wms_cre_rev_water_repay_loan_line_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public Integer getWms_cre_rev_water_repay_loan_id()
    {
        return wms_cre_rev_water_repay_loan_id;
    }

    public void setWms_cre_rev_water_repay_loan_id(Integer obj)
    {
        wms_cre_rev_water_repay_loan_id = obj;
    }

    public String getRepay_name()
    {
        return repay_name;
    }

    public void setRepay_name(String obj)
    {
        repay_name = obj;
    }

    public String getRepay_date()
    {
        return repay_date;
    }

    public void setRepay_date(String obj)
    {
        repay_date = obj;
    }

    public String getRepay_account()
    {
        return repay_account;
    }

    public void setRepay_account(String obj)
    {
        repay_account = obj;
    }

    public String getIs_overdue()
    {
        return is_overdue;
    }

    public void setIs_overdue(String obj)
    {
        is_overdue = obj;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String obj)
    {
        remark = obj;
    }

    public String getOverdue_days()
    {
        return overdue_days;
    }

    public void setOverdue_days(String obj)
    {
        overdue_days = obj;
    }

    public String getLate_fee()
    {
        return late_fee;
    }

    public void setLate_fee(String obj)
    {
        late_fee = obj;
    }

    public String getLoan_date()
    {
        return loan_date;
    }

    public void setLoan_date(String obj)
    {
        loan_date = obj;
    }

    public String getLoan_amount()
    {
        return loan_amount;
    }

    public void setLoan_amount(String obj)
    {
        loan_amount = obj;
    }

    public String getLoan_repay_date()
    {
        return loan_repay_date;
    }

    public void setLoan_repay_date(String obj)
    {
        loan_repay_date = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_water_repay_loan_line_id", this.wms_cre_rev_water_repay_loan_line_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cre_rev_water_repay_loan_id", this.wms_cre_rev_water_repay_loan_id);
        paramMap.put("repay_name", this.repay_name);
        paramMap.put("repay_date", this.repay_date);
        paramMap.put("repay_account", this.repay_account);
        paramMap.put("is_overdue", this.is_overdue);
        paramMap.put("remark", this.remark);
        paramMap.put("overdue_days", this.overdue_days);
        paramMap.put("late_fee", this.late_fee);
        paramMap.put("loan_date", this.loan_date);
        paramMap.put("loan_amount", this.loan_amount);
        paramMap.put("loan_repay_date", this.loan_repay_date);
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
}