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

public class WmsCreRevCertificateModel implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_certificate_model_id;

    private Integer wms_cre_credit_head_id;

    private Integer total_loan_num;

    private Integer unpay_loan_num;

    private java.math.BigDecimal unpay_loan_amount;

    private java.math.BigDecimal unpay_loan_balance;

    private java.math.BigDecimal monthly_payments;

    private Integer overdue_num;

    private Integer overdue_month_num;

    private java.math.BigDecimal overdue_most_amount;

    private Integer overdue_most_month;

    private Integer three_apply_time;

    private Integer six_apply_time;

    private Integer year_apply_time;

    private Integer is_guarantee;

    private Integer guarantee_amount;

    private Integer credit_card_num;

    private java.math.BigDecimal credit_card_total_amount;

    private java.math.BigDecimal credit_card_most_amount;

    private java.math.BigDecimal credit_card_lest_amount;

    private java.math.BigDecimal credit_have_amount;

    private java.math.BigDecimal credit_avg_amount;

    private Integer overdue_card_num;

    private Integer overdue_card_pages;

    private java.math.BigDecimal overdue_card_most_amount;

    private Integer overdue_card_most_month;

    private Integer three_overdue_card_num;

    private Integer six_overdue_card_num;

    private Integer two_year_overdue_card_num;

    private java.math.BigDecimal cur_overdue_card_amount;

    private java.math.BigDecimal two_year_overdue_rate;

    private java.math.BigDecimal one_year_overdue_rate;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_certificate_model_id" };

    private static String[] columnNameArr = { "wms_cre_rev_certificate_model_id", "wms_cre_credit_head_id",
                                             "total_loan_num", "unpay_loan_num", "unpay_loan_amount",
                                             "unpay_loan_balance", "monthly_payments", "overdue_num",
                                             "overdue_month_num", "overdue_most_amount", "overdue_most_month",
                                             "three_apply_time", "six_apply_time", "year_apply_time", "is_guarantee",
                                             "guarantee_amount", "credit_card_num", "credit_card_total_amount",
                                             "credit_card_most_amount", "credit_card_lest_amount",
                                             "credit_have_amount", "credit_avg_amount", "overdue_card_num",
                                             "overdue_card_pages", "overdue_card_most_amount",
                                             "overdue_card_most_month", "three_overdue_card_num",
                                             "six_overdue_card_num", "two_year_overdue_card_num",
                                             "cur_overdue_card_amount", "two_year_overdue_rate",
                                             "one_year_overdue_rate", "isExcludePKFlag" };

    public Integer getWms_cre_rev_certificate_model_id()
    {
        return wms_cre_rev_certificate_model_id;
    }

    public void setWms_cre_rev_certificate_model_id(Integer obj)
    {
        wms_cre_rev_certificate_model_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public Integer getTotal_loan_num()
    {
        return total_loan_num;
    }

    public void setTotal_loan_num(Integer obj)
    {
        total_loan_num = obj;
    }

    public Integer getUnpay_loan_num()
    {
        return unpay_loan_num;
    }

    public void setUnpay_loan_num(Integer obj)
    {
        unpay_loan_num = obj;
    }

    public java.math.BigDecimal getUnpay_loan_amount()
    {
        return unpay_loan_amount;
    }

    public void setUnpay_loan_amount(java.math.BigDecimal obj)
    {
        unpay_loan_amount = obj;
    }

    public java.math.BigDecimal getUnpay_loan_balance()
    {
        return unpay_loan_balance;
    }

    public void setUnpay_loan_balance(java.math.BigDecimal obj)
    {
        unpay_loan_balance = obj;
    }

    public java.math.BigDecimal getMonthly_payments()
    {
        return monthly_payments;
    }

    public void setMonthly_payments(java.math.BigDecimal obj)
    {
        monthly_payments = obj;
    }

    public Integer getOverdue_num()
    {
        return overdue_num;
    }

    public void setOverdue_num(Integer obj)
    {
        overdue_num = obj;
    }

    public Integer getOverdue_month_num()
    {
        return overdue_month_num;
    }

    public void setOverdue_month_num(Integer obj)
    {
        overdue_month_num = obj;
    }

    public java.math.BigDecimal getOverdue_most_amount()
    {
        return overdue_most_amount;
    }

    public void setOverdue_most_amount(java.math.BigDecimal obj)
    {
        overdue_most_amount = obj;
    }

    public Integer getOverdue_most_month()
    {
        return overdue_most_month;
    }

    public void setOverdue_most_month(Integer obj)
    {
        overdue_most_month = obj;
    }

    public Integer getThree_apply_time()
    {
        return three_apply_time;
    }

    public void setThree_apply_time(Integer obj)
    {
        three_apply_time = obj;
    }

    public Integer getSix_apply_time()
    {
        return six_apply_time;
    }

    public void setSix_apply_time(Integer obj)
    {
        six_apply_time = obj;
    }

    public Integer getYear_apply_time()
    {
        return year_apply_time;
    }

    public void setYear_apply_time(Integer obj)
    {
        year_apply_time = obj;
    }

    public Integer getIs_guarantee()
    {
        return is_guarantee;
    }

    public void setIs_guarantee(Integer obj)
    {
        is_guarantee = obj;
    }

    public Integer getGuarantee_amount()
    {
        return guarantee_amount;
    }

    public void setGuarantee_amount(Integer obj)
    {
        guarantee_amount = obj;
    }

    public Integer getCredit_card_num()
    {
        return credit_card_num;
    }

    public void setCredit_card_num(Integer obj)
    {
        credit_card_num = obj;
    }

    public java.math.BigDecimal getCredit_card_total_amount()
    {
        return credit_card_total_amount;
    }

    public void setCredit_card_total_amount(java.math.BigDecimal obj)
    {
        credit_card_total_amount = obj;
    }

    public java.math.BigDecimal getCredit_card_most_amount()
    {
        return credit_card_most_amount;
    }

    public void setCredit_card_most_amount(java.math.BigDecimal obj)
    {
        credit_card_most_amount = obj;
    }

    public java.math.BigDecimal getCredit_card_lest_amount()
    {
        return credit_card_lest_amount;
    }

    public void setCredit_card_lest_amount(java.math.BigDecimal obj)
    {
        credit_card_lest_amount = obj;
    }

    public java.math.BigDecimal getCredit_have_amount()
    {
        return credit_have_amount;
    }

    public void setCredit_have_amount(java.math.BigDecimal obj)
    {
        credit_have_amount = obj;
    }

    public java.math.BigDecimal getCredit_avg_amount()
    {
        return credit_avg_amount;
    }

    public void setCredit_avg_amount(java.math.BigDecimal obj)
    {
        credit_avg_amount = obj;
    }

    public Integer getOverdue_card_num()
    {
        return overdue_card_num;
    }

    public void setOverdue_card_num(Integer obj)
    {
        overdue_card_num = obj;
    }

    public Integer getOverdue_card_pages()
    {
        return overdue_card_pages;
    }

    public void setOverdue_card_pages(Integer obj)
    {
        overdue_card_pages = obj;
    }

    public java.math.BigDecimal getOverdue_card_most_amount()
    {
        return overdue_card_most_amount;
    }

    public void setOverdue_card_most_amount(java.math.BigDecimal obj)
    {
        overdue_card_most_amount = obj;
    }

    public Integer getOverdue_card_most_month()
    {
        return overdue_card_most_month;
    }

    public void setOverdue_card_most_month(Integer obj)
    {
        overdue_card_most_month = obj;
    }

    public Integer getThree_overdue_card_num()
    {
        return three_overdue_card_num;
    }

    public void setThree_overdue_card_num(Integer obj)
    {
        three_overdue_card_num = obj;
    }

    public Integer getSix_overdue_card_num()
    {
        return six_overdue_card_num;
    }

    public void setSix_overdue_card_num(Integer obj)
    {
        six_overdue_card_num = obj;
    }

    public Integer getTwo_year_overdue_card_num()
    {
        return two_year_overdue_card_num;
    }

    public void setTwo_year_overdue_card_num(Integer obj)
    {
        two_year_overdue_card_num = obj;
    }

    public java.math.BigDecimal getCur_overdue_card_amount()
    {
        return cur_overdue_card_amount;
    }

    public void setCur_overdue_card_amount(java.math.BigDecimal obj)
    {
        cur_overdue_card_amount = obj;
    }

    public java.math.BigDecimal getTwo_year_overdue_rate()
    {
        return two_year_overdue_rate;
    }

    public void setTwo_year_overdue_rate(java.math.BigDecimal obj)
    {
        two_year_overdue_rate = obj;
    }

    public java.math.BigDecimal getOne_year_overdue_rate()
    {
        return one_year_overdue_rate;
    }

    public void setOne_year_overdue_rate(java.math.BigDecimal obj)
    {
        one_year_overdue_rate = obj;
    }

    public boolean getgetIsExcludePKFlag()
    {
        return isExcludePKFlag;
    }

    public void setgetIsExcludePKFlag(boolean obj)
    {
        isExcludePKFlag = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_certificate_model_id", this.wms_cre_rev_certificate_model_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("total_loan_num", this.total_loan_num);
        paramMap.put("unpay_loan_num", this.unpay_loan_num);
        paramMap.put("unpay_loan_amount", this.unpay_loan_amount);
        paramMap.put("unpay_loan_balance", this.unpay_loan_balance);
        paramMap.put("monthly_payments", this.monthly_payments);
        paramMap.put("overdue_num", this.overdue_num);
        paramMap.put("overdue_month_num", this.overdue_month_num);
        paramMap.put("overdue_most_amount", this.overdue_most_amount);
        paramMap.put("overdue_most_month", this.overdue_most_month);
        paramMap.put("three_apply_time", this.three_apply_time);
        paramMap.put("six_apply_time", this.six_apply_time);
        paramMap.put("year_apply_time", this.year_apply_time);
        paramMap.put("is_guarantee", this.is_guarantee);
        paramMap.put("guarantee_amount", this.guarantee_amount);
        paramMap.put("credit_card_num", this.credit_card_num);
        paramMap.put("credit_card_total_amount", this.credit_card_total_amount);
        paramMap.put("credit_card_most_amount", this.credit_card_most_amount);
        paramMap.put("credit_card_lest_amount", this.credit_card_lest_amount);
        paramMap.put("credit_have_amount", this.credit_have_amount);
        paramMap.put("credit_avg_amount", this.credit_avg_amount);
        paramMap.put("overdue_card_num", this.overdue_card_num);
        paramMap.put("overdue_card_pages", this.overdue_card_pages);
        paramMap.put("overdue_card_most_amount", this.overdue_card_most_amount);
        paramMap.put("overdue_card_most_month", this.overdue_card_most_month);
        paramMap.put("three_overdue_card_num", this.three_overdue_card_num);
        paramMap.put("six_overdue_card_num", this.six_overdue_card_num);
        paramMap.put("two_year_overdue_card_num", this.two_year_overdue_card_num);
        paramMap.put("cur_overdue_card_amount", this.cur_overdue_card_amount);
        paramMap.put("two_year_overdue_rate", this.two_year_overdue_rate);
        paramMap.put("one_year_overdue_rate", this.one_year_overdue_rate);
        paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
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